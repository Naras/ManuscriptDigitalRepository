/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	LocationMasterVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	27th December 2011
 * Brief Description					:	This class will be used as Value Object for Location Master between UI and DB
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.vo;

import java.util.List;

import com.indven.framework.combohandler.EPMComboEntityVO;
import com.indven.framework.vo.IndvenResultVO;

/**
 * @author Saurabh
 * 
 */

public class LocationMasterVO extends IndvenResultVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Long parentFkId;
	private Long levelFkId;
	private String levelFkName;
	private String levelImageName;
	private Boolean isDeleted;
	private Boolean isWorkflowEnabled;
	private List<EPMComboEntityVO> listOfRequiredRoles;
	private List<EPMComboEntityVO> listOfUsers;
	private Integer rowVersion;

	/**
	 * @return the rowVersion
	 */
	public final Integer getRowVersion() {
		return rowVersion;
	}

	/**
	 * @param rowVersion
	 *            the rowVersion to set
	 */
	public final void setRowVersion(Integer rowVersion) {
		this.rowVersion = rowVersion;
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parentFkId
	 */
	public final Long getParentFkId() {
		return parentFkId;
	}

	/**
	 * @param parentFkId
	 *            the parentFkId to set
	 */
	public final void setParentFkId(Long parentFkId) {
		this.parentFkId = parentFkId;
	}

	/**
	 * @return the levelFkName
	 */
	public String getLevelFkName() {
		return levelFkName;
	}

	/**
	 * @param levelFkName
	 *            the levelFkName to set
	 */
	public void setLevelFkName(String levelFkName) {
		this.levelFkName = levelFkName;
	}

	/**
	 * @return the levelImageName
	 */
	public String getLevelImageName() {
		return levelImageName;
	}

	/**
	 * @param levelImageName
	 *            the levelImageName to set
	 */
	public void setLevelImageName(String levelImageName) {
		this.levelImageName = levelImageName;
	}

	/**
	 * @return the levelFkId
	 */
	public final Long getLevelFkId() {
		return levelFkId;
	}

	/**
	 * @param levelFkId
	 *            the levelFkId to set
	 */
	public final void setLevelFkId(Long levelFkId) {
		this.levelFkId = levelFkId;
	}

	/**
	 * @return the isDeleted
	 */
	public final Boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public final void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the isWorkflowEnabled
	 */
	public Boolean getIsWorkflowEnabled() {
		return isWorkflowEnabled;
	}

	/**
	 * @param isWorkflowEnabled
	 *            the isWorkflowEnabled to set
	 */
	public void setIsWorkflowEnabled(Boolean isWorkflowEnabled) {
		this.isWorkflowEnabled = isWorkflowEnabled;
	}

	/**
	 * @return the listOfRequiredRoles
	 */
	public List<EPMComboEntityVO> getListOfRequiredRoles() {
		return listOfRequiredRoles;
	}

	/**
	 * @param listOfRequiredRoles
	 *            the listOfRequiredRoles to set
	 */
	public void setListOfRequiredRoles(List<EPMComboEntityVO> listOfRequiredRoles) {
		this.listOfRequiredRoles = listOfRequiredRoles;
	}

	/**
	 * @return the listOfUsers
	 */
	public List<EPMComboEntityVO> getListOfUsers() {
		return listOfUsers;
	}

	/**
	 * @param listOfUsers
	 *            the listOfUsers to set
	 */
	public void setListOfUsers(List<EPMComboEntityVO> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

}
