/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	WorkflowCoreDAOImpl.java
 * Project Name							:	EPM
 * Date of Creation						: 	18th March 2012
 * Brief Description					:	This DAO class will be responsible for all the database operations related the the core workflow operations 
 * Author								: 	Sandeep Solomon Kunder
 * Revision History 
 * 
 *************************************************************************************************************************/

package com.indven.workflow.core.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.combohandler.EPMComboEntityVO;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.omds.assembler.DigitalManuscriptAssembler;
import com.indven.omds.entity.DigitalManuscriptBean;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.workflow.core.entity.CurrentProcessDetailsBean;
import com.indven.workflow.core.entity.CurrentProcessMasterBean;
import com.indven.workflow.core.entity.ProcessMasterBean;
import com.indven.workflow.core.exception.WorkFlowCoreException;
import com.indven.workflow.core.vo.WorkflowCoreDataPacket;
import com.indven.workflow.core.vo.WorkflowCountEntityVO;
import com.indven.workflow.location.entity.LocationUserRolesDetailsBean;
import com.indven.workflow.util.WorkflowActionTypeEnum;
import com.indven.workflow.util.WorkflowCountTypeEnum;
import com.indven.workflow.util.WorkflowQueriesConstant;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * This DAO class will be responsible for all the database operations related the the core workflow
 * operations
 * 
 * @author Sandeep Solomon Kunder
 * @see EPMLogger
 */
public class WorkflowCoreDAOImpl {

	private static IndvenLogger logger = IndvenLogger.getInstance(WorkflowCoreDAOImpl.class);

	/**
	 * 
	 * @return
	 * @throws WorkFlowCoreException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProcessMasterBean> findAllProcessMaster() throws WorkFlowCoreException {
		Session session = null;
		Transaction tx = null;
		List<ProcessMasterBean> entities = new ArrayList();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			entities = session.createCriteria(ProcessMasterBean.class).list();

			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			logger.error(WorkFlowCoreException.UNABLE_TO_LIST_ALL_PROCESS_MASTER, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_LIST_ALL_PROCESS_MASTER);
		} catch (Exception e) {
			tx.rollback();
			logger.error(WorkFlowCoreException.UNABLE_TO_LIST_ALL_PROCESS_MASTER, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_LIST_ALL_PROCESS_MASTER);
		} finally {
			session.close();
		}
		return entities;
	}

	/**
	 * This method is responsible for retrieving the counts of various workflows that are assigned
	 * the the user
	 * 
	 * @param userId
	 *            the user id for whom the counts are to be retrieved
	 * @return
	 * @throws WorkFlowCoreException
	 */
	@SuppressWarnings("unchecked")
	public List<WorkflowCountEntityVO> getTotalcountOfWflsForUser(Long userId) throws WorkFlowCoreException {

		Session session = null;
		Query query = null;
		List<WorkflowCountEntityVO> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Map<String, Object> session1 = ActionContext.getContext().getSession();
			Long currentRole = (Long) session1.get("currentRole");
			
			query = session.createSQLQuery("SELECT urd.id FROM wfl_locationuserroledetails urd WHERE urd.UserInfoFkId = :userId AND urd.rolemasterfkid = :currentRole");
			query.setParameter("userId", userId);
			query.setParameter("currentRole",currentRole);
			Long lurId = ((BigInteger)query.uniqueResult()).longValue();
			
			query = session.createSQLQuery("{ CALL USP_GetCountOfWflForUser( ? ) }").addEntity(WorkflowCountEntityVO.class);
			query.setParameter(0, lurId);
			entities = (List<WorkflowCountEntityVO>) query.list();

		} catch (HibernateException he) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_COUNTS_FOR_USER, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_COUNTS_FOR_USER);
		} catch (Exception e) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_COUNTS_FOR_USER, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_COUNTS_FOR_USER);
		} finally {
			session.close();
		}
		return entities;
	}

	/**
	 * This method is used to retrieve the list of process levels that are assigned to the user
	 * 
	 * @param processId
	 *            The process Id for which the list of assigned levels needs to be retrieved
	 * @param userId
	 *            The user id for whome the list of assigned levels needs to be retrieved
	 * @param countType
	 *            indicates what processes levels are to be retrieved (All/Assigned
	 *            Directly/Assigned to Role)
	 * @return a list of type {@link CurrentProcessDetailsBean} each entry will give the details of
	 *         the level assigned
	 * @throws WorkFlowCoreException
	 * @see WorkflowCountTypeEnum
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object>  getDetailedWorkflowList(Long processId, Long userId, Short countType)
			throws WorkFlowCoreException {

		Session session = null;
		Query query = null;
		List<CurrentProcessDetailsBean> entities = null;
		Map<String, Object> objMap = new HashMap<>();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Map<String, Object> session1 = ActionContext.getContext().getSession();
			Long currentRole = (Long) session1.get("currentRole");
			
			query = session.createSQLQuery("SELECT urd.id FROM wfl_locationuserroledetails urd WHERE urd.UserInfoFkId = :userId AND urd.rolemasterfkid = :currentRole");
			query.setParameter("userId", userId);
			query.setParameter("currentRole",currentRole);
			Long lurId = ((BigInteger)query.uniqueResult()).longValue();
			
			query = session.createSQLQuery("{ CALL USP_GetListOfWflForUser(?, ?, ?) }").addEntity(CurrentProcessDetailsBean.class);
			query.setParameter(0, lurId);
			query.setParameter(1, processId);
			query.setParameter(2, countType);

			entities = ((List<CurrentProcessDetailsBean>) query.list());
			
			List<Integer> wflProcessIdList = new ArrayList<>();
			List<String> languageNameList = new ArrayList<>();
			
			List<String> scriptNameList = new ArrayList<>();
			List<String> mantypeList = new ArrayList<>();
			for(int i=0 ; i<entities.size() ; i++) {
				wflProcessIdList.add(entities.get(i).getCurrentProcessMasterFkId().intValue());
				query = session.createSQLQuery(WorkflowQueriesConstant.GET_LANGUAGE_FOR_MANUSCRIPT_FROM_PROCESS_MASTER_ID);
				query.setParameter("wflProcessMasterId",entities.get(i).getCurrentProcessMasterFkId());
				String name = (String) query.uniqueResult();
				languageNameList.add(name);
				
				query = session.createSQLQuery(WorkflowQueriesConstant.GET_SCRIPT_FOR_MANUSCRIPT_FROM_PROCESS_MASTER_ID);
				query.setParameter("wflProcessMasterId",entities.get(i).getCurrentProcessMasterFkId());
				String scrname = (String) query.uniqueResult();
				scriptNameList.add(scrname);
				
				
				query = session.createQuery("select dm from  DigitalManuscriptBean dm , CurrentProcessMasterBean wf WHERE wf.ReferenceFkId = dm.id AND wf.id= :wflProcessMasterId");
				query.setParameter("wflProcessMasterId",entities.get(i).getCurrentProcessMasterFkId());
				DigitalManuscriptBean manType = (DigitalManuscriptBean) query.uniqueResult();
				mantypeList.add(manType.getManuscriptType().name());
			}
			objMap.put("wflDetails", entities);
			objMap.put("languageList", languageNameList);
			objMap.put("scriptList", scriptNameList);
			objMap.put("manTypeList", mantypeList);
		} catch (HibernateException he) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_LISTOF_WFL_FOR_USER, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_LISTOF_WFL_FOR_USER);
		} catch (Exception e) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_LISTOF_WFL_FOR_USER, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_LISTOF_WFL_FOR_USER);
		} finally {

		}
		
		return objMap;
	}

	/**
	 * 
	 * @param locationId
	 * @param processId
	 * @return
	 * @throws WorkFlowCoreException
	 */
	public boolean initiateWorkflow(String locationId, String processId, String referenceId, String description, Session session)
			throws WorkFlowCoreException {
		boolean isSuccess = false;

		Query query = null;

		try {
			query = session.createSQLQuery("{ CALL wfl.USP_InitiateNewWorkflow(?, ?, ?, ?) }");
			query.setParameter(0, locationId);
			query.setParameter(1, processId);
			query.setParameter(2, referenceId);
			query.setParameter(3, description);
			query.executeUpdate();

			isSuccess = true;

		} catch (HibernateException he) {
			logger.error(WorkFlowCoreException.UNABLE_TO_INITIATE_WORKFLOW, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_INITIATE_WORKFLOW);
		} catch (Exception e) {
			logger.error(WorkFlowCoreException.UNABLE_TO_INITIATE_WORKFLOW, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_INITIATE_WORKFLOW);
		} finally {

		}

		return isSuccess;
	}

	/**
	 * This method is responsible for processing the workflow. Based on the action performed, it
	 * will call the appropriate stored procedure to update the database
	 * 
	 * @param workflowDet
	 * @param session
	 *            An active hibernate session used to do all the database operations. the
	 *            transactions should be managed by the calling method
	 * @return returns true if all the details were updated successfully
	 * @throws WorkFlowCoreException
	 */
	public boolean processWorkflow(WorkflowCoreDataPacket workflowDet, Session session) throws WorkFlowCoreException {
		boolean isSuccess = false;

		Query query = null;

		try {
			if (WorkflowActionTypeEnum.getValueFromName(workflowDet.getActionType()) == WorkflowActionTypeEnum.ACTION_TYPE_FORWARD) {

				query = session.createSQLQuery("{ CALL wfl.USP_ProcessWflForward( ? , ? , ? ) }");
				query.setParameter(0, workflowDet.getCurrentWflDet().getId());
				query.setParameter(1, workflowDet.getUserId());
				query.setParameter(2, workflowDet.getSelectedUserRoleId());

			} else if (WorkflowActionTypeEnum.getValueFromName(workflowDet.getActionType()) == WorkflowActionTypeEnum.ACTION_TYPE_SAVE) {

//				query = session.createSQLQuery("{ CALL USP_ProcessWflSave( ? , ?) }");
//				query.setParameter(0, workflowDet.getCurrentWflDet().getId());
//				query.setParameter(1, workflowDet.getUserId());
				processWflAssignMe(workflowDet , session);

			} else if (WorkflowActionTypeEnum.getValueFromName(workflowDet.getActionType()) == WorkflowActionTypeEnum.ACTION_TYPE_RETURN) {

				query = session.createSQLQuery("{ CALL wfl.USP_ProcessWorkflowReturn( ? , ? ) }");
				query.setParameter(0, workflowDet.getCurrentWflDet().getId());

			} else if (WorkflowActionTypeEnum.getValueFromName(workflowDet.getActionType()) == WorkflowActionTypeEnum.ACTION_TYPE_TERMINATE) {

				query = session.createSQLQuery("{ CALL wfl.USP_ProcessWorkflowTerminate( ? ) }");
				query.setParameter(0, workflowDet.getCurrentWflDet().getId());

			} else {
				throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_UNKNOWN_ACTION_TYPE);
			}

			//query.executeUpdate();

			isSuccess = true;

		} catch (WorkFlowCoreException wfe) {
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW) ;
		} catch (HibernateException he) {
			logger.error(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		} catch (Exception e) {
			logger.error(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		} finally {

		}

		return isSuccess;
	}

	@SuppressWarnings("unchecked")
	public List<EPMComboEntityVO> getUsersForNextLevel(Long currentProcessLevelId) throws WorkFlowCoreException {
		Session session = null;
		Query query = null;
		List<EPMComboEntityVO> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			query = session.createSQLQuery("{ CALL wfl.USP_GetUserForNextLevel( ? ) }").addEntity(EPMComboEntityVO.class);
			query.setParameter(0, currentProcessLevelId);
			entities = (List<EPMComboEntityVO>) query.list();
			entities.add(0, new EPMComboEntityVO("-- Select -- ", "-1"));

		} catch (HibernateException he) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_USERS_FOR_NEXT_LEVEL, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_USERS_FOR_NEXT_LEVEL);
		} catch (Exception e) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_USERS_FOR_NEXT_LEVEL, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_USERS_FOR_NEXT_LEVEL);
		} finally {
			session.close();
		}
		return entities;
	}

	public CurrentProcessMasterBean findCurrentProcessMasterById(Long id) throws WorkFlowCoreException {
		Session session = null;
		Transaction tx = null;
		CurrentProcessMasterBean entity = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			entity = (CurrentProcessMasterBean) session.get(CurrentProcessMasterBean.class, id);

			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_PROCESS_MASTER_DETAILS, he);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_PROCESS_MASTER_DETAILS, he);
		} catch (Exception e) {
			logger.error(WorkFlowCoreException.UNABLE_TO_RETRIEVE_PROCESS_MASTER_DETAILS, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEVE_PROCESS_MASTER_DETAILS, e);
		} finally {
			session.close();
		}
		return entity;
	}
	
	/**
	 * @author Deba Prasad
	 * This method is written for temporary purpose as the 'USP_ProcessWflSave' stored proc is not working.
	 * Will remove this method after making the stored proc to work properly. 
	 */
	public boolean processWflAssignMe(WorkflowCoreDataPacket workflowDet, Session session) {
		Long userId = workflowDet.getUserId();
		Long currProcId = workflowDet.getCurrentWflDet().getId(); 
		boolean status = false;
		Query query = null;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			String queryString = "SELECT cpd.LocationUserRoleFkId , cpd.IsUserRoleId , cpm.LocationMasterFkId FROM wfl_currentprocessdetails cpd, wfl_currentprocessmaster cpm WHERE cpm.Id = cpd.CurrentProcessMasterFkId AND cpd.Id=:currentProcessId";
			query = session.createSQLQuery(queryString);
			query.setParameter("currentProcessId", currProcId);
			
			Object[] obj = (Object[]) query.uniqueResult();
			Long l_LocUserRoleId = Long.parseLong(obj[0].toString());
			Long l_IsUserRoleId = Long.parseLong(obj[1].toString());
			Long l_LocId = Long.parseLong(obj[2].toString());
			if(l_IsUserRoleId == 0) {
				queryString = "UPDATE wfl_currentprocessdetails SET LocationUserRoleFkId = " +
								"(SELECT id FROM wfl_locationuserroledetails" +
								" WHERE rolemasterfkid ="+  l_LocUserRoleId+" AND locationmasterfkid ="+ l_LocId+" AND UserInfoFkId =:userId )," +
								" IsUserRoleId = 1 WHERE Id =:currentProcessId";
				query = session.createSQLQuery(queryString);
				query.setParameter("userId", userId);
				query.setParameter("currentProcessId", currProcId);
				query.executeUpdate();
			}
			
			//tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally{
		}
		return status;
	}
	
	public boolean saveWFLDtlsData(CurrentProcessMasterBean masterBean , List<CurrentProcessDetailsBean> processDetailsList) throws WorkFlowCoreException {
		boolean status = false;
		Session session = null;
		Query query = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			query = session.createQuery(WorkflowQueriesConstant.GET_COUNT_OF_GIVEN_MANUSCRIPT_IN_PROCESS_OF_WFL);
			query.setParameter("referenceId", masterBean.getReferenceFkId());
			Long count = (Long) query.uniqueResult();
			if(count > 0L){
				throw new WorkFlowCoreException(WorkFlowCoreException.RECORD_ALREADY_IN_PROCESS);
			} else {
				session.save(masterBean);
				for(int i=0 ; i<processDetailsList.size() ; i++) {
					processDetailsList.get(i).setCurrentProcessMasterFkId(masterBean.getId());
					session.save(processDetailsList.get(i));
				}
				
				/**
				 * The block of code written below will update the manuscript record status to 'Under_Scholar' as the record is ready for 
				 * the scholar to perform his task on the record after triggering the workflow for scholar.  
				 */
				query = session.createQuery("select manBean from DigitalManuscriptBean manBean where id = :manuscriptId");
				query.setParameter("manuscriptId", masterBean.getReferenceFkId());
				DigitalManuscriptBean dmbean = (DigitalManuscriptBean) query.uniqueResult();
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Scholar);
				
//				Criteria criteria = session.createCriteria(DigitalDocumentBean.class)
//						.add(Restrictions.eq("digitalManuscriptFkId", masterBean.getReferenceFkId()))
//						.createAlias("documentDetailsBean", "details")
//						.addOrder(Order.desc("details.version"))
//						.setMaxResults(1);
//				
//				DigitalDocumentBean bean = (DigitalDocumentBean) criteria.uniqueResult();
//				bean.setDocumentStatus(DocumentStatusEnum.Under_Scholar);
			}
			
			tx.commit();
		} catch (WorkFlowCoreException e) {
			tx.rollback();
			logger.error(WorkFlowCoreException.RECORD_ALREADY_IN_PROCESS, e);
			throw new WorkFlowCoreException(WorkFlowCoreException.RECORD_ALREADY_IN_PROCESS);
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}
	
	/**
	 * This method is used for branching the workflow i.e the method simply finds the current process details id and 
	 * updates the LocationUserRoleFkId column for the particular column to the respective user related id.
	 * 
	 * @param referenceProjectId
	 * @param locationUserRoleId
	 * @return
	 * @throws WorkFlowCoreException 
	 */
	public boolean branchWorkflow(Long referenceProjectId) throws WorkFlowCoreException {
		boolean status = false;
		Query query = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			query = session.createQuery("select manBean from DigitalManuscriptBean manBean where id = :manuscriptId");
			query.setParameter("manuscriptId",referenceProjectId);
			DigitalManuscriptBean dmbean = (DigitalManuscriptBean) query.uniqueResult();
			dmbean.setRecordStatus(DocumentStatusEnum.Under_Verifier);
			
			CurrentProcessMasterBean bean ;
			String queryStr = "From CurrentProcessMasterBean bean where bean.ReferenceFkId =:referenceProjectId order by bean.id DESC";
			
			query = session.createQuery(queryStr);
			query.setParameter("referenceProjectId", referenceProjectId);
			query.setMaxResults(1);
			bean = (CurrentProcessMasterBean) query.uniqueResult();
			
			CurrentProcessDetailsBean processDetailsList = new CurrentProcessDetailsBean();
			String query2 = "From CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId and bean.status = 1 and bean.isUserRoleId = 1";
			query = session.createQuery(query2);
			query.setParameter("currentProcessId", bean.getId());
			
			processDetailsList = (CurrentProcessDetailsBean) query.uniqueResult();

			CurrentProcessDetailsBean dtlsBean = new CurrentProcessDetailsBean();
			BeanUtils.copyProperties(dtlsBean, processDetailsList);
			dtlsBean.setLocationUserRoleFkId(8L);
			dtlsBean.setLevel(25L);
			dtlsBean.setIsUserRoleId((short)0);
			
			session.save(dtlsBean);
			processDetailsList.setStatus((short)2);
			
			tx.commit();
			status = true;
		} catch (Exception e) {
			tx.rollback();
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEV_USERS_LIST_WITH_ROLE);
			// TODO: handle exception
		}finally {
			session.close();
		}
		return status;
	}
	
	public boolean rejectWfl(Long prjRefId) throws WorkFlowCoreException {
		boolean status = false;
		Query query = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			CurrentProcessMasterBean bean ;
			String queryStr = "From CurrentProcessMasterBean bean where bean.ReferenceFkId =:referenceProjectId order by bean.id DESC";
			
			query = session.createQuery(queryStr);
			query.setParameter("referenceProjectId", prjRefId);
			query.setMaxResults(1);
			bean = (CurrentProcessMasterBean) query.uniqueResult();
			
			CurrentProcessDetailsBean processDetailsList = new CurrentProcessDetailsBean();
			String query2 = "From CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId and bean.status = 1 and bean.isUserRoleId = 1";
			query = session.createQuery(query2);
			query.setParameter("currentProcessId", bean.getId());
			
			processDetailsList = (CurrentProcessDetailsBean) query.uniqueResult();
			
			CurrentProcessDetailsBean previousProcess = new CurrentProcessDetailsBean();
			String getlastprocessdtls = "From CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId and bean.status = 2 and bean.level < :currentlevel order by bean.level DESC";
			query = session.createQuery(getlastprocessdtls);
			query.setParameter("currentProcessId", bean.getId());
			query.setParameter("currentlevel", processDetailsList.getLevel());
			query.setMaxResults(1);
			
			previousProcess = (CurrentProcessDetailsBean) query.uniqueResult();
			
			processDetailsList.setStatus((short)0);
			previousProcess.setStatus((short)1);
			
			/**
			 * The below code is written to update the status 
			 */
			query = session.createQuery("from LocationUserRolesDetailsBean lur where lur.id="+previousProcess.getLocationUserRoleFkId());
			LocationUserRolesDetailsBean locUserRole = (LocationUserRolesDetailsBean) query.uniqueResult();
			
			query = session.createQuery("select manBean from DigitalManuscriptBean manBean where id = :manuscriptId");
			query.setParameter("manuscriptId",prjRefId);
			DigitalManuscriptBean dmbean = (DigitalManuscriptBean) query.uniqueResult();
			
			if(locUserRole.getRoleMasterFkId().equals(IndvenApplicationConstants.SCHOLAR_ROLE_ID)) {
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Scholar);
			} else if(locUserRole.getRoleMasterFkId().equals(IndvenApplicationConstants.REVIEWER_ROLE_ID)) {
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Reviewer);
			} else if(locUserRole.getRoleMasterFkId().equals(IndvenApplicationConstants.PUBLISHER_ROLE_ID)) {
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Publisher);
			} else if(locUserRole.getRoleMasterFkId().equals(IndvenApplicationConstants.VERIFIER_ROLE_ID)) {
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Verifier);
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		}finally {
			session.close();
		}
		return status;
	}
	
	
	public boolean assignProcessFromUserToRole(Long processId) throws WorkFlowCoreException {
		boolean status = false;
		Query query = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			
			String getlastprocessdtls = "from CurrentProcessDetailsBean bean where bean.id =:dtlsId";
			query = session.createQuery(getlastprocessdtls);

			query.setParameter("dtlsId", processId);
			
			CurrentProcessDetailsBean bean = (CurrentProcessDetailsBean) query.uniqueResult();
			
			if(bean.getIsUserRoleId() == (short)1 && bean.getStatus() == (short)1) {
				query = session.createQuery("from LocationUserRolesDetailsBean lur where  lur.id=:id");
				query.setParameter("id", bean.getLocationUserRoleFkId());
				LocationUserRolesDetailsBean lur = (LocationUserRolesDetailsBean) query.uniqueResult();
				
				bean.setLocationUserRoleFkId(lur.getRoleMasterFkId());
				bean.setIsUserRoleId((short)0);
			} else {
				throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
			}
			
			tx.commit();
			status = true;
		} catch (Exception e) {
			tx.rollback();
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		} finally {
			session.close();
		}
		return status;
	}
	
	public CurrentProcessMasterBean makeNextProcessStatusActive(Long prjRefId,Session session) {
		boolean status = false;
		Query query = null;
		CurrentProcessMasterBean bean = null;
		try {
			String queryStr = "From CurrentProcessMasterBean bean where bean.ReferenceFkId =:referenceProjectId order by bean.id DESC";
			
			query = session.createQuery(queryStr);
			query.setParameter("referenceProjectId", prjRefId);
			query.setMaxResults(1);
			bean = (CurrentProcessMasterBean) query.uniqueResult();
			
			CurrentProcessDetailsBean processDetailsList = new CurrentProcessDetailsBean();
			String query2 = "From CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId and bean.status = 1 and bean.isUserRoleId = 1";
			query = session.createQuery(query2);
			query.setParameter("currentProcessId", bean.getId());
			
			processDetailsList = (CurrentProcessDetailsBean) query.uniqueResult();
			
			CurrentProcessDetailsBean nextProcess = new CurrentProcessDetailsBean();
			String getlastprocessdtls = "From CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId and bean.status = 0 and bean.level > :currentlevel order by bean.level ASC";
			query = session.createQuery(getlastprocessdtls);
			query.setParameter("currentProcessId", bean.getId());
			query.setParameter("currentlevel", processDetailsList.getLevel());
			query.setMaxResults(1);
			
			nextProcess = (CurrentProcessDetailsBean) query.uniqueResult();
			
			if(nextProcess != null) {
				nextProcess.setStatus((short)1);
			}
			processDetailsList.setStatus((short)2);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public boolean triggerForSubmit(Long referenceProjectId , Session session) throws OMDPCoreException {
		boolean status = false;
		Query query = null;
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			
			CurrentProcessMasterBean bean = makeNextProcessStatusActive(referenceProjectId , session);
			
			List<CurrentProcessDetailsBean> processDetailsList = new ArrayList<>();
			String query2 = "From CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId";
			query = session.createQuery(query2);
			query.setParameter("currentProcessId", bean.getId());
			
			processDetailsList = query.list();
			Long currUserId = (Long)ActionContext.getContext().getSession().get("currentRole");
			
			/**
			 * The block of code written below will update the manuscript record status to 'Under_Reviewer' as the record is ready for 
			 * the reviewer to review and edit the details after the record is authorized from scholar. 
			 */
			query = session.createQuery("select manBean from DigitalManuscriptBean manBean where id = :manuscriptId");
			query.setParameter("manuscriptId", referenceProjectId);
			DigitalManuscriptBean dmbean = (DigitalManuscriptBean) query.uniqueResult();
			
			
			
			if(currUserId.equals(IndvenApplicationConstants.SCHOLAR_ROLE_ID) || currUserId.equals(IndvenApplicationConstants.TRANSLATOR_ROLE_ID)) {
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Reviewer);
			} else if(currUserId.equals(IndvenApplicationConstants.REVIEWER_ROLE_ID)){
				if (processDetailsList.size() > 3) {
					dmbean.setRecordStatus(DocumentStatusEnum.Under_Verifier);
				} else {
					dmbean.setRecordStatus(DocumentStatusEnum.Under_Publisher);
				}
				//dmbean.setRecordStatus(DocumentStatusEnum.Under_Publisher);
			} else if(currUserId.equals(IndvenApplicationConstants.VERIFIER_ROLE_ID)){
				dmbean.setRecordStatus(DocumentStatusEnum.Under_Publisher);
				//dmbean.setRecordStatus(DocumentStatusEnum.Under_Publisher);
			} else if(currUserId.equals(IndvenApplicationConstants.PUBLISHER_ROLE_ID)){
				/**
				 * TODO : Here after publishing the document all the process on the document is completed.
				 * So need to remove the records related to the particular Digital Manuscript document from CurrrentProcessMaster and CurrentProcessDetails and
				 * add in corresponding workflow history table.
				 */
				
				dmbean.setRecordStatus(DocumentStatusEnum.Published);
				
				if(dmbean.getManuscriptType().equals(ManuscriptTypeEnum.Original)) {
					DigitalManuscriptBean transcribedBean = new DigitalManuscriptBean();				
					DigitalManuscriptAssembler.copyObjToNewState(transcribedBean, dmbean);
					session.save(transcribedBean);
				}
			}
			status = true;
		}catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT);
		}
		
		return status;
	}

	/**
	 * This method will make the user un-assigned for the particular process details rcord and will agin make the record available for
	 * the particular role.
	 * 
	 * @param currentProcessId
	 * @param roleIdToBeAssigned
	 * @return
	 * @throws WorkFlowCoreException
	 */
	public boolean unAssignWFLToUser(Long currentProcessId , Long roleIdToBeAssigned) throws WorkFlowCoreException {
		boolean noOfRecords = false;
		Session session = null;
		Query query = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session.createQuery("update CurrentProcessDetailsBean set LocationUserRoleFkId =:roleId , isUserRoleId = 0 where id = :processRecordId");
			query.setParameter("roleId", roleIdToBeAssigned);
			query.setParameter("processRecordId", currentProcessId);
			
			int count = query.executeUpdate();
			tx.commit();
			noOfRecords = true;
		} catch (Exception e) {
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		}finally {
			session.close();
		}
		return noOfRecords;
	}
	
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterBean> empBeanListWithSpecificRole(Long roleId) throws WorkFlowCoreException {
		List<EmployeeMasterBean> empBeanList = new ArrayList<>();
		Session session = null;
		Query query = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session.createQuery("SELECT e FROM EmployeeMasterBean e , LocationUserRolesDetailsBean lur WHERE e.id = lur.userInfoFkId AND lur.roleMasterFkId =:roleId");
			query.setParameter("roleId", roleId);
			
			empBeanList = query.list();
			tx.commit();
		} catch (Exception e) {
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_RETRIEV_USERS_LIST_WITH_ROLE);
		}
		return empBeanList;
	}
	
	public int isPresentReviewer2(Long prjRefId) throws WorkFlowCoreException {
		int status = 0;
		Query query = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			String query2 = "select count(bean) from CurrentProcessDetailsBean bean where bean.CurrentProcessMasterFkId =:currentProcessId and bean.level = 25";
			query = session.createQuery(query2);
			query.setParameter("currentProcessId", prjRefId);
			
			Long count = (Long)query.uniqueResult();
			if(count > 0) {
				status = 1;
			}
			tx.commit();
		} catch (Exception e) {
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		}
		return status;
	}
}
