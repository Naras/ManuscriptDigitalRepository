/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuInfoServiceImpl.java
 * Project Name							:	WASP
 * Date of Creation						: 	19th Aug 2013
 * Brief Description					:	This class will be used to perform all the MenuInfo service operations
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.portal.menu.service;

import java.util.List;

import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.menu.assembler.RoleMasterMenuInfoAssembler;
import com.indven.portal.menu.dao.RoleMasterMenuInfoDAOImpl;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.exception.MenuInfoException;
import com.indven.portal.menu.vo.RoleMasterMenuInfoVO;

/**
 * This class will be used to perform all the MenuInfo service operations
 * 
 * @author Saurabh
 * 
 */
public class RoleMasterMenuInfoServiceImpl  {

	private RoleMasterMenuInfoAssembler roleMasterMenuInfoAssembler = new RoleMasterMenuInfoAssembler();
	private RoleMasterMenuInfoDAOImpl roleMasterMenuInfoDAOImpl = new RoleMasterMenuInfoDAOImpl();
	private IndvenResultVO result = new IndvenResultVO();


	public IndvenResultVO save(RoleMasterMenuInfoVO roleMasterMenuInfoVO) {
		return roleMasterMenuInfoVO;

	}

	public IndvenResultVO saveMenusForRole(List<RoleMasterMenuInfoVO> roleMasterMenuInfoVOs) {

		List<AccessControlBean> roleMasterMenuInfoBeans = null;
		try {
			roleMasterMenuInfoBeans = roleMasterMenuInfoAssembler.convertVosToEntities(roleMasterMenuInfoVOs);
			if (roleMasterMenuInfoBeans.size() > 0) {
				roleMasterMenuInfoDAOImpl.purge(roleMasterMenuInfoBeans.get(0).getRoleMasterFkId());
			}
			roleMasterMenuInfoDAOImpl.save(roleMasterMenuInfoBeans);
			result.setStatus(IndvenResultVO.STATUS_SUCCESS);



		} catch (MenuInfoException mie) {
			new IndvenExceptionMessageResolver().resolveMessage(result, mie, IndvenApplicationConstants.LOCALE);
		}

		return result;
	}

}
