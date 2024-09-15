<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<%@ include file='../layout/header.jsp'%>
<body>
	<%@ include file='../manuscript/addDerivedWork.jsp'%>

	<div class="form-group row buttons container-center">
		<input type="hidden" id="currentRoleId"
			value="${sessionScope.currentRole}" />
		
		<s:if test="isVisibleSave != 0">
			<s:if test="#session.currentRole != 5 ">
				<div class="col-md-4">
					<input type="button" class="btn btn-lg btn-primary btn-block" value="Reject" id="rejectWfl" onclick="rejectWfl();">
				</div>
			</s:if>
			<div class="col-md-4">
				<input type="button" class="btn btn-lg btn-primary btn-block"
					id="authorizeWfl">
			</div>
			<!-- <div class="col-md-4">
				<input type="button" class="btn btn-lg btn-primary btn-block"
					id="seclabelreview" value="2nd Label Review">
			</div> -->
		</s:if>
		<div class="col-md-4">
			<s:url id="cancel" action="goToHomePage.action">
			</s:url>
			<s:a href="%{cancel}" cssClass="assignRecord">
				<button type="button" class="btn btn-lg btn-danger btn-block ">Cancel</button>
			</s:a>
		</div>
	</div>

	<%@ include file='../layout/footer.jsp'%>
	<script type="text/javascript">
		$('input[type=button]').on('click', function(e) {
			if ($(this).attr('id') == "authorizeWfl") {
				bootbox.confirm("Have you saved your changes?", function(result) {
					if(result) {
						var action = $('#derivedWorkForm').attr('action');
						action += "?value=ACTION_TYPE_FORWARD";
						$('#derivedWorkForm').attr('action', action);
						$('#derivedWorkForm').submit();
					} else if($(this).attr('id') == "saveTranscription") {
						addDerivedWork();
					}
				})
			}
		});
		
		function addDerivedWork() {
			
		};
		
		$(document).ready(function() {
			var roleId = document.getElementById("currentRoleId").value;
			var isPresentReviewer = $('#isPresentReviewer2').val();
			if (roleId == 5) {
				$("#authorizeWfl").attr('value', 'Submit to Review');
			} else if (roleId == 6 || roleId == 8) {
				$("#authorizeWfl").attr('value', 'Submit to Publish');
			} else if (roleId == 7) {
				$("#authorizeWfl").attr('value', 'Publish');
			}
			if(roleId == 6 && isPresentReviewer==1){
				$("#authorizeWfl").attr('value', 'Submit to Verify');
			}
		});
		
		function branchWflToUser() {
			bootbox.confirm("Have you saved your changes?", function(result) {
				if(result) {
					<%
						String requestid1 = (String) request.getAttribute("requestId");
					%>
					var referencePrjId = $("#manuscriptId").val();
					window.location = '${pageContext.request.contextPath}/branchWfl.action?requestId='+<%=requestid1%>+'&refPrjId='+referencePrjId;
				}
			});
		};
		
		function rejectWfl() {
			
			bootbox.confirm("Have you saved your changes?", function(result) {
				if(result) {
					var referencePrjId = $("#manuscriptId").val();
					window.location = '${pageContext.request.contextPath}/rejectwfl.action?requestId='+<%=requestid1%>+'&refPrjId='+referencePrjId;
				}
			});
			<%-- var conf = bootbox.confirm("Have you saved your changes?");
			if(conf) {
				var referencePrjId = $("#manuscriptId").val();
				window.location = '${pageContext.request.contextPath}/rejectwfl.action?requestId='+<%=requestid1%>+'&refPrjId='+referencePrjId;
			} --%>
		};
	</script>
</body>
</html>