package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Vacation;


@Dependent
@Stateless
public class VacationManager {

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	
	public Vacation find(int id) {
		return em.find(Vacation.class, id);
	}
	
	public void persist(Vacation vacation) {
		em.persist(vacation);
	}
	
	public void remove(Vacation vacation) {
		vacation = find(vacation.getVacationID());
		em.remove(vacation);
	}
	
	public Vacation[] getAll() {
		TypedQuery<Vacation> query = em.createQuery("select v from Vacation v", Vacation.class);
		java.util.List<Vacation> categories = query.getResultList();
		
		Vacation[] vacations = new Vacation[categories.size()];
		for(int i = 0; i < vacations.length; i++) {
			vacations[i] = categories.get(i);
		}
		
		return vacations;
	}
}
