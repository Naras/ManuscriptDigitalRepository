<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file='../layout/header.jsp' %>
	<div class="container container-center  alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
			<div style="margin-left: 3%;margin-right: 3%;">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
                        <$
			<h2 class="form-heading">
				Reset Password
			</h2>
			<s:form name="frm" onsubmit="return validateForm();">
				<div class="form-group row">
					<label class="col-xs-4 control-label">User Login ID</label>
					<div class="col-xs-8">
						<s:textfield id="userName" name="userInfoVO.loginName" required="true" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">Generated ID</label>
					<div class="col-xs-8">
						<s:textfield id="generatedId" name="userInfoVO.resetPasswordId" required="true" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">New Password</label>
					<div class="col-xs-8">
						<s:password id="newpassword" name="userInfoVO.passWord" required="true" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">Re-enter New Password</label>
					<div class="col-xs-8">
						<s:password id="confirmpassword" required="true" cssClass="form-control"/>
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
	<script type="text/javascript">
		function validateForm() {
			var passWord = document.getElementById("newpassword");
			var cnfpassWord = document.getElementById("confirmpassword");
			var loginId = document.getElementById("userName");
			var GeneratedId = document.getElementById("generatedId");
			var fieldForRequiredValidator;
			var emailValidation = [ loginId ];
			var fieldsForCompareValidator = [passWord,cnfpassWord];
			
			fieldForRequiredValidator = [loginId,GeneratedId,passWord,cnfpassWord];
			var isCorrectData = true;
			var message = "";
			
			message += requiredFieldValidator(fieldForRequiredValidator);
			if (message.length > 0) {
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
				
				message += emailValidator(emailValidation);
				if (message.length > 0) {
					for(var i = 0; i < emailValidation.length; i++) {
						if(emailValidator([emailValidation[i]]).length > 0) {
							$(emailValidation[i]).parent().parent().addClass('has-error');
						}
					}
					isCorrectData = false;
				} else {
					for(var i = 0; i < emailValidation.length; i++) {
						$(emailValidation[i]).parent().parent().removeClass('has-error');
					}
					
					message += compareStringValidator(fieldsForCompareValidator);
				 	if(message.length > 0) {
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
			}
			
			if (!isCorrectData) {
				$("#msg-container").text(message);
				$("#msg-container").removeClass('hide');
				return false;	
			}
			else{alert("before submit resetPasswordAction.action");
				var form = document.forms["frm"];
				form.action = "resetPasswordAction.action";
				form.submit();
			}
	
		}
	</script>
	
</html>