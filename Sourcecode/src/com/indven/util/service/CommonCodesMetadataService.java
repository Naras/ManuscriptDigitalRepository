/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CommonCodesMetadataService.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	11th April 2013
 * Brief Description					:	This class will be used to perform all the Common codes meta data DB operations
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.util.service;

import java.util.ArrayList;
import java.util.List;

import com.indven.util.dao.CommonCodesMetadataDAOImpl;
import com.indven.util.entity.CommonCodesMetadataBean;
import com.indven.util.exception.UtilException;

/**
 * @author Saurabh
 *
 */
public class CommonCodesMetadataService {
	private CommonCodesMetadataDAOImpl commonCodesMetadataDAOImpl = new CommonCodesMetadataDAOImpl();

	public CommonCodesMetadataBean save(CommonCodesMetadataBean commonCodesMetadataBean) {
		try {

			commonCodesMetadataDAOImpl.save(commonCodesMetadataBean);
		} catch (UtilException e) {

		}
		return commonCodesMetadataBean;
	}

	public CommonCodesMetadataBean findById(Long id) {
		CommonCodesMetadataBean commonCodesMetadataBean = null;
		try {

			commonCodesMetadataBean = commonCodesMetadataDAOImpl.findById(id);
		} catch (UtilException e) {

		}
		return commonCodesMetadataBean;
	}

	public List<CommonCodesMetadataBean> findAll() {
		List<CommonCodesMetadataBean> commonCodesMetadataList = new ArrayList<CommonCodesMetadataBean>();

		try {
			commonCodesMetadataList = commonCodesMetadataDAOImpl.findAll();
		} catch (UtilException e) {
			// TODO: handle exception
		}

		return commonCodesMetadataList;
	}

	public CommonCodesMetadataBean update(CommonCodesMetadataBean commonCodesMetadataBean) {
		try {

			commonCodesMetadataDAOImpl.update(commonCodesMetadataBean);
		} catch (UtilException e) {

		}
		return commonCodesMetadataBean;
	}

	public void purge(CommonCodesMetadataBean commonCodesMetadataBean) {
		try {

			commonCodesMetadataDAOImpl.purge(commonCodesMetadataBean);
		} catch (UtilException e) {

		}
	}
}
