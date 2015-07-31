package com.bankonet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;//Evite Assert.asserEquals...

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;


public class BankonetAppConfigTest {

	private AnnotationConfigApplicationContext context;
	
	@Before
	public void setUp(){
		try{
			context=new AnnotationConfigApplicationContext(BankonetAppConfig.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		System.out.println("Debut Test");
		DriverManagerDataSource dataSource = context.getBean(DriverManagerDataSource.class);
		assertEquals("bankospring", dataSource.getUsername());
		assertEquals("jdbc:mysql://localhost:8889/bankospring", dataSource.getUrl());
		assertEquals("bankospring", dataSource.getPassword());
		System.out.println("Fin Test");
	}
	
	@Test
	public void testEntityManagerFactory(){
		try{
			EntityManagerFactory emf=context.getBean(EntityManagerFactory.class);
			assertNotNull(emf);
			EntityManager em=emf.createEntityManager();
			assertNotNull(em);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPlarformTransactionManager(){
		try{
			PlatformTransactionManager platformTransactionManager=context.getBean(PlatformTransactionManager.class);
			assertNotNull(platformTransactionManager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
