package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.EmployeeWP;
import ca.bcit.infosys.models.EmployeeWPKey;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.ProjectEmployees;
import ca.bcit.infosys.models.ProjectEmployeesKey;
import ca.bcit.infosys.models.WorkPackage;

@Dependent
@Stateless
public class EmployeeWPManager {
	@PersistenceContext(unitName="BluehostTesty") EntityManager em;

	/**
     * Find Project record from database.
     * 
     * @param id
     *            primary key for record.
     * @return the Category record with key = id, null if not found.
     */
    public EmployeeWP find(WorkPackage wp, Employee e) {
    	EmployeeWPKey key = new EmployeeWPKey(wp, e);
        return em.find(EmployeeWP.class, key);
        }

    /**
     * Persist Project record into database. id must be unique.
     * 
     * @param proj
     *            the record to be persisted.
     */
    public void persist(EmployeeWP proj) {
        em.persist(proj);
    }

    /**
     * merge Project record fields into existing database record.
     * 
     * @param proj
     *            the record to be merged.
     */
    public void merge(EmployeeWP proj) {
        em.merge(proj);
    }

    /**
     * Remove proj from database.
     * 
     * @param proj
     *            record to be removed from database
     */
    public void remove(WorkPackage wp, Employee e) {
        EmployeeWP proj = find(wp, e);
        em.remove(proj);
    }
    
    public EmployeeWP[] findAssignedEmployees(int projectID) {
        TypedQuery<EmployeeWP> query = em.createQuery("select c from ProjectEmployees c WHERE ProjectID = " + projectID +  "", EmployeeWP.class); 
        java.util.List<EmployeeWP> categories = query.getResultList();
        EmployeeWP[] catarray = new EmployeeWP[categories.size()];
        for (int i=0; i < catarray.length; i++) {
            catarray[i] = categories.get(i);
        }
        return catarray;
    }
    
    
}
