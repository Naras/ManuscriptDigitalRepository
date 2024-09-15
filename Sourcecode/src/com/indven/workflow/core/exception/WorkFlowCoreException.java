/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	WorkFlowCoreException.java
 * Project Name							:	EPM
 * Date of Creation						: 	8th February 2012
 * Brief Description					:	This class will be used to provide the relative message for exception for workflow                                          DB operations
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.core.exception;

import com.indven.framework.exception.IndvenException;

/**
 * @author Saurabh, Ramesh
 * 
 */

public class WorkFlowCoreException extends IndvenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5993577821740990063L;

	public WorkFlowCoreException(String message, String[] args, Throwable cause) {
		super(message, args, cause);
	}

	public WorkFlowCoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkFlowCoreException(String message) {
		super(message);
	}

	public static final String UNABLE_TO_LIST_ALL_PROCESS_MASTER = "workflow.core.processmaster.0001";

	public static final String UNABLE_TO_RETRIEVE_COUNTS_FOR_USER = "workflow.core.process.0001";
	public static final String UNABLE_TO_RETRIEVE_LISTOF_WFL_FOR_USER = "workflow.core.process.0002";
	public static final String UNABLE_TO_INITIATE_WORKFLOW = "workflow.core.process.0003";
	public static final String UNABLE_TO_PROCESS_WORKFLOW = "workflow.core.process.0004";
	public static final String UNABLE_TO_PROCESS_UNKNOWN_ACTION_TYPE = "workflow.core.process.0005";
	public static final String UNABLE_TO_RETRIEVE_USERS_FOR_NEXT_LEVEL = "workflow.core.process.0006";
	public static final String UNABLE_TO_RETRIEVE_PROCESS_MASTER_DETAILS = "workflow.core.process.0007";
	
	public static final String RECORD_ALREADY_IN_PROCESS = "workflow.core.in.process.0001";
	
	public static final String UNABLE_TO_RETRIEV_USERS_LIST_WITH_ROLE = "workflow.core.process.0008";

}
