package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.CustomerManager;
import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.PayLevelCostManager;
import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Customer;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.PayLevelCost;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;

@Named("customerController")
@ConversationScoped
public class CustomerController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
    private CustomerManager ctmgr;
    @Inject 
    private ProjectManager pMgr;
    @Inject
    private EmployeeManager eMgr;
    @Inject
    private WorkPackageManager wpMgr;
    @Inject
    private PayLevelManager plvlMgr;
    @Inject
    private PayLevelCostManager plcMgr;
    
    // Local variables
    private Customer editableCustomer;
    private Project newProject;
    private String projectManager;
    //private Project recentlyAddedProject;
    
    // GETTERS AND SETTERS
    public Customer getEditableCustomer() {
    	return editableCustomer;
    }
    public void setEditableCustomer(Customer editableCustomer) {
    	this.editableCustomer = editableCustomer;
    }
    public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public Project getNewProject() {
    	return newProject;
    }
    public void setNewProject(Project newProject) {
    	this.newProject = newProject;
    }
    
    
    //OTHER METHODS
        
    public Customer[] getAllCustomers() {
        return ctmgr.getAll();
    }
    
    public String viewAllCustomers() {
    	return "getCustomerAddress";
    }
    
    public String leaveAddressBook() {
    	return "adminLanding";
    }
    
    public String editCust(Customer cust) {
    	editableCustomer = cust;
    	return "editCustomer";
    }
    
    public String goBack() {
    	return "getCustomerAddress";
    }
    
    public String update(Customer c) {
    	ctmgr.merge(c);
    	return "getCustomerAddress";
    }
    
    public String createNewCust() {
    	editableCustomer = new Customer();
    	return "createCustomer";
    }
    
    public String save(Customer c) {
    	ctmgr.persist(c);
    	return "getCustomerAddress";
    }
    
    public String createNewProject(Customer cust) {
    	editableCustomer = cust;
    	newProject = new Project();
    	return "createProject";
    }
    
    public List<SelectItem> getEmployeeDropdown() {
    	return eMgr.getListOfAllEmployees();
    }
    
    public String createProject() {
    	int empID = new Integer(projectManager);
    	Employee e = eMgr.getTimesheetValidator(empID);
    	newProject.setCust(editableCustomer);
    	newProject.setProjectManager(e);
    	Project p = new Project();
    	p = newProject;
    	pMgr.persist(p);
    	int n = p.getProjectID();
    	WorkPackage wp = new WorkPackage();
    	Project wpProject = new Project();
    	wpProject = pMgr.find(n);
    	wp.setWorkingProject(wpProject);
    	wp.setParentWPID("");
    	wp.setWpID("0");
    	wp.setWpName(newProject.getProjectName());
    	wp.setDescription(newProject.getDescription());
    	wp.setResponsibleEngineerID(newProject.getProjectManager().getEmployeeID());
    	wp.setBudgetedDaysID(25);
    	wp.setRemainingDaysID(54);
    	wp.setTotalBudgetCost(0.0);
    	wp.setTotalBudgetDays(0.0);
    	wpMgr.merge(wp);
    	PayLevelCost plc = new PayLevelCost();
    	Project plcProject = new Project();
    	plcProject = pMgr.find(n);
    	plc.setProject(plcProject);
    	plc.setP1Cost(plvlMgr.getPayLevelInfo(1).getAvgPayRate());
    	plc.setP2Cost(plvlMgr.getPayLevelInfo(2).getAvgPayRate());
    	plc.setP3Cost(plvlMgr.getPayLevelInfo(3).getAvgPayRate());
    	plc.setP4Cost(plvlMgr.getPayLevelInfo(4).getAvgPayRate());
    	plc.setP5Cost(plvlMgr.getPayLevelInfo(5).getAvgPayRate());
    	plc.setP6Cost(plvlMgr.getPayLevelInfo(6).getAvgPayRate());
    	plcMgr.merge(plc);
    	return "getCustomerAddress";
    }
    
}
