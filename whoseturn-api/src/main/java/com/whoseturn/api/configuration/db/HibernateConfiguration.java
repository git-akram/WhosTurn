package com.whoseturn.api.configuration.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
/**
 * Hibernate Configuration.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.whoseturn.api.repository")
@PropertySource({ "classpath:hibernate.properties" })
@ComponentScan("com.whoseturn.api")
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	/**
	 * @param dataSource          datasource used by hibernate
	 * @param hibernateProperties hibernate properties
	 * @param jpaVendorAdapter    used to tell which vendor-specific behavior to use to create
	 *                            Spring's EntityManagerFactory creators
	 * @return localContainerEntityManagerFactoryBean used by spring to build
	 * EntityManagerFactory
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryContainer(
			DataSource dataSource, Properties hibernateProperties,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean
				.setPackagesToScan(new String[]{environment
						.getProperty("entities.package")});
		populateHibernateProperties(hibernateProperties);
		localContainerEntityManagerFactoryBean
				.setJpaProperties(hibernateProperties);
		localContainerEntityManagerFactoryBean
				.setJpaVendorAdapter(jpaVendorAdapter);
		return localContainerEntityManagerFactoryBean;
	}

	/**
	 * @return hibernate vendor adapter
	 */
	@Bean
	public JpaVendorAdapter hibernateJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			AbstractEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory
				.getObject());
		return transactionManager;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(AbstractEntityManagerFactoryBean entityManagerFactory) {
		return entityManagerFactory.getObject();
	}

	private void populateHibernateProperties(Properties properties) {
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	}
}