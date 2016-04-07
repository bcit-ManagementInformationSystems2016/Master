package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.EmployeeWPManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.EmployeeWP;
import ca.bcit.infosys.models.WorkPackage;

@Named("workPackageController")
@ConversationScoped
public class WorkPackageController implements Serializable {
	private static final long serialVersionUID = 1L;

	Employee e;

	public void getUser(Employee emp) {
		e = emp;
	}
	@Inject
	private WorkPackageManager wpmgr;

	@Inject
	private EmployeeManager empmgr;

	@Inject
	private EmployeeWPManager emwpmgr;

	private String engName;

	private Integer[] engineers;

	private Employee currentEmp;

	private EmployeeWP[] empWP;

	private WorkPackage[] resonsibleWPs;

	private WorkPackage[] wpsForReport;

	/**
	 * @return the wpsForReport
	 */
	public WorkPackage[] getWpsForReport() {
		wpsForReport = WPsForReport();
		return wpsForReport;
	}

	/**
	 * @param wpsForReport
	 *            the wpsForReport to set
	 */
	public void setWpsForReport(WorkPackage[] wpsForReport) {
		this.wpsForReport = wpsForReport;
	}

	/**
	 * @return the resonsibleWPs
	 */
	public WorkPackage[] getResonsibleWPs() {
		resonsibleWPs = wpmgr.getWPforEng(e.getEmployeeID());
		return resonsibleWPs;
	}

	/**
	 * @param resonsibleWPs
	 *            the resonsibleWPs to set
	 */
	public void setResonsibleWPs(WorkPackage[] resonsibleWPs) {
		this.resonsibleWPs = resonsibleWPs;
	}

	public EmployeeWP[] getEmpWP() {
		empWP = showAssignedWPs((e.getEmployeeID()));
		return empWP;
	}

	public EmployeeWP[] showAssignedWPs(int empID) {

		return emwpmgr.getYourWPs(empID);
	}

	public void setEmpWP(EmployeeWP[] empWP) {

		this.empWP = empWP;
	}

	public Employee getCurrentEmp() {
		currentEmp = empmgr.find(e.getEmployeeID());
		return currentEmp;
	}

	public void setCurrentEmp(Employee currentEmp) {
		this.currentEmp = currentEmp;
	}

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

	public WorkPackage[] WPsForReport() {

		return null;
	}

	public String wpLanding() {
		return "getWorkpackages";
		// return "wpLanding";
	}
}
