package com.indven.omds.report.controller;

import java.io.ByteArrayOutputStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts2.ServletActionContext;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.omds.report.service.ReportService;
import com.indven.omds.service.ManuscriptMasterServiceImpl;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.omds.util.SourceOfCatalogueEnum;

/*import com.indven.tmfc.core.exception.TMFCCoreException;
import com.indven.tmfc.core.service.DepartmentMasterServiceImpl;
import com.indven.tmfc.core.vo.DepartmentMasterVO;
import com.indven.tmfc.core.vo.StationDataPointVO*/

/**
 * Action class to generate reports
 * Using Jasper Reports
 * @author Rakesh kumar sahoo
 */

public class JasperAction extends BaseAction {
	
	private List<String> documentStatusList = new ArrayList<>();
	
	/**
	 * @return the documentStatusList
	 */
	public final List<String> getDocumentStatusList() {
		for (DocumentStatusEnum dir : DocumentStatusEnum.values()) {
			documentStatusList.add(dir.name());
		}
		return documentStatusList;
	}
	
	/**
	 * @param documentStatusList the documentStatusList to set
	 */
	public final void setDocumentStatusList(List<String> documentStatusList) {
		this.documentStatusList = documentStatusList;
	}
	static String  folderPath1 ="";

	private static final long serialVersionUID = 5819348993569456811L;

	private static IndvenLogger logger = IndvenLogger.getInstance(JasperAction.class);
	
	private String category;
    private Connection conn = null;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    ManuscriptMasterServiceImpl manuscriptMasterServiceImpl = new ManuscriptMasterServiceImpl();
    
    /**
     * This method is used to generate the jasper report in pdf & xls format .
     * @author Rakesh kumar sahoo
     */
    public void exportReport() {
    	Integer startPage= Integer.parseInt(getRequest().getParameter("startPage"));
    	Integer endPage = Integer.parseInt(getRequest().getParameter("endPage"));
    	String status = ERROR;
    	Long id = Long.parseLong(getRequest().getParameter("id"));
    	String type = getRequest().getParameter("type");
    	Short recordType =Short.parseShort(getRequest().getParameter("recordType"));
    	ServletOutputStream outputStream =null;
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	String jasperPath = (String) getRequest().getParameter("jasperPath");
    	String folderPath = (ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
				.getObject("images.system.path").toString()).trim();
    	try {    		
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		ResourceBundle res = ResourceBundle.getBundle("projecthibernate", IndvenApplicationConstants.LOCALE);
			String url = res.getObject("hibernate.connection.url").toString();
			String username = res.getObject("hibernate.connection.username").toString();
			String password = res.getObject("hibernate.connection.password").toString();
			
    		conn = DriverManager.getConnection(url, username, password);

          
               byte[] bytes = null;
               File reportFile = new File(getRequest().getServletContext().getRealPath(jasperPath));
               JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile);
               parameters.put("id",id);
               parameters.put("recordtype",recordType);
               parameters.put("imagePath",folderPath);
               if(type.equalsIgnoreCase("pdf")) {
                  /* bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);*/
            	   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            	   if(startPage>0){
            		   startPage = startPage-1;
            	   }if(endPage<=0 || endPage > jasperPrint.getPages().size()){
            		   endPage=jasperPrint.getPages().size()-1;
            	   }else{
            		   endPage=endPage-1;
            	   }
            	   JRPdfExporter exporter = new JRPdfExporter();
            	   byteArrayOutputStream = new ByteArrayOutputStream();
            	   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            	   exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
            	   exporter.setParameter(JRExporterParameter.START_PAGE_INDEX,startPage);
            	   exporter.setParameter(JRExporterParameter.END_PAGE_INDEX,endPage);
            	   exporter.exportReport();
            	   bytes = byteArrayOutputStream.toByteArray();
                   ServletActionContext.getResponse().setContentType("application/pdf");
               } else if(type.equalsIgnoreCase("rtf")) {
               	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
                if(startPage>0){
         		   startPage = startPage-1;
         	   }if(endPage<=0 || endPage > jasperPrint.getPages().size()){
         		   endPage=jasperPrint.getPages().size()-1;
         	   }else{
        		   endPage=endPage-1;
        	   }
               	JRRtfExporter rtfExporter = new JRRtfExporter();
               	byteArrayOutputStream = new ByteArrayOutputStream();
               	rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
               	rtfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,byteArrayOutputStream);
               	rtfExporter.setParameter(JRExporterParameter.START_PAGE_INDEX,startPage);
               	rtfExporter.setParameter(JRExporterParameter.END_PAGE_INDEX,endPage);
               	rtfExporter.exportReport();
                bytes = byteArrayOutputStream.toByteArray();
                ServletActionContext.getResponse().setContentType("application/rtf");
               	/*JRXlsExporter exporterXLS = new JRXlsExporter();
               	 byteArrayOutputStream = new ByteArrayOutputStream();
               	
                   exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                   exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
                   exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                   exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                   exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                   exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                   exporterXLS.exportReport();
                   
                   bytes = byteArrayOutputStream.toByteArray();
                   ServletActionContext.getResponse().setContentType("application/xls");*/
               }
               outputStream = ServletActionContext.getResponse().getOutputStream();
               ServletActionContext.getResponse().setHeader("Content-Disposition: attachment",  "inline; filename=report." + type);
               outputStream.write(bytes, 0, bytes.length);
               status=SUCCESS;
    	} catch(Exception e) {
    		logger.error(e);
    		addActionError("Unable to generate report");
    	}finally{
    		try {
	    		if(outputStream!=null){
	    				outputStream.flush();
						outputStream.close();
	    		}
	    		if(byteArrayOutputStream!=null){
	    				byteArrayOutputStream.flush();
						byteArrayOutputStream.close();
					} 
	    		}catch (IOException e) {
					logger.error(e);
				}
    	}

 
    }
    
    /**
     * This method is used to generate the jasper report in pdf & rtf FOrtmat for the general public.
     * @author Rakesh kumar sahoo
     */
    public void exportReportForPublic() {
    	String status = ERROR;
    	Long id = Long.parseLong(getRequest().getParameter("id"));
    	String workType = getRequest().getParameter("workType");
    	Short recordType =0;
    	ServletOutputStream outputStream =null;
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	String jasperPath = (String) getRequest().getParameter("jasperPath");
    	String folderPath = (ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
				.getObject("images.system.path").toString()).trim();
    	try {  
    		ReportService service = new ReportService();
    		id = service.getParentId(id,workType);
    		if(workType.equals("Translation")){
    			recordType = 1;
    		}
    		Class.forName("com.mysql.jdbc.Driver");
    		ResourceBundle res = ResourceBundle.getBundle("projecthibernate", IndvenApplicationConstants.LOCALE);
			String url = res.getObject("hibernate.connection.url").toString();
			String username = res.getObject("hibernate.connection.username").toString();
			String password = res.getObject("hibernate.connection.password").toString();
			
    		conn = DriverManager.getConnection(url, username, password);

          
               byte[] bytes = null;
               File reportFile = new File(getRequest().getServletContext().getRealPath(jasperPath));
               JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile);
               parameters.put("id",id);
               parameters.put("recordtype",recordType);
               parameters.put("imagePath",folderPath);
                  /* bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);*/
            	   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            	   JRPdfExporter exporter = new JRPdfExporter();
            	   byteArrayOutputStream = new ByteArrayOutputStream();
            	   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            	   exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
            	   exporter.exportReport();
            	   bytes = byteArrayOutputStream.toByteArray();
                   ServletActionContext.getResponse().setContentType("application/pdf");
               outputStream = ServletActionContext.getResponse().getOutputStream();
               ServletActionContext.getResponse().setHeader("Content-Disposition",  "inline; filename=report.pdf" );
               outputStream.write(bytes, 0, bytes.length);
               status=SUCCESS;
    	} catch(Exception e) {
    		logger.error(e);
    		addActionError("Unable to generate report");
    	}finally{
    		try {
	    		if(outputStream!=null){
	    				outputStream.flush();
						outputStream.close();
	    		}
	    		if(byteArrayOutputStream!=null){
	    				byteArrayOutputStream.flush();
						byteArrayOutputStream.close();
					} 
	    		}catch (IOException e) {
					logger.error(e);
				}
    	}

 
    }
    
    
 /**
     * This method is used to set the parameters which will pass to the jasper tool .
     * @author Rakesh kumar sahoo
     */
    public String generateReportByCriteria() {
    	String status = ERROR;
    	int documentStatus;
    	
    	try { 
    		Long totalManuscript= manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
    		Long totalBook =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
    		//Parameters for Jasper
		    Map<String, Object> parameters = new HashMap<String, Object>();
		   String type = getRequest().getParameter("type");
		    if(type.equals("1")){
		    	String docStatus = getRequest().getParameter("status");
		    	documentStatus =(DocumentStatusEnum.valueOf(docStatus)).getValue();
		    	 parameters.put("status", documentStatus+1);
		    }
		    //Path of Jasper file
		    String jasperPath;
			//parameters.put("category", category);
			parameters.put("totalManuscript", totalManuscript);
		    parameters.put("totalBook", totalBook);
		    String sep=File.separator;
		    folderPath1 = getRequest().getServletContext().getRealPath("/")+"assets"+sep+"images"+sep+"Reportlogo.png";
		    parameters.put("imagePath",folderPath1);
			getRequest().setAttribute("parameters", parameters);
			jasperPath = (String) getRequest().getParameter("jasperPath");
			getRequest().setAttribute("jasperPath", jasperPath);
			getRequest().setAttribute("docType",type);
			getRequest().setAttribute("page", null);

			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
            addActionError("Unable to generate report");
		}
    	return status;
    }
    
   public String generateReport() {
    	String status = ERROR;
    	int documentStatus;
    	try { 
    		
    		Long totalManuscript= manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
    		Long totalBook =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
			//Parameters for Jasper
		    Map<String, Object> parameters = new HashMap<String, Object>();
		    String docType = getRequest().getParameter("docType");
  		    if(docType.equals("1")){
  		    	 documentStatus = Integer.parseInt(getRequest().getParameter("status"));
  		    	/*documentStatus =(DocumentStatusEnum.valueOf(docStatus)).getValue();*/
  		    	 parameters.put("status", documentStatus);
  		    }
		    //Path of Jasper file
			/*parameters.put("category",  getRequest().getParameter("category"));*/
			parameters.put("totalManuscript", totalManuscript);
		    parameters.put("totalBook", totalBook);
			parameters.put("imagePath",folderPath1);
			getRequest().setAttribute("parameters", parameters);
			getRequest().setAttribute("docType",docType);
		
	    	String jasperPath = (String) getRequest().getParameter("jasperPath");
	    	getRequest().setAttribute("page", getRequest().getParameter("page"));
			getRequest().setAttribute("jasperPath", jasperPath);
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
            addActionError("Unable to generate report");
		}
    	
    	return status;
    }
   /**
    * This method is used to generate the jasper report in pdf & xls format .
    * @author Rakesh kumar sahoo
    */
   public void exportReportFromJsp() {
   	String status = ERROR;    	
   	String type = getRequest().getParameter("type");
   	int documentStatus;
   	ServletOutputStream outputStream =null;
   	ByteArrayOutputStream byteArrayOutputStream = null;
   	String jasperPath = (String) getRequest().getParameter("jasperPath");
   	try {    
   		Long totalManuscript= manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
		Long totalBook =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
   		Class.forName("com.mysql.jdbc.Driver");
   		
   		ResourceBundle res = ResourceBundle.getBundle("projecthibernate", IndvenApplicationConstants.LOCALE);
			String url = res.getObject("hibernate.connection.url").toString();
			String username = res.getObject("hibernate.connection.username").toString();
			String password = res.getObject("hibernate.connection.password").toString();
			
   		conn = DriverManager.getConnection(url, username, password);
   		    outputStream = ServletActionContext.getResponse().getOutputStream();
              
              byte[] bytes = null;
              File reportFile = new File(getRequest().getServletContext().getRealPath(jasperPath));
              JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile);
              String docType = getRequest().getParameter("docType");
  		    if(docType.equals("1")){
  		    	 documentStatus = Integer.parseInt(getRequest().getParameter("status"));
  		    	/*documentStatus =(DocumentStatusEnum.valueOf(docStatus)).getValue();*/
  		    	 parameters.put("status", documentStatus);
  		    }
              parameters.put("category",  getRequest().getParameter("category"));
              parameters.put("totalManuscript", totalManuscript);
			  parameters.put("totalBook", totalBook);
			  parameters.put("imagePath",folderPath1);
              if(type.equalsIgnoreCase("pdf")) {
                  bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
                  ServletActionContext.getResponse().setContentType("application/pdf");
                  
              } else if(type.equalsIgnoreCase("xls")) {
              	
              	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
              	
              	JRXlsExporter exporterXLS = new JRXlsExporter();
              	 byteArrayOutputStream = new ByteArrayOutputStream();
              	
                  exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                  exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                  exporterXLS.exportReport();
                  
                  bytes = byteArrayOutputStream.toByteArray();
                  ServletActionContext.getResponse().setContentType("application/xls");
              }
              
             
              ServletActionContext.getResponse().setHeader("Content-Disposition: attachment",  "inline; filename=report." + type);
              
              outputStream.write(bytes, 0, bytes.length);
              status=SUCCESS;
   	} catch(Exception e) {
   		logger.error(e);
   		addActionError("Unable to generate report");
   	}finally{
   		try {
	    		if(outputStream!=null){
	    				outputStream.flush();
						outputStream.close();
	    		}
	    		if(byteArrayOutputStream!=null){
	    				byteArrayOutputStream.flush();
						byteArrayOutputStream.close();
					} 
	    		}catch (IOException e) {
					logger.error(e);
				}
   	}
		
   }
   
   public String reDirectToPage() {
		return SUCCESS;
	}
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the openPoMasterVO
	 *//*
	public OpenPoMasterVO getOpenPoMasterVO() {
		return openPoMasterVO;
	}

	*//**
	 * @param openPoMasterVO the openPoMasterVO to set
	 *//*
	public void setOpenPoMasterVO(OpenPoMasterVO openPoMasterVO) {
		this.openPoMasterVO = openPoMasterVO;
	}*/


}
