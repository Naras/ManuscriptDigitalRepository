package com.indven.omds.vo;

import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.search.vo.GenericSearch;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpecificCategoryVO extends IndvenResultVO implements GenericSearch {
	
	private Long id;
	private String name;
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

	@Override
	public String getTableName() {
		String className ="com.indven.omds.vo.SpecificCategoryVO";
		return className;
	}

	@Override
	public Map<String, String> getActionNames() {
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap.put(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME , "findSpecificCategoryById.action");
		returnMap.put(IndvenApplicationConstants.DELETE_ACTION_NAME , "deletespecificcategoryaction.action");

		return returnMap;
	}

	@Override
	public Map<String, String> getLabelDisplayMap() {
		Map<String, String> returnMap = new LinkedHashMap<String, String>();
		returnMap.put("Specific Category" , "name");

		return returnMap;
	}

	@Override
	public Map<String, String> beanListForFkId() {
		return null;
	}

	@Override
	public Map<String, String> columnName() {
		return null;
	}

	@Override
	public Map<String, String> beanVarName() {
		return null;
	}
}
