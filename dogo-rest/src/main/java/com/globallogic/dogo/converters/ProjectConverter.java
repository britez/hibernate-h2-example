package com.globallogic.dogo.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.representation.ProjectRepresentation;

@Component
public class ProjectConverter {
	
	public ProjectRepresentation convert(Project project){
		ProjectRepresentation result = new ProjectRepresentation();
		result.setId(project.getId());
		result.setDescription(project.getDescription());
		result.setName(project.getName());
		result.setStatics(String.format("http://localhost:8888/dogo-rest/project/%s",project.getId()));
		return result;
	}

	public List<ProjectRepresentation> convert(List<Project> list) {
		List<ProjectRepresentation> result = new ArrayList<ProjectRepresentation>();
		for (Project project : list) {
			result.add(convert(project));
		}
		return result;
	}

	public Project convert(ProjectRepresentation project) {
		Project result = new Project();
		result.setName(project.getName());
		result.setDescription(project.getDescription());
		return result;
	}
}