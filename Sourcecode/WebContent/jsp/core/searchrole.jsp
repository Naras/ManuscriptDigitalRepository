<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function deleteRole() {
		return confirm("Are you sure you want to delete this role?");
	}
</script>
<body>

	<%@ include file='../layout/header.jsp'%>
	<div class="container container-center  alert alert-success appTable" style="max-width: 60%;">
		<%@ include file='../messagecontainer.jsp'%>
		<div class="form">
			<h2 class="form-heading">
				Update or delete roles
			</h2>
			<div class="table-responsive">
				<table class="table">
					<s:if test="#request.objResult.listOfElemnents.size() > 0">
						<thead>
							<th><b>Name</b></th>
							<th><b>Description</b></th>
							<th><b>Update</b></th>
							<th><b>Delete</b></th>
						</thead>
						<tbody>
							<s:iterator id="userTO" value="%{objResult.listOfElemnents}">
								<s:iterator>
									<tr>
									<s:if test="id != 1">
										<s:hidden value="id" />
										<td><s:property value="name" /></td>
										<td><s:property value="description" /></td>
										<td><s:url id="editURL"
												action="findroleByIdAction.action">
												<s:param name="id" value="%{id}"></s:param>
		
											</s:url> <s:a href="%{editURL}"><img style="padding-left: 35px;" height="20" alt=""	src="<%=request.getContextPath()%>/assets/images/update.png"/></s:a></td>
		
										<td><s:url id="deleteURL"
												action="deleteRole.action">
		
												<s:param name="id" value="%{id}"></s:param>
		
											</s:url> <s:a href="%{deleteURL}" onClick="return deleteRole();"><img style="padding-left: 35px;" height="20" alt=""	src="<%=request.getContextPath()%>/assets/images/Delete.jpg"/></s:a></td>
									</s:if>
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>
						
					</s:if>
				</table>
			</div>
		</div>
	</div>
	
	<%@ include file='../layout/footer.jsp'%>
</body>
</html>