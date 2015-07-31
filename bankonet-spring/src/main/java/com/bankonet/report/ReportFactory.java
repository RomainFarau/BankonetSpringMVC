package com.bankonet.report;

public class ReportFactory {
	public static ReportGenerator createReport(String reportType) {
		if(reportType.equals("pdf")){
			return new IReportGeneratorImplPdf();
		}else if(reportType.equals("html"))
		{
			return new IReportGeneratorImplHtml();
		}
		throw new IllegalArgumentException(); 	
	}
}
