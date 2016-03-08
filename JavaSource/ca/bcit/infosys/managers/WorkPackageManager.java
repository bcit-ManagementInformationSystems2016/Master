package ca.bcit.infosys.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ca.bcit.infosys.models.WorkPackage;

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
	public WorkPackage find(String id) {
		return em.find(WorkPackage.class, id);
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
	public void remove(WorkPackage wp) {
		wp = find(wp.getWpID());
		em.remove(wp);
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
	
	public WorkPackage[] getParentProjectWorkPackages(int projectID, String parentID) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT c FROM WorkPackage c WHERE ProjectID = " + projectID + " AND WPParentID = " + parentID, WorkPackage.class);
		List<WorkPackage> wps = query.getResultList();
		WorkPackage[] wpArray = new WorkPackage[wps.size()];
		for (int i=0; i < wpArray.length; i++) {
			wpArray[i] = wps.get(i);
		}
		return wpArray;	
	}

}
