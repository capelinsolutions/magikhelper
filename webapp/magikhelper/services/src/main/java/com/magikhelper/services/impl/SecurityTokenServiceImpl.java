package com.magikhelper.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.magikhelper.dao.SecurityTokenDao;
import com.magikhelper.entities.SecurityToken;
import com.magikhelper.services.SecurityTokenService;

@Service(value = "securityTokenService")
public class SecurityTokenServiceImpl implements SecurityTokenService {

    private static final Log log = LogFactory.getLog(SecurityTokenServiceImpl.class);
    
    @Autowired
    SecurityTokenDao securityTokenDao;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void saveToken(SecurityToken token) {
		securityTokenDao.add(token);
	}
    
}
