package ca.bcit.infosys.models;

import java.io.Serializable;

public class Report implements Serializable{
	
	private int reportId;
	
	private String reportDescription;

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	
	
	
	

}
