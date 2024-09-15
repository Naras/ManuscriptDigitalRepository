/************************************************************************************************************************
 * Copyright Information			:	Indiatech Ventures Pvt. Ltd.
 * File Name						:	MenuMasterAssembler.java
 * Project Name						:	WASP
 * Date of Creation					: 	19th AUG 2013
 * Brief Description				:	This test is used for MenuMaster conversion operations from valueObject to Entity
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
 * @author Saurabh
 *
 */
public class MenuMasterAssembler {

	public static MenuMasterVO convertEntityToVo(MenuMasterBean menuMasterBean) {
		MenuMasterVO menuMasterVO = new MenuMasterVO();

		menuMasterVO.setId(menuMasterBean.getId());
		menuMasterVO.setParentId(menuMasterBean.getParentId());
		menuMasterVO.setMenuName(menuMasterBean.getMenuName());	
		menuMasterVO.setMenuLink(menuMasterBean.getMenuLink());
		menuMasterVO.setLeftPanelLink(menuMasterBean.getLeftPanelLink());	
		menuMasterVO.setMenuOrder(menuMasterBean.getMenuOrder());
		menuMasterVO.setRequestId(menuMasterBean.getRequestId());
		menuMasterVO.setStatusMsg(menuMasterBean.getStatusMsg());
		menuMasterVO.setShortKey(menuMasterBean.getShortKey());
		menuMasterVO.setMenuLevel(menuMasterBean.getMenuLevel());
		menuMasterVO.setDefaultStatus(menuMasterBean.getDefaultStatus());

		return menuMasterVO;
	}
	

	public static List<MenuMasterVO> convertEntitiesToVos(List<MenuMasterBean> entities) {
		List<MenuMasterVO> menuMasterVOs = new ArrayList<MenuMasterVO>();

		for (MenuMasterBean menuMasterBean : entities) {
			menuMasterVOs.add(convertEntityToVo(menuMasterBean));
		}

		return menuMasterVOs;
	}
	
}
