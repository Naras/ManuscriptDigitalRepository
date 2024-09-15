package com.indven.omds.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.indven.omds.exception.OMDPCoreException;

/**
 * This class is to be used for all file I/O
 * @author Saurabh
 */
public class FilesUtil {
	
	/*
	 * Saves the file to the given target path
	 */
	public static String saveFile(File file, String fileName, String targetPath) throws IOException, OMDPCoreException{
		FileInputStream in = null;
		FileOutputStream out = null;
		File destinationFile = new File (targetPath);
		try {
			in = new FileInputStream( file );
			out = new FileOutputStream( destinationFile );
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		}catch (Exception e) {
			throw new OMDPCoreException(OMDPCoreException.UNABLE_TO_PROCESS_THE_RECORD);
		}finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
		
		return destinationFile.getName();
	}
	
	/**
	 * Deletes the file from disk
	 * Using absolute path
	 * @author Neel Borooah
	 * @param targetPath
	 * @return boolean
	 */
	public static boolean deleteFile(String targetPath) {
		
		boolean status = false;
		
		File file  = new File(targetPath); //Load file from path
		status = file.delete(); //Delete file
		
		return status;
	}
	
	
	/**
	 * Copies the file to location on disk
	 * Loads from current path of file
	 * @param currentPath, folderPath
	 * @return String
	 * @throws IOException
	 * @author Neel Borooah
	 * @throws OMDPCoreException 
	 */
	public static String copyFileToDirectory(String currentPath, String folderPath, boolean append) throws IOException, OMDPCoreException {
		String diskPath = null; //Contains the path in file system where the file is to be stored
		
		folderPath = (folderPath.replace("/", File.separator)).trim();
		currentPath = (currentPath.replace("/", File.separator)).trim();
		currentPath = (currentPath.replace("\\", File.separator)).trim();
		String imageName = "";
		if(new File(currentPath).exists()){
			/* If file exists in current location, then continue */
			File file = new File(currentPath);
			String fileName = (currentPath.split("\\"+File.separator)[currentPath.split("\\"+File.separator).length - 1]).trim(); //File name
			if(append){
				diskPath = folderPath + File.separator + fileName;
				imageName = saveFile(file, fileName, diskPath);
			}else{
				/*int flag = 0; //Flag occurs in three states
				Random random = new Random();*/
				fileName = System.currentTimeMillis() + fileName;
				diskPath = folderPath + File.separator + fileName;
				imageName = saveFile(file, fileName, diskPath);
			/*while(flag <= 2) {
				if(flag == 0) {
					 Initial Check 
					diskPath = folderPath + File.separator + fileName;
					if(new File(diskPath).exists()) {
						 File with this name already exists on disk 
						 * Change flag state to generate new name for file 
						flag++;
						continue;
					} else {
						 Change state to save file 
						flag += 2;
						continue;
					}
				} else if(flag == 1) {
					 Secondary check 
					diskPath = folderPath + File.separator + String.valueOf(random.nextLong()) + fileName; //Assign random name to file
					if(new File(diskPath).exists()) {
						 File with this name already exists on disk 
						 * Continue with random name generation 
						continue;
					} else {
						 File with this name does not exist
						 * Change flag state to save file 
						flag++;
					}
				} else {
					 Call save file method 
					imageName = saveFile(file, fileName, diskPath);
					flag++;
				}			
			}*/
			}
		}
		return imageName;
	}
}
