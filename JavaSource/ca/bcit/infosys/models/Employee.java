package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 * Class that contains information about Employee.
 * @author Calvin Yee
 */
@SessionScoped
@Named("employee")
public class Employee implements Serializable {
    /**
     * Whether the field is in editable mode or not.
     */
    private boolean editable;
    /** Employee ID. */
    private Integer userId;
    /**
     * Employee first name.
     */
    private String fname;
    /**
     * Employee last name.
     */
    private String lname;
    /**
     * Employee password.
     */
    private String password;
    /**
     * Employee login user name.
     */
    private String username;
    /**
     * Whether or not employee is supervisor.
     */
    private boolean isSupervisor;
    
    private int supervisorId;
    
    private Date startDate;
    
    private PayLevel paylevel;
    
    private Vacation vacation;
    
    

    public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public PayLevel getPaylevel() {
		return paylevel;
	}

	public void setPaylevel(PayLevel paylevel) {
		this.paylevel = paylevel;
	}

	public Vacation getVacation() {
		return vacation;
	}

	public void setVacation(Vacation vacation) {
		this.vacation = vacation;
	}

	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	/**
     * Gets the first name.
     * @return fname First name of employee
     */
    public String getFname() {
        return fname;
    }

    /**
     * Sets the first name.
     * @param name Name you want to set it to
     */
	public void setFname(String name) {
		fname = name;
	}

	/**
	 * Gets the last name.
	 * @return lname Last name of employee
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Sets the last name.
	 * @param name Name you want to set it to
	 */
	public void setLname(String name) {
		lname = name;
	}

	/**
	 * Gets whether the field is in editable mode.
	 * @return editable Is the field editable
	 */
	public boolean getEditable() {
		return editable;
	}

	/**
	 * Sets whether the fields are in editable mode.
	 * @param editable New editable boolean
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Gets the user id.
	 * @return userId ID of the employee
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * @param userId ID to be set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the password.
	 * @return password Password of the employee
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * @param password Password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 * @return username Username of the employee
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * @param username to be set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets if employee is supervisor or not.
	 * @return if the employee is a supervisor
	 */
    public boolean getIsSupervisor() {
        return isSupervisor;
    }

    /**
     * Sets if the employee is supervisor.
     * @param isSupervisor whether employee is supervisor or not
     */
    public void setIsSupervisor(boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

}