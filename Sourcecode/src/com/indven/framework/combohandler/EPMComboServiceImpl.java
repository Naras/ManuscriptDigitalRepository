/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	EPMComboServiceImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	07th February 2012
 * Brief Description					:	This is the service class related that will handle all the requests related to combo handling
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.combohandler;

import java.util.HashMap;

import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.workflow.location.dao.LocationMasterDAOImpl;
import com.indven.workflow.location.exception.LocationException;

/**
 * This is the service class related that will handle all the requests related to combo handling
 * 
 * @author Sandeep Solomon Kunder
 * 
 */
public class EPMComboServiceImpl implements EPMCombosConstants {

	/**
	 * This is the common method called from flex that is used to load the combo box data
	 * 
	 * @param reqComboVal
	 *            The long value with the bit values set for which the combo data needs to be
	 *            retrieved
	 * @param reqCommonCodesComboVal
	 *            The long value with the bit values set for which the common codes combo data needs
	 *            to be retrieved
	 * @param isOnlyActive
	 *            boolean value indicating if only the active needs to be retrieved or all the data
	 *            needs to be retrieved
	 * @return returns an object of {@link EPMCombosVO} which will have the required combo data set
	 *         with its own bit mask as the kew in the hash map
	 * @throws ProjectCoreException
	 * @throws ProjectCustomerException
	 * @throws SetUpCalenderException
	 * @throws UserInfoException
	 */
	public EPMCombosVO populateDataToComboBoxes(long reqComboVal, long reqCommonCodesComboVal, boolean isOnlyActive)
			 {

		EPMCombosVO epmCombosVO = new EPMCombosVO();

		try {

			if (reqComboVal > 0) {
				processCombo(reqComboVal, epmCombosVO, isOnlyActive);
			}

			if (reqCommonCodesComboVal > 0) {
				processCommonCodesCombo(reqCommonCodesComboVal, epmCombosVO, isOnlyActive);
			}

			epmCombosVO.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (Exception ccex) {
			//new IndvenExceptionMessageResolver().resolveMessage(epmCombosVO, ccex, IndvenApplicationConstants.LOCALE);
		}

		return epmCombosVO;

	}

	/**
	 * 
	 * @param reqComboVal
	 * @param epmCombosVO
	 * @param isOnlyActive
	 * @throws ProjectCoreException
	 * @throws ProjectCustomerException
	 * @throws SetUpCalenderException
	 * @throws SetUpCoreException
	 * @throws UserInfoException
	 */
	private void processCombo(long reqComboVal, EPMCombosVO epmCombosVO, boolean isOnlyActive)  {
		/*if ((reqComboVal & PRJ_COMBO_BUSINESS_UNIT) == PRJ_COMBO_BUSINESS_UNIT) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_BUSINESS_UNIT,
					new BusinessUnitMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_PROGRAM_MASTER) == PRJ_COMBO_PROGRAM_MASTER) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_PROGRAM_MASTER, new ProgrameMasterDAOImpl().findAllForCombo(isOnlyActive));

		}

		if ((reqComboVal & PRJ_COMBO_PROTOFOLIO_MASTER) == PRJ_COMBO_PROTOFOLIO_MASTER) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_PROTOFOLIO_MASTER,
					new PortfolioMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_TASK_MASTER) == PRJ_COMBO_TASK_MASTER) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_TASK_MASTER, new TaskMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_CUSTOMER_MASTER) == PRJ_COMBO_CUSTOMER_MASTER) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_CUSTOMER_MASTER, new CustomerMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_CONTRACTOR_MASTER) == PRJ_COMBO_CONTRACTOR_MASTER) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_CONTRACTOR_MASTER,
					new ContractorMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_DEALER_MASTER) == PRJ_COMBO_DEALER_MASTER) {
			epmCombosVO.getListOfComboData().put(PRJ_COMBO_DEALER_MASTER, new DealerMasterDAOImpl().findAllForCombo(isOnlyActive));

		}

		if ((reqComboVal & STP_COMBO_TIMEZONE_MASTER) == STP_COMBO_TIMEZONE_MASTER) {
			epmCombosVO.getListOfComboData().put(STP_COMBO_TIMEZONE_MASTER, new TimeZoneMasterDAOImpl().findAllForCombo(isOnlyActive));

		}

		if ((reqComboVal & STP_COMBO_CALENDAR_MASTER) == STP_COMBO_CALENDAR_MASTER) {
			epmCombosVO.getListOfComboData().put(STP_COMBO_CALENDAR_MASTER, new CalendarMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & STP_COMBO_QUALIFICATION_MASTER) == STP_COMBO_QUALIFICATION_MASTER) {

			epmCombosVO.getListOfComboData().put(STP_COMBO_QUALIFICATION_MASTER,
					new QualificationMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & HRD_COMBO_SHIFT_MASTER) == HRD_COMBO_SHIFT_MASTER) {

		}

		if ((reqComboVal & WFL_LOCATION_LEVEL_MASTER) == WFL_LOCATION_LEVEL_MASTER) {
			// epmCombosVO.getListOfComboData().put(PRJ_COMBO_CUSTOMER_MASTER,
			// new
			// LocationMasterDAOImpl().findAllLocationLevelForCombo(isOnlyActive));
		}

		if ((reqComboVal & ADM_COMBO_USER_MASTER) == ADM_COMBO_USER_MASTER) {
			epmCombosVO.getListOfComboData().put(ADM_COMBO_USER_MASTER, new UserInfoDAOImpl().findAllForCombo(isOnlyActive));

		}

		if ((reqComboVal & HRD_COMBO_EMPLOYEE_MASTER) == HRD_COMBO_EMPLOYEE_MASTER) {

			epmCombosVO.getListOfComboData().put(HRD_COMBO_EMPLOYEE_MASTER, new EmployeeMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & WFL_COMBO_LOCATION_MASTER) == WFL_COMBO_LOCATION_MASTER) {

			epmCombosVO.getListOfComboData().put(WFL_COMBO_LOCATION_MASTER, new LocationMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & WFL_COMBO_ROLE_MASTER) == WFL_COMBO_ROLE_MASTER) {

			epmCombosVO.getListOfComboData().put(WFL_COMBO_ROLE_MASTER, new RoleMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_PROJECT_MASTER) == PRJ_COMBO_PROJECT_MASTER) {

			epmCombosVO.getListOfComboData().put(PRJ_COMBO_PROJECT_MASTER,
					new ProjectTemplateGrandMasterDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqComboVal & PRJ_COMBO_PENDING_HUMAN_RESOURCES) == PRJ_COMBO_PENDING_HUMAN_RESOURCES) {

			epmCombosVO.getListOfComboData().put(PRJ_COMBO_PENDING_HUMAN_RESOURCES,
					new ProjectTemplateGrandMasterDAOImpl().getPendingRequiredHumanResources(isOnlyActive));
		}

		if ((reqComboVal & MAT_COMBO_LEVEL_MASTER) == MAT_COMBO_LEVEL_MASTER) {

			epmCombosVO.getListOfComboData().put(MAT_COMBO_LEVEL_MASTER, new LevelMasterDAOImpl().findAllForCombo(isOnlyActive));
		}*/

	}

	/**
	 * This method the individual bits in the reqCommonCodesComboVal passed and based on which bit
	 * is set, calls the DAO method to retrieve the respective commoncodes data
	 * 
	 * @param reqCommonCodesComboVal
	 * @param epmCombosVO
	 *            The object into which the retrieved combo data will be put into, This needs to be
	 *            an instansiated object.
	 * @param isOnlyActive
	 *            boolean value indicating if only the active once are to be retrieved or if all are
	 *            to be retrieved
	 * @throws CommonCodesException
	 */
	private void processCommonCodesCombo(long reqCommonCodesComboVal, EPMCombosVO epmCombosVO, boolean isOnlyActive)
			{

		/*if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_META_DATA) == COMMON_CODES_COMBO_META_DATA) {
			epmCombosVO.getListOfcommonCodesComboData().put(COMMON_CODES_COMBO_META_DATA,
					new CommonCodesMetadataDAOImpl().findAllForCombo(isOnlyActive));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_PRIORITY) == COMMON_CODES_COMBO_PRIORITY) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_PRIORITY,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_PRIORITY));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_SALUTATIONS) == COMMON_CODES_COMBO_SALUTATIONS) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_SALUTATIONS,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_SALUTATIONS));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_CURRENCY_TYPE) == COMMON_CODES_COMBO_CURRENCY_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_CURRENCY_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_CURRENCY_TYPE));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_PROJECT_TYPE) == COMMON_CODES_COMBO_PROJECT_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_PROJECT_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_PROJECT_TYPE));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_UNIT_OF_DURATION) == COMMON_CODES_COMBO_UNIT_OF_DURATION) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_UNIT_OF_DURATION,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_UNIT_OF_DURATION));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_COMMUNICATION_TYPE) == COMMON_CODES_COMBO_COMMUNICATION_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_COMMUNICATION_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_COMMUNICATION_TYPE));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_QUALIFICATION_LEVEL) == COMMON_CODES_COMBO_QUALIFICATION_LEVEL) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_QUALIFICATION_LEVEL,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_QUALIFICATION_LEVEL));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_DESIGNATIONS) == COMMON_CODES_COMBO_DESIGNATIONS) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_DESIGNATIONS,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_DESIGNATIONS));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_DEPARTMENTS) == COMMON_CODES_COMBO_DEPARTMENTS) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_DEPARTMENTS,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_DEPARTMENTS));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_EQUIPMENT_TYPE) == COMMON_CODES_COMBO_EQUIPMENT_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_EQUIPMENT_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_EQUIPMENT_TYPE));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_ACCEPTENCE_CRITERIA_STATUS) == COMMON_CODES_COMBO_ACCEPTENCE_CRITERIA_STATUS) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_ACCEPTENCE_CRITERIA_STATUS,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_ACCEPTENCE_CRITERIA_STATUS));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_UNIT_OF_MEASUREMENT) == COMMON_CODES_COMBO_UNIT_OF_MEASUREMENT) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_UNIT_OF_MEASUREMENT,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_UNIT_OF_MEASUREMENT));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_SKILL_SET) == COMMON_CODES_COMBO_SKILL_SET) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_SKILL_SET,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_PRIORITY));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_ITEM_ATTRIBUTE_TYPE) == COMMON_CODES_COMBO_ITEM_ATTRIBUTE_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_ITEM_ATTRIBUTE_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_ITEM_ATTRIBUTE_TYPE));
		}

		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_CHECKLIST_TYPE) == COMMON_CODES_COMBO_CHECKLIST_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_CHECKLIST_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_CHECKLIST_TYPE));
		}
		
		if ((reqCommonCodesComboVal & COMMON_CODES_COMBO_CONTRACT_TYPE) == COMMON_CODES_COMBO_CONTRACT_TYPE) {
			epmCombosVO.getListOfcommonCodesComboData().put(
					COMMON_CODES_COMBO_CONTRACT_TYPE,
					new CommonCodesDAOImpl().findAllForCombo(isOnlyActive,
							CommonCodesMetaDataConstants.COMMON_CODES_META_DATA_TYPE_CONTRACT_TYPE));
		}*/

	}

	/**
	 * This is the common method called from flex that is used to load the combo box data based on
	 * the parent id where ever there is a hierarchy representation
	 * 
	 * @param reqComboVal
	 *            The long value with the bit values set for which the combo data needs to be
	 *            retrieved
	 * @param parentIds
	 *            A hash map of parent Ids for which the name of the combo itself will be the key
	 * @param isOnlyActive
	 *            boolean value indicating if only the active needs to be retrieved or all the data
	 *            needs to be retrieved
	 * @return returns an object of {@link EPMCombosVO} which will have the required combo data set
	 *         with its own bit mask as the key in the hash map
	 * 
	 */
	public EPMCombosVO populateDataToHierarchyComboBoxes(long reqComboVal, HashMap<String, String> parentIds, boolean isOnlyActive) {

		EPMCombosVO epmCombosVO = new EPMCombosVO();
		String parentId = "0";

		/*try {

			if (reqComboVal > 0) {
				if ((reqComboVal & HIERARCHY_COMBO_LOCATION_MASTER) == HIERARCHY_COMBO_LOCATION_MASTER) {

					parentId = parentIds.get("HIERARCHY_COMBO_LOCATION_MASTER");
					epmCombosVO.getListOfHierarchyComboData().put(HIERARCHY_COMBO_LOCATION_MASTER,
							new LocationMasterDAOImpl().findAllForHierarchyComboByParent(isOnlyActive, parentId));

				}

				if ((reqComboVal & HIERARCHY_COMBO_MATERIAL_MASTER) == HIERARCHY_COMBO_MATERIAL_MASTER) {
					parentId = parentIds.get("HIERARCHY_COMBO_MATERIAL_MASTER");
					epmCombosVO.getListOfHierarchyComboData().put(HIERARCHY_COMBO_MATERIAL_MASTER,
							new MaterialHierarchyMasterDAOImpl().findAllForHierarchyComboByParent(isOnlyActive, parentId));

				}

				if ((reqComboVal & HIERARCHY_COMBO_GLOBAL_LOCATION_MASTER) == HIERARCHY_COMBO_GLOBAL_LOCATION_MASTER) {
					parentId = parentIds.get("HIERARCHY_COMBO_GLOBAL_LOCATION_MASTER");					
					epmCombosVO.getListOfHierarchyComboData().put(HIERARCHY_COMBO_GLOBAL_LOCATION_MASTER, new GlobalLocationsMasterDAOImpl().findAllForHierarchyComboByParent(parentId));
				}
			}

			epmCombosVO.setStatus(EPMResultVO.STATUS_SUCCESS);

		} catch (LocationException lex) {
			new EPMExceptionMessageResolver().resolveMessage(epmCombosVO, lex, EPMApplicationConstants.LOCALE);
		} catch (MaterialCoreException mcx) {
			new EPMExceptionMessageResolver().resolveMessage(epmCombosVO, mcx, EPMApplicationConstants.LOCALE);
		} catch (SetUpCalenderException stp) {
			new EPMExceptionMessageResolver().resolveMessage(epmCombosVO, stp, EPMApplicationConstants.LOCALE);
		}*/

		return epmCombosVO;

	}

}
