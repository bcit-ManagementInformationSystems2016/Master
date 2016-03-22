package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.IdClass;

@IdClass(WorkPackageKey.class)
@SessionScoped
public class WorkPackageKey implements Serializable {

	// local variables
	private static final long serialVersionUID = 1L;
	Project workingProject;
	String wpID;

	// ctors
	public WorkPackageKey() {}
	public WorkPackageKey(Project p, String s) {
		workingProject = p;
		wpID = s;
	}
	
	// Getters and Setters
	public Project getWorkingProject() {
		return workingProject;
	}
	public void setWorkingProject(Project workingProject) {
		this.workingProject = workingProject;
	}
	public String getWpID() {
		return wpID;
	}
	public void setWpID(String wpID) {
		this.wpID = wpID;
	}
	
	// Overriden methods
	@Override
    public boolean equals(Object obj) {
		if (this == obj) 
	         return true; 
	      if (obj == null) 
	         return false; 
	      if (getClass() != obj.getClass()) 
	         return false; 
	      WorkPackageKey other = (WorkPackageKey) obj; 
	      if (workingProject == null) 
	      { 
	         if (other.workingProject != null) 
	            return false; 
	      } 
	      else if (!workingProject.equals(other.workingProject)) 
	         return false; 
	      if (wpID == null) 
	      { 
	         if (other.wpID != null) 
	            return false; 
	      } 
	      else if (!wpID.equals(other.wpID)) 
	         return false; 
	      return true; 
    }
 
    @Override
    public int hashCode() {
    	final int prime = 31; 
        int result = 1; 
        result = prime * result + ((workingProject == null) ? 0 : workingProject.hashCode()); 
        result = prime * result + ((wpID == null) ? 0 : wpID.hashCode()); 
        return result; 
    }
}
