package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.VacationManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.PayLevel;
import ca.bcit.infosys.models.Vacation;

@Named("vacation")
@ConversationScoped
public class VacationController implements Serializable {
	Employee e;

	public void getUser(Employee emp) {
		System.out.println("GET USER HR CONTROLLER");
		e = emp;
	}

	private static final long serialVersionUID = 1L;

	@Inject
	private VacationManager vacationManager;
	@Inject
	private EmployeeManager empmgr;
	@Inject
	private PayLevelManager plvlmgr;

	// variable used to get vacation info
	private Employee emp;
	private Vacation[] vacayArray;
	private PayLevel payLvl;

	// variables used to display vacation info
	private int totalDaysAllowed;
	private int days;
	private int daysRemaining;

	public int getDaysRemaining() {
		if (daysRemaining == 0) {
			daysRemaining = vacationManager.getDaysRemaining(e.getEmployeeID());
		}
		return daysRemaining;
	}

	public void setDaysRemaining(int daysRemaining) {
		this.daysRemaining = daysRemaining;
	}

	private Vacation v = new Vacation();

	// Getters and Setters
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Vacation[] getVacayArray() {
		if (vacayArray == null) {
			setVacayArray(vacationManager.getEmployeeVacationRequests(e.getEmployeeID()));
		}
		return vacayArray;
	}

	public void setVacayArray(Vacation[] vacayArray) {
		this.vacayArray = vacayArray;
	}

	public PayLevel getPayLvl() {
		return payLvl;
	}

	public void setPayLvl(PayLevel payLvl) {
		this.payLvl = payLvl;
	}

	public int getTotalDaysAllowed() {
		if (totalDaysAllowed == 0) {
			totalDaysAllowed = vacationManager.getDaysAllowed(e.getEmployeeID());
		}
		return totalDaysAllowed;
	}

	public void setTotalDaysAllowed() {
		int i = vacationManager.getDaysAllowed(e.getEmployeeID());
		totalDaysAllowed = i;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int daysRemaining) {
		this.days = daysRemaining;
	}

	// Other methods

	public String goToVacationPage() {
		setEmp(empmgr.getTimesheetValidator(e.getEmployeeID()));
		setVacayArray(vacationManager.getEmployeeVacationRequests(emp.getEmployeeID()));
		setPayLvl(plvlmgr.getPayLevelInfo(emp.getPayLevelID()));
		return "viewVacation";
	}

	public String createVacation(Vacation v) throws ParseException {

		Date date = new Date();
		v.setEmployeeID(e.getEmployeeID());
		v.setRequestDate(date);
		v.setVacationDaysLeft(vacationManager.getDaysRemaining(e.getEmployeeID()) - days);
		vacationManager.persist(v);

		return "created";
	}

	public Vacation getV() {
		return v;
	}

	public void setV(Vacation v) {
		this.v = v;
	}

	public String goViewVacation() {
		return "viewVacation";
	}

}
