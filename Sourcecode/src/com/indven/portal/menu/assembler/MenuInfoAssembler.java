/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	MenuInfoAssembler.java
 * Project Name						:	WASP
 * Date of Creation					: 	19th AUG 2013
 * Brief Description				:	This test is used for MenuInfo conversion operations from valueObject to Entity
 * Author							: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.menu.assembler;

import java.util.ArrayList;
import java.util.List;

import com.indven.portal.menu.entity.MenuMasterBean;
import com.indven.portal.menu.vo.MenuMasterVO;

/**
 * * This class will be used to perform all the MenuInfo conversion operations from valueObject to
 * Entity and entity to VO
 * 
 * @author Saurabh
 * 
 */
public class MenuInfoAssembler  {

	/**
	 * This method is used to convert the Entity to VO.
	 */
	
	public MenuMasterVO convertEntityToVo(MenuMasterBean menuInfoBean) {

		MenuMasterVO menuInfoVO = new MenuMasterVO();

		menuInfoVO.setId(menuInfoBean.getId());
		menuInfoVO.setMenuName(menuInfoBean.getMenuName());
		menuInfoVO.setMenuOrder(menuInfoBean.getMenuOrder());
		menuInfoVO.setMenuLevel(menuInfoBean.getMenuLevel());
		menuInfoVO.setMenuLink(menuInfoBean.getMenuLink());
		menuInfoVO.setParentId(menuInfoBean.getParentId());

		return menuInfoVO;
	}

	/**
	 * This method is used to convert vo to entity
	 */
	
	public MenuMasterBean convertVoToEntity(MenuMasterVO menuInfoVO) {

		MenuMasterBean menuInfoBean = new MenuMasterBean();

		menuInfoBean.setId(menuInfoVO.getId());
		menuInfoBean.setMenuName(menuInfoVO.getMenuName());
		menuInfoBean.setMenuOrder(menuInfoVO.getMenuOrder());
		menuInfoBean.setMenuLevel(menuInfoVO.getMenuLevel());
		menuInfoBean.setMenuLink(menuInfoVO.getMenuLink());
		menuInfoBean.setParentId(menuInfoVO.getParentId());

		return menuInfoBean;
	}

	/**
	 * This method is used to convert the List of valueObjects to List of Entities.
	 */
	
	public List<MenuMasterBean> convertVosToEntities(List<MenuMasterVO> valueObjects) {
		List<MenuMasterBean> menuInfoBeans = new ArrayList<MenuMasterBean>();

		for (MenuMasterVO menuInfoVO : valueObjects) {
			menuInfoBeans.add(convertVoToEntity(menuInfoVO));
		}

		return menuInfoBeans;
	}

	/**
	 * This method is used to convert the List of Entities to List of valueObjects.
	 */

	public List<MenuMasterVO> convertEntitiesToVos(List<MenuMasterBean> entities) {
		List<MenuMasterVO> menuInfoVOs = new ArrayList<MenuMasterVO>();

		for (MenuMasterBean menuInfoBean : entities) {
			menuInfoVOs.add(convertEntityToVo(menuInfoBean));
		}

		return menuInfoVOs;
	}

}
