/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	RoleMasterBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	13th May 2013
 * Brief Description					:	This bean will have all the RoleMaster information 
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
//
package com.indven.portal.administration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.indven.framework.entity.BaseEntityBean;

/**
 * This bean will have all the RoleMaster information
 * 
 * @author Sandeep Solomon Kunder
 * 
 * @see BaseEntityBean
 */
@Entity
@Table(name = "omds_rolemaster")
public class RoleMasterBean extends BaseEntityBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "isdeleted", nullable = true)
	private Boolean isDeleted;

	/**
	 * Constructor
	 */
	public RoleMasterBean() {
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

}
