/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EHRMSStaleObjectException.java
 * Project Name							:	EPM
 * Date of Creation						: 	07th june 2013
 * Brief Description					:	This Class contains error constants for framework  package 											 
 * 											
 * Author								: 	Deba Prasad
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.exception;

/**
 * This Class contains error constants for framework  package
 * 
 * @author Deba Prasad
 *
 */
public class IndvenStaleObjectException extends IndvenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndvenStaleObjectException(String message, String[] args, Throwable cause) {
		super(message, args, cause);
	}

	public IndvenStaleObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndvenStaleObjectException(String message) {
		super(message);
	}

	public static final String STALE_OBJECT_EXCEPTION = "framework.util.staleobjectexception.0001";
	public static final String RECORD_NOT_FOUND = "framework.util.staleobjectexception.0002";
}
