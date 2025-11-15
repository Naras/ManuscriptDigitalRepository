/**
 * 
 */
package com.indven.omds.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_subject1")
public class Subject1Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "name", insertable = true, updatable = true , nullable = false)
	private String name;
	
	@Column(name = "unicode_point", insertable = true, updatable = true )
	private String unicodePoint;

	@Column(name = "isdeleted")
	private Boolean isDeleted;
	
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
	 * @return the unicodePoint
	 */
	public final String getUnicodePoint() {
		return unicodePoint;
	}

	/**
	 * @param unicodePoint the unicodePoint to set
	 */
	public final void setUnicodePoint(String unicodePoint) {
		this.unicodePoint = unicodePoint;
	}
}
