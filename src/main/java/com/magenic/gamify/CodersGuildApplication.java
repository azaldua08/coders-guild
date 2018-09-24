package com.magenic.gamify;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
public class CodersGuildApplication {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.magenic.gamify.model" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		// transactionManager.setRollbackOnCommitFailure(true);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
				setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
				setProperty("hibernate.show_sql", "true");

				// setProperty("javax.persistence.validation.group.pre-persist",
				// "javax.validation.group.Default");
			}
		};
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

//	@Bean
//	public BeanValidationPostProcessor BeanValidationPostProcessor(final AutowireCapableBeanFactory autowireCapableBeanFactory) {
//		final BeanValidationPostProcessor beanValidationPostProcessor = new BeanValidationPostProcessor();
//		beanValidationPostProcessor.setValidator(validator(autowireCapableBeanFactory));
//		return beanValidationPostProcessor;
//	}
//
//	@Bean
//	public Validator validator(final AutowireCapableBeanFactory autowireCapableBeanFactory) {
//
//		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
//				.constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
//				.buildValidatorFactory();
//		Validator validator = validatorFactory.getValidator();
//
//		return validator;
//	}


	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(final AutowireCapableBeanFactory autowireCapableBeanFactory) {
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidator(validator());
		return methodValidationPostProcessor;
	}

	public static void main(String[] args) {
		SpringApplication.run(CodersGuildApplication.class, args);
	}
}
