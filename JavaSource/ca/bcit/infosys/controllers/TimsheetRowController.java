package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.TimesheetManager;
import ca.bcit.infosys.managers.TimesheetRowManager;
import ca.bcit.infosys.models.Timesheet;
import ca.bcit.infosys.models.TimesheetRow;


@Named("timesheetRow")
@SessionScoped
public class TimsheetRowController implements Serializable {
    /** Manager from Product objects.*/
    @Inject private TimesheetManager timesheetManager;
    /** Manager from Product objects.*/
    @Inject private TimesheetRowManager timesheetRowManager;
    
    private TimesheetRow tsr = new TimesheetRow();
    private Timesheet ts;
    private static ArrayList<TimesheetRow> localRows;
    private static ArrayList<TimesheetRow> databaseRows;
    private static int timesheetRowId = 13123;
    
    public TimesheetRow getTsr() {
        return tsr;
    }
    public Timesheet getTs() {
        return ts;
    }

    public void setTs(Timesheet ts) {
        this.ts = ts;
    }    
    /**
     * Initialize the local data for the timesheet including the timesheet
     * and all the timesheet rows associated with this timesheet.
     * 
     * This includes the local timesheet (ts), the local timesheet rows (arr)
     * and the local arraylist so we can add and delete from rows more easily.
     * 
     * Best to convert all data to arraylist but will figure that out later
     */

    public String editTsr(TimesheetRow tsr) {
        setTsr(tsr);
        System.out.println("Edit timesheetRow");
        return "edit";
    }
    
    public void init() {
        if (getTs() == null) {
            int empId = Login.currentID;
            setTs(timesheetManager.getTimesheetEmpId(empId));
        }
        if (localRows == null) {
            Timesheet ts = getTs();
            int timesheetID = ts.getTimesheetID();
            TimesheetRow[] arr = timesheetRowManager.getRowsWithTimesheetId(timesheetID);
            localRows = new ArrayList<TimesheetRow>(Arrays.asList(arr));
            databaseRows = new ArrayList<TimesheetRow>(Arrays.asList(arr));
            for (int i = 0; i < databaseRows.size(); i++) {
                databaseRows.get(i).setStatus("old");
            }
        }
    }

    public void setTsr(TimesheetRow tsr) {
        this.tsr = tsr;
    }

   
    public String updateTsr(TimesheetRow tsr){
        for (int i = 0; i < localRows.size(); i++) {
            if (localRows.get(i).getTimesheetRowID() == tsr.getTimesheetRowID()) {
                localRows.set(i, tsr);
            }
        }
        for (int i = 0; i < databaseRows.size(); i++) {
            if (localRows.get(i).getTimesheetRowID() == tsr.getTimesheetRowID()) {
                databaseRows.set(i, tsr);
                if (!(tsr.getStatus() == "new")) {
                    System.out.println("set status to old editted");
                    databaseRows.get(i).setStatus("old-editted");
                }
            }
        }
        System.out.println("Updated timesheetRow");
        return "updated";
    }
    
    public String createTsr(TimesheetRow tsr){
        //timesheetRowManager.persist(tsr);
    	TimesheetRow newTsr = new TimesheetRow();
    	
    	timesheetRowId++;
    	newTsr.setTimesheetRowID(timesheetRowId);
    	newTsr.setTimesheetID(getTs().getTimesheetID());
    	newTsr.setHoursFri(tsr.getHoursFri());
    	newTsr.setHoursMon(tsr.getHoursMon());
    	newTsr.setHoursSat(tsr.getHoursSat());
    	newTsr.setHoursSun(tsr.getHoursSun());
    	newTsr.setHoursThurs(tsr.getHoursThurs());
    	newTsr.setHoursTues(tsr.getHoursTues());
    	newTsr.setHoursWed(tsr.getHoursWed());
    	newTsr.setWorkPackageID(tsr.getWorkPackageID());
    	
        localRows.add(newTsr);
        newTsr.setStatus("new");
        databaseRows.add(newTsr);
        System.out.println("Created timesheetRow");
        return "created";
    }
    
    public String deleteTsr(TimesheetRow tsr) {
        //timesheetRowManager.remove(tsr);
        localRows.remove(tsr);
        if (tsr.getStatus() == "new") {
            databaseRows.remove(tsr);
        } else {
            for (int i = 0; i < databaseRows.size(); i++) {
                if (databaseRows.get(i).getTimesheetRowID() == tsr.getTimesheetRowID()) {
                    databaseRows.get(i).setStatus("old-deleted");
                }
            }
        }
        System.out.println("Delete timesheetRow ");
        //list.remove(tsr);
        return null;
    }
    
    public void saveAllTimesheetRows() {
    	Timesheet ts = getTs();
        ts.setTimesheetRows(databaseRows);
        for (int i = 0; i < databaseRows.size(); i++) {
            TimesheetRow row = databaseRows.get(i);
            
            switch (row.getStatus()) {
            case "new":
                System.out.println("call persist");
                timesheetRowManager.merge(row);
                break;
            case "old":
                break;
            case "old-editted":
                System.out.println("call merge");
                timesheetRowManager.merge(row);
                break;
            case "old-deleted":
                timesheetRowManager.remove(row);
                break;
            }
            row.setStatus("old");
        }
        localRows = null;
        databaseRows = null;
        init();
    }

    public ArrayList<TimesheetRow> getAllTimesheetRow() {
        // set the current timesheet to be the current timesheet of the employee
        init();
        return localRows;
    }
    
    public void resetTimesheet() {
        for (int i = 0; i < localRows.size(); i++) {
            TimesheetRow row = localRows.get(i);
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
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();

       System.out.println("rowslength: " + rows.size());
       for (int i=0; i < rows.size(); i++) {
           allHours += rows.get(i).getTotalHours();
           System.out.println("time: " + allHours);
       }
       return allHours;
   }

   public double getSunTotalHours() {
       double sunHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           sunHours += rows.get(i).getHoursSun();
       }
       return sunHours;
   }
   public double getTuesTotalHours() {
       double tuesHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           tuesHours += rows.get(i).getHoursTues();
       }
       return tuesHours;
   }
   public double getMonTotalHours() {
       double monHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           monHours += rows.get(i).getHoursMon();
       }
       return monHours;
   }
   public double getWedTotalHours() {
       double wedHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           wedHours += rows.get(i).getHoursWed();
       }
       return wedHours;
   }
   public double getThursTotalHours() {
       double thursHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           thursHours += rows.get(i).getHoursThurs();
       }
       return thursHours;
   }
   public double getFriTotalHours() {
       double friHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           friHours += rows.get(i).getHoursFri();
       }
       return friHours;
   }
   public double getSatTotalHours() {
       double satHours = 0.0;
       ArrayList<TimesheetRow> rows = getAllTimesheetRow();
       for (int i=0; i < rows.size(); i++) {
           satHours += rows.get(i).getHoursSat();
       }
       return satHours;
   }
   
   public void submitTimesheet() {
	   Timesheet ts = getTs();
	   ts.setSubmitted(true);
	   timesheetManager.merge(ts);
	   setTs(null);
	   init();
   }
}
