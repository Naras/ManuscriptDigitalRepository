<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/bootbox.min.js"></script>
<style type="text/css">
.accordion-heading
{
    background-color:#0CF;
}
.accordion-heading:hover
{
    background-color:#000;
    -webkit-transition: all 0.5s ease-in-out;
    -moz-transition: all 0.5s ease-in-out;
    -o-transition: all 0.5s ease-in-out;
    transition: all 0.5s ease-in-out;
}
.accordion-heading > a
{
    color:#FFF; 
    text-decoration:none; 
    text-transform:uppercase;
}
.pa {
	background: 
}
#accordionPanelHeader , #accordionPanelHeader1 {
	background: #DFDDDD;
	 border: 1px solid #DFDDDD;
}

.glyphiconmargin {
	float:right;
	margin-right: 20px;
	margin-top: 8px;
}

</style>


<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui-1.10.4.custom.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/assets/css/viewimage.css"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui.css"
	rel="stylesheet" />
<div class="form" style="padding-top: -50px;">
	<div class="container" style="padding-top: 0px;">
		<div class="alert alert-danger hide container-center"
			id="msg-container"></div>
		<div class="container-center">
			<%@ include file='../messagecontainer.jsp'%>
		</div>
		<s:form action="addUpdateDigitalDocument" id="derivedWorkForm" enctype="multipart/form-data">
			<s:hidden id="manuscriptId" name="digitalManuscriptVO.id" />
			<s:hidden name="presentReviewer2" id="isPresentReviewer2" />
			<s:hidden id="scribeId" name="digitalManuscriptVO.scribeVO.id" />
			<s:hidden id="commentatorId"
				name="digitalManuscriptVO.commentatorVO.id" />
			<s:hidden id="translatorId"
				name="digitalManuscriptVO.translatorVO.id" />
			<s:hidden id="organisationId"
				name="digitalManuscriptVO.organisationVO.id" />
			<s:hidden id="organisationType"
				name="digitalManuscriptVO.organisationVO.type" />
			<s:hidden id="publisherId" name="publicationVO.publisherVO.id" />
			<s:hidden id="editorId" name="publicationVO.editorVO.id" />
			<s:hidden id="publicationId"
				name="digitalManuscriptVO.publicationVO.id" />
			<s:hidden id="isAvailableValue" name="publicationVO.isAvailable" />
			<s:hidden id="documentType" name="digitalManuscriptVO.documentType" />
			<s:hidden id="nmmDetailsId"
				name="digitalManuscriptVO.nmmDetailsVO.id" />
			<s:hidden id="filePathContainer"
				name="digitalManuscriptVO.filePathContainer" />
			<s:hidden id="fileDiskPathContainer"
				name="digitalManuscriptVO.fileDiskPathContainer" />
			<s:hidden name="digitalDocumentVO.id" />
			<s:hidden id="digitalManuscriptFkId" name="digitalDocumentVO.digitalManuscriptFkId" />
			<s:hidden id="frameId" name="manuscriptFrameId" />
			<s:hidden id="realFrameName" name="digitalManuscriptFrameFkId" />
			<s:hidden id="manuscriptText" name="digitalDocumentVO.detailsVO.text" />
			<s:hidden id="manuscriptAttachment" name="digitalDocumentVO.detailsVO.attachmentFilePath" />
			<s:hidden id="audioPath"/>

			<s:url id="pdfURL" action="exportReportFrame.action">
				<s:param name="type" value="%{'pdf'}"></s:param>
				<s:param name="jasperPath" value="%{'/report/FRLHT1.jasper'}"></s:param>
			</s:url>
			<s:url id="rtfURL" action="exportReportFrame.action">
				<s:param name="type" value="%{'rtf'}"></s:param>
				<s:param name="jasperPath" value="%{'/report/FRLHT1.jasper'}"></s:param>
			</s:url>
				<s:url id="mpdfURL" action="exportReportFrame.action">
				<s:param name="type" value="%{'pdf'}"></s:param>
				<s:param name="jasperPath" value="%{'/report/OnlyTextRepotManuscript.jasper'}"></s:param>
			</s:url>
			<s:url id="mrtfURL" action="exportReportFrame.action">
				<s:param name="type" value="%{'rtf'}"></s:param>
				<s:param name="jasperPath" value="%{'/report/OnlyTextRepotManuscript.jasper'}"></s:param>
			</s:url>

			<br>
			<h3>
				Online Derived Work - <span style="color: #FFA500;"><s:text
						name="digitalManuscriptVO.name"></s:text></span>
			</h3>
			<input type="radio" name="transcriptionType" id="frameLabel"> 
			<label>Frame</label>  
			<input type="radio" name="transcriptionType" id="manuscriptLabel">
			<label>Manuscript</label> 
			<div id="frame">
				<div class="image-details">
					<div
						class="left-column frame-filter-container resizable-h ui-widget-content ui-resizable">
						<div class="image-container panel" style="overflow-y: scroll;">
							<div class="panel-header">Frames
								<span class="glyphicon glyphicon-picture glyphiconmargin"></span>
							</div>
							<div class="panel-body" oncontextmenu="return false"></div>
						</div>
						<div style="overflow-y: scroll;">
							<div class="panel-header">Thumbnail Color Code Info<span class="glyphicon glyphicon-th-list glyphiconmargin"></span>
							</div>
							<div style=" height: 30px;margin-left: 15px;margin-top: 10px;">
								<span class="label label-primary">Edited</span>
								<span class="label label-success">Current</span>
								<span class="label label-danger">Last Edited</span>
							</div>
						
						</div>
						<div class="tag-container panel" style="overflow-y: scroll;">
							<div class="panel-header">Tags<span class="glyphicon glyphicon-tags glyphiconmargin"></span>
							</div>
							<div class="tag-data" style="padding-top: 10px;">
								<s:iterator value="digitalManuscriptVO.tagList" status="var">

									<div class='tag-data-shell'>
										<s:hidden id="%{#var.index}"
											name="digitalManuscriptVO.tagList[%{#var.index}].id"></s:hidden>
											<s:property value="name"/>
										<%-- <s:textfield cssStyle="border-width: 0px;width: 100%;"
											name="digitalManuscriptVO.tagList[%{#var.index}].name" readonly="true"></s:textfield> --%>
										<!--  <a href='#' class='thumbnail-close'>×</a> -->
									</div>
								</s:iterator>
							</div>
						</div>
					</div>
					<div
						class="middle-column resizable-h ui-widget-content ui-resizable">
						<div class="frame-controlbar">
							<s:if test="#session.currentRole == 7">

							<s:hidden name ="pdf"></s:hidden>
								<div class="col-md-4" id="frameLabelExport">

									<div class="frame-controlbar-button">
										
										<button type="button"
											class="btn btn-lg btn-info btn-block btn-state-2"
											onclick="manuscriptReport('pdf');">PDF</button>

									</div>
									<div class="frame-controlbar-button">
										<%-- <s:a href="%{rtfURL}" id="rtf" onclick="append('rtf');"> --%>
										<button type="button"
											class="btn btn-lg btn-info btn-block btn-state-2"
											onclick="manuscriptReport('rtf');">RTF</button>
										<%-- </s:a> --%>
									</div>
									<div class="frame-controlbar-button">
										<button type="button"
											class="btn btn-lg btn-info btn-block btn-state-2" id=""
											onclick="ajaxCallForNMM();">NMM</button>
									</div>
									</div>
									
									<div class="col-md-4" id="manuscriptLabelExport" style="display: none">
									<div class="frame-controlbar-button">
										<s:a href="%{mpdfURL}" id="mpdf" onclick="append('mpdf');">
										<button type="button"
											class="btn btn-lg btn-info btn-block btn-state-2" id="">PDF</button>
									</s:a>

									</div>
									<div class="frame-controlbar-button">
										<s:a href="%{mrtfURL}" id="mrtf" onclick="append('mrtf');">
										<button type="button"
											class="btn btn-lg btn-info btn-block btn-state-2" id="">RTF</button>
									</s:a>
									</div>
									</div>
							</s:if>
							<s:else>
								<div class="col-md-4"></div>
							</s:else>
							<div class="col-md-4 frame-search" oncontextmenu="return false">
								<div class="col-md-10">
									<s:textfield placeholder="search..." cssClass="form-control" id="searchText"/>
								</div>
								<div class="col-md-2">
									<img
										src="${pageContext.servletContext.contextPath}/assets/images/search.png"
										width=30em height=30em onclick="searchWord();">
								</div>
							</div>
							<div class="col-md-4 ime-controlbar">
								<div class="col-md-6">
									<select id="imeLanguage" name="imeLanguage"
										class="form-control"></select>
								</div>
								<div class="col-md-6">
									<select id="imeSelector" name="imeSelector"
										class="form-control"></select>
								</div>
							</div>
						</div>
						<div class=" frame-container" oncontextmenu="return false" align="center">
							<div class="frame-controller">
								<span class="col-md-4 control-label" id="zoomin">+</span> <span
									class="col-md-4 control-label" id="zoomout">-</span>
									 <span
									class="col-md-4 control-label" id="fit"> <img
									src="${pageContext.servletContext.contextPath}/assets/images/reload.png"
									width=20em height=20em>
								</span>
							</div>
							<div class="frame-nextprev" >
								<span class="col-md-4 control-label" id="prevImg"
									style="padding: 10px;">Prev</span> <span
									class="col-md-4 control-label" id="nextImg"
									style="padding: 10px 0px 10px 30px;">Next</span>
							</div>
							
							<img src="" id="frame-img">

							<span class="col-md-3 control-label waterMark"
									style="padding: 10px 0px 0px 0px;">FRLHT</span>
						</div>
						<div class="button-container">
						<label id="audioLable" class="col-md-3 control-label" style="padding: 0px;  width: 140px; margin-top: 8px;">Upload Audio/Video</label>
							<div class="col-md-1" style="padding: 0px;" id="audioBrowse" >
								<span class="btn btn-default btn-file btn-warning">
									<span>Browse</span>
									<input name="audioVideo" type="file" id="audioVideo" multiple="multiple"/>
								</span>
						
							</div>
							<div class="col-md-1"  style="padding: 0px;overflow: hidden; width:11%;" id="audioInfo" >
								<span class="file-info"></span>
							</div>
						  <div class="col-md-1"  style="padding: 0px; width: 5%;" >
							<input id="audioCancel" type="button" class="btn btn-lg btn-primary btn-block"
							     value="Cancel" style="width: 100%; height: 40px; padding-top: 7px; font-size: 12px; display: none;" onclick="cancelAudio();">
							</div>
							<div  id="embedFilepath" class="col-md-4" style="padding: 0px; margin-top: 0px; width: 28%;display: none;">
								</div>
								<div class="col-md-2" id="audioEdit" style="display: none;">
								<input type="button" class="btn btn-lg btn-primary btn-block"
							     value="Edit" style="width: 50%; height: 40px; padding-top: 7px;" onclick="editAudio();">
								</div>
							<div class="col-md-2 control-label display-span" style="width: 15%">
							    <span>Frame No:</span> 
								<span id="currentImg"></span> 
								<span>/</span> 
								<span id="totalImg"></span>
								</div>
							<s:if test="#session.currentRole == 7">
								<div class="col-md-1" style="float: right;">
									<s:a href="%{pdfURL}" id="fpdf" onclick="append('fpdf');">
										<button type="button"
											class="btn btn-lg btn-warning btn-block btn-state-1" id="">PDF</button>
									</s:a>
							</div>

								<div class="col-md-1" style="padding-right: 25px; float: right;">
									<s:a href="%{rtfURL}" id="frtf" onclick="append('frtf');">
										<button type="button"
											class="btn btn-lg btn-warning btn-block btn-state-1" id="">RTF</button>
									</s:a>
								</div>
							</s:if>
						</div>

						<div class="transcriber-container">
							<s:textarea 
								cssClass="form-control transcriber" rows="11" name="transcribedText"></s:textarea>
								<div class="searchDiv" style="display: none;" id="showdiv" onclick="replace();"></div>
						</div>
					</div>
					<div class="right-column ui-widget-content ui-resizable" style="border: none;">
						<div style="height: 55%; box-shadow: none;">
								<div class="panel-group" id="accordion1">
									<div id="accordionPanelHeader" class="panel panel-default">
										<div class="panel-heading" data-target="#collapseOne1" data-toggle="collapse" data-parent="#accordion1" style="background: #DFDDDD;height: 22px;">
											<label style="font-weight: normal;font-size: 14px;cursor: pointer;margin-top: 2px;">Summary</label>	<span class="glyphicon glyphicon-book glyphiconmargin" style="margin-top: 5px;"></span>
										</div>
										<div id="collapseOne1" class="panel-collapse collapse in">
											<div class="panel-body"
												style="background:#ffffff; overflow-y: scroll; display: block; margin-top: 1%; height: 74%;font-family: trebuchet ms ; font-size: 11.5px;font-style: italic;">
												<s:textarea readonly="true" cssStyle="width:100%;height:100%;font-size: 13px;font-style: italic;background-image: url('/OMDS/assets/images/images.jpg');" name="digitalManuscriptVO.summary" />
											</div>
										</div>
									</div>
									<div id="accordionPanelHeader2" class="panel panel-default">
									
										<div class="panel-heading" data-target="#collapseTwo1" data-toggle="collapse" data-parent="#accordion1" style="background: #DFDDDD;height: 26px;">
											<label style="font-weight: normal;font-size: 14px;cursor: pointer; margin-top: 2px;">Other Info</label>	<span class="glyphicon glyphicon-paperclip glyphiconmargin" style=" margin-top: 5px;"></span>
										</div>
										<div id="collapseTwo1" class="panel-collapse collapse">
											<div class="panel-body" style="background:#ffffff; margin-top: 1%; margin-right: 1%; height: 74%; background-image: url('/OMDS/assets/images/images.jpg');">
												<div>
													<label class="control-label">Author:</label>
													<s:property value="digitalManuscriptVO.authorVO.name" />
												</div>
												<div>
													<label class="control-label">Source:</label>
													<s:property value="digitalManuscriptVO.organisationVO.name" />
												</div>
												<div>
													<label class="control-label">Manuscript Id:</label>
													<s:property value="digitalManuscriptVO.manuscriptId" />
												</div>
												<div>
													<label class="control-label">Material:</label>
													<s:property value="digitalManuscriptVO.materialVO.name" />
												</div>
												<div>
													<label class="control-label">Type Of Work:</label>
													<s:property value="digitalManuscriptVO.typeOfWork" />
												</div>											
											</div>
										</div>
									
									</div>
									<div class="panel panel-default" id="accordionPanelHeader1">
										<div class="panel-heading" data-toggle="collapse" data-parent="#accordion1" data-target="#collapseThree1" style="background: #DFDDDD;margin-top: 1px;">
											<label style="font-weight: normal;font-size: 14px;cursor: pointer;">NMM Image Details (Manuscript)</label><span class="glyphicon glyphicon-info-sign glyphiconmargin" style=" margin-top: 3px;"></span>
										</div>
										<div id="collapseThree1" class="panel-collapse collapse" >
											<div class="panel-body" style="background:#ffffff; margin-top: 1%; margin-right: 1%; height: 74%; background-image: url('/OMDS/assets/images/images.jpg');">
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Height:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.height" />
													</div>
												</div>
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Width:</label>
													<div class="col-md-8">
														<s:property value="digitalManuscriptVO.nmmDetailsVO.width" />
													</div>
												</div>
												
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Make:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.cameraMake" />
													</div>
												</div>
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Model:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.cameraModel" />
													</div>
												</div>
												<div class="nmm-fields">
													<label class="col-md-4 control-label">X Res:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.xResolution" />
													</div>
												</div>
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Y Res:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.yResolution" />
													</div>
												</div>
												
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Created:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.createdDate" />
													</div>
												</div>
												<div class="nmm-fields">
													<label class="col-md-4 control-label">Digitised:</label>
													<div class="col-md-8">
														<s:property
															value="digitalManuscriptVO.nmmDetailsVO.digitisedDate" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

						</div>
						<div
							style="height: 45%; box-shadow: none;">
							<div class="panel panel-success">
								<div class="panel-heading" style="height: 30px">Comments<span class="glyphicon glyphicon-comment glyphiconmargin" style="margin-top: 8px;"></span></div>
								<div>
									<div class="container-center" id="comments"
									style="overflow-y: scroll; display: block; margin-top: 1%; height: 75%;">
								</div>
								<div class="col-md-6" style="padding-bottom: 5px;">
									<button type="button" class="btn btn-lg btn-success btn-block"
										id="newcomment" style="margin-top: 4%; background: #D6E9C6 ; border: #D6E9C6; width:180px;"><span class="glyphicon glyphicon-plus glyphiconmargin" style="margin-top: 0px;"></span> Add Comment</button>
								</div>

							</div>

						</div>

							</div>
						</div>

					</div>
				</div>
			<div id="nmmcontent" style="display: none"></div>
			<s:if test="isVisibleSave != 0">
				<div class="form-group row buttons container-center" style="width: 30%;">
					<div class="col-md-4">
						<input type="button" class="btn btn-lg btn-primary btn-block"
							value="Save" style="width: 142px;" onclick="owdSave();">
					</div>
					<s:if test="#session.currentRole == 6 && presentReviewer2 != 1">
						<div class="col-md-4">
							<input type="button" onclick="branchWflToUser();"
								class="btn btn-lg btn-success btn-block"
								style="width: 145px;" value="Submit to verify">
						</div>
					</s:if>
					<%-- <div class="col-md-4">
						<select id='branchTo' class="form-control"></select>
					</div> --%>
					
				</div>
			</s:if>
			<s:else>
				<div class="form-group row buttons container-center">
					<div class="col-md-4">
						<!-- <input type="button" class="btn btn-lg btn-primary btn-block" value="previous" id="previous-manuscript"> -->
					</div>
					<div class="col-md-4">
						<s:url id="assignedToMe" action="assignedToReviewer.action">
							<s:param name="currentWFLDtsid"
								value="%{currentProcessDetailsId}"></s:param>
							<s:param name="currentprocessmasterid"
								value="%{currentProcessMasterFKId}"></s:param>
						</s:url>
						<s:a href="%{assignedToMe}" cssClass="assignRecord">
							<button type="button" class="btn btn-lg btn-primary btn-block ">Assign
								to me</button>
						</s:a>
					</div>
				</div>
			</s:else>
		</s:form>
	</div>
</div>
	  <div id="exportingForm" style="display: none">
				<s:form>
					<fieldset>
					<div class="form-group row">
							<div class="col-md-10">
								<s:hidden id="reportType"></s:hidden>
								<input type="radio" name="selectreport" value="1" id="rd0"/>Export Derived Text
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-10">
								<input type="radio" name="selectreport" value="2" id="rd1"/>Export Full
								Manuscript
							</div>
						</div>
						<div class="form-group row ">
							<div class="col-md-10">

								<input type="radio" name="selectreport" value="3" id="rd2"/>Export
				             Manuscript With Derived Work
							</div>
						</div>
						<div class="form-group row ">
							<div class="col-md-10">
								<input type="radio" name="selectreport" value="4" id="rd3" />Export
								Manuscript For Selected Page
							</div>
						</div>
						<div class="form-group row " style="display: none" id="fromto">
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
								<input type="button" value="OK"
									onclick="generateSelectedReport()" />
							</div>
						</div>
					</fieldset>
				</s:form>
			</div>
				<div id="comment-form" title="Create new comment"
				style="display: none">
				<p class="validateTips">All form fields are required.</p>
				<s:form>
					<fieldset>
						<div class="form-group row manuscript-specific">
							<label class="col-md-4 control-label">Date :</label>
							<div class="col-md-8">
								<s:textfield cssClass="form-control" id="commentDate"
									maxlength="50" readonly="true" />
							</div>
						</div>

						<div class="form-group row manuscript-specific">
							<label class="col-md-4 control-label">Commented By :</label>
							<div class="col-md-8">
								<s:textfield cssClass="form-control" id="commentAuthor"
									name="accNumber" maxlength="50" readonly="true" />
							</div>
						</div>

						<div class="form-group row manuscript-specific">
							<label class="col-md-4 control-label">Comment :</label>
							<div class="col-md-8">
								<s:textarea cssClass="form-control" id="commentBox"
									cssStyle="height:165px;"></s:textarea>
							</div>
						</div>
						<div class="container-center" style="padding-left: 35%;">
						 <input type="button"
								value="Publish Comment" id="publishCommentButton"
								style="width: 50%;" onclick="savecomment();"></input>
							<input type="button" value="Cancel" onclick="removepopup();"
								style="width: 30%;" />
						</div>
						<div>
						
						</div>

					</fieldset>
				</s:form>
			</div> 
			<div id="loadingimage" style="display:none;text-align: center;">
			<img src="<%=request.getContextPath()%>/assets/images/loading_new.gif" width=60 height=60>
			</div>
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
	src="${pageContext.servletContext.contextPath}/assets/js/jquery-panzoom.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.mousewheel.js"></script>
<script type="text/javascript">
	$(function() {
		$( "#accordion" ).accordion();
	});
	
	var contextPath = "";
	var frameId = "";
	
	$(document).ready(function() {
		addManuscriptConstructor("<%=request.getContextPath()%>");
		if($('#fileDiskPathContainer').val().length > 0) {
			var countImage=0;
			var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
			var htmlString = "";
			var filePath;
			for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
				 filePath = fileDiskPathObject[i].filePath.replace(/\\/g, "/");
				 if(fileDiskPathObject[i].isLast==1){
					 htmlString += "<div id='div-"+countImage+"' class='img-thumbnail' style='background-color: #eb7676; border:1px solid #7d0000;position: relative;'>"; 
				 }else{
				 if(fileDiskPathObject[i].text==""){
					 htmlString += "<div id='div-"+countImage+"' class='img-thumbnail' style='position: relative;'>"; 
				 }else{
				   htmlString += "<div id='div-"+countImage+"' class='img-thumbnail' style='background-color: #88b1cb; border:1px solid #133b55;position: relative;'>";
				 }
				 }
				 if(fileDiskPathObject[i].attachmentPath!=null && fileDiskPathObject[i].attachmentPath!=""){
					 htmlString +="<div class='mp3Mark glyphicon glyphicon-music' style='width: 13px;'></div>";
				 }
				//htmlString += "<a href='#' class='thumbnail-close'>×</a>";
				htmlString += "<img src='" + contextPath + '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=40em height=40em class='image-link-container' style='border:1px solid black;'>";
				htmlString += "</div>";
				countImage++;
			}
			 $("#totalImg").text(countImage);
			$('.image-container .panel-body').append(htmlString);
			 $('#frameLabel').attr('checked',true);
			imageChange(0,0);
		} else {
			$('.frame-container').hide();
		}
		
		if($('#nmmDetailsId').val() > 0) {
			$('.image-details').show();
		}
		
		initializeIme();
		$( "#accordion" ).accordion({heightStyle: 'content'});
		//$('.transcriber').ime();
		//$('.resizable').resizable();
		$('.resizable-h').resizable({
			handles:'e '
		});
		initPanZoom();
	});
	
			function getXMLHttpRequest() {
				var xmlHttpReq = false;
				// to create XMLHttpRequest object in non-Microsoft browsers
				if (window.XMLHttpRequest) {
					xmlHttpReq = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					try {
						// to create XMLHttpRequest object in later versions of Internet
						// Explorer
						xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (exp1) {
						try {
							// to create XMLHttpRequest object in older versions of Internet
							// Explorer
							xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (exp2) {
							xmlHttpReq = false;
						};
					};
				}
				return xmlHttpReq;
			}
			function ajaxCallTest(sapData) {
				<%
				String requestd = (String) request.getAttribute("requestId");
			%>
			var audioPath = "";
			if(!($('#manuscriptLabel').prop('checked'))){
				audioPath=$('#audioPath').val();
			}
			$('#frame-img').css('height','50px');
			$('#frame-img').css('width','50px');
			$('#frame-img').css('position','relative');
			$('#frame-img').css('margin-top','150px');
			$('.frame-container #frame-img').attr('src',"<%=request.getContextPath()%>/assets/images/loading_new.gif"); 
				var xmlHttpRequest = getXMLHttpRequest();
			
				xmlHttpRequest.onreadystatechange = getReadyStateHandlerTOSaveResponse(xmlHttpRequest);
				xmlHttpRequest.open("POST", "findRealImage.action?requestId=" + <%=requestd%>
						+ "&filePath=" + sapData +"&audioPath="+audioPath, false);
				xmlHttpRequest.setRequestHeader("Content-Type",	"application/x-www-form-urlencoded");
				xmlHttpRequest.send();
			}
			/*
			 * Returns a function that waits for the state change in XMLHttpRequest
			 */
			function getReadyStateHandlerTOSaveResponse(xmlHttpRequest) {
				
				// an anonymous function returned it listens to the XMLHttpRequest instance
				return function() {
					if (xmlHttpRequest.readyState == 4) {
						if (xmlHttpRequest.status == 200) {

							var dataRead = JSON.parse(xmlHttpRequest.responseText);
							$('#realFrameName').val(dataRead.realPath);
							$('.frame-container #frame-img').attr('src',contextPath + '/temp/' + dataRead.realPath);
							$('#frame-img').css('margin-top','0px');
							$('#frame-img').css('position','absolute');
							if(!($('#manuscriptLabel').prop('checked'))){
							if(dataRead.audioPath!=null){
								$('#audioLable').css('display','none');
								$('#audioBrowse').css('display','none');
								$('#audioInfo').css('display','none');
								$('#embedFilepath').css('display','block');
								$('#audioEdit').css('display','block');
								$('#audioCancel').css('display','none'); 
							inerVar = '<embed src="'+contextPath+'/temp/'+dataRead.audioPath+'" autostart="false" controller="true" width="100%" height="55">'+
					                    '</embed>';
					            document.getElementById('embedFilepath').innerHTML =inerVar;
						 }else{
					           $('#audioLable').css('display','block');
								$('#audioBrowse').css('display','block');
								$('#audioInfo').css('display','block');
								$('#embedFilepath').css('display','none');
								$('#audioEdit').css('display','none');
								$('#audioCancel').css('display','none'); 
							} 
							}
						} else {
							alert("HTTP error ");
						};
					};
				};
			}
	
	function addManuscriptConstructor(contextPath) {
		this.contextPath = contextPath;
	}
	function executemethod(commentBy,commentText,commentTime){
			 var opt = {
				        autoOpen: false,
				        width: 500,
				        height:400,
				        title: 'Comment(Please read here)',
				        modal: true
				};
			 
		 document.getElementById("commentBox").value = commentText;
		 document.getElementById("commentAuthor").value = commentBy;
		 document.getElementById("commentDate").value = commentTime;
		 
		 $( "#publishCommentButton" ).hide();
		 $( "#comment-form" ).dialog(opt).dialog('open');
		  
	}
	
	$('input[type=button]').click(function(e) {
		if($(e.target).attr('id') == "addTagButton") {
			addTag();
		}
		e.preventDefault();
	});
	
	//changing the image onclick of thumbline image
	$(document).on("click",".image-link-container", function(e) {
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var id = $(this).attr('id').split('_')[1];
			var lastimgNo=parseInt($("#currentImg").text())-1;
			for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
				if(fileDiskPathObject[i].id == id) {
					imageChange(i,lastimgNo);
				}
			}
	});
	
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
	
	$(document).on("click",".thumbnail-close", function(e) {
		$(e.target).parent().remove();
	});
	
	$(document).on("keypress","#addTagText", function(e) {
		var code = e.keyCode || e.which; 
		if(code  == 13) {
			addTag();
			e.preventDefault();
			return false;
		}
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
	}
	
	function initPanZoom() {
		$('.frame-container #frame-img').panZoom({
			'zoomIn' : $('#zoomin'),
			'zoomOut' : $('#zoomout'),
			'fit' : $('#fit'),
			'directedit' : false
		});
		
		$('#fit').click();
	}; 
	
	//this is to find out the next image
	$('#nextImg').click(function(){
		var imgno=parseInt($("#currentImg").text());
		if(imgno < parseInt($("#totalImg").text()))
			{
			imageChange(imgno,imgno-1);
			}else{
				bootbox.alert("This is the last image");
			}
	});
	
	//this is to find out the previous image
	$('#prevImg').click(function(){
		var imgno=parseInt($("#currentImg").text());
		if(imgno>1){
			   imageChange(imgno-2,imgno-1);
		}else{
			bootbox.alert("This is the first image");
		}
	});
	
	//changing the image onclick of next previous or anyframe directly
	function imageChange(imgno,lastimgno){
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		 if(fileDiskPathObject[lastimgno].isLast==1){
				$('#div-'+(lastimgno)).css('background-color','#eb7676');
				$('#div-'+(lastimgno)).css('border', '1px solid #7d0000');
			   }else if(fileDiskPathObject[lastimgno].text==""){
				 $('#div-'+(lastimgno)).css('background-color','white');  
				 $('#div-'+(lastimgno)).css('border', '1px solid #ddd');
			   }else{
				   $('#div-'+(lastimgno)).css('background-color','#88b1cb');  
				   $('#div-'+(lastimgno)).css('border', '1px solid #133b55');
			   }
		$('#div-'+imgno).css('border','1px solid #0f4d0d');
	    $('#div-'+imgno).css('background-color','#88cb8a');
		$("#currentImg").text(imgno+1);
		$('.file-info').text("");
		$("#audioVideo").val("");
		//document.getElementById('audioVideo').reset();
		if(!($('#manuscriptLabel').prop('checked'))){
		 var frameId = 'transcriber_'+fileDiskPathObject[imgno].id;
		$('.transcriber').val(fileDiskPathObject[imgno].text);
		$('#audioPath').val(fileDiskPathObject[imgno].attachmentPath);
		document.getElementById("comments").innerHTML="";
		for(var j = 0 ; j<fileDiskPathObject[imgno].comments.length ; j++) {
			var commentBy = "'"+fileDiskPathObject[imgno].comments[j].commentBy+"'";
			var commentOn =  "'"+fileDiskPathObject[imgno].comments[j].commentTime+ "'";
			
			var comment =  "'"+fileDiskPathObject[imgno].comments[j].commentText.replace(/["']/g, "-")+"'";
			var sortcomment = fileDiskPathObject[imgno].comments[j].commentText;
			
			if(sortcomment.length > 75) {
				sortcomment = sortcomment.substring(0,75)+"...(More)";
			}
			var commentStr = '<p>'+sortcomment+'</p>';
			var commentsHtml='<div id="mainTableContainer" class="alert alert-success" style="cursor: pointer;height:70px !important ;  margin-right: 1%;" onclick="executemethod('+commentBy+','+comment+','+commentOn+')">'+commentStr+'<span style="color:#AAAAAA;font-weight:bold;font-size: 10px;">'+fileDiskPathObject[imgno].comments[j].commentBy+' \\ '+fileDiskPathObject[imgno].comments[j].commentTime+'</span>';
			commentsHtml += '</div>';
			
			$('#comments').append(commentsHtml);
		}
		$('.transcriber').attr('id', frameId);
		}
		$('#frameId').val(fileDiskPathObject[imgno].id);
		ajaxCallTest(fileDiskPathObject[imgno].filePathReal);
		
	}

	function addTag() {
		var htmlString = "";
		htmlString += "<div class='tag-data-shell'>";
		htmlString += "<a href='#' class='thumbnail-close'>×</a>";
		htmlString += $('#addTagText').val();
		htmlString += "</div>";
		$('.tag-data').append(htmlString);
		
		$('#addTagText').val("");
	}
function append(rtf){
	/*  $('#pdf').click(function(){ */
		 if(($('#manuscriptLabel').prop('checked'))){
			 var frmId = $('#manuscriptId').val();
			 document.getElementById(rtf).href=(document.getElementById(rtf).href+  
					  '&id='+frmId+'&startPage=0&endPage=0&recordType=0');
		 }else{
		  var frmId = $('#frameId').val();
		  document.getElementById(rtf).href=(document.getElementById(rtf).href+  
				  '&id='+frmId+'&startPage=0&endPage=0&recordType=0');  
			    /*  });  */
	}
	}
	
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
	
function manuscriptReport(type){
	var opt = {
	        autoOpen: false,
	        width: 400,
	        height:280,
	        title: 'Selective Exporting',
	        modal: true
	};
	$('#reportType').val(type);
$( "#exportingForm" ).dialog(opt).dialog('open');
	
}
function generateSelectedReport(){
	$( "#exportingForm" ).dialog('close');
	var manuscriptId =$('#manuscriptId').val();
	var docType =$('#reportType').val();
	var radios = document.getElementsByName("selectreport");
	for(var i = 0; i<radios.length; i++){
	    if (radios[i].checked) {
	    	radioId= radios[i].value;
	        break;
	    }
	}if(radioId == 1){
		window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestd%>+ '&jasperPath=/report/OnlyTextRepot.jasper&id='+manuscriptId +'&type='+docType+'&startPage=0&endPage=0&recordType=0';
	}else if(radioId == 2){
		window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestd%>+ '&jasperPath=/report/FullManuscriptWithFrame.jasper&id='+manuscriptId +'&type='+docType+'&startPage=0&endPage=0&recordType=0';
	}else if(radioId == 3){
		window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestd%>+ '&jasperPath=/report/ManuscriptReport.jasper&id='+manuscriptId +'&type='+docType+'&startPage=0&endPage=0&recordType=0';
	}else if(radioId == 4){
		stPage=$('#startPage').val();
		endPage=$('#endPage').val();
		if(endPage > parseInt($("#totalImg").text()) || stPage < 1){
			alert("Enter a valid range");
			manuscriptReport($('#reportType').val());
		}else{
		window.location = '${pageContext.request.contextPath}/exportReport.action?requestId='+<%=requestd%>+ '&jasperPath=/report/FullManuscriptWithFrame.jasper&id='+manuscriptId +'&type='+docType+'&recordType=0&startPage='+stPage+'&endPage='+endPage;

		}
		}
}

 function ajaxCallForNMM() {
		$.ajax({
		    type: "GET",
		    url: 'frameNMMDate.action?requestId='+<%=requestd%>+ '&filePath='+ $('#realFrameName').val(),
		    dataType: 'json',
		    cache: false,
		    success: function(data) {
		    	 divToPrint = '<html>'
				    	+'<body>'
						+'<div style="width:400,height:400">'
						 +'<table cellspacing="0" cellpadding="0">'
			               +'<tbody>'
			               +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>Height:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.height+'</td>'
	                       +'</tr>'
	                       +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>Width:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.width+'</td>'
	                       +'</tr>'
	                       +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>Created Date:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.createdDate+'</td>'
	                       +'</tr>'
	                       +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>Digitized Date:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.modifiedDate+'</td>'
	                       +'</tr>'
	                       +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>Make:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.lensMake+'</td>'
	                       +'</tr>'
	                       +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>Model:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.lensModel+'</td>'
	                       +'</tr>'
	                       +'<tr>'
	                       +'<td style="padding:10px" align="right"><b>X Resolution:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.xResolution+'</td>'
	                       +'</tr>'
	                       +'<td style="padding:10px" align="right"><b>Y Resolution:</b></td>'
	                       +'<td style="padding:0px !important;">'+data.yResolution+'</td>'
	                       +'</tr>'
	                       +'</tbody></table></div></body></html';
		     /* var popupWin = window.open('', '_blank', 'width=400,height=400');
					popupWin.document.open();
					popupWin.document.write(divToPrint);
					popupWin.document.close(); */
				document.getElementById("nmmcontent").innerHTML = divToPrint;
					  var dp={ resizable: false,
					         height: 400,
					         width: 500,
					         title: 'NMM Details',
					         modal: true};
					      $('#nmmcontent').dialog(dp).dialog('open');
		    },
		    error: function(data) {
		    	bootbox.alert('Sorry No NMM Details Found');
		    }
		});
 }
 $( "#newcomment" )
 .button()
 .click(function() {
	 var opt = {
		        autoOpen: false,
		        width: 550,
		        height:400,
		        title: 'Comments(Please write here)',
		        modal: true
		};
	 var currentdate = new Date(); 
	 var datetime =	currentdate.getDate() + "/"
	                 + (currentdate.getMonth()+1)  + "/" 
	                 + currentdate.getFullYear() + " \\ "  
	                 + currentdate.getHours() + ":"  
	                 + currentdate.getMinutes();
	 
	 $( "#publishCommentButton" ).show();
	 document.getElementById("commentBox").value = "";
	 document.getElementById("commentAuthor").value = "";
	 document.getElementById("commentDate").value = datetime;
	 $( "#comment-form" ).dialog(opt).dialog('open');
   });
 $('#manuscriptLabel').click(function(){
	$('.transcriber').val($('#manuscriptText').val());
	$('#frameLabelExport').css('display','none');
	$('#manuscriptLabelExport').css('display','block');
	$('#fpdf').css('display','none');
	$('#frtf').css('display','none');
	$("#audioVideo").val("");
	if($('#manuscriptAttachment').val()!= null && $('#manuscriptAttachment').val() !=""){
		getAudioFile();
	}
 });
 
 //get the audio file for manuscript lable transcription 
 function getAudioFile() {
	 var opt = {
		        autoOpen: false,
		        width: 60,
		        height: 120,
		       // title: 'Comments(Please write here)',
		        modal: true
		};
	 $( "#loadingimage" ).dialog(opt).dialog('open');
	 $(".ui-dialog-titlebar").hide();
	 $.ajax({
		    type: "GET",
		    url: 'getAudioFile.action?requestId='+<%=requestd%>+ '&audioPath='+ $('#manuscriptAttachment').val(),
		    dataType: 'json',
		    cache: false,
		    success: function(data) {
		    	if(data.audioPath!=null){
		    		$('#audioLable').css('display','none');
					$('#audioBrowse').css('display','none');
					$('#audioInfo').css('display','none');
					$('#embedFilepath').css('display','block');
					$('#audioEdit').css('display','block');
					 $('#audioCancel').css('display','none');
					inerVar = '<embed src="'+contextPath+'/temp/'+data.audioPath+'" autostart="false" controller="true" width="100%" height="55">'+
			                    '</embed>';
			            document.getElementById('embedFilepath').innerHTML =inerVar;
				 }else{
			           $('#audioLable').css('display','block');
						$('#audioBrowse').css('display','block');
						$('#audioInfo').css('display','block');
						$('#embedFilepath').css('display','none');
						$('#audioEdit').css('display','none');
						$('#audioCancel').css('display','none'); 
					} 
		    	$( "#loadingimage" ).dialog('close');
		    },
		    error: function(data) {
		    	$( "#loadingimage" ).dialog('close');
		    	bootbox.alert('Sorry No Audio File  Found');
		    }
		});
 }
 function editAudio(){
	    $('#audioLable').css('display','block');
		$('#audioBrowse').css('display','block');
		$('#audioInfo').css('display','block');
		$('#embedFilepath').css('display','none');
		$('#audioEdit').css('display','none'); 
		$('#audioCancel').css('display','block'); 
 }
 
  function cancelAudio(){
	    $('#audioCancel').css('display','none'); 
	    $('#audioLable').css('display','none');
		$('#audioBrowse').css('display','none');
		$('#audioInfo').css('display','none');
		$('#embedFilepath').css('display','block');
		$('#audioEdit').css('display','block'); 
 }
 
 $('#frameLabel').click(function(){
	// var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
	 var imgno= $("#currentImg").text();
	 imgno=imgno-1;
	 /* var frameId = 'transcriber_'+fileDiskPathObject[imgno].id;
		$('.transcriber').val(fileDiskPathObject[imgno].text);
		document.getElementById("comments").innerHTML="";
		for(var j = 0 ; j<fileDiskPathObject[imgno].comments.length ; j++) {
			var commentBy = "'"+fileDiskPathObject[imgno].comments[j].commentBy+"'";
			var commentOn =  "'"+fileDiskPathObject[imgno].comments[j].commentTime+ "'";
			
			var comment =  "'"+fileDiskPathObject[imgno].comments[j].commentText.replace(/["']/g, "-")+"'";
			var sortcomment = fileDiskPathObject[imgno].comments[j].commentText;
			
			if(sortcomment.length > 75) {
				sortcomment = sortcomment.substring(0,75)+"...(More)";
			}
			var commentStr = '<p>'+sortcomment+'</p>';
			var commentsHtml='<div id="mainTableContainer" class="alert alert-success" style="cursor: pointer;height:70px !important ;  margin-right: 1%;" onclick="executemethod('+commentBy+','+comment+','+commentOn+')">'+commentStr+'<span style="color:#AAAAAA;font-weight:bold;font-size: 10px;">'+fileDiskPathObject[imgno].comments[j].commentBy+' \\ '+fileDiskPathObject[imgno].comments[j].commentTime+'</span>';
			commentsHtml += '</div>';
			
			$('#comments').append(commentsHtml);
		} */
		$('.transcriber').attr('id', frameId);
		$('#manuscriptLabelExport').css('display','none');
		$('#frameLabelExport').css('display','block');
		$('#fpdf').css('display','block');
		$('#frtf').css('display','block');
		imageChange(imgno,imgno);
	 });
 function owdSave(){
	 var opt = {
		        autoOpen: false,
		        width: 60,
		        height: 120,
		       // title: 'Comments(Please write here)',
		        modal: true
		};
	 $( "#loadingimage" ).dialog(opt).dialog('open');
	 $(".ui-dialog-titlebar").hide();
	 var formData = new FormData($('#derivedWorkForm')[0]);
	 var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
	 var text = $('.transcriber').val();
	 var filePath;
	 /* if(filePath==""){
		 filePath=null;
	 } */
	 var type;
	 if(!($('#manuscriptLabel').prop('checked'))){
		 type= 0;
		 //id=$('#frameId').val();
		 filePath = $('#audioPath').val();
	 }else{
		 type=1;
		// id=$('#manuscriptId').val();
		 filePath = $('#manuscriptAttachment').val();
	 }
	 $.ajax({
		    type: "POST",
		    url: 'framePartialSave.action?type='+ type+'&filePath='+filePath,
		    //dataType: 'json',
		    xhr: function() {  // Custom XMLHttpRequest
	            var myXhr = $.ajaxSettings.xhr();
	        	/* If accurate progress is to be shown, it will be read through the myXhr variable */
	            return myXhr;
	        },
	        data :formData,
		    cache: false,
		    processData: false, 
		    contentType: false,
		    success: function(data) {
		    	$( "#loadingimage" ).dialog('close');
		    	 if(($('#manuscriptLabel').prop('checked'))){
		    		// $('#manuscriptId').val($('#digitalManuscriptFkId').val());
		    		$('#manuscriptText').val($('.transcriber').val());
		    		$('#manuscriptAttachment').val(data.attachmentPath);
		    	 }else{
		    	for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
		    		if(fileDiskPathObject[i].isLast= 1)
		    			{
		    			fileDiskPathObject[i].isLast = 0;
		    			if(fileDiskPathObject[i].text==""){
		   				 $('#div-'+i).css('background-color','white');  
		   				 $('#div-'+i).css('border', '1px solid #ddd');
		   			   }else{
		   				   $('#div-'+i).css('background-color','#88b1cb');  
		   				   $('#div-'+i).css('border', '1px solid #133b55');	
		   				   }
		    			}
					if(fileDiskPathObject[i].id == $('#frameId').val()) {
						fileDiskPathObject[i].text = text;
						fileDiskPathObject[i].isLast= 1;
						fileDiskPathObject[i].attachmentPath=data.attachmentPath;
						$('#audioPath').val(data.attachmentPath);
						$('#div-'+i).css('border','1px solid #0f4d0d');
					    $('#div-'+i).css('background-color','#88cb8a');
					}
		    	}
		    	var jsondata = JSON.stringify(fileDiskPathObject);
		    	$('#fileDiskPathContainer').val(jsondata);
		    	 }
		    	 bootbox.alert(data.message);
		    },
		    error: function(data) {
		    	$( "#loadingimage" ).dialog('close');
		    	bootbox.alert(data.message);
		    }
		});
	 
 }
 
 function savecomment() {
	var commentTxt = $('#commentBox').val();
	if(commentTxt.length > 0) {
		$.ajax({
			type :"GET",
			dataType:"json",
			url:'savecommentforframe.action?requestId='+<%=requestd%>+'&text='+ $('#commentBox').val()+'&frameId='+ $('#frameId').val()+'&manuscriptId='+ $('#manuscriptId').val()+'&currentime='+$('#commentDate').val(),
			cache : false,
			success:function(data) {
				bootbox.alert("Successfully saved the comment");
				$( "#comment-form" ).dialog('close');
				var commentBy = "'"+data.newcomment.commentedBy+"'";
				var commentOn =  "'"+data.newcomment.commentedOn+ "'";
				
				var comment =  "'"+data.newcomment.comment.replace(/["']/g, "-")+"'";
				var sortcomment = data.newcomment.comment;
				
				if(sortcomment.length > 75) {
					sortcomment = sortcomment.substring(0,75)+"...(More)";
				}
				var commentStr = '<p>'+sortcomment+'</p>';
				var commentsHtml='<div id="mainTableContainer" class="alert alert-success" style="cursor: pointer;height:70px !important ;  margin-right: 1%;" onclick="executemethod('+commentBy+','+comment+','+commentOn+')">'+commentStr+'<span style="color:#AAAAAA;font-weight:bold;font-size: 10px;">'+commentBy+' \\ '+commentOn+'</span>';
				commentsHtml += '</div>';
				
				$('#comments').append(commentsHtml);
				var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
				for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
					if(fileDiskPathObject[i].id == $('#frameId').val()) {
						fileDiskPathObject[i].comments.push({commentText:data.newcomment.comment,commentTime:data.newcomment.commentedOn,commentBy:data.newcomment.commentedBy});
					}
		    	}
				var jsondata = JSON.stringify(fileDiskPathObject);
		    	$('#fileDiskPathContainer').val(jsondata);
			},
			error : function(){
				$( "#comment-form" ).dialog('close');
				bootbox.alert("failure");
			}
		}); 
	} else {
		bootbox.alert("Please write comment to publish.");
	}
 }
 
 function removepopup() {
	 $( "#comment-form" ).dialog('close');
 }
 function searchWord(){
	 var searchWord = $('#searchText').val();
	 var text = $('.transcriber').val();
	 hiliter(searchWord,text);
 }

 function hiliter(word,text) {
     var rgxp = new RegExp(word, 'g');
     var repl = '<span style="color:#FF3333">' + word + '</span>';
     text =text.replace(rgxp, repl);
     text =text.replace(/\n/g,"<br/>");
     text =text.replace(/" "/g,"&nbsp;");
     $("#showdiv").html("");
     $('.transcriber').css('display','none');
	 $('#showdiv').css('display','block');
     htmlcontent= '<p>'+text+'</p>' ;
     $('#showdiv').append(text);
    
 }
 function replace(){
	 var searchWord = $('#searchText').val();
	 var length = searchWord.length;
	 var cursorPosition = $('.transcriber').val().indexOf(searchWord) + length ; 
	// var cFocus = document.getElementById("transcriber_141").innerHTML;
	 // var pos = cFocus.indexOf(searchWord);
	 $('#showdiv').css('display','none');
	 $('.transcriber').css('display','block');
	 setSelRange($('.transcriber'), cursorPosition, cursorPosition);
 }
 function setSelRange(inputEl, selStart, selEnd) { 
	   if (inputEl.setSelectionRange) { 
	     inputEl.focus(); 
	     inputEl.setSelectionRange(selStart, selEnd); 
	   } else if (inputEl.createTextRange) { 
	     var range = inputEl.createTextRange(); 
	     range.collapse(true); 
	     range.moveEnd('character', selEnd); 
	     range.moveStart('character', selStart); 
	     range.select(); 
	   } 
	}

/*  function FocusMe(what){ 
  var cFocus = document.getElemenById("myarea").innerHTML;
  var pos = cFocus.indexOf(what);
  setSelRange(document.getElementById('myarea'), pos, pos);
 } */
	$(':file').change(function(){
		/*
		 * On change of file, this method is called
		 * it is to be used for validation
		 * Note: No validation has been implemented yet
		 */
		var fileData = "";
		/* Put the selected image data into a div for display */
		if(this.files.length > 0) {
			if(this.files.length > 1) {
				fileData = this.files.length.toString() + " images";
				
			} else {
				fileData = this.files[0].name.toString();
			}
			$('.file-info').text(fileData);
			$('.file-info').fadeIn();	
		}
	});
</script>
</html>