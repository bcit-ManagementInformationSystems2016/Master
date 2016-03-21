package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProjectEmployees")
@IdClass(ProjectEmployeesKey.class)
public class ProjectEmployees implements Serializable {

	private static final long serialVersionUID = 1L;

	//bi-directional one-to-many association to Employee
	// This is the one side
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EmployeeID")
    private Employee emp;
	
	//bi-directional one-to-many association to Projects
	// This is the one side
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ProjectID")
    private Project pro;

	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Project getPro() {
		return pro;
	}
	public void setPro(Project pro) {
		this.pro = pro;
	}
}
