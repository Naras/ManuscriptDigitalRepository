/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	ProcessMasterBean.java
 * Project Name							:	EPM
 * Date of Creation						: 	08th March 2012
 * Brief Description					:	This bean is for holding the location level details
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.indven.framework.entity.BaseEntityBean;

/**
 * This bean will have all the Location level details
 * 
 * @author Sandeep Solomon Kunder
 * 
 * @see BaseEntityBean
 * 
 */
@Entity
@Table(name = "wfl_locationlevelmaster")
public class LocationLevelMasterBean {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", updatable = false)
	private Long id;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "LevelNumber", nullable = false)
	private Long levelNumber;

	@Column(name = "Description", nullable = true)
	private String description;

	@Column(name = "IconImageName", nullable = true)
	private String iconImageName;

	@OneToMany(mappedBy = "LevelFkIdObj")
	private List<LocationMasterBean> locationMasterList;

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

	/**
	 * @return the locationMasterList
	 */
	public List<LocationMasterBean> getLocationMasterList() {
		return locationMasterList;
	}

	/**
	 * @param locationMasterList
	 *            the locationMasterList to set
	 */
	public void setLocationMasterList(List<LocationMasterBean> locationMasterList) {
		this.locationMasterList = locationMasterList;
	}

}
