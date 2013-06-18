package com.westchase.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.westchase.persistence.criteria.SearchCriteria;

/**
 * @author marc
 *
 */
public class BaseDAO<E> implements GenericDAO<E> {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private Class<E> persistentClass;
	
//	protected EntityManager em;
//
//	@PersistenceContext
//	public void setEntityManager(EntityManager em) {
//	this.em = em;
//	}
	
	
	public BaseDAO() {
        this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Query prepareQuery(Query q, SearchCriteria criteria) {
		if (criteria.getNumberOfResults() > 0) {
			q.setMaxResults(criteria.getNumberOfResults());
		}
		if (criteria.getPage() > 0) {
			q.setFirstResult(criteria.getPage() * criteria.getNumberOfResults());
		}
		return q;
	}
	
	protected String getClassName() {
		return this.persistentClass.getName();
	}
	
	public Session getSession() {
		Session session = null;
		try {
			InitialContext ctx = new InitialContext();
			// jboss-4.2.3.GA
			// SessionFactory sessionFactory = (SessionFactory) ctx.lookup("persistence.units:ear=atoms.ear,jar=atjpa.jar,unitName=MySqlDS");
			// jboss-4.2.2.GA
			SessionFactory sessionFactory = (SessionFactory) ctx.lookup("WestchaseFactory");
			if (sessionFactory != null) {
				session = sessionFactory.getCurrentSession();
				if (session == null) {
					session = sessionFactory.openSession();
				}
			}
		} catch (Exception e) {log.info("Unable to get SessionFactory");}
		return session;
	}
	
//	public Session getSession() {
//		return ((HibernateEntityManager) this.em).getSession();
//	}

	public long save(E transientInstance) {
//		return persist(transientInstance);
		long ret = 0;
		Serializable id = getSession().save(transientInstance);
		if (id != null) {
			if (id instanceof Long) {
				ret = ((Long) id).longValue();
			} else if (id instanceof Integer) {
				ret = ((Integer) id).longValue();
			}
		}
		return ret;
	}

	public Serializable persist(E transientInstance) {
		Serializable id = null;
		log.debug("persisting " + getClassName() + " instance");
		try {
//			entityManager.persist(transientInstance);
			id = getSession().save(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
		return id;
	}

	public void update(E persistentInstance) {
		getSession().update(persistentInstance);
	}

	public void saveOrUpdate(E instance) {
		getSession().saveOrUpdate(instance);
	}

	public void delete(Serializable id) {
		remove(get(id));
	}

	public void delete(E persistentInstance) {
		remove(persistentInstance);
	}

	public void remove(E persistentInstance) {
		log.debug("removing " + getClassName() + " instance");
		try {
//			entityManager.remove(persistentInstance);
			getSession().delete(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public E merge(E detachedInstance) {
		log.debug("merging " + getClassName() + " instance");
		try {
//			ActionItems result = entityManager.merge(detachedInstance);
			E result = (E) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<E> findAll() {
		return getSession().createCriteria(this.persistentClass).list();
//		return getSession().createQuery("select person from Person person").list();
	}

	public List<E> findAll(Order order) {
		return getSession().createCriteria(this.persistentClass).addOrder(order).list();
	}

	public E get(Serializable id) {
		return findById(id);
	}

	public E findById(Serializable id) {
		log.debug("getting " + getClassName() + " instance with id: " + id);
		try {
//			ActionItems instance = entityManager.find(ActionItems.class, id);
			E instance = (E) getSession().get(this.persistentClass, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	protected boolean hasListValues(List<Integer> listValues) {
		boolean hasListValues = false;
		if (listValues != null && !listValues.isEmpty()) {
			for (Integer listValue : listValues) {
				if (listValue != null && listValue.intValue() > -1) {
					hasListValues = true;
					break;
				}
			}
		}
		return hasListValues;
	}
}
