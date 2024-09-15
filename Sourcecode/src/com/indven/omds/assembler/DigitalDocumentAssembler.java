package com.indven.omds.assembler;

import org.json.JSONException;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.omds.entity.DigitalDocumentBean;
import com.indven.omds.entity.DigitalDocumentDetailsBean;
import com.indven.omds.vo.DigitalDocumentDetailsVO;
import com.indven.omds.vo.DigitalDocumentVO;

/**
 * Assembler for digital document
 * This stores online derived work
 * @author Neel Borooah
 *
 */
public class DigitalDocumentAssembler {
	
	public static DigitalDocumentVO convertEntityToVO(DigitalDocumentBean bean) throws JSONException {
		
		DigitalDocumentVO vo = new DigitalDocumentVO();
		
		if(bean != null && bean.getId() != null && bean.getId() > 0) {
			vo = (DigitalDocumentVO) CustomBeanUtil.entityToVO(bean, vo);
			
			if(bean.getDocumentDetailsBean() != null && bean.getDocumentDetailsBean().getId() != null 
					&& bean.getDocumentDetailsBean().getId() > 0) {
				DigitalDocumentDetailsVO detailsVO = new DigitalDocumentDetailsVO();
				
				detailsVO = (DigitalDocumentDetailsVO) CustomBeanUtil.entityToVO(bean.getDocumentDetailsBean(), detailsVO);
				
				vo.setDetailsVO(detailsVO);
			}
			
			if(bean.getDigitalManuscriptBean() != null) {
				vo.setDigitalManuscriptVO(DigitalManuscriptAssembler.convertEntityToVo(bean.getDigitalManuscriptBean()));
			}
		}
		
		return vo;
		
	}

	public static DigitalDocumentBean convertVOToEntity(DigitalDocumentVO vo) {
		
		DigitalDocumentBean bean = new DigitalDocumentBean();
		
		if(vo != null) {
			bean = (DigitalDocumentBean) CustomBeanUtil.voToEntity(vo, bean);
			if(bean.getId() <= 0) {
				bean.setId(null);
			}
			if(bean.getDigitalManuscriptFkId() != null && bean.getDigitalManuscriptFkId() <= 0) {
				bean.setDigitalManuscriptFkId(null);
			}
			if(bean.getDigitalManuscriptFrameFkId() != null && bean.getDigitalManuscriptFrameFkId() <= 0) {
				bean.setDigitalManuscriptFrameFkId(null);
			}
			if(bean.getLanguageFkId() != null && bean.getLanguageFkId() <= 0) {
				bean.setLanguageFkId(null);
			}
			if(bean.getScriptFkId() != null && bean.getScriptFkId() <= 0) {
				bean.setScriptFkId(null);
			}
			if(vo.getDigitalManuscriptVO() != null 
					&& vo.getDigitalManuscriptVO().getId() != null && vo.getDigitalManuscriptVO().getId() > 0) {
				bean.setDigitalManuscriptFkId(vo.getDigitalManuscriptVO().getId());
			}
			if(vo.getDetailsVO() != null) {
				DigitalDocumentDetailsBean detailsBean = new DigitalDocumentDetailsBean();

				detailsBean = (DigitalDocumentDetailsBean) CustomBeanUtil.voToEntity(vo.getDetailsVO(), detailsBean);
				if(detailsBean.getId() != null && detailsBean.getId() <= 0) {
					detailsBean.setId(null);
				}
				if(detailsBean.getDigitalDocumentFkId() != null && detailsBean.getDigitalDocumentFkId() <= 0) {
					detailsBean.setDigitalDocumentFkId(null);
				}
				if(detailsBean.getUserRoleDetailsFkId() != null && detailsBean.getUserRoleDetailsFkId() <= 0) {
					detailsBean.setUserRoleDetailsFkId(null);
				}
				if(detailsBean.getVersion() != null && detailsBean.getVersion() <= 0) {
					detailsBean.setVersion(null);
				}
				detailsBean.setDigitalDocumentBean(bean);
				
				bean.setDocumentDetailsBean(detailsBean);
			}
		}
		
		return bean;
	}
}
