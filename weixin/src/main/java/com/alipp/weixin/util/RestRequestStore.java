package com.alipp.weixin.util;

/**
 *=====================================================================
 * Restful Request Object Store 
 *
 * RequestStore can be used to store request scope objects
 * 
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */	 


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class RestRequestStore {
	private static final Logger logger = LoggerFactory.getLogger(RestRequestStore.class);

	//	private RestRequestStore(){
    //
    //	}

	
	private static final ThreadLocal<Map<String, Object>> STORE = new ThreadLocal<Map<String, Object>>() {
		protected Map<String, Object> initialValue() {
			try	{
				return new HashMap<String, Object>();
			}
			finally {
				logger.debug("Request store initialized");
			}
		};
	};

	/**
	 * Get object from RequestStore.
	 * 
	 * @param key
	 *            Key for the Object.
	 * @return the Object stored in RequestStore
	 */
	public static Object getValue(String key) {
		Map<String, Object> map = STORE.get();
		return map.get(key);
	}

	/**
	 * Store the object to RequestStore.
	 * 
	 * @param key
	 *            Key for the object.
	 * @param value
	 *            Object to store.
	 */
	public static void setValue(String key, Object value) {
		STORE.get().put(key, value);
	}

	/**
	 * Clear the Object store.
	 */
	public static void clear() {
		STORE.remove();
		logger.debug("Request Store is cleared");
	}
}
