package com.globallogic.dogo.handlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.dogo.representation.VersionRepresentation;

@RestController
@RequestMapping(value = VersionHandler.PATH)
public class VersionHandler {
	
	protected static final String PATH = "";

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public VersionRepresentation get(){
		VersionRepresentation result = new VersionRepresentation();
		result.setV("1.0");
		return result;
	}
	
}