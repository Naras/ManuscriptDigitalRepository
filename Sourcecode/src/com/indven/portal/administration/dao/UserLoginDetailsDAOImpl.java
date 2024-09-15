/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	UserLoginDetailsDAOImpl.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	11th April 2013
 * Brief Description					:
 * Author								: 	Saurabh
 * Revision History
 *
 *************************************************************************************************************************/
package com.indven.portal.administration.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.dao.BaseCRUDDAOImpl;
import com.indven.framework.enums.UserStatusEnum;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.framework.util.SecurePasswordUtil;
import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.menu.entity.MenuMasterBean;

/**
 * @author Saurabh
 *
 */
public class UserLoginDetailsDAOImpl extends BaseCRUDDAOImpl<UserLoginDetailsBean> {

	private static IndvenLogger logger = IndvenLogger.getInstance(UserLoginDetailsDAOImpl.class);


	public UserLoginDetailsDAOImpl() {
		super(UserLoginDetailsBean.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<UserLoginDetailsBean> getEntityBeanType() {

		return super.getEntityBeanType();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#save(java.lang.Object)
	 */
	/**
	 * This method is used to save the User Login details into database.
	 *
	 * @param entity
	 *            The object of UserLoginDetailsBean that contains the values to be stored into
	 *            DataBase.
	 * @throws AdministrationException
	 * @return returns the saved entity.
	 */
	@Override
	public UserLoginDetailsBean save(UserLoginDetailsBean entity) throws AdministrationException {

		try {
			return super.save(entity);
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_SAVE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_SAVE_USER, e);

		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_SAVE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_SAVE_USER, e);
		}

	}



	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#update(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public UserLoginDetailsBean update(UserLoginDetailsBean entity) throws AdministrationException {
		try {
			return super.update(entity);
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_UPDATE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_UPDATE_USER, e);

		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_UPDATE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_UPDATE_USER, e);
		}

	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	@Override
	public UserLoginDetailsBean findById(Long id) throws AdministrationException {
		try {
			return super.findById(id);
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_FIND_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_FIND_USER, e);

		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_FIND_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_FIND_USER, e);
		}

	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#purge(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public void purge(UserLoginDetailsBean entity) throws AdministrationException {
		try {
			super.purge(entity);
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_DELETE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_DELETE_USER, e);

		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_DELETE_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_DELETE_USER, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#saveOrUpdate(com.indven.framework.entity.BaseEntityBean)
	 */
	@Override
	public UserLoginDetailsBean saveOrUpdate(UserLoginDetailsBean entity)	throws AdministrationException {
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

	@SuppressWarnings("unchecked")
	public List<UserLoginDetailsBean> findAll() throws AdministrationException {
		List<UserLoginDetailsBean> userList = new ArrayList<UserLoginDetailsBean>();

		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
		 	Query query = session.createQuery("from UserLoginDetailsBean");

		 	userList = query.list();
		}catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_LIST_ALL_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LIST_ALL_USER, e);

		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_LIST_ALL_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LIST_ALL_USER, e);
		}
		return userList;
	}


	/**
	 * This method performs the DAO operation for Login action and if the requested record with supplied loginName and password is
	 * present in the database then it updates the last login time of the record as per current date and time in the database.
	 *
	 * @param userInfoBean
	 *            The object of UserInfoBean.
	 * @return userInfoBean The object of UserInfoBean with the values for the record that we get having the supplied loginName and
	 *         password
	 * @throws UserInfoException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> handleLogin(UserLoginDetailsBean oldUserInfoBean) throws AdministrationException {
		Session session = null;
		Query query = null;
		List<UserLoginDetailsBean> userInfoBeans = null;
		Map<String, Object> loginResultMap = new HashMap<>();
		String userName = "";
		Transaction tx = null;
		String queryString = "FROM UserLoginDetailsBean userLoginDetailsBean WHERE userLoginDetailsBean.loginId= :loginId";
		UserLoginDetailsBean userInfoBean = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session.createQuery(queryString);
			query.setParameter("loginId", oldUserInfoBean.getLoginId());

			userInfoBeans = query.list();
			
			if (userInfoBeans.size() > 0) {
				userInfoBean = (UserLoginDetailsBean) query.list().get(0);
				if(SecurePasswordUtil.checkUserPassword(oldUserInfoBean.getPassword(), oldUserInfoBean.getLoginId(), userInfoBean.getPassword())){			
					if(userInfoBean.getIsDeleted() == 1){
						throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER_NOT_ACTIVE);
					}
				}else{
					throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_INVALID_DETAILS);
				}
			} else {
				throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_INVALID_DETAILS);
			}
			
			query = session.createQuery("from EmployeeMasterBean emp where emp.id = :empId");
			query.setParameter("empId", userInfoBean.getRefrenceFkId());
			EmployeeMasterBean emp = (EmployeeMasterBean) query.uniqueResult();
			userName = emp.getFirstName()+" "+emp.getLastName();
			tx.commit();
		} catch (AdministrationException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(AdministrationException.UNABLE_TO_LOGIN_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER, e);
		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_LOGIN_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER, e);
		} finally {
			session.close();
		}
	//	return userInfoBean;
		loginResultMap.put("userBean", userInfoBean);
		loginResultMap.put("userName", userName);
		return loginResultMap;
	}

	/**
	 * This method performs the DAO operation for change password action and if the old password is correct then it will replace the old password with new 
	 * password
	 *
	 * @param loginId
	 *            :This is id of current login user
	 * @param oldPassword
	 *            :This is the old password which will come from request
	 * @param newPassword
	 *            :This is the new password which will come from request  
	 * @return This method will return the integer value which give information that password is updated for not 
	 * @throws AdministrationException
	 */
	public int changePassword(String loginId, String oldPassword,String newPassword) throws AdministrationException {

		Session session = null;
		Transaction tx = null;
		Query q = null;
		int i=0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String queryString = "FROM UserLoginDetailsBean userLoginDetailsBean WHERE userLoginDetailsBean.loginId= :loginId ";
			q = session.createQuery(queryString);
			q.setParameter("loginId",loginId);
			
			UserLoginDetailsBean userInfoBean = null;
			userInfoBean = (UserLoginDetailsBean) q.uniqueResult();
			if (userInfoBean != null) {			
				if(SecurePasswordUtil.checkUserPassword(oldPassword, loginId, userInfoBean.getPassword())){				
					if(userInfoBean.getIsDeleted() == 1){
						throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER_NOT_ACTIVE);
					}else{
						String changePasswordQuery = "update UserLoginDetailsBean set password=? where loginId=? ";
						q = session.createQuery(changePasswordQuery);
						String newSecurePassword = SecurePasswordUtil.hashUserPassword(newPassword, userInfoBean.getLoginId());
						q.setParameter(0,newSecurePassword );
					    q.setParameter(1,loginId );
						i=q.executeUpdate();
					}				
				}else{
					throw new AdministrationException(AdministrationException.WORNG_OLD_PASSWORD);
				}
			}else {
				throw new AdministrationException(AdministrationException.UNABLE_TO_CHANGE_PASSWORD);
			}
			tx.commit();
		}catch (AdministrationException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_CHANGE_PASSWORD, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_CHANGE_PASSWORD, e);

		} catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_CHANGE_PASSWORD, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_CHANGE_PASSWORD, e);
		} finally {
			session.close();
		}
		return i;
	}
	
	/**
	 * This method performs the DAO operation for update user status action 
	 * 
	 * @param idOfUser
	 *            :This is id of current login user
	 * @param userStatus
	 *            :This is status of user         
	 * @return This method will return the integer value which give information that status is updated for not
	 * 
	 * @throws AdministrationException
	 */
	public int updateUserStatus(Long idOfUser , UserStatusEnum userStatus) throws AdministrationException {

		Session session = null;
		Transaction tx = null;
		int i=0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String updateStatusQuery = "update UserLoginDetailsBean set status=? where id=?";
			Query q = session.createQuery(updateStatusQuery);
			q.setParameter(0,userStatus.getValue() );
		    q.setParameter(1,idOfUser );
			i=q.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_UPDATE_USER_STATUS, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_UPDATE_USER_STATUS, e);
		} catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_UPDATE_USER_STATUS, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_UPDATE_USER_STATUS, e);
		} finally {
			session.close();
		}
		return i;
	}
	
	/**
	 * This method performs the DAO operation for reset password action and if reset password action is come with in 2 hours after getting reset password id 
	 * else it will throw an exception 
	 * 
	 * @param userInfoVO
	 *            :This is object of user          
	 * @return This method will return the integer value which give information that password is reseted or not
	 * 
	 * @throws AdministrationException
	 */
	public int resetPassword(UserInfoVO userInfoVO) throws AdministrationException {

		Session session = null;
		Transaction tx = null;
		int i=0;
		Query q = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String queryString = "FROM UserLoginDetailsBean userLoginDetailsBean WHERE userLoginDetailsBean.loginId= :loginId and userLoginDetailsBean.resetPasswordId= :resetPasswordId";
			q = session.createQuery(queryString);
			q.setParameter("loginId",userInfoVO.getLoginName());
			q.setParameter("resetPasswordId",userInfoVO.getResetPasswordId());
			
			UserLoginDetailsBean userInfoBean = null;
			userInfoBean = (UserLoginDetailsBean) q.uniqueResult();			
			if (userInfoBean != null) {
				long diffInMillis = Math.abs(new Date().getTime() - userInfoBean.getGenratedResetPasswordDate().getTime());
				if(diffInMillis <= 2*60*60*1000){
					String updateStatusQuery = "update UserLoginDetailsBean set password=? , modifiedDate =? , modifiedBy = ? , resetPasswordId=? where loginId=? and resetPasswordId=?";
					
					q = session.createQuery(updateStatusQuery);
					q.setParameter(0, SecurePasswordUtil.hashUserPassword(userInfoVO.getPassWord(), userInfoVO.getLoginName()) );
					q.setParameter(1, new Date());
				    q.setParameter(2,userInfoVO.getLoginName() );
				    q.setParameter(3,null );
				    q.setParameter(4,userInfoVO.getLoginName() );
				    q.setParameter(5,userInfoVO.getResetPasswordId() );
					i=q.executeUpdate();
				}else{
					throw new AdministrationException(AdministrationException.UNABLE_TO_RESET_PASSWORD_DETAILS);
				}
			}else{
				throw new AdministrationException(AdministrationException.UNABLE_TO_RESET_INVALID_DETAILS);
			}
			tx.commit();
		} catch (AdministrationException e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
			throw new AdministrationException(e.getMessage(), e);
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
		} catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
		} finally {
			session.close();
		}
		return i;
	}
	
	
	
	/*
	 * This method will take the employeeId as parameter and will return the full name of the employee combining both the first name and the last name.
	 */
	public String getEmpNameById(Long empId) throws AdministrationException {
		Session session = null;
		Transaction tx = null;
		String name = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			// TODO
			
//			String queryString = "FROM EmployeeMasterBean empBean where empBean.id = "+empId;
//			
////			EmployeeMasterBean empBean = (EmployeeMasterBean)session.createQuery(queryString).list().get(0);
////			name = empBean.getFirstName() + " " + empBean.getLastName();
			
			return name;
			
		}  catch (HibernateException e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
		} catch (Exception e) {
			tx.rollback();
			logger.error(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, e);
		} finally {
			session.close();
		}
	}
	
	
	
	/**
	 * This method is responsible for retrieving the menu details for the passed user Id
	 * 
	 * @param menuList An instantiated object which on successful completion will hold the list of menu for the user
	 * @param userLoginDetailsFkId The User Id for whom the menu needs to be retrieved
	 * @return true if the menu details were retrieved successfully, false otherwise
	 * @throws AdministrationException
	 */
	public boolean isMenuDetailsRetrieved(ArrayList<MenuMasterBean> menuList, Long userLoginDetailsFkId) throws AdministrationException {

		boolean isSuccess = false;
		Session session = null;
		Query query = null;
		String queryString = "SELECT menuDetailsBean FROM MenuMasterBean menuDetailsBean , AccessControlBean accessControlBean " +
				" , UserRoleDetailsBean userRoleDetailsBean" +
				" WHERE " +
				" accessControlBean.menuMasterFkId = menuDetailsBean.id " +
				" and accessControlBean.roleMasterFkId = userRoleDetailsBean.roleMasterFkId " +
				" and userRoleDetailsBean.userLoginDetailsFkId = :userLoginDetailsFkId" +
				" and menuDetailsBean.parentId= :parentId ";
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			query = session.createQuery(queryString);
			query.setParameter("userLoginDetailsFkId", userLoginDetailsFkId);
			
			//Passing 1L as parentId here as the root menu is with Id 1 
			getMenuDetails(menuList,1L,query);
			
			isSuccess = true;
			
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_LOGIN_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER, e);
		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_LOGIN_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER, e);
		} finally {
			session.close();
		}
		
		return isSuccess;	
		
	}
	
	/**
	 * This is a recursive method used to retrieve the menu details
	 * 
	 * @param menuList  menuList An instantiated object which on successful completion will have the child menus of the passed parent added
	 * @param parentId the parent id for which the child menu's needs to be retrieved
	 * @param query The Hibernate {@link Query} object to be executed by just setting the parentid
	 * @throws HibernateException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void getMenuDetails(ArrayList<MenuMasterBean> menuList, Long parentId, Query query) throws HibernateException , Exception{
		
		List<MenuMasterBean> menuInfoBeans = null;		

		query.setParameter("parentId", parentId);
		
		menuInfoBeans = query.list();
		
		for(MenuMasterBean menuInfoBean : menuInfoBeans){
			menuList.add(menuInfoBean);
			getMenuDetails(menuList, menuInfoBean.getId() ,query);
		}
		
	}
	
	/**
	 * Finds the user through its refrence foreign key, which is the employee table id
	 * @param refrenceFkId
	 * @return UserLoginDetailsBean
	 * @throws AdministrationException
	 */
	public UserLoginDetailsBean findByRefrenceFkId(Long refrenceFkId) throws AdministrationException {
		Session session = null;
		Transaction tx = null;
		List<UserLoginDetailsBean> userLoginDetailsBeans = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from UserLoginDetailsBean user where user.refrenceFkId = :refrenceFkId");
			query.setParameter("refrenceFkId", refrenceFkId);
			userLoginDetailsBeans = query.list();
			
			tx.commit();
		} catch (HibernateException e) {
			logger.error(AdministrationException.UNABLE_TO_LOGIN_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER, e);
		} catch (Exception e) {
			logger.error(AdministrationException.UNABLE_TO_LOGIN_USER, e);
			throw new AdministrationException(AdministrationException.UNABLE_TO_LOGIN_USER, e);
		} finally {
			session.close();
		}
		
		return userLoginDetailsBeans.get(0);
	}

	
	/**
	 * @param userLoginId
	 * 
	 * This method returns the generated Reset password Id and set the generated Reset password Id,
	 * genratedResetPasswordDate to the database 
	 */
	public String generateResetPasswordId(String resetPasswordId,String userLoginId) throws AdministrationException {
		Query q=null;
		Session session = null;
		Transaction tx = null;
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		String queryString = "FROM UserLoginDetailsBean userLoginDetailsBean WHERE userLoginDetailsBean.loginId= :loginId";
		q = session.createQuery(queryString);
		q.setParameter("loginId",userLoginId);
		
		UserLoginDetailsBean userInfoBean = null;
		userInfoBean = (UserLoginDetailsBean) q.uniqueResult();
		if( userInfoBean!=null){
			if( userInfoBean.getIsDeleted()!=1){
		Query query = session.createQuery("update UserLoginDetailsBean ulogin set "
				+ "ulogin.resetPasswordId= :resetPasswordId,ulogin.genratedResetPasswordDate= :date "
				+ "where ulogin.loginId = :id");
		
		query.setParameter("id", userLoginId);
		query.setParameter("resetPasswordId", resetPasswordId);
		query.setParameter("date", new Date());
		query.executeUpdate();
			}
			else{
				throw new AdministrationException(AdministrationException.USER_DOES_NOT_EXIST);
			}
		}
		else{
			throw new AdministrationException(AdministrationException.USER_DOES_NOT_EXIST);
		}
		tx.commit();
		}catch (AdministrationException e) {
			tx.rollback();
			logger.error(AdministrationException.USER_DOES_NOT_EXIST, e);
			throw new AdministrationException(AdministrationException.USER_DOES_NOT_EXIST, e);
		}
		finally{
		session.close();
		}
		return resetPasswordId;
		
	}
}
