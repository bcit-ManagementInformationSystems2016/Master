/**
 * 
 */
package ca.bcit.infosys.managers;

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
	        tsr = find(tsr.getTimesheetRowID());
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
	    

}
