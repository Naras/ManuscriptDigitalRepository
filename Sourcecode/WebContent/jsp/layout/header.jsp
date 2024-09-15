<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
	<%@ page import="java.util.ArrayList" import="java.util.List" import="com.indven.portal.menu.service.MenuInfoServiceImpl"
		import="com.indven.portal.menu.vo.MenuMasterVO"  import="com.indven.portal.menu.controller.MenuInfoAction" 
		import="java.util.Iterator" %>
<s:set name="theme" value="'simple'" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
.container-center1 {
    margin-left: auto;
    margin-right: auto;
    max-width: 65%;
    width: auto;
}
</style>
<head>
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="author">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/core.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRLHT</title>
<s:head/>
<sx:head/>
</head>
<body>

<%!
	/**
	  * The header menus are printed recursively through this method
	  */
	  
	//Checks if children of node are checked
	public boolean areChildrenChecked(List<MenuMasterVO> list) {
		
		boolean flag = false;
		for(Iterator<MenuMasterVO> i = list.iterator(); i.hasNext();) {
			MenuMasterVO node = i.next();
			if(node.getChecked() == null)
				node.setChecked(false);
			if(node.getChild().size() > 0) {
				if(node.getChecked() == true) {
					return true;
				}
				flag = areChildrenChecked(node.getChild());
			}
			else {
				if(node.getChecked() == true) {
					return true;
				}
			}
		}
		return flag;
		
	}
	
	//Prints the header menu recursively
	public void printMenus(List<MenuMasterVO> list, JspWriter out, String path) {
		Long selectedMenuId = (Long)ServletActionContext.getRequest().getSession().getAttribute("nodeclicked");
		try {
			for(Iterator<MenuMasterVO> i = list.iterator(); i.hasNext();) {
				MenuMasterVO node = (MenuMasterVO) i.next();
				
				Long selectedNodeId = node.getId();
				if(node.getParentId() != 1L) {
					selectedNodeId = node.getParentId();
				}
				
				boolean isMenuSelected = false;
				String extraClass = "";
				if(selectedNodeId.equals(selectedMenuId)) {
					isMenuSelected = true;
					extraClass = "selectedMenu";
				}
				
				Long parentNode = node.getParentId();
				if(parentNode == 1L) {
					parentNode = node.getId();
				}
				
				String menuLink = "";
				if(!node.getMenuLink().equals(""))
					if(node.getMenuLink().contains("?")) {
						menuLink = path + node.getMenuLink() + "&requestId=" + node.getRequestId()+"&nodeclicked="+parentNode;	
					} else {
						menuLink = path + node.getMenuLink() + "?requestId=" + node.getRequestId()+"&nodeclicked="+parentNode;	
					}
				
				if(node.getChecked() == null)
					node.setChecked(false);
				if(node.getChild().size() > 0) {
					//The node has children
					boolean flag2 = false;
					if(node.getChecked() == true) {
						//The node is checked
						out.println("<li>"
								+ "<a class='dropdown-toggle "+extraClass+"' data-toggle='dropdown' href='" + menuLink + "'>" + node.getMenuName()
								+ "<b class='caret'></b></a>" 
								+ "<ul class='dropdown-menu'>");
					}
					else if(areChildrenChecked(node.getChild()) == true) {
						//The children node contain atleast one checked node
						out.println("<li>"
								+ "<a class='dropdown-toggle "+extraClass+"' data-toggle='dropdown' href='" + menuLink + "'>" + node.getMenuName()
								+ "<b class='caret'></b></a>"
								+ "<ul class='dropdown-menu'>");
					}
					else 
						continue;
					
					printMenus(node.getChild(), out, path); //Call function again on child nodes
				}
				else {
					//Node has no children
					if(node.getChecked() == true) {
						//Node is checked
						out.println("<li>"
								+ "<a class='"+extraClass+"' href='" + menuLink + "'>" + node.getMenuName() + "</a>" 
								+ "");
					}
				}
			}
			out.println("</ul>");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

%>
<%@ include file='../layout/topHeader.jsp' %>
	<!-- <div id="wrap"  style="background-image: url('/WEB-INF/assets/images/slider/Ayurveda_Bk_Img.png') ; background-repeat: no-repeat;";> -->
	<div id="wrap">
	
	<div id="leftDiv" style="z-index:-1 ;position: absolute;overflow: auto;">
		<img alt="" src="/WEB-INF/assets/images/theme/bundle-2.png" style="width: 336px;height: 408px;">
	</div>
	
	<div id="rightDiv" style="z-index:-1 ;position: absolute;">
		<img alt="" src="/WEB-INF/assets/images/theme/top_right_second.png">
	</div>
	
	 <div id="middleDiv" style="z-index:-2 ;position: absolute;">
		<img alt="" src="/WEB-INF/assets/images/theme/top_left.png">
	</div> 

	<div class="navbar navbar-inverse navbar-fixed-top" style="position: relative;" role="navigation">
		<div class="container container-center1" id="divul2">
			
			<div class="collapse navbar-collapse" id="divul" style="text-align: center !important;">
				<ul class="nav navbar-nav" id="idul" style="margin-left: auto;margin-right: auto;">  <!-- ul is closed within recursive loop -->
					<%-- <li>
						<s:if test="#session.nodeclicked == 201">
							<a class="navbar-brand selectedMenu" href="gofrlhthome.action?nodeclicked=201" style="font-size: 14px">Home</a>
						</s:if><s:else>
						<a class="navbar-brand" href="gofrlhthome.action?nodeclicked=201" style="font-size: 14px">Home</a></s:else>
					</li>
					<li>
						<s:if test="#session.nodeclicked == 202">
							<a class="navbar-brand selectedMenu" href="aboutpageaction.action?nodeclicked=202" style="font-size: 14px">About Us</a>
						</s:if> <s:else>
							<a class="navbar-brand" href="aboutpageaction.action?nodeclicked=202" style="font-size: 14px">About Us</a>
						</s:else>
					</li>
					<li>
						<s:if test="#session.nodeclicked == 203">
							<a class="navbar-brand selectedMenu" href="aboutpageaction.action?nodeclicked=203" style="font-size: 14px">About S/W</a>
						</s:if> <s:else>
							<a class="navbar-brand" href="aboutpageaction.action?nodeclicked=203" style="font-size: 14px">About S/W</a>
						</s:else>
					
					</li>
					<li>
					
						<s:if test="#session.nodeclicked == 204">
							<a class="navbar-brand selectedMenu" href="faqpageaction.action?nodeclicked=204" style="font-size: 14px">FAQ</a>
						</s:if> <s:else>
							<a class="navbar-brand" href="faqpageaction.action?nodeclicked=204" style="font-size: 14px">FAQ</a>
						</s:else>
					</li> --%>
					
					<% 
						//Gets the header menu through the session and prints it recursively
						MenuMasterVO menu = (MenuMasterVO) session.getAttribute("headerMenu");
						printMenus(menu.getChild(), out, request.getContextPath());
					%>
				</ul>
			</div>
		</div>
	</div>
	
	<%
		
		String requestId = (String) request.getAttribute("requestId");
		String uri = request.getRequestURI();
		//Checks if user entered url directly
		if(!uri.equalsIgnoreCase(request.getContextPath() + "/jsp/login.jsp") && !uri.equalsIgnoreCase(request.getContextPath() + "/")){
			if(requestId == null && request.getHeader("referer") == null && !uri.equalsIgnoreCase(request.getContextPath() + "/jsp/home.jsp")) {
				if(session.getAttribute("loginData") == null) {
					response.sendRedirect(request.getContextPath());
				}
				else {
					response.sendRedirect(request.getContextPath() + "/homePageAction.action");
				}
			} 
		} 
	
	%>
	
<script type="text/javascript">
	var requestId = "";
	window.onload = function() {
		requestId = "<%= requestId %>";
		insertHiddenField();
		appendToLinks();
	}
	
	function insertHiddenField() {
		// Putting the request attribute into the a hidden field
		formCollection = document.getElementsByTagName("form");
		for(var i = 0; i < formCollection.length; i++) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("id", "requestId");
			hiddenField.setAttribute("name", "requestId");
			hiddenField.setAttribute("value", requestId);
		    formCollection[i].appendChild(hiddenField);
		}
	}
	
	function appendToLinks() {
		//Appending the request as parameter to every link
		linkCollection = document.getElementsByTagName("a");
		for(var i = 0; i < linkCollection.length; i++) {
			var link = linkCollection[i].href;
			if(link.indexOf('#') != -1)
				continue;
			var kvp = link.split('&');
			var flag = true;
		    for(var j = 0; j < kvp.length; j++) {
		        var x = kvp[j].split('=');
		        if (x[0].indexOf("requestId") != -1) {
		        	flag = false;
		        	break;
		        }
		    }
		    
		    if(flag) {
		    	if(link.indexOf("?") != -1) {
		    		linkCollection[i].href = link + "&requestId=" + requestId;
		    	} else {
		    		linkCollection[i].href = link + "?requestId=" + requestId;
		    	}
		    }
		}
	}
	
	
	
	$(document).ready(function() {
		var x = $("#idul").width();
		$("#divul").width(x+160);
		$("#divul2").width(x);
		
		var xx = screen.height;
		
		 var d = document.getElementById("leftDiv");
		  d.style.position = "fixed";
		  d.style.left = 0+'px';
		  d.style.top = (xx-500)+'px';
		  
		  
		var width = screen.width;	
		var ele1 = document.getElementById("rightDiv");
		ele1.style.position = "fixed";
		ele1.style.left = (width-370)+'px';
		ele1.style.top = 120+'px';
		
	
		var ele2 = document.getElementById("middleDiv");
		ele2.style.position = "fixed";
		ele2.style.left = 0;
		ele2.style.top =  (xx-900)+'px';
		 
		
		var windw = this;

		$.fn.followTo = function ( pos ) {
		    var $this = this,
		        $window = $(windw);
		    
		    $window.scroll(function(e){
		        if ($window.scrollTop() > pos) {
		            $this.css({
		            	 position: 'fixed',
			                top: 0+'px'
		            });
		        } else {
		            $this.css({
		                
		                position: 'fixed',
		                top: 120+'px'
		            });
		        }
		    });
		};

		$('#rightDiv').followTo(120);
		
	});
</script>


</html>