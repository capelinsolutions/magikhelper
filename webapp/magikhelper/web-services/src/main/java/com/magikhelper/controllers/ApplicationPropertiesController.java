package com.magikhelper.controllers;

import java.util.SortedMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.entities.enums.ApplicationPropertyType;
import com.magikhelper.services.ApplicationPropertiesService;
import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.vo.ApplicationPropertyVO;
import com.magikhelper.vo.UserVO;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping("/test")
public class ApplicationPropertiesController {

    @Autowired
    private ApplicationPropertiesService applicationProperties;

    private static final Log LOGGER = LogFactory.getLog(ApplicationPropertiesController.class);

    @RequestMapping(value="/country",method = RequestMethod.GET,headers="Accept=application/json")
    public ApplicationPropertyVO getCountry(
    		@RequestParam(value = "name", required = true, defaultValue = "Afghanistan") String name,
    		HttpSession session) {
    	com.magikhelper.entities.ApplicationProperty p = applicationProperties.getApplicationPropertiesByTypeAndKey(ApplicationPropertyType.COUNTRIES, name);
        ApplicationPropertyVO prop = new ApplicationPropertyVO(p.getPropertyId(), p.getType().name(), p.getName(), p.getValue(), p.getSortOrder());
    	LOGGER.debug("Is session new: "+session.isNew());
    	LOGGER.debug("session Id: "+session.getId());
    	LOGGER.debug("UserVO: "+(UserVO)session.getAttribute(MagikHelperConstants.SESSION_ATT_LOGIN));
        return prop;
    }
    
    @RequestMapping(value="/property",method = RequestMethod.GET,headers="Accept=application/json")
    public SortedMap<String, String> getPropertyByType(@RequestParam(value = "type", required = true, defaultValue = "COUNTRIES") String type){
    	if (ApplicationPropertyType.COUNTRIES.name().equals(type)){
        	return applicationProperties.getApplicationPropertiesMap(ApplicationPropertyType.COUNTRIES);
    	}
    	else {
        	return applicationProperties.getApplicationPropertiesMap(ApplicationPropertyType.SERVICES);
    	}
    }
}
//smagikhelper-ws/data/property?key=Pakistan