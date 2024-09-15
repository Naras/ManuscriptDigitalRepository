package com.indven.workflow.util;

public enum WorkflowProcessStatusEnum {
	PROCESS_STATUS_PENDING((short) 0), PROCESS_STATUS_CURRENT((short) 1), PROCESS_STATUS_COMPLETED((short) 2), PROCESS_STATUS_SKIPPED(
			(short) 3);

	private WorkflowProcessStatusEnum(Short value) {
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

}
