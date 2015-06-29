package com.globallogic.dogo.representation;

public class JenkinsCredentialsRepresentationMO {

	private static final String USER = "thisIsAUser";
	private static final String TOKEN = "thisIsAToken";

	public static JenkinsCredentialsRepresentation build() {
		JenkinsCredentialsRepresentation result = new JenkinsCredentialsRepresentation();
		result.setToken(TOKEN);
		result.setUsername(USER);
		return result;
	}

}
