package com.magikhelper.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magikhelper.dao.ServicesDao;
import com.magikhelper.entities.Services;
import com.magikhelper.services.ServicesService;
import com.magikhelper.vo.MagikHelperService;

@Service(value = "servicesService")
public class ServicesServiceImpl implements ServicesService {

    private static final Log log = LogFactory.getLog(ServicesServiceImpl.class);
    
    @Autowired
    ServicesDao servicesDao;

	@Override
	public List<MagikHelperService> getServiceByZipcode(Integer zipcode) {
		List<MagikHelperService> magikHelperServices = new ArrayList<MagikHelperService>();
		
		List<Services> services = servicesDao.getServiceByZipcode(zipcode);
		
		for (Services s : services) {
			MagikHelperService mhs = new MagikHelperService();
			mhs.setServiceId(s.getService().getSortOrder());
			mhs.setName(s.getService().getValue());
			mhs.setRate(s.getRate());
			mhs.setZipcode(s.getZipcode());
			
			magikHelperServices.add(mhs);
		}
		
		return magikHelperServices;
	}
    
	@Override
	public List<MagikHelperService> getAllServices() {
		List<MagikHelperService> magikHelperServices = new ArrayList<MagikHelperService>();
		
		List<Services> services = servicesDao.getAllServices();
		
		for (Services s : services) {
			MagikHelperService mhs = new MagikHelperService();
			mhs.setServiceId(s.getService().getSortOrder());
			mhs.setName(s.getService().getValue());
			mhs.setRate(s.getRate());
			mhs.setZipcode(s.getZipcode());
			
			magikHelperServices.add(mhs);
		}
		
		return magikHelperServices;
	}
}
