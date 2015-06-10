package com.globallogic.dogo.configuration;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.globallogic.dogo"})
public class DogoMVCInitializer extends WebMvcConfigurerAdapter{
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	converters.clear();
    	converters.add(new MappingJackson2HttpMessageConverter());
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/DOGO");
    	dataSource.setUrl("jdbc:h2:~/DOGO/db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }
    
    @Autowired
    @Bean
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "com.globallogic.dogo.domain" });
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "false");
        properties.put("hibernate.hbm2ddl.auto", "create");
        return properties;
	}
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}