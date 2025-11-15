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
	
	textarea {
    	resize: vertical;
   	}
	</style>
	<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui-1.10.4.custom.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui.css"
	rel="stylesheet" />
	<div class="container container-center" style="max-width: 65%;margin-left: 220px;">
	<div class="alert alert-success appTable centerdiv">
		<div class="form">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			
			<h2 class="form-heading">
			Search Documents for Viewing
			</h2>
			<s:form action="searchManuscriptPublicUse" name="searchform" id="searchForm">
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Document ID</label>
					<div class="col-xs-4">
						<s:textfield name="digitalManuscriptVO.manuscriptId" maxlength="50" cssClass="form-control" id="manuscriptId"/>
					</div>
					<label class="col-xs-2 control-label">Document Name</label>
					<div class="col-xs-4">
						<s:textfield name="digitalManuscriptVO.name" maxlength="50" cssClass="form-control" id="manuscriptName"/>
					</div>
				</div>
				<div class="form-group row" style="margin-bottom: 0px;">
				<label class="col-xs-2 control-label">Subject</label>
					<div class="col-xs-4">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.categoryFkId" list="categoryVOs" required="true" id="categoryFkId"
										name="digitalManuscriptVO.categoryFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
					<label class="col-xs-2 control-label">Source Name</label>
					<div class="col-xs-4">
						<s:textfield name="digitalManuscriptVO.organisationVO.name" maxlength="50" cssClass="form-control" id="organisationName"/>
					</div>
				</div>
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Document Type</label>
					<div class="col-xs-4">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.documentType" list="documentTypes" required="true" id="documentType"
										name="digitalManuscriptVO.documentType" listKey="value" listValue="label" cssClass="form-control"/>
					</div>
					<label class="col-xs-2 control-label">Type Of Work</label>
					<div class="col-xs-4">
						<s:select value="digitalManuscriptVO.typeOfWork" list="manuscriptWorkTypes" required="true" id="typeOfWork"
										name="digitalManuscriptVO.typeOfWork" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Language</label>
					<div class="col-xs-4">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.languageFkId" list="languageVOs" required="true" id="languageFkId"
										name="digitalManuscriptVO.languageFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
					<label class="col-xs-2 control-label">Script</label>
					<div class="col-xs-4">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.scriptFkId" list="scriptVOs" id="scriptFkId"
										name="digitalManuscriptVO.scriptFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Workflow Status</label>
					<div class="col-xs-4">
						<s:select value="digitalManuscriptVO.recordStatus" list="documentStatusList" 
									required="true" id="documentStatusList" name="digitalManuscriptVO.recordStatus" cssClass="form-control"/>
					</div>
					
					<label class="col-xs-2 control-label">Document Category</label>
					<div class="col-xs-4">
						<s:select value="digitalManuscriptVO.manuscriptType" list="recordStatusList" 
									required="true" id="recordStatusList" name="digitalManuscriptVO.manuscriptType" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Having Content...</label>
					<div class="col-xs-4">
						<s:textfield name="digitalManuscriptVO.summary" maxlength="50" cssClass="form-control" id="summary"/>
					</div>
					<label class="col-xs-2 control-label">Document Status</label>
					<div class="col-md-4">
						 <s:select list="manuscriptDocumentationTypes" cssClass="form-control"
				              name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" maxlength="50"/>
					 </div>
				</div>
				
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Beginning Line</label>
					<div class="col-xs-4">
						<s:textfield name="digitalManuscriptVO.beginningLine" maxlength="50" cssClass="form-control" id="beginningLine" placeholder="Multilingual field.."/>
					</div>
					<label class="col-xs-2 control-label">Ending Line</label>
					<div class="col-md-4">
						<s:textfield name="digitalManuscriptVO.endingLine" maxlength="50" cssClass="form-control" id="endingLine" placeholder="Multilingual field.."/>
					 </div>
				</div>
				
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Minimum Folios</label>
					<div class="col-xs-4">
						<s:textfield name="digitalManuscriptVO.minimumFolios" maxlength="50" cssClass="form-control" id="minFolios"/>
					</div>
					<label class="col-xs-2 control-label">Maximum Folios</label>
					<div class="col-md-4">
						<s:textfield name="digitalManuscriptVO.maximunFolios" maxlength="50" cssClass="form-control" id="maxFolios"/>
					 </div>
				</div>
				
				<div class="form-group row" style="margin-bottom: 0px;">
					<label class="col-xs-2 control-label">Author</label>
					<div class="col-xs-4">
						 <s:select list="authorList" required="true" id="authorId"
										name="digitalManuscriptVO.authorId" listKey="id" listValue="name" cssClass="form-control" multiple="true"/>
					</div>
					<label class="col-xs-2 control-label">Specific Category</label>
					<div class="col-md-4">
						  <s:select list="specificCategoryVOs" required="true" id="specificCategoryId"
										name="digitalManuscriptVO.specificCategoryId" listKey="id" listValue="name" cssClass="form-control" multiple="true"/>
					 </div>
				</div>
				
				<div class="form-group row">
					
				</div>
				
				<div class="form-group row buttons">
					<div class="col-xs-3 col-md-3">
						<s:hidden name="searchType" id="searchType"></s:hidden>
						<button type="button" class="btn btn-lg btn-primary btn-block" onClick="findAll();" style="visibility: hidden;">Find All</button>
					</div>
					<div class="col-xs-3 col-md-3">
						<button type="button" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();" >Search</button>
					</div>
					<div class="col-xs-3 col-md-3">
					 <button type="button" class="btn btn-lg btn-success btn-block" onClick="newRecord();">Reset</button>
						<%-- <s:reset cssClass="btn btn-lg btn-success btn-block"/> --%>
					</div>
					<div class="col-xs-3 col-md-3">
					</div>
				</div>
				<s:if test="listForPagingCombo != null && listForPagingCombo.size() > 0">
				
					<div class="form-group row">
						<label class="col-xs-3 control-label" style="width : 20%;">Total Records: </label>
						<div class="col-xs-3" style="width:10%;">
							<s:text name="totalRecords"/>							
						</div>

						<!-- <label class="col-xs-3 control-label" style="width : 20%;">Records from: </label> -->
						<!-- <div class="col-xs-3" style="width:30%;" align="center"> -->
						<%-- <s:text name="((selectedPage-1)*5)+1"></s:text> &nbsp-
						<s:if test="selectedPage*5 <= totalRecords">
						<s:text name="selectedPage*5"></s:text></s:if><s:else><s:text name="totalRecords"></s:text></s:else> 
						<label class="col-xs-3 control-label" style="width : 20%;">Records from: </label>--%>
						<div class="col-xs-3" style="width:40%;" align="center">
						<%-- <s:text name="((selectedPage-1)*10)+1"></s:text> &nbsp-
						<s:if test="selectedPage*10 <= totalRecords">
						<s:text name="selectedPage*10"></s:text></s:if><s:else><s:text name="totalRecords"></s:text></s:else> --%>
						<span class="glyphicon glyphicon-backward next-previous" onclick="nextPrevious(1);"></span>
						<span class="glyphicon glyphicon-chevron-left next-previous" onclick="nextPrevious(2);"></span>
						<span class="glyphicon glyphicon-chevron-right next-previous" onclick="nextPrevious(3);"></span>
						<span class="glyphicon glyphicon-forward next-previous" onclick="nextPrevious(4);"></span>
						<!-- <input type="button" onclick="nextPrevious(1);" style="background-image: url('./assets/images/last.png');" class="searchbutton"/>
						<input type="button" onclick="nextPrevious(2);" style="background-image: url('./assets/images/previous.png');" class="searchbutton"/>
						<input type="button" onclick="nextPrevious(3);" style="background-image: url('./assets/images/next.png');" class="searchbutton"/>
						<input type="button" onclick="nextPrevious(4);" style="background-image: url('./assets/images/lastr.png');" class="searchbutton"/> -->
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
							<th><b>Type</b></th>
							<th><b>Language</b></th>
							<th><b>Script</b></th>
							<!--<th><b>Category</b></th>
							<th><b>Specific Category</b></th> -->
							<th><b>Frames</b></th>
						</thead>
						<tbody>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}">
								<s:iterator>
									<tr>
										<s:hidden value="id" />
										<td><s:property value="name" /></td>
										<td><s:property value="authorName" /></td>
										<td><s:property value="manuscriptType" /></td>
										<td><s:property value="languageVO.name" /></td>
										<td>
										<s:if test="scriptFkId">
										<s:property value="scriptVO.name" />
										</s:if>
										<s:else>No Script</s:else>
										</td>
										<td>
										<s:if test="presentFrame">
											<s:url id="viewFrameURL" action="viewFrames.action">
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:a href="%{viewFrameURL}">
												<button type="button" class="btn btn-lg btn-success btn-block btn-state-1">View</button>
											</s:a>
										</s:if><s:else>
											<button type="button" class="btn btn-lg btn-success btn-block btn-state-1">N/A</button>
										</s:else>
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
		
	</div>
	
	<%@ include file='../layout/footer.jsp'%>
	
	<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/Validator.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/rangy-core.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.selector.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.preferences.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.inputmethods.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery-ui.js"></script>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.mousewheel.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	$('#beginningLine, #endingLine').ime();
	});
		
		function removepopup() {
			 $( "#comment-form" ).dialog('close');
		 };
		
		function findAll() {
			if(document.getElementById("pagerCombo") != null) {
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value = "";
			}
			for(var i = 0; i < document.searchform.elements.length; i++) {
				if(document.searchform.elements[i].id != 'requestId') {
		               document.searchform.elements[i].value = '';
		        }
					
				/* if(document.searchform.elements[i].type == 'select-one') {
					document.searchform.elements[i].options[document.searchform.elements[i].selectedIndex].value = "";
				}  */
		    }

			$('#searchType').attr('value', 'FIND_ALL');
			document.searchform.submit();
		}
		
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
		<%-- function findAll() {
			var pageNo = document.getElementById("pagerCombo");
			if(pageNo != null) {
				window.location.replace("<%=request.getContextPath()%>/searchForManuscript.action?requestId=" + requestId + "&page="+ pageNo.value) ;
			} else {
				document.searchform.submit();
			}
			
			
			var actnFind = document.getElementById("isFindAllBox").value;
			if(actnFind != null && actnFind > 0) {
				document.searchform.submit();
			} else {
				var pageNo = document.getElementById("pagerCombo");
				if(pageNo != null)
					window.location.replace("<%=request.getContextPath()%>/searchForManuscript.action?requestId=" + requestId + "&page="+ pageNo.value);
				else
					window.location.replace("<%=request.getContextPath()%>/searchForManuscript.action?requestId=" + requestId);
			}
		} --%>
		
		$('.transcriber').on('keyup', function(e) {
			var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
			var currentId = frameId.split('_')[1];
			for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
				if(fileDiskPathObject[i].id == currentId) {
					fileDiskPathObject[i].text = $(this).val();
				}
			} 
			$('#fileDiskPathContainer').val(JSON.stringify(fileDiskPathObject));
			console.log($('#fileDiskPathContainer').val());
		});
		
		function newRecord() {
			window.location.replace("<%=request.getContextPath()%>/searchManuscript.action?requestId="+requestId);
		};
		
		function validateForm(){
			var isCorrectData = true;
			var fieldForNumericValidator;
			fieldForNumericValidator=[minFolios,maxFolios];
			message = numericValidator(fieldForNumericValidator);
			if (message.length > 0) {
				for(var i = 0; i < fieldForNumericValidator.length; i++) {
					$(fieldForNumericValidator[i]).parent().parent().addClass('has-error');
				}
				isCorrectData = false;
			} else {
				for(var i = 0; i < fieldForNumericValidator.length; i++) {
					$(fieldForNumericValidator[i]).parent().parent().removeClass('has-error');
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