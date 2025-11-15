/**
 * 
 */
package com.indven.omds.vo;

/**
 * @author Deba
 *
 */
public class ArticleDetailsVO {
	private Long id;
	private int type;
	private String website;
	private String issnNo;
	private String journalOthrDtls;
	private String abstractOfArticle;

	private String nameOfMagazine;
	private String nameOfEditor;
	private String yearOfPublication;
	private String abstractOfMagazine;
	private String address;
	private String noOfPages;
	private String price;
	private String magazineIssnNo;
	
	
	public String getMagazineIssnNo() {
		return magazineIssnNo;
	}

	public void setMagazineIssnNo(String magazineIssnNo) {
		this.magazineIssnNo = magazineIssnNo;
	}

	private int isPrinted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIssnNo() {
		return issnNo;
	}

	public void setIssnNo(String issnNo) {
		this.issnNo = issnNo;
	}

	public String getJournalOthrDtls() {
		return journalOthrDtls;
	}

	public void setJournalOthrDtls(String journalOthrDtls) {
		this.journalOthrDtls = journalOthrDtls;
	}

	public String getAbstractOfArticle() {
		return abstractOfArticle;
	}

	public void setAbstractOfArticle(String abstractOfArticle) {
		this.abstractOfArticle = abstractOfArticle;
	}

	public String getNameOfMagazine() {
		return nameOfMagazine;
	}

	public void setNameOfMagazine(String nameOfMagazine) {
		this.nameOfMagazine = nameOfMagazine;
	}

	public String getNameOfEditor() {
		return nameOfEditor;
	}

	public void setNameOfEditor(String nameOfEditor) {
		this.nameOfEditor = nameOfEditor;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(String yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getAbstractOfMagazine() {
		return abstractOfMagazine;
	}

	public void setAbstractOfMagazine(String abstractOfMagazine) {
		this.abstractOfMagazine = abstractOfMagazine;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getIsPrinted() {
		return isPrinted;
	}

	public void setIsPrinted(int isPrinted) {
		this.isPrinted = isPrinted;
	}
	
}
