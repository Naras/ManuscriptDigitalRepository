package com.indven.omds.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.FindAllResultVO;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.framework.vo.LabelValueVO;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.service.ManuscriptMasterServiceImpl;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.FilesUtil;
import com.indven.omds.util.ManuscriptConditionType;
import com.indven.omds.util.ManuscriptDocumentationType;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.omds.util.ManuscriptWorkType;
import com.indven.omds.util.SourceOfCatalogueEnum;
import com.indven.omds.vo.AuthorVO;
import com.indven.omds.vo.BundleMasterVO;
import com.indven.omds.vo.CategoryVO;
import com.indven.omds.vo.DigitalDocumentVO;
import com.indven.omds.vo.DigitalManuscriptVO;
import com.indven.omds.vo.DocumentCommentVO;
import com.indven.omds.vo.LanguageVO;
import com.indven.omds.vo.MaterialVO;
import com.indven.omds.vo.OrganisationVO;
import com.indven.omds.vo.PublicationVO;
import com.indven.omds.vo.PublisherVO;
import com.indven.omds.vo.ScriptVO;
import com.indven.omds.vo.SpecificCategoryVO;
import com.indven.omds.vo.TagMasterVO;
import com.indven.portal.administration.dao.RoleMasterDAOImpl;
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.service.EmployeeMasterServiceImpl;
import com.indven.portal.hrd.vo.EmployeeMasterVO;
import com.indven.workflow.core.dao.WorkflowCoreDAOImpl;
import com.indven.workflow.core.entity.CurrentProcessDetailsBean;
import com.indven.workflow.core.entity.CurrentProcessMasterBean;
import com.indven.workflow.core.exception.WorkFlowCoreException;
import com.indven.workflow.core.service.WorkflowCoreServiceImpl;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ManuscriptMasterAction extends BaseAction {

	private static final long serialVersionUID = -6305782935123712627L;

	private static IndvenLogger logger = IndvenLogger
			.getInstance(ManuscriptMasterAction.class);

	private IndvenResultVO resultVO = new IndvenResultVO();

	public IndvenResultVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(IndvenResultVO resultVO) {
		this.resultVO = resultVO;
	}

	private List<String> manuscriptWorkTypes = new ArrayList<>();
	private List<String> recordStatusList = new ArrayList<>();
	private List<String> manuscriptConditionTypes = new ArrayList<>();
	private List<String> manuscriptDocumentationTypes = new ArrayList<>();
	private List<String> sourceOfCatagoryTypes = new ArrayList<>();
	private List<LanguageVO> languageVOs = new ArrayList<>();
	private List<MaterialVO> materialVOs = new ArrayList<>();
	private List<OrganisationVO> organisationVOs = new ArrayList<>();
	private List<AuthorVO> authorVOs = new ArrayList<>();
	private List<ScriptVO> scriptVOs = new ArrayList<>();
	private List<CategoryVO> categoryVOs = new ArrayList<>();
	private List<SpecificCategoryVO> specificCategoryVOs = new ArrayList<>();
	private List<BundleMasterVO> bundleMasterVOs = new ArrayList<>();

	FindAllResultVO<DigitalManuscriptVO> objResult = new FindAllResultVO<DigitalManuscriptVO>();

	private AuthorVO authorVO = new AuthorVO();
	private DigitalManuscriptVO digitalManuscriptVO;
	private PublicationVO publicationVO = new PublicationVO();
	private DigitalDocumentVO digitalDocumentVO;
	private DigitalDocumentVO translatedDocumentVO;

	private List<LabelValueVO> documentTypes = new ArrayList<>();

	private JSONObject jsonObject = new JSONObject();
	private Long selectedPage;
	private Map<String, Object> objMap;
	private List<Long> listForPagingCombo;
	private Long totalRecords;

	String photoFileName;
	String imagePath;
	List<File> photo = null;

   private String transcribedText;
   private Long manuscriptFrameId;
   
   private List<DigitalManuscriptVO> digitalManuscriptVOs;

   
	/**
 * @return the digitalManuscriptVOs
 */
public final List<DigitalManuscriptVO> getDigitalManuscriptVOs() {
	return digitalManuscriptVOs;
}

/**
 * @param digitalManuscriptVOs the digitalManuscriptVOs to set
 */
public final void setDigitalManuscriptVOs(
		List<DigitalManuscriptVO> digitalManuscriptVOs) {
	this.digitalManuscriptVOs = digitalManuscriptVOs;
}

	/**
 * @return the transcribedText
 */
public final String getTranscribedText() {
	return transcribedText;
}

/**
 * @param transcribedText the transcribedText to set
 */
public final void setTranscribedText(String transcribedText) {
	this.transcribedText = transcribedText;
}

/**
 * @return the manuscriptFrameId
 */
public final Long getManuscriptFrameId() {
	return manuscriptFrameId;
}

/**
 * @param manuscriptFrameId the manuscriptFrameId to set
 */
public final void setManuscriptFrameId(Long manuscriptFrameId) {
	this.manuscriptFrameId = manuscriptFrameId;
}

	List<File> audioVideo;
 String audioVideoFileName;
	/**
	 * @return the audioVideo
	 */
	public final List<File> getAudioVideo() {
		return audioVideo;
	}

	/**
	 * @param audioVideo the audioVideo to set
	 */
	public final void setAudioVideo(List<File> audioVideo) {
		this.audioVideo = audioVideo;
	}

	/**
	 * @return the audioVideoFileName
	 */
	public final String getAudioVideoFileName() {
		return audioVideoFileName;
	}

	/**
	 * @param audioVideoFileName the audioVideoFileName to set
	 */
	public final void setAudioVideoFileName(String audioVideoFileName) {
		this.audioVideoFileName = audioVideoFileName;
	}

	private Short isVisibleSave;
	private Long currentProcessDetailsId;
	private Long currentProcessMasterFKId;
	private String searchType;
	private int presentReviewer2;

	/**
	 * @return the translatedDocumentVO
	 */
	public final DigitalDocumentVO getTranslatedDocumentVO() {
		return translatedDocumentVO;
	}

	/**
	 * @param translatedDocumentVO
	 *            the translatedDocumentVO to set
	 */
	public final void setTranslatedDocumentVO(
			DigitalDocumentVO translatedDocumentVO) {
		this.translatedDocumentVO = translatedDocumentVO;
	}

	private DigitalManuscriptVO parentVO;

	/**
	 * @return the parentVO
	 */
	public final DigitalManuscriptVO getParentVO() {
		return parentVO;
	}

	/**
	 * @param parentVO
	 *            the parentVO to set
	 */
	public final void setParentVO(DigitalManuscriptVO parentVO) {
		this.parentVO = parentVO;
	}

	/**
	 * @return the presentReviewer2
	 */
	public final int getPresentReviewer2() {
		return presentReviewer2;
	}

	/**
	 * @param presentReviewer2
	 *            the presentReviewer2 to set
	 */
	public final void setPresentReviewer2(int presentReviewer2) {
		this.presentReviewer2 = presentReviewer2;
	}

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
	 * @return the currentProcessDetailsId
	 */
	public final Long getCurrentProcessDetailsId() {
		return currentProcessDetailsId;
	}

	/**
	 * @param currentProcessDetailsId
	 *            the currentProcessDetailsId to set
	 */
	public final void setCurrentProcessDetailsId(Long currentProcessDetailsId) {
		this.currentProcessDetailsId = currentProcessDetailsId;
	}

	/**
	 * @return the currentProcessMasterFKId
	 */
	public final Long getCurrentProcessMasterFKId() {
		return currentProcessMasterFKId;
	}

	/**
	 * @param currentProcessMasterFKId
	 *            the currentProcessMasterFKId to set
	 */
	public final void setCurrentProcessMasterFKId(Long currentProcessMasterFKId) {
		this.currentProcessMasterFKId = currentProcessMasterFKId;
	}

	/**
	 * @return the isVisibleSave
	 */
	public final Short getIsVisibleSave() {
		return isVisibleSave;
	}

	/**
	 * @param isVisibleSave
	 *            the isVisibleSave to set
	 */
	public final void setIsVisibleSave(Short isVisibleSave) {
		this.isVisibleSave = isVisibleSave;
	}

	private List<String> documentStatusList = new ArrayList<>();

	/**
	 * @return the documentStatusList
	 */
	public final List<String> getDocumentStatusList() {
		for (DocumentStatusEnum dir : DocumentStatusEnum.values()) {
			documentStatusList.add(dir.name());
		}
		return documentStatusList;
	}

	/**
	 * @param documentStatusList
	 *            the documentStatusList to set
	 */
	public final void setDocumentStatusList(List<String> documentStatusList) {
		this.documentStatusList = documentStatusList;
	}

	/**
	 * @return the totalRecords
	 */
	public final Long getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public final void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the listForPagingCombo
	 */
	public final List<Long> getListForPagingCombo() {
		return listForPagingCombo;
	}

	/**
	 * @param listForPagingCombo
	 *            the listForPagingCombo to set
	 */
	public final void setListForPagingCombo(List<Long> listForPagingCombo) {
		this.listForPagingCombo = listForPagingCombo;
	}

	/**
	 * @return the selectedPage
	 */
	public final Long getSelectedPage() {
		return selectedPage;
	}

	/**
	 * @param selectedPage
	 *            the selectedPage to set
	 */
	public final void setSelectedPage(Long selectedPage) {
		this.selectedPage = selectedPage;
	}
	
	public String saveOrUpdateManuscript() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		Boolean isUpdating = false;
		try {
			/*
			 * if(digitalManuscriptVO.getManuscriptId().equals("") ||
			 * digitalManuscriptVO.getManuscriptId().equals(null)){ if(
			 * !digitalManuscriptVO
			 * .getOrganisationVO().getAcronym().equals(null) &&
			 * !digitalManuscriptVO
			 * .getOrganisationVO().getAcronym().equals("")){ String
			 * manuscriptId
			 * =digitalManuscriptVO.getOrganisationVO().getAcronym();
			 * manuscriptId
			 * =manuscriptId+"-"+manuscriptMasterServiceImpl.findNumberOfRecords
			 * (digitalManuscriptVO.getOrganisationVO().getAcronym(),
			 * digitalManuscriptVO.getOrganisationVO().getName());
			 * digitalManuscriptVO.setManuscriptId(manuscriptId); } }
			 */
			if (digitalManuscriptVO.getId() != null
					&& digitalManuscriptVO.getId() > 0) {
				isUpdating = true;
			} else {
				digitalManuscriptVO.setManuscriptType("Original");
			}
			
			String targetPath = getRequest().getServletContext()
					.getRealPath(
							IndvenApplicationConstants.IMAGE_FOLDER_DIR);
			
			
			if(digitalManuscriptVO.getIsSavingMerged() != null && digitalManuscriptVO.getIsSavingMerged().equals(1L)) {
				boolean sta = manuscriptMasterServiceImpl.saveMergedImages(digitalManuscriptVO ,publicationVO ,targetPath);
				digitalManuscriptVO.setMessage("Successfully merged the records");
			} else {
				digitalManuscriptVO = manuscriptMasterServiceImpl
						.saveDigitalManuscript(
								digitalManuscriptVO,
								publicationVO,
								getRequest()
										.getServletContext()
										.getRealPath(
												IndvenApplicationConstants.IMAGE_FOLDER_DIR));
				if (digitalManuscriptVO.getDocumentType() == 1) {
					digitalManuscriptVO.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.SUCCESSFULLY_SAVED_BOOK,
									IndvenApplicationConstants.LOCALE));
				} else {
					digitalManuscriptVO
							.setMessage(IndvenMessageResolver
									.resolveMessage(
											OMDPCoreException.SUCCESSFULLY_SAVED_MANUSCRIPT,
											IndvenApplicationConstants.LOCALE));
				}
			}
			
			addActionMessage(digitalManuscriptVO.getMessage());

			if (!isUpdating) {
				digitalManuscriptVO = new DigitalManuscriptVO();
				publicationVO = new PublicationVO();
			}

			status = SUCCESS;
		} catch (OMDPCoreException e) {
			if (e.getMessage() == OMDPCoreException.DUPLICATE_ACRONYM) {
				digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
				digitalManuscriptVO.setMessage(IndvenMessageResolver
						.resolveMessage(OMDPCoreException.DUPLICATE_ACRONYM,
								IndvenApplicationConstants.LOCALE));
				addActionError(digitalManuscriptVO.getMessage());
				e.printStackTrace();
			} else {
				logger.error(e);
				digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
				digitalManuscriptVO
						.setMessage(IndvenMessageResolver
								.resolveMessage(
										OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT,
										IndvenApplicationConstants.LOCALE));
				addActionError(digitalManuscriptVO.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_MANUSCRIPT,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} finally {
			generateLists();
		}

		return status;
	}

	/**
	 * Saves the digital document Containing the online derived work
	 * 
	 * @author Neel Borooah
	 */
	public String saveOrUpdateDigitalDocument() {
		String status = ERROR;
		String value = getRequest().getParameter("value");
		boolean isWFLAuthorised = false;
		if (value != null && value.equals("ACTION_TYPE_FORWARD")) {
			isWFLAuthorised = true;
		}
		Boolean isUpdating = false;
		try {

			/* Retrieving logged in user role mapper id */
			UserInfoVO userInfoVO = (UserInfoVO) getRequest()
					.getSession()
					.getAttribute(
							IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
			if (userInfoVO != null && userInfoVO.getId() != null
					&& userInfoVO.getId() > 0) {
				digitalDocumentVO.getDetailsVO().setUserRoleDetailsFkId(
						new RoleMasterDAOImpl().findRoleByUser(
								userInfoVO.getId()).getId());
			}
			digitalDocumentVO.setDigitalManuscriptVO(digitalManuscriptVO);
			digitalDocumentVO = new ManuscriptMasterServiceImpl()
					.saveOrUpdateDigitalDocument(digitalDocumentVO,
							isWFLAuthorised);

			digitalDocumentVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.SUCCESSFULLY_SAVED_DOCUMENT,
					IndvenApplicationConstants.LOCALE));
			
			addActionMessage(digitalDocumentVO.getMessage());

			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			digitalDocumentVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalDocumentVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT,
					IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		}

		return status;
	}

	public String findById() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		digitalManuscriptVO = new DigitalManuscriptVO();
		try {
			String id = getRequest().getParameter("id");

			if (id != null) {
			   short recordStatus =  manuscriptMasterServiceImpl.getManuscriptStatus(Long.parseLong(id));
			   if(recordStatus<0 || recordStatus==3){
				digitalManuscriptVO = manuscriptMasterServiceImpl
						.findManuscriptById(
								Long.parseLong(id),
								getRequest()
										.getServletContext()
										.getRealPath(
												IndvenApplicationConstants.IMAGE_FOLDER_DIR));
				if (digitalManuscriptVO.getPublicationFKId() != null
						&& digitalManuscriptVO.getPublicationFKId() > 0) {
					publicationVO = manuscriptMasterServiceImpl
							.findPublicationById(digitalManuscriptVO
									.getPublicationFKId());
				}
			   }else{
				   throw new OMDPCoreException(
							OMDPCoreException.UNABLE_TO_FIND_WORKFLOW_MANUSCRIPT_DETAILS); 
			   }
			} else {
				throw new OMDPCoreException(
						OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS);
			}
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(
					digitalManuscriptVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} finally {
			generateLists();
		}

		return status;
	}

	/**
	 * Loads the digital document containing the online derived work By the
	 * associated manuscript id
	 * 
	 * @author Neel Borooah
	 */
	public String findDocumentByManuscriptId() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String id = getRequest().getParameter("id");

			isVisibleSave = Short.parseShort(getRequest().getParameter(
					"countTypeValue"));
			currentProcessDetailsId = Long.parseLong(getRequest().getParameter(
					"currentProcessId"));
			currentProcessMasterFKId = Long.parseLong(getRequest()
					.getParameter("currentprocessmasterid"));

			if (id != null) {
				/* Loading document for overall manuscript */
				digitalManuscriptVO = manuscriptMasterServiceImpl
						.findManuscriptById(
								Long.parseLong(id),
								getRequest()
										.getServletContext()
										.getRealPath(
												IndvenApplicationConstants.IMAGE_FOLDER_DIR));
				/* Loading document for all frames */

				Long rr = (Long) getRequest().getSession().getAttribute(
						"currentRole");

				String manName = "";
				if (digitalManuscriptVO.getName() == null
						|| digitalManuscriptVO.getName().length() <= 0) {
					if (digitalManuscriptVO.getRegionalName() != null
							&& digitalManuscriptVO.getRegionalName().length() > 0) {
						manName = digitalManuscriptVO.getRegionalName();
					} else {
						manName = digitalManuscriptVO.getDiacriticName();
					}
				} else {
					manName = digitalManuscriptVO.getName();
				}
				digitalManuscriptVO.setName(manName);

				digitalDocumentVO = manuscriptMasterServiceImpl
						.findDocumentByManuscriptId(Long.parseLong(id));

				if (rr.equals(IndvenApplicationConstants.REVIEWER_ROLE_ID)) {
					presentReviewer2 = new WorkflowCoreServiceImpl()
							.isPresentReviewer2(currentProcessMasterFKId);
				}
			} else {
				throw new OMDPCoreException(
						OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS);
			}
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * Loads the digital document containing the online derived work By the
	 * associated manuscript id
	 * 
	 * @author Neel Borooah
	 */
	public String findDocumentByManuscriptIdForTranslation() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		Map<Object, Object> rtrnMap = new HashMap<>();
		try {
			String id = getRequest().getParameter("id");

			isVisibleSave = Short.parseShort(getRequest().getParameter(
					"countTypeValue"));
			currentProcessDetailsId = Long.parseLong(getRequest().getParameter(
					"currentProcessId"));
			currentProcessMasterFKId = Long.parseLong(getRequest()
					.getParameter("currentprocessmasterid"));

			if (id != null) {
				/* Loading document for overall manuscript */
				rtrnMap = manuscriptMasterServiceImpl.findManuscriptByIdTrans(
						Long.parseLong(id),
						getRequest().getServletContext().getRealPath(
								IndvenApplicationConstants.IMAGE_FOLDER_DIR));
				/* Loading document for all frames */

				digitalManuscriptVO = (DigitalManuscriptVO) rtrnMap
						.get("TranscriptedVO");
				parentVO = (DigitalManuscriptVO) rtrnMap.get("Original");

				digitalDocumentVO = (DigitalDocumentVO) rtrnMap
						.get("OriginalDocument");
				translatedDocumentVO = (DigitalDocumentVO) rtrnMap
						.get("TransDocument");
				String manName = "";
				if (digitalManuscriptVO.getName() == null
						|| digitalManuscriptVO.getName().length() <= 0) {
					if (digitalManuscriptVO.getRegionalName() != null
							&& digitalManuscriptVO.getRegionalName().length() > 0) {
						manName = digitalManuscriptVO.getRegionalName();
					} else {
						manName = digitalManuscriptVO.getDiacriticName();
					}
				} else {
					manName = digitalManuscriptVO.getName();
				}
				digitalManuscriptVO.setName(manName);

				Long rr = (Long) getRequest().getSession().getAttribute(
						"currentRole");
				if (rr.equals(IndvenApplicationConstants.REVIEWER_ROLE_ID)) {
					presentReviewer2 = new WorkflowCoreServiceImpl()
							.isPresentReviewer2(currentProcessMasterFKId);
				}
			} else {
				throw new OMDPCoreException(
						OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS);
			}
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		}

		return status;
	}

	public String deriveDocumentsForManuscript() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String id = getRequest().getParameter("id");

			if (id != null) {
				/* Loading document for overall manuscript */
				digitalManuscriptVO = manuscriptMasterServiceImpl
						.findManuscriptById(
								Long.parseLong(id),
								getRequest()
										.getServletContext()
										.getRealPath(
												IndvenApplicationConstants.IMAGE_FOLDER_DIR));
				/* Loading document for all frames */
				digitalDocumentVO = manuscriptMasterServiceImpl
						.findDocumentByManuscriptId(Long.parseLong(id));
			} else {
				throw new OMDPCoreException(
						OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS);
			}
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		}

		return status;
	}  ;
	
/*	public String saveAudioFile(){
		String status = ERROR;
		try {
		String[] photoNames = audioVideoFileName.split(" ");
		Long kj = digitalManuscriptVO.getId();
		for(File i: audioVideo) {
			String convertedImgName = CustomBeanUtil.formatImageName(photoNames[0].split(",")[0]);
			String tempImage =  System.currentTimeMillis() + convertedImgName;
			String targetPath = "C:/Users/Rakesh/Pictures/img/";
			FilesUtil.saveFile(i, tempImage, targetPath+tempImage);
			//jsonObject.put("filePath", targetPath+tempImage);
		}
		status = SUCCESS;
		}catch (IOException e) {
			jsonObject.put("status", "failure");
			e.printStackTrace();
		} catch(Exception e) {
			jsonObject.put("status", "failure");
			e.printStackTrace();
		}
		return status;
	}*/
	
	/**
	 * This methode is used to save the transcribed data from ajax call
	 * @author Rakesh kumar sahoo
	 */
    public String framePartialSave(){
		String status = ERROR;
		Long frameId = null;
		String attachmentFilePath = getRequest().getParameter("filePath");
		ManuscriptMasterServiceImpl manuscriptMasterService = new ManuscriptMasterServiceImpl();
		try {
			Long manuscriptId = digitalManuscriptVO.getId();
			int type =Integer.parseInt(getRequest().getParameter("type"));
			if(type==0){
				frameId = manuscriptFrameId;
			}else{
				frameId = manuscriptId;
			}
			//Save the Audio or video file
			if(audioVideoFileName!=null){
			String[] photoNames = audioVideoFileName.split(" ");
			for(File i: audioVideo) {
				Calendar now = Calendar.getInstance();
				String year = String.valueOf(now.get(Calendar.YEAR));
				String month =  String.valueOf(now.get(Calendar.MONTH)+1);
				String day =  String.valueOf(now.get(Calendar.DAY_OF_MONTH));
				String folderPath = "/"+year+"/"+month+"/"+day+"/"+digitalManuscriptVO.getId();
				
				String convertedImgName = CustomBeanUtil.formatImageName(photoNames[0].split(",")[0]);
				String fileName =  System.currentTimeMillis() + convertedImgName;
				String targetPath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
						.getObject("audio.system.path").toString()+folderPath;
				new File(targetPath).mkdirs();
				FilesUtil.saveFile(i, fileName, targetPath+"/"+fileName);
				attachmentFilePath = folderPath+"/"+fileName;
				audioVideoFileName = null;
				audioVideo = null;
			}
			}
		    manuscriptMasterService.framePartialSave(transcribedText,frameId,manuscriptId,type,attachmentFilePath);
		    jsonObject.put("attachmentPath",attachmentFilePath);
		    jsonObject.put("message","Successfully Saved the Digital Document");
		    status=SUCCESS;
		} catch (IOException e) {
			logger.error(e);
			String msg=IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, IndvenApplicationConstants.LOCALE);
			jsonObject.put("message",msg);
			e.printStackTrace();
		} catch (OMDPCoreException e) {
					logger.error(e);
					String msg=IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, IndvenApplicationConstants.LOCALE);
					jsonObject.put("message",msg);
					status = ERROR;
					e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			String msg=IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, IndvenApplicationConstants.LOCALE);
			jsonObject.put("message",msg);
			status = ERROR;
			e.printStackTrace();
		} 
		
		return status;
	}

	/*public String framePartialSave(){
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterService = new ManuscriptMasterServiceImpl();
		try {
			Long frameId = Long.parseLong(getRequest().getParameter("frameId"));
			Long manuscriptId = Long.parseLong(getRequest().getParameter(
					"manuscriptId"));
			int type = Integer.parseInt(getRequest().getParameter("type"));
			manuscriptMasterService.framePartialSave(comment, frameId,
					manuscriptId, type);
			jsonObject
					.put("message", "Successfully Saved the Digital Document");
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			String msg = IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT,
					IndvenApplicationConstants.LOCALE);
			jsonObject.put("message", msg);
			status = ERROR;
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			String msg = IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT,
					IndvenApplicationConstants.LOCALE);
			jsonObject.put("message", msg);
			status = ERROR;
			e.printStackTrace();
		}

		return status;
	}*/

	public String framePartialSaveTrans() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterService = new ManuscriptMasterServiceImpl();
		try {
			Long frameId = Long.parseLong(getRequest().getParameter("frameId"));
//			Long manuscriptId = Long.parseLong(getRequest().getParameter(
//					"manuscriptId"));
//			int type = Integer.parseInt(getRequest().getParameter("type"));
//			manuscriptMasterService.framePartialSaveTrans(comment, frameId,
//					manuscriptId, type);
//			jsonObject
//					.put("message", "Successfully Saved the Digital Document");
//			status = SUCCESS;
			Long manuscriptId = Long.parseLong(getRequest().getParameter("manuscriptId"));
			int type =Integer.parseInt(getRequest().getParameter("type"));
			String language = getRequest().getParameter("language");
		    manuscriptMasterService.framePartialSaveTrans(comment,frameId,manuscriptId,type,language);
		    jsonObject.put("message","Successfully Saved the Digital Document");
		    status=SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			String msg = IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT,
					IndvenApplicationConstants.LOCALE);
			jsonObject.put("message", msg);
			status = ERROR;
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			String msg = IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT,
					IndvenApplicationConstants.LOCALE);
			jsonObject.put("message", msg);
			status = ERROR;
			e.printStackTrace();
		}

		return status;
	}
	
	public String mergeManuscript() {
		String status = ERROR;
		List<String> cc = null;
		digitalManuscriptVO = new DigitalManuscriptVO();
		try {
			System.out.println("inside the method to merge");
			String order = getRequest().getParameter("value");
			
			if(order != null && order.length() > 0) {
				order = order.replace("orderlist[]=", "");
				cc = new ArrayList<String>(Arrays.asList(order.split(",")));
			}
			digitalManuscriptVO = new ManuscriptMasterServiceImpl().mergeManuscript(cc ,digitalManuscriptVOs,getRequest().getServletContext().getRealPath(IndvenApplicationConstants.IMAGE_FOLDER_DIR));
			digitalManuscriptVO.setIsSavingMerged(1L);
			status = SUCCESS;
		/*} catch(NullPointerException ne){
			logger.error(ne);
			addActionError("Please select any two manuscript to marge");
			ne.printStackTrace();*/
		} catch (OMDPCoreException oe) {
			logger.error(oe);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(
					digitalManuscriptVO, oe, IndvenApplicationConstants.LOCALE);
			addActionError(digitalManuscriptVO.getMessage());
			oe.printStackTrace();
		}catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO
					.setMessage(IndvenMessageResolver
							.resolveMessage(
									OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,
									IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		}finally {
			generateLists();
		}
		return status;
	}
	
	

	@SuppressWarnings("unchecked")
	public String searchForManuscript() {
		String status = ERROR;

		try {
			objMap = new HashMap<String, Object>();
			Long setFirst = 0L;
			if (selectedPage != null) {
				setFirst = (selectedPage - 1)
						* IndvenApplicationConstants.RECORDS_PER_PAGE;
			} else {
				selectedPage = 1L;
			}
			if (searchType != null && searchType.equals("FIND_ALL"))
				objMap = new ManuscriptMasterServiceImpl()
						.searchManuscriptRecord(digitalManuscriptVO, true,
								setFirst.intValue(),
								IndvenApplicationConstants.RECORDS_PER_PAGE);
			else if (searchType != null && searchType.equals("SEARCH_SPC"))
				objMap = new ManuscriptMasterServiceImpl()
						.searchManuscriptRecord(digitalManuscriptVO, false,
								setFirst.intValue(),
								IndvenApplicationConstants.RECORDS_PER_PAGE);

				digitalManuscriptVOs = (List<DigitalManuscriptVO>) objMap.get("manuscriptList");
			objResult.setListOfElemnents((List<DigitalManuscriptVO>) objMap
					.get("manuscriptList"));
			
			totalRecords = (Long) objMap.get("totalCount");
			listForPagingCombo = new ArrayList<>();
			if (totalRecords % IndvenApplicationConstants.RECORDS_PER_PAGE == 0) {
				for (long i = 1; i <= (totalRecords / IndvenApplicationConstants.RECORDS_PER_PAGE); i++) {
					listForPagingCombo.add(i);
				}
			} else {
				for (long i = 1; i <= (totalRecords / IndvenApplicationConstants.RECORDS_PER_PAGE) + 1; i++) {
					listForPagingCombo.add(i);
				}
			}

			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO.setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO.setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} finally {
			generateLists();
		}

		return status;

	}
	
	
	@SuppressWarnings("unchecked")
	public String searchManuscriptForMerging() {
		String status = ERROR;

		try {
			objMap = new HashMap<String, Object>();
			Long setFirst = 0L;
			if (selectedPage != null) {
				setFirst = (selectedPage - 1)
						* IndvenApplicationConstants.RECORDS_PER_PAGE;
			} else {
				selectedPage = 1L;
			}
			if (searchType != null && searchType.equals("FIND_ALL"))
				objMap = new ManuscriptMasterServiceImpl()
						.searchManuscriptForMerging(digitalManuscriptVO, true);
			else if (searchType != null && searchType.equals("SEARCH_SPC"))
				objMap = new ManuscriptMasterServiceImpl()
						.searchManuscriptForMerging(digitalManuscriptVO, false);

				digitalManuscriptVOs = (List<DigitalManuscriptVO>) objMap.get("manuscriptList");
			objResult.setListOfElemnents((List<DigitalManuscriptVO>) objMap
					.get("manuscriptList"));
			
			totalRecords = (Long) objMap.get("totalCount");
			listForPagingCombo = new ArrayList<>();
			if (totalRecords % IndvenApplicationConstants.RECORDS_PER_PAGE == 0) {
				for (long i = 1; i <= (totalRecords / IndvenApplicationConstants.RECORDS_PER_PAGE); i++) {
					listForPagingCombo.add(i);
				}
			} else {
				for (long i = 1; i <= (totalRecords / IndvenApplicationConstants.RECORDS_PER_PAGE) + 1; i++) {
					listForPagingCombo.add(i);
				}
			}

			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO.setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			digitalManuscriptVO.setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS,IndvenApplicationConstants.LOCALE));
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} finally {
			generateLists();
		}

		return status;

	}
	
	

	/*
	 * public String findNumberOfRecords(String acronym,String name) throws
	 * OMDPCoreException{ ManuscriptMasterServiceImpl
	 * manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl(); return
	 * manuscriptMasterServiceImpl.findNumberOfRecords(acronym,name);
	 * 
	 * }
	 */
	/*
	 * @SuppressWarnings("unchecked") public String searchAllManuscript() {
	 * String status = ERROR;
	 * 
	 * try { objMap = new HashMap<String, Object>(); Long setFirst = 0L;
	 * if(selectedPage != null) { setFirst =
	 * selectedPage*IndvenApplicationConstants.RECORDS_PER_PAGE; } else {
	 * selectedPage = 1L; }
	 * 
	 * if(digitalManuscriptVO == null) objMap = new
	 * ManuscriptMasterServiceImpl().searchManuscriptRecord(digitalManuscriptVO
	 * , true , setFirst.intValue() ,
	 * IndvenApplicationConstants.RECORDS_PER_PAGE); else objMap = new
	 * ManuscriptMasterServiceImpl().searchManuscriptRecord(digitalManuscriptVO
	 * , false , setFirst.intValue() ,
	 * IndvenApplicationConstants.RECORDS_PER_PAGE);
	 * 
	 * objResult.setListOfElemnents((List<DigitalManuscriptVO>)
	 * objMap.get("manuscriptList")); Long totalRecords = (Long)
	 * objMap.get("totalCount"); listForPagingCombo = new ArrayList<>();
	 * for(long i=1 ; i<=
	 * (totalRecords/IndvenApplicationConstants.RECORDS_PER_PAGE)+1 ; i++) {
	 * listForPagingCombo.add(i); } status = SUCCESS; } catch (OMDPCoreException
	 * e) { logger.error(e);
	 * digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
	 * digitalManuscriptVO
	 * .setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException
	 * .UNABLE_TO_FIND_MANUSCRIPT_DETAILS, IndvenApplicationConstants.LOCALE));
	 * addActionError(digitalManuscriptVO.getMessage()); e.printStackTrace(); }
	 * catch(Exception e) { logger.error(e);
	 * digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
	 * digitalManuscriptVO
	 * .setMessage(IndvenMessageResolver.resolveMessage(OMDPCoreException
	 * .UNABLE_TO_FIND_MANUSCRIPT_DETAILS, IndvenApplicationConstants.LOCALE));
	 * addActionError(digitalManuscriptVO.getMessage()); e.printStackTrace(); }
	 * finally { generateLists(); }
	 * 
	 * return status;
	 * 
	 * }
	 */

	public String markAsDeleted() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		String id = getRequest().getParameter("id");
		digitalManuscriptVO = new DigitalManuscriptVO();
		try {
			if (id != null) {
				boolean msg = manuscriptMasterServiceImpl
						.deleteManuscriptRecord(Long.parseLong(id));
				if (msg) {
					status = SUCCESS;
				}
			} else {
				throw new OMDPCoreException(
						OMDPCoreException.UNABLE_TO_DELETE_MANUSCRIPT_DETAILS);
			}
			digitalManuscriptVO.setMessage(IndvenMessageResolver
					.resolveMessage(
							OMDPCoreException.SUCCESSFULLY_DELETED_MANUSCRIPT,
							IndvenApplicationConstants.LOCALE));
			addActionMessage(digitalManuscriptVO.getMessage());
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(
					digitalManuscriptVO, e, IndvenApplicationConstants.LOCALE);
			addActionError(digitalManuscriptVO.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} finally {
			generateLists();
		}

		return status;
	}

	public String generateLists() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		try {
			languageVOs = manuscriptMasterServiceImpl.findAllLanguages();
			materialVOs = manuscriptMasterServiceImpl.findAllMaterials();
			organisationVOs = manuscriptMasterServiceImpl
					.findAllOrganisations();
			scriptVOs = manuscriptMasterServiceImpl.findAllScripts();

			categoryVOs = manuscriptMasterServiceImpl.findAllCategories();
			specificCategoryVOs = manuscriptMasterServiceImpl
					.findAllSpecificCategories();
			bundleMasterVOs = manuscriptMasterServiceImpl.findAllBundleNumber();

			documentTypes.add(new LabelValueVO("Book", "1"));
			documentTypes.add(new LabelValueVO("Manuscript", "2"));

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

	/**
	 * This is the action mehtod written for getting the data for auto-complete
	 * box of the 'Digitized By' field.
	 * 
	 * @return
	 */
	public String findAllDigitisers() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		try {
			String requestTerm = new String(getRequest().getParameter("term")
					.getBytes("ISO-8859-1"), "UTF-8");
			List<EmployeeMasterVO> manuscriptVOs = manuscriptMasterServiceImpl
					.findAllDigitisersForTerm(requestTerm);

			if (manuscriptVOs != null && manuscriptVOs.size() > 0) {
				jsonObject.put("digitisers", manuscriptVOs);
			} else {

				EmployeeMasterVO digVO = new EmployeeMasterVO();
				digVO.setFirstName("No record exists");
				digVO.setId(null);
				manuscriptVOs.add(digVO);
				jsonObject.put("digitisers", manuscriptVOs);
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

	public String findAllManuscripts() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String requestTerm = new String(getRequest().getParameter("term")
					.getBytes("ISO-8859-1"), "UTF-8");
			List<DigitalManuscriptVO> manuscriptVOs = manuscriptMasterServiceImpl
					.findAllManuscriptForTerm(requestTerm);

			if (manuscriptVOs != null && manuscriptVOs.size() > 0) {
				jsonObject.put("manuscripts", manuscriptVOs);
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

	public String findRealImage() {
		String status = ERROR;
		String tempAudioFilepath = null;
		try {
			//Original image path from the request object 
			String requestImagePath = new String(getRequest().getParameter(
					"filePath").getBytes("ISO-8859-1"), "UTF-8").trim();
			//Audio File path in db from the request object 		
			String audioDBPath = new String(getRequest().getParameter(
							"audioPath").getBytes("ISO-8859-1"), "UTF-8").trim();
			
			//Path of the temporary server location from IndvenApplicationConstants
			String tempPath = getRequest().getServletContext().getRealPath(IndvenApplicationConstants.IMAGE_FOLDER_DIR).trim();
			
            //Folder path of audio file which contains the audioFile 
			String audioFilePath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
					.getObject("audio.system.path").toString();
			//if audio is there for the frame then copy it to temporary location of server to play it
			if(audioDBPath != null && !audioDBPath.equals("")){
			 tempAudioFilepath = (FilesUtil.copyFileToDirectory(audioFilePath+audioDBPath,tempPath,true)).trim();
			}	
			//Folder path of image file which contains the imageFile 
			String folderPath = (ResourceBundle.getBundle(
					"ApplicationResources", IndvenApplicationConstants.LOCALE)
					.getObject("images.system.path").toString()).trim();
			//copy the original image to temporary location of server to display it
			String realPath = (FilesUtil.copyFileToDirectory(folderPath+ requestImagePath,tempPath,false)).trim(); 
			audioVideoFileName = null;
			audioVideo = null;
			jsonObject.put("realPath", realPath);
			jsonObject.put("audioPath", tempAudioFilepath);
			status = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return status;
	}
	
	public String getAudioFile(){
		String status = ERROR;
		String tempAudioFilepath = null;
		try{
		//Audio File path in db from the request object 
		String audioDBPath = new String(getRequest().getParameter(
				"audioPath").getBytes("ISO-8859-1"), "UTF-8").trim();
		
		//Path of the temporary server location from IndvenApplicationConstants
		String tempPath = getRequest().getServletContext().getRealPath(IndvenApplicationConstants.IMAGE_FOLDER_DIR).trim();
		 //Folder path of audio file which contains the audioFile 
		String audioFilePath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
				.getObject("audio.system.path").toString();
		
		//if audio is there for the frame then copy it to temporary location of server to play it
		if(audioDBPath != null && !audioDBPath.equals("")){
		 tempAudioFilepath = (FilesUtil.copyFileToDirectory(audioFilePath+audioDBPath,tempPath,true)).trim();
		}

		jsonObject.put("audioPath", tempAudioFilepath);
		status = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			status = ERROR;
		}
		return status;
	}

	public String findAllCategoryAutoComplete() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String requestTerm = new String(getRequest().getParameter("term")
					.getBytes("ISO-8859-1"), "UTF-8");
			List<CategoryVO> categoryVOs = manuscriptMasterServiceImpl
					.findAllCategoryTerm(requestTerm);

			if (categoryVOs != null && categoryVOs.size() > 0) {
				jsonObject.put("categories", categoryVOs);
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

	public String findAllPublishers() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String requestTerm = new String(getRequest().getParameter("term")
					.getBytes("ISO-8859-1"), "UTF-8");
			List<PublisherVO> publisherVOs = manuscriptMasterServiceImpl
					.findAllPublisherForTerm(requestTerm);

			if (publisherVOs != null && publisherVOs.size() > 0) {
				jsonObject.put("publishers", publisherVOs);
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

	public String findAllOrganisations() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String requestTerm = new String(getRequest().getParameter("term")
					.getBytes("ISO-8859-1"), "UTF-8");
			List<OrganisationVO> organisationVOs = manuscriptMasterServiceImpl
					.findAllOrganisationForTerm(requestTerm);

			if (organisationVOs != null && organisationVOs.size() > 0) {
				jsonObject.put("organisations", organisationVOs);
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

	public String findAllTags() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {
			String requestTerm = new String(getRequest().getParameter("term")
					.getBytes("ISO-8859-1"), "UTF-8");
			List<TagMasterVO> tagVOs = manuscriptMasterServiceImpl
					.findAllTagForTerm(requestTerm);

			if (tagVOs != null && tagVOs.size() > 0) {
				jsonObject.put("tags", tagVOs);
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

	public String findNoOfManuscripts() {
		String status = ERROR;
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		EmployeeMasterServiceImpl employeeMasterServiceImpl = new EmployeeMasterServiceImpl();
		try {
/*<<<<<<< .mine
			UserInfoVO userInfoVO = (UserInfoVO) super.getRequest()
					.getSession().getAttribute("loginData");
			if (userInfoVO != null
					&& userInfoVO.getRoleMasterFkId().compareTo(
							IndvenApplicationConstants.ADMIN_ID) == 0) {
				Long totalBook = manuscriptMasterServiceImpl
						.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
				Long totalManuscript = manuscriptMasterServiceImpl
						.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
				Long totalRecord = totalBook + totalManuscript;
=======*/
			UserInfoVO userInfoVO = (UserInfoVO) super.getRequest().getSession().getAttribute("loginData");
			if(userInfoVO != null && userInfoVO.getRoleMasterFkId().compareTo(IndvenApplicationConstants.ADMIN_ID) == 0) {
				List<Long> manuscriptStatus =  manuscriptMasterServiceImpl.findNoOfRecordsForDifferentStatus(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
				List<Long> bookStatus =  manuscriptMasterServiceImpl.findNoOfRecordsForDifferentStatus(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
				Long totalBook = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
				Long totalManuscript = manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
				Long totalRecord = totalBook+totalManuscript;

				List<LabelValueVO> vos = new ArrayList<>();
				vos.add(new LabelValueVO("Number of Users",
						employeeMasterServiceImpl.findNoOfEmployees()
								.toString()));
				vos.add(new LabelValueVO("Number of Records", totalRecord
						.toString()));
				vos.add(new LabelValueVO("Number of Books", totalBook
						.toString()));
				vos.add(new LabelValueVO("Number of Manuscripts",
						totalManuscript.toString()));
				if (vos != null && vos.size() > 0) {
					jsonObject.put("digitalManuscripts", vos);
					jsonObject.put("manuscriptStatus", manuscriptStatus);
					jsonObject.put("bookStatus", bookStatus);
				}
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

	public String saveTempPhoto() {
		/*
		 * Saves the photos to a temporary folder within the web server Extracts
		 * NMM data Returns JSON object
		 */
		String status = ERROR;
		try {
			jsonObject = new JSONObject();
			JSONObject imageContainer = new JSONObject();

			String[] photoNames = photoFileName.split(" ");
			int count = 0;
			Long height = 0L;
			Long width = 0L;
			Long xResolution = 0L;
			Long yResolution = 0L;
			String lensMake = null;
			String lensModel = null;
			Date createdDate = null;
			Date modifiedDate = null;

			Map<Date, Integer> createdDateCounter = new HashMap<>();
			Map<Date, Integer> modifiedDateCounter = new HashMap<>();
			Map<String, Integer> lensMakeCounter = new HashMap<>();
			Map<String, Integer> lensModelCounter = new HashMap<>();

			Random random = new Random();

			for (File i : photo) {
				String imageName = photoNames[count].split(",")[0];
				if (!(imageName.contains("Thumbs.db"))) {
					String convertedImgName = CustomBeanUtil
							.formatImageName(photoNames[count].split(",")[0]);
					String tempImage = String.valueOf(random.nextLong())
							+ convertedImgName;
					String targetPath = getRequest().getServletContext()
							.getRealPath(
									IndvenApplicationConstants.IMAGE_FOLDER_DIR
											+ tempImage);
					imagePath = tempImage;
					FilesUtil.saveFile(i, tempImage, targetPath);

					File file = new File(targetPath);
					Metadata metadata = ImageMetadataReader.readMetadata(file);

					/* For iteratively displaying all available EXIF attributes */
					/*
					 * for (Directory directory : metadata.getDirectories()) {
					 * for (Tag tag : directory.getTags()) {
					 * System.out.println(tag); } }
					 */
					// Obtain the EXIF directory
					ExifSubIFDDirectory directory = metadata
							.getDirectory(ExifSubIFDDirectory.class);
					ExifIFD0Directory directory2 = metadata
							.getDirectory(ExifIFD0Directory.class);

					// Query the tag value
					if (directory != null && directory2 != null) {
						if (directory
								.containsTag(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)) {
							createdDate = directory
									.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
							if (createdDateCounter.containsKey(createdDate)) {
								int j = createdDateCounter.get(createdDate);
								createdDateCounter.put(createdDate, ++j);
							} else {
								createdDateCounter.put(createdDate, 0);
							}
						}

						// Query the tag value
						if (directory
								.containsTag(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED)) {
							modifiedDate = directory
									.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);
							if (modifiedDateCounter.containsKey(modifiedDate)) {
								int j = modifiedDateCounter.get(modifiedDate);
								modifiedDateCounter.put(modifiedDate, ++j);
							} else {
								modifiedDateCounter.put(modifiedDate, 0);
							}
						}

						// Query the tag value
						if (directory2.containsTag(ExifIFD0Directory.TAG_MAKE)) {
							lensMake = directory2
									.getString(ExifIFD0Directory.TAG_MAKE);
							if (lensMakeCounter.containsKey(lensMake)) {
								int j = lensMakeCounter.get(lensMake);
								lensMakeCounter.put(lensMake, ++j);
							} else {
								lensMakeCounter.put(lensMake, 0);
							}
						}

						// Query the tag value
						if (directory2.containsTag(ExifIFD0Directory.TAG_MODEL)) {
							lensModel = directory2
									.getString(ExifIFD0Directory.TAG_MODEL);
							if (lensModelCounter.containsKey(lensModel)) {
								int j = lensModelCounter.get(lensModel);
								lensModelCounter.put(lensModel, ++j);
							} else {
								lensModelCounter.put(lensModel, 0);
							}
						}

						// Query the tag value
						if (directory2
								.containsTag(ExifIFD0Directory.TAG_X_RESOLUTION)) {
							xResolution += directory2
									.getLong(ExifIFD0Directory.TAG_X_RESOLUTION);
						}

						if (directory2
								.containsTag(ExifIFD0Directory.TAG_Y_RESOLUTION)) {
							yResolution += directory2
									.getLong(ExifIFD0Directory.TAG_Y_RESOLUTION);
						}

						// Query the tag value
						if (directory
								.containsTag(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT)) {
							height += directory
									.getLong(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);
						}

						if (directory
								.containsTag(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH)) {
							width += directory
									.getLong(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
						}
					}

					targetPath = targetPath.replace("\\", "/");
					// Put the temp path of the image in JSON object
					imageContainer.put(count, targetPath);

					count++;
				}
			}

			// Find median of value
			int upperbound = 0;
			createdDate = new Date();
			for (Date key : createdDateCounter.keySet()) {
				if (createdDateCounter.get(key) >= upperbound) {
					upperbound = createdDateCounter.get(key);
					createdDate = key;
				}
			}

			// Find median of value
			upperbound = 0;
			modifiedDate = new Date();
			for (Date key : modifiedDateCounter.keySet()) {
				if (modifiedDateCounter.get(key) >= upperbound) {
					upperbound = modifiedDateCounter.get(key);
					modifiedDate = key;
				}
			}

			// Find median of value
			upperbound = 0;
			lensMake = "";
			for (String key : lensMakeCounter.keySet()) {
				if (lensMakeCounter.get(key) >= upperbound) {
					upperbound = lensMakeCounter.get(key);
					lensMake = key;
				}
			}

			// Find median of value
			upperbound = 0;
			lensModel = "";
			for (String key : lensModelCounter.keySet()) {
				if (lensModelCounter.get(key) >= upperbound) {
					upperbound = lensModelCounter.get(key);
					lensModel = key;
				}
			}

			// Find average of value
			height /= count;
			width /= count;

			// Find average of value
			xResolution /= count;
			yResolution /= count;

			// Put data into JSON object
			jsonObject.put("images", imageContainer);
			jsonObject.put("createdDate", createdDate.toString());
			jsonObject.put("modifiedDate", modifiedDate.toString());
			jsonObject.put("lensMake", lensMake.trim());
			jsonObject.put("lensModel", lensModel.trim());
			jsonObject.put("xResolution", xResolution);
			jsonObject.put("yResolution", yResolution);
			jsonObject.put("height", height);
			jsonObject.put("width", width);
			jsonObject.put("status", "success");
			status = SUCCESS;
		} catch (IOException e) {
			jsonObject.put("status", "failure");
			e.printStackTrace();
		} catch (Exception e) {
			jsonObject.put("status", "failure");
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * Generate the NMM details for a single frame It return JSON date
	 * 
	 * @author Rakesh Kumar Sahoo
	 */
	public String frameNMMDate() {
		String status = ERROR;
		jsonObject = new JSONObject();
		String fileName = getRequest().getParameter("filePath").trim();
		String targetPath = getRequest().getServletContext().getRealPath(
				IndvenApplicationConstants.IMAGE_FOLDER_DIR + fileName);
		Long height = 0L;
		Long width = 0L;
		Long xResolution = 0L;
		Long yResolution = 0L;
		String lensMake = "";
		String lensModel = "";
		Date createdDate = null;
		Date modifiedDate = null;

		try {
			File file = new File(targetPath);
			Metadata metadata = ImageMetadataReader.readMetadata(file);

			ExifSubIFDDirectory directory = metadata
					.getDirectory(ExifSubIFDDirectory.class);
			ExifIFD0Directory directory2 = metadata
					.getDirectory(ExifIFD0Directory.class);

			// Query the tag value
			if (directory != null && directory2 != null) {
				if (directory
						.containsTag(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)) {
					createdDate = directory
							.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
				}

				// Query the tag value
				if (directory
						.containsTag(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED)) {
					modifiedDate = directory
							.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);
				}

				// Query the tag value
				if (directory2.containsTag(ExifIFD0Directory.TAG_MAKE)) {
					lensMake = directory2.getString(ExifIFD0Directory.TAG_MAKE);
				}

				// Query the tag value
				if (directory2.containsTag(ExifIFD0Directory.TAG_MODEL)) {
					lensModel = directory2
							.getString(ExifIFD0Directory.TAG_MODEL);
				}

				// Query the tag value
				if (directory2.containsTag(ExifIFD0Directory.TAG_X_RESOLUTION)) {
					xResolution += directory2
							.getLong(ExifIFD0Directory.TAG_X_RESOLUTION);
				}

				if (directory2.containsTag(ExifIFD0Directory.TAG_Y_RESOLUTION)) {
					yResolution += directory2
							.getLong(ExifIFD0Directory.TAG_Y_RESOLUTION);
				}

				// Query the tag value
				if (directory
						.containsTag(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT)) {
					height += directory
							.getLong(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);
				}

				if (directory
						.containsTag(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH)) {
					width += directory
							.getLong(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
				}
			}
			jsonObject.put("createdDate", createdDate.toString());
			jsonObject.put("modifiedDate", modifiedDate.toString());
			jsonObject.put("lensMake", lensMake.trim());
			jsonObject.put("lensModel", lensModel.trim());
			jsonObject.put("xResolution", xResolution);
			jsonObject.put("yResolution", yResolution);
			jsonObject.put("height", height);
			jsonObject.put("width", width);
			jsonObject.put("status", "success");
			status = SUCCESS;

		} catch (IOException e) {
			jsonObject.put("status", "failure");
			e.printStackTrace();
		} catch (Exception e) {
			jsonObject.put("status", "failure");
			e.printStackTrace();
		}
		return status;
	}

	public String deleteFrame() {
		String status = ERROR;
		String[] frameId = frameIds.split(",");
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		try {
			manuscriptMasterServiceImpl.deleteFrame(frameId);
			jsonObject.put("message", "Successfully Deleted The Frames");
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			String msg = IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_DELETE_FRAMES,
					IndvenApplicationConstants.LOCALE);
			jsonObject.put("message", msg);
			status = ERROR;
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * Exports the report to the required format Depending upon the type
	 * parameter
	 * 
	 * Contrary to common Struts 2 protocol, this method does not return a
	 * result String
	 * 
	 * @author Neel Borooah
	 */
	public void exportReport() {

		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();

		try {

			String id = getRequest().getParameter("id");

			if (id != null) {
				// digitalManuscriptVO =
				// manuscriptMasterServiceImpl.findManuscriptById(Long.parseLong(id),
				// getRequest().getServletContext().getRealPath(IndvenApplicationConstants.IMAGE_FOLDER_DIR));
				digitalDocumentVO = manuscriptMasterServiceImpl
						.findDocumentByManuscriptId(Long.parseLong(id));
			} else {
				throw new OMDPCoreException(
						OMDPCoreException.UNABLE_TO_FIND_MANUSCRIPT_DETAILS);
			}

			// Setting the response as a downloadable attachment
			ServletActionContext.getResponse()
					.setContentType("application/pdf");
			ServletActionContext.getResponse().setHeader(
					"Content-Disposition: attachment",
					"inline; filename=report.pdf");

			ServletOutputStream outputStream = ServletActionContext
					.getResponse().getOutputStream();

			// Exporting to PDF
			Document document = new Document();
			PdfWriter.getInstance(document, outputStream);
			document.open();
			document.add(new Paragraph("Name : "
					+ digitalDocumentVO.getDigitalManuscriptVO().getName()
					+ "\n"));
			document.add(new Paragraph(digitalDocumentVO.getDetailsVO()
					.getText()));
			document.setMargins(70, 70, 175, 175);
			document.close();

		} catch (Exception e) {
			logger.error(e);
			addActionError("Unable to generate report");
		}

	}

	/**
	 * @return the recordStatusList
	 */
	public final List<String> getRecordStatusList() {
		for (ManuscriptTypeEnum dir : ManuscriptTypeEnum.values()) {
			// manuscriptWorkTypes.add(new LabelValueVO(dir.name(),
			// dir.getValue().toString()));
			recordStatusList.add(dir.name());
		}
		return recordStatusList;
	}

	/**
	 * @param recordStatusList
	 *            the recordStatusList to set
	 */
	public final void setRecordStatusList(List<String> recordStatusList) {
		this.recordStatusList = recordStatusList;
	}

	/**
	 * @return the manuscriptWorkTypes
	 */
	public final List<String> getManuscriptWorkTypes() {
		for (ManuscriptWorkType dir : ManuscriptWorkType.values()) {
			// manuscriptWorkTypes.add(new LabelValueVO(dir.name(),
			// dir.getValue().toString()));
			manuscriptWorkTypes.add(dir.name());
		}
		return manuscriptWorkTypes;
	}

	/**
	 * @return the manuscriptConditionTypes
	 */
	public final List<String> getManuscriptConditionTypes() {
		for (ManuscriptConditionType dir : ManuscriptConditionType.values()) {
			// manuscriptWorkTypes.add(new LabelValueVO(dir.name(),
			// dir.getValue().toString()));
			manuscriptConditionTypes.add(dir.name());
		}
		return manuscriptConditionTypes;
	}

	/**
	 * @param manuscriptConditionTypes
	 *            the manuscriptConditionTypes to set
	 */
	public final void setManuscriptConditionTypes(
			List<String> manuscriptConditionTypes) {
		this.manuscriptConditionTypes = manuscriptConditionTypes;
	}

	/**
	 * @return the manuscriptDocumentationTypes
	 */
	public final List<String> getManuscriptDocumentationTypes() {
		for (ManuscriptDocumentationType dir : ManuscriptDocumentationType
				.values()) {
			// manuscriptWorkTypes.add(new LabelValueVO(dir.name(),
			// dir.getValue().toString()));
			manuscriptDocumentationTypes.add(dir.name());
		}
		return manuscriptDocumentationTypes;
	}

	/**
	 * @param manuscriptDocumentationTypes
	 *            the manuscriptDocumentationTypes to set
	 */
	public final void setManuscriptDocumentationTypes(
			List<String> manuscriptDocumentationTypes) {
		this.manuscriptDocumentationTypes = manuscriptDocumentationTypes;
	}

	/**
	 * @return the sourceOfCatagoryTypes
	 */
	public final List<String> getSourceOfCatagoryTypes() {
		for (SourceOfCatalogueEnum dir : SourceOfCatalogueEnum.values()) {
			// manuscriptWorkTypes.add(new LabelValueVO(dir.name(),
			// dir.getValue().toString()));
			sourceOfCatagoryTypes.add(dir.name());
		}
		return sourceOfCatagoryTypes;
	}

	/**
	 * @param sourceOfCatagoryTypes
	 *            the sourceOfCatagoryTypes to set
	 */
	public final void setSourceOfCatagoryTypes(
			List<String> sourceOfCatagoryTypes) {
		this.sourceOfCatagoryTypes = sourceOfCatagoryTypes;
	}

	/**
	 * @param manuscriptWorkTypes
	 *            the manuscriptWorkTypes to set
	 */
	public final void setManuscriptWorkTypes(List<String> manuscriptWorkTypes) {
		this.manuscriptWorkTypes = manuscriptWorkTypes;
	}

	public DigitalManuscriptVO getDigitalManuscriptVO() {
		return digitalManuscriptVO;
	}

	public void setDigitalManuscriptVO(DigitalManuscriptVO digitalManuscriptVO) {
		try {
			this.digitalManuscriptVO = digitalManuscriptVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<LanguageVO> getLanguageVOs() {
		return languageVOs;
	}

	public void setLanguageVOs(List<LanguageVO> languageVOs) {
		this.languageVOs = languageVOs;
	}

	public List<MaterialVO> getMaterialVOs() {
		return materialVOs;
	}

	public void setMaterialVOs(List<MaterialVO> materialVOs) {
		this.materialVOs = materialVOs;
	}

	public List<OrganisationVO> getOrganisationVOs() {
		return organisationVOs;
	}

	public void setOrganisationVOs(List<OrganisationVO> organisationVOs) {
		this.organisationVOs = organisationVOs;
	}

	public AuthorVO getAuthorVO() {
		return authorVO;
	}

	public void setAuthorVO(AuthorVO authorVO) {
		this.authorVO = authorVO;
	}

	public List<AuthorVO> getAuthorVOs() {
		return authorVOs;
	}

	public void setAuthorVOs(List<AuthorVO> authorVOs) {
		this.authorVOs = authorVOs;
	}

	public PublicationVO getPublicationVO() {
		return publicationVO;
	}

	public void setPublicationVO(PublicationVO publicationVO) {
		this.publicationVO = publicationVO;
	}

	public List<ScriptVO> getScriptVOs() {
		return scriptVOs;
	}

	public void setScriptVOs(List<ScriptVO> scriptVOs) {
		this.scriptVOs = scriptVOs;
	}

	public List<CategoryVO> getCategoryVOs() {
		return categoryVOs;
	}

	public void setCategoryVOs(List<CategoryVO> categoryVOs) {
		this.categoryVOs = categoryVOs;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public List<SpecificCategoryVO> getSpecificCategoryVOs() {
		return specificCategoryVOs;
	}

	public void setSpecificCategoryVOs(
			List<SpecificCategoryVO> specificCategoryVOs) {
		this.specificCategoryVOs = specificCategoryVOs;
	}

	public FindAllResultVO<DigitalManuscriptVO> getObjResult() {
		return objResult;
	}

	public void setObjResult(FindAllResultVO<DigitalManuscriptVO> objResult) {
		this.objResult = objResult;
	}

	public List<LabelValueVO> getDocumentTypes() {
		return documentTypes;
	}

	public void setDocumentTypes(List<LabelValueVO> documentTypes) {
		this.documentTypes = documentTypes;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<File> getPhoto() {
		return photo;
	}

	public void setPhoto(List<File> photo) {
		this.photo = photo;
	}

	/**
	 * @return the bundleMasterVOs
	 */
	public List<BundleMasterVO> getBundleMasterVOs() {
		return bundleMasterVOs;
	}

	/**
	 * @param bundleMasterVOs
	 *            the bundleMasterVOs to set
	 */
	public void setBundleMasterVOs(List<BundleMasterVO> bundleMasterVOs) {
		this.bundleMasterVOs = bundleMasterVOs;
	}

	public DigitalDocumentVO getDigitalDocumentVO() {
		return digitalDocumentVO;
	}

	public void setDigitalDocumentVO(DigitalDocumentVO digitalDocumentVO) {
		this.digitalDocumentVO = digitalDocumentVO;
	}

	public String makeRecordForWFL() throws UnsupportedEncodingException {
		String status = SUCCESS;
		Long id = Long.parseLong(getRequest().getParameter("id"));
		String name = new String(getRequest().getParameter("name").getBytes(
				"ISO-8859-1"), "UTF-8");

		CurrentProcessMasterBean processMasterBean = new CurrentProcessMasterBean();
		processMasterBean.setReferenceFkId(id);
		processMasterBean.setDescription("");
		processMasterBean.setLocationMasterFkId(1L);
		processMasterBean.setProcessMasterFkId(1L);
		processMasterBean.setName("Transcribe Manuscript");

		List<CurrentProcessDetailsBean> processDetailsList = new ArrayList<>();

		CurrentProcessDetailsBean processDtls1 = new CurrentProcessDetailsBean();
		processDtls1.setUrl("workflowWork.action?id=" + id);
		processDtls1.setStatus((short) 1);
		processDtls1.setLevel(10L);
		processDtls1
				.setLocationUserRoleFkId(IndvenApplicationConstants.SCHOLAR_ROLE_ID);
		processDtls1.setScreenName(name);
		processDtls1.setIsReturnButton(false);

		processDtls1.setProcessTimeOut((short) 90);
		processDtls1.setIsUserRoleId((short) 0);
		processDtls1.setIsTerminateButton(true);
		processDtls1.setIsSaveButton(true);
		processDtls1.setIsAuthorizeButton(true);

		processDetailsList.add(processDtls1);

		CurrentProcessDetailsBean processDtls2 = new CurrentProcessDetailsBean();
		processDtls2.setUrl("workflowWork.action?id=" + id);
		processDtls2.setStatus((short) 0);
		processDtls2.setLevel(20L);
		processDtls2
				.setLocationUserRoleFkId(IndvenApplicationConstants.REVIEWER_ROLE_ID);
		processDtls2.setScreenName(name);
		processDtls2.setIsReturnButton(true);

		processDtls2.setProcessTimeOut((short) 90);
		processDtls2.setIsUserRoleId((short) 0);
		processDtls2.setIsTerminateButton(true);
		processDtls2.setIsSaveButton(true);
		processDtls2.setIsAuthorizeButton(true);
		processDetailsList.add(processDtls2);

		CurrentProcessDetailsBean processDtls3 = new CurrentProcessDetailsBean();
		processDtls3 = new CurrentProcessDetailsBean();
		processDtls3.setUrl("workflowWork.action?id=" + id);
		processDtls3.setStatus((short) 0);
		processDtls3.setLevel(30L);
		processDtls3
				.setLocationUserRoleFkId(IndvenApplicationConstants.PUBLISHER_ROLE_ID);
		processDtls3.setScreenName(name);
		processDtls3.setIsReturnButton(true);
		// processDtls3.setCurrentProcessMasterFkId(bean.getId());

		processDtls3.setProcessTimeOut((short) 90);
		processDtls3.setIsUserRoleId((short) 0);
		processDtls3.setIsTerminateButton(true);
		processDtls3.setIsSaveButton(true);
		processDtls3.setIsAuthorizeButton(true);

		processDetailsList.add(processDtls3);

		try {
			new WorkflowCoreDAOImpl().saveWFLDtlsData(processMasterBean,
					processDetailsList);
			
			resultVO.setMessage("Successfully submitted the record");
			resultVO.setStatus("success");
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			// digitalManuscriptVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e,
					IndvenApplicationConstants.LOCALE);
			addActionError(resultVO.getMessage());
			jsonObject.put("result", resultVO);
		}
		return status;
	}

	/**
	 * This method will gets the comment , frame id and manuscript id , current
	 * date and time from javascript as request param and prepares the
	 * DocumentCommentVO which is passed to respective service method to be
	 * saved
	 * 
	 * @return
	 */
	public String saveCommentForFrame() {
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		String status = ERROR;
		DocumentCommentVO commentVo = new DocumentCommentVO();

		String text;
		try {
			text = new String(getRequest().getParameter("text").getBytes(
					"ISO-8859-1"), "UTF-8");

			String time = getRequest().getParameter("currentime");
			Long frameId = Long.parseLong(getRequest().getParameter("frameId"));
			Long manuscriptId = Long.parseLong(getRequest().getParameter(
					"manuscriptId"));

			UserInfoVO userInfoVO = (UserInfoVO) getRequest()
					.getSession()
					.getAttribute(
							IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);

			commentVo.setFrameId(frameId);
			commentVo.setComment(text);
			commentVo.setCommentedBy(userInfoVO.getLoginName());
			commentVo.setCommentedOn(time);
			commentVo.setManuscriptId(manuscriptId);
			manuscriptMasterServiceImpl.saveCommentForFrame(commentVo);
			jsonObject.put("newcomment", commentVo);
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e,
					IndvenApplicationConstants.LOCALE);
			addActionError(resultVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			addActionError("Unable to save the comment.Please try later.");
		}
		return status;
	}

	public String reDirectToPage() {
		// TODO
		return SUCCESS;
	}

	private String frameIds;
	private String comment;

	/**
	 * @return the comment
	 */
	public final String getComment() {
		return comment;
	}

	/**
	 * @return the frameIds
	 */
	public final String getFrameIds() {
		return frameIds;
	}

	/**
	 * @param frameIds
	 *            the frameIds to set
	 */
	public final void setFrameIds(String frameIds) {
		this.frameIds = frameIds;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public final void setComment(String comment) {
		this.comment = comment;
	}

	public String assignProcessFromUserToRole() {
		String status = ERROR;
		Long processDtlsId = Long.parseLong(getRequest().getParameter(
				"activewflProcessId"));
		try {
			new WorkflowCoreServiceImpl()
					.assignProcessFromUserToRole(processDtlsId);
			status = SUCCESS;
			addActionMessage("Successfully completed the request");
			// jsonObject.put("newcomment", resultVO);
		} catch (WorkFlowCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(
					WorkFlowCoreException.UNABLE_TO_PROCESS_WORKFLOW,
					IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		} finally {
			generateLists();
		}
		return status;
	}

	public String createTranslateRecordAndSaveInfo() {
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		String status = ERROR;
		try {
			String language = getRequest().getParameter("language");
			String script = getRequest().getParameter("script");
			Long transcribedManId = Long.parseLong(getRequest().getParameter(
					"parentManId"));
			manuscriptMasterServiceImpl.createTranslateRecordAndSaveInfo(
					language, script, transcribedManId);

			resultVO.setMessage("Successfully submitted for translation");
			resultVO.setStatus(SUCCESS);
			jsonObject.put("message", resultVO);
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e,
					IndvenApplicationConstants.LOCALE);
			addActionError(resultVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_PROCESS_FOR_TRANSLATION,
					IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}

		return status;
	}

	public String getAvailableLanguages() {
		ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
		String status = ERROR;
		try {
			Long transcribedManId = Long.parseLong(getRequest().getParameter(
					"parentManId"));
			String languagesName = manuscriptMasterServiceImpl
					.getAvailableLanguages(transcribedManId);

			resultVO.setMessage("Successfully submitted for translation");
			resultVO.setStatus(SUCCESS);
			jsonObject.put("message", languagesName);
			status = SUCCESS;
		} catch (OMDPCoreException e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(resultVO, e,
					IndvenApplicationConstants.LOCALE);
			addActionError(resultVO.getMessage());
		} catch (Exception e) {
			logger.error(e);
			resultVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			resultVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_PROCESS_FOR_TRANSLATION,
					IndvenApplicationConstants.LOCALE));
			addActionError(resultVO.getMessage());
		}

		return status;
	}

}
