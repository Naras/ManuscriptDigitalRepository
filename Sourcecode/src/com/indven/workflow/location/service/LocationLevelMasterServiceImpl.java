package com.indven.workflow.location.service;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.workflow.location.component.LocationLevelMasterAssembler;
import com.indven.workflow.location.dao.LocationLevelMasterDAOImpl;
import com.indven.workflow.location.exception.LocationException;
import com.indven.workflow.location.vo.LocationLevelMasterVO;

public class LocationLevelMasterServiceImpl {

	private LocationLevelMasterAssembler locationLevelMasterAssembler = new LocationLevelMasterAssembler();

	public FindAllResultVO<LocationLevelMasterVO> findAllLocationLevel() {

		FindAllResultVO<LocationLevelMasterVO> objResult = new FindAllResultVO<LocationLevelMasterVO>();

		List<LocationLevelMasterVO> locationLevelMasterVOsList = new ArrayList<LocationLevelMasterVO>();

		try {

			locationLevelMasterVOsList = locationLevelMasterAssembler.convertEntitiesToVos(new LocationLevelMasterDAOImpl()
					.findAllLocationLevel());

			objResult.setListOfElemnents(locationLevelMasterVOsList);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (LocationException we) {
			new IndvenExceptionMessageResolver().resolveMessage(objResult, we, IndvenApplicationConstants.LOCALE);
		}
		return objResult;
	}

}
