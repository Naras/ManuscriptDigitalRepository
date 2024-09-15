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
import javax.persistence.Table;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_document_comment")
public class DocumentCommentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="commentedby")
	private String commentedBy;
	
	@Column(name="commentedon")
	private String commentedOn ;

	@Column(name="framefkid" , insertable = true , nullable = true)
	private Long frameId;
	
	@Column(name="digitalmanuscriptid" , insertable = true , nullable = true)
	private Long manuscriptId;
	
	@ManyToOne
	@JoinColumn(name = "framefkid", nullable=true,insertable=false,updatable=false)
	private DigitalManuscriptFrame frameFkObj;
	
	/**
	 * @return the manuscriptId
	 */
	public final Long getManuscriptId() {
		return manuscriptId;
	}

	/**
	 * @param manuscriptId the manuscriptId to set
	 */
	public final void setManuscriptId(Long manuscriptId) {
		this.manuscriptId = manuscriptId;
	}

	/**
	 * @return the frameId
	 */
	public final Long getFrameId() {
		return frameId;
	}

	/**
	 * @param frameId the frameId to set
	 */
	public final void setFrameId(Long frameId) {
		this.frameId = frameId;
	}

	/**
	 * @return the frameFkObj
	 */
	public final DigitalManuscriptFrame getFrameFkObj() {
		return frameFkObj;
	}

	/**
	 * @param frameFkObj the frameFkObj to set
	 */
	public final void setFrameFkObj(DigitalManuscriptFrame frameFkObj) {
		this.frameFkObj = frameFkObj;
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
	 * @return the comment
	 */
	public final String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public final void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the commentedBy
	 */
	public final String getCommentedBy() {
		return commentedBy;
	}

	/**
	 * @param commentedBy the commentedBy to set
	 */
	public final void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}

	/**
	 * @return the commentedOn
	 */
	public final String getCommentedOn() {
		return commentedOn;
	}

	/**
	 * @param commentedOn the commentedOn to set
	 */
	public final void setCommentedOn(String commentedOn) {
		this.commentedOn = commentedOn;
	}
	
}
