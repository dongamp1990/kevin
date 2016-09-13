package com.alipp.weixin.util;

/**
 *=====================================================================
 * ACP String Stream Handling Utility 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.io.Reader;
import java.sql.Clob;
import java.util.Random;

public final class StringUtil {
  
	
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
		'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
		'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
		'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
		'Z' };

	/**
	 * 随机ID生成器，由数字、小写字母和大写字母组成
	 * 
	 * @param size
	 * @return
	 */
	public static String generatorId(int size) {
		Random random = new Random();
		char[] cs = new char[size];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = digits[random.nextInt(digits.length)];
		}
		return new String(cs);
	}

  /**
   * Test for null/empty and no whitespace string.
   * @param string
   * @return true if string is null or blank
   */
  public static boolean isBlank(String string) {
    return (string == null || string.trim().length() == 0);
  }

  /**
   * Test for not null string.
   * @param string
   * @return true if string is not null or blank
   */
  public static boolean isNotBlank(String aString) {
	boolean isBlank = false;
	
	if ( aString != null ) {
	   isBlank = aString.trim().length() != 0;
	} 
	return isBlank;
  }

  /**
   * Checks if a string contains a boolean true.
   * Currently the strings "true", "yes", and "1" are considered true.
   * All other strings (including null) are considered false.
   */
  public static boolean isTrue(String str) {
    return ( (str != null)
             && (str.equalsIgnoreCase("true")
             || str.equalsIgnoreCase("yes")
             || str.equals("1")
          ));
  }

  /**
   * Test for Integer in string.
   * @param string
   * @return true if string is Int.
   */
  public static boolean isNumber(String value) {
    try {
    	if (value.contains(".")) {
    		Float.parseFloat(value);
    	} else {
    		Integer.parseInt(value);
    	}
        return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }
    
  
  /** 
   * convert Clob to string 
   * @param clobObject Clob
   * @return String
   */
  public static String convertClobToString(Clob clobObject ) {
	  String stringClob = "";
	  
	  try {
		  if ( clobObject != null && clobObject.length() > 0L ) {
			 StringBuilder sb = new StringBuilder();
			 char[] buffer = new char[(int)clobObject.length()];
			
			 Reader colbReader = clobObject.getCharacterStream();
			 while (colbReader.read(buffer) != -1) {
			     sb.append(buffer);
			 }
			 stringClob = new String(sb); 
		  }
	  
	  } catch (Exception e) {
		  stringClob = "";
	  }
	  
	  return stringClob;
   }

  /** 
   * convert Clob to string 
   * @param clobObject Clob
   * @return String
   */
  public static String convertClobToString_save(Clob clobInData) {
	  String stringClob = "";
	  
	  try {
		  if ( clobInData != null ) {
			 long i = 1;
			 int clobLength = (int) clobInData.length();
			 stringClob = clobInData.getSubString(i, clobLength);
		  }
	  } catch (Exception e) {
		  stringClob = "";
	  }
	  
	  return stringClob;
   }
  
	public static String convertToUtf8(String str) {
		try {
			return new String(str.getBytes("iso8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String randomStrByLength(int length) {
		Random random = new Random();
		int netInt = random.nextInt();
		if (netInt < 0) {
			netInt = netInt * -1;
		}
		
		String str = netInt + "";
		if (str.length() > length) {
			str = str.substring(0, length);
		}else {
			int dValue = length - str.length();
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(str);
			for (int i = 0; i < dValue; i ++) {
				sBuilder.append("0");
			}
			str = sBuilder.toString();
		}
		
		return str.toString();
	}
}

