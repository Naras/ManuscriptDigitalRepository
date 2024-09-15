<html>
<link href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css" rel="stylesheet" />
<link href="${pageContext.servletContext.contextPath}/assets/css/addmanuscript.css" rel="stylesheet" />
<%@ include file='../layout/header.jsp' %>
<style>
  .ui-autocomplete {
    max-height: 200px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
  }
  /* IE 6 doesn't support max-height
   * we use height instead, but this forces the menu to always be this tall
   */
  * html .ui-autocomplete {
    height: 200px;
  }
  
  .labelInfo {
  	margin-left: 10px;
  }
  
  textarea {
     resize: vertical;
   }
</style>
<div class="container container-center alert alert-success appTable" style="max-width: 60%;">
<div class="form">
	<div class="container" style="margin-top:-50px">
		<div class="image-max-container" style="width: 100% ; height: 100%;">
			<div class="image-max-close">X</div>
			<span id="currentImg" style="color:#000"></span>
			<span id="totalImg" style="color:#000"></span>
			<img src="" align="middle"> 
			<div class="frame-nextprev" align="center">
				<span class="col-md-4 control-label" id="prevImg"
					style="padding: 10px;">Prev</span> <span
					class="col-md-4 control-label" id="nextImg"
					style="padding: 10px 0px 10px 30px;">Next</span>
			</div>
		</div>
		<div class="container-center">
			<div class="alert alert-danger hide" id="msg-container"></div>
			<div class="alert alert-success hide" id="msg-success-container"></div>
			<%@ include file='../messagecontainer.jsp' %>
		</div>
		<s:form action="addUpdateManuscript" role="form" id="manuscriptForm" name="manuscriptFormName">
			<s:hidden name="digitalManuscriptVO.id"/>
			<s:hidden name="digitalManuscriptVO.manuscriptType"/>
			<s:hidden name="digitalManuscriptVO.parentFKId"/>
			<s:hidden name="digitalManuscriptVO.recordStatus"/>
			<s:hidden name="digitalManuscriptVO.isSavingMerged" id="savingMerged"/>
			<s:hidden name="digitalManuscriptVO.parentIdsStr"/>
			
			<s:hidden id="authorId" name="digitalManuscriptVO.authorVO.id"/>
			<s:hidden id="scribeId" name="digitalManuscriptVO.scribeVO.id"/>
			<s:hidden id="commentatorId" name="digitalManuscriptVO.commentatorVO.id"/>
			<s:hidden id="subCommentatorId" name="digitalManuscriptVO.subCommentatorVO.id"/>
			<s:hidden id="translatorId" name="digitalManuscriptVO.translatorVO.id"/>
			<s:hidden id="organisationId" name="digitalManuscriptVO.organisationVO.id"/>
			<s:hidden id="organisationType" name="digitalManuscriptVO.organisationVO.type"/>
			<s:hidden id="publisherId" name="publicationVO.publisherVO.id"/>
			<s:hidden id="editorId" name="publicationVO.editorVO.id"/>
			<s:hidden id="publicationId" name="digitalManuscriptVO.publicationVO.id"/>
			<s:hidden id="isAvailableValue" name="publicationVO.isAvailable"/>
			<s:hidden id="documentType" name="digitalManuscriptVO.documentType"/>
			<s:hidden id="nmmDetailsId" name="digitalManuscriptVO.nmmDetailsVO.id"/>
			<s:hidden id="filePathContainer" name="digitalManuscriptVO.filePathContainer"/>
			<s:hidden id="fileDiskPathContainer" name="digitalManuscriptVO.fileDiskPathContainer"/>
			<s:hidden id="natureOfCollection" name="digitalManuscriptVO.natureOfCollection"/>
			<s:hidden id="isBound" name="digitalManuscriptVO.isBound"></s:hidden>
			<s:hidden id="tempTagId"></s:hidden>
			
			<div class="form-group row buttons container-center">
				<div class="col-md-5">
					<a href="#" id="bookContainer" class="main-tab btn btn-lg btn-primary btn-block">Book</a>
				</div>
				<div class="col-md-2">
				</div>
				<div class="col-md-5">
					<a href="#" id="manuscriptContainer" class="main-tab btn btn-lg btn-primary btn-block">Manuscript</a>
				</div>
			</div>
			
			<div class="form-container"  id="tab-form">
				<h2 class="form-heading bookContainer" style="max-width: 60%;">Book Digitization</h2>
				<h2 class="form-heading manuscriptContainer" style="max-width: 60%;">Manuscript Digitization</h2>
				<ul class="nav nav-tabs nav-justified container-center" style="max-width: 100%;">
					<li class="active"><a href="#manuscript" data-toggle="tab">Information</a></li>
					<li class="manuscript-specific"><a href="#author" data-toggle="tab">Author/Scribe</a></li>
					<li class="bookContainer"><a href="#author" data-toggle="tab">Author</a></li>
					<li><a href="#frame" data-toggle="tab">Frames</a></li>
					<li class="manuscript-specific"><a href="#nmm" data-toggle="tab">NMM</a></li>
					<li><a href="#publication" data-toggle="tab">Others</a></li>
				</ul>
				
				<div class="tab-content">
					
					<div class="tab-pane fade active in container-center" id="manuscript" style="max-width: 100%;">
						<br>
						<div class="form-group row">
				          <label class="col-md-4 control-label manuscript-specific">Manuscript ID </label>
				          <label class="col-md-4 control-label bookContainer">Book ID </label>
				          <div class="col-md-8">
				              <s:textfield cssClass="form-control" id="manuscriptIdentification" name="digitalManuscriptVO.manuscriptId" maxlength="100"/>
				          </div>
				        </div>
				        <div class="form-group row manuscript-specific">
				          <label class="col-md-4 control-label">Accession Number</label>
				          <div class="col-md-8">
				              <s:textfield cssClass="form-control" id="manuscriptAccNo" name="digitalManuscriptVO.accNumber" maxlength="50"/>
				          </div>
				        </div>
				         <div class="form-group row manuscript-specific">
				          <label class="col-md-4 control-label">Bundle (Optional)</label>
				          <div class="col-md-8">
		                  <s:select headerKey="-1" headerValue="Unselected" list="bundleMasterVOs" listValue="name" listKey="id" cssClass="form-control"
				              name="digitalManuscriptVO.bundleMasterFkId" autoComplete="false" maxlength="50"/>
				          </div>
				        </div>
						<div class="form-group row">
					          <label class="col-md-4 control-label manuscript-specific">Manuscript Name </label>
					          <label class="col-md-4 control-label bookContainer">Book Name</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="manuscriptName" name="digitalManuscriptVO.name" maxlength="100"/>
					          </div>
					      </div>
					      
					      <div class="form-group row">
					          <div class="col-md-4">
					          	<label class="control-label">Name (in Diacritical)</label>
					          	<span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
					          		data-toggle="popover" data-content="Diacritical Name"></span>
					          </div>
					          
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="manuscriptDiacriticName" name="digitalManuscriptVO.diacriticName" maxlength="100"/>
					          </div>
					      </div>
					      
					      <div class="form-group row">
					         <div class="col-md-4">
						          <label class="control-label">Name (in Vernacular)</label>
						          <span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
						          		data-toggle="popover" data-content="Regional Name"></span>
					         </div>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="manuscriptRegionalName" name="digitalManuscriptVO.regionalName" maxlength="100"/>
					          </div>
					      </div>
						<div class="form-group row">
					      	  <div class="col-md-12">
						      	  <div class="alert alert-warning validation-warning">
						      	  	  <h6>Tip : You must enter at least one of the three form fields (ie. name, regional name, diacritical name)</h6>
								  </div>
					      	  </div>
					      </div>
					      <!-- <div class="form-group row bookContainer">
					      	  <div class="col-md-12">
						      	  <div class="alert alert-warning validation-warning">
						      	  	  <h6>You must enter the book name</h6>
								  </div>
					      	  </div>
					      </div> -->
						<br><br>
						<fieldset>
							<legend style="color: #3C763D;">Work Details</legend>
							<div class="form-group row">
								<label class="col-md-2 control-label" id="languageLabel">Language</label>
								<div class="col-md-4">
						              <s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.languageFkId" list="languageVOs" required="true" id="language"
										name="digitalManuscriptVO.languageFkId" listKey="id" listValue="name" cssClass="form-control"/>
						        </div>
								<label class="col-md-2 control-label">Script</label>
								<div class="col-md-4">
						              <s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.scriptFkId" list="scriptVOs" required="true" id="script"
										name="digitalManuscriptVO.scriptFkId" listKey="id" listValue="name" cssClass="form-control"/>
						        </div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 control-label">Subject</label>
								<div class="col-md-4">
						              <s:select headerKey="-1" headerValue="Unselected" value="digitalManuscriptVO.categoryFkId" list="categoryVOs" required="true" id=""
										name="digitalManuscriptVO.categoryFkId" listKey="id" listValue="name" cssClass="form-control"/>
						        </div>
						         <label class="col-md-2 control-label">Type</label>
								<div class="col-md-4">
						              <s:select value="digitalManuscriptVO.typeOfWork" list="manuscriptWorkTypes" required="true" id=""
										name="digitalManuscriptVO.typeOfWork" cssClass="form-control"/>
						        </div>
							</div>
					        <div class="form-group row" id="originalCommRef">
					          <label class="col-md-4 control-label">Original Commentary</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="parentCommFKId" maxlength="250" name = "digitalManuscriptVO.parentName"/>
					              <s:hidden cssClass="form-control" id="originalCommId" name="digitalManuscriptVO.parentFKId"/>
					          </div>
					        </div>
					        
							<div class="form-group row">
							   <label class="col-md-2 control-label">Specific Category</label>
								<div class="col-md-4">
						              <s:select list="specificCategoryVOs" required="true" id="specificCategoryId"
										name="digitalManuscriptVO.specificCategoryId" listKey="id" listValue="name" cssClass="form-control" onChange="onChangeSpCategory();" multiple="true"/>
									  
									 <a id="originalLink"  style="visibility: collapse;">Link Original</a>
						        </div>
						        <div class="manuscript-specific">
							        <label class="col-xs-2 control-label">Material</label>
									<div class="col-xs-4">
							            <s:select value="digitalManuscriptVO.materialFkId" list="materialVOs" required="true" id=""
										name="digitalManuscriptVO.materialFkId" listKey="id" listValue="name" cssClass="form-control"/>
							        </div>
						        </div>
							</div>
							<div class="form-group row" id="originalWorkRef">
					          <label class="col-md-4 control-label">Original Work</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="parentFKId" maxlength="250" name = "digitalManuscriptVO.parentName"/>
					              <s:hidden cssClass="form-control" id="originalWorkId" name="digitalManuscriptVO.parentFKId"/>
					          </div>
					        </div>
							<div class="author-search alert alert-success">
							<div class="form-group row">
							<label class="col-md-4 control-label">Tag Name</label>
							<div class="col-md-6" style="padding-left: 0px;">
								<s:textfield cssClass="form-control" id="tag" maxlength="50" placeholder="Autocomplete Field.." />
							</div>
							<div class="col-md-2" style="padding-left: 0px;">
								<input type="button" value="Add" id="tagbutton" class="btn btn-lg btn-success btn-block" style="height: 35px; padding-top: 5px;">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-4 control-label">Tags</label>
							<!-- <div id="emptyEmail" class="col-md-8" id="tagDisplay"
								style="float: center; width: 425px; background-color: #FCF8E3;">
								Plese Eneter Tag</div>
							<div id="emailAlreadyExit" class="col-md-8" id="tagDisplay"
								style="float: center; width: 425px; background-color: #FCF8E3; display: none;">Tag
								Already Exist</div> -->
							<div class="col-md-8" class="tag-data" id="tagDisplay"
								style="float: center; width: 425px; height: 100px; border: 1px solid #ccc; border-radius: 4px; padding-left: 5px;">
							
								<s:iterator value="digitalManuscriptVO.tagList" status="var">
						
								<div class='tag-data-shell'>
								<s:hidden id="%{#var.index}" name="digitalManuscriptVO.tagList[%{#var.index}].id"></s:hidden>
                                <s:textfield cssStyle="border-width: 0px;" name="digitalManuscriptVO.tagList[%{#var.index}].name"></s:textfield> 
		                        <a href='#' class='thumbnail-close'>×</a>
								</div>
								</s:iterator>
							</div>
						</div> 
						</div>
							
							<div class="form-group row">
								<label class="col-md-4 control-label">Summary</label>
							</div>
							<div class="form-group row">
						          <div class="col-md-12">
						              <s:textarea cssClass="form-control" name="digitalManuscriptVO.summary" maxlength="3500"/>
						          </div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 control-label">Table Of Contents</label>
							</div>
							<div class="form-group row">
						          <div class="col-md-12">
						              <s:textarea cssClass="form-control" name="digitalManuscriptVO.tableOfContents" maxlength="3500"/>
						          </div>
							</div>
							 
						</fieldset>
				      
				     	<br><br>
						<fieldset>
							<legend style="color: #3C763D;">Specific Contribution</legend>
							<div class="form-group row">
								<label class="col-md-4 control-label">To Ayurveda</label>
							</div>
							<div class="form-group row">
						          <div class="col-md-12">
						              <s:textarea cssClass="form-control" name="digitalManuscriptVO.contributionToAyurveda" maxlength="3500"/>
						          </div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 control-label">Uniqueness of Work</label>
							</div>
							<div class="form-group row">
						          <div class="col-md-12">
						              <s:textarea cssClass="form-control" name="digitalManuscriptVO.uniquenessOfWork" maxlength="3500"/>
						          </div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 control-label">Other Details</label>
							</div>
							<div class="form-group row">
						          <div class="col-md-12">
						              <s:textarea cssClass="form-control" name="digitalManuscriptVO.anyOtherDetails" maxlength="1500"/>
						          </div>
							</div>
						</fieldset>
						
						<div class="form-group row buttons">
					      	  <div class="col-md-4">
					      	  	  <!-- <input type="button" class="btn btn-lg btn-primary btn-block" value="previous" id="previous-manuscript"> -->
					      	  </div>
					      	  <div class="col-md-4">
				      			  <input type="button" class="btn btn-lg btn-danger btn-block" value="Cancel" id="cancel-manuscript">
			      			  </div>
				      		  <div class="col-md-4">
				      		  	  <input type="button" class="btn btn-lg btn-primary btn-block" value="Next" id="submit-manuscript">
					      	  </div>
				      	  </div>
				      </div>
				      <div class="tab-pane fade container-center" id="author"  style="max-width: 100%;">
						<br><br>
							<fieldset>
							<legend style="color: #3C763D;">Author</legend>
							<div class="author-search alert alert-success">
								<div class="form-group row">
									<div class="col-md-4">
										<label class="control-label">Search By Name</label>
										<span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
					          				data-toggle="popover" data-content="Type the first letters of the Author's name in the auto complete field and the 
					          				related records will be fetched if present and then select the author's name you want to refer.Otherwise click on 
					          				ADD NEW RECORD and add a new author."></span>
					          		</div>
									<div class="col-md-6">
									    <s:textfield cssClass="form-control" id="authorSearch" maxlength="50" placeholder="Autocomplete Field.."/>
									</div>
									<div class="col-md-2">
					      			  <input type="button" class="btn btn-lg btn-success btn-block" value="new" id="add-author">
					      			  </div>
								</div>
							</div>
							<div class="author-data">
								  <div class="form-group row">
							          <label class="col-md-4 control-label">Name</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" id="authorName" name="digitalManuscriptVO.authorVO.name" maxlength="100"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Diacritical)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" id="authorDiacriticName" name="digitalManuscriptVO.authorVO.diacriticName" maxlength="100"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Vernacular)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" id="authorRegionalName" name="digitalManuscriptVO.authorVO.regionalName" maxlength="100"/>
							          </div>
							      </div>
							      <div class="form-group row">
							      	  <div class="col-md-12">
								      	  <div class="alert alert-warning validation-warning">
								      	  	  <h6>Tip : You must enter at least one of the three form fields (ie. name, regional name, diacritical name)</h6>
										  </div>
							      	  </div>
							      </div>
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Period of The Author</label>
							          <div class="col-md-6">
							              <s:textfield cssClass="form-control" id="authorPeriod" name="digitalManuscriptVO.authorVO.period" maxlength="10"/>
							          </div>
							          <div class="col-md-2">
							              <s:select list="#{'AD':'AD', 'BC':'BC'}" name="digitalManuscriptVO.authorVO.periodEra" 
							              	value="digitalManuscriptVO.authorVO.periodEra" cssClass="form-control" id="periodEra"/>
							          </div>
							      </div>
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Author's Life History</label>
							          <div class="col-md-8">
							              <s:textarea cssClass="form-control" name="digitalManuscriptVO.authorVO.lifeHistory" id="authorLifeHistory" maxlength="1500"/>
							          </div>
							      </div>
							</div>
						</fieldset>
						<div class="commentator-container">
							<fieldset>
								<legend style="color: #3C763D;">Commentator</legend>
								<div class="commentator-search alert alert-success">
									<div class="form-group row">
										<div class="col-md-4">
											<label class="control-label">Search By Name</label>
											<span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
						          				data-toggle="popover" data-content="Type the first letters of the Commentator's name in the auto complete field and the 
						          				related records will be fetched if present and then select the Commentator's name you want to refer.Otherwise click on 
						          				ADD NEW RECORD and add a new Commentator."></span>
					          			</div>
										<div class="col-md-6">
										    <s:textfield cssClass="form-control" id="commentatorSearch" maxlength="50" placeholder="Autocomplete Field.."/>
										</div>
										<div class="col-md-2">
						      				<input type="button" class="btn btn-lg btn-success btn-block" value="new" id="add-commentator">
						      			</div>
									</div>
								</div>
							</fieldset>
							<div class="commentator-data">
								  <div class="form-group row">
										<label class="col-xs-4 control-label">Commentator Name</label>
										<div class="col-xs-8">
							        		<s:textfield cssClass="form-control" name="digitalManuscriptVO.commentatorVO.name" id="commentatorName" maxlength="50" disabled="false"/>
										</div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Diacritical)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" name="digitalManuscriptVO.commentatorVO.diacriticName" id="commentatorDiacriticName" maxlength="50"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Vernacular)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" name="digitalManuscriptVO.commentatorVO.regionalName" id="commentatorRegionalName" maxlength="50"/>
							          </div>
							      </div>
							</div>
						</div>
						<div class="translator-container">
							<fieldset>
								<legend style="color: #3C763D;">Translator</legend>
								<div class="translator-search alert alert-success">
									<div class="form-group row">
										<div class="col-md-4">
											<label class="control-label">Search By Name</label>
											<span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
							          				data-toggle="popover" data-content="Type the first letters of the Translator's name in the auto complete field and the 
							          				related records will be fetched if present and then select the Translator's name you want to refer.Otherwise click on 
							          				ADD NEW RECORD and add a new Translator."></span>
										</div>
										<div class="col-md-6">
										    <s:textfield cssClass="form-control" id="translatorSearch" maxlength="50" placeholder="Autocomplete Field.."/>
										</div>
										<div class="col-md-2">
						      				<input type="button" class="btn btn-lg btn-success btn-block" value="new" id="add-translator">
						      			</div>
									</div>
								</div>
							</fieldset>
							<div class="translator-data">
								  <div class="form-group row">
										<label class="col-xs-4 control-label">Translator Name</label>
										<div class="col-xs-8">
							        		<s:textfield cssClass="form-control" name="digitalManuscriptVO.translatorVO.name" id="translatorName" maxlength="50" disabled="false"/>
										</div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Diacritical)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" name="digitalManuscriptVO.translatorVO.diacriticName" id="translatorDiacriticName" maxlength="50"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Vernacular)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" name="digitalManuscriptVO.translatorVO.regionalName" id="translatorRegionalName" maxlength="50"/>
							          </div>
							      </div>
							</div>
						</div>
						
						<div class="subcommentator-container">
							<fieldset>
								<legend style="color: #3C763D;">Sub - Commentator</legend>
								<div class="subcommentator-search alert alert-success">
									<div class="form-group row">
										<div class="col-md-4 ">
											<label class="control-label">Search By Name</label>
											<span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
								          				data-toggle="popover" data-content="Type the first letters of the Sub-Commentator's name in the auto complete field and the 
								          				related records will be fetched if present and then select the Sub-Commentator's name you want to refer from the existing list.Otherwise click on 
								          				ADD NEW RECORD and add a new Sub-Commentator"></span>
										</div>
										<div class="col-md-6">
										    <s:textfield cssClass="form-control" id="subCommentatorSearch" maxlength="50" placeholder="Autocomplete Field.."/>
										</div>
										<div class="col-md-2">
						      				<input type="button" class="btn btn-lg btn-success btn-block" value="new" id="add-subCommentator">
						      			</div>
									</div>
								</div>
							</fieldset>
							<div class="subCommentator-data">
								  <div class="form-group row">
										<label class="col-xs-4 control-label">Sub Commentator Name</label>
										<div class="col-xs-8">
							        		<s:textfield cssClass="form-control" name="digitalManuscriptVO.subCommentatorVO.name" id="subCommentatorName" maxlength="50" disabled="false"/>
										</div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Vernacular)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" name="digitalManuscriptVO.subCommentatorVO.diacriticName" id="subCommentatorDiacriticName" maxlength="50"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Name (in Vernacular)</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" name="digitalManuscriptVO.subCommentatorVO.regionalName" id="subCommentatorRegionalName" maxlength="50"/>
							          </div>
							      </div>
							</div>
						</div>
						
						<%-- <fieldset>
							<legend>Author</legend>
							<div class="author-search alert alert-success">
								<div class="form-group row">
									<label class="col-md-4 control-label">Search By Name</label>
									<div class="col-md-6">
									    <s:textfield cssClass="form-control" id="authorSearch" maxlength="50"/>
									</div>
									<div class="col-md-2">
					      			  <input type="button" class="btn btn-lg btn-success btn-block" value="new" id="add-author">
					      			  </div>
								</div>
							</div>
							<div class="author-data">
								  <div class="form-group row">
							          <label class="col-md-4 control-label">Author's Name</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" id="authorName" name="digitalManuscriptVO.authorVO.name" maxlength="50"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Diacritical Name</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" id="authorDiacriticName" name="digitalManuscriptVO.authorVO.diacriticName" maxlength="50"/>
							          </div>
							      </div>
							      
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Regional Name</label>
							          <div class="col-md-8">
							              <s:textfield cssClass="form-control" id="authorRegionalName" name="digitalManuscriptVO.authorVO.regionalName" maxlength="50"/>
							          </div>
							      </div>
							      <div class="form-group row">
							      	  <div class="col-md-12">
								      	  <div class="alert alert-warning validation-warning">
								      	  	  <h6>You must enter at least one of the three form fields (ie. name, regional name, diacritical name)</h6>
										  </div>
							      	  </div>
							      </div>
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Period of The Author</label>
							          <div class="col-md-6">
							              <s:textfield cssClass="form-control" id="authorPeriod" name="digitalManuscriptVO.authorVO.period" maxlength="10"/>
							          </div>
							          <div class="col-md-2">
							              <s:select list="#{'AD':'AD', 'BC':'BC'}" name="digitalManuscriptVO.authorVO.periodEra" 
							              	value="digitalManuscriptVO.authorVO.periodEra" cssClass="form-control" id="periodEra"/>
							          </div>
							      </div>
							      <div class="form-group row">
							          <label class="col-md-4 control-label">Author's Life History</label>
							          <div class="col-md-8">
							              <s:textarea cssClass="form-control" name="digitalManuscriptVO.authorVO.lifeHistory" id="authorLifeHistory" maxlength="1500"/>
							          </div>
							      </div>
							</div>
						</fieldset> --%>
						<div class="manuscript-specific">
							<fieldset>
								<legend style="color: #3C763D;">Scribe</legend>
								<div class="scribe-search alert alert-success">
								<div class="form-group row">
									<label class="col-md-4 control-label">Search By Name</label>
									<div class="col-md-6">
									    <s:textfield cssClass="form-control" id="scribeSearch" maxlength="50" placeholder="Autocomplete Field.."/>
									</div>
									<div class="col-md-2">
					      			  <input type="button" class="btn btn-lg btn-success btn-block" value="new" id="add-scribe">
					      			  </div>
									</div>
								</div>
								<div class="scribe-data">
									  <div class="form-group row">
								          <label class="col-md-4 control-label">Scribe's Name</label>
								          <div class="col-md-8">
								              <s:textfield cssClass="form-control" id="scribeName" name="digitalManuscriptVO.scribeVO.name" maxlength="50"/>
								          </div>
								      </div>
								      
								      <div class="form-group row">
								          <label class="col-md-4 control-label">Name (in Diacritical)</label>
								          <div class="col-md-8">
								              <s:textfield cssClass="form-control" id="scribeDiacriticName" name="digitalManuscriptVO.scribeVO.diacriticName" maxlength="50"/>
								          </div>
								      </div>
								      
								      <div class="form-group row">
								          <label class="col-md-4 control-label">Name (in Vernacular)</label>
								          <div class="col-md-8">
								              <s:textfield cssClass="form-control" id="scribeRegionalName" name="digitalManuscriptVO.scribeVO.regionalName" maxlength="50"/>
								          </div>
								      </div>
								</div>
							</fieldset>
						</div>
					      <div class="form-group row buttons">
					      	  <div class="col-md-4">
								 <input type="button" class="btn btn-lg btn-primary btn-block" value="Previous" id="previous-author">
					      	  </div>
					      	  <div class="col-md-4">
				      			  <input type="button" class="btn btn-lg btn-danger btn-block" value="Cancel" id="cancel-author">
			      			  </div>
				      		  <div class="col-md-4">
				      		  	  <input type="button" class="btn btn-lg btn-primary btn-block" value="Next" id="submit-author">
					      	  </div>
				      	  </div>
					</div>
					<div class="tab-pane fade container-center" id="frame"  style="max-width: 100%;">
						<br>
						<div class="form-group row">
					      	  <div class="col-md-12">
						      	  <div class="alert alert-warning validation-warning">
						      	  	  <h6>You can choose multiple files by using ctrl key</h6>
								  </div>
					      	  </div>
					      </div>
						<div class="form-group row alert alert-success">
							<div class=" col-md-4">
								<label class="control-label" style="padding-top: 15px;">Upload File</label>
								<span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
								          	data-toggle="popover" data-content="Click on the BROWSE button to browse the images you want to upload.Open the destination folder and select the images.
								          											Then click on UPLOAD button and wait for the response to upload the images before clicking the SUBMIT button in last tab."></span>
							</div>
							<div class="col-xs-8 col-md-2" style="padding-top: 4px;">
								<span class="btn btn-default btn-file">
									<span>Browse</span>
									<input name="photo" type="file" id="photo" multiple="multiple"/>
								</span>
							</div>
							<div class="col-md-4"  style="padding-top: 10px;">
								<span class="file-info"></span>
							</div>
							<div class="col-md-2">
								<input type="button" value="Upload" id="uploadImageButton" class="btn btn-lg btn-success btn-block" style="height: 37px; padding-top: 6px; font-size: medium;"/>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-4">
							</div>
							<div class="col-md-4">
								<span class="loading-container"><img src="<%=request.getContextPath()%>/assets/images/loading.gif" width=100em height=auto></span>
							</div>
							</div> 
							<div class="form-group row" style="margin-left: 10%;display: none;" id="thumbnaildisplay">
							<div style="width: 40em; max-height: 32em;border:1px solid #ddd;">
							<div id="selectbutton-pannel">
							<input type="button" id="selectbtn" class="selectbutton btn-primary" value="Select" onclick="selectedIteam()">
							<input type="checkbox" name="imgselect" id="selectframe" style="display:none;"> 
							<input type="checkbox" name="allimgselect" id="selectall" onclick="selectAll();"> Select All
							<input type="button" class="selectbutton btn-danger" id="delimg" value="Delete" style="display: none;float: right;" onclick="deleteImage();">
							</div>
							<div class="panel-body" style="max-width: 40em; max-height: 25em; overflow-y:scroll;border:1px solid #ddd; ">
								<!-- <div class="imageContainer" id="imageContainer">
								</div> -->
						</div>
						</div>
						</div> 
					      <div class="form-group row buttons container-center">
					      	  <div class="col-md-4">
								 <input type="button" class="btn btn-lg btn-primary btn-block" value="Previous" id="previous-frame">
					      	  </div>
					      	  <div class="col-md-4">
				      			  <input type="button" class="btn btn-lg btn-danger btn-block" value="Cancel" id="cancel-frame">
			      			  </div>
				      		  <div class="col-md-4">
				      		  	  <input type="button" class="btn btn-lg btn-primary btn-block" value="Next" id="submit-frame">
					      	  </div>
				      	  </div> 
		      	  	</div>
		      	  	<div class="tab-pane fade container-center" id="nmm"  style="max-width: 100%;">
						<br>
						<div class="panel-group" id="accordion">
						<div id="accordionPanelHeader" class="panel panel-default appTable">
							<div class="panel-heading" data-target="#collapseOne"
								data-toggle="collapse" data-parent="#accordion"
								style="background: #DFDDDD;">Subject Details</div>
						 <div id="collapseOne" class="panel-collapse collapse in" style="padding: 5px;">
							<div class="form-group row">
								<label class="col-md-4 control-label">Nature Of Collection</label>
								<div class="col-md-8 btn-group" data-toggle="buttons">
									<label class="col-xs-4 btn btn-primary"
										id="natureOfCollection1Container"> <input type="radio"
										id="natureOfCollection1" value="1">Personal
									</label> <label class="col-xs-4 btn btn-primary"
										id="natureOfCollection2Container"> <input type="radio"
										id="natureOfCollection2" value="0">Institutional
									</label>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 control-label">Bound</label>
								<div class="col-md-8 btn-group" data-toggle="buttons">
									<label class="col-xs-4 btn btn-primary"
										id="isBound1Container"> <input type="radio"
										id="isBound1" value="1">Yes
									</label> <label class="col-xs-4 btn btn-primary"
										id="isBound2Container"> <input type="radio"
										id="isBound2" value="0">No
									</label>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 control-label">Source Of Catalogue</label>
								<div class="col-md-8">
									<s:select 
										list="sourceOfCatagoryTypes"
										cssClass="form-control"
										name="digitalManuscriptVO.sourceOfCatalogue"
										autoComplete="false" maxlength="50" />
								</div>
							</div>
							 <div class="form-group row">
					          <label class="col-md-4 control-label">Catalogue Number</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" name="digitalManuscriptVO.catalogueNumber" maxlength="100"/>
					          </div>
					      </div>
					         <div class="form-group row">
					          <label class="col-md-4 control-label">Catalogue Details</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" name="digitalManuscriptVO.catalogueDetails" maxlength="100"/>
					          </div>
					      </div>
						  <div class="form-group row">
				          <label class="col-md-4 control-label">Documentation</label>
				          <div class="col-md-8">
		                  <s:select list="manuscriptDocumentationTypes" cssClass="form-control"
				              name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" maxlength="50"/>
				          </div>
				        </div>
				        <div class="form-group row">
					          <label class="col-md-4 control-label">Number Of Folios</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" name="digitalManuscriptVO.totalNumberOfFolios" maxlength="100" id="folios" readonly="true"/>
					          </div>
					      </div>
					        <div class="form-group row">
					          <label class="col-md-4 control-label">Number Of Illustrations</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" name="digitalManuscriptVO.totalNumberOfMaps" maxlength="100"/>
					          </div>
					      </div>
								<div class="form-group row">
								<label class="col-md-4 control-label">Condition Of Manuscript</label>
								<div class="col-md-8">
									<s:select
										list="manuscriptConditionTypes"
										cssClass="form-control"
										name="digitalManuscriptVO.conditionOfManuscript"
										autoComplete="false" maxlength="50" />
								</div>
							</div>
						  <div class="form-group row">
					       <div class="col-md-4">
					          <label class="control-label">Digitized By</label>
					          <span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
								          	data-toggle="popover" data-content="Start typing the digitizer name from the begining in the auto-complete field and you will get the related digitizer list if present in the system.Then select the proper digitizer from the list."></span>
							</div>
					          <div class="col-md-8">
					          	  <s:hidden name="digitalManuscriptVO.digitizerId" id="digitiserId"></s:hidden>
					              <s:textfield cssClass="form-control" name="digitalManuscriptVO.digitizedBy" id="digitiserName" maxlength="100"  placeholder="Autocomplete Field.."/>
					          </div>
					      </div> 
					      <div class="form-group row">
					          <label class="col-md-4 control-label">Beginning Line</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="beginningLine" name="digitalManuscriptVO.beginningLine" maxlength="250" placeholder="Multilingual field"/>
					          </div>
					      </div>
					      <div class="form-group row">
					          <label class="col-md-4 control-label">Ending Line</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="endingLine" name="digitalManuscriptVO.endingLine" placeholder="Multilingual field" maxlength="250"/>
					          </div>
					      </div>
					      <div class="form-group row">
					          <label class="col-md-4 control-label">Colophon</label>
					          <div class="col-md-8">
					              <s:textfield cssClass="form-control" id="colophon" name="digitalManuscriptVO.colophon" placeholder="Multilingual field" maxlength="100"/>
					          </div>
					      </div>
					       </div>
					        </div>
							<div class="panel panel-default appTable" id="accordionPanelHeader1">
								<div class="panel-heading" data-toggle="collapse"
									data-parent="#accordion" data-target="#collapseTwo"
									style="background: #DFDDDD;">Technical Details
									</div>
								<div id="collapseTwo" class="panel-collapse collapse" style="padding: 5px;">
								<!-- <div class="image-details manuscript-specific"> -->
							<div class="form-group row">
						        <label class="col-md-4 control-label">Average Image Height</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageHeight" name="digitalManuscriptVO.nmmDetailsVO.height" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">Average Image Width</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageWidth" name="digitalManuscriptVO.nmmDetailsVO.width" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">Median Created Date</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageCreatedDate" name="digitalManuscriptVO.nmmDetailsVO.createdDate" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">Median Digitised Date</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageDigitisedDate" name="digitalManuscriptVO.nmmDetailsVO.digitisedDate" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">Camera Make</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageCameraMake" name="digitalManuscriptVO.nmmDetailsVO.cameraMake" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">Camera Model</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageCameraModel" name="digitalManuscriptVO.nmmDetailsVO.cameraModel" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">X Resolution</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageXResolution" name="digitalManuscriptVO.nmmDetailsVO.xResolution" maxlength="50"/>
						        </div>
						    </div>
						    <div class="form-group row">
						        <label class="col-md-4 control-label">Y Resolution</label>
						        <div class="col-md-8">
						            <s:textfield cssClass="form-control" id="imageYResolution" name="digitalManuscriptVO.nmmDetailsVO.yResolution" maxlength="50"/>
						        </div>
						    </div>
							</div>
							</div>
							<div class="form-group row buttons container-center" style="padding-top: 20px;">
					      	  <div class="col-md-4">
								 <input type="button" class="btn btn-lg btn-primary btn-block" value="Previous" id="previous-nmm">
					      	  </div>
					      	  <div class="col-md-4">
				      			  <input type="button" class="btn btn-lg btn-danger btn-block" value="Cancel" id="cancel-nmm">
			      			  </div>
				      		  <div class="col-md-4">
				      		  	  <input type="button" class="btn btn-lg btn-primary btn-block" value="Next" id="submit-nmm">
					      	  </div>
				      	  </div> 
				      	  </div>
		      	  	</div>
				      <div class="tab-pane fade container-center" id="publication"  style="max-width: 100%;">
				      	<br>
				      	<fieldset>
							<legend style="color: #3C763D;">Source Details</legend>
							<div class="form-group row">
								<label class="col-xs-4 control-label">Name</label>
								<div class="col-xs-8">
									<s:textfield cssClass="form-control" name="digitalManuscriptVO.organisationVO.name" id="organisationName" maxlength="50" placeholder="Autocomplete Field.."/>
						        </div>
					        </div>
					        <div class="form-group row">
								<label class="col-xs-4 control-label">Website</label>
								<div class="col-xs-8">
									<s:textfield cssClass="form-control" name="digitalManuscriptVO.organisationVO.website" id="organisationWebsite" maxlength="50"/>
						        </div>
					        </div>
					         <div class="form-group row">
								<label class="col-xs-4 control-label">Phone Number</label>
								<div class="col-xs-8">
									<s:textfield cssClass="form-control" name="digitalManuscriptVO.organisationVO.phoneNumber" id="organisationPhone" maxlength="50"/>
						        </div>
					        </div>
					         <div class="form-group row">
								<label class="col-xs-4 control-label">E-mail</label>
								<div class="col-xs-8">
									<s:textfield cssClass="form-control" name="digitalManuscriptVO.organisationVO.email" id="organisationEmail" maxlength="50"/>
						        </div>
					        </div>
					       <%--  <div class="form-group row">
								<label class="col-xs-4 control-label">Acronym</label>
								<div class="col-xs-8">
									<s:textfield cssClass="form-control" name="digitalManuscriptVO.organisationVO.acronym" id="organisationAcronym" maxlength="50"/>
						        </div>
					        </div> --%>
							<div class="form-group row">							        
						        <label class="col-md-4 control-label">Address</label>
								<div class="col-md-8">
						            <s:textarea cssClass="form-control" name="digitalManuscriptVO.organisationVO.address" maxlength="250" id="organisationAddress"/>
						        </div>
					        </div>
					        <div class="form-group row">
					        <label class="col-md-4 control-label">Type</label>
							<div class="col-md-8 btn-group" data-toggle="buttons">
								<label class="col-xs-4 btn btn-primary" id="isOrganisation1Container">
									<input type="radio" id="isOrganisation1" value="1">Individual
								</label>
								<label class="col-xs-4 btn btn-primary" id="isOrganisation2Container">
									<input type="radio" id="isOrganisation2" value="0">Institution
								</label>
							</div>
				        </div>
						</fieldset>
						<fieldset>
							<legend style="color: #3C763D;">Publication Details</legend>
							<div class="form-group row is-published">
					        <label class="col-md-4 control-label">Published</label>
							<div class="col-md-8 btn-group" data-toggle="buttons">
								<label class="col-xs-4 btn btn-primary" id="isPublished1Container">
									<input type="radio" id="isPublished1" value="1">Yes
								</label>
								<label class="col-xs-4 btn btn-primary" id="isPublished2Container">
									<input type="radio" id="isPublished2" value="0">No
								</label>
							</div>
					        </div>
					        <div class="published">
					        	<div class="form-group row">
									<label class="col-md-4 control-label">Name of Publisher</label>
									<div class="col-md-8">
							            <s:textfield cssClass="form-control" name="publicationVO.publisherVO.name" id="publisherName" maxlength="50" placeholder="Autocomplete Field.."/>
							        </div>
						        </div>
						        
						        <div class="form-group row">
									<label class="col-md-4 control-label">Name of Editor</label>
									<div class="col-md-8">
							            <s:textfield cssClass="form-control" name="publicationVO.editorVO.name" id="editorName" maxlength="50"/>
							        </div>
						        </div>
						        
						        <div class="form-group row">
									<label class="col-md-4 control-label">Year of Publication</label>
							        <div class="col-md-8">
							            <s:textfield cssClass="form-control" name="publicationVO.yaerOfPublication" id="publicationYear" maxlength="10"/>
							        </div>
						        </div>
								<div class="form-group row">							        
							        <label class="col-md-4 control-label">Address</label>
									<div class="col-md-8">
							            <s:textarea cssClass="form-control" name="publicationVO.publisherVO.address" id="publisherAddress" maxlength="250"/>
							        </div>
						        </div>
						        <div class="form-group row">
							        <label class="col-md-4 control-label">Number of Pages</label>
									<div class="col-md-8">
							            <s:textfield cssClass="form-control" name="publicationVO.noOfPages" id="noOfPages" maxlength="50"/>
							        </div>
						        </div>
						        <div class="form-group row">
							        <label class="col-md-4 control-label">Price (INR)</label>
									<div class="col-md-8">
							            <s:textfield cssClass="form-control" name="publicationVO.price" id="price" maxlength="50"/>
							        </div>
						        </div>
						        <div class="form-group row">
							        <label class="col-md-4 control-label">Available In Print</label>
									<div class="col-md-8 btn-group" data-toggle="buttons">
										<label class="col-xs-4 btn btn-primary" id="isAvailable1Container">
											<input type="radio" id="isAvailable1" value="1">Yes
										</label>
										<label class="col-xs-4 btn btn-primary" id="isAvailable2Container">
											<input type="radio" id="isAvailable2" value="0">No
										</label>
										<label class="col-xs-4 btn btn-primary" id="isAvailable3Container">
											<input type="radio" id="isAvailable3" value="2">Unknown
										</label>
									</div>
						        </div>
					        </div>
							<div class="form-group row buttons">
						      	  <div class="col-md-4">
						      	  	  <input type="button" class="btn btn-lg btn-primary btn-block" value="Previous" id="previous-publisher">
						      	  </div>
						      	  <div class="col-md-4">
					      			  <input type="button" class="btn btn-lg btn-danger btn-block" value="Cancel" id="cancel-publisher">
				      			  </div>
					      		  <div class="col-md-4">
					      		  	  <s:submit cssClass="btn btn-lg btn-primary btn-block"  value="Submit" id="submit-publisher"/>
						      	  </div>
					      	  </div>
						</fieldset>
			      	</div>
			</div>
			</div>
		</s:form>
	</div>
</div>
</div>
<%@ include file='../layout/footer.jsp' %>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/Validator.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/rangy-core.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.selector.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.preferences.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.inputmethods.js"></script>
<script type="text/javascript">
	$('#originalLink').click(function(e) {
		if($('#specificCategoryId').val() == 1) {
			$('#originalWorkRef').hide();
			$('#originalCommRef').hide();

			$('#originalWorkId').attr('disabled' , true);
			$('#originalCommId').attr('disabled' , true);
		} else {
			if($('#specificCategoryId').val() == 3) {
				$('#originalCommRef').fadeIn();
				$('#originalCommId').attr('disabled' , false);
				$('#originalWorkRef').hide();
			}else {
				$('#originalWorkRef').fadeIn();
				$('#originalWorkId').attr('disabled' , false);
				$('#originalCommRef').hide();
			}
		}
		e.preventDefault();
	});
	
	function onChangeSpCategory() {
		$('.commentator-container').hide();
		$('.translator-container').hide();
		$('.subCommentator-container').hide();
		$('#originalWorkRef').hide();
		$('#originalCommRef').hide();
		$('#originalWorkId').attr('disabled' , true);
		$('#originalCommId').attr('disabled' , true);
		$('#commentatorId').attr('disabled' , true);
		$('#subCommentatorId').attr('disabled' , true);
		$('#commentatorName').attr('disabled' , true);
		$('#subCommentatorName').attr('disabled' , true);
		$('#translatorId').attr('disabled' , true);
		$('#translatorName').attr('disabled' , true);
		var b=$('#specificCategoryId').val();
		var c=b.length;
		for(var j=0;j<b.length;j++ ){
		 if(/* $('#specificCategoryId').val() */ b[j]== 1) {
			$('.commentator-container').hide();
			$('.translator-container').hide();
			$('.subCommentator-container').hide();
		/* } else {
			$('#originalLink').show();
		}
			$('.commentator-container').hide();
			$('.subCommentator-container').hide();
			$('.translator-container').hide(); */
			
	} else if(b[j] == 2) {
			$('.commentator-container').show();
			$('#commentatorId').attr('disabled' , false);
			$('#commentatorName').attr('disabled' , false);
			$('#commentatorDiacriticName').attr('disabled' , false);
			$('#commentatorRegionalName').attr('disabled' , false);
		} else if(b[j] == 4) {
			$('.translator-container').show();
			$('#translatorId').attr('disabled' , false);
			$('#translatorName').attr('disabled' , false);
			$('#translatorDiacriticName').attr('disabled' , false);
			$('#translatorRegionalName').attr('disabled' , false);
		} else if(b[j]== 3) {
			$('.subCommentator-container').show();
			$('#subCommentatorId').attr('disabled' , false);
			$('#subCommentatorName').attr('disabled' , false);
			$('#subCommentatorDiacriticName').attr('disabled' , false);
			$('#subCommentatorRegionalName').attr('disabled' , false);
		} 
	}
	};
	
	$('.btn').button(); /* Toggles between the radio buttons */
	
	$('.main-tab').click(function(e){
		/* Handler for opening respective div
			on new record : Book or Manuscript */
		if($(e.target).attr('id') == "bookContainer") {
			$('#publicationTabs').hide();
			$('#unpublished').hide();
			$('#documentType').val(1);
			$('.manuscriptContainer').hide();
			$('.manuscript-specific').hide();
			$('#manuscriptBundle').hide();
			$('.is-published').hide();
			$('.published').show();
		} else if($(e.target).attr('id') == "manuscriptContainer") {
			$('.bookContainer').hide();
			$('#documentType').val(2);
			$('.manuscript-specific').show();
			$('.is-published').show();
			$('.published').hide();
		}
		$(e.target).parent().parent().fadeOut();		
		$('.form-container').fadeIn();
	});
	
	$(document).ready(function() {
		$('#submit-publisher').show();
		$('#originalWorkRef').hide();
		$('#originalCommRef').hide();
		$('#originalWorkId').attr('disabled' , true);
		$('#originalCommId').attr('disabled' , true);
		if($('#documentType').val() > 0) {
			/* If not new record, then hide opening div (Book/Manuscript) */
			$('.main-tab').parent().parent().hide();
			$('.form-container').show();
			if($('#documentType').val() == 1) {
				$('.manuscriptContainer').hide();
				$('#publicationTabs').hide();
				$('.manuscript-specific').hide();
				$('.is-published').hide();
				$('.published').show();
			} else if($('#documentType').val() == 2) {
				$('.bookContainer').hide();
				$('.manuscript-specific').show();
				$('.is-published').show();
				if($('#publicationId').val() > 0) {
					$('.published').show();
					$('#isPublished1Container').addClass('active');
					$('#isPublished2Container').removeClass('active');
				} else {
					$('.published').hide();
					$('#isPublished1Container').removeClass('active');
					$('#isPublished2Container').addClass('active');
				}
			}
		}
		if($('#specificCategoryId').val() == 1){
			$('#originalWorkRef').hide();
			$('#originalCommRef').hide();
			$('#originalWorkId').attr('disabled' , true);
			$('#originalCommId').attr('disabled' , true);
		}else {
			console.log($('#originalWorkId').val());
			
		 	if($('#parentFKId').val().length > 0 || $('#parentCommFKId').val().length > 0) {
		 		if($('#specificCategoryId').val() == 3) {
					$('#originalCommRef').show();
					$('#originalWorkRef').hide();
					$('#originalWorkId').attr('disabled' , true);
				}else {
					$('#originalWorkRef').show();
					$('#originalCommRef').hide();
					$('#originalCommId').attr('disabled' , true);
				}
		 	}
		}
		
		if($('#specificCategoryId').val() == 2) {
			$('.commentator-container').show();
			$('#commentatorId').attr('disabled' , false);
			$('#commentatorName').attr('disabled' , false);
			$('#commentatorDiacriticName').attr('disabled' , false);
			$('#commentatorRegionalName').attr('disabled' , false);
		} else if($('#specificCategoryId').val() == 4) {
			$('.translator-container').show();
			$('#translatorId').attr('disabled' , false);
			$('#translatorName').attr('disabled' , false);
			$('#translatorDiacriticName').attr('disabled' , false);
			$('#translatorRegionalName').attr('disabled' , false);
		} else if($('#specificCategoryId').val() == 3) {
			$('.subCommentator-container').show();
			$('#subCommentatorId').attr('disabled' , false);
			$('#subCommentatorName').attr('disabled' , false);
			$('#subCommentatorDiacriticName').attr('disabled' , false);
			$('#subCommentatorRegionalName').attr('disabled' , false);
		}
		
		if($('#authorId').val() > 0) {
			$('.author-data').show();
		}
		if($('#scribeId').val() > 0) {
			$('.scribe-data').show();
		}
		if($('#commentatorId').val() > 0) {
			$('.commentator-container').show();
		}
		
		if($('#subCommentatorId').val() > 0) {
			$('.subCommentator-container').show();
		}
		
		if($('#translatorId').val() > 0) {
			$('.translator-container').show();
		}	
		
		if($('#isBound').val() == $('#isBound1').val()) {
			$('#isBound1Container').addClass('active');
			$('#isBound2Container').removeClass('active');
		} else if($('#isBound').val() == $('#isBound2').val()) {
			$('#isBound1Container').removeClass('active');
			$('#isBound2Container').addClass('active');
		} else {
			$('#isBound1Container').removeClass('active');
			$('#isBound2Container').removeClass('active');
		}
		
		if($('#natureOfCollection').val() == $('#natureOfCollection1').val()) {
			$('#natureOfCollection1Container').addClass('active');
			$('#natureOfCollection2Container').removeClass('active');
		} else if($('#natureOfCollection').val() == $('#natureOfCollection2').val()) {
			$('#natureOfCollection1Container').removeClass('active');
			$('#natureOfCollection2Container').addClass('active');
		} else {
			$('#natureOfCollection1Container').removeClass('active');
			$('#natureOfCollection2Container').removeClass('active');
		}
		
		
		if($('#organisationType').val() == $('#isOrganisation1').val()) {
			$('#isOrganisation1Container').addClass('active');
			$('#isOrganisation2Container').removeClass('active');
		} else if($('#organisationType').val() == $('#isOrganisation2').val()) {
			$('#isOrganisation1Container').removeClass('active');
			$('#isOrganisation2Container').addClass('active');
		} else {
			$('#isOrganisation1Container').removeClass('active');
			$('#isOrganisation2Container').removeClass('active');
		}
		
		if($('#isAvailableValue').val() == $('#isAvailable1').val()) {
			/* If not new record, then populate the radio buttons based on saved data */
			$('#isAvailable1Container').addClass('active');
			$('#isAvailable2Container').removeClass('active');
			$('#isAvailable3Container').removeClass('active');
		} else if($('#isAvailableValue').val() == $('#isAvailable2').val()) {
			$('#isAvailable2Container').addClass('active');
			$('#isAvailable1Container').removeClass('active');
			$('#isAvailable3Container').removeClass('active');
		} else if($('#isAvailableValue').val() == $('#isAvailable3').val()) {
			$('#isAvailable3Container').addClass('active');
			$('#isAvailable2Container').removeClass('active');
			$('#isAvailable1Container').removeClass('active');
		}
		
		if($('#nmmDetailsId').val() > 0) {
			$('.image-details').show();
		}
		
		$('#beginningLine, #endingLine, #colophon ,#manuscriptDiacriticName,#manuscriptRegionalName').ime();
		if($('#fileDiskPathContainer').val().length > 0) {
			var countImage=0;
			var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
			var htmlString = "";
			var filePath;
			var str="";
			for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
				if(fileDiskPathObject[i].id != ""){
				 filePath = fileDiskPathObject[i].filePath.replace(/\\/g, "/");
			    htmlString += "<div id='div_" + fileDiskPathObject[i].id + "' class='img-thumbnail'>"; 
			    htmlString += "<input type='checkbox' id='"+fileDiskPathObject[i].id+"' name='check' style='display:none;' />";
				/* htmlString += "<a id='"+fileDiskPathObject[i].id+"' href='#' class='deleteimg-thumbnail'>×</a>"; */
				htmlString += "<img src='" + '/OMDS'+ '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";
				htmlString += "</div>";
				countImage++;
				}
			}
			var folio = Math.round(countImage/2);
			$('#folios').val(folio);
			$("#thumbnaildisplay").css('display','');
			$("#totalImg").text(countImage);
			$('.panel-body').append(htmlString);
			$('#selectframe').prop("checked",false);
			$('#selectall').prop("checked",false);
		}
	});
		
	var authorFlag = 0;
	var scribeFlag = 0;
	var editorFlag = 0;
	var commentatorFlag = 0;
	var subCommentatorFlag = 0;
	var translatorFlag = 0;
	var publisherFlag = 0;
	var organisationFlag = 0;
	var digitiserFlag = 0;
	
	var authorMap = {};
	var scribeMap = {};
	var editorMap = {};
	var commentatorMap = {};
	var subCommentatorMap = {};
	var translatorMap = {};
	var publisherMap = {};
	var organisationMap = {};
	var digitiserMap = {};
	var manuscriptMap = {};
	
	$(function() {
		$('#authorSearch').autocomplete({
			/* jQuery autocomplete handler for author based on author name */
	   	  source: function (request, response) {
		        $.getJSON("findAllAuthors.action?term=" + request.term + "&requestId=" + requestId + "&type=1" , function (data) {
		            response($.map(data.authors, function (item) {
		            	authorMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
	            		
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				
		    	if(ui.item.label != "Add New Record") {
		    		$('#authorId').val(ui.item.id);
		    		$('#authorName').val(authorMap[ui.item.id].name);
		    		$('#authorRegionalName').val(authorMap[ui.item.id].regionalName);
					$('#authorDiacriticName').val(authorMap[ui.item.id].diacriticName);
					$('#authorLifeHistory').val(authorMap[ui.item.id].lifeHistory);
					$('#authorPeriod').val(authorMap[ui.item.id].period);
					$('#periodEra').val(authorMap[ui.item.id].periodEra);
		    	} else {
		    		$('#authorId').val("");
		    		$('#authorName').val("");
		    		$('#authorRegionalName').val("");
					$('#authorDiacriticName').val("");
					$('#authorLifeHistory').val("");
					$('#authorPeriod').val("");
					$('#periodEra').val("");
		    	}
				$('.author-data').show();
				$('#authorName').focus();
				authorFlag = 1;
		 	},
		    minLength:1 
		 }).data("ui-autocomplete")._renderItem = function (ul, item) {
				/* Highlight label if it is Add New Record */
				var st2 = "add new record";
				var customClass = "";
				if(item.label.toLowerCase().indexOf(st2) != -1) {
					customClass = "dropdown-new-record";
				}
				
				return $("<li></li>")
		        .addClass(customClass) //item based custom class to li here
		        .append("<a href='#'>" + item.label + "</a>")
		        .data("ui-autocomplete-item", item)
		        .appendTo(ul);
		        
		    };
		
		    
	    $('#scribeSearch').autocomplete({
			/* jQuery autocomplete handler for author based on author name */
	   	  source: function (request, response) {
		        $.getJSON("findAllAuthors.action?term=" + request.term + "&requestId=" + requestId + "&type=2" , function (data) {
		            response($.map(data.authors, function (item) {
		            	scribeMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
	            		
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
		    	if(ui.item.label != "Add New Record") {
		    		$('#scribeId').val(ui.item.id);
		    		$('#scribeName').val(scribeMap[ui.item.id].name);
		    		$('#scribeRegionalName').val(scribeMap[ui.item.id].regionalName);
					$('#scribeDiacriticName').val(scribeMap[ui.item.id].diacriticName);
		    	} else {
		    		$('#scribeId').val("");
		    		$('#scribeName').val("");
		    		$('#scribeRegionalName').val("");
					$('#scribeDiacriticName').val("");
		    	}
				$('.scribe-data').show();
				$('#scribeName').focus();
				scribeFlag = 1;
		 	},
		    minLength:1 
		 }).data("ui-autocomplete")._renderItem = function (ul, item) {
				/* Highlight label if it is Add New Record */
				var st2 = "add new record";
				var customClass = "";
				if(item.label.toLowerCase().indexOf(st2) != -1) {
					customClass = "dropdown-new-record";
				}
				
				return $("<li></li>")
		        .addClass(customClass) //item based custom class to li here
		        .append("<a href='#'>" + item.label + "</a>")
		        .data("ui-autocomplete-item", item)
		        .appendTo(ul);
		        
		    };
			    
			    
	    $('#editorName').autocomplete({
			/* jQuery autocomplete handler for author based on author name */
	   	  source: function (request, response) {
		        $.getJSON("findAllAuthors.action?term=" + request.term + "&requestId=" + requestId + "&type=3" , function (data) {
		            response($.map(data.authors, function (item) {
		            	editorMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
	            		
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				$('#editorId').val(ui.item.id);
	    		$('#editorName').val(editorMap[ui.item.id].name);
				editorFlag = 1;
		 	},
		    minLength:1 
		 });
	    
	    $('#commentatorSearch').autocomplete({
			/* jQuery autocomplete handler for commentator based on commentator name */
	   	  source: function (request, response) {
		        $.getJSON("findAllAuthors.action?term=" + request.term + "&requestId=" + requestId + "&type=4" , function (data) {
		            response($.map(data.authors, function (item) {
		            	commentatorMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
	            		
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
		    	if(ui.item.label != "Add New Record") {
		    		$('#commentatorId').val(ui.item.id);
		    		$('#commentatorName').val(commentatorMap[ui.item.id].name);
		    		$('#commentatorRegionalName').val(commentatorMap[ui.item.id].regionalName);
					$('#commentatorDiacriticName').val(commentatorMap[ui.item.id].diacriticName);
		    	} else {
		    		$('#commentatorId').val("");
		    		$('#commentatorName').val("");
		    		$('#commentatorRegionalName').val("");
					$('#commentatorDiacriticName').val("");
		    	}
				$('.commentator-data').show();
				$('#commentatorName').focus();
				commentatorFlag = 1;
		 	},
		    minLength:1 
		 }).data("ui-autocomplete")._renderItem = function (ul, item) {
				/* Highlight label if it is Add New Record */
				var st2 = "add new record";
				var customClass = "";
				if(item.label.toLowerCase().indexOf(st2) != -1) {
					customClass = "dropdown-new-record";
				}
				
				return $("<li></li>")
		        .addClass(customClass) //item based custom class to li here
		        .append("<a href='#'>" + item.label + "</a>")
		        .data("ui-autocomplete-item", item)
		        .appendTo(ul);
		        
		    };
	    
	    $('#subCommentatorSearch').autocomplete({
			/* jQuery autocomplete handler for commentator based on commentator name */
	   	  source: function (request, response) {
		        $.getJSON("findAllAuthors.action?term=" + request.term + "&requestId=" + requestId + "&type=6" , function (data) {
		            response($.map(data.authors, function (item) {
		            	subCommentatorMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
	            		
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				$('#subCommentatorId').val(ui.item.id);
	    		$('#subCommentatorName').val(subCommentatorMap[ui.item.id].name);
				editorFlag = 1;
				
				if(ui.item.label != "Add New Record") {
		    		$('#subCommentatorId').val(ui.item.id);
		    		$('#subCommentatorName').val(subCommentatorMap[ui.item.id].name);
		    		$('#subCommentatorRegionalName').val(subCommentatorMap[ui.item.id].regionalName);
					$('#subCommentatorDiacriticName').val(subCommentatorMap[ui.item.id].diacriticName);
		    	} else {
		    		$('#subCommentatorId').val("");
		    		$('#subCommentatorName').val("");
		    		$('#subCommentatorRegionalName').val("");
					$('#subCommentatorDiacriticName').val("");
		    	}
				$('.subCommentator-data').show();
				$('#subCommentatorName').focus();
				subCommentatorFlag = 1;
		 	},
		    minLength:1 
		 }).data("ui-autocomplete")._renderItem = function (ul, item) {
				/* Highlight label if it is Add New Record */
				var st2 = "add new record";
				var customClass = "";
				if(item.label.toLowerCase().indexOf(st2) != -1) {
					customClass = "dropdown-new-record";
				}
				
				return $("<li></li>")
		        .addClass(customClass) //item based custom class to li here
		        .append("<a href='#'>" + item.label + "</a>")
		        .data("ui-autocomplete-item", item)
		        .appendTo(ul);
		        
		    };
	    
	    $('#translatorSearch').autocomplete({
			/* jQuery autocomplete handler for translator based on translator name */
	   	  source: function (request, response) {
		        $.getJSON("findAllAuthors.action?term=" + request.term + "&requestId=" + requestId + "&type=5" , function (data) {
		            response($.map(data.authors, function (item) {
		            	translatorMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
	            		
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				$('#translatorId').val(ui.item.id);
	    		$('#translatorName').val(translatorMap[ui.item.id].name);
				editorFlag = 1;
				
				/* If record is selected from dropdown, then populate rest of the fields based on saved data */
		    	if(ui.item.label != "Add New Record") {
		    		$('#translatorId').val(ui.item.id);
		    		$('#translatorName').val(translatorMap[ui.item.id].name);
		    		$('#translatorRegionalName').val(translatorMap[ui.item.id].regionalName);
					$('#translatorDiacriticName').val(translatorMap[ui.item.id].diacriticName);
		    	} else {
		    		$('#translatorId').val("");
		    		$('#translatorName').val("");
		    		$('#translatorRegionalName').val("");
					$('#translatorDiacriticName').val("");
		    	}
				$('.translator-data').show();
				$('#translatorName').focus();
				translatorFlag = 1;
		 	},
		    minLength:1 
		 }).data("ui-autocomplete")._renderItem = function (ul, item) {
				/* Highlight label if it is Add New Record */
				var st2 = "add new record";
				var customClass = "";
				if(item.label.toLowerCase().indexOf(st2) != -1) {
					customClass = "dropdown-new-record";
				}
				
				return $("<li></li>")
		        .addClass(customClass) //item based custom class to li here
		        .append("<a href='#'>" + item.label + "</a>")
		        .data("ui-autocomplete-item", item)
		        .appendTo(ul);
		        
		    };
			    
		$('#parentFKId').autocomplete({
			/* jQuery autocomplete handler for author based on manuscript name */
	   	  source: function (request, response) {
		        $.getJSON("findAllManuscript.action?term=" + request.term + "&requestId=" + requestId , function (data) {
		            response($.map(data.manuscripts, function (item) {
		            	manuscriptMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
		    	$('#originalCommId').attr('disabled' ,true);
				$('#originalWorkId').val(ui.item.id);
		 	},
		    minLength:1 
		 });
		
		$('#parentCommFKId').autocomplete({
			/* jQuery autocomplete handler for author based on manuscript name */
	   	  source: function (request, response) {
		        $.getJSON("findAllManuscript.action?term=" + request.term + "&requestId=" + requestId , function (data) {
		            response($.map(data.manuscripts, function (item) {
		            	manuscriptMap[item.id] = item;
		            	var labelData = "";
	            		if(item.regionalName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.regionalName;
	                	} else if(item.diacriticName.toLowerCase().indexOf(request.term) != -1) {
		            		labelData = item.diacriticName;
	                	} else {
	                		labelData = item.name;
	                	}
		                return {
	                		label: labelData,
		                    value: labelData,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
		    	$('#originalWorkId').attr('disabled' ,true);
				$('#originalCommId').val(ui.item.id);
		 	},
		    minLength:1 
		 });
		
		$('#publisherName').autocomplete({
		   	  source: function (request, response) {
		   			/* jQuery autocomplete handler for publisher based on publisher name */
			        $.getJSON("findAllPublishers.action?term=" + request.term + "&requestId=" + requestId, function (data) {
			            response($.map(data.publishers, function (item) {
			            	publisherMap[item.id] = item;
			                return {
		                		label: item.name,
			                    value: item.name,
			                    id : item.id
			                };
			            }));
			        });
			    },
			    select: function( event, ui ) {
			    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
					$('#publisherId').val(ui.item.id);
					$('#publisherAddress').val(publisherMap[ui.item.id].address);
					publisherFlag = 1;
			 	},
			    minLength:1 
			 });
			 
			 $('#tag').autocomplete({
					/* jQuery autocomplete handler for tags based on tag name */
			   	  source: function (request, response) {
				        $.getJSON("findAllTags.action?term=" + request.term + "&requestId=" + requestId , function (data) {
				            response($.map(data.tags, function (item) {
				            	organisationMap[item.id] = item;
				                return {
			                		label: item.name,
				                    value: item.name,
				                    id : item.id
				                };
				            }));
				        });
				    },
				    select: function( event, ui ) {
				    	/* if record is selected populate the rest of field*/
						$('#tempTagId').val(ui.item.id);
						tagFlag = 1;
				 	},
				    minLength:1 
				 });
			 
		
		$('#organisationName').autocomplete({
			/* jQuery autocomplete handler for organisation based on organisation name */
	   	  source: function (request, response) {
		        $.getJSON("findAllOrganisations.action?term=" + request.term + "&requestId=" + requestId , function (data) {
		            response($.map(data.organisations, function (item) {
		            	organisationMap[item.id] = item;
		                return {
	                		label: item.name,
		                    value: item.name,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				$('#organisationId').val(ui.item.id);
				$('#organisationWebsite').val(organisationMap[ui.item.id].website);
				$('#organisationPhone').val(organisationMap[ui.item.id].phoneNumber);
				$('#organisationEmail').val(organisationMap[ui.item.id].email);
				$('#organisationAddress').val(organisationMap[ui.item.id].address);
				$('#organisationAcronym').val(organisationMap[ui.item.id].acronym);
				if(organisationMap[ui.item.id].type == 1) {
					$('#isOrganisation1Container').addClass('active');
					$('#isOrganisation2Container').removeClass('active');
				} else if(organisationMap[ui.item.id].type == 0) {
					$('#isOrganisation2Container').addClass('active');
					$('#isOrganisation1Container').removeClass('active');
				}
				organisationFlag = 1;
		 	},
		    minLength:1 
		 });
		
		
		$('#digitiserName').autocomplete({
			/* jQuery autocomplete handler for organisation based on organisation name */
	   	  		source: function (request, response) {
		        $.getJSON("findAllDigitisers.action?term=" + request.term + "&requestId=" + requestId , function (data) {
		            response($.map(data.digitisers, function (item) {
		            	digitiserMap[item.id] = item;
		                return {
	                		label: item.firstName,
		                    value: item.firstName,
		                    id : item.id
		                };
		            }));
		        });
		    },
		    select: function( event, ui ) {
		    	/* If record is selected from dropdown, then populate rest of the fields based on saved data */
				$('#digitiserId').val(ui.item.id);
				digitiserFlag = 1;
		 	},
		    minLength:1 
		 });
		
		/* Style the autocomplete dropdown according to the bootstrap theme */
		$('#ui-id-1').wrap('<div class="dropdown"></div>');
		$('#ui-id-1').addClass('dropdown-menu');
		$('#ui-id-1 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-2').wrap('<div class="dropdown"></div>');
		$('#ui-id-2').addClass('dropdown-menu');
		$('#ui-id-2 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-3').wrap('<div class="dropdown"></div>');
		$('#ui-id-3').addClass('dropdown-menu');
		$('#ui-id-3 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-4').wrap('<div class="dropdown"></div>');
		$('#ui-id-4').addClass('dropdown-menu');
		$('#ui-id-4 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-5').wrap('<div class="dropdown"></div>');
		$('#ui-id-5').addClass('dropdown-menu');
		$('#ui-id-5 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-6').wrap('<div class="dropdown"></div>');
		$('#ui-id-6').addClass('dropdown-menu');
		$('#ui-id-6 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-7').wrap('<div class="dropdown"></div>');
		$('#ui-id-7').addClass('dropdown-menu');
		$('#ui-id-7 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-8').wrap('<div class="dropdown"></div>');
		$('#ui-id-8').addClass('dropdown-menu');
		$('#ui-id-8 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-9').wrap('<div class="dropdown"></div>');
		$('#ui-id-9').addClass('dropdown-menu');
		$('#ui-id-9 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-10').wrap('<div class="dropdown"></div>');
		$('#ui-id-10').addClass('dropdown-menu');
		$('#ui-id-10 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-11').wrap('<div class="dropdown"></div>');
		$('#ui-id-11').addClass('dropdown-menu');
		$('#ui-id-11 a .ui-corner-all').attr('role','menuitem');
		
		$('#ui-id-12').wrap('<div class="dropdown"></div>');
		$('#ui-id-12').addClass('dropdown-menu');
		$('#ui-id-12 a .ui-corner-all').attr('role','menuitem');
		
	});
	
	$('#authorSearch').keyup(function() {
		if(authorFlag != 1) {
			/* If the author data is selected from autocomplete, and author name is changed
				then remove all corresponding data from author fields */
			$('#authorId').val("");
			$('#authorName').val("");
    		$('#authorRegionalName').val("");
			$('#authorDiacriticName').val("");
			$('#authorLifeHistory').val("");
			$('#authorPeriod').val("");
			$('#periodEra').val("");
			$('.author-data').hide();
			authorFlag = 0;
		} else {
			authorFlag = 0;
		}
	});
	
	$('#scribeSearch').keyup(function() {
		if(scribeFlag != 1) {
			/* If the scribe data is selected from autocomplete, and scribe name is changed
				then remove all corresponding data from scribe fields */
			$('#scribeId').val("");
			$('#scribeName').val("");
    		$('#scribeRegionalName').val("");
			$('#scribeDiacriticName').val("");
			$('.scribe-data').hide();
			scribeFlag = 0;
		} else {
			scribeFlag = 0;
		}
	});
	
	$('#editorName').keyup(function() {
		if(editorFlag != 1) {
			/* If the editor data is selected from autocomplete, and editor name is changed
				then remove all corresponding data from editor fields */
			$('#editorId').val("");
			editorFlag = 0;
		} else {
			editorFlag = 0;
		}
	});
	
	$('#commentatorSearch').keyup(function() {
		if(commentatorFlag != 1) {
			/* If the commentator data is selected from autocomplete, and commentator name is changed
				then remove all corresponding data from commentator fields */
			$('#commentatorId').val("");
			$('#commentatorName').val("");
    		$('#commentatorRegionalName').val("");
			$('#commentatorDiacriticName').val("");
			$('.commentator-data').hide();
		} 
		commentatorFlag = 0;
	});
	$('#subCommentatorSearch').keyup(function() {
		if(subCommentatorFlag != 1) {
			/* If the commentator data is selected from autocomplete, and commentator name is changed
				then remove all corresponding data from commentator fields */
			$('#subCommentatorId').val("");
    		$('#subCommentatorName').val("");
    		$('#subCommentatorRegionalName').val("");
			$('#subCommentatorDiacriticName').val("");
			$('.subCommentator-data').hide();
		}
		subCommentatorFlag = 0;
	});
	$('#translatorSearch').keyup(function() {
		if(translatorFlag != 1) {
			/* If the translator data is selected from autocomplete, and translator name is changed
				then remove all corresponding data from translator fields */
			$('#translatorId').val("");
			$('#translatorName').val("");
    		$('#translatorRegionalName').val("");
			$('#translatorDiacriticName').val("");
			$('.translator-data').hide();
		}
		translatorFlag = 0;
	});
	
	$('#publisherName').keyup(function() {
		if(publisherFlag != 1) {
			/* If the publisher data is selected from autocomplete, and publisher name is changed
			then remove all corresponding data from publisher fields */
			$('#publisherId').val("");
			$('#publisherAddress').val("");
			publisherFlag = 0;
		} else {
			publisherFlag = 0;
		}
		
	});
	
	$('#organisationName').keyup(function() {
		if(organisationFlag != 1) {
			/* If the organisation data is selected from autocomplete, and organisation name is changed
				then remove all corresponding data from organisation fields */
			$('#organisationId').val("");
			$('#organisationAddress').val("");
			$('#organisationWebsite').val("");
			$('#organisationPhone').val("");
			$('#organisationEmail').val("");
			$('#organisationAcronym').val("");
			organisationFlag = 0;
		} else {
			organisationFlag = 0;
		}
	});
	
	$('#digitiserName').keyup(function() {
		if(digitiserFlag != 1) {
			/* If the Digitizer data is selected from autocomplete, and organisation name is changed
				then remove all corresponding data from organisation fields */
			$('#digitiserId').val("");
			
			digitiserFlag = 0;
		} else {
			digitiserFlag = 0;
		}
	});

	
	$('input[type=button]').click(function(e) {
		if($(e.target).attr('id') == "submit-author") {
			/* If the next button is clicked on author form */
			if(validateForm1()) { 
				/* After validation */
				$('#tab-form a[href="#frame"]').tab('show');	/* Show the next tab content*/
				$('#tab-form a[href="#frame"]').parent().attr("class",'active'); /* Activate the next tab header */
				$("#msg-container").addClass('hide'); /* Hide the error message container */
			}
		} else if($(e.target).attr('id') == "submit-manuscript") {
			/* If the next button is clicked on manuscript form */
			if(validateForm2()) {
				/* After validation */
				$('#tab-form a[href="#author"]').tab('show'); /* Show the next tab content*/
				$('#tab-form a[href="#author"]').parent().attr("class",'active'); /* Activate the next tab header */
				$("#msg-container").addClass('hide'); /* Hide the error message container */
			}
		} else if($(e.target).attr('id') == "submit-frame") {
			/* If the next button is clicked on manuscript form */
			if($('#documentType').val() == 2) {
				$('#tab-form a[href="#nmm"]').tab('show'); /* Show the next tab content*/
				$('#tab-form a[href="#nmm"]').parent().attr("class",'active'); /* Activate the next tab header */
			} else {
				$('#tab-form a[href="#publication"]').tab('show'); /* Show the next tab content*/
				$('#tab-form a[href="#publication"]').parent().attr("class",'active'); /* Activate the next tab header */
			}
			$("#msg-container").addClass('hide'); /* Hide the error message container */
		}	else if($(e.target).attr('id') == "submit-nmm") {
			/* If the next button is clicked on manuscript form */
			$('#tab-form a[href="#publication"]').tab('show'); /* Show the next tab content*/
			$('#tab-form a[href="#publication"]').parent().attr("class",'active'); /* Activate the next tab header */
			$("#msg-container").addClass('hide'); /* Hide the error message container */
		} else if($(e.target).attr('id') == "previous-author") {
			/* If the previous button is clicked on manuscript form */
			$('#tab-form a[href="#manuscript"]').tab('show'); /* Show the previous tab content*/
			$('#tab-form a[href="#manuscript"]').parent().attr("class",'active'); /* Activate the previous tab header */
		} else if($(e.target).attr('id') == "previous-frame") {
			/* If the previous button is clicked on manuscript form */
			$('#tab-form a[href="#author"]').tab('show'); /* Show the previous tab content*/
			$('#tab-form a[href="#author"]').parent().attr("class",'active'); /* Activate the previous tab header */
		} else if($(e.target).attr('id') == "previous-nmm") {
			/* If the previous button is clicked on publisher form */
			$('#tab-form a[href="#frame"]').tab('show');	/* Show the previous tab content*/
			$('#tab-form a[href="#frame"]').parent().attr("class",'active'); /* Activate the previous tab header */
		} else if($(e.target).attr('id') == "previous-publisher") {
			/* If the previous button is clicked on publisher form */
			if($('#documentType').val() == 2) {
				$('#tab-form a[href="#nmm"]').tab('show');	/* Show the previous tab content*/
				$('#tab-form a[href="#nmm"]').parent().attr("class",'active'); /* Activate the previous tab header */
			} else {
				$('#tab-form a[href="#frame"]').tab('show');	/* Show the previous tab content*/
				$('#tab-form a[href="#frame"]').parent().attr("class",'active'); /* Activate the previous tab header */
			}
		} else if((new RegExp("cancel")).test($(e.target).attr('id'))) {
			/* If the cancel button is clicked */
			window.location.replace("<%=request.getContextPath()%>/homePageAction.action?requestId="+requestId); /* Redirect to home page */
		} else if($(e.target).attr('id') == "add-author") {
			$('#authorSearch').val("");
			$('#authorId').val("");
			$('#authorName').val("");
			$('#authorRegionalName').val("");
			$('#authorDiacriticName').val("");
			$('#authorLifeHistory').val("");
			$('#authorPeriod').val("");
			$('#periodEra').val("");
			
			$('.author-data').fadeIn();
			$('#authorName').focus();
		} else if($(e.target).attr('id') == "add-scribe") {
			$('#scribeSearch').val("");
			$('#scribeId').val("");
			$('#scribeName').val("");
			$('#scribeRegionalName').val("");
			$('#scribeDiacriticName').val("");
			
			$('.scribe-data').fadeIn();
			$('#scribeName').focus();
		} else if($(e.target).attr('id') == "add-commentator") {
			$('#commentatorSearch').val("");
			$('#commentatorId').val("");
			$('#commentatorName').val("");
			$('#commentatorRegionalName').val("");
			$('#commentatorDiacriticName').val("");
			
			$('.commentator-data').fadeIn();
			$('#commentatorName').focus();
		}
		e.preventDefault();
	});
	
	$('#isPublished1Container').click(function() {
		$('.published').fadeIn();
		$(".published > :input, :select, :textarea").attr("disabled", false);
	});
	
	$('#isPublished2Container').click(function() {
		$('.published').fadeOut();
		$(".published :input, :select, :textarea").attr("disabled", true);
	});
	
	$('#isBound1Container').click(function(e) {
		$('#isBound').val($('#isBound1').val());
	});
	$('#isBound2Container').click(function(e) {
		$('#isBound').val($('#isBound2').val());
	});
	
	$('#natureOfCollection1Container').click(function(e) {
		$('#natureOfCollection').val($('#natureOfCollection1').val());
	});
	$('#natureOfCollection2Container').click(function(e) {
		$('#natureOfCollection').val($('#natureOfCollection2').val());
	});
	
	$('#isOrganisation1Container').click(function(e) {
		$('#organisationType').val($('#isOrganisation1').val());
	});
	$('#isOrganisation2Container').click(function(e) {
		$('#organisationType').val($('#isOrganisation2').val());
	});
	
	$('a[href=#published]').click(function() {
		$("#published :input, :select, :textarea").attr("disabled", false);
		$("#unpublished :input, :select, :textarea").attr("disabled", true);
	});
	
	$('a[href=#unpublished]').click(function() {
		$("#unpublished :input, :select, :textarea").attr("disabled", false);
		$("#published :input, :select, :textarea").attr("disabled", true);
	});
	
	$('input[type=submit]').click(function(e) {
		if($(e.target).attr('id') == "submit-publisher") {
			/* All three forms are validated to show error messages together */
			var result1 = validateForm1();
			var result2 = validateForm2();
			var result3 = validateForm3();
			if(result1 && result2 && result3) {
				$('#photo').attr("disabled", true); //Should not submit the file, it crashes the system. It is handled differently.
				$('#submit-publisher').submit();
				$('#submit-publisher').hide();
			} else {
				e.preventDefault();
			}
		}
	});
	/* Disable form submit on press of Enter/Return button */
	/* $('#manuscriptForm').bind("keyup keypress", function(e) {
		  var code = e.keyCode || e.which; 
		  if (code  == 13) {               
		    e.preventDefault();
		    return false;
		  }
		}); */
	
	function validateForm1() {
		/* Validation of the author information form */
		var fieldForNumericValidator = [authorPeriod];
		var isCorrectData = true;
		var fieldForAtleastOneValidator = [authorName, authorRegionalName, authorDiacriticName];
		
		var message = "";
		
		message = atLeastOneFieldValidator(fieldForAtleastOneValidator);
		if(message.length > 0) {
			for(var i = 0; i < fieldForAtleastOneValidator.length; i++) {
				if(atLeastOneFieldValidator([fieldForAtleastOneValidator[i]]).length > 0) {
					$(fieldForAtleastOneValidator[i]).parent().parent().addClass('has-error');
					var id = $(fieldForAtleastOneValidator[i]).parent().parent().parent().parent().parent().attr('id');
					$('a[href=#'+id+']').parent().addClass('error-tab'); 
					$('.author-data').show();
				}
			}
			isCorrectData = false;
		} else {
			for(var i = 0; i < fieldForAtleastOneValidator.length; i++) {
				$(fieldForAtleastOneValidator[i]).parent().parent().removeClass('has-error');
				var id = $(fieldForAtleastOneValidator[i]).parent().parent().parent().parent().parent().attr('id');
				$('a[href=#'+id+']').parent().removeClass('error-tab');
			}
			message = numericValidator(fieldForNumericValidator);
			if(message.length > 0) {
				for(var i = 0; i < fieldForNumericValidator.length; i++) {
					if(numericValidator([fieldForNumericValidator[i]]).length > 0) {
						$(fieldForNumericValidator[i]).parent().parent().addClass('has-error');
						var id = $(fieldForNumericValidator[i]).parent().parent().parent().attr('id');
						$('a[href=#'+id+']').parent().addClass('error-tab');
					}
				}
				isCorrectData = false;
			}
		}
		
		if(isCorrectData){
			return true;
		} else {
			$("#msg-container").text(message);
			$("#msg-container").removeClass('hide');
			return false;	
		}
	}
	
	function validateForm2() {
		/* Validation of the manuscript information form */
		var isCorrectData = true;
		var fieldForAtleastOneValidator = [manuscriptName, manuscriptDiacriticName, manuscriptRegionalName];
		var message = "";
		var id;
		message = atLeastOneFieldValidator(fieldForAtleastOneValidator);
		if(message.length > 0) {
			for(var i = 0; i < fieldForAtleastOneValidator.length; i++) {
				if(atLeastOneFieldValidator([fieldForAtleastOneValidator[i]]).length > 0) {
					$(fieldForAtleastOneValidator[i]).parent().parent().addClass('has-error');
					id = $(fieldForAtleastOneValidator[i]).parent().parent().parent().attr('id');
					$('a[href=#'+id+']').parent().addClass('error-tab');
				}
			}
			isCorrectData = false;
		} else {
			for(var i = 0; i < fieldForAtleastOneValidator.length; i++) {
				$(fieldForAtleastOneValidator[i]).parent().parent().removeClass('has-error');
				id = $(fieldForAtleastOneValidator[i]).parent().parent().parent().attr('id');
				$('a[href=#'+id+']').parent().removeClass('error-tab');
			}
			if(language.value == "-1"){
					message="Select particular Language";
					$(language).parent().addClass('has-error');
					$('#languageLabel').addClass('has-error');
					$('a[href=#'+id+']').parent().addClass('error-tab');
					isCorrectData = false;
				}else{
					$(language).parent().removeClass('has-error');
					$('#languageLabel').removeClass('has-error');
					$('a[href=#'+id+']').parent().removeClass('error-tab');
				}
		}
		
		if(isCorrectData){
			return true;
		} else {
			$("#msg-container").text(message);
			$("#msg-container").removeClass('hide');
			return false;	
		}
	}
	
	function validateForm3() {
		/* Validation of the publisher and publication information form */
		var fieldForNumericValidator = [publicationYear, price, organisationPhone];
		var emailValidation = [organisationEmail];
		var isCorrectData = true;
		
		var message = "";
		
		message = numericValidator(fieldForNumericValidator);
		if(message.length > 0) {
			for(var i = 0; i < fieldForNumericValidator.length; i++) {
				if(numericValidator([fieldForNumericValidator[i]]).length > 0) {
					$(fieldForNumericValidator[i]).parent().parent().addClass('has-error');
					var id = $(fieldForNumericValidator[i]).parent().parent().parent().parent().attr('id');
					$('a[href=#'+id+']').parent().addClass('error-tab');
				}
			}
			isCorrectData = false;
		} else {
			for(var i = 0; i < fieldForNumericValidator.length; i++) {
				$(fieldForNumericValidator[i]).parent().parent().removeClass('has-error');
				var id = $(fieldForNumericValidator[i]).parent().parent().parent().parent().attr('id');
				$('a[href=#'+id+']').parent().removeClass('error-tab');
			}
			
			message = emailValidator(emailValidation);
			if(message.length > 0) {
				for(var i = 0; i < emailValidation.length; i++) {
					if(emailValidator([emailValidation[i]]).length > 0) {
						$(emailValidation[i]).parent().parent().addClass('has-error');
						var id = $(emailValidation[i]).parent().parent().parent().parent().attr('id');
						$('a[href=#'+id+']').parent().addClass('error-tab');
					}
				}
				isCorrectData = false;
			} else {
				for(var i = 0; i < emailValidation.length; i++) {
					$(emailValidation[i]).parent().parent().removeClass('has-error');
					var id = $(emailValidation[i]).parent().parent().parent().parent().attr('id');
					$('a[href=#'+id+']').parent().removeClass('error-tab');
				}
				
			}
			/* if($('#organisationName').val() != "" && $('#organisationAcronym').val() == ""){
				message="Give an acronym for the organisation";
				$('#organisationAcronym').parent().parent().addClass('has-error');
				 $('a[href=#publication]').parent().addClass('error-tab');
				isCorrectData = false;
			}else{
				$('#organisationAcronym').parent().parent().removeClass('has-error');
				$('a[href=#publication]').parent().removeClass('error-tab');
			} */
		}
		
		if($('#isAvailable1Container').hasClass('active')) {
			$('#isAvailableValue').val($('#isAvailable1').val());
		} else if($('#isAvailable2Container').hasClass('active')) {
			$('#isAvailableValue').val($('#isAvailable2').val());
		} else if($('#isAvailable3Container').hasClass('active')) {
			$('#isAvailableValue').val($('#isAvailable3').val());
		}
		
		if(isCorrectData){
			return true;
		} else {
			$("#msg-container").text(message);
			$("#msg-container").removeClass('hide');
			return false;	
		}
	}
	
	/* function imageDisplay() {
		var form = $('#uploadImage');
		$.ajax({
		    type: "POST",
		    url: form.attr('action'),
		    data: form.serialize(),
		    cache: false,
		    success: function(data) {
		        console.log("Success");
		    },
		    error: function() {
		    	console.log("error");
		    }
		});
	}; */
	
	$(':file').change(function(){
		/*
		 * On change of file, this method is called
		 * it is to be used for validation
		 * Note: No validation has been implemented yet
		 */
		var fileData = "";
		var folio ;
		if($('#folios').val() != "")
		{
		folio=eval($('#folios').val());
		}else{
			folio=0;
		}
		/* for(var i = 0; i < this.files.length; i++) {
			var file = this.files[i];
		    var name = file.name;
		    var size = file.size;
		    var type = file.type;
		    
		    console.log(name + " " + size + " " + type);
		} */
		
		/* Put the selected image data into a div for display */
		if(this.files.length > 0) {
			if(this.files.length > 1) {
				fileData = this.files.length.toString() + " images";
				folio=folio+Math.round(this.files.length/2);
			} else {
				fileData = this.files[0].name.toString();
				folio=folio+1;
			}
			$('#folios').val(folio);
			$('.file-info').text(fileData);
			$('.file-info').fadeIn();	
		}
	});
	
	
	$('#uploadImageButton').click(function(){
		/*
		 * Sends the data to the server for upload
		 * Gets the path to these images as well as NMM data
		 * Put them into appropriate fields
		 */
		var formData = new FormData($('form')[0]);
		$('.loading-container').fadeIn();
		$.ajax({
	        url: "/OMDS/uploadImagesToTemp.action",  //Server script to process data
	        type: 'POST',
	        xhr: function() {  // Custom XMLHttpRequest
	            var myXhr = $.ajaxSettings.xhr();
	        	/* If accurate progress is to be shown, it will be read through the myXhr variable */
	            return myXhr;
	        },
	        //Ajax events
	        success: function(data) {
	        	$('#imageHeight').val(data.height);
	        	$('#imageWidth').val(data.width);
	        	$('#imageCreatedDate').val(data.createdDate);
	        	$('#imageDigitisedDate').val(data.modifiedDate);
	        	$('#imageCameraMake').val(data.lensMake);
	        	$('#imageCameraModel').val(data.lensModel);
	        	$('#imageXResolution').val(data.xResolution);
	        	$('#imageYResolution').val(data.yResolution);
	        	
	        	//Scale images and put them in the body
	        	if(data.images != null && Object.keys(data.images).length > 0) {
	        		$('#filePathContainer').val("[" + JSON.stringify(data.images) + "]");
	        	}
	        	if(data.status == "success") {
	        		/* $("#msg-container").addClass('hide');
	        		$( "#msg-success-container" ).show();
	        		$("#msg-success-container").text("Successfully uploaded images");
					$("#msg-success-container").removeClass('hide');
					$( "#msg-success-container" ).fadeOut(3600);
					 */
					alert("Successfully uploaded images");
		        	
	        	} else if(data.status == "failure") {
	        		/* $("#msg-success-container").addClass('hide');
	        		$( "#msg-container" ).show();
	        		$("#msg-container").text("Unable to upload images");
					$("#msg-container").removeClass('hide');
					$( "#msg-container" ).fadeOut(3600 ); */
					alert("Unable to upload images");
	        	}
	        	
	        	$('.loading-container').hide();
	        	$('.loading-container').fadeOut();
	        	if(!$('.image-details').hasClass('manuscript-specific')) {
	        		$('.image-details').fadeIn();
	        	}
	        	
	        },
	        error: function(data) {
	        	$('.loading-container').fadeOut();
	        },
	        // Form data
	        data: formData,
	        //Options to tell jQuery not to process data or worry about content-type.
	        cache: false,
	        contentType: false,
	        processData: false
	    });
	});
	
	/* $(document).on("click","img[class='image-link-container']", function (e) {
		if(!($('#selectframe').is(':checked'))){
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var id = $(this).attr('id').split('_')[1];
		for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
			if(fileDiskPathObject[i].id == id) {
				$("#currentImg").text(i+1);
				ajaxCallForImage(fileDiskPathObject[i].filePathReal);
			};
		} ;
		}
	}); */
	//this is to find out the next image
	$('#nextImg').click(function(){
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var imgno=parseInt($("#currentImg").text());
		 if(imgno < parseInt($("#totalImg").text()))
			{ 
			$("#currentImg").text(imgno+1);
			ajaxCallForImage(fileDiskPathObject[imgno].filePathReal);
			 }else{
				alert("This Is The Last Image");
			} 
	});
	
	//this is to find out the previous image
	$('#prevImg').click(function(){
		var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
		var imgno=parseInt($("#currentImg").text());
		 if(imgno>1){ 
			  $("#currentImg").text(imgno-1);
			ajaxCallForImage(fileDiskPathObject[imgno-2].filePathReal);
	 	}else{
		alert("This Is The First Image");
		} 
	});
	
	$(document).on("click",".image-max-close", function (e) {
		$('.image-max-container').hide();
		$('#manuscriptForm').show();
	    e.preventDefault();
	});
	
	/* $(document).on("click",".thumbnail-close", function (e) {
		$(e.target).parent().fadeOut();
		
	    e.preventDefault();
	}); */
	
	$('input[type=button]').click(function(e) {
		if($(e.target).attr('id') == "tagbutton") {
			addTag();
		}
		e.preventDefault();
	});
function addTag() {
	var count=$('#tagDisplay').children().size();
	var preTag= true;
	if($('#tag').val()!=""){
	if(count>0){
		preTag=checkForTag(count);
		//preTag = $('#'+(count-1)).val();
	 }if(!preTag){
		 alert("This tag is already exist.Please add a new tag");
		 $('#tag').val("");
		 $('#tempTagId').val("");
	 }else{
		var htmlString = "";
		htmlString += "<div class='tag-data-shell'>";
		htmlString +='<s:hidden id="'+ count +'" name="digitalManuscriptVO.tagList['+(count)+'].id" value = "' + $('#tempTagId').val() + '" ></s:hidden>';
     	htmlString +='<s:hidden id="field_' + count + '" name="digitalManuscriptVO.tagList['+(count)+'].name" value = "' + $('#tag').val() + '" ></s:hidden>';
		htmlString += "<a href='#' class='thumbnail-close'>×</a>";
		htmlString += $('#tag').val();
		htmlString += "</div>";
		$('#tagDisplay').append(htmlString);
		count += 1;
		
		$('#tag').val("");
		$('#tempTagId').val(""); 
	 }
	}else{
		alert("Please give a tag name");
	}
	} 
function checkForTag(tagCount){
	var preTag= 0;
	var status=true;
	if($('#tempTagId').val()!=""){
	for(var i=0; i<tagCount;i++){
		preTag = $('#'+i).val();
		if(preTag == $('#tempTagId').val()){
			status=false;
			break;
		}
	}
	}
	return status;
}
$(document).on("click",".thumbnail-close", function(e) {
	$(e.target).parent().remove();
	//count -= 1;
	e.preventDefault();
});
	
function ajaxCallForImage(imgPath) {
	<%
	String requestd = (String) request.getAttribute("requestId");
%>
var audioPath = "";
$('.image-max-container img').attr('src', "<%=request.getContextPath()%>/assets/images/loading.gif");
$('#manuscriptForm').hide();
$('.image-max-container').show();
	$.ajax({
	    type: "GET",
	    url: 'findRealImage.action?requestId='+<%=requestd%>+ '&filePath='+imgPath+"&audioPath="+audioPath,
	    dataType: 'json',
	    cache: false,
	    success: function(data) {
      $('.image-max-container img').attr('src', '/OMDS'+ '/temp/' + data.realPath);
	  /*   $('#manuscriptForm').hide();
	    $('.image-max-container').show(); */
	    /* e.preventDefault(); */
	 },
	    error: function(data) {
	    	alert('Sorry No NMM Details Found');
	    }
	});
}


$('[data-toggle="tooltip"]').tooltip({
    'placement': 'top'
});
$('[data-toggle="popover"]').popover({
    trigger: 'hover',
        'placement': 'top'
});

$(document).on("click", "div.img-thumbnail", function (event) {
/* $("div.img-thumbnail").on("click",function(event) { */
    // var target = $(event.target);
    //if (target.is('input:checkbox')) return;
if($('#selectframe').is(':checked')){
    var checkbox = $(this).find("input[type='checkbox']");
    if( !checkbox.prop("checked") ){
        checkbox.prop("checked",true);
        $(this).css('background-color','red');
    } else {
        checkbox.prop("checked",false);
        $(this).css('background-color','white');
    }
}else{
	var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
	var id = $(this).attr('id').split('_')[1];
	for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
		if(fileDiskPathObject[i].id == id) {
			$("#currentImg").text(i+1);
			ajaxCallForImage(fileDiskPathObject[i].filePathReal);
		};
	} ;
}
}); 
function selectAll(){
	var listofParameters = $('.panel-body input:checkbox');
	if($('#selectall').is(':checked')){
	$('.panel-body').css('background-color','#C0C0C0');
   // $('input[type="checkbox"]').css('display',''); 
	$('#delimg').css('display','');
	$('#selectframe').prop("checked",true);
	$('#selectbtn').attr('value','Unselect');
	for (var i=0;i<listofParameters.length;i++) {
		$(listofParameters[i]).prop("checked",true);
	}
	$('.img-thumbnail').css('background-color','red');
	}else{
		for (var i=0;i<listofParameters.length;i++) {
			$(listofParameters[i]).prop("checked",false);
			//$(listofParameters[i]).css('display','none'); 
		}
		$('.img-thumbnail').css('background-color','white');
		$('.panel-body').css('background-color','white');
		$('#delimg').css('display','none');
		$('#selectframe').prop("checked",false);
		$('#selectbtn').attr('value','Select');
	}
}
function selectedIteam(){
	if($('#selectframe').is(':checked')){
	var listofParameters = $('.panel-body input:checkbox');
	for (var i=0;i<listofParameters.length;i++) {
		$(listofParameters[i]).prop("checked",false);
		//$(listofParameters[i]).css('display','none'); 
	}
	$('.panel-body').css('background-color','white');
	$('#delimg').css('display','none');
	$('#selectframe').prop("checked",false);
	$('.img-thumbnail').css('background-color','white');
	$('#selectbtn').attr('value','Select');
	$('#selectall').prop("checked",false);
	}else{
		$('.panel-body').css('background-color','#C0C0C0');
		$('#delimg').css('display','');
		$('#selectframe').prop("checked",true);
		$('#selectbtn').attr('value','Unselect');
	}
	/*  $(".img-thumbnail").onclick = function() {
	    if (timer) clearTimeout(timer);
	    timer = setTimeout(function() { alert('Single'); }, 250);        
	};  */
}
function deleteImage(){
	var listofParameters = $('.panel-body input:checkbox');
	var frameIds =new Array();
	var count=0;
   var r = confirm("You are going to delete the frames,you will loose all information regarding these frames permanently");
	if(r == true)
	{
	for (var i=0;i<listofParameters.length;i++) {
		if($(listofParameters[i]).is(':checked')){
			//alert((listofParameters[index]).id);
		  frameIds[count] =(listofParameters[i]).id ;
		  count++;
		}
	}
	if(count>0){
	deleteFrame(frameIds);
	}else{
		alert("No Image Is Selected");
	}
	}	
}
function deleteFrame(frameIds){
	var urlData = frameIds.toString();
	$.ajax({
	    type: "POST",
	    url: 'deleteFrame.action?requestId='+<%=requestd%>,
	   	dataType: 'json',
	    data: { frameIds: urlData},
	   	cache: false,
	    success: function(data) {
	    	$('#selectframe').prop("checked",false);
	    	$('#selectall').prop("checked",false);
	    	$('#selectbtn').attr('value','Select');
	    	var fileDiskPathObject1 = JSON.parse($('#fileDiskPathContainer').val());
	    	for(var i = 0; i < Object.keys(fileDiskPathObject1).length; i++){
	    		for(var j= 0;j<frameIds.length;j++){
	    			if(fileDiskPathObject1[i].id==frameIds[j]){
	    				fileDiskPathObject1[i].id="";
	    				fileDiskPathObject1[i].text="";
	    				fileDiskPathObject1[i].filePath="";
	    				fileDiskPathObject1[i].isLast="";
	    				fileDiskPathObject1[i].filePathReal="";
	    				fileDiskPathObject1[i].documentId="";
	    			}
	    		}
	    	}
	    	var jsondata = JSON.stringify(fileDiskPathObject1);
	    	$('#fileDiskPathContainer').val(jsondata);
	    	$('.panel-body').empty();
	    	if($('#fileDiskPathContainer').val().length > 0) {
				var countImage=0;
				var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
				var htmlString = "";
				var filePath;
				for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
					if(fileDiskPathObject[i].id != ""){
					 filePath = fileDiskPathObject[i].filePath.replace(/\\/g, "/");
				    htmlString += "<div id='div_" + fileDiskPathObject[i].id + "' class='img-thumbnail'>"; 
				    htmlString += "<input type='checkbox' id='"+fileDiskPathObject[i].id+"' name='check' style='display:none;' />";
					/* htmlString += "<a id='"+fileDiskPathObject[i].id+"' href='#' class='deleteimg-thumbnail'>×</a>"; */
					htmlString += "<img src='" + '/OMDS'+ '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";
					htmlString += "</div>";
					countImage++;
					}
				}
				$("#thumbnaildisplay").css('display','');
				$("#totalImg").text(countImage);
				$('.panel-body').append(htmlString);
				$('.panel-body').css('background-color','white');
				$('#delimg').css('display','none');
				var folio = Math.round(countImage/2);
				$('#folios').val(folio);
			}else{
				$("#thumbnaildisplay").css('display','none');
			}
	    	alert(data.message);
	    	//location.reload(); 
	    },
	    error: function(data) {
	    	//location.reload(); 
	    		if($('#fileDiskPathContainer').val().length > 0) {
				var countImage=0;
				var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
				var htmlString = "";
				var filePath;
				var str="";
				for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
					if(fileDiskPathObject[i].id != ""){
					 filePath = fileDiskPathObject[i].filePath.replace(/\\/g, "/");
				    htmlString += "<div id='div_" + fileDiskPathObject[i].id + "' class='img-thumbnail'>"; 
				    htmlString += "<input type='checkbox' id='"+fileDiskPathObject[i].id+"' name='check' style='display:none;' />";
					/* htmlString += "<a id='"+fileDiskPathObject[i].id+"' href='#' class='deleteimg-thumbnail'>×</a>"; */
					htmlString += "<img src='" + '/OMDS'+ '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";
					htmlString += "</div>";
					countImage++;
					}
				}
				var folio = Math.round(countImage/2);
				$('#folios').val(folio);
				$("#thumbnaildisplay").css('display','');
				$("#totalImg").text(countImage);
				$('.panel-body').append(htmlString);
			}else{
				$("#thumbnaildisplay").css('display','none');
			}
	    		alert(data.message);
	    }
	});
}
</script>
</html>