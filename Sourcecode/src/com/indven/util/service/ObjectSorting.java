package com.indven.util.service;
import java.util.Collections;
import java.util.List;

import com.indven.omds.entity.DigitalManuscriptFrame;

public class ObjectSorting{
	
	
	public List<DigitalManuscriptFrame> sortingObject(List<DigitalManuscriptFrame> frameList){
		DigitalManuscriptFrame frame = new DigitalManuscriptFrame();
		/*Collections.sort(frameList , frame);*/
		/*for(int){
			
		}
		*/
		return null;
	}
	public int compare(DigitalManuscriptFrame frame1, DigitalManuscriptFrame frame2) {
        String substr1 = frame1.getFilePath().split(".")[0].substring( frame1.getFilePath().split(".")[0].length() - 3);
        String substr2 = frame2.getFilePath().split(".")[0].substring( frame2.getFilePath().split(".")[0].length() - 3);

        return Integer.valueOf(substr2).compareTo(Integer.valueOf(substr1));
    }
	
}
