package com.indven.omds.vo;

import com.indven.framework.vo.IndvenResultVO;

/**
 * Value Object for digital document
 * Contains online derived work for manuscript
 * @author Neel Borooah
 *
 */
public class DigitalDocumentVO extends IndvenResultVO {

	private Long id;
	private Long digitalManuscriptFkId;
	private Long digitalManuscriptFrameFkId;
	private Long languageFkId;
	private Long scriptFkId;
	private String documentStatus;
	private String workType;
	private String language;
	private String script;
	
	private DigitalManuscriptVO digitalManuscriptVO;
	private DigitalDocumentDetailsVO detailsVO;
	
	/**
	 * @return the script
	 */
	public final String getScript() {
		return script;
	}
	/**
	 * @param script the script to set
	 */
	public final void setScript(String script) {
		this.script = script;
	}
	/**
	 * @return the language
	 */
	public final String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public final void setLanguage(String language) {
		this.language = language;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDigitalManuscriptFkId() {
		return digitalManuscriptFkId;
	}
	public void setDigitalManuscriptFkId(Long digitalManuscriptFkId) {
		this.digitalManuscriptFkId = digitalManuscriptFkId;
	}
	public Long getDigitalManuscriptFrameFkId() {
		return digitalManuscriptFrameFkId;
	}
	public void setDigitalManuscriptFrameFkId(Long digitalManuscriptFrameFkId) {
		this.digitalManuscriptFrameFkId = digitalManuscriptFrameFkId;
	}
	public Long getLanguageFkId() {
		return languageFkId;
	}
	public void setLanguageFkId(Long languageFkId) {
		this.languageFkId = languageFkId;
	}
	public Long getScriptFkId() {
		return scriptFkId;
	}
	public void setScriptFkId(Long scriptFkId) {
		this.scriptFkId = scriptFkId;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public DigitalDocumentDetailsVO getDetailsVO() {
		return detailsVO;
	}
	public void setDetailsVO(DigitalDocumentDetailsVO detailsVO) {
		this.detailsVO = detailsVO;
	}
	public DigitalManuscriptVO getDigitalManuscriptVO() {
		return digitalManuscriptVO;
	}
	public void setDigitalManuscriptVO(DigitalManuscriptVO digitalManuscriptVO) {
		this.digitalManuscriptVO = digitalManuscriptVO;
	}
	public String getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}
	
}
