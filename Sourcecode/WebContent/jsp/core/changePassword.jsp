<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<html>
<%@ include file='../layout/header.jsp' %>
<div class="container container-center  alert alert-success appTable" style="max-width: 60%;"">
	<div class="form">
		<div style="margin-left: 3%;margin-right: 3%;">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				Change Password
			</h2>
			<s:form action="changePassword" name="frm" onsubmit="return validateForm();">
				<div class="form-group row">
					<label class="col-xs-4 control-label">Current Password</label>
					<div class="col-xs-8">
						<s:password name="oldPassword" id="password" maxlength="50" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">New Password</label>
					<div class="col-xs-8">
						<s:password name="newPassword" id="newPassword1" maxlength="50" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">Re-enter New Password</label>
					<div class="col-xs-8">
						<s:password name="confirmPassword" id="newPassword2" maxlength="50" cssClass="form-control"/>
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
		$('#submitForm').click(function(e) {
			var fieldForRequiredValidator = [ password, newPassword1, newPassword2 ];
			var fieldsForCompareValidator = [newPassword1,newPassword2];
	
			var isCorrectData = true;
			var message = "";
			
			message = requiredFieldValidator(fieldForRequiredValidator);
			if(message.length > 0) {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					if(requiredFieldValidator([fieldForRequiredValidator[i]]).length > 0) {
						$(fieldForRequiredValidator[i]).parent().parent().addClass('has-error');
					}
				}
				isCorrectData = false;
			} else {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().removeClass('has-error');
				}
				
				message += compareStringValidator(fieldsForCompareValidator);
				if(message.length > 0){
					for(var i = 0; i < fieldsForCompareValidator.length; i++) {
						$(fieldsForCompareValidator[i]).parent().parent().addClass('has-error');
					}
					isCorrectData = false;
				} else {
					for(var i = 0; i < fieldsForCompareValidator.length; i++) {
						$(fieldsForCompareValidator[i]).parent().parent().removeClass('has-error');
					}
				} 
			}
			
			if(!isCorrectData) {
				$("#msg-container").text(message);
				$("#msg-container").removeClass('hide');
				e.preventDefault();
			}
		}); 
	</script>
</html>