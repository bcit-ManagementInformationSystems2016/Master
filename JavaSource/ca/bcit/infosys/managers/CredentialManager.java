/**
 * 
 */
package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Credential;

/**
 * @author nguyen
 *
 */

@Dependent
@Stateless
public class CredentialManager {
	 @PersistenceContext(unitName="BluehostTesty") EntityManager em;

	    /**
	     * Find Credential record from database.
	     * 
	     * @param id
	     *            primary key for record.
	     * @return the Credential record with key = id, null if not found.
	     */
	    public Credential find(int id) {
	        return em.find(Credential.class, id);
	        }

	    /**
	     * Persist Credential record into database. id must be unique.
	     * 
	     * @param credential
	     *            the record to be persisted.
	     */
	    public void persist(Credential credential) {
	        em.persist(credential);
	    }

	    /**
	     * merge Credential record fields into existing database record.
	     * 
	     * @param credential
	     *            the record to be merged.
	     */
	    public void merge(Credential credential) {
	        em.merge(credential);
	    }

	    /**
	     * Remove credential from database.
	     * 
	     * @param credential
	     *            record to be removed from database
	     */
	    public void remove(Credential credential) {
	        //attach category
	        credential = find(credential.getEmployeeID());
	        em.remove(credential);
	    }

	    /**
	     * Return Credentials table as array of Credential.
	     * 
	     * @return Credential[] of all records in Credentials table
	     */
	    public Credential[] getAll() {
	        TypedQuery<Credential> query = em.createQuery("select c from Credential c", Credential.class); 
	        java.util.List<Credential> categories = query.getResultList();
	        Credential[] catarray = new Credential[categories.size()];
	        for (int i=0; i < catarray.length; i++) {
	            catarray[i] = categories.get(i);
	        }
	        return catarray;
	    }

}
