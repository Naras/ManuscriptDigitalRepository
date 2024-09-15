package com.indven.omds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.indven.framework.entity.BaseEntityBean;
import com.indven.portal.administration.entity.UserRoleDetailsBean;

/**
 * Contains the details for digital document
 * @author Neel Borooah
 *
 */

@Entity
@Table(name = "omds_digitaldocument_details")
public class DigitalDocumentDetailsBean extends BaseEntityBean {

	private static final long serialVersionUID = -3482513375989267991L;
	
	@Column(name = "digitalDocumentFkId", insertable = false, updatable = false , nullable = false)
	private Long digitalDocumentFkId;
	
	@OneToOne
	@JoinColumn(name = "digitalDocumentFkId", nullable=true,insertable=true,updatable=true)
	private DigitalDocumentBean digitalDocumentBean;

	@Column(name = "text", insertable = true, updatable = true , nullable = true)
	@Type(type="text")
	private String text;
	
	@Column(name = "attachmentFilePath", insertable = true, updatable = true , nullable = true)
	private String attachmentFilePath;
	
	@Column(name = "userRoleDetailsFkId", insertable = true, updatable = true , nullable = true)
	private Long userRoleDetailsFkId;
	
	@ManyToOne
	@JoinColumn(name = "userRoleDetailsFkId", nullable=true,insertable=false,updatable=false)
	private UserRoleDetailsBean userRoleDetailsBean;
	
	@Column(name = "version", insertable = true, updatable = true , nullable = true)
	private Long version;
	
	@Column(name = "ismax")
	private Short isMax;

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

	public Long getDigitalDocumentFkId() {
		return digitalDocumentFkId;
	}

	public void setDigitalDocumentFkId(Long digitalDocumentFkId) {
		this.digitalDocumentFkId = digitalDocumentFkId;
	}

	public DigitalDocumentBean getDigitalDocumentBean() {
		return digitalDocumentBean;
	}

	public void setDigitalDocumentBean(DigitalDocumentBean digitalDocumentBean) {
		this.digitalDocumentBean = digitalDocumentBean;
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

	public UserRoleDetailsBean getUserRoleDetailsBean() {
		return userRoleDetailsBean;
	}

	public void setUserRoleDetailsBean(UserRoleDetailsBean userRoleDetailsBean) {
		this.userRoleDetailsBean = userRoleDetailsBean;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
