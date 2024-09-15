/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EmployeeMasterBean.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	8th May 2013
 * Brief Description					:	This is the Enum use for Type of Employee
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.enums;

/**
 * @author Saurabh
 *
 */
public enum EmployeeTypeEnum {

	DOCTOR((short) 0), RECEPTIONIST((short) 1);

	private Short value;

	EmployeeTypeEnum(Short value) {
		this.value = value;
	}

	public static EmployeeTypeEnum fromValue(Short value) {  
		   if (value != null) {  
		     for (EmployeeTypeEnum day : values()) {  
		       if (day.value.equals(value)) {  
		         return day;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid Employee Type: " + value);  
	}
	public Short getValue() {
		return value;
	}
}
