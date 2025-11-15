package com.indven.omds.util;

public enum ManuscriptConditionType {
	Unselected((short) -1), Good((short) 0), Bad((short) 1), Brittle((short) 2),Medium((short) 3),
	Termite_affected((short) 4),
	Damaged((short) 5),
    Spotted((short) 6),
	Holes_on_folios((short) 7),
	Broken_corners((short) 8),
	Stick_folios((short) 9),
	Rat_eaten((short) 10),
	Tried_to_unstuck_and_damaged((short) 11),
	Repaired((short) 12);

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

	public  static String valueOfOrDefault(String enumName) {
		String value=enumName.toUpperCase().replaceAll("\\s", "_");
		for(ManuscriptConditionType type : ManuscriptConditionType.class.getEnumConstants()) {
			if(type.name().equalsIgnoreCase(value)) {
				return type.toString();
			}
		}
		return enumName;
	}

}
