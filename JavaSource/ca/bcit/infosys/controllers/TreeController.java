package ca.bcit.infosys.controllers;

import java.io.Serializable;

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
	
	// Other Functions
	public String testFunction() {
		setProjectTree(new TreeManagedBean());
		return "TreeTest";
	}
	
	public String viewProjectTree(Project p) {
		System.out.println("TC: 1");
		WorkPackage top = wpmgr.getTopWorkPackage(p.getProjectID());
		System.out.println("top = " + top.getWpName());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(p.getProjectID()));
		//selectedWP = top;
		return "TreeTest";
	}
	
	public String goBackToProject() {
		return "TreeTest";
	}
	
	public String leaveTreePage() {
		projectTree = null;
		return "showAllProjects";
	}
	
	public void showWorkPackageDetails() {
		System.out.println("TC: 2");
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			System.out.println("TC: not null");
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
			System.out.println("selectedWP = " + selectedWP.getWpName());
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
			return "TreeTest";
		}
	}
	
	public EmployeeWP[] getAssignedEmployees() {
		System.out.println("TC: 3");
		return empwpmgr.findAssignedEmployees(selectedWP.getWorkingProject().getProjectID(), selectedWP.getWpID());
	}
}
