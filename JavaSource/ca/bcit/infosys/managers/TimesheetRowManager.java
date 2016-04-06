/**
 * 
 */
package ca.bcit.infosys.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.TimesheetRow;

/**
 * 
 * Handles CRUD actions for TImesheetRows
 * @author nguyen
 *
 */

@Dependent
@Stateless
public class TimesheetRowManager {
	
	 @PersistenceContext(unitName="BluehostTesty") EntityManager em;
	 
	     /**
	     * Find TimesheetRow record from database.
	     * 
	     * @param id
	     *            primary key for record.
	     * @return the tsr record with key = id, null if not found.
	     */
	    public TimesheetRow find(int id) {
	        return em.find(TimesheetRow.class, id);
	        }

	    /**
	     * Persist TimrsheetRow record into database. id must be unique.
	     * 
	     * @param tsr
	     *            the record to be persisted.
	     */
	    public void persist(TimesheetRow tsr) {
	        em.persist(tsr);
	    }

	    /**
	     * merge Timesheetrow record fields into existing database record.
	     * 
	     * @param tsr
	     *            the record to be merged.
	     */
	    public void merge(TimesheetRow tsr) {
	        em.merge(tsr);
	    }

	    /**
	     * Remove TimesheetRow from database.
	     * 
	     * @param tsr
	     *            record to be removed from database
	     */
	    public void remove(TimesheetRow tsr) {
	        //attach category
	    	System.out.println("Remove in timesheetRow");
	        tsr = find(tsr.getTimesheetRowID());
	        System.out.println(tsr.getTimesheetRowID());
	        em.remove(tsr);
	    }

	    /**
	     * Return TimesheetRow table as array of timesheetRow.
	     * 
	     * @return TimesheetRow[] of all records in TimesheetRow table
	     */
	    public TimesheetRow[] getAll() {
	        TypedQuery<TimesheetRow> query = em.createQuery("select c from TimesheetRow c", TimesheetRow.class); 
	        java.util.List<TimesheetRow> categories = query.getResultList();
	        TimesheetRow[] catarray = new TimesheetRow[categories.size()];
	        for (int i=0; i < catarray.length; i++) {
	            catarray[i] = categories.get(i);
	        }
	        return catarray;
	    }
	    
	    /**
	     * Return total hours of all timesheet rows
	     * 
	     * @return all hours of all records in TimesheetRow table
	     */
	    public double getAllHours() {
	    	double allHours = 0.0;
	    	TypedQuery<TimesheetRow> query = em.createQuery("select c from TimesheetRow c", TimesheetRow.class); 
	    	java.util.List<TimesheetRow> tsrows = query.getResultList();
	    	TimesheetRow[] tsrowarray = new TimesheetRow[tsrows.size()];
	    	for (int i = 0; i< tsrowarray.length; i++) {
	    		allHours += tsrowarray[i].getTotalHours();  	
	    	}
	    	return allHours;
	    }
	    
	    public TimesheetRow[] getRowsWithTimesheetId(int timesheetID) {
	    	TypedQuery<TimesheetRow> query = em.createQuery("select c from TimesheetRow c where c.timesheetID = :timesheetID", TimesheetRow.class);
	    	java.util.List<TimesheetRow> categories = query.setParameter("timesheetID", timesheetID).getResultList();
	    	TimesheetRow[] catarray = new TimesheetRow[categories.size()];
		    for (int i=0; i < catarray.length; i++) {
		        catarray[i] = categories.get(i);
		    }
		    return catarray;
	    }
	    
	    public TimesheetRow[] getRowsWithWPId(String wpID) {
	    	TypedQuery<TimesheetRow> query = em.createQuery("select c from TimesheetRow c where c.workPackageID = :wpID", TimesheetRow.class);
	    	java.util.List<TimesheetRow> categories = query.setParameter("wpID", wpID).getResultList();
	    	TimesheetRow[] catarray = new TimesheetRow[categories.size()];
		    for (int i=0; i < catarray.length; i++) {
		        catarray[i] = categories.get(i);
		    }
		    return catarray;
	    }
	    
	    public TimesheetRow[] getSpecificTimesheetRows(int projectID, String wpID) {
			TypedQuery<TimesheetRow> query = em.createQuery("SELECT c FROM TimesheetRow c WHERE ProjectID = " + projectID + " AND WorkPackageID = '" + wpID + "'", TimesheetRow.class);
			java.util.List<TimesheetRow> categories = query.getResultList();
			java.util.List<TimesheetRow> availableTimesheetRows = new java.util.ArrayList<TimesheetRow>();
			for (int i = 0; i < categories.size(); i++) {
				if (categories.get(i).getTimesheet().getApproved() == true) {
					availableTimesheetRows.add(categories.get(i));
				}
			}
			TimesheetRow[] catarray = new TimesheetRow[availableTimesheetRows.size()];
		    for (int i=0; i < catarray.length; i++) {
		    	catarray[i] = categories.get(i);
		    }
		    return catarray;
		}
	    
	    public TimesheetRow[] getRowsForWP(String wpID) {
			TypedQuery<TimesheetRow> query = em.createQuery("select c from TimesheetRow c WHERE WorkPackageID = " + wpID + "", TimesheetRow.class);
			List<TimesheetRow> wps = query.getResultList();
			TimesheetRow[] wpArray = new TimesheetRow[wps.size()];
			for (int i=0; i < wpArray.length; i++) {
				wpArray[i] = wps.get(i);
			}
			return wpArray;
		
		}
	    
	    public int verifyIfCharges(int projectID, String wpID) {
			TypedQuery<TimesheetRow> query = em.createQuery("SELECT c FROM TimesheetRow c WHERE ProjectID = " + projectID + " AND WorkPackageID = '" + wpID + "'", TimesheetRow.class);
			List<TimesheetRow> wps = query.getResultList();
			int arraySize = wps.size();
			return arraySize;
		}

}
