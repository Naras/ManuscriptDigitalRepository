package com.indven.omds.controller;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.framework.vo.LabelValueVO;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.service.ManuscriptMasterServiceImpl;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptDocumentationType;
import com.indven.omds.util.ManuscriptWorkType;
import com.indven.omds.vo.AuthorVO;
import com.indven.omds.vo.DigitalManuscriptVO;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.service.EmployeeMasterServiceImpl;

import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.util.*;


public class ExternalSearchAction extends BaseAction implements ServletResponseAware {

	private List<String> documentStatusList = new ArrayList<>();


	private DigitalManuscriptVO digitalManuscriptVO;
	private String reportPath;
	private String documentStatus;
	InputStream inputStream = null;


	private static IndvenLogger logger = IndvenLogger.getInstance(ExternalSearchAction.class);
	private String exportDocumentType;
	private String category;
	private Connection conn = null;
	private Map<String, Object> parameters = new HashMap<String, Object>();
	private JSONObject jsonObject = new JSONObject();
	
	private JSONObject dashBoardJsonObject = new JSONObject();

	ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();


	/**
	 * This method is used to set the parameters which will pass to the jasper tool .
	 * @author Rakesh kumar sahoo
	 */
	/**
	 * Modified report to generate docx or pdf file
	 */
	public String generateReportByCriteria() {

		System.out.println("omds.controller.ExternalSearch.generateReportByCriteria for details report-----!!!!!!!!!!!!!1-------" + getRequest().getParameterNames());

		String status = ERROR;
		int eDocumentStatus;
		ServletOutputStream outputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		String reportFilter = "";
		try {
			Long totalManuscript = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
			Long totalBook = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
			Long totalArticle = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_ARTICLE);
			//Parameters for Jasper
			Map<String, Object> parameters = new HashMap<String, Object>();
			//  String type = getRequest().getParameter("type");
			reportFilter = generateReportCriteria();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append("SELECT dm.Id,dm.NAME,dm.regional_name,dm.diacritical_name,dm.SUMMARY,dm.acc_no,dm.table_of_contents,dm.digitized_by,dm.catalogue_no,");
			queryBuffer.append("dm.cataloguedetails,dm.colophon,dm.beginning_line,dm.ending_line,dm.documentation_of_manuscript,dm.isbound,dm.manuscript_id,");
			queryBuffer.append("dm.total_no_of_folios,dm.total_no_of_maps,dm.condition_of_manuscript,dm.source_of_catalogue,dm.TYPE_OF_WORK,org.Id AS orgId,org.NAME AS orgName,");
			queryBuffer.append("org.email,org.ADDRESS AS orgAddress,org.website,org.type AS orgType,org.phoneNumber,op.Id AS publicationId,op.PRICE,op.NO_OF_PAGES,");
			queryBuffer.append("op.YEAR_OF_PUBLICATION,opp.NAME AS editorName,opb.NAME AS publisherName,opb.ADDRESS publisherAddress,ol.NAME AS languageName,");
			queryBuffer.append("os.NAME AS scriptName,om.NAME materialName,ob.name AS bundleName,oc.name AS categoryName,opc.NAME AS scribName, ");
			queryBuffer.append("GROUP_CONCAT(opa.NAME) AS Author,GROUP_CONCAT(opa.regional_name) AS AuthorRegionalName, ");
			queryBuffer.append("GROUP_CONCAT(DISTINCT osc.NAME) AS specificcategory,dm.documentType, ");
			queryBuffer.append("dm.CONTRIBUTION_TO_AYURVEDA as toSubject,dm.UNIQUENESS_OF_WORK as uniquessOfWork,dm.any_other_details as anyOtherDetails ");
			queryBuffer.append(" FROM omds_digital_manuscript dm ");
			queryBuffer.append("LEFT JOIN omds_organisation org ON dm.OrganisationFkId = org.Id ");
			queryBuffer.append("LEFT JOIN omds_publication op ON dm.PublicationFkId = op.Id ");
			queryBuffer.append("LEFT JOIN omds_person opp ON op.editorfkid = opp.Id ");
			queryBuffer.append("LEFT JOIN omds_publisher opb ON op.PublisherFkId = opb.Id ");
			queryBuffer.append("LEFT JOIN omds_language ol ON dm.languageFkId = ol.Id ");
			queryBuffer.append("LEFT JOIN omds_script os ON dm.scriptFkId = os.Id ");
			queryBuffer.append("LEFT JOIN omds_material om ON dm.MaterialFkId = om.Id ");
			queryBuffer.append("LEFT JOIN omds_bundle ob ON dm.bundleMasterfkid = ob.id ");
			queryBuffer.append("LEFT JOIN omds_category oc ON dm.categoryFkId = oc.id ");
			queryBuffer.append("LEFT JOIN omds_person opc ON dm.scribefkid = opc.Id ");
			queryBuffer.append("LEFT JOIN omds_manuscript_authormapper am ON dm.Id = am.manuscriptfkid ");
			queryBuffer.append("LEFT JOIN omds_person opa ON am.authorfkid = opa.Id ");
			queryBuffer.append("LEFT JOIN omds_manuscript_specificcategorymapper oscc ON oscc.manuscriptfkid = dm.Id ");
			queryBuffer.append("LEFT JOIN omds_specificcategory osc ON oscc.specificcategoryfkid = osc.id ");
			queryBuffer.append(" WHERE ");
			queryBuffer.append(reportFilter == null ? "" : reportFilter);
			queryBuffer.append(" GROUP BY dm.Id ORDER BY dm.Id");
			System.out.println("query is ----- " + queryBuffer.toString());


			inputStream = new ByteArrayInputStream(
					queryBuffer.toString().getBytes("UTF8"));
/*
            PrintWriter out = response.getWriter();
            out.write(queryBuffer+"");
            //return null;*/

			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			addActionError("Unable to generate report");

		} finally {

		}
		return status;
	}


	public InputStream getInputStream() {
		return inputStream;
	}

	private Map<String, Object> objMap;
	private String searchType;
	private List<DigitalManuscriptVO> digitalManuscriptVOs;
	FindAllResultVO<DigitalManuscriptVO> objResult = new FindAllResultVO<DigitalManuscriptVO>();


	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the digitalManuscriptVO
	 */
	public DigitalManuscriptVO getDigitalManuscriptVO() {
		return digitalManuscriptVO;
	}

	/**
	 * @param digitalManuscriptVO the digitalManuscriptVO to set
	 */
	public void setDigitalManuscriptVO(DigitalManuscriptVO digitalManuscriptVO) {
		this.digitalManuscriptVO = digitalManuscriptVO;
	}


	public String generateReportCriteria() {
		System.out.println("omds.controller.ExternalSearch.generateReportCriteria:digitalManuscriptVO ----- " + digitalManuscriptVO);
		StringBuffer criteriaStr = new StringBuffer("dm.isDeleted = " + (short) 0);
		if (digitalManuscriptVO != null) {
			if (digitalManuscriptVO.getManuscriptId() != null && digitalManuscriptVO.getManuscriptId().length() > 0) {
				criteriaStr.append(" and dm.manuscript_id like '%" + digitalManuscriptVO.getManuscriptId() + "%'");
			}
			if (digitalManuscriptVO.getName() != null && digitalManuscriptVO.getName().length() > 0) {
				criteriaStr.append(" and dm.NAME like '%" + digitalManuscriptVO.getName() + "%'");
			}
			if (digitalManuscriptVO.getCategoryFkId() != null && digitalManuscriptVO.getCategoryFkId() > 0) {
				criteriaStr.append(" and dm.categoryFkId=" + digitalManuscriptVO.getCategoryFkId());
			}
			if (digitalManuscriptVO.getOrganisationVO() != null && digitalManuscriptVO.getOrganisationVO().getName() != null && digitalManuscriptVO.getOrganisationVO().getName().trim().length() > 0) {
				criteriaStr.append(" and org.NAME ='" + digitalManuscriptVO.getOrganisationVO().getName().trim() + "'");
			}
			if (digitalManuscriptVO.getDocumentType() != null && digitalManuscriptVO.getDocumentType() > 0) {
				criteriaStr.append(" and dm.documentType=" + digitalManuscriptVO.getDocumentType());
			}
			if (digitalManuscriptVO.getTypeOfWork()!=null && ManuscriptWorkType.valueOf(digitalManuscriptVO.getTypeOfWork()) != null && (ManuscriptWorkType.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()) >= 0) {
				criteriaStr.append(" and dm.TYPE_OF_WORK=" + (ManuscriptWorkType.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()));
			}
			if (digitalManuscriptVO.getLanguageFkId() != null && digitalManuscriptVO.getLanguageFkId() > 0) {
				criteriaStr.append(" and dm.languageFkId=" + digitalManuscriptVO.getLanguageFkId());
			} 
			if (digitalManuscriptVO.getScriptFkId() != null && digitalManuscriptVO.getScriptFkId() > 0) {
				criteriaStr.append(" and dm.scriptFkId=" + digitalManuscriptVO.getScriptFkId());
			}
			System.out.println("omds.controller.ExternalSearch.generateReportCriteria stage9 "+DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()));
				/*if(digitalManuscriptVO.getTypeOfWork() !=null && DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue() != null && (DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()) >= 0){
					criteriaStr.append(" and mm.material_fkid="+(DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()));
				}*/
			if (digitalManuscriptVO.getDocumentationOfManuscript() != null && ManuscriptDocumentationType.valueOf(digitalManuscriptVO.getDocumentationOfManuscript()).getValue() != null && (ManuscriptDocumentationType.valueOf(digitalManuscriptVO.getDocumentationOfManuscript()).getValue()) >= 0) {
				criteriaStr.append(" and dm.documentation_of_manuscript=" + ManuscriptDocumentationType.valueOf(digitalManuscriptVO.getDocumentationOfManuscript()).getValue());
			}
			if (digitalManuscriptVO.getBeginningLine() != null && digitalManuscriptVO.getBeginningLine().trim().length() > 0) {
				criteriaStr.append(" and dm.beginning_line like '%" + digitalManuscriptVO.getBeginningLine().trim() + "%'");
			}
			if (digitalManuscriptVO.getEndingLine() != null && digitalManuscriptVO.getEndingLine().trim().length() > 0) {
				criteriaStr.append(" and dm.ending_line like '%" + digitalManuscriptVO.getEndingLine().trim() + "%'");
			}
			System.out.println("omds.controller.ExternalSearch.generateReportCriteria: digitalManuscriptVO.getAuthorFKId()------ "+digitalManuscriptVO.getAuthorFKId());
			if (digitalManuscriptVO.getAuthorFKId() != null && digitalManuscriptVO.getAuthorFKId() > 0) {
				criteriaStr.append(" and opa.Id =" + digitalManuscriptVO.getAuthorFKId());
			}
			
			if (digitalManuscriptVO.getAuthorName() != null && digitalManuscriptVO.getAuthorName().length()>0) {
				criteriaStr.append(" and opa.name  = '"+digitalManuscriptVO.getAuthorName()+"'");
			}
            
            if (digitalManuscriptVO.getId() != null && digitalManuscriptVO.getId() > 0) {
                criteriaStr.append(" and dm.Id =" + digitalManuscriptVO.getId());
            }
			/*if(digitalManuscriptVO.getManuscriptSubject() != null && digitalManuscriptVO.getManuscriptSubject().length() > 0){
					criteriaStr.append(" and mm.manuscript_subject like '%"+digitalManuscriptVO.getManuscriptSubject()+"%'");
				}if(digitalManuscriptVO.getCategoryFkid() != null && digitalManuscriptVO.getCategoryFkid() > 0){
					criteriaStr.append(" and mm.category_fkid ="+digitalManuscriptVO.getCategoryFkid());
				}if(digitalManuscriptVO.getManuscriptStatus() != null && digitalManuscriptVO.getManuscriptStatus().length() > 0){
					criteriaStr.append(" and mm.manuscript_status ='"+digitalManuscriptVO.getManuscriptStatus()+"'");
				}*/
		}
		return criteriaStr.toString();
	}


	private HttpServletResponse response = null;

	@Override
	public void setServletResponse(HttpServletResponse hresponse) {
		// TODO Auto-generated method stub
		this.setResponse(hresponse);

	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getExportDocumentType() {
		return exportDocumentType;
	}

	public void setExportDocumentType(String exportDocumentType) {
		this.exportDocumentType = exportDocumentType;
	}

	private static class Worker extends Thread {
		private final Process process;
		private Integer exit;

		private Worker(Process process) {
			this.process = process;
		}

		public void run() {
			try {
				exit = process.waitFor();
			} catch (InterruptedException ignore) {
				return;
			}
		}
	}

	private Long selectedPage;
	private List<Long> listForPagingCombo;
	private Long totalRecords;
    /**
     * @return the searchType
     */
    public final String getSearchType() {
        return searchType;
    }

    /**
     * @param searchType
     *            the searchType to set
     */
    public final void setSearchType(String searchType) {
        this.searchType = searchType;
    }

	/**
	 * @return the selectedPage
	 */
	public final Long getSelectedPage() {
		return selectedPage;
	}

	/**
	 * @param selectedPage the selectedPage to set
	 */
	public final void setSelectedPage(Long selectedPage) {
		this.selectedPage = selectedPage;
	}

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

	public String searchForManuscript() {
        System.out.println( "omds.controller.ExternalSearch.Action:"+digitalManuscriptVO);
		String status = ERROR;
		jsonObject = new JSONObject();
		try {
			objMap = new HashMap<String, Object>();
			Long setFirst = 0L;
			if (selectedPage != null) {
				setFirst = (selectedPage - 1)
						* IndvenApplicationConstants.RECORDS_PER_PAGE;
			} else {
				selectedPage = 1L;
			}
			if (searchType != null && searchType.equals("FIND_ALL")) {
                System.out.println("omds.controller.ExternalSearch.FIND_ALL");
				objMap = new ManuscriptMasterServiceImpl()
						.searchManuscriptRecord(digitalManuscriptVO, true,
								setFirst.intValue(),
								IndvenApplicationConstants.RECORDS_PER_PAGE);
			} else if (searchType != null && searchType.equals("SEARCH_SPC")) {
                System.out.println("omds.controller.ExternalSearch.SEARCH_SPC");
                objMap = new ManuscriptMasterServiceImpl()
						.searchManuscriptRecord(digitalManuscriptVO, false,
								setFirst.intValue(),
								IndvenApplicationConstants.RECORDS_PER_PAGE);
			}

			digitalManuscriptVOs = (List<DigitalManuscriptVO>) objMap.get("manuscriptList");
			objResult.setListOfElemnents((List<DigitalManuscriptVO>) objMap.get("manuscriptList"));
			org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("manuscriptlist",objResult.getListOfElemnents());
            //jsonObject.accumulate("manuscriptlist","saasdasd");
            System.out.println("omds.controller.ExternalSearch. jsonObject "+jsonObject);
            jsonObject.put("authors", objResult.getListOfElemnents());

			/*totalRecords = (Long) objMap.get("totalCount");
			listForPagingCombo = new ArrayList<>();
			if (totalRecords % IndvenApplicationConstants.RECORDS_PER_PAGE == 0) {
				for (long i = 1; i <= (totalRecords / IndvenApplicationConstants.RECORDS_PER_PAGE); i++) {
					listForPagingCombo.add(i);
				}
			} else {
				for (long i = 1; i <= (totalRecords / IndvenApplicationConstants.RECORDS_PER_PAGE) + 1; i++) {
					listForPagingCombo.add(i);
				}
			}*/
            System.out.println("omds.controller.ExternalSearch. digitalManuscriptVOs "+objResult.getListOfElemnents());
            status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO.setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO.setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS, IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} finally {
		}

		return status;

	}

    public String findAllAuthors() {
        String status = ERROR;
        ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
        AuthorVO authorVO = new AuthorVO();
        try {
            String requestTerm = new String(getRequest().getParameter("term")
                    .getBytes("ISO-8859-1"), "UTF-8");
            String requestType = new String(getRequest().getParameter("type")
                    .getBytes("ISO-8859-1"), "UTF-8");
            Short authorType = null;
            if (requestType != null) {
                authorType = Short.parseShort(requestType);
            }
            List<AuthorVO> authorVOs = manuscriptMasterServiceImpl
                    .findAllAuthorForTerm(requestTerm, authorType);
            if (requestType != null
                    && Short.parseShort(requestType) == IndvenApplicationConstants.PERSON_TYPE_AUTHOR
                    || Short.parseShort(requestType) == IndvenApplicationConstants.PERSON_TYPE_SCRIBE
                    || Short.parseShort(requestType) == IndvenApplicationConstants.PERSON_TYPE_COMMENTATOR
                    || Short.parseShort(requestType) == IndvenApplicationConstants.PERSON_TYPE_TRANSLATOR
                    || Short.parseShort(requestType) == IndvenApplicationConstants.PERSON_TYPE_SUBCOMMENTATOR) {
                authorVO.setName("Add New Record");
                authorVOs.add(authorVO);
            }
            if (authorVOs != null && authorVOs.size() > 0) {
                jsonObject.put("authors", authorVOs);
            }

            status = SUCCESS;
        } catch (OMDPCoreException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

        return status;
    }
    
    public String getHomePageDashBoard() {
    	//ManuscriptMasterAction manuscriptMasterAction = new ManuscriptMasterAction();
    	//manuscriptMasterAction.findNoOfManuscripts()
    	System.out.println("omds.controller.ExternalSearch.Action.getHomePageDashBoard stage1");
    	String status = ERROR;
    	List<JSONObject> jsonObjectList = new ArrayList();
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		EmployeeMasterServiceImpl employeeMasterServiceImpl = new EmployeeMasterServiceImpl();
		try {
 
			
				List<Long> manuscriptStatus =  manuscriptMasterServiceImpl.findNoOfRecordsForDifferentStatus(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
				List<Long> bookStatus =  manuscriptMasterServiceImpl.findNoOfRecordsForDifferentStatus(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
				Long totalBook = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
				Long totalManuscript = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
				Long totalArticle = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_ARTICLE);
				Long totalRecord = totalBook+totalManuscript+totalArticle;

				List<LabelValueVO> vos = new ArrayList<>();
				vos.add(new LabelValueVO("Users",
						employeeMasterServiceImpl.findNoOfEmployees()
								.toString()));
				vos.add(new LabelValueVO("Number of Documents", totalRecord
						.toString()));
				vos.add(new LabelValueVO("Books", totalBook
						.toString()));
				vos.add(new LabelValueVO("Manuscripts",
						totalManuscript.toString()));
				vos.add(new LabelValueVO("Articles",
						totalArticle.toString()));
				HashMap<String,String> hashMap = new HashMap<String,String>();
				hashMap.put("documents",totalRecord+"");
				hashMap.put("books",totalBook+"");
				hashMap.put("manuscripts",totalManuscript.toString()+"");
				hashMap.put("articles",totalArticle.toString()+"");
				/*if (vos != null && vos.size() > 0) {
					jsonObject.put("digitalManuscripts", vos);
					jsonObject.put("manuscriptStatus", manuscriptStatus);
					jsonObject.put("bookStatus", bookStatus);
				}*/
				//jsonObject.put(key, value)
				System.out.println("omds.controller.ExternalSearch.dashBoardJsonObject "+jsonObject);
				jsonObject.put("digitalManuscripts", hashMap);
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return status;
    	
    }
}
