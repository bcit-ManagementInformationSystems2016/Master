package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.PayLevelDays;

@Dependent
@Stateless
public class PayLevelDaysManager {
	
	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	
	public PayLevelDays find(int id) {
		return em.find(PayLevelDays.class, id);
	}

	public void persist(PayLevelDays payLevelDays) {
		em.persist(payLevelDays);
	}
	
	public void merge(PayLevelDays payLevelDays) {
		em.merge(payLevelDays);
	}

	public void remove(PayLevelDays payLevelDays) {
		payLevelDays = find(payLevelDays.getPayLevelDaysID());
		em.remove(payLevelDays);
	}

	public PayLevelDays[] getAll() {
		TypedQuery<PayLevelDays> query = em.createQuery("select p from PayLevelDays p", PayLevelDays.class);
		java.util.List<PayLevelDays> categories = query.getResultList();

		PayLevelDays[] payLevelDays = new PayLevelDays[categories.size()];
		for (int i = 0; i < payLevelDays.length; i++) {
			payLevelDays[i] = categories.get(i);
		}
		return payLevelDays;
	}
	
	public PayLevelDays getSingleEntry(int pldID) {
		TypedQuery<PayLevelDays> queryOne = em.createQuery("SELECT c FROM PayLevelDays c WHERE PLvlDaysID = " + pldID + "", PayLevelDays.class);
		PayLevelDays pld = queryOne.getSingleResult();
		return pld;
	}
}
