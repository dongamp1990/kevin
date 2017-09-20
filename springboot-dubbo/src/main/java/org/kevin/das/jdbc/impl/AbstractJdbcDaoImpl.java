package org.kevin.das.jdbc.impl;

/**
 *=====================================================================
 * Abstract JDBC DAO Implementation
 * 
 * Default operations for all the JDBC based DAO. 
 * When you think this POJO might just need CRUD, so we can use this 
 * default DAO. If you think this POJO has some other specific operations, 
 * we should create a separated DAO instead of using default one.
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kevin.das.jdbc.AbstractJdbcDao;
import org.kevin.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

public abstract class AbstractJdbcDaoImpl extends NamedParameterJdbcDaoSupport implements AbstractJdbcDao {
	private static Logger logger = LoggerFactory
			.getLogger(AbstractJdbcDaoImpl.class.getName());

	/**
	 * @param query
	 * @param paramMap
	 * @return
	 * @throws DaoException
	 */
	public List<Map<String, Object>> executeJdbcQuery(String query,
			Map<String, Object> paramMap) throws DaoException {
		String errorDescription = "";
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			resultList = getNamedParameterJdbcTemplate().queryForList(query,
					paramMap);
		} catch (DataAccessException e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ " The given query is :  <query start >  " + query
					+ "   <query end >";
			logger.error("executeJdbcQuery error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		} catch (Exception e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is :  <query start >  " + query
					+ "   <query end >";
			logger.error("executeJdbcQuery error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		}
		return resultList;
	}

	/**
	 * @param query
	 * @param paramMap
	 * @return
	 * @throws DaoException
	 */
	public int executeJdbcUpdate(String query, Map<String, Object> paramMap)
			throws DaoException {
		int count = 0;
		String errorDescription = "";
		try {
			count = getNamedParameterJdbcTemplate().update(query, paramMap);
		} catch (DataAccessException e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is :  <query start >  " + query
					+ "   <query end >";
			logger.error("executeJdbcUpdate error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		} catch (Exception e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is :  <query start >  " + query
					+ "   <query end >";
			logger.error("executeJdbcUpdate error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		}
		return count;
	}

	/**
	 * @param query
	 * @param paramSource
	 * @param generatedKeyHolder
	 *            : output parameter, hold primary keys affected by the query
	 * @return number of rows affected
	 * @throws DaoException
	 */
	public int executeJdbcUpdate(String query, SqlParameterSource paramSource,
			KeyHolder generatedKeyHolder) throws DaoException {
		int count = 0;
		String errorDescription = "";
		try {
			count = getNamedParameterJdbcTemplate().update(query, paramSource,
					generatedKeyHolder);
		} catch (DataAccessException e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is : <query start > " + query
					+ " <query end >";
			logger.error("executeJdbcUpdate error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		} catch (Exception e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is : <query start > " + query
					+ " <query end >";
			logger.error("executeJdbcUpdate error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		}
		return count;
	}

	/**
	 * @param query
	 * @param paramMap
	 * @return
	 * @throws DaoException
	 */
	public int[] executeJdbcBatchUpdate(String query,
			Map<String, ?>[] batchValues) throws DaoException {
		int counts[];
		String errorDescription = "";
		try {
			counts = getNamedParameterJdbcTemplate().batchUpdate(query,
					batchValues);
		} catch (DataAccessException e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is : <query start > " + query
					+ " <query end >";
			logger.error("executeJdbcUpdate error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		} catch (Exception e) {
			errorDescription = "Failed to excute the query " + query
					+ " . The system error is :" + e.getMessage();
			errorDescription = errorDescription
					+ "The given query is : <query start > " + query
					+ " <query end >";
			logger.error("executeJdbcUpdate error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorDescription);
		}
		return counts;
	}

	/**
	 * @param procedureString
	 * @param paramMap
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> executeJdbcStoredProcedure(
			final String procedureString, final Map<String, String> paramMap)
			throws DaoException {
		String errorMsg = "";

		List<Map<String, Object>> resultList = null;
		List<SqlParameter> outParameterList = new ArrayList<SqlParameter>();
		try {
			// Execute Stored Procedure using Jdbc Template
			Map<String, Object> spResultMap = getJdbcTemplate().call(
					new CallableStatementCreator() {
						@Override
						public CallableStatement createCallableStatement(
								Connection con) throws SQLException {
							CallableStatement cstmt = null;
							String callableStr = getCallableStr(
									procedureString, paramMap.size());
							cstmt = con.prepareCall(callableStr);
							Iterator<String> paramValues = paramMap.values()
									.iterator();
							int index = 1;
							while (paramValues.hasNext()) {
								cstmt.setObject(index++, paramValues.next());
							}
							return cstmt;
						}
					}, outParameterList);
			resultList = (List<Map<String, Object>>) spResultMap
					.get("result-set");
		} catch (DataAccessException e) {
			errorMsg = "Failed to excute the SP " + procedureString
					+ " . The system error is :" + e.getMessage();
			errorMsg = errorMsg + "The given query is :  <query start >  "
					+ procedureString + "   <query end >";
			logger.error("executeJdbcStoredProcedure error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorMsg);
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "Failed to excute the SP " + procedureString
					+ " . The system error is :" + e.getMessage();
			errorMsg = errorMsg + "The given query is :  <query start >  "
					+ procedureString + "   <query end >";
			logger.error("executeJdbcStoredProcedure error ", e);
			throw new DaoException("DAO_ERROR", "DAO001", errorMsg);
		}
		return resultList;
	}
	
	/**
	 * @param procedureString
	 * @param parameterSize
	 * @return String
	 */
	private String getCallableStr(String procedureString, int parameterSize) {

		// create procedure syntax
		final StringBuilder sb = new StringBuilder("{call ");
		sb.append(procedureString);
		sb.append(" (");
		for (int i = 0; i < parameterSize; i++) {
			if (i == 0) {
				sb.append("?");
			} else {
				sb.append(",?");
			}
		}
		sb.append(")}");
		return sb.toString();
	}

}
