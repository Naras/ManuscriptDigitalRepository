package com.indven.search.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The object of this class is an important parameter for the dynamic search module.
 * This VO Object contains all the informations about the form creation , search parameters , specific class name , FKId relations etc.
 *   
 * @author Deba Prasad
 *
 */
public class SearchVO {

	private List<Object> labelList = new ArrayList<>();
	private List<Object> nameList = new ArrayList<>();
	//private List<String> actionList = new ArrayList<>();
	private List<Object> displayList = new ArrayList<>();
	private String tableName = "";
	private String updateAction = "";
	private String deleteAction = "";
	
	private String createPageURL = "";
	
	
	private List<String> fkObjName = new ArrayList<>();
	private Map<String, String> beanMapForFkId = new HashMap<String, String>();
	private Map<String, String> columnMapForFkIdSearch = new HashMap<String, String>();
	private Map<String, String> varNameInEntity = new HashMap<String, String>();
	
	/**
	 * @return the beanMapForFkId
	 */
	public final Map<String, String> getBeanMapForFkId() {
		return beanMapForFkId;
	}
	/**
	 * @param beanMapForFkId the beanMapForFkId to set
	 */
	public final void setBeanMapForFkId(Map<String, String> beanMapForFkId) {
		this.beanMapForFkId = beanMapForFkId;
	}
	/**
	 * @return the columnMapForFkIdSearch
	 */
	public final Map<String, String> getColumnMapForFkIdSearch() {
		return columnMapForFkIdSearch;
	}
	/**
	 * @param columnMapForFkIdSearch the columnMapForFkIdSearch to set
	 */
	public final void setColumnMapForFkIdSearch(
			Map<String, String> columnMapForFkIdSearch) {
		this.columnMapForFkIdSearch = columnMapForFkIdSearch;
	}
	/**
	 * @return the fkObjName
	 */
	public final List<String> getFkObjName() {
		return fkObjName;
	}
	/**
	 * @param fkObjName the fkObjName to set
	 */
	public final void setFkObjName(List<String> fkObjName) {
		this.fkObjName = fkObjName;
	}
	
	
	/**
	 * @return the createPAgeURL
	 */
	public final String getCreatePageURL() {
		return createPageURL;
	}
	/**
	 * @param createPAgeURL the createPAgeURL to set
	 */
	public final void setCreatePageURL(String createPageURL) {
		this.createPageURL = createPageURL;
	}
	/**
	 * @return the updateAction
	 */
	public final String getUpdateAction() {
		return updateAction;
	}
	/**
	 * @param updateAction the updateAction to set
	 */
	public final void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}
	/**
	 * @return the deleteAction
	 */
	public final String getDeleteAction() {
		return deleteAction;
	}
	/**
	 * @param deleteAction the deleteAction to set
	 */
	public final void setDeleteAction(String deleteAction) {
		this.deleteAction = deleteAction;
	}
	/**
	 * @return the displayList
	 */
	public final List<Object> getDisplayList() {
		return displayList;
	}
	/**
	 * @param displayList the displayList to set
	 */
	public final void setDisplayList(List<Object> displayList) {
		this.displayList = displayList;
	}
	/**
	 * @return the tableName
	 */
	public final String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public final void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the labelList
	 */
	public final List<Object> getLabelList() {
		return labelList;
	}
	/**
	 * @param labelList the labelList to set
	 */
	public final void setLabelList(List<Object> labelList) {
		this.labelList = labelList;
	}
	/**
	 * @return the nameList
	 */
	public final List<Object> getNameList() {
		return nameList;
	}
	/**
	 * @param nameList the nameList to set
	 */
	public final void setNameList(List<Object> nameList) {
		this.nameList = nameList;
	}
	/**
	 * @return the varNameInEntity
	 */
	public Map<String, String> getVarNameInEntity() {
		return varNameInEntity;
	}
	/**
	 * @param varNameInEntity the varNameInEntity to set
	 */
	public void setVarNameInEntity(Map<String, String> varNameInEntity) {
		this.varNameInEntity = varNameInEntity;
	}
}
