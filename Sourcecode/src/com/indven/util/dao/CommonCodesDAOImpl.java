/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CommonCodesDAOImpl.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	This class will be used to perform all the Common codes DB operations
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.util.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.indven.framework.dao.GenericBaseCRUDDAOImpl;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.util.entity.CommonCodesBean;
import com.indven.util.exception.UtilException;

/**
 * This class will be used to perform all the Common codes DB operations.
 * 
 * @author Saurabh
 * 
 */
public class CommonCodesDAOImpl extends GenericBaseCRUDDAOImpl<CommonCodesBean> {
	
	private static IndvenLogger logger = IndvenLogger.getInstance(CommonCodesDAOImpl.class);

	public CommonCodesDAOImpl() {
		super(CommonCodesBean.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<CommonCodesBean> getEntityBeanType() {

		return super.getEntityBeanType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#save(java.lang.Object)
	 */
	/**
	 * This method is used to throw the UtilException.
	 * 
	 * @param entity
	 *            The object of CommonCodesBean that contains the values to be
	 *            stored into DataBase.
	 * @throws UtilException
	 * @return returns the saved entity.
	 */
	@Override
	public CommonCodesBean save(CommonCodesBean entity) throws UtilException {

		logger.error(UtilException.UNABLE_TO_SAVE_COMMON_CODES_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_SAVE_COMMON_CODES_DETAILS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#update(java.lang.Object)
	 */
	/**
	 * This method is used to throw the UtilException.
	 * 
	 * @param entity
	 *            The object of CommonCodesBean which contains the updated
	 *            values of an existing record to be updated in database.
	 * @throws EPMStaleObjectException
	 * @throws UtilException.
	 */
	@Override
	public CommonCodesBean update(CommonCodesBean entity) throws UtilException {
		logger.error(UtilException.UNABLE_TO_UPDATE_COMMON_CODES_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_UPDATE_COMMON_CODES_DETAILS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	/**
	 * This method is used to find the CommonCodes record from the database of
	 * given ID.
	 * 
	 * @param id
	 *            The long type unique value of a record to be searched from
	 *            database.
	 * @return returns the CommonCodesBean object that is searched from database
	 *         containing the supplied ID.
	 * @throws UtilException
	 */
	@Override
	public CommonCodesBean findById(Long id) throws UtilException {

		try {
			return super.findById(id);
		} catch (HibernateException e) {
			logger.error(UtilException.UNABLE_TO_FIND_COMMON_CODES_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_FIND_COMMON_CODES_DETAILS, e);
		} catch (Exception e) {
			logger.error(UtilException.UNABLE_TO_FIND_COMMON_CODES_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_FIND_COMMON_CODES_DETAILS, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#delete(java.lang.Object)
	 */
	/**
	 * This method is used to delete a CommonCodes record present in the
	 * database.
	 * 
	 * @param entity
	 *            The object of CommonCodesBean which is to be deleted from
	 *            DataBase.
	 * @throws UtilException
	 */
	@Override
	public void purge(CommonCodesBean entity) throws UtilException {

		logger.error(UtilException.UNABLE_TO_DELETE_COMMON_CODES_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_DELETE_COMMON_CODES_DETAILS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findAll()
	 */
	/**
	 * This method is used to find the list of all the CommonCodes records
	 * present in the database.
	 * 
	 * @return returns the list of CommonCodesBean objects those are present in
	 *         the database.
	 * @throws UtilException
	 */
	@Override
	public List<CommonCodesBean> findAll() throws UtilException {

		try {
			return super.findAll();
		} catch (HibernateException e) {
			logger.error(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_DETAILS, e);
		} catch (Exception e) {
			logger.error(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_DETAILS, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indven.framework.dao.GenericBaseCRUDDAOImpl#purgeById(java.lang.Long)
	 */
	@Override
	public void purgeById(Long id) throws UtilException {
			logger.error(UtilException.UNABLE_TO_DELETE_COMMON_CODES_DETAILS);
			throw new UtilException(UtilException.UNABLE_TO_DELETE_COMMON_CODES_DETAILS);
	}
	
	@SuppressWarnings("unchecked")
	public List<CommonCodesBean> findAllForCombo( long type) throws UtilException {

		Session session = null;
		List<CommonCodesBean> entities = null;
		
		final String COMMON_CODES_MASTER_ALL = "FROM CommonCodesBean WHERE CommonCodesMetadataFkId = " + type;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			entities = (List<CommonCodesBean>) session.createQuery(COMMON_CODES_MASTER_ALL).list();

		} catch (HibernateException e) {
			logger.error(UtilException.UNABLE_TO_RETRIEVE_DATA_FOR_COMBO, e);
			throw new UtilException(UtilException.UNABLE_TO_RETRIEVE_DATA_FOR_COMBO, e);
		} catch (Exception e) {
			logger.error(UtilException.UNABLE_TO_RETRIEVE_DATA_FOR_COMBO, e);
			throw new UtilException(UtilException.UNABLE_TO_RETRIEVE_DATA_FOR_COMBO, e);
		}finally {
			session.close();
		}

		return entities;
	}
}
