<%-- 
    Document   : dashboard
    Created on : 29 Sep, 2024, 6:50:34 PM
    Author     : User
--%>
		<s:if test="#session.currentRole == 1">
		<div class="statistic-span">
		<span class="glyphicon glyphicon-dashboard"></span> DASHBOARD
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
		<s:if test="#session.currentRole == 10">
		<div class="statistic-span">
		<span class="glyphicon glyphicon-dashboard"></span> DASHBOARD
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
                    </s:if>	
