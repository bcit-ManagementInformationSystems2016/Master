package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Employee;

@Named("weekly")
@SessionScoped
public class WeeklyReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmployeeManager empmgr;
	
	public String showWeeklyReport() {
		return "weeklyReport";
	}
	

	
	
	
	

}
