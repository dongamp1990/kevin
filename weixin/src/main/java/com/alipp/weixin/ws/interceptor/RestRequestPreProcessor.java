package com.alipp.weixin.ws.interceptor;
/**
 *=====================================================================
 * Restful Request PreProcessor
 *
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.alipp.weixin.constant.CommonConstant;
import com.alipp.weixin.util.RestRequestStore;
import com.alipp.weixin.ws.vo.json.BaseRestRequestVO;

@Named
@Provider
@ServerInterceptor
public class RestRequestPreProcessor implements PreProcessInterceptor, MessageBodyReaderInterceptor {
	Logger logger = LoggerFactory.getLogger(RestRequestPreProcessor.class);

	@Context
	HttpServletRequest servletRequest;

	public static final ThreadLocal<String> timeZone_local = new ThreadLocal<String>();
	
	public static final ThreadLocal<Long> enterpriseID_local = new ThreadLocal<Long>();

	@Value("${token_validation_flag}")
	private String tokenValidationFlagStr;

	@SuppressWarnings("unchecked")
	public ServerResponse preProcess(HttpRequest request, ResourceMethod resourceMethod) throws Failure, WebApplicationException {

		/*
		 * SECURITY CHECK will has to be implemented
		 */
		RestRequestStore.setValue(CommonConstant.BASE_REST_REQ,updateRequestProperties(request)); 
		Enumeration<String> attributeNames = servletRequest.getParameterNames();
		StringBuilder params = new StringBuilder();
		while (attributeNames.hasMoreElements()) {
			String paramName = (String) attributeNames.nextElement();
			String paramValue = servletRequest.getParameter(paramName);
			params.append(paramName).append(":").append(paramValue).append(", ");
		}

//		logger.info("The oauth security flag is "+ enableAuthentication);

//		String timezone = servletRequest.getHeader(CommonConstant.REQ_PARAM_TIMEZONE);
		String authorizationStr = servletRequest.getHeader(CommonConstant.REQ_PARAM_AUTHORIZATION);
//		if(StringUtils.isNotBlank(timezone)){
//			timeZone_local.set(timezone);
//			logger.info("timeZone is "+ timezone);
//		}
		
		boolean tokenValidationFlag = tokenValidationFlagStr==null? true: "true".equalsIgnoreCase(tokenValidationFlagStr);
		
		if(tokenValidationFlag){
			String requestURL = request.getUri().getRequestUri().getPath();
//			if(requestURL==null || RestConst.LOGIN_URL1.equals(requestURL)|| RestConst.LOGIN_URL2.equals(requestURL)
//					|| requestURL.startsWith(RestConst.EXPRESS_URL1) || requestURL.startsWith(RestConst.EXPRESS_URL2)){
//				logger.info("Skip token validation : "+requestURL);
//			}else{
//				try {
//					//Validate the token information
//					oauthService.validateToken(authorizationStr);
//				} catch (ServiceException e) {
//					StatusVO status = ExceptionUtil.genStatusVOFromErrorVO(e);
//					BaseErrorInfoResponseVO response = new BaseErrorInfoResponseVO(status);
//					return new ServerResponse(response, Integer.valueOf(status.getCode()), new Headers<Object>());
//				}
//				AccessToken accessToken = OauthService.getAccessToken();
//
//				String clientIP = servletRequest.getHeader(RestConst.TOKEN_REQUEST_CLIENT_IP);
//				String sessionID = servletRequest.getHeader(RestConst.TOKEN_REQUEST_SESSION_ID);
//				
//				accessToken.setClientIP(clientIP);
//				accessToken.setSessionId(sessionID);
//			}
		}
//		String enterpriseID = servletRequest.getHeader(RestConst.HEADER_REQUEST_ENTERPRISE_ID);
//		String deviceType = servletRequest.getHeader(RestConst.HEADER_REQUEST_DEVICE_TYPE);
//		String deviceInfo = servletRequest.getHeader(RestConst.HEADER_REQUEST_DEVICE_INFO);
//		String appVersion = servletRequest.getHeader(RestConst.HEADER_REQUEST_APP_VERSION);
		
//		String versionInfo = new StringBuilder("EnterpriseID: ").append(enterpriseID).append(", deviceType: ").append(deviceType)
//				            .append(", appVersion: ").append(appVersion).toString();
		String requestInfo = new StringBuilder("Request URI: ").append(request.getUri().getAbsolutePath())
				            .append(", Request Parameters: ").append(params.toString()).toString();
//		logger.info(versionInfo);
		logger.info(requestInfo);
		
//		enterpriseID_local.set(enterpriseID==null?null:Long.parseLong(enterpriseID));
		return null;
	}


	@SuppressWarnings("unchecked")
	public Object read(MessageBodyReaderContext context) throws IOException, WebApplicationException {

		String data = null;
		Object object = null;
		BufferedReader reader = null;
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder sBuilder = new StringBuilder();
		InputStream inputStream = context.getInputStream();

		if (inputStream != null) {
			reader = new BufferedReader(new InputStreamReader(inputStream,Charset.forName("UTF-8")));            
			while ((data = reader.readLine()) != null) {
				sBuilder.append(data);
			}
		}
		logger.info("reading data: "+ sBuilder.toString());

		try {
			if(sBuilder.length() > 0) {
				object = mapper.readValue(sBuilder.toString(), context.getType());
			}    		
		} catch (Exception e) {
			BaseRestRequestVO baseRestRequest = (BaseRestRequestVO)RestRequestStore.getValue(CommonConstant.BASE_REST_REQ);
			baseRestRequest.setValidationErrorInfo(e.getMessage());             
		}            	
		return object;
	}    

	private BaseRestRequestVO updateRequestProperties(HttpRequest request) {

		BaseRestRequestVO baseReq = new BaseRestRequestVO();    	
		baseReq.setFormParameters(request.getFormParameters());
//		baseReq.setFormat(RestConst.REQ_DATA_FORMAT);
		baseReq.setHttpMethod(request.getHttpMethod());
		baseReq.setUri(request.getUri().getBaseUri().getRawQuery());	    	
		baseReq.setRequestId(UUID.randomUUID());
		baseReq.setStartTime(new Date());		
		return baseReq;    	
	}

}
