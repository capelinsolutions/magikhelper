/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao;

import java.util.List;

import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.enums.ApplicationPropertyType;

public interface ApplicationPropertiesDao extends GenericDao<ApplicationProperty, Integer> {

    public static final String name = "applicationPropertiesDao";

    public List<ApplicationProperty> getPropertiesByType(ApplicationPropertyType type);

    public ApplicationProperty getPropertiesByTypeAndKey(ApplicationPropertyType type, String key);

    public List<ApplicationProperty> getSortedProperties();

    public List<ApplicationProperty> getServicesWithRates();

}
