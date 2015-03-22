package com.magikhelper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.services.ServicesService;
import com.magikhelper.vo.MagikHelperService;

/**
 *
 * @author aahmed
 */
@RestController
@RequestMapping("/helperServices")
public class MagikHelperServicesController {

    @Autowired
    private ServicesService servicesService;

    @RequestMapping(method = RequestMethod.GET,headers="Accept=application/json")
    public List<MagikHelperService> getServices() {
        List<MagikHelperService> services = servicesService.getAllServices();
        return services;
    }
    
    @RequestMapping(value="/zipcode/{zipcode}",method = RequestMethod.GET,headers="Accept=application/json")
    public List<MagikHelperService> getServicesByZipcode(@PathVariable("zipcode") Integer zipcode) {
        List<MagikHelperService> services = servicesService.getServiceByZipcode(zipcode);
        return services;
    }
}
