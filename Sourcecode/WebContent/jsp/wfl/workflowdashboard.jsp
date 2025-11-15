<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<style>
		#mainTableContainer {
			display:none;
		}
	
	</style>
	<div class="container container-center" style="max-width: 100%;">
		
		<s:if test="#session.currentRole > 1">
			<div id="mainTableContainer" class="alert alert-success appTable">
				<div class="table-responsive">
				<span class="glyphicon glyphicon-stats" style="font-size: x-large;text-decoration: underline;">Statistics</span>
					<table id="tableContainer" class="table">
					</table>
				</div>
			</div>
		</s:if>
	</div>
	<s:if test="#session.currentRole == 5 ||  #session.currentRole == 6 || #session.currentRole == 7 || #session.currentRole == 8 || #session.currentRole == 12">
		<script type="text/javascript">
			function getNSetData() {
				/* Gets the number of books and manuscripts from the server
					and prints it within the footer */
				<%
					String requestd = (String) request.getAttribute("requestId");
				%>
				$.ajax({
				    type: "GET",
				    url: 'getWFLCount.action?requestId='+<%=requestd%>,
				    dataType: 'json',
				    cache: false,
				    success: function(data) {
				    	var menu = data.digitalManuscripts;
				        var string = "";
				        string +="<tr> <td><a href='/mdr-src/getWflDetailedList.action?requestId="+<%=requestd%>+"&id="+menu.processId+"&counttype=COUNT_TYPE_ROLE'> Tasks in the pool</a></td><td>"+menu.cntRole+"</td></tr>";
				        string +="<tr><td><a href='/mdr-src/getWflDetailedList.action?requestId="+<%=requestd%>+"&id="+menu.processId+"&counttype=COUNT_TYPE_DIRECT'>Tasks assigned to me</a></td><td>" + menu.cntDirect + "</a></td></tr>";							
						
				        $('#tableContainer').html(string);
				        $('#mainTableContainer').fadeIn();
				    }
				});
			}
			
		</script>
	</s:if>
</html>