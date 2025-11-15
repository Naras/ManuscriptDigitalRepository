package com.indven.util.export;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.math.BigInteger;

public class TableBuilder {
    private int rows = 1, cols = 1;
    private int width=8800;
    private XWPFBorderType innerHBorder = XWPFBorderType.SINGLE;

    public TableBuilder() {
    }

    public TableBuilder size(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        return this;
    }

    public TableBuilder size(int rows, int cols, int width) {
        this.rows = rows;
        this.cols = cols;
        this.width = width;
        return this;
    }

    public XWPFTable createTable(XWPFDocument doc) {
        XWPFTable table = doc.createTable(rows, cols);
        table.setCellMargins(0, 0, 0, 0);
        CTTblWidth cTTblWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        cTTblWidth.setType(STTblWidth.DXA);
        cTTblWidth.setW(BigInteger.valueOf(width));
        table.setInsideHBorder(innerHBorder, 1, 0, "000000");

        return table;
    }

    public TableBuilder setInnerHBorder(XWPFBorderType borderStyle) {
        this.innerHBorder = borderStyle;
        return this;
    }


}
