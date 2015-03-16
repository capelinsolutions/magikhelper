package com.magikhelper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magikhelper.services.ApplicationPropertiesService;
import com.magikhelper.vo.MagikHelperService;

/**
 *
 * @author aahmed
 */
@RestController
//@RequestMapping("/")
public class MagikHelperServicesController {

    @Autowired
    private ApplicationPropertiesService applicationProperties;

    @RequestMapping(value="/helperServices",method = RequestMethod.GET,headers="Accept=application/json")
    public List<MagikHelperService> getServices() {
        List<MagikHelperService> services = applicationProperties.getServicesWithRates();
        return services;
    }
}
