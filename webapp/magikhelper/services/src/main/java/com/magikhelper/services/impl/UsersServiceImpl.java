package com.magikhelper.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.magikhelper.dao.RolesDao;
import com.magikhelper.dao.UsersDao;
import com.magikhelper.entities.Contact;
import com.magikhelper.entities.SystemRole;
import com.magikhelper.entities.User;
import com.magikhelper.entities.UserRole;
import com.magikhelper.exceptions.UserNotRegisteredException;
import com.magikhelper.services.UsersService;
import com.magikhelper.utils.MagikHelperConstants;
import com.magikhelper.vo.ContactVO;
import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.MagikHelperService;
import com.magikhelper.vo.VendorVO;

@Service(value = "usersServices")
public class UsersServiceImpl implements UsersService {

    private static final Log log = LogFactory.getLog(UsersServiceImpl.class);
    
    @Autowired
    UsersDao usersDao;
    
    @Autowired
    RolesDao rolesDao;

	@Override
	public List<VendorVO> getVendorWithSkills(Integer pVendorId) {
		List<Object[]> vendorObjs = usersDao.getVendorWithSkills(pVendorId);
		List<VendorVO> vendors = new ArrayList<VendorVO>();
		List<Integer> vendorsId = new ArrayList<Integer>();
		
		int index=-1;
		for (Object[] vendorData : vendorObjs) {

			Integer vendorId = (Integer)vendorData[0];
			String email = (String)vendorData[1];
			String firstName = (String)vendorData[2];
			String lastName = (String)vendorData[3];
			BigDecimal rate = (BigDecimal)vendorData[4];
			Integer serviceId =  (Integer)vendorData[5];
			String serviceName = (String)vendorData[6];
			
			MagikHelperService vendorSkill=new MagikHelperService(serviceId, serviceName, rate);
			
			if(vendorsId.contains(vendorId)==false){
				VendorVO vendor=new VendorVO(vendorId,email,firstName,lastName);
				List<MagikHelperService> services = new ArrayList<MagikHelperService>();
				services.add(vendorSkill);
				vendor.setServices(services);
				vendors.add(vendor);
				
				vendorsId.add(vendorId);
				index++;
			}
			else {
				vendors.get(index).getServices().add(vendorSkill);
			}
		}
		return vendors;
	}

	@Override
    public List<UserVO> getClients(Integer clientId){
    	List<UserVO> clients = new ArrayList<UserVO>();
    	List<User> users = usersDao.getCleints(clientId);
    	for (User user : users) {
			UserVO client = new UserVO();
			ContactVO contact = new ContactVO();
			client.setUserId(user.getRowId());
			client.setEmail(user.getEmail());
			
			if (user.getContact()!=null){
				contact.setLastName(user.getContact().getLastName());
				contact.setFirstName(user.getContact().getFirstName());
				contact.setMobilePhone(user.getContact().getMobilePhone());
				contact.setStreet(user.getContact().getStreet());
				contact.setAdditional(user.getContact().getAdditional());
				contact.setCity(user.getContact().getCity());
				contact.setZip(user.getContact().getZip());
				contact.setState(user.getContact().getState());	
				contact.setCountry(user.getContact().getCountry());
			}
			
			client.setContact(contact);
			clients.add(client);
		}
    	return clients;
    }

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void addClient(UserVO userVo) {
		User user = new User();
		Contact contact = new Contact();
		SystemRole role = rolesDao.getReference(MagikHelperConstants.SYS_ROLE_CLIENT);
		UserRole userRole = new UserRole();
		
		userRole.setSystemRole(role);
		userRole.populatedAuditFields("SYSTEM");
		
		user.setEmail(userVo.getEmail());
		user.setPassword(userVo.getPassword());
		user.populatedAuditFields("SYSTEM");
		user.addUserRole(userRole);

		
		contact.setFirstName(userVo.getContact().getFirstName());
		contact.setLastName(userVo.getContact().getLastName());
		contact.setStreet(userVo.getContact().getStreet());
		contact.setAdditional(userVo.getContact().getAdditional());
		contact.setCity(userVo.getContact().getCity());
		contact.setState(userVo.getContact().getState());
		contact.setZip(userVo.getContact().getZip());
		contact.setCountry(userVo.getContact().getCountry());
		contact.setMobilePhone(userVo.getContact().getMobilePhone());
		contact.populatedAuditFields("SYSTEM");
		
		user.setContact(contact);
		
		try {
			usersDao.add(user);
			userVo.setUserId(user.getRowId());
		} 
		catch (DataIntegrityViolationException e){
//			log.error(e,e);
			if(e.getMessage().indexOf("ConstraintViolationException") != -1 ){
				throw new UserNotRegisteredException("Duplicate User");
			}
			else{
				throw new UserNotRegisteredException("Generic Error");
			}
		}
		/*catch (ConstraintViolationException e){
			log.error(e,e);
		}
		catch (JpaSystemException e) {
			log.error(e, e);
			
		}*/
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void updateUser(UserVO userVo) {
		
		User user = usersDao.get(userVo.getUserId());
		Contact contact = user.getContact();
		
		user.populatedAuditFieldsOnUpdate("SYSTEM");

		contact.setFirstName(userVo.getContact().getFirstName());
		contact.setLastName(userVo.getContact().getLastName());
		contact.setStreet(userVo.getContact().getStreet());
		contact.setAdditional(userVo.getContact().getAdditional());
		contact.setCity(userVo.getContact().getCity());
		contact.setState(userVo.getContact().getState());
		contact.setZip(userVo.getContact().getZip());
		contact.setCountry(userVo.getContact().getCountry());
		contact.setMobilePhone(userVo.getContact().getMobilePhone());
		contact.populatedAuditFieldsOnUpdate("SYSTEM");
		
		usersDao.update(user);
	}
	@Override
	public UserVO loginUser(String email, String password) {
		User user = usersDao.loginUser(email, password);
		if (user != null){
			UserVO userVo = new UserVO();
			ContactVO contact = new ContactVO();
			userVo.setUserId(user.getRowId());
			userVo.setEmail(user.getEmail());
			userVo.setContact(contact);
			contact.setFirstName(user.getContact().getFirstName());
			contact.setLastName(user.getContact().getLastName());
			contact.setMobilePhone(user.getContact().getMobilePhone());
			contact.setStreet(user.getContact().getStreet());
			contact.setAdditional(user.getContact().getAdditional());
			contact.setCity(user.getContact().getCity());
			contact.setZip(user.getContact().getZip());
			contact.setState(user.getContact().getState());
			contact.setCountry(user.getContact().getCountry());
			
			return userVo;
		}
		return null;
	}
	
	
}
