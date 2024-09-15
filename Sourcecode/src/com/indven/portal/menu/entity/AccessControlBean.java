/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	AccessControlBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	13th May 2013
 * Brief Description					:	This bean will have all the Roles to menu mapping.
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.menu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This bean will have all the Roles to menu mapping.
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
@Entity
@Table(name = "omds_accesscontrol")
public class AccessControlBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "rolemasterfkid", insertable = true, updatable = true)
	private Long roleMasterFkId = null;

	@Column(name = "menumasterfkid", insertable = true, updatable = true)
	private Long menuMasterFkId = null;

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

	public Long getMenuMasterFkId() {
		return menuMasterFkId;
	}

	public void setMenuMasterFkId(Long menuMasterFkId) {
		this.menuMasterFkId = menuMasterFkId;
	}
}
