/**
 * 
 */
package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Project;

/**
 * Handles CRUD actions for Project
 * @author cbow
 *
 */

@Dependent
@Stateless
public class ProjectManager {
	 @PersistenceContext(unitName="BluehostTesty") EntityManager em;

	    /**
	     * Find Project record from database.
	     * 
	     * @param id
	     *            primary key for record.
	     * @return the Category record with key = id, null if not found.
	     */
	    public Project find(int id) {
	        return em.find(Project.class, id);
	        }

	    /**
	     * Persist Project record into database. id must be unique.
	     * 
	     * @param proj
	     *            the record to be persisted.
	     */
	    public void persist(Project proj) {
	        em.persist(proj);
	    }

	    /**
	     * merge Project record fields into existing database record.
	     * 
	     * @param proj
	     *            the record to be merged.
	     */
	    public void merge(Project proj) {
	        em.merge(proj);
	    }

	    /**
	     * Remove proj from database.
	     * 
	     * @param proj
	     *            record to be removed from database
	     */
	    public void remove(Project proj) {
	        //attach category
	        proj = find(proj.getProjectID());
	        em.remove(proj);
	    }

	    /**
	     * Return Projects table as array of project.
	     * 
	     * @return Project[] of all records in Project table
	     */
	    public Project[] getAll() {
	        TypedQuery<Project> query = em.createQuery("select c from Project c", Project.class); 
	        java.util.List<Project> categories = query.getResultList();
	        Project[] catarray = new Project[categories.size()];
	        for (int i=0; i < catarray.length; i++) {
	            catarray[i] = categories.get(i);
	        }
	        return catarray;
	    }
	

}