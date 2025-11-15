package com.indven.omds.report.service;

import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.report.dao.ReportDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ReportService {
  
	ReportDAO dao = new ReportDAO();
	public Long getParentId(Long id,String type) throws OMDPCoreException{
		return dao.getParentId(id,type);
	}

	public void getDetailReportData(String filterQuery) {
		dao.getDetailReportData(filterQuery);
	}

	public List<HashMap<String,String>> convertResultSetToList(ResultSet resultSet) throws SQLException {
		return dao.convertResultSetToList(resultSet);
	}
}
