package ca.bcit.infosys.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Credentials database table.
 * 
 */
@Entity
@Table(name="Credentials")
//@NamedQuery(name="Credential.findAll", query="SELECT c FROM Credential c")
public class Credential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CredentialsID")
	private int credentialsID;

	@Column(name="EmployeeID")
	private int employeeID;

	@Column(name="Password")
	private String password;

	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="CredentialsID")
	private Employee employee;

	public Credential() {
	}
	
	public Credential(int credID, int empID, String password) {
		this.credentialsID = credID;
		this.employeeID = empID;
		this.password = password;
	}

	public int getCredentialsID() {
		return this.credentialsID;
	}

	public void setCredentialsID(int credentialsID) {
		this.credentialsID = credentialsID;
	}

	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}