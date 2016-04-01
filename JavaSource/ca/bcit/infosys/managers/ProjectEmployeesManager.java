package ca.bcit.infosys.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.EmployeeWP;
import ca.bcit.infosys.models.Project;
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
    public ProjectEmployees find(Project p, Employee e) {
    	ProjectEmployeesKey key = new ProjectEmployeesKey(p, e);
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
    public void remove(Project p, Employee e) {
        ProjectEmployees proj = find(p, e);
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
    
    public List<SelectItem> getAllAvailableProjects(int empID) {
    	TypedQuery<Project> proQuery = em.createQuery("select c from Project c", Project.class); 
        List<Project> projects = proQuery.getResultList();
        TypedQuery<ProjectEmployees> proEmpQuery = em.createQuery("SELECT c FROM ProjectEmployees c WHERE EmployeeID = " + empID + "", ProjectEmployees.class);
        List<ProjectEmployees> preassignedProjects = proEmpQuery.getResultList();
        List<SelectItem> availableProjects = new ArrayList<SelectItem>();
        for (int i=0; i < projects.size(); i++) {
        	boolean contains = false;
        	for (int j=0; j < preassignedProjects.size(); j++) {
        		if (projects.get(i).getProjectID() == preassignedProjects.get(j).getPro().getProjectID()) {
        			contains = true;
        			break;
        		}
        	}
        	if (!contains) {
        		availableProjects.add(new SelectItem(projects.get(i), projects.get(i).getProjectName()));
        	}
        }
        return availableProjects;
    }
    
    public List<SelectItem> getAvailableEmployees(int projectID, String wpID) {
    	TypedQuery<ProjectEmployees> proQuery = em.createQuery("select c from ProjectEmployees c WHERE ProjectID = " + projectID + "", ProjectEmployees.class); 
        List<ProjectEmployees> proEmps = proQuery.getResultList();
        TypedQuery<EmployeeWP> ewpQuery = em.createQuery("select c from EmployeeWP c WHERE ProjectID = " + projectID + " AND WorkPackageID = '" + wpID + "'", EmployeeWP.class);
        List<EmployeeWP> assignedEmps = ewpQuery.getResultList();
        List<SelectItem> availableEmployees = new ArrayList<SelectItem>();
        for (int i=0; i < proEmps.size(); i++) {
        	boolean contains = false;
        	for (int j = 0; j < assignedEmps.size(); j++ ) {
        		if (proEmps.get(i).getEmp().getEmployeeID() == assignedEmps.get(j).getEmp().getEmployeeID()) {
        			contains = true;
        			break;
        		}
        	}
        	if (!contains) {
        		TypedQuery<Employee> empQuery = em.createQuery("select c from Employee c WHERE EmployeeID = " + proEmps.get(i).getEmp().getEmployeeID() + "", Employee.class);
            	Employee e = empQuery.getSingleResult();
            	availableEmployees.add(new SelectItem(e, e.getFirstName() + " " + e.getLastName()));
        	}	
        }
        return availableEmployees;
    }
    
    public List<SelectItem> getAllAvailableEmployees(int projectID) {
    	TypedQuery<ProjectEmployees> proQuery = em.createQuery("select c from ProjectEmployees c WHERE ProjectID = " + projectID + "", ProjectEmployees.class);
    	 List<ProjectEmployees> proEmps = proQuery.getResultList();
    	 List<SelectItem> availableEmployees = new ArrayList<SelectItem>();
    	 for (int i = 0; i < proEmps.size(); i++) {
    		 availableEmployees.add(new SelectItem(proEmps.get(i), proEmps.get(i).getEmp().getFirstName() + " " + proEmps.get(i).getEmp().getLastName()));
    	 }
    	 return availableEmployees;
    }
}
