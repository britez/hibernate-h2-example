package com.globallogic.dogo.handlers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.globallogic.dogo.converters.ProjectConverter;
import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.mo.ProjectMO;
import com.globallogic.dogo.representation.ProjectRepresentation;
import com.globallogic.dogo.representation.ProjectRepresentationMO;
import com.globallogic.dogo.services.ProjectService;

public class ProjectHandlerTest {
	
	private ProjectHandler handler;
	private ProjectService mockedService;
	private ProjectConverter mockedConverter;
	
	 @Before
	 public void setUp(){
		 this.mockedService = Mockito.mock(ProjectService.class);
		 this.mockedConverter = Mockito.mock(ProjectConverter.class);
		 this.handler = new ProjectHandler(mockedService, mockedConverter);
	 }
	 
	 @Test
	 public void testGetProject(){
		 Project mockedProject = ProjectMO.build();
		 ProjectRepresentation mockedResult = ProjectRepresentationMO.build();
		 Mockito.when(mockedService.get(ProjectMO.ID)).thenReturn(mockedProject);
		 Mockito.when(mockedConverter.convert(mockedProject)).thenReturn(mockedResult);
		 ProjectRepresentation result = this.handler.get(ProjectMO.ID);
		 Assert.assertNotNull(result);
		 Assert.assertEquals(mockedResult, result);
	 }
	 
	 @Test
	 public void testListProjects(){
		 List<Project> mockedList = new ArrayList<Project>();
		 mockedList.add(ProjectMO.build());
		 Mockito.when(mockedService.list()).thenReturn(mockedList);
		 List<ProjectRepresentation> mockedResult = new ArrayList<ProjectRepresentation>();
		 mockedResult.add(ProjectRepresentationMO.build());
		 Mockito.when(mockedConverter.convert(mockedList)).thenReturn(mockedResult);
		 List<ProjectRepresentation> result = this.handler.list();
		 Assert.assertNotNull(result);
		 Assert.assertEquals(mockedResult, result);
	 }
	 
	 @Test
	 public void testCreate(){
		 ProjectRepresentation mockedProject = ProjectRepresentationMO.build();
		 this.handler.create(mockedProject);
	 }

}