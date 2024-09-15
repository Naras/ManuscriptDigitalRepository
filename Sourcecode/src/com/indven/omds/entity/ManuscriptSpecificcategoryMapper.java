package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "omds_manuscript_specificcategorymapper")
public class ManuscriptSpecificcategoryMapper implements Serializable{
private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "manuscriptfkid", insertable = true, updatable = true)
	private Long manuscriptFkId = null;

	@Column(name = "specificcategoryfkid", insertable = true, updatable = true)
	private Long specificcategoryFkId = null;

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
	 * @return the manuscriptFkId
	 */
	public final Long getManuscriptFkId() {
		return manuscriptFkId;
	}

	/**
	 * @param manuscriptFkId the manuscriptFkId to set
	 */
	public final void setManuscriptFkId(Long manuscriptFkId) {
		this.manuscriptFkId = manuscriptFkId;
	}

	/**
	 * @return the specificcategoryFkId
	 */
	public final Long getSpecificcategoryFkId() {
		return specificcategoryFkId;
	}

	/**
	 * @param specificcategoryFkId the specificcategoryFkId to set
	 */
	public final void setSpecificcategoryFkId(Long specificcategoryFkId) {
		this.specificcategoryFkId = specificcategoryFkId;
	}
	

}
