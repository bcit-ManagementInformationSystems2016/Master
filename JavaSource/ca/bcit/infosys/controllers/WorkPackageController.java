package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.WorkPackage;

@Named("workPackageController")
@SessionScoped
public class WorkPackageController implements Serializable {
	
	@Inject
	private WorkPackageManager wpmgr;
	
	@Inject
	private EmployeeManager empmgr;
	
	
	
	private String engName;
	
	private Integer[] engineers;
	
	public WorkPackage[] getAllWorkPackages() {
		return wpmgr.getAll();
	}

	public Integer[] getEngineers() {
		engineers = wpmgr.getAllEngineers();
		return engineers;
	}

	public void setEngineers(Integer[] engineers) {
		this.engineers = engineers;
	}

	public String engIDtoName(int empID) {
		
		Employee emp = empmgr.find(empID);
		engName = emp.getFirstName() + " " + emp.getLastName();
		return engName;
		
	}



	public String wpLanding() {
		return "wpLanding";
	}
}
