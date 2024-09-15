/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	UtilException.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.util.exception;

import com.indven.framework.exception.FrameworkException;


/**
 * This Class contains error constants for SetUp Core package
 * 
 * @author Saurabh
 * 
 */
public class UtilException extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(String message) {
		super(message);
	}
	
	public static final String UNABLE_TO_SAVE_COMMON_CODES_METADATA_DETAILS = "util.commoncodesmetadata.0001";	
	public static final String UNABLE_TO_UPDATE_COMMON_CODES_METADATA_DETAILS = "util.commoncodesmetadata.0002";	
	public static final String UNABLE_TO_FIND_COMMON_CODES_METADATA_DETAILS = "util.commoncodesmetadata.0003";
	public static final String UNABLE_TO_DELETE_COMMON_CODES_METADATA_DETAILS = "util.commoncodesmetadata.0004";
	public static final String UNABLE_TO_LIST_ALL_COMMON_CODES_METADATA_DETAILS = "util.commoncodesmetadata.0005";
	
	public static final String UNABLE_TO_SAVE_COMMON_CODES_DETAILS = "util.commoncode.0001";	
	public static final String UNABLE_TO_UPDATE_COMMON_CODES_DETAILS = "util.commoncode.0002";	
	public static final String UNABLE_TO_FIND_COMMON_CODES_DETAILS = "util.commoncode.0003";
	public static final String UNABLE_TO_DELETE_COMMON_CODES_DETAILS = "util.commoncode.0004";
	public static final String UNABLE_TO_LIST_ALL_COMMON_CODES_DETAILS = "util.commoncode.0005";
	public static final String UNABLE_TO_RETRIEVE_DATA_FOR_COMBO = "util.commoncode.0006";
}
