package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.TimesheetManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Timesheet;

@Named("timesheetValidatorController")
@ConversationScoped
public class TimesheetValidatorController implements Serializable {
	Employee e;

	public void getUser(Employee emp) {
		e = emp;
	}

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;

	@Inject
	private EmployeeManager empmgr;
	@Inject
	private TimesheetManager tsmgr;

	// local variables
	private Employee currentEmployee;

	// variables used for caching
	private Employee[] validatees;
	private Timesheet[] unapprovedTimesheets;

	// Getters and Setters

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	// Other methods

	public Employee[] getValidatees() {
		return validatees;
	}

	public void setValidatees(Employee[] validatees) {
		this.validatees = validatees;
	}

	public Timesheet[] getUnapprovedTimesheets() {
		return unapprovedTimesheets;
	}

	public void setUnapprovedTimesheets(Timesheet[] unapprovedTimesheets) {
		this.unapprovedTimesheets = unapprovedTimesheets;
	}

	public Employee[] getYourValidatees() {
		if (validatees == null) {
			setValidatees(empmgr.getValidatees(e.getEmployeeID()));
		}
		return validatees;
	}

	public Timesheet[] getYouUnapprovedTimesheets() {
		if (validatees == null) {
			setValidatees(empmgr.getValidatees(e.getEmployeeID()));
		}
		if (unapprovedTimesheets == null) {
			setUnapprovedTimesheets(tsmgr.getUnapprovedTimesheets(validatees));
		}
		return unapprovedTimesheets;
	}

	public String validateTimesheet(Timesheet ts) {
		System.out.println("Validate Timesheet");
		System.out.println("Employee ID: " + ts.getEmployeeID());
		ts.setApproved(true);
		tsmgr.merge(ts);
		setUnapprovedTimesheets(null);
		setValidatees(null);
		return "getValidating";
	}

	public String disapproveTimesheet(Timesheet ts) {
		System.out.println("Disapprove Timesheet");
		System.out.println("Employee ID: " + ts.getEmployeeID());
		ts.setSubmitted(false);
		tsmgr.merge(ts);
		setUnapprovedTimesheets(null);
		setValidatees(null);
		return "getValidating";
	}

	public String viewValidatees() {
		setCurrentEmployee(empmgr.find(e.getEmployeeID()));
		setUnapprovedTimesheets(null);
		setValidatees(null);
		return "getValidating";
	}

	public String leaveTimesheetValidating() {
		setCurrentEmployee(null);
		setUnapprovedTimesheets(null);
		setValidatees(null);
		return "adminLanding";
	}
}
