package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.indven.omds.util.DocumentWorkType;

/**
 * Contains the online derived work of the digital manuscript
 * @author Neel Borooah
 *
 */
@Entity
@Table(name = "omds_digitaldocument")
public class DigitalDocumentBean implements Serializable {

	private static final long serialVersionUID = 2055266422456039638L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "digitalManuscriptFkId", insertable = true, updatable = true , nullable = true)
	private Long digitalManuscriptFkId;
	
	@OneToOne
	@JoinColumn(name = "digitalManuscriptFkId", nullable=true,insertable=false,updatable=false)
	private DigitalManuscriptBean digitalManuscriptBean;
	
	@Column(name = "digitalManuscriptFrameFkId", insertable = true, updatable = true , nullable = true)
	private Long digitalManuscriptFrameFkId;
	
	@OneToOne
	@JoinColumn(name = "digitalManuscriptFrameFkId", nullable=true,insertable=false,updatable=false)
	private DigitalManuscriptFrame digitalManuscriptFrame;

	@Column(name = "languageFkId", insertable = true, updatable = true , nullable = true)
	private Long languageFkId;
	
	@ManyToOne
	@JoinColumn(name = "languageFkId", nullable=true,insertable=false,updatable=false)
	private LanguageBean languageBean;
	
	@Column(name = "scriptFkId", insertable = true, updatable = true , nullable = true)
	private Long scriptFkId;
	
	@ManyToOne
	@JoinColumn(name = "scriptFkId", nullable=true,insertable=false,updatable=false)
	private ScriptBean scriptBean;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "workType", nullable = true)
	private DocumentWorkType workType;
	
	@OneToOne(mappedBy="digitalDocumentBean", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private DigitalDocumentDetailsBean documentDetailsBean;
	
	@Column(name = "language", insertable = true, updatable = true , nullable = true)
	private String language;
	
	@Column(name = "script", insertable = true, updatable = true , nullable = true)
	private String script;
	
	@Column(name = "recordtype", insertable = true, updatable = true , nullable = true)
	private Short recordType;
	
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

	/**
	 * @return the recordType
	 */
	public final Short getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType the recordType to set
	 */
	public final void setRecordType(Short recordType) {
		this.recordType = recordType;
	}

	public Long getDigitalManuscriptFkId() {
		return digitalManuscriptFkId;
	}

	public void setDigitalManuscriptFkId(Long digitalManuscriptFkId) {
		this.digitalManuscriptFkId = digitalManuscriptFkId;
	}

	public DigitalManuscriptBean getDigitalManuscriptBean() {
		return digitalManuscriptBean;
	}

	public void setDigitalManuscriptBean(DigitalManuscriptBean digitalManuscriptBean) {
		this.digitalManuscriptBean = digitalManuscriptBean;
	}

	public Long getDigitalManuscriptFrameFkId() {
		return digitalManuscriptFrameFkId;
	}

	public void setDigitalManuscriptFrameFkId(Long digitalManuscriptFrameFkId) {
		this.digitalManuscriptFrameFkId = digitalManuscriptFrameFkId;
	}

	public DigitalManuscriptFrame getDigitalManuscriptFrame() {
		return digitalManuscriptFrame;
	}

	public void setDigitalManuscriptFrame(
			DigitalManuscriptFrame digitalManuscriptFrame) {
		this.digitalManuscriptFrame = digitalManuscriptFrame;
	}

	public Long getLanguageFkId() {
		return languageFkId;
	}

	public void setLanguageFkId(Long languageFkId) {
		this.languageFkId = languageFkId;
	}

	public LanguageBean getLanguageBean() {
		return languageBean;
	}

	public void setLanguageBean(LanguageBean languageBean) {
		this.languageBean = languageBean;
	}

	public Long getScriptFkId() {
		return scriptFkId;
	}

	public void setScriptFkId(Long scriptFkId) {
		this.scriptFkId = scriptFkId;
	}

	public ScriptBean getScriptBean() {
		return scriptBean;
	}

	public void setScriptBean(ScriptBean scriptBean) {
		this.scriptBean = scriptBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentWorkType getWorkType() {
		return workType;
	}

	public void setWorkType(DocumentWorkType workType) {
		this.workType = workType;
	}

	public DigitalDocumentDetailsBean getDocumentDetailsBean() {
		return documentDetailsBean;
	}

	public void setDocumentDetailsBean(DigitalDocumentDetailsBean documentDetailsBean) {
		this.documentDetailsBean = documentDetailsBean;
	}
}
