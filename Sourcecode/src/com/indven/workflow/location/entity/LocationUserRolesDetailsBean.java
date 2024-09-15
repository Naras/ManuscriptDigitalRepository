/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	LocationUserRoleDetails.java
 * Project Name							:	EPM
 * Date of Creation						: 	08th March 2012
 * Brief Description					:	
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wfl_locationuserroledetails")
public class LocationUserRolesDetailsBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "rolemasterfkid", nullable = false)
	private Long roleMasterFkId;

	@Column(name = "locationmasterfkid", nullable = false)
	private Long locationMasterFkId;

	@Column(name = "UserInfoFkId", nullable = false)
	private Long userInfoFkId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ValidFromDate", nullable = false)
	private Date validFromDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ValidToDate", nullable = false)
	private Date validToDate;

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
	 * @return the roleMasterFkId
	 */
	public Long getRoleMasterFkId() {
		return roleMasterFkId;
	}

	/**
	 * @param roleMasterFkId
	 *            the roleMasterFkId to set
	 */
	public void setRoleMasterFkId(Long roleMasterFkId) {
		this.roleMasterFkId = roleMasterFkId;
	}

	/**
	 * @return the locationMasterFkId
	 */
	public Long getLocationMasterFkId() {
		return locationMasterFkId;
	}

	/**
	 * @param locationMasterFkId
	 *            the locationMasterFkId to set
	 */
	public void setLocationMasterFkId(Long locationMasterFkId) {
		this.locationMasterFkId = locationMasterFkId;
	}

	/**
	 * @return the userInfoFkId
	 */
	public Long getUserInfoFkId() {
		return userInfoFkId;
	}

	/**
	 * @param userInfoFkId
	 *            the userInfoFkId to set
	 */
	public void setUserInfoFkId(Long userInfoFkId) {
		this.userInfoFkId = userInfoFkId;
	}

	/**
	 * @return the validFromDate
	 */
	public Date getValidFromDate() {
		return validFromDate;
	}

	/**
	 * @param validFromDate
	 *            the validFromDate to set
	 */
	public void setValidFromDate(Date validFromDate) {
		this.validFromDate = validFromDate;
	}

	/**
	 * @return the validToDate
	 */
	public Date getValidToDate() {
		return validToDate;
	}

	/**
	 * @param validToDate
	 *            the validToDate to set
	 */
	public void setValidToDate(Date validToDate) {
		this.validToDate = validToDate;
	}
}
