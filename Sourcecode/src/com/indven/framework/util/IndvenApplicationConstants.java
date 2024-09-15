/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	EHRMSApplicationConstants.java
 * Project Name						:	EHRMS
 * Date of Creation					: 	16 April 2013
 * Brief Description				:	This interface will have all the Application Constants
 * Author							: 	Yusuf
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.framework.util;

import java.util.Locale;

/**
 * @author Yusuf
 * 
 */
public interface IndvenApplicationConstants {
	
	public static final String WEB_APPLICATION_VERSION = "1.4.4";

	public static final Locale LOCALE = Locale.US;
	
	public static final String LOGGEDIN_USER_SESSION_DATA = "loginData";
	
	public static final String SYSTEM_GENERATED_USER = "System";

	public static final long EPM_HIERARCHY_ROOT_ID = 1;
	
	public static final String FIND_BY_ID_ACTION_NAME = "updateActionName";
	public static final String DELETE_ACTION_NAME = "deleteActionName";
	
	public static final String lineseparator = "=====================";
	/**
	 * Administrator ID
	 * For certain functions which bypass the menu system
	 */
	public static final Long ADMIN_ID = 1L;
	
	
	public static final Long GUEST_ID = 10L;
	
	/**
	 * Digital Manuscript Type 
	 * @author Neel Borooah
	 */
	public static final Long DIGITALMANUSCRIPT_TYPE_BOOK = 1L;
	public static final Long DIGITALMANUSCRIPT_TYPE_MANUSCRIPT = 2L;
	
	/**
	 * Person type
	 * @author Neel Borooah
	 */
	public static final Short PERSON_TYPE_AUTHOR = (short) 1;
	public static final Short PERSON_TYPE_SCRIBE = (short) 2;
	public static final Short PERSON_TYPE_EDITOR = (short) 3;
	public static final Short PERSON_TYPE_COMMENTATOR = (short) 4;
	public static final Short PERSON_TYPE_SUBCOMMENTATOR = (short) 6;
	public static final Short PERSON_TYPE_TRANSLATOR = (short) 5;
	
	public static final String IMAGE_FOLDER_DIR = "/temp/";
	
	/**
	 * Boolean variable which removes certain portions of code for testing purposes
	 * @author Neel Borooah
	 */
	public static final Boolean isTesting = false; 
	
	public static final int RECORDS_PER_PAGE = 10;
	
	public static final String MANUSCRIPT_ID_LENGTH = "%05d";
	
	public static final Long SCHOLAR_ROLE_ID = 5L;
	public static final Long REVIEWER_ROLE_ID = 6L;
	public static final Long PUBLISHER_ROLE_ID = 7L;
	public static final Long VERIFIER_ROLE_ID = 8L;
	
	
	public static final Long TRANSLATOR_ROLE_ID = 12L;
	
	public static final int IMAGE_NAME_SIZE = 4;
	
}
