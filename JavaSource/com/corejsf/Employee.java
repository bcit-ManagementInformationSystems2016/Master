package com.corejsf;

import java.io.Serializable;

//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
//@SessionScoped
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	 /** The employee's name. */
    private String name;
    /** The employee's employee number. */
    private int empNumber;
    /** The employee's login ID. */
    private String userName;
    
    private boolean editable;
    private boolean addEmp;


    /**
     * The no-argument constructor. Used to create new employees from within the
     * application.
     */
    public Employee() {
    }

    /**
     * The argument-containing constructor. Used to create the initial employees
     * who have access as well as the administrator.
     *
     * @param empName the name of the employee.
     * @param number the empNumber of the user.
     * @param id the loginID of the user.
     */
    public Employee(final String empName, final int number, final String id) {
        name = empName;
        empNumber = number;
        userName = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param empName the name to set
     */
    public void setName(final String empName) {
        name = empName;
    }

    /**
     * @return the empNumber
     */
    public int getEmpNumber() {
        return empNumber;
    }

    /**
     * @param number the empNumber to set
     */
    public void setEmpNumber(final int number) {
        empNumber = number;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param id the userName to set
     */
    public void setUserName(final String id) {
        userName = id;
    }

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isAddEmp() {
		return addEmp;
	}

	public void setAddEmp(boolean addEmp) {
		this.addEmp = addEmp;
	}
}
