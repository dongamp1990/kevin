package com.alipp.weixin.ws.vo.json;

/**
 *=====================================================================
 * ACP Restful Web Service Based Request Value Object 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Base Rest Request for REST Service API Implementation. 
 * 
 */
public class BaseRestRequestVO {
	
   /** 
	* The HTTP method of the incoming request
	*/ 
	private String httpMethod;

   /**
	* The URI of the incoming request
	*/ 
	private String uri;

   /** 
	* The form parameters of the incoming request
	*/
	private MultivaluedMap<String, String> formParameters;
	
	   /** 
	* The query parameters of the incoming request
	*/
	private MultivaluedMap<String, String> queryParameters;

   /**
	* The response format (either JSON or XML)
	*/
	private String format;

   /**
	* The starting page index for methods that return lists of results. This is a 0 based index.
	*/
	private int offset;

   /**
	* The number of items to return in a "page".
	*/
	private int pageSize;


   /**
	* Any additional metadata to return along with the core response.
	*/
	private List<String> includes;

	private String includeString;

   /**
	* Internally generated id to match the request with the response.
	*/
	private UUID requestId;


   /**
	* Time at which the request was received.
	*/
	private Date startTime;
	
  /**
	* Validation Error Info.
	*/
	private String validationErrorInfo;

    /**
	 * @param httpMethod
	 *            the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod)
	{
		this.httpMethod = httpMethod;
	}

    /**
	 * @return the httpMethod
	 */
	public String getHttpMethod()
	{
		return httpMethod;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri)
	{
		this.uri = uri;
	}

	/**
	 * @return the uri
	 */
	public String getUri()
	{
		return uri;
	}

	/**
	 * @param formParams
	 *            the formParameters to set
	 */
	public void setFormParameters(MultivaluedMap<String, String> formParams) {
		this.formParameters = formParams;
	}


	/**
	 * @return the formParameters
	 */
	public MultivaluedMap<String, String> getFormParameters()
	{
		return formParameters;
	}
	
	/**
	 * @param queryParams
	 *            the queryParameters to set
	 */
	public void setQueryParameters(MultivaluedMap<String, String> queryParams) {
		this.queryParameters = queryParams;		
	}

	/**
	 * @return the queryParameters
	 */
	public MultivaluedMap<String, String> getQueryParameters() {
		return queryParameters;
	}	

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format)
	{
		this.format = format;
	}

	/**
	 * @return the format
	 */
	public String getFormat()
	{
		return format;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset)
	{
		if (offset > 0) this.offset = offset;
	}

	/**
	 * @return the offset
	 */
	public int getOffset()
	{
		return offset;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param includes
	 *            the includes to set
	 */
	public void setIncludes(List<String> includes)
	{
		this.includes = includes;
	}

	/**
	 * @return the includes
	 */
	public List<String> getIncludes()
	{
		return includes;
	}

	/**
	 * get Include Parameter Name.
	 * 
	 * @return include parameter name
	 */

	public String getIncludeString()
	{
		return includeString;
	}

	/**
	 * set Include Parameter Name.
	 * 
	 * @param includeString
	 *            include string
	 */
	public void setIncludeString(String includeString)
	{
		this.includeString = includeString;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(UUID requestId)
	{
		this.requestId = requestId;
	}

	/**
	 * @return the requestId
	 */
	public UUID getRequestId()
	{
		return requestId;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * @return the validationErrorInfo
	 */
	public String getValidationErrorInfo() {
		return validationErrorInfo;
	}

	/**
	 * @param validationErrorInfo the validationErrorInfo to set
	 */
	public void setValidationErrorInfo(String validationErrorInfo) {
		this.validationErrorInfo = validationErrorInfo;
	}
}