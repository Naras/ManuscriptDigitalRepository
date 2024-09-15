/**
 *
 */
package com.indven.portal.administration.exception;

import com.indven.framework.exception.IndvenException;


/**
 * @author Deba prasad
 *
 */
public class AdministrationException extends IndvenException{

	/**
	 *
	 */
	private static final long serialVersionUID = -5939803705028324890L;

	public AdministrationException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}

	public AdministrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdministrationException(String message) {
		super(message);
	}

	public static final String UNABLE_TO_SAVE_USER = "user.core.0001";
	public static final String UNABLE_TO_UPDATE_USER = "user.core.0002";
	public static final String UNABLE_TO_FIND_USER = "user.core.0003";
	public static final String UNABLE_TO_DELETE_USER = "user.core.0004";
	public static final String UNABLE_TO_LIST_ALL_USER = "user.core.0005";
	public static final String UNABLE_TO_SAVE_OR_UPDATE_USER = "user.core.0006";
	public static final String UNABLE_TO_LOGIN_USER = "user.core.0007";
	public static final String UNABLE_TO_LOGIN_INVALID_DETAILS = "user.core.0008";
	public static final String UNABLE_TO_LOGIN_USER_NOT_ACTIVE = "user.core.0009";
	
	public static final String UNABLE_TO_CHANGE_PASSWORD = "user.core.0010";
	public static final String WORNG_OLD_PASSWORD = "user.core.0011";
	public static final String SAVE_CHANGE_PASSWORD = "user.core.0012";
	public static final String UNABLE_TO_UPDATE_USER_STATUS = "user.core.0013";
	public static final String UNABLE_TO_UPDATE_USER_STATUS_FOR_GIVEN_ID = "user.core.0014";
	public static final String UPDATE_USER_STATUS_FOR_GIVEN_ID = "user.core.0015";
	public static final String UNABLE_TO_RESET_USER_PASSWORD = "user.core.0016";
	public static final String UNABLE_TO_RESET_INVALID_DETAILS = "user.core.0017";
	public static final String SAVE_RESET_PASSWORD = "user.core.0018";
	public static final String UNABLE_TO_RESET_PASSWORD_DETAILS = "user.core.0019";
	public static final String UNABLE_TO_LOGIN_PASSWORD_NOT_RESETED = "user.core.0020";
	
	public static final String UNABLE_TO_LIST_ALL_ROLE_MASTER ="admin.role.0001";
	public static final String UNABLE_TO_FIND_ROLE_MASTER_DETAILS = "admin.role.002";
	public static final String DELETE_SUCCESS_FOR_ROLE_MASTER = "admin.role.003";
	public static final String UNABLE_TO_MARK_ROLE_MASTER_DELETED = "admin.role.004";
	public static final String VOILATE_UNIQUE_ROLE_NAME_CONSTRAINT = "admin.role.0005";
	
	public static final String UNABLE_TO_FETCH_USER_ROLE_DETAILS = "admin.role.0006";
	
	public static final String SAVE_SUCCESS_ROLE_MASTER = "admin.role.success.0001";
	
	public static final String USER_DOES_NOT_EXIST = "user.core.0021";
	
	
}
