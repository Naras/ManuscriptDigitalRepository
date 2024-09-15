/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CurrentProcessMasterVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	28th April 2012
 * Brief Description					:	This bean is for holding the current process master VO details
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.vo;

import com.indven.framework.vo.IndvenResultVO;

public class CurrentProcessMasterVO extends IndvenResultVO {

	private Long id;
	private Long refrenceFkId;
	private String name;
	private String description;

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
	 * @return the refrenceFkId
	 */
	public Long getRefrenceFkId() {
		return refrenceFkId;
	}

	/**
	 * @param refrenceFkId
	 *            the refrenceFkId to set
	 */
	public void setRefrenceFkId(Long refrenceFkId) {
		this.refrenceFkId = refrenceFkId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
