package ca.bcit.infosys.timesheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.inject.Named;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.convert.FacesConverter;

/**
 * A class representing a single row of a Timesheet.
 *
 * @author Soran Shim
 * @author Calvin Yee
 * @author Bruce Link
 */
@SessionScoped
@Named("timesheetData")
@FacesConverter(value = "timesheetData")
public class TimesheetData implements Serializable {

    /** The ArrayList of all lists (i.e. rows) that the form contains. */
    private ArrayList<TimesheetRow> list;

    /** The date of Friday for the week of the timesheet. */
    private Date endWeek;

    /** The user associated with this timesheet. */
    @Inject private TimesheetRow user;

    /**
     * Constructor for Timesheet.
     * Initialize a Timesheet with no rows and
     * for the current date.
     */
    public TimesheetData() {
        list = new ArrayList<TimesheetRow>();
        this.initList();

        Calendar c = new GregorianCalendar();
        int currentDay = c.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.FRIDAY - currentDay;
        c.add(Calendar.DATE, leftDays);
        endWeek = c.getTime();
    }

    /**
     * @return the endWeek
     */
    public final Date getEndWeek() {
        return endWeek;
    }
    /**
     * @param end the endWeek. Must be a Friday
     */
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
    public final void setEndWeek(final Date end) {
        checkFriday(end);
        endWeek = end;
    }

    /**
     * @return the weeknumber of the timesheet
     */
    public final int getWeekNumber() {
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
    public final void setWeekNumber(final int weekNo, final int weekYear) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SATURDAY);
        c.setTime(endWeek);
        c.setWeekDate(weekYear, weekNo, Calendar.FRIDAY);
        endWeek = c.getTime();
    }

    /**
     * @return the endWeek as string
     */
    public final String getWeekEnding() {
        Calendar c = new GregorianCalendar();
        c.setTime(endWeek);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month += 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return month + "/" + day + "/" + year;
    }


    /**
     * Creates a Timesheet object.
     *
     * @return a String representing navigation to the viewTimesheet page.
     */
    public final String createTimesheetRow() {
        TimesheetRow newUser = new TimesheetRow();

        newUser.setProjectID(user.getProjectID());
        newUser.setWorkPackage(user.getWorkPackage());
        newUser.setSat(user.getSat());
        newUser.setSun(user.getSun());
        newUser.setMon(user.getMon());
        newUser.setTue(user.getThu());
        newUser.setWed(user.getWed());
        newUser.setThu(user.getThu());
        newUser.setFri(user.getFri());
        newUser.setNotes(user.getNotes());

        list.add(newUser);
        return "success";
    }

    /**
     * Adds a Timesheet object.
     *
     * @return a String representing navigation to the viewTimesheet page.
     */
    public final String  addAction() {
        TimesheetRow newUser = new TimesheetRow();

        newUser.setProjectID(user.getProjectID());
        newUser.setWorkPackage(user.getWorkPackage());
        newUser.setSat(user.getSat());
        newUser.setSun(user.getSun());
        newUser.setMon(user.getMon());
        newUser.setTue(user.getThu());
        newUser.setWed(user.getWed());
        newUser.setThu(user.getThu());
        newUser.setFri(user.getFri());
        newUser.setNotes(user.getNotes());

        list.add(newUser);
        return "success";
    }

    /**
     * Clear and Initialize a Timesheet with empty rows.
     * @return null
     */
    public final String newTimewsheet() {
        list.clear();
        this.initList();
        return null;
    }

    /**
     * Deletes the specified row from the timesheet.
     *
     * @param rowToDelete
     *            the row to remove from the timesheet.
     * @return null
     */
    public final String removeTimesheetRow(final TimesheetRow rowToDelete) {
        list.remove(rowToDelete);
        return null;
    }

    /**
     * Goes back page.
     *
     * @return "success"
     */
    public final String goBack() {
        return "success";
    }

    /**
     * Saves the specified row from the timesheet after edited.
     *
     *            the row to save from the timesheet.
     * @return null
     */
    public final String save() {
        for (TimesheetRow user : list) {
            user.setEditable(false);
        }
        return null;
    }

    /**
     * @param list the list to set
     */
    public final void setList(final ArrayList<TimesheetRow> list) {
        this.list = list;
    }

    /**
     * @return the list
     */
    public final ArrayList<TimesheetRow> getList() {
        return list;
    }

    /**
     * Initialize a Timesheet with 5 empty rows.
     */
    public final void initList() {
        list.add(new TimesheetRow());
        list.add(new TimesheetRow());
        list.add(new TimesheetRow());
        list.add(new TimesheetRow());
        list.add(new TimesheetRow());
    }

    /**
     * Calculates the total hours.
     *
     * @return total hours for timesheet.
     */
    public final double totalTotal() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getTotalDay();
        }
        return total;
    }

    /**
     * Calculates the total hours of saturday .
     *
     * @return total hours for saturday of week for timesheet.
     */
    public final double totalSat() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getSat();
        }
        return total;
    }

    /**
     * Calculates the total hours of sunday .
     *
     * @return total hours for sunday of week for timesheet.
     */
    public final double totalSun() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getSun();
        }
        return total;
    }

    /**
     * Calculates the total hours of monday .
     *
     * @return total hours for monday of week for timesheet.
     */
    public final double totalMon() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getMon();
        }
        return total;
    }

    /**
     * Calculates the total hours of tuesday .
     *
     * @return total hours for tuesday of week for timesheet.
     */
    public final double totalTue() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getTue();
        }
        return total;
    }

    /**
     * Calculates the total hours of wednesday .
     *
     * @return total hours for wednesday of week for timesheet.
     */
    public final double totalWed() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getWed();
        }
        return total;
    }

    /**
     * Calculates the total hours of thursday .
     *
     * @return total hours for thursday of week for timesheet.
     */
    public final double totalThu() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getThu();
        }
        return total;
    }

    /**
     * Calculates the total hours of friday .
     *
     * @return total hours for friday of week for timesheet.
     */
    public final double totalFri() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getFri();
        }
        return total;
    }

}
