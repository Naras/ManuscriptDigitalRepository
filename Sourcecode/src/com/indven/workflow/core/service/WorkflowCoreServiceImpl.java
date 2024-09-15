/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	WorkflowCoreServiceImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	18th March 2012
 * Brief Description					:	This Service class will be responsible for all the core workflow related services 
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.workflow.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.indven.framework.combohandler.EPMCombosVO;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.portal.hrd.assembler.EmployeeMasterAssembler;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.hrd.vo.EmployeeMasterVO;
import com.indven.workflow.core.component.CurrentProcessDetailAssembler;
import com.indven.workflow.core.component.CurrentProcessMasterlAssembler;
import com.indven.workflow.core.component.ProcessMasterAssembler;
import com.indven.workflow.core.dao.WorkflowCoreDAOImpl;
import com.indven.workflow.core.entity.CurrentProcessDetailsBean;
import com.indven.workflow.core.entity.CurrentProcessMasterBean;
import com.indven.workflow.core.exception.WorkFlowCoreException;
import com.indven.workflow.core.vo.CurrentProcessDetailsVO;
import com.indven.workflow.core.vo.CurrentProcessMasterVO;
import com.indven.workflow.core.vo.ProcessMasterVO;
import com.indven.workflow.core.vo.WorkflowCoreDataPacket;
import com.indven.workflow.core.vo.WorkflowCountEntityVO;
import com.indven.workflow.util.WorkflowCountTypeEnum;

/**
 * This Service class will be responsible for all the core workflow related services
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public class WorkflowCoreServiceImpl {

	private ProcessMasterAssembler processMasterAssembler = new ProcessMasterAssembler();

	private WorkflowCoreDAOImpl dao = new WorkflowCoreDAOImpl();
	/**
	 * This method is responsible for retrieving the list of workflow processes from ProcessMaster.
	 * Used at 1. While creating a new location, to select the list of active workflows
	 * 
	 * @return returns an object of {@link FindAllResultVO} which will contain the list of
	 *         {@link ProcessMasterVO} objects
	 * @see ProcessMasterVO
	 */
	public FindAllResultVO<ProcessMasterVO> findAllWorkflowProcess() {

		FindAllResultVO<ProcessMasterVO> objResult = new FindAllResultVO<ProcessMasterVO>();

		List<ProcessMasterVO> processMasterVOsList = new ArrayList<ProcessMasterVO>();

		try {

			processMasterVOsList = processMasterAssembler.convertEntitiesToVos(new WorkflowCoreDAOImpl().findAllProcessMaster());

			objResult.setListOfElemnents(processMasterVOsList);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (WorkFlowCoreException we) {
			new IndvenExceptionMessageResolver().resolveMessage(objResult, we, IndvenApplicationConstants.LOCALE);
		}
		return objResult;
	}

	/**
	 * This method is used to get the count of the workflows for the passed user id
	 * 
	 * @param userId
	 * @return
	 */
	public WorkflowCoreDataPacket getCountOfAssignedWorkflowsForUser(Long userId) throws WorkFlowCoreException {

		WorkflowCoreDataPacket objResult = new WorkflowCoreDataPacket();

		

			List<WorkflowCountEntityVO> entities = new WorkflowCoreDAOImpl().getTotalcountOfWflsForUser(userId);
			
			objResult.setWorkflowCountsForUser(entities);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);

		
		return objResult;

	}

	/**
	 * This method is used to get the list of workflow levels that are assigned to the user
	 * 
	 * @param processId
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public WorkflowCoreDataPacket getDetailedListOfAssignedWorkflowsForUser(Long processId, Long userId, String countType) {

		WorkflowCoreDataPacket objResult = new WorkflowCoreDataPacket();
		// long countTypeVal = 2L;
		Map<String, Object> objMap = new HashMap<>();

		try {

			/*
			 * if(WorkflowCountTypeEnum.getValueFromName(countType) ==
			 * WorkflowCountTypeEnum.COUNT_TYPE_ALL){ countTypeVal = 2L; } else
			 * if(WorkflowCountTypeEnum.getValueFromName(countType) ==
			 * WorkflowCountTypeEnum.COUNT_TYPE_DIRECT){ countTypeVal = 1L; } else
			 * if(WorkflowCountTypeEnum.getValueFromName(countType) ==
			 * WorkflowCountTypeEnum.COUNT_TYPE_ROLE){ countTypeVal = 0L; }
			 */

			// WorkflowCountTypeEnum obj = new WorkflowCountTypeEnum(countType);

			objMap = new WorkflowCoreDAOImpl().getDetailedWorkflowList(processId, userId,
					WorkflowCountTypeEnum.getValueFromName(countType).getValue());
		  //  List<CurrentProcessDetailsBean> entities = TestMethod.getTestVOS();
			List<CurrentProcessDetailsBean> entities = (List<CurrentProcessDetailsBean>) objMap.get("wflDetails");
			List<String> languageNameList = (List<String>) objMap.get("languageList");
			List<String> scriptNameList = (List<String>) objMap.get("scriptList");
			List<String> manTypeList = (List<String>) objMap.get("manTypeList");
			
			List<CurrentProcessDetailsVO> curProcessDetVOs = new ArrayList<CurrentProcessDetailsVO>();

			for (int i=0; i<entities.size() ; i++) {
				CurrentProcessDetailsVO vo = new CurrentProcessDetailAssembler().convertEntityToVo(entities.get(i));
				vo.setLanguageName(languageNameList.get(i));
				vo.setScriptName(scriptNameList.get(i));
				vo.setManuscriptType(manTypeList.get(i));
				curProcessDetVOs.add(vo);
			}
			
			objResult.setWorkflowListForUser(curProcessDetVOs);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (WorkFlowCoreException we) {
			new IndvenExceptionMessageResolver().resolveMessage(objResult, we, IndvenApplicationConstants.LOCALE);
		}
		return objResult;

	}

	/**
	 * This method is used to get the process Master details for a given level
	 * 
	 * @param processId
	 * @return
	 */
	public CurrentProcessMasterVO getProcessMasterDetails(Long processMasterId) {

		CurrentProcessMasterBean curMasterBean = new CurrentProcessMasterBean();
		CurrentProcessMasterVO curMasterVo = new CurrentProcessMasterVO();
		;

		try {

			// WorkflowCountTypeEnum obj = new WorkflowCountTypeEnum(countType);

			curMasterBean = new WorkflowCoreDAOImpl().findCurrentProcessMasterById(processMasterId);

			curMasterVo = new CurrentProcessMasterlAssembler().convertEntityToVo(curMasterBean);
			curMasterVo.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (WorkFlowCoreException we) {
			new IndvenExceptionMessageResolver().resolveMessage(curMasterVo, we, IndvenApplicationConstants.LOCALE);
		}
		return curMasterVo;

	}

	/**
	 * This method us responsible for retriving the list of users for the next level in the process
	 * 
	 * @param currentProcessLevelId
	 * @return
	 */
	public EPMCombosVO getUserForNextLevel(Long currentProcessLevelId) {
		EPMCombosVO listOfUsers = new EPMCombosVO();

		try {

			listOfUsers.getListOfComboData().put(currentProcessLevelId,
					new WorkflowCoreDAOImpl().getUsersForNextLevel(currentProcessLevelId));
			listOfUsers.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (WorkFlowCoreException we) {
			new IndvenExceptionMessageResolver().resolveMessage(listOfUsers, we, IndvenApplicationConstants.LOCALE);
		}
		return listOfUsers;
	}
	
	/**
	 * This method is used to unassign a particular user from a task and make the task available to particular role pool again.
	 * @param currentProcessId
	 * @param roleIdToBeAssigned
	 * @return
	 * @throws OMDPCoreException 
	 */
	public boolean unAssignWFLToUser(Long currentProcessId , Long roleIdToBeAssigned) throws WorkFlowCoreException {
		boolean isSuccess = false;
		isSuccess = new WorkflowCoreDAOImpl().unAssignWFLToUser(currentProcessId , roleIdToBeAssigned);
		return isSuccess;
	}

	/**
	 * This method will retrieve all the employees with specified role depending on the mapping in wfl_userroledetails table.
	 * @param roleId
	 * @return
	 * @throws WorkFlowCoreException 
	 */
	public List<EmployeeMasterVO> listOfUsers(Long roleId) throws WorkFlowCoreException {
		List<EmployeeMasterVO> empVos = new ArrayList<>();
		List<EmployeeMasterBean> empBeanList;
		
		empBeanList = dao.empBeanListWithSpecificRole(roleId);
		empVos = EmployeeMasterAssembler.convertEntitiesToVos(empBeanList);
		return empVos;
	}
	
	public boolean branchWorkflow(Long referenceProjectId) throws WorkFlowCoreException {
		
		return dao.branchWorkflow(referenceProjectId);
	}
	
	public boolean rejectWorkFlow(Long referenceProjectId) throws WorkFlowCoreException {
		return dao.rejectWfl(referenceProjectId);
	}
	
	public boolean assignProcessFromUserToRole(Long processDtlsId) throws WorkFlowCoreException {
		return dao.assignProcessFromUserToRole(processDtlsId);
	}
	
	public int isPresentReviewer2(Long prjRefId) throws WorkFlowCoreException {
		return dao.isPresentReviewer2(prjRefId);
	}
}
