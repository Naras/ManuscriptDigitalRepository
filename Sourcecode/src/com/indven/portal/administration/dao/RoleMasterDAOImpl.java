package com.indven.portal.administration.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indven.framework.dao.BaseCRUDDAOImpl;
import com.indven.framework.entity.BaseEntityBean;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.vo.RoleMasterVO;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.exception.MenuInfoException;

public class RoleMasterDAOImpl extends BaseCRUDDAOImpl<RoleMasterBean>{

	private static IndvenLogger logger = IndvenLogger.getInstance(RoleMasterDAOImpl.class);

	public RoleMasterDAOImpl() {
		super(RoleMasterBean.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<RoleMasterBean> getEntityBeanType() {

		return super.getEntityBeanType();
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#save(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public RoleMasterBean save(RoleMasterBean entity)
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#update(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public RoleMasterBean update(RoleMasterBean entity)
			throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	@Override
	public RoleMasterBean findById(Long id) throws AdministrationException{
		try {

			return super.findById(id);

		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_FIND_ROLE_MASTER_DETAILS, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_FIND_ROLE_MASTER_DETAILS, e);
		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_FIND_ROLE_MASTER_DETAILS, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_FIND_ROLE_MASTER_DETAILS, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#purge(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public void purge(RoleMasterBean entity) throws HibernateException,
			Exception {
		// TODO Auto-generated method stub
		super.purge(entity);
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMasterBean> findAll() throws AdministrationException {
		
		Session session = null;
		List<RoleMasterBean> entities = null;
		Query query = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String entityClassName = getEntityBeanType().getName();
			String queryString = "from " + entityClassName + " where isDeleted =:isDeleted ORDER BY name";
			query = session.createQuery(queryString);
			query.setParameter("isDeleted", false);
			entities = query.list();
//			 entities = session.createCriteria(RoleMasterBean.class).add(Restrictions.eq("isDeleted", false)).list();;
			tx.commit();
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_LIST_ALL_ROLE_MASTER, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_LIST_ALL_ROLE_MASTER, e);
		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_LIST_ALL_ROLE_MASTER, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_LIST_ALL_ROLE_MASTER, e);
		} finally {
			session.close();
		}
		 return entities;
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#saveOrUpdate(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public RoleMasterBean saveOrUpdate(RoleMasterBean entity) throws AdministrationException {
		try {
			return super.saveOrUpdate(entity);
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_SAVE_OR_UPDATE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_SAVE_OR_UPDATE_USER, e);

		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_SAVE_OR_UPDATE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_SAVE_OR_UPDATE_USER, e);
		}
	}
	
	/**
	 * Saves the role information
	 * Also saves the corresponding access control info
	 * @param entity
	 * @param accessControlBeans
	 * @return
	 * @throws MenuInfoException
	 */
	public RoleMasterBean saveRoleInfo(RoleMasterBean entity, List<AccessControlBean> accessControlBeans) throws MenuInfoException, AdministrationException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx=null;	
		String status = "error";
		Query query = null;
		try {
			tx = session.beginTransaction();
			
			if(entity!=null && entity.getId()==0){
				query = session.createQuery("From RoleMasterBean rmb where rmb.name = :name");
				query.setParameter("name", entity.getName());
				if(query.list()!=null && query.list().size()>0){
					throw new AdministrationException(AdministrationException.VOILATE_UNIQUE_ROLE_NAME_CONSTRAINT);
				}
			}else if(entity!=null && entity.getId()!=0){
				query = session.createQuery("Select rmb.name From RoleMasterBean rmb where rmb.id != :id");
//				query.setParameter("name", entity.getName());
				query.setParameter("id", entity.getId());
				if(query.list()!=null && query.list().size()>0 && query.list().contains(entity.getName()) ){
					throw new AdministrationException(AdministrationException.VOILATE_UNIQUE_ROLE_NAME_CONSTRAINT);
				}
			}
			{
				session.saveOrUpdate(entity); //Saves the role information
				
				if(accessControlBeans.size() > 0) {
					query = session.createQuery("delete from AccessControlBean accessControlBean where accessControlBean.roleMasterFkId = :roleMasterFkId");
					query.setParameter("roleMasterFkId", entity.getId());
		   			query.executeUpdate();
		   			for (AccessControlBean accessControlBean : accessControlBeans) {
		   				accessControlBean.setRoleMasterFkId(entity.getId());
		   				session.save(accessControlBean); //Saves the the menu id for the current role
		   			}
				}
	   			
	   			tx.commit();
	   			status = "success";
			}
		}catch (AdministrationException e) {
			tx.rollback();
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw e;
		}catch (HibernateException e) {
			tx.rollback();
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);

		} catch (Exception e) {
			tx.rollback();
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		}finally {
			session.close();
		}
		
		return entity;
	}
	
	public int markedAsDeleted(Long id) throws AdministrationException {

		Session session = null;
		Transaction tx = null;
		int deleteStatus = 0;
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();

//			if (epmConstraintsCheckerUtilDAO.checkAllDeleteConstraints(session, id,
//					EPMDeleteConstraints.PRE_CUSTOMER_MASTER_DELETE_CHECK)) {
//				deleteStatus = super.markedAsDeleted(id);
//			}
			tx = session.beginTransaction();
			String entityClassName = getEntityBeanType().getName();
			String queryString = "select rowVersion  from " + entityClassName + " where id = :id";

			Query qry = session.createQuery(queryString);
			qry.setParameter("id", id);

			Integer rowVersion = (Integer) qry.list().get(0) + 1;
			String markAsDeleteHql = "update " + entityClassName + " set isDeleted = true , rowVersion =:rowVersion  where id=:id";
			Query query = session.createQuery(markAsDeleteHql);
			query.setParameter("id", id);
			query.setParameter("rowVersion", rowVersion);
			deleteStatus = query.executeUpdate();

			tx.commit();
			return deleteStatus;

		} catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
		}catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
		}finally{
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#setBaseValues(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public BaseEntityBean setBaseValues(BaseEntityBean entity) throws Exception {
		// TODO Auto-generated method stub
		return super.setBaseValues(entity);
	}
	
	/**
	 * finds the role associated with the given user
	 * @param id
	 * @return UserRoleDetailsBean
	 * @throws AdministrationException
	 */
	@SuppressWarnings("unchecked")
	public UserRoleDetailsBean findRoleByUser(Long id) throws AdministrationException {
		Session session = null;
		Transaction tx = null;
		List<UserRoleDetailsBean> userRoleDetailsBeans = new ArrayList<UserRoleDetailsBean>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			userRoleDetailsBeans = session.createQuery("from UserRoleDetailsBean role").list();
			userRoleDetailsBeans = session.createQuery("from UserRoleDetailsBean role where role.userLoginDetailsFkId =" + id.longValue()).list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
		}catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
		}finally{
			session.close();
		}
		if(userRoleDetailsBeans.size() > 0) {
			return userRoleDetailsBeans.get(0);
		}
		return null;
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<Long> findRoleIdsByUserId(Long id) throws AdministrationException {
		Session session = null;
		Transaction tx = null;
		List<UserRoleDetailsBean> userRoleDetailsBeans = new ArrayList<UserRoleDetailsBean>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			userRoleDetailsBeans = session.createQuery("from UserRoleDetailsBean role where role.userLoginDetailsFkId =" + id.longValue()).list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
		}catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_MARK_ROLE_MASTER_DELETED, e);
		}finally{
			session.close();
		}

		List<Long> roleIdsList = new ArrayList<>();
		for(UserRoleDetailsBean bean : userRoleDetailsBeans) {
			roleIdsList.add(bean.getRoleMasterFkId());
		}
		
		return roleIdsList;
	}
	*/
	
	/**
	 * @author Deba Prasad
	 * @Date : 13th Feb 2014
	 * 
	 * This method will take the user login Id as parameter which is the id for that user in UserLoginDetailsBean and
	 * will fetch the list of RoleMasterBean entities associated with the user in UserRoleDetailsBean.
	 * 
	 * @param userId
	 * @return
	 * @throws AdministrationException
	 */
	@SuppressWarnings("unchecked")
	public List<RoleMasterBean> getRoleListForUser(Long userId) throws AdministrationException {
		List<RoleMasterBean> roleList = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		List<UserRoleDetailsBean> userRoleDetailsBeans = new ArrayList<UserRoleDetailsBean>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			userRoleDetailsBeans = session.createQuery("from UserRoleDetailsBean role where role.userLoginDetailsFkId =" + userId.longValue()).list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_FETCH_USER_ROLE_DETAILS, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_FETCH_USER_ROLE_DETAILS, e);
		}catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_FETCH_USER_ROLE_DETAILS, e);
			throw new AdministrationException(
					AdministrationException.UNABLE_TO_FETCH_USER_ROLE_DETAILS, e);
		}finally{
			session.close();
		}
		for(int i=0 ; i< userRoleDetailsBeans.size() ; i++) {
			roleList.add(userRoleDetailsBeans.get(i).getRoleMasterFKObj());
		}
		return roleList;
	}

}
