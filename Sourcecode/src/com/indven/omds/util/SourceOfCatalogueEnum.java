package com.indven.omds.util;

public enum SourceOfCatalogueEnum {
	Unselected((short) -1), Descriptive((short) 0), Handllist((short) 1), Triennial((short) 2), Alphabetical((short) 3);

	private Short value;

	SourceOfCatalogueEnum(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public static SourceOfCatalogueEnum fromValue(Short value) {
		if (value != null) {
			for (SourceOfCatalogueEnum type : values()) {
				if (type.value.equals(value)) {
					return type;
				}
			}
		}
		throw new IllegalArgumentException("Invalid Type: " + value);
	}
}
