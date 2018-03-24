package org.iron.ultimate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.iron.ultimate.model.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Value("${ultimate-iron.datasource.driverClassName}")
	private String databaseDriverClassName;
	
	@Value("${ultimate-iron.datasource.url}")
	private String databaseUrl;
	
	@Value("${ultimate-iron.datasource.username}")
	private String databaseUsername;
	
	@Value("${ultimate-iron.datasource.password}")
	private String databasePassword;
	
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName(databaseDriverClassName)
				.url(databaseUrl)
				.username(databaseUsername)
				.password(databasePassword)
				.build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"org.iron.ultimate"});
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Properties additionalProperties = new Properties();
		additionalProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
		return em;
	}
	
	@Bean
	public MapperFacade mapperFacade() {
		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		return factory.getMapperFacade();
	}
	
	@Bean
	public ResourceMapper resourceMapper() {
		return new ResourceMapper();
	}
	
}
