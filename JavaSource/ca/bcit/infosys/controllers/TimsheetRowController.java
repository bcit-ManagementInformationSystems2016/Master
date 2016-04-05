package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeWPManager;
import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.managers.TimesheetManager;
import ca.bcit.infosys.managers.TimesheetRowManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Timesheet;
import ca.bcit.infosys.models.TimesheetRow;


@Named("timesheetRow")
@ConversationScoped
public class TimsheetRowController implements Serializable {
    /** Manager from Product objects.*/
    @Inject private TimesheetManager timesheetManager;
    /** Manager from Product objects.*/
    @Inject private TimesheetRowManager timesheetRowManager;
    /** Manager from Product objects.*/
    @Inject private WorkPackageManager workPackageManager;
    
    @Inject private EmployeeWPManager ewpManager;
    
    @Inject private ProjectManager pManager;
    private TimesheetRow tsr = new TimesheetRow();
    private Timesheet ts;
    public static Timesheet archivedTs;
    static ArrayList<TimesheetRow> localRows;
    private static ArrayList<TimesheetRow> databaseRows;
    private static TimesheetRow[] archivedRows;
    private int archivedTimesheetId;
    static List<SelectItem> workPackageList;
    static List<SelectItem> projectList;
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
    
    public String gotoArchivedTimesheet(Timesheet ts) {
        archivedTimesheetId = ts.getTimesheetID();
        archivedTs = timesheetManager.find(archivedTimesheetId);
        archivedRows = timesheetRowManager.getRowsWithTimesheetId(archivedTimesheetId);
        return "viewArchivedTimesheet";
    }

    public void setTsr(TimesheetRow tsr) {
        this.tsr = tsr;
    }

    public TimesheetRow[] getArchivedTimesheetRows() {
        if (archivedRows == null) {
            archivedRows = timesheetRowManager.getRowsWithTimesheetId(archivedTimesheetId);
        }
        return archivedRows;
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
    	newTsr.setProjectID(tsr.getProjectID());
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
                timesheetRowManager.persist(row);
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
        for (int j = 0; j < databaseRows.size(); j++) {
            TimesheetRow row = databaseRows.get(j);
            row.setWorkPackageID("");
            row.setHoursSun(0);
            row.setHoursMon(0);
            row.setHoursTues(0);
            row.setHoursWed(0);
            row.setHoursThurs(0);
            row.setHoursFri(0);
            row.setHoursSat(0);
            if (row.getStatus() == "old") {
            	row.setStatus("old-editted");
            }
        }
    }
    
    public List<SelectItem> getWorkPackageList() {
		if (workPackageList == null) {
			int empId = Login.currentID;
			workPackageList = ewpManager.getYourWorkPackages(empId);
		}
		return workPackageList;
	}
    
    public List<SelectItem> getProjectList() {
		if (projectList == null) {
			int empId = Login.currentID;
			System.out.println(empId);
			projectList = pManager.getYourProjects(empId);
		}
		return projectList;
	}
    
    public boolean isSubmitted() {
    	init();
    	return getTs().getSubmitted();
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
   
   public double getAllArchivedTotalHours() {
       double allHours = 0.0;
       

       System.out.println("rowslength: " + archivedRows.length);
       for (int i=0; i < archivedRows.length; i++) {
           allHours += archivedRows[i].getTotalHours();
           System.out.println("time: " + allHours);
       }
       return allHours;
   }

   public double getArchivedSunTotalHours() {
       double sunHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           sunHours += archivedRows[i].getHoursSun();
       }
       return sunHours;
   }
   public double getArchivedTuesTotalHours() {
       double tuesHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           tuesHours += archivedRows[i].getHoursTues();
       }
       return tuesHours;
   }
   public double getArchivedMonTotalHours() {
       double monHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           monHours += archivedRows[i].getHoursMon();
       }
       return monHours;
   }
   public double getArchivedWedTotalHours() {
       double wedHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           wedHours += archivedRows[i].getHoursWed();
       }
       return wedHours;
   }
   public double getArchivedThursTotalHours() {
       double thursHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           thursHours += archivedRows[i].getHoursThurs();
       }
       return thursHours;
   }
   public double getArchivedFriTotalHours() {
       double friHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           friHours += archivedRows[i].getHoursFri();
       }
       return friHours;
   }
   public double getArchivedSatTotalHours() {
       double satHours = 0.0;
       
       for (int i=0; i < archivedRows.length; i++) {
           satHours += archivedRows[i].getHoursSat();
       }
       return satHours;
   }
   
   public double getHoursForWP(String wpID) {
	   double allHours = 0.0;
       TimesheetRow[] rows = timesheetRowManager.getRowsWithWPId(wpID);

      
       for (int i=0; i < rows.length; i++) {
           allHours += rows[i].getTotalHours();
           
       }
       return allHours;
	   
   }
   
   public void submitTimesheet() {
	   saveAllTimesheetRows();
	   Timesheet ts = getTs();
	   ts.setSubmitted(true);
	   timesheetManager.merge(ts);
	   setTs(null);
	   init();
   }
   
   public TimesheetRow[] getCharges(String wpID) {
	   return timesheetRowManager.getRowsWithWPId(wpID);
   }
   
   public void dropdownChange(){
	   System.out.println("dropdown changed");
	   System.out.println("Project " + tsr.projectID);
	   workPackageList = new ArrayList<SelectItem>(ewpManager.getProjectWP(tsr.projectID));
   }
}
