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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.indven.framework.entity.BaseEntityBean;

/**
 * Entity Bean to hold common codes
 * 
 * @author Saurabh
 * @see BaseEntityBean
 * 
 */
@Entity
@Table(name = "omds_commoncodes")
public class CommonCodesBean   implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "commoncodesmetadatafkid", insertable = true, updatable = true)
	private Long commonCodeMetaDataFkId = null;
	
	@ManyToOne
	@JoinColumn(name = "commoncodesmetadatafkid",nullable = false, insertable = false, updatable = false)
	private CommonCodesMetadataBean commonCodeMetaDataFkObj = null;

	/**
	 * Constructor
	 */
	public CommonCodesBean() {

	}

	/**
	 * @param id
	 * @param name
	 * @param commonCodeMetaDataFkId
	 */
	public CommonCodesBean(Long id, String name, Long commonCodeMetaDataFkId) {
		this.id = id;
		this.name = name;
		this.commonCodeMetaDataFkId = commonCodeMetaDataFkId;
	}


	

	/**
	 * @return the commonCodeMetaDataFkObj
	 */
	public final CommonCodesMetadataBean getCommonCodeMetaDataFkObj() {
		return commonCodeMetaDataFkObj;
	}

	/**
	 * @param commonCodeMetaDataFkObj the commonCodeMetaDataFkObj to set
	 */
	public final void setCommonCodeMetaDataFkObj(
			CommonCodesMetadataBean commonCodeMetaDataFkObj) {
		this.commonCodeMetaDataFkObj = commonCodeMetaDataFkObj;
	}

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
	 * @return the commonCodeMetaDataFkId
	 */
	public final Long getCommonCodeMetaDataFkId() {
		return commonCodeMetaDataFkId;
	}



	/**
	 * @param commonCodeMetaDataFkId the commonCodeMetaDataFkId to set
	 */
	public final void setCommonCodeMetaDataFkId(Long commonCodeMetaDataFkId) {
		this.commonCodeMetaDataFkId = commonCodeMetaDataFkId;
	}

}
