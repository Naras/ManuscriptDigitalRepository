/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CurrentProcessDetailsBean.java
 * Project Name							:	EPM
 * Date of Creation						: 	26th March 2012
 * Brief Description					:	This bean is for holding the current process details
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This bean is for holding the process master details
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
@Entity
@Table(name = "wfl_currentprocessdetails")
public class CurrentProcessDetailsBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", updatable = false)
	private Long id;

	@Column(name = "Level", nullable = false)
	private Long level;

	@Column(name = "LocationUserRoleFkId", nullable = false)
	private Long LocationUserRoleFkId;

	@Column(name = "CurrentProcessMasterFkId", nullable = false)
	private Long CurrentProcessMasterFkId;

	@Column(name = "Url", nullable = false)
	private String url;

	@Column(name = "Status", nullable = false)
	private Short status;

	@Column(name = "ScreenName", nullable = false)
	private String screenName;

	@Column(name = "ProcessTimeOut", nullable = false)
	private Short processTimeOut;

	@Column(name = "IsUserRoleId", nullable = false)
	private Short isUserRoleId;

	@Column(name = "StartedOn", nullable = true)
	private Date startedOn;

	@Column(name = "CompletedOn", nullable = true)
	private Date completedOn;

	@Column(name = "IsReturnButton", nullable = false)
	private Boolean IsReturnButton;

	@Column(name = "IsTerminateButton", nullable = false)
	private Boolean IsTerminateButton;

	@Column(name = "IsSaveButton", nullable = false)
	private Boolean IsSaveButton;

	@Column(name = "IsAuthorizeButton", nullable = false)
	private Boolean IsAuthorizeButton;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the level
	 */
	public Long getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Long level) {
		this.level = level;
	}

	/**
	 * @return the locationUserRoleFkId
	 */
	public Long getLocationUserRoleFkId() {
		return LocationUserRoleFkId;
	}

	/**
	 * @param locationUserRoleFkId
	 *            the locationUserRoleFkId to set
	 */
	public void setLocationUserRoleFkId(Long locationUserRoleFkId) {
		LocationUserRoleFkId = locationUserRoleFkId;
	}

	/**
	 * @return the currentProcessMasterFkId
	 */
	public Long getCurrentProcessMasterFkId() {
		return CurrentProcessMasterFkId;
	}

	/**
	 * @param currentProcessMasterFkId
	 *            the currentProcessMasterFkId to set
	 */
	public void setCurrentProcessMasterFkId(Long currentProcessMasterFkId) {
		CurrentProcessMasterFkId = currentProcessMasterFkId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName
	 *            the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the processTimeOut
	 */
	public Short getProcessTimeOut() {
		return processTimeOut;
	}

	/**
	 * @param processTimeOut
	 *            the processTimeOut to set
	 */
	public void setProcessTimeOut(Short processTimeOut) {
		this.processTimeOut = processTimeOut;
	}

	/**
	 * @return the isUserRoleId
	 */
	public Short getIsUserRoleId() {
		return isUserRoleId;
	}

	/**
	 * @param isUserRoleId
	 *            the isUserRoleId to set
	 */
	public void setIsUserRoleId(Short isUserRoleId) {
		this.isUserRoleId = isUserRoleId;
	}

	/**
	 * @return the startedOn
	 */
	public Date getStartedOn() {
		return startedOn;
	}

	/**
	 * @param startedOn
	 *            the startedOn to set
	 */
	public void setStartedOn(Date startedOn) {
		this.startedOn = startedOn;
	}

	/**
	 * @return the completedOn
	 */
	public Date getCompletedOn() {
		return completedOn;
	}

	/**
	 * @param completedOn
	 *            the completedOn to set
	 */
	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	/**
	 * @return the isReturnButton
	 */
	public Boolean getIsReturnButton() {
		return IsReturnButton;
	}

	/**
	 * @param isReturnButton
	 *            the isReturnButton to set
	 */
	public void setIsReturnButton(Boolean isReturnButton) {
		IsReturnButton = isReturnButton;
	}

	/**
	 * @return the isTerminateButton
	 */
	public Boolean getIsTerminateButton() {
		return IsTerminateButton;
	}

	/**
	 * @param isTerminateButton
	 *            the isTerminateButton to set
	 */
	public void setIsTerminateButton(Boolean isTerminateButton) {
		IsTerminateButton = isTerminateButton;
	}

	/**
	 * @return the isSaveButton
	 */
	public Boolean getIsSaveButton() {
		return IsSaveButton;
	}

	/**
	 * @param isSaveButton
	 *            the isSaveButton to set
	 */
	public void setIsSaveButton(Boolean isSaveButton) {
		IsSaveButton = isSaveButton;
	}

	/**
	 * @return the isAuthorizeButton
	 */
	public Boolean getIsAuthorizeButton() {
		return IsAuthorizeButton;
	}

	/**
	 * @param isAuthorizeButton
	 *            the isAuthorizeButton to set
	 */
	public void setIsAuthorizeButton(Boolean isAuthorizeButton) {
		IsAuthorizeButton = isAuthorizeButton;
	}

}
