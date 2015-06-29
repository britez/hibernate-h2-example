package com.globallogic.dogo.persistence;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.mo.JenkinsConfigMO;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;

public class ConfigPersistenceTest {
	
	private ConfigPersistence persistence;
	private SessionFactory mockedSessionFactory;
	private Session mockedSession;
	
	@Before
	public void setUp(){
		this.mockedSessionFactory = Mockito.mock(SessionFactory.class);
		this.mockedSession = Mockito.mock(Session.class);
		Mockito.when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		this.persistence = new ConfigPersistence(mockedSessionFactory);
	}
	
	@Test
	public void testListJenkinsConfig() throws ObjectNotFoundException{
		List<JenkinsConfig> mockedResult = new ArrayList<JenkinsConfig>();
		JenkinsConfig mockedConfig = JenkinsConfigMO.build();
		mockedResult.add(mockedConfig);
		Criteria mockedCriteria = Mockito.mock(Criteria.class);
		Mockito.when(mockedSession.createCriteria(JenkinsConfig.class)).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.list()).thenReturn(mockedResult);
		JenkinsConfig result = this.persistence.listJenkinsConfig();
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedConfig, result);
		Mockito.verify(mockedSession,Mockito.times(1)).createCriteria(JenkinsConfig.class);
		Mockito.verify(mockedCriteria,Mockito.times(1)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Mockito.verify(mockedCriteria,Mockito.times(1)).list();
		Mockito.verify(mockedSession,Mockito.times(1)).close();
	}
}