package com.indven.framework.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class stores a map of all the active sessions in the application
 * Key is user id
 * Value is session id
 * 
 * @author Neel Borooah
 *
 */
public class ApplicationSessions {
	
	private static Map<Long, String> mapOfActiveSessions = new HashMap<Long, String>();

	public static Map<Long, String> getMapOfActiveSessions() {
		return mapOfActiveSessions;
	}
	
}

