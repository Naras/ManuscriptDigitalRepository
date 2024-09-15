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
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
		<div class="form">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			
			<h2 class="form-heading">
				Advanced Search For Manuscripts
			</h2>
			<s:form action="searchForManuscript" name="searchform" id="searchForm">
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
					<label class="col-xs-3 control-label">Workflow Status</label>
					<div class="col-xs-3">
						<s:select value="digitalManuscriptVO.recordStatus" list="documentStatusList" 
									required="true" id="documentStatusList" name="digitalManuscriptVO.recordStatus" cssClass="form-control"/>
					</div>
					
					<label class="col-xs-3 control-label">Document Category</label>
					<div class="col-xs-3">
						<s:select value="digitalManuscriptVO.manuscriptType" list="recordStatusList" 
									required="true" id="recordStatusList" name="digitalManuscriptVO.manuscriptType" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-xs-3 control-label">Having Content...</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.summary" maxlength="50" cssClass="form-control" id="summary"/>
					</div>
					<label class="col-xs-3 control-label">Manuscript Status</label>
					<div class="col-md-3">
						 <s:select list="manuscriptDocumentationTypes" cssClass="form-control"
				              name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" maxlength="50"/>
					 </div>
				</div>
				
				<div class="form-group row">
					<label class="col-xs-3 control-label">Beginning Line</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.beginningLine" maxlength="50" cssClass="form-control" id="beginningLine"/>
					</div>
					<label class="col-xs-3 control-label">Ending Line</label>
					<div class="col-md-3">
						<s:textfield name="digitalManuscriptVO.endingLine" maxlength="50" cssClass="form-control" id="endingLine"/>
					 </div>
				</div>
				
				<div class="form-group row">
					<label class="col-xs-3 control-label">Minimum Folios</label>
					<div class="col-xs-3">
						<s:textfield name="digitalManuscriptVO.minimumFolios" maxlength="50" cssClass="form-control" id="minFolios"/>
					</div>
					<label class="col-xs-3 control-label">Maximum Folios</label>
					<div class="col-md-3">
						<s:textfield name="digitalManuscriptVO.maximunFolios" maxlength="50" cssClass="form-control" id="maxFolios"/>
					 </div>
				</div>
				
				<div class="form-group row">
					<label class="col-xs-3 control-label">Subject</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.categoryFkId" list="categoryVOs" required="true" id="categoryFkId"
										name="digitalManuscriptVO.categoryFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
					<label class="col-xs-3 control-label">Specific Category</label>
					<div class="col-md-3">
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
						<button type="submit" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();" >Search</button>
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
							<th><b>Status</b></th>
							<th><b>Name</b></th>
							<th><b>Author</b></th>
							<th><b>Type</b></th>
							<th><b>Owner</b></th>
							<th><b>Unassign</b></th>
							<!-- <th><b>Type</b></th>
							<th><b>Language</b></th>
							<th><b>Category</b></th>
							<th><b>Specific Category</b></th> -->
							<th><b>Frames</b></th>
							<th><b>Update</b></th>
							<th><b>Delete</b></th>
						</thead>
						<tbody>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}">
								<s:iterator>
									<tr>
										<s:hidden value="id" />
										<s:if test="isUnderWfl == null">
										<td>
											<s:if test="manuscriptType.equals('Transcription')">
													
													<button type="button" id="translationReqBtn<s:property value="id"/>" class="btn btn-lg btn-warning btn-block btn-state-1" style="width: 70;" onclick="makeTranslate('<s:property value="id"/>','<s:property value="name"/>')">Transliterate</button>
													<%-- <s:url id="assignURL1">
														<s:param name="id" value="%{id}"></s:param>
													</s:url>
													<s:a href="%{assignURL1}" cssClass="unAssignRecord">
													<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1" onclick="makeTranslate('#(%{id})')">Unassign</button>
												</s:a> --%>
											</s:if>
											
											<%-- <s:if test="manuscriptType.equals('Translation')">
												<button type="button" class="btn btn-lg btn-info btn-block btn-state-1">N/A</button>
											</s:if> --%>
											<s:else>
												<s:if test="manuscriptType.equals('Translation')">
													<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1">N/A</button>
												</s:if>
												<s:else>
													<s:if test="presentFrame">
														<button type="button" style="width: 68;" id="assignButton<s:property value="id"/>" class="btn btn-lg btn-warning btn-block btn-state-1" onclick="pushintowfl('<s:text name="id" />','<s:text name="name" />')">Assign</button>
													</s:if>
													<s:else>
														<button type="button" style="width: 68;" disabled class="btn btn-lg btn-warning btn-block btn-state-1">Assign</button>
													</s:else>
													<%-- <s:url id="assignURL" action="makeRecordForWFL.action">
														<s:param name="id" value="%{id}"></s:param>
														<s:param name="name" value="%{name}"></s:param>
													</s:url>
													<s:a href="%{assignURL}" cssClass="assignRecord">
														<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1">Assign</button>
													</s:a> --%>
												</s:else>
											</s:else>
										</td>
										</s:if><s:else>
											<td>
												<button type="button" class="btn btn-lg btn-info btn-block btn-state-2" style="width: 68;">Assigned</button>
											</td>
										</s:else>
										
										<td><s:property value="name" /></td>
										<td><s:property value="authorVO.name" /></td>
										<td><s:property value="manuscriptType" /></td>
										<td><s:property value="wflProcessOwner" default="Pool"/></td>
										
										<td>
											<s:if test="wflProcessOwner != 'N/A' && wflProcessOwner != null">
												<s:url id="unAssignURL" action="assignprocessfromusertorole.action">
													<s:param name="activewflProcessId" value="%{activewflProcessId}"></s:param>
												</s:url>
												
												<s:a href="%{unAssignURL}" cssClass="unAssignRecord">
													<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1">Unassign</button>
												</s:a>
											</s:if><s:else>
												<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1">N/A</button>
											</s:else>
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
										<td>
											<s:if test="!(manuscriptType.equals('Transcription') || manuscriptType.equals('Translation'))">
												<s:url id="editURL"	action="getManuscriptById.action">
													<s:param name="id" value="%{id}"></s:param>	
												</s:url>
												<s:a href="%{editURL}"><img style="padding-left: 35px;" height="20" alt=""	src="<%=request.getContextPath()%>/assets/images/update.png" /></s:a>
											</s:if><s:else>
													<button type="button" class="btn btn-lg btn-success btn-block btn-state-1" disabled>N/A</button>
											</s:else>
										</td>
										<td>
											<s:url id="deleteURL" action="deleteManuscriptaction.action">	
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:a href="%{deleteURL}" onClick="return deleteManuscript();"><img style="padding-left: 35px;" height="20" alt=""	src="<%=request.getContextPath()%>/assets/images/Delete.jpg"/></s:a>
										</td>
		
										
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>
					</s:if>
				</table>
				<div class="transcriber-container">
				<s:textarea 
								cssClass="form-control transcriber" rows="0" cssStyle="visibility:collapse;height: 0;"></s:textarea>
							<div class="searchDiv" style="display: none;" id="showdiv" onclick="replace();"></div>
						</div>
				<div id="comment-form" title="Select language and script" style="display: none;background-color: #F5FAF3;">
					<s:form>
						<fieldset>
							<div class="form-group row manuscript-specific">
								<label class="col-md-4 control-label">Manuscript :</label>
								<div class="col-md-8">
									<s:textfield cssClass="form-control" id="commentDate"
										maxlength="50" readonly="true" />
									<s:hidden id="transcriptedManId"></s:hidden>
								</div>
							</div>
	
							<div class="form-group row manuscript-specific">
									<label class="col-md-4 control-label">Language :</label>
									<div class="col-md-6">
									<select id="imeLanguage" name="imeLanguage"
										class="form-control"></select>
										</div>
							</div>
	
							<div class="form-group row manuscript-specific">
								<label class="col-md-4 control-label">Script :</label>
								<div class="col-md-6">
									<select id="imeSelector" name="imeSelector"
										class="form-control"></select>
								</div>
							</div>
							
							<div class="form-group row manuscript-specific">
								<label class="col-md-4 control-label">Available in:</label>
								<s:textarea readonly="true"
								cssClass="form-control transcriber" id="availLanguages" rows="2" cssStyle="height:100px;"></s:textarea>
							</div>
							
							<div class="container-center col-md-4" style="padding-left: 35%;">
								<input type="button" value="Cancel" class="btn btn-lg btn-danger" onclick="removepopup();" style="height: 37px; padding-top: 6px; font-size: medium;"/>
								<input type="button" value="Transliterate" class="btn btn-lg btn-success" onclick="saveTranslationInfo();" style="height: 37px; padding-top: 6px; font-size: medium;"/>
							</div>
						</fieldset>
					</s:form>
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
		function deleteManuscript() {
			return confirm("Are you sure you want to delete this manuscript?");
		};
		
		function pushintowfl(recordid , recordname) {
			var cnf = confirm("Before assigning for editing/transcribing, please make sure that the manuscript is complete and has all the frames for transcription. Do you want to continue ?");
			if(cnf) {
				<%
					String requestd1 = (String) request.getAttribute("requestId");
				%>
				$.ajax({
					type :"GET",
					dataType:"json",
					url:'makeRecordForWFL.action?requestId='+<%=requestd1%>+'&id='+ recordid +'&name='+ recordname,
					cache : false,
					success:function(data) {
						var buttonId = "#assignButton"+recordid;
						$(buttonId).removeClass('btn-state-1');
						$(buttonId).removeClass('btn-warning');
						$(buttonId).addClass('btn-info');
						$(buttonId).addClass('btn-state-2');
						$(buttonId).text('Assigned');
						$(buttonId).attr('disabled' , true);
						
						alert('Record Submitted successfully');
					},
					error : function(){
						removepopup();
						alert("Unable to assign the record for workflow");
					}
				});
			}
		};
		
		function saveTranslationInfo() {

			var language = $('#imeLanguage').val();
			var script = $('#imeSelector').val();
			var parentManId = $('#transcriptedManId').val();
			var str = '#translationReqBtn'+parentManId;
			if($(str).hasClass('btn-state-1')) {
				<%
					String requestd = (String) request.getAttribute("requestId");
				%>
				
				$.ajax({
					type :"GET",
					dataType:"json",
					url:'savetranslationlangscript.action?requestId='+<%=requestd%>+'&language='+ language +'&script='+ script +'&parentManId='+ parentManId,
					cache : false,
					success:function(data) {
						removepopup();
						alert(data.message.message);
					},
					error : function(){
						removepopup();
						alert("Unable to save translation details.Please try later.");
					}
				});
			} else if($(this).children('button').hasClass('btn-state-2')) {
				alert('Record already in process.');
			}
		};
		
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
		
	/* 	$(document).ready(function() {
			 initializeIme();
		}); */
		
		function makeTranslate(id , name) {
			$.ajax({
				type :"GET",
				dataType:"json",
				url:'gettranslationinexistinglanguages.action?requestId='+<%=requestd%>+'&parentManId='+ id,
				cache : false,
				success:function(data) {
					 var opt = {
						        autoOpen: false,
						        width: 520,
						        height:400,
						        title: 'Select language and script for Translation',
						        modal: true
					 };
					 initializeIme();
					 var currentdate = name; 
					 document.getElementById("commentDate").value = currentdate;
					 document.getElementById("transcriptedManId").value = id;
					 document.getElementById("availLanguages").value = data.message;
					 $( "#comment-form").dialog(opt).dialog('open');
				},
				error : function(){
					removepopup();
					alert("Unable to perform the operation. Please try later.");
				}
			});
		};
		
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
		
		function initializeIme() {
			'use strict';
			var imeselector, languages, $imeSelector, $langselector;
			$( '.transcriber' ).ime();
			$( '#bold' ).click( function () {
				document.execCommand( 'bold', false, null );
			} );
			$( '#italic' ).click( function () {
				document.execCommand( 'italic', false, null );
			} );
			$( '#underline' ).click( function () {
				document.execCommand( 'underline', false, null );
			} );
			// get an instance of inputmethods
			imeselector = $( '.transcriber' ).data( 'imeselector' );
			imeselector.$imeSetting = $([]);
			languages = $.ime.languages;
			// Also test system inputmethods.
			$imeSelector = $( 'select#imeSelector' );
			$langselector = $( 'select#imeLanguage' );
			function listinputmethods ( language ) {
				var inputmethods = $.ime.languages[language].inputmethods;
				$imeSelector.empty();
				$.each( inputmethods, function ( index, inputmethodId ) {
					var inputmethod = $.ime.sources[inputmethodId];
					$imeSelector.append( $( '<option></option>' )
					.attr( 'value', inputmethodId ).text( inputmethod.name ) );
				} );
				$imeSelector.trigger( 'change' );
			}
			$.each( languages, function ( lang, language ) {
				$langselector.append( $( '<option></option>' )
				.attr( 'value', lang )
				.text( language.autonym ) );
			} );
			$imeSelector.on( 'change', function () {
				var inputmethod = $imeSelector.find( 'option:selected' ).val();
				imeselector.selectIM( inputmethod );
				imeselector.$element.focus();
			} );
			$langselector.on( 'change', function () {
				var language = $langselector.find( 'option:selected' ).val();
				imeselector.selectLanguage( language );
				listinputmethods( language );
			} );

			/* var element = document.getElementById('imeLanguage');
		    element.value = 'gu'; */
		    
		    $langselector.trigger( 'change' );
		};
		
		
		
		
		function newRecord() {
			window.location.replace("<%=request.getContextPath()%>/searchManuscript.action?requestId="+requestId);
		};
		
		$('.assignRecord').on('click', function(e) {
			var status = 0 ;
			if($(this).children('button').hasClass('btn-state-1')) {
				$(this).children('button').removeClass('btn-state-1');
				$(this).children('button').removeClass('btn-warning');
				$(this).children('button').addClass('btn-info');
				$(this).children('button').addClass('btn-state-2');
				$(this).children('button').text('Assigned');
				$(this).children('button').attr('disabled' , true);
				status = 1;
			} else if($(this).children('button').hasClass('btn-state-2')) {
				alert('Record already in process.');
			}
			console.log($(this).attr('href'));
			if(status ==1) {
				$.ajax({
			        url: $(this).attr('href'),  //Server script to process data
			        type: 'GET',
			        dataType: 'json',
				    cache: false,
			        xhr: function() {  // Custom XMLHttpRequest
			            var myXhr = $.ajaxSettings.xhr();
			        	/* If accurate progress is to be shown, it will be read through the myXhr variable */
			            return myXhr;
			        },
			        //Ajax events
			        success: function(data) {
			        	var menu = data.result;
			        	var string = "";
					    string +="<ul class='errorMessage'><li><span>"+menu.message+"</span></li></ul>";
					    $('#errorMessageBox').html(string);
				        $('#errorMessageBox').fadeIn();
			        },
			        error: function(data) {
			        	console.log(data);
			        },
			        //Options to tell jQuery not to process data or worry about content-type.
			        cache: false,
			        contentType: false,
			        processData: false
			    });
			}
			e.preventDefault();
		});
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