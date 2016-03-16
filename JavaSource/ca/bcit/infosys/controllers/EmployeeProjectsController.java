package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Project;

@Named("employeeProjectsController")
@SessionScoped
public class EmployeeProjectsController implements Serializable {

	@Inject
	private ProjectManager pjtmgr;
	@Inject
	private EmployeeManager empmgr;
	
	// variables
	private Project viewableProject;
	
	//Getters and Setters
	public Project getViewableProject() {
		return viewableProject;
	}

	public void setViewableProject(Project viewableProject) {
		this.viewableProject = viewableProject;
	}
	
	
	// Other Methods
	public String showAssignedEmps(Project p) {
		setViewableProject(p);
		return "listEmpsForProject";
	}
	
	public Employee[] allEmployeesForProject() {
		return empmgr.getAllWithinProject(viewableProject.getProjectID());
	}

	
}
