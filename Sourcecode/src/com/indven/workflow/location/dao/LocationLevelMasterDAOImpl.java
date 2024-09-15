package com.indven.workflow.location.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.workflow.location.entity.LocationLevelMasterBean;
import com.indven.workflow.location.exception.LocationException;

public class LocationLevelMasterDAOImpl {

	private static IndvenLogger logger = IndvenLogger.getInstance(LocationMasterDAOImpl.class);

	@SuppressWarnings("unchecked")
	public List<LocationLevelMasterBean> findAllLocationLevel() throws LocationException {
		Session session = null;
		Transaction tx = null;
		List<LocationLevelMasterBean> entities = new ArrayList();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			entities = session.createCriteria(LocationLevelMasterBean.class).list();

			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			logger.error(LocationException.UNABLE_TO_LIST_ALL_LOCATION_LEVEL_MASTER, he);
			throw new LocationException(LocationException.UNABLE_TO_LIST_ALL_LOCATION_LEVEL_MASTER);
		} catch (Exception e) {
			tx.rollback();
			logger.error(LocationException.UNABLE_TO_LIST_ALL_LOCATION_LEVEL_MASTER, e);
			throw new LocationException(LocationException.UNABLE_TO_LIST_ALL_LOCATION_LEVEL_MASTER);
		} finally {
			session.close();
		}
		return entities;
	}

}
