package com.magikhelper.services;

import java.util.List;

import com.magikhelper.vo.MagikHelperService;


public interface ServicesService {
    public List<MagikHelperService> getServiceByZipcode(Integer zipcode);
    
    public List<MagikHelperService> getAllServices();
}
