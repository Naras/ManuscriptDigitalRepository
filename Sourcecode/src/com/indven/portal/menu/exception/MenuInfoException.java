/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuInfoException.java
 * Project Name							:	WASP
 * Date of Creation						: 	19th AUG 2013
 * Brief Description					:	 											 
 * 											
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.menu.exception;

import com.indven.framework.exception.IndvenException;

/**
 * @author Saurabh
 */
public class MenuInfoException extends IndvenException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuInfoException(String message, String[] args, Throwable cause) {
		super(message, args, cause);
	}

	public MenuInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public MenuInfoException(String message) {
		super(message);
	}
	
	public static final String UNABLE_TO_SAVE_MENUINFO				   = "administration.menu.menuinfo.0001";
	public static final String UNABLE_TO_UPDATE_MENUINFO			   = "administration.menu.menuinfo.0002";
	public static final String UNABLE_TO_FIND_MENUINFO_DETAILS		   = "administration.menu.menuinfo.0003";	
	public static final String UNABLE_TO_DELETE_MENUINFO_DETAILS	   = "administration.menu.menuinfo.0004";	
	public static final String UNABLE_TO_MARK_MENUINFO_DELETED		   = "administration.menu.menuinfo.0005";
	public static final String UNABLE_TO_LIST_ALL_MENUINFOS			   = "administration.menu.menuinfo.0006";
	public static final String UNABLE_TO_RETRIEVE_DATA_FOR_MENUINFOCOMBO = "administration.menu.menuinfo.0007";
	
	public static final String UNABLE_TO_RETRIEVE_ROLEINFO_FOR_USER    = "administration.menu.roleforuser.0001";
	
}
