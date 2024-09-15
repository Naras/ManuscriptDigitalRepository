<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<%@ include file='../layout/header.jsp' %>
	<div class="container container-center  alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
		<div style="margin-left: 3%;margin-right: 3%;">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				<s:if test="employeeMasterVO != null && employeeMasterVO.id > 0">
					Update User
				</s:if>
				<s:else>
					Add User
				</s:else>
			</h2>
			<s:form action="adduseraction" role="form" onsubmit="return validateForm();">
				<s:hidden name="employeeMasterVO.id"></s:hidden>
				<s:hidden name="employeeMasterVO.rowVersion"></s:hidden>
				<s:hidden name="employeeMasterVO.createdDate"></s:hidden>
				<s:hidden name="employeeMasterVO.createdBy"></s:hidden>
				<s:hidden name="employeeMasterVO.gender" id="genderValue"></s:hidden>
				<div class="form-group row manuscript-specific">
					<label class="col-xs-4 control-label">First Name</label>
					<div class="col-xs-8">
						<s:textfield name="employeeMasterVO.firstName" id="firstName" required="true" maxlength="50" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">Last Name</label>
					<div class="col-xs-8">
						<s:textfield name="employeeMasterVO.lastName" id="lastname" required="true" maxlength="50" cssClass="form-control"/> 
					</div>							
				</div>
				<s:if test="employeeMasterVO.id != null">
					<div class="form-group row">
						<label class="col-xs-4 control-label">Email</label>
						<div class="col-xs-8">
							<s:textfield name="employeeMasterVO.email" id="email" readonly="true" cssStyle="background-color:#D3D3D3" maxlength="50" cssClass="form-control"/>
						</div>
					</div>
				</s:if>
				<s:if test="employeeMasterVO.id == null">
					<div class="form-group row">
						<label class="col-xs-4 control-label">Email</label>
						<div class="col-xs-8">
							<s:textfield disabled="false" name="employeeMasterVO.email" id="email" required="true" maxlength="50" cssClass="form-control"/> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-4 control-label">Password</label>
						<div class="col-xs-8">
							<s:password name="userInfoVO.passWord" id="password" required="true" maxlength="50" cssClass="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-xs-4 control-label">Confirm Password</label>
						<div class="col-xs-8">
							<s:password id="cnfpassword" required="true" maxlength="50" cssClass="form-control"/> 
						</div>
					</div>
				</s:if>
				<div class="form-group row">
					<label class="col-xs-4 control-label">Phone Number</label>
					<div class="col-xs-8">
						<s:textfield name="employeeMasterVO.phoneNumber" id="telephone"  required="true" maxlength="50" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
			        <label class="col-xs-4 control-label">Address</label>
			        <div class="col-xs-8">
			            <s:textarea name="employeeMasterVO.address1" id="address"  required="true" maxlength="200" cssClass="form-control"/>
			        </div>
			    </div>
			    <div class="form-group row" id="genderContainer">
					<label class="col-xs-4 control-label">Gender</label>
					<div class="col-xs-8 btn-group" data-toggle="buttons">
						<label class="btn btn-primary" id="gender1">
							<input type="radio" id="genderMale" value="1">Male
						</label>
						<label class="btn btn-primary" id="gender2">
							<input type="radio" id="genderFemale" value="2">Female
						</label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label">Role</label>
					<div class="col-xs-8">
						<s:checkboxlist value="employeeMasterVO.rolesList" list="roleMasterVOsList" required="true" id="roleID"
							name="userInfoVO.roleIds" listKey="id" listValue="name" cssClass="form-control" theme="vertical-checkbox"/>
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
	
		$('.btn').button();

		$(document).ready(function(){
			if($('#genderValue').val() == 1) {
				$('#gender1').addClass('active');
				$('#gender2').removeClass('active');
			} else if($('#genderValue').val() == 2) {
				$('#gender2').addClass('active');
				$('#gender1').removeClass('active');
			}
		});
		function validateForm(){
			var passWord = document.getElementById("password");
			var cnfpassWord = document.getElementById("cnfpassword");
			
			var fieldForRequiredValidator;
			var fieldsForCompareValidator = [];
			var newUser = true;
			if(passWord == null && cnfpassWord == null) {
				fieldForRequiredValidator = [firstName,lastname,email,telephone,address];
				newUser = false;
			} else {
				fieldForRequiredValidator = [firstName,lastname,email,passWord,cnfpassword,telephone,address];
				fieldsForCompareValidator = [password,cnfpassword];
			}
			var fieldForNoSpecialCharacterValidator = [ firstName,lastname ];
			var minLengthValidation = [firstName];
			var emailValidation = [email];
			var fieldForNumericValidator = [telephone];
			var isCorrectData = true;
			var message = "";
			
			message = requiredFieldValidator(fieldForRequiredValidator);
			
			if (message.length > 0) {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().addClass('has-error');
				}
				isCorrectData = false;
			} else {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().removeClass('has-error');
				}
				
				message = minLengthCharacterValidator(minLengthValidation);
				if(message.length > 0) {
					for(var i = 0; i < minLengthValidation.length; i++) {
						$(minLengthValidation[i]).parent().parent().addClass('has-error');
					}
					isCorrectData = false;
				} else {
					for(var i = 0; i < minLengthValidation.length; i++) {
						$(minLengthValidation[i]).parent().parent().removeClass('has-error');
					}
					
					message = noSpecialCharacterValidator(fieldForNoSpecialCharacterValidator);
					if(message.length > 0) {
						for(var i = 0; i < fieldForNoSpecialCharacterValidator.length; i++) {
							$(fieldForNoSpecialCharacterValidator[i]).parent().parent().addClass('has-error');
						}
						isCorrectData = false;
					} else {
						for(var i = 0; i < fieldForNoSpecialCharacterValidator.length; i++) {
							$(fieldForNoSpecialCharacterValidator[i]).parent().parent().removeClass('has-error');
						}
						message = emailValidator(emailValidation);
						if(message.length > 0) {
							for(var i = 0; i < emailValidation.length; i++) {
								$(emailValidation[i]).parent().parent().addClass('has-error');
							}
							isCorrectData = false;
						} else {
							for(var i = 0; i < emailValidation.length; i++) {
								$(emailValidation[i]).parent().parent().removeClass('has-error');
							}
							message = phoneNOValidator(fieldForNumericValidator);
							if(message.length > 0) {
								for(var i = 0; i < fieldForNumericValidator.length; i++) {
									$(fieldForNumericValidator[i]).parent().parent().addClass('has-error');
								}
								isCorrectData = false;
							} else {
								for(var i = 0; i < fieldForNumericValidator.length; i++) {
									$(fieldForNumericValidator[i]).parent().parent().removeClass('has-error');
								}
								if(newUser){
									message = compareStringValidator(fieldsForCompareValidator);
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
								
								if($("#gender1").hasClass("active") || $("#gender2").hasClass("active")) {
									if($("#gender1").hasClass("active")) {
										$('#genderValue').val($('#genderMale').val());
									} else {
										$('#genderValue').val($('#genderFemale').val());
									}
									$('#genderContainer').removeClass('has-error');
									
								} else {
									message += "Please select a gender";
									$('#genderContainer').addClass('has-error');
									
									isCorrectData = false;
								}
								
								
							}
						}
					}
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