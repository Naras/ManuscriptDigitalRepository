/**
 * 
 */
package com.indven.omds.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.dao.GenericBaseCRUDDAO;
import com.indven.framework.exception.FrameworkException;
import com.indven.framework.util.HibernateUtil;
import com.indven.omds.entity.ManuscriptTagMapper;
import com.indven.omds.entity.TagMasterBean;
import com.indven.omds.exception.OMDPCoreException;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

/**
 * @author Deba Prasad
 *
 */
public class MasterAddUpdateDAOImpl <T extends Object> implements GenericBaseCRUDDAO<T>{

	private final Class<T> entityBeanType;

	/**
	 * This constructor will tell us what bean type is the class
	 * 
	 * @param entityBeanType
	 */
	public MasterAddUpdateDAOImpl(final Class<T> entityBeanType) {
		this.entityBeanType = entityBeanType;
	}

	public Class<T> getEntityBeanType() {
		return entityBeanType;
	}

	@Override
	public T save(T entity) throws HibernateException, Exception {
		return entity;
	}
	
	@Override
	public T update(T entity) throws HibernateException, Exception {
		return entity;
	}

	public T saveOrUpdate(T entity) throws HibernateException, Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}finally {
			session.close();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(Long id) throws HibernateException, Exception {
		Session session = null;
		Transaction tx = null;
		T entity = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			entity = (T) session.get(getEntityBeanType(), id);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
		return entity;
	}

	@Override
	public void purge(T entity) throws HibernateException, Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<T> findAll() throws HibernateException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * This method will delete the entity by id
	 * 
	 * @param id
	 *            of type Long which is the entity id.
	 * @throws Exception
	 */
	@Override
	public void purgeById(Long id) throws HibernateException, Exception {
		
	}
	
	public int deleteById(Long id) throws HibernateException, Exception {
		Session session = null;
		Transaction tx = null;
		String queryString = "Update "+entityBeanType.getName()+" set isdeleted = 1 where id = :id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery(queryString);
			query.setParameter("id", id);
			int deletedRow = query.executeUpdate();

			if (deletedRow == 0)
				throw new HibernateException(FrameworkException.RECORD_NOT_FOUND);

			tx.commit();
			return deletedRow;
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}
	
	public List<TagMasterBean> findTag(String name){
		Session session = null;
		Transaction tx = null;
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		Query query = null;
		query= session.createQuery("FROM TagMasterBean t where t.name Like:tagName ");
		query.setParameter("tagName", name+"%");
		List<TagMasterBean> tagList = query.list();
		tx.commit();
		return tagList;
		}catch (HibernateException he) {
			tx.rollback();
			throw he;
		}finally {
			session.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String replaceTag(List<Long> replaceId,TagMasterBean replaceTag) throws OMDPCoreException{
		Session session = null;
		Transaction tx = null;
		Query query = null;
		/*Query query1 = null;*/
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			if(replaceTag .getId() <= 0){
				replaceTag .setId(null);
			}
			session.save(replaceTag);
			StringBuffer queryStrBuffer = new StringBuffer("From ManuscriptTagMapper mapper where mapper.tagsFkId =  ");
			queryStrBuffer = queryStrBuffer.append(replaceId.get(0));
			
			for(int i =1; i<replaceId.size();i++){
				queryStrBuffer = queryStrBuffer.append(" or mapper.tagsFkId = "+replaceId.get(i));
			}
			
			query = session.createQuery(queryStrBuffer.toString());
			
			List<ManuscriptTagMapper> mapperList = query.list();
			
			for(ManuscriptTagMapper bean : mapperList) {
				bean.setTagsFkId(replaceTag.getId());
			}
			
			// deleting the replaced tag from tag table
			StringBuffer queryStrBuffer1 = new StringBuffer("From TagMasterBean tm where tm.id =  ");
			queryStrBuffer1 = queryStrBuffer1.append(replaceId.get(0));
			for(int i =1; i<replaceId.size();i++){
				queryStrBuffer1 = queryStrBuffer1.append(" or tm.id = "+replaceId.get(i));
			}
			
			Query query1 = session.createQuery(queryStrBuffer1.toString());
			List<TagMasterBean> tagList = query1.list();
			for(TagMasterBean bean : tagList) {
				bean.setIsDeleted(true);
			}
			
			tx.commit();
			tx = session.beginTransaction();
			Query query2=session.createQuery("Delete TagMasterBean tm where tm.isDeleted = 1 ");
			//query2.setParameter("isdeleteid", 1);
			query2.executeUpdate();
			tx.commit();
		} catch(HibernateException he) {
			tx.rollback();
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_FIND_THE_RECORD, he);
		} finally {
			session.close();
		}
		
		return null;
	}

}
