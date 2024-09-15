/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuInfoDAOImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	23rd December 2011
 * Brief Description					:	This class will be used to perform all the Menu Info
 *                                          DB operations
 * Author								: 	Rashmiranjan
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.menu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.dao.GenericBaseCRUDDAOImpl;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.exception.MenuInfoException;

/**
 * This class will be used to perform all the Customer Master DB operations
 * 
 * @author Rashmiranjan
 * 
 */
public class RoleMasterMenuInfoDAOImpl extends GenericBaseCRUDDAOImpl<AccessControlBean> {

	private static IndvenLogger logger = IndvenLogger.getInstance(RoleMasterMenuInfoDAOImpl.class);

	public RoleMasterMenuInfoDAOImpl() {
		super(AccessControlBean.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<AccessControlBean> getEntityBeanType() {

		return super.getEntityBeanType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#save(java.lang.Object)
	 */
	@Override
	public AccessControlBean save(AccessControlBean entity) throws MenuInfoException {

		try {
			return super.save(entity);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		}
	}

	public void save(List<AccessControlBean> RoleMasterMenuInfoBeans) throws MenuInfoException {

		try {
			for (AccessControlBean entity : RoleMasterMenuInfoBeans) {
				super.save(entity);
			}

		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#update(java.lang.Object)
	 */
	@Override
	public AccessControlBean update(AccessControlBean entity) throws MenuInfoException {

		try {
			super.update(entity);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	@Override
	public AccessControlBean findById(Long id) throws MenuInfoException {

		try {
			return super.findById(id);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#delete(java.lang.Object)
	 */
	@Override
	public void purge(AccessControlBean entity) throws MenuInfoException {

		try {
			super.purge(entity);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
		}

	}

	public void purge(Long roleFkId) throws MenuInfoException {

		Session session = null;
		Transaction tx = null;
		Query query = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session
					.createQuery("DELETE AccessControlBean roleMasterMenuInfoBean  WHERE roleMasterMenuInfoBean.roleFkId =:roleFkId");
			query.setParameter("roleFkId", roleFkId);
			int result = query.executeUpdate();
			tx.commit();

		} catch (HibernateException he) {
			logger.error(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, he);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, he);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findAll()
	 */
	@Override
	public List<AccessControlBean> findAll() throws MenuInfoException {

		try {
			return super.findAll();
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
		}

	}

}
