package com.globallogic.dogo.persistence;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.mo.ProjectMO;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;

public class ProjectPersistenceTest {
	
	private ProjectPersistence persistence;
	private SessionFactory mockedSessionFactory;
	private Session mockedSession;
	
	@Before
	public void setUp(){
		this.mockedSessionFactory = Mockito.mock(SessionFactory.class);
		this.mockedSession = Mockito.mock(Session.class);
		Mockito.when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		this.persistence = new ProjectPersistence(mockedSessionFactory);
	}
	
	@Test
	public void testList(){
		List<Project> mockedResult = new ArrayList<Project>();
		Criteria mockedCriteria = Mockito.mock(Criteria.class);
		Mockito.when(mockedSession.createCriteria(Project.class)).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.list()).thenReturn(mockedResult);
		List<Project> result = this.persistence.list();
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedResult, result);
		Mockito.verify(mockedSession,Mockito.times(1)).createCriteria(Project.class);
		Mockito.verify(mockedCriteria,Mockito.times(1)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Mockito.verify(mockedCriteria,Mockito.times(1)).list();
		Mockito.verify(mockedSession,Mockito.times(1)).close();
	}
	
	@Test
	public void testGet() throws ObjectNotFoundException{
		Project mockedResult = ProjectMO.build();
		Criteria mockedCriteria = Mockito.mock(Criteria.class);
		Mockito.when(mockedSession.createCriteria(Project.class)).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.add(Mockito.any(Criterion.class))).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.uniqueResult()).thenReturn(mockedResult);
		Project result = this.persistence.findById(ProjectMO.ID);
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedResult, result);
		Mockito.verify(mockedSession,Mockito.times(1)).createCriteria(Project.class);
		Mockito.verify(mockedCriteria,Mockito.times(1)).add(Mockito.any(Criterion.class));
		Mockito.verify(mockedCriteria,Mockito.times(1)).uniqueResult();
		Mockito.verify(mockedSession,Mockito.times(1)).close();
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testGetNull() throws ObjectNotFoundException{
		Criteria mockedCriteria = Mockito.mock(Criteria.class);
		Mockito.when(mockedSession.createCriteria(Project.class)).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.add(Mockito.any(Criterion.class))).thenReturn(mockedCriteria);
		Mockito.when(mockedCriteria.uniqueResult()).thenReturn(null);
		Project result = this.persistence.findById(ProjectMO.ID);
		Assert.assertNull(result);
		Mockito.verify(mockedSession,Mockito.times(1)).createCriteria(Project.class);
		Mockito.verify(mockedCriteria,Mockito.times(1)).add(Mockito.any(Criterion.class));
		Mockito.verify(mockedCriteria,Mockito.times(1)).uniqueResult();
		Mockito.verify(mockedSession,Mockito.times(1)).close();
	}
	
	@Test
	public void testSave(){
		Project mockedProject = ProjectMO.build();
		this.persistence.create(mockedProject);
		Mockito.verify(mockedSession, Mockito.times(1)).save(mockedProject);
		Mockito.verify(mockedSession, Mockito.times(1)).flush();
		Mockito.verify(mockedSession, Mockito.times(1)).close();
	}

}
