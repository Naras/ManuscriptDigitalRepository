/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	WorkflowQueriesConstant.java
 * Project Name							:	EPM
 * Date of Creation						: 	20th March 2012
 * Brief Description					:	This is the constants file that will hold the various queries related to the workflow module
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.util;

/**
 * This is the constants file that will hold the various queries related to the workflow module
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public interface WorkflowQueriesConstant {

	public static final String REQUIRED_UNASSIGNED_ROLES_FOR_LOCATION = "select distinct rm.Id as Id, rm.Id as value, rm.Name as Label from wfl.LocationProcessDetails lpd "
			+ " INNER JOIN wfl.RoleMaster rm ON lpd.RoleMasterFkId = rm.id "
			+ " INNER JOIN wfl.LocationProcessMaster lpm on lpd.LocationProcessMasterFkId = lpm.Id "
			+ " LEFT JOIN wfl.LocationUserRoleDetails lur on lur.RoleMasterFkId = lpd.RoleMasterFkId "
			+ " AND lur.LocationMasterFkId = lpm.LocationMasterFkId " + " WHERE lur.Id is null AND lpm.LocationMasterFkId = ";

	public static final String ASSIGNED_WORKFLOWS_FOR_LOCATION = "select ProcessMasterFkId as Id, ProcessMasterFkId as value, Name as Label from wfl.LocationProcessMaster "
			+ " WHERE LocationMasterFkId = ";

	public static final String COUNT_OF_WORKFLOWS_FOR_USER = "SELECT ID as Id, count(Id) as value, Name as Label FROM wfl.LocationMaster WHERE parentFkId = ";;

	public static final String LIST_OF_USERS_FOR_ROLES_ASSIGN = "select Id as ID, Id as Value, LoginName as Label from adm.UserInfo WHERE IsDeleted = 0";

	public static final String GET_LANGUAGE_FOR_MANUSCRIPT_FROM_PROCESS_MASTER_ID ="SELECT l.name FROM omds_language l , omds_digital_manuscript dm , " +
															"wfl_currentprocessmaster wf " +
															"WHERE wf.ReferenceFkId = dm.Id AND dm.languagefkid=l.Id  AND wf.Id= :wflProcessMasterId";

	public static final String GET_SCRIPT_FOR_MANUSCRIPT_FROM_PROCESS_MASTER_ID = "SELECT l.name FROM omds_script l , omds_digital_manuscript dm , " +
			"wfl_currentprocessmaster wf WHERE wf.ReferenceFkId = dm.Id AND dm.scriptFkId=l.Id  AND wf.Id= :wflProcessMasterId";
	
	public static final String GET_COUNT_OF_GIVEN_MANUSCRIPT_IN_PROCESS_OF_WFL = "select count(dtlsBean) from CurrentProcessDetailsBean dtlsBean where " +
			"dtlsBean.status < 2 and dtlsBean.CurrentProcessMasterFkId = (select processMaster.id from " +
			"CurrentProcessMasterBean processMaster where processMaster.ReferenceFkId = :referenceId)";
}
