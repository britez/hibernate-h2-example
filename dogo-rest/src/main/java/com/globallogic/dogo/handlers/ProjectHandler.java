package com.globallogic.dogo.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dogo.converters.ProjectConverter;
import com.globallogic.dogo.representation.ProjectRepresentation;
import com.globallogic.dogo.services.ProjectService;

@RestController
@RequestMapping(value = ProjectHandler.PATH)
public class ProjectHandler {
	
	protected static final String PATH = "projects";
	
	private ProjectService service;
	private ProjectConverter converter;
	
	@Autowired
	public ProjectHandler(ProjectService theProjectService,
						  ProjectConverter theProjectConverter) {
		this.service = theProjectService;
		this.converter = theProjectConverter;
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectRepresentation get(@PathVariable Long id){
		return this.converter.convert(this.service.get(id)); 
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ProjectRepresentation> list(){
		return this.converter.convert(this.service.list()); 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody ProjectRepresentation project){
		this.service.create(this.converter.convert(project));
	}
}