/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	DaysEnum.java
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
public enum DaysEnum {
	SUN((short) 1), MON((short) 2), TUE((short) 3), WED((short) 4), THU((short) 5), FRI((short) 6), SAT((short) 7);

	private Short value;

	DaysEnum(Short value) {
		this.value = value;
	}

	public static DaysEnum fromValue(Short value) {  
		   if (value != null) {  
		     for (DaysEnum day : values()) {  
		       if (day.value.equals(value)) {  
		         return day;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid Day: " + value);  
	}
	public Short getValue() {
		return value;
	}

//	public void setValue(Short value) {
//		this.value = value;
//	}

}
