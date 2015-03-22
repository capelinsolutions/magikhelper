/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao;

import java.util.List;

import com.magikhelper.entities.Services;

public interface ServicesDao extends GenericDao<Services, Integer> {
    public static final String name = "servicesDao";

    public List<Services> getServiceByZipcode(Integer zipcode);
    
    public List<Services> getAllServices();

}
