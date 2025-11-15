package com.indven.util.export;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class CellHeaderBuilder {
    private String text;
    private boolean isBold = false;
    private ParagraphAlignment alignment = ParagraphAlignment.LEFT;
    private int beforeSpacing = 70;

    public CellHeaderBuilder() {
    }

    public void createCellHeader(XWPFTableCell cell) {
        XWPFParagraph p;
        if (cell.getParagraphs().get(0).getRuns().size() == 0)
            p = cell.getParagraphs().get(0);
        else
            p = cell.addParagraph();

        //CTTcPr cellPr = cell.getCTTc().addNewTcPr();
        //cellPr.addNewTcW().setW(BigInteger.valueOf(0));

        p.setAlignment(alignment);
        p.setSpacingBefore(beforeSpacing);
        p.setIndentationLeft(20);
        p.setSpacingAfter(100);
        XWPFRun rHeading = p.createRun();
        //System.out.println("text is "+text);
        rHeading.setText(StringUtils.isBlank(text)?"": StringUtils.capitalize(text));
        rHeading.setFontFamily("Arial");
        rHeading.setFontSize(7);
        rHeading.setBold(isBold);
    }

    public CellHeaderBuilder text(String text) {
        //System.out.println("text oin text method "+text);
        this.text = text;
        return this;
    }

    public CellHeaderBuilder bold() {
        this.isBold = true;
        return this;
    }

    public CellHeaderBuilder alignment(ParagraphAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public CellHeaderBuilder beforeSpacing(int beforeSpacing) {
        this.beforeSpacing = beforeSpacing;
        return this;
    }
}
