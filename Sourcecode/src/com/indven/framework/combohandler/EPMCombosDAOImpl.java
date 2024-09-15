package com.indven.framework.combohandler;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.indven.framework.util.HibernateUtil;

public class EPMCombosDAOImpl {

	@SuppressWarnings("unchecked")
	public List<EPMComboEntityVO> execQueryForCombo(String strQuery) {
		Session session = null;
		List<EPMComboEntityVO> entities = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			entities = (List<EPMComboEntityVO>) session.createSQLQuery(strQuery).addEntity(EPMComboEntityVO.class).list();

			entities.add(0, EPMCombosConstants.objSel);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			session.close();
		}
		return entities;
	}

}
