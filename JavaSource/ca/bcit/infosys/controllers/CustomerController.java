package ca.bcit.infosys.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.CustomerManager;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.models.Customer;
import ca.bcit.infosys.models.Project;

@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @Inject
    private CustomerManager ctmgr;
    
    // Local variables
    private Customer editableCustomer;
    
    // GETTERS AND SETTERS
    public Customer getEditableCustomer() {
    	return editableCustomer;
    }
    public void setEditableCustomer(Customer editableCustomer) {
    	this.editableCustomer = editableCustomer;
    }
    
    
    //OTHER METHODS
        
    public Customer[] getAllCustomers() {
        System.out.println("test");
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
    	return "createCustomer";
    }
    
    public String save(Customer c) {
    	Customer newCust = new Customer();
    	newCust.setCompanyName(editableCustomer.getCompanyName());
    	newCust.setContactFName(editableCustomer.getContactFName());
    	return "getCustomerAddress";
    }
    
}
