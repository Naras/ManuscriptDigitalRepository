/**
 * 
 */
package com.indven.omds.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.HibernateUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.omds.assembler.DigitalManuscriptAssembler;
import com.indven.omds.entity.AuthorBean;
import com.indven.omds.entity.BundleMasterBean;
import com.indven.omds.entity.CategoryBean;
import com.indven.omds.entity.DigitalDocumentBean;
import com.indven.omds.entity.DigitalDocumentDetailsBean;
import com.indven.omds.entity.DigitalManuscriptBean;
import com.indven.omds.entity.DigitalManuscriptFrame;
import com.indven.omds.entity.DocumentCommentBean;
import com.indven.omds.entity.LanguageBean;
import com.indven.omds.entity.ManuscriptSpecificcategoryMapper;
import com.indven.omds.entity.ManuscriptTagMapper;
import com.indven.omds.entity.MaterialBean;
import com.indven.omds.entity.Organisation;
import com.indven.omds.entity.PublicationBean;
import com.indven.omds.entity.PublisherBean;
import com.indven.omds.entity.ScriptBean;
import com.indven.omds.entity.SpecificCategoryBean;
import com.indven.omds.entity.TagMasterBean;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.service.ManuscriptMasterServiceImpl;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptQueriesConstant;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.workflow.core.dao.WorkflowCoreDAOImpl;
import com.indven.workflow.core.entity.CurrentProcessDetailsBean;
import com.indven.workflow.core.entity.CurrentProcessMasterBean;
import com.indven.workflow.core.exception.WorkFlowCoreException;
import com.indven.workflow.core.vo.WorkflowCoreDataPacket;

/**
 * @author Deba Prasad
 *
 */
public class ManuscriptMasterDAOImpl {
	
	@SuppressWarnings({ "unchecked", "unused" })
	public Map<String, Object> searchManuscriptRecord(DigitalManuscriptBean manuscriptBean , AuthorBean authorBean , Organisation orgBean , int setFirst , int noOfRecords , List<Long> specificCategoryIds,Long minFolio , Long maxFolio) throws OMDPCoreException {
		List<DigitalManuscriptBean> manuscriptBeanList = new ArrayList<DigitalManuscriptBean>();
		List<DigitalManuscriptBean> manuscriptBeanListUnderWfl = new ArrayList<DigitalManuscriptBean>();
		List<DigitalManuscriptBean> manuscriptBeanListFree = new ArrayList<DigitalManuscriptBean>();
		
		List<DigitalManuscriptBean> manuscriptBeanListUnderWflUser = new ArrayList<DigitalManuscriptBean>();
		List<EmployeeMasterBean> listOfRecordOwners = new ArrayList<EmployeeMasterBean>();
		List<Long> listOfRecordIds = new ArrayList<Long>();
		
		List<Boolean> framePresence = new ArrayList<Boolean>();
		
		Session session = null;
		Transaction tx = null;
		Query query  = null;
		Long count ;
		try {
			session = HibernateUtil.getSessionFactory().openSession();		
			tx = session.beginTransaction();
			
			StringBuffer qryBfrNoSpCategory = new StringBuffer("from DigitalManuscriptBean digitalmanuscriptbean  ");
			StringBuffer qryBfrWithSpCategory = new StringBuffer("from DigitalManuscriptBean digitalmanuscriptbean , ManuscriptSpecificcategoryMapper spcfcCat ");
			
			StringBuffer queryStrBuffer = new StringBuffer(" where digitalmanuscriptbean.authorFKId = digitalmanuscriptbean.authorFkObj.id and  digitalmanuscriptbean.isDeleted = 0 ");
			
			if(manuscriptBean.getDocumentType() != null &&  manuscriptBean.getDocumentType() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.documentType = :documentTypeId ");
			}
			
			if(manuscriptBean.getLanguageFkId() != null  && manuscriptBean.getLanguageFkId() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.languageFkId = :languageFkId ");
			}
			
			if(manuscriptBean.getCategoryFkId() != null && manuscriptBean.getCategoryFkId() > 0 ) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.categoryFkId = :categoryFkId ");
			}
			
			if(minFolio != null && minFolio > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.totalNumberOfFolios > "+minFolio );
			}
			
			if(maxFolio != null && maxFolio > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.totalNumberOfFolios <="+maxFolio);
			}
			
			/*if(manuscriptBean.getSpecificCategoryFkId() != null && manuscriptBean.getSpecificCategoryFkId() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.specificCategoryFkId = :specificCategoryFkId ");
			}*/
			
			if(manuscriptBean.getScriptFkId() != null && manuscriptBean.getScriptFkId() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.scriptFkId = :scriptFkId ");
			}
			
			if(manuscriptBean.getTypeOfWork() != null && manuscriptBean.getTypeOfWork().getValue() >= 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.typeOfWork = :workType ");
			}
			
			if(authorBean.getName() != null && authorBean.getName().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and (digitalmanuscriptbean.authorFkObj.name LIKE :authorName or digitalmanuscriptbean.authorFkObj.regionalName LIKE :authorName or digitalmanuscriptbean.authorFkObj.diacriticName LIKE :authorName)");
			}
			
			if(manuscriptBean.getName() != null && manuscriptBean.getName().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and (digitalmanuscriptbean.name LIKE :manuscriptName or digitalmanuscriptbean.regionalName LIKE :manuscriptName or digitalmanuscriptbean.diacriticName LIKE :manuscriptName) ");
			}
			
			if(manuscriptBean.getSummary() != null && manuscriptBean.getSummary().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and (digitalmanuscriptbean.summary LIKE :summary or digitalmanuscriptbean.tableOfContents LIKE :summary or digitalmanuscriptbean.uniquenessOfWork LIKE :summary or digitalmanuscriptbean.contributionToAyurveda LIKE :summary) ");
			}
			
			if(manuscriptBean.getBeginningLine() != null && manuscriptBean.getBeginningLine().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.beginningLine LIKE :beginningLine ");
			}
			
			if(manuscriptBean.getEndingLine() != null && manuscriptBean.getEndingLine().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.endingLine LIKE :endingLine ");
			}
			
			if(orgBean.getName() != null && orgBean.getName().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.organisationFkObj.name LIKE :orgName");
			}
			
			if(manuscriptBean.getManuscriptId() != null && manuscriptBean.getManuscriptId().length() > 0) {
				queryStrBuffer = queryStrBuffer.append("and digitalmanuscriptbean.manuscriptId LIKE :manuscriptId");
			}
			
			if(manuscriptBean.getRecordStatus()!= null && manuscriptBean.getRecordStatus().ordinal() > 0) {
				short jd = (short)manuscriptBean.getRecordStatus().ordinal();
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.recordStatus = "+(short)manuscriptBean.getRecordStatus().ordinal());
			}
			
			if(manuscriptBean.getManuscriptType() != null && manuscriptBean.getManuscriptType().ordinal() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.manuscriptType = "+(short)manuscriptBean.getManuscriptType().ordinal());
			}
			
			if(manuscriptBean.getDocumentationOfManuscript() != null && manuscriptBean.getDocumentationOfManuscript().ordinal() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.documentationOfManuscript = "+(short)manuscriptBean.getDocumentationOfManuscript().ordinal());
			}
			
			if(specificCategoryIds != null && specificCategoryIds.size() > 0) {
				queryStrBuffer = qryBfrWithSpCategory.append(queryStrBuffer);
				
				StringBuffer buff = new StringBuffer(" and spcfcCat.manuscriptFkId = digitalmanuscriptbean.id AND (");
				StringBuffer buff2 = new StringBuffer();
				
				for(int i=0 ; i<specificCategoryIds.size() ; i++) {
					if(i > 0) {
						buff2.append(" or ");
					}
					buff2.append("spcfcCat.specificcategoryFkId = "+specificCategoryIds.get(i));
				}
				buff2.append(") GROUP BY spcfcCat.manuscriptFkId ");
				buff.append(buff2);
				queryStrBuffer.append(buff);
			} else {
				queryStrBuffer = qryBfrNoSpCategory.append(queryStrBuffer);
			}
			
			String queryStr = queryStrBuffer.toString();		
			String countQueryStr = "select count(digitalmanuscriptbean) "+queryStr;
			//queryStr = queryStr+" order by digitalmanuscriptbean.id asc";
			Query countQuery = session.createQuery(countQueryStr);
			
			queryStr = "select digitalmanuscriptbean "+queryStr;
			query = session.createQuery(queryStr.toString());
			
			if(authorBean.getName() != null && authorBean.getName().length() > 0) {
				query.setParameter("authorName", "%"+authorBean.getName().trim()+"%");
				countQuery.setParameter("authorName", "%"+authorBean.getName().trim()+"%");
			}
			
			if(manuscriptBean.getName() != null && manuscriptBean.getName().length() > 0) {
				query.setParameter("manuscriptName", "%" + manuscriptBean.getName().trim() + "%");
				countQuery.setParameter("manuscriptName", "%" + manuscriptBean.getName().trim() + "%");
			}
			
			if(manuscriptBean.getSummary() != null && manuscriptBean.getSummary().length() > 0) {
				query.setParameter("summary", "%" + manuscriptBean.getSummary().trim() + "%");
				countQuery.setParameter("summary", "%" + manuscriptBean.getSummary().trim() + "%");
			}
			
			if(manuscriptBean.getBeginningLine() != null && manuscriptBean.getBeginningLine().length() > 0) {
				query.setParameter("beginningLine", "%" + manuscriptBean.getBeginningLine().trim() + "%");
				countQuery.setParameter("beginningLine", "%" + manuscriptBean.getBeginningLine().trim() + "%");
			}
			
			if(manuscriptBean.getEndingLine() != null && manuscriptBean.getEndingLine().length() > 0) {
				query.setParameter("endingLine", "%" + manuscriptBean.getEndingLine().trim() + "%");
				countQuery.setParameter("endingLine", "%" + manuscriptBean.getEndingLine().trim() + "%");
			}
			
			if(orgBean.getName() != null && orgBean.getName().length() > 0) {
				query.setParameter("orgName", "%" + orgBean.getName() + "%");
				countQuery.setParameter("orgName", "%" + orgBean.getName() + "%");
			}
			
			if(manuscriptBean.getTypeOfWork() != null && manuscriptBean.getTypeOfWork().getValue() >= 0) {
				query.setParameter("workType", manuscriptBean.getTypeOfWork());
				countQuery.setParameter("workType", manuscriptBean.getTypeOfWork());
			}
			
			if(manuscriptBean.getDocumentType() != null &&  manuscriptBean.getDocumentType() > 0) {
				query.setParameter("documentTypeId", manuscriptBean.getDocumentType());
				countQuery.setParameter("documentTypeId", manuscriptBean.getDocumentType());
			}
			
			if(manuscriptBean.getLanguageFkId() != null && manuscriptBean.getLanguageFkId() > 0) {
				query.setParameter("languageFkId", manuscriptBean.getLanguageFkId());
				countQuery.setParameter("languageFkId", manuscriptBean.getLanguageFkId());
			}
			
			if(manuscriptBean.getCategoryFkId() != null && manuscriptBean.getCategoryFkId() > 0) {
				query.setParameter("categoryFkId", manuscriptBean.getCategoryFkId());
				countQuery.setParameter("categoryFkId", manuscriptBean.getCategoryFkId());
			}
			
		/*	if(manuscriptBean.getSpecificCategoryFkId() != null && manuscriptBean.getSpecificCategoryFkId() > 0) {
				query.setParameter("specificCategoryFkId", manuscriptBean.getSpecificCategoryFkId());
				countQuery.setParameter("specificCategoryFkId", manuscriptBean.getSpecificCategoryFkId());
			}*/
			
			if(manuscriptBean.getScriptFkId() != null && manuscriptBean.getScriptFkId() > 0) {
				query.setParameter("scriptFkId", manuscriptBean.getScriptFkId());
				countQuery.setParameter("scriptFkId", manuscriptBean.getScriptFkId());
			}
			if(manuscriptBean.getManuscriptId() != null && manuscriptBean.getManuscriptId().length() > 0) {
				query.setParameter("manuscriptId", "%"+manuscriptBean.getManuscriptId()+"%");
				countQuery.setParameter("manuscriptId", "%"+manuscriptBean.getManuscriptId()+"%");
			}
			query.setFirstResult(setFirst);
			query.setMaxResults(noOfRecords);
			manuscriptBeanList = query.list();
			
			for (int i = 0; i < manuscriptBeanList.size(); i++) {
				query = session.createQuery("from DigitalManuscriptFrame frame where frame.digitalManuscriptFkId ="+ manuscriptBeanList.get(i).getId());
				List<DigitalManuscriptFrame> frameList = query.list();
				
				if(frameList != null && frameList.size() >0) {
					manuscriptBeanList.get(i).setPresentFrame(true);
				}else {
					manuscriptBeanList.get(i).setPresentFrame(false);
				}
			
				query = session
						.createQuery("select processMaster.id from CurrentProcessMasterBean processMaster where processMaster.ReferenceFkId = :referenceId");
				query.setParameter("referenceId", manuscriptBeanList.get(i)
						.getId());
				List<Long> idList = new ArrayList<Long>();
				idList = query.list();
				if (idList.size() <= 0) {
					manuscriptBeanListFree.add(manuscriptBeanList.get(i));
				} else {
					Long id = idList.get(idList.size() - 1);
					query = session
							.createQuery("select dtlsBean from CurrentProcessDetailsBean dtlsBean where "
									+ "dtlsBean.status < 2 and dtlsBean.CurrentProcessMasterFkId ="
									+ id);

					List<CurrentProcessDetailsBean> dtlsBeanList = query.list();
					Long count1 = new Long((long) dtlsBeanList.size());
					if (count1 > 0) {
						boolean isUnderUser = false;
						for (CurrentProcessDetailsBean bean : dtlsBeanList) {
							if (bean.getStatus() == (short) 1
									&& bean.getIsUserRoleId() == (short) 1) {
								isUnderUser = true;

								query = session
										.createQuery("select empl from UserLoginDetailsBean userLoginDtls , LocationUserRolesDetailsBean lur , EmployeeMasterBean empl "
												+ " where lur.userInfoFkId = userLoginDtls.id and empl.id=userLoginDtls.refrenceFkId and lur.id = :locUserRoleId");

								query.setParameter("locUserRoleId",
										bean.getLocationUserRoleFkId());
								EmployeeMasterBean usrLognDtl = (EmployeeMasterBean) query
										.uniqueResult();
								listOfRecordOwners.add(usrLognDtl);
								listOfRecordIds.add(bean.getId());
							}
						}

						if (isUnderUser) {
							manuscriptBeanListUnderWflUser
									.add(manuscriptBeanList.get(i));
						} else {
							manuscriptBeanListUnderWfl.add(manuscriptBeanList
									.get(i));
						}

					} else {
						manuscriptBeanListFree.add(manuscriptBeanList.get(i));
					}
				}
				
			}
			
			if(specificCategoryIds != null && specificCategoryIds.size() > 0) {
				List<Object> countList =  countQuery.list();
				count =  Long.valueOf(countList.size());
			} else {
				count = (Long) countQuery.uniqueResult();
			}
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		} finally {
			session.close();
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("manuscriptListFree", manuscriptBeanListFree);
		returnMap.put("manuscriptListUndWfl", manuscriptBeanListUnderWfl);
		returnMap.put("totalCount", count);
		
		
		returnMap.put("manuscriptListUndWflUser", manuscriptBeanListUnderWflUser);
		returnMap.put("ownerOfWflProcess", listOfRecordOwners);
		returnMap.put("idOfWflProcess", listOfRecordIds);
		
		return returnMap;
	}
	
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public Map<String, Object> searchManuscriptToMerge(DigitalManuscriptBean manuscriptBean , AuthorBean authorBean , Organisation orgBean , List<Long> specificCategoryIds) throws OMDPCoreException {
		List<DigitalManuscriptBean> manuscriptBeanList = new ArrayList<>();
		List<DigitalManuscriptBean> manuscriptBeanListUnderWfl = new ArrayList<>();
		
		//List<DigitalManuscriptBean> manuscriptBeanListFree = new ArrayList<>();
		
		//List<DigitalManuscriptBean> manuscriptBeanListUnderWflUser = new ArrayList<>();
		//List<EmployeeMasterBean> listOfRecordOwners = new ArrayList<>();
		List<Long> listOfRecordIds = new ArrayList<>();
		
	//	List<Boolean> framePresence = new ArrayList<>();
		
		Session session = null;
		Transaction tx = null;
		Query query  = null;
		Long count ;
		try {
			session = HibernateUtil.getSessionFactory().openSession();		
			tx = session.beginTransaction();
			
			StringBuffer qryBfrNoSpCategory = new StringBuffer("from DigitalManuscriptBean digitalmanuscriptbean  ");
			StringBuffer qryBfrWithSpCategory = new StringBuffer("from DigitalManuscriptBean digitalmanuscriptbean , ManuscriptSpecificcategoryMapper spcfcCat ");
			
			StringBuffer queryStrBuffer = new StringBuffer(" where digitalmanuscriptbean.authorFKId = digitalmanuscriptbean.authorFkObj.id and  digitalmanuscriptbean.isDeleted = 0 and  digitalmanuscriptbean.manuscriptType = 1 and digitalmanuscriptbean.id not in ( SELECT process.ReferenceFkId from CurrentProcessMasterBean process ) ");
			
			if(manuscriptBean.getDocumentType() != null &&  manuscriptBean.getDocumentType() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.documentType = :documentTypeId ");
			}
			
			if(manuscriptBean.getLanguageFkId() != null  && manuscriptBean.getLanguageFkId() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.languageFkId = :languageFkId ");
			}
			
			if(manuscriptBean.getCategoryFkId() != null && manuscriptBean.getCategoryFkId() > 0 ) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.categoryFkId = :categoryFkId ");
			}
			
			if(manuscriptBean.getScriptFkId() != null && manuscriptBean.getScriptFkId() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.scriptFkId = :scriptFkId ");
			}
			
			if(manuscriptBean.getTypeOfWork() != null && manuscriptBean.getTypeOfWork().getValue() >= 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.typeOfWork = :workType ");
			}
			
			if(authorBean.getName() != null && authorBean.getName().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and (digitalmanuscriptbean.authorFkObj.name LIKE :authorName or digitalmanuscriptbean.authorFkObj.regionalName LIKE :authorName or digitalmanuscriptbean.authorFkObj.diacriticName LIKE :authorName)");
			}
			
			if(manuscriptBean.getName() != null && manuscriptBean.getName().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and (digitalmanuscriptbean.name LIKE :manuscriptName or digitalmanuscriptbean.regionalName LIKE :manuscriptName or digitalmanuscriptbean.diacriticName LIKE :manuscriptName) ");
			}
			
			/*if(manuscriptBean.getSummary() != null && manuscriptBean.getSummary().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and (digitalmanuscriptbean.summary LIKE :summary or digitalmanuscriptbean.tableOfContents LIKE :summary or digitalmanuscriptbean.uniquenessOfWork LIKE :summary) ");
			}
			
			if(manuscriptBean.getBeginningLine() != null && manuscriptBean.getBeginningLine().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.beginningLine LIKE :beginningLine ");
			}
			
			if(manuscriptBean.getEndingLine() != null && manuscriptBean.getEndingLine().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.endingLine LIKE :endingLine ");
			}*/
			
			if(orgBean.getName() != null && orgBean.getName().length() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.organisationFkObj.name LIKE :orgName");
			}
			
			if(manuscriptBean.getManuscriptId() != null && manuscriptBean.getManuscriptId().length() > 0) {
				queryStrBuffer = queryStrBuffer.append("and digitalmanuscriptbean.manuscriptId LIKE :manuscriptId");
			}
			
			if(manuscriptBean.getRecordStatus()!= null && manuscriptBean.getRecordStatus().ordinal() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.recordStatus = "+(short)manuscriptBean.getRecordStatus().ordinal());
			}
			
			if(manuscriptBean.getManuscriptType() != null && manuscriptBean.getManuscriptType().ordinal() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.manuscriptType = "+(short)manuscriptBean.getManuscriptType().ordinal());
			}
			
			if(manuscriptBean.getDocumentationOfManuscript() != null && manuscriptBean.getDocumentationOfManuscript().ordinal() > 0) {
				queryStrBuffer = queryStrBuffer.append(" and digitalmanuscriptbean.documentationOfManuscript = "+(short)manuscriptBean.getDocumentationOfManuscript().ordinal());
			}
			
			if(specificCategoryIds != null && specificCategoryIds.size() > 0) {
				queryStrBuffer = qryBfrWithSpCategory.append(queryStrBuffer);
				
				StringBuffer buff = new StringBuffer(" and spcfcCat.manuscriptFkId = digitalmanuscriptbean.id AND (");
				StringBuffer buff2 = new StringBuffer();
				
				for(int i=0 ; i<specificCategoryIds.size() ; i++) {
					if(i > 0) {
						buff2.append(" or ");
					}
					buff2.append("spcfcCat.specificcategoryFkId = "+specificCategoryIds.get(i));
				}
				buff2.append(") GROUP BY spcfcCat.manuscriptFkId ");
				buff.append(buff2);
				queryStrBuffer.append(buff);
			} else {
				queryStrBuffer = qryBfrNoSpCategory.append(queryStrBuffer);
			}
			
			String queryStr = queryStrBuffer.toString();		
			String countQueryStr = "select count(digitalmanuscriptbean) "+queryStr;
			//queryStr = queryStr+" order by digitalmanuscriptbean.id asc";
			Query countQuery = session.createQuery(countQueryStr);
			
			queryStr = "select digitalmanuscriptbean "+queryStr;
			query = session.createQuery(queryStr.toString());
			
			if(authorBean.getName() != null && authorBean.getName().length() > 0) {
				query.setParameter("authorName", "%"+authorBean.getName().trim()+"%");
				countQuery.setParameter("authorName", "%"+authorBean.getName().trim()+"%");
			}
			
			if(manuscriptBean.getName() != null && manuscriptBean.getName().length() > 0) {
				query.setParameter("manuscriptName", "%" + manuscriptBean.getName().trim() + "%");
				countQuery.setParameter("manuscriptName", "%" + manuscriptBean.getName().trim() + "%");
			}
			
			/*if(manuscriptBean.getSummary() != null && manuscriptBean.getSummary().length() > 0) {
				query.setParameter("summary", "%" + manuscriptBean.getSummary().trim() + "%");
				countQuery.setParameter("summary", "%" + manuscriptBean.getSummary().trim() + "%");
			}
			
			if(manuscriptBean.getBeginningLine() != null && manuscriptBean.getBeginningLine().length() > 0) {
				query.setParameter("beginningLine", "%" + manuscriptBean.getBeginningLine().trim() + "%");
				countQuery.setParameter("beginningLine", "%" + manuscriptBean.getBeginningLine().trim() + "%");
			}
			
			if(manuscriptBean.getEndingLine() != null && manuscriptBean.getEndingLine().length() > 0) {
				query.setParameter("endingLine", "%" + manuscriptBean.getEndingLine().trim() + "%");
				countQuery.setParameter("endingLine", "%" + manuscriptBean.getEndingLine().trim() + "%");
			}*/
			
			if(orgBean.getName() != null && orgBean.getName().length() > 0) {
				query.setParameter("orgName", "%" + orgBean.getName() + "%");
				countQuery.setParameter("orgName", "%" + orgBean.getName() + "%");
			}
			
			if(manuscriptBean.getTypeOfWork() != null && manuscriptBean.getTypeOfWork().getValue() >= 0) {
				query.setParameter("workType", manuscriptBean.getTypeOfWork());
				countQuery.setParameter("workType", manuscriptBean.getTypeOfWork());
			}
			
			if(manuscriptBean.getDocumentType() != null &&  manuscriptBean.getDocumentType() > 0) {
				query.setParameter("documentTypeId", manuscriptBean.getDocumentType());
				countQuery.setParameter("documentTypeId", manuscriptBean.getDocumentType());
			}
			
			if(manuscriptBean.getLanguageFkId() != null && manuscriptBean.getLanguageFkId() > 0) {
				query.setParameter("languageFkId", manuscriptBean.getLanguageFkId());
				countQuery.setParameter("languageFkId", manuscriptBean.getLanguageFkId());
			}
			
			if(manuscriptBean.getCategoryFkId() != null && manuscriptBean.getCategoryFkId() > 0) {
				query.setParameter("categoryFkId", manuscriptBean.getCategoryFkId());
				countQuery.setParameter("categoryFkId", manuscriptBean.getCategoryFkId());
			}
			
		/*	if(manuscriptBean.getSpecificCategoryFkId() != null && manuscriptBean.getSpecificCategoryFkId() > 0) {
				query.setParameter("specificCategoryFkId", manuscriptBean.getSpecificCategoryFkId());
				countQuery.setParameter("specificCategoryFkId", manuscriptBean.getSpecificCategoryFkId());
			}*/
			
			if(manuscriptBean.getScriptFkId() != null && manuscriptBean.getScriptFkId() > 0) {
				query.setParameter("scriptFkId", manuscriptBean.getScriptFkId());
				countQuery.setParameter("scriptFkId", manuscriptBean.getScriptFkId());
			}
			if(manuscriptBean.getManuscriptId() != null && manuscriptBean.getManuscriptId().length() > 0) {
				query.setParameter("manuscriptId", "%"+manuscriptBean.getManuscriptId()+"%");
				countQuery.setParameter("manuscriptId", "%"+manuscriptBean.getManuscriptId()+"%");
			}
			
			manuscriptBeanList = query.list();
			
			/*for (int i = 0; i < manuscriptBeanList.size(); i++) {
				query = session.createQuery("from DigitalManuscriptFrame frame where frame.digitalManuscriptFkId ="+ manuscriptBeanList.get(i).getId());
				List<DigitalManuscriptFrame> frameList = query.list();
				
				if(frameList != null && frameList.size() >0) {
					manuscriptBeanList.get(i).setPresentFrame(true);
				}else {
					manuscriptBeanList.get(i).setPresentFrame(false);
				}
			
				query = session
						.createQuery("select processMaster.id from CurrentProcessMasterBean processMaster where processMaster.ReferenceFkId = :referenceId");
				query.setParameter("referenceId", manuscriptBeanList.get(i)
						.getId());
				List<Long> idList = new ArrayList<>();
				idList = query.list();
				if (idList.size() <= 0) {
					manuscriptBeanListFree.add(manuscriptBeanList.get(i));
				} else {
					Long id = idList.get(idList.size() - 1);
					query = session
							.createQuery("select dtlsBean from CurrentProcessDetailsBean dtlsBean where "
									+ "dtlsBean.status < 2 and dtlsBean.CurrentProcessMasterFkId ="
									+ id);

					List<CurrentProcessDetailsBean> dtlsBeanList = query.list();
					Long count1 = new Long((long) dtlsBeanList.size());
					if (count1 > 0) {
						boolean isUnderUser = false;
						for (CurrentProcessDetailsBean bean : dtlsBeanList) {
							if (bean.getStatus() == (short) 1
									&& bean.getIsUserRoleId() == (short) 1) {
								isUnderUser = true;

								query = session
										.createQuery("select empl from UserLoginDetailsBean userLoginDtls , LocationUserRolesDetailsBean lur , EmployeeMasterBean empl "
												+ " where lur.userInfoFkId = userLoginDtls.id and empl.id=userLoginDtls.refrenceFkId and lur.id = :locUserRoleId");

								query.setParameter("locUserRoleId",
										bean.getLocationUserRoleFkId());
								EmployeeMasterBean usrLognDtl = (EmployeeMasterBean) query
										.uniqueResult();
								listOfRecordOwners.add(usrLognDtl);
								listOfRecordIds.add(bean.getId());
							}
						}

						if (isUnderUser) {
							manuscriptBeanListUnderWflUser
									.add(manuscriptBeanList.get(i));
						} else {
							manuscriptBeanListUnderWfl.add(manuscriptBeanList
									.get(i));
						}

					} else {
						manuscriptBeanListFree.add(manuscriptBeanList.get(i));
					}
				}
				
			}*/
			
			//if(specificCategoryIds != null && specificCategoryIds.size() > 0) {
				//List<Object> countList =  countQuery.list();
				count = Long.valueOf( manuscriptBeanList.size());
//			} else {
//				count = (Long) countQuery.uniqueResult();
//			}
			
			count = Long.valueOf( manuscriptBeanList.size());
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		} finally {
			session.close();
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("totalCount", count);
		returnMap.put("manuscriptListUndWflUser", manuscriptBeanList);
		returnMap.put("idOfWflProcess", listOfRecordIds);
		
		return returnMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<LanguageBean> findAllLanguages() throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<LanguageBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From LanguageBean bean where bean.isDeleted = 0");
			entities = query.list();
			
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Organisation> findAllOrganisations() throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<Organisation> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From Organisation");
			entities = query.list();
			
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	/**
	 * This method will returns the list of bundles
	 * @author Rakesh kumar sahoo
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BundleMasterBean> findAllBundleNumber() throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<BundleMasterBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From BundleMasterBean");
			entities = query.list();
			
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MaterialBean> findAllMaterial() throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<MaterialBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From MaterialBean");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_LANGUAGES, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthorBean> findAllAuthor() throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<AuthorBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From AuthorBean");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_AUTHORS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_AUTHORS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthorBean> findAllAuthorForTerm(String term, Short authorType) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<AuthorBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From AuthorBean ab where (ab.name like :name or ab.regionalName like :name or ab.diacriticName like :name) and ab.type = :type");
			query.setParameter("name", "%"+term+"%");
			query.setParameter("type", authorType);
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_AUTHORS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_AUTHORS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<DigitalManuscriptBean> findAllManuscriptForTerm(String term) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<DigitalManuscriptBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From DigitalManuscriptBean ab where ab.name like :name or ab.regionalName like :name or ab.diacriticName like :name");
			query.setParameter("name", "%"+term+"%");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_AUTHORS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_AUTHORS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<EmployeeMasterBean> findAllDigitisersForTerm(String term) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<EmployeeMasterBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("select ab From EmployeeMasterBean ab , LocationUserRolesDetailsBean lur , UserLoginDetailsBean userlogin " +
					"where userlogin.refrenceFkId = ab.id and lur.userInfoFkId = userlogin.refrenceFkId " +
					"and lur.roleMasterFkId = 9 and ab.firstName like :name");
			query.setParameter("name", "%"+term+"%");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_DIGITISERS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_DIGITISERS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryBean> findAllCategoryForTerm(String term) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<CategoryBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From CategoryBean bean where bean.name like :name");
			query.setParameter("name", "%"+term+"%");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_CATEGORIES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_CATEGORIES, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<PublisherBean> findAllPublisherForTerm(String term) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<PublisherBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From PublisherBean pb where pb.name like :name");
			query.setParameter("name", "%"+term+"%");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_PUBLISHERS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_PUBLISHERS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<TagMasterBean> findAllTagForTerm(String term) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<TagMasterBean> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From TagMasterBean tag where tag.name like :name");
			query.setParameter("name", "%"+term+"%");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_TAGS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_TAGS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Organisation> findAllOrganisationForTerm(String term) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		List<Organisation> entities = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From Organisation org where org.name like :name");
			query.setParameter("name", "%"+term+"%");
			entities = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_ORGANISATIONS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_ORGANISATIONS, e);
		}finally {
			session.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	public List<ScriptBean> findAllScripts() throws OMDPCoreException {
		
		List<ScriptBean> beans = null;
		
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From ScriptBean bean where bean.isDeleted = 0");
			beans = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_SCRIPTS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_SCRIPTS, e);
		}finally {
			session.close();
		}
		
		return beans;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryBean> findAllCategories() throws OMDPCoreException {
		List<CategoryBean> beans = new ArrayList<CategoryBean>();
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From CategoryBean cb where cb.isDeleted = 0");
			beans = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_CATEGORIES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_CATEGORIES, e);
		}finally {
			session.close();
		}
		
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpecificCategoryBean> findAllSpecificCategories() throws OMDPCoreException {
		List<SpecificCategoryBean> beans = new ArrayList<SpecificCategoryBean>();
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("From SpecificCategoryBean");
			beans = query.list();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_SPECIFIC_CATEGORIES, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_ALL_SPECIFIC_CATEGORIES, e);
		}finally {
			session.close();
		}
		
		return beans;
	}
	
	public PublicationBean findPublicationById(Long id) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		PublicationBean entity = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session.createQuery("from PublicationBean dmb where dmb.id = :id");
			query.setParameter("id", id);
			List<PublicationBean> digitalManuscriptBeans = query.list();
			if(digitalManuscriptBeans != null && digitalManuscriptBeans.size() > 0) {
				entity = digitalManuscriptBeans.get(0);
			}
//			entity = (DigitalManuscriptBean) session.get(DigitalManuscriptBean.class, id);
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		}finally {
			session.close();
		}
		return entity;
	}
	/**
	 * Method for saving/updating the digitial manuscript
	 * @param bean
	 * @param publicationBean
	 * @param TagList
	 * @return DigitalManuscriptBean
	 * @throws OMDPCoreException
	 */
	public DigitalManuscriptBean saveDigitalManuscript(DigitalManuscriptBean bean , PublicationBean publicationBean,List<TagMasterBean> tagList,List<Long>specificCategoryList) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		Query query=null;
		try {
			List<DigitalManuscriptFrame> frameList = new ArrayList<DigitalManuscriptFrame>();
			frameList = bean.getDigitalManuscriptFrames();
			bean.setDigitalManuscriptFrames(null);
			
			if(bean.getParentFKId()!=null && bean.getParentFKId().equals(0L) ) {
				bean.setParentFKId(null);
			}
			
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(bean.getAuthorFkObj());
			bean.setAuthorFKId(bean.getAuthorFkObj().getId());
			
			if(bean.getNmmDetailsFkObj() != null && bean.getNmmDetailsFkObj().getHeight().length() > 0) {
				session.saveOrUpdate(bean.getNmmDetailsFkObj());
				bean.setNmmDetailsFkId(bean.getNmmDetailsFkObj().getId());
			}

			if(bean.getScribeFkObj() != null && (bean.getScribeFkObj().getName() != null && bean.getScribeFkObj().getName().length() > 0)
					|| (bean.getScribeFkObj().getDiacriticName() != null && bean.getScribeFkObj().getDiacriticName().length() > 0)
					|| (bean.getScribeFkObj().getRegionalName() != null && bean.getScribeFkObj().getRegionalName().length() > 0)) {
				session.saveOrUpdate(bean.getScribeFkObj());
				bean.setScribeFkId(bean.getScribeFkObj().getId());
			}
			
			if(bean.getCommentatorFkObj() != null && (bean.getCommentatorFkObj().getName() != null && bean.getCommentatorFkObj().getName().length() > 0)) {
				session.saveOrUpdate(bean.getCommentatorFkObj());
				bean.setCommentatorFkId(bean.getCommentatorFkObj().getId());
			}
			
			if(bean.getTranslatorFkObj() != null && (bean.getTranslatorFkObj().getName() != null && bean.getTranslatorFkObj().getName().length() > 0)) {
				session.saveOrUpdate(bean.getTranslatorFkObj());
				bean.setTranslatorFkId(bean.getTranslatorFkObj().getId());
			}
			
			if(bean.getSubCommentatorFkObj() != null && (bean.getSubCommentatorFkObj().getName() != null && bean.getSubCommentatorFkObj().getName().length() > 0)) {
				session.saveOrUpdate(bean.getSubCommentatorFkObj());
				bean.setSubCommentatorFkId(bean.getSubCommentatorFkObj().getId());
			}
			
			if(publicationBean != null && publicationBean.getPublisherFkObj() != null) {
				publicationBean.setAuthorFkId(bean.getAuthorFkObj().getId());
				if(publicationBean.getPublisherFkObj() != null){
					session.saveOrUpdate(publicationBean.getPublisherFkObj());
					publicationBean.setPublisherFkId(publicationBean.getPublisherFkObj().getId());
					if(publicationBean.getEditorFkObj() != null && publicationBean.getEditorFkObj().getName() != null && publicationBean.getEditorFkObj().getName().length() > 0) {
						session.saveOrUpdate(publicationBean.getEditorFkObj());
						publicationBean.setEditorFkId(publicationBean.getEditorFkObj().getId());
					} else {
						publicationBean.setEditorFkId(null);
					}
					session.saveOrUpdate(publicationBean);
					bean.setPublicationFKId(publicationBean.getId());
				}
			}
			
			if(bean.getOrganisationFkObj() != null && bean.getOrganisationFkObj().getName() != null && bean.getOrganisationFkObj().getName().length() > 0) {
				session.saveOrUpdate(bean.getOrganisationFkObj());
				bean.setOrganisationFkId(bean.getOrganisationFkObj().getId());
			} else {
				bean.setOrganisationFkId(null);
			}
			session.saveOrUpdate(bean);
			
			List<TagMasterBean> tagListSave=new ArrayList<TagMasterBean>();
			
			query=session.createQuery("delete ManuscriptSpecificcategoryMapper maper where maper.manuscriptFkId= :id");
			query.setParameter("id", bean.getId());
			query.executeUpdate();
			query=null;
			
			ManuscriptSpecificcategoryMapper specificCategoryMapper= null;
			for(Long specificCategoryId : specificCategoryList){
				specificCategoryMapper= new ManuscriptSpecificcategoryMapper();
				specificCategoryMapper.setManuscriptFkId(bean.getId());
				specificCategoryMapper.setSpecificcategoryFkId(specificCategoryId);
				session.save(specificCategoryMapper);
			}
			query=session.createQuery("delete ManuscriptTagMapper mapper where mapper.manuscriptFkId= :id");
			query.setParameter("id", bean.getId());
			query.executeUpdate();
			
			/*List<TagMasterBean> tagListSave=new ArrayList<TagMasterBean>();
			for(TagMasterBean tg : tagList){
				if(tg.getId() == 0){
					tagListSave.add(tg);
				}
			}*/
			
			for(TagMasterBean tg : tagList){
				if(tg .getId() <= 0){
					tg .setId(null);
					session.save(tg);
				}
				//session.saveOrUpdate(tg);
			}
		
			ManuscriptTagMapper mapperBean= null;
			for(TagMasterBean tg : tagList){
				mapperBean= new ManuscriptTagMapper();
				mapperBean.setManuscriptFkId(bean.getId());
				mapperBean.setTagsFkId(tg.getId());
				session.save(mapperBean);
			}
			tx.commit();
			if(frameList != null && frameList.size() > 0)
				saveFramesForDocument(bean, frameList, session);
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	
	public DigitalManuscriptBean saveFramesForDocument(DigitalManuscriptBean bean ,List<DigitalManuscriptFrame> frameList,Session session) throws OMDPCoreException, IOException , Exception {
		Transaction tx = null;
		tx = session.beginTransaction();
		String folderPath = ""; //Path where the Directory need to be created for saving the images.
		
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR));
		String month =  String.valueOf(now.get(Calendar.MONTH)+1);
		String day =  String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		
		folderPath = "/"+year+"/"+month+"/"+day+"/"+bean.getId() ;
		bean.setDigitalManuscriptFrames(frameList);
		bean = new ManuscriptMasterServiceImpl().convertFilePaths(bean, folderPath);
		
		//session.update(bean);
	//	session.saveOrUpdate(bean.getDigitalManuscriptFrames());
		
		for(int i= 0 ; i<bean.getDigitalManuscriptFrames().size() ; i++)  {
			session.saveOrUpdate(bean.getDigitalManuscriptFrames().get(i));
		}
		tx.commit();
		return bean;
	}
	
	/**
	 * Save or update the digital document
	 * 
	 * @author Deba Prasad
	 * @date 22nd May 2014
	 * 
	 * This method will now only trigger the workflow to  next label.The saving of digital document and digital document details records is happening
	 * by the AJAX call and removed from here only to get a clear separation between document details save and and workflow progress.
	 * 
	 * TODO : The name of the method should be changed and the method need to be cleaned by removing the unnecessary code later.
	 * 
	 * @param bean
	 * @return
	 * @throws OMDPCoreException
	 */
	public DigitalDocumentBean saveOrUpdateDigitalDocument(DigitalDocumentBean bean ,boolean isWFLForwarded) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		Criteria criteria = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Long versionNumber = 0L;
			
			/* Retrieving the current max version number for the document */
			if(bean.getId() != null && bean.getId() > 0) {
				criteria = session.createCriteria(DigitalDocumentDetailsBean.class)
						.add(Restrictions.eq("digitalDocumentFkId", bean.getId()))
						.setProjection(Projections.max("version"));
				versionNumber = (Long) criteria.uniqueResult();
				if(versionNumber == null) {
					versionNumber = 0L;
				}
			}
			
			
			bean.getDocumentDetailsBean().setId(null);
			bean.getDocumentDetailsBean().setVersion(++versionNumber); //Incrementing version
			
			if(bean.getDigitalManuscriptFkId() != null && bean.getDigitalManuscriptFkId() > 0) {
			//	session.saveOrUpdate(bean);
			}
			//if(isWFLForwarded && bean.getDigitalManuscriptFkId() != null) {
				boolean status = new WorkflowCoreDAOImpl().triggerForSubmit(bean.getDigitalManuscriptFkId(), session);
			//}
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	
	public boolean deleteManuscriptRecord(Long id) throws OMDPCoreException {
		boolean status = false;		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("from DigitalManuscriptBean e where e.id = :id");
			query.setParameter("id", id);
			DigitalManuscriptBean bean = (DigitalManuscriptBean) query.uniqueResult();
			if(bean.getRecordStatus().getValue()<0){
			bean.setIsDeleted((short)1);
			if(bean.getPublicationFKObj() != null)
				bean.getPublicationFKObj().setIsDeleted((short)1);
			}else{
				throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_DELETE_WORKFLOW_MANUSCRIPT_DETAILS);
			}
			tx.commit();
			status = true;
		} catch (OMDPCoreException oe) {
			tx.rollback();
			throw oe;
		}
		catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_DELETE_MANUSCRIPT_DETAILS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_DELETE_MANUSCRIPT_DETAILS, e);
		}finally {
			session.close();
		}
		return status;
	}
	
	//Method to check the Manuscript status
	public short getManuscriptStatus(Long id) throws OMDPCoreException{
		Session session = null;
		Transaction tx = null;
		short status ;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			query = session.createQuery("from DigitalManuscriptBean e where e.id = :id");
			query.setParameter("id", id);
			DigitalManuscriptBean bean = (DigitalManuscriptBean) query.uniqueResult();
			status = bean.getRecordStatus().getValue();
		}catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_STATUS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_STATUS, e);
		}finally {
			session.close();
		}
		return status;
	}
	
	public Long findNoOfRecordsByDocumentType(Long type) throws OMDPCoreException {
		Long numberOfRecords = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = null;
			
			query = session.createQuery("select count(e) from DigitalManuscriptBean e where e.documentType = :type and e.isDeleted = 0");
			query.setParameter("type", type);
			numberOfRecords = (Long) query.uniqueResult();

			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		}finally {
			session.close();
		}
		return numberOfRecords;
	}
	
	public List <Long> findNoOfRecordsForDifferentStatus(Long type) throws OMDPCoreException {
		Long numberOfRecords = null;
		Session session = null;
		Transaction tx = null;
		List <Long> differentStatusCount = new ArrayList<Long>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();
			Query query = null;
			for(Short status = 0; status<=5; status++){
				DocumentStatusEnum enumStatus =	DocumentStatusEnum.fromValue(status);
				query = session.createQuery("select count(e) from DigitalManuscriptBean e where e.isDeleted = 0 and e.recordStatus = :status");
				//query.setParameter("type", type);
				query.setParameter("status", enumStatus);
				numberOfRecords = (Long) query.uniqueResult();
				differentStatusCount.add(numberOfRecords);
			}
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		}finally {
			session.close();
		}
		return differentStatusCount;
	}
	/**
	 * This method will returns total number of records for a perticular Acronym 
	 * @author Rakesh kumar sahoo
	 * @param String Acronym, String Organization name
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findNumberOfRecords(String acronym,String name) throws OMDPCoreException{
		Long i = null;
		String numberOfRecords;
		Session session = null;
		Transaction tx = null;
		try {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		Query query = null;
		query=session.createSQLQuery(ManuscriptQueriesConstant.GET_TOTAL_NO_OF_RECORDS_FOR_ACCRONYM);
		query.setParameter("param", acronym+"%");
		List<Object> result=query.list();
		Object[] fields=(Object[]) result.get(0);
		BigInteger iBig=(BigInteger) fields[0];
		i = iBig.longValue();
		if(i>0){
			if(!name.equals(fields[1])){
				throw new OMDPCoreException(OMDPCoreException.DUPLICATE_ACRONYM);
			}
		}
		numberOfRecords=String.format(IndvenApplicationConstants.MANUSCRIPT_ID_LENGTH , i+1);
		
		tx.commit();
		}catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.DUPLICATE_ACRONYM, e);
		}finally {
			session.close();
		}
		return numberOfRecords;

	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object>  findById(Long id) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		DigitalManuscriptBean entity = null;
		Query query1 = null;
		Map<String, Object> returnMap;
		Query query2=null;
		List<DocumentCommentBean> frameCommentsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session.createQuery("from DigitalManuscriptBean dmb where dmb.id = :id");
			query.setParameter("id", id);

			List<DigitalManuscriptBean> digitalManuscriptBeans = query.list();
			if(digitalManuscriptBeans != null && digitalManuscriptBeans.size() > 0) {
				entity = digitalManuscriptBeans.get(0);
				
				Hibernate.initialize(entity.getDigitalManuscriptFrames());
				for(int i=0 ; i<entity.getDigitalManuscriptFrames().size() ; i++) {
					//Hibernate.initialize(entity.getDigitalManuscriptFrames().get(i).getFrameCommentsList());
					frameCommentsList = new ArrayList<DocumentCommentBean>();
					query2 = session.createQuery("from DocumentCommentBean cmntBean where cmntBean.frameId = :frameId and cmntBean.manuscriptId = :manuscriptId");
					query2.setParameter("frameId",entity.getDigitalManuscriptFrames().get(i).getId());
					query2.setParameter("manuscriptId",entity.getId());
					frameCommentsList = query2.list();
					entity.getDigitalManuscriptFrames().get(i).setFrameCommentsList(frameCommentsList);
				}
			}
			
//			entity = (DigitalManuscriptBean) session.get(DigitalManuscriptBean.class, id);
			query1=session.createQuery("from ManuscriptTagMapper mtm where mtm.manuscriptFkId = :manuacriptId");
			query1.setParameter("manuacriptId", id);
			List<ManuscriptTagMapper> manuscriptTagMappers = query1.list();

			query2=session.createQuery("from ManuscriptSpecificcategoryMapper msc where msc.manuscriptFkId = :manuacriptId");
			query2.setParameter("manuacriptId", id);
			List<ManuscriptSpecificcategoryMapper> specificCategoryMapper = query2.list();
		    returnMap = new HashMap<String, Object>();
			returnMap.put("manuscriptBean", entity);
			returnMap.put("tagList", manuscriptTagMappers);
			returnMap.put("specificCategoryList", specificCategoryMapper);
			String digitiserName = "";
			if(entity.getDigitizerId() != null && entity.getDigitizerId() > 0) {
				query = session.createQuery("select e.firstName from EmployeeMasterBean e where e.id=:digitiserId");
				query.setParameter("digitiserId", entity.getDigitizerId());
				digitiserName = (String) query.uniqueResult();
			}
			returnMap.put("digitiserName", digitiserName);
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		}finally {
			session.close();
		}
		return returnMap;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object>  findByIdTrans(Long id) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		DigitalManuscriptBean entity = null;
		Query query1 = null;
		Map<String, Object> returnMap;
		Query query2=null;
		List<DocumentCommentBean> frameCommentsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			query = session.createQuery("from DigitalManuscriptBean dmb where dmb.id = :id");
			query.setParameter("id", id);

			List<DigitalManuscriptBean> digitalManuscriptBeans = query.list();
			if(digitalManuscriptBeans != null && digitalManuscriptBeans.size() > 0) {
				entity = digitalManuscriptBeans.get(0);
				
				Hibernate.initialize(entity.getDigitalManuscriptFrames());
				for(int i=0 ; i<entity.getDigitalManuscriptFrames().size() ; i++) {
					frameCommentsList = new ArrayList<DocumentCommentBean>();
					query2 = session.createQuery("from DocumentCommentBean cmntBean where cmntBean.frameId = :frameId and cmntBean.manuscriptId = :manuscriptId");
					query2.setParameter("frameId",entity.getDigitalManuscriptFrames().get(i).getId());
					query2.setParameter("manuscriptId",entity.getId());
					frameCommentsList = query2.list();
					entity.getDigitalManuscriptFrames().get(i).setFrameCommentsList(frameCommentsList);
				}

				if(digitalManuscriptBeans.get(0).getParentFkObj() != null) {
					Hibernate.initialize(digitalManuscriptBeans.get(0).getParentFkObj().getParentFkObj().getDigitalManuscriptFrames());
					
					for(int i=0 ; i<digitalManuscriptBeans.get(0).getParentFkObj().getParentFkObj().getDigitalManuscriptFrames().size() ; i++) {
						//Hibernate.initialize(digitalManuscriptBeans.get(0).getParentFkObj().getParentFkObj().getDigitalManuscriptFrames().get(i).getFrameCommentsList());
						frameCommentsList = new ArrayList<DocumentCommentBean>();
						query2 = session.createQuery("from DocumentCommentBean cmntBean where cmntBean.frameId = :frameId and cmntBean.manuscriptId = :manuscriptId");
						query2.setParameter("frameId",digitalManuscriptBeans.get(0).getParentFkObj().getParentFkObj().getDigitalManuscriptFrames().get(i).getId());
						query2.setParameter("manuscriptId",entity.getId());
						frameCommentsList = query2.list();
						digitalManuscriptBeans.get(0).getParentFkObj().getParentFkObj().getDigitalManuscriptFrames().get(i).setFrameCommentsList(frameCommentsList);
					}
				}
					
			}
			
//			entity = (DigitalManuscriptBean) session.get(DigitalManuscriptBean.class, id);
			query1=session.createQuery("from ManuscriptTagMapper mtm where mtm.manuscriptFkId = :manuacriptId");
			query1.setParameter("manuacriptId", id);
			List<ManuscriptTagMapper> manuscriptTagMappers = query1.list();
			returnMap = new HashMap<String, Object>();
			returnMap.put("manuscriptBean", entity);
			returnMap.put("tagList", manuscriptTagMappers);

//			query2=session.createQuery("from ManuscriptSpecificcategoryMapper msc where msc.manuscriptFkId = :manuacriptId");
//			query2.setParameter("manuacriptId", id);
//			List<ManuscriptSpecificcategoryMapper> specificCategoryMapper = query2.list();
//			returnMap.put("specificCategoryList", specificCategoryMapper);
			/*String digitiserName = "";
			if(entity.getDigitizerId() != null && entity.getDigitizerId() > 0) {
				query = session.createQuery("select e.firstName from EmployeeMasterBean e where e.id=:digitiserId");
				query.setParameter("digitiserId", entity.getDigitizerId());
				digitiserName = (String) query.uniqueResult();
			}
			returnMap.put("digitiserName", digitiserName);*/
			
			
			Long originalWorkId = entity.getParentFkObj().getParentFKId();
			DigitalDocumentBean bean = new DigitalDocumentBean();
				/* Sub query to order records by version number of child bean (DocumentDetailsBean)
				 * and select record with max version */
			Criteria criteria = session.createCriteria(DigitalDocumentBean.class)
					.add(Restrictions.eq("digitalManuscriptFkId", originalWorkId)).add(Restrictions.isNull("digitalManuscriptFrameFkId"))
					.createAlias("documentDetailsBean", "details")
					.addOrder(Order.desc("details.version"))
					.setMaxResults(1);
				
			bean = (DigitalDocumentBean) criteria.uniqueResult();
			returnMap.put("originalRecordDoc", bean);
			
			
			query = session.createQuery("from DigitalDocumentBean docBean where docBean.digitalManuscriptFkId = :manScrId and docBean.digitalManuscriptFrameFkId = null and docBean.language = :transLanguage");
			//bean = (DigitalDocumentBean) session.get(DigitalDocumentBean.class, id);
			query.setParameter("manScrId", id);
			query.setParameter("transLanguage", entity.getLanguage());
			
			DigitalDocumentBean beanTrans = new DigitalDocumentBean();
			beanTrans = (DigitalDocumentBean) query.uniqueResult();
			returnMap.put("TransRecordDoc", beanTrans);
			
			
			
			List<DigitalManuscriptFrame> frames = new ArrayList<DigitalManuscriptFrame>();
			List<String> translatedDocsDtls = new ArrayList<String>();
			
			DigitalDocumentBean transcribedFrameDocBean = new DigitalDocumentBean();
			DigitalDocumentBean translatedDocBean = new DigitalDocumentBean();
			
			for(DigitalManuscriptFrame frame : entity.getParentFkObj().getParentFkObj().getDigitalManuscriptFrames()) {
				transcribedFrameDocBean = new DigitalDocumentBean();
				criteria = session.createCriteria(DigitalDocumentBean.class)
						.add(Restrictions.eq("digitalManuscriptFrameFkId", frame.getId())).add(Restrictions.eq("digitalManuscriptFkId",entity.getParentFkObj().getParentFKId()))
						.createAlias("documentDetailsBean", "details")
						.addOrder(Order.desc("details.version"))
						.setMaxResults(1);
				
				transcribedFrameDocBean = (DigitalDocumentBean) criteria.uniqueResult();
				frame.setDigitalDocumentBean(transcribedFrameDocBean);
				
				//query = session.createQuery("from DigitalDocumentBean docBean , DigitalDocumentDetailsBean docDtls where docBean.digitalManuscriptFkId = :manScrId and docBean.digitalManuscriptFrameFkId = :frameId order by docDtls.version DESC");
				criteria = session.createCriteria(DigitalDocumentBean.class)
						.add(Restrictions.eq("digitalManuscriptFkId", id)).add(Restrictions.eq("digitalManuscriptFrameFkId", frame.getId()))
						.createAlias("documentDetailsBean", "details")
						.addOrder(Order.desc("details.version"))
						.setMaxResults(1);
				
//				query.setParameter("manScrId", id);
//				query.setParameter("frameId", frame.getId());
				translatedDocBean = (DigitalDocumentBean) criteria.uniqueResult();
				String str;
				if(translatedDocBean != null && translatedDocBean.getDocumentDetailsBean() != null) {
					str = translatedDocBean.getDocumentDetailsBean().getText();
					translatedDocsDtls.add(translatedDocBean.getDocumentDetailsBean().getText());
				}else {
					str = "";
					translatedDocsDtls.add("");
				}
				frame.setTranslatedText(str);
				frames.add(frame);
			}
			returnMap.put("TranscribesFramesDocs", frames);
			returnMap.put("TranslatedTexts", translatedDocsDtls);
			
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, e);
		}finally {
			session.close();
		}
		return returnMap;
	}
	
	
	
	
	
	public DigitalManuscriptBean fetchRecordForTranslation(Long id) {
		DigitalManuscriptBean bean = new DigitalManuscriptBean();
		
		return bean;
	}
	
	/**
	 * Searches for the digital document 
	 * Based the manuscript id
	 * @param id
	 * @return
	 * @throws OMDPCoreException
	 */
	public DigitalDocumentBean findDocumentByManuscriptId(Long id) throws OMDPCoreException {
		DigitalDocumentBean bean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			/* Sub query to order records by version number of child bean (DocumentDetailsBean)
			 * and select record with max version */
			Criteria criteria = session.createCriteria(DigitalDocumentBean.class)
					.add(Restrictions.eq("digitalManuscriptFkId", id)).add(Restrictions.isNull("digitalManuscriptFrameFkId"))
					.createAlias("documentDetailsBean", "details")
					.addOrder(Order.desc("details.version"))
					.setMaxResults(1);
			
			bean = (DigitalDocumentBean) criteria.uniqueResult();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	
	
	public DigitalDocumentBean findDocumentByManuscriptIdTranslated(Long id) throws OMDPCoreException {
		DigitalDocumentBean bean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		Query query=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			query = session.createQuery("from DigitalDocumentBean docBean where docBean.digitalManuscriptFkId = :manScrId and docBean.digitalManuscriptFrameFkId = null");
			//bean = (DigitalDocumentBean) session.get(DigitalDocumentBean.class, id);
			query.setParameter("manScrId", id);
			bean = (DigitalDocumentBean) query.uniqueResult();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	

	public boolean assignWorkToReviewer(WorkflowCoreDataPacket workflowDet) throws OMDPCoreException , WorkFlowCoreException {
		boolean status = false;
		DigitalDocumentBean bean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			/**
			 * Write the code to perform the logic on manuscript record when reviewer assigns the work to himself. Call the process workflow method
			 * to assign the record for the particular user. 
			 */
			new WorkflowCoreDAOImpl().processWorkflow(workflowDet, session);
			
			tx.commit();
		} catch(WorkFlowCoreException e) {
			throw new WorkFlowCoreException(WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW);
		}catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, e);
		}finally {
			session.close();
		}
		return status;
	}
	
	/**
	 * Searches for digital document
	 * Based on frame id
	 * @param id
	 * @return
	 * @throws OMDPCoreException
	 */
	public DigitalDocumentBean findDocumentByFrameId(Long id) throws OMDPCoreException {
		DigitalDocumentBean bean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			/* Sub query to order records by version number of child bean (DocumentDetailsBean)
			 * and select record with max version */
			Criteria criteria = session.createCriteria(DigitalDocumentBean.class)
					.add(Restrictions.eq("digitalManuscriptFrameFkId", id))
					.createAlias("documentDetailsBean", "details")
					.addOrder(Order.desc("details.version"))
					.setMaxResults(1);
			
			bean = (DigitalDocumentBean) criteria.uniqueResult();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	
	
	public DigitalDocumentBean findDocumentByFrameIdTrans(Long id) throws OMDPCoreException {
		DigitalDocumentBean bean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		Query query=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			query = session.createQuery("from DigitalDocumentBean docBean where docBean.digitalManuscriptFkId = :manScrId and docBean.digitalManuscriptFrameFkId = null");
			//bean = (DigitalDocumentBean) session.get(DigitalDocumentBean.class, id);
			query.setParameter("manScrId", id);
			bean = (DigitalDocumentBean) query.uniqueResult();
			
			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_DOCUMENT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	
	
	
	public String framePartialSave(String text,Long frameId,Long manuscriptId,int type,String attachmentFilePath) throws OMDPCoreException{
		DigitalDocumentBean ddBean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		Query query=null;
		Criteria criteria = null;
		String queryString;
		Short isMax = 1;
		Short recordType = 0;
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		if(type == 0){
			  //set all the freme islast value to 0
		    query= session.createQuery("update DigitalManuscriptFrame set isLast = :flage" +
    				" where isLast = :existFlage and digitalManuscriptFkId = :manuscriptId");
		    query.setParameter("flage",(short)0);
		    query.setParameter("existFlage",(short)1);
            query.setParameter("manuscriptId",manuscriptId );
            query.executeUpdate();
            query=null;
            
            //set the updated freme islast value to 1
		    query= session.createQuery("update DigitalManuscriptFrame set isLast = 1" +
    				"where id = :frameId");
            query.setParameter("frameId",frameId);
            query.executeUpdate();
            queryString="select ddb.id from DigitalDocumentBean ddb where ddb.digitalManuscriptFrameFkId = :id";
		}else{
			queryString="select ddb.id from DigitalDocumentBean ddb where ddb.digitalManuscriptFkId = :id and ddb.digitalManuscriptFrameFkId is null";
		}
		query = session.createQuery(queryString);
		query.setParameter("id", frameId);
		Long id =  (Long) query.uniqueResult();
		query=null;
		if(id== null || id<=0){
			ddBean = new DigitalDocumentBean();
			ddBean.setId(null);
			if(type == 0){
				ddBean.setDigitalManuscriptFrameFkId(frameId);
			}
			ddBean.setDigitalManuscriptFkId(manuscriptId);
			ddBean.setRecordType(recordType);
			session.saveOrUpdate(ddBean);
			id=ddBean.getId();
		}else{
			ddBean = new DigitalDocumentBean();
			ddBean.setId(id);
		}
		DigitalDocumentDetailsBean dddBean = new DigitalDocumentDetailsBean();
		/* Retrieving the current max version number for the document */
		Long versionNumber = 0L;
		if(ddBean.getId() != null && ddBean.getId() > 0) {
			 //set the updated digitalDocumentDetails previous isMax value to 0
		    query= session.createQuery("update DigitalDocumentDetailsBean set isMax = 0" +
    				"where digitalDocumentFkId = :documentId");
            query.setParameter("documentId",ddBean.getId());
            query.executeUpdate();
			criteria = session.createCriteria(DigitalDocumentDetailsBean.class)
					.add(Restrictions.eq("digitalDocumentFkId", ddBean.getId()))
					.setProjection(Projections.max("version"));
			versionNumber = (Long) criteria.uniqueResult();
			if(versionNumber == null) {
				versionNumber = 0L;
			}
		}if(attachmentFilePath!=null && !attachmentFilePath.equals("")){
			dddBean.setAttachmentFilePath(attachmentFilePath);
		}
		    dddBean.setId(null);
	        dddBean.setVersion(++versionNumber);
		    dddBean.setDigitalDocumentBean(ddBean);
			dddBean.setText(text);
			dddBean.setIsMax(isMax);
			CustomBeanUtil.setBaseValues(dddBean, true);
		    session.saveOrUpdate(dddBean);
		    
		tx.commit();
		}catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, e);
		}finally {
			session.close();
		}
		return null;
	}
	
	public DocumentCommentBean saveCommentForFrame(DocumentCommentBean bean) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			if(bean.getId() != null && bean.getId() <= 0) {
				bean.setId(null);
			}
			session.saveOrUpdate(bean);
			tx.commit();
		}catch(Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_COMMENT);
		} finally {
			session.close();
		}
		return bean;
	}
	
	public String deleteFrame(String[] frameIds) throws OMDPCoreException{
		String status="ERROR";
		Session session = null;
		Transaction tx = null;
		Query query = null;
		Long frameId;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			for(int i = 0;i<frameIds.length;i++){
			frameId = Long.parseLong(frameIds[i]);
			query = session.createQuery("select dd.id from DigitalDocumentBean dd where dd.digitalManuscriptFrameFkId = :id");
			query.setParameter("id", frameId);
			Long digitalDocumentId =  (Long) query.uniqueResult();
			if(digitalDocumentId!=null && digitalDocumentId>0){
			//Deleting all the record in digitalDocumentDetail for the digitalDocumentId
			query = session.createQuery("delete from DigitalDocumentDetailsBean ddb where ddb.digitalDocumentFkId = :documentId");
			query.setParameter("documentId", digitalDocumentId);
			query.executeUpdate();
			
			//Deleting the record in digitalDocument for the digitalDocumentId
			query = session.createQuery("delete from DigitalDocumentBean where id = :digitalDocumentId");
			query.setParameter("digitalDocumentId", digitalDocumentId);
			query.executeUpdate();
			}
			//Deleting the record from comment table 
			query = session.createQuery("delete from DocumentCommentBean dcb where dcb.frameId = :frameId");
			query.setParameter("frameId", frameId);
			query.executeUpdate();
			
			//Deleting the record in frame  for the frameId
			query = session.createQuery("delete from DigitalManuscriptFrame where id = :id");
			query.setParameter("id", frameId);
			query.executeUpdate();
		}
		tx.commit();
		status="SUCCESS";
		}catch(Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_DELETE_FRAMES);
		} finally {
			session.close();
		}
		return status;
	}
	
	public boolean createTranslateRecordAndSaveInfo(String language , String script , Long transcribedManId) throws OMDPCoreException {
		boolean status = false;
		Session session = null;
		Transaction tx = null;
		Query query = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			//DigitalManuscriptBean transcribedBean = (DigitalManuscriptBean) query.uniqueResult();
			
			DigitalManuscriptBean transcribedBean = (DigitalManuscriptBean)session.get(DigitalManuscriptBean.class, transcribedManId);
			System.out.println(transcribedBean.getName());
			
			DigitalManuscriptBean translatedBean = new DigitalManuscriptBean()	;
			DigitalManuscriptAssembler.copyObjToNewState(translatedBean, transcribedBean);
			translatedBean.setManuscriptType(ManuscriptTypeEnum.Translation);
			translatedBean.setRecordStatus(DocumentStatusEnum.Under_Translator);
			translatedBean.setLanguage(language);
			
			
			query = session.createQuery("from LanguageBean bean where bean.unicodePoint = :mappedLangIdStr");
			query.setParameter("mappedLangIdStr", language);
			LanguageBean lang = (LanguageBean) query.uniqueResult();
			translatedBean.setLanguageFkId(lang.getId());
			
			session.save(translatedBean);
			
			DigitalDocumentBean digitalDocument = new DigitalDocumentBean();
			digitalDocument.setDigitalManuscriptFkId(translatedBean.getId());
			digitalDocument.setLanguage(language);
			digitalDocument.setScript(script);
			digitalDocument.setRecordType((short)1);
			
			session.save(digitalDocument);
			
			Long id = translatedBean.getId();
			String name = translatedBean.getName();
			
			CurrentProcessMasterBean processMasterBean = new CurrentProcessMasterBean();
			processMasterBean.setReferenceFkId(id);
			processMasterBean.setDescription("");
			processMasterBean.setLocationMasterFkId(1L);
			processMasterBean.setProcessMasterFkId(1L);
			processMasterBean.setName("Translate Manuscript");
			
			List<CurrentProcessDetailsBean> processDetailsList = new ArrayList<CurrentProcessDetailsBean>();
			
			CurrentProcessDetailsBean processDtls1 = new CurrentProcessDetailsBean();
			processDtls1.setUrl("workflowWorkTranslator.action?id="+id);
			processDtls1.setStatus((short)1);
			processDtls1.setLevel(10L);
			processDtls1.setLocationUserRoleFkId(IndvenApplicationConstants.TRANSLATOR_ROLE_ID);
			processDtls1.setScreenName(name);
			processDtls1.setIsReturnButton(false);
			
			processDtls1.setProcessTimeOut((short)90);
			processDtls1.setIsUserRoleId((short)0);
			processDtls1.setIsTerminateButton(true);
			processDtls1.setIsSaveButton(true);
			processDtls1.setIsAuthorizeButton(true);
			
			
			processDetailsList.add(processDtls1);
			
			CurrentProcessDetailsBean processDtls2 = new CurrentProcessDetailsBean();
			processDtls2.setUrl("workflowWorkTranslator.action?id="+id);
			processDtls2.setStatus((short)0);
			processDtls2.setLevel(20L);
			processDtls2.setLocationUserRoleFkId(IndvenApplicationConstants.REVIEWER_ROLE_ID);
			processDtls2.setScreenName(name);
			processDtls2.setIsReturnButton(true);
			
			processDtls2.setProcessTimeOut((short)90);
			processDtls2.setIsUserRoleId((short)0);
			processDtls2.setIsTerminateButton(true);
			processDtls2.setIsSaveButton(true);
			processDtls2.setIsAuthorizeButton(true);
			processDetailsList.add(processDtls2);
			
			
			CurrentProcessDetailsBean processDtls3 = new CurrentProcessDetailsBean();
			processDtls3 = new CurrentProcessDetailsBean();
			processDtls3.setUrl("workflowWorkTranslator.action?id="+id);
			processDtls3.setStatus((short)0);
			processDtls3.setLevel(30L);
			processDtls3.setLocationUserRoleFkId(IndvenApplicationConstants.PUBLISHER_ROLE_ID);
			processDtls3.setScreenName(name);
			processDtls3.setIsReturnButton(true);
			//processDtls3.setCurrentProcessMasterFkId(bean.getId());
			
			processDtls3.setProcessTimeOut((short)90);
			processDtls3.setIsUserRoleId((short)0);
			processDtls3.setIsTerminateButton(true);
			processDtls3.setIsSaveButton(true);
			processDtls3.setIsAuthorizeButton(true);
			
			processDetailsList.add(processDtls3);
			
			session.save(processMasterBean);
			for(int i=0 ; i<processDetailsList.size() ; i++) {
				processDetailsList.get(i).setCurrentProcessMasterFkId(processMasterBean.getId());
				session.save(processDetailsList.get(i));
			}
			
			tx.commit();
			status = true;
		}catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_PROCESS_FOR_TRANSLATION);
		} finally {
			session.close();
		}
		return status;
	}
	
	
	public String framePartialSaveTrans(String text,Long docOrframeId,Long manuscriptId,int type,String language) throws OMDPCoreException{
		DigitalDocumentBean ddBean = new DigitalDocumentBean();
		Session session = null;
		Transaction tx = null;
		Query query=null;
		Criteria criteria = null;
		String queryString;
		Short isMax = 1;
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		
		if(type == 0){
            queryString="select ddb.id from DigitalDocumentBean ddb where ddb.digitalManuscriptFrameFkId = :id and ddb.digitalManuscriptFkId="+manuscriptId;
		}else{
			docOrframeId = manuscriptId;
			queryString="select ddb.id from DigitalDocumentBean ddb where ddb.digitalManuscriptFkId = :id and ddb.digitalManuscriptFrameFkId is null";
		}
		
		query = session.createQuery(queryString);
		query.setParameter("id", docOrframeId);
		Long id =  (Long) query.uniqueResult();
		query=null;
		if(id== null || id<=0){
			ddBean = new DigitalDocumentBean();
			ddBean.setId(null);
			if(type == 0){
				ddBean.setDigitalManuscriptFrameFkId(docOrframeId);
			}
			ddBean.setDigitalManuscriptFkId(manuscriptId);
			ddBean.setRecordType(isMax);
			ddBean.setLanguage(language);

			session.saveOrUpdate(ddBean);
			id=ddBean.getId();
		}else{
			ddBean = new DigitalDocumentBean();
			ddBean.setId(id);
		}
		DigitalDocumentDetailsBean dddBean = new DigitalDocumentDetailsBean();
		/* Retrieving the current max version number for the document */
		Long versionNumber = 0L;
		if(ddBean.getId() != null && ddBean.getId() > 0) {
			 //set the updated digitalDocumentDetails previous isMax value to 0
		    query= session.createQuery("update DigitalDocumentDetailsBean set isMax = 0" +
    				"where digitalDocumentFkId = :documentId");
            query.setParameter("documentId",ddBean.getId());
            query.executeUpdate();
			criteria = session.createCriteria(DigitalDocumentDetailsBean.class)
					.add(Restrictions.eq("digitalDocumentFkId", ddBean.getId()))
					.setProjection(Projections.max("version"));
			versionNumber = (Long) criteria.uniqueResult();
			if(versionNumber == null) {
				versionNumber = 0L;
			}
		}
		    dddBean.setId(null);
	        dddBean.setVersion(++versionNumber);
		    dddBean.setDigitalDocumentBean(ddBean);
			dddBean.setText(text);
			dddBean.setIsMax(isMax);
			CustomBeanUtil.setBaseValues(dddBean, true);
		    session.saveOrUpdate(dddBean);
		    
		tx.commit();
		}catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, e);
		}finally {
			session.close();
		}
		return null;
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<LanguageBean> getAvailableLanguages(Long transcriptedManId) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		Query query=null;
		List<LanguageBean> languageList = new ArrayList<LanguageBean>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			query = session.createQuery("select lang from LanguageBean lang , DigitalManuscriptBean digMan where digMan.parentFKId = :transcriptedId and digMan.isDeleted = :isDelet and lang.id = digMan.languageFkId");
			query.setParameter("transcriptedId", transcriptedManId);
			query.setParameter("isDelet",(short)0);
			languageList = query.list();
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_PROCESS_FOR_TRANSLATION, e);
		}
		
		return languageList;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> mergeManuscript(List<Long> manuscriptIds) throws OMDPCoreException {
		List<DigitalManuscriptBean> manuscriptBeans = new ArrayList<DigitalManuscriptBean>();
		List<ManuscriptTagMapper> manuscriptTagMappers = new ArrayList<ManuscriptTagMapper>();
		List<ManuscriptSpecificcategoryMapper> specificCategoryMapper =  new ArrayList<ManuscriptSpecificcategoryMapper>();
		DigitalManuscriptBean bean = null;
		Session session = null;
		Transaction tx = null;
		Query query1 = null;
		Query query2 = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			/*for(Long manId : manuscriptIds) {
				bean = new DigitalManuscriptBean();
				bean = (DigitalManuscriptBean)session.get(DigitalManuscriptBean.class, manId);
				Hibernate.initialize(bean.getDigitalManuscriptFrames());
				
				manuscriptBeans.add(bean);
			}*/
			long type = 0;
			for(int i=0;i<manuscriptIds.size();i++) {
				bean = new DigitalManuscriptBean();
				bean = (DigitalManuscriptBean)session.get(DigitalManuscriptBean.class, manuscriptIds.get(i));
				Hibernate.initialize(bean.getDigitalManuscriptFrames());
				if(i==0){
					type=bean.getDocumentType();
				}else{
					if(type != bean.getDocumentType()){
						throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_GET_MERGED_DATE_FOR_TOW_DIFFERENT_DOCUMANETTYPE);
					}
				}
				manuscriptBeans.add(bean);
			}
			
			
			query1=session.createQuery("from ManuscriptTagMapper mtm where mtm.manuscriptFkId = :manuacriptId");
			query1.setParameter("manuacriptId", manuscriptIds.get(0));
			manuscriptTagMappers = query1.list();

			query2=session.createQuery("from ManuscriptSpecificcategoryMapper msc where msc.manuscriptFkId = :manuacriptId");
			query2.setParameter("manuacriptId", manuscriptIds.get(0));
			specificCategoryMapper = query2.list();
			
			returnMap.put("manuscriptlist", manuscriptBeans);
			returnMap.put("manuscriptTag", manuscriptTagMappers);
			returnMap.put("manuscriptSpecificCat", specificCategoryMapper);
			
			tx.commit();
		}catch(OMDPCoreException oe) { 
			tx.rollback();
			throw oe;
		}
		catch(Exception e) { 
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_GET_MERGED_DATE, e);
		} finally {
			session.close();
		}
		return returnMap;
	}
	
	
	
	
	
	
	/**
	 * Method for saving/updating the digitial manuscript
	 * @param bean
	 * @param publicationBean
	 * @param TagList
	 * @return DigitalManuscriptBean
	 * @throws OMDPCoreException
	 */
	public DigitalManuscriptBean saveDigitalManuscriptMerging(DigitalManuscriptBean bean , PublicationBean publicationBean,List<TagMasterBean> tagList,List<Long>specificCategoryList , String tempFolderName , String tempFolderPath , List<String> parentIdList) throws OMDPCoreException {
		Session session = null;
		Transaction tx = null;
		try {
			List<DigitalManuscriptFrame> frameList = new ArrayList<DigitalManuscriptFrame>();
			frameList = bean.getDigitalManuscriptFrames();
			bean.setDigitalManuscriptFrames(null);
			
			
			if(bean.getParentFKId() != null && bean.getParentFKId().equals(0L)) {
				bean.setParentFKId(null);
			}
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(bean.getAuthorFkObj());
			bean.setAuthorFKId(bean.getAuthorFkObj().getId());
			
			if(bean.getNmmDetailsFkObj() != null && bean.getNmmDetailsFkObj().getHeight().length() > 0) {
				session.saveOrUpdate(bean.getNmmDetailsFkObj());
				bean.setNmmDetailsFkId(bean.getNmmDetailsFkObj().getId());
			}

			if(bean.getScribeFkObj() != null && (bean.getScribeFkObj().getName() != null && bean.getScribeFkObj().getName().length() > 0)
					|| (bean.getScribeFkObj().getDiacriticName() != null && bean.getScribeFkObj().getDiacriticName().length() > 0)
					|| (bean.getScribeFkObj().getRegionalName() != null && bean.getScribeFkObj().getRegionalName().length() > 0)) {
				session.saveOrUpdate(bean.getScribeFkObj());
				bean.setScribeFkId(bean.getScribeFkObj().getId());
			}
			
			if(bean.getCommentatorFkObj() != null && (bean.getCommentatorFkObj().getName() != null && bean.getCommentatorFkObj().getName().length() > 0)) {
				session.saveOrUpdate(bean.getCommentatorFkObj());
				bean.setCommentatorFkId(bean.getCommentatorFkObj().getId());
			}
			
			if(bean.getTranslatorFkObj() != null && (bean.getTranslatorFkObj().getName() != null && bean.getTranslatorFkObj().getName().length() > 0)) {
				session.saveOrUpdate(bean.getTranslatorFkObj());
				bean.setTranslatorFkId(bean.getTranslatorFkObj().getId());
			}
			
			if(bean.getSubCommentatorFkObj() != null && (bean.getSubCommentatorFkObj().getName() != null && bean.getSubCommentatorFkObj().getName().length() > 0)) {
				session.saveOrUpdate(bean.getSubCommentatorFkObj());
				bean.setSubCommentatorFkId(bean.getSubCommentatorFkObj().getId());
			}
			
			if(publicationBean != null && publicationBean.getPublisherFkObj() != null) {
				publicationBean.setAuthorFkId(bean.getAuthorFkObj().getId());
				if(publicationBean.getPublisherFkObj() != null){
					session.saveOrUpdate(publicationBean.getPublisherFkObj());
					publicationBean.setPublisherFkId(publicationBean.getPublisherFkObj().getId());
					if(publicationBean.getEditorFkObj() != null && publicationBean.getEditorFkObj().getName() != null && publicationBean.getEditorFkObj().getName().length() > 0) {
						session.saveOrUpdate(publicationBean.getEditorFkObj());
						publicationBean.setEditorFkId(publicationBean.getEditorFkObj().getId());
					} else {
						publicationBean.setEditorFkId(null);
					}
					session.saveOrUpdate(publicationBean);
					bean.setPublicationFKId(publicationBean.getId());
				}
			}
			
			if(bean.getOrganisationFkObj() != null && bean.getOrganisationFkObj().getName() != null && bean.getOrganisationFkObj().getName().length() > 0) {
				session.saveOrUpdate(bean.getOrganisationFkObj());
				bean.setOrganisationFkId(bean.getOrganisationFkObj().getId());
			} else {
				bean.setOrganisationFkId(null);
			}
			session.saveOrUpdate(bean);
			
			ManuscriptSpecificcategoryMapper specificCategoryMapper= null;
			for(Long specificCategoryId : specificCategoryList){
				specificCategoryMapper= new ManuscriptSpecificcategoryMapper();
				specificCategoryMapper.setManuscriptFkId(bean.getId());
				specificCategoryMapper.setSpecificcategoryFkId(specificCategoryId);
				session.save(specificCategoryMapper);
			}
			
			for(TagMasterBean tg : tagList){
				if(tg .getId() <= 0){
					tg .setId(null);
				}
				session.saveOrUpdate(tg);
			}
		
			ManuscriptTagMapper mapperBean= null;
			for(TagMasterBean tg : tagList){
				mapperBean= new ManuscriptTagMapper();
				mapperBean.setManuscriptFkId(bean.getId());
				mapperBean.setTagsFkId(tg.getId());
				session.save(mapperBean);
			}
			
			for(int i=0 ; i< parentIdList.size() ; i++) {
				DigitalManuscriptBean parentBean = (DigitalManuscriptBean) session.get(DigitalManuscriptBean.class, Long.parseLong(parentIdList.get(i)));
				session.delete(parentBean);
			}
			
			String originalImageFolderPath = ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE).getObject("images.system.path").toString();
			String thumbnailFolderPath = ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE).getObject("imagesResize.system.path").toString();
			
			File dir = new File(originalImageFolderPath+tempFolderPath+tempFolderName);  
            File newName = new File(originalImageFolderPath+tempFolderPath+bean.getId());  
            if ( dir.isDirectory() ) {  
                    dir.renameTo(newName);  
            }
            
            dir = new File(thumbnailFolderPath+tempFolderPath+tempFolderName);  
            newName = new File(thumbnailFolderPath+tempFolderPath+bean.getId());  
            if ( dir.isDirectory() ) {  
                    dir.renameTo(newName);  
            }
            
			if(frameList != null && frameList.size() >0) {
				 bean.setDigitalManuscriptFrames(frameList);
				 DigitalManuscriptFrame frameBean = null;
					for(int i= 0 ; i<bean.getDigitalManuscriptFrames().size() ; i++)  {
						frameBean = bean.getDigitalManuscriptFrames().get(i);
						String str = bean.getDigitalManuscriptFrames().get(i).getFilePath();
						str = str.replace(tempFolderName, bean.getId().toString());
						
						frameBean.setFilePath(str);
					}
			}

			tx.commit();
		}  catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT, e);
		}finally {
			session.close();
		}
		return bean;
	}
	
}
