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
	
	.sortable {
    	list-style-type: none;
    	cursor: move;
	}
	</style>
	
	<script>
		var order;
		$(function() {
		//$( "#sortable" ).sortable();
		$(".sortable").sortable({
		    
		      update : function () {
		                order = $('.sortable').sortable('serialize');
		                console.log(order);
		            }
		          });
		$( ".sortable" ).disableSelection();
		
	});
		
		
	</script>
	
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
				Merge Manuscripts
			</h2>
			<s:form action="searchForManuscriptMerge" name="searchform" id="searchForm">
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
					<div class="col-xs-4 col-md-4">
						<s:hidden name="searchType" id="searchType"></s:hidden>
						<button type="button" class="btn btn-lg btn-primary btn-block" onClick="findAll();" style="visibility: hidden;">Find All</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="submit" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();">Search</button>
					</div>
				</div>
				<s:if test="listForPagingCombo != null && listForPagingCombo.size() > 0">
				
					<div class="form-group row" style="visibility: collapse;">
						<label class="col-xs-3 control-label" style="width : 20%;">Total Records: </label>
						<div class="col-xs-3" style="width:10%;">
							<s:text name="totalRecords"/>							
						</div>
						<div class="col-xs-4 col-md-4" style="float: right;">
							<button type="button" name="reorder" class="btn btn-lg btn-primary btn-block" onclick="makeReorder();" id="reorderButton">Reorder</button></div>
						<%-- <div class="col-xs-3" style="width:40%;" align="center">
						<input type="button" onclick="nextPrevious(1);" style="background-image: url('./assets/images/last.png');" class="searchbutton"/>
						<input type="button" onclick="nextPrevious(2);" style="background-image: url('./assets/images/previous.png');" class="searchbutton"/>
						<input type="button" onclick="nextPrevious(3);" style="background-image: url('./assets/images/next.png');" class="searchbutton"/>
						<input type="button" onclick="nextPrevious(4);" style="background-image: url('./assets/images/lastr.png');" class="searchbutton"/>
						</div>
						<label class="col-xs-3 control-label" style="width : 20%;">Show page No:</label>
						<div class="col-xs-3" style="width:10%;">
							<s:select  list="listForPagingCombo" required="true" id="pagerCombo"
											name="selectedPage" cssClass="pagerCombo" onchange="changePagerCombo()"/>
						</div> --%>
					</div>
					
					
					<div class="form-group row">
					      	  <div class="col-md-12">
						      	  <div class="alert alert-warning validation-warning">
						      	  	  <h6>Tip : Select the mansucripts (by checking the checkbox) you want to mergre and then reorder the checked mansucripts by dragging the checked manuscripts.
						      	  	  			The order of images in the merged manuscript will be in the order you order the manuscript here.
						      	  	  </h6>
								  </div>
					      	  </div>
				    </div>
				    
				</s:if>
			</s:form>
			<div class="errors" id="errorMessageBox">
				
			
			</div>
			<s:form action="mergeManuscript" id="frm">
				<div class="table-responsive">
					<table class="table" id="mytab1">
						<s:if test="#request.objResult.listOfElemnents.size() > 0">
							<thead>
								<th><b>Action</b></th>
								<th><b>Name</b></th>
								<th><b>Author</b></th>
								<th><b>Type</b></th>
							</thead>
							<tbody id="iteratorTblBody" class="sortable">
								<s:iterator id="userTO" value="digitalManuscriptVOs" status="var">
										<tr id="orderlist_<s:text name="digitalManuscriptVOs[%{#var.index}].id"></s:text>">
											<td>
												<s:hidden name="digitalManuscriptVOs[%{#var.index}].id"></s:hidden>
												<s:checkbox name="digitalManuscriptVOs[%{#var.index}].isMerging"></s:checkbox>
											</td>
	
											<td>
												<s:text name="digitalManuscriptVOs[%{#var.index}].name"></s:text>
											</td>
											<td><s:text name="digitalManuscriptVOs[%{#var.index}].authorVO.name"/></td>
											<td><s:text name="digitalManuscriptVOs[%{#var.index}].manuscriptType"/></td>
											
											<%-- <td>
												<s:if test="digitalManuscriptVOs[%{#var.index}].isMerging == false"></s:if>
												<s:textfield name="digitalManuscriptVOs[%{#var.index}].serial"/>
											</td> --%>
										</tr>
								</s:iterator>
							</tbody>
						</s:if>
					</table>
				</div>
				
				<s:if test="#request.objResult.listOfElemnents.size()> 0">
				
					<div class="form-group row buttons">
						<div class="col-xs-4 col-md-4">
							
						</div>
						<div class="col-xs-4 col-md-4">
							<button value="Merge" onclick="methodx()" class="btn btn-lg btn-primary btn-block">Merge</button>
						</div>
					</div>
				</s:if>
				
			</s:form>
			
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
		
		function makeReorder() {
			var dd = document.getElementById("reorderButton");
			console.log(dd);
			var name = dd.name;
			alert(name);
			
			if(name=="reorder") {
				var val = "Done";
				$("#reorderButton").text(val);
				
				$('#reorderButton').attr('name' , 'done');
				$('#iteratorTblBody').addClass("sortable");
				var order;
				$(".sortable").sortable({
				      update : function () {
				                order = $('.sortable').sortable('serialize');
				                console.log(order);
				            }
				          });
				$( ".sortable" ).disableSelection();
			} else if(name=="done") {
				var nm = 'Reorder';
				$("#reorderButton").text(nm);
				
				$('#reorderButton').attr('name','reorder');
				$(".sortable").sortable("disable");
				$('#iteratorTblBody').removeClass('sortable');
				$('#iteratorTblBody').removeClass('ui-sortable');
				$('#iteratorTblBody').addClass("doneSort");
			}
		};
	
		function methodx() {
			var factorial = sss(order);
		
			var action = $('#frm').attr('action');
			action += "?value="+factorial;
			
			$('#frm').attr('action', action);
		}
	
		function sss(order) {
		    if (order.indexOf('&orderlist[]=') == -1) {
		        return order;
		    }
		    return arguments.callee(order.replace('&orderlist[]=',','));
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
			window.location.replace("<%=request.getContextPath()%>/addManuscript.action?requestId="+requestId);
		};
		
	</script>
</html>