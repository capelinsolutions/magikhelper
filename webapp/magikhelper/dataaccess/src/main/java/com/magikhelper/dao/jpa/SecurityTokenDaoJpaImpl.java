/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.SecurityTokenDao;
import com.magikhelper.entities.SecurityToken;

@Repository(SecurityTokenDao.name)
public class SecurityTokenDaoJpaImpl extends GenericDaoJpaImpl<SecurityToken, Integer> implements SecurityTokenDao {

    private static final Log log = LogFactory.getLog(SecurityTokenDaoJpaImpl.class);

    public SecurityTokenDaoJpaImpl() {
        super(SecurityToken.class);
    }

	
}
