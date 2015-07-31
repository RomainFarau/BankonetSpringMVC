package com.bankonet.report;

import org.springframework.stereotype.Component;

@Component("htmlReport")
public class IReportGeneratorImplHtml extends ReportGenerator implements IReportGenerator{

	public void generate() {
		System.out.println("Generation rapport html");
		
	}

	public void generate(String auteur) {
		this.generate();
		System.out.println("->Auteur : "+auteur);
	}

}
