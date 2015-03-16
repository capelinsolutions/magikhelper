/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import com.magikhelper.dao.ApplicationPropertiesDao;
import com.magikhelper.entities.ApplicationProperty;
import com.magikhelper.entities.enums.ApplicationPropertyType;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository(ApplicationPropertiesDao.name)
public class ApplicationPropertiesDaoJpaImpl extends GenericDaoJpaImpl<ApplicationProperty, Integer> implements ApplicationPropertiesDao {

    private static final Log log = LogFactory.getLog(ApplicationPropertiesDaoJpaImpl.class);

    public ApplicationPropertiesDaoJpaImpl() {
        super(ApplicationProperty.class);
    }

    @Override
    public List<ApplicationProperty> getPropertiesByType(ApplicationPropertyType type) {
        log.info("Load aplication properties of type " + type);
        Query query = entityManager.createQuery("from ApplicationProperty ap where ap.type=:type order by sortOrder asc");
        query.setParameter("type", type);
        log.info("application properties loading done");
        return query.getResultList();
    }

    @Override
    public ApplicationProperty getPropertiesByTypeAndKey(ApplicationPropertyType type, String key) {
        log.info("get aplication property by type and key (" + type + ": " + key + ")");
        Query query = entityManager.createQuery("from ApplicationProperty ap where ap.type=:type and ap.name=:key ");
        query.setParameter("type", type);
        query.setParameter("key", key);
        log.info("application properties loading done");
        return (ApplicationProperty) query.getSingleResult();
    }

    @Override
    public List<ApplicationProperty> getSortedProperties() {
        log.info("Get all aplication properties sorted in ascending manner");
        Query query = entityManager.createQuery("from ApplicationProperty ap order by ap.type, ap.sortOrder");
        return query.getResultList();
    }

    @Override
	public List<ApplicationProperty> getServicesWithRates() {
		Query query = entityManager.createNamedQuery("serviceWithRates");
		query.setParameter("type", ApplicationPropertyType.SERVICES);
		return query.getResultList();
	}
}
