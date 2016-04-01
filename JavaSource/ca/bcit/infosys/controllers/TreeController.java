package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.EmployeeWPManager;
import ca.bcit.infosys.managers.PayLevelCostManager;
import ca.bcit.infosys.managers.ProjectEmployeesManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.EmployeeWP;
import ca.bcit.infosys.models.PayLevelCost;
import ca.bcit.infosys.models.PayLevelDays;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.TreeManagedBean;
import ca.bcit.infosys.models.WorkPackage;

@Named("treeController")
@SessionScoped
public class TreeController implements Serializable {
	
	public static final String BRANCH_PARENT_ID = "0";

	private static final long serialVersionUID = 1L;
	@Inject
	private WorkPackageManager wpmgr;
	@Inject
	private EmployeeWPManager empwpmgr;
	@Inject
	private ProjectEmployeesManager pjtEmpMgr;
	@Inject
	private EmployeeManager empmgr;
	@Inject
	private PayLevelCostManager plcmgr;
	
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
	private PayLevelDays budget;
	private PayLevelDays remaining;
	private PayLevelCost projectCost;
	
	// variables used for assigning Employees to Workpackages
	private static List<SelectItem> availableEmployees;
	private String selectedEmployee;
	
	// variables used for caching employees
	private static HashMap<Integer, Employee> cachedEmps = new HashMap<Integer, Employee>();
	private Employee responsibleEngineer;
	
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
	public static List<SelectItem> getAvailableEmployees() {
		return availableEmployees;
	}
	public static void setAvailableEmployees(List<SelectItem> availableEmployees) {
		TreeController.availableEmployees = availableEmployees;
	}
	public String getSelectedEmployee() {
		return selectedEmployee;
	}
	public void setSelectedEmployee(String selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	public Employee getResponsibleEngineer() {
		return responsibleEngineer;
	}
	public void setResponsibleEngineer(Employee responsibleEngineer) {
		this.responsibleEngineer = responsibleEngineer;
	}
	public PayLevelDays getBudget() {
		return budget;
	}
	public void setBudget(PayLevelDays budget) {
		this.budget = budget;
	}
	public PayLevelDays getRemaining() {
		return remaining;
	}
	public void setRemaining(PayLevelDays remaining) {
		this.remaining = remaining;
	}
	public PayLevelCost getProjectCost() {
		return projectCost;
	}
	public void setProjectCost(PayLevelCost projectCost) {
		this.projectCost = projectCost;
	}
	
	
	// Other Functions
	public String testFunction() {
		setProjectTree(new TreeManagedBean());
		return "viewProjectDetails";
	}
	
	public String viewProjectTree(Project p) {
		setEditableProject(p);
		projectCost = plcmgr.getProjectCosts(p.getProjectID());
		WorkPackage top = wpmgr.getTopWorkPackage(p.getProjectID());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(p.getProjectID()));
		return "viewProjectDetails";
	}
	
	public String goBackToProject() {
		return "viewProjectDetails";
	}
	
	public String leaveTreePage() {
		projectTree = null;
		selectedWP = null;
		responsibleEngineer = null;
		return "showAllProjects";
	}
	
	public void showWorkPackageDetails() {
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
		} else {
			selectedWP = wpmgr.find(editableProject, "0");
		}
		if (!cachedEmps.containsKey(selectedWP.getResponsibleEngineerID())) {
			Employee tmp = empmgr.getTimesheetValidator(selectedWP.getResponsibleEngineerID());
			cachedEmps.put(selectedWP.getResponsibleEngineerID(), tmp);
		}
		setResponsibleEngineer(cachedEmps.get(selectedWP.getResponsibleEngineerID()));
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
//		projectCost = plcmgr.getProjectCosts(editableProject.getProjectID());
		if (wpToAdd != null) {
			wpToAdd = new WorkPackage();
		}
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
		} else {
			return "viewProjectDetails";
		}
		budget = new PayLevelDays();
		remaining = new PayLevelDays();
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
		setProjectToAdd(editableProject);
		getWpToAdd().setWpID(getNewWPID());
		getWpToAdd().setParentWPID(newWPPID);
		getWpToAdd().setWorkingProject(projectToAdd);
		wpmgr.merge(w);
		WorkPackage top = wpmgr.getTopWorkPackage(editableProject.getProjectID());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(editableProject.getProjectID()));
		return "viewProjectDetails";
	}
	
	public String cancelCreateWP() {
		setAvailableEmployees(null);
		return "viewProjectDetails";
	}
	
	public String createNewBranch() {
		if (wpToAdd != null) {
			wpToAdd = new WorkPackage();
		}
		setNewWPPID(BRANCH_PARENT_ID);
		int nextWP = 0;
		nextWP = wpmgr.getWorkPackageCount(editableProject.getProjectID(), BRANCH_PARENT_ID) + 1;
		setNewWPID("" + nextWP);
		System.out.println("editable project: " + editableProject.getProjectName());
		return "createNewWP";
	}
	
	public String assignEmpToWP() {
		if (projectTree.getSingleSelectedTreeNode() != null ) {
			selectedWP = (WorkPackage) projectTree.getSingleSelectedTreeNode().getData();
		} else {
			return "viewProjectDetails";
		}
		return "assignEmpToWP";
	}
	
	public List<SelectItem> getDropdownForWP() {
		if (selectedWP == null) {
			System.out.println("TreeController - There is no selected WP");
		}
		if (availableEmployees == null) {
			setAvailableEmployees(pjtEmpMgr.getAvailableEmployees(editableProject.getProjectID(), selectedWP.getWpID()));
		}
		return availableEmployees;
	}
	
	public List<SelectItem> getDropdownForNewWP() {
		if (availableEmployees == null) {
			setAvailableEmployees(pjtEmpMgr.getAllAvailableEmployees(editableProject.getProjectID()));
		}
		return availableEmployees;
	}
	
	public String assignEmployee() {
		int n = new Integer(selectedEmployee);
		EmployeeWP empWP = new EmployeeWP();
		Employee e = new Employee();
		e = empmgr.getTimesheetValidator(n);
		empWP.setEmp(e);
		empWP.setWp(selectedWP);
		empWP.setTotalHours(0.0);
		empwpmgr.merge(empWP);
		setAvailableEmployees(null);
		setAssignedEmps(empwpmgr.findAssignedEmployees(selectedWP.getWorkingProject().getProjectID(), selectedWP.getWpID()));
		return "viewProjectDetails";
	}
	
	public String cancelAssignment() {
		setAvailableEmployees(null);
		return "viewProjectDetails";
	}
}
