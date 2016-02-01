package com.corejsf;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;


import java.io.Serializable;

import javax.inject.Named;
@Named
/**
 * A class representing a single Timesheet.
 *
 * @author Bruce Link
 */
public class Timesheet implements Serializable {
	/** Serial version number. */
    private static final long serialVersionUID = 2L;
    /** The user associated with this timesheet. */
    private Employee employee;
    /** The date of Friday for the week of the timesheet. */
    private Date endWeek;
    /** The ArrayList of all details (i.e. rows) that the form contains. */
    private List<TimesheetRow> details;
    /** The total number of overtime hours on the timesheet. */
    private BigDecimal overtime;
    /** The total number of flextime hours on the timesheet. */
    private BigDecimal flextime;
    private int timesheetId; 
    
    private BigDecimal[] sums;
    
    private BigDecimal sum;

    /** Number of days in a week. */
    public static final int DAYS_IN_WEEK = 7;

    /** Number of hours in a day. */
    public static final BigDecimal HOURS_IN_DAY =
            new BigDecimal(24.0).setScale(1, BigDecimal.ROUND_HALF_UP);

    /** Full work week */
    public static final BigDecimal FULL_WORK_WEEK =
            new BigDecimal(40.0).setScale(1, BigDecimal.ROUND_HALF_UP);
    /**
     * Constructor for Timesheet.
     * Initialize a Timesheet with no rows and
     * for the current date.
     */
    public Timesheet() {
        details = new ArrayList<TimesheetRow>(Arrays.asList(
	        new TimesheetRow(),
	        new TimesheetRow(),
	        new TimesheetRow(),
	        new TimesheetRow(),
	        new TimesheetRow()
        ));

        Calendar c = new GregorianCalendar();
        int currentDay = c.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.FRIDAY - currentDay;
        c.add(Calendar.DATE, leftDays);
        endWeek = c.getTime();
    }

    /**
     * Creates a Timesheet object with all fields set. Used to create sample
     * data.
     * @param user The owner of the timesheet
     * @param end The date of the end of the week for the timesheet (Friday)
     * @param charges The detailed hours charged for the week for this timesheet
     */
    public Timesheet(final Employee user, final Date end,
            final List<TimesheetRow> charges) {
        employee = user;
        checkFriday(end);
        endWeek = end;
        details = charges;
    }

    /**
     * @return the employee.
     */
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee e) {
    	employee = e;
    }

    /**
     * @return the endWeek
     */
    public Date getEndWeek() {
        return endWeek;
    }

    private void checkFriday(final Date end) {
        Calendar c = new GregorianCalendar();
        c.setTime(end);
        int currentDay = c.get(Calendar.DAY_OF_WEEK);
        if (currentDay != Calendar.FRIDAY) {
            throw new IllegalArgumentException("EndWeek must be a Friday");
        }

    }
    /**
     * @param end the endWeek to set. Must be a Friday
     */
    public void setEndWeek(final Date end) {
        checkFriday(end);
        endWeek = end;
    }

    /**
     * @return the weeknumber of the timesheet
     */
    public int getWeekNumber() {
        Calendar c = new GregorianCalendar();
        c.setTime(endWeek);
        c.setFirstDayOfWeek(Calendar.SATURDAY);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Sets the end of week based on the week number.
     *
     * @param weekNo the week number of the timesheet week
     * @param weekYear the year of the timesheet
     */
    public void setWeekNumber(final int weekNo, final int weekYear) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SATURDAY);
        c.setTime(endWeek);
        c.setWeekDate(weekYear, weekNo, Calendar.FRIDAY);
        endWeek = c.getTime();
    }

    /**
     * @return the endWeek as string
     */
    public String getWeekEnding() {
        Calendar c = new GregorianCalendar();
        c.setTime(endWeek);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month += 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + "/" + month + "/" + day;
    }

    /**
     * @return the details
     */
    public List<TimesheetRow> getDetails() {
        return details;
    }

    /**
     * Sets the details of the timesheet.
     *
     * @param newDetails new weekly charges to set
     */
    public void setDetails(final ArrayList<TimesheetRow> newDetails) {
        details = newDetails;
    }

    /**
     * @return the overtime
     */
    public BigDecimal getOvertime() {
        return overtime;
    }

    /**
     * @param ot the overtime to set
     */
    public void setOvertime(final BigDecimal ot) {
        overtime = ot.setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @return the flextime
     */
    public BigDecimal getFlextime() {
        return flextime;
    }
    
    public int getTimesheetId() {
    	return timesheetId;
    }
    
    public void setTimesheetId(int timesheetId) {
    	this.timesheetId = timesheetId;
    }

    /**
     * @param flex the flextime to set
     */
    public void setFlextime(final BigDecimal flex) {
        flextime = flex.setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calculates the total hours.
     *
     * @return total hours for timesheet.
     */
    public BigDecimal getTotalHours() {
        BigDecimal sum = BigDecimal.ZERO;
        for (TimesheetRow row : details) {
            sum = sum.add(row.getSum());
        }
        return sum;
    }

    /**
     * Calculates the daily hours.
     *
     * @return array of total hours for each day of week for timesheet.
     */
    public BigDecimal[] getSums() {
        sums = new BigDecimal[DAYS_IN_WEEK];
        for (TimesheetRow day : details) {
            BigDecimal[] hours = day.getHoursForWeek();
            for (int i = 0; i < DAYS_IN_WEEK; i++) {
                if (hours[i] != null) {
                    if (sums[i] == null) {
                        sums[i] = hours[i];
                    } else {
                        sums[i] = sums[i].add(hours[i]);
                    }
                }
            }
        }
        return sums;
    }
    
    public void getSums(BigDecimal[] value){
    	this.sums = value;
    }
    
    /**
     * @return the total sum
     */
    public BigDecimal getSum() {
    	sum = BigDecimal.ZERO;
    	for(TimesheetRow total : details){
    		BigDecimal hours = total.getSum();
			if (hours != null) {
                if (sum == null) {
                    sum = hours;
                } else {
                    sum = sum.add(hours);
                }
            }
    	}
		return sum;
    }
    
    public void setSum(BigDecimal value){
    	this.sum = value;
    }
    

    /**
     * Checks to see if timesheet total nets 40 hours.
     * @return true if FULL_WORK_WEEK == hours -flextime - overtime
     */
    public boolean isValid() {
        BigDecimal net = getTotalHours();
        if (overtime != null) {
            net = net.subtract(overtime);
        }
        if (flextime != null) {
            net = net.subtract(flextime);
        }
        return net.equals(FULL_WORK_WEEK);
    }

    /**
     * Deletes the specified row from the timesheet.
     *
     * @param rowToRemove
     *            the row to remove from the timesheet.
     */
    public void deleteRow(final TimesheetRow rowToRemove) {
        details.remove(rowToRemove);
    }

    /**
     * Add an empty to to the timesheet.
     */
    public void addRow() {
        details.add(new TimesheetRow());
    }
	
    /**
     * @return to current page but refresh the values
     */
    public String save() {
	      for (TimesheetRow rows : details) rows.setEditable(false);
	      return null;
    }

}
