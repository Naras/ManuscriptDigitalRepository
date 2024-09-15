/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	BaseCRUDDAO.java
 * Project Name							:	EPM
 * Date of Creation						: 	22nd December 2011
 * Brief Description					:	This is the Basic CRUD operation DAO Interface which will be implemented by BaseCRUDDAOImpl class and 											 
 * 											basic CRUD methods are declared here
 * Author								: 	Yusuf Pasha and Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.indven.framework.entity.BaseEntityBean;

/**
 * This is the Basic CRUD operation DAO Interface which will be implemented by BaseCRUDDAOImpl class
 * and basic CRUD methods are declared here
 * 
 * @author Yusuf
 * 
 */
public interface BaseCRUDDAO<T extends BaseEntityBean> {

	public abstract T save(T entity) throws HibernateException, Exception;

	public abstract T update(T entity) throws HibernateException, Exception;

	public abstract T findById(Long id) throws HibernateException, Exception;

	public abstract Class<T> getEntityBeanType();

	public abstract void purge(T entity) throws HibernateException, Exception;

	public abstract List<T> findAll() throws HibernateException, Exception;

}
