package com.indven.framework.combohandler;

public interface EPMComboQueriesConstants {

	public static final String COMMON_CODES_METADATA_ALL = "SELECT ID as Id, Id as value,Description as Label FROM stp.CommonCodesMetadata  ";
	public static final String COMMON_CODES_METADATA_ACTIVE = "SELECT ID as Id, Id as value,Description as Label FROM stp.CommonCodesMetadata WHERE IsVisible = 1";

	public static final String COMMON_CODES_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM stp.CommonCodes WHERE CommonCodesMetadataFkId = ";
	public static final String COMMON_CODES_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM stp.CommonCodes WHERE IsDeleted = 0 AND IsDeletable = 1 AND CommonCodesMetadataFkId = ";

	public static final String PORTFOLIO_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.PortfolioMaster";
	public static final String PORTFOLIO_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.PortfolioMaster WHERE isDeleted = 0";

	public static final String SHIFT_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM hrd.ShiftMaster";
	public static final String SHIFT_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM hrd.ShiftMaster WHERE isDeleted = 0";

	public static final String EQUIPMENT_RESOURCE_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM eqp.EquipmentResourceMaster";
	public static final String EQUIPMENT_RESOURCE_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM eqp.EquipmentResourceMaster WHERE isDeleted = 0";

	public static final String BUSINESS_MASTER_ALL = "SELECT ID as Id, Id as value, Name as Label FROM prj.BusinessUnitMaster";
	public static final String BUSINESS_MASTER_ACTIVE = "SELECT ID as Id, Id as value, Name as Label FROM prj.BusinessUnitMaster WHERE IsDeleted = 0";

	public static final String PROGRAME_MASTER_ALL = "SELECT ID as Id, Id as value, Name as Label FROM prj.ProgrameMaster";
	public static final String PROGRAME_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.ProgrameMaster WHERE IsDeleted = 0";

	// Active Combo query is not there as timeZoneMaster does not have isDeleted field
	// Active Combo query is not there as timeZoneMaster does not have isDeleted
	// field
	public static final String TIME_ZONE_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM stp.TimeZoneMaster";

	public static final String CALENDAR_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM stp.CalendarMaster";
	public static final String CALENDAR_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM stp.CalendarMaster WHERE isDeleted = 0";

	public static final String QUALIFICATION_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM stp.QualificationMaster";
	public static final String QUALIFICATION_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM stp.QualificationMaster WHERE isDeleted = 0";

	public static final String TASK_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.TaskMaster";
	public static final String TASK_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.TaskMaster WHERE isDeleted = 0";

	public static final String CONTRACTOR_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.ContractorMaster";
	public static final String CONTRACTOR_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.ContractorMaster WHERE isDeleted = 0";

	public static final String CONTRACT_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.ContractMaster";
	public static final String CONTRACT_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.ContractMaster WHERE isDeleted = 0 AND ContractorFKId = ";

	public static final String CUSTOMER_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.CustomerMaster";
	public static final String CUSTOMER_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.CustomerMaster WHERE isDeleted = 0";

	public static final String DEALER_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM mat.DealerMaster";
	public static final String DEALER_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM mat.DealerMaster WHERE isDeleted = 0";

	public static final String PROJECT_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.ProjectMaster";
	public static final String PROJECT_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.ProjectTemplateGrandMaster WHERE isDeleted = 0";

	public static final String MATERIAL_HIERARCHY_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM mat.MaterialHierarchyMaster";
	public static final String MATERIAL_HIERARCHY_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM mat.MaterialHierarchyMaster WHERE isDeleted = 0";

	public static final String LEVEL_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM mat.LevelMaster order by LevelNumber";
	public static final String LEVEL_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM mat.LevelMaster WHERE isDeleted = 0 order by LevelNumber";

	public static final String CHECKLIST_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM stp.ChecklistMaster";
	public static final String CHECKLIST_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM stp.ChecklistMaster WHERE isDeleted = 0";

	public static final String EMPLOYEE_MASTER_ALL = "SELECT ID as Id, Id as value,firstName+' '+lastName as Label FROM hrd.EmployeeMaster";
	public static final String EMPLOYEE_MASTER_ACTIVE = "SELECT ID as Id, Id as value,firstName+' '+lastName as Label FROM hrd.EmployeeMaster WHERE isDeleted = 0";

	public static final String LOCATION_LEVEL_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM prj.PortfolioMaster";
	public static final String LOCATION_LEVEL_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM prj.PortfolioMaster WHERE isDeleted = 0";

	public static final String LOCATION_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM wfl.LocationMaster";
	public static final String LOCATION_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM wfl.LocationMaster WHERE isDeleted = 0";

	public static final String ITEM_ATTRIBUTE_TEMPLATE_ALL = "SELECT ID as Id, Id as value,Name as Label FROM mat.ItemAttributeTemplateDetails";
	public static final String ITEM_ATTRIBUTE_TEMPLATE_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM mat.ItemAttributeTemplateDetails WHERE isDeleted = 0";

	public static final String MATERIAL_HIERARCHY_MASTER_CHILD_ITEM_ALL = "SELECT ID as Id, Id as value,Name as Label FROM mat.MaterialHierarchyMaster where LevelFkId=";
	public static final String MATERIAL_HIERARCHY_MASTER_CHILD_ITEM_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM mat.MaterialHierarchyMaster WHERE isDeleted = 0 and ParentFkId!=0 and levelFkId=(select Id as levelFkId from mat.LevelMaster where mat.LevelMaster.LevelNumber = (select (mat.LevelMaster.LevelNumber -1) from mat.LevelMaster where mat.LevelMaster.Id=";

	//Omitting the selection of root and administrator
	public static final String ROLE_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM wfl.RoleMaster WHERE ID != 1 AND ID != 2";
	public static final String ROLE_MASTER_ACTIVE = "SELECT ID as Id, Id as value, Name as Label FROM wfl.RoleMaster WHERE IsDeleted = 0 AND ID != 1 AND ID != 2";

	public static final String HIERARCHY_LOCATION_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM wfl.LocationMaster WHERE parentFkId = ";
	public static final String HIERARCHY_LOCATION_MASTER_ACTIVE = "SELECT ID as Id, Id as value,Name as Label FROM wfl.LocationMaster WHERE isDeleted = 0 AND parentFkId = ";

	public static final String PENDING_REQUIRED_HUMAN_RESOURCES_ACTIVE = "SELECT p.ID as Id, p.Id as value, p.Name as Label FROM prj.Projectmaster p WHERE P.isDeleted = 0 and p.Id in (SELECT a.projectFkId FROM prj.RequiredHumanResources a WHERE a.NumberRequired > 0)";
	public static final String PENDING_REQUIRED_HUMAN_RESOURCES_ALL = "SELECT p.ID as Id, p.Id as value, p.Name as Label FROM prj.Projectmaster p WHERE p.Id in (SELECT a.projectFkId FROM prj.RequiredHumanResources a WHERE a.NumberRequired > 0)";

	public static final String HIERARCHY_GLOBAL_LOCATION_MASTER_ALL = "SELECT ID as Id, Id as value,Name as Label FROM stp.GlobalLocationsMaster WHERE parentFkId = ";

	public static final String GET_USER_WORKING_HOURS = "SELECT * from adm.UserWorkingHours WHERE userInfoFkId = ";

	public static final String GET_USER_LOCATIONS = "SELECT DISTINCT lm.ID as Id, lm.Id as value, lm.Name as Label FROM wfl.LocationUserRoleDetails lurd INNER JOIN  wfl.LocationMaster lm ON lm.id=lurd.LocationMasterFkId WHERE lurd.UserInfoFkId= ";

	public static final String GET_ROLES_FOR_NOT_ASSIGNED_MENU = "SELECT DISTINCT rm.ID as Id, rm.Id as value, rm.Name as Label from wfl.rolemaster as rm, adm.accesscontrol ac where ac.rolefkid = rm.id  and rm.id != 2";

	public static final String GETROLESFORMENU_ADD = " SELECT DISTINCT rm.ID as Id, rm.Id as value, rm.Name as Label FROM wfl.RoleMaster rm  where rm.id not in (SELECT DISTINCT RoleFkId FROM adm.AccessControl ) and rm.ID != 1 ";
	

}
