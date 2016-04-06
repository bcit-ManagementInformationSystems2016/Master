package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.TimesheetManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Timesheet;

@Named("timesheet")
@ConversationScoped

public class TimesheetController implements Serializable {
	private static final long serialVersionUID = 1L;

	Employee e;

	public void getUser(Employee emp) {
		System.out.println("GET USER HR CONTROLLER");
		e = emp;
	}

	/** Manager from Product objects. */
	@Inject
	private TimesheetManager timesheetManager;

	@Inject
	private EmployeeManager employeeManager;
	private Timesheet ts;
	private Employee em;

	private Employee[] employeeRows;
	private int countApproved;
	private static Timesheet[] allTimesheetRows;

	public Employee getEm() {
		return em;
	}

	public void setEm(Employee em) {
		this.em = em;
	}

	public Employee[] getAllEmployee() {
		return employeeRows;
	}

	public int getEmployeeId() {
		return TimsheetRowController.archivedTs.getEmployeeID();
	}

	public int getCurrentID() {
		return e.getEmployeeID();
	}

	public String getFirstName() {
		init();
		String firstName = null;
		Employee[] rows = getAllEmployee();
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].getEmployeeID() == e.getEmployeeID())
				firstName = rows[i].getFirstName();
		}
		return firstName;
	}

	public String getArchivedFirstName() {
		init();
		String firstName = null;
		Employee[] rows = getAllEmployee();
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].getEmployeeID() == TimsheetRowController.archivedTs.getEmployeeID())
				firstName = rows[i].getFirstName();
		}
		return firstName;
	}

	public String getLastName() {
		init();
		// TimsheetRowController.archivedTs.getEmployeeID()
		String lastName = null;
		Employee[] rows = getAllEmployee();
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].getEmployeeID() == e.getEmployeeID())
				lastName = rows[i].getLastName();
		}
		return lastName;
	}

	public String getArchivedLastName() {
		init();
		String lastName = null;
		Employee[] rows = getAllEmployee();
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].getEmployeeID() == TimsheetRowController.archivedTs.getEmployeeID())
				lastName = rows[i].getLastName();
		}
		return lastName;
	}

	/**
	 * Initialize the local data for the timesheet including the timesheet and
	 * all the timesheet rows associated with this timesheet.
	 * 
	 * This includes the local timesheet (ts), the local timesheet rows (arr)
	 * and the local arraylist so we can add and delete from rows more easily.
	 * 
	 * Best to convert all data to arraylist but will figure that out later
	 */
	public void init() {
		if (getTs() == null) {
			int empId = e.getEmployeeID();
			setTs(timesheetManager.getTimesheetEmpId(empId));
		}
		if (employeeRows == null) {
			employeeRows = employeeManager.getAll();
		}
	}

	public Timesheet getTs() {
		return ts;
	}

	public void setTs(Timesheet ts) {
		this.ts = ts;
	}

	public String editTs(Timesheet ts) {
		setTs(ts);
		System.out.println("Edit timesheet");
		return "edit";
	}

	public String updateTs(Timesheet ts) {
		timesheetManager.merge(ts);
		System.out.println("Update timesheet");
		ts = null;
		return "updated";
	}

	public String createTs(Timesheet ts) {
		timesheetManager.persist(ts);
		System.out.println("Created timesheet");
		return "created";
	}

	public String deleteTs(Timesheet ts) {
		timesheetManager.remove(ts);
		System.out.println("Delete timesheet ");
		// list.remove(ts);
		return null;
	}

	public Timesheet[] getAllTimesheet() {
		int empId = e.getEmployeeID();
		allTimesheetRows = timesheetManager.getArchivedTimesheetsWithEmpId(empId);
		return allTimesheetRows;
	}

	public int getCountApproved() {
		getAllTimesheet();
		return allTimesheetRows.length;
	}

	public void setCountApproved(int countApproved) {
		this.countApproved = countApproved;
	}

	public int getWeekNumber() throws ParseException {
		init();
		Date date = ts.getStartDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;

	}

	public int getArchivedWeekNumber() throws ParseException {
		init();
		return TimsheetRowController.archivedTs.getWeekNumber();

	}

	public Date getWeekEnding() throws ParseException {
		Date date = ts.getStartDate();

		// SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		// String weekEnd = sdf.format(date);
		// Calendar c = Calendar.getInstance();
		// c.setTime(sdf.parse(weekEnd));
		// c.add(Calendar.DATE, 6);
		return date;
	}

	public String timesheetLanding() {
		return "timesheetLanding";
	}

	public String goCreate() {
		return "create";
	}

	public String goArchive() {
		return "archive";
	}

}
