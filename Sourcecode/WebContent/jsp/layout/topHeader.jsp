<%-- <%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%> --%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html;">
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible"> -->
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/core.css">
</head>
<body>
<div class="top-header" style="height: 80px;">
<div style=" width: 15%; height: 100%; float:left;">
<img src="<%=request.getContextPath()%>/assets/images/logo_sf.png" alt="Image" style="width: 190px; height: 100%;">
</div>
<div style="margin-left: 23%;margin-right: 18%; height: 95%;width: 50%;">
<!-- <p style="font-size: x-large; font-style: oblique; padding-top: 2%; color: green;">Digital Repository of Medical Manuscripts and Books of Karnataka</p>
<p style="color: green;">Sponsored by Department of Ayush(Karnataka)
					           Department of Health and Family Welfare
					           Government of Karnataka
					          (As per recommendation of the Karnataka Knowledge Commission)</p><br> -->
 <%-- <img src="<%=request.getContextPath()%>/assets/images/Header_version2.png" alt="Image"style="height: 100px;width: 100%;"> --%>
 
</div>
		
		
				<div class="form-group row"  style="float: right;margin-top: -30px; margin-right: 25px;width: 35%;padding: 0px;">
					<div class="col-md-12"<%-- style="margin-top: -2%;"--%>>
					<s:if test="#session.loginData != null">
						<div class="col-md-6">
								<span style="float: left;">Welcome:<s:label name="#session.currentUser"></s:label></span>
						</div>
								 <!-- <span style="float: left; margin-top: -2%;">Role:<s:label name="#session.currentRoleName"/>
								</span>  -->
						<div class="col-md-6">
								 <s:if test="#session.userRoles != null && #session.userRoles.size > 1">
									<s:form name="roleIdForm" action="changemenuforrole">
									<s:hidden name="currentRoleName" id="currentRoleName"></s:hidden>
											 <span style="float: left;">Logged in as:</span>
											<div class="col-md-7" style="padding:0px;">
												<s:select list="#session.userRoles"  value="#session.currentRole" required="true" id="roleID" listKey="id" onchange="makeRequest();" listValue="name" cssClass="form-control" name="userRoleId" cssStyle="height:25px;padding:0px;"/>
											</div>
									</s:form> 
								</s:if>
						
								<s:else>
									<span class="control-label" id="userrole" style="/*margin-top:-20px;*/"></span>
									<s:select list="#session.userRoles" cssStyle="visibility:collapse;" value="#session.currentRole" required="true" id="roleID" listKey="id" onchange="makeRequest();" listValue="name" cssClass="form-control" name="userRoleId" />
									<script>
										var role = $('select[name=userRoleId] option:selected').text();
										document.getElementById('userrole').innerHTML = 'Logged in as : '+ role;
									</script>
								</s:else>
						</div>
					</s:if>
					</div>
					
				</div>
</div>
		
</body>
<script type="text/javascript">
function makeRequest() {
	document.roleIdForm.submit();
}
</script>
</html>