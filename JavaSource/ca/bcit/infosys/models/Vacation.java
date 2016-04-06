package ca.bcit.infosys.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vacation")
public class Vacation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VacationID")
	private int vacationID;
	
	@Column(name="EmployeeID")
	private int employeeID;
	
	@Column(name="RequestDate")
	private Date requestDate;
	
	@Column(name="VacationDaysLeft")
	private int vacationDaysLeft;
	
	@Column(name="isApproved")
	private boolean isApproved;

	public int getVacationID() {
		return vacationID;
	}

	public void setVacationID(int vacationID) {
		this.vacationID = vacationID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		
		this.requestDate = requestDate;
	}

	public int getVacationDaysLeft() {
		return vacationDaysLeft;
	}

	public void setVacationDaysLeft(int vacationDaysLeft) {
		this.vacationDaysLeft = vacationDaysLeft;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
