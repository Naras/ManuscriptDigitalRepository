/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	PatientMasterServiceImpl.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	This class will be used to perform all the Common codes DB operations
 * Author								: 	Saurabh
 * Revision History
 *
 *************************************************************************************************************************/
package com.indven.portal.administration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.indven.framework.enums.UserStatusEnum;
import com.indven.framework.exception.IndvenException;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.service.BaseEntityCRUDService;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.util.SecurePasswordUtil;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.administration.assembler.UserLoginAssembler;
import com.indven.portal.administration.dao.RoleMasterDAOImpl;
import com.indven.portal.administration.dao.UserLoginDetailsDAOImpl;
import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.vo.RoleMasterVO;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.dao.EmployeeMasterDAOImpl;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.hrd.exception.HumanResourceException;

/**
 * @author Saurabh
 *
 */
public class UserLoginDetailsServiceImpl implements	BaseEntityCRUDService<UserInfoVO> {

	private UserLoginDetailsDAOImpl userLoginDetailsDAOImpl = new UserLoginDetailsDAOImpl();

//	@Override
//	public UserLoginDetailsBean save(UserLoginDetailsBean userLoginDetailsBean) {
//		userLoginDetailsBean.setCreatedDate(new Date());
//		userLoginDetailsBean.setModifiedDate(new Date());
//		try {
//
//			userLoginDetailsDAOImpl.save(userLoginDetailsBean);
//		} catch (AdministrationException e) {
//
//		}
//		return userLoginDetailsBean;
//	}

//	@Override
//	public UserLoginDetailsVO findById(Long id) {
//		UserLoginDetailsBean userLoginDetailsBean = null;
//		try {
//
//			userLoginDetailsDAOImpl.findById(id);
//		} catch (AdministrationException e) {
//			// TODO: handle exception
//		}
//		return userLoginDetailsBean;
//	}
//
//	@Override
//	public List<UserLoginDetailsBean> findAll() {
//		List<UserLoginDetailsBean> userLoginDetailsList = new ArrayList<UserLoginDetailsBean>();
//
//		try {
//			userLoginDetailsList = userLoginDetailsDAOImpl.findAll();
//		} catch (AdministrationException e) {
//			// TODO: handle exception
//		}
//
//		return userLoginDetailsList;
//	}
//
//	@Override
//	public UserLoginDetailsBean update(UserLoginDetailsBean userLoginDetailsBean) {
//		userLoginDetailsBean.setCreatedDate(new Date());
//		userLoginDetailsBean.setModifiedDate(new Date());
//		try {
//
//			userLoginDetailsDAOImpl.update(userLoginDetailsBean);
//		} catch (AdministrationException e) {
//			// TODO: handle exception
//		}
//		return userLoginDetailsBean;
//	}
//
//	@Override
//	public void purge(UserLoginDetailsBean userLoginDetailsBean) {
//		try {
//
//			userLoginDetailsDAOImpl.purge(userLoginDetailsBean);
//		} catch (AdministrationException e) {
//			// TODO: handle exception
//		}
//
//	}
//


	/**
	 * This method performs the service operation for Login action.
	 *
	 * @param valueObject
	 *            :The object of UserInfoVO with the values that we get from the screen.
	 * @return userInfoVO: The object of UserInfoVO with the values that we get for the result we
	 *         get having the supplied loginName and password
	 *
	 */
	public UserInfoVO handleLogin(UserInfoVO userInfoVO) throws AdministrationException{
		UserLoginDetailsBean userInfoBean = new UserLoginDetailsBean();
		Map<String, Object> loginResultMap = new HashMap<>();   
		try {

			userInfoBean.setLoginId(userInfoVO.getLoginName());
			userInfoBean.setPassword(userInfoVO.getPassWord());

			loginResultMap = new UserLoginDetailsDAOImpl().handleLogin(userInfoBean);
			
			userInfoBean = (UserLoginDetailsBean) loginResultMap.get("userBean");
			
			if(userInfoBean != null) {
				userInfoVO.setName(userLoginDetailsDAOImpl.getEmpNameById(userInfoBean.getRefrenceFkId()));
				userInfoVO.setId(userInfoBean.getId());
				userInfoVO.setPassWord(null);
				userInfoVO.setName((String)loginResultMap.get("userName"));
			}

			// Retrieving menu information for a user
			if (userInfoVO.getId() != null) {
				
					//Retrieve role for current user and save to user VO
					RoleMasterDAOImpl roleMasterDAOImpl = new RoleMasterDAOImpl();
					UserRoleDetailsBean userRoleDetailsBean = roleMasterDAOImpl.findRoleByUser(userInfoVO.getId());
					userInfoVO.setRoleMasterFkId(userRoleDetailsBean.getRoleMasterFkId());
					
//					/**
//					 * @author Deba Prasad
//					 * The below line is added for testing my new method for one user with multiple role concept.
//					 * So uncommented the previous line.
//					 */
//					userInfoVO.setRoleMasterFkId((roleMasterDAOImpl.getRoleListForUser(userInfoVO.getId())).get(0).getId());
					
					EmployeeMasterBean employeeMasterBean = new EmployeeMasterDAOImpl().findById(userInfoBean.getRefrenceFkId());
					userInfoVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
				
			} else {
				userInfoVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			}

		} catch (AdministrationException pce) {
			new IndvenExceptionMessageResolver().resolveMessage(userInfoVO, pce,  IndvenApplicationConstants.LOCALE);
		} catch (HumanResourceException pce) {
			new IndvenExceptionMessageResolver().resolveMessage(userInfoVO, pce,  IndvenApplicationConstants.LOCALE);
		} 

		return userInfoVO;
	}
	
	/**
	 * This method performs the service operation for change password action.
	 *
	 * @param loginId
	 *            :This is id of current login user
	 * @param oldPassword
	 *            :This is the old password which will come from request
	 * @param newPassword
	 *            :This is the new password which will come from request           
	 * @return This method will return the integer value which give information that password is updated for not
	 * 
	 * @throws AdministrationException
	 *
	 */
	public int changePassword(String loginId, String oldPassword,String newPassword) throws AdministrationException{

		return new UserLoginDetailsDAOImpl().changePassword(loginId, oldPassword, newPassword);		
		
	}
	
	/**
	 * This method performs the service operation for update user status action.
	 *
	 * @param idOfUser
	 *            :This is id of current login user
	 * @param userStatus
	 *            :This is status of user         
	 * @return This method will return the integer value which give information that status is updated for not
	 * 
	 * @throws AdministrationException
	 *
	 */
	public int updateUserStatus(Long idOfUser , UserStatusEnum userStatus) throws AdministrationException{

		return new UserLoginDetailsDAOImpl().updateUserStatus(idOfUser, userStatus);		
		
	}
	
	/**
	 * This method performs the service operation for reset password action.
	 *
	 * @param userInfoVO
	 *            :This is object of user          
	 * @return This method will return the integer value which give information that password is reseted or not
	 * 
	 * @throws AdministrationException
	 *
	 */
	public int resetPassword(UserInfoVO userInfoVO) throws AdministrationException{

		return new UserLoginDetailsDAOImpl().resetPassword(userInfoVO);		
		
	}
	
	/**
	 * @author Neel Borooah
	 * @param id
	 * @return UserInfoVO
	 * @throws AdministrationException
	 * 
	 * This method returns the user login details based on its refrence id
	 * Refrence id is the primary id of the employee master table
	 */
	public UserInfoVO findByRefrenceFkId(Long id) throws AdministrationException {
		UserInfoVO userInfoVO = UserLoginAssembler.convertEntityToVo(new UserLoginDetailsDAOImpl().findByRefrenceFkId(id));
		return userInfoVO;
		
	}

	@Override
	public UserInfoVO save(UserInfoVO valueObject) throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoVO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfoVO> findAll() throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoVO update(UserInfoVO valueObject) throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purge(UserInfoVO valueObject) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param userLoginId
	 * 
	 * This method returns the generated Reset password Id
	 */
	
	public String generateResetPasswordId(String userLoginId) throws AdministrationException{
		
		String resetPasswordId = SecurePasswordUtil.genrateResetPasswordId();
		return new UserLoginDetailsDAOImpl().generateResetPasswordId(resetPasswordId,userLoginId);
		
	}
	
	public List<RoleMasterVO> getRoleListForUser(Long userId) throws AdministrationException {
		List<RoleMasterVO> roleList = new ArrayList<>();
		RoleMasterVO vo = null;
		
		List<RoleMasterBean> roleBeanList = new RoleMasterDAOImpl().getRoleListForUser(userId);
		for(RoleMasterBean bean : roleBeanList) {
			vo = new RoleMasterVO();
			roleList.add((RoleMasterVO)CustomBeanUtil.entityToVO(bean,vo));
		}
		return roleList;
	}
}
