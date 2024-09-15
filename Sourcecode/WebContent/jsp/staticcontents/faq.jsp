<html>
	<style type="text/css">
.accordion-heading
{
    background-color:#0CF;
}
.accordion-heading:hover
{
    background-color:#000;
    -webkit-transition: all 0.5s ease-in-out;
    -moz-transition: all 0.5s ease-in-out;
    -o-transition: all 0.5s ease-in-out;
    transition: all 0.5s ease-in-out;
}
.accordion-heading > a
{
    color:#FFF; 
    text-decoration:none; 
    text-transform:uppercase;
}
</style>
	<%@include file='../layout/header.jsp' %>
	<%-- <s:if test="#session.loginData == null">
		<%@include file='../layout/staticheader.jsp' %>
	</s:if> --%>
	<br>
		<div class="container container-center" style="max-width: 990px">
			<%-- <div class="imageContainer">
				<img src="<%=request.getContextPath()%>/assets/images/carousel/img1.png" alt="Image">
			</div> --%>

    <div class="panel-group" id="accordion">
	  <div class="panel panel-info">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Manuscript</a>
	      </h4>
	    </div>
	    <div id="collapseOne" class="panel-collapse collapse">
	      <div class="panel-body">
	      		<div class="panel-group" id="accordion1">
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">How to add a Manuscript?</a>
				      </h4>
				    </div>
				    <div id="collapseOne1" class="panel-collapse collapse">
				      <div class="panel-body">
				        <p>Twitter Bootstrap is a powerful front-end framework for faster and easier web development. It is a collection of CSS and HTML conventions.</p>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne2">How to update a Manuscript?</a>
				      </h4>
				    </div>
				    <div id="collapseOne2" class="panel-collapse collapse">
				      <div class="panel-body">
				      
				       <div>
				<img style="border:5px solid #F9F8F6;border-radius:15px;width: 100% ;" alt="" src="/WEB-INF/assets/images/slider/searchmanuscript.png">
				<p class="dropcap" style="font-size: 14px; font-family: Verdana,Arial,Helvetica,sans-serif; font-weight: 500; text-align: justify; line-height: 150%; 
						">
						
					1: Click on Manuscript -> Search. Then you will get the manuscript search screen.<br>
					2: Fill the desired criteria and click on Search button to get the related manuscripts. You can click on FindAll button also to get all the
						manuscript records.<br>
					3: Click on the update image and you will get the manuscript details in the manuscript form.<br>
					4: Edit the form to update the field you want and Submit.<br>
				</p>

			</div>
				      
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne3">How to delete a Manuscript?</a>
				      </h4>
				    </div>
				    <div id="collapseOne3" class="panel-collapse collapse">
				      <div class="panel-body">
				        <p>CSS stands for Cascading Style Sheet. CSS allows you to specify various style properties for a given HTML element such as colors, backgrounds, fonts etc.</p>
				      </div>
				    </div>
				  </div>
				</div>
	      
	      
	      </div>
	    </div>
	  </div>
  
  
  <div class="panel panel-info">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Transcription</a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse">
      <div class="panel-body">
        <p>Twitter Bootstrap is a powerful front-end framework for faster and easier web development. It is a collection of CSS and HTML conventions.</p>
      </div>
    </div>
  </div>
  
  
   <div class="panel panel-info">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapsefour">Translation</a>
      </h4>
    </div>
    <div id="collapsefour" class="panel-collapse collapse">
      <div class="panel-body">
        <p>Twitter Bootstrap is a powerful front-end framework for faster and easier web development. It is a collection of CSS and HTML conventions.</p>
      </div>
    </div>
  </div>
  
  
  <div class="panel panel-info">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">Report</a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse">
      <div class="panel-body">
        <p>CSS stands for Cascading Style Sheet. CSS allows you to specify various style properties for a given HTML element such as colors, backgrounds, fonts etc.</p>
      </div>
    </div>
  </div>
  
  
   <div class="panel panel-info">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapsefive">FAQ</a>
      </h4>
    </div>
    <div id="collapsefive" class="panel-collapse collapse">
      <div class="panel-body">
        <p>CSS stands for Cascading Style Sheet. CSS allows you to specify various style properties for a given HTML element such as colors, backgrounds, fonts etc.</p>
      </div>
    </div>
  </div>
  
  
</div>
</div>
<%@include file='../layout/footer.jsp' %>
</html>