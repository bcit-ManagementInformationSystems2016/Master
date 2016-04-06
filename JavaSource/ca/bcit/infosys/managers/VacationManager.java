package ca.bcit.infosys.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.PayLevel;
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

	public int getDaysAllowed(int empID) {
		int plID;

		TypedQuery<Employee> query1 = em.createQuery("select e from Employee e where e.employeeID = " + empID,
				Employee.class);
		List<Employee> elist = query1.getResultList();
		Employee[] earray = new Employee[elist.size()];
		for (int i = 0; i < earray.length; i++) {
			earray[i] = elist.get(i);
		}
		plID = earray[0].getPayLevelID();

		TypedQuery<PayLevel> query2 = em.createQuery("select p from PayLevel p where p.payLevelID = " + plID,
				PayLevel.class);
		List<PayLevel> plist = query2.getResultList();
		PayLevel[] parray = new PayLevel[plist.size()];
		for (int i = 0; i < parray.length; i++) {
			parray[i] = plist.get(i);
		}
		return parray[0].getTotalVacationDays();
	}

	public Vacation[] getEmployeeVacationRequests(int empID) {
		TypedQuery<Vacation> query = em.createQuery("select c from Vacation c WHERE EmployeeID = " + empID,
				Vacation.class);
		java.util.List<Vacation> categories = query.getResultList();
		Vacation[] vacayArray = new Vacation[categories.size()];
		for (int i = 0; i < vacayArray.length; i++) {
			vacayArray[i] = categories.get(i);
		}
		return vacayArray;
	}

	public int getDaysRemaining(int empID) {
		int count = 0;
		TypedQuery<Vacation> query = em.createQuery("select c from Vacation c WHERE EmployeeID = " + empID + " AND IsApproved = " + true,
				Vacation.class);
		java.util.List<Vacation> categories = query.getResultList();
		Vacation[] vacayArray = new Vacation[categories.size()];
		for (int i = 0; i < vacayArray.length; i++) {
			vacayArray[i] = categories.get(i);
		}
		for(int i = 0; i < categories.size(); i++){
			count += vacayArray[i].getVacationDaysLeft();
		}
		if (categories.size() > 1)
			return vacayArray[categories.size() - 1].getVacationDaysLeft() - count;
		else
			return getDaysAllowed(empID) - count;
		
	}
}
