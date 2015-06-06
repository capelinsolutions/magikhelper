package com.magikhelper.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.exceptions.UserNotRegisteredException;
import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.MagikHelperService;
import com.magikhelper.vo.VendorVO;

public class UsersServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(UsersServiceTest.class);

    @Autowired
    private UsersService usersService;

    public UsersServiceTest() {
    }

    @Test
    public void testVendorSkills() {
        List<VendorVO> vendors = usersService.getVendorWithSkills(6);
        for (VendorVO vendor : vendors) {
        	log.debug(vendor);
			for (MagikHelperService skill : vendor.getServices()) {
				log.debug(skill);
			}
		}
    }
 
    @Test
    public void testGetClients() {
       List<UserVO> clients = usersService.getClients(null);
       for (UserVO client : clients) {
    	   log.debug(client);
       }
    }
    
    @Test
    public void testAddClient(){
    	UserVO client = new UserVO();
    	client.setEmail("client-test"+new Date().getTime()+"@hotmail.com");
    	client.setPassword("Password");
    	client.setFirstName("Test");
    	client.setLastName("UserVO");
    	client.setMobilePhone("Mobile");
    	client.setStreet("Street");
    	client.setAdditional("Additional");
    	client.setCity("City");
    	client.setZip("Zip");
    	client.setState("State");
    	client.setCountry("Country");
    	
    	try {
			usersService.addClient(client);
		} catch (UserNotRegisteredException e) {
			e.printStackTrace();
		}
    }
}
