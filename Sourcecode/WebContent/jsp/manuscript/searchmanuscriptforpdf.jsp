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
			<s:form action="searchManuscriptRecordForPDF" name="searchform" id="searchForm">
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
					<label class="col-xs-3 control-label">Manuscript Status</label>
					<div class="col-md-3">
						 <s:select list="manuscriptDocumentationTypes" cssClass="form-control"
				              name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" maxlength="50"/>
					 </div>
				</div>
				<%--<s:hidden value="Published" name="digitalManuscriptVO.recordStatus" />--%>
				 <div class="form-group row">
					<label class="col-xs-3 control-label">Document Status</label>
					<div class="col-xs-3">
						<s:select value="digitalManuscriptVO.recordStatus" list="documentStatusList" 
									required="true" id="documentStatusList" name="digitalManuscriptVO.recordStatus" cssClass="form-control"/>
					</div>
					<label class="col-xs-3 control-label">Specific Category</label>
					<div class="col-xs-3">
						 <s:select list="specificCategoryVOs" required="true" id="specificCategoryId"
										name="digitalManuscriptVO.specificCategoryId" listKey="id" listValue="name" cssClass="form-control" multiple="true"/>
										
					</div>
				</div>
				 
				
				<div class="form-group row buttons">
					<div class="col-xs-4 col-md-4" style="visibility: hidden;">
						<s:hidden name="searchType" id="searchType"></s:hidden>
						<button type="button" class="btn btn-lg btn-primary btn-block" onClick="findAll();">Find All</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="submit" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();">Search</button>
					</div>
					<div class="col-xs-4 col-md-4" style="visibility: hidden;">
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
							<th><b>Name</b></th>
							<th><b>Author</b></th>
							<th><b>Frames</b></th>
							<th><b>PDF</b></th>
							<th><b>RTF</b></th>
						</thead>
						<tbody>
						<%int i= -1; %>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}" status="var">
							<% i++; %>
								<s:iterator>
									<tr>
										<s:hidden  id="%{#var.index}" name="id" />
										<td><s:property value="name" /></td>
										<td><s:property value="authorVO.name" /></td>
											<td style="text-align: center;">
										<s:if test="presentFrame">
										<s:property value="totalFrame" />
										</s:if><s:else>0</s:else>
										</td>
										<td align="left">
										<s:if test="presentFrame">
												<input type="button" style="width: 60px;" class="btn btn-lg btn-success btn-block btn-state-1" value="PDF" onclick="manuscriptReport(<%=i%>,'pdf')">
										</s:if><s:else>
										<input type="button" style="width: 60px;" class="btn btn-lg btn-success btn-block btn-state-1" value="N/A">
										</s:else>
										</td>
										<td align="left">
										<s:if test="presentFrame">
												<input type="button" style="width: 60px;" class="btn btn-lg btn-success btn-block btn-state-1" value="RTF" onclick="manuscriptReport(<%=i%>,'rtf')">
												</s:if><s:else>
										<input type="button" style="width: 60px;" class="btn btn-lg btn-success btn-block btn-state-1" value="N/A">
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
	   <div id="exportingForm" style="display:none">
         <fieldset>
                       <div class="form-group row">
				          <div class="col-md-10">
				          <s:hidden id="reportType"></s:hidden>
				          <s:hidden id="pdfManuscriptId"></s:hidden>
				                  <input type="radio" name="selectreport" value="1" id="rd0"/>Export Derived Text
				          </div>
				        </div>
                       <div class="form-group row">
				          <div class="col-md-10">
				                  <input type="radio" name="selectreport" value="2" id="rd1"/>Export Full Manuscript
				          </div>
				        </div>
				        <div class="form-group row ">
				          <div class="col-md-10">
				              <input type="radio" name="selectreport"  value="3" id="rd2"/>Export Manuscript With Derivedwork
				          </div>
				        </div>
				        <div class="form-group row ">
				          <div class="col-md-10">
				                <input type="radio" name="selectreport"  value="4" id="rd3"/>Export Manuscript For Selected Page
				          </div>
				        </div>
				         <div class="form-group row " style="display:none" id="fromto">
				          <label class="col-md-2 control-label">From</label>
				          <div class="col-md-4">
		                  <s:textfield cssClass="form-control" id="startPage"></s:textfield>
				          </div>
				           <label class="col-md-2 control-label">To</label>
				          <div class="col-md-4">
		                  <s:textfield cssClass="form-control" id="endPage"></s:textfield>
				          </div>
				        </div>
				       <div class="form-group row ">
				          <div class="col-md-8">
				                <input type="button" value="OK" onclick="generateSelectedReport()"/>
				          </div>
				        </div>
        </fieldset>
 
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
		
		function newRecord() {
			window.location.replace("<%=request.getContextPath()%>/addManuscript.action?requestId="+requestId);
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
		
		$('#rd3').click(function() {
			$( "#fromto" ).show();
			});
		$('#rd0').click(function() {
			$( "#fromto" ).hide();
			});
		$('#rd1').click(function() {
			$( "#fromto" ).hide();
			});
		$('#rd2').click(function() {
			$( "#fromto" ).hide();
			});
		function manuscriptReport(rowNo,type){
			var manuscriptId=$('#'+rowNo).val();
			$('#pdfManuscriptId').val(manuscriptId);
			$('#reportType').val(type);
			var opt = {
			        autoOpen: false,
			        width: 400,
			        height:280,
			        title: 'Selective Exporting',
			        modal: true
			};
		$( "#exportingForm" ).dialog(opt).dialog('open');
			
		}
		function generateSelectedReport(){
			<%
			String requestdp = (String) request.getAttribute("requestId");
		%>
			$( "#exportingForm" ).dialog('close');
			var manuscriptId =$('#pdfManuscriptId').val();
			var radios = document.getElementsByName("selectreport");
			var docType =$('#reportType').val();
			for(var i = 0; i<radios.length; i++){
			    if (radios[i].checked) {
			    	radioId= radios[i].value;
			        break;
			    }
			}
			if(radioId == 1){
				window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestdp%>+ '&jasperPath=/report/OnlyTextRepot.jasper&id='+manuscriptId +'&type='+docType+'&startPage=0&endPage=0&recordType=0';
			}else if(radioId == 2){
				window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestdp%>+ '&jasperPath=/report/FullManuscriptWithFrame.jasper&id='+manuscriptId +'&type='+docType+'&startPage=0&endPage=0&recordType=0';
			}else if(radioId == 3){
				window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestdp%>+ '&jasperPath=/report/ManuscriptReport.jasper&id='+manuscriptId +'&type='+docType+'&startPage=0&endPage=0&recordType=0';
			}else if(radioId == 4){
				stPage=$('#startPage').val();
				ndPage=$('#endPage').val();
				window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestdp%>+ '&jasperPath=/report/FullManuscriptWithFrame.jasper&id='+manuscriptId +'&type='+docType+'&recordType=0&startPage='+stPage+'&endPage='+ndPage;
			};
		};
	</script>
</html>