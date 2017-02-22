package org.alipp.api.controller;

import java.lang.reflect.Field;

import org.alipp.api.services.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.core.Controller;

public class BaseController extends Controller {
	private static Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	@SuppressWarnings("unchecked")
	public BaseController(){
		Field[] fields = this.getClass().getDeclaredFields();
        for (int i=0;i < fields.length; i++){
            Field field = fields[i];
            @SuppressWarnings("rawtypes")
			Class clazz = field.getType();
            if(BaseService.class.isAssignableFrom(clazz) && clazz != BaseService.class){
                try {
                	field.setAccessible(true);
                    field.set(this, BaseService.getInstance(clazz, this));
                } catch (IllegalAccessException e) {
                	logger.error("IllegalAccessException", e);
                }
            }
        }
    }
}
