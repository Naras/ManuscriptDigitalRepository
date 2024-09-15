package com.indven.omds.util;

public enum DocumentStatusEnum {
	
	Unselected((short) -1), Under_Scholar((short) 0), Under_Reviewer((short) 1), Under_Publisher((short) 2) , Published((short) 3), Under_Verifier((short) 4), Under_Translator((short) 5);

	private Short value;

	DocumentStatusEnum(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
	
	public static DocumentStatusEnum fromValue(Short value) {  
		   if (value != null) {  
		     for (DocumentStatusEnum type : values()) {  
		       if (type.value.equals(value)) {  
		         return type;  
		       }  
		     }  
		   }  
		   throw new IllegalArgumentException("Invalid Type: " + value);  
	}
}
