/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	BaseEntityCRUDService.java
 * Project Name							:	EPM
 * Date of Creation						: 	23rd December 2011
 * Brief Description					:	This is the Base Entity CRUD Service Interface which will be implemented by all Entity Service Implementation classes and 											 
 * 											basic CRUD methods are declared here
 * Author								: 	Yusuf Pasha and Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.service;

import java.util.List;

import com.indven.framework.exception.IndvenException;
import com.indven.framework.vo.IndvenResultVO;


/**
 * @author Yusuf
 * 
 */
public interface BaseEntityCRUDService<T extends IndvenResultVO> {

	public abstract T save(T valueObject) throws IndvenException;

	public abstract T findById(Long id) throws IndvenException;

	public abstract List<T> findAll() throws IndvenException;

	public abstract T update(T valueObject) throws IndvenException;

	public abstract void purge(T valueObject);

}
