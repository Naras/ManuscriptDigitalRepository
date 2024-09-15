package com.indven.omds.vo;

import com.indven.framework.vo.IndvenResultVO;

public class NMMDetailsVO extends IndvenResultVO {
	
	private Long id;
	private String height;
	private String width;
	private String createdDate;
	private String digitisedDate;
	private String cameraMake;
	private String cameraModel;
	private String xResolution;
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
