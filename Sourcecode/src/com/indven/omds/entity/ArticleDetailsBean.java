/**
 * 
 */
package com.indven.omds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

/**
 * @author Deba
 *
 */
@Entity
@Table(name = "mdr_article_details")
public class ArticleDetailsBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "type", insertable = true, updatable = true , nullable = true)
	private int type;
	
	@Column(name = "website", insertable = true, updatable = true , nullable = true)
	private String website;
	
	@Column(name = "issnNo", insertable = true, updatable = true , nullable = true)
	private String issnNo;
	
	@Column(name = "journalOthrDtls", insertable = true, updatable = true , nullable = true)
	private String journalOthrDtls;
	
	@Column(name = "abstractOfArticle", insertable = true, updatable = true , nullable = true)
	private String abstractOfArticle;

	@Column(name = "nameOfMagazine", insertable = true, updatable = true , nullable = true)
	private String nameOfMagazine;
	
	@Column(name = "nameOfEditor", insertable = true, updatable = true , nullable = true)
	private String nameOfEditor;
	
	@Column(name = "yearOfPublication", insertable = true, updatable = true , nullable = true)
	private String yearOfPublication;
	
	@Column(name = "abstractOfMagazine", insertable = true, updatable = true , nullable = true)
	private String abstractOfMagazine;
	
	@Column(name = "address", insertable = true, updatable = true , nullable = true)
	private String address;
	
	@Column(name = "noOfPages", insertable = true, updatable = true , nullable = true)
	private String noOfPages;
	
	@Column(name = "price", insertable = true, updatable = true , nullable = true)
	private String price;
	
	@Column(name = "isPrinted", insertable = true, updatable = true , nullable = true)
	private int isPrinted;
	
	@Column(name = "magazineIssnNo", insertable = true, updatable = true , nullable = true)
	private String magazineIssnNo;
    
	
	public String getMagazineIssnNo() {
		return magazineIssnNo;
	}

	public void setMagazineIssnNo(String magazineIssnNo) {
		this.magazineIssnNo = magazineIssnNo;
	}

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
