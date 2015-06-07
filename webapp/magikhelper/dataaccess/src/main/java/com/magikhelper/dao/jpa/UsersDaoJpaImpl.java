/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.UsersDao;
import com.magikhelper.entities.User;

@Repository(UsersDao.name)
public class UsersDaoJpaImpl extends GenericDaoJpaImpl<User, Integer> implements UsersDao {

    private static final Log log = LogFactory.getLog(UsersDaoJpaImpl.class);

    public UsersDaoJpaImpl() {
        super(User.class);
    }

	@Override
	public List<Object[]> getVendorWithSkills(Integer vendorId) {
		StringBuilder qryStr=new StringBuilder();
		qryStr.append("SELECT ");
		qryStr.append("	    users.row_id, email, first_name, last_name, rates, props.property_id, value");
		qryStr.append("	FROM ");
		qryStr.append("	    system_role roles, ");
		qryStr.append("	    user_role user_roles, ");
		qryStr.append("	    user users, ");
		qryStr.append("	    contact contact, ");
		qryStr.append("	    vendor_skill skills, ");
		qryStr.append("	    application_properties props ");
		qryStr.append("	WHERE ");
		qryStr.append("	    roles.role_id = user_roles.role_id ");
		qryStr.append("	        AND user_roles.user_id = users.row_id ");
		qryStr.append("	        AND roles.title = 'VENDOR_ROLE' ");
		qryStr.append("	        AND users.row_id = skills.vendor_id ");
		qryStr.append("	        AND users.address_id = contact.row_id ");
		qryStr.append("	        AND skills.skill_id = props.property_id ");
		qryStr.append("	        AND props.type = 'SERVICES' ");
		qryStr.append("	        AND roles.is_active = '1' ");
		qryStr.append("	        AND users.is_active = '1' ");
		qryStr.append("	        AND props.is_active = '1' ");
		if (vendorId != null && vendorId.intValue()>0){
			qryStr.append("	    AND user_roles.user_id = :userId ");
		}
		Query query = entityManager.createNativeQuery(qryStr.toString());
		if (vendorId != null && vendorId.intValue()>0){
			query.setParameter("userId", vendorId);
		}
		return query.getResultList();
	}

	@Override
	public List<User> getCleints(Integer clientId) {
		String queryStr = "select DISTINCT user from User user "
				+ "inner join fetch user.userRoles userRoles "
				+ "inner join fetch userRoles.systemRole systemRoles "
				+ "inner join fetch user.contact contact "
				+ "where systemRoles.title='CLIENT_ROLE' ";
		if (clientId!=null && clientId.intValue()>0){
			queryStr +="and user.rowId = :userId ";
		}
		Query query = entityManager.createQuery(queryStr);
		if (clientId!=null && clientId.intValue()>0){
			query.setParameter("userId", clientId);
		}
		return query.getResultList();
	}

	@Override
	public User loginUser(String email, String password) {
		String qryStr = "Select user from User user where user.email=:email and password=:password";
		Query query = entityManager.createQuery(qryStr);
		query.setParameter("email", email);
		query.setParameter("password", password);
		Object obj;
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			log.debug("User not found");
		}
		return null;
	}
	
}
