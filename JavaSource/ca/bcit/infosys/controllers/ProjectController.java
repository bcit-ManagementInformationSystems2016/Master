package ca.bcit.infosys.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;

@Named("projectController")
@SessionScoped
public class ProjectController implements Serializable {

	@Inject
	private ProjectManager pjtmgr;
	@Inject
	private WorkPackageManager wpmgr;
	
	// variable to save the current project
	private Project editableProject;
	
	// Getters and Setters
	public void setEditableProject(Project editableProject) {
		this.editableProject = editableProject;
	}
	public Project getEditableProject() {
		return editableProject;
	}
	
	// Other methods
	
	public Project[] getAllProjects() {
		return pjtmgr.getAll();
	}
	
	public WorkPackage[] getWorkPackagesForProject() {
		return wpmgr.getProjectWorkPackages(editableProject.getProjectID());
	} 
	
	public String goToShowWP(Project project) {
		setEditableProject(project);
		return "wpDetails";
	}
}
