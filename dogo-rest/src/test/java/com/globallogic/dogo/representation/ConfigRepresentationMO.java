package com.globallogic.dogo.representation;

public class ConfigRepresentationMO {
	
	private static final String JENKINS_URL = "thisIsAURL";
	
	public static ConfigRepresentation build(){
		ConfigRepresentation result = new ConfigRepresentation();
		result.setJenkins(JENKINS_URL);
		return result;
	}

}