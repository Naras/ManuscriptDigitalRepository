<%@page import="com.indven.search.vo.SearchVO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/paging.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/jquery-ui.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/searchformgenerator.js"></script>


	<%@include file='../layout/header.jsp'%>
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
	<div class="form">
		<div >
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				Moderate Tags
			</h2>
			
			<s:form id="frm" action="findTag" >
			<fieldset style="border: 2px solid #ccc; margin: 2px; border-radius: 4px">
						<legend style="font-size: large; width: 0px;"></legend>
				<div class="form-group row" style="padding-right: 10px; padding-left: 10px;">
					<label class="col-xs-6 control-label" id="formContainer">Search Tag</label>
					<div class="col-xs-6">
						<s:textfield id="tag" cssClass="form-control" name="tagVO.name"/>
					</div>							
				</div>
				<div class="form-group row buttons" style="padding-right: 10px; padding-left: 10px;">
					<div class="col-xs-4 ">
						<s:submit cssClass="btn btn-lg btn-primary btn-block" ></s:submit>
					</div>
					<div class="col-xs-4">
						<input type="button" class="btn btn-lg btn-danger btn-block" value="cancel" id="cancel-publisher" onclick="newDoc();" />
					</div>
					<div class="col-xs-4">
						<button type="button" class="btn btn-lg btn-success btn-block" onClick="Redirect();">New record</button>
					</div>
				</div>
				</fieldset>
				<!-- <div id="pageNavPosition" style="padding-top: 2px ; " align="center" ></div>
				<div class="table-responsive">
					<table id="displayRow" class="table">
						<tr id="iteratorrow">
							<td colspan="2" id="gridContainer" class="inVisibleTable">
								<div class="CSSTableGenerator" id="outerTableDiv" style=" margin-top:-5px ;">
								</div>
							</td>
						</tr>
					</table>						
				</div> -->
			</s:form>
			
			<s:form action="replaceTag" id="frm" role="form" onsubmit="return validateForm();">
			<div  style="border: 1px solid #ccc; border-radius: 4px; padding-top: 10px;">
			<div style="padding: 5px;" class="form-group row">
			<div class="col-xs-4 ">
			<input type="button" value="Reconcile" style="height: 34px; width: 120px; padding-top: 5px;" class="btn btn-lg btn-success btn-block" onclick="selectDiv();" />
			</div>
			<div style=" display: none;" id="replacediv">
			<div class="col-xs-4 ">
			<s:hidden id="tag1" name="tagVO.name"></s:hidden>
			<s:textfield id="text" name="replacedTag.name" cssClass="form-control" cssStyle="border-width: 2px;"></s:textfield>
			</div>
			<div class="col-xs-4 ">
			<s:submit value="Replace" cssStyle="height: 34px; width: 100px; padding-top: 2px;" cssClass="btn btn-lg btn-success btn-block"/>
			</div>
			</div>
			</div>
			<div id="grid" style="border:  3px solid #ccc;  height: 200px; padding-left: 10px; overflow-y: scroll;" align="center">
			<s:iterator value="tagList" status="var">
		    <div class="griddiv">
		    <s:checkbox name="tagList[%{#var.index}].replaceId"></s:checkbox>
		    <s:hidden name="tagList[%{#var.index}].id"></s:hidden>
			<%-- <s:textfield name="tagList[%{#var.index}].name" cssStyle="border-width: 0px; width: 100px;padding: 0px;background-color:#F5FAF3;"></s:textfield> --%>
			<s:property value="name"/>
			</div>
			</s:iterator>
			</div>
			</div>
			</s:form>
		
		</div>
	</div>
	</div>
	   <%-- <div style=" display: none;" id="replacediv">
			<div class="col-xs-4" style="padding-top: 30px;">
			<s:textfield id="text" name="replacedTag.name" cssClass="form-control" cssStyle="border-width: 2px;"></s:textfield>
			</div>
			<div class="col-xs-2" style="padding-top: 30px; padding-left: 0px;">
			<s:submit value="Replace" cssStyle="height: 34px; width: 80px; padding-top: 2px;" cssClass="btn btn-lg btn-success btn-block"/>
			</div>
			</div> --%>
	<%@ include file="../layout/footer.jsp" %>
	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/Validator.js"></script>
	<script type="text/javascript">
	function Redirect(){
	 document.getElementById("frm").action="addtag";
	 document.getElementById("frm").submit();
	}
	function selectDiv(){
		document.getElementById("replacediv").style.display= 'block' ;
		var name=document.getElementById("tag").value;
		document.getElementById("tag1").value=name;
	}
	function newDoc()
	  {
	  window.location.assign("jsp/home.jsp");
	  }
	
	function validateForm(){
		var isCorrectData = true;
		var fieldForRequiredValidator;
		fieldForRequiredValidator=[text];
		message = requiredFieldValidator(fieldForRequiredValidator);
		if (message.length > 0) {
			for(var i = 0; i < fieldForRequiredValidator.length; i++) {
				$(fieldForRequiredValidator[i]).parent().parent().addClass('has-error');
			}
			alert(message);
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