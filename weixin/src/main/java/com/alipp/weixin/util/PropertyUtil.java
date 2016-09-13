package com.alipp.weixin.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.StringValueResolver;

/**
 * @author kjain
 * This class is used to return all the properties in a Map.  
 * Along with that it will also resolve the place holders.
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer {

    static Map<String, String> props = new HashMap<String, String>();

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.Properties)
     */
    @SuppressWarnings("static-access")
	@Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {

        this.props.clear();
        for (Entry<Object, Object> e: props.entrySet())
            this.props.put(e.getKey().toString(), e.getValue().toString());

        super.processProperties(beanFactory, props);
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.PlaceholderConfigurerSupport#doProcessProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, org.springframework.util.StringValueResolver)
     */
    @Override
    protected void doProcessProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
            StringValueResolver valueResolver) {
     	
        super.doProcessProperties(beanFactoryToProcess, valueResolver);
        
        for(Entry<String, String> e: props.entrySet())
            e.setValue(valueResolver.resolveStringValue(e.getValue()));
         

   }

   public static String get(Object key) { return props.get(key); }
}