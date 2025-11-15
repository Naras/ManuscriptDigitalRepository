<html>
<%@ include file='homestyle.jsp' %>
<body onload="startApp();"><div id="mainTableContainer" style="min-height: 100px; padding: 2px;">
				<div id="tableContainer" class="metro-nav metro-fix-view">
				</div>
			</div>
	<% 
		if(session.getAttribute("loginData") != null) {
			response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
		}
			
	%>

                <%@ include file='dashboard.jsp' %>
                <%@ include file='dashboardfunctions.jsp' %>
		<script>
			$(document).ready(function() 
				requestId = 1795737537;
				if(requestId == null) {
					$('a').each(function() {
						if($(this).attr('href').indexOf('homePageAction') != -1) {
							window.location = $(this).attr('href');
						}
					});
				}
                                console.log("ready");
                                getNSetData();
			);
			/* setInterval(function() {
				getNSetData();
			}, 120000); */
			</script>
                <%@ include file='dashboardfunctions2.jsp' %>
</body>
<script type="text/javascript">
function startApp() {
	window.location = "${pageContext.request.contextPath}/loadforguest.action?nodeclicked=205";
}
</script>

</html>