/**
 * 
 */
package com.indven.portal.administration.assembler;

import java.util.ArrayList;
import java.util.List;

import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.vo.RoleMasterVO;

/**
 * @author Sandeep solomon Kunder
 *
 */
public class RoleMasterAssembler {

	public static RoleMasterVO convertEntityToVo(RoleMasterBean roleMasterBean) {
		RoleMasterVO roleMasterVO = new RoleMasterVO();
		roleMasterVO.setId(roleMasterBean.getId());
		roleMasterVO.setName(roleMasterBean.getName());
		roleMasterVO.setDescription(roleMasterBean.getDescription());
		roleMasterVO.setRowVersion(roleMasterBean.getRowVersion());
		return roleMasterVO;
	}
	
	public static RoleMasterBean convertVoToEntity(RoleMasterVO roleMasterVO) {
		RoleMasterBean roleMasterBean = new RoleMasterBean();
		roleMasterBean.setId(roleMasterVO.getId());
		roleMasterBean.setName(roleMasterVO.getName());
		roleMasterBean.setDescription(roleMasterVO.getDescription());
		roleMasterBean.setRowVersion(roleMasterVO.getRowVersion());
		return roleMasterBean;
	}
	
	/**
	 * This method is used to convert the List of valueObjects to List of Entities.
	 * 
	 * @param valueObjects
	 *            The List of objects of RoleMasterVOs.
	 * @return returns the List object containing RoleMasterBean objects which we get after converting RoleMasterVO objects to
	 *         RoleMasterBean objects.
	 */
	public static List<RoleMasterBean> convertVosToEntities(List<RoleMasterVO> roleMasterVOs) {
		List<RoleMasterBean> roleMasterBeanBeans = new ArrayList<RoleMasterBean>();
		for (RoleMasterVO roleMasterVO : roleMasterVOs) {
			roleMasterBeanBeans.add(convertVoToEntity(roleMasterVO));
		}
		return roleMasterBeanBeans;
	}

	/**
	 * This method is used to convert the List of Entities to List of valueObjects.
	 * 
	 * @param entities
	 *            The List of objects of RoleMasterBeans.
	 * @return returns the List object containing RoleMasterVO objects which we get after converting RoleMasterBean objects to
	 *         RoleMasterVO objects.
	 */
	public static List<RoleMasterVO> convertEntitiesToVos(List<RoleMasterBean> roleMasterBeans) {
		List<RoleMasterVO> roleMasterVOs = new ArrayList<RoleMasterVO>();

		for (RoleMasterBean roleMasterBean : roleMasterBeans) {
			roleMasterVOs.add(convertEntityToVo(roleMasterBean));
		}

		return roleMasterVOs;
	}


	
	
}
