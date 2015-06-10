package com.globallogic.dogo.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DogoInitializer implements WebApplicationInitializer{

	private static final String SERVLET_MAPPING = "/*";
	private static final String SERVLET_NAME = "dispatcher";

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		ServletRegistration.Dynamic dynamic = container.addServlet(SERVLET_NAME, new DispatcherServlet(getDispatcherServletConfigContext()));
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping(SERVLET_MAPPING);
	}

	private AnnotationConfigWebApplicationContext getDispatcherServletConfigContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(DogoMVCInitializer.class);
		return context;
	}
	

}
