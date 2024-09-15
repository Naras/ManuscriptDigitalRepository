/**
 * 
 */
package com.indven.portal.hrd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.dao.BaseCRUDDAOImpl;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.hrd.exception.HumanResourceException;

/**
 * @author Deba Prasad
 *
 */
public class EmployeeMasterDAOImpl extends BaseCRUDDAOImpl<EmployeeMasterBean>{
	
	private static IndvenLogger logger = IndvenLogger.getInstance(EmployeeMasterDAOImpl.class);

	public EmployeeMasterDAOImpl() {
		super(EmployeeMasterBean.class);
	}
	
	/* (non-Javadoc)
	 * @see com.indven.framework.dao.BaseCRUDDAOImpl#findById(java.lang.Long)
	 */
	@Override
	public EmployeeMasterBean findById(Long id) throws HumanResourceException {
			try{
				return super.findById(id);
			} catch (HibernateException e) {
				logger.error(HumanResourceException.UNABLE_TO_SEARCH_EMPLOYEE, e);
				throw new HumanResourceException(HumanResourceException.UNABLE_TO_SEARCH_EMPLOYEE, e);

			} catch (Exception e) {
				logger.error(HumanResourceException.UNABLE_TO_SEARCH_EMPLOYEE, e);
				throw new HumanResourceException(HumanResourceException.UNABLE_TO_SEARCH_EMPLOYEE, e);
			} 
	}
	
	/**
	 * If the employee does not exist in DB, creates a new record
	 * Else updates existing employee record
	 * @param employeeMasterBean
	 * @param userBean
	 * @param userRoleDetailsBean
	 * @return
	 * @throws HumanResourceException
	 */
	@SuppressWarnings("unchecked")
	public EmployeeMasterBean saveOrUpdateEmployee(EmployeeMasterBean employeeMasterBean , UserLoginDetailsBean userBean, 
		UserRoleDetailsBean userRoleDetailsBean , List<UserRoleDetailsBean> listOfUserRoles)  throws HumanResourceException{
		Session session = null;
		Transaction tx = null;
		Query query = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Long loginId = null;
			List<UserRoleDetailsBean> lurList = new ArrayList<>();
			List<Long> lurIdlist = new ArrayList<>();
			/*if(employeeMasterBean.getId() == null)
				session.save(employeeMasterBean);
			else {
				Query query = session.createQuery("update EmployeeMasterBean emp set "
						+ "emp.firstName = :firstName, emp.lastName = :lastName, emp.phoneNumber = :phoneNumber, "
						+ "emp.address1 = :address1, emp.gender = :gender "
						+ "where emp.id = :id");
				query.setParameter("id", employeeMasterBean.getId());
				query.setParameter("firstName", employeeMasterBean.getFirstName());
				query.setParameter("lastName", employeeMasterBean.getLastName());
				query.setParameter("phoneNumber", employeeMasterBean.getPhoneNumber());
				query.setParameter("address1", employeeMasterBean.getAddress1());
				query.setParameter("gender", employeeMasterBean.getGender());
				query.executeUpdate();
			}*/
			if(employeeMasterBean.getId() != null && employeeMasterBean.getId() > 0) {
				query = session.createQuery("from UserLoginDetailsBean bean where bean.refrenceFkId = :empId");
				query.setParameter("empId", employeeMasterBean.getId());
				loginId = ((UserLoginDetailsBean)query.uniqueResult()).getRefrenceFkId();
				
				
				query = session.createQuery("select bean.roleMasterFkId from UserRoleDetailsBean bean where bean.userLoginDetailsFkId = :userloginid");
				query.setParameter("userloginid", loginId);
				lurIdlist = query.list();
				
				/*
				query = session.createQuery("delete from UserRoleDetailsBean urd where urd.userLoginDetailsFkId = :userloginid");
				query.setParameter("userloginid", loginId);
				int count = query.executeUpdate();*/
			}
			
			session.saveOrUpdate(employeeMasterBean);
			
			if(userBean != null) {
				userBean.setRefrenceFkId(employeeMasterBean.getId());
				
				query = session.createQuery("From UserLoginDetailsBean uldb where uldb.loginId = :loginId");
				query.setParameter("loginId", userBean.getLoginId());
				
				if(query.list()!=null && query.list().size()>0){
					throw new HumanResourceException(HumanResourceException.VOILATE_UNIQUE_EMAIL_CONSTRAINT);
				}
				session.save(userBean);
			}
			
//			if(userRoleDetailsBean != null) {
//				if(userRoleDetailsBean.getId() == null)
//					userRoleDetailsBean.setUserLoginDetailsFkId(userBean.getId());
//				//session.saveOrUpdate(userRoleDetailsBean);
//			}
			List <Long> urIdList = new ArrayList<Long>();
			for(UserRoleDetailsBean bean : listOfUserRoles) {
				urIdList.add(bean.getRoleMasterFkId());
				if(!(lurIdlist.contains(bean.getRoleMasterFkId()))) {
					if(userBean != null)
						bean.setUserLoginDetailsFkId(userBean.getId());
					else
						bean.setUserLoginDetailsFkId(loginId);
					
					bean.setLocationMasterFkId(1L);
					session.saveOrUpdate(bean);
				}
			}
			for(Long uid :lurIdlist){
				if(!(urIdList.contains(uid))){
					query=session.createQuery("delete UserRoleDetailsBean mapper where mapper.userLoginDetailsFkId= :lId and mapper.roleMasterFkId = :rId");
					query.setParameter("lId",loginId);
					query.setParameter("rId",uid);
					query.executeUpdate();
				}
			}
			tx.commit();
			
		}catch(HumanResourceException e){
			tx.rollback();
			logger.error(HumanResourceException.VOILATE_UNIQUE_EMAIL_CONSTRAINT, e);
			throw e;
		}catch (HibernateException e) {
			tx.rollback();
			logger.error(HumanResourceException.UNABLE_TO_SAVE_EMPLOYEE, e);
			throw new HumanResourceException(HumanResourceException.UNABLE_TO_SAVE_EMPLOYEE, e);

		} catch (Exception e) {
			tx.rollback();
			logger.error(HumanResourceException.UNABLE_TO_SAVE_EMPLOYEE, e);
			throw new HumanResourceException(HumanResourceException.UNABLE_TO_SAVE_EMPLOYEE, e);
		} finally {
			session.close();
		}
		return employeeMasterBean;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterBean> findAllEmployeeByGivenField(EmployeeMasterBean employeeMasterBean,Long roleId) throws HumanResourceException {
		
		List<EmployeeMasterBean> employeeList = new ArrayList<EmployeeMasterBean>();
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			String queryString = prepareUserListQuery(employeeMasterBean, roleId);
			Query query = null;		
			query = session.createQuery(queryString);
			employeeList = query.list();
			List<EmployeeMasterBean> removalList = new ArrayList<EmployeeMasterBean>();
			for(EmployeeMasterBean bean : employeeList) {
				
				query = session.createQuery("from UserLoginDetailsBean user where user.refrenceFkId = :refrenceFkId");
				query.setParameter("refrenceFkId", bean.getId());
				if(query.list().size() > 0){
					UserLoginDetailsBean userLoginDetailsBean = (UserLoginDetailsBean) query.list().get(0);
					if(userLoginDetailsBean.getIsDeleted() == 1) {
						removalList.add(bean);
					}
				}
			}
			
			if(removalList.size() > 0) {
				for(EmployeeMasterBean bean : removalList) {
					employeeList.remove(bean);
				}
			}
			
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			logger.error(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);
			throw new HumanResourceException(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);

		} catch (Exception e) {
			tx.rollback();
			logger.error(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);
			throw new HumanResourceException(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);
		} finally {
			session.close();
		}
		return employeeList; 
		
	}
		
		/**
		 * This method is use to create the dynamic query from employee master bean object for finding the list of employee for given criteria 
		 * 
		 * @param empBean
		 *            This object is use to create the dynamic query for this method 
		 * @return This method will return the query string for finding the list of employee for given criteria
		 * 
		 */
		private String prepareUserListQuery(EmployeeMasterBean empBean, Long roleId) {
			StringBuffer qry = new StringBuffer("from EmployeeMasterBean employee ");
			boolean whereAdded = false;
			if (empBean.getFirstName() != null && empBean.getFirstName().length() != 0) {
				qry.append(" where employee.firstName like '%"+ empBean.getFirstName()+"%'");
				whereAdded = true;
			}
			
			if (empBean.getLastName() != null && empBean.getLastName().length() != 0) {
				if (whereAdded) {
					qry.append(" and employee.lastName like '%"+ empBean.getLastName() +"%'");
				} else {
					qry.append(" where employee.lastName like '%" + empBean.getLastName() +"%'");
					whereAdded = true;
				}
			}
			
			if (empBean.getEmail() != null && empBean.getEmail().length() != 0) {
				if (whereAdded) {
					qry.append(" and employee.email like '%"+ empBean.getEmail() +"%'");
				} else {
					qry.append(" where employee.email like '%" + empBean.getEmail() +"%'");
					whereAdded = true;
				}
			}
			
			if (roleId != null && roleId > 0) {
				if (whereAdded) {
					qry.append(" and employee.id IN(SELECT uld.refrenceFkId FROM UserLoginDetailsBean uld WHERE uld.id IN (SELECT urd.userLoginDetailsFkId FROM UserRoleDetailsBean urd WHERE urd.roleMasterFkId="+ roleId+"))");
				} else {
					qry.append(" where employee.id IN(SELECT uld.refrenceFkId FROM UserLoginDetailsBean uld WHERE uld.id IN (SELECT urd.userLoginDetailsFkId FROM UserRoleDetailsBean urd WHERE urd.roleMasterFkId=" + roleId+"))");
					whereAdded = true;
				}
			}
			
			qry.append(" ORDER BY firstName");
			
			return qry.toString();
		}
		
		
		@SuppressWarnings("unchecked")
		public void deleteEmployeeAndDetails(Long id)  throws HumanResourceException {
			Session session = null;
			Transaction tx = null;
			Query query = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				
//				query = session.createQuery("delete UserLoginDetailsBean userBean where userBean.refrenceFkId = :referenceId");
//				query.setParameter("referenceId", id);
//				query.executeUpdate();
//				
//				query = session.createQuery("delete EmployeeMasterBean employeeBean where employeeBean.id = :id");
//				query.setParameter("id", id);
//				query.executeUpdate();
//				tx.commit();
				
				
				query = session.createQuery("from UserLoginDetailsBean user where user.refrenceFkId = :refrenceFkId");
				query.setParameter("refrenceFkId", id);
				List<UserLoginDetailsBean> userLoginDetailsBeans = query.list();
				if(userLoginDetailsBeans.size() > 0) {
					UserLoginDetailsBean userLoginDetailsBean = userLoginDetailsBeans.get(0);
					userLoginDetailsBean.setIsDeleted(1);
					session.saveOrUpdate(userLoginDetailsBean);
				} else {
					throw new HumanResourceException(HumanResourceException.UNABLE_TO_DELETE_EMPLOYEES);
				}
				
				query = session.createQuery("from EmployeeMasterBean emp where emp.id = :id");
				query.setParameter("id", id);
				List<EmployeeMasterBean> employeeMasterBeans = query.list();
				if(employeeMasterBeans.size() > 0) {
					EmployeeMasterBean bean = employeeMasterBeans.get(0);
					bean.setIsDeleted(true);
					session.saveOrUpdate(bean);
				} else {
					throw new HumanResourceException(HumanResourceException.UNABLE_TO_DELETE_EMPLOYEES);
				}
				
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
				logger.error(HumanResourceException.UNABLE_TO_DELETE_EMPLOYEES, e);
				throw new HumanResourceException(HumanResourceException.UNABLE_TO_DELETE_EMPLOYEES, e);

			} catch (Exception e) {
				tx.rollback();
				logger.error(HumanResourceException.UNABLE_TO_DELETE_EMPLOYEES, e);
				throw new HumanResourceException(HumanResourceException.UNABLE_TO_DELETE_EMPLOYEES, e);
			} finally {
				session.close();
			}
			
		}
		
		public Long findNoOfEmployees() throws HumanResourceException {
			Long numberOfRecords = null;
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				Query query = null;
				
				query = session.createQuery("select count(e) from EmployeeMasterBean e where e.isDeleted = :isDeleted");
				query.setParameter("isDeleted", false);
				numberOfRecords = (Long) query.uniqueResult();

				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
				logger.error(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);
				throw new HumanResourceException(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);

			} catch (Exception e) {
				tx.rollback();
				logger.error(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);
				throw new HumanResourceException(HumanResourceException.UNABLE_TO_LIST_EMPLOYEES, e);
			} finally {
				session.close();
			}
			return numberOfRecords;
		}
}
