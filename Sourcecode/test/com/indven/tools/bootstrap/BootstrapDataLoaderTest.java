package com.indven.tools.bootstrap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.entity.MenuMasterBean;
import com.indven.workflow.location.entity.LocationLevelMasterBean;
import com.indven.workflow.location.entity.LocationMasterBean;

/**
 * Basic smoke test for {@link BootstrapDataLoader}. Uses an in-memory H2 schema
 * so the bootstrapper can be exercised without touching a real MySQL instance.
 *
 * The test is skipped automatically if the H2 driver is missing from the
 * classpath.
 */
public class BootstrapDataLoaderTest {

	private static final int EXPECTED_MIN_MENU_COUNT = 13;
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void setUpClass() throws Exception {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Skipping BootstrapDataLoaderTest because H2 driver is not on the classpath.");
			Assume.assumeTrue(false);
		}

		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(MenuMasterBean.class);
		configuration.addAnnotatedClass(AccessControlBean.class);
		configuration.addAnnotatedClass(RoleMasterBean.class);
		configuration.addAnnotatedClass(UserLoginDetailsBean.class);
		configuration.addAnnotatedClass(UserRoleDetailsBean.class);
		configuration.addAnnotatedClass(EmployeeMasterBean.class);
		configuration.addAnnotatedClass(LocationLevelMasterBean.class);
		configuration.addAnnotatedClass(LocationMasterBean.class);
		configuration.addAnnotatedClass(UserRoleDetailsBootstrapBean.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:mdr_bootstrap;DB_CLOSE_DELAY=-1;MODE=MYSQL");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		configuration.setProperty("hibernate.show_sql", "false");
		configuration.setProperty("hibernate.format_sql", "false");

		sessionFactory = configuration.buildSessionFactory();
	}

	@AfterClass
	public static void tearDownClass() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	@Test
	public void seedsMenusRolesAndAdminUser() throws Exception {
		BootstrapDataLoader loader = new BootstrapDataLoader(sessionFactory);
		loader.run();

		Session session = sessionFactory.openSession();
		try {
			Long menuCount = (Long) session.createQuery("select count(m) from MenuMasterBean m").uniqueResult();
			assertTrue("Bootstrapper should insert the default menu tree",
					menuCount != null && menuCount >= EXPECTED_MIN_MENU_COUNT);

			RoleMasterBean role = (RoleMasterBean) session
					.createQuery("from RoleMasterBean where name = :name")
					.setParameter("name", "administrator")
					.uniqueResult();
			assertNotNull("Administrator role must exist", role);

			UserLoginDetailsBean adminLogin = (UserLoginDetailsBean) session
					.createQuery("from UserLoginDetailsBean where loginId = :login")
					.setParameter("login", "CTO@samskriti.org")
					.uniqueResult();
			assertNotNull("Administrator login must exist", adminLogin);

			EmployeeMasterBean employee = (EmployeeMasterBean) session
					.get(EmployeeMasterBean.class, adminLogin.getRefrenceFkId());
			assertNotNull("Employee linked to admin login must exist", employee);

			Long accessControlCount = (Long) session.createQuery("select count(a) from AccessControlBean a").uniqueResult();
			assertTrue("Administrator role should get menu privileges",
					accessControlCount != null && accessControlCount >= 10);

			Long roleMappingCount = (Long) session.createQuery("select count(r) from UserRoleDetailsBean r").uniqueResult();
			assertTrue("Admin user should be linked to the administrator role",
					roleMappingCount != null && roleMappingCount >= 1);
		} finally {
			session.close();
		}
	}
}

