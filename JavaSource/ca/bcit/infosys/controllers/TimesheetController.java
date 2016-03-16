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
import ca.bcit.infosys.managers.TimesheetRowManager;
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

    public TimesheetRow getTsr() {
        return tsr;
    }

    public void setTsr(TimesheetRow tsr) {
        this.tsr = tsr;
    }

    public String editTsr(TimesheetRow tsr) {
        setTsr(tsr);
        System.out.println("Edit timesheet");
        return "edit";
    }
    
    public String updateTsr(TimesheetRow tsr){
        timesheetRowManager.merge(tsr);
        tsr = null;
        return "updated";
    }
    
    public String createTsr(TimesheetRow tsr){
        timesheetRowManager.persist(tsr);
        return "created";
    }
    
    public String deleteTsr(TimesheetRow tsr) {
        timesheetRowManager.remove(tsr);
        //list.remove(tsr);
        return null;
    }
    
    public Timesheet[] getAllTimesheet() {
        return timesheetManager.getAll();
    }

    public TimesheetRow[] getAllTimesheetRow() {
        return timesheetRowManager.getAll();
    }
    
   public double getAllTotalHours() {
	   double allHours = 0.0;
	   TimesheetRow[] rows = timesheetRowManager.getAll();

       System.out.println("rowslength: " + rows.length);
	   for (int i=0; i < rows.length; i++) {
		   allHours += rows[i].getTotalHours();
		   System.out.println("time: " + allHours);
	   }
	   return allHours;
   }

   public double getSunTotalHours() {
       double sunHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           sunHours += rows[i].getHoursSun();
       }
       return sunHours;
   }
   public double getTuesTotalHours() {
       double tuesHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           tuesHours += rows[i].getHoursTues();
       }
       return tuesHours;
   }
   public double getMonTotalHours() {
       double monHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           monHours += rows[i].getHoursMon();
       }
       return monHours;
   }
   public double getWedTotalHours() {
       double wedHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           wedHours += rows[i].getHoursWed();
       }
       return wedHours;
   }
   public double getThursTotalHours() {
       double thursHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           thursHours += rows[i].getHoursThurs();
       }
       return thursHours;
   }
   public double getFriTotalHours() {
       double friHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           friHours += rows[i].getHoursFri();
       }
       return friHours;
   }
   public double getSatTotalHours() {
       double satHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getAll();
       for (int i=0; i < rows.length; i++) {
           satHours += rows[i].getHoursSat();
       }
       return satHours;
   }
}
