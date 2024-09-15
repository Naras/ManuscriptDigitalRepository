/**
 * 
 */
package com.indven.omds.util;

/**
 * @author Deba Prasad
 * 
 */
public enum ManuscriptWorkType {

	Unselected((short) -1), Poetry((short) 0), Prose((short) 1), Poem_And_Prose((short) 2), Compendium((short) 3);

	private Short value;

	ManuscriptWorkType(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public static ManuscriptWorkType fromValue(Short value) {
		if (value != null) {
			for (ManuscriptWorkType type : values()) {
				if (type.value.equals(value)) {
					return type;
				}
			}
		}
		throw new IllegalArgumentException("Invalid Type: " + value);
	}
}
