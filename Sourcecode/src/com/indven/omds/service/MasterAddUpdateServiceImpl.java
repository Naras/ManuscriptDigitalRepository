package com.indven.omds.service;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.HibernateException;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.omds.dao.MasterAddUpdateDAOImpl;
import com.indven.omds.entity.BundleMasterBean;
import com.indven.omds.entity.CategoryBean;
import com.indven.omds.entity.LanguageBean;
import com.indven.omds.entity.ScriptBean;
import com.indven.omds.entity.TagMasterBean;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.vo.BundleMasterVO;
import com.indven.omds.vo.CategoryVO;
import com.indven.omds.vo.LanguageVO;
import com.indven.omds.vo.ScriptVO;
import com.indven.omds.vo.TagMasterVO;

public class MasterAddUpdateServiceImpl {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LanguageVO saveLanguage(LanguageVO languageVO) throws OMDPCoreException {
		LanguageBean bean = new LanguageBean();
		bean = (LanguageBean) CustomBeanUtil.voToEntity(languageVO, bean);
		bean.setIsDeleted(false);
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(LanguageBean.class);
		try {
			if(bean.getId() != null && bean.getId() <= 0) {
				bean.setId(null);
			}
			bean = (LanguageBean) dao.saveOrUpdate(bean);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}catch (Exception  e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}
		return languageVO;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LanguageVO findById(Long id) throws OMDPCoreException {
		LanguageBean bean = new LanguageBean();
		LanguageVO vo = new LanguageVO();
		
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(LanguageBean.class);
		try {
			bean = (LanguageBean) dao.findById(id);
			vo = (LanguageVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
		return vo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteById(Long id) throws OMDPCoreException {
		LanguageBean bean = new LanguageBean();
		LanguageVO vo = new LanguageVO();
		
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(LanguageBean.class);
		try {
			dao.deleteById(id);
			vo = (LanguageVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CategoryVO saveCategory(CategoryVO vo) throws OMDPCoreException {
		CategoryBean bean = new CategoryBean();
		bean = (CategoryBean) CustomBeanUtil.voToEntity(vo, bean);
		bean.setIsDeleted(false);
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(CategoryBean.class);
		try {
			if(bean.getId() != null && bean.getId() <= 0) {
				bean.setId(null);
			}
			
			if(bean.getParentFKId() != null && bean.getParentFKId() <= 0) {
				bean.setParentFKId(null);
			}
			bean = (CategoryBean) dao.saveOrUpdate(bean);
			vo = (CategoryVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}catch (Exception  e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}
		return vo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CategoryVO findCategoryById(Long id) throws OMDPCoreException {
		CategoryBean bean = new CategoryBean();
		CategoryVO vo = new CategoryVO();
		
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(CategoryBean.class);
		try {
			bean = (CategoryBean) dao.findById(id);
			vo = (CategoryVO) CustomBeanUtil.entityToVO(bean, vo);
			if(bean.getParentFkObj() != null) {
				vo.setParentName(bean.getParentFkObj().getName());
				vo.setParentFKId(bean.getParentFkObj().getId());
			}
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
		return vo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteCategoryById(Long id) throws OMDPCoreException {
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(CategoryBean.class);
		try {
			dao.deleteById(id);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScriptVO saveScript(ScriptVO vo) throws OMDPCoreException {
		ScriptBean bean = new ScriptBean();
		bean = (ScriptBean) CustomBeanUtil.voToEntity(vo, bean);
		bean.setIsDeleted(false);
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(ScriptBean.class);
		try {
			if(bean.getId() != null && bean.getId() <= 0) {
				bean.setId(null);
			}
			bean = (ScriptBean) dao.saveOrUpdate(bean);
			vo = (ScriptVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}catch (Exception  e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}
		return vo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScriptVO findScriptById(Long id) throws OMDPCoreException {
		ScriptBean bean = new ScriptBean();
		ScriptVO vo = new ScriptVO();
		
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(ScriptBean.class);
		try {
			bean = (ScriptBean) dao.findById(id);
			vo = (ScriptVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
		return vo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteScriptById(Long id) throws OMDPCoreException {
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(ScriptBean.class);
		try {
			dao.deleteById(id);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TagMasterVO saveTag(TagMasterVO vo) throws OMDPCoreException{
		TagMasterBean bean = new TagMasterBean();
		if(vo.getName()==null || vo.getName().equals("")){
			throw new OMDPCoreException(OMDPCoreException.PLEASE_ENTER_A_TAG);
		}
		bean = (TagMasterBean) CustomBeanUtil.voToEntity(vo, bean);
		bean.setIsDeleted(false);
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(TagMasterBean.class);
		try {
			if(bean.getId() != null && bean.getId() <= 0) {
				bean.setId(null);
			}
				bean = (TagMasterBean) dao.saveOrUpdate(bean);
			  vo = (TagMasterVO) CustomBeanUtil.entityToVO(bean, vo);
			} catch (HibernateException e) {
				throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
			}catch (Exception  e) {
				throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
			}
		
		return vo;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String replaceTag(List<TagMasterVO> replaceTagList,TagMasterVO replaceTag) throws OMDPCoreException{
		TagMasterBean bean = new TagMasterBean();
		List<Long> replaceId = new ArrayList<Long>();
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(TagMasterBean.class);
		for(TagMasterVO tag : replaceTagList){
			if(tag.getReplaceId()){
				replaceId.add(tag.getId());
			}
		}
		bean=(TagMasterBean) CustomBeanUtil.voToEntity(replaceTag, bean);
		if(replaceId.size()<1){
			throw new OMDPCoreException(OMDPCoreException.SELECT_TAG_TO_REPLACE);
		}
		dao.replaceTag(replaceId,bean);
		return null;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TagMasterVO> findTag(String tagName) throws OMDPCoreException {
		List <TagMasterVO> tagVoList = new ArrayList<TagMasterVO>();
		TagMasterVO vo;
		
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(TagMasterBean.class);
		try {
			List<TagMasterBean> tagList= dao.findTag(tagName);
			for(TagMasterBean tagBean : tagList){
				vo = new TagMasterVO();
			vo = (TagMasterVO) CustomBeanUtil.entityToVO(tagBean, vo);
			tagVoList.add(vo);
			}
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
		return tagVoList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteTagById(Long id) throws OMDPCoreException {
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(TagMasterBean.class);
		try {
			dao.deleteById(id);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BundleMasterVO saveBundle(BundleMasterVO vo) throws OMDPCoreException {
		BundleMasterBean bean = new BundleMasterBean();
		bean = (BundleMasterBean) CustomBeanUtil.voToEntity(vo, bean);
		bean.setIsDeleted(false);
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(ScriptBean.class);
		try {
			if(bean.getId() != null && bean.getId() <= 0) {
				bean.setId(null);
			}
			bean = (BundleMasterBean) dao.saveOrUpdate(bean);
			vo = (BundleMasterVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}catch (Exception  e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD, e);
		}
		return vo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BundleMasterVO findBundleById(Long id) throws OMDPCoreException {
		BundleMasterBean bean = new BundleMasterBean();
		BundleMasterVO vo = new BundleMasterVO();
		
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(BundleMasterBean.class);
		try {
			bean = (BundleMasterBean) dao.findById(id);
			vo = (BundleMasterVO) CustomBeanUtil.entityToVO(bean, vo);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
		return vo;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteBundleById(Long id) throws OMDPCoreException {
		MasterAddUpdateDAOImpl dao = new MasterAddUpdateDAOImpl(BundleMasterBean.class);
		try {
			dao.deleteById(id);
		} catch (HibernateException e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		} catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, e);
		}
	}
}
