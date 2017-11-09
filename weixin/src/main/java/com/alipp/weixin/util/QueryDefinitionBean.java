package com.alipp.weixin.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class QueryDefinitionBean {

	private Map<String, String> namedQueryMap = new HashMap<String, String>();
	private static Logger logger = Logger
			.getLogger(QueryDefinitionBean.class.getName());

	public Map<String, String> getNamedQueryMap() {
		return namedQueryMap;
	}

	public void setNamedQueryMap(Map<String, String> namedQueryMap) {
		this.namedQueryMap = namedQueryMap;
	}

	/**
	 * @param queryName
	 * @return
	 * @throws DaoException
	 */
	public String getQueryByName(String queryName) throws Exception {
		String queryStr = "";
		String errorDescription = "";
		if (namedQueryMap != null) {
			queryStr = namedQueryMap.get(queryName);
			if(StringUtils.isEmpty(queryStr)){
				errorDescription = "Failed to get the query string by name ' "+queryName+"'.";
				logger.error("Failed to get the query string by name ' "+queryName+"'.");
				throw new Exception("Failed to get the query string by name ' "+queryName+"'.");
			}
			return queryStr;
		} else {
			errorDescription = "Failed to get the query string by name ' "+queryName+"'. "
					+ "The namedQueryMap is null. Please check the spring bean configuraion file in Java.";
			logger.error(errorDescription);
			throw new Exception(errorDescription	);
		}
	}

}
