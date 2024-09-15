<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<html>
<%@ include file='../layout/header.jsp' %>
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
		<div style="margin-left: 3%;margin-right: 3%;">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				Script
			</h2>
			<s:form action="savescript" name="frm" onsubmit="return validateForm()">
				<div class="form-group row">
					<label class="col-xs-6 control-label">Script Name</label>
					<div class="col-xs-6">
						<s:hidden name="script.id"/>
						<s:textfield name="script.name" id="Sname" required="true" maxlength="250" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-6 control-label">Unicode Point</label>
					<div class="col-xs-6">
						<s:textfield name="script.unicodePoint" id="unicodePoint" maxlength="50" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group row buttons">
					<div class="col-md-3">
					</div>
					<div class="col-md-6">
						<input type="submit" class="btn btn-lg btn-primary btn-block" value="submit" id="submitForm">
					</div>
					<div class="col-md-3">
					</div>
				</div>
			</s:form>
		</div>
	</div>
	</div>					
	<%@ include file='../layout/footer.jsp' %>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/Validator.js"></script>
	<script>
		function validateForm() {
			var fieldForRequiredValidator = [Sname];
	
			var isCorrectData = true;
			var message = requiredFieldValidator(fieldForRequiredValidator);
			if (message.length > 0) {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().addClass('has-error');
				}
				isCorrectData = false;
			} else {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().removeClass('has-error');
				}
			}
			
			if(!isCorrectData) {
				$("#msg-container").text(message);
				$("#msg-container").removeClass('hide');
				return false;	
			}
			
			return isCorrectData;
		}
	</script>
</html>
