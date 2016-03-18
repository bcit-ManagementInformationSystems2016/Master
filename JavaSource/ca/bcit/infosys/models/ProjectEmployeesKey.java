package ca.bcit.infosys.models;

import java.io.Serializable;

public class ProjectEmployeesKey implements Serializable {

	private static final long serialVersionUID = 1L;
	Project project;
    Employee employee;
    
    public ProjectEmployeesKey(Project project, Employee employee) {
    	this.project = project;
    	this.employee = employee;
    }
    
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
    public boolean equals(Object obj) {
        if(obj instanceof ProjectEmployeesKey){
        	ProjectEmployeesKey carPk = (ProjectEmployeesKey) obj;
 
            if(!(carPk.getProject().equals(project))){
                return false;
            }
 
            if(!(carPk.getEmployee().equals(employee))){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return project.hashCode() + employee.hashCode();
    }
	
}
