/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	FrameworkException.java
 * Project Name						:	EPM
 * Date of Creation					: 	10 April 2013
 * Brief Description				:	This class will catch all the Framework Exception .
 * Author							: 	
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.exception;


/**
 * This class will catch all the Framework Exception .
 * 
 * 
 * 
 */
public class FrameworkException extends IndvenException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameworkException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

	public FrameworkException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameworkException(String message) {
		super(message);
	}

	public static final String RECORD_NOT_FOUND = "framework.util.epmentities.0001";
}
