/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	ProcessMasterVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	18th Mar 2012
 * Brief Description					:	This class will be used as Value Object for Process Master between UI and DB
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.vo;

import com.indven.framework.vo.IndvenResultVO;


public class ProcessMasterVO extends IndvenResultVO {

	private Long id;
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
