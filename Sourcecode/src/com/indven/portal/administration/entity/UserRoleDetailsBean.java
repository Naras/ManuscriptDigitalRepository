/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	UserRoleDetailsBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	13th May 2013
 * Brief Description					:	This bean will have all the Roles to users mapping.
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.administration.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This bean will have all the Roles to menu mapping.
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
@Entity
@Table(name = "wfl_locationuserroledetails")
public class UserRoleDetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "rolemasterfkid", insertable = true, updatable = true)
	private Long roleMasterFkId = null;

	@Column(name = "UserInfoFkId", insertable = true, updatable = true)
	private Long userLoginDetailsFkId = null;
	
	@Column(name = "locationmasterfkid", nullable = false)
	private Long locationMasterFkId;

	@ManyToOne
	@JoinColumn(name = "rolemasterfkid", nullable=true,insertable=false,updatable=false)
	private RoleMasterBean roleMasterFKObj;
	
	/**
	 * @return the locationMasterFkId
	 */
	public final Long getLocationMasterFkId() {
		return locationMasterFkId;
	}

	/**
	 * @param locationMasterFkId the locationMasterFkId to set
	 */
	public final void setLocationMasterFkId(Long locationMasterFkId) {
		this.locationMasterFkId = locationMasterFkId;
	}

	/**
	 * @return the roleMasterFKObj
	 */
	public final RoleMasterBean getRoleMasterFKObj() {
		return roleMasterFKObj;
	}

	/**
	 * @param roleMasterFKObj the roleMasterFKObj to set
	 */
	public final void setRoleMasterFKObj(RoleMasterBean roleMasterFKObj) {
		this.roleMasterFKObj = roleMasterFKObj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleMasterFkId() {
		return roleMasterFkId;
	}

	public void setRoleMasterFkId(Long roleMasterFkId) {
		this.roleMasterFkId = roleMasterFkId;
	}

	public Long getUserLoginDetailsFkId() {
		return userLoginDetailsFkId;
	}

	public void setUserLoginDetailsFkId(Long userLoginDetailsFkId) {
		this.userLoginDetailsFkId = userLoginDetailsFkId;
	}
        
        public String toString() { 
            return ("id "+ id + " roleMaster FkId " + roleMasterFkId + " user Login FkId " + userLoginDetailsFkId
                    + " location master FkId " + locationMasterFkId);
        }
}
 