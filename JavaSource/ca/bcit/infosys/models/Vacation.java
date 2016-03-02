/**
 * 
 */
package ca.bcit.infosys.models;

import java.util.Date;

/**
 * @author nguyen
 *
 */
public class Vacation {

	private int VacationID;
	
	private int EmpID;
	
	private Date RequestDate;
	
	private int VacationDaysLeft;

	/**
	 * @return the vacationID
	 */
	public int getVacationID() {
		return VacationID;
	}

	/**
	 * @param vacationID the vacationID to set
	 */
	public void setVacationID(int vacationID) {
		VacationID = vacationID;
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
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return RequestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		RequestDate = requestDate;
	}

	/**
	 * @return the vacationDaysLeft
	 */
	public int getVacationDaysLeft() {
		return VacationDaysLeft;
	}

	/**
	 * @param vacationDaysLeft the vacationDaysLeft to set
	 */
	public void setVacationDaysLeft(int vacationDaysLeft) {
		VacationDaysLeft = vacationDaysLeft;
	}
	
	
	
	

	
	
	
	
}
