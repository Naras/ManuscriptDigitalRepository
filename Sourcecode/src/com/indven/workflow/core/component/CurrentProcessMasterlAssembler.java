/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	CurrentProcessMasterlAssembler.java
 * Project Name						:	EPM
 * Date of Creation					: 	28th April 2012
 * Brief Description				:	This class is used for current process master conversion operations from Entity to valueObject
 * Author							: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.component;

import java.util.ArrayList;
import java.util.List;

import com.indven.workflow.core.entity.CurrentProcessMasterBean;
import com.indven.workflow.core.vo.CurrentProcessMasterVO;

/**
 * This class is used for process Master conversion operations from valueObject to Entity
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public class CurrentProcessMasterlAssembler {

	public CurrentProcessMasterVO convertEntityToVo(CurrentProcessMasterBean entity) {

		CurrentProcessMasterVO curProcessMast = new CurrentProcessMasterVO();

		curProcessMast.setId(entity.getId());
		curProcessMast.setName(entity.getName());
		curProcessMast.setDescription(entity.getDescription());
		curProcessMast.setRefrenceFkId(entity.getReferenceFkId());

		return curProcessMast;
	}

	public List<CurrentProcessMasterVO> convertEntitiesToVos(List<CurrentProcessMasterBean> entities) {
		List<CurrentProcessMasterVO> curProcessDetVOs = new ArrayList<CurrentProcessMasterVO>();

		for (CurrentProcessMasterBean curProcessMastBean : entities) {
			curProcessDetVOs.add(convertEntityToVo(curProcessMastBean));
		}

		return curProcessDetVOs;
	}

}
