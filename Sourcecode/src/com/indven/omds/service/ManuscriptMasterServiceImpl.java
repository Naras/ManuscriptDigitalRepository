/**
 * 
 */
package com.indven.omds.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indven.framework.exception.IndvenException;
import com.indven.framework.service.BaseEntityCRUDService;
import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.omds.assembler.DigitalDocumentAssembler;
import com.indven.omds.assembler.DigitalManuscriptAssembler;
import com.indven.omds.assembler.PublicationAssembler;
import com.indven.omds.dao.ManuscriptMasterDAOImpl;
import com.indven.omds.entity.AuthorBean;
import com.indven.omds.entity.BundleMasterBean;
import com.indven.omds.entity.CategoryBean;
import com.indven.omds.entity.DigitalDocumentBean;
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
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.FilesUtil;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.omds.vo.AuthorVO;
import com.indven.omds.vo.BundleMasterVO;
import com.indven.omds.vo.CategoryVO;
import com.indven.omds.vo.DigitalDocumentVO;
import com.indven.omds.vo.DigitalManuscriptFrameVO;
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
import com.indven.portal.administration.vo.UserInfoVO;
import com.indven.portal.hrd.entity.EmployeeMasterBean;
import com.indven.portal.hrd.vo.EmployeeMasterVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Deba Prasad
 *
 */
public class ManuscriptMasterServiceImpl implements BaseEntityCRUDService<IndvenResultVO> {

	public ManuscriptMasterDAOImpl dao = new ManuscriptMasterDAOImpl();
	
	@Override
	public IndvenResultVO save(IndvenResultVO valueObject)
			throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndvenResultVO findById(Long id) throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IndvenResultVO> findAll() throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndvenResultVO update(IndvenResultVO valueObject)
			throws IndvenException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purge(IndvenResultVO valueObject) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> searchManuscriptRecord(DigitalManuscriptVO vo, boolean isFindAll, int setFirst , int recordPerPage) throws OMDPCoreException, JSONException {
		List<DigitalManuscriptVO> digitalManuscriptVOs = new ArrayList<>();
		List<DigitalManuscriptBean> beans = new ArrayList<>();
		List<DigitalManuscriptBean> beansInWfl = new ArrayList<>();
		
		List<DigitalManuscriptBean> beansInWflUnderUser = new ArrayList<>();
		List<EmployeeMasterBean> listOfRecordOwners = new ArrayList<>();
		List<Long> listOfRecordIds = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		Long noOfRecords = 0L;
		
		Long minFolio=null ;
		if(vo !=null && vo.getMinimumFolios() != null && vo.getMinimumFolios().length() > 0) {
			try {
				minFolio = Long.parseLong(vo.getMinimumFolios());
			} catch(Exception e) {
				minFolio = 10000L;
			}
		}
		
		Long maxFolio=null ;
		if(vo !=null && vo.getMaximunFolios() != null && vo.getMaximunFolios().length() > 0) {
			try {
				maxFolio = Long.parseLong(vo.getMaximunFolios());
			} catch(Exception e) {
				maxFolio = 10000L;
			}
		}
		if(isFindAll){
			map = dao.searchManuscriptRecord(new DigitalManuscriptBean(), new AuthorBean() , new Organisation() , setFirst , recordPerPage , null , minFolio , maxFolio);
			
		}else{
			DigitalManuscriptBean digitalManuscriptBean = DigitalManuscriptAssembler.convertVoToEntity(vo);
//			DocumentStatusEnum documentstatus = DocumentStatusEnum.valueOf(vo.getDocumentPublicationStatus());
//			short documentstatusShort = (short)documentstatus.ordinal();
			map = dao.searchManuscriptRecord(digitalManuscriptBean, digitalManuscriptBean.getAuthorFkObj() , digitalManuscriptBean.getOrganisationFkObj(), setFirst , recordPerPage , vo.getSpecificCategoryId(), minFolio , maxFolio);
		}
		
		beans = (List<DigitalManuscriptBean>) map.get("manuscriptListFree");
		beansInWfl = (List<DigitalManuscriptBean>) map.get("manuscriptListUndWfl");
		
		beansInWflUnderUser = (List<DigitalManuscriptBean>) map.get("manuscriptListUndWflUser");
		listOfRecordOwners = (List<EmployeeMasterBean>) map.get("ownerOfWflProcess");
		listOfRecordIds = (List<Long>) map.get("idOfWflProcess");
		
		noOfRecords = (Long) map.get("totalCount");
		
		if(beansInWfl != null && beansInWfl.size() > 0) {
			for(Iterator<DigitalManuscriptBean> i = beansInWfl.iterator(); i.hasNext();) {
				DigitalManuscriptVO dmVo = DigitalManuscriptAssembler.convertEntityToVo(i.next());
				if(dmVo.getManuscriptType().equals("Original")) {
					dmVo.setIsUnderWfl((short)1);
				}
				
				digitalManuscriptVOs.add(dmVo);
			}
		}
		
		
		if(beansInWflUnderUser != null && beansInWflUnderUser.size() > 0) {
			for(int i=0 ; i<beansInWflUnderUser.size() ; i++) {
				DigitalManuscriptVO dmVo = DigitalManuscriptAssembler.convertEntityToVo(beansInWflUnderUser.get(i));
				
				if(dmVo.getManuscriptType().equals("Original")) {
					dmVo.setIsUnderWfl((short)1);
				}
				
				dmVo.setWflProcessOwner(listOfRecordOwners.get(i).getFirstName()+" "+listOfRecordOwners.get(i).getLastName());
				dmVo.setActivewflProcessId(listOfRecordIds.get(i));
				digitalManuscriptVOs.add(dmVo);
			}
		}
		
		
		if(beans != null && beans.size() > 0) {
			/*for(Iterator<DigitalManuscriptBean> i = beans.iterator(); i.hasNext();) {
				digitalManuscriptVOs.add(DigitalManuscriptAssembler.convertEntityToVo(i.next()));
			}*/
			
			for(int i=0 ; i<beans.size() ; i++) {
				DigitalManuscriptVO dmVo = DigitalManuscriptAssembler.convertEntityToVo(beans.get(i));
				dmVo.setWflProcessOwner("N/A");
				digitalManuscriptVOs.add(dmVo);
			}
		}
		
		for(DigitalManuscriptVO  digManVo : digitalManuscriptVOs) {
			if(digManVo.getName() == null || digManVo.getName().length() <= 0) {
				if(digManVo.getRegionalName() != null && digManVo.getRegionalName().length() > 0) {
					digManVo.setName(digManVo.getRegionalName());
				} else {
					digManVo.setName(digManVo.getDiacriticName());
				}
			}
		}
		
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("manuscriptList", digitalManuscriptVOs);
		returnmap.put("totalCount", noOfRecords);
		return returnmap;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> searchManuscriptForMerging(DigitalManuscriptVO vo, boolean isFindAll) throws OMDPCoreException, JSONException {
		List<DigitalManuscriptVO> digitalManuscriptVOs = new ArrayList<>();
	//	List<DigitalManuscriptBean> beans = new ArrayList<>();
	//	List<DigitalManuscriptBean> beansInWfl = new ArrayList<>();
		
		List<DigitalManuscriptBean> beansInWflUnderUser = new ArrayList<>();
	//	List<EmployeeMasterBean> listOfRecordOwners = new ArrayList<>();
		List<Long> listOfRecordIds = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		Long noOfRecords = 0L;
		
		Long minFolio=null ;
		if(vo !=null && vo.getMinimumFolios() != null && vo.getMinimumFolios().length() > 0) {
			try {
				minFolio = Long.parseLong(vo.getMinimumFolios());
			} catch(Exception e) {
				minFolio = 0L;
			}
		}
		
		Long maxFolio=null ;
		if(vo !=null && vo.getMaximunFolios() != null && vo.getMaximunFolios().length() > 0) {
			try {
				maxFolio = Long.parseLong(vo.getMaximunFolios());
			} catch(Exception e) {
				maxFolio = 0L;
			}
		}
		if(isFindAll){
			map = dao.searchManuscriptToMerge(new DigitalManuscriptBean(), new AuthorBean() , new Organisation() , null);
			
		}else{
			DigitalManuscriptBean digitalManuscriptBean = DigitalManuscriptAssembler.convertVoToEntity(vo);
//			DocumentStatusEnum documentstatus = DocumentStatusEnum.valueOf(vo.getDocumentPublicationStatus());
//			short documentstatusShort = (short)documentstatus.ordinal();
			map = dao.searchManuscriptToMerge(digitalManuscriptBean, digitalManuscriptBean.getAuthorFkObj() , digitalManuscriptBean.getOrganisationFkObj(),vo.getSpecificCategoryId());
		}
		
	//	beans = (List<DigitalManuscriptBean>) map.get("manuscriptListFree");
	//	beansInWfl = (List<DigitalManuscriptBean>) map.get("manuscriptListUndWfl");
		
		beansInWflUnderUser = (List<DigitalManuscriptBean>) map.get("manuscriptListUndWflUser");
	//	listOfRecordOwners = (List<EmployeeMasterBean>) map.get("ownerOfWflProcess");
		listOfRecordIds = (List<Long>) map.get("idOfWflProcess");
		
		noOfRecords = (Long) map.get("totalCount");
		
		/*if(beansInWfl != null && beansInWfl.size() > 0) {
			for(Iterator<DigitalManuscriptBean> i = beansInWfl.iterator(); i.hasNext();) {
				DigitalManuscriptVO dmVo = DigitalManuscriptAssembler.convertEntityToVo(i.next());
				if(dmVo.getManuscriptType().equals("Original")) {
					dmVo.setIsUnderWfl((short)1);
				}
				
				digitalManuscriptVOs.add(dmVo);
			}
		}*/
		
		
		/*if(beansInWflUnderUser != null && beansInWflUnderUser.size() > 0) {
			for(int i=0 ; i<beansInWflUnderUser.size() ; i++) {
				DigitalManuscriptVO dmVo = DigitalManuscriptAssembler.convertEntityToVo(beansInWflUnderUser.get(i));
				
				if(dmVo.getManuscriptType().equals("Original")) {
					dmVo.setIsUnderWfl((short)1);
				}
				
				dmVo.setWflProcessOwner(listOfRecordOwners.get(i).getFirstName()+" "+listOfRecordOwners.get(i).getLastName());
				dmVo.setActivewflProcessId(listOfRecordIds.get(i));
				digitalManuscriptVOs.add(dmVo);
			}
		}*/
		
		
		if(beansInWflUnderUser != null && beansInWflUnderUser.size() > 0) {
			/*for(Iterator<DigitalManuscriptBean> i = beansInWflUnderUser.iterator(); i.hasNext();) {
				digitalManuscriptVOs.add(DigitalManuscriptAssembler.convertEntityToVo(i.next()));
			}*/
			
			for(int i=0 ; i<beansInWflUnderUser.size() ; i++) {
				DigitalManuscriptVO dmVo = DigitalManuscriptAssembler.convertEntityToVo(beansInWflUnderUser.get(i));
				dmVo.setWflProcessOwner("N/A");
				digitalManuscriptVOs.add(dmVo);
			}
		}
		
		/*for(DigitalManuscriptVO  digManVo : digitalManuscriptVOs) {
			if(digManVo.getName() == null || digManVo.getName().length() <= 0) {
				if(digManVo.getRegionalName() != null && digManVo.getRegionalName().length() > 0) {
					digManVo.setName(digManVo.getRegionalName());
				} else {
					digManVo.setName(digManVo.getDiacriticName());
				}
			}
		}*/
		
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("manuscriptList", digitalManuscriptVOs);
		returnmap.put("totalCount", noOfRecords);
		return returnmap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<LanguageVO> findAllLanguages() throws OMDPCoreException {
		List<LanguageVO> languageList = new ArrayList<>();
		List<LanguageBean> languageBeanList = new ArrayList<>();
		
		languageBeanList = dao.findAllLanguages();
		
		LanguageVO vo = null;
		for(LanguageBean bean : languageBeanList) {
			vo = new LanguageVO();
			vo = (LanguageVO) CustomBeanUtil.entityToVO(bean, vo);
			languageList.add(vo);
		}
		return languageList;
	}
	
	public List<ScriptVO> findAllScripts() throws OMDPCoreException {
		List<ScriptVO> vos = new ArrayList<>();
		List<ScriptBean> beans = new ArrayList<>();
		
		beans = dao.findAllScripts();
		ScriptVO vo = null;
		for(Iterator<ScriptBean> i = beans.iterator(); i.hasNext();) {
			vo = (ScriptVO) CustomBeanUtil.entityToVO(i.next(), new ScriptVO());
			vos.add(vo);
		}
		
		return vos;
	}
	
	public List<CategoryVO> findAllCategories() throws OMDPCoreException {
		List<CategoryVO> vos = new ArrayList<>();
		List<CategoryBean> beans = new ArrayList<>();
		
		beans = dao.findAllCategories();
		CategoryVO vo = null;
//		for(Iterator<CategoryBean> i = beans.iterator(); i.hasNext();) {
//			vo = (CategoryVO) CustomBeanUtil.entityToVO(i.next(), new CategoryVO());
//			vo.setName(setCatName(i.next()));
//			vos.add(vo);
//		}
		for(CategoryBean bean : beans) {
			vo = (CategoryVO) CustomBeanUtil.entityToVO(bean, new CategoryVO());
			vo.setName(setCatName(bean));
			vos.add(vo);
		}
		
		return vos;
	}
	
	public String getRecursiveName(CategoryBean bean) {
		if(bean == null) {
			return "";
		} else {
			return ((bean.getName() + "->")+getRecursiveName(bean.getParentFkObj()));
		}
	}
	public String setCatName(CategoryBean bean) {
		String name = getRecursiveName(bean);
		String[] splits = name.split("->");

		StringBuffer str = new StringBuffer();
		
		for(int i=splits.length ; i >0 ; i--) {
			if(i!=splits.length) {
				str.append("->");
			}
			str.append(splits[i-1]);
		}
		
//		for(int i=0 ; i <= splits.length ; i++) {
//			if(i!=splits.length) {
//				str.append("<-");
//			}
//			str.append(splits[i+1]);
//		}
		return str.toString();
	}
	
	public List<SpecificCategoryVO> findAllSpecificCategories() throws OMDPCoreException {
		List<SpecificCategoryVO> vos = new ArrayList<>();
		List<SpecificCategoryBean> beans = new ArrayList<>();
		
		beans = dao.findAllSpecificCategories();
		SpecificCategoryVO vo = null;
		for(Iterator<SpecificCategoryBean> i = beans.iterator(); i.hasNext();) {
			vo = (SpecificCategoryVO) CustomBeanUtil.entityToVO(i.next(), new SpecificCategoryVO());
			vos.add(vo);
		}
		
		return vos;
	}
	
	public List<MaterialVO> findAllMaterials() throws OMDPCoreException {
		List<MaterialVO> materialList = new ArrayList<>();
		List<MaterialBean> materialBeanList = new ArrayList<>();
		
		materialBeanList = dao.findAllMaterial();
		
		MaterialVO vo = null;
		for(MaterialBean bean : materialBeanList) {
			vo = new MaterialVO();
			vo = (MaterialVO) CustomBeanUtil.entityToVO(bean, vo);
			materialList.add(vo);
		}
		return materialList;
	}
	
	public List<OrganisationVO> findAllOrganisations()  throws OMDPCoreException {
		List<OrganisationVO> organisationList = new ArrayList<>();
		List<Organisation> organisationBeanList = new ArrayList<>();
		
		organisationBeanList = dao.findAllOrganisations();
		
		OrganisationVO vo = null;
		for(Organisation bean : organisationBeanList) {
			vo = new OrganisationVO();
			vo = (OrganisationVO) CustomBeanUtil.entityToVO(bean, vo);
			organisationList.add(vo);
		}
		return organisationList;
	}
	
	public List<BundleMasterVO> findAllBundleNumber()  throws OMDPCoreException {
		List<BundleMasterVO> bundleList = new ArrayList<>();
		List<BundleMasterBean> bundleBeanList = new ArrayList<>();
		
		bundleBeanList = dao.findAllBundleNumber();
		
		BundleMasterVO vo = null;
		for(BundleMasterBean bean : bundleBeanList) {
			vo = new BundleMasterVO();
			vo = (BundleMasterVO) CustomBeanUtil.entityToVO(bean, vo);
			bundleList.add(vo);
		}
		return bundleList;
	}
	
	public List<AuthorVO> findAllAuthor()  throws OMDPCoreException {
		List<AuthorVO> organisationList = new ArrayList<>();
		List<AuthorBean> organisationBeanList = new ArrayList<>();
		
		organisationBeanList = dao.findAllAuthor();
		
		AuthorVO vo = null;
		for(AuthorBean bean : organisationBeanList) {
			vo = new AuthorVO();
			vo = (AuthorVO) CustomBeanUtil.entityToVO(bean, vo);
			String period = vo.getPeriod();
			if(period != null && period.length() > 2) {
				vo.setPeriod(period.substring(0, period.length() - 2));
				vo.setPeriodEra(period.substring(period.length() - 2));
			}
			organisationList.add(vo);
		}
		return organisationList;
	}
	
	public List<AuthorVO> findAllAuthorForTerm(String term, Short authorType) throws OMDPCoreException {
		List<AuthorVO> authorVOs = new ArrayList<>();
		List<AuthorBean> authorBeans = new ArrayList<>();
		
		authorBeans = dao.findAllAuthorForTerm(term, authorType);
		
		AuthorVO vo = null;
		for(AuthorBean bean : authorBeans) {
			vo = new AuthorVO();
			vo = (AuthorVO) CustomBeanUtil.entityToVO(bean, vo);
			String period = vo.getPeriod();
			if(period != null && period.length() > 2) {
				vo.setPeriod(period.substring(0, period.length() - 2));
				vo.setPeriodEra(period.substring(period.length() - 2));
			}
			authorVOs.add(vo);
		}
		return authorVOs;
	}
	
	public List<DigitalManuscriptVO> findAllManuscriptForTerm(String term) throws OMDPCoreException {
		List<DigitalManuscriptVO> manuscriptVOs = new ArrayList<>();
		List<DigitalManuscriptBean> manuscriptBeans = new ArrayList<>();
		
		manuscriptBeans = dao.findAllManuscriptForTerm(term);
		
		DigitalManuscriptVO vo = null;
		for(DigitalManuscriptBean bean : manuscriptBeans) {
			vo = new DigitalManuscriptVO();
			vo = (DigitalManuscriptVO) CustomBeanUtil.entityToVO(bean, vo);
			manuscriptVOs.add(vo);
		}
		return manuscriptVOs;
	}
	
	public List<CategoryVO> findAllCategoryTerm(String term) throws OMDPCoreException {
		List<CategoryVO> categoryVOs = new ArrayList<>();
		List<CategoryBean> categoryBeans = new ArrayList<>();
		
		categoryBeans = dao.findAllCategoryForTerm(term);
		
		CategoryVO vo = null;
		for(CategoryBean bean : categoryBeans) {
			vo = new CategoryVO();
			vo = (CategoryVO) CustomBeanUtil.entityToVO(bean, vo);
			vo.setName(setCatName(bean));
			categoryVOs.add(vo);
		}
		return categoryVOs;
	}
	
	public List<PublisherVO> findAllPublisherForTerm(String term) throws OMDPCoreException {
		List<PublisherVO> publisherVOs = new ArrayList<>();
		List<PublisherBean> publisherBeans = new ArrayList<>();
		
		publisherBeans = dao.findAllPublisherForTerm(term);
		
		PublisherVO vo = null;
		for(PublisherBean bean : publisherBeans) {
			vo = new PublisherVO();
			vo = (PublisherVO) CustomBeanUtil.entityToVO(bean, vo);
			publisherVOs.add(vo);
		}
		return publisherVOs;
	}
	
	public List<TagMasterVO> findAllTagForTerm(String term) throws OMDPCoreException {
		List<TagMasterVO> tagVOs = new ArrayList<>();
		List<TagMasterBean> tagBeans = new ArrayList<>();
		
		tagBeans = dao.findAllTagForTerm(term);
		
		for(TagMasterBean bean : tagBeans) {
			tagVOs.add((TagMasterVO) CustomBeanUtil.entityToVO(bean, new TagMasterVO()));
		}
		return tagVOs;
	}
	
	public List<OrganisationVO> findAllOrganisationForTerm(String term) throws OMDPCoreException {
		List<OrganisationVO> organisationVOs = new ArrayList<>();
		List<Organisation> organisations = new ArrayList<>();
		
		organisations = dao.findAllOrganisationForTerm(term);
		
		for(Organisation bean : organisations) {
			organisationVOs.add((OrganisationVO) CustomBeanUtil.entityToVO(bean, new OrganisationVO()));
		}
		return organisationVOs;
	}
	
	//Method to check the manuscript status 
	public short getManuscriptStatus(Long id) throws OMDPCoreException{
		return dao.getManuscriptStatus(id);
	}
	
	@SuppressWarnings("unchecked")
	public DigitalManuscriptVO findManuscriptById(Long id, String folderPath) throws OMDPCoreException, JSONException, IOException {
		DigitalManuscriptVO vo = new DigitalManuscriptVO();
		List<TagMasterVO> tagVOs = new ArrayList<>();
		List<Long> specificcategorylist= new ArrayList<>();
		Map<String, Object> returnMap = dao.findById(id);
		
		DigitalManuscriptBean bean = (DigitalManuscriptBean) returnMap.get("manuscriptBean");
		List<DigitalManuscriptFrame> frames = new ArrayList<>();
		for(DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
			frame.setDigitalDocumentBean(dao.findDocumentByFrameId(frame.getId()));
			frames.add(frame);
		}
		DigitalManuscriptFrame sortFrame = new DigitalManuscriptFrame();
		Collections.sort(frames , sortFrame);
		bean.setDigitalManuscriptFrames(frames);
		List<String> pathList = new ArrayList<>();
		for(int i=0 ; i<bean.getDigitalManuscriptFrames().size() ; i++) {
			pathList.add(bean.getDigitalManuscriptFrames().get(i).getFilePath());
		}
		
		bean = convertFilePathsForDisplay(bean, folderPath);
		
		vo = DigitalManuscriptAssembler.convertEntityToVo(bean);
		vo.setDigitizedBy((String) returnMap.get("digitiserName"));
		
		if(vo.getDigitalManuscriptFrameVOs() != null && vo.getDigitalManuscriptFrameVOs().size() > 0) {
			/*
			 * If there are Digital Frames, then convert them to JSON objects 
			 * and put them in the fileDiskPathContainer field
			 */
			
		JSONArray jsonArray = new JSONArray();
		for(int i=0 ; i < bean.getDigitalManuscriptFrames().size() ; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", bean.getDigitalManuscriptFrames().get(i).getId());
			jsonObject.put("filePath", bean.getDigitalManuscriptFrames().get(i).getFilePath());
			jsonObject.put("isLast", bean.getDigitalManuscriptFrames().get(i).getIsLast());
			jsonObject.put("filePathReal", pathList.get(i));
			if(bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean() != null) {
				jsonObject.put("documentId", bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getId());
				jsonObject.put("text", bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getText());
				
				//checking for the attachment file path
				if(bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath()!= null){
					/*String audioFilePath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
							.getObject("audio.system.path").toString();
					String dbFilePath =  bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath();
					File audioFile = new File(audioFilePath+dbFilePath);
					String tempAudioFileName = FilesUtil.saveFile(audioFile,audioFile.getName(), folderPath+"/"+audioFile.getName());*/
					String dbFilePath =  bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath();
				jsonObject.put("attachmentPath",dbFilePath);
				}else{
					jsonObject.put("attachmentPath","");
				}
			} else {
				jsonObject.put("documentId", "");
				jsonObject.put("text", "");
				jsonObject.put("attachmentPath","");
			}
			
			JSONArray commentArray = new JSONArray();
			JSONObject commentObj = null;
			
			for(int j=0 ; j<bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().size() ; j++) {
				commentObj = new JSONObject();
				commentObj.put("commentText",bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().get(j).getComment());
				commentObj.put("commentBy",bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().get(j).getCommentedBy());
				commentObj.put("commentTime",bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().get(j).getCommentedOn());
				commentArray.put(commentObj);
			}
			
			jsonObject.put("comments", commentArray);
			jsonArray.put(jsonObject);
		}
		vo.setFileDiskPathContainer(jsonArray.toString());
		}
		/*for(int i=0 ; i < vo.getDigitalManuscriptFrameVOs().size() ; i++) {
			vo.getDigitalManuscriptFrameVOs().get(i).setFullPath(pathList.get(i));
		}
		*/
		
		List<ManuscriptTagMapper> mapperList = (List<ManuscriptTagMapper>) returnMap.get("tagList");
		for(ManuscriptTagMapper taglist : mapperList){
			tagVOs.add((TagMasterVO) CustomBeanUtil.entityToVO(taglist.getTagsFkObj(), new TagMasterVO()));
		}
		for(ManuscriptSpecificcategoryMapper specificcategory : (List<ManuscriptSpecificcategoryMapper>)returnMap.get("specificCategoryList")){
			specificcategorylist.add(specificcategory.getSpecificcategoryFkId());
		}
		vo.setSpecificCategoryId(specificcategorylist);
		vo.setTagList(tagVOs);
		return vo;
	}
	
	public PublicationVO findPublicationById(Long id) throws OMDPCoreException {
		PublicationVO vo = new PublicationVO();
		vo = PublicationAssembler.convertEntityToVO(dao.findPublicationById(id));
		return vo;
	}
	
	public Long findNoOfRecordsByDocumentType(Long type) throws OMDPCoreException {
		return dao.findNoOfRecordsByDocumentType(type);
	}
	public List<Long> findNoOfRecordsForDifferentStatus(Long type) throws OMDPCoreException {
		return dao.findNoOfRecordsForDifferentStatus(type);
	}
	
	public DigitalManuscriptVO saveDigitalManuscript(DigitalManuscriptVO vo, PublicationVO publicationVO, String serverPath) throws OMDPCoreException, JSONException, IOException {
		DigitalManuscriptBean bean = DigitalManuscriptAssembler.convertVoToEntity(vo);
		if(bean.getRecordStatus() == null) {
			bean.setRecordStatus(DocumentStatusEnum.fromValue((short)-1));
		}
		PublicationBean publicationBean = new PublicationBean();
		publicationBean = PublicationAssembler.convertVOToEntity(publicationVO);
		List<TagMasterBean> tagList= new ArrayList<TagMasterBean>();
		if(vo.getTagList() != null){
		for(TagMasterVO tagvo: vo.getTagList() ){
			if(tagvo != null){
			TagMasterBean tagBean= new TagMasterBean();
			tagList.add((TagMasterBean) CustomBeanUtil.voToEntity(tagvo,tagBean));
			}
		}
		}
		bean.setIsDeleted((short)0);
		publicationBean.setIsDeleted((short)0);
		if(bean.getParentFKId() != null && bean.getParentFKId() <= 0 ) {
			bean.setParentFKId(null);
		}
//		String folderPath = ""; //Path where the Directory need to be created for saving the images.
//		Calendar now = Calendar.getInstance();
//		String year = String.valueOf(now.get(Calendar.YEAR));
//		String month =  String.valueOf(now.get(Calendar.MONTH)+1);
//		String day =  String.valueOf(now.get(Calendar.DAY_OF_MONTH));
//		
//		folderPath = "/"+year+"/"+month+"/"+day+"/"+vo.getName() ;
		
		/* Add paths of those images that are already on the file system */
		bean = DigitalManuscriptAssembler.addDiskPathImages(bean, vo);
		
		/* Move images that are on the server to the file system */
	//	bean = convertFilePaths(bean, folderPath);
		
		bean = dao.saveDigitalManuscript(bean , publicationBean,tagList,vo.getSpecificCategoryId()); //Save the beans
		
		/* Convert resultant image paths back to relative paths
		 * So that they can be displayed on the web */
		bean = convertFilePathsForDisplay(bean, serverPath); 
		vo = DigitalManuscriptAssembler.convertEntityToVo(bean);
		return vo;
	}
	
	/**
	 * Save or update the digital document
	 * @param vo
	 * @return
	 * @throws OMDPCoreException
	 * @throws JSONException 
	 */
	public DigitalDocumentVO saveOrUpdateDigitalDocument(DigitalDocumentVO vo , boolean isWFLForwarded) throws OMDPCoreException , JSONException{
		DigitalDocumentBean bean = DigitalDocumentAssembler.convertVOToEntity(vo);
		CustomBeanUtil.setBaseValues(bean.getDocumentDetailsBean(), true);
		bean = dao.saveOrUpdateDigitalDocument(bean , isWFLForwarded);
		vo = DigitalDocumentAssembler.convertEntityToVO(bean);
		
		return vo;
	}
	
	/**
	 * Save or update the digital document
	 * Containing within frames of manuscript
	 * @param vo
	 * @return
	 * @throws JSONException
	 * @throws OMDPCoreException
	 */
	public DigitalManuscriptVO saveOrUpdateDigitalDocument(DigitalManuscriptVO vo , boolean isWFLForwarded) throws JSONException, OMDPCoreException {
		DigitalManuscriptVO vo2 = vo;
		DigitalManuscriptBean bean = DigitalManuscriptAssembler.convertVoToEntity(vo);
		bean = DigitalManuscriptAssembler.addDiskPathImages(bean, vo);
		List<DigitalManuscriptFrame> digitalManuscriptFrames = new ArrayList<>();
		if(bean.getDigitalManuscriptFrames() != null && bean.getDigitalManuscriptFrames().size() > 0) {
			for(DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
				DigitalDocumentBean documentBean = frame.getDigitalDocumentBean();
				
				if(documentBean != null) {
					CustomBeanUtil.setBaseValues(documentBean.getDocumentDetailsBean(), true);
					documentBean = dao.saveOrUpdateDigitalDocument(documentBean , isWFLForwarded);
				}
				
				frame.setDigitalDocumentBean(documentBean);
				digitalManuscriptFrames.add(frame);
			}
		}
		bean.setDigitalManuscriptFrames(digitalManuscriptFrames);
		vo = DigitalManuscriptAssembler.convertEntityToVo(bean);
		vo.setTagList(vo2.getTagList());
		vo.setNmmDetailsVO(vo2.getNmmDetailsVO());
		return vo;
	}
	
	public String framePartialSave(String text,Long frameId,Long ManuscriptId,int type,String attachmentFilePath) throws OMDPCoreException{
		return dao.framePartialSave (text,frameId,ManuscriptId,type,attachmentFilePath);
	}
	
	public String framePartialSaveTrans(String text,Long frameId,Long ManuscriptId,int type,String language) throws OMDPCoreException{
		return dao.framePartialSaveTrans (text,frameId,ManuscriptId,type,language);
	}
	
	
	public boolean deleteManuscriptRecord(Long id) throws OMDPCoreException {
		return (dao.deleteManuscriptRecord(id));
	}
	/**
	 * This method will returns total number of records for a particular Acronym 
	 * @author Rakesh kumar sahoo
	 * @param String Acronym, String Organization name
	 * @throws Exception
	 */
	public String findNumberOfRecords(String acronym,String name) throws OMDPCoreException{
		
		return dao.findNumberOfRecords(acronym,name);
	}
	
	/**
	 * Searches for the digital document 
	 * Based the manuscript id
	 * @param id
	 * @return
	 * @throws OMDPCoreException
	 * @throws JSONException 
	 */
	public DigitalDocumentVO findDocumentByManuscriptId(Long id) throws OMDPCoreException, JSONException {
		return DigitalDocumentAssembler.convertEntityToVO(dao.findDocumentByManuscriptId(id));
	}
	
	/**
	 * Searches for the digital document 
	 * Based the manuscript id
	 * @param id
	 * @return
	 * @throws OMDPCoreException
	 * @throws JSONException 
	 */
	public DigitalDocumentVO findDocumentByManuscriptIdTrans(Long id) throws OMDPCoreException, JSONException {
		return DigitalDocumentAssembler.convertEntityToVO(dao.findDocumentByManuscriptIdTranslated(id));
	}
	
	public DigitalManuscriptBean convertFilePathsForDisplay(DigitalManuscriptBean bean, String folderPath) throws IOException, OMDPCoreException {
		
		if(bean.getDigitalManuscriptFrames() != null && bean.getDigitalManuscriptFrames().size() > 0) {
			/* Loading the images from file system to server */
			String resizeFolderPath = ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
					.getObject("imagesResize.system.path").toString();
	
		/*	List<DigitalManuscriptFrame> frames = new ArrayList<>();*/
			String newPath = null;
			for(DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
				if(frame.getFilePath() != null) {
					newPath = FilesUtil.copyFileToDirectory(resizeFolderPath+frame.getFilePath(), folderPath,true); //Copies file to server
					if(newPath == null || newPath.length() <= 0) {
						/* Image for frame is not found
						 * Hence, do not add frame to list */
						continue;
					} else {
						frame.setFilePath(newPath);
					}
				}
				/*frames.add(frame);*/
			}
			
			/*bean.setDigitalManuscriptFrames(frames);*/
		}
		
		return bean;
	}
	
	/**
	 * Copies files from file system to server
	 * Replaces absolute path for all images with relative path
	 * Required to display images on the server
	 * @param bean
	 * @param fldPath :- This is the path where the folder will be generated depending on the date,month and year of upload.
	 * @return
	 * @throws IOException convertFilePathsForDisplay
	 * @throws OMDPCoreException 
	 */
	public DigitalManuscriptBean convertFilePaths(DigitalManuscriptBean bean, String fldPath) throws IOException, OMDPCoreException {
		
		String folderPath = ResourceBundle
				.getBundle("ApplicationResources",
						IndvenApplicationConstants.LOCALE)
				.getObject("images.system.path").toString()
				+ fldPath;

		if (bean.getDigitalManuscriptFrames() != null
				&& bean.getDigitalManuscriptFrames().size() > 0) {

			/* Loading the images from file system to server */

			/* List<DigitalManuscriptFrame> frames = new ArrayList<>(); */
			String newPath = null;
			for (DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
				if (frame.getFilePath() != null) {
					if (frame.getId() == null) {
						new File(folderPath).mkdirs();
						newPath = FilesUtil.copyFileToDirectory(frame.getFilePath(), folderPath, false); // Copies file to server
						if (newPath == null || newPath.length() <= 0) {
							/*
							 * Image for frame is not found Hence, do not add
							 * frame to list
							 */
							continue;
						} else {
							frame.setFilePath(cropImage(folderPath + "/"
									+ newPath, fldPath));
						}
					} else {
						frame.setFilePath(new File(frame.getFilePath())
								.getName());
					}
				}
				/* frames.add(frame); */
			}

			/* bean.setDigitalManuscriptFrames(frames); */
		}

		return bean;
	}
	
	public String cropImage(String newPath , String resFolder){
		String cropedPath = null;
		try{
			String resizeFolderPath = ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
					.getObject("imagesResize.system.path").toString()+resFolder; 
			
			new File(resizeFolderPath).mkdirs();
			
		File cropImg= new File(newPath);
		String cropName=cropImg.getName();
		BufferedImage originalImage = ImageIO.read(cropImg);
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
 
		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ImageIO.write(resizeImageJpg, "jpg", new File(resizeFolderPath+"/"+cropName));
		cropedPath = resFolder+"/"+cropName; 
		
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		return cropedPath; 
	}
	private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(50, 50, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 50, 50, null);
		g.dispose();
		 
		return resizedImage;
	}
		
	public DocumentCommentVO saveCommentForFrame(DocumentCommentVO vo) throws OMDPCoreException {
		DocumentCommentBean bean = new DocumentCommentBean();
		CustomBeanUtil.voToEntity(vo, bean);
		bean = new ManuscriptMasterDAOImpl().saveCommentForFrame(bean);
		CustomBeanUtil.entityToVO(bean, vo);
		return vo;
	}
	public String deleteFrame(String[] frameId) throws OMDPCoreException{
		return dao.deleteFrame(frameId);
	}
	
	/**
	 * This is the method to fetch the list of digitizers for the auto complete field.
	 * @param term - The particular term to search as digitizer name.
	 * @return
	 * @throws OMDPCoreException
	 */
	public List<EmployeeMasterVO> findAllDigitisersForTerm(String term) throws OMDPCoreException {
		List<EmployeeMasterVO> disitiserVOS = new ArrayList<>();
		List<EmployeeMasterBean> disitiserBeans = new ArrayList<>();
		
		disitiserBeans = dao.findAllDigitisersForTerm(term);
		
		EmployeeMasterVO vo = null;
		for(EmployeeMasterBean bean : disitiserBeans) {
			vo = new EmployeeMasterVO();
			vo = (EmployeeMasterVO) CustomBeanUtil.entityToVO(bean, vo);
			disitiserVOS.add(vo);
		}
		return disitiserVOS;
	}
	
	public boolean createTranslateRecordAndSaveInfo(String language , String script , Long transcribedManId) throws OMDPCoreException {
		return dao.createTranslateRecordAndSaveInfo(language, script, transcribedManId);
	}
	
	
	public String getAvailableLanguages(Long transcribedManId) throws OMDPCoreException {
		StringBuffer availableLanguages = new StringBuffer("");
		List<LanguageBean> languageList = dao.getAvailableLanguages(transcribedManId);
		
		for(LanguageBean bean : languageList) {
			availableLanguages.append(bean.getName());
			availableLanguages.append(", ");
		}
		
		return availableLanguages.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<Object, Object> findManuscriptByIdTrans(Long id, String folderPath) throws OMDPCoreException, JSONException, IOException {
		
		 Map<Object, Object> rtrnMap = new HashMap<>();
		 
		DigitalManuscriptVO vo = new DigitalManuscriptVO();
		DigitalManuscriptVO voTranscripted = new DigitalManuscriptVO();
		List<TagMasterVO> tagVOs = new ArrayList<>();
		Map<String, Object> returnMap = dao.findByIdTrans(id);
		
		DigitalDocumentVO docVoOrig =  DigitalDocumentAssembler.convertEntityToVO((DigitalDocumentBean)returnMap.get("originalRecordDoc"));
		DigitalDocumentVO docVoTrans =  DigitalDocumentAssembler.convertEntityToVO((DigitalDocumentBean)returnMap.get("TransRecordDoc"));
		
		rtrnMap.put("OriginalDocument", docVoOrig);
		rtrnMap.put("TransDocument", docVoTrans);
		
		DigitalManuscriptBean bean = new DigitalManuscriptBean(); 
		DigitalManuscriptBean bean1 = (DigitalManuscriptBean) returnMap.get("manuscriptBean");
		
		if(bean1.getManuscriptType() == ManuscriptTypeEnum.Translation) {
			voTranscripted = DigitalManuscriptAssembler.convertEntityToVo(bean1);
			rtrnMap.put("TranscriptedVO", voTranscripted);
			
			bean = bean1.getParentFkObj().getParentFkObj();
		}
		
		List<DigitalManuscriptFrame> frames = new ArrayList<>();
		List<String> translatedDataList = new ArrayList<>();
		
		frames = (List<DigitalManuscriptFrame>)returnMap.get("TranscribesFramesDocs");
		translatedDataList = (List<String>)returnMap.get("TranslatedTexts");
		/*for(DigitalManuscriptFrame frame : bean.getDigitalManuscriptFrames()) {
			frame.setDigitalDocumentBean(dao.findDocumentByFrameId(frame.getId()));
			frames.add(frame);
		}*/
		DigitalManuscriptFrame sortFrame = new DigitalManuscriptFrame();
		Collections.sort(frames , sortFrame);
		bean.setDigitalManuscriptFrames(frames);
		List<String> pathList = new ArrayList<>();
		for(int i=0 ; i<bean.getDigitalManuscriptFrames().size() ; i++) {
			pathList.add(bean.getDigitalManuscriptFrames().get(i).getFilePath());
		}
		
		bean = convertFilePathsForDisplay(bean, folderPath);
		
		vo = DigitalManuscriptAssembler.convertEntityToVo(bean);
		vo.setDigitizedBy((String) returnMap.get("digitiserName"));
		
		if(vo.getDigitalManuscriptFrameVOs() != null && vo.getDigitalManuscriptFrameVOs().size() > 0) {
			/*
			 * If there are Digital Frames, then convert them to JSON objects 
			 * and put them in the fileDiskPathContainer field
			 */
			
		JSONArray jsonArray = new JSONArray();
		for(int i=0 ; i < bean.getDigitalManuscriptFrames().size() ; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", bean.getDigitalManuscriptFrames().get(i).getId());
			jsonObject.put("filePath", bean.getDigitalManuscriptFrames().get(i).getFilePath());
			jsonObject.put("isLast", bean.getDigitalManuscriptFrames().get(i).getIsLast());
			jsonObject.put("filePathReal", pathList.get(i));
			
			if(bean.getDigitalManuscriptFrames().get(i).getTranslatedText() != null && bean.getDigitalManuscriptFrames().get(i).getTranslatedText().length() > 0) {
				jsonObject.put("translatedtext", bean.getDigitalManuscriptFrames().get(i).getTranslatedText());
			} else {
				jsonObject.put("translatedtext", "");
			}
			
			if(bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean() != null) {
				jsonObject.put("documentId", bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getId());
				jsonObject.put("text", bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getText());
				
				//checking for the attachment file path
				if(bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath()!= null){
					String dbFilePath =  bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath();
				jsonObject.put("attachmentPath",dbFilePath);
				}else{
					jsonObject.put("attachmentPath","");
				}
			} else {
				jsonObject.put("documentId", "");
				jsonObject.put("text", "");
			}
			
			JSONArray commentArray = new JSONArray();
			JSONObject commentObj = null;
			
			for(int j=0 ; j<bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().size() ; j++) {
				commentObj = new JSONObject();
				commentObj.put("commentText",bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().get(j).getComment());
				commentObj.put("commentBy",bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().get(j).getCommentedBy());
				commentObj.put("commentTime",bean.getDigitalManuscriptFrames().get(i).getFrameCommentsList().get(j).getCommentedOn());
				commentArray.put(commentObj);
			}
			
			jsonObject.put("comments", commentArray);
			jsonArray.put(jsonObject);
		}
		vo.setFileDiskPathContainer(jsonArray.toString());
		}
		/*for(int i=0 ; i < vo.getDigitalManuscriptFrameVOs().size() ; i++) {
			vo.getDigitalManuscriptFrameVOs().get(i).setFullPath(pathList.get(i));
		}
		*/
		
		List<ManuscriptTagMapper> mapperList = (List<ManuscriptTagMapper>) returnMap.get("tagList");
		for(ManuscriptTagMapper taglist : mapperList){
			tagVOs.add((TagMasterVO) CustomBeanUtil.entityToVO(taglist.getTagsFkObj(), new TagMasterVO()));
		}
		/*for(ManuscriptSpecificcategoryMapper specificcategory : (List<ManuscriptSpecificcategoryMapper>)returnMap.get("specificCategoryList")){
			specificcategorylist.add(specificcategory.getSpecificcategoryFkId());
		}
		vo.setSpecificCategoryId(specificcategorylist);*/
		vo.setTagList(tagVOs);
		
		rtrnMap.put("Original", vo);
		return rtrnMap;
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public DigitalManuscriptVO mergeManuscript(List<String> allListStr, List<DigitalManuscriptVO> originalLists , String folderPath) throws OMDPCoreException , Exception {
		List<Long> manuscriptIds = new ArrayList<>();
		Map<String, Object> returnMap = new HashMap<>();
		JSONArray jsonArray = new JSONArray();
		List<DigitalManuscriptFrameVO> frameVOList = new ArrayList<>();
		
		DigitalManuscriptVO mergedVo = new DigitalManuscriptVO();
		for(DigitalManuscriptVO vo : originalLists) {
			if(vo.getIsMerging()) {
				manuscriptIds.add(vo.getId());
			} else if(allListStr != null && allListStr.size()>0){
				String bfr = vo.getId().toString();
				allListStr.remove(bfr);
			}
		}
		
		List<Long> lngIds = new ArrayList<>();
		
		if(allListStr != null && allListStr.size() > 0) {
			for(String str: allListStr) {
				lngIds.add(Long.parseLong(str));
			}
		} else {
			lngIds = manuscriptIds;
		}
		if(lngIds.size()<2){
			throw new OMDPCoreException(OMDPCoreException.AT_LEAST_SELECT_TWO_MANUSCRIPT_TO_MERGE);
	    }
		net.sf.json.JSONArray jsonA = net.sf.json.JSONArray.fromObject(lngIds);
		returnMap = dao.mergeManuscript(lngIds);
		
		originalLists = new ArrayList<>();
		List<DigitalManuscriptBean> digitalManuBeanLists = new ArrayList<>();
		digitalManuBeanLists = (List<DigitalManuscriptBean>) returnMap.get("manuscriptlist");
		
		DigitalManuscriptVO vo = null;
		
		for(int k=0 ; k<digitalManuBeanLists.size() ; k++) {
			vo = new DigitalManuscriptVO();
			
			DigitalManuscriptBean bean = digitalManuBeanLists.get(k);
			List<DigitalManuscriptFrame> frames = bean.getDigitalManuscriptFrames();
			
			DigitalManuscriptFrame sortFrame = new DigitalManuscriptFrame();
			Collections.sort(bean.getDigitalManuscriptFrames() , sortFrame);
			
			bean.setDigitalManuscriptFrames(frames);
			
			List<String> pathList = new ArrayList<>();
			for(int j=0 ; j<bean.getDigitalManuscriptFrames().size() ; j++) {
				pathList.add(bean.getDigitalManuscriptFrames().get(j).getFilePath());
			}
			
			bean = convertFilePathsForDisplay(bean, folderPath);
			
			vo = DigitalManuscriptAssembler.convertEntityToVo(bean);
			
			if(vo.getDigitalManuscriptFrameVOs() != null && vo.getDigitalManuscriptFrameVOs().size() > 0) {
			
			
			
			for(int i=0 ; i < bean.getDigitalManuscriptFrames().size() ; i++) {
				DigitalManuscriptFrameVO voFr = new DigitalManuscriptFrameVO();
				CustomBeanUtil.entityToVO(bean.getDigitalManuscriptFrames().get(i), voFr) ;
				voFr.setId(null);
				frameVOList.add(voFr);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", bean.getDigitalManuscriptFrames().get(i).getId());
				jsonObject.put("filePath", bean.getDigitalManuscriptFrames().get(i).getFilePath());
				jsonObject.put("filePathReal", pathList.get(i));
				if(bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean() != null) {
					jsonObject.put("documentId", bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getId());
					if(bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath()!= null){
						jsonObject.put("attachmentPath", bean.getDigitalManuscriptFrames().get(i).getDigitalDocumentBean().getDocumentDetailsBean().getAttachmentFilePath());
					}else{
						jsonObject.put("attachmentPath","");
					}
				} else {
					jsonObject.put("documentId", "");
					jsonObject.put("attachmentPath","");
				}
				
				jsonArray.put(jsonObject);
			}
			vo.setDigitalManuscriptFrameVOs(frameVOList);
			vo.setFileDiskPathContainer(jsonArray.toString());
			}
			
			if(k==0) {
				List<ManuscriptTagMapper> mapperList = (List<ManuscriptTagMapper>) returnMap.get("manuscriptTag");
				
				vo.setTagList(new ArrayList<TagMasterVO>());
				vo.setSpecificCategoryId(new ArrayList<Long>());
				
				for(ManuscriptTagMapper taglist : mapperList){
					vo.getTagList().add((TagMasterVO) CustomBeanUtil.entityToVO(taglist.getTagsFkObj(), new TagMasterVO()));
				}
				for(ManuscriptSpecificcategoryMapper specificcategory : (List<ManuscriptSpecificcategoryMapper>)returnMap.get("manuscriptSpecificCat")){
					vo.getSpecificCategoryId().add(specificcategory.getSpecificcategoryFkId());
				}
			}
			originalLists.add(vo);
		}
		mergedVo = getMergedVo(originalLists);
		mergedVo.setParentIdsStr(jsonA.toString());
		
		return mergedVo;
	}
	
	
	public DigitalManuscriptVO getMergedVo(List<DigitalManuscriptVO> vosToBeMerged) {
		DigitalManuscriptVO mergedVO = new DigitalManuscriptVO();
		
		for(int i=0 ; i<vosToBeMerged.size() ; i++) {
			if(i== 0) {
				mergedVO = vosToBeMerged.get(i);
				mergedVO.setId(null);
			} else {
				String xx = "\r\n";
				mergedVO.setSummary(IndvenApplicationConstants.lineseparator+mergedVO.getName()+IndvenApplicationConstants.lineseparator+xx+mergedVO.getSummary()+xx+IndvenApplicationConstants.lineseparator+vosToBeMerged.get(i).getName()+IndvenApplicationConstants.lineseparator+xx+ vosToBeMerged.get(i).getSummary());
				mergedVO.setTableOfContents(IndvenApplicationConstants.lineseparator+mergedVO.getName()+IndvenApplicationConstants.lineseparator+xx+mergedVO.getTableOfContents()+xx+IndvenApplicationConstants.lineseparator+vosToBeMerged.get(i).getName()+IndvenApplicationConstants.lineseparator+xx+ vosToBeMerged.get(i).getTableOfContents());
				mergedVO.setContributionToAyurveda(IndvenApplicationConstants.lineseparator+mergedVO.getName()+IndvenApplicationConstants.lineseparator+xx+mergedVO.getContributionToAyurveda()+xx+IndvenApplicationConstants.lineseparator+vosToBeMerged.get(i).getName()+IndvenApplicationConstants.lineseparator+xx+ vosToBeMerged.get(i).getContributionToAyurveda());
				
				mergedVO.setUniquenessOfWork(IndvenApplicationConstants.lineseparator+mergedVO.getName()+IndvenApplicationConstants.lineseparator+xx+mergedVO.getUniquenessOfWork()+xx+IndvenApplicationConstants.lineseparator+vosToBeMerged.get(i).getName()+IndvenApplicationConstants.lineseparator+xx+ vosToBeMerged.get(i).getUniquenessOfWork());
				mergedVO.setAnyOtherDetails(IndvenApplicationConstants.lineseparator+mergedVO.getName()+IndvenApplicationConstants.lineseparator+xx+mergedVO.getAnyOtherDetails()+xx+IndvenApplicationConstants.lineseparator+vosToBeMerged.get(i).getName()+IndvenApplicationConstants.lineseparator+xx+ vosToBeMerged.get(i).getAnyOtherDetails());
			}
			
			if(i == vosToBeMerged.size() -1) {
				mergedVO.setFileDiskPathContainer(vosToBeMerged.get(i).getFileDiskPathContainer());
			}
		}
		
		return mergedVO;
	}
	
	public boolean saveMergedImages(DigitalManuscriptVO vo , PublicationVO publicationVO , String targetPath) throws JSONException, OMDPCoreException, IOException {
		boolean status = false;
		String temporaryName = "";
		String folderDateYearStruct = "";
		List<String> list = new ArrayList<String>();
		
		DigitalManuscriptBean bean = DigitalManuscriptAssembler.convertVoToEntity(vo);
		
		PublicationBean publicationBean = new PublicationBean();
		publicationBean = PublicationAssembler.convertVOToEntity(publicationVO);
		List<TagMasterBean> tagList= new ArrayList<TagMasterBean>();
		if(vo.getTagList() != null){
		for(TagMasterVO tagvo: vo.getTagList() ){
			TagMasterBean tagBean= new TagMasterBean();
			tagList.add((TagMasterBean) CustomBeanUtil.voToEntity(tagvo,tagBean));
		}
		}
		bean.setIsDeleted((short)0);
		publicationBean.setIsDeleted((short)0);
		
		String voStr = vo.getFileDiskPathContainer();
		
		if(voStr != null && voStr.length() > 0) {
			voStr = "{'myArray':"+voStr+"}";
			JSONObject jObject = new JSONObject(voStr);
			JSONArray jArray = jObject.getJSONArray("myArray");
			List<DigitalManuscriptFrame> frameList = new ArrayList<>();
			
			DigitalManuscriptFrame framebean = null;
			
			
			String resizeFolderPath = ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE).getObject("images.system.path").toString();
			String thumbnailFolderPath = ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE).getObject("imagesResize.system.path").toString();
			
			String folderPath = ""; //Path where the Directory need to be created for saving the images.
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfoVO userInfoVO = (UserInfoVO) session.get(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
			
			Calendar now = Calendar.getInstance();
			String year = String.valueOf(now.get(Calendar.YEAR));
			String month =  String.valueOf(now.get(Calendar.MONTH)+1);
			String day =  String.valueOf(now.get(Calendar.DAY_OF_MONTH));
			
			folderDateYearStruct = "/"+year+"/"+month+"/"+day+"/";
			temporaryName = userInfoVO.getLoginName()+now.getTimeInMillis();
			
			folderPath = folderDateYearStruct+temporaryName ;
			
			System.out.println(resizeFolderPath+folderPath);
			
			new File(resizeFolderPath+folderPath).mkdirs();
			new File(thumbnailFolderPath+folderPath).mkdirs();
			
			for(int i=0 ; i<jArray.length() ; i++) {
				 framebean = new DigitalManuscriptFrame();
				 JSONObject jObj = jArray.getJSONObject(i);
				 
				 System.out.println(resizeFolderPath+jObj.get("filePathReal"));
				 
				 File file = new File(resizeFolderPath+jObj.get("filePathReal"));
				 
				 String xx= "";
				 xx = ("0000" + i).substring(String.valueOf(i).length())+".jpg";
				 String targetpathOrg = resizeFolderPath+folderPath+"/"+xx;
				 
				 FilesUtil.saveFile(file, xx, targetpathOrg);
				 cropImage(targetpathOrg, folderPath);
					
				 framebean.setFilePath(folderPath+"/"+xx);
				 framebean.setDigitalManuscriptFkObj(bean);
				 frameList.add(framebean);
			}
			
			bean.setDigitalManuscriptFrames(frameList);
		} 
		
		String numbers = vo.getParentIdsStr().substring(1, vo.getParentIdsStr().length()-1);
		list = new ArrayList<String>(Arrays.asList(numbers.split(",")));
		
		bean = dao.saveDigitalManuscriptMerging(bean,publicationBean,tagList,vo.getSpecificCategoryId(),temporaryName,folderDateYearStruct,list);
		status = true;
		return status;
	}
}
  
