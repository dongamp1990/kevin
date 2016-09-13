package com.alipp.weixin.ws.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipp.weixin.util.ExceptionUtil;
import com.alipp.weixin.util.JSONUtil;
import com.alipp.weixin.util.RestRequestStore;
import com.alipp.weixin.util.StringUtil;
import com.alipp.weixin.ws.vo.json.BaseErrorInfoResponseVO;
import com.alipp.weixin.ws.vo.json.BaseRestRequestVO;
import com.alipp.weixin.ws.vo.json.BaseRestResponseVO;
import com.alipp.weixin.ws.vo.json.ErrorVO;
import com.alipp.weixin.ws.vo.json.ResponseDebugVO;
import com.alipp.weixin.ws.vo.json.StatusVO;


public abstract class BaseRestService {
	protected static final Logger logger = LoggerFactory.getLogger(BaseRestService.class);
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	@Context
	protected HttpServletRequest request;

	/**
	 * get Base Rest Request.
	 * 
	 * @return base rest request
	 */
	public final BaseRestRequestVO getBaseRestRequest() {
		/** Value initialized in RestRequestPreprocessor */
		return (BaseRestRequestVO) RestRequestStore.getValue("baserestrequest");
	}

	/**
	 * get input parameter value based on parameter key.
	 * 
	 * @param parameter
	 *            key name
	 * @return input parameter key value
	 */
	protected String getFormParameterValue(String parameter) {
		MultivaluedMap<String, String> params = getBaseRestRequest().getFormParameters();
		if (params.get(parameter) == null || StringUtil.isBlank(params.get(parameter).get(0)))	{
			return null;
		} else {
			logger.debug("getFormParameterValue - "+ parameter +" : " + params.get(parameter).get(0));
			return params.get(parameter).get(0);
		}
	}	
	
	protected <T> T getParam(Class<T> clazz) {
		MultivaluedMap<String, String> params = getBaseRestRequest().getFormParameters();
		if (params == null) {
			return null;
		}
		try {
			String jsonStr = JSONUtil.objectToJson(params);
			return JSONUtil.jsonToObject(jsonStr, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}  
	}

	/**
	 * set the Response Data.
	 * 
	 * @param response base response
	 * 
	 * @return base rest response
	 */	
	protected Response setResponseData(BaseRestResponseVO response)	{		
		StatusVO status = response.getResponseStatus();
		String code = String.valueOf(200);
		if (status == null) {	
			status = new StatusVO();
			status.setCode(code);
			status.setMessage(SUCCESS);
		} else if (NumberUtils.isDigits(status.getCode())) {
			code = status.getCode();
		}
		if (getBaseRestRequest()!=null) {
			ResponseDebugVO debug = new ResponseDebugVO();
			debug.setTimings(getBaseRestRequest().getStartTime());
			debug.setDescription(getBaseRestRequest().getUri());
			status.setDebug(debug);
		}
		response.setResponseStatus(status);
		RestRequestStore.clear();
//		return Response.status(Integer.valueOf(code)).entity(response).build();
		return Response.status(Integer.valueOf(status.getCode())).entity(response).header("Access-Control-Allow-Origin", "*").build();
	}

	/**
	 * set the Error Info Response Data.
	 * 
	 * @param serviceException service exception
	 *  
	 * @return base rest response
	 */	
	protected Response setErrorInfoResponse(ErrorVO errorVo)	{
		StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(errorVo);
		BaseErrorInfoResponseVO response = new BaseErrorInfoResponseVO(status);
		RestRequestStore.clear();
//		return Response.status(Integer.valueOf(status.getCode())).entity(response).build();
		return Response.status(Integer.valueOf(status.getCode())).entity(response).header("Access-Control-Allow-Origin", "*").build();
	}

	/**
	 * set the Error Info Response Data.
	 * 
	 * @param message error message
	 *  
	 * @return base rest response
	 *//*	
	protected Response setErrorInfoResponse(String message)	{					
		BaseErrorInfoResponseVO errorInfoResponse = new BaseErrorInfoResponseVO(new ErrorVO(ErrorDescription.ERR_CD_SERVICE_VALIDATION, 
				                                              message, ErrorDescription.ERR_MSG_SERVICE_VALIDATION));
		errorInfoResponse.setCode(404);
		errorInfoResponse.setTimings(getBaseRestRequest().getStartTime());
		RestRequestStore.clear();
		return null; //Response.serverError().status(errorInfoResponse.getCode()).entity(errorInfoResponse).build();		
	}*/
}