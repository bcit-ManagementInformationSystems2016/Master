package ca.bcit.infosys.tests.beans;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

import ca.bcit.infosys.models.*;

public class TimesheetBeanTest {

	@Test
	public void testTimesheetGettersAndSetters() {
	
		// variables
		final int timesheetID = 1;
		final boolean approved = false;
		final int employeeID = 1;
		final Date startDate = new Date();
		final boolean submitted = false;
		final List<TimesheetRow> timesheetRows = new ArrayList<TimesheetRow>();
		
		// creating the timesheet object
		final Timesheet timesheet = new Timesheet();
		
		// test setters
		timesheet.setTimesheetID(timesheetID);
		timesheet.setApproved(approved);
		timesheet.setEmployeeID(employeeID);
		timesheet.setStartDate(startDate);
		timesheet.setSubmitted(submitted);
		timesheet.setTimesheetRows(timesheetRows);
		
		// actual results from getters
		final int timesheetIDActualResult = timesheet.getTimesheetID();
		final boolean approvedActualResult = timesheet.getApproved();
		final int employeeIDActualResult = timesheet.getEmployeeID();
		final Date startDateActualResult = timesheet.getStartDate();
		final boolean submittedActualResult = timesheet.getSubmitted();
		final List<TimesheetRow> timesheetRowsActualResult = timesheet.getTimesheetRows();
		
		// expected results
		final int timesheetIDExpectedResult = timesheetID;
		final boolean approvedExpectedResult = approved;
		final int employeeIDExpectedResult = employeeID;
		final Date startDateExpectedResult = startDate;
		final boolean submittedExpectedResult = submitted;
		final List<TimesheetRow> timesheetRowsExpectedResult = timesheetRows;
		
		TestCase.assertEquals("testTimesheetGettersAndSetters timesheetID FAILED", timesheetIDExpectedResult, timesheetIDActualResult);
		TestCase.assertEquals("testTimesheetGettersAndSetters approved FAILED", approvedExpectedResult, approvedActualResult);
		TestCase.assertEquals("testTimesheetGettersAndSetters employeeID FAILED", employeeIDExpectedResult, employeeIDActualResult);
		TestCase.assertEquals("testTimesheetGettersAndSetters startDate FAILED", startDateExpectedResult, startDateActualResult);
		TestCase.assertEquals("testTimesheetGettersAndSetters submitted FAILED", submittedExpectedResult, submittedActualResult);
		TestCase.assertEquals("testTimesheetGettersAndSetters timesheetRows FAILED", timesheetRowsExpectedResult, timesheetRowsActualResult);
	}
}
