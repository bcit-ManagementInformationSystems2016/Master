package ca.bcit.infosys.models;

import java.io.Serializable;

public class ProjectEmployeesKey implements Serializable {

	private static final long serialVersionUID = 1L;
	int projID;
    int empID;
    
    public ProjectEmployeesKey(int empID, int projID) {
    	this.projID = projID;
    	this.empID = empID;
    }
    
	public int getProjID() {
		return projID;
	}
	public void setProjID(int projID) {
		this.projID = projID;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	
}
