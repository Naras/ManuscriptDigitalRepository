/**
 * 
 */
package com.indven.omds.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.portal.hrd.vo.EmployeeMasterVO;
import com.indven.search.vo.GenericSearch;

/**
 * @author Deba Prasad
 *
 */
public class DigitalManuscriptVO extends IndvenResultVO implements GenericSearch  {

	private Long id;
	private String name;
	private String regionalName;
	private String diacriticName;
	private String manuscriptId;
	private String typeOfWork;
	private String summary;
	private String uniquenessOfWork;
	private String contributionToAyurveda;
	private String remarks;
	private String tableOfContents;
	private String anyOtherDetails;
	
	private Long languageFkId;
	private Long scriptFkId;
	private Long materialFkId;
	private Long organisationFkId;
	private Long publicationFKId;
	private Long categoryFkId;
	private Long specificCategoryFkId;
	private Long documentType;
	
	private String authorName ;
	private Long authorFKId;
	private Long scribeFkId;
	private Long parentFKId;
	private String parentName;
	private String accNumber;
	private Long nmmDetailsFkId;
	
	private String sourceOfCatalogue;
	private String documentationOfManuscript;
	private String conditionOfManuscript;
	private String manuscriptType;
	private String recordStatus;

	private String catalogueDetails;
	private Long totalNumberOfFolios;
	private Long totalNumberOfMaps;
	private String catalogueNumber;
	private Short natureOfCollection;
	private Short isBound;
	private String beginningLine;
	private String endingLine;
	private String colophon;
	
	private String minimumFolios;
	private String maximunFolios;
	
	private NMMDetailsVO nmmDetailsVO;
	private List<DigitalManuscriptFrameVO> digitalManuscriptFrameVOs;
	private String filePathContainer;
	private String fileDiskPathContainer;
	
	private Long bundleMasterFkId;

	private AuthorVO authorVO = new AuthorVO();
	private OrganisationVO organisationVO = new OrganisationVO();
	private MaterialVO materialVO = new MaterialVO();
	private PublisherVO publisherVO = new PublisherVO();
	private PublicationVO publicationVO = new PublicationVO();
	private AuthorVO scribeVO;
	private AuthorVO commentatorVO;
	private AuthorVO translatorVO;
	private AuthorVO subCommentatorVO;
	
	private LanguageVO languageVO;
	private ScriptVO scriptVO;
	
	private List<TagMasterVO> tagList;
	private List<Long> specificCategoryId;
	
	private String documentPublicationStatus;
	
	private EmployeeMasterVO digitiserVO;
	private Long digitizerId;
	private String digitizedBy;
	
	private Short isUnderWfl;
	private String wflProcessOwner;
	private Long activewflProcessId;
	private boolean presentFrame; 
	private Integer totalFrame;
	
	private Boolean isMerging;
	private Long isSavingMerged ;
	private String parentIdsStr;
	
	/**
	 * @return the minimumFolios
	 */
	public final String getMinimumFolios() {
		return minimumFolios;
	}
	/**
	 * @param minimumFolios the minimumFolios to set
	 */
	public final void setMinimumFolios(String minimumFolios) {
		this.minimumFolios = minimumFolios;
	}
	/**
	 * @return the maximunFolios
	 */
	public final String getMaximunFolios() {
		return maximunFolios;
	}
	/**
	 * @param maximunFolios the maximunFolios to set
	 */
	public final void setMaximunFolios(String maximunFolios) {
		this.maximunFolios = maximunFolios;
	}
	/**
	 * @return the parentIdsStr
	 */
	public final String getParentIdsStr() {
		return parentIdsStr;
	}
	/**
	 * @param parentIdsStr the parentIdsStr to set
	 */
	public final void setParentIdsStr(String parentIdsStr) {
		this.parentIdsStr = parentIdsStr;
	}
	/**
	 * @return the isSavingMerged
	 */
	public final Long getIsSavingMerged() {
		return isSavingMerged;
	}
	/**
	 * @param isSavingMerged the isSavingMerged to set
	 */
	public final void setIsSavingMerged(Long isSavingMerged) {
		this.isSavingMerged = isSavingMerged;
	}
	/**
	 * @return the isMerging
	 */
	public final Boolean getIsMerging() {
		return isMerging;
	}
	/**
	 * @param isMerging the isMerging to set
	 */
	public final void setIsMerging(Boolean isMerging) {
		this.isMerging = isMerging;
	}
	/**
	 * @return the manuscriptType
	 */
	public final String getManuscriptType() {
		return manuscriptType;
	}
	/**
	 * @param manuscriptType the manuscriptType to set
	 */
	public final void setManuscriptType(String manuscriptType) {
		this.manuscriptType = manuscriptType;
	}
	/**
	 * @return the recordStatus
	 */
	public final String getRecordStatus() {
		return recordStatus;
	}
	/**
	 * @param recordStatus the recordStatus to set
	 */
	public final void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	/**
	 * @return the presentFrame
	 */
	public final boolean getPresentFrame() {
		return presentFrame;
	}
	/**
	 * @param presentFrame the presentFrame to set
	 */
	public final void setPresentFrame(boolean presentFrame) {
		this.presentFrame = presentFrame;
	}
	/**
	 * @return the activewflProcessId
	 */
	public final Long getActivewflProcessId() {
		return activewflProcessId;
	}
	/**
	 * @param activewflProcessId the activewflProcessId to set
	 */
	public final void setActivewflProcessId(Long activewflProcessId) {
		this.activewflProcessId = activewflProcessId;
	}
	/**
	 * @return the wflProcessOwner
	 */
	public final String getWflProcessOwner() {
		return wflProcessOwner;
	}
	/**
	 * @param wflProcessOwner the wflProcessOwner to set
	 */
	public final void setWflProcessOwner(String wflProcessOwner) {
		this.wflProcessOwner = wflProcessOwner;
	}
	/**
	 * @return the digitizerId
	 */
	public final Long getDigitizerId() {
		return digitizerId;
	}
	/**
	 * @param digitizerId the digitizerId to set
	 */
	public final void setDigitizerId(Long digitizerId) {
		this.digitizerId = digitizerId;
	}
	/**
	 * @return the digitiserVO
	 */
	public final EmployeeMasterVO getDigitiserVO() {
		return digitiserVO;
	}
	/**
	 * @param digitiserVO the digitiserVO to set
	 */
	public final void setDigitiserVO(EmployeeMasterVO digitiserVO) {
		this.digitiserVO = digitiserVO;
	}
	/**
	 * @return the isUnderWfl
	 */
	public final Short getIsUnderWfl() {
		return isUnderWfl;
	}
	/**
	 * @param isUnderWfl the isUnderWfl to set
	 */
	public final void setIsUnderWfl(Short isUnderWfl) {
		this.isUnderWfl = isUnderWfl;
	}
	/**
	 * @return the documentPublicationStatus
	 */
	public final String getDocumentPublicationStatus() {
		return documentPublicationStatus;
	}
	/**
	 * @param documentPublicationStatus the documentPublicationStatus to set
	 */
	public final void setDocumentPublicationStatus(String documentPublicationStatus) {
		this.documentPublicationStatus = documentPublicationStatus;
	}
	/**
	 * @return the sourceOfCatalogue
	 */
	public final String getSourceOfCatalogue() {
		return sourceOfCatalogue;
	}
	/**
	 * @param sourceOfCatalogue the sourceOfCatalogue to set
	 */
	public final void setSourceOfCatalogue(String sourceOfCatalogue) {
		this.sourceOfCatalogue = sourceOfCatalogue;
	}
	/**
	 * @return the documentationOfManuscript
	 */
	public final String getDocumentationOfManuscript() {
		return documentationOfManuscript;
	}
	/**
	 * @param documentationOfManuscript the documentationOfManuscript to set
	 */
	public final void setDocumentationOfManuscript(String documentationOfManuscript) {
		this.documentationOfManuscript = documentationOfManuscript;
	}
	/**
	 * @return the conditionOfManuscript
	 */
	public final String getConditionOfManuscript() {
		return conditionOfManuscript;
	}
	/**
	 * @param conditionOfManuscript the conditionOfManuscript to set
	 */
	public final void setConditionOfManuscript(String conditionOfManuscript) {
		this.conditionOfManuscript = conditionOfManuscript;
	}
	/**
	 * @return the digitizedBy
	 */
	public final String getDigitizedBy() {
		return digitizedBy;
	}
	/**
	 * @param digitizedBy the digitizedBy to set
	 */
	public final void setDigitizedBy(String digitizedBy) {
		this.digitizedBy = digitizedBy;
	}
	/**
	 * @return the catalogueDetails
	 */
	public final String getCatalogueDetails() {
		return catalogueDetails;
	}
	/**
	 * @param catalogueDetails the catalogueDetails to set
	 */
	public final void setCatalogueDetails(String catalogueDetails) {
		this.catalogueDetails = catalogueDetails;
	}
	/**
	 * @return the totalNumberOfFolios
	 */
	public final Long getTotalNumberOfFolios() {
		return totalNumberOfFolios;
	}
	/**
	 * @param totalNumberOfFolios the totalNumberOfFolios to set
	 */
	public final void setTotalNumberOfFolios(Long totalNumberOfFolios) {
		this.totalNumberOfFolios = totalNumberOfFolios;
	}
	/**
	 * @return the totalNumberOfMaps
	 */
	public final Long getTotalNumberOfMaps() {
		return totalNumberOfMaps;
	}
	/**
	 * @param totalNumberOfMaps the totalNumberOfMaps to set
	 */
	public final void setTotalNumberOfMaps(Long totalNumberOfMaps) {
		this.totalNumberOfMaps = totalNumberOfMaps;
	}
	/**
	 * @return the catalogueNumber
	 */
	public final String getCatalogueNumber() {
		return catalogueNumber;
	}
	/**
	 * @param catalogueNumber the catalogueNumber to set
	 */
	public final void setCatalogueNumber(String catalogueNumber) {
		this.catalogueNumber = catalogueNumber;
	}
	/**
	 * @return the subCommentatorVO
	 */
	public final AuthorVO getSubCommentatorVO() {
		return subCommentatorVO;
	}
	/**
	 * @param subCommentatorVO the subCommentatorVO to set
	 */
	public final void setSubCommentatorVO(AuthorVO subCommentatorVO) {
		this.subCommentatorVO = subCommentatorVO;
	}
	/**
	 * @return the parentName
	 */
	public final String getParentName() {
		return parentName;
	}
	/**
	 * @param parentName the parentName to set
	 */
	public final void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * @return the parentFKId
	 */
	public final Long getParentFKId() {
		return parentFKId;
	}
	/**
	 * @param parentFKId the parentFKId to set
	 */
	public final void setParentFKId(Long parentFKId) {
		this.parentFKId = parentFKId;
	}
	/**
	 * @return the regionalName
	 */
	public final String getRegionalName() {
		return regionalName;
	}
	/**
	 * @param regionalName the regionalName to set
	 */
	public final void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}
	/**
	 * @return the diacriticName
	 */
	public final String getDiacriticName() {
		return diacriticName;
	}
	/**
	 * @param diacriticName the diacriticName to set
	 */
	public final void setDiacriticName(String diacriticName) {
		this.diacriticName = diacriticName;
	}
	/**
	 * @return the anyOtherDetails
	 */
	public final String getAnyOtherDetails() {
		return anyOtherDetails;
	}
	/**
	 * @param anyOtherDetails the anyOtherDetails to set
	 */
	public final void setAnyOtherDetails(String anyOtherDetails) {
		this.anyOtherDetails = anyOtherDetails;
	}
	/**
	 * @return the tableOfContents
	 */
	public final String getTableOfContents() {
		return tableOfContents;
	}
	/**
	 * @param tableOfContents the tableOfContents to set
	 */
	public final void setTableOfContents(String tableOfContents) {
		this.tableOfContents = tableOfContents;
	}
	
	/**
	 * @return the authorFKId
	 */
	public final Long getAuthorFKId() {
		return authorFKId;
	}
	/**
	 * @param authorFKId the authorFKId to set
	 */
	public final void setAuthorFKId(Long authorFKId) {
		this.authorFKId = authorFKId;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the typeOfWork
	 */
	public final String getTypeOfWork() {
		return typeOfWork;
	}
	/**
	 * @param typeOfWork the typeOfWork to set
	 */
	public final void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}
	/**
	 * @return the summary
	 */
	public final String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public final void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the uniquenessOfWork
	 */
	public final String getUniquenessOfWork() {
		return uniquenessOfWork;
	}
	/**
	 * @param uniquenessOfWork the uniquenessOfWork to set
	 */
	public final void setUniquenessOfWork(String uniquenessOfWork) {
		this.uniquenessOfWork = uniquenessOfWork;
	}
	/**
	 * @return the contributionToAyurveda
	 */
	public final String getContributionToAyurveda() {
		return contributionToAyurveda;
	}
	/**
	 * @param contributionToAyurveda the contributionToAyurveda to set
	 */
	public final void setContributionToAyurveda(String contributionToAyurveda) {
		this.contributionToAyurveda = contributionToAyurveda;
	}
	/**
	 * @return the remarks
	 */
	public final String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public final void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the authorVO
	 */
	public final AuthorVO getAuthorVO() {
		return authorVO;
	}
	/**
	 * @param authorVO the authorVO to set
	 */
	public final void setAuthorVO(AuthorVO authorVO) {
		this.authorVO = authorVO;
	}
	/**
	 * @return the organisationVO
	 */
	public final OrganisationVO getOrganisationVO() {
		return organisationVO;
	}
	/**
	 * @param organisationVO the organisationVO to set
	 */
	public final void setOrganisationVO(OrganisationVO organisationVO) {
		this.organisationVO = organisationVO;
	}
	/**
	 * @return the materialVO
	 */
	public final MaterialVO getMaterialVO() {
		return materialVO;
	}
	/**
	 * @param materialVO the materialVO to set
	 */
	public final void setMaterialVO(MaterialVO materialVO) {
		this.materialVO = materialVO;
	}
	public Long getLanguageFkId() {
		return languageFkId;
	}
	public void setLanguageFkId(Long languageFkId) {
		this.languageFkId = languageFkId;
	}
	public Long getMaterialFkId() {
		return materialFkId;
	}
	public void setMaterialFkId(Long materialFkId) {
		this.materialFkId = materialFkId;
	}
	
	public PublisherVO getPublisherVO() {
		return publisherVO;
	}
	public void setPublisherVO(PublisherVO publisherVO) {
		this.publisherVO = publisherVO;
	}
	public PublicationVO getPublicationVO() {
		return publicationVO;
	}
	public void setPublicationVO(PublicationVO publicationVO) {
		this.publicationVO = publicationVO;
	}
	public Long getPublicationFKId() {
		return publicationFKId;
	}
	public void setPublicationFkId(Long publicationFKId) {
		this.publicationFKId = publicationFKId;
	}
	public Long getScriptFkId() {
		return scriptFkId;
	}
	public void setScriptFkId(Long scriptFkId) {
		this.scriptFkId = scriptFkId;
	}
	
	
	@Override
	public String getTableName() {
		String className ="com.indven.omds.vo.DigitalManuscriptVO";		
		return className;
	}
	@Override
	public Map<String, String> getActionNames() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME , "getManuscriptById.action");
		returnMap.put(IndvenApplicationConstants.DELETE_ACTION_NAME , "deleteManuscriptaction.action");
		
		return returnMap;
	}
	@Override
	public Map<String, String> getLabelDisplayMap() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put("Manuscript Name" , "name");
		returnMap.put("Author Name" , "authorName");
		
		return returnMap;
	}
	@Override
	public Map<String, String> beanListForFkId() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put("authorName" , "AuthorBean");
		
		return returnMap;
	}
	@Override
	public Map<String, String> columnName() {
		Map<String, String> returnMap = new HashMap<String, String>();		
		returnMap.put("authorName" , "authorFKId");
		
		return returnMap;
	}
	@Override
	public Map<String, String> beanVarName() {
		Map<String, String> returnMap = new HashMap<String, String>();		
		returnMap.put("authorName" , "name");		
		return returnMap;
	}
	public Long getCategoryFkId() {
		return categoryFkId;
	}
	public void setCategoryFkId(Long categoryFkId) {
		this.categoryFkId = categoryFkId;
	}
	public Long getOrganisationFkId() {
		return organisationFkId;
	}
	public void setOrganisationFkId(Long organisationFkId) {
		this.organisationFkId = organisationFkId;
	}
	public Long getSpecificCategoryFkId() {
		return specificCategoryFkId;
	}
	public void setSpecificCategoryFkId(Long specificCategoryFkId) {
		this.specificCategoryFkId = specificCategoryFkId;
	}
	public Long getDocumentType() {
		return documentType;
	}
	public void setDocumentType(Long documentType) {
		this.documentType = documentType;
	}
	public String getManuscriptId() {
		return manuscriptId;
	}
	public void setManuscriptId(String manuscriptId) {
		this.manuscriptId = manuscriptId;
	}
	public AuthorVO getScribeVO() {
		return scribeVO;
	}
	public void setScribeVO(AuthorVO scribeVO) {
		this.scribeVO = scribeVO;
	}
	public Long getScribeFkId() {
		return scribeFkId;
	}
	public void setScribeFkId(Long scribeFkId) {
		this.scribeFkId = scribeFkId;
	}
	public AuthorVO getCommentatorVO() {
		return commentatorVO;
	}
	public void setCommentatorVO(AuthorVO commentatorVO) {
		this.commentatorVO = commentatorVO;
	}
	public AuthorVO getTranslatorVO() {
		return translatorVO;
	}
	public void setTranslatorVO(AuthorVO translatorVO) {
		this.translatorVO = translatorVO;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public Long getNmmDetailsFkId() {
		return nmmDetailsFkId;
	}
	public void setNmmDetailsFkId(Long nmmDetailsFkId) {
		this.nmmDetailsFkId = nmmDetailsFkId;
	}
	public NMMDetailsVO getNmmDetailsVO() {
		return nmmDetailsVO;
	}
	public void setNmmDetailsVO(NMMDetailsVO nmmDetailsVO) {
		this.nmmDetailsVO = nmmDetailsVO;
	}
	public List<DigitalManuscriptFrameVO> getDigitalManuscriptFrameVOs() {
		return digitalManuscriptFrameVOs;
	}
	public void setDigitalManuscriptFrameVOs(
			List<DigitalManuscriptFrameVO> digitalManuscriptFrameVOs) {
		this.digitalManuscriptFrameVOs = digitalManuscriptFrameVOs;
	}
	public String getFilePathContainer() {
		return filePathContainer;
	}
	public void setFilePathContainer(String filePathContainer) {
		this.filePathContainer = filePathContainer;
	}
	public String getFileDiskPathContainer() {
		return fileDiskPathContainer;
	}
	public void setFileDiskPathContainer(String fileDiskPathContainer) {
		this.fileDiskPathContainer = fileDiskPathContainer;
	}
	/**
	 * @return the bundleMasterFkId
	 */
	public Long getBundleMasterFkId() {
		return bundleMasterFkId;
	}
	/**
	 * @param bundleMasterFkId the bundleMasterFkId to set
	 */
	public void setBundleMasterFkId(Long bundleMasterFkId) {
		this.bundleMasterFkId = bundleMasterFkId;
	}
	/**
	 * @return the tagList
	 */
	public List<TagMasterVO> getTagList() {
		return tagList;
	}
	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(List<TagMasterVO> tagList) {
		this.tagList = tagList;
	}
	/**
	 * @return the natureOfCollection
	 */
	public Short getNatureOfCollection() {
		return natureOfCollection;
	}
	/**
	 * @param natureOfCollection the natureOfCollection to set
	 */
	public void setNatureOfCollection(Short natureOfCollection) {
		this.natureOfCollection = natureOfCollection;
	}
	/**
	 * @return the isBound
	 */
	public Short getIsBound() {
		return isBound;
	}
	/**
	 * @param isBound the isBound to set
	 */
	public void setIsBound(Short isBound) {
		this.isBound = isBound;
	}
	public String getBeginningLine() {
		return beginningLine;
	}
	public void setBeginningLine(String beginningLine) {
		this.beginningLine = beginningLine;
	}
	public String getEndingLine() {
		return endingLine;
	}
	public void setEndingLine(String endingLine) {
		this.endingLine = endingLine;
	}
	public String getColophon() {
		return colophon;
	}
	public void setColophon(String colophon) {
		this.colophon = colophon;
	}
	public void setPublicationFKId(Long publicationFKId) {
		this.publicationFKId = publicationFKId;
	}
	/**
	 * @return the specificCategoryId
	 */
	public List<Long> getSpecificCategoryId() {
		return specificCategoryId;
	}
	/**
	 * @param specificCategoryId the specificCategoryId to set
	 */
	public void setSpecificCategoryId(List<Long> specificCategoryId) {
		this.specificCategoryId = specificCategoryId;
	}
	/**
	 * @return the languageVO
	 */
	public LanguageVO getLanguageVO() {
		return languageVO;
	}
	/**
	 * @param languageVO the languageVO to set
	 */
	public void setLanguageVO(LanguageVO languageVO) {
		this.languageVO = languageVO;
	}
	/**
	 * @return the scriptVO
	 */
	public ScriptVO getScriptVO() {
		return scriptVO;
	}
	/**
	 * @param scriptVO the scriptVO to set
	 */
	public void setScriptVO(ScriptVO scriptVO) {
		this.scriptVO = scriptVO;
	}
	/**
	 * @return the totalFrame
	 */
	public Integer getTotalFrame() {
		return totalFrame;
	}
	/**
	 * @param totalFrame the totalFrame to set
	 */
	public void setTotalFrame(Integer totalFrame) {
		this.totalFrame = totalFrame;
	}
	
}
