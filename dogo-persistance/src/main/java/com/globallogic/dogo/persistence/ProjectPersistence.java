package com.globallogic.dogo.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;

@Repository
@Transactional
public class ProjectPersistence extends Persistence<Project>{
	
	@Autowired
	public ProjectPersistence(SessionFactory theSessionFactory) {
		super(theSessionFactory);
	}
	
	public List<Project> list(){
		return super.list(Project.class);
	}

	public Project findById(Long id) throws ObjectNotFoundException {
		return get(id, Project.class);
	}
}