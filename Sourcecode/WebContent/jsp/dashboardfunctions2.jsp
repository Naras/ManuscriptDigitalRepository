<%-- 
    Document   : dashboardfunctions2.jsp
    Created on : 29 Sep, 2024, 7:26:47 PM
    Author     : User
--%>

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
