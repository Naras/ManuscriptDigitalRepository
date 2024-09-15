/**
 * 
 */
package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.indven.omds.util.MaterialTypeEnum;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_material")
public class MaterialBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "name", nullable = true)
	private MaterialTypeEnum name;

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
	public final MaterialTypeEnum getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(MaterialTypeEnum name) {
		this.name = name;
	}
}
