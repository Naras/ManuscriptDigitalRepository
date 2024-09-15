/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	ProcessMasterAssembler.java
 * Project Name						:	EPM
 * Date of Creation					: 	18th March 2012
 * Brief Description				:	This class is used for process Master conversion operations from valueObject to Entity
 * Author							: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.component;

import java.util.ArrayList;
import java.util.List;

import com.indven.workflow.core.entity.ProcessMasterBean;
import com.indven.workflow.core.vo.ProcessMasterVO;

/**
 * This class is used for process Master conversion operations from valueObject to Entity
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public class ProcessMasterAssembler {

	public ProcessMasterVO convertEntityToVo(ProcessMasterBean entity) {

		ProcessMasterVO processMasterVO = new ProcessMasterVO();

		processMasterVO.setId(entity.getId());
		processMasterVO.setName(entity.getName());
		processMasterVO.setDescription(entity.getDescription());

		return processMasterVO;
	}

	public List<ProcessMasterVO> convertEntitiesToVos(List<ProcessMasterBean> entities) {
		List<ProcessMasterVO> processMasterVOs = new ArrayList<ProcessMasterVO>();

		for (ProcessMasterBean processMasterBean : entities) {
			processMasterVOs.add(convertEntityToVo(processMasterBean));
		}

		return processMasterVOs;
	}

}
