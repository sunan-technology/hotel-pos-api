package com.sunan;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class DatabaseConfiguration {

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriver;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${spring.jpa.show-sql}")
	private String hibernateShowSql;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String hibernateHbmToDdl;

	@Value("${entitymanager.packagesToScan}")
	private String packagesToScan;

	@Value("${spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults}")
	private boolean metadataDefaults;

	/*
	 * @Bean(name = "SessionFactory") public LocalSessionFactoryBean
	 * sessionFactory() { LocalSessionFactoryBean sessionFactory = new
	 * LocalSessionFactoryBean(); sessionFactory.setDataSource(dataSource());
	 * sessionFactory.setPackagesToScan(packagesToScan); Properties
	 * hibernateProperties = new Properties();
	 * hibernateProperties.put("hibernate.dialect", hibernateDialect);
	 * hibernateProperties.put("hibernate.show_sql", hibernateShowSql); //
	 * hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbmToDdl);
	 * hibernateProperties.put("hibernate.temp.use_jdbc_metadata_defaults",
	 * metadataDefaults);
	 * hibernateProperties.put("hibernate.id.new_generator_mappings", false);
	 * sessionFactory.setHibernateProperties(hibernateProperties); return
	 * sessionFactory; }
	 */

//	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		vendorAdapter.setDatabasePlatform(hibernateDialect);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.vs");
		factory.setDataSource(dataSource());
		 
		return factory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	/*
	 * @Bean(name = "HibernateTransaction") public HibernateTransactionManager
	 * transactionManager() { HibernateTransactionManager txManager = new
	 * HibernateTransactionManager();
	 * txManager.setSessionFactory(sessionFactory().getObject()); return txManager;
	 * }
	 */

//	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
