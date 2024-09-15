package com.indven.workflow.util;

public enum WorkflowActionTypeEnum {
	ACTION_TYPE_FORWARD((short) 0), ACTION_TYPE_SAVE((short) 1), ACTION_TYPE_RETURN((short) 2), ACTION_TYPE_TERMINATE((short) 3);

	private WorkflowActionTypeEnum(Short value) {
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

	public static WorkflowActionTypeEnum getValueFromName(final String name) {
		for (WorkflowActionTypeEnum c : WorkflowActionTypeEnum.values()) {
			if (c.name().equals(name)) {
				return c;
			}// if
		}// for
		throw new IllegalArgumentException(name);
	}// fromValue
}
