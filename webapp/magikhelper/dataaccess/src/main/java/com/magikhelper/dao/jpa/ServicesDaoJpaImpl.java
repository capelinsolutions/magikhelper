/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.ServicesDao;
import com.magikhelper.entities.Services;

@Repository(ServicesDao.name)
public class ServicesDaoJpaImpl extends GenericDaoJpaImpl<Services, Integer> implements ServicesDao {

    private static final Log log = LogFactory.getLog(ServicesDaoJpaImpl.class);

    public ServicesDaoJpaImpl() {
        super(Services.class);
    }

	@Override
	public List<Services> getServiceByZipcode(Integer zipcode) {
		String qryStr = "Select services from Services services where services.zipcode=:zipcode order by services.service.value";
		Query query = entityManager.createQuery(qryStr);
		query.setParameter("zipcode", zipcode);
		return query.getResultList();
	}

	@Override
	public List<Services> getAllServices() {
		String qryStr = "Select services from Services services order by services.zipcode, services.service.value";
		Query query = entityManager.createQuery(qryStr);
		return query.getResultList();
	}
    
}
