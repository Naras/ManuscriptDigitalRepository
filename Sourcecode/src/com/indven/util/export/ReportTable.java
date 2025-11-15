package com.indven.util.export;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ReportTable {
    String headerName;
    int noOfRows;
    int noOfColumns;
    String headerName1;
    String reportType;

    public boolean isSingleCell() {
        return isSingleCell;
    }

    public void setSingleCell(boolean singleCell) {
        isSingleCell = singleCell;
    }

    boolean isSingleCell=false;

    public LinkedList<String> getImageList() {
        return imageList;
    }

    public void setImageList(LinkedList<String> imageList) {
        this.imageList = imageList;
    }

    LinkedList<String> imageList= new LinkedList<String>();

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getHeaderName1() {
        return headerName1;
    }

    public void setHeaderName1(String headerName1) {
        this.headerName1 = headerName1;
    }

    LinkedHashMap<String,String> stringStringLinkedHashMap;

    String documentNameAndAuthor;
    public String getDocumentNameAndAuthor() {
        return documentNameAndAuthor;
    }

    public void setDocumentNameAndAuthor(String documentNameAndAuthor) {
        this.documentNameAndAuthor = documentNameAndAuthor;
    }

    boolean isVerticalTable=false;


    public boolean isVerticalTable() {
        return isVerticalTable;
    }

    public void setVerticalTable(boolean verticalTable) {
        isVerticalTable = verticalTable;
    }



    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    public int getNoOfColumns() {
        return noOfColumns;
    }

    public void setNoOfColumns(int noOfColumns) {
        this.noOfColumns = noOfColumns;
    }

    public LinkedHashMap<String, String> getStringStringLinkedHashMap() {
        return stringStringLinkedHashMap;
    }

    public void setStringStringLinkedHashMap(LinkedHashMap<String, String> stringStringLinkedHashMap) {
        this.stringStringLinkedHashMap = stringStringLinkedHashMap;
    }


}
