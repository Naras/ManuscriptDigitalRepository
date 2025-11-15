<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file='../layout/header.jsp' %>
<%--https://codepen.io/Sinetheta/pen/qvbCr--%>
<link href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css" rel="stylesheet" />
<link href="${pageContext.servletContext.contextPath}/assets/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
<link href="${pageContext.servletContext.contextPath}/assets/css/viewframe.css" rel="stylesheet" />
<div class="form" style="height: 90%;width:81%;margin-left: 220px;">
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
			<div class="panel panel-default">
				<div class="panel-heading special_font">Manuscript/Article/Book Information</div>
				<div class="panel-body special_font"
						 style="">
						<div class="col-md-4 special_font">
							<label class="control-label">Document Id:</label>
							<s:property value="digitalManuscriptVO.manuscriptId" />
						</div>
						<div class="col-md-4">
							<label class="control-label">Document Name:</label>
							<s:property value="digitalManuscriptVO.name" />
						</div>
						<div class="col-md-4">
							<label class="control-label">Author:</label>
							<s:property value="digitalManuscriptVO.authorName" />
						</div>
						<div class="col-md-4">
							<label class="control-label">Source:</label>
							<s:property value="digitalManuscriptVO.organisationVO.name" />
						</div>
						<div class="col-md-4">
							<label class="control-label">Language:</label>
							<s:property value="digitalManuscriptVO.languageVO.name" />
						</div>
						<div class="col-md-4">
							<label class="control-label">Script:</label>
							<s:property value="digitalManuscriptVO.scriptVO.name" />
						</div>
						<div class="col-md-4">
							<label class="control-label">Document Type</label>
							<s:if test="digitalManuscriptVO.documentType==1">
								Book
							</s:if>
							<s:if test="digitalManuscriptVO.documentType==2">
								Manuscript
							</s:if>
							<s:if test="digitalManuscriptVO.documentType==3">
								Article
							</s:if>
						</div>
						<div class="col-md-4">
							<label class="control-label">No Of frames:</label>
							<s:property value="digitalManuscriptVO.digitalManuscriptFrameVOs.size()" />
						</div>
						<div class="col-md-4">
							<label class="control-label">No Of Folios:</label>
							<s:property value="digitalManuscriptVO.totalNumberOfFolios" />
						</div>
					</div>
				</div>
			</div>


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
		   <div class="container-center" style="width: 200px;">
					<button  type="button" class="btn btn-lg btn-danger btn-block " onclick="goBack()">Cancel</button>
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
			var htmlString = "<ul class='sortable-list'>";
			var str="";
			for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
				var filePath = fileDiskPathObject[i].filePathReal.replace(/\\/g, "/");
                var file_name = filePath.substring(filePath.lastIndexOf('/')+1);

                htmlString += "<li id='"+fileDiskPathObject[i].id+"|"+file_name+"' class='img-thumbnail'>";
				htmlString += "<img src='" + contextPath + '/imageAction.action?isThumbnail=true&imagePath='+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "'  class='image-link-container'>";
				htmlString += "</li>";
				countImage++;
			}
            htmlString += "</ul>";
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
//http://www.tutorialspark.com/jqueryUI/jQuery_UI_Sortable_Interaction_Events.php
        $('.sortable-list').sortable({
            //items: "> li",
             stop: function(e, ui) {
                 var sortedArray = $('.sortable-list').sortable('toArray')
                 alert('-111---'+   sortedArray)
                 //console.log();
                 $.ajax({
                     url: contextPath+'/saveReSortingOrder.action',
                     type: 'post',
					 data: {'sortedArray':sortedArray.toString()},
                     dataType: 'json',
                     success: function( data, textStatus, jQxhr ){
                         $('#response pre').html( JSON.stringify( data ) );
                     },
                     error: function( jqXhr, textStatus, errorThrown ){
                         console.log( errorThrown );
                     }
                 });
             }
            /*update: function(event, ui) {
                console.log('update: '+ui.item.attr('id'))
            },
            start: function(event, ui) {
                console.log('start: ' + ui.item.index())
            }*/
        });

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
	$('#frame-img').css('height','150px');
	$('#frame-img').css('width','150px');
	$('#frame-img').css('position','relative');
	$('#frame-img').css('margin-top','250px');
	$('#frame-img').css('margin-left','230px');
	$('.frame-container #frame-img').attr('src',"<%=request.getContextPath()%>/assets/images/loading.gif");
		var xmlHttpRequest = getXMLHttpRequest();
		var filePath = "<%=request.getContextPath()%>/imageAction.action?requestId="+ <%=requestd%>
		+ "&imagePath=" + sapData+"&audioPath="+audioPath
		xmlHttpRequest.onreadystatechange = getReadyStateHandlerTOSaveResponse(xmlHttpRequest,filePath);
		<%-- xmlHttpRequest.open("POST", "findRealImage.action?requestId="+ <%=requestd%>
				+ "&filePath=" + sapData+"&audioPath="+audioPath, false); --%>
		
			xmlHttpRequest.open("POST", "<%=request.getContextPath()%>/imageAction.action?requestId="+ <%=requestd%>
				+ "&imagePath=" + sapData+"&audioPath="+audioPath, false);
		xmlHttpRequest.setRequestHeader("Content-Type",	"application/x-www-form-urlencoded");
		xmlHttpRequest.send();
	}
	
	/*
	 * Returns a function that waits for the state change in XMLHttpRequest
	 */
	
	function getReadyStateHandlerTOSaveResponse(xmlHttpRequest,filePath) {
		
		// an anonymous function returned it listens to the XMLHttpRequest instance
		return function() {
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) { 
					//var dataRead = JSON.parse(xmlHttpRequest.responseText);
			 
					//$('.frame-container #frame-img').attr('src',contextPath + '/temp/' + dataRead.realPath);
					$('.frame-container #frame-img').attr('src',filePath);
					$('#frame-img').css('margin-top','0px');
					$('#frame-img').css('margin-left','0px');
					$('#frame-img').css('position','absolute');
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
	
	function goBack(){
		window.history.back();
	}
</script>
</html>