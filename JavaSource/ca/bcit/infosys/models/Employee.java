package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the Employees database table.
 * 
 */
@Entity
@Table(name = "Employees")
//@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeID")
	private int employeeID;

	@Column(name = "FirstName")
	private String firstName;

	@Column
	private boolean isActive;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "ValidatorID")
	private int validatorID;

	@Column(name = "Salary")
	private double salary;

	@Column(name = "PayLevelID")
	private int payLevelID;

	@Column(name = "SupervisorID")
	private int supervisorID;

	@Column(name = "RoleID")
	private int roleID;

	// bi-directional one-to-one association to Credential
	@OneToOne(mappedBy = "employee")
	private Credential credential;

	// used for one-to-many mapping between projects and project managers
	@OneToMany(mappedBy = "projectManager")
	private List<Project> projectManagers;

	public Employee() {
	}

	
	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getValidatorID() {
		return validatorID;
	}

	public void setValidatorID(int validatorID) {
		this.validatorID = validatorID;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Credential getCredential() {
		return this.credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public int getPayLevelID() {
		return payLevelID;
	}

	public void setPayLevelID(int payLevelID) {
		this.payLevelID = payLevelID;
	}

	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		this.supervisorID = supervisorID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public List<Project> getProjectManagers() {
		return projectManagers;
	}

	public void setProjectManagers(List<Project> projectManagers) {
		this.projectManagers = projectManagers;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}