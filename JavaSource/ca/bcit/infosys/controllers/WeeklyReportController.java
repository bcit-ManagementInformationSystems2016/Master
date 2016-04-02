package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;

@Named("weekly")
@SessionScoped
public class WeeklyReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmployeeManager empmgr;
	
	@Inject 
	private WorkPackageManager wpmgr;
	
	@Inject
	private WorkPackageController wpctr;
	
	@Inject 
	private TimsheetRowController tsrctr;
	
	private double personDays;
	
	private double personDollars;
	
	private double estToComplete;
	
	private String engineer;
	
	
	
	
	
	public String getEngineer() {
		return engineer;
	}



	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}



	public double getPersonDays() {
		return personDays;
	}



	public void setPersonDays(double personDays) {
		this.personDays = personDays;
	}



	public double getPersonDollars() {
		return personDollars;
	}



	public void setPersonDollars(double personDollars) {
		this.personDollars = personDollars;
	}



	public double getEstToComplete() {
		return estToComplete;
	}



	public void setEstToComplete(double estToComplete) {
		this.estToComplete = estToComplete;
	}



	public String showWeeklyReport(Project proj, String wpID) {
		WorkPackage wp = wpmgr.find(proj, wpID);
		double wpDays = tsrctr.getHoursForWP(wpID) / 8;
		personDays = wp.getTotalBudgetDays();
		personDollars = wp.getTotalBudgetCost();
		engineer = wpctr.engIDtoName(wp.getResponsibleEngineerID());
		estToComplete = personDays - wpDays;
	
		return "weeklyReport";
	}
	
	
	

	
	
	
	

}
