/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.RolesDao;
import com.magikhelper.entities.SystemRole;

@Repository(RolesDao.name)
public class RolesDaoJpaImpl extends GenericDaoJpaImpl<SystemRole, Integer> implements RolesDao {

    private static final Log log = LogFactory.getLog(RolesDaoJpaImpl.class);

    public RolesDaoJpaImpl() {
        super(SystemRole.class);
    }

	
}
