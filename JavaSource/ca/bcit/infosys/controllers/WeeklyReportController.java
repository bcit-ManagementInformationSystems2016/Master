package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
//import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.PayLevelCostManager;
import ca.bcit.infosys.managers.PayLevelDaysManager;
import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.TimesheetRowManager;
import ca.bcit.infosys.models.Employee;
//import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.PayLevelCost;
import ca.bcit.infosys.models.PayLevelDays;
import ca.bcit.infosys.models.TimesheetRow;
import ca.bcit.infosys.models.WorkPackage;

@Named("weekly")
@SessionScoped
public class WeeklyReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Inject
	//private EmployeeManager empmgr;

	//@Inject
	//private WorkPackageManager wpmgr;

	@Inject
	private WorkPackageController wpctr;

	@Inject
	private TimsheetRowController tsrctr;
	
	@Inject
	private PayLevelDaysManager pldMgr;
	
	@Inject
	private TimesheetRowManager tsrmgr;
	
	@Inject
	private PayLevelCostManager plcMgr;
	
	@Inject
	private EmployeeManager empMgr;
	
	@Inject
	private PayLevelManager plvlMgr;

	private double personDays;
	private double personDollars;
	private PayLevelCost plc;
	private double estToComplete;
	private String engineer;
	private PayLevelDays pld;
	private TimesheetRow[] tsrows;
	private WorkPackage wpForReport;
	

	// GETTERS AND SETTERS

	public String getEngineer() {
		return engineer;
	}
	public TimesheetRow[] getTsrows() {
		
		return tsrows;
	}
	public WorkPackage getWpForReport() {
		return wpForReport;
	}
	public void setWpForReport(WorkPackage wpForReport) {
		this.wpForReport = wpForReport;
	}
	public PayLevelCost getPlc() {
		return plc;
	}
	public void setPlc(PayLevelCost plc) {
		this.plc = plc;
	}
	public void setTsrows(TimesheetRow[] tsrows) {
		this.tsrows = tsrows;
	}
	public PayLevelDays getPld() {
		return pld;
	}
	public void setPld(PayLevelDays pld) {
		this.pld = pld;
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

	// FUNCTIONALITY METHODS
	
	public String showWeeklyReport(WorkPackage wp) {
		pld = pldMgr.find(wp.getRemainingDaysID());
		double wpDays = tsrctr.getHoursForWP(wp.getWpID());
		wpForReport = wp;
		tsrows = tsrmgr.getRowsWithWPId(wp.getWpID());
		plc = plcMgr.getProjectCosts(wp.getWorkingProject().getProjectID());
		personDays = getActualDays(tsrows);
		personDollars = getActualCost(plc, tsrows);
		estToComplete = wpDays / 8;
		return "weeklyReport";
	}
	
	public double getActualDays(TimesheetRow[] tsrs) {
		double totalHours = 0;
		double convertedHours = 0;
		for (int i=0; i < tsrs.length; i++) {
			totalHours += tsrs[i].getTotalHours();
		}
		convertedHours = Math.round(totalHours / 8);
		return convertedHours;
	}
	
	public double getActualCost(PayLevelCost plc, TimesheetRow[] tsrs) {
		double totalCost = 0;
		for (int i=0; i < tsrs.length; i++) {
			int empID = tsrs[i].getTimesheet().getEmployeeID();
			Employee e = empMgr.getTimesheetValidator(empID);
			totalCost += (tsrs[i].getTotalHours()) * (plvlMgr.find(e.getPayLevelID()).getAvgPayRate() / 8);
		}
		return totalCost;
	}
	
	public double totalCharges() {
		estToComplete = 0;
		double days = 0;
		for (int i =0; i < tsrows.length; i++) {
			days += tsrows[i].getTotalHours() / 8;
		}
		return days;
	}
	
	public String createReport() {
		pldMgr.merge(pld);
		return "createReport";
	}
	
	public String goBack() {
		return "adminLanding";
	}

}
