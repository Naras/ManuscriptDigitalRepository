	<s:if test="#session.currentRole == 1 || #session.currentRole == 10">
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
