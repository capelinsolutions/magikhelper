package com.magikhelper.services;

import java.util.SortedMap;

import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.enums.ApplicationPropertyType;

public interface ApplicationPropertiesService {

    public ApplicationProperty getApplicationPropertiesByTypeAndKey(ApplicationPropertyType type, String key);

    public SortedMap<String, String> getApplicationPropertiesMap(ApplicationPropertyType type);

    public void refreshPropertiesCache();

    public String propertyByNameAndType(ApplicationPropertyType type, String name);

    public SortedMap<String, String> propertiesByType(ApplicationPropertyType type);

    public String geValuesAsCommaSeparateString(ApplicationPropertyType type);
 
}
