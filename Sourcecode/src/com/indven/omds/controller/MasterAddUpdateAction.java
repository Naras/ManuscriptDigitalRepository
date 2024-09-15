/**
 * 
 */
package com.indven.omds.controller;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.exceptionhandler.IndvenMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.service.MasterAddUpdateServiceImpl;
import com.indven.omds.vo.BundleMasterVO;
import com.indven.omds.vo.CategoryVO;
import com.indven.omds.vo.LanguageVO;
import com.indven.omds.vo.ScriptVO;
import com.indven.omds.vo.TagMasterVO;

/**
 * @author Deba Prasad
 * 
 */
public class MasterAddUpdateAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static IndvenLogger logger = IndvenLogger
			.getInstance(MasterAddUpdateAction.class);
	private LanguageVO languageVO = new LanguageVO();
	private CategoryVO categoryVO = new CategoryVO();
	private MasterAddUpdateServiceImpl service = new MasterAddUpdateServiceImpl();
	private ScriptVO script = new ScriptVO();
	private BundleMasterVO bundleVO = new BundleMasterVO();
	private TagMasterVO tagVO = new TagMasterVO(); 
	private List<TagMasterVO> tagList= new ArrayList<>();	
	private TagMasterVO replacedTag=new TagMasterVO(); ;
	/**
	 * @return the bundleVO
	 */
	public final BundleMasterVO getBundleVO() {
		return bundleVO;
	}

	/**
	 * @param bundleVO the bundleVO to set
	 */
	public final void setBundleVO(BundleMasterVO bundleVO) {
		this.bundleVO = bundleVO;
	}

	/**
	 * @return the script
	 */
	public final ScriptVO getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	public final void setScript(ScriptVO script) {
		this.script = script;
	}

	/**
	 * @return the categoryVO
	 */
	public final CategoryVO getCategoryVO() {
		return categoryVO;
	}

	/**
	 * @param categoryVO
	 *            the categoryVO to set
	 */
	public final void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	/**
	 * @return the languageVO
	 */
	public final LanguageVO getLanguageVO() {
		return languageVO;
	}

	/**
	 * @param languageVO
	 *            the languageVO to set
	 */
	public final void setLanguageVO(LanguageVO languageVO) {
		this.languageVO = languageVO;
	}

	public String saveLanguage() {
		String status = ERROR;
		boolean isUpdating = false;
		if (languageVO.getId() != null && languageVO.getId() > 0) {
			isUpdating = true;
		}
		try {
			languageVO = service.saveLanguage(languageVO);
			if (!isUpdating) {
				languageVO = new LanguageVO();
			}
			status = SUCCESS;
			addActionMessage("Successfully saved the record");
		} catch (OMDPCoreException e) {
			logger.error(e);
			languageVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			languageVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(languageVO.getMessage());
		}
		return status;
	}

	public String findLanguageById() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			languageVO = service.findById(Long.parseLong(id));
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			languageVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			languageVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_FIND_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(languageVO.getMessage());
		}

		return status;

	}

	public String deleteLanguage() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			service.deleteById(Long.parseLong(id));
			status = SUCCESS;
			addActionMessage("Successfully deleted the record");
		} catch (Exception e) {
			logger.error(e);
			languageVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			languageVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_DELETE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(languageVO.getMessage());
		}

		return status;

	}

	public String saveCategory() {
		String status = ERROR;
		boolean isUpdating = false;
		if (categoryVO.getId() != null && categoryVO.getId() > 0) {
			isUpdating = true;
		}
		try {
			categoryVO = service.saveCategory(categoryVO);
			if (!isUpdating) {
				categoryVO = new CategoryVO();
			}
			status = SUCCESS;
			addActionMessage("Successfully saved the record");
		} catch (OMDPCoreException e) {
			logger.error(e);
			categoryVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			categoryVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(categoryVO.getMessage());
		}
		return status;
	}

	public String findCategoryById() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			categoryVO = service.findCategoryById(Long.parseLong(id));
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			categoryVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			categoryVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_FIND_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(categoryVO.getMessage());
		}

		return status;

	}

	public String deleteCategory() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			service.deleteCategoryById(Long.parseLong(id));
			status = SUCCESS;
			addActionMessage("Successfully deleted the record");
		} catch (Exception e) {
			logger.error(e);
			categoryVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			categoryVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_DELETE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(categoryVO.getMessage());
		}

		return status;

	}
	
	public String saveTag(){
		String status = ERROR;
		boolean isUpdating = false;
		if(tagVO.getId() != null && tagVO.getId() >0){
			isUpdating = true;
		}
		try {
		tagVO = service.saveTag(tagVO);
		if (!isUpdating) {
			tagVO = new TagMasterVO();
		}
		status = SUCCESS;
		addActionMessage("Successfully saved the record");
		}catch (OMDPCoreException e) {
			logger.error(e);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(
					script, e, IndvenApplicationConstants.LOCALE);
			addActionError(script.getMessage());
			status = ERROR;
		}
		return status;
	}
	
	public String replaceTag(){
		String status = ERROR;
		try {
		service.replaceTag(tagList,replacedTag);
		status = SUCCESS;
		setTagList(findTagAfterReplace(tagVO.getName()));
		addActionMessage("Successfully Replaced The Record");
		status = SUCCESS;
		}catch (OMDPCoreException oe) {
			try {
				setTagList(findTagAfterReplace(tagVO.getName()));
			} catch (OMDPCoreException e) {
				 status = ERROR;
				addActionError("There is some problem While Replacing the Tags");
				e.printStackTrace();
			}
			logger.error(oe);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			new IndvenExceptionMessageResolver().resolveMessage(
					script, oe, IndvenApplicationConstants.LOCALE);
			addActionError(script.getMessage());
			 status = ERROR;
		}catch (Exception e) {
			addActionError("There is some problem While Replacing the Tags");
			 status = ERROR;
		}
		
		return status;
		
	}
	
	public List<TagMasterVO> findTagAfterReplace(String name) throws OMDPCoreException {
		List<TagMasterVO> tags = new ArrayList<>();
		
			tags=service.findTag(name);
		return tags;

	}
	
	public String findTag() {
		String status = ERROR;
		try {
			 setTagList(service.findTag(tagVO.getName()));
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			script.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_FIND_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(script.getMessage());
		}

		return status;

	}
	
	public String deleteTag() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			service.deleteTagById(Long.parseLong(id));
			status = SUCCESS;
			addActionMessage("Successfully deleted the record");
		} catch (Exception e) {
			logger.error(e);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			script.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_DELETE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(script.getMessage());
		}

		return status;
	}
	

	public String saveScript() {
		String status = ERROR;
		boolean isUpdating = false;
		if (script.getId() != null && script.getId() > 0) {
			isUpdating = true;
		}
		try {
			script = service.saveScript(script);
			if (!isUpdating) {
				script = new ScriptVO();
			}
			status = SUCCESS;
			addActionMessage("Successfully saved the record");
		} catch (OMDPCoreException e) {
			logger.error(e);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			script.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(script.getMessage());
		}
		return status;
	}

	public String findScriptById() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			script = service.findScriptById(Long.parseLong(id));
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			script.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_FIND_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(script.getMessage());
		}

		return status;

	}

	public String deleteScript() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			service.deleteScriptById(Long.parseLong(id));
			status = SUCCESS;
			addActionMessage("Successfully deleted the record");
		} catch (Exception e) {
			logger.error(e);
			script.setStatus(IndvenResultVO.STATUS_FAILURE);
			script.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_DELETE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(script.getMessage());
		}

		return status;
	}
	
	
	
	
	public String saveBundle() {
		String status = ERROR;
		boolean isUpdating = false;
		if (bundleVO.getId() != null && bundleVO.getId() > 0) {
			isUpdating = true;
		}
		try {
			bundleVO = service.saveBundle(bundleVO);
			if (!isUpdating) {
				bundleVO = new BundleMasterVO();
			}
			status = SUCCESS;
			addActionMessage("Successfully saved the record");
		} catch (OMDPCoreException e) {
			logger.error(e);
			bundleVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			bundleVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_SAVE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(bundleVO.getMessage());
		}
		return status;
	}

	public String findBundleById() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			bundleVO = service.findBundleById(Long.parseLong(id));
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			bundleVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			bundleVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_FIND_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(bundleVO.getMessage());
		}

		return status;

	}

	public String deleteBundle() {
		String id = getRequest().getParameter("id");
		String status = ERROR;
		try {
			service.deleteBundleById(Long.parseLong(id));
			status = SUCCESS;
			addActionMessage("Successfully deleted the record");
		} catch (Exception e) {
			logger.error(e);
			bundleVO.setStatus(IndvenResultVO.STATUS_FAILURE);
			bundleVO.setMessage(IndvenMessageResolver.resolveMessage(
					OMDPCoreException.UNABLE_TO_DELETE_THE_RECORD,
					IndvenApplicationConstants.LOCALE));
			addActionError(bundleVO.getMessage());
		}
		return status;

	}

	/*
	 * This method override to redirect the page to different page using the
	 * different action which will come DB through UI
	 */
	public String reDirectToPage() {
		return SUCCESS;
	}

	/**
	 * @return the tagVO
	 */
	public TagMasterVO getTagVO() {
		return tagVO;
	}

	/**
	 * @param tagVO the tagVO to set
	 */
	public void setTagVO(TagMasterVO tagVO) {
		this.tagVO = tagVO;
	}

	/**
	 * @return the tagList
	 */
	public List<TagMasterVO> getTagList() {
		return tagList;
	}

	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(List<TagMasterVO> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the replacedTag
	 */
	public TagMasterVO getReplacedTag() {
		return replacedTag;
	}

	/**
	 * @param replacedTag the replacedTag to set
	 */
	public void setReplacedTag(TagMasterVO replacedTag) {
		this.replacedTag = replacedTag;
	}

}
