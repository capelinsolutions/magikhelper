package com.magikhelper;

import com.magikhelper.dao.ApplicationPropertiesDao;
import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.ServicesRate;
import com.magikhelper.entities.enums.ApplicationPropertyType;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class UtilitiesDaoTest extends AbstractDaoTest {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource(name = ApplicationPropertiesDao.name)
    private ApplicationPropertiesDao applicationPropertiesDao;

    @Test
    public void testListApplicationProperties() {
        List<ApplicationProperty> properties = applicationPropertiesDao.getPropertiesByType(ApplicationPropertyType.COUNTRIES);
        for (ApplicationProperty applicationProperties : properties) {
            log.info(applicationProperties.getSortOrder() + " -- " + applicationProperties.getName() + " -- " + applicationProperties.getValue());
        }
    }

    @Test
    public void testServicesWithRates(){
    	List<ApplicationProperty> services= applicationPropertiesDao.getServicesWithRates();
    	for (ApplicationProperty service : services) {
    		log.debug(service);
    		
    		List<ServicesRate> servicesRate = service.getServicesRates();
    		for (ServicesRate serviceRate : servicesRate) {
				log.debug(serviceRate.getRate());
			}
		}
    }
}
