package com.indven.portal.administration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.administration.assembler.RoleMasterAssembler;
import com.indven.portal.administration.dao.RoleMasterDAOImpl;
import com.indven.portal.administration.entity.RoleMasterBean;
import com.indven.portal.administration.exception.AdministrationException;
import com.indven.portal.administration.service.RoleMasterServiceImpl;
import com.indven.portal.administration.vo.RoleMasterVO;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.exception.MenuInfoException;
import com.indven.portal.menu.service.MenuInfoServiceImpl;
import com.indven.portal.menu.vo.MenuMasterVO;

public class RoleMasterAction extends BaseAction {
	
	private static IndvenLogger logger = IndvenLogger.getInstance(RoleMasterAction.class);


	/**
	 * 
	 */
	private static final long serialVersionUID = 5479785369240748162L;
	RoleMasterVO roleMasterVO = new RoleMasterVO();
	FindAllResultVO<RoleMasterVO> objResult = new FindAllResultVO<RoleMasterVO>();
	IndvenResultVO indvenResultVO = new IndvenResultVO();

	/**
	 * @return the roleMasterVO
	 */
	public final RoleMasterVO getRoleMasterVO() {
		return roleMasterVO;
	}

	/**
	 * @param roleMasterVO
	 *            the roleMasterVO to set
	 */
	public final void setRoleMasterVO(RoleMasterVO roleMasterVO) {
		this.roleMasterVO = roleMasterVO;
	}
	
	/**
	 * saves the role information 
	 * @return status string
	 * @throws IOException
	 * @throws JSONException
	 */
	public String saveRoleInfo() throws IOException, JSONException {
		String status = ERROR;
		boolean isSave = false;
		if(roleMasterVO.getId() == 0){
			isSave = true;
		}
		RoleMasterBean roleMasterBean = RoleMasterAssembler
				.convertVoToEntity(roleMasterVO);
		MenuInfoServiceImpl menuInfoServiceImpl = new MenuInfoServiceImpl();
		MenuMasterVO menuMasterVO = new MenuMasterVO();
		String menuFkId = getRequest().getParameter("menuFkId");
		
		CustomBeanUtil.setBaseValues(roleMasterBean, false);
		try {
			
			//List of accessible menus for role
			List<AccessControlBean> accessControlBeans = menuInfoServiceImpl.createAccessControlBean(menuFkId, roleMasterBean.getId());

			RoleMasterServiceImpl roleServiceImpl = new RoleMasterServiceImpl();
			roleMasterBean.setIsDeleted(false);
			roleMasterVO = roleServiceImpl.saveRoleInfo(roleMasterBean, accessControlBeans);
			
			
			status = SUCCESS;
			if(isSave){
				roleMasterVO = new RoleMasterVO();
			}
			roleMasterVO.setMessage(IndvenMessageResolver.resolveMessage(AdministrationException.SAVE_SUCCESS_ROLE_MASTER, IndvenApplicationConstants.LOCALE));
			addActionMessage(roleMasterVO.getMessage());
			

		} catch (AdministrationException e) {
			roleMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(roleMasterVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(roleMasterVO.getMessage());
		} catch(MenuInfoException e) {
			roleMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(roleMasterVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(roleMasterVO.getMessage());
		}catch(Exception e) {
			logger.error(e);
			roleMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			addActionError(roleMasterVO.getMessage());
		}finally{
			//Set Menu Information in request
			if(roleMasterVO.getId() == 0) {
				menuMasterVO = new MenuInfoServiceImpl().findMenuInfo();
//				roleMasterVO = new RoleMasterVO();
			} else {
				try {
					menuMasterVO = new MenuInfoServiceImpl().getActiveMenuPrivilegeForRole(roleMasterVO.getId());
				} catch (MenuInfoException e) {
					roleMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
					new IndvenExceptionMessageResolver().resolveMessage(roleMasterVO, e, IndvenApplicationConstants.LOCALE);
					addActionError(roleMasterVO.getMessage());
				}
			}
			getRequest().setAttribute("menu", menuMasterVO);
		}
		
		
		return status;
	}

	public String findAll() {
		String status = ERROR;

		RoleMasterDAOImpl roleDAOImpl = new RoleMasterDAOImpl();
		List<RoleMasterVO> roleMasterVOsList = new ArrayList<RoleMasterVO>();
		
		try {
			roleMasterVOsList = RoleMasterAssembler.convertEntitiesToVos(roleDAOImpl.findAll());
			objResult.setListOfElemnents(roleMasterVOsList);
			objResult.setStatus(IndvenResultVO.STATUS_SUCCESS);
			status = SUCCESS;
		} catch (AdministrationException e) {
			roleMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(objResult, e,
					IndvenApplicationConstants.LOCALE);
			addActionError(roleMasterVO.getMessage());
		}

		return status;
	}
	public String findById() {
		String status = ERROR;
		RoleMasterServiceImpl roleMasterServiceImpl = new RoleMasterServiceImpl();
		MenuMasterVO menuMasterVO = new MenuMasterVO();
		try {
			roleMasterVO = roleMasterServiceImpl.findRoleById(Long.parseLong(getRequest().getParameter("id")));
			if(roleMasterVO != null) {
				//Gets the menu for a given role and sets it to request
				menuMasterVO = new MenuInfoServiceImpl().getActiveMenuPrivilegeForRole(roleMasterVO.getId());
				getRequest().setAttribute("menu", menuMasterVO);
				
				status = SUCCESS;
			} else {
				addActionError(IndvenResultVO.STATUS_FAILURE);
			} 
		} catch (AdministrationException re) {
			new IndvenExceptionMessageResolver().resolveMessage(roleMasterVO, re,
					IndvenApplicationConstants.LOCALE);
			addActionError(roleMasterVO.getMessage());
		} catch (MenuInfoException e) {
			menuMasterVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(menuMasterVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(menuMasterVO.getMessage());
		} 

		return status;
	}
	/**
	 * @return the objResult
	 */
	public final FindAllResultVO<RoleMasterVO> getObjResult() {
		return objResult;
	}

	/**
	 * @param objResult the objResult to set
	 */
	public final void setObjResult(FindAllResultVO<RoleMasterVO> objResult) {
		this.objResult = objResult;
	}
	
	/**
	 * This method will mark the Role Master as deleted by setting the Field
	 * isDeleted as true.
	 * 
	 * @param id
	 *            The id of the record which will be modified by setting the
	 *            isDeleted field as true.
	 * @return returns object of EPMResultVO which contains either status as
	 *         success or failure.
	 */
	public String markedAsDeleted() {
		String status = ERROR;
		RoleMasterDAOImpl roleMasterDAOImpl = new RoleMasterDAOImpl();
		try {
			long id = Long.parseLong(getRequest().getParameter("id")); 
			int deletesta = roleMasterDAOImpl.markedAsDeleted(id);
			if(deletesta==1){
				indvenResultVO.setStatus(IndvenResultVO.STATUS_SUCCESS);
				indvenResultVO.setMessage(IndvenMessageResolver.resolveMessage(
					AdministrationException.DELETE_SUCCESS_FOR_ROLE_MASTER,
					IndvenApplicationConstants.LOCALE));
				
				addActionMessage(indvenResultVO.getMessage());
				status = SUCCESS;
			}
			findAll();
		} catch (AdministrationException re) {
			new IndvenExceptionMessageResolver().resolveMessage(indvenResultVO, re,
					IndvenApplicationConstants.LOCALE);
			addActionError(indvenResultVO.getMessage());
		}
		return status;
	}
	
	
}
