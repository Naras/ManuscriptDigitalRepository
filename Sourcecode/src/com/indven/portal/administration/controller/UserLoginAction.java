/**
 *
 */
package com.indven.portal.administration.controller;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.util.ApplicationSessions;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.service.UserLoginDetailsServiceImpl;
import com.indven.portal.administration.vo.RoleMasterVO;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.menu.controller.MenuInfoAction;
import com.indven.portal.menu.exception.MenuInfoException;
import com.indven.portal.menu.service.MenuInfoServiceImpl;
import com.indven.portal.menu.vo.MenuMasterVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Sandeep Solomon Kunder
 *
 */
public class UserLoginAction extends BaseAction {

	/**
	 *
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String customGreeting = "Success";
	private UserInfoVO userInfoVO = new UserInfoVO();
	MenuInfoAction menuInfoAction = new MenuInfoAction();
	
	private JSONObject resultObj = null;
	
	private List<RoleMasterVO> userRoleList;
	private Long userRoleId ;
	private Short isReloadMenu = (short)1;
	
	/**
	 * @return the isReloadMenu
	 */
	public final Short getIsReloadMenu() {
		return isReloadMenu;
	}

	/**
	 * @param isReloadMenu the isReloadMenu to set
	 */
	public final void setIsReloadMenu(Short isReloadMenu) {
		this.isReloadMenu = isReloadMenu;
	}

	/**
	 * @return the userRoleId
	 */
	public final Long getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public final void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the userRoleList
	 */
	public final List<RoleMasterVO> getUserRoleList() {
		return userRoleList;
	}

	/**
	 * @param userRoleList the userRoleList to set
	 */
	public final void setUserRoleList(List<RoleMasterVO> userRoleList) {
		this.userRoleList = userRoleList;
	}

	/**
	 * @return the resultObj
	 */
	public final JSONObject getResultObj() {
		return resultObj;
	}

	/**
	 * @param resultObj the resultObj to set
	 */
	public final void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}

	public String getCustomGreeting() {
		return customGreeting;
	}

	public void setCustomGreeting(String customGreeting) {
		this.customGreeting = customGreeting;
	}


	public UserInfoVO getUserInfoVO() {
		return userInfoVO;
	}

	public void setUserInfoVO(UserInfoVO userInfoVO) {
		this.userInfoVO = userInfoVO;
	}
	
	
	public String validateLogin() {

		String status = ERROR;
		UserLoginDetailsServiceImpl userServiceImpl = new UserLoginDetailsServiceImpl();
		MenuMasterVO menusForCurrentRole = new MenuMasterVO();
		try {
			//Check if user tries multiple log in to system on same browser
			UserInfoVO loginData = (UserInfoVO) super.getRequest().getSession().getAttribute("loginData");
			if(loginData != null) {
				addActionError("You are already logged in");
				return status;
			}
			userServiceImpl.handleLogin(userInfoVO);
			
			if(userInfoVO.getStatus()!=null && userInfoVO.getStatus().equals(IndvenResultVO.STATUS_SUCCESS)){
				//Save the user VO to session
				super.getRequest().getSession().setAttribute(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA, userInfoVO);
				
				//Save the header menu for user to session
				menusForCurrentRole = new MenuInfoServiceImpl().getActiveMenuPrivilegeForRole(userInfoVO.getRoleMasterFkId());
				super.getRequest().getSession().setAttribute("headerMenu", menusForCurrentRole);
				
				//Adding user and session data to list of active sessions
				ApplicationSessions.getMapOfActiveSessions().put(userInfoVO.getId(), super.getRequest().getSession().getId());
				
				super.getRequest().getSession().setAttribute("currentRole", userInfoVO.getRoleMasterFkId());
				super.getRequest().getSession().setAttribute("currentUser", userInfoVO.getName());
				/**
				 * Author : @author Deba Prasad
				 * Date - 13th Feb 2014
				 * 
				 * This part of code is written for retrieving the associated list of role entities for the particular logged in user.
				 * 
				*/
				userRoleList = userServiceImpl.getRoleListForUser(userInfoVO.getId());
				super.getRequest().getSession().setAttribute("currentRoleName", userRoleList.get(0).getName());
				super.getRequest().getSession().setAttribute("userRoles", userRoleList);
				
				status = SUCCESS;
			} else {
				addActionError(userInfoVO.getMessage());
				status = ERROR;
			}

		} catch (AdministrationException e) {
			userInfoVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(userInfoVO, e, IndvenApplicationConstants.LOCALE);
			
			addActionError(userInfoVO.getMessage());
		} catch (MenuInfoException e) {
			userInfoVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(menusForCurrentRole, e, IndvenApplicationConstants.LOCALE);;
		}
		return status;
	}
	
	public String getMenuForSelectedRole() {
		String status = ERROR;
		MenuMasterVO menusForCurrentRole = new MenuMasterVO();
		
		try {
			menusForCurrentRole = new MenuInfoServiceImpl().getActiveMenuPrivilegeForRole(userRoleId);
			super.getRequest().getSession().setAttribute("currentRole", userRoleId);
			
			
			UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute("loginData");
			userInfoVO.setRoleMasterFkId(userRoleId);
			super.getRequest().getSession().setAttribute("loginData", userInfoVO);
			super.getRequest().getSession().setAttribute("currentRoleName", getRequest().getParameter("currentRoleName"));
			 
			 
			super.getRequest().getSession().setAttribute("headerMenu", menusForCurrentRole);
			
			isReloadMenu = (short)0;
			status = SUCCESS;
		} catch (MenuInfoException e) {
			menusForCurrentRole.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(menusForCurrentRole, e, IndvenApplicationConstants.LOCALE);
			addActionError(menusForCurrentRole.getMessage());
		}
		return status;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getDefaultMenuForGuest() {
		String status = ERROR;
		MenuMasterVO menusForCurrentRole = null;
		
		try {
			Map<String, MenuMasterVO> application = (Map<String, MenuMasterVO>) ActionContext.getContext().get("application");
			
			if(application.get("headerMenu")== null) {
				Long userRoleId = IndvenApplicationConstants.GUEST_ID;
				menusForCurrentRole = new MenuInfoServiceImpl().getActiveMenuPrivilegeForRole(userRoleId);
				application.put("headerMenu",menusForCurrentRole);
			} else {
				menusForCurrentRole = application.get("headerMenu");
			}

			super.getRequest().getSession().setAttribute("currentRole", userRoleId);
			super.getRequest().getSession().setAttribute("headerMenu", menusForCurrentRole);
			
			status = SUCCESS;
		} catch (MenuInfoException e) {
			menusForCurrentRole = new MenuMasterVO();
			menusForCurrentRole.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(menusForCurrentRole, e, IndvenApplicationConstants.LOCALE);
			addActionError(menusForCurrentRole.getMessage());
		}
		return status;
	}
	

	public String handleLogout() {
		
		String status =  SUCCESS;
		
		//Checking if user has already logged out
		UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
		if(userInfoVO == null) {
			return status;
		}
		
		//Remove user from list of active sessions
		if(ApplicationSessions.getMapOfActiveSessions().get(userInfoVO.getId()) != null && ApplicationSessions.getMapOfActiveSessions().get(userInfoVO.getId()).equalsIgnoreCase(super.getRequest().getSession().getId())) {
			ApplicationSessions.getMapOfActiveSessions().remove(userInfoVO.getId());
		}
		
		//Remove user VO from session
		super.getRequest().getSession().removeAttribute(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
		//Remove header menu from session
		super.getRequest().getSession().removeAttribute("headerMenu");
		
		if(super.getRequest().getAttribute("status") != null) {
			status = (String) super.getRequest().getAttribute("status");
			return status;
		}
		
		return status;
	}

	/**
	 * This method will change the password for current login user by getting the old password and new password from request
	 * @return  success or failure string.
	 * 
	 */
	public String changePassword() {
		String status = ERROR;
		UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
		
		String loginId = userInfoVO.getLoginName();
		
		String oldPassword = getRequest().getParameter("oldPassword");
		String newPassword = getRequest().getParameter("newPassword");

		IndvenResultVO  resultVO = new IndvenResultVO();
		try{
		
			UserLoginDetailsServiceImpl userServiceImpl = new UserLoginDetailsServiceImpl();
			
			int result = userServiceImpl.changePassword(loginId, oldPassword,newPassword);
	
			if(result==1){
					resultVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
					resultVO.setMessage(IndvenMessageResolver.resolveMessage(AdministrationException.SAVE_CHANGE_PASSWORD, IndvenApplicationConstants.LOCALE));
					status = SUCCESS;
					addActionMessage(resultVO.getMessage());
			}else if(result==0){
					resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
					resultVO.setMessage(IndvenMessageResolver.resolveMessage(AdministrationException.WORNG_OLD_PASSWORD, IndvenApplicationConstants.LOCALE));
					status = ERROR;
					addActionError(resultVO.getMessage());
			}
		} catch (AdministrationException e) {
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(resultVO.getMessage());
		}
		return status;
	}
	
	
	/**
	 * This method will use to reset the user password 
	 * @return  success or failure string.
	 * 
	 */
	public String resetPassword() {System.out.println("at resetPassword 0");
		
//		IndvenResultVO  ehrmsResultVO = new IndvenResultVO();
//		try{
//		
//			UserLoginDetailsServiceImpl userServiceImpl = new UserLoginDetailsServiceImpl();
//			
//			int result = userServiceImpl.resetPassword(userInfoVO);
//	
//			if(result==1){
//				ehrmsResultVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
//				ehrmsResultVO.setMessage(IndvenMessageResolver.resolveMessage(AdministrationException.SAVE_RESET_PASSWORD, IndvenApplicationConstants.LOCALE));
//			}else if(result==0){
//				ehrmsResultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
////				ehrmsResultVO.setMessage(EHRMSMessageResolver.resolveMessage(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, EHRMSApplicationConstants.LOCALE));
//			}
//		} catch (AdministrationException e) {
//			ehrmsResultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
//			new IndvenExceptionMessageResolver().resolveMessage(ehrmsResultVO, e, IndvenApplicationConstants.LOCALE);
//		}
//		return SUCCESS;
		
		
		
		String status = ERROR;
		
		try{
		
			UserLoginDetailsServiceImpl userServiceImpl = new UserLoginDetailsServiceImpl();
			System.out.println("at resetPassword 1");
			int result = userServiceImpl.resetPassword(userInfoVO);
			System.out.println("at resetPassword 2");
	
			if(result==1){System.out.println("at resetPassword success");
				addActionMessage(IndvenMessageResolver.resolveMessage(AdministrationException.SAVE_RESET_PASSWORD, IndvenApplicationConstants.LOCALE));
				status=SUCCESS;
			}else if(result==0){System.out.println("at resetPassword failure");
				addActionError(IndvenMessageResolver.resolveMessage(AdministrationException.UNABLE_TO_RESET_USER_PASSWORD, IndvenApplicationConstants.LOCALE));
				status = ERROR;
			}
		} catch (AdministrationException e) {System.out.println("at resetPassword error");
			IndvenResultVO  resultVO = new IndvenResultVO();
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(resultVO.getMessage());
			status = ERROR;
			
		}
		return status;
	}
	
	/* 
	 * This method override to redirect the page to different page using the different action which will come DB through UI 
	 * 
	 * 
	 */
	public String reDirectToPage()  {
		// TODO 
		return SUCCESS;
	}

	/**
	 * This method will use to generate the reset Password Id
	 * @return  success return generatedId
	 */
	
	public String generateResetPasswordId(){
		String status = ERROR;
		String userLoginId = getRequest().getParameter("loginId");
		resultObj = new JSONObject();
		try{
		UserLoginDetailsServiceImpl userServiceImpl = new UserLoginDetailsServiceImpl();
		String resetPasswordId=userServiceImpl.generateResetPasswordId(userLoginId);
		this.resultObj.put("resetPasswordId", resetPasswordId);
		status=SUCCESS;
		return status;
		}catch(AdministrationException e){
			IndvenResultVO  resultVO = new IndvenResultVO();
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e, IndvenApplicationConstants.LOCALE);
			this.resultObj.put("resetPasswordId", null);
			status = SUCCESS;
			return status;
		}
	}
}
