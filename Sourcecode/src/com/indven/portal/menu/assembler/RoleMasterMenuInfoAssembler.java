/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	RoleMasterMenuInfoAssembler.java
 * Project Name						:	WASP
 * Date of Creation					: 	19th 2013
 * Brief Description				:	This test is used for RoleMasterMenuInfo conversion operations from valueObject to Entity
 * Author							: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.menu.assembler;

import java.util.ArrayList;
import java.util.List;

import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.vo.RoleMasterMenuInfoVO;

/**
 * * This class will be used to perform all the RoleMasterMenuInfo conversion operations from
 * valueObject to Entity and entity to VO
 * 
 * @author Saurabh
 * 
 */
public class RoleMasterMenuInfoAssembler {

	/**
	 * This method is used to convert the Entity to VO.
	 */
	public RoleMasterMenuInfoVO convertEntityToVo(AccessControlBean roleMasterMenuInfoBean) {
		RoleMasterMenuInfoVO roleInfoMenuInfoVO = new RoleMasterMenuInfoVO();

		roleInfoMenuInfoVO.setMenuId(roleMasterMenuInfoBean.getMenuMasterFkId());
		roleInfoMenuInfoVO.setRoleId(roleMasterMenuInfoBean.getRoleMasterFkId());

		return roleInfoMenuInfoVO;
	}

	/**
	 * This method is used to convert vo to entity
	 */
	public AccessControlBean convertVoToEntity(RoleMasterMenuInfoVO roleMasterMenuInfoVO) {

		AccessControlBean roleMasterMenuInfoBean = new AccessControlBean();

		roleMasterMenuInfoBean.setRoleMasterFkId(roleMasterMenuInfoVO.getRoleId());
		roleMasterMenuInfoBean.setMenuMasterFkId(roleMasterMenuInfoVO.getMenuId());

		return roleMasterMenuInfoBean;
	}

	/**
	 * This method is used to convert the List of valueObjects to List of Entities.
	 */
	public List<AccessControlBean> convertVosToEntities(List<RoleMasterMenuInfoVO> valueObjects) {
		List<AccessControlBean> roleMasterMenuInfoBeans = new ArrayList<AccessControlBean>();

		for (RoleMasterMenuInfoVO roleMasterMenuInfoVO : valueObjects) {
			roleMasterMenuInfoBeans.add(convertVoToEntity(roleMasterMenuInfoVO));
		}

		return roleMasterMenuInfoBeans;
	}

	/**
	 * This method is used to convert the List of Entities to List of valueObjects.
	 */
	public List<RoleMasterMenuInfoVO> convertEntitiesToVos(List<AccessControlBean> entities) {
		List<RoleMasterMenuInfoVO> roleMasterMenuInfoVOs = new ArrayList<RoleMasterMenuInfoVO>();

		for (AccessControlBean roleMasterMenuInfoBean : entities) {
			roleMasterMenuInfoVOs.add(convertEntityToVo(roleMasterMenuInfoBean));
		}

		return roleMasterMenuInfoVOs;
	}

}
