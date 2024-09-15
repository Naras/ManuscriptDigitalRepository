<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file='../layout/header.jsp' %>
<link href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css" rel="stylesheet" />
<link href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
<link href="${pageContext.servletContext.contextPath}/assets/css/viewframe.css" rel="stylesheet" />
<div class="form" style="height: 90%;">
	<div class="container">
		<div class="alert alert-danger hide container-center" id="msg-container"></div>
		<div class="container-center">
			<%@ include file='../messagecontainer.jsp' %>
		</div>
		<s:form action="addUpdateDigitalDocument" id="derivedWorkForm">
			
			<s:hidden id="filePathContainer" name="digitalManuscriptVO.filePathContainer"/>
			<s:hidden id="fileDiskPathContainer" name="digitalManuscriptVO.fileDiskPathContainer"/>
			<s:hidden name="digitalDocumentVO.id"/>
			<s:hidden name="digitalDocumentVO.digitalManuscriptFkId"/>
			<br>
			<h3>View Frames -
				<span style="color: #FFA500;"><s:text name="digitalManuscriptVO.name"></s:text></span>
			</h3>
			
			<div id="frame">
				<div class="image-details">
					<div class="left-column frame-filter-container resizable-h ui-widget-content ui-resizable">
						<div class="image-container panel">
							<div class="panel-header">
								Frame
							</div>
							<div class="panel-body" oncontextmenu="return false"></div>
						</div>
						<div class="information-deatsil panel">
							<div class="panel-group" id="accordion">
									<div id="accordionPanelHeader" class="panel">
										<div class="panel-heading" data-target="#collapseOne" data-toggle="collapse" data-parent="#accordion" style="background: #DFDDDD;">
											<label style="font-weight: normal;font-size: 14px;cursor: pointer;">Summary</label>	<span class="glyphicon glyphicon-paperclip" style=" float:right;margin-right: 20px;"></span>
										</div>
										<div id="collapseOne" class="panel-collapse collapse in">
											<div class="panel-body"
												style="background:#ffffff; overflow-y: scroll; display: block; margin-top: 1%; height: 70%;font-family: trebuchet ms ; font-size: 11.5px;font-style: italic;">
												<s:textarea name="digitalManuscriptVO.summary" readonly="true" cssStyle="width:100%;height:100%;font-size: 13px;font-style: italic;background-image: url('/OMDS/assets/images/images.jpg');" />
											</div>
										</div>
										</div>
										<div id="accordionPanelHeader1" class="panel">
										<div class="panel-heading" data-target="#collapseTwo" data-toggle="collapse" data-parent="#accordion" style="background: #DFDDDD;">
											<label style="font-weight: normal;font-size: 14px;cursor: pointer;">Other Information</label>
										</div>
										<div id="collapseTwo" class="panel-collapse collapse">
											<div class="panel-body"
												style="background:#ffffff; overflow-y: scroll; display: block; margin-top: 1%; height: 70%;font-family: trebuchet ms ; font-size: 14px;font-style: italic;background-image: url('/OMDS/assets/images/images.jpg');">
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
											</div>
										</div>
									</div>
									</div>
						</div>
						
					</div>
					<div class="middle-column resizable-h ui-widget-content ui-resizable">
						<div class=" frame-container"  oncontextmenu="return false">
							<div class="frame-controller">
								<span class="col-md-4 control-label" id="zoomin">+</span>
				      	    	<span class="col-md-4 control-label" id="zoomout">-</span>
			      		    	<span class="col-md-4 control-label" id="fit">
									<img src="${pageContext.servletContext.contextPath}/assets/images/reload.png" width=20em height=20em>
								</span>
							</div> 
							<img src="" id="frame-img">
							<div class="frame-nextprev" >
								<span class="col-md-4 control-label" id="prevImg"
									style="padding: 10px;">Prev</span> <span
									class="col-md-4 control-label" id="nextImg"
									style="padding: 10px 0px 10px 30px;">Next</span>
							</div>
						</div>
						
						<div class="transcriber-container" style="visibility: collapse;">
							<s:textarea name="digitalDocumentVO.detailsVO.text" cssClass="form-control transcriber" rows="8"></s:textarea>
						</div>
					</div>
				</div>
			</div>
		</s:form>
		<div align="center" class="imageNo" >
		   <span>Frame No:</span>
				<span id="currentImg"></span>
				<span >/</span>
			 <span id="totalImg"></span>
			 </div>
		   <div class="container-center">
				<s:url id="assignedToMe" action="searchManuscript.action">
				</s:url>
				<s:a href="%{assignedToMe}" cssClass="assignRecord">
					<button  type="button" class="btn btn-lg btn-danger btn-block ">Cancel</button>
				</s:a>
		</div>
	</div>
	<%@ include file='../layout/footer.jsp' %>
</div>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/rangy-core.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.selector.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.preferences.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.inputmethods.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery-panzoom.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.mousewheel.js"></script>
<script type="text/javascript">
	/* var contextPath = ""; */
	var frameId = "";
	
	$(document).ready(function() {
		addManuscriptConstructor("<%=request.getContextPath()%>");
		
		if($('#fileDiskPathContainer').val().length > 0) {
			var countImage = 0;
			var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
			var htmlString = "";
			var str="";
			for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
				var filePath = fileDiskPathObject[i].filePath.replace(/\\/g, "/");
				htmlString += "<div id='div-"+countImage+"' class='img-thumbnail'>";
				//htmlString += "<a href='#' class='thumbnail-close'>×</a>";
				htmlString += "<img src='" + contextPath + '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container'>";
				htmlString += "</div>";
				countImage++;
			}
			$('.image-container .panel-body').append(htmlString);
			$("#currentImg").text(1);
			$("#totalImg").text(countImage);
			var imgno=0;
			$('#div-'+imgno).css('border','1px solid #0f4d0d');
		    $('#div-'+imgno).css('background-color','#88cb8a');
			ajaxCallTest(fileDiskPathObject[0].filePathReal);
		} else {
			$('.frame-container').hide();
		}
		initializeIme();
		
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
				}
			}
		}
		return xmlHttpReq;
	}
	function ajaxCallTest(sapData) {
		<%
		String requestd = (String) request.getAttribute("requestId");
	%>
	var audioPath = "";
	$('.frame-container #frame-img').attr('src',"<%=request.getContextPath()%>/assets/images/loading.gif");
		var xmlHttpRequest = getXMLHttpRequest();
	
		xmlHttpRequest.onreadystatechange = getReadyStateHandlerTOSaveResponse(xmlHttpRequest);
		xmlHttpRequest.open("POST", "findRealImage.action?requestId=" + <%=requestd%>
				+ "&filePath=" + sapData+"&audioPath="+audioPath, false);
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

					$('.frame-container #frame-img').attr('src',contextPath + '/temp/' + dataRead.realPath);
				} else {
					alert("HTTP error ");
				};
			};
		};
	}
	
	function addManuscriptConstructor(contextPath) {
		this.contextPath = contextPath;
	}
	
	
	
	$(document).on("click",".image-link-container", function(e) {
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var id = $(this).attr('id').split('_')[1];
		 $('#div-'+(parseInt($("#currentImg").text())-1)).css('background-color','white');  
		 $('#div-'+(parseInt($("#currentImg").text())-1)).css('border', '1px solid #ddd');
		for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
			if(fileDiskPathObject[i].id == id) {
				$("#currentImg").text(i+1);
				$('#div-'+i).css('border','1px solid #0f4d0d');
			    $('#div-'+i).css('background-color','#88cb8a');
				ajaxCallTest(fileDiskPathObject[i].filePathReal);
			}
		} 
	});
	//this is to find out the next image
	$('#nextImg').click(function(){
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var imgno=parseInt($("#currentImg").text());
		 if(imgno < parseInt($("#totalImg").text()))
			{ 
			 $('#div-'+(parseInt($("#currentImg").text())-1)).css('background-color','white');  
			 $('#div-'+(parseInt($("#currentImg").text())-1)).css('border', '1px solid #ddd');
			 $('#div-'+parseInt($("#currentImg").text())).css('border','1px solid #0f4d0d');
		     $('#div-'+parseInt($("#currentImg").text())).css('background-color','#88cb8a');
			$("#currentImg").text(imgno+1);
			ajaxCallTest(fileDiskPathObject[imgno].filePathReal);
			 }else{
				alert("This Is The Last Image");
			} 
	});
	
	//this is to find out the previous image
	$('#prevImg').click(function(){
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var imgno=parseInt($("#currentImg").text());
		 if(imgno>1){ 
			 $('#div-'+(parseInt($("#currentImg").text())-1)).css('background-color','white');  
			 $('#div-'+(parseInt($("#currentImg").text())-1)).css('border', '1px solid #ddd');
			 $('#div-'+(parseInt($("#currentImg").text())-2)).css('border','1px solid #0f4d0d');
		     $('#div-'+(parseInt($("#currentImg").text())-2)).css('background-color','#88cb8a');
			  $("#currentImg").text(imgno-1);
			  ajaxCallTest(fileDiskPathObject[imgno-2].filePathReal);
	 	}else{
		alert("This Is The First Image");
		} 
	});
	
	$(document).on("click",".thumbnail-close", function(e) {
		$(e.target).parent().remove();
	});
	
	function initializeIme() {
		'use strict';
		var imeselector,$imeSelector;
		$( '.transcriber' ).ime();
		
		// get an instance of inputmethods
		imeselector = $( '.transcriber' ).data( 'imeselector' );
		imeselector.$imeSetting = $([]);
		// Also test system inputmethods.
		$imeSelector = $( 'select#imeSelector' );
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
</script>
</html>