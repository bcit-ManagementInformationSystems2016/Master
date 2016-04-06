package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.ProjectEmployeesManager;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.ProjectEmployees;
//import ca.bcit.infosys.models.ProjectEmployeesKey;

@Named("projectEmployeesController")
@ConversationScoped
public class ProjectEmployeesController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ProjectManager pjtmgr;
	@Inject
	private EmployeeManager empmgr;
	@Inject
	private ProjectEmployeesManager pjtEmpMgr;

	// variables
	private Project viewableProject;
	private Employee viewableEmployee;
	private String assignedProject;
	private int assignedProjectID;

	// variables used for caching
	private Project[] pros;
	private List<SelectItem> availableProjects;
	private Employee[] availableEmps;

	// Getters and Setters
	public Project getViewableProject() {
		return viewableProject;
	}

	public void setViewableProject(Project viewableProject) {
		this.viewableProject = viewableProject;
	}

	public Employee getViewableEmployee() {
		return viewableEmployee;
	}

	public void setViewableEmployee(Employee viewableEmployee) {
		this.viewableEmployee = viewableEmployee;
	}

	public String getAssignedProject() {
		return assignedProject;
	}

	public void setAssignedProject(String assignedProject) {
		this.assignedProject = assignedProject;
	}

	public int getAssignedProjectID() {
		return assignedProjectID;
	}

	public void setAssignedProjectID(int assignedProjectID) {
		this.assignedProjectID = assignedProjectID;
	}

	public Project[] getPros() {
		return pros;
	}

	public void setPros(Project[] pros) {
		this.pros = pros;
	}

	public List<SelectItem> getAvailableProjects() {
		return availableProjects;
	}

	public void setAvailableProjects(List<SelectItem> availableProjects) {
		this.availableProjects = availableProjects;
	}

	public Employee[] getAvailableEmps() {
		return availableEmps;
	}

	public void setAvailableEmps(Employee[] availableEmps) {
		this.availableEmps = availableEmps;
	}

	// Other Methods
	public String showAssignedEmps(Project p) {
		setViewableProject(p);
		return "listEmpsForProject";
	}

	public String showAssignedProjects(Employee e) {
		setViewableEmployee(e);
		return "listProjectsForEmps";
	}

	public String goToAssignPage(Employee e) {
		setViewableEmployee(e);
		return "assignEmpToProject";
	}

	public Employee[] allEmployeesForProject() {
		if (availableEmps == null) {
			availableEmps = empmgr.getAllWithinProject(viewableProject.getProjectID());
		}
		return availableEmps;
	}

	public Project[] allProjectsForEmployee() {
		if (pros == null) {
			setPros(pjtmgr.getAllProjectsForEmp(viewableEmployee.getEmployeeID()));
		}
		return pros;
	}

	public List<SelectItem> getDropdownForProjects() {
		if (viewableEmployee == null) {
			System.out.println("ProjectEmployeesController - There is no employee yet");
		}
		if (availableProjects == null) {
			setAvailableProjects(pjtEmpMgr.getAllAvailableProjects(this.viewableEmployee.getEmployeeID()));
		}
		return availableProjects;
	}

	public String assignEmployee() {
		int n = new Integer(assignedProject);
		ProjectEmployees pe = new ProjectEmployees();
		Employee e = new Employee();
		e = empmgr.getTimesheetValidator(viewableEmployee.getEmployeeID());
		pe.setEmp(e);
		pe.setPro(pjtmgr.find(n));
		pjtEmpMgr.merge(pe);
		setAvailableProjects(null);
		return "viewMinions";
	}

	public String unassignEmployee(Project p) {
		pjtEmpMgr.remove(p, viewableEmployee);
		return "viewMinions";
	}

	public String goToViewMinionsPage() {
		setPros(null);
		return "viewMinions";
	}

	public String cancelAssignment() {
		setAvailableProjects(null);
		return "viewMinions";
	}

	public String goToViewProjectsPage() {
		setAvailableEmps(null);
		return "showAllProjects";
	}

}
