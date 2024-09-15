/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuInfoVO.java
 * Project Name							:	WASP
 * Date of Creation						: 	19 AUG 2013
 * Brief Description					:	This class will be used as Value Object for MenuInfo between UI and DB
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.portal.menu.vo;

import java.io.Serializable;

import com.indven.framework.vo.IndvenResultVO;

/**
 * @author Saurabh
 * 
 */
public class RoleMasterMenuInfoVO extends IndvenResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private		Long roleId	  = null;
	private 	Long menuId	  = null;
	
	
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(final Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the menuID
	 */
	public Long getMenuId() {
		return menuId;
	}
	/**
	 * @param menuID the menuID to set
	 */
	public void setMenuId(final Long menuId) {
		this.menuId = menuId;
	}
	
}
