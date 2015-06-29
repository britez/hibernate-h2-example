package com.globallogic.dogo.mo;

import com.globallogic.dogo.domain.config.JenkinsConfig;

public class JenkinsConfigMO {
	
	private static final String TOKEN = "thisIsAToken";
	private static final String USERNAME = "thisIsAUsername";
	private static final String HOST = "thisIsAHost";

	public static JenkinsConfig build(){
		JenkinsConfig result = new JenkinsConfig();
		result.setHost(HOST);
		result.setToken(TOKEN);
		result.setUsername(USERNAME);
		return result;
	}
}