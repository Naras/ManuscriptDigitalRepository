package com.indven.tools.bootstrap;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indven.framework.enums.UserStatusEnum;
import com.indven.framework.enums.UserTypeEnum;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.HibernateUtil;
import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.workflow.location.entity.LocationLevelMasterBean;
import com.indven.workflow.location.entity.LocationMasterBean;
import com.indven.framework.util.SecurePasswordUtil;

/**
 * Command-line bootstrapper that seeds the database with the minimum data set
 * (menus, roles, admin account) that the web-application expects.
 *
 * Run this class once after the schema is created but before logging into the
 * UI.
 */
public class BootstrapDataLoader {

	private final SessionFactory sessionFactory;

	private static final String ADMIN_ROLE_NAME = "administrator";

	private String adminLogin = "CTO@Samskriti.org";
	private String adminPassword = "CTO123$%^";

	private static final List<MenuSeed> MENU_SEEDS =
			/*
			 * Id, Defaultstatus, LeftPanelLink, MenuLevel, MenuLink, MenuName, MenuOrder,
			 * ParentId, RequestId, ShortKey, StatusMsg
			 */
			Arrays.asList(
					new MenuSeed(1L, "1", "Root", 0, "Root", "ROOT", 0, null, "0", null, "WASP Configuration"),
					new MenuSeed(101L, "1", "widget.leftprofile|widget.mybookmarks", 0, "/homePageAction.action",
							"Dashboard", 1, 1L, "1",
							null, "Home"),
					new MenuSeed(102L, "0", "", 0, "", "Role", 3, 1L, "102", null, "Role Management"),
					new MenuSeed(103L, "0", "", 0, "", "User", 2, 1L, "103", null, "User Management"),
					new MenuSeed(104L, "0", "", 0, "", "My Account", 4, 1L, "4", null, "My Account"),
					new MenuSeed(105L, "0", null, 0, "", "Documents", 1, 1L, "105", null, "Documents"),
					new MenuSeed(106L, "0", null, 0, "", "Lookup Management", 1, 1L, "106", null, "Lookup Management"),
					new MenuSeed(107L, "0", null, 0, "/goToReportPage.action", "Report", 1, 1L, "107", null, "Report"),
					new MenuSeed(10201L, "0", "", 1, "/displayMenuInfo.action", "New", 0, 102L, "10201", null,
							"Create Role"),
					new MenuSeed(10202L, "0", null, 1, "/showroleaction.action", "List All", 1, 102L, "10202", null,
							"Update Role"),
					new MenuSeed(10301L, "0", null, 1, "/addUserPageWithRoles.action", "New", 0, 103L, "10301", null,
							"Add User"),
					new MenuSeed(10302L, "0", null, 1, "/searchPageAction.action", "Search", 1, 103L, "10302", null,
							"Search Users"),
					new MenuSeed(10303L, "0", null, 1, "/generateResetPasswordIdPageAction.action", "Reset Password", 1,
							103L, "10303", null,
							"Reset password"),
					new MenuSeed(10401L, "0", "widget.leftprofile|widget.mybookmarks", 1, "/logoutaction.action",
							"Sign out", 1,
							104L, "10401", null, "Sign out"),
					new MenuSeed(10402L, "0", "widget.leftprofile|widget.mybookmarks", 1,
							"/changePasswordAction.action", "Change Password", 1,
							104L, "10402", null, "Change Password"),
					new MenuSeed(10501L, "0", null, 1, "/addManuscript.action", "New", 0, 105L, "10501", null,
							"Add Manuscript"),
					new MenuSeed(10502L, "0", null, 1, "/searchForManuscript.action", "Search", 1, 105L, "10502", null,
							"Search Manuscripts"),
					new MenuSeed(10601L, "0", null, 1, "/createSearchFormAction.action", "Language", 0, 106L, "10601",
							null,
							"Language"),
					new MenuSeed(10602L, "0", null, 1, "/createSearchFormAction.action", "Subject", 0, 106L, "10602",
							null,
							"Subject"),
					new MenuSeed(10603L, "0", null, 1, "/createSearchFormAction.action", "Script", 0, 106L, "10603",
							null,
							"Script"),
					new MenuSeed(10604L, "0", null, 1, "/createSearchFormAction.action", "Bundle", 0, 106L, "10604",
							null, ""),
					new MenuSeed(10605L, "0", null, 1, "/createSearchFormAction.action", "Tag", 0, 106L, "10605", null,
							""),
					new MenuSeed(10606L, "0", null, 1, "/createSearchFormAction.action", "Specific Category", 0, 106L,
							"10606", null, ""));

	private static final Set<Long> ADMIN_MENU_IDS = new HashSet<>(
			Arrays.asList(101L, 102L, 10201L, 10202L, 103L, 10301L, 10302L, 10303L, 104L, 10401L, 10402L, 105L, 10501L,
					10502L, 106L, 10601L, 10602L, 10603L, 10604L, 10605L, 10606L, 107L));

	public BootstrapDataLoader() {
		this(HibernateUtil.getSessionFactory());
	}

	BootstrapDataLoader(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {
		BootstrapDataLoader loader = new BootstrapDataLoader();
		if (args != null && args.length >= 2) {
			loader.adminLogin = args[0];
			loader.adminPassword = args[1];
		}
		loader.run();
		HibernateUtil.getSessionFactory().close();
	}

	public void setAdminCredentials(String login, String password) {
		this.adminLogin = login;
		this.adminPassword = password;
	}

	public void run() {
		Session session = sessionFactory.openSession();
		try {
			java.sql.Connection connection = session.connection();
			java.sql.DatabaseMetaData metaData = connection.getMetaData();
			System.out.println("----------------------------");
			System.out.println("DEBUG: JDBC Connection Details:");
			System.out.println("URL: " + metaData.getURL());
			System.out.println("User: " + metaData.getUserName());
			System.out.println("----------------------------");
		} catch (Exception e) {
			System.err.println("DEBUG: Failed to get connection details");
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			seedMenuMaster(session);
			Long adminRoleId = seedAdminRole(session);
			seedAccessControl(session, adminRoleId);
			Long defaultLocationId = seedDefaultLocation(session);
			seedAdministrator(session, adminRoleId, defaultLocationId);
			tx.commit();
			System.out.println("Bootstrap complete.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Failed to bootstrap seed data", ex);
		} finally {
			session.close();
		}
	}

	private void seedMenuMaster(Session session) {
		for (MenuSeed seed : MENU_SEEDS) {
			if (recordExists(session, "omds_menumaster", "Id", seed.id)) {
				continue;
			}
			SQLQuery query = session.createSQLQuery(
					"INSERT INTO omds_menumaster (Id, DefaultStatus, LeftPanelLink, MenuLevel, MenuLink, MenuName, MenuOrder, ParentId, RequestId, ShortKey, StatusMsg) "
							+ "VALUES (:id, :defaultStatus, :leftPanelLink, :menuLevel, :menuLink, :menuName, :menuOrder, :parentId, :requestId, :shortKey, :statusMsg)");
			query.setParameter("id", seed.id);
			query.setParameter("defaultStatus", seed.defaultStatus);
			query.setParameter("leftPanelLink", seed.leftPanelLink);
			query.setParameter("menuLevel", seed.menuLevel);
			query.setParameter("menuLink", seed.menuLink);
			query.setParameter("menuName", seed.menuName);
			query.setParameter("menuOrder", seed.menuOrder);
			query.setParameter("parentId", seed.parentId);
			query.setParameter("requestId", seed.requestId);
			query.setParameter("shortKey", seed.shortKey);
			query.setParameter("statusMsg", seed.statusMsg);
			query.executeUpdate();
			System.out.printf("Inserted menu %s (%d)%n", seed.menuName, seed.id);
		}
	}

	private Long seedAdminRole(Session session) throws Exception {
		RoleMasterBean role = (RoleMasterBean) session
				.createQuery("from RoleMasterBean where lower(name) = :name")
				.setParameter("name", ADMIN_ROLE_NAME.toLowerCase())
				.uniqueResult();
		if (role != null) {
			return role.getId();
		}

		role = new RoleMasterBean();
		role.setName(ADMIN_ROLE_NAME);
		role.setDescription("System administrator");
		role.setIsDeleted(false);
		CustomBeanUtil.setBaseValues(role, false);
		session.save(role);
		System.out.println("Created administrator role");
		return role.getId();
	}

	private void seedAccessControl(Session session, Long roleId) {
		for (Long menuId : ADMIN_MENU_IDS) {
			BigInteger count = (BigInteger) session.createSQLQuery(
					"SELECT COUNT(1) FROM omds_accesscontrol WHERE RoleMasterFkId = :roleId AND MenuMasterFkId = :menuId")
					.setParameter("roleId", roleId)
					.setParameter("menuId", menuId)
					.uniqueResult();
			if (count != null && count.longValue() > 0) {
				continue;
			}
			AccessControlBean acl = new AccessControlBean();
			acl.setRoleMasterFkId(roleId);
			acl.setMenuMasterFkId(menuId);
			session.save(acl);
		}
		System.out.println("Ensured administrator access control entries");
	}

	private Long seedDefaultLocation(Session session) throws Exception {
		BigInteger locationCount = (BigInteger) session.createSQLQuery("SELECT COUNT(1) FROM wfl_locationmaster")
				.uniqueResult();
		if (locationCount != null && locationCount.longValue() > 0) {
			LocationMasterBean existing = (LocationMasterBean) session
					.createQuery("from LocationMasterBean order by id")
					.setMaxResults(1)
					.uniqueResult();
			return existing.getId();
		}

		LocationLevelMasterBean level = new LocationLevelMasterBean();
		level.setName("Root Level");
		level.setLevelNumber(1L);
		level.setDescription("Bootstrap Level");
		level.setIconImageName(null);
		session.save(level);

		LocationMasterBean location = new LocationMasterBean();
		location.setName("Global");
		location.setDescription("Bootstrap Location");
		location.setIsDeleted(false);
		location.setIsWorkflowEnabled(false);
		location.setLevelFkId(level.getId());
		location.setParentFkId(null);
		CustomBeanUtil.setBaseValues(location, false);
		session.save(location);
		System.out.println("Created default workflow location");
		return location.getId();
	}

	private void seedAdministrator(Session session, Long roleId, Long locationId) throws Exception {
		if (locationId == null) {
			locationId = seedDefaultLocation(session);
		}
		UserLoginDetailsBean existingUser = (UserLoginDetailsBean) session
				.createQuery("from UserLoginDetailsBean where lower(loginId) = :login")
				.setParameter("login", adminLogin.toLowerCase())
				.uniqueResult();
		if (existingUser != null) {
			System.out.println("Administrator account already present, skipping user seed");
			return;
		}

		EmployeeMasterBean employee = new EmployeeMasterBean();
		employee.setFirstName("System");
		employee.setLastName("Administrator");
		employee.setDateOfBirth(new Date());
		employee.setEmail(adminLogin);
		employee.setPhoneNumber("0000000000");
		employee.setAddress1("Bootstrap generated administrator");
		employee.setEmployeeType((short) 1);
		employee.setGender((short) 1);
		employee.setIsDeleted(false);
		CustomBeanUtil.setBaseValues(employee, false);
		session.save(employee);

		UserLoginDetailsBean adminLoginBean = new UserLoginDetailsBean();
		adminLoginBean.setLoginId(adminLogin);
		adminLoginBean.setPassword(SecurePasswordUtil.hashUserPassword(adminPassword, adminLogin));
		adminLoginBean.setRefrenceFkId(employee.getId());
		adminLoginBean.setStatus(UserStatusEnum.ACTIVE.getValue());
		adminLoginBean.setType(UserTypeEnum.ADMIN.getValue());
		adminLoginBean.setIsDeleted(0);
		CustomBeanUtil.setBaseValues(adminLoginBean, false);
		session.save(adminLoginBean);

		UserRoleDetailsBean roleDetails = new UserRoleDetailsBean();
		roleDetails.setRoleMasterFkId(roleId);
		roleDetails.setUserLoginDetailsFkId(adminLoginBean.getId());
		roleDetails.setLocationMasterFkId(locationId);
		session.save(roleDetails);

		UserRoleDetailsBootstrapBean OmdsUserRoleDetails = new UserRoleDetailsBootstrapBean();
		OmdsUserRoleDetails.setRoleMasterFkId(roleId);
		OmdsUserRoleDetails.setUserLoginDetailsFkId(adminLoginBean.getId());
		session.save(OmdsUserRoleDetails);

		System.out.println("Created administrator account (" + adminLogin + "/" + adminPassword + "}");
	}

	private boolean recordExists(Session session, String table, String column, Object value) {
		BigInteger count = (BigInteger) session
				.createSQLQuery("SELECT COUNT(1) FROM " + table + " WHERE " + column + " = :value")
				.setParameter("value", value)
				.uniqueResult();
		return count != null && count.longValue() > 0;
	}

	private static final class MenuSeed {
		private final Long id;
		private final String defaultStatus;
		private final String leftPanelLink;
		private final Integer menuLevel;
		private final String menuLink;
		private final String menuName;
		private final Integer menuOrder;
		private final Long parentId;
		private final String requestId;
		private final String shortKey;
		private final String statusMsg;

		private MenuSeed(Long id, String defaultStatus, String leftPanelLink, Integer menuLevel, String menuLink,
				String menuName, Integer menuOrder, Long parentId, String requestId, String shortKey,
				String statusMsg) {
			this.id = id;
			this.defaultStatus = defaultStatus;
			this.leftPanelLink = leftPanelLink;
			this.menuLevel = menuLevel;
			this.menuLink = menuLink;
			this.menuName = menuName;
			this.menuOrder = menuOrder;
			this.parentId = parentId;
			this.requestId = requestId;
			this.shortKey = shortKey;
			this.statusMsg = statusMsg;
		}
	}
}
