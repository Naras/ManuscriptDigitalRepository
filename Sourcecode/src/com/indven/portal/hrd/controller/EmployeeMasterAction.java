/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EmployeeMasterAction.java
 * Project Name							:	GPIL
 * Date of Creation						: 	12th Aug 2013
 * Brief Description					:	
 * Author								: 	Deba Prasad
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.hrd.controller;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.service.RoleMasterServiceImpl;
import com.indven.portal.administration.vo.RoleMasterVO;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.exception.HumanResourceException;
import com.indven.portal.hrd.service.EmployeeMasterServiceImpl;
import com.indven.portal.hrd.vo.EmployeeMasterVO;

/**
 * @author Deba Prasad
 *
 */
public class EmployeeMasterAction extends BaseAction {
	
	private static IndvenLogger logger = IndvenLogger.getInstance(EmployeeMasterAction.class);

	private static final long serialVersionUID = 1L;
	
	private EmployeeMasterVO employeeMasterVO = new EmployeeMasterVO();
	private UserInfoVO userInfoVO = new UserInfoVO();
	
	List<EmployeeMasterVO> empVOList = new ArrayList<EmployeeMasterVO>();
	private List<RoleMasterVO> roleMasterVOsList = new ArrayList<RoleMasterVO>();
	
	private List<Long> rolesValues = new ArrayList<>();
	private List<String> rolesKeys = new ArrayList<>();
	/**
	 * @return the roles
	 */
	public final List<Long> getRolesValues() {
		if(rolesValues.size() <= 0) {
			for(int  i= 0 ;i < roleMasterVOsList.size() ; i++) {
				rolesValues.add(roleMasterVOsList.get(i).getId());
			}
		}
		return rolesValues;
	}

	/**
	 * @param roles the roles to set
	 */
	public final void setRolesValues(List<Long> rolesValues) {
		this.rolesValues = rolesValues;
	}

	/**
	 * @return the empVOList
	 */
	public List<EmployeeMasterVO> getEmpVOList() {
		return empVOList;
	}

	/**
	 * @param empVOList the empVOList to set
	 */
	public void setEmpVOList(List<EmployeeMasterVO> empVOList) {
		this.empVOList = empVOList;
	}

	/**
	 * @return the userInfoVO
	 */
	public UserInfoVO getUserInfoVO() {
		return userInfoVO;
	}

	/**
	 * @param userInfoVO the userInfoVO to set
	 */
	public void setUserInfoVO(UserInfoVO userInfoVO) {
		this.userInfoVO = userInfoVO;
	}

	public EmployeeMasterVO getEmployeeMasterVO() {
		return employeeMasterVO;
	}

	public void setEmployeeMasterVO(EmployeeMasterVO employeeMasterVO) {
		this.employeeMasterVO = employeeMasterVO;
	}

	/**
	 * Saves new employee data or updates existing employee data
	 * @return
	 */
	public String addUpdateEmployee()  {System.out.println("addUpdate employee method");
		
		EmployeeMasterServiceImpl employeeMasterServiceImpl = new EmployeeMasterServiceImpl();
		String status = ERROR;
		boolean isUpdating = false;
		
		try {
			if(employeeMasterVO.getId() != null) {
				isUpdating = true;
			}
			employeeMasterVO = employeeMasterServiceImpl.addUpdateEmployee(employeeMasterVO, userInfoVO);
			
			if(!isUpdating) {
				employeeMasterVO = new EmployeeMasterVO();
			}
			userInfoVO = new UserInfoVO();
			status = SUCCESS;
			
			userInfoVO.setMessage(IndvenMessageResolver.resolveMessage(HumanResourceException.SAVE_SUCCESS_EMPLOYEE, IndvenApplicationConstants.LOCALE));
			addActionMessage(userInfoVO.getMessage());
		} catch (HumanResourceException e) {
			new IndvenExceptionMessageResolver().resolveMessage(userInfoVO, e,  IndvenApplicationConstants.LOCALE);
			addActionError(userInfoVO.getMessage());
		} catch (AdministrationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(userInfoVO, e,  IndvenApplicationConstants.LOCALE);
			addActionError(userInfoVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			addActionError("Unable to save");
		}
		generateListOfAllRoles();
		return status;
	}
	
	public List<RoleMasterVO> getRoleMasterVOsList() {
		return roleMasterVOsList;
	}

	public void setRoleMasterVOsList(List<RoleMasterVO> roleMasterVOsList) {
		this.roleMasterVOsList = roleMasterVOsList;
	}

	public String searchEmployeeByCriteria() {
		String status = ERROR;
//		INDVENResultVO resultVO = new INDVENResultVO();
		EmployeeMasterServiceImpl employeeMasterServiceImpl = new EmployeeMasterServiceImpl();
		
		try {
			empVOList = employeeMasterServiceImpl.findAllEmployeeByGivenField(employeeMasterVO);
			if(empVOList!=null && empVOList.size()<=0){
				addActionMessage(IndvenExceptionMessageResolver.resolveMessage(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES_FOR_GIVEN_CRITERIA, IndvenApplicationConstants.LOCALE));
			}	
		} catch (HumanResourceException e) {
			new IndvenExceptionMessageResolver().resolveMessage(employeeMasterVO, e,  IndvenApplicationConstants.LOCALE);
			addActionError(employeeMasterVO.getMessage());
		}
		this.generateListOfAllRoles();
		return status;
	}
	
	public String searchEmployeeById() {
		String status = ERROR;
		
		try {
			Long id = Long.parseLong(getRequest().getParameter("id"));
			
			employeeMasterVO = new EmployeeMasterServiceImpl().searchEmployeeById(id);
			generateListOfAllRoles();

			status = SUCCESS;
		} catch (HumanResourceException e) {
			new IndvenExceptionMessageResolver().resolveMessage(employeeMasterVO, e,  IndvenApplicationConstants.LOCALE);
			addActionError(employeeMasterVO.getMessage());
		} catch (AdministrationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(employeeMasterVO, e,  IndvenApplicationConstants.LOCALE);
			addActionError(employeeMasterVO.getMessage());
		} 
		return status;
	}
	
	public String deleteEmployeeDetails() {System.out.println("delete employee method");
		String status = ERROR;
		try {
			Long id = Long.parseLong(getRequest().getParameter("id"));
			EmployeeMasterServiceImpl employeeMasterServiceImpl = new EmployeeMasterServiceImpl();
			employeeMasterServiceImpl.deleteEmployeeAndDetails(id);
			
			status = SUCCESS;
			employeeMasterVO.setMessage(IndvenMessageResolver.resolveMessage(HumanResourceException.DELETE_SUCCESS_EMPLOYEE, IndvenApplicationConstants.LOCALE));
			addActionMessage(employeeMasterVO.getMessage());
		} catch (HumanResourceException e) {
			new IndvenExceptionMessageResolver().resolveMessage(employeeMasterVO, e,  IndvenApplicationConstants.LOCALE);
			addActionError(employeeMasterVO.getMessage());
		}
		return status;
	}
	
	/**
	 * Generates list of all roles in the database
	 * @author Neel Borooah
	 * @return String
	 */
	public String generateListOfAllRoles() {
		String status = ERROR;
		try {
			roleMasterVOsList = new RoleMasterServiceImpl().generateListOfAllRoles();
			status = SUCCESS;
		} catch (AdministrationException e) {
			userInfoVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(userInfoVO, e,
					IndvenApplicationConstants.LOCALE);
			addActionError(userInfoVO.getMessage());
		}

		return status;
	}

	/**
	 * @return the rolesKeys
	 */
	public List<String> getRolesKeys() {
		if(rolesKeys.size() <= 0) {
			for(int  i= 0 ;i < roleMasterVOsList.size() ; i++) {
				rolesKeys.add(roleMasterVOsList.get(i).getName());
			}
		}
		return rolesKeys;
	}

	/**
	 * @param rolesKeys the rolesKeys to set
	 */
	public void setRolesKeys(List<String> rolesKeys) {
		this.rolesKeys = rolesKeys;
	}

}
