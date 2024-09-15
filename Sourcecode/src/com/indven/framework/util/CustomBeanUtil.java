/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	CustomBeanUtil.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	17th April 2013
 * Brief Description					:	This Class will be use to convert value object to Entity and vice-versa for a given entity
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.indven.framework.entity.BaseEntityBean;
import com.indven.portal.administration.vo.UserInfoVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * This Class will be use to convert value object to Entity and vice-versa for a given project
 * 
 * @author Saurabh
 * 
 * 
 * 
 */
public class CustomBeanUtil {

	public static Object voToEntity(Object valueObject, Object entity) {
		try {
			BeanUtils.copyProperties(entity, valueObject);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return entity;
	}

	public static Object entityToVO(Object entity, Object valueObject) {
		try {
			BeanUtils.copyProperties(valueObject, entity);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return valueObject;
	}
	
	public static BaseEntityBean setBaseValues(BaseEntityBean entity,Boolean isFromSession){
		

			String userName = "";
			
			if(isFromSession){
				Map<String, Object> session = ActionContext.getContext().getSession();
				UserInfoVO userInfoVO = (UserInfoVO) session.get(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
				userName = userInfoVO.getLoginName();
				
			}else{
				userName = IndvenApplicationConstants.SYSTEM_GENERATED_USER;
			}
			if (entity.getId() != null && entity.getId() > 0) {
				entity.setModifiedBy(userName);
				entity.setModifiedDate(new Date());
			} else {
				entity.setCreatedBy(userName);
				entity.setCreatedDate(new Date());
				entity.setModifiedBy(userName);
				entity.setModifiedDate(new Date());
			}
		return entity;
	}
	
	
	public static Date convertStringToDateWithTime(String strDate) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {
			date = df.parse(strDate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date convertStringToDate(String strDate) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			date = df.parse(strDate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Long getBarcodeIdFromScannedData(String barcodeScannedData) {
		Long barcodeId = Long.parseLong(barcodeScannedData.substring(8));
		
		return barcodeId;
	}
	
	public static String getPONoFromScannedData(String barcodeScannedData) {
		String poNumber = barcodeScannedData.substring(0,8);
		
		return poNumber;
	}
	
	public static String getLoggedInUserLoginName() {
		String userName = "";
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfoVO userInfoVO = (UserInfoVO) session.get(IndvenApplicationConstants.LOGGEDIN_USER_SESSION_DATA);
		userName = userInfoVO.getLoginName();
		
		return userName;
	}
	
	public static String getMessageDetails(String message , String status) {
		String cssStyle = "errorMessage";
		if(status.equals("success")){
			cssStyle = "welcome";
		}
		String htmlStr = "<ul class="+cssStyle+"><li><span>"+message+"</span></li></ul>";
		return htmlStr;
	}
	
	public static String formatImageName(String imageName) {
		String imageExt = imageName.split("\\.")[1];
		
		String naemBeforeExt = imageName.split("\\.")[0];
		String img = "";
		
		if(!(NumberUtils.isNumber(naemBeforeExt.substring(naemBeforeExt.length() - 1)))) {
			StringBuffer bfrStr = new StringBuffer(naemBeforeExt.substring(naemBeforeExt.length() - 1));
			
			String str1 = naemBeforeExt.substring(0, naemBeforeExt.length()-1);
			imageName = str1+"."+imageExt;
			
			img = convertString(imageName.split("\\.")[0] , (imageName.split("\\.")[0]).length());
			img = img+(bfrStr.toString());
			
		} else {
			img = convertString(imageName.split("\\.")[0] , (imageName.split("\\.")[0]).length());
		}
		
		return img+"."+imageExt;
	}
	
	private static String convertString(String str,int length) {
		String subStr1 = "";
		if(str.length() > 4) {
			subStr1 = str.substring(0, str.length()-length);
			length = 4;
		}
		String subStr =str.substring(str.length()-length);;
		
		StringBuffer srtP2 = new StringBuffer("");
		
		for(int i=str.length() ; i > 0; i--) {
			if(NumberUtils.isNumber(str)) {
				str = ("0000" + str).substring(str.length());
				i=0;
			} else {
				srtP2.append(str.charAt(0));
				str=str.substring(1);
			}
		}
		subStr = srtP2.toString() + str;
		System.out.println(subStr);
		return subStr;
	}
	
}
