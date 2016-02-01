package com.corejsf;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
//import javax.validation.constraints.AssertTrue;


import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped; 

@Named ("employeeDatabase")
@SessionScoped
public class EmployeeDatabase implements Serializable, EmployeeList {

	private static final long serialVersionUID = 1L;

	static int counter = 1;
	private String newEmployeeName = "";
	private String newEmployeeUsername = "";
	private String newEmployeePassword = "";
	private String newEmployeePasswordTwo = "";
	private boolean isValidLogin = true;
	private boolean canAddEmployees = true;
	private boolean isSuperUser = false;
	private Employee editableEmployee;
	private Employee loggedEmployee;
	
	//@Inject private TimesheetDatabase localTimesheetDatabase;

	@Inject private Credentials credentials;
	
	// HashMap to save all valid username and password combinations
	//private static final HashMap<String, String> userNamePassword = new HashMap<String, String>();
	
	// ArrayList to save all valid employees
	private ArrayList<Employee> employees;
	
	@Resource(mappedName = "java:jboss/datasources/cjsf_assigntwo")
	private DataSource ds;
	
	/**
	 * Default Constructor
	 */
	public EmployeeDatabase() {

	}
	
	
	// ----------------------- SQL Code -------------------------------
	
	public ArrayList<Employee> getEmployeeList() throws SQLException{
		if(ds == null){
			throw new SQLException("Can't get data source");
		}
		Connection con = ds.getConnection();
		if(con == null){
			throw new SQLException("Can't get connection");
		}
		PreparedStatement prepStmt = con.prepareStatement("SELECT empname, employeeid, username FROM Employee");
		
		ResultSet result = prepStmt.executeQuery();
		
		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		while(result.next()){
			Employee employee = new Employee();
			employee.setName(result.getString("empname"));
			employee.setEmpNumber(result.getInt("employeeid"));
			employee.setUserName(result.getString("username"));
			
			empList.add(employee);
		}
		
		if (prepStmt != null) {
			prepStmt.close();
		}
		if (con != null) {
			con.close();
		}
		
		return empList;
	}
	
	   /**
	    * @param name the name field of the employee
	    * @return the employees.
	    */
	   public Employee getEmployee(String name) {
		   Connection connection = null;
	       Statement stmt = null;
	       try {
	           try {
	               connection = ds.getConnection();
	               try {
	                   stmt = connection.createStatement();
	                   ResultSet result = stmt.executeQuery(
	                           "SELECT * FROM employee where username = '" + name + "'");
	                   if (result.next()) {
	                	   return new Employee(result.getString("empname"), 
	                			   				result.getInt("employeeid"),
	                			   				result.getString("username")
	                			   				);
	                   } else {
	                       return null;
	                   }
	               } finally {
	                   if (stmt != null) {
	                       stmt.close();
	                   }
	               }
	           } finally {
	               if (connection != null) {
	                   connection.close();
	               }
	           }
	       } catch (SQLException ex) {
	           System.out.println("Error in find " + name);
	           ex.printStackTrace();
	           return null;
	       }
	   }
	   
	    public String finalizePasswordChange() {
	    	if (newEmployeePassword.equals(newEmployeePasswordTwo)) {
	    		Connection connection = null;
	  	      	Statement stmt = null;
	  	      	try {
	  				try {
	  					connection = ds.getConnection();
	  					try {
	  						stmt = connection.createStatement();
	  						String updateString = String.format("UPDATE %s SET password = '%s' WHERE username = '%s'", 
	  								"credentials", newEmployeePassword, editableEmployee.getUserName());
	  						stmt.executeUpdate(updateString);
	  					} finally {
	  						if (stmt != null) {
	  							stmt.close();
	  						}
	  					}
	  				} finally {
	  					if (connection != null) {
	  						connection.close();
	  					}
	  				}
	  			} catch (SQLException ex) {
	  				System.out.println("Error in updating password");
	  				ex.printStackTrace();
	  			}
	    		newEmployeePassword = "";
	    		newEmployeePasswordTwo = "";
	    		return "passwordChanged";
	    	}
	    	return "passwords not the same";
	    }
	    
	    public boolean sqlVerify(Credentials credential) {
	        Connection connection = null;
	        Statement stmt = null;
	        try {
	            try {
	                connection = ds.getConnection();
	                try {
	                    stmt = connection.createStatement();
	                    ResultSet result = stmt.executeQuery(
	                            "SELECT * FROM credentials where username = '" + credential.getUserName() + "'");
	                    if (result.next()) {
	                 	   System.out.println("got results");
	                 	   if (credential.getPassword().equals(result.getString("password"))) {
	                 		   return true;
	                 	   } else {
	                 		   return false;
	                 	   }
	                    } else {
	                 	   System.out.println("got no results");
	                        return false;
	                    }
	                } finally {
	                    if (stmt != null) {
	                        stmt.close();
	                    }
	                }
	            } finally {
	                if (connection != null) {
	                    connection.close();
	                }
	            }
	        } catch (SQLException ex) {
	            System.out.println("Error in find " + credential.getUserName());
	            ex.printStackTrace();
	            return false;
	        }
	    }
	    
	    /**
		 * creates new employee and adds them to the ArrayList as well as
		 * adding their username and password to the HashMap
		 * @return string for navigation
		 */
		public void createNewEmployee(String username, String name) {
			Connection connection = null;
			Statement stmt = null;
			if (!(name.equals("")) && !(username.equals(""))) {
				try {
					try {
						System.out.println("about to get connection");
						connection = ds.getConnection();
						try {
							System.out.println("try statements");
							stmt = connection.createStatement();
							String insertString = String.format("INSERT INTO %s (empname, username) VALUES('%s', '%s')", 
									"employee", name, username);
							stmt.executeUpdate(insertString);
						} finally {
							if (stmt != null) {
								stmt.close();
							}
						}
					} finally {
						if (connection != null) {
							connection.close();
						}
					}
				} catch (SQLException ex) {
					System.out.println("Error in inserting new employee " + name);
					ex.printStackTrace();
				}
			}
		}
		
		/**
		 * creates new employee and adds them to the ArrayList as well as
		 * adding their username and password to the HashMap
		 * @return string for navigation
		 */
		public void createNewCredential(String username, String password) {
			Connection connection = null;
			Statement stmt = null;
			if (!(password.equals("")) && !(username.equals(""))) {
				try {
					try {
						connection = ds.getConnection();
						try {
							stmt = connection.createStatement();
							String insertString = String.format("INSERT INTO %s VALUES('%s', '%s')", 
									"credentials", username, password);
							stmt.executeUpdate(insertString);
						} finally {
							if (stmt != null) {
								stmt.close();
							}
						}
					} finally {
						if (connection != null) {
							connection.close();
						}
					}
				} catch (SQLException ex) {
					System.out.println("Error in inserting new credential " + username);
					ex.printStackTrace();
				}
			}
		}
		
	   /**
	    * Deletes the specified user from the collection of Users.
	    *
	    * @param userToDelete the user to delete.
	    */
	   public void deleteEmployeeSQL(int empID) {
		      Connection connection = null;
		      Statement stmt = null;
		      try {
					try {
						connection = ds.getConnection();
						try {
							stmt = connection.createStatement();
							String deleteString = String.format("DELETE FROM %s WHERE employeeid=%d", 
									"employee", empID);
							stmt.executeUpdate(deleteString);
						} finally {
							if (stmt != null) {
								stmt.close();
							}
						}
					} finally {
						if (connection != null) {
							connection.close();
						}
					}
				} catch (SQLException ex) {
					System.out.println("Error in deleting employee id: " + empID);
					ex.printStackTrace();
			}
	   	}
	   
	   public void deleteCredential(String username) {
		   Connection connection = null;
		      Statement stmt = null;
		      try {
					try {
						connection = ds.getConnection();
						try {
							stmt = connection.createStatement();
							stmt = connection.createStatement();
							String deleteString = String.format("DELETE FROM %s WHERE username='%s'", 
									"credentials", username);
							stmt.executeUpdate(deleteString);
						} finally {
							if (stmt != null) {
								stmt.close();
							}
						}
					} finally {
						if (connection != null) {
							connection.close();
						}
					}
				} catch (SQLException ex) {
					System.out.println("Error in deleting credential " + username);
					ex.printStackTrace();
			}
	   }
	   
	   public void updateEmployee(int empID, String name) {
		   Connection connection = null;
		      Statement stmt = null;
		      try {
					try {
						connection = ds.getConnection();
						try {
							stmt = connection.createStatement();
							stmt = connection.createStatement();
							String sqlStmt = String.format("UPDATE %s SET empname='%s' WHERE employeeid=%d", 
									"employee", name, empID);
							stmt.executeUpdate(sqlStmt);
						} finally {
							if (stmt != null) {
								stmt.close();
							}
						}
					} finally {
						if (connection != null) {
							connection.close();
						}
					}
				} catch (SQLException ex) {
					System.out.println("Error in updating employee " + name);
					ex.printStackTrace();
			}
	   }
	   
	   public ArrayList<Integer> getTimesheetIDs(int empID) {
		   Connection connection = null;
	      Statement stmt = null;
	      ArrayList<Integer> tsIDs = new ArrayList<Integer>();
	      try {
				try {
					connection = ds.getConnection();
					try {
						stmt = connection.createStatement();
						stmt = connection.createStatement();
						String sqlStmt = String.format("SELECT timesheetid FROM timesheet WHERE employeeid=%d", 
								 empID);
						ResultSet result = stmt.executeQuery(sqlStmt);
						while (result.next()) {
							tsIDs.add(result.getInt("timesheetid"));
						}
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}
				} finally {
					if (connection != null) {
						connection.close();
					}
				}
			} catch (SQLException ex) {
				System.out.println("Error in getting timesheets for employee " + empID);
				ex.printStackTrace();
		}
	    return tsIDs;
   }
	   
	   public void deleteTimesheetRowSQL(int tsID) {
	      Connection connection = null;
	      Statement stmt = null;
	      try {
				try {
					connection = ds.getConnection();
					try {
						stmt = connection.createStatement();
						String deleteString = String.format("DELETE FROM %s WHERE timesheetid=%d", 
								"timesheetrow", tsID);
						stmt.executeUpdate(deleteString);
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}
				} finally {
					if (connection != null) {
						connection.close();
					}
				}
			} catch (SQLException ex) {
				System.out.println("Error in deleting timesheet id: " + tsID);
				ex.printStackTrace();
		}
   	}
	   
	   public void deleteTimesheetSQL(int empID) {
	      Connection connection = null;
	      Statement stmt = null;
	      try {
				try {
					connection = ds.getConnection();
					try {
						stmt = connection.createStatement();
						String deleteString = String.format("DELETE FROM %s WHERE employeeid=%d", 
								"timesheet", empID);
						stmt.executeUpdate(deleteString);
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}
				} finally {
					if (connection != null) {
						connection.close();
					}
				}
			} catch (SQLException ex) {
				System.out.println("Error in deleting timesheet with emp id: " + empID);
				ex.printStackTrace();
		}
   	}

	
	
	// --------------------- GETTERS AND SETTERS ---------------------
	public Employee getEditableEmployee() {
		return editableEmployee;
	}


	public void setEditableEmployee(Employee editableEmployee) {
		this.editableEmployee = editableEmployee;
	}


	public String getNewEmployeePasswordTwo() {
		return newEmployeePasswordTwo;
	}


	public void setNewEmployeePasswordTwo(String newEmployeePasswordTwo) {
		this.newEmployeePasswordTwo = newEmployeePasswordTwo;
	}


	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	public void setNewEmployeeName(String newEmployeeName) {
		this.newEmployeeName = newEmployeeName;
	}

	public String getNewEmployeeUsername() {
		return newEmployeeUsername;
	}

	public void setNewEmployeeUsername(String newEmployeeUsername) {
		this.newEmployeeUsername = newEmployeeUsername;
	}

	public String getNewEmployeePassword() {
		return newEmployeePassword;
	}

	public void setNewEmployeePassword(String newEmployeePassword) {
		this.newEmployeePassword = newEmployeePassword;
	}
	
	public boolean getIsValidLogin() {
		return isValidLogin;
	}
	public void setIsValidLogin(boolean isValidLogin) {
		this.isValidLogin = isValidLogin;
	}   
   
   public boolean getIsSuperUser() {
		return isSuperUser;
	}

	public void setIsSuperUser(boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	public boolean isCanAddEmployees() {
		return canAddEmployees;
	}

	public void setCanAddEmployees(boolean canAddEmployees) {
		this.canAddEmployees = canAddEmployees;
	}	
	public Employee getLoggedEmployee() {
		return loggedEmployee;
	}

	public void setLoggedEmployee(Employee loggedEmployee) {
		this.loggedEmployee = loggedEmployee;
	}
	

	/*public TimesheetDatabase getLocalTimesheetDatabase() {
		return localTimesheetDatabase;
	}

	public void setLocalTimesheetDatabase(TimesheetDatabase timesheetDatabase) {
		this.localTimesheetDatabase = timesheetDatabase;
	} */

	/**
     * @return the ArrayList of users.
     */
   public ArrayList<Employee> getEmployees() { return employees;}
   
   public Credentials getCredentials() {
	   return credentials;
   }
   
   public void setCredentials(Credentials credentials) {
	   this.credentials = credentials;
   }
   
   
   // --------------- OTHER METHODS --------------------

   /**
    * @return the Map containing the valid (userName, password) combinations.
    */
   /*public Map<String, String> getLoginCombos() {
	   return userNamePassword;
   } */

   /**
    * @return the current user.
    */
   public Employee getCurrentEmployee() {
	   String username = credentials.getUserName();
	   Employee e = getEmployee(username);
	   return e;
   }

   /**
    * @return the administrator user object.
    */
   public String getAdministrator() {
	   String username = "";
	   Properties properties = new Properties();
       try
       {
           properties.load(EmployeeDatabase.class.getResourceAsStream("superUser.properties"));
       }
       catch (Exception e)
       {
           System.out.println("Properties file failed to load.");
           return null;
       }
       username = properties.getProperty("superUsername");
	   //String username = properties.getProperty("superUsername"); 
	   return username;
   }

   /**
    * Verifies that the loginID and password is a valid combination.
    *
    * @param credential (userName, Password) pair
    * @return true if it is, false if it is not.
    */
   /*@AssertTrue
   public boolean verifyUser(Credentials credential) {
	   if (!(userNamePassword.containsKey(credentials.getUserName()))){
		   return false;
	   }
	   if (!(userNamePassword.get(credential.getUserName()).equals(credential.getPassword())) ) {
		   return false;
	   }
	   return true;
   } */
   

   /**
    * Logs the user out of the system.
    *
    * @param employee the user to logout.
    * @return a String representing the login page.
    */
   public String logout(Employee employee) {
	   //credentials = null;
	   return "logout";
   }
   
   public String goToEmployeesPage() {
	   try {
		employees = getEmployeeList();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return "employees";
   }
   
   /**
    * Refreshes the employee table to update values
    * @return null
 	*/
   public String save() {
	      for (Employee employee : employees) employee.setEditable(false);
	      return null;
   }
   
   /**
    * Adds a new Employee to the collection of Employees.
    * @param newEmployee the employee to add to the collection
    */
   public void addEmployee(Employee newEmployee){
	   if (newEmployee != null) {
		   employees.add(newEmployee);
	   }
   }
   
   public void addEmp() {
	   employees.add(new Employee("", counter++, ""));
   }
   
	public String goToAddEmployeePage() {
	   if (isSuperUser) {
		   return "goToAddEmployee";
	   }
	   else {
		   setCanAddEmployees(false);
		   return "notValid";
	   }
   }

   public String validateLogin() {
	   if (sqlVerify(this.credentials)) {
		   isValidLogin = true;
		   String adminUsername = getAdministrator();
		   if (adminUsername.equals(credentials.getUserName())) {
			   isSuperUser = true;
		   }
		   setLoggedEmployee(getCurrentEmployee());
		   return "successfulLogin";
	   }
	   isValidLogin = false;
	   return "unsuccessfulLogin";
   }
   
   public String logoutOfSystem() {
	   Employee e = getCurrentEmployee();
	   String s = logout(e);
	   setIsSuperUser(false);
	   setCanAddEmployees(true);
	   setIsValidLogin(true);
	   return s;
   }
   
   public String newEmployee() {
	   	String name = getNewEmployeeName();
		String username = getNewEmployeeUsername();
		String password = getNewEmployeePassword();
		createNewCredential(username, password);
		createNewEmployee(username, name);		
		setNewEmployeeName("");
		setNewEmployeeUsername("");
		setNewEmployeePassword("");
		try {
			employees = getEmployeeList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   return "employeeAdded";
   }
   
   public String changeEmployeePassword(Employee e) {
	   setEditableEmployee(e);
	   return "changePassword";
   }
   
   public void deleteEmployee(Employee e) {
	   String username = e.getUserName();
	   int id = e.getEmpNumber();
	   ArrayList<Integer> tsIDs = getTimesheetIDs(id);
	   for (int i = 0; i < tsIDs.size(); i++) {
		   deleteTimesheetRowSQL(tsIDs.get(i));
	   }
	   deleteTimesheetSQL(id);
	   deleteEmployeeSQL(id);
	   deleteCredential(username);
	   try {
			employees = getEmployeeList();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
   }
   
   public String saveChanges(){
	   for (int i = 0; i < employees.size(); i++) {
		   updateEmployee(employees.get(i).getEmpNumber(), employees.get(i).getName());
	   }
	   return "changeEmployees";
   }

	@Override
	public Map<String, String> getLoginCombos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyUser(Credentials credential) {
		// TODO Auto-generated method stub
		return false;
	}
   
}