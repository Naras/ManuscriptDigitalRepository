<%@page import="com.indven.search.vo.SearchVO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/paging.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/jquery-ui.css" />
<style>
.table {
    margin-bottom: 20px;
    width: 100%;
    background-color: #f5faf3;
}

.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    border-top: none;
    line-height: 1.42857;
    padding: 8px;
    vertical-align: top;
    background-color: #f5faf3;
}

</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/searchformgenerator.js"></script>
<%! String tableName = "";
String requestStr ="";
String idTrack = "";
%>
<%
SearchVO searchVO = (SearchVO)request.getAttribute("respectiveVO");
tableName = searchVO.getTableName();
%>
	<%@include file='../layout/header.jsp'%>
	<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
	<div class="form" style="padding-top: 0px;">
		<div>
			<%@ include file='../messagecontainer.jsp' %>
			<h2 class="form-heading">
				Search
			</h2>
			
			<s:form id="mainForm" action="" role="form">
			
				<%
					for(int i =0 ; i < searchVO.getLabelList().size() ; i++) {
				%>
				<div class="form-group row">
					<label class="col-xs-6 control-label" id="formContainer"><%= searchVO.getLabelList().get(i).toString()%></label>
					<div class="col-xs-6">
						<input type="text" id="<%=searchVO.getDisplayList().get(i+1).toString()%>" class="form-control"/>
					</div>							
				</div>
				<%
					}
				%>	
				<input type="hidden" id="fullyQualifiedVOName" value = "<%=tableName%>"/>
				<input type="hidden" id="createPageURL" value = "<%=request.getContextPath() %><%= searchVO.getCreatePageURL()%>"/>
				<div class="form-group row buttons">
					<div class="col-xs-4 col-md-4">
						<button type="button" class="btn btn-lg btn-primary btn-block" onclick="findAllRecord(1, 20);" >Find All</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="button" class="btn btn-lg btn-primary btn-block" onclick="searchSpecificRecord(1,20);" >Search</button>
					</div>
					<div class="col-xs-4 col-md-4">
						<button type="button" class="btn btn-lg btn-success btn-block" onClick="sendToPage();">New record</button>
					</div>
				</div>
				<div id="pageNavPosition" style="padding-top: 2px ; " align="center" ></div>
				<div class="table-responsive">
					<table id="displayRow" class="table">
						<tr id="iteratorrow">
							<td colspan="2" id="gridContainer" class="inVisibleTable">
								<div class="CSSTableGenerator" id="outerTableDiv" style=" margin-top:-5px ;">
								</div>
							</td>
						</tr>
					</table>						
				</div>
			</s:form>
		</div>
	</div>
	</div>
	<%@ include file="../layout/footer.jsp" %>
	
	<script type="text/javascript">

		function searchSpecificRecord(pageNumber , recordsPerPage) {
			var requestQuery = ""; 
			var xmlHttpRequest = getXMLHttpRequest();
			var isValuePresent = false;
			
			<%
				requestStr = "";
				for(int i =0; i<searchVO.getDisplayList().size()-1 ; i++)  {
					requestStr = "&"+ (String)searchVO.getDisplayList().get(i+1)+"=";
					idTrack = (String)searchVO.getDisplayList().get(i+1);
			%>
					var ccc = '<%=idTrack%>' ;
					var xyz = document.getElementById(ccc).value;
					var requestStr = '<%=requestStr%>';
					
					requestStr = requestStr+xyz;
					requestQuery = requestQuery+requestStr;
					if(xyz.length > 0) {
						isValuePresent = true;
					}
			<%
				}
			%>

			var tableName = '<%=tableName%>';
			var pagerParamStr = "&pageNumber="+ pageNumber +"&recordsPerPage="+recordsPerPage;
			if(isValuePresent){
				xmlHttpRequest.onreadystatechange = getReadyStateHandle(xmlHttpRequest);
				xmlHttpRequest.open("POST","searchAction.action?requestId="+ requestId + "&tableName=" + tableName +requestQuery+pagerParamStr,  true);
				xmlHttpRequest.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded");
				xmlHttpRequest.send();
				isConditionalSearch = true;
				pageNo = pageNumber;
			} else {
				alert("Please enter any value to search.");
			}
			
		}
		
		$(document).ready(function(){
			setRequestId(requestId);
		});
		
	</script>
</html>