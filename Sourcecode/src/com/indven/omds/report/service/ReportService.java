package com.indven.omds.report.service;

import com.indven.omds.exception.OMDPCoreException;
import com.indven.omds.report.dao.ReportDAO;

public class ReportService {
  
	ReportDAO dao = new ReportDAO();
	public Long getParentId(Long id,String type) throws OMDPCoreException{
		return dao.getParentId(id,type);
	}
}
