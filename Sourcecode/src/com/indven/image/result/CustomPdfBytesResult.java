package com.indven.image.result;

import com.indven.image.action.ImageAction;
import com.indven.image.action.PdfAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;


public class CustomPdfBytesResult implements Result {

    public void execute(ActionInvocation invocation) throws Exception {

        PdfAction action = (PdfAction) invocation.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType(action.getCustomContentType());
        response.getOutputStream().write(action.getPdfBytes());
        response.getOutputStream().flush();

    }

}