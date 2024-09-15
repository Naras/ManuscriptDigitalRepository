<html>
	<%@ include file='../layout/header.jsp' %>
	
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
		<%@ include file='../messagecontainer.jsp' %>
		<div class="form">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
			
			<h2 class="form-heading">
				Search Manuscripts
			</h2>
			<s:form action="searchForDerivedWork" name="searchform">
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
					<label class="col-xs-3 control-label">Category</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.categoryFkId" list="categoryVOs" required="true" id="categoryFkId"
										name="digitalManuscriptVO.categoryFkId" listKey="id" listValue="name" cssClass="form-control"/>
					</div>
					<label class="col-xs-3 control-label">Specific Category</label>
					<div class="col-xs-3">
						<s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.specificCategoryFkId" list="specificCategoryVOs" required="true" id="specificCategoryFkId"
										name="digitalManuscriptVO.specificCategoryFkId" listKey="id" listValue="name" cssClass="form-control"/>
										
					</div>
				</div>
				<div class="form-group row buttons">
					<div class="col-xs-4 col-md-4">
						<button type="button" class="btn btn-lg btn-primary btn-block" onClick="findAll();">Find All</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="submit" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();">Search</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="button" class="btn btn-lg btn-success btn-block" onClick="newRecord();">New record</button>
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
						<div class="col-xs-3" style="width:30%;" align="center">
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
							<s:select value="selectedPage" list="listForPagingCombo" required="true" id="pagerCombo" onchange="changePagerCombo();"
											name="selectedPage" cssClass="pagerCombo"/>
						</div>
					</div>
				</s:if>
			</s:form>
			
			<div class="table-responsive">
				<table class="table">
					<s:if test="#request.objResult.listOfElemnents.size() > 0">
						<thead>
							<th><b>Name</b></th>
							<th><b>Author</b></th>
							<!-- <th><b>Type</b></th>
							<th><b>Language</b></th>
							<th><b>Category</b></th>
							<th><b>Specific Category</b></th> -->
							<th><b>Update</b></th>
							<th><b>Delete</b></th>
						</thead>
						<tbody>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}">
								<s:iterator>
									<tr>
										<s:hidden value="id" />
										<td><s:property value="name" /></td>
										<td><s:property value="authorVO.name" /></td>
										<%--<td><s:property value="description" /></td>
										<td><s:property value="description" /></td>
										<td><s:property value="description" /></td>
										<td><s:property value="description" /></td> --%>
										<td><s:url id="editURL"
												action="getDerivedWorkById.action">
												<s:param name="id" value="%{id}"></s:param>
		
											</s:url> <s:a href="%{editURL}"><img style="padding-left: 35px;" height="20" alt=""	src="<%=request.getContextPath()%>/assets/images/update.png"/></s:a></td>
		
										<td><s:url id="deleteURL"
												action="deleteManuscriptaction.action">
		
												<s:param name="id" value="%{id}"></s:param>
		
											</s:url> <s:a href="%{deleteURL}" onClick="return deleteManuscript();"><img style="padding-left: 35px;" height="20" alt=""	src="<%=request.getContextPath()%>/assets/images/Delete.jpg"/></s:a></td>
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>
					</s:if>
				</table>
			</div>
		</div>
		
	</div>
	
	<%@ include file='../layout/footer.jsp'%>
	<script type="text/javascript">
		function deleteManuscript() {
			return confirm("Are you sure you want to delete this manuscript?");
		}
		
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
			document.searchform.submit();
				
		}
		
		function searchSpecific() {
			if(document.getElementById("pagerCombo") != null) {
				document.getElementById("pagerCombo").options[document.getElementById("pagerCombo").selectedIndex].value = "";
			}
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
		
		function newRecord() {
			window.location.replace("<%=request.getContextPath()%>/addManuscript.action?requestId="+requestId);
		}
	</script>
</html>