package org.kevin.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class JSONUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.setVisibility(JsonMethod.FIELD, Visibility.ANY).setSerializationInclusion(Inclusion.NON_NULL);
	public static String objectToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T jsonToObject(String object, Class<T> cls) {
		try {
			return objectMapper.readValue(object, cls);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T readValueToTargetObj(Object obj, Class<T> cls) {
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(obj), cls);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> object2Map(Object obj) {
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(obj), HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}	
