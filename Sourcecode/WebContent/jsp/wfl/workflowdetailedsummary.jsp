<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>

<s:set name="theme" value="'simple'" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>	
		<meta content="IE=edge" http-equiv="X-UA-Compatible">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<meta content="" name="description">
		<meta content="" name="author">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/core.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
	</head>

<body>
		
		<%@ include file='../layout/header.jsp' %>		
		<div class="container container-center">
		<%@ include file='../messagecontainer.jsp' %>
		<s:form name="roleIdForm" action="changemenuforrole">
			<div class="form-group row">
				<div class="col-xs-6">
					<s:select list="#session.userRoles" value="#session.currentRole" required="true" id="roleID" listKey="id" onchange="makeRequest();" listValue="name" cssClass="form-control" name="userRoleId" />
				</div>
			</div>
		</s:form>
		<div class="imageContainer">
			<br><br><br>
			<img src="<%=request.getContextPath()%>/assets/images/carousel/img1.png" alt="Image">
		</div>
		<br><br><br>
					<table class="table">
						<thead>
							<th><b>Description</b></th>
							<th><b>Assigned</b></th>
						</thead>
						<tbody>
							<s:iterator id="entityVO" value="%{entityVO}">
								<s:iterator>
									<tr>
										<s:hidden value="processId" />
										<td>
											<s:url id="detailsURL"
												action="getWflDetailedList.action">
												<s:param name="id" value="%{processId}"></s:param>
											</s:url>
											<s:a href="%{detailsURL}">
												<s:property value="processDescription"/>
											</s:a>
										</td>
										<td><s:property value="cntRole" /></td>
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>					
					</table>
	</div>
	<%@ include file='../layout/footer.jsp' %>	
	
</body>
</html>