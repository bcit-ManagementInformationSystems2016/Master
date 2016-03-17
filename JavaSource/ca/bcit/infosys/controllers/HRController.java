package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.managers.CredentialManager;
import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Project;

@Named("hrController")
@SessionScoped
public class HRController implements Serializable {
	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	@Inject
	private EmployeeManager empmgr;

	@Inject
	private CredentialManager crdmgr;

	// variable to view specific employee data
	private Employee viewableEmp;
	private Project assignedProject;

	// variable to save the current employee
	private Employee emp = new Employee();
	private Credential crd = new Credential();

	// GETTERS AND SETTERS
	public Credential getCrd() {
		return crd;
	}

	public void setCrd(Credential crd) {
		this.crd = crd;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Employee getViewableEmp() {
		return viewableEmp;
	}

	public void setViewableEmp(Employee emp) {
		viewableEmp = emp;
	}

	public Project getAssignedProject() {
		return assignedProject;
	}

	public void setAssignedProject(Project assignedProject) {
		this.assignedProject = assignedProject;
	}

	// Other Methods
	public String editEmp(Employee e) {
		setEmp(e);
		TypedQuery<Credential> query = em
				.createQuery("select c from Credential c where c.employeeID = " + e.getEmployeeID(), Credential.class);
		List<Credential> cred = query.getResultList();
		Credential[] crarray = new Credential[cred.size()];
		for (int i = 0; i < crarray.length; i++) {
			crarray[i] = cred.get(i);
		}
		crd.setUsername(crarray[0].getUsername());
		crd.setPassword(crarray[0].getPassword());
		return "employee";
	}

	public String updateEmp(Employee e) {
		empmgr.merge(e);
		crd.setEmployeeID(e.getEmployeeID());
		crdmgr.merge(crd);
		AccountController.e = empmgr.getAll();
		emp = new Employee();
		crd = new Credential();
		return "updated";
	}

	public String createEmp(Employee e) {
		empmgr.persist(e);
		crd.setEmployeeID(e.getEmployeeID());
		crdmgr.merge(crd);
		AccountController.e = empmgr.getAll();
		emp = new Employee();
		crd = new Credential();
		return "created";
	}

	public Employee[] getMinions() {
		return empmgr.getAllMinions(Login.currentID);
	}

	public String viewMinionsPage() {
		return "viewMinions";
	}

	public String goToAssignPage(Employee e) {
		setViewableEmp(e);
		return "assignEmpToProject";
	}

	public String changePassword() {
		crd.setEmployeeID(Login.currentID);
		crd.setUsername(Login.username);
		crdmgr.merge(crd);
		crd = new Credential();
		return "changed";
	}
}
