package com.alipp.weixin.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static XmlMapper xmlMapper = new XmlMapper();
	
	public static Map<String, String> xml2Map(String msg) {
		try {
			JsonParser parser = xmlMapper.getFactory().createParser(msg);
			@SuppressWarnings("unchecked")
			Map<String, String> requestMap = objectMapper.readValue(parser, Map.class);
			return requestMap;
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String xml2json(String xml) {
		StringWriter w = new StringWriter();
		JsonParser jp;
		try {
			jp = xmlMapper.getFactory().createParser(xml);
			JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
			while (jp.nextToken() != null) {
				jg.copyCurrentEvent(jp);
			}
			jp.close();
			jg.close();
			return w.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
