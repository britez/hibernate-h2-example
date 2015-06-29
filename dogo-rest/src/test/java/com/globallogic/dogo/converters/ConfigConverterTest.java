package com.globallogic.dogo.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.mo.JenkinsConfigMO;
import com.globallogic.dogo.representation.ConfigRepresentation;
import com.globallogic.dogo.representation.JenkinsConfigRepresentation;
import com.globallogic.dogo.representation.JenkinsConfigRepresentationMO;
import com.globallogic.dogo.representation.JenkinsCredentialsRepresentation;

public class ConfigConverterTest {
	
	private ConfigConverter converter;
	
	@Before
	public void setUp(){
		this.converter = new ConfigConverter();
	}
	
	@Test
	public void testConvert(){
		ConfigRepresentation result = this.converter.convert();
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getJenkins());
	}
	
	@Test
	public void testConverJenkinsConfig(){
		JenkinsConfig mockedJenkinsConfig = JenkinsConfigMO.build();
		JenkinsConfigRepresentation result = this.converter.convert(mockedJenkinsConfig );
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testConvertJCRtoJC(){
		JenkinsConfigRepresentation mockedRepresentation = JenkinsConfigRepresentationMO.build();
		JenkinsConfig result = this.converter.convert(mockedRepresentation);
		Assert.assertNotNull(result);
		checkCredentials(result, mockedRepresentation.getCredentials());
	}

	private void checkCredentials(JenkinsConfig current, JenkinsCredentialsRepresentation expected) {
		Assert.assertNotNull(current);
		Assert.assertEquals(expected.getToken(), current.getToken());
		Assert.assertEquals(expected.getUsername(), current.getUsername());
	}
}