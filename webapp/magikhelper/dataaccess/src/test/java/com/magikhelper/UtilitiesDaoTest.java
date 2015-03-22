package com.magikhelper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.magikhelper.dao.ApplicationPropertiesDao;
import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.enums.ApplicationPropertyType;

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
}
