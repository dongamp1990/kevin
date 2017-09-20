package org.kevin.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kevin.exception.BaseException;
import org.kevin.exception.DaoException;

import com.alibaba.dubbo.common.utils.StringUtils;

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
	public String getQueryByName(String queryName) throws BaseException {
		String queryStr = "";
		String errorDescription = "";
		if (namedQueryMap != null) {
			queryStr = namedQueryMap.get(queryName);
			if(StringUtils.isBlank(queryStr)){
				errorDescription = "Failed to get the query string by name ' "+queryName+"'.";
				logger.error(errorDescription);
				throw new BaseException();
			}
			return queryStr;
		} else {
			errorDescription = "Failed to get the query string by name ' "+queryName+"'. The namedQueryMap is null. Please check the spring bean configuraion file in Java.";
			logger.error(errorDescription);
			throw new BaseException();
		}
	}

}
