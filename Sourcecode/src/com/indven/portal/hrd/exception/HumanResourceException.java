/**
 * 
 */
package com.indven.portal.hrd.exception;

import com.indven.framework.exception.IndvenException;

/**
 * @author Deba Prasad
 *
 */
public class HumanResourceException  extends IndvenException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HumanResourceException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

	public HumanResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public HumanResourceException(String message) {
		super(message);
	}
	
	public static final String UNABLE_TO_SAVE_EMPLOYEE = "gpil.hrd.core.0001";
	public static final String UNABLE_TO_SEARCH_EMPLOYEE = "gpil.hrd.core.0002";
	public static final String UNABLE_TO_LIST_EMPLOYEES = "gpil.hrd.core.0003";
	public static final String UNABLE_TO_DELETE_EMPLOYEES = "gpil.hrd.core.0004";
	public static final String VOILATE_UNIQUE_EMAIL_CONSTRAINT = "gpil.hrd.core.0005";
	public static final String UNABLE_TO_LIST_EMPLOYEES_FOR_GIVEN_CRITERIA = "gpil.hrd.core.0006";
	
	
	public static final String SAVE_SUCCESS_EMPLOYEE = "gpil.hrd.core.success.0001";
	public static final String DELETE_SUCCESS_EMPLOYEE = "gpil.hrd.core.success.0002";
}
