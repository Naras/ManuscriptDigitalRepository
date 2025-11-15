package com.indven.omds.report.controller;

import java.awt.image.BufferedImage;
import java.io.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.indven.omds.controller.ManuscriptMasterAction;
import com.indven.omds.exception.OMDPCoreException;
import com.indven.util.export.ExportToDetailedReportWord;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.exceptionhandler.IndvenExceptionMessageResolver;
import com.indven.framework.logging.IndvenLogger;
import com.indven.framework.util.IndvenApplicationConstants;
import com.indven.framework.vo.IndvenResultVO;
import com.indven.omds.report.service.ReportService;
import com.indven.omds.service.ManuscriptMasterServiceImpl;
import com.indven.omds.util.DocumentStatusEnum;
import com.indven.omds.util.ManuscriptDocumentationType;
import com.indven.omds.util.ManuscriptTypeEnum;
import com.indven.omds.util.ManuscriptWorkType;
import com.indven.omds.util.SourceOfCatalogueEnum;
import com.indven.omds.vo.DigitalManuscriptVO;
import org.joda.time.DateTime;
import org.openxmlformats.schemas.drawingml.x2006.diagram.STOutputShapeType;

/*import com.indven.tmfc.core.exception.TMFCCoreException;
import com.indven.tmfc.core.service.DepartmentMasterServiceImpl;
import com.indven.tmfc.core.vo.DepartmentMasterVO;
import com.indven.tmfc.core.vo.StationDataPointVO*/

/**
 * Action class to generate reports
 * Using Jasper Reports
 * @author Rakesh kumar sahoo
 */

public class JasperAction extends BaseAction implements ServletResponseAware{
	
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
	private DigitalManuscriptVO digitalManuscriptVO;
	private String reportPath;
	private String documentStatus;
	InputStream inputStream = null;
	
	/**
	 * @param documentStatusList the documentStatusList to set
	 */
	public final void setDocumentStatusList(List<String> documentStatusList) {
		this.documentStatusList = documentStatusList;
	}
	private static final long serialVersionUID = 5819348993569456811L;

	private static IndvenLogger logger = IndvenLogger.getInstance(JasperAction.class);
	private String exportDocumentType;
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
	/**
	 * Modified report to generate docx or pdf file
	 */
    public void generateReportByCriteria() {
		System.out.println("in to generateReportByCriteria for details report---");
    	String status = ERROR;
    	int eDocumentStatus;
		ServletOutputStream outputStream =null;
		ByteArrayOutputStream byteArrayOutputStream=null;
		System.out.println("exportDocumentType "+exportDocumentType);
		try {
    		Long totalManuscript= manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
    		Long totalBook =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
    		Long totalArticle =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_ARTICLE);
    		//Parameters for Jasper
		    Map<String, Object> parameters = new HashMap<String, Object>();
		 //  String type = getRequest().getParameter("type");
		    parameters.put("reportfilter",generateReportCriteria());
		    /*//Path of Jasper file
		    String jasperPath;
			//parameters.put("category", category);
			parameters.put("totalManuscript", totalManuscript);
		    parameters.put("totalBook", totalBook);
		    parameters.put("totalAreticle", totalArticle);
		    String reqId = (String) getRequest().getAttribute("requestId");
		   // String filePath = "http://localhost:8080/MDR/imageTest.action?requestId="+reqId+"&filedbpath=";
		    String filePath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
					.getObject("images.system.path").toString();
		    parameters.put("filePath", filePath);
		    String subReportPath = getRequest().getServletContext().getRealPath("/report/MDRManuscriptDetailsReport_subreport1.jasper");
		    parameters.put("SUBREPORT_DIR", subReportPath);
		   // String sep=File.separator;
			getRequest().setAttribute("parameters", parameters);
			getRequest().setAttribute("jasperPath", reportPath);
			getRequest().setAttribute("page", null);*/
			byteArrayOutputStream = ExportToDetailedReportWord.exportProcessToWord(generateReportCriteria());
			System.out.println("byteArrayOutputStream "+byteArrayOutputStream.size());
			byte[] bytes = byteArrayOutputStream.toByteArray();

			if (StringUtils.isBlank(exportDocumentType) || "doc".equalsIgnoreCase(exportDocumentType)) {
				getResponse().setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				getResponse().setHeader("Content-Disposition", "attachment;filename=DetailedReport.docx");
				getResponse().getOutputStream().write(bytes);
			} else {
				String saveFilePath = "/tmp/mdr/pdf_conv/";
				File convertionFilePath = new File(saveFilePath);
				if (!convertionFilePath.exists()) {
					convertionFilePath.mkdirs();
				}
				String currentTimeInMillis = DateTime.now().getMillis()+"";
				String docxfileName =currentTimeInMillis+".docx";
				String fileName = saveFilePath+docxfileName;
				try {

				    //soffice --headless --convert-to pdf:writer_pdf_Export report11.docx

                    FileUtils.writeByteArrayToFile(new File(saveFilePath+docxfileName),byteArrayOutputStream.toByteArray());
					String batchCommand = "soffice --headless --convert-to pdf:writer_pdf_Export "+saveFilePath+docxfileName;
                    //--
					Process p = Runtime.getRuntime().exec(batchCommand,null,convertionFilePath);
					p.waitFor();

					BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					StringBuffer sb = new StringBuffer();
					String line = "";
					while ((line = reader.readLine())!= null) {
						sb.append(line + "\n");
					}
					System.out.println("message ---->"+sb.toString());

                    //int processComplete = p.waitFor();

                    //---
					InputStream is = new FileInputStream(fileName.replaceAll(".docx",".pdf"));
					byte[] outBytes = IOUtils.toByteArray(is);
					//ByteArrayOutputStream byteArrayPdfOutputStream = new ByteArrayOutputStream();
					//XWPFDocument document=new XWPFDocument(new ByteArrayInputStream(bytes));

					//org.apache.poi.xwpf.converter.pdf.PdfConverter.getInstance().convert(document,byteArrayPdfOutputStream,null);
					getResponse().setContentType("application/pdf");
					getResponse().setHeader("Content-Disposition", "attachment;filename=DetailedReport.pdf");
					getResponse().getOutputStream().write(outBytes);
				} catch (Exception e) {
					System.out.println("exception while converting docx to pdf "+e);
				}
				finally {
					File[] files = convertionFilePath.listFiles((FilenameFilter) new PrefixFileFilter(currentTimeInMillis));
					for (File file : files) {
						file.delete();
					}

				}
			}
			//outputStream.write(bytes, 0, bytes.length);
			byteArrayOutputStream.close();

			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
            addActionError("Unable to generate report");

		} finally {
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
		//return status;
    }
    //http://www.catchexceptions.com/java/convert-word-file-docx-to-pdf-file-using-apache-poi/
   public void generateReport() {
    	String status = ERROR;
    	try { 
    		
    		Long totalManuscript= manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
    		Long totalBook =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
    		Long totalArticle =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_ARTICLE);
			//Parameters for Jasper
		    Map<String, Object> parameters = new HashMap<String, Object>();
  		    parameters.put("reportfilter", getRequest().getParameter("reportfilter"));
			parameters.put("totalManuscript", totalManuscript);
		    parameters.put("totalBook", totalBook);
		    parameters.put("totalAreticle", totalArticle);
		    String reqId = (String) getRequest().getAttribute("requestId");
		   // String filePath = "http://localhost:8080/MDR/imageTest.action?requestId="+reqId+"&filedbpath=";
		   String filePath =ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
					.getObject("images.system.path").toString();
		    parameters.put("filePath", filePath);
		    String subReportPath = getRequest().getServletContext().getRealPath("/report/MDRManuscriptDetailsReport_subreport1.jasper");
		    parameters.put("SUBREPORT_DIR", subReportPath);
			getRequest().setAttribute("parameters", parameters);
	    	String jasperPath = (String) getRequest().getParameter("jasperPath");
	    	getRequest().setAttribute("page", getRequest().getParameter("page"));
			getRequest().setAttribute("jasperPath", jasperPath);
			status = SUCCESS;
		} catch (Exception e) {
			logger.error(e);
            addActionError("Unable to generate report");
		}
    	
    	//return status;
    }
   /**
    * This method is used to generate the jasper report in pdf & xls format .
    * @author Rakesh kumar sahoo
    */
   public void exportReportFromJsp() {
   	String status = ERROR;    	
   	String type = getRequest().getParameter("type");
   	ServletOutputStream outputStream =null;
   	ByteArrayOutputStream byteArrayOutputStream = null;
   	String jasperPath = (String) getRequest().getParameter("jasperPath");
   	try {    
   		Long totalManuscript= manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_MANUSCRIPT);
		Long totalBook =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_BOOK);
		Long totalArticle =  manuscriptMasterServiceImpl.findNoOfRecordsByDocumentType(IndvenApplicationConstants.DIGITALMANUSCRIPT_TYPE_ARTICLE);
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
              parameters.put("category",  getRequest().getParameter("category"));
              parameters.put("totalManuscript", totalManuscript);
			  parameters.put("totalBook", totalBook);
			  parameters.put("totalAreticle", totalArticle);
			  parameters.put("reportfilter", getRequest().getParameter("reportfilter"));
			  String filePath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
						.getObject("images.system.path").toString();
			    parameters.put("filePath", filePath);
			    String subReportPath = getRequest().getServletContext().getRealPath("/report/MDRManuscriptDetailsReport_subreport1.jasper");
			    parameters.put("SUBREPORT_DIR", subReportPath);
              if(type.equalsIgnoreCase("pdf")) {
                  bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
                  ServletActionContext.getResponse().setContentType("application/pdf");
                  
              } else if(type.equalsIgnoreCase("xlsx")) {
              	
              	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
              	
              	byteArrayOutputStream = new ByteArrayOutputStream();
              	 JRXlsxExporter exporterXLS = new JRXlsxExporter();

                  exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                  exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
                  //exporterXLS.setParameter(JRExporterParameter.START_PAGE_INDEX,0);
                  //exporterXLS.setParameter(JRExporterParameter.END_PAGE_INDEX,10);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
                  exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,Boolean.FALSE);
                  exporterXLS.exportReport();
                  
                  bytes = byteArrayOutputStream.toByteArray();
                  ServletActionContext.getResponse().setContentType("application/xlsx");
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
	 * @return the digitalManuscriptVO
	 */
	public DigitalManuscriptVO getDigitalManuscriptVO() {
		return digitalManuscriptVO;
	}

	/**
	 * @param digitalManuscriptVO the digitalManuscriptVO to set
	 */
	public void setDigitalManuscriptVO(DigitalManuscriptVO digitalManuscriptVO) {
		this.digitalManuscriptVO = digitalManuscriptVO;
	}

	/**
	 * @return the reportPath
	 */
	public String getReportPath() {
		return reportPath;
	}

	/**
	 * @param reportPath the reportPath to set
	 */
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	/**
	 * @return the documentStatus
	 */
	public String getDocumentStatus() {
		return documentStatus;
	}

	/**
	 * @param documentStatus the documentStatus to set
	 */
	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
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
	 public String generateReportCriteria(){
	    	StringBuffer criteriaStr = new StringBuffer("dm.isDeleted = "+(short)0);
	    	if(digitalManuscriptVO != null){
				if(digitalManuscriptVO.getManuscriptId() != null && digitalManuscriptVO.getManuscriptId().length() > 0){
					criteriaStr.append(" and dm.manuscript_id like '%"+digitalManuscriptVO.getManuscriptId()+"%'");
				}if(digitalManuscriptVO.getName() != null && digitalManuscriptVO.getName().length() > 0){
					criteriaStr.append(" and dm.NAME like '%"+digitalManuscriptVO.getName()+"%'");
				}if(digitalManuscriptVO.getCategoryFkId() != null && digitalManuscriptVO.getCategoryFkId() > 0){
					criteriaStr.append(" and dm.categoryFkId="+digitalManuscriptVO.getCategoryFkId());
				}if(digitalManuscriptVO.getOrganisationVO() != null && digitalManuscriptVO.getOrganisationVO().getName() != null && digitalManuscriptVO.getOrganisationVO().getName().trim().length() > 0){
					criteriaStr.append(" and org.NAME ='"+digitalManuscriptVO.getOrganisationVO().getName().trim()+"'");
				}if(digitalManuscriptVO.getDocumentType() != null && digitalManuscriptVO.getDocumentType() > 0){
					criteriaStr.append(" and dm.documentType="+digitalManuscriptVO.getDocumentType());
				}if(ManuscriptWorkType.valueOf(digitalManuscriptVO.getTypeOfWork())!= null && (ManuscriptWorkType.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()) >= 0){
					criteriaStr.append(" and dm.TYPE_OF_WORK="+(ManuscriptWorkType.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()));
				}if(digitalManuscriptVO.getLanguageFkId() != null && digitalManuscriptVO.getLanguageFkId() > 0){
					criteriaStr.append(" and dm.languageFkId="+digitalManuscriptVO.getLanguageFkId());
				}if(digitalManuscriptVO.getScriptFkId() != null && digitalManuscriptVO.getScriptFkId()> 0){
					criteriaStr.append(" and dm.scriptFkId="+digitalManuscriptVO.getScriptFkId());
				}if(DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue() != null && (DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()) >= 0){
					criteriaStr.append(" and mm.material_fkid="+(DocumentStatusEnum.valueOf(digitalManuscriptVO.getTypeOfWork()).getValue()));
				}if(ManuscriptDocumentationType.valueOf(digitalManuscriptVO.getDocumentationOfManuscript()).getValue() != null && (ManuscriptDocumentationType.valueOf(digitalManuscriptVO.getDocumentationOfManuscript()).getValue()) >= 0){
					criteriaStr.append(" and dm.documentation_of_manuscript="+ManuscriptDocumentationType.valueOf(digitalManuscriptVO.getDocumentationOfManuscript()).getValue());
				}if(digitalManuscriptVO.getBeginningLine() != null && digitalManuscriptVO.getBeginningLine().trim().length() > 0){
					criteriaStr.append(" and dm.beginning_line like '%"+digitalManuscriptVO.getBeginningLine().trim()+"%'");
				}if(digitalManuscriptVO.getEndingLine() != null && digitalManuscriptVO.getEndingLine().trim().length() > 0){
					criteriaStr.append(" and dm.ending_line like '%"+digitalManuscriptVO.getEndingLine().trim()+"%'");
				}if(digitalManuscriptVO.getAuthorFKId() != null && digitalManuscriptVO.getAuthorFKId() > 0){
					criteriaStr.append(" and opa.Id ="+digitalManuscriptVO.getAuthorFKId());
				}/*if(digitalManuscriptVO.getManuscriptSubject() != null && digitalManuscriptVO.getManuscriptSubject().length() > 0){
					criteriaStr.append(" and mm.manuscript_subject like '%"+digitalManuscriptVO.getManuscriptSubject()+"%'");
				}if(digitalManuscriptVO.getCategoryFkid() != null && digitalManuscriptVO.getCategoryFkid() > 0){
					criteriaStr.append(" and mm.category_fkid ="+digitalManuscriptVO.getCategoryFkid());
				}if(digitalManuscriptVO.getManuscriptStatus() != null && digitalManuscriptVO.getManuscriptStatus().length() > 0){
					criteriaStr.append(" and mm.manuscript_status ='"+digitalManuscriptVO.getManuscriptStatus()+"'");
				}*/
			}
	    	return criteriaStr.toString();
	    }
	 public void imageTest(){
	        try {
	        	String basePath = ResourceBundle.getBundle("ApplicationResources",IndvenApplicationConstants.LOCALE)
						.getObject("images.system.path").toString();
	        	String fileDBPath = getRequest().getParameter("filedbpath");
	        BufferedImage originalImage;
		    originalImage = ImageIO.read(new File(basePath+fileDBPath));
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
					//inbaos.toByteArray();
			getResponse().getOutputStream().write(imageInByte);
			
	 	}catch(Exception e){
	    	e.printStackTrace();
	    }
	 }

	private HttpServletResponse response = null;
	 @Override
	public void setServletResponse(HttpServletResponse hresponse) {
		// TODO Auto-generated method stub
		 this.setResponse(hresponse);
		
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getExportDocumentType() {
		return exportDocumentType;
	}

	public void setExportDocumentType(String exportDocumentType) {
		this.exportDocumentType = exportDocumentType;
	}

    private static class Worker extends Thread {
        private final Process process;
        private Integer exit;
        private Worker(Process process) {
            this.process = process;
        }
        public void run() {
            try {
                exit = process.waitFor();
            } catch (InterruptedException ignore) {
                return;
            }
        }
    }
}
