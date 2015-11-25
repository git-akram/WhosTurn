package com.whoseturn.api.configuration.db.dev;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.whoseturn.api.configuration.db.DatabaseConfiguration;

@Configuration
@Profile("dev")
@PropertySource({ "classpath:h2db.database.properties" })
public class H2DBConfiguration extends DatabaseConfiguration {
	
	@SuppressWarnings("unchecked")
	@Bean
	public DataSource dataSource() throws ClassNotFoundException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass((Class<? extends Driver>) Class
				.forName(environment.getProperty("dataSource.driverClass")));
		dataSource.setUrl(environment.getProperty("dataSource.url"));
		dataSource.setUsername(environment.getProperty("dataSource.username"));
		dataSource.setPassword(environment.getProperty("dataSource.password"));
		return dataSource;
	}
}
