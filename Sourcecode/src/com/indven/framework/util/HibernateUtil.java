/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	HibernateUtil.java
 * Project Name							:	EPM
 * Date of Creation						: 	22nd December 2011
 * Brief Description					:	This is the Singleton Class to create One Session Factory
 * Author								: 	Yusuf Pasha and Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.util;

import java.util.ResourceBundle;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * This is the Singleton Class to create One Session Factory. 
 * 
 * @author Yusuf
 * 
 */
/**
 * In this Class set the url, username and password by getting from epmhibernate.properties file for
 * setting the url, username and password dynamically according to properties file
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new AnnotationConfiguration().configure();
			configuration.setProperty("hibernate.connection.url",
					ResourceBundle.getBundle("projecthibernate").getString("hibernate.connection.url"));
			configuration.setProperty("hibernate.connection.username",
					ResourceBundle.getBundle("projecthibernate").getString("hibernate.connection.username"));
			configuration.setProperty("hibernate.connection.password",
					ResourceBundle.getBundle("projecthibernate").getString("hibernate.connection.password"));
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;

	}
}
