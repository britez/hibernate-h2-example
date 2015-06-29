package com.globallogic.dogo.representation;


public class JenkinsConfigRepresentation {
	
	private JenkinsCredentialsRepresentation credentials;
	private String host;

	public JenkinsCredentialsRepresentation getCredentials() {
		return credentials;
	}

	public void setCredentials(JenkinsCredentialsRepresentation credentials) {
		this.credentials = credentials;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
