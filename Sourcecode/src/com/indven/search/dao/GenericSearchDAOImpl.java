/**
 * 
 */
package com.indven.search.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.HibernateUtil;
import com.indven.search.exception.SearchException;
import com.indven.search.util.SearchConstants;
import com.indven.search.vo.SearchVO;

/**
 * @author Deba Prasad
 *
 */
public class GenericSearchDAOImpl {
	
	private static IndvenLogger logger = IndvenLogger.getInstance(GenericSearchDAOImpl.class);

	private List<Object[]> list = new ArrayList<Object[]>();
	
	/**
	 * This method is responsible for generating the dynamic search HQL query as per the class name and depending upon the field values
	 * provided for search , execute the query and get the List of objects for available records.
	 * In this method we are getting the total no of records count and adding that to return map for pagination purpose in UI.
	 * 
	 * @param searchVO
	 * @param paramMap
	 * @param paramMapForJoin
	 * @param onlyActive
	 * @param setFirst
	 * @param lastRecord
	 * @return
	 * @throws SearchException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> search(SearchVO searchVO , Map<String, String> paramMap ,  Map<String, String> paramMapForJoin , Boolean onlyActive , int setFirst , int lastRecord ) throws SearchException {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		Map<String, String> paramBindMap = new HashMap<String, String>();
		
		List<Object> returnList = new ArrayList<Object>();
		boolean isWhereAdded = false;
	    Session session = null;
	    Transaction tx = null;
		Query query  = null;
		
		try {
			Class<?> c = Class.forName(searchVO.getTableName());
			Object obj = c.newInstance();

			session = HibernateUtil.getSessionFactory().openSession();		
			tx = session.beginTransaction();

			String strMainBean = SearchConstants.ENTITY_FOR_VO.get(searchVO.getTableName());
			String strMainAlias = strMainBean.toLowerCase();
			List<String> selectQryOrderList = new ArrayList<>();

			StringBuffer selectFromMainTab = new StringBuffer("select ");
			StringBuffer fromQueryStr = new StringBuffer(" from ");
			StringBuffer whereQueryStr = new StringBuffer();
			
			fromQueryStr.append(strMainBean+" "+strMainAlias);
			boolean isPresentFkId = false;
			for(int i=0 ; i<searchVO.getDisplayList().size() ; i++) {
				if(i>0){
					selectFromMainTab.append(" , ");
				}
				String searchMember = (String) searchVO.getDisplayList().get(i);
				
				if(!(searchVO.getFkObjName().contains(searchMember))){
					selectFromMainTab.append(strMainAlias+"."+searchMember);
					selectQryOrderList.add(searchMember);
				} else {
					
					String fkOBjClassName = searchVO.getBeanMapForFkId().get(searchMember);
					String fkObjAlias = fkOBjClassName.toLowerCase();
					String searchMemberInBean = searchVO.getVarNameInEntity().get(searchMember);
					
					fromQueryStr.append(", "+fkOBjClassName+" "+fkObjAlias);
					
					selectFromMainTab.append(fkObjAlias+"."+searchMemberInBean);
					selectQryOrderList.add(searchMember);
					
					if(!isWhereAdded) {
						whereQueryStr.append(" where ");
						isWhereAdded = true;
					}
					
					if(isPresentFkId) {
						whereQueryStr.append(" and ");
					}
					whereQueryStr.append(strMainAlias+"."+searchVO.getColumnMapForFkIdSearch().get(searchMember) +" = " +fkObjAlias+".id");
					isPresentFkId = true;
					if(paramMapForJoin.size() > 0) {
						if(paramMapForJoin.containsKey(searchMember)) {
							String s1 = searchMemberInBean+"1";
							whereQueryStr.append(" and "+fkObjAlias+"."+searchMemberInBean+" LIKE :"+s1+"");
							paramBindMap.put(s1 , '%'+paramMapForJoin.get(searchMember)+'%');
						}
						
					}
					
				}
			}
			
			if(paramMap.size() > 0) {
				for (Map.Entry<String, String> entry : paramMap.entrySet()) {
					if(!isWhereAdded) {
						whereQueryStr.append(" where ");
						isWhereAdded = true;
					} else {
						whereQueryStr.append(" and ");
					}
						
					String whereStr = strMainAlias+"."+entry.getKey()+" like :"+entry.getKey();
					paramBindMap.put(entry.getKey(), "%"+entry.getValue()+"%");
					whereQueryStr.append(whereStr);
				}
			}
			

			if(onlyActive) {
				if(isWhereAdded){
					String isDeletedStr = " and "+strMainAlias+".isDeleted = '0'";
					whereQueryStr.append(isDeletedStr);	
				} else {
					String isDeletedStr = " where "+strMainAlias+".isDeleted = '0'";
					isWhereAdded = true;
					whereQueryStr.append(isDeletedStr);	
				}
				
			}
			
			StringBuffer queryStr = new StringBuffer();
			
			queryStr.append(selectFromMainTab);
			queryStr.append(fromQueryStr);
			queryStr.append(whereQueryStr);
			
			queryStr.append(" order by "+strMainAlias+".id asc ");
			
			String getCountStr = "select count("+strMainAlias+")";
			getCountStr = getCountStr+fromQueryStr+whereQueryStr;
			Query query1 = session.createQuery(getCountStr);
			List<Long> list1 = new ArrayList<Long>();
			
			for (Map.Entry<String, String> entry : paramBindMap.entrySet()) {
				query1.setParameter( entry.getKey(), entry.getValue());
			}
			
			list1 = query1.list();
			
			query = session.createQuery(queryStr.toString());
			
			for (Map.Entry<String, String> entry : paramBindMap.entrySet()) {
				query.setParameter( entry.getKey(), entry.getValue());
			}
			
			query.setFirstResult(setFirst);
			query.setMaxResults(lastRecord);
			
			list = query.list();
			tx.commit();
			
			if(list != null) {
				for(int i=0;i<list.size() ; i++) {
					obj = c.newInstance();
					for(int k = 0 ; k < searchVO.getDisplayList().size() ; k++) {
						PropertyUtils.setSimpleProperty(obj, (String) searchVO.getDisplayList().get(k), (list.get(i))[k]);
					}
					returnList.add(obj);
				}
			}
			
			returnMap.put("RecordCount", list1.get(0));
			returnMap.put("ListOfObjects", returnList);
			
		}catch(Exception e) {
			logger.error(e);
			throw new SearchException(SearchException.UNABLE_TO_SEARCH , e);
		} finally {
			session.close();
		}
		return returnMap;
	}
}
