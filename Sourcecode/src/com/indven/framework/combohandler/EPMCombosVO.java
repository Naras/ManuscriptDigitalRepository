/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EPMComboEntityVO.java
 * Project Name							:	EPM
 * Date of Creation						: 	07th February 2012
 * Brief Description					:	This is the VO which will be used for Sending combo data to and from flex
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.framework.combohandler;

import java.util.HashMap;
import java.util.List;

import com.indven.framework.vo.IndvenResultVO;

/**
 * This is the VO which will be used for Sending combo data to and from flex It will contain
 * hashmaps that will hold combobox data as a list of EPMComboEntityVO as the value part and the
 * respective bit mask from EPMCombosConstants as the key part
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public class EPMCombosVO extends IndvenResultVO {

	/**
	 * This Hashmap is used to store all the non common codes related lists
	 */
	private HashMap<Long, List<EPMComboEntityVO>> listOfComboData = new HashMap<Long, List<EPMComboEntityVO>>();

	/**
	 * This Hashmap is used to store all the lists related to common codes.
	 */
	private HashMap<Long, List<EPMComboEntityVO>> listOfcommonCodesComboData = new HashMap<Long, List<EPMComboEntityVO>>();

	/**
	 * This Hashmap is used to store all the lists related to common codes.
	 */
	private HashMap<Long, List<EPMComboEntityVO>> listOfHierarchyComboData = new HashMap<Long, List<EPMComboEntityVO>>();

	/**
	 * @return the listOfComboData
	 */
	public HashMap<Long, List<EPMComboEntityVO>> getListOfComboData() {
		return listOfComboData;
	}

	/**
	 * @param listOfComboData
	 *            the listOfComboData to set
	 */
	public void setListOfComboData(HashMap<Long, List<EPMComboEntityVO>> listOfComboData) {
		this.listOfComboData = listOfComboData;
	}

	/**
	 * @return the listOfcommonCodesComboData
	 */
	public HashMap<Long, List<EPMComboEntityVO>> getListOfcommonCodesComboData() {
		return listOfcommonCodesComboData;
	}

	/**
	 * @param listOfcommonCodesComboData
	 *            the listOfcommonCodesComboData to set
	 */
	public void setListOfcommonCodesComboData(HashMap<Long, List<EPMComboEntityVO>> listOfcommonCodesComboData) {
		this.listOfcommonCodesComboData = listOfcommonCodesComboData;
	}

	/**
	 * @return the listOfHierarchyComboData
	 */
	public HashMap<Long, List<EPMComboEntityVO>> getListOfHierarchyComboData() {
		return listOfHierarchyComboData;
	}

	/**
	 * @param listOfHierarchyComboData
	 *            the listOfHierarchyComboData to set
	 */
	public void setListOfHierarchyComboData(HashMap<Long, List<EPMComboEntityVO>> listOfHierarchyComboData) {
		this.listOfHierarchyComboData = listOfHierarchyComboData;
	}

}
