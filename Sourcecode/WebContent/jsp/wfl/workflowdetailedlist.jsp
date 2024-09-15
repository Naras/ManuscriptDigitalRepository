    <%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<style>
	.btn-state-1, .btn-state-2 {
		height: 30px;
		padding: 0px;
		font-size: 12px;
	}
	
	.btn-state-1 {
		background-color: white;
		color: black;
	}
	
	.btn-state-2 {
		/* border-radius: 30px; */
	}
	
	a:hover {
		text-decoration:none;
	}
	</style>
<body>
		<s:if test="workflowListForUser.size > 0">
		<div class="container container-center alert alert-success appTable" style="max-width: 100%;">
		<br>
					<table class="table">
						<thead>
							<th><b>Task Description</b></th>
							<th><b>Work Type</b></th>
							<th><b>Language</b></th>
							
							<th><b>Script</b></th>
							<th><b>Action</b></th>
						</thead>
						<tbody>
							<s:hidden name = "isVisibleSave"></s:hidden>
							<s:iterator id="entityVO" value="%{workflowListForUser}">
								<s:iterator>
									<tr>
										<s:hidden value="id"/>
										<td>
											<s:a href="%{url}">
												<s:property value="screenName"/>
											</s:a>
										</td>
										<td>
											<s:property value="manuscriptType" default="N/A"/>
										</td>
										<td>
											<s:property value="languageName" default="N/A"/>
										</td>
										
										<td>
											<s:property value="scriptName" default="N/A"/>
										</td>
										<s:if test="isVisibleSave==0">
											<td>
											<s:url id="assignedToMe" action="assignedToReviewer.action">
												<s:param name="currentWFLDtsid" value="%{id}"></s:param>
												<s:param name="currentprocessmasterid" value="%{processMasterId}"></s:param>
											</s:url>
											<s:a href="%{assignedToMe}" cssClass="assignRecord">
												<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1" style="width: 80px;">Assign to me</button>
											</s:a>
											</td>
										</s:if><s:else>
											<td>
											<s:url id="unassign" action="unassignwfl.action">
												<s:param name="currentWFLDtsid" value="%{id}"></s:param>
											</s:url>
											<s:a href="%{unassign}" cssClass="assignRecord">
												<button type="button" class="btn btn-lg btn-warning btn-block btn-state-1">Unassign</button>
											</s:a>
											</td>
										</s:else>
										
									</tr>
								</s:iterator>
							</s:iterator>
						</tbody>					
					</table>
	</div>
	</s:if>
</body>
</html>