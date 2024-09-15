package com.indven.workflow.core.vo;

import java.util.List;

import com.indven.framework.vo.IndvenResultVO;

public class WorkflowCoreDataPacket extends IndvenResultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5589890309634344529L;

	private List<WorkflowCountEntityVO> workflowCountsForUser = null;
	private List<CurrentProcessDetailsVO> workflowListForUser = null;
	private Long currentWflType;
	private CurrentProcessDetailsVO currentWflDet;
	private String actionType;
	private Long userId;
	private Long selectedUserRoleId;

	/**
	 * @return the workflowCountsForUser
	 */
	public List<WorkflowCountEntityVO> getWorkflowCountsForUser() {
		return workflowCountsForUser;
	}

	/**
	 * @param workflowCountsForUser
	 *            the workflowcountsForUser to set
	 */
	public void setWorkflowCountsForUser(List<WorkflowCountEntityVO> workflowCountsForUser) {
		this.workflowCountsForUser = workflowCountsForUser;
	}

	/**
	 * @return the workflowListForUser
	 */
	public List<CurrentProcessDetailsVO> getWorkflowListForUser() {
		return workflowListForUser;
	}

	/**
	 * @param workflowListForUser
	 *            the workflowListForUser to set
	 */
	public void setWorkflowListForUser(List<CurrentProcessDetailsVO> workflowListForUser) {
		this.workflowListForUser = workflowListForUser;
	}

	/**
	 * @return the currentWflType
	 */
	public Long getCurrentWflType() {
		return currentWflType;
	}

	/**
	 * @param currentWflType
	 *            the currentWflType to set
	 */
	public void setCurrentWflType(Long currentWflType) {
		this.currentWflType = currentWflType;
	}

	/**
	 * @return the currentWflDet
	 */
	public CurrentProcessDetailsVO getCurrentWflDet() {
		return currentWflDet;
	}

	/**
	 * @param currentWflDet
	 *            the currentWflDet to set
	 */
	public void setCurrentWflDet(CurrentProcessDetailsVO currentWflDet) {
		this.currentWflDet = currentWflDet;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the selectedUserRoleId
	 */
	public Long getSelectedUserRoleId() {
		return selectedUserRoleId;
	}

	/**
	 * @param selectedUserRoleId
	 *            the selectedUserRoleId to set
	 */
	public void setSelectedUserRoleId(Long selectedUserRoleId) {
		this.selectedUserRoleId = selectedUserRoleId;
	}

}
