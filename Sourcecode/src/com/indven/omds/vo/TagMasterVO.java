package com.indven.omds.vo;

import java.util.HashMap;
import java.util.Map;

import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.search.vo.GenericSearch;

public class TagMasterVO extends IndvenResultVO  {
	private Long id;
	private String name;
	private Boolean isDeleted;
	private Boolean replaceId;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/*@Override
	public String getTableName() {
		// TODO Auto-generated method stub
        String className ="com.indven.omds.vo.TagMasterVO";
		
		return className;
	}
	@Override
	public Map<String, String> getActionNames() {
		// TODO Auto-generated method stub
       Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME , "findTagById.action");
		returnMap.put(IndvenApplicationConstants.DELETE_ACTION_NAME , "deleteTagaction.action");
		
		return returnMap;
	}
	@Override
	public Map<String, String> getLabelDisplayMap() {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put("Tag Name" , "name");
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
	}*/
	/**
	 * @return the isDeleted
	 */
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the replaceId
	 */
	public Boolean getReplaceId() {
		return replaceId;
	}
	/**
	 * @param replaceId the replaceId to set
	 */
	public void setReplaceId(Boolean replaceId) {
		this.replaceId = replaceId;
	}

}
