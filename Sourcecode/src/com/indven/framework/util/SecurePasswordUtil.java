/************************************************************************************************************************
 * Copyright Information				:	Indiatech Ventures Pvt. Ltd.
 * File Name							:	SecurePasswordUtil.java
 * Project Name							:	EHRMS
 * Date of Creation						: 	13th May 2013
 * Brief Description					:	This Class will be use convert the plain text password to secure password using BCrypt and check the password
 * 											using BCrypt 
 * Author								: 	Saurabh Gupta
 * Revision History 
 * 
 *************************************************************************************************************************/
package com.indven.framework.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author Saurabh
 *
 */
public class SecurePasswordUtil {

	public static String hashUserPassword(String password,String loginId){
		
		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		String hashed = BCrypt.hashpw(password + loginId, BCrypt.gensalt(12));
		
		return hashed;
	}
	
	public static boolean checkUserPassword(String plainPassword,String loginId, String hashedPassword){
		
		return BCrypt.checkpw(plainPassword + loginId, hashedPassword);
	}
	
	public static String genrateResetPasswordId(){
		Random rnd = new Random();
		Integer n = (100000 + rnd.nextInt(900000));
		String resetPasswordId = n.toString();
		
		return resetPasswordId;
	}
}
