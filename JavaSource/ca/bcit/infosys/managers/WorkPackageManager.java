package ca.bcit.infosys.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;
import ca.bcit.infosys.models.WorkPackageKey;

/**
 * Handles CRUD actions for Work Packages
 * @author cbow
 *
 */

@Dependent
@Stateless
public class WorkPackageManager {
	@PersistenceContext(unitName="BluehostTesty") EntityManager em;
	
	/**
	 * Find Work Package record from database
	 * @param id primary key for record
	 * @return the WorkPackage with key = id, null if not found
	 */
	public WorkPackage find(Project p, String id) {
		WorkPackageKey key = new WorkPackageKey(p, id);
		return em.find(WorkPackage.class, key);
	}
	
	/**
	 * merge Work Package record fields into existing database record. 
	 * @param wp the record to be merged
	 */
	public void persist(WorkPackage wp) {
		em.persist(wp);
	}
	
	/**
	 * merge Work Package record fields into existing database record.
	 * @param wp the record to be merged
	 */
	public void merge(WorkPackage wp) {
		em.merge(wp);
	}
	
	/**
	 * remove Work Package from database
	 * @param wp record to be removed from the database
	 */
	public void remove(Project p, String id) {
		WorkPackage wp = find(p, id);
		em.remove(wp);
	}
	
	public void addWP(WorkPackage wp) {
		/* WorkPackage newWP = new WorkPackage();
		newWP.setWpID(wp.getWpID());
		newWP.setWorkingProject(wp.getWorkingProject());
		newWP.setParentWPID(wp.getParentWPID());
		newWP.setDescription(wp.getDescription());
		newWP.setEstimatedHours(wp.getEstimatedHours());
		newWP.setWpName(wp.getWpName()); */
		//WorkPackage newWP = new WorkPackage(wp.getWpID(), wp.getParentWPID(), wp.getEstimatedHours(), wp.getWpName(), wp.getDescription(), wp.getWorkingProject());
		//em.getTransaction().begin();
		//System.out.println("create new WP: " + newWP.getWpID() + " - " + newWP.getWpName());
		//em.persist(newWP);
		System.out.println("did it persist?");
		//em.getTransaction().commit();
	}
	
	public WorkPackage[] getAll() {
		TypedQuery<WorkPackage> query = em.createQuery("select c from WorkPackage c", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		WorkPackage[] wpArray = new WorkPackage[wps.size()];
		for (int i=0; i < wpArray.length; i++) {
			wpArray[i] = wps.get(i);
		}
		return wpArray;
	}
	
	public WorkPackage[] getProjectWorkPackages(int projectID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + "", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		WorkPackage[] wpArray = new WorkPackage[wps.size()];
		for (int i=0; i < wpArray.length; i++) {
			wpArray[i] = wps.get(i);
		}
		return wpArray;	
	}
	
	public List<WorkPackage> getProjectWorkPackagesForTree(int projectID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WorkPackageID <> 'X'", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		return wps;	
	}
	
	public WorkPackage[] getParentProjectWorkPackages(int projectID, String parentID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WPParentID = '" + parentID + "'", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		WorkPackage[] wpArray = new WorkPackage[wps.size()];
		for (int i=0; i < wpArray.length; i++) {
			wpArray[i] = wps.get(i);
		}
		return wpArray;	
	}
	
	public WorkPackage[] getParentProjectWorkPackagesNull(int projectID, String parentID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WPParentID = " + parentID + "", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		WorkPackage[] wpArray = new WorkPackage[wps.size()];
		for (int i=0; i < wpArray.length; i++) {
			wpArray[i] = wps.get(i);
		}
		return wpArray;	
	}
	
	public int getWorkPackageCount(int projectID, String parentID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WPParentID = '" + parentID + "'", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		int arraySize = wps.size();
		return arraySize;
	}
	
	public int getWorkPackageCountWithNull(int projectID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WPParentID IS NULL", WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		int arraySize = wps.size();
		return arraySize;
	}
	
	/*public void insertWP(WorkPackage wp) {		
		INSERT INTO Customers (CustomerName, City, Country)
		VALUES ('Cardinal', 'Stavanger', 'Norway');
	} */
	
	public WorkPackage getTopWorkPackage(int projectID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WorkPackageID = 'X'", WorkPackage.class);
		WorkPackage top = query.getSingleResult();
		return top;
	}

}
