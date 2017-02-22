package org.alipp.api.services;

import java.util.HashMap;
import java.util.Map;

import org.alipp.api.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
	private static Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	protected BaseController controller;
	private static Map<Class<? extends BaseService>, BaseService> INSTANCE_MAP = new HashMap<Class<? extends BaseService>, BaseService>();

	@SuppressWarnings("unchecked")
	public static <Ser extends BaseService> Ser getInstance(Class<Ser> clazz, BaseController controller){
        Ser service = (Ser) INSTANCE_MAP.get(clazz);
        if (service == null){
            try {
                service = clazz.newInstance();
                INSTANCE_MAP.put(clazz, service);
            } catch (InstantiationException e) {
            	logger.error("InstantiationException", e);
            } catch (IllegalAccessException e) {
            	logger.error("IllegalAccessException", e);
            }
        }
        service.controller = controller;
        return service;
    }
}
