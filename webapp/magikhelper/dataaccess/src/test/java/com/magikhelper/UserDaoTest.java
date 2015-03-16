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
    public void testGetVendorSkills() {
    	List<Object[]> vendors = usersDao.getVendorWithSkills(5);
    	for (Object[] data : vendors) {
    		log.debug(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+" "+data[4]+" "+data[5]+" "+data[6]);
		}
    }
    
    @Test
    public void testGetClients() {
    	List<User> clients = usersDao.getCleints(3);
    	for (User user : clients) {
			log.debug(user);
			log.debug(user.getContact());
		}
    }
    
    @Test
    public void testLogin(){
    	/*String email="client1@hotmail.com";
    	String password="Test123";
    	User user = usersDao.loginUser(email, password);
    	log.debug("=======>"+user);*/
    }
}
