<html>
<link
	href="${pageContext.servletContext.contextPath}/assets/css/jquery.ime.css"
	rel="stylesheet" />
<link
	href="${pageContext.servletContext.contextPath}/assets/css/addmanuscript.css"
	rel="stylesheet" />
<%@ include file='../layout/header.jsp'%>
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
<div class="container container-center"
	style="max-width: 70%; margin-left: 190px;">
	<div class="alert alert-success appTable centerdiv">
		<div class="form">
			<div class="container" style="margin-top: -50px">
				<div class="image-max-container" style="width: 100%; height: 100%;">
					<div class="image-max-close" style="width: 100%;">Close</div>
					<span id="currentImg" style="color: #000"></span> <span
						id="totalImg" style="color: #000"></span> <img src=""
						align="middle" style="max-width: 90%; height: 90%;">
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
					<%@ include file='../messagecontainer.jsp'%>
				</div>
				<s:form action="addUpdateManuscript" role="form" id="manuscriptForm"
					name="manuscriptFormName">
					<s:hidden name="digitalManuscriptVO.id" />
					<s:hidden name="digitalManuscriptVO.manuscriptType" />
					<s:hidden name="digitalManuscriptVO.parentFKId" />
					<s:hidden name="digitalManuscriptVO.recordStatus" />
					<s:hidden name="digitalManuscriptVO.isSavingMerged"
						id="savingMerged" />
					<s:hidden name="digitalManuscriptVO.parentIdsStr" />

					<s:hidden id="authorId" name="digitalManuscriptVO.authorVO.id" />
					<s:hidden id="scribeId" name="digitalManuscriptVO.scribeVO.id" />
					<s:hidden id="commentatorId"
						name="digitalManuscriptVO.commentatorVO.id" />
					<s:hidden id="subCommentatorId"
						name="digitalManuscriptVO.subCommentatorVO.id" />
					<s:hidden id="translatorId"
						name="digitalManuscriptVO.translatorVO.id" />
					<s:hidden id="organisationId"
						name="digitalManuscriptVO.organisationVO.id" />
					<s:hidden id="organisationType"
						name="digitalManuscriptVO.organisationVO.type" />
					<s:hidden id="publisherId" name="publicationVO.publisherVO.id" />
					<s:hidden id="editorId" name="publicationVO.editorVO.id" />
					<s:hidden id="publicationId"
						name="digitalManuscriptVO.publicationVO.id" />
					<s:hidden id="isAvailableValue" name="publicationVO.isAvailable" />
					<s:hidden id="isPrintedValue"
						name="digitalManuscriptVO.articleDetailsVO.isPrinted" />
					<s:hidden id="documentType" name="digitalManuscriptVO.documentType" />
					<s:hidden id="nmmDetailsId"
						name="digitalManuscriptVO.nmmDetailsVO.id" />
					<s:hidden id="filePathContainer"
						name="digitalManuscriptVO.filePathContainer" />
					<s:hidden id="fileDiskPathContainer"
						name="digitalManuscriptVO.fileDiskPathContainer" />
					<s:hidden id="natureOfCollection"
						name="digitalManuscriptVO.natureOfCollection" />
					<s:hidden id="isBound" name="digitalManuscriptVO.isBound"></s:hidden>
					<s:hidden id="tempTagId"></s:hidden>

					<s:hidden id="articleLanguage"
						name="digitalManuscriptVO.articleLaguage" />

					<div class="form-group row buttons container-center">
						<div class="col-md-3">
							<a href="#" id="bookContainer"
								class="main-tab btn btn-lg btn-primary btn-block">Book</a>
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-3">
							<a href="#" id="articleContainer"
								class="main-tab btn btn-lg btn-primary btn-block">Article</a>
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-3">
							<a href="#" id="manuscriptContainer"
								class="main-tab btn btn-lg btn-primary btn-block">Manuscript</a>
						</div>

					</div>

					<div class="form-container" id="tab-form">
						<h2 class="form-heading bookContainer" style="max-width: 60%;">Book
							Digitization</h2>
						<h2 class="form-heading manuscriptContainer"
							style="max-width: 60%;">Manuscript Digitization</h2>
						<h2 class="form-heading articleContainer" style="max-width: 60%;">Article
							Digitization</h2>
						<ul class="nav nav-tabs" style="max-width: 100%; padding: 0px;">
							<li class="active"><a href="#manuscript" data-toggle="tab">Basic
									Info.</a></li>
							<li class="manuscript-specific"><a href="#author"
								data-toggle="tab">Author Info./Scribe Info.</a></li>
							<li class="bookContainer"><a href="#author"
								data-toggle="tab">Author Info.</a></li>
							<li class="articleContainer"><a href="#author"
								data-toggle="tab">Author Info.</a></li>
							<li><a href="#frame" data-toggle="tab">Images</a></li>
							<li class="manuscript-specific"><a href="#nmm"
								data-toggle="tab">NMM</a></li>
							<li><a href="#publication" data-toggle="tab">Additional
									Info.</a></li>
						</ul>

						<div class="tab-content">

							<div class="tab-pane fade active in container-center"
								id="manuscript" style="max-width: 100%;">
								<br>
								<div class="form-group row" style="margin-bottom: 2px;">
									<label class="col-md-2 control-label manuscript-specific">Manuscript
										ID </label> <label class="col-md-2 control-label bookContainer">Book
										ID </label> <label class="col-md-2 control-label articleContainer">Article
										ID </label>
									<div class="col-md-4">
										${digitalManuscriptVO.manuscriptId}</div>
									<label class="col-md-2 control-label manuscript-specific">Manuscript
										Name </label> <label class="col-md-2 control-label bookContainer">Book
										Name</label> <label class="col-md-2 control-label articleContainer">Article
										Name</label>
									<div class="col-md-4">${digitalManuscriptVO.name}</div>
								</div>

								<div class="form-group row manuscript-specific"
									style="margin-bottom: 2px;">
									<label class="col-md-2 control-label">Accession Number</label>
									<div class="col-md-4">${digitalManuscriptVO.accNumber}</div>
									<label class="col-md-2 control-label">Bundle (Optional)</label>
									<div class="col-md-4">
										<s:iterator value="bundleMasterVOs" status="languageVO">
											<s:if test="id==digitalManuscriptVO.bundleMasterFkId">
												<s:property value="name" />
											</s:if>
										</s:iterator>
									</div>
								</div>
								<div class="form-group row" id="otherLanguageNameField"
									style="margin-bottom: 2px;">
									<div class="col-md-2" style="padding-right: 0px;">
										<label class="control-label">Name(in Diacritical) </label>
									</div>

									<div class="col-md-4">
										${digitalManuscriptVO.diacriticName}</div>
									<div class="col-md-2" style="padding-right: 0px;">
										<label class="control-label">Name(in Vernacular) </label>
									</div>
									<div class="col-md-4">
										${digitalManuscriptVO.regionalName}</div>
								</div>

								<fieldset>
									<legend style="color: #404040;">Work Details</legend>
									<div class="form-group row" style="margin-bottom: 6px;">
										<label class="col-md-2 control-label" id="languageLabel">Language*</label>
										<div class="col-md-4">
											<s:iterator value="languageVOs" status="languageVO">
												<s:if test="id==digitalManuscriptVO.languageFkId">
													<s:property value="name" />
												</s:if>
											</s:iterator>
										</div>
										<label class="col-md-2 control-label">Script</label>
										<div class="col-md-4">
											<s:iterator value="scriptVOs" status="scriptVO">
												<s:if test="id==digitalManuscriptVO.scriptFkId">
													<s:property value="name" />
												</s:if>
											</s:iterator>
										</div>
									</div>
									<div class="form-group row" style="margin-bottom: 6px;">
										<label class="col-md-2 control-label">Subject</label>
										<div class="col-md-4">
											<s:iterator value="categoryVOs" status="categoryvo">
												<s:if test="id==digitalManuscriptVO.categoryFkId">
													<s:property value="name" />
												</s:if>
											</s:iterator>
										</div>
										<label class="col-md-2 control-label">Type</label>
										<div class="col-md-4">${digitalManuscriptVO.typeOfWork}
										</div>
									</div>
									<div class="form-group row" id="originalCommRef">
										<label class="col-md-4 control-label">Original
											Commentary</label>
										<div class="col-md-8">
											<s:text id="parentCommFKId"
												name="digitalManuscriptVO.parentName" />
											<s:hidden id="originalCommId"
												name="digitalManuscriptVO.parentFKId" />
										</div>
									</div>

									<div class="form-group row">
										<label class="col-md-2 control-label">Specific
											Category</label>
										<div class="col-md-4 man-book-specific">
											<s:iterator value="digitalManuscriptVO.specificCategoryId"
												var="storedSpecificCategoryVO"
												status="specificCategoryVOIndex">
												<s:if test="#storedSpecificCategoryVO==1">
													Orginal Work
												</s:if>
											</s:iterator>
											<s:iterator value="specificCategoryVOs"
												status="specificCategoryVO" var="specificCategoryVO">
												<s:iterator value="digitalManuscriptVO.specificCategoryId"
													var="storedSpecificCategoryVOID"
													status="specificCategoryVOIndex">
													<s:if
														test="#specificCategoryVO.id==#storedSpecificCategoryVOID">
														<br />
														<s:property value="#specificCategoryVO.name" />
													</s:if>
												</s:iterator>
											</s:iterator>
										</div>

										<%--<div class="col-md-4 articleContainer" >
											<s:select list="articleSpecificCategoryVOs" required="true" id="specificCategoryId"
													  name="digitalManuscriptVO.specificCategoryId" listKey="id" listValue="name"  multiple="true"/>

											&lt;%&ndash;<s:iterator value="digitalManuscriptVO.specificCategoryId" var="storedSpecificCategoryVO" status="specificCategoryVOIndex">
												<s:if test="#storedSpecificCategoryVO==1">
													Orginal Work
												</s:if>
											</s:iterator>&ndash;%&gt;
											<s:iterator value="specificCategoryVOs" status="specificCategoryVO" var="specificCategoryVO">
												<s:iterator value="digitalManuscriptVO.specificCategoryId" var="storedSpecificCategoryVOID" status="specificCategoryVOIndex">
													<s:if test="#specificCategoryVO.id==#storedSpecificCategoryVOID">
														<br/><s:property value="#specificCategoryVO.name"/>
													</s:if>
												</s:iterator>
											</s:iterator>
										</div>--%>
										<div class="manuscript-specific">
											<label class="col-xs-2 control-label">Material</label>
											<div class="col-xs-4">
												<s:iterator value="materialVOs" status="materialVOs">
													<s:if test="id==digitalManuscriptVO.materialFkId">
														<s:property value="name" />
													</s:if>
												</s:iterator>
											</div>
										</div>
									</div>
									<div class="form-group row" id="originalWorkRef">
										<label class="col-md-4 control-label">Original Work</label>
										<div class="col-md-8">
											<s:text id="parentFKId" name="digitalManuscriptVO.parentName" />
											<s:hidden id="originalWorkId"
												name="digitalManuscriptVO.parentFKId" />
										</div>
									</div>
									<br>

									<div class="author-search alert alert-success"
										style="background-color: #EEE; border-color: #404040; margin-top: 2%;">
										<%--<div class="form-group row">
											<label class="col-md-4 control-label">Tag Name</label>
											<div class="col-md-6" style="padding-left: 0px;">
												<s:text  id="tag" />
											</div>
											<div class="col-md-2" style="padding-left: 0px;">
												<input type="button" value="Add" id="tagbutton" class="btn btn-lg btn-success btn-block" style="height: 35px; padding-top: 5px;">
											</div>
										</div>--%>
										<div class="form-group row" style="min-height: 100px;">
											<label class="col-md-4 control-label">Tags</label>
											<div class="col-md-8" class="tag-data" id="tagDisplay"
												style="">

												<s:iterator value="digitalManuscriptVO.tagList" status="var">

													<div class='tag-data-shell'>
														<s:hidden id="%{#var.index}"
															name="digitalManuscriptVO.tagList[%{#var.index}].id"></s:hidden>
														<s:text
															name="digitalManuscriptVO.tagList[%{#var.index}].name"></s:text>
													</div>
												</s:iterator>
											</div>
										</div>
									</div>

									<div class="form-group row" style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">Summary</label>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<s:text name="digitalManuscriptVO.summary" />
										</div>
									</div>
									<div class="form-group row" style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">Table Of
											Contents</label>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<s:text name="digitalManuscriptVO.tableOfContents" />
										</div>
									</div>

								</fieldset>
								<fieldset>
									<legend style="color: #404040;">Specific Contribution</legend>
									<div class="form-group row manuscriptContainer"
										style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">To Subject</label>
									</div>
									<div class="form-group row bookContainer"
										style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">To Subject</label>
									</div>
									<div class="form-group row articleContainer"
										style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">To
											Philosophy/respective field of thought </label>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<s:text name="digitalManuscriptVO.contributionToAyurveda" />
										</div>
									</div>
									<div class="form-group row articleContainer"
										style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">Names of fields
											covered</label>
									</div>
									<div class="form-group row articleContainer">
										<div class="col-md-12">
											<s:text name="digitalManuscriptVO.fieldsCovered" />
										</div>
									</div>
									<div class="form-group row" style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">Uniqueness of
											Work</label>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<s:text name="digitalManuscriptVO.uniquenessOfWork" />
										</div>
									</div>
									<div class="form-group row" style="margin-bottom: 0px;">
										<label class="col-md-4 control-label">Other Details</label>
									</div>
									<div class="form-group row">
										<div class="col-md-12">
											<s:text name="digitalManuscriptVO.anyOtherDetails" />
										</div>
									</div>
								</fieldset>

								<fieldset class="articleContainer">
									<legend style="color: #404040;">Other Details</legend>
									<div class="form-group row is-published-oth articleContainer">
										<label class="col-md-4 control-label">Type</label>
										<div class="col-md-8 btn-group articleContainer"
											data-toggle="buttons">
											<label class="col-xs-4 btn btn-primary"
												id="isOtherPublished1Container"> <input type="radio"
												id="isJournal" value="1">Online Journal/Publication
											</label> <label class="col-xs-4 btn btn-primary"
												id="isOtherPublished2Container"> <input type="radio"
												id="isMagazineOthers" value="0">Journal/Magazine/Others
											</label>
											<s:text name="digitalManuscriptVO.articleDetailsVO.type"
												id="articleDetailsType"></s:text>
											<%--											<s:text name="digitalManuscriptVO.articleDetailsVO.id"></s:text>--%>
										</div>
									</div>

									<div class="ifMagazine">
										<div class="form-group row">
											<label class="col-md-2 control-label">Name of the
												Journal / Magazine / Any other type:</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.nameOfMagazine" />
											</div>
											<label class="col-md-2 control-label">ISSN No</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.magazineIssnNo"
													id="issnNo" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-2 control-label">Name of the
												editor</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.nameOfEditor"
													id="artnameOfEditor" />
											</div>
											<label class="col-md-2 control-label">Year of
												Publication</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.yearOfPublication"
													id="yearOfPublication" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-2 control-label">Abstract of the
												Article</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.abstractOfMagazine"
													id="abstractOfArticle" />
											</div>
											<label class="col-md-2 control-label">Address</label>
											<div class="col-md-4">
												<s:text name="digitalManuscriptVO.articleDetailsVO.address"
													id="magazineAddress" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-2 control-label">Number of Pages</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.noOfPages"
													id="noOfPages" />
											</div>
											<label class="col-md-2 control-label">Price (INR)</label>
											<div class="col-md-4">
												<s:text name="digitalManuscriptVO.articleDetailsVO.price"
													id="magazineprice" />
											</div>
										</div>

										<!-- <div class="form-group row">
                                            <label class="col-md-4 control-label">Is Printed</label>
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
                                        </div> -->
									</div>

									<div class="Otherpublished">
										<div class="form-group row">
											<label class="col-md-2 control-label">Website where
												it is published</label>
											<div class="col-md-4">
												<s:text name="digitalManuscriptVO.articleDetailsVO.website"
													id="websiteName" />
											</div>
											<label class="col-md-2 control-label">ISSN No</label>
											<div class="col-md-4">
												<s:text name="digitalManuscriptVO.articleDetailsVO.issnNo"
													id="publicationIssnNo" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-2 control-label">Other details
												about the Journal</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.journalOthrDtls"
													id="journalOthDtls" />
											</div>
											<label class="col-md-2 control-label">Abstract of the
												Article</label>
											<div class="col-md-4">
												<s:text
													name="digitalManuscriptVO.articleDetailsVO.abstractOfArticle"
													id="AbstractOfArticle" />
											</div>
										</div>

									</div>
									<div class="form-group row" id="isPrinted">
										<label class="col-md-4 control-label">Is Printed</label>
										<div class="col-md-8 btn-group" data-toggle="buttons">
											<label class="col-xs-4 btn btn-primary"
												id="isPrinted1Container"> <input type="radio"
												id="isPrinted1" value="1">Yes
											</label> <label class="col-xs-4 btn btn-primary"
												id="isPrinted2Container"> <input type="radio"
												id="isPrinted2" value="0">No
											</label> <label class="col-xs-4 btn btn-primary"
												id="isPrinted3Container"> <input type="radio"
												id="isPrinted3" value="2">Unknown
											</label>
										</div>
									</div>
								</fieldset>

								<div class="form-group row buttons">
									<div class="col-md-4">
										<!-- <input type="button" class="btn btn-lg btn-primary btn-block" value="previous" id="previous-manuscript"> -->
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-danger btn-block"
											value="Cancel" id="cancel-manuscript">
									</div>
									<%--<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block" value="Next" id="submit-manuscript">
									</div>--%>
								</div>
							</div>
							<div class="tab-pane fade container-center" id="author"
								style="max-width: 100%;">
								<br> <br>
								<fieldset>
									<legend style="color: #404040;">Author</legend>
									<!-- <input type="button" onclick="getAuthorField();" value="Add More"></input> -->
									<%--<div class="author-search alert alert-success" style="background-color: #EEE; border-color: #404040;">
										<div class="form-group row">
											<div class="col-md-4">
												<label class="control-label">Search By Name</label>
												<!-- <span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
                                                      data-toggle="popover" data-content="Type the first letters of the Author's name in the auto complete field and the
                                                      related records will be fetched if present and then select the author's name you want to refer.Otherwise click on
                                                      ADD NEW RECORD and add a new author."></span> -->
											</div>
											<div class="col-md-6">
												<s:text  id="authorSearch"/>
											</div>
											<div class="col-md-2">
												<input type="button" class="btn btn-lg btn-success btn-block" value="New" id="add-author">
											</div>
										</div>
									</div>--%>

									<div id="authorBox">
										<%
											int i=-1;%>
										<s:iterator value="digitalManuscriptVO.authors" status="var">
											<%
												i++;%>
											<div>
												<%--<div class="authorheaderspan headerAuth<%=i%>"><span>Author[+]</span></div>--%>
												<div class='containerAuth '
													style='border: thin solid #404040;' id="authorDivId<%=i%>">
													<br>
													<div class='form-group row'>
														<label class='col-md-4 control-label'>Name</label>
														<div class='col-md-8'>
															<s:text
																name='digitalManuscriptVO.authors[%{#var.index}].name' />
														</div>
													</div>

													<div class='form-group row'>
														<label class='col-md-4 control-label'>Name (in
															Diacritical)</label>
														<div class='col-md-8'>
															<s:text
																name='digitalManuscriptVO.authors[%{#var.index}].diacriticName' />
														</div>
													</div>

													<div class='form-group row'>
														<label class='col-md-4 control-label'>Name (in
															Vernacular)</label>
														<div class='col-md-8'>
															<s:text
																name='digitalManuscriptVO.authors[%{#var.index}].regionalName' />
														</div>
													</div>

													<div class='form-group row'>
														<label class='col-md-4 control-label'>Period of
															the Author</label>
														<div class='col-md-6'>
															<s:text
																name='digitalManuscriptVO.authors[%{#var.index}].period' />
														</div>
														<div class='col-md-2'>
															<s:select list="#{'AD':'AD', 'BC':'BC'}"
																name='digitalManuscriptVO.authors[%{#var.index}].periodEra'
																value='digitalManuscriptVO.authors[%{#var.index}].periodEra'
																cssClass='form-control' id='periodEra%{#var.index}' />
														</div>
													</div>
													<div class='form-group row'>
														<label class='col-md-4 control-label'>Author's
															Biography</label>
														<div class='col-md-8'>
															<s:text
																name='digitalManuscriptVO.authors[%{#var.index}].lifeHistory' />
														</div>
													</div>
												</div>
												<%--<script type="text/javascript">
                                                    var authorDivClass = '.headerAuth<%=i%>';

                                                    $(authorDivClass).click(function () {

                                                        $header = $(this);
                                                        //getting the next element
                                                        $content = $header.next();
                                                        //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
                                                        $content.slideToggle(500, function () {
                                                            //execute this after slideToggle is done
                                                            //change text of header based on visibility of content div
                                                            $header.text(function () {
                                                                //change text based on condition
                                                                return $content.is(":visible") ? "Author[-]" : "Author[+]";
                                                            });
                                                        });

                                                    });
												</script>--%>
											</div>
										</s:iterator>
									</div>
									<%-- <div class="author-data">
                                              <div class="form-group row">
                                                  <label class="col-md-4 control-label">Name</label>
                                                  <div class="col-md-8">
                                                      <s:text  id="authorName" name="digitalManuscriptVO.authorVO.name" />
                                                  </div>
                                              </div>

                                              <div class="form-group row">
                                                  <label class="col-md-4 control-label">Name (in Diacritical)</label>
                                                  <div class="col-md-8">
                                                      <s:text  id="authorDiacriticName" name="digitalManuscriptVO.authorVO.diacriticName"  placeholder="Multilingual field.."/>
                                                  </div>
                                              </div>

                                              <div class="form-group row">
                                                  <label class="col-md-4 control-label">Name (in Vernacular)</label>
                                                  <div class="col-md-8">
                                                      <s:text  id="authorRegionalName" name="digitalManuscriptVO.authorVO.regionalName"  placeholder="Multilingual field.."/>
                                                  </div>
                                              </div>
                                              <div class="form-group row">
                                                    <div class="col-md-12">
                                                        <div class="alert alert-warning validation-warning" style="border-color: #d88a25;">
                                                              <h6>Tip : You must enter at least one of the three form fields (ie. name, regional name, diacritical name)</h6>
                                                      </div>
                                                    </div>
                                              </div>
                                              <div class="form-group row">
                                                  <label class="col-md-4 control-label">Period of The Author</label>
                                                  <div class="col-md-6">
                                                      <s:text  id="authorPeriod" name="digitalManuscriptVO.authorVO.period" />
                                                  </div>
                                                  <div class="col-md-2">
                                                      <s:select list="#{'AD':'AD', 'BC':'BC'}" name="digitalManuscriptVO.authorVO.periodEra"
                                                          value="digitalManuscriptVO.authorVO.periodEra"  id="periodEra"/>
                                                  </div>
                                              </div>
                                              <div class="form-group row">
                                                  <label class="col-md-4 control-label">Author's Life History</label>
                                                  <div class="col-md-8">
                                                      <s:textarea  name="digitalManuscriptVO.authorVO.lifeHistory" id="authorLifeHistory" maxlength="1500"/>
                                                  </div>
                                              </div>
                                        </div> --%>
								</fieldset>
								<div id="extraAuthoeDiv"></div>
								<div class="commentator-container">
									<fieldset>
										<legend style="color: #404040;">Commentator</legend>
										<div class="commentator-search alert alert-success"
											style="background-color: #EEE; border-color: #404040;">
											<div class="form-group row">
												<div class="col-md-4">
													<label class="control-label">Search By Name</label> <span
														class="glyphicon glyphicon-info-sign labelInfo"
														style="color: red;" data-toggle="popover"
														data-content="Type the first letters of the Commentator's name in the auto complete field and the
						          				related records will be fetched if present and then select the Commentator's name you want to refer.Otherwise click on
						          				ADD NEW RECORD and add a new Commentator."></span>
												</div>
												<div class="col-md-6">
													<s:text name="commentatorSearch" id="commentatorSearch" />
												</div>
												<div class="col-md-2">
													<input type="button"
														class="btn btn-lg btn-success btn-block" value="New"
														id="add-commentator">
												</div>
											</div>
										</div>
									</fieldset>
									<div class="commentator-data">
										<div class="form-group row">
											<label class="col-xs-4 control-label">Commentator
												Name</label>
											<div class="col-xs-8">
												<s:text name="digitalManuscriptVO.commentatorVO.name"
													id="commentatorName" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-4 control-label">Name (in
												Diacritical)</label>
											<div class="col-md-8">
												<s:text
													name="digitalManuscriptVO.commentatorVO.diacriticName"
													id="commentatorDiacriticName" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-4 control-label">Name (in
												Vernacular)</label>
											<div class="col-md-8">
												<s:text
													name="digitalManuscriptVO.commentatorVO.regionalName"
													id="commentatorRegionalName" />
											</div>
										</div>
									</div>
								</div>
								<div class="translator-container">
									<fieldset>
										<legend style="color: #404040;">Translator</legend>
										<div class="translator-search alert alert-success"
											style="background-color: #EEE; border-color: #404040;">
											<div class="form-group row">
												<div class="col-md-4">
													<label class="control-label">Search By Name</label> <span
														class="glyphicon glyphicon-info-sign labelInfo"
														style="color: red;" data-toggle="popover"
														data-content="Type the first letters of the Translator's name in the auto complete field and the
							          				related records will be fetched if present and then select the Translator's name you want to refer.Otherwise click on
							          				ADD NEW RECORD and add a new Translator."></span>
												</div>
												<div class="col-md-6">
													<s:text name="111" id="translatorSearch" />
												</div>
												<div class="col-md-2">
													<input type="button"
														class="btn btn-lg btn-success btn-block" value="New"
														id="add-translator">
												</div>
											</div>
										</div>
									</fieldset>
									<div class="translator-data">
										<div class="form-group row">
											<label class="col-xs-4 control-label">Translator Name</label>
											<div class="col-xs-8">
												<s:text name="digitalManuscriptVO.translatorVO.name"
													id="translatorName" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-4 control-label">Name (in
												Diacritical)</label>
											<div class="col-md-8">
												<s:text
													name="digitalManuscriptVO.translatorVO.diacriticName"
													id="translatorDiacriticName" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-4 control-label">Name (in
												Vernacular)</label>
											<div class="col-md-8">
												<s:text name="digitalManuscriptVO.translatorVO.regionalName"
													id="translatorRegionalName" />
											</div>
										</div>
									</div>
								</div>

								<div class="subcommentator-container">
									<fieldset>
										<legend style="color: #404040;">Sub - Commentator</legend>
										<div class="subcommentator-search alert alert-success"
											style="background-color: #EEE; border-color: #404040;">
											<div class="form-group row">
												<div class="col-md-4 ">
													<label class="control-label">Search By Name</label> <span
														class="glyphicon glyphicon-info-sign labelInfo"
														style="color: red;" data-toggle="popover"
														data-content="Type the first letters of the Sub-Commentator's name in the auto complete field and the
								          				related records will be fetched if present and then select the Sub-Commentator's name you want to refer from the existing list.Otherwise click on
								          				ADD NEW RECORD and add a new Sub-Commentator"></span>
												</div>
												<div class="col-md-6">
													<s:text name="subCommentatorSearch"
														id="subCommentatorSearch" />
												</div>
												<div class="col-md-2">
													<input type="button"
														class="btn btn-lg btn-success btn-block" value="New"
														id="add-subCommentator">
												</div>
											</div>
										</div>
									</fieldset>
									<div class="subCommentator-data">
										<div class="form-group row">
											<label class="col-xs-4 control-label">Sub Commentator
												Name</label>
											<div class="col-xs-8">
												<s:text name="digitalManuscriptVO.subCommentatorVO.name"
													id="subCommentatorName" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-4 control-label">Name (in
												Vernacular)</label>
											<div class="col-md-8">
												<s:text
													name="digitalManuscriptVO.subCommentatorVO.diacriticName"
													id="subCommentatorDiacriticName" />
											</div>
										</div>

										<div class="form-group row">
											<label class="col-md-4 control-label">Name (in
												Vernacular)</label>
											<div class="col-md-8">
												<s:text
													name="digitalManuscriptVO.subCommentatorVO.regionalName"
													id="subCommentatorRegionalName" />
											</div>
										</div>
									</div>
								</div>


								<div class="manuscript-specific">
									<fieldset>
										<legend style="color: #404040;">Scribe</legend>
										<%--<div class="scribe-search alert alert-success" style="background-color: #EEE; border-color: #404040;">
											<div class="form-group row">
												<label class="col-md-4 control-label">Search By Name</label>
												<div class="col-md-6">
													<s:textfield cssClass="form-control" id="scribeSearch" maxlength="50" placeholder="Autocomplete Field.."/>
												</div>
												<div class="col-md-2">
													<input type="button" class="btn btn-lg btn-success btn-block" value="New" id="add-scribe">
												</div>
											</div>
										</div>--%>
										<div class="">
											<div class="form-group row">
												<label class="col-md-4 control-label">Scribe's Name</label>
												<div class="col-md-8">
													<s:property value="digitalManuscriptVO.scribeVO.name" />
												</div>
											</div>

											<div class="form-group row">
												<label class="col-md-4 control-label">Name (in
													Diacritical)</label>
												<div class="col-md-8">
													<s:text name="digitalManuscriptVO.scribeVO.diacriticName" />
												</div>
											</div>

											<div class="form-group row">
												<label class="col-md-4 control-label">Name (in
													Vernacular)</label>
												<div class="col-md-8">
													<s:text name="digitalManuscriptVO.scribeVO.regionalName" />
												</div>
											</div>
										</div>
									</fieldset>
								</div>
								<div class="form-group row buttons">
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block"
											value="Previous" id="previous-author">
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-danger btn-block"
											value="Cancel" id="cancel-author">
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block"
											value="Next" id="submit-author">
									</div>
								</div>
							</div>
							<div class="tab-pane fade container-center" id="frame"
								style="max-width: 100%;">


								<div class="form-group row" style="margin-bottom: 2px;">

									<div class="col-md-12" align="center">
										<span class="loading-container"><img
											src="<%=request.getContextPath()%>/assets/images/loading.gif"
											width=100em height=auto></span>
									</div>
								</div>
								<div class="form-group row"
									style="display: none; padding: 15px;" id="thumbnaildisplay">
									<div style="max-height: 32em; border: 1px solid #404040;">
										<div id="selectbutton-pannel"></div>
										<div class="panel-body"
											style="max-height: 25em; overflow-y: scroll; border-top: 1px solid #404040;">
											<!-- <div class="imageContainer" id="imageContainer">
                                            </div> -->
										</div>
									</div>
								</div>
								<div class="form-group row buttons container-center"
									style="margin-top: 100px;" id="frameButton">
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block"
											value="Previous" id="previous-frame">
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-danger btn-block"
											value="Cancel" id="cancel-frame">
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block"
											value="Next" id="submit-frame">
									</div>
								</div>
							</div>
							<div class="tab-pane fade container-center" id="nmm"
								style="max-width: 100%;">
								<br>
								<div class="panel-group" id="accordion">
									<div id="accordionPanelHeader"
										class="panel panel-default appTable">
										<div class="panel-heading" data-target="#collapseOne"
											data-toggle="expand" data-parent="#accordion"
											style="background: #eee;">Subject Details</div>
										<div id="collapseOne" class="panel-collapse expand in"
											style="padding: 5px;">
											<div class="form-group row">
												<label class="col-md-2 control-label">Nature Of
													Collection</label>
												<div class="col-md-4 ">
													<s:if test="digitalManuscriptVO.natureOfCollection==1">
															Personal
														</s:if>
													<s:if test="digitalManuscriptVO.natureOfCollection==0">
															Institutional
														</s:if>
												</div>


												<label class="col-md-2 control-label">Bound</label>
												<div class="col-md-4 btn-group" data-toggle="buttons">

													<s:if test="digitalManuscriptVO.isBound==1">
															Yes
														</s:if>
													<s:if test="digitalManuscriptVO.isBound==0">
															No
														</s:if>
												</div>
											</div>

											<div class="form-group row">
												<label class="col-md-2 control-label">Source Of
													Catalogue</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.sourceOfCatalogue" />
												</div>
												<label class="col-md-2 control-label">Catalogue
													Number</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.catalogueNumber" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                 <label class="col-md-4 control-label">Catalogue Number</label>
                                                 <div class="col-md-8">
                                                     <s:text  name="digitalManuscriptVO.catalogueNumber" />
                                                 </div>
                                             </div> --%>
											<div class="form-group row">
												<label class="col-md-2 control-label">Catalogue
													Details</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.catalogueDetails" />
												</div>
												<label class="col-md-2 control-label">Documentation</label>
												<div class="col-md-4">
													<s:text
														name="digitalManuscriptVO.documentationOfManuscript" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                <label class="col-md-4 control-label">Documentation</label>
                                                <div class="col-md-8">
                                                <s:select list="manuscriptDocumentationTypes"
                                                    name="digitalManuscriptVO.documentationOfManuscript" autoComplete="false" />
                                                </div>
                                              </div> --%>
											<div class="form-group row">
												<%-- <label class="col-md-2 control-label">Number Of Folios</label>
                                                    <div class="col-md-4">
                                                        <s:text  name="digitalManuscriptVO.totalNumberOfFolios"  id="folios" readonly="true"/>
                                                    </div> --%>
												<label class="col-md-2 control-label">Colophon</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.colophon" />
												</div>
												<label class="col-md-2 control-label">Number Of
													Illustrations</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.totalNumberOfMaps" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                  <label class="col-md-4 control-label">Number Of Illustrations</label>
                                                  <div class="col-md-8">
                                                      <s:text  name="digitalManuscriptVO.totalNumberOfMaps" />
                                                  </div>
                                              </div> --%>
											<div class="form-group row">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Condition Of Manuscript</label>
												<div class="col-md-4">


													<s:iterator value="conditionOfManuscriptVOS"
														status="conditionOfManuscriptVOIndex"
														var="conditionOfManuscriptVOS">
														<s:iterator value="digitalManuscriptVO.comIds"
															var="comIds" status="comIdsIndex">
															<s:if test="#conditionOfManuscriptVOS.id==#comIds">

																<s:property value="#conditionOfManuscriptVOS.name" />
																<br />
															</s:if>
														</s:iterator>
													</s:iterator>
													<%--<s:select list="specificCategoryVOs" required="true" id="specificCategoryId"--%>
													<%--name="digitalManuscriptVO.specificCategoryId" listKey="id" listValue="name"  onChange="onChangeSpCategory();" multiple="true"/>--%>
												</div>
												<div class="col-md-2">
													<label class="control-label">Digitized By</label> <span
														class="glyphicon glyphicon-info-sign labelInfo"
														style="color: red;" data-toggle="popover"
														data-content="Start typing the name and select the required entry from the list of available entries"></span>
												</div>
												<div class="col-md-4">
													<s:hidden name="digitalManuscriptVO.digitizerId"
														id="digitiserId"></s:hidden>
													<s:text name="digitalManuscriptVO.digitizedBy" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                 <div class="col-md-4">
                                                    <label class="control-label">Digitized By</label>
                                                    <span class="glyphicon glyphicon-info-sign labelInfo" style="color: red;"
                                                                    data-toggle="popover" data-content="Start typing the digitizer name from the begining in the auto-complete field and you will get the related digitizer list if present in the system.Then select the proper digitizer from the list."></span>
                                                  </div>
                                                    <div class="col-md-8">
                                                          <s:hidden name="digitalManuscriptVO.digitizerId" id="digitiserId"></s:hidden>
                                                        <s:text  name="digitalManuscriptVO.digitizedBy" id="digitiserName"   placeholder="Autocomplete Field.."/>
                                                    </div>
                                                </div>  --%>
											<div class="form-group row">
												<label class="col-md-2 control-label">Beginning Line</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.beginningLine" />
												</div>
												<label class="col-md-2 control-label">Ending Line</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.endingLine" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                    <label class="col-md-4 control-label">Ending Line</label>
                                                    <div class="col-md-8">
                                                        <s:text  id="endingLine" name="digitalManuscriptVO.endingLine" placeholder="Multilingual field.." />
                                                    </div>
                                                </div> --%>
										</div>
									</div>
									<div class="panel panel-default appTable"
										id="accordionPanelHeader1">
										<div class="panel-heading" data-toggle="collapse"
											data-parent="#accordion" data-target="#collapseTwo"
											style="background: #eee;">Technical Details</div>
										<div id="collapseTwo" class="panel-collapse expand"
											style="padding: 5px;">
											<!-- <div class="image-details manuscript-specific"> -->
											<div class="form-group row">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Average Image Height</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.height" />
												</div>
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Average Image Width</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.width" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                    <label class="col-md-4 control-label">Average Image Width</label>
                                                    <div class="col-md-8">
                                                        <s:text  id="imageWidth" name="digitalManuscriptVO.nmmDetailsVO.width" />
                                                    </div>
                                                </div> --%>
											<div class="form-group row">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Median Created Date</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.createdDate" />
												</div>
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Median Digitised Date</label>
												<div class="col-md-4">
													<s:text
														name="digitalManuscriptVO.nmmDetailsVO.digitisedDate" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                    <label class="col-md-4 control-label">Median Digitised Date</label>
                                                    <div class="col-md-8">
                                                        <s:text  id="imageDigitisedDate" name="digitalManuscriptVO.nmmDetailsVO.digitisedDate" />
                                                    </div>
                                                </div> --%>
											<div class="form-group row">
												<label class="col-md-2 control-label">Camera Make</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.cameraMake" />
												</div>
												<label class="col-md-2 control-label">Camera Model</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.cameraModel" />
												</div>
											</div>
											<%-- <div class="form-group row">
                                                    <label class="col-md-4 control-label">Camera Model</label>
                                                    <div class="col-md-8">
                                                        <s:text  id="imageCameraModel" name="digitalManuscriptVO.nmmDetailsVO.cameraModel" />
                                                    </div>
                                                </div> --%>
											<div class="form-group row">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">X Resolution</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.xResolution" />
												</div>
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Y Resolution</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.nmmDetailsVO.yResolution" />
												</div>
											</div>

										</div>
									</div>

									<div class="panel panel-default appTable"
										id="accordionPanelHeader2">
										<div class="panel-heading" data-toggle="collapse"
											data-parent="#accordion" data-target="#collapseThree"
											style="background: #eee;">Other Details</div>
										<div id="collapseThree" class="panel-collapse expand"
											style="padding: 5px;">
											<div class="form-group">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Lines Per Page</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.linesPerPage" />
												</div>
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Characters per line</label>
												<div class="col-md-4">
													<s:text name="digitalManuscriptVO.charactersPerLine" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Patha</label>
												<div class="col-md-4">
													<%-- <s:select list="pathaVOs" required="true" id="pathaId"
														name="digitalManuscriptVO.pathaIds" listKey="id"
														listValue="name" multiple="true" /> --%>
													<s:iterator value="pathaVOs" status="pathaVOsIndex"
														var="pathaVO">
														<s:iterator value="digitalManuscriptVO.pathaIds"
															var="pathaId" status="pathaIdIndex">
															<s:if test="#pathaVO.id==#pathaId">
																<s:property value="#pathaVO.name" />
																<br />
															</s:if>
														</s:iterator>
													</s:iterator>

												</div>
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Red Marking</label>
												<div class="col-md-4">
													<div class="row">
														<div class="col-md-6">
															<label class="">
																${digitalManuscriptVO.redMarked==1?'Red Mark':''} </label>
														</div>
														<div class="col-md-6 div_redMarkedText"
															style="${digitalManuscriptVO.redMarked==1?'':'display: none'}">

															<s:text name="digitalManuscriptVO.redMarkedText" />
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<label class="">
																${digitalManuscriptVO.redLines==1?'Red lines':''} </label>
														</div>
														<div class="col-md-6 div_redLinesText"
															style="${digitalManuscriptVO.redLines==1?'':'display: none'}">
															<s:text name="digitalManuscriptVO.redLinesText" />
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<label class="">
																${digitalManuscriptVO.redLetters==1?'Red
																Letters':''}
															</label>
														</div>
														<div class="col-md-6 div_redLettersText"
															style="${digitalManuscriptVO.redLetters==1?'':'display: none'}">
															<s:text name="digitalManuscriptVO.redLettersText" />
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">

															${digitalManuscriptVO.redDigits==1?'Red digits':''} </label>
														</div>
														<div class="col-md-6 div_redDigitsText"
															style="${digitalManuscriptVO.redDigits==1?'':'display: none'}">
															<s:text name="digitalManuscriptVO.redDigitsText" />
														</div>
													</div>
												</div>
											</div>
											<div class="clearfix"></div>
											<hr />
											<div class="form-group">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Edited</label>
												<div class="col-md-4">
													<div class="row">
														<div class="col-md-12">

															${digitalManuscriptVO.edited==1?'Yes':''}
															${digitalManuscriptVO.edited==0?'No':''}</div>
													</div>

													<div class="row edited"
														style="${digitalManuscriptVO.edited==1?'':'display: none'}">

														<div class="col-md-8">
															<%-- 	<s:checkboxlist theme="vertical-checkbox"
																label="Edited  List" list="editedTypeList"
																name="digitalManuscriptVO.editedType"
																value="digitalManuscriptVO.savedEditedType" /> --%>

														</div>
														<div class="col-md-12">
															<label>Remarks</label>
															<s:text id="editedRemarks"
																name="digitalManuscriptVO.editedTypeRemarks" />
														</div>
													</div>
												</div>

												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Decorated</label>
												<div class="col-md-4">
													<label class="checkbox-inline">
														${digitalManuscriptVO.decorated==1?'Yes':''}

														${digitalManuscriptVO.decorated==0?'No':''} </label>
													<div class="col-md-12  decorated"
														style="${digitalManuscriptVO.decorated==1?'':'display: none'}">
														<label class="control-label">Remarks </label>
														<s:text id="editedRemarks"
															name="digitalManuscriptVO.decoratedRemarks" />
													</div>

												</div>

											</div>
											<div class="clearfix"></div>
											<hr />
											<div class="form-group">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Illustrations</label>
												<div class="col-md-4">
													<div class="row">
														<div class="col-md-12">
															<label class="checkbox-inline">
																${digitalManuscriptVO.illustrations==1?'Yes':''}

																${digitalManuscriptVO.illustrations==0?'No':''} </label>
														</div>
													</div>
													<div
														style="${digitalManuscriptVO.illustrations==1?'':'display: none'}">

														<div class="col-md-10">

															<s:iterator
																value="digitalManuscriptVO.savedIllustrationsType"
																var="illstrutartion">
																<p>
																	<s:property value="illstrutartion" />
																</p>
															</s:iterator>


														</div>
														<div class="col-md-12 illremarks"
															style="${digitalManuscriptVO.savedIllustrationsType.contains("Other")?'':'display:none;'}">
															<label> Remarks </label>
															<s:text name="digitalManuscriptVO.illustrationsOthers" />
														</div>
													</div>
												</div>

												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Ink/Pigment</label>
												<div class="col-md-4">
													<div class="row">
														<div class="col-md-9">
															<%-- <s:checkboxlist theme="vertical-checkbox"
																label="ink Pigment" list="inkPigmentList"
																onclick="return inkPigment(this)"
																name="digitalManuscriptVO.inkPigment"
																value="digitalManuscriptVO.savedInkPigmentTypeList" /> --%>
															<s:iterator
																value="digitalManuscriptVO.savedInkPigmentTypeList"
																var="savedInkPigment">
																<p>
																	<s:property value="savedInkPigment" />
																</p>
															</s:iterator>
														</div>
														<div class="col-md-12 inkremarks"
															style="${digitalManuscriptVO.savedIllustrationsType.contains("Other")?'':'display:none;'}">
															<label>Remarks</label>
															<s:text name="digitalManuscriptVO.inkPigmentOthers" />
														</div>
													</div>
												</div>

											</div>
											<div class="clearfix"></div>
											<hr />
											<div class="form-group">
												<label class="col-md-2 control-label"
													style="padding-right: 0px;">Miscellaneous Remarks</label>
												<div class="col-md-10">
													<s:text name="digitalManuscriptVO.miscellaneousRemarks" />
													<!-- <textarea rows="5" cols="400" class="form-control"></textarea> -->
												</div>
											</div>


										</div>
									</div>
								</div>
								<div class="clearfix"></div>
								<hr />

								<div class="form-group  buttons container-center"
									style="padding-top: 4px;">
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block"
											value="Previous" id="previous-nmm">
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-danger btn-block"
											value="Cancel" id="cancel-nmm">
									</div>
									<div class="col-md-4">
										<input type="button" class="btn btn-lg btn-primary btn-block"
											value="Next" id="submit-nmm">
									</div>
								</div>
								<div class="clearfix"></div>
								<hr />
							</div>
							<div class="tab-pane fade container-center" id="publication"
								style="max-width: 100%;">
								<br>
								<fieldset>
									<legend style="color: #404040;">Source Details</legend>
									<div class="form-group row">
										<label class="col-xs-2 control-label">Name</label>
										<div class="col-xs-4">
											<s:text name="digitalManuscriptVO.organisationVO.name" />
										</div>
										<label class="col-xs-2 control-label">Website</label>
										<div class="col-xs-4">
											<s:text name="digitalManuscriptVO.organisationVO.website" />
										</div>
									</div>
									<%-- <div class="form-group row">
                                            <label class="col-xs-4 control-label">Website</label>
                                            <div class="col-xs-8">
                                                <s:text  name="digitalManuscriptVO.organisationVO.website" id="organisationWebsite" />
                                            </div>
                                        </div> --%>
									<div class="form-group row">
										<label class="col-xs-2 control-label">Phone Number</label>
										<div class="col-xs-4">
											<s:text name="digitalManuscriptVO.organisationVO.phoneNumber" />
										</div>
										<label class="col-xs-2 control-label">E-mail</label>
										<div class="col-xs-4">
											<s:text name="digitalManuscriptVO.organisationVO.email" />
										</div>
									</div>
									<%-- <div class="form-group row">
                                           <label class="col-xs-4 control-label">E-mail</label>
                                           <div class="col-xs-8">
                                               <s:text  name="digitalManuscriptVO.organisationVO.email" id="organisationEmail" />
                                           </div>
                                       </div> --%>
									<%--  <div class="form-group row">
                                             <label class="col-xs-4 control-label">Acronym</label>
                                             <div class="col-xs-8">
                                                 <s:text  name="digitalManuscriptVO.organisationVO.acronym" id="organisationAcronym" />
                                             </div>
                                         </div> --%>
									<div class="form-group row">
										<label class="col-md-2 control-label">Address</label>
										<div class="col-md-10">
											<s:text name="digitalManuscriptVO.organisationVO.address" />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-md-4 control-label">Type</label>

										<div class="col-md-8">
											<s:if test="digitalManuscriptVO.organisationVO.type==1">
															Individual
														</s:if>
											<s:if test="digitalManuscriptVO.organisationVO.type==0">
															Institution
														</s:if>
											<!-- <label class="col-xs-4 btn btn-primary"
												id="isOrganisation1Container"> <input type="radio"
												id="isOrganisation1" value="1">
											</label> <label class="col-xs-4 btn btn-primary"
												id="isOrganisation2Container"> <input type="radio"
												id="isOrganisation2" value="0"> -->
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend style="color: #404040;">Publication Details</legend>
									<div class="form-group row is-published">
										<label class="col-md-4 control-label">Published</label>
										<div class="col-md-4 btn-group" data-toggle="buttons">
											<!-- <label class="col-xs-4 btn btn-primary"
												id="isPublished1Container"> <input type="radio"
												id="isPublished1" value="1">Yes
											</label> <label class="col-xs-4 btn btn-primary"
												id="isPublished2Container"> <input type="radio"
												id="isPublished2" value="0">No
											</label> -->

											<s:if test="digitalManuscriptVO.publicationVO.id>0">
												Yes
											</s:if>
											<s:if test="digitalManuscriptVO.publicationVO.id==0">
												No
											</s:if>
										</div>
									</div>
									<div class="published">
										<div class="form-group row">
											<label class="col-md-2 control-label">Name of Publisher</label>
											<div class="col-md-4">
												<s:text name="publicationVO.publisherVO.name"
													  />
											</div>
											<label class="col-md-2 control-label">Name of Editor</label>
											<div class="col-md-4">
												<s:text name="publicationVO.editorVO.name"   />
											</div>
										</div>

										<%-- <div class="form-group row">
                                                <label class="col-md-4 control-label">Name of Editor</label>
                                                <div class="col-md-8">
                                                    <s:text  name="publicationVO.editorVO.name" id="editorName" />
                                                </div>
                                            </div> --%>

										<div class="form-group row">
											<label class="col-md-2 control-label">Year of
												Publication</label>
											<div class="col-md-4">
												<s:text name="publicationVO.yaerOfPublication"
													  />
											</div>
											<label class="col-md-2 control-label">Number of Pages</label>
											<div class="col-md-4">
												<s:text name="publicationVO.noOfPages"  />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-md-2 control-label">Price (INR)</label>
											<div class="col-md-4">
												<s:text name="publicationVO.price"  />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-md-2 control-label">Address</label>
											<div class="col-md-10">
												<s:text name="publicationVO.publisherVO.address"
													  />
											</div>
										</div>
										<%-- <div class="form-group row">
                                                <label class="col-md-4 control-label">Number of Pages</label>
                                                <div class="col-md-8">
                                                    <s:text  name="publicationVO.noOfPages" id="noOfPages" />
                                                </div>
                                            </div> --%>
										<div class="form-group row">
											<label class="col-md-4 control-label">Available In Print</label>
											<div class="col-md-8 btn-group" data-toggle="buttons">
												<s:if test="publicationVO.isAvailable==1">
													Yes
												</s:if>
												<s:if test="publicationVO.isAvailable==0">
													No
												</s:if>
												<s:if test="publicationVO.isAvailable==2">
													Unknown
												</s:if>
												<!-- <label class="col-xs-4 btn btn-primary"
													id="isAvailable1Container"> <input type="radio"
													id="isAvailable1" value="1">Yes
												</label> <label class="col-xs-4 btn btn-primary"
													id="isAvailable2Container"> <input type="radio"
													id="isAvailable2" value="0">No
												</label> <label class="col-xs-4 btn btn-primary"
													id="isAvailable3Container"> <input type="radio"
													id="isAvailable3" value="2">Unknown
												</label> -->
											</div>
										</div>
									</div>
									<div class="form-group row buttons">
										<div class="col-md-4">
											<input type="button" class="btn btn-lg btn-primary btn-block"
												value="Previous" id="previous-publisher">
										</div>
										<div class="col-md-4">
											<input type="button" class="btn btn-lg btn-danger btn-block"
												value="Cancel" id="cancel-publisher">
										</div>
										<div class="col-md-4">
											<span class="loading-container" id="loadingContainer">
												<img
												src="<%=request.getContextPath()%>/assets/images/loading.gif"
												width=100em height=auto>
											</span>
											<%-- <s:submit cssClass="btn btn-lg btn-primary btn-block"
												value="Submit" id="submit-publisher" /> --%>
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
</div>
<%@ include file='../layout/footer.jsp'%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/Validator.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/addmanuscript.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/rangy-core.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.selector.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.preferences.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/assets/js/jquery.ime.inputmethods.js"></script>
<script type="text/javascript">




    $(document).ready(function() {
        $('#submit-publisher').show();
        $('#originalWorkRef').hide();
        $('#originalCommRef').hide();
        //$('#thumbnaildisplay').hide();
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
                $('.articleContainer').hide();
                $('.is-published-oth').hide();
            } else if($('#documentType').val() == 2) {
                $('.bookContainer').hide();
                $('.manuscript-specific').show();
                $('.is-published').show();
                $('.articleContainer').hide();
                $('.is-published-oth').hide();
                if($('#publicationId').val() > 0) {
                    $('.published').show();
                    $('#isPublished1Container').addClass('active');
                    $('#isPublished2Container').removeClass('active');
                } else {
                    $('.published').hide();
                    $('#isPublished1Container').removeClass('active');
                    $('#isPublished2Container').addClass('active');
                }
            }else if($('#documentType').val() == 3) {
                $('.bookContainer').hide();
                $('.man-book-specific').hide();
                $('.manuscriptContainer').hide();
                $('.articleContainer').show();
                $('.manuscript-specific').hide();
                $('.is-published-oth').show();
                $('.is-published').hide();
                $('.published').show();
                /* if($('#publicationId').val() > 0) {
                    $('.published').show();
                    $('#isPublished1Container').addClass('active');
                    $('#isPublished2Container').removeClass('active');
                } else {
                    $('.published').hide();
                    $('#isPublished1Container').removeClass('active');
                    $('#isPublished2Container').addClass('active');
                } */

                if($('#articleDetailsType').val()==1) {
                    $('.Otherpublished').fadeIn();
                    $('.ifMagazine').fadeOut();
                    $('#isPrinted').fadeIn();

                    $('#isOtherPublished1Container').addClass('active');
                    $('#isOtherPublished2Container').removeClass('active');

                } else if($('#articleDetailsType').val()==2) {
                    $('.Otherpublished').fadeOut();
                    $('.ifMagazine').fadeIn();
                    $('#isPrinted').fadeIn();
                    $('#isOtherPublished2Container').addClass('active');
                    $('#isOtherPublished1Container').removeClass('active');
                }else{
                    $('.Otherpublished').fadeOut();
                    $('.ifMagazine').fadeOut();
                    $('#isPrinted').fadeOut();
                    /* $('#isOtherPublished1Container').removeClass('active');
                    $('#isOtherPublished2Container').removeClass('active'); */
                }


            }
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

        $('#beginningLine, #endingLine, #colophon ,#manuscriptDiacriticName,#manuscriptRegionalName,#authorDiacriticName,#authorRegionalName,#commentatorRegionalName,#commentatorDiacriticName,#translatorDiacriticName,#translatorRegionalName,#subCommentatorDiacriticName,#subCommentatorRegionalName,#scribeDiacriticName,#scribeRegionalName').ime();
        if($('#fileDiskPathContainer').val().length > 0) {
            var countImage=0;
            var fileDiskPathObject = JSON.parse($('#fileDiskPathContainer').val());
            var htmlString = "";
            var filePath;
            var str="";
            for(var i = 0; i < Object.keys(fileDiskPathObject).length; i++) {
                if(fileDiskPathObject[i].id != ""){
                    filePath = fileDiskPathObject[i].filePathReal.replace(/\\/g, "/");
                    htmlString += "<div id='div_" + fileDiskPathObject[i].id + "' class='img-thumbnail'>";
                    htmlString += "<input type='checkbox' id='"+fileDiskPathObject[i].id+"' name='check' style='display:none;' />";
                    /* htmlString += "<a id='"+fileDiskPathObject[i].id+"' href='#' class='deleteimg-thumbnail'>ï¿½</a>"; */
                    /* htmlString += "<img src='" + '/MDR'+ '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>"; */
					console.log(filePath)
					//if (!filePath.contains(".pdf")) {
						htmlString += "<img src='" + '<%=request.getContextPath()%>/imageAction.action?isThumbnail=true&imagePath=' + filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";
				/*	} else {
						htmlString += "===================";

					}*/
                    //htmlString += "<img src='" + '<%=request.getContextPath()%>/imageAction.action?isThumbnail=true&imagePath='+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";
                    htmlString += "</div>";
                    countImage++;
                }
            }
            /* var folio = Math.round(countImage/2);
            $('#folios').val(folio); */
            $('#frameCount').text("Number Of Frames : "+countImage);
            $('#frameButton').css('margin-top', '350px');
            $("#thumbnaildisplay").css('display','');
            //$('#thumbnaildisplay').show();
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

    /* $('#authorSearch').keyup(function() {
        if(authorFlag != 1) { */
    /* If the author data is selected from autocomplete, and author name is changed
        then remove all corresponding data from author fields */
    /* $('#authorId').val("");
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
}); */

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

            //if(validateForm1()) {

            $('#tab-form a[href="#frame"]').tab('show');
            $('#tab-form a[href="#frame"]').parent().attr("class",'active');
            $("#msg-container").addClass('hide');
            //}
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
            window.history.back();
            <%-- window.location.replace("<%=request.getContextPath()%>/homePageAction.action?requestId="+requestId); --%> /* Redirect to home page */
        } else if($(e.target).attr('id') == "add-author") {
            /* $('#authorSearch').val("");
            $('#authorId').val("");
            $('#authorName').val("");
            $('#authorRegionalName').val("");
            $('#authorDiacriticName').val("");
            $('#authorLifeHistory').val("");
            $('#authorPeriod').val("");
            $('#periodEra').val("");

            $('.author-data').fadeIn();
            $('#authorName').focus(); */
            getAuthorField();
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

    $('#isOtherPublished1Container').click(function() {
        $('.Otherpublished').fadeIn();
        //$(".Otherpublished > :input, :select, :textfield").attr("disabled", false);

        $('.ifMagazine').fadeOut();
        $('#isPrinted').fadeIn();

        $('#articleDetailsType').val(1);
        //$(".ifMagazine > :input, :select, :textarea").attr("disabled", true);

    });

    /* $('#nameInEnglish').click(function() {
        $('#otherLanguageNameField').fadeOut();
        $('#articleLanguage').val(2);
    });

    $('#nameInOtherLanguage').click(function() {
        $('#otherLanguageNameField').fadeIn();
        $('#articleLanguage').val(1);
    }); */

    $('#isPublished2Container').click(function() {
        $('.published').fadeOut();
        $(".published :input, :select, :textarea").attr("disabled", true);
    });

    $('#isOtherPublished2Container').click(function() {
        $('.Otherpublished').fadeOut();
        //$(".Otherpublished > :input, :select,:textfield").attr("disabled", true);

        $('.ifMagazine').fadeIn();
        $('#isPrinted').fadeIn();
        $('#articleDetailsType').val(2);
        //$(".ifMagazine > :input, :select,:textfield").attr("disabled", false);
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
            //var result1 = validateForm1();
            var result2 = validateForm2();
            var result3 = validateForm3();
            if(result2 && result3) {
                $('#photo').attr("disabled", true);
                //Should not submit the file, it crashes the system. It is handled differently.
                $('#submit-publisher').submit();
                $('#submit-publisher').hide();
                $('#loadingContainer').css("display", "block");
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

    /* function validateForm1() {

        var fieldForNumericValidator = [authorPeriod];
        var isCorrectData = true;
        var fieldForAtleastOneValidator = [authorName, authorRegionalName, authorDiacriticName];

        var message = "abc";

        //message = atLeastOneFieldValidator(fieldForAtleastOneValidator);
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
     */
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
                    $(fieldForAtleastOneValidator[i]).parent().addClass('has-error');
                    id = $(fieldForAtleastOneValidator[i]).parent().parent().parent().attr('id');
                    $('a[href=#'+id+']').parent().addClass('error-tab');
                }
            }
            isCorrectData = false;
        } else {
            for(var i = 0; i < fieldForAtleastOneValidator.length; i++) {
                $(fieldForAtleastOneValidator[i]).parent().removeClass('has-error');
                id = $(fieldForAtleastOneValidator[i]).parent().parent().parent().attr('id');
                $('a[href=#'+id+']').parent().removeClass('error-tab');
            }
            if($('#documentType').val()!=3){
                if(language.value == "-1"){
                    message="Select particular Language";
                    $(language).parent().addClass('has-error');
                    $('#languageLabel').addClass('has-error');
                    $('a[href=#'+id+']').parent().addClass('error-tab');
                    isCorrectData = false;
                } else {
                    $(language).parent().removeClass('has-error');
                    $('#languageLabel').removeClass('has-error');
                    $('a[href=#'+id+']').parent().removeClass('error-tab');
                }
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
        if($('#isPrinted1Container').hasClass('active')) {
            $('#isPrintedValue').val($('#isPrinted1').val());
        } else if($('#isPrinted2Container').hasClass('active')) {
            $('#isPrintedValue').val($('#isPrinted2').val());
        } else if($('#isPrinted3Container').hasClass('active')) {
            $('#isPrintedValue').val($('#isPrinted3').val());
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
        /* var folio ;
        if($('#folios').val() != "")
        {
        folio=eval($('#folios').val());
        }else{
            folio=0;
        } */
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
                //folio=folio+Math.round(this.files.length/2);
            } else {
                fileData = this.files[0].name.toString();
                /* folio=folio+1; */
            }
            /* $('#folios').val(folio); */
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
        var fileData = $('.file-info').text();
        if(fileData.length > 0){
            var formData = new FormData($('#manuscriptForm')[0]);
            $('.loading-container').fadeIn();
            $.ajax({
//                url: "/MDR/uploadImagesToTemp.action",  //Server script to process data
                url: "<%=request.getContextPath()%>/uploadImagesToTemp.action",  //Server script to process data
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
                    //	alert($('#filePathContainer').val());
                    if(data.status == "success") {
                        /* $("#msg-container").addClass('hide');
                        $( "#msg-success-container" ).show();
                        $("#msg-success-container").text("Successfully uploaded images");
                        $("#msg-success-container").removeClass('hide');
                        $( "#msg-success-container" ).fadeOut(3600);
                         */
                        alert(data.msg);

                    } else if(data.status == "failure") {
                        /* $("#msg-success-container").addClass('hide');
                        $( "#msg-container" ).show();
                        $("#msg-container").text("Unable to upload images");
                        $("#msg-container").removeClass('hide');
                        $( "#msg-container" ).fadeOut(3600 ); */
                        alert(data.msg);
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
        }else{
            alert("Select a file for upload using the Browse button");
        }
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
                htmlString += "<a href='#' class='thumbnail-close'>ï¿½</a>";
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
//                $('.image-max-container img').attr('src', '/MDR'+ '/temp/' + data.realPath);
                $('.image-max-container img').attr('src', '<%=request.getContextPath()%>/temp/' + data.realPath);
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
                            filePath = fileDiskPathObject[i].filePathReal.replace(/\\/g, "/");
                            htmlString += "<div id='div_" + fileDiskPathObject[i].id + "' class='img-thumbnail'>";
                            htmlString += "<input type='checkbox' id='"+fileDiskPathObject[i].id+"' name='check' style='display:none;' />";
                            /* htmlString += "<a id='"+fileDiskPathObject[i].id+"' href='#' class='deleteimg-thumbnail'>ï¿½</a>"; */
                            /* htmlString += "<img src='" + '/MDR'+ '/ImageAction.action?imagePath='+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>"; */
                            htmlString += "<img src='" + '<%=request.getContextPath()%>/imageAction.action?imagePath='+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";
                            htmlString += "</div>";
                            countImage++;
                        }
                    }
                    $('#frameButton').css('margin-top', '350px');
                    $("#thumbnaildisplay").css('display','');
                    //$('#thumbnaildisplay').show();
                    $("#totalImg").text(countImage);
                    $('.panel-body').append(htmlString);
                    $('.panel-body').css('background-color','white');
                    $('#delimg').css('display','none');
                    /* var folio = Math.round(countImage/2);
                    $('#folios').val(folio); */
                    $('#frameCount').text("Number Of Frames : "+countImage);
                }else{
                    $('#frameButton').css('margin-top', '100px');
                    $("#thumbnaildisplay").css('display','none');
                    //$('#thumbnaildisplay').hide();
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
                            filePath = fileDiskPathObject[i].filePathReal.replace(/\\/g, "/");
                            htmlString += "<div id='div_" + fileDiskPathObject[i].id + "' class='img-thumbnail'>";
                            htmlString += "<input type='checkbox' id='"+fileDiskPathObject[i].id+"' name='check' style='display:none;' />";
                            /* htmlString += "<a id='"+fileDiskPathObject[i].id+"' href='#' class='deleteimg-thumbnail'>ï¿½</a>"; */
                            /* htmlString += "<img src='" + '/MDR'+ '/temp/'+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>"; */
                            htmlString += "<img src='" + '<%=request.getContextPath()%>/imageAction.action?imagePath='+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>";

                            /* htmlString += "<img src='" + '/MDR'+ '/imageAction.action?imagePath='+ filePath + "' id='frame_" + fileDiskPathObject[i].id + "' width=35em height=35em class='image-link-container' style='border:1px solid black;'>"; */


                            htmlString += "</div>";
                            countImage++;
                        }
                    }
                    /* var folio = Math.round(countImage/2);
                    $('#folios').val(folio); */
                    $('#frameCount').text("Number Of Frames : "+countImage);
                    $('#frameButton').css('margin-top', '350px');
                    $("#thumbnaildisplay").css('display','');
                    //$('#thumbnaildisplay').show();
                    $("#totalImg").text(countImage);
                    $('.panel-body').append(htmlString);
                }else{
                    $('#frameButton').css('margin-top', '100px');
                    $("#thumbnaildisplay").css('display','none');
                    //$('#thumbnaildisplay').hide();
                }
                alert(data.message);
            }
        });
    }
</script>
</html>