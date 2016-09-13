package com.alipp.weixin.ws.contractor;

import javax.inject.Named;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alipp.weixin.domain.JsWeixinConfig;
import com.alipp.weixin.util.WeixinUtil;
import com.alipp.weixin.ws.service.BaseRestService;
import com.alipp.weixin.ws.vo.GenericResponseVO;
import com.alipp.weixin.ws.vo.json.ErrorVO;

@Named
@Path("/process")
@WebService
public class ProcessContractor  extends BaseRestService {
	
	@POST
	@Path("/weixin_config")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getWxConfig(@FormParam("url") String url) {
		
		if (url == null) {
			String errorMsg = "url cannot be null";
			ErrorVO errorVO = new ErrorVO("SE001","service validation error", errorMsg);
			return setErrorInfoResponse(errorVO);
		}
		JsWeixinConfig config = WeixinUtil.getJsWeixinConfig(url);
		return setResponseData(new GenericResponseVO(config));
	}
}
