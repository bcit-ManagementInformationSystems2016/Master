package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.IdClass; 

@IdClass(ProjectEmployeesKey.class)
@SessionScoped
public class ProjectEmployeesKey implements Serializable {

	private static final long serialVersionUID = 1L;
	Project pro;
    Employee emp;
    
    public ProjectEmployeesKey() {

    }
    
    public ProjectEmployeesKey(Project p, Employee e) {
    	this.emp = e;
    	this.pro = p;
    }
    
	public Project getPro() {
		return pro;
	}
	public void setPro(Project project) {
		this.pro = project;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee employee) {
		this.emp = employee;
	}
	
	@Override
    public boolean equals(Object obj) {
		if (this == obj) 
	         return true; 
	      if (obj == null) 
	         return false; 
	      if (getClass() != obj.getClass()) 
	         return false; 
	      ProjectEmployeesKey other = (ProjectEmployeesKey) obj; 
	      if (pro == null) 
	      { 
	         if (other.pro != null) 
	            return false; 
	      } 
	      else if (!pro.equals(other.pro)) 
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
        result = prime * result + ((pro == null) ? 0 : pro.hashCode()); 
        result = prime * result + ((emp == null) ? 0 : emp.hashCode()); 
        return result; 
    }
	
}
