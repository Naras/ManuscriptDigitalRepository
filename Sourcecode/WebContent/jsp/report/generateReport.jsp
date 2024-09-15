<html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/paging.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/jquery-ui.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/searchformgenerator.js"></script>

	<%@include file='../layout/header.jsp'%>
	<div class="about-page">
		<div class="container" style="width: 60%;background-color: #F5FAF3;" id="rep">
			<div class="appTable">
				<div class="panel-body">
				<s:form
				action=""
				target="reportContainer" name="frm">
				<div class="form-group row buttons">
					<div class="col-md-6">
						<input type="button" class="btn btn-lg btn-primary btn-block"
							value="Status Report" id="genericReport">
					</div>
					<div class="col-md-6">
						<input type="button" class="btn btn-lg btn-primary btn-block"
							value="Drill Down Report" id="StatusReport">
					</div>
				</div>
				<br>
				<div class="form-group row" id="statusRow" style="display: none;">
					<label class="col-xs-2 control-label" style="padding: 0px;">Status Label</label>
					<div class="col-xs-4">
						<s:select
							list="documentStatusList" required="true" id="documentStatus"
							cssClass="form-control" />
					</div>
					<div class="col-md-3">
						<input type="button" style="height: 35px; padding-top: 3px;" class="btn btn-lg btn-primary btn-block" Value="Submit" onclick="generateStatusReport()">
					</div>
				</div>
				</s:form>
				</div>
				</div>
				</div>
				<div class="container" style="width: 60%" align="center" >
		<iframe id="reportContainer" name="reportContainer" height="1000" width="100%" frameborder="0"
				src="<%=request.getContextPath()%>/jsp/report/report.jsp"></iframe> 
				</div>
				</div>

    <%@ include file='../layout/footer.jsp' %>
    	<script type="text/javascript">
    	<%
		String requestd = (String) request.getAttribute("requestId");
	%>
				function generateStatusReport(){
					var status = $('#documentStatus').val();
					var docType = 1;
					document.frm.action='generateReportByCriteria.action?requestId='+<%=requestd%>+'&jasperPath=/report/StatusReport.jasper&status='+ status +'&type='+docType;
					 document.frm.submit();
					 $('#rep').css('display','none');
				}
				$('#genericReport').click(function(){
					$('#statusRow').css('display','none');
					var docType = 0;
					document.frm.action='generateReportByCriteria.action?requestId='+<%=requestd%>+'&jasperPath=/report/Recortreport.jasper&status=Under_Publisher&type='+docType;
				    document.frm.submit();
				    $('#rep').css('display','none');
				})
				$('#StatusReport').click(function(){
					$('#statusRow').css('display','block');
				})
				</script>
    	