/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EPMCombosConstants.java
 * Project Name							:	EPM
 * Date of Creation						: 	07th February 2012
 * Brief Description					:	This is the constants file that will hold the various constants related to the combo box handling
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.combohandler;

/**
 * This is the constants file that will hold the various constants related to the combo box handling
 * NOTE : Any changes in values in this file HAS to be changed in the flex code as well at
 * com.indven.framework.combohandler.EPMCombosConstants
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public interface EPMCombosConstants {

	public static final EPMComboEntityVO objSel = new EPMComboEntityVO("-- Select -- ", "-1");

	public static final long PRJ_COMBO_BUSINESS_UNIT = 1;
	public static final long PRJ_COMBO_PROGRAM_MASTER = 2;
	public static final long PRJ_COMBO_PROTOFOLIO_MASTER = 4;
	public static final long PRJ_COMBO_TASK_MASTER = 8;
	public static final long PRJ_COMBO_CUSTOMER_MASTER = 16;
	public static final long PRJ_COMBO_CONTRACTOR_MASTER = 32;
	public static final long PRJ_COMBO_DEALER_MASTER = 64;
	public static final long PRJ_COMBO_PROJECT_MASTER = 65536;
	public static final long PRJ_COMBO_PENDING_HUMAN_RESOURCES = 131072;
	public static final long PRJ_COMBO_CONTRACT_MASTER = 524288;
	
	public static final long STP_COMBO_TIMEZONE_MASTER = 128;
	public static final long STP_COMBO_CALENDAR_MASTER = 256;
	public static final long STP_COMBO_QUALIFICATION_MASTER = 512;

	public static final long HRD_COMBO_SHIFT_MASTER = 1024;
	public static final long HRD_COMBO_EMPLOYEE_MASTER = 4096;

	public static final long ADM_COMBO_USER_MASTER = 2048;

	public static final long WFL_COMBO_LOCATION_MASTER = 8192;
	public static final long WFL_COMBO_ROLE_MASTER = 16384;
	public static final long WFL_LOCATION_LEVEL_MASTER = 32768;

	public static final long MAT_COMBO_LEVEL_MASTER = 262144;

	public static final long COMMON_CODES_COMBO_META_DATA = 1;
	public static final long COMMON_CODES_COMBO_PRIORITY = 2;
	public static final long COMMON_CODES_COMBO_SALUTATIONS = 4;
	public static final long COMMON_CODES_COMBO_CURRENCY_TYPE = 8;
	public static final long COMMON_CODES_COMBO_PROJECT_TYPE = 16;
	public static final long COMMON_CODES_COMBO_UNIT_OF_DURATION = 32;
	public static final long COMMON_CODES_COMBO_COMMUNICATION_TYPE = 64;
	public static final long COMMON_CODES_COMBO_QUALIFICATION_LEVEL = 128;
	public static final long COMMON_CODES_COMBO_DESIGNATIONS = 256;
	public static final long COMMON_CODES_COMBO_DEPARTMENTS = 512;
	public static final long COMMON_CODES_COMBO_EQUIPMENT_TYPE = 1024;
	public static final long COMMON_CODES_COMBO_ACCEPTENCE_CRITERIA_STATUS = 2048;
	public static final long COMMON_CODES_COMBO_UNIT_OF_MEASUREMENT = 4096;
	public static final long COMMON_CODES_COMBO_SKILL_SET = 8192;
	public static final long COMMON_CODES_COMBO_ITEM_ATTRIBUTE_TYPE = 16384;
	public static final long COMMON_CODES_COMBO_CHECKLIST_TYPE = 32768;
	public static final long COMMON_CODES_COMBO_CONTRACT_TYPE  = 65536;

	public static final long HIERARCHY_COMBO_LOCATION_MASTER = 1;
	public static final long HIERARCHY_COMBO_MATERIAL_MASTER = 2;
	public static final long HIERARCHY_COMBO_GLOBAL_LOCATION_MASTER = 4;

}
