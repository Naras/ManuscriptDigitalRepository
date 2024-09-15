/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	LocationMasterDAOImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	14th December 2011
 * Brief Description					:	This class will be used to perform all the LocationMaster DB operations.
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.workflow.location.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.combohandler.EPMComboEntityVO;
import com.indven.framework.combohandler.EPMCombosDAOImpl;
import com.indven.framework.dao.BaseCRUDDAOImpl;
import com.indven.framework.entity.BaseEntityBean;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.workflow.location.entity.LocationMasterBean;
import com.indven.workflow.location.entity.LocationUserRolesDetailsBean;
import com.indven.workflow.location.exception.LocationException;
import com.indven.workflow.util.WorkflowQueriesConstant;

/**
 * This class will be used to perform all the LocationMaster DB operations.
 * 
 * @author Saurabh
 * 
 * @see BaseEntityBean
 * 
 */
public class LocationMasterDAOImpl extends BaseCRUDDAOImpl<LocationMasterBean> {

	private static IndvenLogger logger = IndvenLogger.getInstance(LocationMasterDAOImpl.class);

	public LocationMasterDAOImpl() {
		super(LocationMasterBean.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<LocationMasterBean> getEntityBeanType() {
		return super.getEntityBeanType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#save(java.lang.Object)
	 */
	/**
	 * This method is used to save the Location Master details into database.
	 * 
	 * @param entity
	 *            The object of LocationMasterBean that contains the values to be stored into
	 *            DataBase.
	 * @throws LocationException
	 * @return returns the saved entity.
	 */
	@Override
	public LocationMasterBean save(LocationMasterBean entity) throws LocationException {
		try {

			return super.save(entity);

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_SAVE_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_SAVE_LOCATION, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_SAVE_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_SAVE_LOCATION, e);
		}
	}

	/**
	 * This method is used to update the details of LocationMaster in database.
	 * 
	 * @param entity
	 *            The object of LocationMasterBean which contains the updated values of an existing
	 *            record to be updated in database.
	 * @throws LocationException.
	 */
	@Override
	public LocationMasterBean update(LocationMasterBean entity) throws LocationException {
		try {
			return super.update(entity);
		} catch (HibernateException e) {

			logger.error(LocationException.UNABLE_TO_UPDATE_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_UPDATE_LOCATION, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_UPDATE_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_UPDATE_LOCATION, e);
		}

	}

	/**
	 * This method is used to find the Location Master record from the database of given ID.
	 * 
	 * @param id
	 *            The long type unique value of a record to be searched from database.
	 * @return returns the LocationMasterBean object that is searched from database containing the
	 *         supplied ID.
	 * @throws LocationException
	 */
	@Override
	public LocationMasterBean findById(Long id) throws LocationException {
		try {

			return super.findById(id);

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_FIND_LOCATION_DETAILS, e);
			throw new LocationException(LocationException.UNABLE_TO_FIND_LOCATION_DETAILS, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_FIND_LOCATION_DETAILS, e);
			throw new LocationException(LocationException.UNABLE_TO_FIND_LOCATION_DETAILS, e);
		}
	}

	/**
	 * This method is used to delete a Location Master record present in the database.
	 * 
	 * @param entity
	 *            The object of LocationMasterBean which is to be deleted from DataBase.
	 * @throws LocationException
	 */
	@Override
	public void purge(LocationMasterBean entity) throws LocationException {
		try {

			super.purge(entity);

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_DELETE_LOCATION_DETAILS, e);
			throw new LocationException(LocationException.UNABLE_TO_DELETE_LOCATION_DETAILS, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_DELETE_LOCATION_DETAILS, e);
			throw new LocationException(LocationException.UNABLE_TO_DELETE_LOCATION_DETAILS, e);
		}

	}

	/**
	 * This method is used to find the list of all the Location Master records present in the
	 * database.
	 * 
	 * @return returns the list of LocationMasterBean objects those are present in the database.
	 * @throws LocationException
	 */
	@Override
	public List<LocationMasterBean> findAll() throws LocationException {
		try {

			return super.findAll();

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_LIST_ALL_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_LIST_ALL_LOCATION, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_LIST_ALL_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_LIST_ALL_LOCATION, e);
		}
	}

	/**
	 * This method will mark the entity as deleted by setting the Field isDeleted as true for the
	 * given entity
	 * 
	 * @param id
	 *            The id of the record,which will be modified by setting the isDeleted field as
	 *            true.
	 * @return returns integer value representing no of records modified in database.
	 * @throws LocationException
	 */
	@Override
	public int markedAsDeleted(Long id) throws LocationException {
		try {

			return super.markedAsDeleted(id);

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_MARK_LOCATION_DELETED, e);
			throw new LocationException(LocationException.UNABLE_TO_MARK_LOCATION_DELETED, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_MARK_LOCATION_DELETED, e);
			throw new LocationException(LocationException.UNABLE_TO_MARK_LOCATION_DELETED, e);
		}
	}

	/**
	 * This method will be used to retrieve data from a table to be set as combo box values.
	 * 
	 * @param isOnlyActive
	 *            boolean value indicating if only the active needs to be retrieved or all the data
	 *            needs to be retrieved
	 * @return returns the List of EPMComboEntityVO objects containing the records from database.
	 * @throws LocationException
	 */
	public List<EPMComboEntityVO> findAllForCombo(boolean isOnlyActive) throws LocationException {

		List<EPMComboEntityVO> list = null;

		try {

			if (isOnlyActive) {

				//list = new EPMCombosDAOImpl().execQueryForCombo(EPMComboQueriesConstants.LOCATION_MASTER_ACTIVE);

			} else {
				//list = new EPMCombosDAOImpl().execQueryForCombo(EPMComboQueriesConstants.LOCATION_MASTER_ALL);
			}

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
		}

		return list;
	}

	/**
	 * This method is responsible for retrieving the list of location for a given parent Id
	 * 
	 * @param isOnlyActive
	 *            boolean value indicating if only the active locations are to be retrieved or all
	 *            locations are to be retrieved
	 * @param parentId
	 *            The parent Id for which the list is to be retrieved
	 * @return returns an list of {@link EPMComboEntityVO}
	 * @throws LocationException
	 * @see EPMComboEntityVO
	 * @author Sandeep Solomon Kunder
	 */
	public List<EPMComboEntityVO> findAllForHierarchyComboByParent(boolean isOnlyActive, String parentId) throws LocationException {

		List<EPMComboEntityVO> list = null;

		try {

			if (isOnlyActive) {

				//list = new EPMCombosDAOImpl().execQueryForCombo(EPMComboQueriesConstants.HIERARCHY_LOCATION_MASTER_ACTIVE + parentId);

			} else {
				//list = new EPMCombosDAOImpl().execQueryForCombo(EPMComboQueriesConstants.HIERARCHY_LOCATION_MASTER_ALL + parentId);
			}

		} catch (HibernateException e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_DATA_FOR_LOCATIONCOMBO, e);
		}

		return list;
	}

	public List<EPMComboEntityVO> getSelectedWorkflowForLocation(Number locationId) throws LocationException {
		List<EPMComboEntityVO> entities = null;

		try {

			entities = new EPMCombosDAOImpl().execQueryForCombo(WorkflowQueriesConstant.ASSIGNED_WORKFLOWS_FOR_LOCATION + locationId);
			entities.remove(0); // Removing the default -- Select --

		} catch (HibernateException he) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_WORKFLOW_FOR_LOCATION, he);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_WORKFLOW_FOR_LOCATION);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_WORKFLOW_FOR_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_WORKFLOW_FOR_LOCATION);
		}

		return entities;
	}

	/**
	 * This method is responsible for inserting the selected workflows for a given location
	 * 
	 * @param processIds
	 *            The list of processes(Workflows) Ids for which it has to be inserted for the
	 *            location
	 * @param locationId
	 *            The location Id for which the processes needs to be inserted
	 * @return returns true if everyting was updated successful, false otherwise
	 * @throws LocationException
	 *             If any error occurs
	 * @author Sandeep Solomon Kunder
	 */
	public boolean insertSelectedWorkflowForLocation(ArrayList<String> processIds, String locationId) throws LocationException {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		boolean isSuccess = false;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (String processId : processIds) {
				query = session.createSQLQuery("{ CALL wfl.USP_PouplateWorkflowForLocation(?, ?) }");
				query.setParameter(0, locationId);
				query.setParameter(1, processId);
				query.executeUpdate();
			}

			tx.commit();

			isSuccess = true;
		} catch (HibernateException he) {
			tx.rollback();
			logger.error(LocationException.UNABLE_TO_SAVE_WORKFLOW_DATA_FOR_LOCATION, he);
			throw new LocationException(LocationException.UNABLE_TO_SAVE_WORKFLOW_DATA_FOR_LOCATION);
		} catch (Exception e) {
			tx.rollback();
			logger.error(LocationException.UNABLE_TO_SAVE_WORKFLOW_DATA_FOR_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_SAVE_WORKFLOW_DATA_FOR_LOCATION);
		} finally {
			session.close();
		}
		return isSuccess;
	}

	/**
	 * This method is responsible for inserting the User Role mapping for a location
	 * 
	 * @param userRolesBeans
	 *            The list of {@link LocationUserRolesDetailsBean} beans which has to be inserted
	 *            for the location
	 * @return returns true if everyting was updated successful, false otherwise
	 * @throws LocationException
	 *             If any error occurs
	 * @author Sandeep Solomon Kunder
	 */
	public boolean insertUserRoleForLocation(ArrayList<LocationUserRolesDetailsBean> userRolesBeans) throws LocationException {
		Session session = null;
		Transaction tx = null;
		boolean isSuccess = false;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (LocationUserRolesDetailsBean userRoleBean : userRolesBeans) {
			   if(userRoleBean.getLocationMasterFkId() == 0) {
				  // UserInfoDAOImpl userInfoDAOImpl = new UserInfoDAOImpl();
				   //Long userHomeLocationId = userInfoDAOImpl.getUserHomeLocation(userRoleBean.getUserInfoFkId());
				   userRoleBean.setLocationMasterFkId(1L);
			    }
				session.save(userRoleBean);
			}

			tx.commit();

			isSuccess = true;
		} catch (HibernateException he) {
			tx.rollback();
			logger.error(LocationException.UNABLE_TO_UPDATE_USER_ROLE_MAPPING, he);
			throw new LocationException(LocationException.UNABLE_TO_UPDATE_USER_ROLE_MAPPING);
		} catch (Exception e) {
			tx.rollback();
			logger.error(LocationException.UNABLE_TO_UPDATE_USER_ROLE_MAPPING, e);
			throw new LocationException(LocationException.UNABLE_TO_UPDATE_USER_ROLE_MAPPING);
		} finally {
			session.close();
		}
		return isSuccess;
	}

	/**
	 * This method is responsible for retrieving the list of un-assigned roles that are required for
	 * the workflows at the passed location id
	 * 
	 * @param locationId
	 *            the location id for which the un-assigned roles are to be retrieved
	 * @return returns a list of Roles
	 * @throws LocationException
	 * @see EPMComboEntityVO
	 * @author Sandeep Solomon Kunder
	 */
	public List<EPMComboEntityVO> getUnassignedRolesRequiredForLocation(String locationId) throws LocationException {
		List<EPMComboEntityVO> entities = null;

		try {

			entities = new EPMCombosDAOImpl().execQueryForCombo(WorkflowQueriesConstant.REQUIRED_UNASSIGNED_ROLES_FOR_LOCATION
					+ locationId);
			entities.remove(0); // Removing the default -- Select --

		} catch (HibernateException he) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_ROLES_FOR_LOCATION, he);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_ROLES_FOR_LOCATION);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_ROLES_FOR_LOCATION, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_ROLES_FOR_LOCATION);
		}

		return entities;
	}

	/**
	 * This method is responsible for retrieving the list of users available in the system
	 * 
	 * @return returns a list of Users
	 * @throws LocationException
	 * @see EPMComboEntityVO
	 * @author Sandeep Solomon Kunder
	 */
	public List<EPMComboEntityVO> getListOfUsers() throws LocationException {
		List<EPMComboEntityVO> entities = null;

		try {

			entities = new EPMCombosDAOImpl().execQueryForCombo(WorkflowQueriesConstant.LIST_OF_USERS_FOR_ROLES_ASSIGN);

		} catch (HibernateException he) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_LIST_OF_USERS, he);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_LIST_OF_USERS);
		} catch (Exception e) {
			logger.error(LocationException.UNABLE_TO_RETRIEVE_LIST_OF_USERS, e);
			throw new LocationException(LocationException.UNABLE_TO_RETRIEVE_LIST_OF_USERS);
		}

		return entities;
	}

}