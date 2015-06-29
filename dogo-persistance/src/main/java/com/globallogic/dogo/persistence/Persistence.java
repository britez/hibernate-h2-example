package com.globallogic.dogo.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;

public class Persistence<T> {
	
	protected SessionFactory sessionFactory;
	
	protected Persistence(SessionFactory theSessionFactory){
		this.sessionFactory = theSessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void create(T elem) {
		Session session = getSession();
		session.save(elem);
		session.flush();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	protected T get(Long id, Class<T> clazz) throws ObjectNotFoundException{
		Session session = getSession();
		T result = (T)session.createCriteria(clazz).add(Restrictions.eq("id", id)).uniqueResult();
		if (result == null){
			throw new ObjectNotFoundException();
		}
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> list(Class<T> clazz){
		Session session = getSession();
		List<T> result = session.createCriteria(clazz).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		session.close();
		return result;
	}

}
