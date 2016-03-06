package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.controllers.EditableTimesheet;
import ca.bcit.infosys.managers.TimesheetManager;
import ca.bcit.infosys.models.Timesheet;


@Named("timesheet")
@SessionScoped
public class TimesheetController  implements Serializable {
    /** Manager from Product objects.*/
    @Inject private TimesheetManager timesheetManager;
    /** The conversation bean. */
    @Inject private Conversation conversation;
    /** The list of editableTimesheets in the database. */
    private List<EditableTimesheet> list;

    /**
     * Constructor for TimesheetListForm.
     * Initialize a TimesheetListForm with no rows and
     * for the current date.
     */
    private TimesheetController() {
        Calendar c = new GregorianCalendar();
        int currentDay = c.get(Calendar.DAY_OF_WEEK);
        int leftDays = Calendar.FRIDAY - currentDay;
        c.add(Calendar.DATE, leftDays);
        endWeek = c.getTime();
    }

    /** The date of Friday for the week of the timesheet. */
    private Date endWeek;


    /**
     * Gets the List of EditableTimesheet.
     * @return list
     */
    public List<EditableTimesheet> getList() {
       if (list == null) {
           refreshList();
       }
       return list;
   }
    
    /**
     * Refresh List of Timesheets.
     */
   private void refreshList() {
       Timesheet[] timesheets = timesheetManager.getAll();
       list = new ArrayList<EditableTimesheet>();
       for (int i = 0; i < timesheets.length; i++) {
           list.add(new EditableTimesheet(timesheets[i]));
       }
   }

   /**
    * Sets the List of editableTimesheet.
    * @param ep to be set
    */
   public  void setList(List<EditableTimesheet> ep) {
       list = ep;
   }

   /**
    * Adds Timesheet.
    */
   public String addRow() {
       list.add(new EditableTimesheet(new Timesheet()));
       return null;
   }

    /**
     * Delete the timesheet and return to the same page.
     * @param e the timesheet to be deleted
     * @return Navigation string
     */
    public String deleteRow(EditableTimesheet e) {
        timesheetManager.remove(e.getTimesheet());
        list.remove(e);
        return null;
    }

    /**
     * Saves updated timesheet's infomation.
     */
    public String save() {
        for (EditableTimesheet e : list) {
            if (e.isEditable()) {
                timesheetManager.merge(e.getTimesheet());
                e.setEditable(false);
            }
        }
        return null;
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
    
    public Timesheet[] getAllTimesheet() {
        return timesheetManager.getAll();
    }
}
