package com.magikhelper.dao.management;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public enum MHSessionManagement {
	INSTANCE;
	private SessionFactory sessionFactory;
	
	private MHSessionManagement(){
		 Configuration configuration = new Configuration();
	     configuration.configure("/resources/hibernate.cfg.xml");
	     ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
	     sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
	}
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
