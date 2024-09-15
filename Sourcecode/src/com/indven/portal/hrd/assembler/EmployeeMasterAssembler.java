/**
 * 
 */
package com.indven.portal.hrd.assembler;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.hrd.vo.EmployeeMasterVO;

/**
 * @author Saurabh
 *
 */
public class EmployeeMasterAssembler {
	
	public static EmployeeMasterVO convertEntityToVo(EmployeeMasterBean employeeMasterBean) {
		EmployeeMasterVO employeeMasterVO = new EmployeeMasterVO();

		employeeMasterVO.setId(employeeMasterBean.getId());
		employeeMasterVO.setFirstName(employeeMasterBean.getFirstName());
		employeeMasterVO.setLastName(employeeMasterBean.getLastName());
		
		if(employeeMasterBean.getDateOfBirth() != null)
			employeeMasterVO.setDateOfBirth(employeeMasterBean.getDateOfBirth().toString());
		
		employeeMasterVO.setEmail(employeeMasterBean.getEmail());
		employeeMasterVO.setPhoneNumber(employeeMasterBean.getPhoneNumber());
		
		employeeMasterVO.setAddress1(employeeMasterBean.getAddress1());
		employeeMasterVO.setGender(employeeMasterBean.getGender());
		
		employeeMasterVO.setCreatedBy(employeeMasterBean.getCreatedBy());
		employeeMasterVO.setCreatedDate(employeeMasterBean.getCreatedDate());
		employeeMasterVO.setModifiedBy(employeeMasterBean.getModifiedBy());
		employeeMasterVO.setModifiedDate(employeeMasterBean.getModifiedDate());
		employeeMasterVO.setRowVersion(employeeMasterBean.getRowVersion());
		return employeeMasterVO;
	}
	
	public static EmployeeMasterBean convertVoToEntity(EmployeeMasterVO employeeMasterVO) {
		
		EmployeeMasterBean employeeMasterBean = new EmployeeMasterBean();
		if(employeeMasterVO.getId() != null)		
			employeeMasterBean.setId(employeeMasterVO.getId());
		employeeMasterBean.setFirstName(employeeMasterVO.getFirstName());
		employeeMasterBean.setLastName(employeeMasterVO.getLastName());
		if(employeeMasterVO.getDateOfBirth() != null)
			employeeMasterBean.setDateOfBirth(CustomBeanUtil.convertStringToDate(employeeMasterVO.getDateOfBirth()));
		employeeMasterBean.setEmail(employeeMasterVO.getEmail());
		employeeMasterBean.setPhoneNumber(employeeMasterVO.getPhoneNumber());
		employeeMasterBean.setAddress1(employeeMasterVO.getAddress1());
		employeeMasterBean.setGender(employeeMasterVO.getGender());
		
		employeeMasterBean.setEmployeeType(employeeMasterVO.getUserType());
		employeeMasterBean.setRowVersion(employeeMasterVO.getRowVersion());
		return employeeMasterBean;
	}
	
	public static List<EmployeeMasterBean> convertVosToEntities(List<EmployeeMasterVO> valueObjects) {
		List<EmployeeMasterBean> employeeMasterBeans = new ArrayList<EmployeeMasterBean>();
		
		for (EmployeeMasterVO employeeMasterVO : valueObjects) {
			employeeMasterBeans.add(convertVoToEntity(employeeMasterVO));
		}
		
		return employeeMasterBeans;
	}
	
	
	public static List<EmployeeMasterVO> convertEntitiesToVos(List<EmployeeMasterBean> entities) {
		List<EmployeeMasterVO> employeeMasterVOs = new ArrayList<EmployeeMasterVO>();

		for (EmployeeMasterBean employeeMasterBean : entities) {
			employeeMasterVOs.add(convertEntityToVo(employeeMasterBean));
		}

		return employeeMasterVOs;
	}

}
