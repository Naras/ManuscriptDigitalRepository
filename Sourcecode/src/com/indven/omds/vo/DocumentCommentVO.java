/**
 * 
 */
package com.indven.omds.vo;


/**
 * @author Deba Prasad
 *
 */
public class DocumentCommentVO {

	private Long id;
	private String comment;
	private String commentedBy;
	private String commentedOn ;
	private Long frameId;
	private Long manuscriptId;
	
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
}
