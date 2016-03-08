package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Project;

@Named("hrController")
@SessionScoped
public class HRController implements Serializable {

	@Inject
	private EmployeeManager empmgr;

	// variable to save the current employee
	private Employee emp = new Employee();

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public String editEmp(Employee e) {
		setEmp(e);
		System.out.println("edit emp");
		return "employee";
	}
	public String updateEmp(Employee e){
		empmgr.merge(e);
		emp = null;
		return "updated";
	}
	public String createEmp(Employee e){
		empmgr.persist(e);
		return "created";
	}
}
