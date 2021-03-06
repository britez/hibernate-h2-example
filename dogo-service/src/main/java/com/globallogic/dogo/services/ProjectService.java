package com.globallogic.dogo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.persistence.ProjectPersistence;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;
import com.globallogic.dogo.services.exception.ResourceNotFoundException;

@Service
public class ProjectService {
	
	private ProjectPersistence persistance;
	
	@Autowired
	public ProjectService(ProjectPersistence projectPersistance) {
		this.persistance = projectPersistance;
	}
	
	public List<Project> list(){
		return persistance.list();
	}
	
	public Project get(Long id){
		Project result = null;
		try {
			result = persistance.findById(id);
		} catch (ObjectNotFoundException e) {
			throw new ResourceNotFoundException(String.format("El Projecto con id: %s, no existe",id));
		}
		return result;
	}

	public void create(Project project) {
		persistance.create(project);
	}

}