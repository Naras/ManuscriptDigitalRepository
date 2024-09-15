/**
 * 
 */
package com.indven.search.exception;

import com.indven.framework.exception.FrameworkException;

/**
 * @author Deba Prasad
 *
 */
public class SearchException  extends FrameworkException {

	public SearchException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}
	
	public SearchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SearchException(String message) {
		super(message);
	}
	
	public static final String UNABLE_TO_SEARCH = "portal.search.0001";
}
