package com.alipp.weixin.util;

import org.apache.commons.lang.StringUtils;

import com.alipp.weixin.ws.service.BaseRestService;
import com.alipp.weixin.ws.vo.json.BaseRestRequestVO;
import com.alipp.weixin.ws.vo.json.ErrorVO;
import com.alipp.weixin.ws.vo.json.ResponseDebugVO;
import com.alipp.weixin.ws.vo.json.StatusVO;

public class ExceptionUtil {

	/**
	 * Method to generate the error code depending on the exception type
	 * @param exceptionType
	 * @return int
	 */
	public static int getErrorCode(String exceptionType)  {
		int errorCode = 500; // default value
		if (StringUtils.isNotBlank(exceptionType)) {
			String codeForType = PropertyUtil.get(exceptionType);
			if (StringUtils.isNumeric(codeForType)) {
				errorCode = Integer.valueOf(codeForType);
			}
		}
		return errorCode;
	}

	/**
	 * Generate status VO from Error VO.
	 * @param baseException
	 * @return StatusVO
	 */
	public static StatusVO genStatusVOFromErrorVO(ErrorVO errorVO) {
		// retrieve code from exception type.
		String code = errorVO.getErrCd();
		StatusVO status = new StatusVO();
		status.setCode(code);
		status.setMessage(BaseRestService.FAILURE);
		if (errorVO != null) {
			status.setErrors(errorVO.getErrors());
		}
		// If debug is set.
		BaseRestRequestVO baseRequest = null;
		Object baseRequestObject = RestRequestStore.getValue("baserestrequest");
		if(baseRequestObject!=null){
			baseRequest = (BaseRestRequestVO)baseRequestObject;
			ResponseDebugVO debug = new ResponseDebugVO();
			debug.setTimings(baseRequest.getStartTime());
			debug.setDescription(baseRequest.getUri());
			status.setDebug(debug);
		}
		return status;
	}

}