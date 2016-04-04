package ca.bcit.infosys.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * The persistent class for the Timesheet database table.
 * 
 */
@Entity
@Table(name="Timesheet")
//@NamedQuery(name="Timesheet.findAll", query="SELECT t FROM Timesheet t")
public class Timesheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TimesheetID")
	private int timesheetID;

	@Column(name="Approved")
	private boolean approved;

	@Column(name="EmployeeID")
	private int employeeID;

	@Temporal(TemporalType.DATE)
	@Column(name="StartDate")
	private Date startDate;

	@Column(name="Submitted")
	private boolean submitted;
	
	@Column(name="WeekNumber")
	private int weekNumber;

	//bi-directional many-to-one association to TimesheetRow
	@OneToMany(mappedBy="timesheet", cascade = {CascadeType.ALL})
	private List<TimesheetRow> timesheetRows;

	public Timesheet() {
	}

	public int getTimesheetID() {
		return this.timesheetID;
	}

	public void setTimesheetID(int timesheetID) {
		this.timesheetID = timesheetID;
	}

	public boolean getApproved() {
		return this.approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	

	public Date getStartDate() throws ParseException {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = new Date();
		String now = dateformat.format(date);
		startDate = dateformat.parse(now);
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean getSubmitted() {
		return this.submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	
	public int getWeekNumber() {
		return this.weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public List<TimesheetRow> getTimesheetRows() {
		return this.timesheetRows;
	}

	public void setTimesheetRows(List<TimesheetRow> timesheetRows) {
		this.timesheetRows = timesheetRows;
	}

	public TimesheetRow addTimesheetRow(TimesheetRow timesheetRow) {
		getTimesheetRows().add(timesheetRow);
		timesheetRow.setTimesheet(this);

		return timesheetRow;
	}

	public TimesheetRow removeTimesheetRow(TimesheetRow timesheetRow) {
		getTimesheetRows().remove(timesheetRow);
		timesheetRow.setTimesheet(null);

		return timesheetRow;
	}

}