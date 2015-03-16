package com.magikhelper.services.impl;

import com.magikhelper.dao.ApplicationPropertiesDao;
import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.ServicesRate;
import com.magikhelper.entities.enums.ApplicationPropertyType;
import com.magikhelper.services.ApplicationPropertiesService;
import com.magikhelper.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "applicationPropertiesService")
public class ApplicationPropertiesServiceImpl implements ApplicationPropertiesService {

    private static final Log log = LogFactory.getLog(ApplicationPropertiesServiceImpl.class);
    @Autowired
    ApplicationPropertiesDao applicationPropertiesDao;

    @Override
    public SortedMap<String, String> getApplicationPropertiesMap(ApplicationPropertyType type) {
        SortedMap<String, String> map = new TreeMap<String, String>();
        List<ApplicationProperty> lst = applicationPropertiesDao.getPropertiesByType(type);
        for (ApplicationProperty ap : lst) {
            map.put(ap.getName(), ap.getValue());
        }
        return map;
    }

    @Override
    public ApplicationProperty getApplicationPropertiesByTypeAndKey(ApplicationPropertyType type, String key) {
    	ApplicationProperty applicationProperties = applicationPropertiesDao.getPropertiesByTypeAndKey(type, key);
        return applicationProperties;
    }

    @Override
    public String geValuesAsCommaSeparateString(ApplicationPropertyType type) {
        StringBuilder result = new StringBuilder();
        SortedMap<String, String> lst = propertiesByType(type);
        for (Map.Entry<String, String> entry : lst.entrySet()) {
            result.append(entry.getValue());
            result.append(",");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }
    private SortedMap<ApplicationPropertyType, SortedMap<String, String>> propertiesCache = new TreeMap<ApplicationPropertyType, SortedMap<String, String>>();

    @PostConstruct
    public void initPropertiesCache() {
        log.info("Initializing application properties...");
        refreshPropertiesCache();
    }

    @Override
    public void refreshPropertiesCache() {
        log.info("Refreshing application properties...");
        log.info("Setting existing map to null");

        SortedMap<ApplicationPropertyType, SortedMap<String, String>> tempPropertiesCache
                = new TreeMap<ApplicationPropertyType, SortedMap<String, String>>();

        List<ApplicationProperty> sortedProperties = applicationPropertiesDao.getSortedProperties();

        log.info("Number of properties fetched from database = [" + sortedProperties.size() + "]");
        for (ApplicationProperty property : sortedProperties) {
            addPropertyToCache(tempPropertiesCache, property);
        }
        propertiesCache = tempPropertiesCache;
    }

    private void addPropertyToCache(SortedMap<ApplicationPropertyType, SortedMap<String, String>> propertiesCache, ApplicationProperty property) {
//        log.info("Property: Type ["+property.getType() +"] Name : ["+property.getName()+"] Value : ["+property.getValue()+"]");
        if (propertiesCache.get(property.getType()) != null) {
            propertiesCache.get(property.getType()).put(property.getName(), property.getValue());
        } else {
            SortedMap<String, String> innerSortedMap = new TreeMap<String, String>();
            innerSortedMap.put(property.getName(), property.getValue());

            propertiesCache.put(property.getType(), innerSortedMap);
        }
    }

    @Override
    public String propertyByNameAndType(ApplicationPropertyType type, String name) {
        if (propertiesCache.get(type) == null) {
            return null;
        }

        return propertiesCache.get(type).get(name);
    }

    @Override
    public SortedMap<String, String> propertiesByType(ApplicationPropertyType type) {
        return propertiesCache.get(type);
    }

	@Override
	public List<MagikHelperService> getServicesWithRates() {
		List<MagikHelperService> services = new ArrayList<MagikHelperService>();
		List<ApplicationProperty> appServices= applicationPropertiesDao.getServicesWithRates();
    	for (ApplicationProperty service : appServices) {
    		MagikHelperService s = new MagikHelperService(service.getPropertyId(), service.getName());
    		List<ServicesRate> servicesRate = service.getServicesRates();
    		for (ServicesRate serviceRate : servicesRate) {
				s.setRate(serviceRate.getRate());
			}
    		services.add(s);
		}
    	return services;
	}
    
    
}
