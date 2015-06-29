package com.globallogic.dogo.services;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.mo.ProjectMO;
import com.globallogic.dogo.persistence.ProjectPersistence;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;
import com.globallogic.dogo.services.exception.ResourceNotFoundException;

public class ProjectServiceTest {
	
	private ProjectService service;
	private ProjectPersistence mockedPersistance;
	
	@Before
	public void setUp(){
		mockedPersistance = Mockito.mock(ProjectPersistence.class);
		service = new ProjectService(mockedPersistance);
	}
	
	@Test
	public void testGet() throws ObjectNotFoundException{
		Project mockedProject = ProjectMO.build();
		Mockito.when(mockedPersistance.findById(ProjectMO.ID)).thenReturn(mockedProject);
		Project result = service.get(ProjectMO.ID);
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedProject, result);
		Mockito.verify(mockedPersistance).findById(ProjectMO.ID);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testObjectNotFound() throws ObjectNotFoundException{
		Mockito.when(mockedPersistance.findById(ProjectMO.ID)).thenThrow(new ObjectNotFoundException());
		Project result = service.get(ProjectMO.ID);
		Assert.fail();
		Assert.assertNull(result);
		Mockito.verify(mockedPersistance.findById(ProjectMO.ID),Mockito.times(1));
	}
	
	@Test
	public void testList(){
		List<Project> mockedResult = new ArrayList<Project>();
		Project mockedProject = ProjectMO.build();
		mockedResult.add(mockedProject);
		Mockito.when(mockedPersistance.list()).thenReturn(mockedResult);
		List<Project> result = service.list();
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedProject, result.get(0));
		Mockito.verify(mockedPersistance).list();
	}
	
	@Test
	public void testCreate(){
		Project mockedProject = ProjectMO.build();
		this.service.create(mockedProject);
		Mockito.verify(mockedPersistance, Mockito.times(1)).create(mockedProject);
	}
}