<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file='../layout/header.jsp' %>
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
		<div style="margin-left: 3%;margin-right: 3%;">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				Subject
			</h2>
			<s:form action="savecategory" name="frm" onsubmit="return validateForm();">
				<div class="form-group row">
					<label class="col-xs-4 control-label">Subject Name</label>
					<div class="col-xs-8">
						<s:hidden name="categoryVO.id"/>
						<s:textfield name="categoryVO.name" id="Cname" required="true" maxlength="250" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group row" id="originalWorkRef">
					  <label class="col-md-4 control-label">Parent Subject Name</label>
					  <div class="col-xs-8">
					     <s:textfield cssClass="form-control" id="parentFKId" maxlength="250" name = "categoryVO.parentName" placeholder="Auto-complete field to add parent"/>
					     <s:hidden cssClass="form-control" id="originalWorkId" name="categoryVO.parentFKId"/>
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
			var fieldForRequiredValidator = [Cname];
	
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
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jquery-ui-1.10.4.custom.js"></script>
	<script>
	var categoryMap = {};
	$(function() {
		$('#parentFKId').autocomplete({
			/* jQuery autocomplete handler for author based on manuscript name */
	   	  source: function (request, response) {
		        $.getJSON("findAllCategoryAutoComplete.action?term=" + request.term + "&requestId=" + requestId , function (data) {
		            response($.map(data.categories, function (item) {
		            	categoryMap[item.id] = item;
		                return {
	                		label: item.name,
		                    value: item.name,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				$('#originalWorkId').val(ui.item.id);
		 	},
		    minLength:1
		 });
		$('#ui-id-1').wrap('<div class="dropdown"></div>');
		$('#ui-id-1').addClass('dropdown-menu');
		$('#ui-id-1 a .ui-corner-all').attr('role','menuitem');
	});
	</script>
</html>
