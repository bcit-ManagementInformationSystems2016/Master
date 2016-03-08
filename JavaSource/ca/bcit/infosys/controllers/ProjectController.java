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
	private boolean showAllwps;
	private WorkPackage parentWP;
	private WorkPackage newWP;
	
	// Getters and Setters
	public void setEditableProject(Project editableProject) {
		this.editableProject = editableProject;
	}
	public Project getEditableProject() {
		return editableProject;
	}
	public void setShowAllwps(boolean showAllwps) {
		this.showAllwps = showAllwps;
	}
	public boolean getShowAllwps() {
		return showAllwps;
	}
	public WorkPackage getParentWP() {
		return parentWP;
	}
	public void setParentWP(WorkPackage parentWP) {
		this.parentWP = parentWP;
	}
	public void setNewWP(WorkPackage newWP) {
		this.newWP = newWP;
	}
	public WorkPackage getNewWP() {
		return newWP;
	}
	
	// Other methods
	public Project[] getAllProjects() {
		return pjtmgr.getAll();
	}
	
	public WorkPackage[] getWorkPackagesForProject() {
		if (getShowAllwps()) {
			return wpmgr.getProjectWorkPackages(editableProject.getProjectID());
		}
		else if (getParentWP() == null){
			return wpmgr.getParentProjectWorkPackages(editableProject.getProjectID(), null);
		}
		else {
			return wpmgr.getParentProjectWorkPackages(editableProject.getProjectID(), getParentWP().getWpID());
			
		}
	} 
	
	public String goToShowWP(Project project) {
		setEditableProject(project);
		setShowAllwps(true);
		setParentWP(null);
		return "wpDetails";
	}
	
	public String goToShowWPTree(Project project) {
		setEditableProject(project);
		setShowAllwps(false);
		setParentWP(null);
		return "wpDetails";
	}
	
	public String goToShowWPChild(WorkPackage wp) {
		setParentWP(wp);
		return "wpDetails";
	}
	
	public String goToShowWPParent() {
		if (parentWP == null) {
			return "showProjects";
		}
		if (getParentWP().getWpID() == null) {
			return "showProjects";
		}
		if (getParentWP().getParentWPID() == null) {
			setParentWP(null);
		} else {
			WorkPackage parent = wpmgr.find(getParentWP().getParentWPID());
			setParentWP(parent);
		}
		return "wpDetails";
	}
	
	public String createNewWorkPackage() {
		//System.out.println("Parent ID: " + getParentWP().getParentWPID());
		//System.out.println("ID: " + getParentWP().getWpID());
		WorkPackage wp = new WorkPackage();
		if (parentWP == null) {
			wp.setParentWPID(null);
		} else if (getParentWP().getParentWPID() == null) {
			wp.setParentWPID(null);
		} else {
			wp.setParentWPID(getParentWP().getWpID());
		}
		int nextWP = 0;
		if (parentWP == null || getParentWP().getParentWPID() == null) {
			nextWP = wpmgr.getWorkPackageCountWithNull(editableProject.getProjectID()) + 1;
		} else {
			nextWP = wpmgr.getWorkPackageCount(editableProject.getProjectID(), getParentWP().getWpID()) + 1;
		}
		wp.setWpID(wp.getParentWPID() + "." + nextWP);
		wp.setWorkingProject(editableProject);
		setNewWP(wp);
		return "createWP";
	}
}
