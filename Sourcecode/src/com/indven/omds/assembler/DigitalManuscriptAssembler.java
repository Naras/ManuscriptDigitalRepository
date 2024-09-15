/**
 * 
 */
package com.indven.omds.assembler;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.omds.entity.AuthorBean;
import com.indven.omds.entity.DigitalDocumentBean;
import com.indven.omds.entity.DigitalManuscriptBean;
import com.indven.omds.entity.DigitalManuscriptFrame;
import com.indven.omds.entity.MaterialBean;
import com.indven.omds.entity.NMMDetailsBean;
import com.indven.omds.entity.Organisation;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptConditionType;
import com.indven.omds.util.ManuscriptDocumentationType;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.omds.util.ManuscriptWorkType;
import com.indven.omds.util.MaterialTypeEnum;
import com.indven.omds.util.SourceOfCatalogueEnum;
import com.indven.omds.vo.AuthorVO;
import com.indven.omds.vo.DigitalDocumentDetailsVO;
import com.indven.omds.vo.DigitalDocumentVO;
import com.indven.omds.vo.DigitalManuscriptFrameVO;
import com.indven.omds.vo.DigitalManuscriptVO;
import com.indven.omds.vo.LanguageVO;
import com.indven.omds.vo.MaterialVO;
import com.indven.omds.vo.NMMDetailsVO;
import com.indven.omds.vo.OrganisationVO;
import com.indven.omds.vo.PublicationVO;
import com.indven.omds.vo.ScriptVO;
import com.indven.omds.vo.TagMasterVO;


/**
 * @author Deba Prasad
 *
 */
public class DigitalManuscriptAssembler {

	public static DigitalManuscriptVO convertEntityToVo(DigitalManuscriptBean bean) throws JSONException {
		DigitalManuscriptVO vo = new DigitalManuscriptVO();

		vo = (DigitalManuscriptVO) CustomBeanUtil.entityToVO(bean, vo);
		vo.setPublicationFkId(bean.getPublicationFKId());
		
		if(bean.getParentFkObj() != null) {
			vo.setParentName(bean.getParentFkObj().getName());
		}
		
		if(bean.getLanguageFkObj()!= null) {
			vo.setLanguageVO((LanguageVO)CustomBeanUtil.entityToVO(bean.getLanguageFkObj(), new LanguageVO()));
		}
		if(bean.getScriptFkObj()!= null) {
			vo.setScriptVO((ScriptVO)CustomBeanUtil.entityToVO(bean.getScriptFkObj(), new ScriptVO()));
		}
		
		if(bean.getAuthorFkObj() != null) {
			/* Converting the mapped author bean to vo if it isn't null */
			vo.setAuthorVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getAuthorFkObj(), new AuthorVO()));
			String period = vo.getAuthorVO().getPeriod();
			if(period != null && period.length() > 2) {
				/*The period is display in two forms in the view. One for the date and one for AD/BC. 
				Splitting the string from bean to conform to such data*/
				vo.getAuthorVO().setPeriod(period.substring(0, period.length() - 2));
				vo.getAuthorVO().setPeriodEra(period.substring(period.length() - 2));
			}
		}
		if(bean.getScribeFkObj() != null && bean.getScribeFkObj().getId() != null && bean.getScribeFkObj().getId() > 0) {
			vo.setScribeVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getScribeFkObj(), new AuthorVO()));
		}
		
		if(bean.getCommentatorFkObj() != null && bean.getCommentatorFkObj().getId() != null && bean.getCommentatorFkObj().getId() > 0) {
			vo.setCommentatorVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getCommentatorFkObj(), new AuthorVO()));
		}
		
		if(bean.getSubCommentatorFkObj() != null && bean.getSubCommentatorFkObj().getId() != null && bean.getSubCommentatorFkObj().getId() > 0) {
			vo.setSubCommentatorVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getSubCommentatorFkObj(), new AuthorVO()));
		}
		
		if(bean.getTranslatorFkObj() != null && bean.getTranslatorFkObj().getId() != null && bean.getTranslatorFkObj().getId() > 0) {
			vo.setTranslatorVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getTranslatorFkObj(), new AuthorVO()));
		}
		
		if(bean.getPublicationFKObj() != null) {
			vo.setPublicationVO((PublicationVO) CustomBeanUtil.entityToVO(bean.getPublicationFKObj(), new PublicationVO()));
		}
		if(bean.getOrganisationFkObj() != null)
			vo.setOrganisationVO((OrganisationVO) CustomBeanUtil.entityToVO(bean.getOrganisationFkObj(), new OrganisationVO()));
		if(bean.getMaterialFkObj() != null)
			vo.setMaterialVO((MaterialVO) CustomBeanUtil.entityToVO(bean.getMaterialFkObj(), new MaterialVO()));
		
		if(bean.getNmmDetailsFkObj() != null) {
			vo.setNmmDetailsVO((NMMDetailsVO) CustomBeanUtil.entityToVO(bean.getNmmDetailsFkObj(), new NMMDetailsVO()));
		}
		
		if(bean.getDigitalManuscriptFrames() != null && bean.getDigitalManuscriptFrames().size() > 0) {
			/*
			 * If there are Digital Frames, then convert them to JSON objects 
			 * and put them in the fileDiskPathContainer field
			 */
	/*		JSONArray jsonArray = new JSONArray();
			for(DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", frame.getId());
				jsonObject.put("filePath", frame.getFilePath());
				if(frame.getDigitalDocumentBean() != null) {
					jsonObject.put("documentId", frame.getDigitalDocumentBean().getId());
					jsonObject.put("text", frame.getDigitalDocumentBean().getDocumentDetailsBean().getText());
				} else {
					jsonObject.put("documentId", "");
					jsonObject.put("text", "");
				}
				jsonArray.put(jsonObject);
			}
			vo.setFileDiskPathContainer(jsonArray.toString());*/
			
			List<DigitalManuscriptFrameVO> digitalManuscriptFrameVOs = new ArrayList<>();
			for(DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
				digitalManuscriptFrameVOs.add((DigitalManuscriptFrameVO) CustomBeanUtil.entityToVO(frame, new DigitalManuscriptFrameVO()));
			}
			vo.setDigitalManuscriptFrameVOs(digitalManuscriptFrameVOs);
			vo.setTotalFrame(digitalManuscriptFrameVOs.size());
		}
		
		return vo;
	}
	
	public static DigitalManuscriptBean convertVoToEntity(DigitalManuscriptVO vo) throws JSONException {
		DigitalManuscriptBean bean = new DigitalManuscriptBean();

		bean.setAuthorFKId(vo.getAuthorFKId());
		bean.setContributionToAyurveda(vo.getContributionToAyurveda());
		bean.setId(vo.getId());
		bean.setMaterialFkId(vo.getMaterialFkId());
		bean.setName(vo.getName());
		bean.setRegionalName(vo.getRegionalName());		
		bean.setDiacriticName(vo.getDiacriticName());
		bean.setAccNumber(vo.getAccNumber());
		bean.setParentFKId(vo.getParentFKId());		
		
		bean.setManuscriptId(vo.getManuscriptId());

		bean.setTableOfContents(vo.getTableOfContents());
		bean.setAnyOtherDetails(vo.getAnyOtherDetails());
		bean.setOrganisationFkId(vo.getOrganisationFkId());
		
		bean.setTotalNumberOfFolios(vo.getTotalNumberOfFolios());
		bean.setTotalNumberOfMaps(vo.getTotalNumberOfMaps());
		bean.setCatalogueDetails(vo.getCatalogueDetails());
		bean.setCatalogueNumber(vo.getCatalogueNumber());
		bean.setDigitizerId(vo.getDigitizerId());
		bean.setIsBound(vo.getIsBound());
		bean.setNatureOfCollection(vo.getNatureOfCollection());
		if(vo.getSourceOfCatalogue() != null && vo.getSourceOfCatalogue().length() > 0)
			bean.setSourceOfCatalogue(SourceOfCatalogueEnum.valueOf(vo.getSourceOfCatalogue()));
		if(vo.getConditionOfManuscript() != null && vo.getConditionOfManuscript().length() > 0)
			bean.setConditionOfManuscript(ManuscriptConditionType.valueOf( vo.getConditionOfManuscript()));
		
		if(vo.getManuscriptType() != null && vo.getManuscriptType().length() > 0)
			bean.setManuscriptType(ManuscriptTypeEnum.valueOf( vo.getManuscriptType()));
		
		if(vo.getRecordStatus() != null && vo.getRecordStatus().length() > 0)
			bean.setRecordStatus(DocumentStatusEnum.valueOf( vo.getRecordStatus()));
		
	//	manuscriptType
		if(vo.getDocumentationOfManuscript() != null && vo.getDocumentationOfManuscript().length() > 0)
			bean.setDocumentationOfManuscript(ManuscriptDocumentationType.valueOf( vo.getDocumentationOfManuscript()));
		bean.setBeginningLine(vo.getBeginningLine());
		bean.setEndingLine(vo.getEndingLine());
		bean.setColophon(vo.getColophon());
		
		
		bean.setBundleMasterFkId(vo.getBundleMasterFkId());
		if(bean.getBundleMasterFkId() != null && bean.getBundleMasterFkId() < 0) {
			bean.setBundleMasterFkId(null);
		}
		
		bean.setLanguageFkId(vo.getLanguageFkId());
		if(bean.getLanguageFkId() != null && bean.getLanguageFkId() <= 0) 
			bean.setLanguageFkId(null);
		if(bean.getOrganisationFkId() != null && bean.getOrganisationFkId() < 0) {
			bean.setOrganisationFkId(null);
		}
		bean.setScriptFkId(vo.getScriptFkId());
		if(bean.getScriptFkId() != null && bean.getScriptFkId() <= 0) 
			bean.setScriptFkId(null);
		bean.setRemarks(vo.getRemarks());
		bean.setSummary(vo.getSummary());
		bean.setCategoryFkId(vo.getCategoryFkId());
		if(bean.getCategoryFkId() != null && bean.getCategoryFkId() <= 0) {
			bean.setCategoryFkId(null);
		}
		/*bean.setSpecificCategoryFkId(vo.getSpecificCategoryFkId());*/
		if(vo.getTypeOfWork() != null && vo.getTypeOfWork().length() > 0)
			bean.setTypeOfWork(ManuscriptWorkType.valueOf(vo.getTypeOfWork()));
		bean.setUniquenessOfWork(vo.getUniquenessOfWork());
		bean.setDocumentType(vo.getDocumentType());
		
		AuthorBean authorBean = new AuthorBean();
		authorBean.setId(vo.getAuthorVO().getId());
		authorBean.setLifeHistory(vo.getAuthorVO().getLifeHistory());
		authorBean.setName(vo.getAuthorVO().getName());
		authorBean.setDiacriticName(vo.getAuthorVO().getDiacriticName());
		authorBean.setRegionalName(vo.getAuthorVO().getRegionalName());
		authorBean.setType(IndvenApplicationConstants.PERSON_TYPE_AUTHOR);
		if(vo.getAuthorVO().getPeriod() != null && vo.getAuthorVO().getPeriodEra() != null)
			authorBean.setPeriod(vo.getAuthorVO().getPeriod().concat(vo.getAuthorVO().getPeriodEra()));
		if(authorBean.getPeriod() != null && authorBean.getPeriod().length() <= 2) {
			authorBean.setPeriod(null);
		}
		bean.setAuthorFkObj(authorBean);
		
		if(vo.getScribeVO() != null) {
			AuthorBean scribeBean = new AuthorBean();
			scribeBean.setId(vo.getScribeVO().getId());
			scribeBean.setLifeHistory(vo.getScribeVO().getLifeHistory());
			scribeBean.setName(vo.getScribeVO().getName());
			scribeBean.setDiacriticName(vo.getScribeVO().getDiacriticName());
			scribeBean.setRegionalName(vo.getScribeVO().getRegionalName());
			scribeBean.setType(IndvenApplicationConstants.PERSON_TYPE_SCRIBE);
			if(vo.getScribeVO().getPeriod() != null && vo.getScribeVO().getPeriodEra() != null)
				scribeBean.setPeriod(vo.getScribeVO().getPeriod().concat(vo.getScribeVO().getPeriodEra()));
			if(scribeBean.getPeriod() != null && scribeBean.getPeriod().length() <= 2) {
				scribeBean.setPeriod(null);
			}
			bean.setScribeFkObj(scribeBean);
		}
		
		if(vo.getCommentatorVO() != null) {
			AuthorBean commentatorBean = new AuthorBean();
			commentatorBean.setId(vo.getCommentatorVO().getId());
			commentatorBean.setLifeHistory(vo.getCommentatorVO().getLifeHistory());
			commentatorBean.setName(vo.getCommentatorVO().getName());
			commentatorBean.setDiacriticName(vo.getCommentatorVO().getDiacriticName());
			commentatorBean.setRegionalName(vo.getCommentatorVO().getRegionalName());
			commentatorBean.setType(IndvenApplicationConstants.PERSON_TYPE_COMMENTATOR);
			if(vo.getCommentatorVO().getPeriod() != null && vo.getCommentatorVO().getPeriodEra() != null)
				commentatorBean.setPeriod(vo.getCommentatorVO().getPeriod().concat(vo.getCommentatorVO().getPeriodEra()));
			if(commentatorBean.getPeriod() != null && commentatorBean.getPeriod().length() <= 2) {
				commentatorBean.setPeriod(null);
			}
			bean.setCommentatorFkObj(commentatorBean);
		}
		
		if(vo.getSubCommentatorVO() != null) {
			AuthorBean subCommentatorBean = new AuthorBean();
			subCommentatorBean.setId(vo.getSubCommentatorVO().getId());
			subCommentatorBean.setLifeHistory(vo.getSubCommentatorVO().getLifeHistory());
			subCommentatorBean.setName(vo.getSubCommentatorVO().getName());
			subCommentatorBean.setDiacriticName(vo.getSubCommentatorVO().getDiacriticName());
			subCommentatorBean.setRegionalName(vo.getSubCommentatorVO().getRegionalName());
			subCommentatorBean.setType(IndvenApplicationConstants.PERSON_TYPE_SUBCOMMENTATOR);
			if(vo.getSubCommentatorVO().getPeriod() != null && vo.getSubCommentatorVO().getPeriodEra() != null)
				subCommentatorBean.setPeriod(vo.getSubCommentatorVO().getPeriod().concat(vo.getSubCommentatorVO().getPeriodEra()));
			if(subCommentatorBean.getPeriod() != null && subCommentatorBean.getPeriod().length() <= 2) {
				subCommentatorBean.setPeriod(null);
			}
			bean.setSubCommentatorFkObj(subCommentatorBean);
		}
		
		if(vo.getTranslatorVO() != null) {
			AuthorBean translatorBean = new AuthorBean();
			translatorBean.setId(vo.getTranslatorVO().getId());
			translatorBean.setLifeHistory(vo.getTranslatorVO().getLifeHistory());
			translatorBean.setName(vo.getTranslatorVO().getName());
			translatorBean.setDiacriticName(vo.getTranslatorVO().getDiacriticName());
			translatorBean.setRegionalName(vo.getTranslatorVO().getRegionalName());
			translatorBean.setType(IndvenApplicationConstants.PERSON_TYPE_TRANSLATOR);
			if(vo.getTranslatorVO().getPeriod() != null && vo.getTranslatorVO().getPeriodEra() != null)
				translatorBean.setPeriod(vo.getTranslatorVO().getPeriod().concat(vo.getTranslatorVO().getPeriodEra()));
			if(translatorBean.getPeriod() != null && translatorBean.getPeriod().length() <= 2) {
				translatorBean.setPeriod(null);
			}
			bean.setTranslatorFkObj(translatorBean);
		}
		
		MaterialBean materialBean = new MaterialBean();
		materialBean.setId(vo.getMaterialVO().getId());
		if(vo.getMaterialVO() != null && vo.getMaterialVO().getName() == null)
			vo.getMaterialVO().setName("Unselected");
		materialBean.setName(MaterialTypeEnum.valueOf(vo.getMaterialVO().getName()));
		bean.setMaterialFkObj(materialBean);
		
		Organisation organisation = new Organisation();
		organisation.setId(vo.getOrganisationVO().getId());
		organisation.setName(vo.getOrganisationVO().getName());
		organisation.setAddress(vo.getOrganisationVO().getAddress());
		organisation.setEmail(vo.getOrganisationVO().getEmail());
		organisation.setPhoneNumber(vo.getOrganisationVO().getPhoneNumber());
		organisation.setWebsite(vo.getOrganisationVO().getWebsite());
		organisation.setType(vo.getOrganisationVO().getType());
		organisation.setAcronym(vo.getOrganisationVO().getAcronym());
		
		bean.setOrganisationFkObj(organisation);
		
		if(vo.getNmmDetailsVO() != null) {
			bean.setNmmDetailsFkObj((NMMDetailsBean) CustomBeanUtil.voToEntity(vo.getNmmDetailsVO(), new NMMDetailsBean()));
			if(bean.getNmmDetailsFkObj().getId() != null && bean.getNmmDetailsFkObj().getId() <= 0) {
				bean.getNmmDetailsFkObj().setId(null);
			}
		}
		
		if(vo.getFilePathContainer() != null && vo.getFilePathContainer().length() > 0) {
			/*
			 * If file path is not null and length is greater than zero
			 * Create new DigitalFrameVOs with these file paths
			 */
			JSONArray jsonArray = new JSONArray(vo.getFilePathContainer());
			if(vo.getDigitalManuscriptFrameVOs() == null || vo.getDigitalManuscriptFrameVOs().size() <= 0) {
				List<DigitalManuscriptFrameVO> frameVOs = new ArrayList<>();
				vo.setDigitalManuscriptFrameVOs(frameVOs);
			}
			
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = new JSONObject(jsonArray.get(i).toString());
				for(@SuppressWarnings("unchecked")
				Iterator<String> keys = jsonObj.keys(); keys.hasNext();) {
					DigitalManuscriptFrameVO frameVO = new DigitalManuscriptFrameVO();
					frameVO.setFilePath(jsonObj.getString(keys.next()));
					vo.getDigitalManuscriptFrameVOs().add(frameVO);
				}
			}
		}
		
		if(vo.getDigitalManuscriptFrameVOs() != null && vo.getDigitalManuscriptFrameVOs().size() > 0) {
			/*
			 * Convert the DigitalFrameVOs to DigitalFrame Beans
			 */
			List<DigitalManuscriptFrame> digitalManuscriptFrames = new ArrayList<>();
			
			for(DigitalManuscriptFrameVO frameVO : vo.getDigitalManuscriptFrameVOs()) {
				DigitalManuscriptFrame frame = new DigitalManuscriptFrame();
				frame.setId(frameVO.getId());
				frame.setFilePath(frameVO.getFilePath());
				frame.setDigitalManuscriptFkId(vo.getId());
				frame.setDigitalManuscriptFkObj(bean);
				
				digitalManuscriptFrames.add(frame);
			}
			bean.setDigitalManuscriptFrames(digitalManuscriptFrames);
		}
		
		
		return bean;
	}
	
	public static DigitalManuscriptBean addDiskPathImages(DigitalManuscriptBean bean, DigitalManuscriptVO vo) throws JSONException {
		
		if(vo.getFileDiskPathContainer() != null && vo.getFileDiskPathContainer().length() > 0) {
			/*
			 * If file path on disk is not null and its length is greater than zero
			 * Create new DigitalFrameVOs with these file paths
			 */
			JSONArray jsonArray = new JSONArray(vo.getFileDiskPathContainer());
			if(vo.getDigitalManuscriptFrameVOs() == null || vo.getDigitalManuscriptFrameVOs().size() <= 0) {
				List<DigitalManuscriptFrameVO> frameVOs = new ArrayList<>();
				vo.setDigitalManuscriptFrameVOs(frameVOs);
			}
			
			for(int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = new JSONObject(jsonArray.get(i).toString());
				if(!jsonObj.getString("id").equals("")){
				DigitalManuscriptFrameVO frameVO = new DigitalManuscriptFrameVO();
				frameVO.setId(Long.parseLong(jsonObj.getString("id")));
				if(jsonObj.getString("filePath") != null) {
					frameVO.setFilePath(jsonObj.getString("filePath"));
				}
				
				if(jsonObj.getString("text") != null && jsonObj.getString("text").length() > 0) {
					/* Document details | start here */
					DigitalDocumentVO documentVO = new DigitalDocumentVO();
					if(jsonObj.getString("documentId").length() > 0) {
						documentVO.setId(Long.parseLong(jsonObj.getString("documentId")));
					}

					DigitalDocumentDetailsVO detailsVO = new DigitalDocumentDetailsVO();
					detailsVO.setText(jsonObj.getString("text"));
					
					documentVO.setDetailsVO(detailsVO);
					
					frameVO.setDocumentVO(documentVO);
					/* Document details | end here */
				} else {
					frameVO.setDocumentVO(null);
				}
				vo.getDigitalManuscriptFrameVOs().add(frameVO);
			}
				
			}
		}
		
		if(vo.getDigitalManuscriptFrameVOs() != null && vo.getDigitalManuscriptFrameVOs().size() > 0) {
			/*
			 * Convert the DigitalFrameVOs to DigitalFrame Beans
			 */
			List<DigitalManuscriptFrame> digitalManuscriptFrames = new ArrayList<>();
			
			for(DigitalManuscriptFrameVO frameVO : vo.getDigitalManuscriptFrameVOs()) {
				DigitalManuscriptFrame frame = new DigitalManuscriptFrame();
				frame.setId(frameVO.getId());
				frame.setFilePath(frameVO.getFilePath());
				frame.setDigitalManuscriptFkId(vo.getId());
				frame.setIsLast(frameVO.getIsLast());
				frame.setDigitalManuscriptFkObj(bean);
				
				if(frameVO.getDocumentVO() != null) {
					DigitalDocumentBean digitalDocumentBean = DigitalDocumentAssembler.convertVOToEntity(frameVO.getDocumentVO());
					digitalDocumentBean.setDigitalManuscriptFrameFkId(frame.getId());
					frame.setDigitalDocumentBean(digitalDocumentBean);
				}
				
				digitalManuscriptFrames.add(frame);
			}
			bean.setDigitalManuscriptFrames(digitalManuscriptFrames);
		}
		
		return bean;
	}
	
	public static DigitalManuscriptBean copyObjToNewState(DigitalManuscriptBean transcribedBean , DigitalManuscriptBean srcObj) {
		
		try {
			BeanUtils.copyProperties(transcribedBean, srcObj);
			
			transcribedBean.setAuthorFKId(srcObj.getAuthorFKId());
			transcribedBean.setLanguageFkId(srcObj.getLanguageFkId());
			transcribedBean.setScribeFkId(srcObj.getScribeFkId());
			transcribedBean.setScriptFkId(srcObj.getScriptFkId());
			transcribedBean.setTranslatorFkId(srcObj.getTranslatorFkId());
			transcribedBean.setSubCommentatorFkId(srcObj.getSubCommentatorFkId());
			transcribedBean.setCommentatorFkId(srcObj.getCommentatorFkId());
			transcribedBean.setCategoryFkId(srcObj.getCategoryFkId());
			transcribedBean.setDigitizerId(srcObj.getDigitizerId());
			transcribedBean.setBundleMasterFkId(srcObj.getBundleMasterFkId());
			transcribedBean.setOrganisationFkId(srcObj.getOrganisationFkId());
			transcribedBean.setPublicationFKId(srcObj.getPublicationFKId());
			
			/*DigitalManuscriptFrame frame = null;
			List<DigitalManuscriptFrame> transcribedFrame = new ArrayList<>();
			for(int i=0;i<transcribedBean.getDigitalManuscriptFrames().size() ; i++) {
				frame = new DigitalManuscriptFrame();
				
				BeanUtils.copyProperties(frame, transcribedBean.getDigitalManuscriptFrames().get(i));
				frame.setId(null);
				frame.setFrameCommentsList(null);
				frame.setDigitalManuscriptFkObj(transcribedBean);
				frame.setDigitalManuscriptFkId(null);
				
				transcribedFrame.add(frame);
			}*/
			transcribedBean.setManuscriptType(ManuscriptTypeEnum.fromValue((short) 1));
			transcribedBean.setRecordStatus(DocumentStatusEnum.fromValue((short)-1));
			transcribedBean.setId(null);
			transcribedBean.setDigitalManuscriptFrames(null);
			transcribedBean.setParentFKId(srcObj.getId());
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transcribedBean;
	}
}
