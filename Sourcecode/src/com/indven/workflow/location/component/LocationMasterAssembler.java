/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	LocationMasterAssembler.java
 * Project Name						:	EPM
 * Date of Creation					: 	13th FEB 2012
 * Brief Description				:	This class will be used to perform all the Location Master conversion operations from valueObject to Entity
 * Author							: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.component;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.workflow.location.entity.LocationMasterBean;
import com.indven.workflow.location.vo.LocationMasterVO;

/**
 * This class will be used to perform all the Location Master conversion operations from VO to
 * Entity and Entity To VO
 * 
 * @author Saurabh,Nithya
 * 
 */
public class LocationMasterAssembler {

	/**
	 * This method is used to convert the Entity to ValueObject.
	 * 
	 * @param entity
	 *            The object of LocationMasterBean.
	 * @return returns the object of LocationMasterVO.
	 */
	public LocationMasterVO convertEntityToVo(LocationMasterBean locationMasterBean) {

		LocationMasterVO locationMasterVO = new LocationMasterVO();

		locationMasterVO.setId(locationMasterBean.getId());
		locationMasterVO.setName(locationMasterBean.getName());
		locationMasterVO.setDescription(locationMasterBean.getDescription());
		locationMasterVO.setIsWorkflowEnabled(locationMasterBean.getIsWorkflowEnabled());
		locationMasterVO.setIsDeleted(locationMasterBean.getIsDeleted());
		locationMasterVO.setLevelFkId(locationMasterBean.getLevelFkId());
		if (locationMasterBean.getLevelFkIdObj() != null) {
			locationMasterVO.setLevelFkName(locationMasterBean.getLevelFkIdObj().getName());
			locationMasterVO.setLevelImageName(locationMasterBean.getLevelFkIdObj().getIconImageName());
		}

		locationMasterVO.setParentFkId(locationMasterBean.getParentFkId());
		locationMasterVO.setRowVersion(locationMasterBean.getRowVersion());
		return locationMasterVO;
	}

	/**
	 * This method is used to convert the ValueObject to Entity.
	 * 
	 * @param valueObject
	 *            The object of LocationMasterVO which will be converted into LocationMasterBean
	 *            object.
	 * @param entity
	 *            The object of LocationMasterBean.
	 * @return returns the object of LocationMasterBean which we get after converting
	 *         LocationMasterVO object to LocationMasterBean object.
	 */
	public LocationMasterBean convertVoToEntity(LocationMasterVO locationMasterVO) {

		LocationMasterBean locationMasterBean = new LocationMasterBean();

		locationMasterBean.setId(locationMasterVO.getId());
		locationMasterBean.setName(locationMasterVO.getName());
		locationMasterBean.setDescription(locationMasterVO.getDescription());
		locationMasterBean.setIsWorkflowEnabled(locationMasterVO.getIsWorkflowEnabled());
		locationMasterBean.setIsDeleted(locationMasterVO.getIsDeleted());
		locationMasterBean.setLevelFkId(locationMasterVO.getLevelFkId());
		locationMasterBean.setParentFkId(locationMasterVO.getParentFkId());
		locationMasterBean.setRowVersion(locationMasterVO.getRowVersion());
		CustomBeanUtil.setBaseValues(locationMasterBean , false);
		return locationMasterBean;
	}

	/**
	 * This method is used to convert the List of valueObjects to List of Entities.
	 * 
	 * @param valueObjects
	 *            The List of objects of LocationMasterVOs.
	 * @return returns the List object containing LocationMasterBean objects which we get after
	 *         converting LocationMasterVO objects to LocationMasterBean objects.
	 */
	public List<LocationMasterBean> convertVosToEntities(List<LocationMasterVO> valueObjects) {
		List<LocationMasterBean> locationMasterBeans = new ArrayList<LocationMasterBean>();

		for (LocationMasterVO locationMasterVO : valueObjects) {
			locationMasterBeans.add(convertVoToEntity(locationMasterVO));
		}

		return locationMasterBeans;
	}

	/**
	 * This method is used to convert the List of Entities to List of valueObjects.
	 * 
	 * @param entities
	 *            The List of objects of LocationMasterBeans.
	 * @return returns the List object containing LocationMasterVO objects which we get after
	 *         converting LocationMasterBean objects to LocationMasterVO objects.
	 */
	public List<LocationMasterVO> convertEntitiesToVos(List<LocationMasterBean> entities) {
		List<LocationMasterVO> locationMasterVOs = new ArrayList<LocationMasterVO>();

		for (LocationMasterBean locationMasterBean : entities) {
			locationMasterVOs.add(convertEntityToVo(locationMasterBean));
		}
		return locationMasterVOs;
	}

}
