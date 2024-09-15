package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "omds_nmm_details")
public class NMMDetailsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5666632012588091349L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "height", insertable = true, updatable = true , nullable = true)
	private String height;
	
	@Column(name = "width", insertable = true, updatable = true , nullable = true)
	private String width;
	
	@Column(name = "createdDate", insertable = true, updatable = true , nullable = true)
	private String createdDate;
	
	@Column(name = "digitisedDate", insertable = true, updatable = true , nullable = true)
	private String digitisedDate;
	
	@Column(name = "cameraMake", insertable = true, updatable = true , nullable = true)
	private String cameraMake;
	
	@Column(name = "cameraModel", insertable = true, updatable = true , nullable = true)
	private String cameraModel;
	
	@Column(name = "xResolution", insertable = true, updatable = true , nullable = true)
	private String xResolution;
	
	@Column(name = "yResolution", insertable = true, updatable = true , nullable = true)
	private String yResolution;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDigitisedDate() {
		return digitisedDate;
	}

	public void setDigitisedDate(String digitisedDate) {
		this.digitisedDate = digitisedDate;
	}

	public String getCameraMake() {
		return cameraMake;
	}

	public void setCameraMake(String cameraMake) {
		this.cameraMake = cameraMake;
	}

	public String getCameraModel() {
		return cameraModel;
	}

	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

	public String getXResolution() {
		return xResolution;
	}

	public void setXResolution(String xResolution) {
		this.xResolution = xResolution;
	}

	public String getYResolution() {
		return yResolution;
	}

	public void setYResolution(String yResolution) {
		this.yResolution = yResolution;
	}
	
}
