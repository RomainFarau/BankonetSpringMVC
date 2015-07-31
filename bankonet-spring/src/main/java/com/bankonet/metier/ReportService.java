package com.bankonet.metier;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bankonet.report.IReportGenerator;


@Service("reportService")
@Scope("prototype")
public class ReportService implements IReportService{

	@Resource(name="htmlReport")
	private IReportGenerator reportGenerator;




	private String auteur;

	public ReportService(){
		
	}

	public ReportService(IReportGenerator reportGenerator){
		
		this.reportGenerator=reportGenerator;
	}

	public IReportGenerator getReportGenerator() {
		return reportGenerator;
	}

	public void setReportGenerator(IReportGenerator reportGenerator) {
		this.reportGenerator = reportGenerator;
	}

	public void generate(){
		reportGenerator.generate(auteur);
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	

}
