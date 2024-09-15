package com.indven.workflow.util;

public enum WorkflowCountTypeEnum {

	COUNT_TYPE_ROLE((short) 0), COUNT_TYPE_DIRECT((short) 1), COUNT_TYPE_ALL((short) 2);

	private WorkflowCountTypeEnum(Short value) {
		this.value = value;
	}

	private Short value;

	/**
	 * @return the value
	 */
	public final Short getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(Short value) {
		this.value = value;
	}

	public static WorkflowCountTypeEnum getValueFromName(final String name) {
		for (WorkflowCountTypeEnum c : WorkflowCountTypeEnum.values()) {
			if (c.name().equals(name)) {
				return c;
			}// if
		}// for
		throw new IllegalArgumentException(name);
	}// fromValue

}
