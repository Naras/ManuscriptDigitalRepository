package com.indven.workflow.core.vo;

public class CurrentProcessDetailsVO {

	private Long id;
	private Long processMasterId;
	private String screenName;
	private String url;
	private String assignedOn;
	private Boolean IsReturnButton;
	private Boolean IsTerminateButton;
	private Boolean IsSaveButton;
	private Boolean IsAuthorizeButton;

	private String languageName;
	private String scriptName;
	private String manuscriptType;
	
	/**
	 * @return the manuscriptType
	 */
	public final String getManuscriptType() {
		return manuscriptType;
	}

	/**
	 * @param manuscriptType the manuscriptType to set
	 */
	public final void setManuscriptType(String manuscriptType) {
		this.manuscriptType = manuscriptType;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

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
	 * @return the processMasterId
	 */
	public Long getProcessMasterId() {
		return processMasterId;
	}

	/**
	 * @param processMasterId
	 *            the processMasterId to set
	 */
	public void setProcessMasterId(Long processMasterId) {
		this.processMasterId = processMasterId;
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
	 * @return the assignedOn
	 */
	public String getAssignedOn() {
		return assignedOn;
	}

	/**
	 * @param assignedOn
	 *            the assignedOn to set
	 */
	public void setAssignedOn(String assignedOn) {
		this.assignedOn = assignedOn;
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
