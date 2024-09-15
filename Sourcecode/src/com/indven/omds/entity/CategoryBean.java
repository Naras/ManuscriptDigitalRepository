package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "omds_category")
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = -4462067184939565274L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "name")
	private String name = null;
	
	@Column(name = "isdeleted")
	private Boolean isDeleted;
	
	@Column(name = "parentfkid", insertable = true, updatable = true, nullable = true)
	private Long parentFKId;
	
	@ManyToOne
	@JoinColumn(name = "parentfkid", nullable=true,insertable=false,updatable=false)
	private CategoryBean parentFkObj;
	
	/**
	 * @return the parentFKId
	 */
	public final Long getParentFKId() {
		return parentFKId;
	}

	/**
	 * @param parentFKId the parentFKId to set
	 */
	public final void setParentFKId(Long parentFKId) {
		this.parentFKId = parentFKId;
	}

	/**
	 * @return the parentFkObj
	 */
	public final CategoryBean getParentFkObj() {
		return parentFkObj;
	}

	/**
	 * @param parentFkObj the parentFkObj to set
	 */
	public final void setParentFkObj(CategoryBean parentFkObj) {
		this.parentFkObj = parentFkObj;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
