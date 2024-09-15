/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	StatusEnum.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	24 April 2013
 * Brief Description					:	This Class is enum type and it is use for converting Days to respected values
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.enums;

/**
 * This Class is enum type and it is use for converting Days to respected values
 * 
 * @author Saurabh
 * 
 */
public enum UserStatusEnum {
	ACTIVE((short) 0), INACTIVE((short) 1);

	private Short value;

	UserStatusEnum(Short value) {
		this.value = value;
	}

	public static UserStatusEnum fromValue(Short value) {  
		   if (value != null) {  
		     for (UserStatusEnum day : values()) {  
		       if (day.value.equals(value)) {  
		         return day;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid Status: " + value);  
	}
	public Short getValue() {
		return value;
	}

//	public void setValue(Short value) {
//		this.value = value;
//	}

}
