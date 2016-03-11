package ca.bcit.infosys.tests;

import org.junit.Test;

import ca.bcit.infosys.models.Timesheet;
import ca.bcit.infosys.models.TimesheetRow;
import junit.framework.TestCase;

public class TimesheetRowBeanTest {

	@Test
	public void testTimesheetRowGettersAndSetters() {
	
		// variables
		final int timesheetRowID = 1;
		final double hoursFri = 1.0;
		final double hoursMon = 1.0;
		final double hoursThurs = 1.0;
		final double hoursTues = 1.0;
		final double hoursWed = 1.0;
		final double hoursSat = 1.0;
		final double hoursSun = 1.0;
		final int timesheetID = 1;
		final String workPackageID = "workPackageID";
		final Timesheet timesheet = new Timesheet();
		
		// creating the timesheet row object
		final TimesheetRow timesheetRow = new TimesheetRow();
		
		// test setters
		timesheetRow.setTimesheetRowID(timesheetRowID);
		timesheetRow.setHoursFri(hoursFri);
		timesheetRow.setHoursMon(hoursMon);
		timesheetRow.setHoursThurs(hoursThurs);
		timesheetRow.setHoursTues(hoursTues);
		timesheetRow.setHoursWed(hoursWed);
		timesheetRow.setHoursSat(hoursSat);
		timesheetRow.setHoursSun(hoursSun);
		timesheetRow.setTimesheetID(timesheetID);
		timesheetRow.setWorkPackageID(workPackageID);
		timesheetRow.setTimesheet(timesheet);
		
		// actual results from getters
		final int timesheetRowIDActualResult = timesheetRow.getTimesheetID();
		final double hoursFriActualResult = timesheetRow.getHoursFri();
		final double hoursMonActualResult = timesheetRow.getHoursMon();
		final double hoursThursActualResult = timesheetRow.getHoursThurs();
		final double hoursTuesActualResult = timesheetRow.getHoursTues();
		final double hoursWedActualResult = timesheetRow.getHoursWed();
		final double hoursSatActualResult = timesheetRow.getHoursSat();
		final double hoursSunActualResult = timesheetRow.getHoursSun();
		final double timesheetIDActualResult = timesheetRow.getTimesheetID();
		final String workPackageIDActualResult = timesheetRow.getWorkPackageID();
		final Timesheet timesheetActualResult = timesheetRow.getTimesheet();
		
		// expected results
		final int timesheetRowIDExpectedResult = timesheetRowID;
		final double hoursFriExpectedResult = hoursFri;
		final double hoursMonExpectedResult = hoursMon;
		final double hoursThursExpectedResult = hoursThurs;
		final double hoursTuesExpectedResult = hoursTues;
		final double hoursWedExpectedResult = hoursWed;
		final double hoursSatExpectedResult = hoursSat;
		final double hoursSunExpectedResult = hoursSun;
		final double timesheetIDExpectedResult = timesheetID;
		final String workPackageIDExpectedResult = workPackageID;
		final Timesheet timesheetExpectedResult = timesheet;
		
		TestCase.assertEquals("testTimesheetRowGettersAndSetters timesheetRowID FAILED", timesheetRowIDExpectedResult, timesheetRowIDActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursFri FAILED", hoursFriExpectedResult, hoursFriActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursMon FAILED", hoursMonExpectedResult, hoursMonActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursThurs FAILED", hoursThursExpectedResult, hoursThursActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursTues FAILED", hoursTuesExpectedResult, hoursTuesActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursWed FAILED", hoursWedExpectedResult, hoursWedActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursSat FAILED", hoursSatExpectedResult, hoursSatActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters hoursSun FAILED", hoursSunExpectedResult, hoursSunActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters timesheetID FAILED", timesheetIDExpectedResult, timesheetIDActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters workPackageID FAILED", workPackageIDExpectedResult, workPackageIDActualResult);
		TestCase.assertEquals("testTimesheetRowGettersAndSetters timesheet FAILED", timesheetExpectedResult, timesheetActualResult);
	
	}

}
