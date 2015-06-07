package com.magikhelper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.magikhelper.dao.ServicesDao;
import com.magikhelper.entities.Services;

public class ServicesDaoTest extends AbstractDaoTest {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource(name = ServicesDao.name)
    private ServicesDao servicesDao;

    @Test
    public void getServiceByZipcode() {
    	List<Services> services = servicesDao.getServiceByZipcode(new Integer("75254"));
    	for (Services s : services) {
    		System.out.println(s);
		}
    }
    
    @Test
    public void getAllServices() {
    	List<Services> services = servicesDao.getAllServices();
    	for (Services s : services) {
    		System.out.println(s);
		}
    }
}
