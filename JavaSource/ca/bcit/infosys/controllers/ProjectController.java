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
	
	private static Project[] pro;
	private static WorkPackage[] wp;
	
	// variable to save the current project
	private Project editableProject;
	private boolean showAllwps;
	private WorkPackage parentWP;
	private WorkPackage newWP;
	private int savedProjectID;
	private String savedWPID;
	
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
		public static Project[] getPro() {
		return pro;
	}
	public static void setPro(Project[] pro) {
		ProjectController.pro = pro;
	}	
	public static WorkPackage[] getWp() {
		return wp;
	}
	public static void setWp(WorkPackage[] wp) {
		ProjectController.wp = wp;
	}
	public int getSavedProjectID() {
		return savedProjectID;
	}
	public void setSavedProjectID(int savedProjectID) {
		this.savedProjectID = savedProjectID;
	}
	public String getSavedWPID() {
		return savedWPID;
	}
	public void setSavedWPID(String savedWPID) {
		this.savedWPID = savedWPID;
	}
	
	// Other methods
	public Project[] getAllProjects() {
		if (pro == null) {
			return pjtmgr.getAll();
		}
		return pro;	
	}
	
	public WorkPackage[] getWorkPackagesForProject() {
		if (getShowAllwps()) {
			if ( getSavedProjectID() != editableProject.getProjectID() ) {
				wp = wpmgr.getProjectWorkPackages(editableProject.getProjectID());
				setSavedProjectID(editableProject.getProjectID());
			}
			return wp;
		}
		else if (getParentWP() == null){
			if ( getSavedProjectID() != editableProject.getProjectID() ||  getSavedWPID() != null )  {
				wp = wpmgr.getParentProjectWorkPackagesNull(editableProject.getProjectID(), null);
				setSavedProjectID(editableProject.getProjectID());
				setSavedWPID(null);
			}
			return wp;
		}
		else {
			if ( getSavedProjectID() != editableProject.getProjectID() ||  getSavedWPID() != getParentWP().getWpID() ) {
				wp = wpmgr.getParentProjectWorkPackages(editableProject.getProjectID(), getParentWP().getWpID());
				setSavedProjectID(editableProject.getProjectID());
				setSavedWPID(getParentWP().getWpID());
			}
			return wp;
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
			setSavedProjectID(-1);
			return "showProjects";
		}
		if (getParentWP().getWpID() == null) {
			setSavedProjectID(-1);
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
		WorkPackage wp = new WorkPackage();
		if (parentWP == null) {
			wp.setParentWPID(null);
		} 
		else {
			wp.setParentWPID(getParentWP().getWpID());
		}
		int nextWP = 0;
		if (parentWP == null) {
			nextWP = wpmgr.getWorkPackageCountWithNull(editableProject.getProjectID()) + 1;
			wp.setWpID("" + nextWP);
		} else {
			nextWP = wpmgr.getWorkPackageCount(editableProject.getProjectID(), getParentWP().getWpID()) + 1;
			wp.setWpID(wp.getParentWPID() + "." + nextWP);
		}
		wp.setWorkingProject(editableProject);
		setNewWP(wp);
		return "createWP";
	}
	
	public String saveNewWP() {
		wpmgr.addWP(newWP);
		return "wpDetails";
	}
}
