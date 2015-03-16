package com.magikhelper.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class Utils {

	public static String getSecurityToken(String message){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] result = md.digest(message.getBytes());
			return new Base64().encodeToString(result);
		} catch (NoSuchAlgorithmException e) {
			
		}
		return null;
	}
}
