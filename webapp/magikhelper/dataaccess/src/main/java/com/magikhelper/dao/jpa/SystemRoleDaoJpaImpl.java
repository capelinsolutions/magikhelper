/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.SystemRoleDao;
import com.magikhelper.entities.SystemRole;

@Repository(SystemRoleDao.name)
public class SystemRoleDaoJpaImpl extends GenericDaoJpaImpl<SystemRole, Integer> implements SystemRoleDao {

    private static final Log log = LogFactory.getLog(SystemRoleDaoJpaImpl.class);

    public SystemRoleDaoJpaImpl() {
        super(SystemRole.class);
    }

}
