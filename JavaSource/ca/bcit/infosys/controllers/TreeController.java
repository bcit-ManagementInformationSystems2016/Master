package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeWPManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.EmployeeWP;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.TreeManagedBean;
import ca.bcit.infosys.models.WorkPackage;

@Named("treeController")
@SessionScoped
public class TreeController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private WorkPackageManager wpmgr;
	@Inject
	private EmployeeWPManager empwpmgr;
	
	// variable used to display tree
	private static TreeManagedBean projectTree;
	private static WorkPackage selectedWP;
	private EmployeeWP[] assignedEmps;
	private Project editableProject;
	
	// variables used for creating new Work Package
	private WorkPackage wpToAdd = new WorkPackage();
	private Project projectToAdd = new Project();
	private String newWPID;
	private String newWPPID;
	
	// Getters and Setters
	public void setProjectTree(TreeManagedBean projectTree) {
		TreeController.projectTree = projectTree;
	}
	public TreeManagedBean getProjectTree() {
		return projectTree;
	}
	public WorkPackage getSelectedWP() {
		return selectedWP;
	}
	public void setSelectedWP(WorkPackage selectedWP) {
		TreeController.selectedWP = selectedWP;
	}
	public void setAssignedEmps(EmployeeWP[] assignedEmps) {
		this.assignedEmps = assignedEmps;
	}
	public EmployeeWP[] getAssignedEmps() {
		return assignedEmps;
	}
	public Project getEditableProject() {
		return editableProject;
	}
	public void setEditableProject(Project editableProject) {
		this.editableProject = editableProject;
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
	
	
	// Other Functions
	public String testFunction() {
		setProjectTree(new TreeManagedBean());
		return "viewProjectDetails";
	}
	
	public String viewProjectTree(Project p) {
		setEditableProject(p);
		WorkPackage top = wpmgr.getTopWorkPackage(p.getProjectID());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(p.getProjectID()));
		return "viewProjectDetails";
	}
	
	public String goBackToProject() {
		return "viewProjectDetails";
	}
	
	public String leaveTreePage() {
		projectTree = null;
		return "showAllProjects";
	}
	
	public void showWorkPackageDetails() {
		System.out.println("TC: 2");
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
		}
		//EmployeeWP[] empsOnWP = empwpmgr.findAssignedEmployees(selectedWP.getWorkingProject().getProjectID(), selectedWP.getWpID());
		setAssignedEmps(empwpmgr.findAssignedEmployees(selectedWP.getWorkingProject().getProjectID(), selectedWP.getWpID()));
	}
	
	public String viewAssignedEmployeesForWP() {
		if (selectedWP != null) {
			System.out.println("TC: 4");
			return "listEmpsForWorkPackage";
		}
		else {
			return "viewProjectDetails";
		}
	}
	
	public EmployeeWP[] getAssignedEmployees() {
		System.out.println("TC: 3");
		return empwpmgr.findAssignedEmployees(selectedWP.getWorkingProject().getProjectID(), selectedWP.getWpID());
	}
	
	public String createNewWorkPackage() {
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
		}
		setNewWPPID(selectedWP.getWpID());
		int nextWP = 0;
		nextWP = wpmgr.getWorkPackageCount(editableProject.getProjectID(), selectedWP.getWpID()) + 1;
		if (selectedWP.getWpID().equals('0')) {
			setNewWPID("" + nextWP);
		} else {
			setNewWPID(selectedWP.getWpID() + "." + nextWP);
		}
		System.out.println("editable project: " + editableProject.getProjectName());
		return "createNewWP";
	}
	
	public String saveNewWP(WorkPackage w) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date date = new Date();
		setProjectToAdd(editableProject);
		getWpToAdd().setWpID(getNewWPID());
		getWpToAdd().setParentWPID(newWPPID);
		getWpToAdd().setWorkingProject(projectToAdd);
		getWpToAdd().setActualStart(date);
		getWpToAdd().setEstimatedEnd(date);
		getWpToAdd().setEstimatedStart(date);
		wpmgr.merge(w);
		WorkPackage top = wpmgr.getTopWorkPackage(editableProject.getProjectID());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(editableProject.getProjectID()));
		return "viewProjectDetails";
	}
	
	public String cancelCreateWP() {
		return "viewProjectDetails";
	}
}
