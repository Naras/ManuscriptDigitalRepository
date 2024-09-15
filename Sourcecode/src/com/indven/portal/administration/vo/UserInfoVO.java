/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	UserInfoVO.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	22nd April 2013
 * Brief Description					:	This class will be used as Value Object for UserInfoVO between UI and DB
 * Author								: 	Sandeep Solomon Kunder
 * Revision History
 *
 *************************************************************************************************************************/

package com.indven.portal.administration.vo;

import java.util.List;

import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.menu.vo.MenuMasterVO;


/**
 * This class will be used as Value Object for UserInfoVO between UI and DB
 *
 * @author Sandeep Solomon Kunder
 *
 */
public class UserInfoVO extends IndvenResultVO {


	private Long id;
	private String loginName;
	private String passWord;
	private Long referenceFkId;
	private String type;
	
	private String resetPasswordId;
	
	private List<Long> practiceCenterFkIdList;

	//The field name is added to store the full name(firstName + lastName) of the logged in person.
	private String name;
	
	private Long mapperFkId;
	private List<MenuMasterVO> menuList = null;
	
	//This field gets the role id of the logged in user
	private Long roleMasterFkId;
	private List<Long> roleIds;
	
	/**
	 * @return the roleIds
	 */
	public final List<Long> getRoleIds() {
		return roleIds;
	}
	/**
	 * @param roleIds the roleIds to set
	 */
	public final void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
	public List<MenuMasterVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuMasterVO> menuList) {
		this.menuList = menuList;
	}
	
	
	/**
	 * @return the mapperFkId
	 */
	public final Long getMapperFkId() {
		return mapperFkId;
	}

	/**
	 * @param mapperFkId the mapperFkId to set
	 */
	public final void setMapperFkId(Long mapperFkId) {
		this.mapperFkId = mapperFkId;
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
	 * @return the practiceCenterFkIdList
	 */
	public final List<Long> getPracticeCenterFkIdList() {
		return practiceCenterFkIdList;
	}

	/**
	 * @param practiceCenterFkIdList the practiceCenterFkIdList to set
	 */
	public final void setPracticeCenterFkIdList(List<Long> practiceCenterFkIdList) {
		this.practiceCenterFkIdList = practiceCenterFkIdList;
	}

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
	 * @return the loginName
	 */
	public final String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public final void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the passWord
	 */
	public final String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord
	 *            the passWord to set
	 */
	public final void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the referenceFkId
	 */
	public final Long getReferenceFkId() {
		return referenceFkId;
	}

	/**
	 * @param referenceFkId
	 *            the referenceFkId to set
	 */
	public final void setReferenceFkId(Long referenceFkId) {
		this.referenceFkId = referenceFkId;
	}

	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the resetPasswordId
	 */
	public final String getResetPasswordId() {
		return resetPasswordId;
	}

	/**
	 * @param resetPasswordId the resetPasswordId to set
	 */
	public final void setResetPasswordId(String resetPasswordId) {
		this.resetPasswordId = resetPasswordId;
	}
	
	
	public Long getRoleMasterFkId() {
		return roleMasterFkId;
	}

	public void setRoleMasterFkId(Long roleMasterFkId) {
		this.roleMasterFkId = roleMasterFkId;
	}

}
