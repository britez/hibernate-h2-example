package com.globallogic.dogo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dogo.converters.ConfigConverter;
import com.globallogic.dogo.representation.ConfigRepresentation;
import com.globallogic.dogo.representation.JenkinsConfigRepresentation;
import com.globallogic.dogo.services.ConfigService;

@RestController
@RequestMapping(value = ConfigHandler.PATH)
public class ConfigHandler {
	
	protected static final String PATH = "configs";
	
	private ConfigService service;
	private ConfigConverter converter;
	
	@Autowired
	public ConfigHandler(ConfigService theConfigService,
						 ConfigConverter theConfigConverter) {
		this.service = theConfigService;
		this.converter = theConfigConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ConfigRepresentation list(){
		return this.converter.convert(); 
	}
	
	@RequestMapping(value = "/jenkins", method = RequestMethod.GET)
	@ResponseBody
	public JenkinsConfigRepresentation get(){
		return this.converter.convert(this.service.getJenkinsConfig()); 
	}
	
	@RequestMapping(value = "/jenkins", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody JenkinsConfigRepresentation jenkinsConfig){
		this.service.create(this.converter.convert(jenkinsConfig));
	}
}