package com.magikhelper.services;

import java.util.List;

import com.magikhelper.vo.UserVO;
import com.magikhelper.vo.VendorVO;


public interface UsersService {
	public List<VendorVO> getVendorWithSkills(Integer vendorId);
	
    public List<UserVO> getClients(Integer clientId);
    
    public void addClient(UserVO user);
    
    public void updateUser(UserVO user);
    
    public UserVO loginUser(String email, String password);
}
