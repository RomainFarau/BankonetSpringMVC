package com.bankonet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// Création du contexte Spring applicatif principal
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(BankonetAppConfig.class);
		// Gestion du cycle de vie du contexte applicatif principal
		servletContext.addListener(new ContextLoaderListener(rootContext));
		// Création d'un contexte pour la servlet Spring
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(BankonetMVCConfig.class);
		// Création de la servlet Spring et mapping
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
		new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}
