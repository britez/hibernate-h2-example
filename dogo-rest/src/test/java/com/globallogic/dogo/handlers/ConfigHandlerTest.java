package com.globallogic.dogo.handlers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.globallogic.dogo.converters.ConfigConverter;
import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.mo.JenkinsConfigMO;
import com.globallogic.dogo.representation.ConfigRepresentation;
import com.globallogic.dogo.representation.ConfigRepresentationMO;
import com.globallogic.dogo.representation.JenkinsConfigRepresentation;
import com.globallogic.dogo.representation.JenkinsConfigRepresentationMO;
import com.globallogic.dogo.services.ConfigService;

public class ConfigHandlerTest {
	
	private ConfigHandler handler;
	private ConfigService mockedService;
	private ConfigConverter mockedConverter;
	
	 @Before
	 public void setUp(){
		 this.mockedService = Mockito.mock(ConfigService.class);
		 this.mockedConverter = Mockito.mock(ConfigConverter.class);
		 this.handler = new ConfigHandler(mockedService, mockedConverter);
	 }
	 
	 @Test
	 public void testListConfig(){
		 ConfigRepresentation mockedResult = ConfigRepresentationMO.build();
		 Mockito.when(mockedConverter.convert()).thenReturn(mockedResult);
		 ConfigRepresentation result = this.handler.list();
		 Assert.assertNotNull(result);
		 Assert.assertEquals(mockedResult, result);
	 }
	 
	 @Test
	 public void testGetJenkinsConfig(){
		 JenkinsConfig mockedConfig = JenkinsConfigMO.build();
		 JenkinsConfigRepresentation mockedResult = JenkinsConfigRepresentationMO.build();
		 Mockito.when(mockedService.getJenkinsConfig()).thenReturn(mockedConfig);
		 Mockito.when(mockedConverter.convert(mockedConfig)).thenReturn(mockedResult);
		 JenkinsConfigRepresentation result = this.handler.get();
		 Assert.assertNotNull(result);
		 Assert.assertEquals(mockedResult, result);
	 }
	 
	 @Test
	 public void testCreateJenkinsConfig(){
		 JenkinsConfigRepresentation mockedJenkinsCR = JenkinsConfigRepresentationMO.build();
		 JenkinsConfig mockedJenkinsConfig = JenkinsConfigMO.build();
		 Mockito.when(mockedConverter.convert(mockedJenkinsCR)).thenReturn(mockedJenkinsConfig);
		 this.handler.create(mockedJenkinsCR);
		 Mockito.verify(mockedConverter,Mockito.times(1)).convert(mockedJenkinsCR);
		 Mockito.verify(mockedService,Mockito.times(1)).create(mockedJenkinsConfig);
	 }
}