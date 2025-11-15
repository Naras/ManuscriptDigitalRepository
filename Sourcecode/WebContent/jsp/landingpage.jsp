<html>

<body onload="startApp();">
	<% 
		if(session.getAttribute("loginData") != null) {
			response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
		}
			
	%>
</body>
<script type="text/javascript">
function startApp() {
	window.location = "${pageContext.request.contextPath}/loadforguest.action?nodeclicked=205";
}
</script>

</html>