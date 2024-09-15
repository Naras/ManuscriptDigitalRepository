/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuMasterBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	09th May 2013
 * Brief Description					:	This bean will have all menu details.
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

@Entity
@Table(name = "omds_menumaster")
public class MenuMasterBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -579509380408040547L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "parentid")
	private Long parentId = null;

	@Column(name = "menuname")
	private String menuName = null;

	@Column(name = "menulink")
	private String menuLink = null;
	
	@Column(name = "leftpanellink")
	private String leftPanelLink = null;

	@Column(name = "menuorder")
	private Integer menuOrder = null;

	@Column(name = "requestid")
	private String requestId = null;

	@Column(name = "statusmsg")
	private String statusMsg = null;

	@Column(name = "shortkey")
	private String shortKey = null;

	@Column(name = "menulevel")
	private Integer menuLevel = null;

	@Column(name = "defaultstatus")
	private String defaultStatus = null;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getLeftPanelLink() {
		return leftPanelLink;
	}

	public void setLeftPanelLink(String leftPanelLink) {
		this.leftPanelLink = leftPanelLink;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

}
