/**
 * 
 */
package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

}
