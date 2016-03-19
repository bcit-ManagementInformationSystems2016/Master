package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Employee;

@Named("supervisorController")
@SessionScoped
public class SupervisorController implements Serializable {

	@Inject
	private EmployeeManager empmgr;
	
	//local variables
	private Employee viewableEmployee;
	private Employee timesheetValidator;
	
	// GETTERS AND SETTERS
	public Employee getViewableEmployee() {
		return viewableEmployee;
	}
	public void setViewableEmployee(Employee viewableEmployee) {
		this.viewableEmployee = viewableEmployee;
	}
	public Employee getTimesheetValidator() {
		return timesheetValidator;
	}
	public void setTimesheetValidator(Employee timesheetValidator) {
		this.timesheetValidator = timesheetValidator;
	}
	
	// OTHER METHODS
	
	public String assignTimesheetValidator(Employee e) {
		System.out.println("method started");
		System.out.println("Employee is: " + e.getFirstName() + " " + e.getLastName());
		setViewableEmployee(e);
		System.out.println("The viewable emp is " + getViewableEmployee().getFirstName());
		setTimesheetValidator(empmgr.getTimesheetValidator(e.getValidatorID()));
		System.out.println("The Timesheet emp is " + getTimesheetValidator().getFirstName());
		return "assignTimesheetValidator";
	}
}
