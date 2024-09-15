/**
 * 
 */
package com.indven.omds.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Deba Prasad
 *
 */
@Entity
@Table(name = "omds_person")
public class AuthorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "name", insertable = true, updatable = true , nullable = true)
	private String name;
	
	@Column(name = "regional_name", insertable = true, updatable = true , nullable = true)
	private String regionalName;
	
	@Column(name = "diacritical_name", insertable = true, updatable = true , nullable = true)
	private String diacriticName;
	
	@Column(name = "life_history", insertable = true, updatable = true)
	private String lifeHistory ;
	
	@Column(name = "period", insertable = true, updatable = true)
	private String period;
	
	@Column(name = "type", insertable = true, updatable = true)
	private Short type;

	/*@OneToMany(mappedBy = "authorFkObj", cascade = CascadeType.ALL)
	private List<PublicationBean> publisherList;*/
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
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
	 * @return the lifeHistory
	 */
	public final String getLifeHistory() {
		return lifeHistory;
	}

	/**
	 * @param lifeHistory the lifeHistory to set
	 */
	public final void setLifeHistory(String lifeHistory) {
		this.lifeHistory = lifeHistory;
	}

	/**
	 * @return the period
	 */
	public final String getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public final void setPeriod(String period) {
		this.period = period;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	
}
