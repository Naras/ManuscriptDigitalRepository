package com.indven.framework.vo;

public class LabelValueVO {
	
	private String label;
	private String value;
	
	
	
	/**
	 * @param label
	 * @param value
	 */
	public LabelValueVO(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	/**
	 * @return the label
	 */
	public final String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public final void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}
	
	
	

}
