/**
 * 
 */
package com.indven.omds.vo;


/**
 * @author Deba Prasad
 *
 */
public class AuthorVO {

	private Long id;
	private String name;
	private String lifeHistory ;
	private String period;
	private String periodEra;
	private String regionalName;
	private String diacriticName;
	private Short type;
	
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
	public String getPeriodEra() {
		return periodEra;
	}
	public void setPeriodEra(String periodEra) {
		this.periodEra = periodEra;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	
	
}
