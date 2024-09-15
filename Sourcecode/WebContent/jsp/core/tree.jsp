    <%@ page import="com.indven.portal.menu.service.MenuInfoServiceImpl" import="com.indven.portal.menu.vo.MenuMasterVO"
    	import="java.util.List" import="java.util.Iterator"
    	import="java.util.Set"
    %>
    <%@ taglib prefix="s" uri="/struts-tags"%>

<style>
li {
	list-style-type:none;
}
img {
	min-height:10;
	min-width:10;
}

.click {
	cursor:pointer;
}

/* Overriding the bootstrap css for checkbox */
#tree .checkbox {
	display:inline;
	white-space:nowrap;
}
</style>

<script src="<%=request.getContextPath() %>/assets/js/triStateTree.js" type="text/javascript"></script>
<script type="text/javascript">
function initializeTree() {
	var path = "<%=request.getContextPath().substring(1) %>";
	setContextPath(path);
	
	var menuFkId = [];
	var allElem = document.getElementsByTagName("img");
	for ( var i = 0; i < allElem.length; i++) {
		if (allElem[i].getAttribute("class") != "checkbox")
			continue;
		if (allElem[i].alt != "checked")
			continue;
		var map = {};
		map["menuFkId"] = allElem[i].id;
		map["roleFkId"] = 1;
		menuFkId.push(map);
	}
	document.getElementById("menuFkId").value = JSON.stringify(menuFkId);
	return true;
}
</script>

	<%!
	public void printNodes(List<MenuMasterVO> list, JspWriter out, String path) {
		try {
			for(Iterator<MenuMasterVO> i = list.iterator(); i.hasNext();) {
				MenuMasterVO node = i.next();
				if(node.getId() != 205L) {
					if(node.getChecked() == null)
						node.setChecked(false);
					if(node.getDefaultStatus().equalsIgnoreCase("1")) {
						if(node.getChild().size() > 0) {
							out.println("<li>" 
							+ "<img id=" + node.getId() + "IMG src='" + path + "/assets/images/menu/tree_minus.png' class='hasChildrenOpen' onclick='toggleVisibility(" + node.getId() + ")'> " 
							+ "<img src='" + path + "/assets/images/menu/checked.gif' class='checkbox' id=" + node.getId() + " alt='checked' >"
							+ "<span class='click' id=" + node.getId() + " onclick='toggleVisibility(this.id)'> " + node.getMenuName() +"</span>"
							+ "<div id=" + node.getId() + "DIV style='display:block'><ul>");
							
							printNodes(node.getChild(), out, path);
						}
						else {
							out.println("<li>"
							+ "<img id=" + node.getId() + "IMG src='" + path + "/assets/images/menu/blank.gif' class='noChildren' onclick='toggleVisibility(" + node.getId() + ")'> "
							+ "<img src='" + path + "/assets/images/menu/checked.gif' class='checkbox' id=" + node.getId() + " alt='checked'>"
							+ "<span class='click' id=" + node.getId() + " onclick='toggleVisibility(this.id)'> " + node.getMenuName() +"</span>"
							+ "<div id=" + node.getId() + "DIV style='display:block'><ul>");
						}
						
					}
					else {
						if(node.getChild().size() > 0) {
							if(node.getChecked() == true)
								out.println("<li>" 
								+ "<img id=" + node.getId() + "IMG src='" + path + "/assets/images/menu/tree_minus.png' class='hasChildrenOpen' onclick='toggleVisibility(" + node.getId() + ")'> " 
								+ "<img src='" + path + "/assets/images/menu/checked.gif' class='checkbox' id=" + node.getId() + " onclick='toggleNodes(this.id)' alt='checked' >"
								+ "<span class='click' id=" + node.getId() + " onclick='toggleVisibility(this.id)'> " + node.getMenuName() +"</span>"
								+ "<div id=" + node.getId() + "DIV style='display:block'><ul>");
							else if(node.getChecked() == false)
								out.println("<li>"
								+ "<img id=" + node.getId() + "IMG src='" + path + "/assets/images/menu/tree_minus.png' class='hasChildrenOpen' onclick='toggleVisibility(" + node.getId() + ")'> "
								+ "<img src='" + path + "/assets/images/menu/unchecked.gif' class='checkbox' id=" + node.getId() + " alt='unchecked' onclick='toggleNodes(this.id)'>"
								+ "<span class='click' id=" + node.getId() + " onclick='toggleVisibility(this.id)'> " + node.getMenuName() +"</span>"
								+ "<div id=" + node.getId() + "DIV style='display:block' ><ul>");

							printNodes(node.getChild(), out, path);
						}
						else {
							
							if(node.getChecked() == true)
								out.println("<li>"
								+ "<img id=" + node.getId() + "IMG src='" + path + "/assets/images/menu/blank.gif' class='noChildren' onclick='toggleVisibility(" + node.getId() + ")'> "
								+ "<img src='" + path + "/assets/images/menu/checked.gif' class='checkbox' id=" + node.getId() + " alt='checked' onclick='toggleNodes(this.id)'>"
								+ "<span class='click' id=" + node.getId() + " onclick='toggleVisibility(this.id)'> " + node.getMenuName() +"</span>"
								+ "<div id=" + node.getId() + "DIV style='display:block'><ul>");
							else if(node.getChecked() == false)
								out.println("<li>"
								+ "<img id=" + node.getId() + "IMG src='" + path + "/assets/images/menu/blank.gif' class='noChildren' onclick='toggleVisibility(" + node.getId() + ")'> "
								+ "<img src='" + path + "/assets/images/menu/unchecked.gif' class='checkbox' id=" + node.getId() + " alt='unchecked' onclick='toggleNodes(this.id)'>" 
								+ "<span class='click' id=" + node.getId() + " onclick='toggleVisibility(this.id)'> " + node.getMenuName() +"</span>" 
								+ "<div id=" + node.getId() + "DIV style='display:block' ><ul>");
						}
					}
					
					
					out.println("</ul></div>");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	%>
	<div>
		<ul id="tree">
			<%
			MenuMasterVO menu1 = (MenuMasterVO) request.getAttribute("menu");
			printNodes(menu1.getChild(), out, request.getContextPath());
	    	%>
    	</ul>
		<s:hidden name="menuFkId" id="menuFkId" />
	</div>
</html>