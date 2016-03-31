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


import ca.bcit.infosys.models.Roles;

/**
 * @author Brendan Voon
 *
 */
@Dependent
@Stateless
public class RolesManager {

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;

	public Roles find(int id) {
		return em.find(Roles.class, id);
	}

	public void persist(Roles payLevel) {
		em.persist(payLevel);
	}

	public void remove(Roles payLevel) {
		payLevel = find(payLevel.getRoleID());
		em.remove(payLevel);
	}

	public Roles[] getAll() {
		TypedQuery<Roles> query = em.createQuery("select p from Roles p", Roles.class);
		java.util.List<Roles> categories = query.getResultList();

		Roles[] roles = new Roles[categories.size()];
		for (int i = 0; i < roles.length; i++) {
			roles[i] = categories.get(i);
		}

		return roles;
	}

	public List<SelectItem> getRolesIDs() {
		TypedQuery<Roles> proQuery = em.createQuery("select p from Roles p", Roles.class);
		List<Roles> roless = proQuery.getResultList();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (int i = 0; i < roless.size(); i++) {
			list.add(new SelectItem(roless.get(i).roleID, roless.get(i).getRoleName()));
		}
		return list;
	}
}
