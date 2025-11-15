<html>
<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/paging.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/jquery-ui.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/searchformgenerator.js"></script>

	<%@include file='../layout/header.jsp'%>
	 <div class="about-page"> 
		<div class="container container-center" style="max-width: 80%;margin-left: 190px;" id="rep">
		<div class=" alert alert-success appTable centerdiv">
			<div class="appTable">
				<div class="panel-body">
				<div class="form-group row buttons">
					<div class="col-md-6">
						<input type="button" class="btn btn-lg btn-primary btn-block"
							value="Details Report" id="detailReport">
					</div>
					<div class="col-md-6">
						<input type="button" class="btn btn-lg btn-primary btn-block"
							value="Status Report" id="genericReport">
					</div>
					<div class="col-md-12">
					<!-- <img  src="/MDR/imageTest.action?requestId=-276633278" />
					<a href="/MDR/imageTest.action">sdgsd</a> -->
					</div>
				</div>
				<br>
				<div id="criteriaReport" style="display: none;">
					<s:form action="generateReportByCriteria" target="reportContainer" name="searchform" id="searchForm" onsubmit="removeRep();">
					<s:hidden id="reportPath" name="reportPath" />
					<s:hidden id="reportType2" name="reportType" />
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
								<label class="col-xs-2 control-label">Document Status</label>
								<div class="col-md-4">
									 <s:select list="manuscriptDocumentationTypes" cssClass="form-control"
							              name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" maxlength="50"/>
								 </div>
								
								<%-- <label class="col-xs-2 control-label">Document Category</label>
								<div class="col-xs-4">
									<s:select value="digitalManuscriptVO.manuscriptType" list="recordStatusList" 
												required="true" id="recordStatusList" name="digitalManuscriptVO.manuscriptType" cssClass="form-control"/>
								</div> --%>
							</div>
							
							<%-- <div class="form-group row" style="margin-bottom: 0px;">
								<label class="col-xs-2 control-label">Having Content...</label>
								<div class="col-xs-4">
									<s:textfield name="digitalManuscriptVO.summary" maxlength="50" cssClass="form-control" id="summary"/>
								</div>
								<label class="col-xs-2 control-label">Document Status</label>
								<div class="col-md-4">
									 <s:select list="manuscriptDocumentationTypes" cssClass="form-control"
							              name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" maxlength="50"/>
								 </div>
							</div> --%>
							
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
							
							<%-- <div class="form-group row" style="margin-bottom: 0px;">
								<label class="col-xs-2 control-label">Minimum Folios</label>
								<div class="col-xs-4">
									<s:textfield name="digitalManuscriptVO.minimumFolios" maxlength="50" cssClass="form-control" id="minFolios"/>
								</div>
								<label class="col-xs-2 control-label">Maximum Folios</label>
								<div class="col-md-4">
									<s:textfield name="digitalManuscriptVO.maximunFolios" maxlength="50" cssClass="form-control" id="maxFolios"/>
								 </div>
							</div> --%>
							
							<div class="form-group row" style="margin-bottom: 0px;">
								<label class="col-xs-2 control-label">Author</label>
								<div class="col-xs-4">
									 <s:select list="authorList" required="true" id="authorId" headerKey="-1" headerValue="Unselected"
													name="digitalManuscriptVO.authorFKId" listKey="id" listValue="name" cssClass="form-control"/>
								</div>
								<label class="col-xs-2 control-label">Specific Category</label>
								<div class="col-md-4">
									  <s:select list="specificCategoryVOs" required="true" id="specificCategoryId" headerKey="-1" headerValue="Unselected"
													name="digitalManuscriptVO.specificCategoryId" listKey="id" listValue="name" cssClass="form-control"/>
								 </div>
							</div>

							<div class="form-group row">
								<label class="col-xs-2 control-label">Export as </label>
								<div class="col-xs-4">
									<s:radio label="Export As" name="exportDocumentType" list="#{'doc':'DOC','pdf':'PDF'}" value="doc" />
								</div>
								<label class="col-xs-2 control-label"> </label>
								<div class="col-md-4">

								</div>
							</div>
							<div class="form-group row buttons">
								<div class="col-xs-4 col-md-4">
								
								</div>
								<div class="col-xs-4 col-md-4">
								<s:submit cssClass="btn btn-lg btn-primary btn-block" value="Get Report"></s:submit>
									<!-- <button type="submit" class="btn btn-lg btn-primary btn-block" onclick="searchSpecific();" >Search</button> -->
								</div>
								<div class="col-xs-4 col-md-4">
								 <!-- <button type="button" class="btn btn-lg btn-success btn-block" onClick="newRecord();">Reset</button> -->
									<%-- <s:reset cssClass="btn btn-lg btn-success btn-block"/> --%>
								</div>
								<!-- <div class="col-xs-3 col-md-3">
								</div> -->
							</div>
			     </s:form>
				</div>
				
				</div>
				</div>
				</div>
				</div>
				<div class="container" style="width: 65%" align="center" >
		<iframe id="reportContainer" name="reportContainer" height="1000" width="100%" frameborder="0"
				src="<%=request.getContextPath()%>/jsp/report/report.jsp"></iframe> 
				</div>
			 </div>

    <%@ include file='../layout/footer.jsp' %>
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
	src="${pageContext.servletContext.contextPath}/assets/js/jquery-ui.js"></script>s
    	<script type="text/javascript">
    	$(document).ready(function() {
    		$('#beginningLine, #endingLine').ime();
    		});
    	<%
		String requestd = (String) request.getAttribute("requestId");
	%>
				<%-- function generateStatusReport(){
					var status = $('#documentStatus').val();
					var docType = 1;
					document.frm.action='generateReportByCriteria.action?requestId='+<%=requestd%>+'&jasperPath=/report/StatusReport.jasper&status='+ status +'&type='+docType;
					 document.frm.submit();
					 $('#rep').css('display','none');
				} --%>
				$('#genericReport').click(function(){
					$('#statusRow').css('display','none');
					$('#criteriaReport').css('display','block');
					$('#reportPath').val("/report/Recortreport.jasper");
					$('#reportType2').val("0");
					 var docType = 0;
					//document.frm.action='generateReportByCriteria.action?requestId='+<%=requestd%>+'&jasperPath=/report/Recortreport.jasper&status=Under_Publisher&type='+docType;
				   // document.frm.submit();
				  // $('#rep').css('display','none');
				});
				/* $('#StatusReport').click(function(){
					$('#criteriaReport').css('display','none');
					$('#statusRow').css('display','block');
					$('#reportPath1').val("/report/StatusReport.jasper");
					$('#reportType2').val("1");
				}); */
				$('#detailReport').click(function(){
					$('#statusRow').css('display','none');
					$('#criteriaReport').css('display','block');
					$('#reportPath').val("/report/MDRManuscriptDetailsReport.jasper");
					$('#reportType2').val("0");
					 var docType = 0;
					//document.frm.action='generateReportByCriteria.action?requestId='+<%=requestd%>+'&jasperPath=/report/MDRManuscriptDetailsReport.jasper&status=Under_Publisher&type='+docType;
				   // document.frm.submit();
				  // $('#rep').css('display','none'); 
				});
				function removeRep(){
					$('#rep').css('display','none'); 
				}
				</script>
    	