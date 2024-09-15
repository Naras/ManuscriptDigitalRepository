/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EHRMSMessageResolver.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	16 April 2013
 * Brief Description					:	This class is responsible for resolving the error codes into messages from the property file 
 * Author								: 	Saurabh 
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.exceptionhandler;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Saurabh 
 * 
 */
public class IndvenMessageResolver {

	/**
	 * This method takes message and locale as input and resolving the error codes into messages
	 * from the property file
	 * 
	 * @param message
	 * @param locale
	 * @return message
	 */
	public static String resolveMessage(String message, Locale locale) {

		ResourceBundle res = ResourceBundle.getBundle("omds_status_messages", locale);
		return res.getString(message);
	}

}
