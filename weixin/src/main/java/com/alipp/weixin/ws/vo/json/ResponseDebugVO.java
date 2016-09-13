package com.alipp.weixin.ws.vo.json;

/**
 *=====================================================================
 * ACP Restful Web Service Based Response Value Object 
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)
@JsonPropertyOrder({"code", "build","serverName","duration","description"})
public class ResponseDebugVO 
{
	private static final Logger logger = Logger.getLogger(ResponseDebugVO.class.getName());
	
	protected String build = "1.0";
	
	protected long duration;

	protected Date endDateTime;

	protected String serverName = "localhost";

	protected Date startDateTime = new Date();

	protected String description;
	
	
	
	 
	

	/**
	 * default constructor for Base Rest Response.
	 */
	public ResponseDebugVO()
	{
		try
		{
			this.serverName = InetAddress.getLocalHost().getHostName();			
		}
		catch (UnknownHostException e)
		{
			logger.error("UnknownHostException - {} "+ e.getMessage());
		}
	}

	/**
	 * get Build Name.
	 * 
	 * @return build name
	 */	
	@JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
	public String getBuild()
	{
		return build;
	}

	
	/**
	 * get Duration.
	 * 
	 * @return duration
	 */	
	public long getDuration()
	{
		return duration;
	}

	/**
	 * get Server Name.
	 * 
	 * @return server name
	 */	
	public String getServerName()
	{
		return serverName;
	}


	@JsonIgnore
	public Date getStartDateTime(){
		return startDateTime;
	}

	/**
	 * get Build Version.
	 * 
	 * @param build
	 *            build version
	 */
	@JsonProperty("build")
	public void setBuild(String build)
	{
		this.build = build;
	}


	/**
	 * set Duration.
	 * 
	 * @param duration
	 *            duration
	 */
	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	/**
	 * set End Time.
	 * 
	 * @param date
	 *            end time
	 */
	public void setEndTime(Date date)
	{
		this.endDateTime = date;
	}

	/**
	 * set Server Name.
	 * 
	 * @param serverName
	 *            server name
	 */
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	/**
	 * set Start Time.
	 * 
	 * @param startTime
	 *            start time
	 */
	public void setStartTime(Date startTime)
	{
		this.startDateTime = startTime;
	}



	/**
	 * Set the endDateTime and duration when finished.
	 */
	public void setTimings()
	{
		setEndTime(Calendar.getInstance().getTime());
		this.duration = endDateTime.getTime() - startDateTime.getTime();
	}

	/**
	 * Updates startDateTime with request startDate time.
	 * 
	 * Sets endDateTime and calculates request duration.
	 * 
	 * @param requestStartTime
	 *            request Start Time
	 */
	public void setTimings(Date requestStartTime)
	{
		setStartTime(requestStartTime);
		setEndTime(Calendar.getInstance().getTime());
		this.duration = endDateTime.getTime() - startDateTime.getTime();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}