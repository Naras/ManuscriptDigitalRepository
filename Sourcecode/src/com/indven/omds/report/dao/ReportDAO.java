package com.indven.omds.report.dao;

import org.hibernate.*;

import com.indven.framework.util.HibernateUtil;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.util.ManuscriptTypeEnum;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public void getDetailReportData(String filterQuery) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			SQLQuery query = session.createSQLQuery("SELECT dm.Id,dm.NAME,dm.regional_name,dm.diacritical_name,dm.SUMMARY,dm.acc_no,dm.table_of_contents,dm.digitized_by,dm.catalogue_no,\n" +
					"dm.cataloguedetails,dm.colophon,dm.beginning_line,dm.ending_line,dm.documentation_of_manuscript,dm.isbound,dm.manuscript_id,\n" +
					"dm.total_no_of_folios,dm.total_no_of_maps,dm.condition_of_manuscript,dm.source_of_catalogue,dm.TYPE_OF_WORK,org.Id AS orgId,org.NAME AS orgName,\n" +
					"org.email,org.ADDRESS AS orgAddress,org.website,org.type AS orgType,org.phoneNumber,op.Id AS publicationId,op.PRICE,op.NO_OF_PAGES,\n" +
					"op.YEAR_OF_PUBLICATION,opp.NAME AS editorName,opb.NAME AS publisherName,opb.ADDRESS publisherAddress,ol.NAME AS languageName,\n" +
					"os.NAME AS scriptName,om.NAME materialName,ob.name AS bundleName,oc.name AS categoryName,opc.NAME AS scribName,\n" +
					"GROUP_CONCAT(opa.NAME) AS Author,GROUP_CONCAT(DISTINCT osc.NAME) AS specificcategory FROM omds_digital_manuscript dm\n" +
					"LEFT JOIN omds_organisation org ON dm.OrganisationFkId = org.Id\n" +
					"LEFT JOIN omds_publication op ON dm.PublicationFkId = op.Id\n" +
					"LEFT JOIN omds_person opp ON op.editorfkid = opp.Id\n" +
					"LEFT JOIN omds_publisher opb ON op.PublisherFkId = opb.Id\n" +
					"LEFT JOIN omds_language ol ON dm.languageFkId = ol.Id\n" +
					"LEFT JOIN omds_script os ON dm.scriptFkId = os.Id\n" +
					"LEFT JOIN omds_material om ON dm.MaterialFkId = om.Id\n" +
					"LEFT JOIN omds_bundle ob ON dm.bundleMasterfkid = ob.id\n" +
					"LEFT JOIN omds_category oc ON dm.categoryFkId = oc.id\n" +
					"LEFT JOIN omds_person opc ON dm.scribefkid = opc.Id\n" +
					"LEFT JOIN omds_manuscript_authormapper am ON dm.Id = am.manuscriptfkid\n" +
					"LEFT JOIN omds_person opa ON am.authorfkid = opa.Id\n" +
					"LEFT JOIN omds_manuscript_specificcategorymapper oscc ON oscc.manuscriptfkid = dm.Id\n" +
					"LEFT JOIN omds_specificcategory osc ON oscc.specificcategoryfkid = osc.id\n" +
					"WHERE "+filterQuery+" GROUP BY dm.Id\n" +
					"ORDER BY dm.Id ");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList=query.list();
			System.out.println("query.list() "+aliasToValueMapList.size());
			/*List<Object[]> rows = query.list();
			System.out.println("rows size "+rows);
			for(Object[] row : rows) {

			}*/


				//query= session.createQuery("select bean.parentFKId from DigitalManuscriptBean bean");
				//System.out.println("stage1 1"+query.setFirstResult(1));
			tx.commit();
		}catch (HibernateException he) {
			tx.rollback();
			System.out.println(" hibernate exception "+he);

		} catch (Exception e) {
			tx.rollback();
			System.out.println(" exception "+e);

		}finally {
			session.close();
		}
	}

    public List<HashMap<String,String>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        while (rs.next()) {
            HashMap<String,String> row = new HashMap<String, String>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnLabel(i),rs.getString(i));
            }
            list.add(row);
        }
        return list;
    }
}
