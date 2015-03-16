package com.magikhelper.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.magikhelper.dao.management.MHSessionManagement;
import com.magikhelper.dao.pojo.DataObject;

public abstract class AbstractFacade<T> {
	@PersistenceContext(unitName = "InventlesServicesPU")
	private EntityManager em;
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected SessionFactory getSessionFactory() {
		return MHSessionManagement.INSTANCE.getSessionFactory();
	}

	
	 public void upsert(T entity) { 
		 Session sess = getSessionFactory().openSession();
		
		 Transaction tx = null;
		 try {
		     tx = sess.beginTransaction();
		     sess.saveOrUpdate(entity);
		     tx.commit();
		 }catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
		     sess.close();
		 }
		
		 
	 }
	 
	/* public void edit(T entity) { }
	 public void create(T entity) { }
	 */
	/* public void remove(T entity) {
	 * getEntityManager().remove(getEntityManager().merge(entity)); }
	 */

	public T find(Serializable id) {
		Session session = getSessionFactory().openSession();
		return (T) session.get(entityClass, id);
	}

	public List<T> findAll() {
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(entityClass);
		return criteria.list();
	}
	
}
