/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	ProcessMasterVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	08th Mar 2012
 * Brief Description					:	This class will be used as Value Object for Location Level Master between UI and DB
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.vo;

import java.io.Serializable;

import com.indven.framework.vo.IndvenResultVO;

public class LocationLevelMasterVO extends IndvenResultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7360126355272948185L;

	private Long id;
	private String name;
	private Long levelNumber;
	private String description;
	private String iconImageName;

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
	 * @return the levelNumber
	 */
	public Long getLevelNumber() {
		return levelNumber;
	}

	/**
	 * @param levelNumber
	 *            the levelNumber to set
	 */
	public void setLevelNumber(Long levelNumber) {
		this.levelNumber = levelNumber;
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

	/**
	 * @return the iconImageName
	 */
	public String getIconImageName() {
		return iconImageName;
	}

	/**
	 * @param iconImageName
	 *            the iconImageName to set
	 */
	public void setIconImageName(String iconImageName) {
		this.iconImageName = iconImageName;
	}

}
