package com.alipp.weixin.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通用方法，用于转换xml到bean，或者bean 到xml
 * @author dongshengyu
 */
public class JacksonUtils {
	
	public static class JacksonMapper {
	    /**
	     * can reuse, share globally
	     */
	    private static final ObjectMapper object = new ObjectMapper();

	    /**
	     * can reuse, share globally
	     */
	    private static final XmlMapper xml = new XmlMapper();

	    /**
	     * private constructor
	     */
	    private JacksonMapper() {
	    }

	    /**
	     * return a ObjectMapper that is singleton
	     * @return
	     */
	    public static ObjectMapper getObjectMapper() {
	        return object;
	    }

	    /**
	     * return a XmlMapper that is singleton
	     * @return
	     */
	    public static XmlMapper getXmlMapper() {
	    	xml.setSerializationInclusion(Include.NON_NULL);
	        return xml;
	    }
	}

    /**
     * XML To Object
     *
     * @param xmlPath
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(String xmlContent, Class<T> cls) throws IOException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        T obj = xml.readValue(xmlContent, cls);
        return obj;
    }

    /**
     * @param xmlFile
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(File xmlFile, Class<T> cls) throws IOException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        T obj = xml.readValue(xmlFile, cls);
        return obj;
    }

    /**
     * @param xmlInputStream
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(InputStream xmlInputStream, Class<T> cls) throws IOException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        T obj = xml.readValue(xmlInputStream, cls);
        return obj;
    }

    /**
     * @param bean 
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> String beanToXml(T bean) throws JsonProcessingException {
        XmlMapper xml = JacksonMapper.getXmlMapper();
        String string = xml.writeValueAsString(bean);
        return string;
    }
}