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
	private boolean showAllPros;
	private WorkPackage parentWP;
	private int savedProjectID;
	private String savedWPID;

	// variables to create new Work Packages
	private WorkPackage wpToAdd = new WorkPackage();
	private Project projectToAdd = new Project();
	private String newWPID;
	private String newWPPID;

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

	public String getNewWPID() {
		return newWPID;
	}

	public void setNewWPID(String newWPID) {
		this.newWPID = newWPID;
	}

	public String getNewWPPID() {
		return newWPPID;
	}

	public void setNewWPPID(String newWPPID) {
		this.newWPPID = newWPPID;
	}

	public void setWpToAdd(WorkPackage wpToAdd) {
		this.wpToAdd = wpToAdd;
	}

	public WorkPackage getWpToAdd() {
		return wpToAdd;
	}

	public Project getProjectToAdd() {
		return projectToAdd;
	}

	public void setProjectToAdd(Project projectToAdd) {
		this.projectToAdd = projectToAdd;
	}

	public boolean getShowAllPros() {
		return showAllPros;
	}

	public void setShowAllPros(boolean showAllPros) {
		this.showAllPros = showAllPros;
	}

	// Other methods
	public Project[] getAllProjects() {
		if (pro == null) {
			if (getShowAllPros()) {
				return pjtmgr.getAll();
			} else {
				return pjtmgr.getSome(Login.currentID);
			}
		}
		return pro;
	}

	public WorkPackage[] getWorkPackagesForProject() {
		setWpArray();
		return wp;
	}

	public void setWpArray() {
		if (getShowAllwps()) {
			if (getSavedProjectID() != editableProject.getProjectID()) {
				wp = wpmgr.getProjectWorkPackages(editableProject.getProjectID());
				setSavedProjectID(editableProject.getProjectID());
			}
		} else if (getParentWP() == null) {
			if (getSavedProjectID() != editableProject.getProjectID() || getSavedWPID() != null) {
				wp = wpmgr.getParentProjectWorkPackagesNull(editableProject.getProjectID(), null);
				setSavedProjectID(editableProject.getProjectID());
				setSavedWPID(null);
			}
		} else {
			if (getSavedProjectID() != editableProject.getProjectID() || getSavedWPID() != getParentWP().getWpID()) {
				wp = wpmgr.getParentProjectWorkPackages(editableProject.getProjectID(), getParentWP().getWpID());
				setSavedProjectID(editableProject.getProjectID());
				setSavedWPID(getParentWP().getWpID());
			}
		}
	}

	public String exit() {
		setSavedProjectID(-1);
		setParentWP(null);
		return "projectsLanding";
	}

	public String showAllProjects() {
		setShowAllPros(true);
		return "showAllProjects";
	}

	public String showYourProjects() {
		setShowAllPros(false);
		return "showAllProjects";
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
			WorkPackage parent = wpmgr.find(editableProject, getParentWP().getParentWPID());
			setParentWP(parent);
		}
		return "wpDetails";
	}

	public String createNewWorkPackage() {
		if (parentWP == null) {
			setNewWPPID(null);
		} else {
			setNewWPPID(getParentWP().getWpID());
		}
		int nextWP = 0;
		if (parentWP == null) {
			nextWP = wpmgr.getWorkPackageCountWithNull(editableProject.getProjectID()) + 1;
			setNewWPID("" + nextWP);
		} else {
			nextWP = wpmgr.getWorkPackageCount(editableProject.getProjectID(), getParentWP().getWpID()) + 1;
			setNewWPID(getParentWP().getWpID() + "." + nextWP);
		}
		return "createWP";
	}

	public String saveNewWP(WorkPackage w) {
		setProjectToAdd(editableProject);
		getWpToAdd().setWpID(getNewWPID());
		getWpToAdd().setParentWPID(newWPPID);
		getWpToAdd().setWorkingProject(projectToAdd);
		wpmgr.merge(w);
		if (getParentWP() == null) {
			wp = wpmgr.getParentProjectWorkPackagesNull(editableProject.getProjectID(), null);
		} else {
			wp = wpmgr.getParentProjectWorkPackages(editableProject.getProjectID(), getParentWP().getWpID());
		}
		return "wpDetails";
	}

	public String projectsLanding() {
		return "projectsLanding";
	}
}
