/**
 * 
 */
package com.indven.omds.exception;

import com.indven.framework.exception.FrameworkException;

/**
 * @author Deba Prasad
 *
 */
public class OMDPCoreException extends FrameworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OMDPCoreException(String message, Object[] args, Throwable cause) {
		super(message, args, cause);
	}
	
	public OMDPCoreException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OMDPCoreException(String message) {
		super(message);
	}

	public static final String UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT = "omds.core.0001";
	public static final String UNABLE_TO_FIND_ALL_LANGUAGES = "omds.core.0002";
	public static final String UNABLE_TO_FIND_ALL_MATERIALS = "omds.core.0003";
	public static final String UNABLE_TO_FIND_ALL_ORGANISATIONS = "omds.core.0004";
	public static final String UNABLE_TO_FIND_MANUSCRIPT_DETAILS = "omds.core.0005";
	public static final String UNABLE_TO_DELETE_MANUSCRIPT_DETAILS = "omds.core.0006";
	public static final String UNABLE_TO_FIND_ALL_SCRIPTS = "omds.core.0007";
	public static final String UNABLE_TO_FIND_ALL_CATEGORIES = "omds.core.0008";
	public static final String UNABLE_TO_FIND_ALL_AUTHORS = "omds.core.0009";
	public static final String UNABLE_TO_FIND_ALL_PUBLISHERS = "omds.core.0010";
	public static final String UNABLE_TO_FIND_ALL_SPECIFIC_CATEGORIES = "omds.core.0011";
	public static final String DUPLICATE_ACRONYM = "omds.core.0012";
	public static final String UNABLE_TO_SAVE_DIGITAL_DOCUMENT = "omds.core.0013";
	public static final String UNABLE_TO_FIND_DOCUMENT = "omds.core.0014";
	public static final String UNABLE_TO_FIND_ALL_TAGS = "omds.core.0015";
	public static final String UNABLE_TO_DELETE_FRAMES = "omds.core.0016";
	public static final String UNABLE_TO_FIND_ALL_DIGITISERS = "omds.core.0017";
	public static final String UNABLE_TO_PROCESS_FOR_TRANSLATION = "omds.core.0018";
	public static final String UNABLE_TO_DELETE_WORKFLOW_MANUSCRIPT_DETAILS= "omds.core.0019";
	public static final String UNABLE_TO_GET_MERGED_DATE = "omds.core.0020";
	public static final String UNABLE_TO_GET_MERGED_DATE_FOR_TOW_DIFFERENT_DOCUMANETTYPE = "omds.core.0021";
	public static final String UNABLE_TO_FIND_MANUSCRIPT_STATUS = "omds.core.0022";
	public static final String UNABLE_TO_FIND_WORKFLOW_MANUSCRIPT_DETAILS= "omds.core.0023";
	public static final String AT_LEAST_SELECT_TWO_MANUSCRIPT_TO_MERGE= "omds.core.0024";
	public static final String SELECT_TAG_TO_REPLACE= "omds.core.0025";
	public static final String PLEASE_ENTER_A_TAG= "omds.core.0026";
	
	public static final String SUCCESSFULLY_SAVED_MANUSCRIPT = "omds.core.success.0001";
	public static final String SUCCESSFULLY_DELETED_MANUSCRIPT = "omds.core.success.0002";
	public static final String SUCCESSFULLY_SAVED_DOCUMENT = "omds.core.success.0003";
	public static final String SUCCESSFULLY_SAVED_BOOK = "omds.core.success.0004"; 
	
	public static final String UNABLE_TO_SAVE_THE_RECORD = "omds.generic.master.0001";
	public static final String UNABLE_TO_UPDATE_THE_RECORD = "omds.generic.master.0002";
	public static final String UNABLE_TO_FIND_THE_RECORD = "omds.generic.master.0003";
	public static final String UNABLE_TO_DELETE_THE_RECORD = "omds.generic.master.0004";
	
	public static final String UNABLE_TO_PROCESS_THE_RECORD = "omds.generic.image.0001";
	
	public static final String UNABLE_TO_SAVE_COMMENT = "omds.document.comments.0001";
	
	
}
