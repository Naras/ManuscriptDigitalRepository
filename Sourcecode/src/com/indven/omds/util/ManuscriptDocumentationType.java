package com.indven.omds.util;

public enum ManuscriptDocumentationType {
	Unselected((short) -1),Complete((short) 0), Ongoing((short) 1);

	private Short value;

	ManuscriptDocumentationType(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public static ManuscriptDocumentationType fromValue(Short value) {
		if (value != null) {
			for (ManuscriptDocumentationType type : values()) {
				if (type.value.equals(value)) {
					return type;
				}
			}
		}
		throw new IllegalArgumentException("Invalid Type: " + value);
	}

}
