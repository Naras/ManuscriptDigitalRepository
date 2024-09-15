/**
 * 
 */
package com.indven.omds.util;


/**
 * @author Deba Prasad
 *
 */
public enum MaterialTypeEnum {

	Unselected((short) -1), Palm_leaf((short) 0), Paper((short) 1), Birchwood((short) 2);
	
	private Short value;

	MaterialTypeEnum(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
	
	public static MaterialTypeEnum fromValue(Short value) {  
		   if (value != null) {  
		     for (MaterialTypeEnum type : values()) {  
		       if (type.value.equals(value)) {  
		         return type;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid Type: " + value);  
	}
}
