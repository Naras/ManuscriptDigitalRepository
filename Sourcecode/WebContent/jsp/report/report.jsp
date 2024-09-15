<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.indven.framework.util.IndvenApplicationConstants" %>
<%@ page import="java.util.ResourceBundle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body style="margin:0px;">
 <style>
    .cancel-button{
background-color: #D9534F;
    border-color: #D43F3A;
    color: #FFFFFF;
     border-radius: 6px;
    font-size: 18px;
    line-height: 1.33;
    cursor:pointer;
}
</style>
	<script type="text/javascript">
		/**
		 * This method appends all the parent search parameters
		 * to the links on this page
		 *
		 * @author Rakesh kumar sahoo
		 */
		window.onload = function() {
			var linkString = "";
			
			// Getting all the select field details
			var params = window.parent.document.getElementsByTagName("select");
			for(var i = 0; i < params.length; i++) {
				var id = params[i].id;
				var value = params[i].value;
				if(value == null || value == "-1") {
					value = '';
				}
				if(linkString.length <= 0) {
					linkString += id + "=" + value;
				} else {
					linkString += "&" + id + "=" + value;
				}
			}
			
			// Getting all the input field details
			params = window.parent.document.getElementsByTagName("input");
			for(var i = 0; i < params.length; i++) {
				if(params[i].type != "submit") {
					var id = params[i].id;
					var value = params[i].value;
					if(value == null || value == "NaN") {
						value == '';
					}
					if(linkString.length <= 0) {
						linkString += id + "=" + value;
					} else {
						linkString += "&" + id + "=" + value;
					}
				}
			}

			// Appending this parameters to the links on this page
			linkCollection = document.getElementsByTagName("a");
			for(var i = 0; i < linkCollection.length; i++) {
				var link = linkCollection[i].href;
				var kvp = link.split('&');
					
				if(link.indexOf("?") != -1) {
		    		linkCollection[i].href = link + "&" + linkString;
		    	} else {
		    		linkCollection[i].href = link + "?" + linkString;
		    	}
			}
		}
	
	</script>
			
			<%
			
				@SuppressWarnings("unchecked")
				Map<String, Object> parameters = (Map<String, Object>) request.getAttribute("parameters");
				String jasperPath = (String) request.getAttribute("jasperPath");
				String docType = (String)request.getAttribute("docType");
		
				if(jasperPath != null) {
					File reportFile = new File(request.getServletContext().getRealPath(jasperPath));
				    if (!reportFile.exists())
						throw new JRRuntimeException("Jasper file not found. The report design must be compiled first.");
				
					JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile);
					
					ResourceBundle res = ResourceBundle.getBundle("projecthibernate", IndvenApplicationConstants.LOCALE);
					String url = res.getObject("hibernate.connection.url").toString();
					String username = res.getObject("hibernate.connection.username").toString();
					String password = res.getObject("hibernate.connection.password").toString();
					JasperPrint jasperPrint = null;
					Connection conn = null;
					try {
						Class.forName("com.mysql.jdbc.Driver");
					    conn = DriverManager.getConnection(url, username, password);
						jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
					} catch(Exception e) {
						e.printStackTrace();
					} finally {
						conn.close();
					}
					
					int pageIndex = 0;
					int lastPageIndex = 0;
					
					if (jasperPrint.getPages() != null && jasperPrint.getPages().size() > 0 ) {
						lastPageIndex = jasperPrint.getPages().size() - 1;
						//out.print(request.getAttribute("page"));
	
						String pageStr = request.getParameter("page");
						
						if(pageStr == null) {
							pageStr = "0";
						}
						
						pageIndex = Integer.parseInt(pageStr);
						
						if (pageIndex < 0) {
							pageIndex = 0;
						} else if (pageIndex > lastPageIndex) {
							pageIndex = lastPageIndex;
						}
						
						StringBuffer sbuffer = new StringBuffer();
						
						HtmlExporter exporter = new HtmlExporter();
						exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
						exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
						// Map imagesMap=new HashMap();
						//request.getSession().setAttribute("IMAGES_MAP",imagesMap);
						//exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP,imagesMap);
						//exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?image="); 
						//exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);            
			           // response.setContentType("text/html");
						exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));
						exporter.exportReport(); 
				
				
			%>
		<div style="border:2px solid #DDE3E5;width: 99.3%; border-radius:5px;background-color: #F5FAF3;" align="center">
			<table style="border-width: 0px; width:100% ">
		      <tr style="background-color: #3C763D;">	
		      	<td align="center">
		      		<table>
		      			<tr>
		      			    <td><button style="background-image: url(assets/images/print.jpg);height: 30px; width: 26px;cursor:pointer;" value="Pri" onclick="printDiv();"></button></td>
		      				<td><a href="pdfReport.action?jasperPath=<%=jasperPath%>&docType=<%=docType%>&status=<%=parameters.get("status")%>&type=pdf">
		      					<button style="background-image: url(assets/images/pdf.jpg);height: 30px; width: 26px;cursor:pointer;"></button></a></td>
		      				<td><a href="xlsReport.action?jasperPath=<%=jasperPath%>&docType=<%=docType%>&status=<%=parameters.get("status")%>&type=xls">
		      					<button style="background-image: url(assets/images/xls.png);height: 30px; width: 26px;cursor:pointer;"></button></a></td>
							<%
								if (pageIndex > 0) {
							%>
							        <td><a href="generateReportForNext.action?jasperPath=<%=jasperPath%>&docType=<%=docType%>&status=<%=parameters.get("status")%>&page=0"><img src="<%=request.getContextPath() %>/assets/images/first.GIF"></a></td>
							        <td><a href="generateReportForNext.action?jasperPath=<%=jasperPath%>&docType=<%=docType%>&status=<%=parameters.get("status")%>&page=<%=pageIndex - 1%>"><img src="<%=request.getContextPath() %>/assets/images/previous.GIF"></a></td>
							<%
								} else {
							%>
							        <td><img src="<%=request.getContextPath() %>/assets/images/first_grey.GIF"></td>
							        <td><img src="<%=request.getContextPath() %>/assets/images/previous_grey.GIF"></td>
							<%
								}
							
								if (pageIndex < lastPageIndex) {
							%>
							        <td><a href="generateReportForNext.action?jasperPath=<%=jasperPath%>&docType=<%=docType%>&status=<%=parameters.get("status")%>&page=<%=pageIndex + 1%>"><img src="<%=request.getContextPath() %>/assets/images/next.GIF"></a></td>
							        <td><a href="generateReportForNext.action?jasperPath=<%=jasperPath%>&docType=<%=docType%>&status=<%=parameters.get("status")%>&page=<%=lastPageIndex%>"><img src="<%=request.getContextPath() %>/assets/images/last.GIF"></a></td>
							<%
								} else {
							%>
							        <td><img src="<%=request.getContextPath() %>/assets/images/next_grey.GIF"></td>
							        <td><img src="<%=request.getContextPath() %>/assets/images/last_grey.GIF"></td>
							<%
								}
							%>
		      			</tr>
		      		</table>
		      	</td>
		      	
	      	</tr>	      
			<tr>
			  <td align="center" id="Print">
				<%=sbuffer.toString()%>
			  </td>
			</tr>
			<tr>
			<td align="center">
			<button class="cancel-button" style="height: 30px; width: 90px;" onclick="reportPage()">Cancel</button></td>
			</tr>
		</table>
		</div>
		<%		} else {
					out.println("This search did not return any results");
				}
			}
						
		%>
		<script type="text/javascript">
		<%
		String requestd = (String) request.getAttribute("requestId");
	%>
		function printDiv() {
			var divToPrint = document.getElementById('Print');
			var popupWin = window.open('', '_blank', 'width=800,height=842');
			popupWin.document.open();
			popupWin.document.write('<html><body onload="window.print()">'
					+ divToPrint.innerHTML + '</body></html>');
			popupWin.document.close();
		}
		function reportPage(){
			window.parent.location = '${pageContext.request.contextPath}/goToReportPage.action?requestId='+<%=requestd%>;
			
		}
		</script>
</body>
</html>