/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EPMComboEntityVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	07th February 2012
 * Brief Description					:	This is a common VO which will be used for retrieving all the data from different tables for combo handling
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.framework.combohandler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * This is a common VO which will be used for retrieving all the data from different tables for
 * combo handling
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
@Entity
public class EPMComboEntityVO {

	public EPMComboEntityVO() {

	}

	public EPMComboEntityVO(String label, String value) {
		this.label = label;
		this.value = value;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private long id;

	@Column(name = "Value", updatable = false)
	private String value;

	@Column(name = "Label", nullable = false)
	private String label;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
