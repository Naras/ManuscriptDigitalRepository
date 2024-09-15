<%-- <%@ page language="java" contentType="text/html;"
    pageEncoding="utf-8"%> --%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html;">
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible"> -->
<title>Old OMDS Web App!</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/core.css">
</head>
<body>
<div style="height: 100px; width: 23%; padding-top: 10px; float:left;">
<img src="<%=request.getContextPath()%>/assets/images/frlht_logo_80.png" alt="Image" style="width: auto; height: 80px">
</div>
<div style="margin-left: 23%;margin-right: 23%; height: 105px;width: 54%;">
<!-- <p style="font-size: x-large; font-style: oblique; padding-top: 2%; color: green;">Digital Repository of Medical Manuscripts and Books of Karnataka</p>
<p style="color: green;">Sponsored by Department of Ayush(Karnataka)
					           Department of Health and Family Welfare
					           Government of Karnataka
					          (As per recommendation of the Karnataka Knowledge Commission)</p><br> -->
 <img src="<%=request.getContextPath()%>/assets/images/Header_version2.png" alt="Image"style="height: 100px;width: 100%;">
 
</div>
		
		
				<div class="form-group row"  style="float: right;margin-top: -100px ; margin-right: 25px;width: 13%;text-align: right;">
					<div class="col-xs-11" style="margin-bottom: 6px">
						<img src="<%=request.getContextPath()%>/assets/images/slider/welfare.png" style="float: left;"> 
 						<img src="<%=request.getContextPath()%>/assets/images/slider/Ayush.png" style="float: left;">
					</div>
					<s:if test="#session.loginData != null">
						<div class="col-xs-12" style="margin-top: -2%;">
							<span style="color : #3C763D;float: left;">Welcome:<s:label name="#session.currentUser"></s:label></span>
							<span style="color : #3C763D;float: left; margin-top: -2%;">Role:<s:label name="#session.currentRoleName"/>
							</span>
						</div>
					</s:if>
				</div>
		
</body>
<script type="text/javascript">
function makeRequest() {
	document.roleIdForm.submit();
}
</script>
</html>