package com.indven.portal.menu.vo;

import java.util.ArrayList;
import java.util.List;

import com.indven.framework.vo.IndvenResultVO;

public class MenuMasterVO extends IndvenResultVO{
	
	private Long id;
	private Long parentId;
	private String menuName;
	private String menuLink;
	private String leftPanelLink;	
	private Integer menuOrder;
	private String requestId;
	private String statusMsg;
	private String shortKey;
	private Integer menuLevel;
	private String defaultStatus;
	private Boolean checked;
	private List<MenuMasterVO> child = new ArrayList<MenuMasterVO>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	public String getLeftPanelLink() {
		return leftPanelLink;
	}
	public void setLeftPanelLink(String leftPanelLink) {
		this.leftPanelLink = leftPanelLink;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public String getShortKey() {
		return shortKey;
	}
	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getDefaultStatus() {
		return defaultStatus;
	}
	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public final List<MenuMasterVO> getChild() {
		return child;
	}
	public final void setChild(List<MenuMasterVO> child) {
		this.child = child;
	}
	public String toString() {
            String result="";
//            result += "id:" + this.id + " parent id:" + this.parentId + " menu name:" + this.menuName + " menu link :" + this.menuLink
//                  + " left link :" + this.leftPanelLink + " menu order:" + this.menuOrder + " status message:" + this.statusMsg 
//                  + " request id:" + this.requestId + " default status:" + this.defaultStatus + " checked:" + this.checked
//                   + "child:" ;
            for (MenuMasterVO item : child) {
            result += "\nid:" + item.getId() 
//                    + " parent id:" + item.getParentId()
                    + " menu name:" + item.getMenuName() 
                    + " menu link :" + item.getMenuLink()
//                  + " left link :" + item.getLeftPanelLink() 
                    + " menu order:" + item.getMenuOrder() 
                    + " level:" + item.getMenuLevel() 
//                    + " status message:" + item.getStatusMsg() 
                  + " request id:" + item.getRequestId() 
                    + " default status:" + item.getDefaultStatus()
                    + " checked:" + item.getChecked()
                    ;
            }
            return result;
        }
}
