/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	WorkflowCountEntityVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	06th April 2012
 * Brief Description					:	This is a VO which will be used for retrieving the counts of workflow assigned
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.workflow.core.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * This is a VO which will be used for retrieving the counts of workflow assigned This will be used
 * as both entity and a VO
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
@Entity
public class WorkflowCountEntityVO {

	public WorkflowCountEntityVO() {

	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private Long id;

	@Column(name = "processId", updatable = false)
	private Long processId;

	@Column(name = "cntDirect", updatable = false)
	private Long cntDirect;

	@Column(name = "cntRole", nullable = false)
	private Long cntRole;

	// private long cntTotal;

	@Column(name = "processDescription", nullable = false)
	private String processDescription;

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
	 * @return the processId
	 */
	public Long getProcessId() {
		return processId;
	}

	/**
	 * @param processId
	 *            the processId to set
	 */
	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	/**
	 * @return the cntDirect
	 */
	public Long getCntDirect() {
		return cntDirect;
	}

	/**
	 * @param cntDirect
	 *            the cntDirect to set
	 */
	public void setCntDirect(Long cntDirect) {
		this.cntDirect = cntDirect;
		// this.cntTotal = this.cntDirect + this.cntRole;
	}

	/**
	 * @return the cntRole
	 */
	public Long getCntRole() {
		return cntRole;
	}

	/**
	 * @param cntRole
	 *            the cntRole to set
	 */
	public void setCntRole(Long cntRole) {
		this.cntRole = cntRole;
		// this.cntTotal = this.cntDirect + this.cntRole;
	}

	/**
	 * @return the cntTotal
	 * 
	 *         public long getCntTotal() { return cntTotal; }
	 */
	/**
	 * @return the processDescription
	 */
	public String getProcessDescription() {
		return processDescription;
	}

	/**
	 * @param processDescription
	 *            the processDescription to set
	 */
	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

}
