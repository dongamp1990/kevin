package com.alipp.weixin.util;

/**
 *=====================================================================
 * ACP Number Type Data Handling Utility 
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumberUtil {
	private static Logger logger = LoggerFactory.getLogger(NumberUtil.class.getName());
	
	/**
	 * 格式为不带小数点
	 */
	public static final NumberFormat def_decimal_format = new DecimalFormat("#"); 
	public static final NumberFormat decimal_format_2 = new DecimalFormat("#0.00"); 

	/**
	 * check ID is Null
	 */
	public static boolean isNullID(Long id ) {
		boolean isNewKey = false;
	    
		if ( id == null || id < 1L ) {
			isNewKey = true;
	    } 
		return isNewKey ;
	}	
	
	/**
	 * check Id is not Null
	 */
	public static boolean isNotNullID(Long id ) {
		boolean isExsited = false;
	    
		if ( isNullID(id) ) {
			isExsited = false;
	    } else {
	    	isExsited = true;
	    }
		return isExsited ;
	}	
	
	/**
	 * check version is Null
	 */
	public static boolean isNullVersion(Integer version ) {
		boolean isNewKey = false;
	    
		if ( version == null || version < 1 ) {
			isNewKey = true;
	    } 
		return isNewKey ;
	}	
	
	/**
	 * check version is not Null
	 */
	public static boolean isNotNullVersion(Integer version ) {
		boolean isExsited = false;
	    
		if ( isNullVersion(version) ) {
			isExsited = false;
	    } else {
	    	isExsited = true;
	    }
		return isExsited ;
	}	

	
	/**
	 * check number
	 */
	public static BigDecimal checkNull(BigDecimal number ) {
		BigDecimal bigDec = null;
	    
		if ( number != null ) {
			bigDec = number;
	    } else {
			bigDec = new BigDecimal(0);
		}
		return bigDec;
	}	
	

	/**
	 * convert BigDecmal to Double
	 */
	public static Double convertDouble(BigDecimal number ) {
		Double doubleObj = null;
	    
		if ( number != null ) {
			doubleObj = number.doubleValue();
	    } else {
	    	doubleObj = new Double(0.0);
		}
		return doubleObj;
	}	

	/**
	 * convert Long to Integer
	 */
	public static Integer convertInteger(Long number ) {
		Integer integerObj = null;
	    
		if ( number != null ) {
			integerObj = number.intValue();
	    } else {
	    	integerObj = new Integer(0);
		}
		return integerObj;
	}	
	
	
	/**
	 * convert BigDecmal to Integer
	 */
	public static Integer convertInteger(BigDecimal number ) {
		Integer integerObj = null;
	    
		if ( number != null ) {
			integerObj = number.intValue();
	    } else {
	    	integerObj = new Integer(0);
		}
		return integerObj;
	}	

	/**
	 * convert Double to BigDecmal
	 */
	public static BigDecimal convertBigDecmal(Double number ) {
		BigDecimal bigDec = null;
	    
		if ( number != null ) {
			bigDec = new BigDecimal(number);
	    } else {
	    	bigDec = null;
		}
		return bigDec;
	}	
	
	/**
	 * convert Integer to BigDecmal
	 */
	public static BigDecimal convertBigDecmal(Integer number ) {
		BigDecimal bigDec = null;
	    
		if ( number != null ) {
			bigDec = new BigDecimal(number);
	    } else {
	    	bigDec = null;
		}
		return bigDec;
	}	
	
	/**
	 * convert String to BigDecmal
	 */
	public static BigDecimal convertBigDecmal(String number ) {
		BigDecimal bigDec = null;
		if ( number == null || "".equals(number)) {
			bigDec = new BigDecimal(0);
			return bigDec;
	    } else {
	    	try{
	    		bigDec = new BigDecimal(number);
			}catch(Exception e){
				bigDec = new BigDecimal(0);
				logger.error("Error: Can't convert '"+bigDec+"' to BigDecimal.");
				logger.error(e.getMessage());
			}
			return bigDec;
		}
	}	

	
	/**
	 *  convert temperature
	 */
	public  static enum TemperatureUnit { Kelvin, Celsius, Fahrenheit, Rankine }
	private static final double UnitsFforC = 1.8;
	private static final double FreezeF = 32.0;
	private static final double FreezeAbsC = 273.15;
	
	public static double convertTemperature(double baseValue, TemperatureUnit srcUnit, TemperatureUnit destUnit) {

		if (srcUnit == destUnit) { return baseValue; }
		
		// normalize everything to celsius
		double celsius;
		if (srcUnit==TemperatureUnit.Kelvin) {
			celsius = baseValue - FreezeAbsC;
		} else if (srcUnit==TemperatureUnit.Fahrenheit) {
			celsius = (baseValue - FreezeF) / UnitsFforC;
		} else if (srcUnit==TemperatureUnit.Rankine) {
			celsius = (baseValue / UnitsFforC) - FreezeAbsC;
		} else {
			celsius = baseValue;
		}

		double returnValue;
		// convert out from celsius
		if (destUnit==TemperatureUnit.Kelvin) {
			returnValue = celsius + FreezeAbsC;
		} else if (destUnit==TemperatureUnit.Rankine) {
			returnValue = (celsius + FreezeAbsC) * UnitsFforC;
		} else if (destUnit==TemperatureUnit.Fahrenheit) {
			returnValue = (celsius * UnitsFforC) + FreezeF;
		} else {
			returnValue = celsius;
		}
		
		return (Math.floor((returnValue * 10.0) + 0.5))/10.0;
			
	}
	
	/**
	 * Parse the String to Long
	 * @param inputVal
	 * @return
	 */
	public static Long parseStringToLong(String inputVal){
		Long returnVal = null;
		if(null==inputVal || "".equals(inputVal)){
			returnVal = new Long(0);
			return returnVal;
		}else{
			try{
				returnVal = new Long(inputVal);
			}catch(Exception e){
				returnVal = new Long(0);
				logger.error("Error: Can't convert '"+inputVal+"' to Long.");
				logger.error(e.getMessage());
			}
			return returnVal;
		}
	}
	
	/**
	 * Parse the String to Integer
	 * @param inputVal
	 * @return
	 */
	public static Integer parseStringToInteger(String inputVal){
		Integer returnVal = null;
		if(null==inputVal || "".equals(inputVal)){
			return returnVal;
		}else{
			try{
				returnVal = new Integer(inputVal);
			}catch(Exception e){
				logger.error("Error: Can't convert '"+inputVal+"' to Integer.");
				logger.error(e.getMessage());
			}
			return returnVal;
		}
	}
	
	public static String round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return (""+bd.floatValue()).replace(".0", "");
    }
	
	/**
	 * 把数字格式为字符串
	 * @param number
	 * @return
	 */
	public static String numberToString(Number number, NumberFormat format) {
		
		if (number == null) {
			return "";
		}
		
		return format.format(number);
	}
	
	public static Short nullIfZero(Short value)	{
		return value == null ? null : (value == 0 ? null : value);
	}
	
	public static Integer nullIfZero(Integer value)	{
		return value == null ? null : (value == 0 ? null : value);
	}
	
	public static Long nullIfZero(Long value) {
		return value == null ? null : (value == 0 ? null : value);
	}
	
	public static short getValueof(Short oValue) {
		return oValue == null ? 0 : oValue.shortValue();
	}
	
	public static int getValueof(Integer oValue) {
		return oValue == null ? 0 : oValue.intValue();
	}
	
	public static long getValueof(Long oValue)	{
		return oValue == null ? 0 : oValue.longValue();
	}
	
	public static boolean isNullOrZero(Short value)	{
		return value == null || value == 0;
	}
	
	public static boolean isNullOrZero(Integer value) {
		return value == null || value == 0;
	}
	
	public static boolean isNullOrZero(Long value)	{
		return value == null || value == 0;
	}
		
	public static boolean isNullOrZero(Double value) {
		return value == null || value == 0;
	}
	
	public static boolean isEqual(Object object1, Object object2) {
		if (object1 instanceof Short && object2 instanceof Short) {
			return isEqual((Short)object1, (Short)object2);
		}
		else if (object1 instanceof Integer && object2 instanceof Integer) {
			return isEqual((Integer)object1, (Integer)object2);
		}
		else if (object1 instanceof Long && object2 instanceof Long) {
			return isEqual((Long)object1, (Long)object2);
		}
		else if (object1 instanceof Short && object2 instanceof Integer) {
			return isEqual((Short)object1, (Integer)object2);
		}
		else if (object1 instanceof Integer && object2 instanceof Short) {
			return isEqual((Integer)object1, (Short)object2);
		}		
		else if (object1 instanceof Short && object2 instanceof Long) {
			return isEqual((Short)object1, (Long)object2);
		}
		else if (object1 instanceof Long && object2 instanceof Short) {
			return isEqual((Long)object1, (Short)object2);
		}		
		else if (object1 instanceof Integer && object2 instanceof Long)	{
			return isEqual((Integer)object1, (Long)object2);
		}
		else if (object1 instanceof Long && object2 instanceof Integer)	{
			return isEqual((Long)object1, (Integer)object2);
		}		
		else if (object1 instanceof Double && object2 instanceof Double) {
			return isEqual((Double)object1, (Double)object2);
		}
		else if (object1 instanceof Byte && object2 instanceof Integer) {
			return isEqual((Byte)object1, (Integer)object2);
		}
		else if (object1 instanceof Integer && object2 instanceof Byte) {
			return isEqual((Integer)object1, (Byte)object2);
		}else {
			return false;
		}
	}
	
	public static boolean isEqual(Short short1, Short short2) {
		if (short1 == null) short1 = 0;
		if (short2 == null) short2 = 0;

		return short1.shortValue() == short2.shortValue();
	}
	
	public static boolean isEqual(Byte byte1, Integer integer2) {
		if (byte1 == null) byte1 = 0;
		if (integer2 == null) integer2 = 0;

		return byte1.byteValue() == integer2.byteValue();
	}
	
	public static boolean isEqual(Integer integer2, Byte byte1) {
		if (byte1 == null) byte1 = 0;
		if (integer2 == null) integer2 = 0;

		return byte1.byteValue() == integer2.byteValue();
	}
	
	public static boolean isEqual(Integer integer1, Integer integer2) {
		if (integer1 == null) integer1 = 0;
		if (integer2 == null) integer2 = 0;

		return integer1.intValue() == integer2.intValue();
	}
	
	public static boolean isEqual(Long long1, Long long2) {
		if (long1 == null) long1 = 0L;
		if (long2 == null) long2 = 0L;

		return long1.longValue() == long2.longValue();
	}

	public static boolean isEqual(Short short1, Integer integer2) {
		if (short1 == null) short1 = 0;
		if (integer2 == null) integer2 = 0;
		
		return short1.shortValue() == integer2.intValue();
	}

	public static boolean isEqual(Integer integer2, Short short1) {
		if (short1 == null) short1 = 0;
		if (integer2 == null) integer2 = 0;
		
		return short1.shortValue() == integer2.intValue();
	}
	
	public static boolean isEqual(Byte value1, Byte value2) {
		if (value1 == null) value1 = 0;
		if (value2 == null) value2 = 0;
		
		return value1.byteValue() == value2.byteValue();
	}
	
	public static boolean isEqual(Short short1, Long long2)	{
		if (short1 == null) short1 = 0;
		if (long2 == null) long2 = 0L;
		
		return short1.shortValue() == long2.longValue();
	}
	
	public static boolean isEqual(Long long2, Short short1)	{
		if (short1 == null) short1 = 0;
		if (long2 == null) long2 = 0L;
		
		return short1.shortValue() == long2.longValue();
	}

	public static boolean isEqual(Integer integer1, Long long2)	{
		if (integer1 == null) integer1 = 0;
		if (long2 == null) long2 = 0L;
		
		return integer1.intValue() == long2.longValue();
	}
	
	public static boolean isEqual(Long long2, Integer integer1)	{
		if (integer1 == null) integer1 = 0;
		if (long2 == null) long2 = 0L;
		
		return integer1.intValue() == long2.longValue();
	}
	
	public static boolean isEqual(Double double1, Double double2) {
		if (double1 == null) double1 = 0.0;
		if (double2 == null) double2 = 0.0;
		
		return double1.doubleValue() == double2.doubleValue();
	}
	
	/**
	 *  四舍五入
	 * @param scale 保留小数位
	 * @param value double值
	 * @return double <br>
	 * 
	 * ROUND_HALF_UP 
     * 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
	 */
	public static double doubleRoundingX(int scale, double value) {
		return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}

