package com.indven.util.export;

import com.indven.framework.util.IndvenApplicationConstants;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.imgscalr.Scalr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.imageio.ImageIO;

public class ExportToDetailedReportWord {

    public static ByteArrayOutputStream exportProcessToWord (String filterQuery)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {

            XWPFDocument document = new XWPFDocument();
            XWPFStyles styles = document.createStyles();

            CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(720L));
            pageMar.setTop(BigInteger.valueOf(700L));
            pageMar.setRight(BigInteger.valueOf(720L));
            pageMar.setBottom(BigInteger.valueOf(700L));

        /*    XWPFHeaderFooterPolicy headerFooterPolicy = document.createHeaderFooterPolicy();
            XWPFParagraph headerParagraph = document.createParagraph();
            XWPFHeader defaultHeader = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
           */
            //XWPFHeader header = headerFooterPolicy.getDefaultHeader();
            //XWPFFooter footer = headerFooterPolicy.getDefaultFooter();

            int rows;
            int cols;
            List<HashMap<String,String>> searchDocumentList = searchDocumentList(filterQuery);
            int i=0;
            CellHeaderBuilder cellHeaderBuilder;
            for (HashMap<String,String> documentMap : searchDocumentList) {
                Map<String,HashMap<String,String>> stringHashMapLinkedHashMap = createDataMap( documentMap);
                String documentName = getValueFromStringStringHashMap(documentMap,"NAME");
                String documentAutor = getValueFromStringStringHashMap(documentMap,"Author");

                XWPFParagraph headerParagraph = document.createParagraph();
                ParagraphBuilder paragraphBuilder = new ParagraphBuilder();

                paragraphBuilder.bold(true);
                paragraphBuilder.bottomBorder();
                paragraphBuilder.fontSize(14);
                paragraphBuilder.alignment(ParagraphAlignment.CENTER);
                paragraphBuilder.text(documentName+(StringUtils.isBlank(documentAutor)?"":" - "+documentAutor));
                paragraphBuilder.createParagraph(document);

                LinkedList<ReportTable> reportTableLinkedList = createReportTableDataList(documentMap);
                for (int k=0;k<reportTableLinkedList.size();k++) {
                    ReportTable reportTable = reportTableLinkedList.get(k);
                    String headerName = reportTable.getHeaderName();
                    rows = reportTable.getNoOfRows() + 1;
                    cols = reportTable.getNoOfColumns();
                    XWPFTable table;
                    if ("noheader".equals(headerName) && reportTable.isSingleCell) {
                        table = createTable(document, rows-1, cols, 10800);
                    } else {
                        table = createTable(document, rows, cols, 10800);
                    }
                   /* CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
                    type.setType(STTblLayoutType.AUTOFIT);
                    */
                    CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();

                    width.setType(STTblWidth.DXA);
                    width.setW(BigInteger.valueOf(10800));

                    if (reportTable.isVerticalTable()) {
                        verticalFillTable(table, reportTable.getStringStringLinkedHashMap(), rows, cols);
                    } else if ("images".equals(reportTable.getReportType())) {
                        System.out.println("is images "+reportTable.getImageList().size());
                        if (reportTable.getImageList().size()>0) {
                            XWPFTableCell xwpfTableCell = table.getRow(0).getCell(0);
                            createCellHeader(headerName, xwpfTableCell);
                            for (int im = 0; im < reportTable.getImageList().size(); im++) {
                                //System.out.println("\t\t images for loop");
                                createTableImageCell(table, 1, im, reportTable.getImageList().get(im));
                            }
                            mergeCellHorizontally(table, 0, 0, 2);
                        }
                    } else {
                        if (!"noheader".equals(headerName)) {
                            //cellHeaderBuilder = new CellHeaderBuilder();
                            if (StringUtils.isNotBlank(reportTable.getHeaderName1()) && StringUtils.isNotBlank(reportTable.getHeaderName())  ) {
                                XWPFTableCell xwpfTableCell = table.getRow(0).getCell(0);
                                createCellHeader(headerName,xwpfTableCell);
                                mergeCellHorizontally(table, 0, 0, 1);
                                xwpfTableCell = table.getRow(0).getCell(2);
                                createCellHeader(reportTable.getHeaderName1(),xwpfTableCell);
                                mergeCellHorizontally(table, 0, 2, 3);
                            } else {
                                XWPFTableCell xwpfTableCell = table.getRow(0).getCell(0);
                                createCellHeader(headerName,xwpfTableCell);
                                mergeCellHorizontally(table, 0, 0, cols - 1);
                            }
                            if (reportTable.getStringStringLinkedHashMap().size()>0) {
                                //System.out.println("report table--------------->");
                                horizonatalFillTable(table, reportTable.getStringStringLinkedHashMap(), rows, cols);
                            }
                        }/* else {
                            System.out.println("in no header");
                            horizonatalFillTable(table, reportTable.getStringStringLinkedHashMap(), rows, cols);
                        }*/
                        else if ("noheader".equals(headerName) && reportTable.isSingleCell) {
                            if (reportTable.getStringStringLinkedHashMap().size()>0) {
                                fillSingleColumnTable(table, reportTable.getStringStringLinkedHashMap(), "", document);
                            }
                        } else {
                            horizonatalFillTable(table, reportTable.getStringStringLinkedHashMap(), rows, cols);

                        }

                    }

                    XWPFParagraph breakParagraph = document.createParagraph();
                    setSpacingBetween(breakParagraph,0);
                    breakParagraph.createRun().addBreak();

                }
                i++;
                //document.createParagraph().setPageBreak(true);
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.addCarriageReturn();
                run.addCarriageReturn();
                System.out.println("document no is "+i);
            }
            //FileOutputStream out = new FileOutputStream("/tmp/report11.docx");
            document.write(byteArrayOutputStream);
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;
    }

    public static void createCellHeader(String headerText,XWPFTableCell  xwpfTableCell) {
        CellHeaderBuilder cellHeaderBuilder = new CellHeaderBuilder();
        cellHeaderBuilder.text(headerText);
        cellHeaderBuilder.bold();
        cellHeaderBuilder.createCellHeader(xwpfTableCell);
    }

    public static void setSpacingBetween(XWPFParagraph breakParagraph,int spaces) {
        CTP ctP = breakParagraph.getCTP();
        CTPPr ctPr = ctP.isSetPPr() ? ctP.getPPr() : ctP.addNewPPr();
        CTSpacing ctSpacing = ctPr.isSetSpacing() ? ctPr.getSpacing() : ctPr.addNewSpacing();
        ctSpacing.setLine(new BigInteger(spaces+""));
    }

    public static void createTableImageCell(XWPFTable table,int rowNum,int colNum,String filePath) throws IOException, InvalidFormatException {
        //xwpfTableCell = rows.getCell(0);
        //System.out.println(" rownum "+rowNum+" cellnum "+colNum);
        XWPFTableCell xwpfTableCell  =
                table.getRow(rowNum).getCell(colNum);
        xwpfTableCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);


        XWPFParagraph paragraph = xwpfTableCell.addParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        XWPFRun run = paragraph.createRun();



        String imageFilePath = imageFolderPath+filePath;
        System.out.println("\t\timageFilePath "+imageFilePath);
        File file = new File(imageFilePath);
        System.out.println("\t\t---> "+file.exists());
        if (StringUtils.isNotBlank(filePath) && file.exists() && !FilenameUtils.isExtension(file.getName(), "pdf")) {
            //FileInputStream fis = new FileInputStream(imageFilePath);
            //index = student.getImagePath().lastIndexOf('\\') + 1;
            //imageName = student.getImagePath().substring(index);
            //String imageName = FilenameUtils.getName()
            run.addPicture(resizeImage(imageFilePath,""), XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(100), Units.toEMU(100));
        }
        xwpfTableCell.removeParagraph(0);
    }

    public static void setSingleLineSpacing(XWPFParagraph para) {
        CTPPr ppr = para.getCTP().getPPr();
        if (ppr == null) ppr = para.getCTP().addNewPPr();
        CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
        spacing.setAfter(BigInteger.valueOf(0));
        spacing.setBefore(BigInteger.valueOf(0));
        spacing.setLineRule(STLineSpacingRule.AUTO);
        spacing.setLine(BigInteger.valueOf(240));
    }

    private static XWPFTable createTable(XWPFDocument document,int rows,int cols,int tableWidth) {
        XWPFTable table = document.createTable(rows, cols);
        CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
        //width.setType(STTblWidth.DXA);
        width.setW(BigInteger.valueOf(tableWidth));
        return table;
    }
    public static void createTableCell(XWPFTableCell cell,String cellText) {
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFParagraph para = cell.addParagraph();
        para.setVerticalAlignment(TextAlignment.CENTER);
        XWPFRun xwpfRunWPFRun = para.createRun();
        xwpfRunWPFRun.setText(cellText);
        xwpfRunWPFRun.setFontFamily("Arial");
        xwpfRunWPFRun.setFontSize(8);
        cell.removeParagraph(0);
    }
    private static void verticalFillTable(XWPFTable table,Map<String,String> subMap,int noOfRows,int noColumns) {
        int rowEntryIndex=0;
        //System.out.println("no of rows "+noOfRows+" no col "+noColumns);\
        table.removeRow(0);
        for (int j=0;j<noOfRows-1;j++) {
            for (int i=0;i<noColumns;i++) {
                XWPFTableRow xwpfTableRow = table.getRow(j+1);
                //System.out.println("\t\t\t i"+i+" j "+j+" % is "+(j%2));
                if (j%2==0) {
                    //System.out.println("is even ");

                    //XWPFTableCell cell = table.getRow(j+1).getCell(i);
                    XWPFTableCell cell = xwpfTableRow.getCell(i);
                    Map.Entry<String, String> rowEntry = CollectionUtils.get(subMap, i+j);
                    //System.out.println(" \t\tR"+j+"C" + i +" rowEntry " + rowEntry.getKey());

                    CellHeaderBuilder cellHeaderBuilder = new CellHeaderBuilder();
                    cellHeaderBuilder.text(rowEntry.getKey());
                    cellHeaderBuilder.bold();
                    cellHeaderBuilder.createCellHeader(cell);
                } else if (j%2>0) {
                    //System.out.println("\t\t\t\tis odd "+(i+j));
                    XWPFTableCell cell1 = table.getRow(j+1).getCell(i);
                    int mapIndex = i-j;
                    Map.Entry<String, String> rowEntry = CollectionUtils.get(subMap, i+(j-1));
                    String cellContent = rowEntry.getValue().replaceAll("\r","");
                    //System.out.println("cellContent--------------- " +cellContent);
                    if (StringUtils.isNotBlank(cellContent) && (cellContent.contains("\n") || cellContent.contains("\r"))) {
                        //System.out.println("contains new line char------------- ");
                        cell1.removeParagraph(0);
                        cell1.setText(cellContent);
                    } else {
                        createTableCell(cell1, cellContent);
                        cell1.removeParagraph(0);
                    }
                }


            }
        }

    }

    private static void horizonatalFillTable(XWPFTable table,Map<String,String> subMap,int noOfRows,int noColumns) {
        int rowEntryIndex=0;
        for (int rowIndex = 1; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            //System.out.println("\tnoColumns " + noColumns + " subMap " + subMap.size() + " rowIndex " + rowIndex+" cells size "+row.getTableCells().size());
            int i = 1;
            int cindex=0;
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                //System.out.println("\t\trowEntryIndex "+rowEntryIndex);
                Map.Entry<String, String> rowEntry = CollectionUtils.get(subMap, rowEntryIndex);
                XWPFTableCell cell = row.getCell(colIndex);

                CellHeaderBuilder cellHeaderBuilder = new CellHeaderBuilder();

                cellHeaderBuilder.text(rowEntry.getKey());
                cellHeaderBuilder.bold();
                cellHeaderBuilder.createCellHeader(cell);
                //System.out.println("\t\tcell index is " + cindex +" key is "+ rowEntry.getKey());

                cindex+=1;
                XWPFTableCell cell1 = row.getCell(colIndex+1);
                String cellContent = rowEntry.getValue();
                if (StringUtils.isNotBlank(cellContent) && (cellContent.contains("\n") || cellContent.contains("\r"))) {
                    //System.out.println("contains new line char------------- ");
                    cell1.removeParagraph(0);
                    //cell1.setText(rowEntry.getValue().replaceAll("\r",""));
                    createTableCell(cell1, rowEntry.getValue());
                } else {
                    createTableCell(cell1, rowEntry.getValue());
                }
                //System.out.println("\t\tcell index for is " + cindex +" value is "+ rowEntry.getValue());
                colIndex+=1;
                rowEntryIndex++;
            }
        }
    }

    private static void createParaGraphImage(XWPFDocument document) {
        XWPFParagraph image = document.createParagraph();
        image.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun imageRun = image.createRun();
        imageRun.setTextPosition(20);
        /*Path imagePath = Paths.get(ClassLoader.getSystemResource(logo).toURI());
        imageRun.addPicture(Files.newInputStream(imagePath),
                XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),
                Units.toEMU(50), Units.toEMU(50));*/
    }

    private static void fillSingleColumnTable(XWPFTable table,Map<String,String> subMap,String tableTitle,XWPFDocument document) {
        //System.out.println("tableTitle "+tableTitle+" no of rows "+table.getNumberOfRows());
        //table.getRow(1).getCell(0).setText(tableTitle);
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            //System.out.println("\trowindex "+rowIndex);
            XWPFTableRow row = table.getRow(rowIndex);
            /*for (Map.Entry<String,String> subEntry : subMap.entrySet()) {
                System.out.println("\t\t sub entry "+subEntry.getKey()+" value "+subEntry.getValue());
            }*/
            Map.Entry<String,String> rowEntry = CollectionUtils.get(subMap,rowIndex);

            XWPFTableCell cell = row.getCell(0);
            String cellContent = rowEntry.getValue().replaceAll("\r","");
            //createTableCell(cell,rowEntry.getKey()+":\n "+cellContent);
            //System.out.println("cellContent---> "+cellContent);

            //XWPFParagraph headerParagraph = document.createParagraph();

            ParagraphBuilder paragraphBuilder = new ParagraphBuilder();

            paragraphBuilder.bold(true);
            paragraphBuilder.alignment(ParagraphAlignment.LEFT);
            paragraphBuilder.text(rowEntry.getKey());
            paragraphBuilder.createCellText(cell);

            paragraphBuilder = new ParagraphBuilder();

            //paragraphBuilder.bold(true);
            paragraphBuilder.alignment(ParagraphAlignment.LEFT);
            paragraphBuilder.text(cellContent);
            paragraphBuilder.createCellText(cell);
        }
    }

    private static void fillTable(XWPFTable table,Map<String,String> subMap,String tableTitle) {
        ParagraphBuilder paragraphBuilder = new ParagraphBuilder();
        System.out.println("tableTitle "+tableTitle+" no of rows "+table.getNumberOfRows());
        //table.getRow(1).getCell(0).setText(tableTitle);
        for (int rowIndex = 1; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            /*for (Map.Entry<String,String> subEntry : subMap.entrySet()) {
                System.out.println("\t\t sub entry "+subEntry.getKey()+" value "+subEntry.getValue());
            }*/


            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                Map.Entry<String,String> rowEntry = CollectionUtils.get(subMap,colIndex);
                XWPFTableCell cell = row.getCell(colIndex);
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                XWPFParagraph para = cell.addParagraph();
                para.setVerticalAlignment(TextAlignment.CENTER);
                para.createRun().setText(rowEntry.getKey());
                cell.removeParagraph(0);

            }
        }
    }

    private static void fillTable(XWPFTable table) {
        ParagraphBuilder paragraphBuilder = new ParagraphBuilder();
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);

            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                //cell.setText("ssadd");
                XWPFParagraph para = cell.addParagraph();
               /* paragraphBuilder.createCellText(cell);
                paragraphBuilder.bold(true);
                paragraphBuilder.text(" cell " + rowIndex + colIndex);*/
                para.setVerticalAlignment(TextAlignment.CENTER);
                para.createRun().setText(" cell " + rowIndex + colIndex);
                cell.removeParagraph(0);
            }
        }
    }

    private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {

        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if ( rowIndex == fromRow ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        for(int colIndex = fromCol; colIndex <= toCol; colIndex++){
            CTHMerge hmerge = CTHMerge.Factory.newInstance();
            if(colIndex == fromCol){
                // The first merged cell is set with RESTART merge value
                hmerge.setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                hmerge.setVal(STMerge.CONTINUE);
            }
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            // Try getting the TcPr. Not simply setting an new one every time.
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setHMerge(hmerge);
            } else {
                // only set an new TcPr if there is not one already
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setHMerge(hmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }

    static Connection conn;
    static String imageFolderPath;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ResourceBundle res = ResourceBundle.getBundle("projecthibernate", IndvenApplicationConstants.LOCALE);
            String url = res.getObject("hibernate.connection.url").toString();
            String username = res.getObject("hibernate.connection.username").toString();
            String password = res.getObject("hibernate.connection.password").toString();
            conn = DriverManager.getConnection(url, username, password);
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        imageFolderPath = (ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
                .getObject("images.system.path").toString()).trim();
        System.out.println("ExportToDetailedReportWord folderPath "+imageFolderPath);
        //File file = new File(FilenameUtils.separatorsToSystem(folderPath+ "/"+imagePath));
    }

    public static LinkedList<String> getFrameImageList(String parentid) {
        ResultSet resultSet = null;
        Statement statement = null;
        LinkedList<String> filePathList = new LinkedList<String>();
       /*String query =" SELECT filePath FROM omds_digital_manuscript_frame WHERE digitalManuscriptFkId="+parentid+" AND frame_order =1) AS frame1, "+
        " (SELECT filePath FROM omds_digital_manuscript_frame WHERE digitalManuscriptFkId="+parentid+" AND frame_order =2) AS frame2, "+
       " (SELECT filePath FROM omds_digital_manuscript_frame WHERE digitalManuscriptFkId="+parentid+" AND frame_order =3) AS frame3 ";*/
        String query = "SELECT filePath FROM omds_digital_manuscript_frame where digitalManuscriptFkId="+parentid+" order by frame_order LIMIT 3";
        System.out.println("query "+query);
        //Connection conn=null;
        try {
            /*Class.forName("com.mysql.jdbc.Driver");

            ResourceBundle res = ResourceBundle.getBundle("projecthibernate", IndvenApplicationConstants.LOCALE);
            String url = res.getObject("hibernate.connection.url").toString();
            String username = res.getObject("hibernate.connection.username").toString();
            String password = res.getObject("hibernate.connection.password").toString();*/


           /* Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mdr?autoReconnect=true&characterEncoding=UTF-8";
            String username = "root";
            String password = "password";*/

            // conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            int fetchSize = statement.getFetchSize();
            // Set the fetch size on the statement
            //statement.setFetchSize(100);

            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                System.out.println("filepath "+resultSet.getString(1));
                filePathList.add(resultSet.getString(1));

            }
            //resultSet.setFetchSize(100);

          /*  searchDocumentList = convertResultSetToList(resultSet);
            System.out.println(searchDocumentList.size());*/

        } catch (Exception e) {
            System.out.println("exception "+e);
            e.printStackTrace();
        } finally {
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
        return filePathList;
    }


    public static LinkedList<ReportTable> createReportTableDataList(HashMap<String,String> documentMap) throws IOException {
        //Information
        LinkedList<ReportTable> reportTablesList = new LinkedList<ReportTable>();
        ReportTable reportTable = new ReportTable();

        reportTable.setHeaderName("Information");
        reportTable.setNoOfColumns(4);
        reportTable.setNoOfRows(3);

        LinkedHashMap<String, String> datas = new LinkedHashMap<String, String>();
        datas.put("Document ID :", getValueFromStringStringHashMap(documentMap, "manuscript_id"));
        datas.put("Document Name :", getValueFromStringStringHashMap(documentMap, "NAME"));
        datas.put("Accession No. :", getValueFromStringStringHashMap(documentMap, "acc_no"));
        datas.put("Diacritical Name :", getValueFromStringStringHashMap(documentMap, "diacritical_name"));
        datas.put("Bundle:", getValueFromStringStringHashMap(documentMap, "bundleName") == null ? "N/A" : getValueFromStringStringHashMap(documentMap, "bundleName"));
        datas.put("Vernacular Name :", getValueFromStringStringHashMap(documentMap, "regional_name"));
        reportTable.setStringStringLinkedHashMap(datas);
        reportTablesList.add(reportTable);

        String documentType = getValueFromStringStringHashMap(documentMap, "documentType");
        System.out.println(" documentType ------------- "+documentType);

        //--Work Details
        reportTable = new ReportTable();
        reportTable.setHeaderName("Work Details");
        reportTable.setNoOfColumns(6);
        reportTable.setNoOfRows(2);
        datas = new LinkedHashMap<String, String>();
        datas.put("Language:", getValueFromStringStringHashMap(documentMap, "languageName"));
        datas.put("Script:", getValueFromStringStringHashMap(documentMap, "scriptName"));
        datas.put("Subject :", getValueFromStringStringHashMap(documentMap, "categoryName"));
        String materialName = getValueFromStringStringHashMap(documentMap, "materialName");
        materialName = (materialName.equals("2")) ? "Paper" : (materialName.equals("1")) ? "Palm Leaf" : (materialName.equals("3")) ? "BrichWood" : "N/A";
        datas.put("Material :", materialName);

        String TYPE_OF_WORK = getValueFromStringStringHashMap(documentMap, "TYPE_OF_WORK");
        TYPE_OF_WORK = (TYPE_OF_WORK.equals("1")) ? "Poetry" : (TYPE_OF_WORK.equals("2")) ? "Prose" : (TYPE_OF_WORK.equals("3")) ? "Poem And Prose" : (TYPE_OF_WORK.equals("4")) ? "Compendium" : "N/A";
        //System.out.println(" TYPE_OF_WORK after "+TYPE_OF_WORK);
        datas.put("Type:", TYPE_OF_WORK);
        datas.put("Specific Category:", getValueFromStringStringHashMap(documentMap, "specificcategory"));
        reportTable.setStringStringLinkedHashMap(datas);
        reportTablesList.add(reportTable);

        //Summary Table and Table of Contents
        reportTable = new ReportTable();
        reportTable.setHeaderName("noheader");
        reportTable.setNoOfColumns(1);

        reportTable.setVerticalTable(false);
        reportTable.setSingleCell(true);
        datas = new LinkedHashMap<String, String>();
        int noRows = 0;
        if (getValueFromStringStringHashMap(documentMap, "SUMMARY").trim().length() > 0) {
            datas.put("Summary :", getValueFromStringStringHashMap(documentMap, "SUMMARY"));
            noRows++;
        }
        if (getValueFromStringStringHashMap(documentMap, "table_of_contents").trim().length() > 0) {
            datas.put("Table Of Contents :", getValueFromStringStringHashMap(documentMap, "table_of_contents"));
            noRows++;
        }
        reportTable.setNoOfRows(noRows);
        reportTable.setStringStringLinkedHashMap(datas);
        reportTablesList.add(reportTable);

        reportTable = new ReportTable();
        reportTable.setHeaderName("noheader");
        reportTable.setNoOfColumns(4);
        reportTable.setNoOfRows(1);
        reportTable.setVerticalTable(false);
        //reportTable.setSingleCell(true);
        datas = new LinkedHashMap<String, String>();
        String author =getValueFromStringStringHashMap(documentMap,"Author");
        author = author==null?"N/A":author;
        datas.put("Authors",author);

        String scribName = getValueFromStringStringHashMap(documentMap,"scribName");
        scribName = scribName==null?"N/A":scribName;
        datas.put("Scribe",scribName);
        reportTable.setStringStringLinkedHashMap(datas);
        reportTablesList.add(reportTable);
        //stringHashMapLinkedHashMap.put("NH",datas);

        //Frames
        reportTable = new ReportTable();
        reportTable.setHeaderName("frames");
        reportTable.setNoOfColumns(3);
        reportTable.setNoOfRows(1);
        reportTable.setVerticalTable(false);

        reportTable.setReportType("images");
        datas = new LinkedHashMap<String, String>();
        LinkedList<String> filePathList = getFrameImageList(getValueFromStringStringHashMap(documentMap,"Id"));
        for (String filePath : filePathList) {

        }
        reportTable.setImageList(filePathList);
        reportTable.setStringStringLinkedHashMap(new LinkedHashMap<String, String>());
        reportTablesList.add(reportTable);


        //Subject details
        System.out.println("documentType----- "+documentType);
        if ("2".equalsIgnoreCase(documentType)) {
            boolean isTableRequired=false;
            datas = new LinkedHashMap<String, String>();
            String isBound = getValueFromStringStringHashMap(documentMap, "isBound");
            System.out.println("stage1");
            //System.out.println("isBound ------- "+isBound);
            /*if (StringUtils.isNotBlank(isBound) || isBound.length()>0) {
                isTableRequired=true;
            }*/
            isTableRequired = checkForValuePresent(isBound);
            //System.out.println("isTableRequired------------ "+isTableRequired);
            isBound = isBound.equals("0") ? "No" : "Yes";
            datas.put("Bound :", isBound);
            System.out.println("stage2");
            String catalogNo = getValueFromStringStringHashMap(documentMap, "catalogue_no");
            System.out.println("stage3");
            isTableRequired = checkForValuePresent(catalogNo);
            datas.put("Catalogue No.:", catalogNo);

            String digitizedBy = getValueFromStringStringHashMap(documentMap, "digitized_by");
            isTableRequired = checkForValuePresent(digitizedBy);
            digitizedBy = digitizedBy == null ? "N/A" : digitizedBy;
            datas.put("Digitized By. :", digitizedBy);
            System.out.println("stage4");
            String documentation = getValueFromStringStringHashMap(documentMap, "documentation");
            isTableRequired = checkForValuePresent(documentation);
            documentation = (documentation.equals("1")) ? "Complete" : (documentation.equals("2")) ? "Ongoing" : "N/A";
            datas.put("Documentation :", documentation);
            System.out.println("stage5");
            String totalNoOfFolios = getValueFromStringStringHashMap(documentMap, "total_no_of_folios");
            isTableRequired = checkForValuePresent(totalNoOfFolios);
            datas.put("No Of Folies :", totalNoOfFolios);
            datas.put("Colophone :", getValueFromStringStringHashMap(documentMap, "colophon}"));

            String sourceOfCatalogue = getValueFromStringStringHashMap(documentMap, "source_of_catalogue");
            isTableRequired = checkForValuePresent(sourceOfCatalogue);
            sourceOfCatalogue = (sourceOfCatalogue.equals("1")) ? "Descriptive" : (sourceOfCatalogue.equals("2")) ? "Handllist" : (sourceOfCatalogue.equals("3")) ? "Triennial" : (sourceOfCatalogue.equals("4")) ? "Alphabetical" : "N/A";
            datas.put("Source Of Catalogue :", sourceOfCatalogue);

            String catalogueDetails = getValueFromStringStringHashMap(documentMap, "cataloguedetails");
            isTableRequired = checkForValuePresent(catalogueDetails);
            datas.put("Catalogue Details :", catalogueDetails);

            String totalNoOfMaps = getValueFromStringStringHashMap(documentMap, "total_no_of_maps");
            isTableRequired = checkForValuePresent(totalNoOfMaps);
            datas.put("No. Of Illustrations :", getValueFromStringStringHashMap(documentMap, "total_no_of_maps"));

            String beginningLine = getValueFromStringStringHashMap(documentMap, "beginning_line");
            isTableRequired = checkForValuePresent(beginningLine);
            datas.put("Begining Line :",beginningLine);

            String endingLine = getValueFromStringStringHashMap(documentMap, "ending_line");
            isTableRequired = checkForValuePresent(endingLine);
            datas.put("Ending Line :", endingLine);

            String manuScript = getValueFromStringStringHashMap(documentMap, "condition_of_manuscript");
            isTableRequired = checkForValuePresent(manuScript);
            manuScript = (manuScript.equals("1")) ? "Good" : (manuScript.equals("2")) ? "Bad" : (manuScript.equals("3")) ? "Brittle" : "N/A";
            datas.put("Manuscript :", manuScript);
            System.out.println("final isTableRequired------- "+isTableRequired);
            if (isTableRequired) {
                reportTable = new ReportTable();
                reportTable.setHeaderName("Subject Details");
                reportTable.setNoOfColumns(4);
                reportTable.setNoOfRows(4);
                reportTable.setVerticalTable(false);

                reportTable.setStringStringLinkedHashMap(datas);
                reportTablesList.add(reportTable);
            }

        }
        //--Subject Details End

        //Source Details
        reportTable = new ReportTable();
        reportTable.setHeaderName("Source Details");
        reportTable.setNoOfColumns(4);
        reportTable.setNoOfRows(4);
        reportTable.setVerticalTable(false);
        reportTable.setHeaderName1("Publication Details");
        datas = new LinkedHashMap<String, String>();
        datas.put("Name:",getValueFromStringStringHashMap(documentMap,"orgName"));
        datas.put("Address:",getValueFromStringStringHashMap(documentMap,"orgAddress"));
        datas.put("Phone:",getValueFromStringStringHashMap(documentMap,"phoneNumber"));
        datas.put("Email ID :",getValueFromStringStringHashMap(documentMap,"email"));
        datas.put("Website",getValueFromStringStringHashMap(documentMap,"website"));
        datas.put("Name :",getValueNAFromStringStringHashMap(documentMap,"publisherName"));
        datas.put("Address :",getValueNAFromStringStringHashMap(documentMap,"publisherAddress"));
        datas.put("Editor Name:",getValueNAFromStringStringHashMap(documentMap,"editorName"));
        datas.put("No Of Pages :",getValueNAFromStringStringHashMap(documentMap,"NO_OF_PAGES"));
        datas.put("Price :",getValueNAFromStringStringHashMap(documentMap,"PRICE"));
        datas.put("Year :",getValueNAFromStringStringHashMap(documentMap,"YEAR_OF_PUBLICATION"));
        reportTable.setStringStringLinkedHashMap(datas);
        reportTablesList.add(reportTable);
        return reportTablesList;
    }

    public static LinkedHashMap<String,HashMap<String,String>> createDataMap(HashMap<String,String> documentMap) throws IOException {
        //Information
        LinkedHashMap<String,HashMap<String,String>> stringHashMapLinkedHashMap = new LinkedHashMap<String,HashMap<String,String>>();
        LinkedHashMap<String, String> datas = new LinkedHashMap<String, String>();
        datas.put("Document ID :",getValueFromStringStringHashMap(documentMap,"manuscript_id"));
        datas.put("Document Name :",getValueFromStringStringHashMap(documentMap,"NAME"));
        datas.put("Accession No. :",getValueFromStringStringHashMap(documentMap,"acc_no"));
        datas.put("Diacritical Name :",getValueFromStringStringHashMap(documentMap,"diacritical_name"));
        datas.put("Bundle:",getValueFromStringStringHashMap(documentMap,"bundleName")==null?"N/A":getValueFromStringStringHashMap(documentMap,"bundleName"));
        datas.put("Vernacular Name :",getValueFromStringStringHashMap(documentMap,"regional_name"));
        stringHashMapLinkedHashMap.put("Information",datas);

        //--Work Details
        datas = new LinkedHashMap<String, String>();
        datas.put("Language:",getValueFromStringStringHashMap(documentMap,"languageName"));
        datas.put("Sanskrit:",getValueFromStringStringHashMap(documentMap,"scriptName"));
        datas.put("Subject :",getValueFromStringStringHashMap(documentMap,"categoryName"));
        String materialName =getValueFromStringStringHashMap(documentMap,"materialName");
        materialName =  (materialName.equals("2"))?"Paper":(materialName.equals("1"))?"Palm Leaf":(materialName.equals("3"))?"BrichWood":"N/A";
        datas.put("Material :",materialName);

        //System.out.println("Type of Work "+getValueFromStringStringHashMap(documentMap,"TYPE_OF_WORK"));
        String TYPE_OF_WORK=getValueFromStringStringHashMap(documentMap,"TYPE_OF_WORK");
        TYPE_OF_WORK= (TYPE_OF_WORK.equals("1"))?"Poetry":(TYPE_OF_WORK.equals("2"))?"Prose":(TYPE_OF_WORK.equals("3"))?"Poem And Prose":(TYPE_OF_WORK.equals("4"))?"Compendium":"N/A";
        //System.out.println(" TYPE_OF_WORK after "+TYPE_OF_WORK);
        datas.put("Type:", TYPE_OF_WORK);
        datas.put("Specific Category:",getValueFromStringStringHashMap(documentMap,"specificcategory"));
        stringHashMapLinkedHashMap.put("Work Details",datas);

        //Summary Table and Table of Contents
        datas = new LinkedHashMap<String, String>();
        datas.put("Summary :",getValueFromStringStringHashMap(documentMap,"SUMMARY"));
        datas.put("Table Of Contents :", getValueFromStringStringHashMap(documentMap,"table_of_contents"));

        String author =getValueFromStringStringHashMap(documentMap,"Author");
        author = author==null?"N/A":author;
        datas.put("Authors",author);

        String scribName = getValueFromStringStringHashMap(documentMap,"");
        scribName = scribName==null?"N/A":scribName;
        datas.put("Scribe",scribName);
        stringHashMapLinkedHashMap.put("NO Header",datas);


        String isBound =getValueFromStringStringHashMap(documentMap,"isBound");
        isBound = isBound.equals("0")?"No":"Yes";
        datas.put("bound",isBound);

        datas.put("catalogNo",getValueFromStringStringHashMap(documentMap,"catalogue_no"));

        String digitizedBy =getValueFromStringStringHashMap(documentMap,"digitized_by");
        digitizedBy = digitizedBy==null?"N/A":digitizedBy;
        datas.put("digitizedBy",digitizedBy);

        String documentation = getValueFromStringStringHashMap(documentMap,"documentation");
        documentation = (documentation.equals("1"))?"Complete":(documentation.equals("2"))?"Ongoing":"N/A";
        datas.put("documentation",documentation);

        datas.put("noofFolies",getValueFromStringStringHashMap(documentMap,"total_no_of_folios"));
        datas.put("colophone",getValueFromStringStringHashMap(documentMap,"colophon}"));

        String sourceOfCatalogue =getValueFromStringStringHashMap(documentMap,"source_of_catalogue");
        sourceOfCatalogue=  (sourceOfCatalogue.equals("1"))?"Descriptive":(sourceOfCatalogue.equals("2"))?"Handllist":(sourceOfCatalogue.equals("3"))?"Triennial":(sourceOfCatalogue.equals("4"))?"Alphabetical":"N/A";
        datas.put("sourceOfCatalogue",sourceOfCatalogue);

        datas.put("catalogDetails",getValueFromStringStringHashMap(documentMap,"cataloguedetails"));
        datas.put("noOfIllutrations",getValueFromStringStringHashMap(documentMap,"total_no_of_maps"));
        datas.put("beginingLine",getValueFromStringStringHashMap(documentMap,"beginning_line"));
        datas.put("endingLine",getValueFromStringStringHashMap(documentMap,"ending_line"));

        String manuScript = getValueFromStringStringHashMap(documentMap,"condition_of_manuscript");
        manuScript = (manuScript.equals("1"))?"Good":(manuScript.equals("2"))?"Bad":(manuScript.equals("3"))?"Brittle":"N/A";
        datas.put("manuScript",manuScript);

        datas.put("sourceName",getValueFromStringStringHashMap(documentMap,"orgName"));
        datas.put("sourceAddress",getValueFromStringStringHashMap(documentMap,"orgAddress"));
        datas.put("sourcePhone",getValueFromStringStringHashMap(documentMap,"phoneNumber"));
        datas.put("sourceEmail",getValueFromStringStringHashMap(documentMap,"email"));
        datas.put("sourceWebsite",getValueFromStringStringHashMap(documentMap,"website"));
        datas.put("publicationName",getValueNAFromStringStringHashMap(documentMap,"publisherName"));
        datas.put("publicationAddress",getValueNAFromStringStringHashMap(documentMap,"publisherAddress"));
        datas.put("publicationEditorName",getValueNAFromStringStringHashMap(documentMap,"editorName"));
        datas.put("publicationNoOfPage",getValueNAFromStringStringHashMap(documentMap,"NO_OF_PAGES"));
        datas.put("publicationPrice",getValueNAFromStringStringHashMap(documentMap,"PRICE"));
        datas.put("publicationYear",getValueNAFromStringStringHashMap(documentMap,"YEAR_OF_PUBLICATION"));
        return stringHashMapLinkedHashMap;
    }


    public static String getValueFromStringStringHashMap(HashMap<String,String> stringStringHashMap, String key){
        String keyValue = stringStringHashMap.containsKey(key)?stringStringHashMap.get(key):"";
        return keyValue;
    }

    public static String getValueNAFromStringStringHashMap(HashMap<String,String> stringStringHashMap, String key){
        String keyValue = stringStringHashMap.containsKey(key)?stringStringHashMap.get(key):"N/A";
        keyValue = keyValue==null?"N/A":keyValue;
        return keyValue;
    }

    public static java.util.List<HashMap<String,String>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        java.util.List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        while (rs.next()) {
            HashMap<String,String> row = new HashMap<String, String>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnLabel(i),rs.getString(i));
            }
            list.add(row);
            //break;
        }
        return list;
    }

    public static List<HashMap<String,String>> searchDocumentList(String filterQuery) {
        ResultSet resultSet = null;
        Statement statement = null;
        List<HashMap<String,String>> searchDocumentList = new ArrayList<HashMap<String, String>>();
        String query = "SELECT dm.Id,dm.NAME,dm.regional_name,dm.diacritical_name,dm.SUMMARY,dm.acc_no,dm.table_of_contents,dm.digitized_by,dm.catalogue_no,\n" +
                "dm.cataloguedetails,dm.colophon,dm.beginning_line,dm.ending_line,dm.documentation_of_manuscript,dm.isbound,dm.manuscript_id,\n" +
                "dm.total_no_of_folios,dm.total_no_of_maps,dm.condition_of_manuscript,dm.source_of_catalogue,dm.TYPE_OF_WORK,org.Id AS orgId,org.NAME AS orgName,\n" +
                "org.email,org.ADDRESS AS orgAddress,org.website,org.type AS orgType,org.phoneNumber,op.Id AS publicationId,op.PRICE,op.NO_OF_PAGES,\n" +
                "op.YEAR_OF_PUBLICATION,opp.NAME AS editorName,opb.NAME AS publisherName,opb.ADDRESS publisherAddress,ol.NAME AS languageName,\n" +
                "os.NAME AS scriptName,om.NAME materialName,ob.name AS bundleName,oc.name AS categoryName,opc.NAME AS scribName,\n" +
                "GROUP_CONCAT(opa.NAME) AS Author,GROUP_CONCAT(DISTINCT osc.NAME) AS specificcategory,dm.documentType FROM omds_digital_manuscript dm\n" +
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
                "ORDER BY dm.NAME";
        //and dm.acc_no='S16-3-21'
        System.out.println("query "+query);
        try {

            /*hibernate.connection.url =  jdbc:mysql://localhost:3306/mdr?autoReconnect=true&characterEncoding=UTF-8
            hibernate.connection.username =  root
            hibernate.connection.password =  password*/

            /*Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mdr?autoReconnect=true&characterEncoding=UTF-8";
            String username = "root";
            String password = "password";

            conn = DriverManager.getConnection(url, username, password);*/
            statement = conn.createStatement();
            int fetchSize = statement.getFetchSize();
            System.out.println(fetchSize);
            // Set the fetch size on the statement
            statement.setFetchSize(100);

            resultSet = statement.executeQuery(query);
            resultSet.setFetchSize(100);

            searchDocumentList = convertResultSetToList(resultSet);
            System.out.println(searchDocumentList.size());

        } catch (Exception e) {
            System.out.println("exception "+e);
            e.printStackTrace();
        } finally {
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }/*
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
        return searchDocumentList;
    }
    public static InputStream resizeImage(String filePath,String name) throws IOException {
        //String imgFile = "/home/lakshmi/workspace/manuscripts/img/img/2016/7/19/1156/1468926090347221468925873171P_0478.jpg";
        BufferedImage srcImage = ImageIO.read(new File(filePath)); // Load image
        String fileExtensionType = FilenameUtils.getExtension(filePath);
        BufferedImage scaledImage = Scalr.resize(srcImage, 600); // Scale image
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, fileExtensionType, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    public static boolean checkForValuePresent(String propertyValue) {
        boolean isValuePresent=false;
        System.out.println("propertyValue "+propertyValue);
        if (StringUtils.isNotBlank(propertyValue) && propertyValue!=null && propertyValue.length()>0) {
            isValuePresent=true;
        }
        return isValuePresent;
    }


}
