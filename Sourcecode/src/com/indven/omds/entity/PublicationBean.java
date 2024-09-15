/**
 * 
 */
package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_publication")
public class PublicationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "year_of_publication", insertable = true, updatable = true , nullable = true)
	private String yaerOfPublication;
	
	@Column(name = "isavailable", insertable = true, updatable = true, nullable = true)
	private Short isAvailable;
	
	@Column(name = "no_of_pages", insertable = true, updatable = true, nullable = true)
	private String noOfPages;
	
	@Column(name = "price", insertable = true, updatable = true, nullable = true)
	private String price;
	
	@Column(name = "remark", insertable = true, updatable = true, nullable = true)
	private String remark;
	
	@ManyToOne
	@JoinColumn(name = "authorfkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean authorFkObj;
	
	@Column(name="editorfkid", nullable=true,insertable=true,updatable=true)
	private Long editorFkId;
	
	@ManyToOne
	@JoinColumn(name = "editorfkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean editorFkObj;
	
	@OneToOne
	@JoinColumn(name = "publisherfkid", nullable=true,insertable=false,updatable=false)
	private PublisherBean publisherFkObj;
	
	@Column(name = "authorfkid", insertable = true, updatable = true, nullable=true)
	private Long authorFkId;
	
	@Column(name = "publisherfkid", insertable = true, updatable = true , nullable = true)
	private Long publisherFkId;
	
	@Column(name = "isdeleted", insertable = true, updatable = true)
	private Short isDeleted;

	/**
	 * @return the isDeleted
	 */
	public final Short getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public final void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the authorFkId
	 */
	public final Long getAuthorFkId() {
		return authorFkId;
	}

	/**
	 * @param authorFkId the authorFkId to set
	 */
	public final void setAuthorFkId(Long authorFkId) {
		this.authorFkId = authorFkId;
	}

	/**
	 * @return the publisherFkId
	 */
	public final Long getPublisherFkId() {
		return publisherFkId;
	}

	/**
	 * @param publisherFkId the publisherFkId to set
	 */
	public final void setPublisherFkId(Long publisherFkId) {
		this.publisherFkId = publisherFkId;
	}

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
	 * @return the authorFkObj
	 */
	public final AuthorBean getAuthorFkObj() {
		return authorFkObj;
	}

	/**
	 * @param authorFkObj the authorFkObj to set
	 */
	public final void setAuthorFkObj(AuthorBean authorFkObj) {
		this.authorFkObj = authorFkObj;
	}

	/**
	 * @return the publisherFkObj
	 */
	public final PublisherBean getPublisherFkObj() {
		return publisherFkObj;
	}

	/**
	 * @param publisherFkObj the publisherFkObj to set
	 */
	public final void setPublisherFkObj(PublisherBean publisherFkObj) {
		this.publisherFkObj = publisherFkObj;
	}

	public Long getEditorFkId() {
		return editorFkId;
	}

	public void setEditorFkId(Long editorFkId) {
		this.editorFkId = editorFkId;
	}

	public AuthorBean getEditorFkObj() {
		return editorFkObj;
	}

	public void setEditorFkObj(AuthorBean editorFkObj) {
		this.editorFkObj = editorFkObj;
	}
}
