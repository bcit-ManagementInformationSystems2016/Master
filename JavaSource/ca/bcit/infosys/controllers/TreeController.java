package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.TreeManagedBean;
import ca.bcit.infosys.models.WorkPackage;

@Named("treeController")
@SessionScoped
public class TreeController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private WorkPackageManager wpmgr;
	
	// variable used to display tree
	private static TreeManagedBean projectTree;
	private WorkPackage selectedWP;
	
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
		this.selectedWP = selectedWP;
	}
	
	// Other Functions
	public String testFunction() {
		setProjectTree(new TreeManagedBean());
		return "TreeTest";
	}
	
	public String viewProjectTree(Project p) {
		WorkPackage top = wpmgr.getTopWorkPackage(p.getProjectID());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(p.getProjectID()));
		selectedWP = top;
		return "TreeTest";
	}
	
	public String leaveTreePage() {
		projectTree = null;
		return "showAllProjects";
	}
	
	public void showWorkPackageDetails() {
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
		}
	}
}
