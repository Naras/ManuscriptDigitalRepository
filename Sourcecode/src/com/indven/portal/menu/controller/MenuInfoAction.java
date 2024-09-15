package com.indven.portal.menu.controller;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.menu.exception.MenuInfoException;
import com.indven.portal.menu.service.MenuInfoServiceImpl;
import com.indven.portal.menu.vo.MenuMasterVO;
import com.indven.portal.menu.vo.RoleMasterMenuInfoVO;

public class MenuInfoAction extends BaseAction {

	/**
	 * Author Neel Borooah
	 */
	private static final long serialVersionUID = -325782620897791924L;
	
	public List<RoleMasterMenuInfoVO> displayRoleInfo() {

		List<RoleMasterMenuInfoVO> list = new ArrayList<RoleMasterMenuInfoVO>();
		return list;
	
	}
	
	/**
	 * Finds list of menus and stores it to request
	 * @return
	 */
	public String findMenuInfo() {
		String status = ERROR;
		MenuMasterVO menuMasterVO = new MenuMasterVO();
		try {
			String id = getRequest().getParameter("id");
			MenuInfoServiceImpl menuInfoServiceImpl = new MenuInfoServiceImpl();
			if(id == null) 
				menuMasterVO = menuInfoServiceImpl.findMenuInfo();
			else
				menuMasterVO = menuInfoServiceImpl.getActiveMenuPrivilegeForRole(new Long(id));
			
			getRequest().setAttribute("menu", menuMasterVO);
			status = SUCCESS;
			
		} catch (MenuInfoException e) {
			menuMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(menuMasterVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(menuMasterVO.getMessage());
		}
		return status;
	}
	
}
