package com.indven.omds.util;

public enum ManuscriptConditionType {
	Unselected((short) -1), Good((short) 0), Bad((short) 1), Brittle((short) 2);

	private Short value;

	ManuscriptConditionType(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public static ManuscriptConditionType fromValue(Short value) {
		if (value != null) {
			for (ManuscriptConditionType type : values()) {
				if (type.value.equals(value)) {
					return type;
				}
			}
		}
		throw new IllegalArgumentException("Invalid Type: " + value);
	}

}
