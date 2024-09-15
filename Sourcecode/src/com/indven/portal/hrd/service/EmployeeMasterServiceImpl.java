/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EmployeeMasterServiceImpl.java
 * Project Name							:	GPIL
 * Date of Creation						: 	12th Aug 2013
 * Brief Description					:	
 * Author								: 	Deba Prasad
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.hrd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.indven.framework.exception.IndvenException;
import com.indven.framework.service.BaseEntityCRUDService;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.SecurePasswordUtil;
import com.indven.portal.administration.dao.RoleMasterDAOImpl;
import com.indven.portal.administration.dao.UserLoginDetailsDAOImpl;
import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.assembler.EmployeeMasterAssembler;
import com.indven.portal.hrd.dao.EmployeeMasterDAOImpl;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.hrd.exception.HumanResourceException;
import com.indven.portal.hrd.vo.EmployeeMasterVO;

/**
 * @author Deba Prasad
 *
 */
public class EmployeeMasterServiceImpl implements BaseEntityCRUDService<EmployeeMasterVO> {
	
	
	/**
	 * Converts VOs to Beans and passes them to the DAO
	 * @param employeeVO
	 * @param userInfoVO
	 * @return
	 * @throws HumanResourceException
	 * @throws AdministrationException
	 */
	public EmployeeMasterVO addUpdateEmployee(EmployeeMasterVO employeeVO , UserInfoVO userInfoVO) throws HumanResourceException, AdministrationException {
		
		EmployeeMasterBean employeeMasterBean = EmployeeMasterAssembler.convertVoToEntity(employeeVO);
//		EmployeeMasterBean employeeMasterBean = (EmployeeMasterBean) CustomBeanUtil.voToEntity(employeeVO, new EmployeeMasterBean());
		CustomBeanUtil.setBaseValues(employeeMasterBean, false);
		
		employeeMasterBean.setDateOfBirth(new Date());
		employeeMasterBean.setEmployeeType((short) 1);
		employeeMasterBean.setIsDeleted(false);
		
		UserLoginDetailsBean userBean = null;
		UserRoleDetailsBean userRoleDetailsBean = null;
		
		
		List<UserRoleDetailsBean>  userRoleDetails = new ArrayList<>();
		if(employeeMasterBean.getId() == null || employeeMasterBean.getId() <= 0) {
			userBean = new UserLoginDetailsBean();
			userBean.setLoginId(employeeVO.getEmail());
			userBean.setPassword(SecurePasswordUtil.hashUserPassword(userInfoVO.getPassWord(),employeeVO.getEmail()));
			userBean.setStatus((short) 1);
			userBean.setType((short) 1);
			CustomBeanUtil.setBaseValues(userBean, false);
			
			userRoleDetailsBean = new UserRoleDetailsBean();
			userRoleDetailsBean.setRoleMasterFkId(userInfoVO.getRoleMasterFkId());
			employeeMasterBean.setRowVersion(null);
			
		}
		else {
			UserLoginDetailsBean userBeanForId = new UserLoginDetailsDAOImpl().findByRefrenceFkId(employeeVO.getId());
			userRoleDetailsBean = new RoleMasterDAOImpl().findRoleByUser(userBeanForId.getId());
			if(userRoleDetailsBean != null)
			userRoleDetailsBean.setRoleMasterFkId(userInfoVO.getRoleMasterFkId());
		}
		
		for(int i = 0; i < userInfoVO.getRoleIds().size() ; i++) {
			userRoleDetailsBean = new UserRoleDetailsBean();
			userRoleDetailsBean.setRoleMasterFkId(userInfoVO.getRoleIds().get(i));
			userRoleDetails.add(userRoleDetailsBean);
		}
	
		EmployeeMasterDAOImpl employeeMasterDAOImpl = new EmployeeMasterDAOImpl();
		employeeMasterBean = employeeMasterDAOImpl.saveOrUpdateEmployee(employeeMasterBean, userBean, userRoleDetailsBean , userRoleDetails);
		
		employeeVO = EmployeeMasterAssembler.convertEntityToVo(employeeMasterBean);
		
		return employeeVO;
	}

	/**
	 * This is the action method for searching the list of employees present in the database as per the criteria value given in search form.
	 */
	public List<EmployeeMasterVO> findAllEmployeeByGivenField(EmployeeMasterVO employeeVO) throws HumanResourceException {
		EmployeeMasterBean employeeBean = EmployeeMasterAssembler.convertVoToEntity(employeeVO);
		List<EmployeeMasterVO> empVOList = new ArrayList<EmployeeMasterVO>();
		
		EmployeeMasterDAOImpl employeeMasterDAOImpl = new EmployeeMasterDAOImpl();
//		empVOList = EmployeeMasterAssembler.convertEntitiesToVos(employeeMasterDAOImpl.findAllEmployeeByGivenField(employeeBean, employeeVO.getRoleMasterFkId()));
		List<EmployeeMasterBean> employeeMasterBeans = employeeMasterDAOImpl.findAllEmployeeByGivenField(employeeBean, employeeVO.getRoleMasterFkId());
		for(Iterator<EmployeeMasterBean> i = employeeMasterBeans.iterator(); i.hasNext();) {
			empVOList.add((EmployeeMasterVO) CustomBeanUtil.entityToVO(i.next(), new EmployeeMasterVO()));
		}
		return empVOList;
	}
	
	public void deleteEmployeeAndDetails(Long id)  throws HumanResourceException {
		//try {
			EmployeeMasterDAOImpl employeeMasterDAOImpl = new EmployeeMasterDAOImpl();
			employeeMasterDAOImpl.deleteEmployeeAndDetails(id);
//		} catch (HumanResourceException pce) {
//			logger.error(HumanResourceException.UNABLE_TO_SAVE_EMPLOYEE, pce);
//			throw new HumanResourceException(HumanResourceException.UNABLE_TO_SAVE_EMPLOYEE, pce);
//		}
	}
	
	public EmployeeMasterVO searchEmployeeById(Long id) throws HumanResourceException, AdministrationException {
		EmployeeMasterDAOImpl empDaoImpl = new EmployeeMasterDAOImpl();
		EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
		
		employeeMasterBean = empDaoImpl.findById(id);
//		EmployeeMasterVO employeeMasterVO = EmployeeMasterAssembler.convertEntityToVo(employeeMasterBean);
		EmployeeMasterVO employeeMasterVO = (EmployeeMasterVO) CustomBeanUtil.entityToVO(employeeMasterBean, new EmployeeMasterVO());
		UserLoginDetailsBean userLoginDetailsBean = new UserLoginDetailsDAOImpl().findByRefrenceFkId(employeeMasterVO.getId());
		UserRoleDetailsBean userRolebean = new RoleMasterDAOImpl().findRoleByUser(userLoginDetailsBean.getId());
		if(userRolebean != null)
		employeeMasterVO.setRoleMasterFkId(userRolebean.getRoleMasterFkId());
		
		//employeeMasterVO.setRolesList(new RoleMasterDAOImpl().findRoleIdsByUserId(userLoginDetailsBean.getId()));
		List<RoleMasterBean> employeeRoles = new RoleMasterDAOImpl().getRoleListForUser(userLoginDetailsBean.getId());
		employeeMasterVO.setRolesList(new ArrayList<Long>());
		for(RoleMasterBean bean : employeeRoles) {
			employeeMasterVO.getRolesList().add(bean.getId());
		}
		employeeMasterBean = null;
		return employeeMasterVO;
	}
	
	public Long findNoOfEmployees() throws HumanResourceException {
		return new EmployeeMasterDAOImpl().findNoOfEmployees();
	}

	@Override
	public EmployeeMasterVO save(EmployeeMasterVO valueObject)
			throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeMasterVO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeMasterVO> findAll() throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeMasterVO update(EmployeeMasterVO valueObject)
			throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purge(EmployeeMasterVO valueObject) {
		// TODO Auto-generated method stub
		
	}
}
