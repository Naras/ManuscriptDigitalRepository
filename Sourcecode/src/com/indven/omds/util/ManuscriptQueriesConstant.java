/**
 * 
 */
package com.indven.omds.util;

/**
 * @author Deba Prasad
 *
 */
public class ManuscriptQueriesConstant {

	public static final String GET_TOTAL_NO_OF_RECORDS_FOR_ACCRONYM = "SELECT COUNT(od.manuscript_id) countno,oo.NAME FROM omds_digital_manuscript" +
			" od JOIN omds_organisation oo ON od.OrganisationFkId=oo.id WHERE od.manuscript_id  LIKE :param";
}
