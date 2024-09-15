<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/Validator.js"></script>
<script>
	function validateForm() {
		var fieldForRequiredValidator = [ roleName ];
		var fieldForNoSpecialCharacterValidator = [ roleName ];
		var minLengthValidation = [roleName];
		var isCorrectData = true;
		var message;
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
			if (message.length > 0) {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().addClass('has-error');
				}
				isCorrectData = false;
			} else {
				for(var i = 0; i < fieldForRequiredValidator.length; i++) {
					$(fieldForRequiredValidator[i]).parent().parent().removeClass('has-error');
				}
				message = noSpecialCharacterValidator(fieldForNoSpecialCharacterValidator);
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
			}
	}
	/* 	if (requiredFieldValidator(fieldForRequiredValidator)) {
			message = ["Please provide mandatory fields"];
			isCorrectData = false;
		}else if(minLengthCharacterValidator(minLengthValidation)) {
			message = ["Please give atleast 3 characters"];
			isCorrectData = false;
		}else if (noSpecialCharacterValidator(fieldForNoSpecialCharacterValidator)) {
			message = ["Please give only alphabet and space"];
			isCorrectData = false;
		} */
		
		if(isCorrectData){
			saveTreeState();
			return true;
		}else {
			alert(message);
			return false;	
		}
	}

	
</script>

	<%@ include file='../layout/header.jsp'%>
	<div class="container container-center  alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
		<div style="margin-left: 3%;margin-right: 3%;">
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				<s:if test="roleMasterVO != null && roleMasterVO.id !=0">
					Update Role
				</s:if>
				<s:else>
					Add Role
				</s:else>
			</h2>
			
			<s:form action="rolemasteraction" name="frm" onsubmit="return validateForm();" role="form">
				<s:hidden name="roleMasterVO.id" /> 
				<s:hidden name="roleMasterVO.rowVersion" /> 
				<div class="form-group row">
					<label class="col-xs-4 control-label" id="formContainer">Name</label>
					<div class="col-xs-8">
						<s:textfield name="roleMasterVO.name" id="roleName" required="true" maxlength="50" cssClass="form-control"/>
					</div>							
				</div>
				<div class="form-group row">
					<label class="col-xs-4 control-label" id="formContainer">Description</label>
					<div class="col-xs-8">
						<s:textarea name="roleMasterVO.description"	id="roleDesc" maxlength="100" cssClass="form-control"/>
					</div>							
				</div>
				
				<%@ include file='tree.jsp'%>
				<script type="text/javascript">
					initializeTree();
				</script>
										
				<div class="form-group row buttons">
					<div class="col-xs-5 col-md-6">
						<input type="reset" class="btn btn-lg btn-primary btn-block" value="reset" onClick="return getTreeState();">
					</div>
					<div class="col-xs-1">
					</div>
					<div class="col-xs-5 col-md-6">
						<input type="submit" class="btn btn-lg btn-primary btn-block" value="submit">
					</div>
				</div>
				
				<div class="table-responsive" id="gridContainer">
					<table id="displayRow" class="table">
						<tr id="iteratorrow">
						</tr>
					</table>						
				</div>
			</s:form>
		</div>
	</div>
	</div>
	<%@ include file='../layout/footer.jsp'%>
</html>