package com.whoseturn.api.configuration.db;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Database configuration.
 */
@Configuration
public abstract class DatabaseConfiguration {

	@Inject
	protected Environment environment;

	/**
	 * @return basic DataSource
	 * @throws ClassNotFoundException
	 *             if driver not found
	 */
	@Bean
	public abstract DataSource dataSource() throws ClassNotFoundException;

}
