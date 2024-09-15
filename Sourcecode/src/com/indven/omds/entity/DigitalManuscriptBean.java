/**
 * 
 */
package com.indven.omds.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptConditionType;
import com.indven.omds.util.ManuscriptDocumentationType;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.omds.util.ManuscriptWorkType;
import com.indven.omds.util.SourceOfCatalogueEnum;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_digital_manuscript")
public class DigitalManuscriptBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "manuscript_id", insertable = true, updatable = true , nullable = true)
	private String manuscriptId;
	
	@Column(name = "name", insertable = true, updatable = true , nullable = true)
	private String name;
	
	@Column(name = "regional_name", insertable = true, updatable = true , nullable = true)
	private String regionalName;
	
	@Column(name = "diacritical_name", insertable = true, updatable = true , nullable = true)
	private String diacriticName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type_of_work", nullable = true)
	private ManuscriptWorkType typeOfWork;
	
	@Column(name = "summary", insertable = true, updatable = true , nullable = true)
	private String summary;
	
	@Column(name = "table_of_contents", insertable = true, updatable = true , nullable = true)
	private String tableOfContents;
	
	@Column(name = "uniqueness_of_work", insertable = true, nullable = true)
	private String uniquenessOfWork;
	
	@Column(name = "contribution_to_ayurveda", insertable = true, updatable = true, nullable = true)
	private String contributionToAyurveda;
	
	@Column(name = "remarks", insertable = true, updatable = true, nullable = true)
	private String remarks;
	
	@Column(name = "any_other_details", insertable = true, updatable = true, nullable = true)
	private String anyOtherDetails;
	
	@OneToOne
	@JoinColumn(name = "materialfkid", nullable=true,insertable=false,updatable=false)
	private MaterialBean materialFkObj;
	
	@OneToOne
	@JoinColumn(name = "publicationfkid", nullable=true,insertable=false,updatable=false)
	private PublicationBean publicationFKObj;
	
	@Column(name = "publicationfkid", insertable = true, updatable = true )
	private Long publicationFKId;
	
	/*@ManyToOne
	@JoinColumn(name = "bundleMasterfkid", nullable=true,insertable=false,updatable=false)
	private BundleMasterBean bundleMasterFkObj;*/
	
	@Column(name = "bundleMasterfkid", insertable = true, updatable = true )
	private Long bundleMasterFkId;
	
	@Column(name = "organisationfkid", insertable = true, updatable = true, nullable = true)
	private Long organisationFkId;
	
	@ManyToOne
	@JoinColumn(name = "organisationfkid", nullable=true,insertable=false,updatable=false)
	private Organisation organisationFkObj;
	
	@Column(name = "authorfkid", insertable = true, updatable = true, nullable = true)
	private Long authorFKId;
	
	@ManyToOne
	@JoinColumn(name = "authorfkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean authorFkObj;
	
	@Column(name="scribefkid", nullable=true,insertable=true,updatable=true)
	private Long scribeFkId;
	
	@ManyToOne
	@JoinColumn(name = "scribefkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean scribeFkObj;
	
	@Column(name="commentatorfkid", nullable=true,insertable=true,updatable=true)
	private Long commentatorFkId;
	
	@ManyToOne
	@JoinColumn(name = "commentatorfkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean commentatorFkObj;
	
	@Column(name="subCommentatorfkid", nullable=true,insertable=true,updatable=true)
	private Long subCommentatorFkId;
	
	@ManyToOne
	@JoinColumn(name = "subCommentatorfkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean subCommentatorFkObj;
	
	@Column(name="translatorfkid", nullable=true,insertable=true,updatable=true)
	private Long translatorFkId;
	
	@ManyToOne
	@JoinColumn(name = "translatorfkid", nullable=true,insertable=false,updatable=false)
	private AuthorBean translatorFkObj;

	
	@Column(name = "materialfkid", insertable = true, updatable = true, nullable = true)
	private Long materialFkId;
	
	@Column(name = "isdeleted", insertable = true, updatable = true)
	private Short isDeleted;

	@Column(name = "languagefkid", insertable = true, updatable = true, nullable = true)
	private Long languageFkId;
	
	@ManyToOne
	@JoinColumn(name = "languagefkid", nullable=true,insertable=false,updatable=false)
	private LanguageBean languageFkObj;
	
	@Column(name = "scriptFkId", insertable = true, updatable = true, nullable = true)
	private Long scriptFkId;
	
	@ManyToOne
	@JoinColumn(name = "scriptFkId", nullable=true,insertable=false,updatable=false)
	private ScriptBean scriptFkObj;
	
	@Column(name = "categoryFkId", insertable = true, updatable = true, nullable = true)
	private Long categoryFkId;
	
	@ManyToOne
	@JoinColumn(name = "categoryFkId", nullable=true,insertable=false,updatable=false)
	private CategoryBean categoryFkObj;
	
/*	@Column(name = "specificCategoryFkId", insertable = true, updatable = true, nullable = true)
	private Long specificCategoryFkId;
	
	@ManyToOne
	@JoinColumn(name = "specificCategoryFkId", nullable=true,insertable=false,updatable=false)
	private SpecificCategoryBean specificCategoryFkObj;*/
	
	@Column(name = "documentType", insertable = true, updatable = true, nullable = true)
	private Long documentType;
	
	@Column(name = "parentfkid", insertable = true, updatable = true, nullable = true)
	private Long parentFKId;
	
	@ManyToOne
	@JoinColumn(name = "parentfkid", nullable=true,insertable=false,updatable=false)
	private DigitalManuscriptBean parentFkObj;
	
	@Column(name = "acc_no", insertable = true, updatable = true, nullable = true)
	private String accNumber;

	@Column(name = "nmmDetailsFkId", insertable = true, updatable = true, nullable = true)
	private Long nmmDetailsFkId;
	
	@OneToOne
	@JoinColumn(name = "nmmDetailsFkId", nullable=true,insertable=false,updatable=false)
	private NMMDetailsBean nmmDetailsFkObj;
	
	@OneToMany(mappedBy = "digitalManuscriptFkObj", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DigitalManuscriptFrame> digitalManuscriptFrames;

	@Column(name = "catalogue_no", insertable = true, updatable = true, nullable = true)
	private String catalogueNumber;

	@Column(name = "total_no_of_maps", insertable = true, updatable = true, nullable = true)
	private Long totalNumberOfMaps;

	@Column(name = "total_no_of_folios", insertable = true, updatable = true, nullable = true)
	private Long totalNumberOfFolios;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "condition_of_manuscript", nullable = true)
	private ManuscriptConditionType conditionOfManuscript;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "manuscripttype", nullable = true)
	private ManuscriptTypeEnum manuscriptType;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "recordstatus", nullable = true)
	private DocumentStatusEnum recordStatus;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "documentation_of_manuscript", nullable = true)
	private ManuscriptDocumentationType documentationOfManuscript;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "source_of_catalogue", nullable = true)
	private SourceOfCatalogueEnum sourceOfCatalogue;
	
	/*@Column(name = "digitized_by", insertable = true, updatable = true, nullable = true)
	private String digitizedBy;*/
	
	@Column(name = "isbound", insertable = true, updatable = true )
	private Short isBound;
	
	@Column(name = "nature_of_collection", insertable = true, updatable = true )
	private Short natureOfCollection;
	
	@Column(name = "beginning_line", insertable = true, updatable = true, nullable = true)
	private String beginningLine;
	
	@Column(name = "ending_line", insertable = true, updatable = true, nullable = true)
	private String endingLine;
	
	@Column(name = "colophon", insertable = true, updatable = true, nullable = true)
	private String colophon;
	
	@Column(name = "digitizerid", insertable = true, updatable = true, nullable = true)
	private Long digitizerId;
	
	@Transient
	private boolean presentFrame;
	
	@Column(name = "transLanguage", insertable = true, updatable = true, nullable = true)
	private String language;
	
	
	/*	*//**
	 * @return the bundleMasterFkObj
	 *//*
	public final BundleMasterBean getBundleMasterFkObj() {
		return bundleMasterFkObj;
	}

	*//**
	 * @param bundleMasterFkObj the bundleMasterFkObj to set
	 *//*
	public final void setBundleMasterFkObj(BundleMasterBean bundleMasterFkObj) {
		this.bundleMasterFkObj = bundleMasterFkObj;
	}*/

	/**
	 * @return the language
	 */
	public final String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public final void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the presentFrame
	 */
	public final boolean getPresentFrame() {
		return presentFrame;
	}

	/**
	 * @return the manuscriptType
	 */
	public final ManuscriptTypeEnum getManuscriptType() {
		return manuscriptType;
	}

	/**
	 * @param manuscriptType the manuscriptType to set
	 */
	public final void setManuscriptType(ManuscriptTypeEnum manuscriptType) {
		this.manuscriptType = manuscriptType;
	}

	/**
	 * @return the recordStatus
	 */
	public final DocumentStatusEnum getRecordStatus() {
		return recordStatus;
	}

	/**
	 * @param recordStatus the recordStatus to set
	 */
	public final void setRecordStatus(DocumentStatusEnum recordStatus) {
		this.recordStatus = recordStatus;
	}

	/**
	 * @param presentFrame the presentFrame to set
	 */
	public final void setPresentFrame(boolean presentFrame) {
		this.presentFrame = presentFrame;
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
	 * @return the bundleMasterFkId
	 */
	public final Long getBundleMasterFkId() {
		return bundleMasterFkId;
	}

	/**
	 * @param bundleMasterFkId the bundleMasterFkId to set
	 */
	public final void setBundleMasterFkId(Long bundleMasterFkId) {
		this.bundleMasterFkId = bundleMasterFkId;
	}

	/**
	 * @return the isBound
	 */
	public final Short getIsBound() {
		return isBound;
	}

	/**
	 * @param isBound the isBound to set
	 */
	public final void setIsBound(Short isBound) {
		this.isBound = isBound;
	}

	/**
	 * @return the natureOfCollection
	 */
	public final Short getNatureOfCollection() {
		return natureOfCollection;
	}

	/**
	 * @param natureOfCollection the natureOfCollection to set
	 */
	public final void setNatureOfCollection(Short natureOfCollection) {
		this.natureOfCollection = natureOfCollection;
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
	 * @return the conditionOfManuscript
	 */
	public final ManuscriptConditionType getConditionOfManuscript() {
		return conditionOfManuscript;
	}

	/**
	 * @param conditionOfManuscript the conditionOfManuscript to set
	 */
	public final void setConditionOfManuscript(
			ManuscriptConditionType conditionOfManuscript) {
		this.conditionOfManuscript = conditionOfManuscript;
	}

	/**
	 * @return the documentationOfManuscript
	 */
	public final ManuscriptDocumentationType getDocumentationOfManuscript() {
		return documentationOfManuscript;
	}

	/**
	 * @param documentationOfManuscript the documentationOfManuscript to set
	 */
	public final void setDocumentationOfManuscript(
			ManuscriptDocumentationType documentationOfManuscript) {
		this.documentationOfManuscript = documentationOfManuscript;
	}

	/**
	 * @return the sourceOfCatalogue
	 */
	public final SourceOfCatalogueEnum getSourceOfCatalogue() {
		return sourceOfCatalogue;
	}

	/**
	 * @param sourceOfCatalogue the sourceOfCatalogue to set
	 */
	public final void setSourceOfCatalogue(SourceOfCatalogueEnum sourceOfCatalogue) {
		this.sourceOfCatalogue = sourceOfCatalogue;
	}

//	/**
//	 * @return the digitizedBy
//	 */
//	public final String getDigitizedBy() {
//		return digitizedBy;
//	}
//
//	/**
//	 * @param digitizedBy the digitizedBy to set
//	 */
//	public final void setDigitizedBy(String digitizedBy) {
//		this.digitizedBy = digitizedBy;
//	}

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

	@Column(name = "cataloguedetails", insertable = true, updatable = true, nullable = true)
	private String catalogueDetails;

	/**
	 * @return the subCommentatorFkId
	 */
	public final Long getSubCommentatorFkId() {
		return subCommentatorFkId;
	}

	/**
	 * @param subCommentatorFkId the subCommentatorFkId to set
	 */
	public final void setSubCommentatorFkId(Long subCommentatorFkId) {
		this.subCommentatorFkId = subCommentatorFkId;
	}

	/**
	 * @return the subCommentatorFkObj
	 */
	public final AuthorBean getSubCommentatorFkObj() {
		return subCommentatorFkObj;
	}

	/**
	 * @param subCommentatorFkObj the subCommentatorFkObj to set
	 */
	public final void setSubCommentatorFkObj(AuthorBean subCommentatorFkObj) {
		this.subCommentatorFkObj = subCommentatorFkObj;
	}

	/**
	 * @return the parentFkObj
	 */
	public final DigitalManuscriptBean getParentFkObj() {
		return parentFkObj;
	}

	/**
	 * @param parentFkObj the parentFkObj to set
	 */
	public final void setParentFkObj(DigitalManuscriptBean parentFkObj) {
		this.parentFkObj = parentFkObj;
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
	 * @return the publicationFKObj
	 */
	public final PublicationBean getPublicationFKObj() {
		return publicationFKObj;
	}

	/**
	 * @param publicationFKObj the publicationFKObj to set
	 */
	public final void setPublicationFKObj(PublicationBean publicationFKObj) {
		this.publicationFKObj = publicationFKObj;
	}

	/**
	 * @return the publicationFKId
	 */
	public final Long getPublicationFKId() {
		return publicationFKId;
	}

	/**
	 * @param publicationFKId the publicationFKId to set
	 */
	public final void setPublicationFKId(Long publicationFKId) {
		this.publicationFKId = publicationFKId;
	}

	/**
	 * @return the isDeleted
	 */
	public final Short getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public final void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
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
	 * @return the materialFkId
	 */
	public final Long getMaterialFkId() {
		return materialFkId;
	}

	/**
	 * @param materialFkId the materialFkId to set
	 */
	public final void setMaterialFkId(Long materialFkId) {
		this.materialFkId = materialFkId;
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
	public final ManuscriptWorkType getTypeOfWork() {
		return typeOfWork;
	}

	/**
	 * @param typeOfWork the typeOfWork to set
	 */
	public final void setTypeOfWork(ManuscriptWorkType typeOfWork) {
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
	 * @return the authorFkObj
	 */
	public final AuthorBean getAuthorFkObj() {
		return authorFkObj;
	}

	/**
	 * @param authorFkObj the authorFkObj to set
	 */
	public final void setAuthorFkObj(AuthorBean authorFkObj) {
		this.authorFkObj = authorFkObj;
	}

	/**
	 * @return the organisationFkObj
	 */
	public final Organisation getOrganisationFkObj() {
		return organisationFkObj;
	}

	/**
	 * @param organisationFkObj the organisationFkObj to set
	 */
	public final void setOrganisationFkObj(Organisation organisationFkObj) {
		this.organisationFkObj = organisationFkObj;
	}

	/**
	 * @return the materialFkObj
	 */
	public final MaterialBean getMaterialFkObj() {
		return materialFkObj;
	}

	/**
	 * @param materialFkObj the materialFkObj to set
	 */
	public final void setMaterialFkObj(MaterialBean materialFkObj) {
		this.materialFkObj = materialFkObj;
	}

	public Long getLanguageFkId() {
		return languageFkId;
	}

	public void setLanguageFkId(Long languageFkId) {
		this.languageFkId = languageFkId;
	}

	public LanguageBean getLanguageFkObj() {
		return languageFkObj;
	}

	public void setLanguageFkObj(LanguageBean languageFkObj) {
		this.languageFkObj = languageFkObj;
	}

	public Long getScriptFkId() {
		return scriptFkId;
	}

	public void setScriptFkId(Long scriptFkId) {
		this.scriptFkId = scriptFkId;
	}

	public ScriptBean getScriptFkObj() {
		return scriptFkObj;
	}

	public void setScriptFkObj(ScriptBean scriptFkObj) {
		this.scriptFkObj = scriptFkObj;
	}

	public Long getCategoryFkId() {
		return categoryFkId;
	}

	public void setCategoryFkId(Long categoryFkId) {
		this.categoryFkId = categoryFkId;
	}

	public CategoryBean getCategoryFkObj() {
		return categoryFkObj;
	}

	public void setCategoryFkObj(CategoryBean categoryFkObj) {
		this.categoryFkObj = categoryFkObj;
	}

	public Long getOrganisationFkId() {
		return organisationFkId;
	}

	public void setOrganisationFkId(Long organisationFkId) {
		this.organisationFkId = organisationFkId;
	}

	/*public Long getSpecificCategoryFkId() {
		return specificCategoryFkId;
	}

	public void setSpecificCategoryFkId(Long specificCategoryFkId) {
		this.specificCategoryFkId = specificCategoryFkId;
	}

	public SpecificCategoryBean getSpecificCategoryFkObj() {
		return specificCategoryFkObj;
	}

	public void setSpecificCategoryFkObj(SpecificCategoryBean specificCategoryFkObj) {
		this.specificCategoryFkObj = specificCategoryFkObj;
	}*/

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

	public Long getScribeFkId() {
		return scribeFkId;
	}

	public void setScribeFkId(Long scribeFkId) {
		this.scribeFkId = scribeFkId;
	}

	public AuthorBean getScribeFkObj() {
		return scribeFkObj;
	}

	public void setScribeFkObj(AuthorBean scribeFkObj) {
		this.scribeFkObj = scribeFkObj;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public Long getCommentatorFkId() {
		return commentatorFkId;
	}

	public void setCommentatorFkId(Long commentatorFkId) {
		this.commentatorFkId = commentatorFkId;
	}

	public AuthorBean getCommentatorFkObj() {
		return commentatorFkObj;
	}

	public void setCommentatorFkObj(AuthorBean commentatorFkObj) {
		this.commentatorFkObj = commentatorFkObj;
	}

	public Long getTranslatorFkId() {
		return translatorFkId;
	}

	public void setTranslatorFkId(Long translatorFkId) {
		this.translatorFkId = translatorFkId;
	}

	public AuthorBean getTranslatorFkObj() {
		return translatorFkObj;
	}

	public void setTranslatorFkObj(AuthorBean translatorFkObj) {
		this.translatorFkObj = translatorFkObj;
	}

	public Long getNmmDetailsFkId() {
		return nmmDetailsFkId;
	}

	public void setNmmDetailsFkId(Long nmmDetailsFkId) {
		this.nmmDetailsFkId = nmmDetailsFkId;
	}

	public NMMDetailsBean getNmmDetailsFkObj() {
		return nmmDetailsFkObj;
	}

	public void setNmmDetailsFkObj(NMMDetailsBean nmmDetailsFkObj) {
		this.nmmDetailsFkObj = nmmDetailsFkObj;
	}

	public List<DigitalManuscriptFrame> getDigitalManuscriptFrames() {
		return digitalManuscriptFrames;
	}

	public void setDigitalManuscriptFrames(List<DigitalManuscriptFrame> digitalManuscriptFrames) {
		this.digitalManuscriptFrames = digitalManuscriptFrames;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
