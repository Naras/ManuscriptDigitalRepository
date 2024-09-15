/**
 * 
 */
package com.indven.search.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.search.dao.GenericSearchDAOImpl;
import com.indven.search.exception.SearchException;
import com.indven.search.util.SearchConstants;
import com.indven.search.vo.SearchVO;

/**
 * @author Deba Prasad
 *
 */
public class SearchAction extends BaseAction{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSONObject resultObj = null;	
	
	private SearchVO searchVO = new SearchVO();	

	/**
	 * @return the resultObj
	 */
	public final JSONObject getResultObj() {
		return resultObj;
	}

	/**
	 * @param resultObj the resultObj to set
	 */
	public final void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	} 
	
	
	public String searchGenericUser() throws Exception {
		
		String voName = SearchConstants.VALUES_BY_NAME.get(getRequest().getParameter("vo"));
				
		searchVO = getSearchVOFromClassName(voName , false);
		searchVO.setCreatePageURL(SearchConstants.CREATE_ACTION_URL.get(getRequest().getParameter("vo")));
		
		getRequest().setAttribute("respectiveVO", searchVO);
		
		return SUCCESS;
	 }

	/**
	 * This is the action method for search or findAll.
	 * This method just gets the list of objects from the dao and converts the objects into json format required in the ui. 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String searchAll() {
		String status = ERROR;
		List<Object> searchList = new ArrayList<>();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultObj = new JSONObject();
		
		
		String tableName = getRequest().getParameter("tableName");
		
		int pageNumber = Integer.parseInt(getRequest().getParameter("pageNumber"));
		int recordsPerPage = Integer.parseInt(getRequest().getParameter("recordsPerPage"));
		
		//List<String> paramList = new ArrayList<>();
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String> paramMapForJoin = new HashMap<String, String>();
		
		try {
			searchVO = getSearchVOFromClassName(tableName , true);
			
			for(int i =0; i<searchVO.getDisplayList().size()-1 ; i++) {
				String idTrack = (String)searchVO.getDisplayList().get(i+1);
				
				if(searchVO.getFkObjName().contains(idTrack)) {
					String value = getRequest().getParameter(idTrack);
					if(value != null && value.length() > 0)
						//value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
						paramMapForJoin.put(idTrack, new String(value.getBytes("ISO-8859-1"), "UTF-8"));
				} else {
					String value = getRequest().getParameter(idTrack);
					if(value != null && value.length() > 0)
						paramMap.put(idTrack,  new String(value.getBytes("ISO-8859-1"), "UTF-8"));
				}
			}
			
			GenericSearchDAOImpl dao = new GenericSearchDAOImpl();
			
			int firstResultNo = ((pageNumber-1)*recordsPerPage);
			resultMap = dao.search(searchVO,paramMap ,paramMapForJoin ,true , firstResultNo , recordsPerPage);
			
			Long count = (Long) resultMap.get("RecordCount");
			searchList = (List<Object>) resultMap.get("ListOfObjects");
			
			this.resultObj.put("parentList", searchList);
			this.resultObj.put("nameList", searchVO.getDisplayList());
			this.resultObj.put("displayList", searchVO.getLabelList());
			this.resultObj.put("updateAction", searchVO.getUpdateAction());
			this.resultObj.put("deleteAction", searchVO.getDeleteAction());
			this.resultObj.put("recordCount", count);
			//this.resultObj.put("actionList", searchVO.getActionList());
			
			status = SUCCESS;
			
		} catch (SearchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
  
	/**
	 * This method is responsible for creating the SearchVO object by executing the methods those are overridden in the 
	 * respective VO class. 
	 * 
	 * @param className
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchVO getSearchVOFromClassName(String className , boolean flag) throws Exception{
		
		Class<?> c = Class.forName(className);
		Object obj = c.newInstance();
		
		Map<String, String> actionNamesMap = new HashMap<String, String>();
		Map<String, String> labelDisplayMap = new HashMap<String, String>();

		Method method = c.getDeclaredMethod ("getTableName");
		searchVO.setTableName((String) method.invoke(obj));
		
		method = c.getDeclaredMethod ("getActionNames");
		actionNamesMap = (Map<String, String>) method.invoke(obj);
		
		searchVO.setUpdateAction(actionNamesMap.get(IndvenApplicationConstants.FIND_BY_ID_ACTION_NAME));
		searchVO.setDeleteAction(actionNamesMap.get(IndvenApplicationConstants.DELETE_ACTION_NAME));
		
		method = c.getDeclaredMethod ("getLabelDisplayMap");
		labelDisplayMap = (Map<String, String>) method.invoke(obj);
		searchVO.getDisplayList().add("id");
		for (Map.Entry<String, String> entry : labelDisplayMap.entrySet()) {
			searchVO.getLabelList().add(entry.getKey());
			searchVO.getDisplayList().add(entry.getValue());
		}
		
		if(flag) {
			Map<String, String> beanMapForFkId = new HashMap<String, String>();
			Map<String, String> columnMapForFkIdSearch = new HashMap<String, String>();
			Map<String, String> varNameInBeanClass = new HashMap<String, String>();

			method = c.getDeclaredMethod("beanListForFkId");
			beanMapForFkId = (Map<String, String>) method.invoke(obj);
			
			
			if(beanMapForFkId != null) {
				
				method = c.getDeclaredMethod("columnName");
				columnMapForFkIdSearch = (Map<String, String>) method.invoke(obj);
				
				method = c.getDeclaredMethod("beanVarName");
				varNameInBeanClass = (Map<String, String>) method.invoke(obj);
				
				searchVO.setBeanMapForFkId(beanMapForFkId);
				searchVO.setColumnMapForFkIdSearch(columnMapForFkIdSearch);
				searchVO.setVarNameInEntity(varNameInBeanClass);
				
				for (Map.Entry<String, String> entry : beanMapForFkId.entrySet()) {
					searchVO.getFkObjName().add(entry.getKey());
				}
			}
		}
		
		return searchVO;
	}
}
