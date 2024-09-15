/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	UserLoginDetails.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	10th April 2013
 * Brief Description					:	
 * Author								: 	Saurabh
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.portal.administration.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indven.framework.entity.BaseEntityBean;
import com.indven.framework.enums.UserStatusEnum;
import com.indven.framework.enums.UserTypeEnum;

/**
 * This class gives all the details of user login.
 * 
 * @author Saurabh
 * 
 */

@Entity
@Table(name = "omds_userlogindetails")
public class UserLoginDetailsBean extends BaseEntityBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name = "loginid", nullable = false)
	private String loginId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "refrencefkid")
	private Long refrenceFkId;
	
	@Column(name = "type", nullable = false)
	private Short type;
	
	@Column(name = "status", nullable = false)
	private Short status;
	
	@Column(name = "resetpasswordid")
	private String resetPasswordId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "genratedresetpassworddate")
	private Date genratedResetPasswordDate;

	@Column(name = "isdeleted")
	private int isDeleted;


	/**
	 * @return the loginId
	 */
	public final String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public final void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the refrenceFkId
	 */
	public final Long getRefrenceFkId() {
		return refrenceFkId;
	}

	/**
	 * @param refrenceFkId the refrenceFkId to set
	 */
	public final void setRefrenceFkId(Long refrenceFkId) {
		this.refrenceFkId = refrenceFkId;
	}

	/**
	 * @return the type
	 */
	public final UserTypeEnum getType() {
		return UserTypeEnum.fromValue(type);
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(Short type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public final UserStatusEnum getStatus() {
		return UserStatusEnum.fromValue(status);
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @return the resetPasswordId
	 */
	public final String getResetPasswordId() {
		return resetPasswordId;
	}

	/**
	 * @param resetPasswordId the resetPasswordId to set
	 */
	public final void setResetPasswordId(String resetPasswordId) {
		this.resetPasswordId = resetPasswordId;
	}

	/**
	 * @return the genratedResetPasswordDate
	 */
	public final Date getGenratedResetPasswordDate() {
		return genratedResetPasswordDate;
	}

	/**
	 * @param genratedResetPasswordDate the genratedResetPasswordDate to set
	 */
	public final void setGenratedResetPasswordDate(Date genratedResetPasswordDate) {
		this.genratedResetPasswordDate = genratedResetPasswordDate;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
        public String toString() {
            return ("login id "+ loginId + " pw " + password + " status " + status + " RefrenceFkid " + refrenceFkId); 
        }
	
}
