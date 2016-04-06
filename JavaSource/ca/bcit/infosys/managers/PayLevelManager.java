/**
 * 
 */
package ca.bcit.infosys.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.PayLevel;

/**
 * @author Brendan Voon
 *
 */
@Dependent
@Stateless
public class PayLevelManager {

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;

	public PayLevel find(int id) {
		return em.find(PayLevel.class, id);
	}
	public void merge(PayLevel payLevel) {
		em.merge(payLevel);
	}
	public PayLevel[] getAll() {
		TypedQuery<PayLevel> query = em.createQuery("select p from PayLevel p", PayLevel.class);
		java.util.List<PayLevel> categories = query.getResultList();

		PayLevel[] payLevels = new PayLevel[categories.size()];
		for (int i = 0; i < payLevels.length; i++) {
			payLevels[i] = categories.get(i);
		}

		return payLevels;
	}
	public List<SelectItem> getPayLevelIDs() {
		TypedQuery<PayLevel> proQuery = em.createQuery("select p from PayLevel p", PayLevel.class);
		List<PayLevel> paylevels = proQuery.getResultList();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (int i = 0; i < paylevels.size(); i++) {
			list.add(new SelectItem(paylevels.get(i).payLevelID));
		}
		return list;
	}
	
	public PayLevel getPayLevelInfo(int payLevelID) {
		TypedQuery<PayLevel> queryOne = em.createQuery("SELECT c FROM PayLevel c WHERE PayLevelID = " + payLevelID + "", PayLevel.class);
		PayLevel p = queryOne.getSingleResult();
		return p;
	}
}
