/**
 * 
 */
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

/**
 * @author Deba
 *
 */
@Entity
@Table(name = "omds_manuscript_authormapper")
public class ManuscriptAuthorMapperBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "manuscriptfkid", insertable = false, updatable = false)
	private Long manuscriptFkId = null;

	@Column(name = "authorfkid", insertable = true, updatable = true)
	private Long authorFkId = null;
	
	@ManyToOne
	@JoinColumn(name = "authorfkid", insertable = false, updatable = false)
	private AuthorBean authorFkObj ;
	
	@ManyToOne
	@JoinColumn(name = "manuscriptfkid", nullable=true,insertable=true,updatable=true)
	private DigitalManuscriptBean manuscriptFkObj;

	/**
	 * @return the manuscriptFkObj
	 */
	public final DigitalManuscriptBean getManuscriptFkObj() {
		return manuscriptFkObj;
	}

	/**
	 * @param manuscriptFkObj the manuscriptFkObj to set
	 */
	public final void setManuscriptFkObj(DigitalManuscriptBean manuscriptFkObj) {
		this.manuscriptFkObj = manuscriptFkObj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getManuscriptFkId() {
		return manuscriptFkId;
	}

	public void setManuscriptFkId(Long manuscriptFkId) {
		this.manuscriptFkId = manuscriptFkId;
	}

	public Long getAuthorFkId() {
		return authorFkId;
	}

	public void setAuthorFkId(Long authorFkId) {
		this.authorFkId = authorFkId;
	}

	public final AuthorBean getAuthorFkObj() {
		return authorFkObj;
	}

	public  final void setAuthorFkObj(AuthorBean authorFkObj) {
		this.authorFkObj = authorFkObj;
	}

}
