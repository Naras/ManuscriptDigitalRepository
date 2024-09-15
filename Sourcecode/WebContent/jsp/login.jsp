    <%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@include file='layout/header.jsp' %>
<%-- <s:if test="#session.loginData == null">
		<%@include file='layout/staticheader.jsp' %>
</s:if> --%>
<head>
<title>FRLHT</title>
</head>
<body>
   
	<% 
		if(session.getAttribute("loginData") != null) {
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		}
			
	%>
	<div class="login-page">
		<div class="container container-center">
			<%@ include file='messagecontainer.jsp' %>
			<s:form role="form" action="loginaction" cssClass="form-signin">
				<h2 class="form-signin-heading">Please sign in</h2>
				<s:textfield name="userInfoVO.loginName" maxlength="50" cssClass="form-control" autofocus="" required="true" placeholder="Email address"/>
				<s:password placeholder="Password" name="userInfoVO.passWord" maxlength="50" cssClass="form-control" required="true" />
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				<a href="<%=request.getContextPath()%>/showResetPasswordPage.action">Forgot Password</a>
			</s:form>
		</div>
	</div>

</body>
<%@include file='layout/footer.jsp' %>
</html>