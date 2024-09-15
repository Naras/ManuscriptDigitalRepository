package com.indven.omds.assembler;

import com.indven.framework.util.CustomBeanUtil;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.omds.entity.AuthorBean;
import com.indven.omds.entity.PublicationBean;
import com.indven.omds.entity.PublisherBean;
import com.indven.omds.vo.AuthorVO;
import com.indven.omds.vo.PublicationVO;
import com.indven.omds.vo.PublisherVO;

public class PublicationAssembler {

	public static PublicationVO convertEntityToVO(PublicationBean bean) {
		PublicationVO vo = new PublicationVO();
		
		vo = (PublicationVO) CustomBeanUtil.entityToVO(bean, vo);
		if(bean.getAuthorFkObj() != null)
			vo.setAuthorVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getAuthorFkObj(), new AuthorVO()));
		if(bean.getPublisherFkObj() != null)
			vo.setPublisherVO((PublisherVO) CustomBeanUtil.entityToVO(bean.getPublisherFkObj(), new PublisherVO()));
		if(bean.getEditorFkObj() != null && bean.getEditorFkObj().getId() != null && bean.getEditorFkObj().getId() > 0) {
			vo.setEditorVO((AuthorVO) CustomBeanUtil.entityToVO(bean.getEditorFkObj(), new AuthorVO()));
		}
		
		return vo ;
	}
	
	public static PublicationBean convertVOToEntity(PublicationVO vo) {
		PublicationBean bean = new PublicationBean();
		
		bean = (PublicationBean) CustomBeanUtil.voToEntity(vo, bean);
		if(bean.getId() <= 0)
			bean.setId(null);
		if(bean.getPublisherFkId() <= 0)
			bean.setPublisherFkId(null);
		if(bean.getAuthorFkId() <= 0)
			bean.setAuthorFkId(null);

		if(vo.getPublisherVO() != null) {
			/* If the publisher vo is not null, set the bean data similarly */
			bean.setPublisherFkObj((PublisherBean) CustomBeanUtil.voToEntity(vo.getPublisherVO() , new PublisherBean()));
			if(bean.getPublisherFkObj() != null && bean.getPublisherFkObj().getName().length() <= 0 
					&& bean.getPublisherFkObj().getAddress().length() <= 0) {
				bean.setPublisherFkObj(null);
			} 
			if(bean.getPublisherFkObj() != null && bean.getPublisherFkObj().getId() <= 0)
				bean.getPublisherFkObj().setId(null);
		}
		
		AuthorBean editorBean = new AuthorBean();
		editorBean.setId(vo.getEditorVO().getId());
		editorBean.setLifeHistory(vo.getEditorVO().getLifeHistory());
		editorBean.setName(vo.getEditorVO().getName());
		editorBean.setDiacriticName(vo.getEditorVO().getDiacriticName());
		editorBean.setRegionalName(vo.getEditorVO().getRegionalName());
		editorBean.setType(IndvenApplicationConstants.PERSON_TYPE_EDITOR);
		if(vo.getEditorVO().getPeriod() != null && vo.getEditorVO().getPeriodEra() != null)
			editorBean.setPeriod(vo.getEditorVO().getPeriod().concat(vo.getEditorVO().getPeriodEra()));
		if(editorBean.getPeriod() != null && editorBean.getPeriod().length() <= 2) {
			editorBean.setPeriod(null);
		}
		bean.setEditorFkObj(editorBean);
		
		return bean;
	}
}
