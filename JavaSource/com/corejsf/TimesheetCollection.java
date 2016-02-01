package com.corejsf;

import java.io.Serializable;
import java.util.List;

//import ca.bcit.infosys.employee.Employee;

/**
 * A interface for accessing all existing Timesheets.
 *
 * @author Bruce Link
 *
 */
public interface TimesheetCollection extends Serializable {
    /**
     * @return all of the timesheets.
     */
    List<Timesheet> getTimesheets();

    /**
     * @param e the employee whose timesheets are returned
     * @return all of the timesheets for an employee.
     */
    List<Timesheet> getTimesheets(Employee e);

    /**
     * @param e teh employee whose current timesheet is returned
     * @return the current timesheet for an employee.
     */
    Timesheet getCurrentTimesheet(Employee e);

    /**
     * Creates a Timesheet object and adds it to the collection.
     *
     * @return a String representing navigation to the newTimesheet page.
     */
    String addTimesheet();
}
