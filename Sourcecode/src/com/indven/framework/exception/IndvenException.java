/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EHRMSException.java
 * Project Name							:	EPM
 * Date of Creation						: 	23rd December 2011
 * Brief Description					:	This is the ServiceException thrown at Service Layer
 * Author								: 	Yusuf Pasha and Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.exception;

/**
 * @author Deba prasad
 *
 */
public class IndvenException extends Exception {
	private String message;
	private Object args[];

	/**
	 * Java5 enforces serialVersionUID through warnings. This UID allows backward-compatibility in
	 * serialization.
	 */
	private static final long serialVersionUID = 1202936530491L;

	/**
	 * 
	 */
	public IndvenException(final String message) {
		super(message, null);
		this.message = message;
	}

	/**
	 * Creates EPMException with the given description and cause.
	 * 
	 * @param message
	 *            Description
	 * @param cause
	 *            Exception cause
	 */
	public IndvenException(final String message, final Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * Creates EPMException with the given description and cause.
	 * 
	 * @param message
	 *            Description
	 * @param cause
	 *            Exception cause
	 */
	public IndvenException(final String message, Object[] args, final Throwable cause) {
		super(message, cause);
		this.message = message;
		this.args = args;
	}
	
	/**
	 * Creates EPMException with the given description and args.
	 * @param message Description
	 * @param args list of args which intern contains the list of exceptions.
	 *  @author RAVICHANDRA 19JUNE2012           
	 */
	public IndvenException(final String message, Object[] args) {
		this.message = message;
		this.args = args;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}

}
