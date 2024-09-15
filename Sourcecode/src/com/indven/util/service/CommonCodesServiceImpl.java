/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CommonCodesServiceImpl.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	This class will be used to perform all the Common codes DB operations
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.util.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import com.indven.framework.exception.IndvenException;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.vo.LabelValueVO;
import com.indven.util.dao.CommonCodesDAOImpl;
import com.indven.util.entity.CommonCodesBean;
import com.indven.util.exception.UtilException;

/**
 * @author Saurabh
 * 
 */
public class CommonCodesServiceImpl {

	private CommonCodesDAOImpl commonCodesDAOImpl = new CommonCodesDAOImpl();

	public CommonCodesBean save(CommonCodesBean commonCodesBean) {
		try {

			commonCodesDAOImpl.save(commonCodesBean);
		} catch (UtilException e) {
			// TODO: handle exception
		}
		return commonCodesBean;
	}

	public CommonCodesBean findById(Long id) {
		CommonCodesBean commonCodesBean = null;
		try {

			commonCodesBean = commonCodesDAOImpl.findById(id);
		} catch (UtilException e) {
			// TODO: handle exception
		}
		return commonCodesBean;
	}

	public List<CommonCodesBean> findAll() {
		List<CommonCodesBean> commonCodesList = new ArrayList<CommonCodesBean>();

		try {
			commonCodesList = commonCodesDAOImpl.findAll();
		} catch (UtilException e) {
			// TODO: handle exception
		}

		return commonCodesList;
	}
	
	
	public List<LabelValueVO> findAllForCombo( long type) throws UtilException {
		List<LabelValueVO> commonCodesVOs = new ArrayList<LabelValueVO>();
		
		try {
			List<CommonCodesBean> commonCodesBeanBeans = commonCodesDAOImpl.findAllForCombo(type);
			CommonCodesBean obj = null;
			for(Iterator<CommonCodesBean> i = commonCodesBeanBeans.iterator(); i.hasNext();) {
				obj = i.next();				
				commonCodesVOs.add(new LabelValueVO( obj.getName(),String.valueOf( obj.getId() ) ) );
				obj = null;
			}
			
		} catch (HibernateException e) {
			throw new UtilException(UtilException.UNABLE_TO_RETRIEVE_DATA_FOR_COMBO, e);
		} catch (Exception e) {
			throw new UtilException(UtilException.UNABLE_TO_RETRIEVE_DATA_FOR_COMBO, e);
		}
		
		return commonCodesVOs;
	}


	public CommonCodesBean update(CommonCodesBean commonCodesBean) {
		try {

			commonCodesDAOImpl.update(commonCodesBean);
		} catch (UtilException e) {
			// TODO: handle exception
		}
		return commonCodesBean;
	}

	public void purge(CommonCodesBean commonCodesBean) {
		try {

			commonCodesDAOImpl.purge(commonCodesBean);
		} catch (UtilException e) {
			// TODO: handle exception
		}
	}
}
