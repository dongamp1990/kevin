package org.kevin.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtil {
	
	private static String _Key = "_";
	private static final Logger LOGGER = LoggerFactory.getLogger("app");
	
	public static <T> T convertEntity(Map<String, Object> map, Class<T> entityClass) {
		T entity;
		try {
			entity = entityClass.newInstance();
			for (String key : map.keySet()) {
				Field field = null;
				String fieldName = convertFieldName(key);
				try {
					field = entityClass.getDeclaredField(fieldName);
				} catch (NoSuchFieldException e) {
					LOGGER.warn("BeanUtil.convertEntity not found " + e.getMessage() + " field");
				}
				if (field == null) {
					continue;
				}
				field.setAccessible(true);
				Object val = map.get(key);
				if (val == null) {
					continue;
				}
				String type = field.getType().toString();
				if (type.endsWith("Long") || type.endsWith("long")) {
					field.set(entity, Long.parseLong(map.get(key).toString()));
				}else if (type.endsWith("Byte") || type.endsWith("byte")) {
					field.set(entity, Byte.parseByte(map.get(key).toString()));
				}else if (type.endsWith("Integer") || type.endsWith("int")) {
					field.set(entity, Integer.parseInt(map.get(key).toString()));
				}else if (type.endsWith("Double") || type.endsWith("double")) {
					field.set(entity, Double.parseDouble(map.get(key).toString()));
				}else if (type.endsWith("Float") || type.endsWith("float")) {
					field.set(entity, Float.parseFloat(map.get(key).toString()));
				}else if(type.endsWith("Date")){
					//date类型dateStr字段，日期格式
					field.set(entity, val);
					try {
						Field dateStrFile = entityClass.getDeclaredField(fieldName + "Str");
						if (dateStrFile != null && dateStrFile.getType().toString().endsWith("String")) {
							dateStrFile.setAccessible(true);
							Date date = (Date) val;
							dateStrFile.set(entity, DateUtil.parseDateToString(date, DateUtil.sdf_bidStatus));
						}
					} catch (Exception e) {
						LOGGER.warn("BeanUtil.convertEntity nou found " + e.getMessage() + "field");
					}
				}else {
					field.set(entity, val);
				}
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String convertFieldName(String key) {
		if (key.contains(_Key)) {
			int index = key.indexOf(_Key);
			String x = key.substring(index + 1, index + 2);
			String res = key.replace(_Key + x, x.toUpperCase());
			return convertFieldName(res);
		}
		return key;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
//		System.out.println(convertFieldName("company_id"));
//		System.out.println(convertFieldName("deposit_history_id"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_ouid", "aa");
		map.put("company_id",1);
		map.put("enterprise_group_id",1);
		map.put("weixin",1);
		System.out.println(new Date().getTime() - date.getTime());
	}
}
