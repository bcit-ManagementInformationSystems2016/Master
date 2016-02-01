package com.corejsf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@Named("timesheetDatabase")
@SessionScoped
public class TimesheetDatabase implements TimesheetCollection, Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Timesheet> timesheets;
	private ArrayList<Timesheet> employeeTimesheets;
	private Timesheet viewableTimesheet = new Timesheet();
	private boolean editableTimesheet = false;
	private Employee loggedEmployee;
	private boolean createNewTimesheet = false;
	
	@Resource(mappedName = "java:jboss/datasources/cjsf_assigntwo")
	private DataSource ds;
	
	
	// Default Constructor
	public TimesheetDatabase() {

	}
	
	
	/**
     * @return all of the timesheets.
     */
    public List<Timesheet> getTimesheets() {
    	return timesheets;
    }
    
    // --------------------- SQL Methods -------------------------------------------

    /**
     * @param e the employee whose timesheets are returned
     * @return all of the timesheets for an employee.
     */
    public List<Timesheet> getTimesheets(Employee e) {
    	Connection connection = null;
        Statement stmt = null;
        ArrayList<Timesheet> ts = new ArrayList<Timesheet>();
        ArrayList<TimesheetRow> details = new ArrayList<TimesheetRow>();
        try {
            try {
                connection = ds.getConnection();
                try {
                    stmt = connection.createStatement();
                    String sqlStmt = String.format("SELECT * FROM %s WHERE employeeid = %d", 
            				"timesheet", e.getEmpNumber());
                    ResultSet result = stmt.executeQuery(sqlStmt);
                    while(result.next()){
            			Timesheet timesheet = new Timesheet();
            			timesheet.setEmployee(e);
            			timesheet.setEndWeek(result.getDate("enddate"));
            			timesheet.setTimesheetId(result.getInt("timesheetid"));
            			details = (ArrayList<TimesheetRow>) getTimesheetDetails(timesheet);
            			timesheet.setDetails(details);            			
            			ts.add(timesheet);
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
            System.out.println("Error in creating timesheets for " + e.getName());
            ex.printStackTrace();
        }
        return ts;
    }
    
    public List<TimesheetRow> getTimesheetDetails(Timesheet t) throws SQLException {
    	if(ds == null){
			throw new SQLException("Can't get data source");
		}
		Connection con = ds.getConnection();
		if(con == null){
			throw new SQLException("Can't get connection");
		}
		String sqlStmt = String.format("SELECT * FROM %s WHERE timesheetid = %d", 
					"timesheetrow", t.getTimesheetId());
		PreparedStatement prepStmt = con.prepareStatement(sqlStmt);
		
		ResultSet result = prepStmt.executeQuery();
		
		ArrayList<TimesheetRow> details = new ArrayList<TimesheetRow>();
		
		while(result.next()){
			TimesheetRow tr = new TimesheetRow();
			tr.setRowID(result.getInt("timesheetrowid"));
			tr.setProjectID(result.getInt("projectid"));
			tr.setWorkPackage(result.getString("wp"));
			tr.setNotes(result.getString("notes"));
			BigDecimal mon = result.getBigDecimal("monhours");
			BigDecimal tues = result.getBigDecimal("tueshours");
			BigDecimal wed = result.getBigDecimal("wedhours");
			BigDecimal thurs = result.getBigDecimal("thurshours");
			BigDecimal fri = result.getBigDecimal("frihours");
			BigDecimal sat = result.getBigDecimal("sathours");
			BigDecimal sun = result.getBigDecimal("sunhours");
			BigDecimal[] b = {mon, tues, wed, thurs, fri, sat, sun};
			tr.setHoursForWeek(b);			
			details.add(tr);
		}
		
		if (prepStmt != null) {
			prepStmt.close();
		}
		if (con != null) {
			con.close();
		}
		
		return details;
    }
    
    public void createNewTimesheetRowSQL(String wp, int projectId, String notes, BigDecimal[] b, int timesheetId) {
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = ds.getConnection();
				try {
					stmt = connection.createStatement();
					BigDecimal mon = b[0];
					BigDecimal tues = b[1];
					BigDecimal wed = b[2];
					BigDecimal thurs = b[3];
					BigDecimal fri = b[4];
					BigDecimal sat = b[5];
					BigDecimal sun = b[6];
					String insertString = String.format("INSERT INTO %s (wp, projectid, notes, monhours, tueshours, wedhours, thurshours, frihours, sathours, sunhours, timesheetid) "
							+ "VALUES('%s', %d, '%s', %f, %f, %f, %f, %f, %f, %f, %d)", 
							"timesheetrow", wp, projectId, notes, mon, tues, wed, thurs, fri, sat, sun, timesheetId);
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
			System.out.println("Error in creating new timesheetRow");
			ex.printStackTrace();
		}
	}
    
    public void deleteTimesheetRowSQL(int rowID) {
		  Connection connection = null;
	      Statement stmt = null;
	      try {
				try {
					connection = ds.getConnection();
					try {
						stmt = connection.createStatement();
						stmt = connection.createStatement();
						String deleteString = String.format("DELETE FROM %s WHERE timesheetrowid=%d", 
								"timesheetrow", rowID);
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
				System.out.println("Error in deleting timesheetrow " + rowID);
				ex.printStackTrace();
		}
   }
    
    public void createNewTimesheetSQL(String endDate, int empID) {
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = ds.getConnection();
				try {
					stmt = connection.createStatement();
					String insertString = String.format("INSERT INTO %s (enddate, employeeid) VALUES('%s', %d)", 
							"timesheet", endDate, empID);
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
			System.out.println("Error in creating new timesheet");
			ex.printStackTrace();
		}
	}
    
    public int getNewTimesheetID() {
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = ds.getConnection();
				try {
					stmt = connection.createStatement();
					String sqlStmt = "SELECT timesheetid FROM timesheet ORDER BY timesheetid DESC LIMIT 1;";
                    ResultSet result = stmt.executeQuery(sqlStmt);
                    if (result.next()) {
                    	return result.getInt("timesheetid");
                    } else {
                 	   System.out.println("got no results");
                 	   return -1;
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
			System.out.println("Error in creating new timesheet");
			ex.printStackTrace();
		}
		return -1;
	}
    
    public int getNewTimesheetRowID() {
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = ds.getConnection();
				try {
					stmt = connection.createStatement();
					String sqlStmt = "SELECT timesheetrowid FROM timesheetrow ORDER BY timesheetid DESC LIMIT 1;";
                    ResultSet result = stmt.executeQuery(sqlStmt);
                    if (result.next()) {
                    	return result.getInt("timesheetrowid");
                    } else {
                 	   System.out.println("got no results");
                 	   return -1;
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
			System.out.println("Error in getting new timesheetrow");
			ex.printStackTrace();
		}
		return -1;
	}
    
    public void updateTimesheetRow(int rowID, String wp, int proID, BigDecimal[] b, String notes) {
		  Connection connection = null;
	      Statement stmt = null;
	      BigDecimal mon = b[0];
	      BigDecimal tues = b[1];
	      BigDecimal wed = b[2];
	      BigDecimal thurs = b[3];
	      BigDecimal fri = b[4];
	      BigDecimal sat = b[5];
	      BigDecimal sun = b[6];
	      try {
				try {
					connection = ds.getConnection();
					try {
						stmt = connection.createStatement();
						stmt = connection.createStatement();
						String sqlStmt = String.format("UPDATE %s SET wp='%s', projectid=%d, monhours=%f, "
								+ "tueshours=%f, wedhours=%f, thurshours=%f, frihours=%f, sathours=%f, sunhours=%f, notes='%s' "
								+ "WHERE timesheetrowid=%d", 
								"timesheetrow", wp, proID, mon, tues, wed, thurs, fri, sat, sun, notes, rowID);
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
				System.out.println("Error in updating timesheet row " + rowID);
				ex.printStackTrace();
		}
   }
    

    /**
     * @param e the employee whose current timesheet is returned
     * @return the current timesheet for an employee.
     */
    public Timesheet getCurrentTimesheet(Employee e) {
    	ArrayList<Timesheet> employeeArray = (ArrayList<Timesheet>) getTimesheets(e);
    	return employeeArray.get(employeeArray.size()-1);    	
    }

    /**
     * Creates a Timesheet object and adds it to the collection.
     *
     * @return a String representing navigation to the newTimesheet page.
     */
    public String addTimesheet() {
    	Timesheet t = new Timesheet();
    	timesheets.add(t);
    	return "timesheetAdded";
    }

    // ---------------------- GETTERS AND SETTERS --------------------------------
    
	public Timesheet getViewableTimesheet() {
		return viewableTimesheet;
	}

	public void setViewableTimesheet(Timesheet viewableTimesheet) {
		this.viewableTimesheet = viewableTimesheet;
	}

	public boolean isEditableTimesheet() {
		return editableTimesheet;
	}

	public void setEditableTimesheet(boolean editableTimesheet) {
		this.editableTimesheet = editableTimesheet;
	}

	public Employee getLoggedEmployee() {
		return loggedEmployee;
	}

	public void setLoggedEmployee(Employee loggedEmployee) {
		this.loggedEmployee = loggedEmployee;
	}

	public boolean isCreateNewTimesheet() {
		return createNewTimesheet;
	}

	public void setCreateNewTimesheet(boolean createNewTimesheet) {
		this.createNewTimesheet = createNewTimesheet;
	}

	public ArrayList<Timesheet> getEmployeeTimesheets() {
		return employeeTimesheets;
	}

	public void setEmployeeTimesheets(List<Timesheet> list) {
		this.employeeTimesheets = (ArrayList<Timesheet>) list;
	}
	
	// ---------------------------- OTHER METHODS ------------------------------


	/**
	 * moves to the page where a user can view a single non-editable timesheet
	 * @param Timesheet t
	 * @return string for navigation
	 */
	public String viewTimesheet(Timesheet t) {
		setEditableTimesheet(false);
		setViewableTimesheet(t);
		setLoggedEmployee(viewableTimesheet.getEmployee());
		return "timesheet";
	}
	
	/**
	 * moves to the create new timesheet page
	 * @param Employee e
	 * @return string for navigation
	 */
	public String createTimesheet(Employee e) {
		setViewableTimesheet(new Timesheet());
		setEditableTimesheet(true);
		setCreateNewTimesheet(true);
		setLoggedEmployee(e);
		return "timesheet";
	}
	
	/**
	 * Saves the new Timesheet to the Timesheet array
	 * @param ArrayList<TimesheetRow> tr
	 * @return string for navigation
	 */
	public String saveTimesheet( ArrayList<TimesheetRow> tr) {
		Calendar c = new GregorianCalendar();
        int currentDay = c.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.FRIDAY - currentDay;
        c.add(Calendar.DATE, leftDays);
        Date endWeek = c.getTime();
		Timesheet ts = new Timesheet(loggedEmployee, endWeek, viewableTimesheet.getDetails());
		createNewTimesheetSQL(ts.getWeekEnding(), loggedEmployee.getEmpNumber());
		int tsID = getNewTimesheetID();
		for (int i = 0; i < ts.getDetails().size(); i++) {
			TimesheetRow tsr = ts.getDetails().get(i);
			createNewTimesheetRowSQL(tsr.getWorkPackage(), tsr.getProjectID(), tsr.getNotes(), tsr.getHoursForWeek(), tsID);
		}
		setCreateNewTimesheet(false);
		return "timesheetAdded";
	}
	
	/**
	 * moves to a page to edit a timesheet
	 * @param employee e
	 * @return string for navigation
	 */
	public String editTimesheet(Employee e) {
		setLoggedEmployee(e);
		setViewableTimesheet(getCurrentTimesheet(loggedEmployee));
		setEditableTimesheet(true);
		setCreateNewTimesheet(false);
		return "timesheet";
	}
	
	/**
	 * moves to the page where a user can view all previous timesheets
	 * @param employee e
	 * @return string for navigation
	 */
	public String viewTimesheets(Employee e) {
		setLoggedEmployee(e);
		setEmployeeTimesheets(getTimesheets(loggedEmployee));
		return "timesheetTable";
	}
	
	public void deleteTimesheetRow(TimesheetRow tr) {
		deleteTimesheetRowSQL(tr.getRowID());
		setViewableTimesheet(getCurrentTimesheet(loggedEmployee));
	}
	
	public String goBackFromTimesheets() {
		setEditableTimesheet(false);
		setCreateNewTimesheet(false);
		return "options";
	}
	
	public String updateTimesheetRow(Timesheet ts) {
		for (int i = 0; i < ts.getDetails().size(); i++) {
			TimesheetRow tr = ts.getDetails().get(i);
			String wp = tr.getWorkPackage();
			int proID = tr.getProjectID();
			BigDecimal[] b = tr.getHoursForWeek();
			int rowID = tr.getRowID();
			String notes = tr.getNotes();
			updateTimesheetRow(rowID, wp, proID, b, notes);
		}
		return "options";
	}
	
	public void addNewTimesheetRow(Timesheet ts) {
		BigDecimal[] b = {new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0),new BigDecimal(0)};
		createNewTimesheetRowSQL("", 0, "", b, ts.getTimesheetId());
		setViewableTimesheet(getCurrentTimesheet(loggedEmployee));		
	}
	
}
