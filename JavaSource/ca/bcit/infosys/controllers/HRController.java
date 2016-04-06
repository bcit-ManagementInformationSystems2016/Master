package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ca.bcit.infosys.managers.CredentialManager;
import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.RolesManager;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Employee;

@Named("hrController")
@ConversationScoped
public class HRController implements Serializable {
	private static final long serialVersionUID = 1L;
	Employee e;

	public void getUser(Employee emp) {
		System.out.println("GET USER HR CONTROLLER");
		e = emp;
	}

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	@Inject
	private EmployeeManager empmgr;

	@Inject
	private CredentialManager crdmgr;

	@Inject
	private PayLevelManager plmgr;

	@Inject
	private RolesManager rmgr;

	// variable to view specific employee data
	private Employee viewableEmp;

	// variable to save the current employee
	static Employee emp = new Employee();
	private Credential crd = new Credential();

	// variables used for caching
	private Employee[] minions;
	private List<SelectItem> employeeList;
	private List<SelectItem> payLevelList;
	private List<SelectItem> roleList;

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
		HRController.emp = emp;
	}
	public Employee getViewableEmp() {
		return viewableEmp;
	}
	public void setViewableEmp(Employee emp) {
		viewableEmp = emp;
	}
	public void setMinions(Employee[] minions) {
		this.minions = minions;
	}
	public Employee[] getMinions() {
		return empmgr.getAllMinions(e.getEmployeeID());
	}
	public List<SelectItem> getPayLevelList() {
		if (payLevelList == null) {
			setPayLevelList(plmgr.getPayLevelIDs());
		}
		return payLevelList;
	}
	public void setPayLevelList(List<SelectItem> payLevelList) {
		this.payLevelList = payLevelList;
	}
	public List<SelectItem> getEmployeeList() {
		if (employeeList == null) {
			setEmployeeList(empmgr.getEmployeeIDs());
		}
		return employeeList;
	}
	public List<SelectItem> getRoleList() {
		if (roleList == null) {
			setRoleList(rmgr.getRolesIDs());
		}
		return roleList;
	}
	public void setEmployeeList(List<SelectItem> employeeList) {
		this.employeeList = employeeList;
	}
	public void setRoleList(List<SelectItem> roleList) {
		this.roleList = roleList;
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
		setEmployeeList(empmgr.getEmployeeIDs());
		emp = new Employee();
		crd = new Credential();
		return "updated";
	}

	public String createEmp(Employee e) {
		empmgr.persist(e);
		crd.setEmployeeID(e.getEmployeeID());
		crdmgr.merge(crd);
		AccountController.e = empmgr.getAll();
		setEmployeeList(empmgr.getEmployeeIDs());
		emp = new Employee();
		crd = new Credential();
		return "created";
	}

	public Employee[] getYourMinions() {
		if (minions == null) {
			setMinions(empmgr.getAllMinions(e.getEmployeeID()));
		}
		return minions;
	}

	public String leaveMinionsPage() {
		setMinions(null);
		return "adminLanding";
	}

	public String viewMinionsPage() {
		setMinions(null);
		System.out.println("HR: test");
		return "viewMinions";
	}

	public String goToAssignPage(Employee e) {
		setViewableEmp(e);
		return "assignEmpToProject";
	}

	public String changePassword() {
		crd.setEmployeeID(e.getEmployeeID());
		// crd.setUsername(l.getUsername());
		crd.setUsername("username");
		crdmgr.merge(crd);
		crd = new Credential();
		return "changed";
	}

	public String hrLanding() {
		emp = new Employee();
		crd = new Credential();
		return "hrLanding";
	}

}
