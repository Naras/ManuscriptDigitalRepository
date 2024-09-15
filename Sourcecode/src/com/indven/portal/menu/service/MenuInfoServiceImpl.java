/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuInfoServiceImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	19th AUG 2013
 * Brief Description					:	This class will be used to perform all the MenuInfo service operations
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.portal.menu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.menu.assembler.MenuInfoAssembler;
import com.indven.portal.menu.dao.MenuInfoDAOImpl;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.exception.MenuInfoException;
import com.indven.portal.menu.vo.MenuMasterVO;

/**
 * This class will be used to perform all the MenuInfo service operations
 * 
 * @author Saurabh
 * 
 */
public class MenuInfoServiceImpl {

	private MenuInfoDAOImpl menuInfoDAOImpl = new MenuInfoDAOImpl();
	private MenuInfoAssembler menuInfoAssembler = new MenuInfoAssembler();
	private IndvenResultVO result = new IndvenResultVO();

	public MenuInfoServiceImpl() {

	}

	public IndvenResultVO save(MenuMasterVO valueObject) {
		return result;
	}
	
	public String saveMenuInfo(List<AccessControlBean> accessControlBeans) throws MenuInfoException {
		MenuInfoDAOImpl menuDAO = new MenuInfoDAOImpl();
		menuDAO.saveMenuInfo(accessControlBeans);
		return "error";
	}

	public MenuMasterVO findById(Long id) {

		MenuMasterVO menuInfoVO = new MenuMasterVO();

		return menuInfoVO;
	}


	public FindAllResultVO<MenuMasterVO> findAll() {
		FindAllResultVO<MenuMasterVO> objResult = new FindAllResultVO<MenuMasterVO>();
		List<MenuMasterVO> menuInfoVOsList = new ArrayList<MenuMasterVO>();
		MenuMasterVO menuInfoVO = new MenuMasterVO();
		try {
			menuInfoVOsList = menuInfoAssembler.convertEntitiesToVos(menuInfoDAOImpl.findAll());
			objResult.setListOfElemnents(menuInfoVOsList);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);
		} catch (MenuInfoException mie) {

			new IndvenExceptionMessageResolver().resolveMessage(menuInfoVO, mie, IndvenApplicationConstants.LOCALE);
		}

		return objResult;
	}

	/**
	 * This method is used to get list of all the menus.This list will be used to assign to roles 
	 * 
	 * @return a String of MenuMasterVO object data
	 * 
	 */
	public MenuMasterVO findMenuInfo() {
		MenuMasterVO menuMasterVO = null;
		try {

			menuMasterVO =  menuInfoDAOImpl.findMenuInfo();

		} catch (MenuInfoException mie) {
			new IndvenExceptionMessageResolver().resolveMessage(result, mie, IndvenApplicationConstants.LOCALE);
		}
		return menuMasterVO;
	}
	
	/**
	 * Creates a list of all menus 
	 * Also contains Checked data for determining if menu is valid for given role
	 * @return MenuMasterVO object data
	 * @throws MenuInfoException 
	 */
	public MenuMasterVO getActiveMenuPrivilegeForRole(Long id) throws MenuInfoException {
		MenuMasterVO menuMasterVO = null;
		menuMasterVO =  menuInfoDAOImpl.getActiveMenuPrivilegeForRole(id);
		return menuMasterVO;
	}
	
	/**
	 * Generates a list of accessible menus for the given role
	 * Receives a JSON object as input
	 * @param menuNumber
	 * @param roleFkId
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public List<AccessControlBean> createAccessControlBean(String menuNumber, Long roleFkId) throws IOException, JSONException, Exception {
		
		JSONArray jsonArray = null;
		System.out.println(roleFkId);
		List<AccessControlBean> accessControlBeans = new ArrayList<AccessControlBean>();
		
		jsonArray = new JSONArray(menuNumber);
		
		
		for(int i=0;i<jsonArray.length();i++){

			JSONObject jsonObj = new JSONObject(jsonArray.get(i).toString());
			
			AccessControlBean accessControlBean = new AccessControlBean();

			accessControlBean.setMenuMasterFkId(Long.valueOf(jsonObj.getString("menuFkId")).longValue());
			accessControlBean.setRoleMasterFkId(roleFkId);
			
			accessControlBeans.add(accessControlBean);
		}
		return accessControlBeans;
	}
}
