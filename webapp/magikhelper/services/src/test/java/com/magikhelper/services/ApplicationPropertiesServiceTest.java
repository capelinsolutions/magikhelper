package com.magikhelper.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.entities.enums.ApplicationPropertyType;
import com.magikhelper.vo.MagikHelperService;

public class ApplicationPropertiesServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(ApplicationPropertiesServiceTest.class);

    @Autowired
    private ApplicationPropertiesService applicationProperties;

    public ApplicationPropertiesServiceTest() {
    }

    @Test
    public void testService() {
        String properties = applicationProperties.geValuesAsCommaSeparateString(ApplicationPropertyType.COUNTRIES);
        log.debug("Properties ==========> " + properties);
    }
    
}
