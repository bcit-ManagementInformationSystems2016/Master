package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import ca.bcit.infosys.managers.TimesheetRowManager;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Timesheet;
import ca.bcit.infosys.models.TimesheetRow;


@Named("timesheet")
@SessionScoped
public class TimesheetController  implements Serializable {
    /** Manager from Product objects.*/
    @Inject private TimesheetManager timesheetManager;
    /** Manager from Product objects.*/
    @Inject private TimesheetRowManager timesheetRowManager;
    
    private TimesheetRow tsr = new TimesheetRow();
    private Timesheet ts;
    private static TimesheetRow[] rows;
    private static ArrayList<TimesheetRow> localRows;
    
    public TimesheetRow getTsr() {
        return tsr;
    }

    public void setTsr(TimesheetRow tsr) {
        this.tsr = tsr;
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
    
    public String editTsr(TimesheetRow tsr) {
        setTsr(tsr);
        System.out.println("Edit timesheetRow");
        return "edit";
    }
    public String updateTs(Timesheet ts){
        timesheetManager.merge(ts);
        ts = null;
        return "updated";
    }
    public String updateTsr(TimesheetRow tsr){
        timesheetRowManager.merge(tsr);
        tsr = null;
        return "updated";
    }
    public String createTs(Timesheet ts){
        timesheetManager.persist(ts);
        System.out.println("Created timesheet");
        return "created";
    }
    public String createTsr(TimesheetRow tsr){
        //timesheetRowManager.persist(tsr);
        localRows.add(tsr);
        TimesheetRow[] newRows = new TimesheetRow[localRows.size()];
        rows = localRows.toArray(newRows);
        System.out.println("Created timesheetRow");
        return "created";
    }
    
    public String deleteTsr(TimesheetRow tsr) {
        timesheetRowManager.remove(tsr);
        System.out.println("Delete timesheetRow ");
        //list.remove(tsr);
        return null;
    }
    
    public Timesheet[] getAllTimesheet() {
        return timesheetManager.getAll();
    }

    public TimesheetRow[] getAllTimesheetRow() {
    	// set the current timesheet to be the current timesheet of the employee
    	if (getTs() == null) {
	    	int empId = Login.currentID;
	    	setTs(timesheetManager.getTimesheetEmpId(empId));
    	}
    	
    	if (rows == null) {
    		Timesheet ts = getTs();
        	int timesheetID = ts.getTimesheetID();
    		TimesheetRow[] arr = timesheetRowManager.getRowsWithTimesheetId(timesheetID);
    		localRows = new ArrayList<TimesheetRow>(Arrays.asList(arr));
    		rows = arr;
    	}
        return rows;
    }
    
    public void resetTimesheet() {
    	for (int i = 0; i < rows.length; i++) {
    		TimesheetRow row = rows[i];
    		row.setWorkPackageID("");
    		row.setHoursSun(0);
    		row.setHoursMon(0);
    		row.setHoursTues(0);
    		row.setHoursWed(0);
    		row.setHoursThurs(0);
    		row.setHoursFri(0);
    		row.setHoursSat(0);
    	}
    }
    
   public double getAllTotalHours() {
	   double allHours = 0.0;
	   TimesheetRow[] rows = getAllTimesheetRow();

       System.out.println("rowslength: " + rows.length);
	   for (int i=0; i < rows.length; i++) {
		   allHours += rows[i].getTotalHours();
		   System.out.println("time: " + allHours);
	   }
	   return allHours;
   }

   public double getSunTotalHours() {
       double sunHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           sunHours += rows[i].getHoursSun();
       }
       return sunHours;
   }
   public double getTuesTotalHours() {
       double tuesHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           tuesHours += rows[i].getHoursTues();
       }
       return tuesHours;
   }
   public double getMonTotalHours() {
       double monHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           monHours += rows[i].getHoursMon();
       }
       return monHours;
   }
   public double getWedTotalHours() {
       double wedHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           wedHours += rows[i].getHoursWed();
       }
       return wedHours;
   }
   public double getThursTotalHours() {
       double thursHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           thursHours += rows[i].getHoursThurs();
       }
       return thursHours;
   }
   public double getFriTotalHours() {
       double friHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           friHours += rows[i].getHoursFri();
       }
       return friHours;
   }
   public double getSatTotalHours() {
       double satHours = 0.0;
       TimesheetRow[] rows = getAllTimesheetRow();
       for (int i=0; i < rows.length; i++) {
           satHours += rows[i].getHoursSat();
       }
       return satHours;
   }
   
   public int getWeekNumber() throws ParseException{
	   TimesheetRow[] rows = getAllTimesheetRow();
	   Date date = ts.getStartDate();
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(date);
	   int week = cal.get(Calendar.WEEK_OF_YEAR);
	   return week;
	   
   }
   
   public Date getWeekEnding() throws ParseException {
	   Date date = ts.getStartDate();
	   
//	   SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
//	   String weekEnd = sdf.format(date);
//	   Calendar c = Calendar.getInstance();
//	   c.setTime(sdf.parse(weekEnd));
//	   c.add(Calendar.DATE, 6);
	   return date;
   }
}
