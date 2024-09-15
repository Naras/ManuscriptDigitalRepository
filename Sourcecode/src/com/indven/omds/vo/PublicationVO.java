/**
 * 
 */
package com.indven.omds.vo;


/**
 * @author Deba Prasad
 *
 */
public class PublicationVO {

	private Long id;
	private String yaerOfPublication;
	private Short isAvailable;
	private String noOfPages;
	private String price;
	private String remark;
	private Long authorFkId;
	private Long editorFkId;
	private AuthorVO authorVO;
	private Long publisherFkId;
	private PublisherVO publisherVO;
	private AuthorVO editorVO;
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the yaerOfPublication
	 */
	public final String getYaerOfPublication() {
		return yaerOfPublication;
	}
	/**
	 * @param yaerOfPublication the yaerOfPublication to set
	 */
	public final void setYaerOfPublication(String yaerOfPublication) {
		this.yaerOfPublication = yaerOfPublication;
	}
	/**
	 * @return the isAvailable
	 */
	public final Short getIsAvailable() {
		return isAvailable;
	}
	/**
	 * @param isAvailable the isAvailable to set
	 */
	public final void setIsAvailable(Short isAvailable) {
		this.isAvailable = isAvailable;
	}
	/**
	 * @return the noOfPages
	 */
	public final String getNoOfPages() {
		return noOfPages;
	}
	/**
	 * @param noOfPages the noOfPages to set
	 */
	public final void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}
	/**
	 * @return the price
	 */
	public final String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public final void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the remark
	 */
	public final String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public final void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the authorVO
	 */
	public final AuthorVO getAuthorVO() {
		return authorVO;
	}
	/**
	 * @param authorVO the authorVO to set
	 */
	public final void setAuthorVO(AuthorVO authorVO) {
		this.authorVO = authorVO;
	}
	/**
	 * @return the publisherVO
	 */
	public final PublisherVO getPublisherVO() {
		return publisherVO;
	}
	/**
	 * @param publisherVO the publisherVO to set
	 */
	public final void setPublisherVO(PublisherVO publisherVO) {
		this.publisherVO = publisherVO;
	}
	public Long getAuthorFkId() {
		return authorFkId;
	}
	public void setAuthorFkId(Long authorFkId) {
		this.authorFkId = authorFkId;
	}
	public Long getPublisherFkId() {
		return publisherFkId;
	}
	public void setPublisherFkId(Long publisherFkId) {
		this.publisherFkId = publisherFkId;
	}
	public AuthorVO getEditorVO() {
		return editorVO;
	}
	public void setEditorVO(AuthorVO editorVO) {
		this.editorVO = editorVO;
	}
	public Long getEditorFkId() {
		return editorFkId;
	}
	public void setEditorFkId(Long editorFkId) {
		this.editorFkId = editorFkId;
	}
	
}
