package com.globallogic.dogo.services;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.mo.JenkinsConfigMO;
import com.globallogic.dogo.persistence.ConfigPersistence;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;

public class ConfigServiceTest {
	
	private ConfigService service;
	private ConfigPersistence mockedPersistance;
	
	@Before
	public void setUp(){
		mockedPersistance = Mockito.mock(ConfigPersistence.class);
		service = new ConfigService(mockedPersistance);
	}
	
	@Test
	public void testGetJenkinsConfig() throws ObjectNotFoundException{
		JenkinsConfig mockedResult = JenkinsConfigMO.build();
		Mockito.when(mockedPersistance.listJenkinsConfig()).thenReturn(mockedResult);
		JenkinsConfig result = this.service.getJenkinsConfig();
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedResult, result);
		Mockito.verify(mockedPersistance,Mockito.times(1)).listJenkinsConfig();
	}
	
	@Test
	public void testCreate(){
		JenkinsConfig mockedConfig = JenkinsConfigMO.build();
		service.create(mockedConfig);
		Mockito.verify(mockedPersistance,Mockito.times(1)).create(mockedConfig);
	}
}
