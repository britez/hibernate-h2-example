package com.globallogic.dogo.services;


import org.junit.Before;
import org.mockito.Mockito;

import com.globallogic.dogo.persistance.ProjectPersistance;
import com.globallogic.dogo.services.ProjectService;

public class ProjectServiceTest {
	
	private ProjectService service;
	private ProjectPersistance mockedPersistance;
	
	@Before
	public void setUp(){
		mockedPersistance = Mockito.mock(ProjectPersistance.class);
		service = new ProjectService(mockedPersistance);
	}
}
