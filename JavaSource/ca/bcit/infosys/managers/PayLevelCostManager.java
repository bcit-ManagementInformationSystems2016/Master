package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.PayLevelCost;

@Dependent
@Stateless
public class PayLevelCostManager {

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	
	public PayLevelCost find(int id) {
		return em.find(PayLevelCost.class, id);
	}

	public void persist(PayLevelCost payLevelCost) {
		em.persist(payLevelCost);
	}

	public void remove(PayLevelCost payLevelCost) {
		payLevelCost = find(payLevelCost.getPayLevelCostID());
		em.remove(payLevelCost);
	}

	public PayLevelCost[] getAll() {
		TypedQuery<PayLevelCost> query = em.createQuery("select p from PayLevelCost p", PayLevelCost.class);
		java.util.List<PayLevelCost> categories = query.getResultList();

		PayLevelCost[] payLevelCosts = new PayLevelCost[categories.size()];
		for (int i = 0; i < payLevelCosts.length; i++) {
			payLevelCosts[i] = categories.get(i);
		}
		return payLevelCosts;
	}
	
}
