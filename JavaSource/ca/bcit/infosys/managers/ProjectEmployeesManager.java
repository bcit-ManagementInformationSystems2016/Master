package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.ProjectEmployees;
import ca.bcit.infosys.models.ProjectEmployeesKey;

@Dependent
@Stateless
public class ProjectEmployeesManager {
	@PersistenceContext(unitName="BluehostTesty") EntityManager em;
	
    /**
     * Find Project record from database.
     * 
     * @param id
     *            primary key for record.
     * @return the Category record with key = id, null if not found.
     */
    public ProjectEmployees find(int empID, int projID) {
    	ProjectEmployeesKey key = new ProjectEmployeesKey(empID, projID);
        return em.find(ProjectEmployees.class, key);
        }

    /**
     * Persist Project record into database. id must be unique.
     * 
     * @param proj
     *            the record to be persisted.
     */
    public void persist(ProjectEmployees proj) {
        em.persist(proj);
    }

    /**
     * merge Project record fields into existing database record.
     * 
     * @param proj
     *            the record to be merged.
     */
    public void merge(ProjectEmployees proj) {
        em.merge(proj);
    }

    /**
     * Remove proj from database.
     * 
     * @param proj
     *            record to be removed from database
     */
    public void remove(ProjectEmployees proj) {
        //attach category
        proj = find(proj.getEmp().getEmployeeID(), proj.getPro().getProjectID());
        em.remove(proj);
    }

    /**
     * Return Projects table as array of project.
     * 
     * @return Project[] of all records in Project table
     */
    public ProjectEmployees[] getAll() {
        TypedQuery<ProjectEmployees> query = em.createQuery("select c from ProjectEmployees c", ProjectEmployees.class); 
        java.util.List<ProjectEmployees> categories = query.getResultList();
        ProjectEmployees[] catarray = new ProjectEmployees[categories.size()];
        for (int i=0; i < catarray.length; i++) {
            catarray[i] = categories.get(i);
        }
        return catarray;
    }
}
