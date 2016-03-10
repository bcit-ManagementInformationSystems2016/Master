package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model Class for Customer
 * @author StuArt
 *
 */

@Entity
@Table(name="Customer")
//@NamedQuery(name="Project.findAll", query="SELECT e FROM Projects e")
public class Customer implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CustomerID")
    private int customerID;

    @Column(name="ContactFName")
    private String contactFName;

    @Column(name="ContactLName")
    private String contactLName;
    
    @Column(name="CompanyName")
    private String companyName;

    @Column(name="Description")
    private String description;
    
    //bi-directional one-to-many association to Projects
    @OneToMany(mappedBy = "cust")
    private List<Project> projects;
    
    // Ctor
    public Customer() {}

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName){
        this.companyName = companyName; 
    }

    public String getContactFName(){
        return contactFName;
    }
    public void setContactFName(String contactFName) {
        this.contactFName = contactFName;
    }
    
    public String getContactLName(){
        return contactLName;
    }
    
    public void setContactLName(String contactLName) {
        this.contactLName = contactLName;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}