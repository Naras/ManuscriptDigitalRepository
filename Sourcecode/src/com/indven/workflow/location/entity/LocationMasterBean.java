/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	PaymentsBean.java
 * Project Name							:	EPM
 * Date of Creation						: 	14th December 2011
 * Brief Description					:	This bean will have all the payments information for a given project
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.indven.framework.entity.BaseEntityBean;

/**
 * 
 * @author Saurabh,Nithya
 * 
 * @see BaseEntityBean
 * 
 */
@Entity
@Table(name = "wfl_locationmaster")
public class LocationMasterBean extends BaseEntityBean {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Description", nullable = false)
	private String description;

	@Column(name = "IsDeleted", nullable = false)
	private Boolean isDeleted;

	@Column(name = "IsWorkflowEnabled", nullable = false)
	private Boolean isWorkflowEnabled;

	@ManyToOne
	@JoinColumn(name = "LevelFkId", nullable = false, insertable = false, updatable = false)
	private LocationLevelMasterBean LevelFkIdObj = null;

	@Column(name = "LevelFkId", nullable = false, insertable = true, updatable = true)
	private Long levelFkId;

	@ManyToOne
	@JoinColumn(name = "ParentFkId", nullable = false, insertable = false, updatable = false)
	private LocationMasterBean parentFkIdObj = null;

	@Column(name = "ParentFkId", insertable = true, updatable = true)
	private Long parentFkId = null;


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
	 * @return the levelFkIdObj
	 */
	public LocationLevelMasterBean getLevelFkIdObj() {
		return LevelFkIdObj;
	}

	/**
	 * @param levelFkIdObj
	 *            the levelFkIdObj to set
	 */
	public void setLevelFkIdObj(LocationLevelMasterBean levelFkIdObj) {
		LevelFkIdObj = levelFkIdObj;
	}

	/**
	 * @return the parentFkIdObj
	 */
	public final LocationMasterBean getParentFkIdObj() {
		return parentFkIdObj;
	}

	/**
	 * @param parentFkIdObj
	 *            the parentFkIdObj to set
	 */
	public final void setParentFkIdObj(LocationMasterBean parentFkIdObj) {
		this.parentFkIdObj = parentFkIdObj;
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

}
