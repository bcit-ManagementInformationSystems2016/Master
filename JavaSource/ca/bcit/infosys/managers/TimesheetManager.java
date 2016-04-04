/**
 * 
 */
package ca.bcit.infosys.managers;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.ProjectEmployees;
import ca.bcit.infosys.models.Timesheet;

/**
 * @author nguyen
 *
 */

@Dependent
@Stateless
public class TimesheetManager {
	
	 @PersistenceContext(unitName="BluehostTesty") EntityManager em;

	    /**
	     * Find Timesheet record from database.
	     * 
	     * @param id
	     *            primary key for record.
	     * @return the ts record with key = id, null if not found.
	     */
	    public Timesheet find(int id) {
	        return em.find(Timesheet.class, id);
	        }

	    /**
	     * Persist Timesheet record into database. id must be unique.
	     * 
	     * @param ts
	     *            the record to be persisted.
	     */
	    public void persist(Timesheet ts) {
	        em.persist(ts);
	    }

	    /**
	     * merge Timesheet record fields into existing database record.
	     * 
	     * @param ts
	     *            the record to be merged.
	     */
	    public void merge(Timesheet ts) {
	        em.merge(ts);
	    }

	    /**
	     * Remove ts from database.
	     * 
	     * @param ts
	     *            record to be removed from database
	     */
	    public void remove(Timesheet ts) {
	        //attach category
	    	System.out.println("Remove in timehseet");
	        ts = find(ts.getTimesheetID());
	        em.remove(ts);
	    }

	    /**
	     * Return Timesheet table as array of timesheet.
	     * 
	     * @return Timesheet[] of all records in Timesheet table
	     */
	    public Timesheet[] getAll() {
	        TypedQuery<Timesheet> query = em.createQuery("select c from Timesheet c", Timesheet.class); 
	        java.util.List<Timesheet> categories = query.getResultList();
	        Timesheet[] catarray = new Timesheet[categories.size()];
	        for (int i=0; i < catarray.length; i++) {
	            catarray[i] = categories.get(i);
	        }
	        return catarray;
	    }
	    
	    public Timesheet getTimesheetEmpId(int empId) {
	    	while(true) {
	    		TypedQuery<Timesheet> query = em.createQuery("select c from Timesheet c where c.employeeID = :empId and c.approved = false", Timesheet.class);
	    		java.util.List<Timesheet> categories = query.setParameter("empId", empId).getResultList();
		    	if (categories.size() > 0) {
		    		Timesheet[] catarray = new Timesheet[categories.size()];
			    	for (int i=0; i < catarray.length; i++) {
			            catarray[i] = categories.get(i);
			        }
			    	return catarray[0];
		    	} else {
		    		// create new timesheet
		    		//TODO create new timesheet properly and persist into database
		    		Timesheet ts = new Timesheet();
		    		Date date = new Date();
		    		ts.setStartDate(date);
		    		Calendar cal = Calendar.getInstance();
		    		cal.setTime(date);
		    		int weekNum = cal.get(Calendar.WEEK_OF_YEAR);
		    		ts.setWeekNumber(weekNum);
		    		ts.setEmployeeID(empId);
		    		persist(ts);
		    	}
	    	}
	    }
	    
	    public Timesheet[] getUnapprovedTimesheets(Employee[] validatees) {
	    	java.util.List<Timesheet> categories  = new java.util.ArrayList<Timesheet>();
	    	for (int i = 0; i < validatees.length; i++) {
	    		TypedQuery<Timesheet> query = em.createQuery("SELECT c FROM Timesheet c WHERE EmployeeID = " + validatees[i].getEmployeeID() + " AND approved = false AND Submitted = true", Timesheet.class);
	    		categories.addAll(query.getResultList());
	    	}
	    	Timesheet[] catarray = new Timesheet[categories.size()];
	        for (int i=0; i < catarray.length; i++) {
	            catarray[i] = categories.get(i);
	        }
	        return catarray;
	    }
	    
	    public Timesheet[] getArchivedTimesheetsWithEmpId(int empId) {
            TypedQuery<Timesheet> query = em.createQuery("select c from Timesheet c where c.employeeID = :empId and c.approved = true and c.submitted = true", Timesheet.class);
            java.util.List<Timesheet> categories = query.setParameter("empId", empId).getResultList();
            Timesheet[] catarray = new Timesheet[categories.size()];
            for (int i=0; i < catarray.length; i++) {
                catarray[i] = categories.get(i);
            }
            return catarray;
        }

}
