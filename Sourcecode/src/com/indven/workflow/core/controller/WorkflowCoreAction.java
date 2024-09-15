/**
 * 
 */
package com.indven.workflow.core.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.json.JSONException;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.omds.dao.ManuscriptMasterDAOImpl;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.vo.EmployeeMasterVO;
import com.indven.workflow.core.exception.WorkFlowCoreException;
import com.indven.workflow.core.service.WorkflowCoreServiceImpl;
import com.indven.workflow.core.vo.CurrentProcessDetailsVO;
import com.indven.workflow.core.vo.WorkflowCoreDataPacket;
import com.indven.workflow.core.vo.WorkflowCountEntityVO;
import com.indven.workflow.util.WorkflowCountTypeEnum;

/**
 * @author Deba Prasad
 *
 */
public class WorkflowCoreAction  extends BaseAction{

	/**
	 * 
	 */
	private WorkflowCoreServiceImpl service = new WorkflowCoreServiceImpl();
	private static final long serialVersionUID = 1L;
	private static IndvenLogger logger = IndvenLogger.getInstance(WorkflowCoreAction.class);
	
	private JSONObject jsonObject = new JSONObject();
	private WorkflowCoreDataPacket dataPacketVO ;
	private WorkflowCountEntityVO entityVO;
	private List<CurrentProcessDetailsVO> workflowListForUser;
	private Short isVisibleSave;
	private IndvenResultVO resultVO = new IndvenResultVO();
	
	public IndvenResultVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(IndvenResultVO resultVO) {
		this.resultVO = resultVO;
	}
	/**
	 * @return the isVisibleSave
	 */
	public final Short getIsVisibleSave() {
		return isVisibleSave;
	}

	/**
	 * @param isVisibleSave the isVisibleSave to set
	 */
	public final void setIsVisibleSave(Short isVisibleSave) {
		this.isVisibleSave = isVisibleSave;
	}

	/**
	 * @return the workflowListForUser
	 */
	public final List<CurrentProcessDetailsVO> getWorkflowListForUser() {
		return workflowListForUser;
	}

	/**
	 * @param workflowListForUser the workflowListForUser to set
	 */
	public final void setWorkflowListForUser(
			List<CurrentProcessDetailsVO> workflowListForUser) {
		this.workflowListForUser = workflowListForUser;
	}

	/**
	 * @return the entityVO
	 */
	public final WorkflowCountEntityVO getEntityVO() {
		return entityVO;
	}

	/**
	 * @param entityVO the entityVO to set
	 */
	public final void setEntityVO(WorkflowCountEntityVO entityVO) {
		this.entityVO = entityVO;
	}

	/**
	 * @return the dataPacketVO
	 */
	public final WorkflowCoreDataPacket getDataPacketVO() {
		return dataPacketVO;
	}

	/**
	 * @param dataPacketVO the dataPacketVO to set
	 */
	public final void setDataPacketVO(WorkflowCoreDataPacket dataPacketVO) {
		this.dataPacketVO = dataPacketVO;
	}

	/**
	 * @return the jsonObject
	 */
	public final JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * @param jsonObject the jsonObject to set
	 */
	public final void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getWFLTaskCount() {
		String status = ERROR;
		UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute("loginData");
		
		WorkflowCoreServiceImpl service = new WorkflowCoreServiceImpl();
		
		WorkflowCoreDataPacket obj;
		try {
			obj = service.getCountOfAssignedWorkflowsForUser(userInfoVO.getId());
			if(obj.getWorkflowCountsForUser() != null) {
				if(obj.getWorkflowCountsForUser().size() <= 0) {
					entityVO = new WorkflowCountEntityVO();
				} else {
					entityVO = obj.getWorkflowCountsForUser().get(0);
				}
				jsonObject.put("digitalManuscripts", entityVO);
			} else {
				
				entityVO.setCntRole(0L);
				jsonObject.put("digitalManuscripts", entityVO);
			}
			status = SUCCESS;
		} catch (WorkFlowCoreException e) {
			addActionError("Unable to fetch the task count.");
		}
			
		return status;
	}
	public String processId;
	
	/**
	 * @return the processId
	 */
	public final String getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public final void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getDetailedSummaryWFL() throws JSONException{
		String status = ERROR;

		org.json.JSONObject jObject  = new org.json.JSONObject(processId);
		
		entityVO = new WorkflowCountEntityVO();
		
		entityVO.setProcessId(Long.parseLong(jObject.getString("processId")));		
		entityVO.setCntDirect(Long.parseLong(jObject.getString("cntDirect")));
		entityVO.setProcessDescription(jObject.getString("processDescription"));
		entityVO.setCntRole(Long.parseLong(jObject.getString("cntRole")));
		
		status = SUCCESS;
		
		return status;
	}
	
	public String getWflDetailedList() {
		String status = ERROR;
		String id = getRequest().getParameter("id");
		String countType = getRequest().getParameter("counttype");
		isVisibleSave = (short)WorkflowCountTypeEnum.valueOf(countType).ordinal();
		
		UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute("loginData");
		
		WorkflowCoreServiceImpl service = new WorkflowCoreServiceImpl();
		WorkflowCoreDataPacket vo = service.getDetailedListOfAssignedWorkflowsForUser(Long.parseLong(id), userInfoVO.getId(),countType);
		
		if(vo != null) {
			workflowListForUser = vo.getWorkflowListForUser();
			
			for(int i=0 ; i< workflowListForUser.size() ; i++) {
				workflowListForUser.get(i).setUrl(workflowListForUser.get(i).getUrl() + "&countTypeValue="+isVisibleSave+"&currentProcessId="+workflowListForUser.get(i).getId()+"&currentprocessmasterid="+workflowListForUser.get(i).getProcessMasterId());
			}
			
		}
		status = SUCCESS;
		
		return status;
	}
	
	public String assignWorkToReviewer() {
		String status = ERROR;
		UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute("loginData");
		try {
			Long wflDtlId =  Long.parseLong(getRequest().getParameter("currentWFLDtsid"));
			Long processmasterId = Long.parseLong(getRequest().getParameter("currentprocessmasterid"));
			
			WorkflowCoreDataPacket vo = new WorkflowCoreDataPacket();
			vo.setActionType("ACTION_TYPE_SAVE");
			vo.setUserId(userInfoVO.getId());
			vo.setSelectedUserRoleId(0L);
			vo.setCurrentWflDet(new CurrentProcessDetailsVO());
			
			vo.getCurrentWflDet().setId(wflDtlId);
			vo.getCurrentWflDet().setProcessMasterId(processmasterId);
		
		
			new ManuscriptMasterDAOImpl().assignWorkToReviewer(vo);
			status = SUCCESS;
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}catch (Exception e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}
		return status;
	}
	
	public String forwardWorkToReviewer() {
		String status = ERROR;
		
		return status;
	}
	
	public String unAssignWFLToUser() {
		String status = ERROR;
		Long wflDtlId =  Long.parseLong(getRequest().getParameter("currentWFLDtsid"));
		Long currentRoleId = (Long)super.getRequest().getSession().getAttribute("currentRole");
		
		try {
			new WorkflowCoreServiceImpl().unAssignWFLToUser(wflDtlId,currentRoleId);
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}
		return status;
	}
	
	public String getReviewersList() {
		String status = ERROR;
		Long roleId = Long.parseLong(getRequest().getParameter("roleId"));
		List<EmployeeMasterVO> reviewerList = new ArrayList<>();
		
		try {
			reviewerList = service.listOfUsers(roleId);
			jsonObject.put("reviewerList", reviewerList);
			status = SUCCESS;
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(WorkFlowCoreException.UNABLE_TO_RETRIEV_USERS_LIST_WITH_ROLE, IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		} 
		return status;
	}
	
	public String branchWflInSameLabel() {
		String status = ERROR;
		Long referencePrjId = Long.parseLong(getRequest().getParameter("refPrjId"));
		try {
			service.branchWorkflow(referencePrjId);
			status = SUCCESS;
			addActionMessage("Successfully completed the request");
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}
		return status;
	}
	
	public String rejectWflToPreviousPerson() {
		String status = ERROR;
		Long referencePrjId = Long.parseLong(getRequest().getParameter("refPrjId"));
		
		try {
			service.rejectWorkFlow(referencePrjId);
			status = SUCCESS;
			addActionMessage("Successfully completed the request");
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}
		return status;
	}
	
}
