<html>
	<%@ include file='../layout/header.jsp' %>
	<style>
	.btn-state-1, .btn-state-2 {
		height: 30px;
		padding: 0px;
		font-size: 12px;
	}
	
	.btn-state-1 {
		background-color: white;
		color: black;
	}
	
	.btn-state-2 {
		/* border-radius: 30px; */
	}
	
	a:hover {
		text-decoration:none;
	}
	</style>
	<link href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css" rel="stylesheet" />
	<link href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
	<link href="${pageContext.servletContext.contextPath}/assets/css/viewimage.css" rel="stylesheet" />
	<link href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui.css" rel="stylesheet" />
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
		<div class="form">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			
			<h2 class="form-heading">
				Search Manuscripts
			</h2>
			<s:form action="searchManuscriptPublicUse" name="searchform" id="searchForm">
				<div class="form-group row">
					<label class="col-xs-3 control-label">Manuscript ID</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.manuscriptId" maxlength="50" cssClass="form-control" id="manuscriptId"/>
					</div>
					<label class="col-xs-3 control-label">Manuscript Name</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.name" maxlength="50" cssClass="form-control" id="manuscriptName"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-3 control-label">Author Name</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.authorVO.name" maxlength="50" cssClass="form-control" id="authorName"/>
					</div>
					<label class="col-xs-3 control-label">Source Name</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.organisationVO.name" maxlength="50" cssClass="form-control" id="organisationName"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-3 control-label">Document Type</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.documentType" list="documentTypes" required="true" id="documentType"
										name="digitalManuscriptVO.documentType" listKey="value" listValue="label" cssClass="form-control"/>
					</div>
					<label class="col-xs-3 control-label">Type Of Work</label>
					<div class="col-xs-3">
						<s:select value="digitalManuscriptVO.typeOfWork" list="manuscriptWorkTypes" required="true" id="typeOfWork"
										name="digitalManuscriptVO.typeOfWork" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-3 control-label">Language</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.languageFkId" list="languageVOs" required="true" id="languageFkId"
										name="digitalManuscriptVO.languageFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
					<label class="col-xs-3 control-label">Script</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.scriptFkId" list="scriptVOs" id="scriptFkId"
										name="digitalManuscriptVO.scriptFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-xs-3 control-label">Subject</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.categoryFkId" list="categoryVOs" required="true" id="categoryFkId"
										name="digitalManuscriptVO.categoryFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<div class="col-xs-3">
						<s:hidden value="Published"
									 name="digitalManuscriptVO.recordStatus" cssClass="form-control"/>
					</div>
				</div>
				
				
				<div class="form-group row buttons">
					<div class="col-xs-4 col-md-4" style="visibility: hidden;">
						<s:hidden name="searchType" id="searchType"></s:hidden>
						<button type="button" class="btn btn-lg btn-primary btn-block">Find All</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="submit" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();">Search</button>
					</div>
					<div class="col-xs-4 col-md-4" style="visibility: hidden;">
						<button type="button" class="btn btn-lg btn-success btn-block">New record</button>
					</div>
				</div>
				
				
				
				<s:if test="listForPagingCombo != null && listForPagingCombo.size() > 0">
				
					<div class="form-group row">
						<label class="col-xs-3 control-label" style="width : 20%;">Total Records: </label>
						<div class="col-xs-3" style="width:10%;">
							<s:text name="totalRecords"/>							
						</div>

						<div class="col-xs-3" style="width:40%;" align="center">
							<input type="button" onclick="nextPrevious(1);" style="background-image: url('./assets/images/last.png');" class="searchbutton"/>
							<input type="button" onclick="nextPrevious(2);" style="background-image: url('./assets/images/previous.png');" class="searchbutton"/>
							<input type="button" onclick="nextPrevious(3);" style="background-image: url('./assets/images/next.png');" class="searchbutton"/>
							<input type="button" onclick="nextPrevious(4);" style="background-image: url('./assets/images/lastr.png');" class="searchbutton"/>
						</div>
						<label class="col-xs-3 control-label" style="width : 20%;">Show page No:</label>
						<div class="col-xs-3" style="width:10%;">
							<s:select  list="listForPagingCombo" required="true" id="pagerCombo"
											name="selectedPage" cssClass="pagerCombo" onchange="changePagerCombo()"/>
						</div>
					</div>
				</s:if>
			</s:form>
			<div class="errors" id="errorMessageBox">
				
			
			</div>
			<div class="table-responsive">
				<table class="table">
					<s:if test="#request.objResult.listOfElemnents.size() > 0">
						<thead>
							<th><b>Name</b></th>
							<th><b>Author</b></th>
							<th><b>Language</b></th>
							<th><b>Script</b></th>
							<th><b>PDF</b></th>
						</thead>
						<tbody>
						<%int i= -1; %>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}" status="var">
							<% i++; %>
								<s:iterator>
									<tr>
										<s:hidden  id="%{#var.index}" name="id" />
										<s:hidden  id="workType%{#var.index}" name="manuscriptType" />
										<td><div style="text-decoration:underline; color: #428BCA;"><s:property value="name" /></div>
										<div style="max-height: 40px; width: 180px; overflow: hidden; font-size: 10;color:#999999; "><s:property value="summary" /></div>
										</td>
										<td><s:property value="authorVO.name" /></td>
										<td><s:property value="languageVO.name" /></td>
										<td>
										<s:if test="scriptFkId">
										<s:property value="scriptVO.name" />
										</s:if><s:else>No Script</s:else>
										</td>
										<td>
												<input type="button" style="width: 60px;" class="btn btn-lg btn-success btn-block btn-state-1" value="VIEW" onclick="manuscriptReport(<%=i%>,'pdf')">
										</td>
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>
					</s:if>
				</table>
			</div>
		</div>
		
	</div>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/Validator.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/rangy-core.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.selector.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.preferences.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.inputmethods.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery-panzoom.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.mousewheel.js"></script>
	
	<%@ include file='../layout/footer.jsp'%>
	<script type="text/javascript">
		
		function searchSpecific() {
			if(document.getElementById("pagerCombo") != null) {
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value = "";
			}
			
			$('#searchType').attr('value', 'SEARCH_SPC');
			document.searchform.submit();
		}
		
		function nextPrevious(nextpreve){
			if(nextpreve==1){
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value=1;
				changePagerCombo(); 
			}else if(nextpreve==2){
				var page =parseInt(document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value);
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value=page-1;
			    changePagerCombo();
			}
			else if(nextpreve==3){
				var page = parseInt(document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value);
				var lastValue = document.getElementById('pagerCombo').options[document.getElementById('pagerCombo').options.length - 1].value;
				if(page < lastValue){
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value=page+1;
				changePagerCombo();
				}else{
				alert("This is the last page");
				}
			}else if(nextpreve==4){
				var theSelect = document.getElementById('pagerCombo');
				var lastValue = theSelect.options[theSelect.options.length - 1].value;
				/* var page = document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value; */
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value=lastValue;
				changePagerCombo();
			}
		}

		function changePagerCombo() {
			var page = document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value;
			document.searchform.reset();
			document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value = page;
			document.searchform.submit();
		}
		
		function manuscriptReport(rowNo,type){
			var manuscriptId=$('#'+rowNo).val();
			var workType = $('#workType'+rowNo).val();
			<%
			String requestdp = (String) request.getAttribute("requestId");
		%>
			var docType =$('#reportType').val();
				window.open('${pageContext.request.contextPath}/exportReportForPublic.action?requestId='+<%=requestdp%>+ '&jasperPath=/report/FullManuscriptWithFrame.jasper&id='+manuscriptId +'&workType='+workType);
		};
	</script>
</html>