package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Project implements Serializable {
	
	private int projectID;
	
	private  String projectName;
	
	private Date startDate;
	
	private Double rate;
	
	private int employeeId;
	
	private ArrayList<WorkPackage> workpackages;

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public ArrayList<WorkPackage> getWorkpackages() {
		return workpackages;
	}

	public void setWorkpackages(ArrayList<WorkPackage> workpackages) {
		this.workpackages = workpackages;
	}
	
	

}
