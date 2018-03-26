package org.iron.ultimate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.iron.ultimate.model.mapper.ResourceMapper;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

	@Bean
	@ConfigurationProperties(prefix = "ultimate-iron.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
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
