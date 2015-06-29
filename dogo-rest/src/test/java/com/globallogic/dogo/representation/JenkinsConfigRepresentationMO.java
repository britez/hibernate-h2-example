package com.globallogic.dogo.representation;

public class JenkinsConfigRepresentationMO {

	public static JenkinsConfigRepresentation build() {
		JenkinsConfigRepresentation result = new JenkinsConfigRepresentation();
		result.setCredentials(JenkinsCredentialsRepresentationMO.build());
		return result;
	}

}
