/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	LocationException.java
 * Project Name							:	EPM
 * Date of Creation						: 	8th February 2012
 * Brief Description					:	This class will be used to provide the relative message for exception for workflow                                          DB operations
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.exception;

import com.indven.framework.exception.IndvenException;


/**
 * @author Saurabh, Ramesh
 * 
 */

public class LocationException extends IndvenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5993577821740990063L;

	public LocationException(String message, String[] args, Throwable cause) {
		super(message, args, cause);
	}

	public LocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public LocationException(String message) {
		super(message);
	}

	public static final String UNABLE_TO_LIST_ALL_LOCATION_LEVEL_MASTER = "workflow.location.locationmaster.0007";

	public static final String UNABLE_TO_SAVE_LOCATION = "workflow.location.locationmaster.0001";
	public static final String UNABLE_TO_UPDATE_LOCATION = "workflow.location.locationmaster.0002";
	public static final String UNABLE_TO_FIND_LOCATION_DETAILS = "workflow.location.locationmaster.0003";
	public static final String UNABLE_TO_DELETE_LOCATION_DETAILS = "workflow.location.locationmaster.0004";
	public static final String UNABLE_TO_MARK_LOCATION_DELETED = "workflow.location.locationmaster.0005";
	public static final String UNABLE_TO_LIST_ALL_LOCATION = "workflow.location.locationmaster.0006";
	public static final String UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO = "workflow.location.locationmaster.0007";
	public static final String UNABLE_TO_SAVE_WORKFLOW_DATA_FOR_LOCATION = "workflow.location.locationmaster.0008";
	public static final String UNABLE_TO_RETRIEVE_ROLES_FOR_LOCATION = "workflow.location.locationmaster.0009";
	public static final String UNABLE_TO_RETRIEVE_LIST_OF_USERS = "workflow.location.locationmaster.0010";
	public static final String UNABLE_TO_UPDATE_USER_ROLE_MAPPING = "workflow.location.locationmaster.0011";
	public static final String UNABLE_TO_RETRIEVE_WORKFLOW_FOR_LOCATION = "workflow.location.locationmaster.0012";

}
