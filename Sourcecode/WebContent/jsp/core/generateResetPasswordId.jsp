<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<style>
	.generated-id-container {
		display:none;
	}
</style>
	<%@ include file='../layout/header.jsp' %>
	<div class="container container-center  alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
		<div>
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				Password Reset
			</h2>
			<s:form name="frm">
				<div class="form-group row">
					<label class="col-xs-3 control-label">User Login ID</label>
					<div class="col-xs-6">
						<s:textfield id="userName" required="true" cssClass="form-control"/>
					</div>
					<div class="col-md-3">
						<input type="button" onclick="makeRequest();"  class="btn btn-lg btn-primary btn-block" value="Generate">
					</div>
				</div>
				<div class="form-group row generated-id-container alert alert-success">
					<label class="col-xs-3 control-label">Generated ID</label>
					<div class="col-xs-6">
						<label id="generatedId"></label>
					</div>
				</div>
			</s:form>
		</div>
	</div>
	</div>						
	<%@ include file='../layout/footer.jsp' %>


<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/commonUtil.js"></script>
<script type="text/javascript">
	/*
	 * AJAX call starts with this function
	 */
	function makeRequest() {
		var xmlHttpRequest = getXMLHttpRequest();
		xmlHttpRequest.onreadystatechange = getReadyStateHandlerforResetpasswordId(xmlHttpRequest);
		var loginId = document.getElementById("userName").value;
		xmlHttpRequest.open("POST", "generateResetPasswordId.action?requestId=" + requestId+ "&loginId=" + loginId, true);
		xmlHttpRequest.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.send();
	}
	
	function getReadyStateHandlerforResetpasswordId(xmlHttpRequest){
		return function() {
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					var dataRead = JSON.parse(xmlHttpRequest.responseText);
					if(dataRead.resetPasswordId != null){
						$('#generatedId').text(dataRead.resetPasswordId);
						$('.generated-id-container').fadeIn();
					}
					else{
						$("#msg-container").text("User does not exist");
						$("#msg-container").removeClass('hide');
					}
				}
			}
		}
	}
</script>
</html>

