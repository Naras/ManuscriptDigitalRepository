/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EHRMSExceptionMessageResolver.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	16 April 2013
 * Brief Description					:	This class is responsible for resolving the error codes into messages from the property file and also replacing the place holders
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.exceptionhandler;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import com.indven.framework.exception.IndvenException;
import com.indven.framework.vo.IndvenResultVO;

public class IndvenExceptionMessageResolver {

	public void resolveMessage(IndvenResultVO result, IndvenException exception, Locale locale) {
		ResourceBundle res = ResourceBundle.getBundle("omds_status_messages", locale);
		String errorMsg = res.getString(exception.getMessage());

		if (exception.getArgs() != null) {
			// int i = 0;
			// TODO: Need to check and see if there is a direct way to do this
			// for (String str : exception.getArgs()) {
			// String s="({" + i + "})";
			// errorMsg.replaceAll(s, str);
			// i++;
			// }
			errorMsg = MessageFormat.format(errorMsg, exception.getArgs());
		}
		if (!result.getStatus().equals("success")) {
			result.setStatus(IndvenResultVO.STATUS_FAILURE);
		}

		result.setMessage(errorMsg);
	}

	public static String resolveMessage(String message, Locale locale) {

		ResourceBundle res = ResourceBundle.getBundle("omds_status_messages", locale);
		return res.getString(message);
	}

}
