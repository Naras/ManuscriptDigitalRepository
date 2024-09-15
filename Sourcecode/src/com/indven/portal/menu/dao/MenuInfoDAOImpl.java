/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	MenuInfoDAOImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	20th Aug 2013
 * Brief Description					:	This class will be used to perform all the Menu Info
 *                                          DB operations
 * Author								: 	saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.menu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.indven.framework.dao.GenericBaseCRUDDAOImpl;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.portal.menu.entity.AccessControlBean;
import com.indven.portal.menu.entity.MenuMasterBean;
import com.indven.portal.menu.exception.MenuInfoException;
import com.indven.portal.menu.vo.MenuMasterVO;

/**
 * This class will be used to perform all the MenuInfo Master DB operations
 * 
 * @author Saurabh
 * 
 */
public class MenuInfoDAOImpl extends GenericBaseCRUDDAOImpl<MenuMasterBean> {

	private static IndvenLogger logger = IndvenLogger.getInstance(MenuInfoDAOImpl.class);

	public MenuInfoDAOImpl() {
		super(MenuMasterBean.class);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#getEntityBeanType()
	 */
	@Override
	public Class<MenuMasterBean> getEntityBeanType() {

		return super.getEntityBeanType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#save(java.lang.Object)
	 */
	@Override
	public MenuMasterBean save(MenuMasterBean entity) throws MenuInfoException {

		try {
			return super.save(entity);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#update(java.lang.Object)
	 */
	@Override
	public MenuMasterBean update(MenuMasterBean entity) throws MenuInfoException {

		try {
			return super.update(entity);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_UPDATE_MENUINFO, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	@Override
	public MenuMasterBean findById(Long id) throws MenuInfoException {

		try {
			return super.findById(id);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#delete(java.lang.Object)
	 */
	@Override
	public void purge(MenuMasterBean entity) throws MenuInfoException {

		try {
			super.purge(entity);
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_DELETE_MENUINFO_DETAILS, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findAll()
	 */
	@Override
	public List<MenuMasterBean> findAll() throws MenuInfoException {

		try {
			return super.findAll();
		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_LIST_ALL_MENUINFOS, e);
		}

	}



	/**
	 * This method is used to get list of all the menus.This list will be used to assign to roles
	 * 
	 * @return a String of MenuMasterVO object data
	 * 
	 */

	public MenuMasterVO findMenuInfo() throws MenuInfoException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			MenuMasterVO root = new MenuMasterVO();
			root.setId(IndvenApplicationConstants.EPM_HIERARCHY_ROOT_ID);

			createMapForRole(session,root, IndvenApplicationConstants.EPM_HIERARCHY_ROOT_ID);

			return root;

		} catch (MenuInfoException mie) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, mie);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, mie);
		}

	}
	
	
	/**
	 * DAO which returns map of all roles
	 * Includes current checked/unchecked status
	 * 
	 * @return MenuMasterVO object data
	 */
	public MenuMasterVO getActiveMenuPrivilegeForRole(Long id) throws MenuInfoException {
		
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			MenuMasterVO root = new MenuMasterVO();
			root.setId(IndvenApplicationConstants.EPM_HIERARCHY_ROOT_ID);
			
			List<AccessControlBean> menuForRole = new ArrayList<AccessControlBean>();
			menuForRole = getMenusForRole(session, id);

			getMapPrivilegeForRole(session,root, IndvenApplicationConstants.EPM_HIERARCHY_ROOT_ID, menuForRole);

			return root;

		} catch (MenuInfoException mie) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, mie);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, mie);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AccessControlBean> getMenusForRole(Session session, Long id) {
		List<AccessControlBean> accessControlBeans = new ArrayList<AccessControlBean>();
		try {
			accessControlBeans = session.createSQLQuery("select * from omds_accesscontrol ac where ac.RoleMasterFkId =" + id.longValue())
					.addEntity(AccessControlBean.class).list();

		} catch(Exception e) {
			e.printStackTrace();
		}
		return accessControlBeans;
	}
	
	@SuppressWarnings("unchecked")
	public static MenuMasterVO  createMapForRole(Session session, MenuMasterVO root,  Long parentId)
			throws MenuInfoException {

		// constant defined here
		List<MenuMasterBean> childMenus = null;
		MenuMasterBean objMenuInfoBeanTemp = null;

		try {

			childMenus = session.createSQLQuery("SELECT *  FROM omds_menumaster mi  WHERE mi.ParentId =" + parentId + " ORDER BY mi.MenuOrder ASC,mi.MenuLevel ASC")
					.addEntity(MenuMasterBean.class).list();
			for (ListIterator<MenuMasterBean> iterMenuInfoBeans = childMenus.listIterator(); iterMenuInfoBeans.hasNext();) {

				objMenuInfoBeanTemp = iterMenuInfoBeans.next();
				List<MenuMasterVO> child =  root.getChild();
				MenuMasterVO childElement = new MenuMasterVO();
				childElement.setId(objMenuInfoBeanTemp.getId());
				childElement.setParentId(objMenuInfoBeanTemp.getParentId());
				childElement.setMenuName(objMenuInfoBeanTemp.getMenuName());
				childElement.setMenuLink(objMenuInfoBeanTemp.getMenuLink());
				childElement.setMenuLevel(objMenuInfoBeanTemp.getMenuLevel());
				childElement.setMenuOrder(objMenuInfoBeanTemp.getMenuOrder());
				childElement.setDefaultStatus(objMenuInfoBeanTemp.getDefaultStatus());
				childElement.setChecked(false);
				
				child.add(childElement);
				
				createMapForRole(session, childElement, objMenuInfoBeanTemp.getId());

			}

		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		}

		return root;
	}
	
	/**
	 * Creates a list of all elements within the table
	 * Has previous checked/unchecked status
	 * 
	 * @return MenuMasterVO Object data
	 */
	@SuppressWarnings("unchecked")
	public static MenuMasterVO  getMapPrivilegeForRole(Session session, MenuMasterVO root,  Long parentId, List<AccessControlBean> menuForRole) throws MenuInfoException {
		
		// constant defined here
		List<MenuMasterBean> childMenus = null;
		MenuMasterBean objMenuInfoBeanTemp = null;
		AccessControlBean objAccessControlBeanTemp = null;
		try {

			childMenus = session.createSQLQuery("SELECT *  FROM omds_menumaster mi  WHERE mi.ParentId =" + parentId + " ORDER BY mi.MenuOrder ASC,mi.MenuLevel ASC").addEntity(MenuMasterBean.class).list();
			for (ListIterator<MenuMasterBean> iterMenuInfoBeans = childMenus.listIterator(); iterMenuInfoBeans.hasNext();) {

				objMenuInfoBeanTemp = iterMenuInfoBeans.next();
				List<MenuMasterVO> child =  root.getChild();
				MenuMasterVO childElement = new MenuMasterVO();
				childElement.setId(objMenuInfoBeanTemp.getId());
				childElement.setParentId(objMenuInfoBeanTemp.getParentId());
				childElement.setMenuName(objMenuInfoBeanTemp.getMenuName());
				childElement.setMenuLink(objMenuInfoBeanTemp.getMenuLink());
				childElement.setMenuLevel(objMenuInfoBeanTemp.getMenuLevel());
				childElement.setMenuOrder(objMenuInfoBeanTemp.getMenuOrder());
				childElement.setDefaultStatus(objMenuInfoBeanTemp.getDefaultStatus());

				int hashRequestId = (objMenuInfoBeanTemp.getRequestId() + new Date().getTime()).hashCode();
				childElement.setRequestId(String.valueOf(hashRequestId));
				childElement.setChecked(false);
				for (ListIterator<AccessControlBean> iterAccessControlBean = menuForRole.listIterator(); iterAccessControlBean.hasNext();) {
					objAccessControlBeanTemp = iterAccessControlBean.next();
					if(objMenuInfoBeanTemp.getId().equals(objAccessControlBeanTemp.getMenuMasterFkId())) {
						childElement.setChecked(true);
					}
				}
				
				
				child.add(childElement);
				
				getMapPrivilegeForRole(session, childElement, objMenuInfoBeanTemp.getId(), menuForRole);

			}

		} catch (HibernateException e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		} catch (Exception e) {
			logger.error(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_FIND_MENUINFO_DETAILS, e);
		}

		return root;
	}
	
	public String saveMenuInfo(List<AccessControlBean> accessControlBeans) throws MenuInfoException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx=null;	
		try {
			tx = session.beginTransaction();
			if(accessControlBeans.size() > 0) {
				Query query1 = session.createQuery("delete from AccessControlBean accessControlBean where accessControlBean.roleMasterFkId = :roleMasterFkId");
				query1.setParameter("roleMasterFkId", accessControlBeans.get(0).getRoleMasterFkId());
	   			query1.executeUpdate();
	   			for (AccessControlBean accessControlBean : accessControlBeans) {
	   				session.save(accessControlBean);
	   			}
			}
   			
   			tx.commit();
   			
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);

		} catch (Exception e) {
			tx.rollback();
			logger.error(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
			throw new MenuInfoException(MenuInfoException.UNABLE_TO_SAVE_MENUINFO, e);
		}finally {
			session.close();
		}
		
		return "error";
	}

}
