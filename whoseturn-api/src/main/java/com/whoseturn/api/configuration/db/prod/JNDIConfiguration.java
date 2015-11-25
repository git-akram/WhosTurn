package com.whoseturn.api.configuration.db.prod;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.whoseturn.api.configuration.db.DatabaseConfiguration;

@Configuration
@Profile("prod")
public class JNDIConfiguration extends DatabaseConfiguration {

	Logger logger = LoggerFactory.getLogger(JNDIConfiguration.class);

	//@todo test using application server JNDI (ie tomcat context.xml).
    @Value("#{ environment['jndi.datasource'] }")
    private String JAVA_JNDI_DATASOURCE;
	
	@Bean
	@Override
	public DataSource dataSource() {

		DataSource dataSource = null;
		logger.debug("Starting JNDI Data Source Lookup");
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName(JAVA_JNDI_DATASOURCE);
		jndiObjectFactoryBean.setLookupOnStartup(true);
		jndiObjectFactoryBean.setCache(true);
		Object o = jndiObjectFactoryBean.getObject();
		logger.debug("Object Factory got this...", o);
		if (o != null) {
			dataSource = (DataSource) o;
		} else {
			logger.error("\n\n\nData Source not found for '{}'", JAVA_JNDI_DATASOURCE);
		}

		logger.debug("Returning JNDI Data Source '{}'", dataSource);

		return dataSource;	
	}

}
