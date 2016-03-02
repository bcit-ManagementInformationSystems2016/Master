package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.inject.Named;

@Named 
public class Employee implements Serializable{
	
	private int EmpID;
	
	private int PayLevelID;
	
	private int SupervisorID;
	
	private int ValidatorID;
	
	private int RoleID;
	
	private Double Salary;
	
	private String FirstName;
	
	private String LastName;
	
	private Boolean isActive;

	/**
	 * @return the empID
	 */
	public int getEmpID() {
		return EmpID;
	}

	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(int empID) {
		EmpID = empID;
	}

	/**
	 * @return the payLevelID
	 */
	public int getPayLevelID() {
		return PayLevelID;
	}

	/**
	 * @param payLevelID the payLevelID to set
	 */
	public void setPayLevelID(int payLevelID) {
		PayLevelID = payLevelID;
	}

	/**
	 * @return the supervisorID
	 */
	public int getSupervisorID() {
		return SupervisorID;
	}

	/**
	 * @param supervisorID the supervisorID to set
	 */
	public void setSupervisorID(int supervisorID) {
		SupervisorID = supervisorID;
	}

	/**
	 * @return the validatorID
	 */
	public int getValidatorID() {
		return ValidatorID;
	}

	/**
	 * @param validatorID the validatorID to set
	 */
	public void setValidatorID(int validatorID) {
		ValidatorID = validatorID;
	}

	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return RoleID;
	}

	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return Salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		Salary = salary;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	

}
