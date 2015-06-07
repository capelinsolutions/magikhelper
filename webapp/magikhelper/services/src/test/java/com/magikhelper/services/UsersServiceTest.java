package com.magikhelper.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.magikhelper.exceptions.UserNotRegisteredException;
import com.magikhelper.vo.ContactVO;
import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.VendorVO;

public class UsersServiceTest extends BaseServiceTest {

    private static final Log log = LogFactory.getLog(UsersServiceTest.class);

    @Autowired
    private UsersService usersService;

    public UsersServiceTest() {
    }

    @Test
    public void getVendorWithSkills() {
        List<VendorVO> vendors = usersService.getVendorWithSkills(4);
        for (VendorVO vendor : vendors) {
        	System.out.println(vendor);
		}
    }
 
    @Test
    public void addClient(){
    	UserVO client = new UserVO();
    	ContactVO contact = new ContactVO();
    	client.setEmail("client-test"+new Date().getTime()+"@hotmail.com");
    	client.setPassword("Password");
    	
    	contact.setFirstName("Test");
    	contact.setLastName("UserVO");
    	contact.setMobilePhone("Mobile");
    	contact.setStreet("Street");
    	contact.setAdditional("Additional");
    	contact.setCity("City");
    	contact.setZip("Zip");
    	contact.setState("State");
    	contact.setCountry("Country");
    	client.setContact(contact);
    	
    	try {
			usersService.addClient(client);
			System.out.println("Client Added");
		} catch (UserNotRegisteredException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void updateUser(){
    	UserVO user = new UserVO();
    	ContactVO contact = new ContactVO();

    	user.setUserId(5);
    	contact.setFirstName("Test--");
    	contact.setLastName("UserVO--");
    	contact.setMobilePhone("Mobile--");
    	contact.setStreet("Street--");
    	contact.setAdditional("Additional--");
    	contact.setCity("City--");
    	contact.setZip("Zip--");
    	contact.setState("State--");
    	contact.setCountry("Country--");
    	user.setContact(contact);
    	
//    	usersService.updateUser(user);
    }
    
    @Test
    public void getClients() {
       List<UserVO> clients = usersService.getClients(null);
       for (UserVO client : clients) {
    	   System.out.println(client);
       }
    }
    
    @Test
    public void loginUser(){
    	System.out.println("login user test");
    	UserVO user = usersService.loginUser("leo_adnan@hotmail.com", "Test123");
    	System.out.println("Logged In User: "+user);
    }
}
