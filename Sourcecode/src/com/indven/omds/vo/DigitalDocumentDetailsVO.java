package com.indven.omds.vo;

/**
 * Value Object for digital document details
 * Contains online derived work for manuscript
 * @author Neel Borooah
 *
 */
public class DigitalDocumentDetailsVO {
	
	private Long id;
	private Long digitalDocumentFkId;
	private String text;
	private String attachmentFilePath;
	private Long userRoleDetailsFkId;
	private Long version;
	private Short isMax=0;
	
	/**
	 * @return the isMax
	 */
	public final Short getIsMax() {
		return isMax;
	}
	/**
	 * @param isMax the isMax to set
	 */
	public final void setIsMax(Short isMax) {
		this.isMax = isMax;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDigitalDocumentFkId() {
		return digitalDocumentFkId;
	}
	public void setDigitalDocumentFkId(Long digitalDocumentFkId) {
		this.digitalDocumentFkId = digitalDocumentFkId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}
	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}
	public Long getUserRoleDetailsFkId() {
		return userRoleDetailsFkId;
	}
	public void setUserRoleDetailsFkId(Long userRoleDetailsFkId) {
		this.userRoleDetailsFkId = userRoleDetailsFkId;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
}
