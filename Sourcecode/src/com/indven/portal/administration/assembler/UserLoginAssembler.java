package com.indven.portal.administration.assembler;

import java.util.ArrayList;
import java.util.List;

import com.indven.portal.administration.entity.UserLoginDetailsBean;
import com.indven.portal.administration.vo.UserInfoVO;

public class UserLoginAssembler {
	/**
	 * @author Neel Borooah
	 * 
	 */
	
	
	public static UserInfoVO convertEntityToVo(UserLoginDetailsBean userLoginDetailsBean) {
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setId(userLoginDetailsBean.getId());
		userInfoVO.setLoginName(userLoginDetailsBean.getLoginId());
		userInfoVO.setPassWord(userLoginDetailsBean.getPassword());
		userInfoVO.setReferenceFkId(userLoginDetailsBean.getRefrenceFkId());
		return userInfoVO;
	}	
	
	public static UserLoginDetailsBean convertVoToEntity(UserInfoVO userInfoVO) {
		UserLoginDetailsBean userLoginDetailsBean = new UserLoginDetailsBean();
		userLoginDetailsBean.setId(userInfoVO.getId());
		userLoginDetailsBean.setLoginId(userInfoVO.getLoginName());
		userLoginDetailsBean.setPassword(userInfoVO.getPassWord());
		userLoginDetailsBean.setRefrenceFkId(userInfoVO.getReferenceFkId());
		return userLoginDetailsBean;
	}
	
	public static List<UserLoginDetailsBean> convertVosToEntities(List<UserInfoVO> userInfoVOs) {
		List<UserLoginDetailsBean> userLoginDetailsBeans = new ArrayList<UserLoginDetailsBean>();
		for (UserInfoVO userInfoVO : userInfoVOs) {
			userLoginDetailsBeans.add(convertVoToEntity(userInfoVO));
		}
		return userLoginDetailsBeans;
	}
	
	public static List<UserInfoVO> convertEntitiesToVos(List<UserLoginDetailsBean> userLoginDetailsBeans) {
		List<UserInfoVO> userInfoVOs = new ArrayList<UserInfoVO>();

		for (UserLoginDetailsBean userLoginDetailsBean : userLoginDetailsBeans) {
			userInfoVOs.add(convertEntityToVo(userLoginDetailsBean));
		}

		return userInfoVOs;
	}
}
