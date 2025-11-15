package com.indven.image.action;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.util.IndvenApplicationConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class PdfAction extends BaseAction {

    byte[] bytes = null;
    String id;
    String path;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private HttpServletRequest servletRequest;


    public PdfAction() {
        //System.out.println("ImageAction");
    }

    public String execute() {
        return SUCCESS;
    }

    public byte[] getPdfBytes() {
        System.out.println("image path======= " + path);
        
        try {
            File imageFile = getFile(path);
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            String folderPath = (ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
                    .getObject("images.system.path").toString()).trim();
            File file = new File(FilenameUtils.separatorsToSystem(folderPath+ "/"+path));
            if (imageFile!=null) {
                return FileUtils.readFileToByteArray(imageFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    private File getFile(String imagePath) {
        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
        String folderPath = (ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
                .getObject("images.system.path").toString()).trim();
        System.out.println("image.action.PdfAction.getFile() folderPath ====== "+folderPath);
        File file = new File(FilenameUtils.separatorsToSystem(folderPath+ "/"+imagePath));
        System.out.println(file.toString());
        return file;
    }

    public String getCustomContentType() {
        return "application/pdf";
    }

    public String getCustomContentDisposition() {
        return "application/pdf";
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;

    }

}