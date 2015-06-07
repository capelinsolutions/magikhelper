package com.magikhelper.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.vo.MagikHelperService;

public class ServicesServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(ServicesServiceTest.class);

    @Autowired
    private ServicesService servicesService;

    public ServicesServiceTest() {
    }

    @Test
    public void getServiceByZipcode() {
    	List<MagikHelperService> magikHelperServices = servicesService.getServiceByZipcode(new Integer(75254));
    	System.out.println(magikHelperServices);
    }
    
    @Test
    public void getAllServices(){
    	List<MagikHelperService> magikHelperServices = servicesService.getAllServices();
    	System.out.println(magikHelperServices);
    }
    
}
