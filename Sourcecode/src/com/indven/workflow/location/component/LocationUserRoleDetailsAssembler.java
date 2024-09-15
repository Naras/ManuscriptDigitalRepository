/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	LocationUserRoleDetailsAssembler.java
 * Project Name						:	EPM
 * Date of Creation					: 	10th March 2012
 * Brief Description				:	This class will be used to perform all the LocationUserRoleDetailsAssembler conversion operations from valueObject to Entity
 * Author							: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.workflow.location.component;

import java.util.ArrayList;
import java.util.List;

import com.indven.workflow.location.entity.LocationUserRolesDetailsBean;
import com.indven.workflow.location.vo.LocationUserRoleDetailsVO;

public class LocationUserRoleDetailsAssembler {

	public LocationUserRoleDetailsVO convertEntityToVo(LocationUserRolesDetailsBean entity) {

		LocationUserRoleDetailsVO locationUserRoleDetailsVO = new LocationUserRoleDetailsVO();

		locationUserRoleDetailsVO.setId(entity.getId());
		locationUserRoleDetailsVO.setLocationId(entity.getLocationMasterFkId());
		locationUserRoleDetailsVO.setRoleId(entity.getRoleMasterFkId());
		locationUserRoleDetailsVO.setUserId(entity.getUserInfoFkId());
		locationUserRoleDetailsVO.setValidFrom(entity.getValidFromDate());
		locationUserRoleDetailsVO.setValidTo(entity.getValidToDate());

		return locationUserRoleDetailsVO;
	}

	public LocationUserRolesDetailsBean convertVoToEntity(LocationUserRoleDetailsVO locationUserRoleDetailsVO) {

		LocationUserRolesDetailsBean locationUserRoleDetailsBean = new LocationUserRolesDetailsBean();

		locationUserRoleDetailsBean.setId(locationUserRoleDetailsVO.getId());
		locationUserRoleDetailsBean.setLocationMasterFkId(locationUserRoleDetailsVO.getLocationId());
		locationUserRoleDetailsBean.setRoleMasterFkId(locationUserRoleDetailsVO.getRoleId());
		locationUserRoleDetailsBean.setUserInfoFkId(locationUserRoleDetailsVO.getUserId());
		locationUserRoleDetailsBean.setValidFromDate(locationUserRoleDetailsVO.getValidFrom());
		locationUserRoleDetailsBean.setValidToDate(locationUserRoleDetailsVO.getValidTo());

		return locationUserRoleDetailsBean;
	}

	public List<LocationUserRoleDetailsVO> convertEntitiesToVos(List<LocationUserRolesDetailsBean> entities) {

		List<LocationUserRoleDetailsVO> locationUserRoleVOs = new ArrayList<LocationUserRoleDetailsVO>();

		for (LocationUserRolesDetailsBean locationUserRoleDetailBean : entities) {
			locationUserRoleVOs.add(convertEntityToVo(locationUserRoleDetailBean));
		}

		return locationUserRoleVOs;
	}

	public List<LocationUserRolesDetailsBean> convertVosToEntities(List<LocationUserRoleDetailsVO> valueObjects) {
		List<LocationUserRolesDetailsBean> locationUserRoleDetailsBeans = new ArrayList<LocationUserRolesDetailsBean>();

		for (LocationUserRoleDetailsVO locationUserRoleDetailsVO : valueObjects) {
			locationUserRoleDetailsBeans.add(convertVoToEntity(locationUserRoleDetailsVO));
		}

		return locationUserRoleDetailsBeans;
	}

}
