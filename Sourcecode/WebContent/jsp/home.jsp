<html>
	<% 
		if(session.getAttribute("loginData") == null) {
			response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		} 
	%>
	<style>
		.imageContainer {
			vertical-align: middle;
		}
		
		#mainTableContainer {
			display:none;
		}
		#chartcontainer {#chartcontainer {
	width		: 100%;
	height		: 500px;
	font-size	: 11px;
}			
	width		: 100%;
	height		: 400px;
	font-size	: 11px;
}			

	
	</style>
	<%@ include file='layout/header.jsp' %>
	<div class="container container-center" style="max-width: 60%;">
		<%@ include file='messagecontainer.jsp' %>
		<s:if test="#session.loginData != null && #session.userRoles != null && #session.userRoles.size > 1">
			<s:form name="roleIdForm" action="changemenuforrole">
			<s:hidden name="currentRoleName" id="currentRoleName"></s:hidden>
					 <label class="col-md-2 control-label"  style="margin-top:-20px;">Logged in as :</label>
					<div class="col-xs-4" style="margin-top:-30px;">
						<s:select list="#session.userRoles"  value="#session.currentRole" required="true" id="roleID" listKey="id" onchange="makeRequest();" listValue="name" cssClass="form-control" name="userRoleId" />
					</div>
			</s:form> 
		</s:if>
		
		<s:else>
			<label class="col-md-4 control-label" id="userrole" style="margin-top:-20px;"></label>
			<s:select list="#session.userRoles" cssStyle="visibility:collapse;" value="#session.currentRole" required="true" id="roleID" listKey="id" onchange="makeRequest();" listValue="name" cssClass="form-control" name="userRoleId" />
			<script>
				var role = $('select[name=userRoleId] option:selected').text();
				document.getElementById('userrole').innerHTML = 'Logged in as : '+ role;
			</script>
		</s:else>
		<%@ include file='wfl/workflowdashboard.jsp' %>
		<%@ include file='wfl/workflowdetailedlist.jsp' %>
		<s:if test="#session.currentRole == 1">
			<div id="mainTableContainer" class="alert alert-success appTable">
				<div class="table-responsive">
					<h3>Statistics</h3>
					<table id="tableContainer" class="table">
					
					</table>
				</div>
			</div>
			<div class="appTable" style="border: 2px solid #D6E9C6; border-radius: 4px;">
			<div>
			<div  id="chartcontainer">
			</div>
			</div>
			<div class="form-group row" style="margin-top: -85px;margin-left : 5px;">
				<h4 class="col-xs-3" style="cursor: pointer; color: #3C763D;" onclick='findAll()'>Click Here For Details</h4>
			</div>
			<s:form action="searchForManuscriptDisplay" name="searchform" id="searchForm">
			<s:hidden name="searchType" id="searchType"></s:hidden>
			<s:if test="listForPagingCombo != null && listForPagingCombo.size() > 0">
				
					<div class="form-group row">
					    <div class="col-xs-3" style="width:30%;" align="center"></div>
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
			<div class="table-responsive">
				<table class="table">
					<s:if test="#request.objResult.listOfElemnents.size() > 0">
						<thead>
							<th><b>Name</b></th>
							<th><b>Author</b></th>
							<th><b>Owner</b></th>
							<th><b>Language</b></th>
							<th><b>Script</b></th>
							<th><b>Frames</b></th>
						</thead>
						<tbody>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}">
								<s:iterator>
									<tr>
										<s:hidden value="id" />
										<td><s:property value="name" /></td>
										<td><s:property value="authorVO.name" /></td>
										<td><s:property value="wflProcessOwner" default="Pool"/></td>
										<td><s:property value="languageVO.name" /></td>
										<td>
										<s:if test="scriptFkId">
										<s:property value="scriptVO.name" />
										</s:if><s:else>No Script</s:else>
										</td>
										<td style="text-align: center;">
										<%-- <s:if test="presentFrame">
											<s:url id="viewFrameURL" action="viewFrames.action">
												<s:param name="id" value="%{id}"></s:param>
											</s:url>
											<s:a href="%{viewFrameURL}">
												<button type="button" class="btn btn-lg btn-success btn-block btn-state-1">View</button>
											</s:a>
										</s:if><s:else>
											<button type="button" class="btn btn-lg btn-success btn-block btn-state-1">N/A</button>
										</s:else> --%>
										
										<s:if test="presentFrame">
										<s:property value="totalFrame" />
										</s:if><s:else>0</s:else>
										</td>
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>
					</s:if>
				</table>
			</div>
			</div>
				</s:if>
		</div>
	
	<%@ include file='layout/footer.jsp' %>
	<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/chartjs/jscharts.js"></script>
	<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/chartjs/charts.js"></script>
	<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/chartjs/pie.js"></script>
	<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/chartjs/thems.js"></script>
	<s:if test="#session.currentRole == 1">
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
			var requestId = "";
			function getNSetData() {
				/* Gets the number of books and manuscripts from the server
					and prints it within the footer */
				$.ajax({
				    type: "GET",
				    url: 'findNoOfManuscripts.action?requestId='+requestId+'&nodeclicked=101',
				    dataType: 'json',
				    cache: false,
				    success: function(data) {
				        var string = "";
				        for(var i = 0; i < data.digitalManuscripts.length; i++) {
				        	string +="<tr>";
				        	string += "<td>" + data.digitalManuscripts[i].label + "</td><td>" + data.digitalManuscripts[i].value + "</td";
							string +="</tr>";
				        }
				        $('#tableContainer').html(string);
				        $('#mainTableContainer').fadeIn();
				        pieChat(data.manuscriptStatus,data.digitalManuscripts[1].value);
				    }
				});
				
			}
			</script>
		</s:if>
		<script>
			$(document).ready(function() {
				requestId = <%=requestId%>;
				if(requestId == null) {
					$('a').each(function() {
						if($(this).attr('href').indexOf('homePageAction') != -1) {
							window.location = $(this).attr('href');
						}
					});
				} 
				getNSetData();
			});
			/* setInterval(function() {
				getNSetData();
			}, 120000); */
			</script>
		<s:if test="isReloadMenu == 0">
			<script>
				$('a').each(function() {
					if($(this).attr('href').indexOf('homePageAction') != -1) {
						window.location = $(this).attr('href');
					}
				});
			
			</script>
		</s:if>
	<script type="text/javascript">
		function makeRequest() {
			var role = $('select[name=userRoleId] option:selected').text();
			 $('#currentRoleName').val(role);
			document.roleIdForm.submit();
		}
	 /* function pieChat(data , totalRecord){
		 var totalasigned = data[0]+data[1]+data[2]+data[3]+data[4]+data[5];
		 var total = totalRecord-totalasigned;
		var myData = new Array(['', data[0]], ['', data[1]], ['', data[2]], ['', data[3]], ['', data[4]], ['', data[5]],['',total]);
		var colors = ['#FACC00', '#FB9900', '#3C763D', '#FB4800', '#996600', '#428BCA','#999999'];
		var myChart = new JSChart('chartcontainer', 'pie');
		myChart.setDataArray(myData);
		myChart.colorizePie(colors);
		myChart.setTitle('Manuscript Details');
		myChart.setTitleFontSize(14);
		myChart.setTitleColor('#3C763D');
		myChart.setPieUnitsColor('#3C763D');
		myChart.setPieValuesColor('#6A0000');
		myChart.draw();
	}  */
	function pieChat(data , totalRecord){
		 var totalasigned = data[0]+data[1]+data[2]+data[3]+data[4]+data[5];
		 var total = totalRecord-totalasigned;
	var chart = AmCharts.makeChart("chartcontainer", {
	    "type": "pie",	
		"theme": "light",
	    "legend": {
	        "markerType": "circle",
	        "position": "right",
			"marginRight": 80,		
			"autoMargins": false
	    },
	    "dataProvider": [{
	        "country": "Scholar",
	        "litres": data[0]
	    }, {
	        "country": "Reviewer",
	        "litres": data[1]
	    }, {
	        "country": "Publisher",
	        "litres": data[2]
	    }, {
	        "country": "Published",
	        "litres": data[3]
	    }, {
	        "country": "Verifier",
	        "litres": data[4]
	    }, {
	        "country": "Translator",
	        "litres": data[5]
	    }, {
	        "country": "Not Assigned",
	        "litres": total
	    }],
	    "valueField": "litres",
	    "titleField": "country",
	    "balloonText": "[[title]]<br><span style='font-size:12px'><b>[[value]]</b> ([[percents]]%)</span>",
	    "exportConfig": {
	        "menuTop":"-20px",
	        "menuItems": [{
	            "icon": '/lib/3/images/export.png',
	            "format": 'png'
	        }]
	    }
	});
	}
</script>	
</html>