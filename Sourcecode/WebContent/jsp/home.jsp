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
	.chartcontainer {
	width		: 100%;
	height		: 35%;
	font-size	: 11px;
}	
	</style>
	<%@ include file='layout/header.jsp' %>
	<div class="container container-center" style="max-width: 80%; margin-left: 190px;">
		<%@ include file='messagecontainer.jsp' %>
		<%@ include file='wfl/workflowdashboard.jsp' %>
		<%@ include file='wfl/workflowdetailedlist.jsp' %>
		<s:if test="#session.currentRole == 1">
		<div class="statistic-span">
		<span class="glyphicon glyphicon-dashboard"></span> Administrator Dashboard
		</div>
			<div id="mainTableContainer" style="min-height: 100px; padding: 2px;">
				<div id="tableContainer" class="metro-nav metro-fix-view">
				</div>
			</div>
			<div style="width: 100%; overflow: hidden; margin-bottom: 1%;">
				<div class="appTable chart-tables" style="float: left;">
					<div style="min-height: 30px; border: 2px solid #D6E9C6;background-color: #D6E9C6;font-weight: bold;">
					<span class="glyphicon glyphicon-align-justify"></span> Workflow Summary
					</div>
					<div  id="chartcontainer1"  class ="chartcontainer" style="width:100%;">
					</div>
				</div>
				<div class="appTable chart-tables" style="float: right;">
					<div style="min-height: 30px; border: 2px solid #D6E9C6;background-color: #D6E9C6;font-weight: bold;">
					<span class="glyphicon glyphicon-align-justify"></span> Document Summary
					</div>
					<div  id="chartcontainer2" class ="chartcontainer" style="width:100%;">
					</div>
				</div>
			</div>
                    </s:if>
<!--		<s:if test="#session.currentRole == 10">
		<div class="statistic-span">
		<span class="glyphicon glyphicon-dashboard"></span> Guest Dashboard
		</div>
			<div id="mainTableContainer" style="min-height: 100px; padding: 2px;">
				<div id="tableContainer" class="metro-nav metro-fix-view">
				</div>
			</div>
			<div style="width: 100%; overflow: hidden; margin-bottom: 1%;">
				<div class="appTable chart-tables" style="float: right;">
					<div style="min-height: 30px; border: 2px solid #D6E9C6;background-color: #D6E9C6;font-weight: bold;">
					<span class="glyphicon glyphicon-align-justify"></span> Document Summary
					</div>
					<div  id="chartcontainer2" class ="chartcontainer" style="width:100%;">
					</div>
				</div>
			</div>
                    </s:if>	-->
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
	<%--<s:if test="#session.currentRole == 1 || #session.currentRole == 10">--%>
	<s:if test="#session.currentRole == 1">
		<script type="text/javascript">
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
				    	displayDasboard(data.digitalManuscripts);
				        pieChat(data.manuscriptStatus,data.digitalManuscripts[1].value);
				    }
				});
				
			}
			//used to display the dasboard boxes
			function displayDasboard(manuscriptsRecords){
				var string = "";
		        var icon ="glyphicon glyphicon-file";
		        for(var i = 0; i < manuscriptsRecords.length; i++) {
		        	if(i == 0){
		        		icon = "glyphicon glyphicon-user";
		        	}else{
		        		icon ="glyphicon glyphicon-file";
		        	}
		        	string +="<div class='metro-nav-block nav-block-purple double'><i class='"+ icon+"'></i>";
		        	//string += "<td>" + data.digitalManuscripts[i].label + "</td><td>" + data.digitalManuscripts[i].value + "</td";
		        	string += "<div class='info'>"+ manuscriptsRecords[i].value +"</div>";
		        	string += "<div class='status'>"+ manuscriptsRecords[i].label +"</div>";
					string +="</div>";
		        }
		        $('#tableContainer').html(string);
		        $('#mainTableContainer').fadeIn();
		        
		        var chart = AmCharts.makeChart("chartcontainer2", {
		    	    "type": "pie",	
		    		"theme": "light",
		    		"labelsEnabled": false,
		    		"autoMargins": true,
		    		"marginTop": 35,
		    		"marginBottom": 35,
		    		"marginLeft": 35,
		    		"pullOutRadius": 12,
		    		"marginRight": 0,
		    	    "legend": {
		    	        "markerType": "circle",
		    	        "position": "right",
		    			"marginRight": 0,		
		    			"autoMargins": false
		    	    },
		    	    "dataProvider": [{
		    	        "country": manuscriptsRecords[2].label,
		    	        "litres": manuscriptsRecords[2].value
		    	    }, {
		    	        "country": manuscriptsRecords[3].label,
		    	        "litres": manuscriptsRecords[3].value
		    	    },{
		    	        "country": manuscriptsRecords[4].label,
		    	        "litres": manuscriptsRecords[4].value
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

	function pieChat(data , totalRecord){
		 var totalasigned = data[0]+data[1]+data[2]+data[3]+data[4]+data[5];
		 var total = totalRecord-totalasigned;
	var chart = AmCharts.makeChart("chartcontainer1", {
	    "type": "pie",	
		"theme": "light",
		"labelsEnabled": false,
		"autoMargins": true,
		"marginTop": 35,
		"marginBottom": 35,
		"marginLeft": 35,
		"pullOutRadius": 12,
		"marginRight": 0,
	    "legend": {
	        "markerType": "circle",
	        "position": "right",
			"marginRight": 0,		
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