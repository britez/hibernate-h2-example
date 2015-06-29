package com.globallogic.dogo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.persistence.ConfigPersistence;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;
import com.globallogic.dogo.services.exception.ResourceNotFoundException;

@Service
public class ConfigService {
	
	private ConfigPersistence persistence;
	
	@Autowired
	public ConfigService(ConfigPersistence theConfigPersistance) {
		this.persistence = theConfigPersistance;
	}

	public JenkinsConfig getJenkinsConfig() {
		try {
			return persistence.listJenkinsConfig();
		} catch (ObjectNotFoundException e) {
			throw new ResourceNotFoundException("No existen configuraciones hasta el momento");
		}
	}

	public void create(JenkinsConfig config) {
		persistence.create(config);
	}
}