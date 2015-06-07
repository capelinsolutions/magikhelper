package com.magikhelper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.magikhelper.dao.UsersDao;
import com.magikhelper.entities.User;

public class UserDaoTest extends AbstractDaoTest {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource(name = UsersDao.name)
    private UsersDao usersDao;

    @Test
    public void getVendorWithSkills() {
    	List<Object[]> vendors = usersDao.getVendorWithSkills(4);
    	for (Object[] data : vendors) {
    		System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+" "+data[4]+" "+data[5]+" "+data[6]);
		}
    }
    
    @Test
    public void getCleints() {
    	List<User> clients = usersDao.getCleints(3);
    	for (User user : clients) {
    		System.out.println(user);
    		System.out.println(user.getContact());
		}
    }
    
    @Test
    public void loginUser(){
    	String email="client1@hotmail.com";
    	String password="Test123";
    	User user = usersDao.loginUser(email, password);
    	System.out.println("=======>"+user);
    }
}
