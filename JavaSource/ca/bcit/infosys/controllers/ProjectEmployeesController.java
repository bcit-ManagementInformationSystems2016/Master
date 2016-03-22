package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
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
@SessionScoped
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
	private static Project[] pros;
	
	//Getters and Setters
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
	public void setPros(Project[] pros) {
		ProjectEmployeesController.pros = pros;
	}
	public Project[] getPros() {
		return pros;
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
		return empmgr.getAllWithinProject(viewableProject.getProjectID());
	}
	
	public Project[] allProjectsForEmployee() {
		if (pros == null) {
			setPros(pjtmgr.getAllProjectsForEmp(viewableEmployee.getEmployeeID()));
		}
		return pros;
	}
	
	public java.util.List<SelectItem> getDropdownForProjects() {
		if (viewableEmployee == null) {
			System.out.println("ProjectEmployeesController - There is no employee yet");
		}
		return pjtEmpMgr.getAllAvailableProjects(this.viewableEmployee.getEmployeeID());
	}
	
	public String assignEmployee() {
		int n = new Integer(assignedProject);
		ProjectEmployees pe = new ProjectEmployees();
		Employee e = new Employee();
		e = empmgr.getTimesheetValidator(viewableEmployee.getEmployeeID());
		pe.setEmp(e);
		pe.setPro(pjtmgr.find(n));
		pjtEmpMgr.merge(pe);	
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
	
}
