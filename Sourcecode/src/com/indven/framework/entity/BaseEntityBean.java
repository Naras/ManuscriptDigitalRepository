/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	BaseEntityBean.java
 * Project Name							:	EPM
 * Date of Creation						: 	12th December 2011
 * Brief Description					:	This class needs to be extended by every entity bean implementation which 
 * 											has createdBy, createdDate, modifiedBy, modifiedDate, rowVersion
 * Author								: 	Yusuf Pasha and Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author Yusuf
 * 
 */

@MappedSuperclass
public abstract class BaseEntityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createddate", insertable = true, updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifieddate")
	private Date modifiedDate;
	
	@Column(name = "createdby", insertable = true, updatable = false)
	private String createdBy;

	@Column(name = "modifiedby")
	private String modifiedBy;

	@Column(name = "rowversion")
	@Version
	// supports Hibernate optimistic locking
	private Integer rowVersion;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the rowVersion
	 */
	public final Integer getRowVersion() {
		return rowVersion;
	}

	/**
	 * @param rowVersion
	 *            the rowVersion to set
	 */
	public final void setRowVersion(Integer rowVersion) {
		this.rowVersion = rowVersion;
	}

	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public final Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public final void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public final String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public final void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public final String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public final void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		// return CustomToStringBuilder.reflectionToString(this);

		return "TODO";
	}

}
