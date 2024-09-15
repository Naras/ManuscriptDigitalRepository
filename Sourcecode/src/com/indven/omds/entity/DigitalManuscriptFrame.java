/**
 * 
 */
package com.indven.omds.entity;

import java.io.Serializable;
import java.util.List;

import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.math.NumberUtils;

import com.indven.framework.util.IndvenApplicationConstants;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_digital_manuscript_frame")
public class DigitalManuscriptFrame implements Serializable,Comparator<DigitalManuscriptFrame>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "filePath", insertable = true, updatable = false , nullable = false)
	private String filePath;
	
	@Column(name = "digitalManuscriptFkId", insertable = false, updatable = false , nullable = false)
	private Long digitalManuscriptFkId;
	
	@ManyToOne
	@JoinColumn(name = "digitalManuscriptFkId", nullable=true,insertable=true,updatable=true)
	private DigitalManuscriptBean digitalManuscriptFkObj;
	
	@Column(updatable = false , insertable = false)
	private DigitalDocumentBean digitalDocumentBean;
	
	@Column(name = "islast")
	private Short isLast;
	
	@OneToMany(mappedBy = "frameFkObj", cascade = CascadeType.ALL)
	private List<DocumentCommentBean> frameCommentsList;

	@Transient
	private String translatedText;
	
	/**
	 * @return the translatedText
	 */
	public final String getTranslatedText() {
		return translatedText;
	}

	/**
	 * @param translatedText the translatedText to set
	 */
	public final void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

	/**
	 * @return the frameCommentsList
	 */
	public final List<DocumentCommentBean> getFrameCommentsList() {
		return frameCommentsList;
	}

	/**
	 * @param frameCommentsList the frameCommentsList to set
	 */
	public final void setFrameCommentsList(
			List<DocumentCommentBean> frameCommentsList) {
		this.frameCommentsList = frameCommentsList;
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @return the isLast
	 */
	public final Short getIsLast() {
		return isLast;
	}

	/**
	 * @param isLast the isLast to set
	 */
	public final void setIsLast(Short isLast) {
		this.isLast = isLast;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the filePath
	 */
	public final String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public final void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getDigitalManuscriptFkId() {
		return digitalManuscriptFkId;
	}

	public void setDigitalManuscriptFkId(Long digitalManuscriptFkId) {
		this.digitalManuscriptFkId = digitalManuscriptFkId;
	}

	public DigitalManuscriptBean getDigitalManuscriptFkObj() {
		return digitalManuscriptFkObj;
	}

	public void setDigitalManuscriptFkObj(DigitalManuscriptBean digitalManuscriptFkObj) {
		this.digitalManuscriptFkObj = digitalManuscriptFkObj;
	}

	public DigitalDocumentBean getDigitalDocumentBean() {
		return digitalDocumentBean;
	}

	public void setDigitalDocumentBean(DigitalDocumentBean digitalDocumentBean) {
		this.digitalDocumentBean = digitalDocumentBean;
	}
	@Override
	public int compare(DigitalManuscriptFrame frame1, DigitalManuscriptFrame frame2) {
		String compValue1=frame2.getFilePath().split("\\.")[0].substring( frame2.getFilePath().split("\\.")[0].length() - IndvenApplicationConstants.IMAGE_NAME_SIZE);
		String compValue2=frame1.getFilePath().split("\\.")[0].substring( frame1.getFilePath().split("\\.")[0].length() - IndvenApplicationConstants.IMAGE_NAME_SIZE);
		
		if(!(NumberUtils.isNumber(compValue1.substring(compValue1.length() - 1)))) {
			compValue1=frame2.getFilePath().split("\\.")[0].substring( frame2.getFilePath().split("\\.")[0].length() - 5);
		}
		
		if(!(NumberUtils.isNumber(compValue2.substring(compValue2.length() - 1)))) {
			compValue2 = frame1.getFilePath().split("\\.")[0].substring( frame1.getFilePath().split("\\.")[0].length() - 5);
		}
		
		return (compValue2.compareTo(compValue1));
		//return o1.salary-o2.salary;
	}

}
