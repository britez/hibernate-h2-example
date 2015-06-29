package com.globallogic.dogo.converters;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.mo.ProjectMO;
import com.globallogic.dogo.representation.ProjectRepresentation;
import com.globallogic.dogo.representation.ProjectRepresentationMO;

public class ProjectConverterTest {
	
	private ProjectConverter converter;
	
	@Before
	public void setUp(){
		this.converter = new ProjectConverter();
	}

	@Test
	public void testConvertProject(){
		Project mockedProject = ProjectMO.build();
		ProjectRepresentation result = this.converter.convert(mockedProject);
		checkProject(mockedProject, result);
		
	}
	
	@Test
	public void testConvertProjectList(){
		Project mockedProject = ProjectMO.build();
		List<Project> mockedList = new ArrayList<Project>();
		mockedList.add(mockedProject);
		List<ProjectRepresentation> result = this.converter.convert(mockedList);
		Assert.assertNotNull(result);
		Assert.assertEquals(1L, result.size());
		checkProject(mockedProject, result.get(0));
	}
	
	@Test
	public void testConvertProjectRepresentation(){
		ProjectRepresentation mockedRepresentation = ProjectRepresentationMO.build();
		Project result = this.converter.convert(mockedRepresentation);
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedRepresentation.getDescription(), result.getDescription());
		Assert.assertEquals(mockedRepresentation.getName(), result.getName());
		Assert.assertNull(result.getId());
	}
	
	private void checkProject(Project mockedProject, ProjectRepresentation result) {
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedProject.getId(), result.getId());
		Assert.assertEquals(mockedProject.getName(), result.getName());
		Assert.assertEquals(mockedProject.getDescription(), result.getDescription());
		Assert.assertNotNull(result.getStatics());
	}
}