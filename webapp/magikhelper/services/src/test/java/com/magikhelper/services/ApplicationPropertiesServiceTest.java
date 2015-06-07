package com.magikhelper.services;

import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.entities.enums.ApplicationPropertyType;

public class ApplicationPropertiesServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(ApplicationPropertiesServiceTest.class);

    @Autowired
    private ApplicationPropertiesService applicationProperties;

    public ApplicationPropertiesServiceTest() {
    }

    @Test
    public void getApplicationPropertiesMap(){
    	SortedMap<String, String> props= applicationProperties.getApplicationPropertiesMap(ApplicationPropertyType.SERVICES);
    	
    	for (String key : props.keySet()) {
    		System.out.println("Key : " + key.toString() + " Value : " + props.get(key));
    	}
    }
    
    @Test
    public void getApplicationPropertiesByTypeAndKey(){
    	
    }
    
    @Test
    public void geValuesAsCommaSeparateString(){
        String properties = applicationProperties.geValuesAsCommaSeparateString(ApplicationPropertyType.BOOKING_STATUS);
        System.out.println("Properties ==========> " + properties);    	
    }
    
    @Test
    public void propertyByNameAndType(){
    	System.out.println(applicationProperties.propertyByNameAndType(ApplicationPropertyType.COUNTRIES, "Pakistan"));
    }
    
    @Test
    public void propertiesByType(){
    	SortedMap<String, String> props=applicationProperties.propertiesByType(ApplicationPropertyType.BOOKING_STATUS);
    	for (String key : props.keySet()) {
    		System.out.println("Key : " + key.toString() + " Value : " + props.get(key));
    	}
    }
    
}
