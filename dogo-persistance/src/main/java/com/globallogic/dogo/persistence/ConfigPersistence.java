package com.globallogic.dogo.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.globallogic.dogo.domain.config.JenkinsConfig;
import com.globallogic.dogo.persistence.exception.ObjectNotFoundException;

@Repository
@Transactional
public class ConfigPersistence extends Persistence<JenkinsConfig>{
	
	@Autowired
	public ConfigPersistence(SessionFactory theSessionFactory) {
		super(theSessionFactory);
	}

	public JenkinsConfig listJenkinsConfig() throws ObjectNotFoundException {
		List<JenkinsConfig> result = list(JenkinsConfig.class);
		if(CollectionUtils.isEmpty(result)){
			throw new ObjectNotFoundException();
		}
		return result.get(0);
	}
}
