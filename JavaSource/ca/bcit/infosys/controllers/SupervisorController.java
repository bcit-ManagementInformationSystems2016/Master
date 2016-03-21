package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.ProjectEmployees;

@Named("supervisorController")
@SessionScoped
public class SupervisorController implements Serializable {

	@Inject
	private EmployeeManager empmgr;
	
	//local variables
	private Employee viewableEmployee;
	private Employee timesheetValidator;
	private String newTimesheetValidator;
	
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
	public String getNewTimesheetValidator() {
		return newTimesheetValidator;
	}
	public void setNewTimesheetValidator(String newTimesheetValidator) {
		this.newTimesheetValidator = newTimesheetValidator;
	}
	
	// OTHER METHODS
	
	public String assignTimesheetValidator(Employee e) {
		setViewableEmployee(e);
		setTimesheetValidator(empmgr.getTimesheetValidator(e.getValidatorID()));
		return "assignTimesheetValidator";
	}
	
	public java.util.List<SelectItem> getDropdownForValidators() {
		if (viewableEmployee == null) {
			System.out.println("SupervisorController - There is no employee yet");
		}
		return empmgr.getListOfEmployees(this.viewableEmployee.getEmployeeID());
	}
	
	public String assignValidator() {
		int n = new Integer(newTimesheetValidator);
		getViewableEmployee().setValidatorID(n);
		empmgr.merge(viewableEmployee);	
		return "viewMinions";
	}
	
}
