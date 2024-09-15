package com.indven.omds.vo;

public class DigitalManuscriptFrameVO {

	private Long id;
	private String filePath;
	private Long digitalManuscriptFkId;

	private DigitalDocumentVO documentVO;
	private Short isLast=0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getDigitalManuscriptFkId() {
		return digitalManuscriptFkId;
	}
	public void setDigitalManuscriptFkId(Long digitalManuscriptFkId) {
		this.digitalManuscriptFkId = digitalManuscriptFkId;
	}
	public DigitalDocumentVO getDocumentVO() {
		return documentVO;
	}
	public void setDocumentVO(DigitalDocumentVO documentVO) {
		this.documentVO = documentVO;
	}
	/**
	 * @return the isLast
	 */
	public Short getIsLast() {
		return isLast;
	}
	/**
	 * @param isLast the isLast to set
	 */
	public void setIsLast(Short isLast) {
		this.isLast = isLast;
	}

}
