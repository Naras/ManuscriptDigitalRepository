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
@Table(name = "omds_manuscript_tagmapper")
public class ManuscriptTagMapper  implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "manuscriptfkid", insertable = true, updatable = true)
	private Long manuscriptFkId = null;

	@Column(name = "tagsfkid", insertable = true, updatable = true)
	private Long tagsFkId = null;
	
	@ManyToOne
	@JoinColumn(name = "tagsfkid", insertable = false, updatable = false)
	private TagMasterBean tagsFkObj ;

	/**
	 * @return the tagsFkObj
	 */
	public final TagMasterBean getTagsFkObj() {
		return tagsFkObj;
	}

	/**
	 * @param tagsFkObj the tagsFkObj to set
	 */
	public final void setTagsFkObj(TagMasterBean tagsFkObj) {
		this.tagsFkObj = tagsFkObj;
	}

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
	 * @return the tagsFkId
	 */
	public final Long getTagsFkId() {
		return tagsFkId;
	}

	/**
	 * @param tagsFkId the tagsFkId to set
	 */
	public final void setTagsFkId(Long tagsFkId) {
		this.tagsFkId = tagsFkId;
	}




}
