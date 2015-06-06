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
			client.setUserId(user.getRowId());
			client.setEmail(user.getEmail());
			client.setFirstName(user.getFirstName());
			client.setLastName(user.getLastName());
			if (user.getContact()!=null){
				client.setMobilePhone(user.getContact().getMobilePhone());
				client.setStreet(user.getContact().getStreet());
				client.setAdditional(user.getContact().getAdditional());
				client.setCity(user.getContact().getCity());
				client.setZip(user.getContact().getZip());
				client.setState(user.getContact().getState());	
				client.setCountry(user.getContact().getCountry());
			}
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
		user.setFirstName(userVo.getFirstName());
		user.setLastName(userVo.getLastName());
		user.setPassword(userVo.getPassword());
		user.populatedAuditFields("SYSTEM");
		user.addUserRole(userRole);
		
		contact.setStreet(userVo.getStreet());
		contact.setAdditional(userVo.getAdditional());
		contact.setCity(userVo.getCity());
		contact.setState(userVo.getState());
		contact.setZip(userVo.getZip());
		contact.setCountry(userVo.getCountry());
		contact.setMobilePhone(userVo.getMobilePhone());
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
		
		user.setFirstName(userVo.getFirstName());
		user.setLastName(userVo.getLastName());
		user.populatedAuditFieldsOnUpdate("SYSTEM");
		
		contact.setStreet(userVo.getStreet());
		contact.setAdditional(userVo.getAdditional());
		contact.setCity(userVo.getCity());
		contact.setState(userVo.getState());
		contact.setZip(userVo.getZip());
		contact.setCountry(userVo.getCountry());
		contact.setMobilePhone(userVo.getMobilePhone());
		contact.populatedAuditFieldsOnUpdate("SYSTEM");
		
		usersDao.update(user);
	}
	@Override
	public UserVO loginUser(String email, String password) {
		User user = usersDao.loginUser(email, password);
		if (user != null){
			UserVO userVo = new UserVO();
			userVo.setUserId(user.getRowId());
			userVo.setEmail(user.getEmail());
			userVo.setFirstName(user.getFirstName());
			userVo.setLastName(user.getLastName());
			userVo.setMobilePhone(user.getContact().getMobilePhone());
			userVo.setStreet(user.getContact().getStreet());
			userVo.setAdditional(user.getContact().getAdditional());
			userVo.setCity(user.getContact().getCity());
			userVo.setZip(user.getContact().getZip());
			userVo.setState(user.getContact().getState());
			userVo.setCountry(user.getContact().getCountry());
			return userVo;
		}
		return null;
	}
	
	
}
