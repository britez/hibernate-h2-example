package com.globallogic.dogo.converters;

import org.springframework.stereotype.Component;

import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.representation.ConfigRepresentation;
import com.globallogic.dogo.representation.JenkinsConfigRepresentation;
import com.globallogic.dogo.representation.JenkinsCredentialsRepresentation;

@Component
public class ConfigConverter {

	public ConfigRepresentation convert() {
		ConfigRepresentation result = new ConfigRepresentation();
		result.setJenkins("http://localhost:8888/dogo-rest/configs/jenkins");
		return result;
	}

	public JenkinsConfigRepresentation convert(JenkinsConfig jenkinsConfig) {
		JenkinsConfigRepresentation result = new JenkinsConfigRepresentation();
		result.setCredentials(convertCredentials(jenkinsConfig));
		result.setHost(jenkinsConfig.getHost());
		return result;
	}

	private JenkinsCredentialsRepresentation convertCredentials(JenkinsConfig jenkinsConfig) {
		JenkinsCredentialsRepresentation credentials = new JenkinsCredentialsRepresentation();
		credentials.setToken(jenkinsConfig.getToken());
		credentials.setUsername(jenkinsConfig.getUsername());
		return credentials;
	}

	public JenkinsConfig convert(JenkinsConfigRepresentation jenkinsConfig) {
		JenkinsConfig result = new JenkinsConfig();
		result.setHost(jenkinsConfig.getHost());
		JenkinsCredentialsRepresentation credentials = jenkinsConfig.getCredentials();
		result.setToken(credentials.getToken());
		result.setUsername(credentials.getUsername());
		return result;
	}
}