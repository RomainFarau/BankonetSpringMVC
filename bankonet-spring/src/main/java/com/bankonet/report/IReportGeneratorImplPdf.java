package com.bankonet.report;

import org.springframework.stereotype.Component;

@Component("pdfReport")
public class IReportGeneratorImplPdf extends ReportGenerator implements IReportGenerator{

	public void generate() {
		System.out.println("Génération d'un rapport pdf.");
		
	}

	public void generate(String auteur) {
		this.generate();
		System.out.println("->Auteur : "+auteur);
	}
	
}
