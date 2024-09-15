/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	BaseDAOImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	22nd December 2011
 * Brief Description					:	This is the Basic CRUD operation DAO which has to be extended by all entity DAOs and use the 
 * 											basic CRUD methods by extending this class with default constructor setting the value with 
 * 											implementing bean type.
 * Author								: 	Yusuf Pasha and Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.entity.BaseEntityBean;
import com.indven.framework.exception.FrameworkException;
import com.indven.framework.util.HibernateUtil;

/**
 * This is the Basic CRUD operation DAO which has to be extended by all entity DAOs and use the
 * basic CRUD methods by extending this class with default constructor setting the value with
 * implementing bean type.
 * 
 * @author Yusuf
 * @see BaseEntityBean
 * 
 */
public class GenericBaseCRUDDAOImpl<T extends Object> implements GenericBaseCRUDDAO<T> {

	private final Class<T> entityBeanType;

	/**
	 * This constructor will tell us what bean type is the class
	 * 
	 * @param entityBeanType
	 */
	public GenericBaseCRUDDAOImpl(final Class<T> entityBeanType) {
		this.entityBeanType = entityBeanType;
	}

	public Class<T> getEntityBeanType() {
		return entityBeanType;
	}

	/**
	 * This method will save the given entity in to the DB and will return the saved entity
	 * 
	 * @param entity
	 *            of type generic which extends the BaseEntityBean
	 */
	public T save(T entity) throws HibernateException, Exception {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}

		return entity;
	}

	/**
	 * This method will update the given entity
	 * 
	 * @param entity
	 *            of type generic which extends the BaseEntityBean
	 */
	public T update(T entity) throws HibernateException, Exception {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
		return entity;

	}

	/**
	 * This method will find the entity given an Id the given entity
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id) throws HibernateException, Exception {

		Session session = null;
		Transaction tx = null;
		T entity = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			entity = (T) session.get(getEntityBeanType(), id);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
		return entity;

	}

	/**
	 * This method will delete the given entity
	 * 
	 * @param entity
	 *            of type generic which extends the BaseEntityBean
	 */
	public void purge(T entity) throws HibernateException, Exception {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}

	}

	/**
	 * This method will return all the entities if it is not having isDeleted field and returns only
	 * those entities which is having isDeleted field
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws HibernateException, Exception {
		Session session = null;
		Transaction tx = null;
		List<T> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			entities = session.createCriteria(entityBeanType).list();
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
		return entities;
	}

	/**
	 * This method will delete the entity by id
	 * 
	 * @param id
	 *            of type Long which is the entity id.
	 * @throws Exception
	 */
	public void purgeById(Long id) throws HibernateException, Exception {
		Session session = null;
		Transaction tx = null;
		String queryString = "DELETE FROM " + entityBeanType.getName() + "  where id = :id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery(queryString);
			query.setParameter("id", id);
			int deletedRow = query.executeUpdate();

			if (deletedRow == 0)
				throw new HibernateException(FrameworkException.RECORD_NOT_FOUND);

			tx.commit();

		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}

}
