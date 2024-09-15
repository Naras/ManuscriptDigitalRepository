/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	LocationMasterServiceImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	08th Mar 2012
 * Brief Description					:	This class will be used to perform all the Location Master related  
 *                                          service operations
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.indven.framework.combohandler.EPMComboEntityVO;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.service.BaseEntityCRUDService;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.workflow.location.component.LocationMasterAssembler;
import com.indven.workflow.location.component.LocationUserRoleDetailsAssembler;
import com.indven.workflow.location.dao.LocationMasterDAOImpl;
import com.indven.workflow.location.entity.LocationMasterBean;
import com.indven.workflow.location.entity.LocationUserRolesDetailsBean;
import com.indven.workflow.location.exception.LocationException;
import com.indven.workflow.location.vo.LocationMasterVO;
import com.indven.workflow.location.vo.LocationUserRoleDetailsVO;

/**
 * This class will be used to perform all the Location Master related service operations.
 * 
 * @author Nithya
 * 
 */
public class LocationMasterServiceImpl {

	private LocationMasterDAOImpl locationMasterDAOImpl = new LocationMasterDAOImpl();
	private LocationMasterAssembler locationMasterAssembler = new LocationMasterAssembler();
	private IndvenResultVO result = new IndvenResultVO();

	/**
	 * This method is used to save the Location Master details to database.
	 * 
	 * @param locationMasterVO
	 *            The object of LocationMasterVO that contains the values to be stored into
	 *            DataBase.
	 * @return returns the EPMResultVO object which contains either status success or failure.
	 */
	public LocationMasterVO save(LocationMasterVO locationMasterVO) {

		LocationMasterBean locationMasterBean;
		try {
			locationMasterBean = locationMasterAssembler.convertVoToEntity(locationMasterVO);
			locationMasterBean = locationMasterDAOImpl.save(locationMasterBean);
			locationMasterVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
			locationMasterVO.setId(locationMasterBean.getId());
			
		} catch (LocationException e) {

			new IndvenExceptionMessageResolver().resolveMessage(result, e, IndvenApplicationConstants.LOCALE);
		}
		return locationMasterVO;
	}

	/**
	 * This method is used to find the Location Master record from the database of given ID.
	 * 
	 * @param id
	 *            The long type unique value of a record to be searched from database.
	 * @return returns the LocationMasterVO object that is searched from database containing the
	 *         supplied ID.
	 */
	public LocationMasterVO findById(Long id) {
		LocationMasterVO locationMasterVO = new LocationMasterVO();

		try {
			locationMasterVO = locationMasterAssembler.convertEntityToVo(locationMasterDAOImpl.findById(id));
			locationMasterVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
		} catch (LocationException e) {

			new IndvenExceptionMessageResolver().resolveMessage(locationMasterVO, e, IndvenApplicationConstants.LOCALE);
		}
		return locationMasterVO;
	}

	/**
	 * This method is used to find the list of all the Location Master records present in the
	 * database.
	 * 
	 * @return returns the list of LocationMasterVO objects those are present in the database.
	 */
	public FindAllResultVO<LocationMasterVO> findAll() {
		List<LocationMasterVO> locationMasterVOsList = new ArrayList<LocationMasterVO>();
		FindAllResultVO<LocationMasterVO> objResult = new FindAllResultVO<LocationMasterVO>();
		try {
			locationMasterVOsList = locationMasterAssembler.convertEntitiesToVos(locationMasterDAOImpl.findAll());
			objResult.setListOfElemnents(locationMasterVOsList);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);
		} catch (LocationException e) {

			new IndvenExceptionMessageResolver().resolveMessage(objResult, e, IndvenApplicationConstants.LOCALE);
		}

		return objResult;
	}

	/**
	 * This method is used to update the details of Location Master in database.
	 * 
	 * @param locationMasterVO
	 *            The object of LocationMasterVO which contains the updated values of an existing
	 *            record to be updated in database.
	 * @return returns the object of EPMResultVO which contains either status success or failure.
	 */
	public LocationMasterVO update(LocationMasterVO locationMasterVO) {
		LocationMasterBean locationMasterBean = new LocationMasterBean();

		try {
			locationMasterBean = locationMasterAssembler.convertVoToEntity(locationMasterVO);
			//locationMasterVO = locationMasterAssembler.convertEntityToVo(locationMasterDAOImpl.update(locationMasterBean));
			locationMasterBean = locationMasterDAOImpl.update(locationMasterBean);
			locationMasterVO.setRowVersion(locationMasterBean.getRowVersion());
			locationMasterVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(locationMasterVO, e, IndvenApplicationConstants.LOCALE);
		}

		return locationMasterVO;
	}

	/**
	 * This method is used to delete a Location Master record present in the database.
	 * 
	 * @param locationMasterVO
	 *            The object of LocationMasterVO containing the record which is to be deleted from
	 *            DataBase.
	 * @return returns the object of EPMResultVO which contains either status as success or failure.
	 */
	public IndvenResultVO purge(LocationMasterVO locationMasterVO) {
		LocationMasterBean locationMasterBean = new LocationMasterBean();

		try {
			locationMasterBean = locationMasterDAOImpl.findById(locationMasterVO.getId());
			locationMasterDAOImpl.purge(locationMasterBean);
			result.setStatus(IndvenResultVO.STATUS_SUCCESS);
		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(result, e, IndvenApplicationConstants.LOCALE);
		}

		return result;
	}

	/**
	 * This method will mark the Location Master as deleted by setting the Field isDeleted as true.
	 * 
	 * @param id
	 *            The id of the record which will be modified by setting the isDeleted field as
	 *            true.
	 * @return returns object of EPMResultVO which contains either status as success or failure.
	 */
	public IndvenResultVO markedAsDeleted(Long id) {

		try {
			locationMasterDAOImpl.markedAsDeleted(id);
			result.setStatus(IndvenResultVO.STATUS_SUCCESS);
		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(result, e, IndvenApplicationConstants.LOCALE);
		} 
		return result;
	}

	/**
	 * This method is responsible for calling the DAO method to insert the required workflows for a
	 * location and do the required audit.
	 * 
	 * @param locationId
	 *            The location Id for which the workflow needs to be inserted
	 * @return an object of {@link EPMResultVO} with the appropriate message set
	 * @author Sandeep Solomon Kunder
	 */
	public FindAllResultVO<EPMComboEntityVO> getSelectedWorkflowforLocation(Long locationId) {

		FindAllResultVO<EPMComboEntityVO> objResult = new FindAllResultVO<EPMComboEntityVO>();

		try {

			objResult.setListOfElemnents(locationMasterDAOImpl.getSelectedWorkflowForLocation(locationId));
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(objResult, e, IndvenApplicationConstants.LOCALE);
		}
		return objResult;
	}

	/**
	 * This method is responsible for calling the DAO method to insert the required workflows for a
	 * location and do the required audit.
	 * 
	 * @param processIds
	 *            The list of processes(Workflows) that needs to be inserted for the location
	 * @param locationId
	 *            The location Id for which the workflow needs to be inserted
	 * @return an object of {@link EPMResultVO} with the appropriate message set
	 * @author Sandeep Solomon Kunder
	 */
	public IndvenResultVO saveSelectedWorkflowforLocation(ArrayList<String> processIds, String locationId) {

		try {

			if (locationMasterDAOImpl.insertSelectedWorkflowForLocation(processIds, locationId)) {
				result.setStatus(IndvenResultVO.STATUS_SUCCESS);
			}

		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(result, e, IndvenApplicationConstants.LOCALE);
		}
		return result;
	}

	/**
	 * This method is responsible for calling the DAO method to retrieve the unique list of
	 * un-assigned roles that are required.
	 * 
	 * @param locationId
	 *            The location Id for which the required roles are to be retrieved
	 * @return an object of {@link EPMResultVO} with the appropriate message set
	 * @author Sandeep Solomon Kunder
	 */
	public LocationMasterVO getRequiredRolesForLocation(String locationId) {

		LocationMasterVO locationMasterVo = new LocationMasterVO();

		try {

			locationMasterVo.setListOfRequiredRoles(locationMasterDAOImpl.getUnassignedRolesRequiredForLocation(locationId));
			locationMasterVo.setListOfUsers(locationMasterDAOImpl.getListOfUsers());
			locationMasterVo.setStatus(IndvenResultVO.STATUS_SUCCESS);

		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(result, e, IndvenApplicationConstants.LOCALE);
		}
		return locationMasterVo;
	}

	/**
	 * This method is responsible for callign the DAO method to update the user role mapping for the
	 * location
	 * 
	 * @param listOfUserRole
	 *            An Arraylist of {@link LocationUserRoleDetailsVO} which will hold the details of
	 *            the roles assigned to the user
	 * @author Sandeep Solomon Kunder
	 */
	public IndvenResultVO updateAssignedRolesForLocation(ArrayList<LocationUserRoleDetailsVO> listOfUserRole) {

		LocationUserRoleDetailsAssembler locationUserRoleAssembler = new LocationUserRoleDetailsAssembler();
		ArrayList<LocationUserRolesDetailsBean> userRolesBeans = null;

		try {
			userRolesBeans = (ArrayList<LocationUserRolesDetailsBean>) locationUserRoleAssembler.convertVosToEntities(listOfUserRole);
			if (locationMasterDAOImpl.insertUserRoleForLocation(userRolesBeans)) {
				result.setStatus(IndvenResultVO.STATUS_SUCCESS);
			}

		} catch (LocationException e) {
			new IndvenExceptionMessageResolver().resolveMessage(result, e, IndvenApplicationConstants.LOCALE);
		}

		return result;
	}

}
