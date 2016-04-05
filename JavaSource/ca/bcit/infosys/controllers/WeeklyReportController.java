package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.PayLevelDaysManager;
import ca.bcit.infosys.managers.TimesheetRowManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.PayLevelDays;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.TimesheetRow;
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
	
	@Inject
	private PayLevelDaysManager pldMgr;
	
	@Inject
	private TimesheetRowManager tsrmgr;

	private double personDays;

	private double personDollars;

	private double estToComplete;

	private String engineer;

	private PayLevelDays pld;
	
	private TimesheetRow[] tsrows;
	

	

	public String getEngineer() {
		return engineer;
	}

	public TimesheetRow[] getTsrows() {
		
		return tsrows;
	}

	public void setTsrows(TimesheetRow[] tsrows) {
		this.tsrows = tsrows;
	}

	/**
	 * @return the pld
	 */
	public PayLevelDays getPld() {
		return pld;
	}

	/**
	 * @param pld the pld to set
	 */
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

	public String showWeeklyReport(WorkPackage wp) {
		//WorkPackage wp = wpmgr.find(proj, wpID);
		pld = pldMgr.find(wp.getRemainingDaysID());
		double wpDays = tsrctr.getHoursForWP(wp.getWpID());
		//System.out.println(wpDays);
		personDays = wp.getTotalBudgetDays();
		personDollars = wp.getTotalBudgetCost();
		engineer = wpctr.engIDtoName(wp.getResponsibleEngineerID());
		estToComplete = wpDays / 8;
		tsrows = tsrmgr.getRowsWithWPId(wp.getWpID());

		return "weeklyReport";
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
