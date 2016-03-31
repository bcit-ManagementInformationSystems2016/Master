package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.IdClass;

@IdClass(EmployeeWPKey.class)
@SessionScoped
public class EmployeeWPKey implements Serializable {

	// local variables
	private static final long serialVersionUID = 1L;
	WorkPackage wp;
	Employee emp;
	
	// ctors
	public EmployeeWPKey() {}
	public EmployeeWPKey(WorkPackage wp, Employee emp) {
		this.wp = wp;
		this.emp = emp;
	}
	
	// Getters and Setters
	public WorkPackage getWp() {
		return wp;
	}
	public void setWp(WorkPackage wp) {
		this.wp = wp;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	// Overridden methods
	@Override
    public boolean equals(Object obj) {
		if (this == obj) 
	         return true; 
	      if (obj == null) 
	         return false; 
	      if (getClass() != obj.getClass()) 
	         return false; 
	      EmployeeWPKey other = (EmployeeWPKey) obj; 
	      if (wp == null) 
	      { 
	         if (other.wp != null) 
	            return false; 
	      } 
	      else if (!wp.equals(other.wp)) 
	         return false; 
	      if (emp == null) 
	      { 
	         if (other.emp != null) 
	            return false; 
	      } 
	      else if (!emp.equals(other.emp)) 
	         return false; 
	      return true; 
    }
 
    @Override
    public int hashCode() {
    	final int prime = 31; 
        int result = 1; 
        result = prime * result + ((wp == null) ? 0 : wp.hashCode()); 
        result = prime * result + ((emp == null) ? 0 : emp.hashCode()); 
        return result; 
    }
}
