package com.indven.omds.util;

public enum DocumentWorkType {

	unselected((short) -1), document((short) 0), audio((short) 1), video((short) 2), file((short) 3);

	private Short value;

	DocumentWorkType(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
	
	public static DocumentWorkType fromValue(Short value) {  
		   if (value != null) {  
		     for (DocumentWorkType type : values()) {  
		       if (type.value.equals(value)) {  
		         return type;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid Type: " + value);  
	}
}
