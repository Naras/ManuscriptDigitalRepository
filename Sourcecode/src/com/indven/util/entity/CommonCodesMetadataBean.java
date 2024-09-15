/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CommonCodesMetadataBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.util.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity Bean to hold common codes meta data
 * 
 * @author Saurabh
 * 
 */
@Entity
@Table(name = "omds_commoncodesmetadata")
public class CommonCodesMetadataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "commonCodeMetaDataFkObj")
	private List<CommonCodesBean> commonCodes;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the commonCodes
	 */
	public final List<CommonCodesBean> getCommonCodes() {
		return commonCodes;
	}

	/**
	 * @param commonCodes the commonCodes to set
	 */
	public final void setCommonCodes(List<CommonCodesBean> commonCodes) {
		this.commonCodes = commonCodes;
	}

	
}
