/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EmployeeMasterBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	8th May 2013
 * Brief Description					:	
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.hrd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indven.framework.entity.BaseEntityBean;

/**
 * @author Saurabh
 *
 */
@Entity
@Table(name = "omds_employeemaster")
public class EmployeeMasterBean extends BaseEntityBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "firstname", nullable = false)
	private String firstName;
	
	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dob", insertable = true, updatable = true, nullable = false)
	private Date dateOfBirth;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "phonenumber", nullable = false)
	private String phoneNumber;
	
	@Column(name = "address1", nullable = false)
	private String address1;
	
	@Column(name = "type", nullable = false)
	private Short employeeType;
	
	@Column(name = "gender", nullable = false)
	private Short gender;
	
	@Column(name = "isdeleted")
	private Boolean isDeleted;
	
	
	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public final Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public final void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public final String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address1
	 */
	public final String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public final void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the employeeType
	 */
	public final Short getEmployeeType() {
		return employeeType;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public final void setEmployeeType(Short employeeType) {
		this.employeeType = employeeType;
	}


	/**
	 * @return the gender
	 */
	public final Short getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public final void setGender(Short gender) {
		this.gender = gender;
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
