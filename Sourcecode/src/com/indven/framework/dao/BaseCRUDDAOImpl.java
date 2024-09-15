/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	BaseDAOImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	22nd December 2011
 * Brief Description					:	This is the Basic CRUD operation DAO which has to be extended by all entity DAOs and use the 
 * 											basic CRUD methods by extending this class with default constructor setting the value with 
 * 											implementing bean type.
 * Author								: 	
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.indven.framework.entity.BaseEntityBean;
import com.indven.framework.util.HibernateUtil;

/**
 * This is the Basic CRUD operation DAO which has to be extended by all entity DAOs and use the
 * basic CRUD methods by extending this class with default constructor setting the value with
 * implementing bean type.
 * 
 * @author 
 * @see BaseEntityBean
 * 
 */
public class BaseCRUDDAOImpl<T extends BaseEntityBean> implements BaseCRUDDAO<T> {

	private final Class<T> entityBeanType;

	/**
	 * This constructor will tell us what bean type is the class
	 * 
	 * @param entityBeanType
	 */
	public BaseCRUDDAOImpl(final Class<T> entityBeanType) {
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

			return entity;
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}

	}

	/**
	 * This method will find the entity given an Id the given entity
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id) throws HibernateException, Exception {
		List<T> entities = null;
		Session session = null;
		Transaction tx = null;
		T entity = null;
		Criteria criteria = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			criteria = session.createCriteria(entityBeanType);
			criteria.add(Restrictions.eq("id", id));
			entities = criteria.list();
			if (entities != null && entities.size() > 0) {
				entity = (T) entities.get(0);
			}

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
	 * This method will save the given entity in to the DB and will return the saved entity
	 * 
	 * @param entity
	 *            of type generic which extends the BaseEntityBean
	 */
	public T saveOrUpdate(T entity) throws HibernateException, Exception {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(entity);
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
	 * This method will mark the entity as delete by setting the Field isDeleted as true for the
	 * given entity
	 * 
	 * @param entity
	 *            of type generic which extends the BaseEntityBean
	 */
	public int markedAsDeleted(Long id) throws HibernateException, Exception {

		Session session = null;
		Transaction tx = null;
		int deleteStatus = 0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String entityClassName = getEntityBeanType().getName();
			String queryString = "select rowVersion  from " + entityClassName + " where id = :id";

			Query qry = session.createQuery(queryString);
			qry.setParameter("id", id);

			Integer rowVersion = (Integer) qry.list().get(0) + 1;

			String markAsDeleteHql = "update " + entityClassName + " set isDeleted = true , rowVersion =:rowVersion  where id=:id";
			Query query = session.createQuery(markAsDeleteHql);
			query.setParameter("id", id);
			query.setParameter("rowVersion", rowVersion);
			deleteStatus = query.executeUpdate();

			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
		return deleteStatus;
	}

	/**
	 * This method is used to set the createdBy and modifiedBy values for the bean
	 * 
	 * @param entity
	 *            of type generic which extends the BaseEntityBean
	 * 
	 * @throws Exception
	 */
	public BaseEntityBean setBaseValues(BaseEntityBean entity) throws Exception {
		//String epmUserName = EPMSessionHandler.getUserName();
		try {
			if (entity.getId() != null && entity.getId() > 0) {
				//entity.setModifiedBy(epmUserName);
				entity.setModifiedDate(new Date());
			} else {
				//entity.setCreatedBy(epmUserName);
				entity.setCreatedDate(new Date());
				//entity.setModifiedBy(epmUserName);
				entity.setModifiedDate(new Date());
			}

		} catch (Exception e) {
			throw e;
		}
		return entity;
	}
}
