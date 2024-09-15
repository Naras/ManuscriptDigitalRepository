package com.indven.omds.vo;

import java.util.HashMap;
import java.util.Map;

import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.search.vo.GenericSearch;

public class ScriptVO extends IndvenResultVO implements GenericSearch  {
	
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnicodePoint() {
		return unicodePoint;
	}
	public void setUnicodePoint(String unicodePoint) {
		this.unicodePoint = unicodePoint;
	}

	@Override
	public String getTableName() {
		String className ="com.indven.omds.vo.ScriptVO";
		
		return className;
	}

	@Override
	public Map<String, String> getActionNames() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME , "findScriptById.action");
		returnMap.put(IndvenApplicationConstants.DELETE_ACTION_NAME , "deletescriptaction.action");
		
		return returnMap;
	}

	@Override
	public Map<String, String> getLabelDisplayMap() {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("Script Name" , "name");
		
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
