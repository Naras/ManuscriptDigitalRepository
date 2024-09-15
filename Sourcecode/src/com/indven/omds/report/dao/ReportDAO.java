package com.indven.omds.report.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.util.HibernateUtil;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.util.ManuscriptTypeEnum;

public class ReportDAO {

	public Long getParentId(Long id,String type) throws OMDPCoreException{
		Session session = null;
		Transaction tx = null;
		Query query=null;
		Long parentId =(long) 0;
		ManuscriptTypeEnum enumWorkType = ManuscriptTypeEnum.valueOf(type);
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		if(type.equals("Original")){
			parentId = id;
		}else{
			query= session.createQuery("select bean.parentFKId from DigitalManuscriptBean bean where bean.id = :manuscriptId");
			query.setParameter("manuscriptId", id);
			parentId = (Long) query.uniqueResult();
			if(type.equals("Translation")){
				query= session.createQuery("select bean.parentFKId from DigitalManuscriptBean bean where bean.id = :manuscriptId");
				query.setParameter("manuscriptId", parentId);
				parentId = (Long) query.uniqueResult();	
			}
		}
		tx.commit();
		}catch (HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, he);
		} catch (Exception e) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_SAVE_DIGITAL_DOCUMENT, e);
		}finally {
			session.close();
		}
		return parentId;
	}
}
