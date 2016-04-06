package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Employee;

@Named("supervisorController")
@ConversationScoped
public class SupervisorController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EmployeeManager empmgr;

	// local variables
	private Employee viewableEmployee;
	private Employee timesheetValidator;
	private String newTimesheetValidator;

	// variable used for caching
	private List<SelectItem> availableValidators;

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

	public List<SelectItem> getAvailableValidators() {
		return availableValidators;
	}

	public void setAvailableValidators(List<SelectItem> availableValidators) {
		this.availableValidators = availableValidators;
	}

	// OTHER METHODS
	public String assignTimesheetValidator(Employee e) {
		setViewableEmployee(e);
		setTimesheetValidator(empmgr.getTimesheetValidator(e.getValidatorID()));
		return "assignTimesheetValidator";
	}

	public List<SelectItem> getDropdownForValidators() {
		if (viewableEmployee == null) {
			System.out.println("SupervisorController - There is no employee yet");
		}
		if (availableValidators == null) {
			System.out.println("Got from database");
			setAvailableValidators(empmgr.getListOfEmployees(this.viewableEmployee.getEmployeeID()));
		} else {
			System.out.println("skipped query");
		}
		return availableValidators;
	}

	public String assignValidator() {
		int n = new Integer(newTimesheetValidator);
		getViewableEmployee().setValidatorID(n);
		empmgr.merge(viewableEmployee);
		setAvailableValidators(null);
		return "viewMinions";
	}

	public String cancelAssignment() {
		setAvailableValidators(null);
		return "viewMinions";
	}

}
