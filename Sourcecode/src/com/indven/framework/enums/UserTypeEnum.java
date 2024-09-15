/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	UserTypeEnum.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	24 April 2013
 * Brief Description					:	This Class is enum type and it is use for converting user type to respected values
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.enums;

/**
 * This Class is enum type and it is use for converting user type to respected values
 * 
 * @author Saurabh
 * 
 */
public enum UserTypeEnum {
	PATIENT((short) 0), EMPLOYEE((short) 1), ADMIN((short) 2);

	private Short value;

	UserTypeEnum(Short value) {
		this.value = value;
	}

	public static UserTypeEnum fromValue(Short value) {  
		   if (value != null) {  
		     for (UserTypeEnum day : values()) {  
		       if (day.value.equals(value)) {  
		         return day;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid User Type: " + value);  
	}
	public Short getValue() {
		return value;
	}

//	public void setValue(Short value) {
//		this.value = value;
//	}

}
