package com.indven.framework.vo;

import java.util.List;

public class FindAllResultVO<T> extends IndvenResultVO {

	List<T> listOfElemnents;

	/**
	 * @return the listOfElemnents
	 */
	public List<T> getListOfElemnents() {
		return listOfElemnents;
	}

	/**
	 * @param listOfElemnents
	 *            the listOfElemnents to set
	 */
	public void setListOfElemnents(List<T> listOfElemnents) {
		this.listOfElemnents = listOfElemnents;
	}

}
