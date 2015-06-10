package com.globallogic.dogo.persistance;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.persistance.exception.ObjectNotFoundException;

@Repository
@Transactional
public class ProjectPersistance {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public ProjectPersistance(SessionFactory theSessionFactory) {
		this.sessionFactory = theSessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> list(){
		Session session = getSession();
		List<Project> result = session.createCriteria(Project.class)
						   			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						   			.list();
		session.close();
		return result;
	}

	public Project findById(Long id) throws ObjectNotFoundException {
		Session session = getSession();
		Project result =  (Project)session.createCriteria(Project.class)
								.add(Restrictions.eq("id", id))
								.uniqueResult();
		if (result == null){
			throw new ObjectNotFoundException();
		}
		session.close();
		return result;
	}
	
	private Session getSession() {
		return sessionFactory.openSession();
	}

	public void save(Project project) {
		Session session = getSession();
		session.save(project);
		session.flush();
		session.close();
	}

}