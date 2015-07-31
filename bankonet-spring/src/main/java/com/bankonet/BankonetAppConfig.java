package com.bankonet;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.bankonet")
@PropertySource("jdbc.properties")
@EnableTransactionManagement
public class BankonetAppConfig {

	@Value("${jdbc.username}") private String username;
	@Value("${jdbc.url}") private String url;
	@Value("${jdbc.password}") private String password;
	@Value("${jdbc.driverClassName}") private String driverClassName;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setPassword(password);
		driverManagerDataSource.setUsername(username);
		driverManagerDataSource.setDriverClassName(driverClassName);
		return driverManagerDataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
		
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.bankonet.model");
		factory.setDataSource(dataSource());
		
		EclipseLinkJpaDialect jpaDialect=new EclipseLinkJpaDialect();
		factory.setJpaDialect(jpaDialect);
	
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
	
		Map<String,String> jpaPropertyMap=new HashMap<String,String>();
		jpaPropertyMap.put("eclipselink.weaving", "static");
		jpaPropertyMap.put("eclipselink.ddl-generation", "create-tables");
		
		factory.setJpaPropertyMap(jpaPropertyMap);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager getPlatformTransactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}
}
