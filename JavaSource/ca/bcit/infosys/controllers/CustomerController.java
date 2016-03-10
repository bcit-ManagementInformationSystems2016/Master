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
    
    public Customer[] getAllCustomers() {
        System.out.println("test");
        return ctmgr.getAll();
    }
}
