/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	CurrentProcessDetailAssembler.java
 * Project Name						:	EPM
 * Date of Creation					: 	27th March 2012
 * Brief Description				:	This class is used for current process detail conversion operations from Entity to valueObject
 * Author							: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.component;

import java.util.ArrayList;
import java.util.List;

import com.indven.workflow.core.entity.CurrentProcessDetailsBean;
import com.indven.workflow.core.vo.CurrentProcessDetailsVO;

/**
 * This class is used for process Master conversion operations from valueObject to Entity
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public class CurrentProcessDetailAssembler {

	public CurrentProcessDetailsVO convertEntityToVo(CurrentProcessDetailsBean entity) {

		CurrentProcessDetailsVO curProcessDet = new CurrentProcessDetailsVO();

		curProcessDet.setId(entity.getId());
		curProcessDet.setScreenName(entity.getScreenName());
		curProcessDet.setAssignedOn((entity.getStartedOn() == null) ? "" : entity.getStartedOn().toString());
		curProcessDet.setUrl(entity.getUrl());
		curProcessDet.setProcessMasterId(entity.getCurrentProcessMasterFkId());
		curProcessDet.setIsAuthorizeButton(entity.getIsAuthorizeButton());
		curProcessDet.setIsSaveButton(entity.getIsSaveButton());
		curProcessDet.setIsReturnButton(entity.getIsReturnButton());
		curProcessDet.setIsTerminateButton(entity.getIsTerminateButton());

		return curProcessDet;
	}

	public List<CurrentProcessDetailsVO> convertEntitiesToVos(List<CurrentProcessDetailsBean> entities) {
		List<CurrentProcessDetailsVO> curProcessDetVOs = new ArrayList<CurrentProcessDetailsVO>();

		for (CurrentProcessDetailsBean curProcessDetBean : entities) {
			curProcessDetVOs.add(convertEntityToVo(curProcessDetBean));
		}

		return curProcessDetVOs;
	}

}
