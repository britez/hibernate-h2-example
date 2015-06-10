package com.globallogic.dogo.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globallogic.dogo.converters.ProjectConverter;
import com.globallogic.dogo.domain.Project;
import com.globallogic.dogo.mo.ProjectMO;
import com.globallogic.dogo.representation.ProjectRepresentation;

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
		Assert.assertNotNull(result);
		Assert.assertEquals(mockedProject.getId(), result.getId());
		Assert.assertEquals(mockedProject.getName(), result.getName());
		Assert.assertEquals(mockedProject.getDescription(), result.getDescription());
		
	}
}