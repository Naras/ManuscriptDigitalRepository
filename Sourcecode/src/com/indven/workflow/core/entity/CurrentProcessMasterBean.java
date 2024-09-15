/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CurrentProcessMasterBean.java
 * Project Name							:	EPM
 * Date of Creation						: 	28th April 2012
 * Brief Description					:	This bean is for holding the current process master
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wfl_currentprocessmaster")
public class CurrentProcessMasterBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", updatable = false)
	private Long id;

	@Column(name = "ProcessMasterFkId", nullable = false)
	private Long ProcessMasterFkId;

	@Column(name = "LocationMasterFkId", nullable = false)
	private Long LocationMasterFkId;

	@Column(name = "ReferenceFkId", nullable = false)
	private Long ReferenceFkId;

	@Column(name = "Name", nullable = false)
	private String Name;

	@Column(name = "Description", nullable = false)
	private String Description;

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
	 * @return the processMasterFkId
	 */
	public Long getProcessMasterFkId() {
		return ProcessMasterFkId;
	}

	/**
	 * @param processMasterFkId
	 *            the processMasterFkId to set
	 */
	public void setProcessMasterFkId(Long processMasterFkId) {
		ProcessMasterFkId = processMasterFkId;
	}

	/**
	 * @return the locationMasterFkId
	 */
	public Long getLocationMasterFkId() {
		return LocationMasterFkId;
	}

	/**
	 * @param locationMasterFkId
	 *            the locationMasterFkId to set
	 */
	public void setLocationMasterFkId(Long locationMasterFkId) {
		LocationMasterFkId = locationMasterFkId;
	}

	/**
	 * @return the referenceFkId
	 */
	public Long getReferenceFkId() {
		return ReferenceFkId;
	}

	/**
	 * @param referenceFkId
	 *            the referenceFkId to set
	 */
	public void setReferenceFkId(Long referenceFkId) {
		ReferenceFkId = referenceFkId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

}
