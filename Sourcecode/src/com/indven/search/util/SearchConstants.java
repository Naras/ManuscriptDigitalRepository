/**
 * 
 */
package com.indven.search.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Deba Prasad
 *
 */
public class SearchConstants {

	public static final Map<String, String> VALUES_BY_NAME = new HashMap<>();
	public static final Map<String, String> CREATE_ACTION_URL = new HashMap<>();
	public static final Map<String, String> ENTITY_FOR_VO = new HashMap<>();
	
	static {
		
		CREATE_ACTION_URL.put("EmployeeMaster", "/addUserPageWithRoles.action");
		CREATE_ACTION_URL.put("DigitalManuscript", "/addManuscript.action");
		CREATE_ACTION_URL.put("LanguageMaster", "/addlanguage.action");
		CREATE_ACTION_URL.put("CategoryMaster", "/addcategory.action");
		CREATE_ACTION_URL.put("ScriptMaster", "/addscript.action");
		CREATE_ACTION_URL.put("BundleMaster", "/addbundle.action");
		
		VALUES_BY_NAME.put("EmployeeMaster", "com.indven.portal.hrd.vo.EmployeeMasterVO");
	    VALUES_BY_NAME.put("DigitalManuscript", "com.indven.omds.vo.DigitalManuscriptVO");
	    VALUES_BY_NAME.put("LanguageMaster", "com.indven.omds.vo.LanguageVO");
	    VALUES_BY_NAME.put("CategoryMaster", "com.indven.omds.vo.CategoryVO");
	    VALUES_BY_NAME.put("ScriptMaster", "com.indven.omds.vo.ScriptVO");
	    VALUES_BY_NAME.put("BundleMaster", "com.indven.omds.vo.BundleMasterVO");
	    
	   	    
	    ENTITY_FOR_VO.put("com.indven.portal.hrd.vo.EmployeeMasterVO", "EmployeeMasterBean");
	    ENTITY_FOR_VO.put("com.indven.omds.vo.DigitalManuscriptVO", "DigitalManuscriptBean");
	    ENTITY_FOR_VO.put("com.indven.omds.vo.LanguageVO", "LanguageBean");
	    ENTITY_FOR_VO.put("com.indven.omds.vo.CategoryVO", "CategoryBean");
	    ENTITY_FOR_VO.put("com.indven.omds.vo.ScriptVO", "ScriptBean");
	    ENTITY_FOR_VO.put("com.indven.omds.vo.BundleMasterVO", "BundleMasterBean");
	    
	}
}
