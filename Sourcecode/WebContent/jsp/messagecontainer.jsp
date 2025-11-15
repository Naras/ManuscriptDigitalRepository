<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <br>
	<div id="msg_error" class="alert hide">
		<s:if test="hasActionErrors()">
			<script type="text/javascript">
				jQuery(document).ready(function() {
					jQuery('#msg_error').attr('class', 'alert alert-danger');
				});
			</script>
			<div class="errors">
				<s:actionerror />
			</div>
		</s:if> 
		<span onclick="closeErrorDiv();" style="cursor:pointer;float:right" class="glyphicon glyphicon-remove"></span>
	</div>
	<div id="msg_success" class="alert hide">
		<s:if test="hasActionMessages()">
			<script type="text/javascript">
				jQuery(document).ready(function() {
					jQuery('#msg_success').attr('class', 'alert alert-success');
					$( "#msg_success" ).fadeOut(12000);
				});
			</script>
			<s:actionmessage />
		</s:if>	
	</div>
</html>
<script type="text/javascript">
	function closeErrorDiv(){
			$( "#msg_error" ).fadeOut();
	}
				
</script>