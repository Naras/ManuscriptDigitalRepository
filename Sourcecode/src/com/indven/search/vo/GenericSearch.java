/**
 * 
 */
package com.indven.search.vo;

import java.util.Map;

/**
 * @author Deba Prasad
 *
 */
public interface GenericSearch {


	/**
	 * This method will be overridden for the fully qualified VO Name.
	 */
	public String getTableName();
	
	/**
	 * This method will be overridden for the action names written for update and delete button click.
	 * The key will be an application constant and and the value will be the respective action name.
	 */
	public Map<String , String> getActionNames();
	
	/**
	 * This is the method which is mainly contains the attributes for which the search form(i.e the text boxes with labels to search) will be formed and
	 * which values will be displayed in the search grid.
	 * 
	 * The key attribute for a "key-value" pain in the map object will hold the label of the text field to be created for search form.
	 * and the value will hold the variable name(from vo) which will represent the key.
	 * 
	 * NOTE:-
	 * The variable name(s) to be used in search purpose should be exactly same in the bean and vo class.
	 */
	public Map<String , String> getLabelDisplayMap() ;
	
	/**
	 * This method will be overridden if there is a foreign key(FKId object) value to be used for searching purpose.
	 * 
	 * In the "key-value" pair the key will contain the variable name in vo(present in value attribute in getLabelDisplayMap() method) to be searched and the value attribute will contain
	 *  the bean name to which the foreign key belongs.
	 */
	public Map<String, String> beanListForFkId();
	
	/**
	 * This will contain the variable name in the vo as key value and respective Long type var name associated with @Column annotation in the 
	 * 	child bean(Here StationDataPointBean) as value attribute.
	 * @return
	 */
	public Map<String, String> columnName();
	
	/**
	 * This will contain the variable name in the vo as key attribute and respective String type var name associated with @Column annotation in the 
	 * 	parent bean(DepartmentMasterBean or StationMasterBean or CommonCodesBean) as value attribute.
	 * @return
	 */
	public Map<String, String> beanVarName();
	
}
