/**
 * 
 */
package com.indven.search.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deba Prasad
 *
 */
public class FormCreator {

	public static List<Object> getNameList() {
		List<Object> nameList = new ArrayList<>();
		
		nameList.add("empVo.firstName");
		nameList.add("empVo.lastName");
		nameList.add("empVo.email");
		
		return nameList;
	}
	
	public static List<Object> getLabelList() {
		List<Object> labelList = new ArrayList<>();
		
		labelList.add("FirstName");
		labelList.add("LastName");
		labelList.add("Email");
		
		return labelList;
	}
	
	public static List<Object> getDisplayHeader() {
		List<Object> displayList = new ArrayList<>();
		
		displayList.add("FirstName");
		displayList.add("LastName");
		displayList.add("Email");
		displayList.add("Phone Number");
		
		return displayList;
	}
	
	public static List<Object> getDisplayValue() {
		List<Object> displayList = new ArrayList<>();
		
		displayList.add("id");
		displayList.add("firstName");
		displayList.add("lastName");
		displayList.add("email");
		
		return displayList;
	}
	
	public static List<String> getActionNames() {
		List<String> actionList = new ArrayList<>();
		
		actionList.add("displayuseraction.action");
		actionList.add("deleteuseraction.action");
		
		return actionList;
	}
	
	
}
