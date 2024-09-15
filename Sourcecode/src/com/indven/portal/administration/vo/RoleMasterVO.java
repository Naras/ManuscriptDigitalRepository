package com.indven.portal.administration.vo;

import java.io.Serializable;

import com.indven.framework.vo.IndvenResultVO;

public class RoleMasterVO extends IndvenResultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String description;
	private Boolean isDeleted;
	private Integer rowVersion;
	/**
	 * @return the rowVersion
	 */
	public final Integer getRowVersion() {
		return rowVersion;
	}
	/**
	 * @param rowVersion the rowVersion to set
	 */
	public final void setRowVersion(Integer rowVersion) {
		this.rowVersion = rowVersion;
	}
	/**
	 * @return the id
	 */
	public final long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(long id) {
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
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
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
	 * @param isDeleted the isDeleted to set
	 */
	public final void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
