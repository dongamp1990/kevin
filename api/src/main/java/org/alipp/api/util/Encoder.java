package org.alipp.api.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Encoder {

	public static String encryptSHA(String inStr)  {
		MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        byte[] byteArray;
		try {
			byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = sha.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) { 
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        return hexValue.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
    }
	
	public static void main(String[] args)  {
		//

    }
}
