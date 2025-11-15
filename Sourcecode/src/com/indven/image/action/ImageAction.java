package com.indven.image.action;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.indven.framework.controller.BaseAction;
import com.indven.framework.util.IndvenApplicationConstants;
//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
//import org.apache.struts2.interceptor.ServletRequestAware;
//import com.opensymphony.xwork2.ActionSupport;
import org.imgscalr.Scalr;
import com.indven.framework.logging.IndvenLogger;

public class ImageAction extends BaseAction {
	private static IndvenLogger logger = IndvenLogger
            .getInstance(ImageAction.class);

    byte[] imageInByte = null;
    String imageId;

    public String getIsThumbnail() {
        return isThumbnail;
    }

    public void setIsThumbnail(String isThumbnail) {
        this.isThumbnail = isThumbnail;
    }

    String isThumbnail;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String imagePath="";
    private HttpServletRequest servletRequest;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ImageAction() {
        //logger.debug("ImageAction");
    }

    public String execute() {
        return SUCCESS;
    }

    public byte[] getCustomImageInBytes() {
        logger.debug("getCustomImageInBytes() image path " + imagePath+" isthumbnail "+isThumbnail);
        //logger.debug("imageId" + imageId);

        BufferedImage originalImage;

        try {
            File imageFile = getImageFile(this.imagePath);
            originalImage = ImageIO.read(imageFile);
            String fileBaseName = org.apache.commons.io.FilenameUtils.getBaseName(imageFile.getName());
            String fileExt = org.apache.commons.io.FilenameUtils.getExtension(imageFile.getName());

            if (StringUtils.isNotBlank(isThumbnail) && isThumbnail.equals("true")) {
                originalImage = Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, 120, 120);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, fileExt, baos);
                baos.flush();
                imageInByte = baos.toByteArray();
                baos.close();
               /* ImageIO.write( thumbnail,fileExt, baos );
                baos.writeTo(new FileOutputStream(ipThumbFile));*/
            }// else {
                // convert BufferedImage to byte array

            //}
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, fileExt, baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return imageInByte;
    }


    private File getImageFile(String imagePath) {
        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
        String folderPath = (ResourceBundle.getBundle("ApplicationResources", IndvenApplicationConstants.LOCALE)
                .getObject("images.system.path").toString()).trim();
        logger.debug("ImageAction.getImageFile() folderPath "+folderPath);
        File file = new File(FilenameUtils.separatorsToSystem(folderPath+ "/"+imagePath));
        logger.debug("ImageAction.getImageFile() file "+file.toString());
        return file;
    }

    public String getCustomContentType() {
        return "image/jpeg";
    }

    public String getCustomContentDisposition() {
        return "anyname.jpg";
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;

    }

}