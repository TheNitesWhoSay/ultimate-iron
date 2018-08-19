package org.iron.ultimate.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.sql.DataSource;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.iron.ultimate.model.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Value("${ultimate-iron.discord.staff-ranks.webhook.id}")
	private String staffDiscordWebhookId;
	
	@Value("${ultimate-iron.discord.staff-ranks.webhook.token}")
	private String staffDiscordWebhookToken;
	
	@Bean
	public String staffDiscordWebhookId() {
		return staffDiscordWebhookId;
	}
	
	@Bean
	public String staffDiscordWebhookToken() {
		return staffDiscordWebhookToken;
	}
	
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
	
	@Bean
	public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }
	
}
