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
import ca.bcit.infosys.managers.PayLevelDaysManager;
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
	@Inject
	private PayLevelDaysManager pldmgr;
	
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
		assignedEmps = null;
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
		return "createNewWP";
	}
	
	public String saveNewWP(WorkPackage w) {
		setProjectToAdd(editableProject);
		getWpToAdd().setWpID(getNewWPID());
		getWpToAdd().setParentWPID(newWPPID);
		getWpToAdd().setWorkingProject(projectToAdd);
		pldmgr.persist(budget);
		remaining.setP1Day(budget.getP1Day());
		remaining.setP2Day(budget.getP2Day());
		remaining.setP3Day(budget.getP3Day());
		remaining.setP4Day(budget.getP4Day());
		remaining.setP5Day(budget.getP5Day());
		remaining.setP6Day(budget.getP6Day());
		pldmgr.persist(remaining);
		int n = new Integer(selectedEmployee);
		wpToAdd.setResponsibleEngineerID(n);
		wpToAdd.setRemainingDaysID(remaining.getPayLevelDaysID());
		wpToAdd.setBudgetedDaysID(budget.getPayLevelDaysID());
		double manDays = budget.getP1Day() + budget.getP2Day() + budget.getP3Day() + budget.getP4Day() + budget.getP5Day() + budget.getP6Day();
		double wpCost = (budget.getP1Day() * projectCost.getP1Cost()) + (budget.getP2Day() * projectCost.getP2Cost()) + (budget.getP3Day() * projectCost.getP3Cost()) + 
				(budget.getP4Day() * projectCost.getP4Cost()) + (budget.getP5Day() * projectCost.getP5Cost()) + (budget.getP6Day() * projectCost.getP6Cost());
		wpToAdd.setTotalBudgetCost(wpCost);
		wpToAdd.setTotalBudgetDays(manDays);
		wpmgr.merge(w);
		updateBudget(wpToAdd);
		WorkPackage top = wpmgr.getTopWorkPackage(editableProject.getProjectID());
		projectTree = new TreeManagedBean(top, wpmgr.getProjectWorkPackagesForTree(editableProject.getProjectID()));
		selectedWP = wpToAdd;
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
		budget = new PayLevelDays();
		remaining = new PayLevelDays();
		setNewWPPID(BRANCH_PARENT_ID);
		int nextWP = 0;
		nextWP = wpmgr.getWorkPackageCount(editableProject.getProjectID(), BRANCH_PARENT_ID) + 1;
		setNewWPID("" + nextWP);
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
	
	public void updateBudget(WorkPackage wp) {
		boolean isRoot = false;
		String currentWpID = wp.getParentWPID();
		WorkPackage parentWP;
		while (!isRoot) {
			double totalDays = 0;
			double totalCost = 0;
			WorkPackage[] childWPs;
			parentWP = wpmgr.getWorkPackage(editableProject.getProjectID(), currentWpID);
			childWPs = wpmgr.getParentProjectWorkPackages(editableProject.getProjectID(), parentWP.getWpID());
			for (int i = 0; i < childWPs.length; i++) {
				totalDays += childWPs[i].getTotalBudgetDays();
				totalCost += childWPs[i].getTotalBudgetCost();
			}
			parentWP.setTotalBudgetDays(totalDays);
			parentWP.setTotalBudgetCost(totalCost);
			wpmgr.merge(parentWP);
			if (!parentWP.getWpID().equals("0")) {
				currentWpID = parentWP.getParentWPID();
			} else {
				isRoot = true;
			}
		}
	}
	
	public String unassignEmployee(EmployeeWP ewp) {
		empwpmgr.remove(ewp.getWp(), ewp.getEmp());
		setAssignedEmps(empwpmgr.findAssignedEmployees(selectedWP.getWorkingProject().getProjectID(), selectedWP.getWpID()));
		return "viewProjectDetails";
	}
}
