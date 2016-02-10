package ca.bcit.infosys.models;

import java.io.Serializable;

public class TimeSheetRow implements Serializable{
	
	private int timesheetId;
	
	private int wpId;
	
	private double hours;
	
	private int projectId;

	public int getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(int timesheetId) {
		this.timesheetId = timesheetId;
	}

	public int getWpId() {
		return wpId;
	}

	public void setWpId(int wpId) {
		this.wpId = wpId;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	

}
