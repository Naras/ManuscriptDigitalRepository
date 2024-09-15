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
import javax.persistence.Table;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_organisation")
public class Organisation implements Serializable{

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
	
	@Column(name = "address", insertable = true, updatable = true )
	private String address;
	
	@Column(name = "website", insertable = true, updatable = true )
	private String website;
	
	@Column(name = "phoneNumber", insertable = true, updatable = true )
	private String phoneNumber;
	
	@Column(name = "email", insertable = true, updatable = true )
	private String email;
	
	@Column(name = "acronym", insertable = true, updatable = true )
	private String acronym;
	
	/**
	 * @return the acronym
	 */
	public final String getAcronym() {
		return acronym;
	}

	/**
	 * @param acronym the acronym to set
	 */
	public final void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	@Column(name = "type", insertable = true, updatable = true )
	private Short type;

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
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	
}
