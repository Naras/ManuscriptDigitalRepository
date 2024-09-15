/**
 * 
 */
package com.indven.omds.vo;

import java.util.HashMap;
import java.util.Map;

import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.search.vo.GenericSearch;


/**
 * @author Deba Prasad
 *
 */
public class LanguageVO extends IndvenResultVO implements GenericSearch {
	private Long id;
	private String name;
	private String unicodePoint;
	private Boolean isDeleted;
	
	/**
	 * @return the isDeleted
	 */
	public final Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public final void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the unicodePoint
	 */
	public final String getUnicodePoint() {
		return unicodePoint;
	}
	/**
	 * @param unicodePoint the unicodePoint to set
	 */
	public final void setUnicodePoint(String unicodePoint) {
		this.unicodePoint = unicodePoint;
	}
	
	@Override
	public String getTableName() {
		String className ="com.indven.omds.vo.LanguageVO";
		
		return className;
	}
	@Override
	public Map<String, String> getActionNames() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME , "findLanguageById.action");
		returnMap.put(IndvenApplicationConstants.DELETE_ACTION_NAME , "deletelanguageaction.action");
		
		return returnMap;
	}
	@Override
	public Map<String, String> getLabelDisplayMap() {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("Language Name" , "name");
		
		return returnMap;
	}
	@Override
	public Map<String, String> beanListForFkId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> columnName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> beanVarName() {
		// TODO Auto-generated method stub
		return null;
	}
}
