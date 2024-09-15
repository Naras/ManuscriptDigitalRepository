/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CommonCodesMetadataDAOImpl.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.util.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.indven.framework.dao.GenericBaseCRUDDAOImpl;
import com.indven.framework.logging.IndvenLogger;
import com.indven.util.entity.CommonCodesMetadataBean;
import com.indven.util.exception.UtilException;

/**
 * This class will be used to perform all the CommonCodes Meta Data DB operations.
 * 
 * @author Saurabh
 * 
 */
public class CommonCodesMetadataDAOImpl extends GenericBaseCRUDDAOImpl<CommonCodesMetadataBean> {
	
	private static IndvenLogger logger = IndvenLogger.getInstance(CommonCodesMetadataDAOImpl.class);
	public CommonCodesMetadataDAOImpl() {
		super(CommonCodesMetadataBean.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<CommonCodesMetadataBean> getEntityBeanType() {

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
	 *            The object of CommonCodesMetadataBean that contains the values to be stored into
	 *            DataBase.
	 * @throws UtilException
	 * @return returns the saved entity.
	 */
	@Override
	public CommonCodesMetadataBean save(CommonCodesMetadataBean entity) throws UtilException {

		logger.error(UtilException.UNABLE_TO_SAVE_COMMON_CODES_METADATA_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_SAVE_COMMON_CODES_METADATA_DETAILS);
		
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
	 *            The object of CommonCodesMetadataBean which contains the updated values of an
	 *            existing record to be updated in database.
	 * @throws UtilException.
	 */
	@Override
	public CommonCodesMetadataBean update(CommonCodesMetadataBean entity) throws UtilException {

		logger.error(UtilException.UNABLE_TO_UPDATE_COMMON_CODES_METADATA_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_UPDATE_COMMON_CODES_METADATA_DETAILS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	/**
	 * This method is used to find the CommonCodesMetadata record from the database of given ID.
	 * 
	 * @param id
	 *            The long type unique value of a record to be searched from database.
	 * @return returns the CommonCodesMetadataBean object that is searched from database containing
	 *         the supplied ID.
	 * @throws UtilException
	 */
	@Override
	public CommonCodesMetadataBean findById(Long id) throws UtilException {

		try {
			return super.findById(id);
		} catch (HibernateException e) {
			logger.error(UtilException.UNABLE_TO_FIND_COMMON_CODES_METADATA_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_FIND_COMMON_CODES_METADATA_DETAILS, e);
		} catch (Exception e) {
			logger.error(UtilException.UNABLE_TO_FIND_COMMON_CODES_METADATA_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_FIND_COMMON_CODES_METADATA_DETAILS, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#delete(java.lang.Object)
	 */
	/**
	 * This method is used to throw the UtilException.
	 * 
	 * @param entity
	 *            The object of CommonCodesMetadataBean which is to be deleted from DataBase.
	 * @throws UtilException
	 */
	@Override
	public void purge(CommonCodesMetadataBean entity) throws UtilException {

		logger.error(UtilException.UNABLE_TO_DELETE_COMMON_CODES_METADATA_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_DELETE_COMMON_CODES_METADATA_DETAILS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findAll()
	 */
	/**
	 * This method is used to find the list of all the CommonCodesMetadata records present in the
	 * database.
	 * 
	 * @return returns the list of CommonCodesMetadataBean objects those are present in the
	 *         database.
	 * @throws UtilException
	 */
	@Override
	public List<CommonCodesMetadataBean> findAll() throws UtilException {

		try {
			return super.findAll();
		} catch (HibernateException e) {
			logger.error(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_METADATA_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_METADATA_DETAILS, e);
		} catch (Exception e) {
			logger.error(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_METADATA_DETAILS, e);
			throw new UtilException(UtilException.UNABLE_TO_LIST_ALL_COMMON_CODES_METADATA_DETAILS, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indven.framework.dao.GenericBaseCRUDDAOImpl#purgeById(java.lang.Long)
	 */
	@Override
	public void purgeById(Long id) throws UtilException {
		logger.error(UtilException.UNABLE_TO_DELETE_COMMON_CODES_METADATA_DETAILS);
		throw new UtilException(UtilException.UNABLE_TO_DELETE_COMMON_CODES_METADATA_DETAILS);
	}

}
