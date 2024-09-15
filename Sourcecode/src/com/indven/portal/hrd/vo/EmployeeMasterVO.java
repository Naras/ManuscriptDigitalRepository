/**
 * 
 */
package com.indven.portal.hrd.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.search.vo.GenericSearch;

/**
 * @author Saurabh
 *
 */
public class EmployeeMasterVO extends IndvenResultVO implements GenericSearch{

	private Long id;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String email;
	private String phoneNumber;
	private Short gender;
	private String address1;

	private Short userType;
	
	private String password;
	
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private Integer rowVersion;
	private Long roleMasterFkId;
	
	private Long departmentMasterFkId;
	
	private List<Long> rolesList;
	
	/**
	 * @return the rolesList
	 */
	public final List<Long> getRolesList() {
		return rolesList;
	}
	/**
	 * @param rolesList the rolesList to set
	 */
	public final void setRolesList(List<Long> rolesList) {
		this.rolesList = rolesList;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the gender
	 */
	public Short getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Short gender) {
		this.gender = gender;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Short getUserType() {
		return userType;
	}
	public void setUserType(Short userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Integer getRowVersion() {
		return rowVersion;
	}
	/**
	 * @return the roleMasterFkId
	 */
	public final Long getRoleMasterFkId() {
		return roleMasterFkId;
	}
	/**
	 * @param roleMasterFkId the roleMasterFkId to set
	 */
	public final void setRoleMasterFkId(Long roleMasterFkId) {
		this.roleMasterFkId = roleMasterFkId;
	}
	public void setRowVersion(Integer rowVersion) {
		this.rowVersion = rowVersion;
	}
	

	public Long getDepartmentMasterFkId() {
		return departmentMasterFkId;
	}
	public void setDepartmentMasterFkId(Long departmentMasterFkId) {
		this.departmentMasterFkId = departmentMasterFkId;
	}
	
	@Override
	public final String getTableName() {
		String className ="com.indven.portal.hrd.vo.EmployeeMasterVO";
		
		return className;
	}
	@Override
	public Map<String, String> getLabelDisplayMap() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put("FirstName" , "firstName");
		returnMap.put("LastName" , "lastName");
		returnMap.put("Email" , "email");
		returnMap.put("Telephone" , "phoneNumber");
		
		return returnMap;
	}
	@Override
	public Map<String, String> getActionNames() {
		Map<String, String> returnMap = new HashMap<String, String>();
		
		returnMap.put(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME , "displayuseraction.action");
		returnMap.put(IndvenApplicationConstants.DELETE_ACTION_NAME , "deleteuseraction.action");
		
		return returnMap;
	}
	
	public Map<String, String> beanListForFkId() {
		return null;
	}
	
	public Map<String, String> columnName() {
		return null;
	}
	
	public Map<String, String> beanVarName() {
		return null;
	}
}
