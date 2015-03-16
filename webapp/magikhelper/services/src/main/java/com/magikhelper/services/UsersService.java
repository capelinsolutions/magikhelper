package com.magikhelper.services;

import java.util.List;

import com.magikhelper.vo.Client;
import com.magikhelper.vo.VendorVO;


public interface UsersService {
	public List<VendorVO> getVendorWithSkills(Integer vendorId);
	
    public List<Client> getClients(Integer clientId);
    
    public void addClient(Client client);
    
    public Client loginUser(String email, String password);
}
