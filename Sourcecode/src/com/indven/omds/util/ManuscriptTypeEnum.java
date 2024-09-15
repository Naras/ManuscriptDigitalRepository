/**
 * 
 */
package com.indven.omds.util;

/**
 * @author Deba Prasad
 *
 */
public enum ManuscriptTypeEnum {
	Unselected((short) -1), Original((short) 0), Transcription((short) 1), Translation((short) 2);

	private Short value;

	ManuscriptTypeEnum(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public static ManuscriptTypeEnum fromValue(Short value) {
		if (value != null) {
			for (ManuscriptTypeEnum type : values()) {
				if (type.value.equals(value)) {
					return type;
				}
			}
		}
		throw new IllegalArgumentException("Invalid Type: " + value);
	}
}
