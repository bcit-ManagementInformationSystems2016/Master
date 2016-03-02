/**
 * 
 */
package ca.bcit.infosys.models;

import java.util.Date;

/**
 * @author nguyen
 *
 */
public class Timesheet {
	
	private int TimesheetID;
	
	private int EmpID;
	
	private boolean isApproved;
	
	private boolean isSubmitted;
	
	private Date startDate;

	/**
	 * @return the timesheetID
	 */
	public int getTimesheetID() {
		return TimesheetID;
	}

	/**
	 * @param timesheetID the timesheetID to set
	 */
	public void setTimesheetID(int timesheetID) {
		TimesheetID = timesheetID;
	}

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
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * @return the isSubmitted
	 */
	public boolean isSubmitted() {
		return isSubmitted;
	}

	/**
	 * @param isSubmitted the isSubmitted to set
	 */
	public void setSubmitted(boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
	

}
