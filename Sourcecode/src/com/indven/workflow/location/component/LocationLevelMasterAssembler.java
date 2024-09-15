/**
 * 
 */
package com.indven.workflow.location.component;

import java.util.ArrayList;
import java.util.List;

import com.indven.workflow.location.entity.LocationLevelMasterBean;
import com.indven.workflow.location.vo.LocationLevelMasterVO;

/**
 * @author Saurabh
 * 
 */
public class LocationLevelMasterAssembler {// implements
											// Assembler<ProcessMasterVO,
											// ProcessMasterBean> {

	public LocationLevelMasterVO convertEntityToVo(LocationLevelMasterBean entity) {

		LocationLevelMasterVO locationLeveleMasterVO = new LocationLevelMasterVO();

		locationLeveleMasterVO.setId(entity.getId());
		locationLeveleMasterVO.setName(entity.getName());
		locationLeveleMasterVO.setDescription(entity.getDescription());
		locationLeveleMasterVO.setLevelNumber(entity.getLevelNumber());
		locationLeveleMasterVO.setIconImageName(entity.getIconImageName());

		return locationLeveleMasterVO;
	}

	public List<LocationLevelMasterVO> convertEntitiesToVos(List<LocationLevelMasterBean> entities) {
		List<LocationLevelMasterVO> projectMasterVOs = new ArrayList<LocationLevelMasterVO>();

		for (LocationLevelMasterBean locationLevelMasterBean : entities) {
			projectMasterVOs.add(convertEntityToVo(locationLevelMasterBean));
		}

		return projectMasterVOs;
	}

}
