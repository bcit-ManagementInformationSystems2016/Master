/**
 * 
 */
package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Employee;

/**
 * Handles CRUD action for Employees
 * 
 * @author nguyen
 *
 */

@Dependent
@Stateless
public class EmployeeManager {

	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	
	/**
	 * ID of the employee logged in, hardcoded to 1 for now
	 */
	private static int curID = 1;
	
	/**
	 * Find Employee record from database.
	 * 
	 * @param id
	 *            primary key for record.
	 * @return the Employee record with key = id, null if not found.
	 */
	public Employee find(int id) {
		return em.find(Employee.class, id);
	}

	/**
	 * Persist Employee record into database. id must be unique.
	 * 
	 * @param category
	 *            the record to be persisted.
	 */
	public void persist(Employee employee) {
		em.persist(employee);
	}

	/**
	 * merge Employee record fields into existing database record.
	 * 
	 * @param employee
	 *            the record to be merged.
	 */
	public void merge(Employee employee) {
		em.merge(employee);
	}

	/**
	 * Remove employee from database.
	 * 
	 * @param employee
	 *            record to be removed from database
	 */
	public void remove(Employee employee) {
		// attach category
		employee = find(employee.getEmployeeID());
		em.remove(employee);
	}

	/**
	 * Return employees table as array of employee.
	 * 
	 * @return Employee[] of all records in Employees table
	 */
	public Employee[] getAll() {
		System.out.println("inside getAll employeemanager");
		TypedQuery<Employee> query = em.createQuery("select c from Employee c", Employee.class);
		java.util.List<Employee> categories = query.getResultList();	
		Employee[] emparray = new Employee[categories.size()];
		for (int i = 0; i < emparray.length; i++) {
			emparray[i] = categories.get(i);
			// System.out.println("This is being added to array: " +
			// categories.get(i).getRoleID());
		}
		return emparray;
	}

	public Employee[] getValidating() {
		System.out.println("get validating method");
		TypedQuery<Employee> query = em.createQuery("select c from Employee c where ValidatorID =" + curID, Employee.class);
		System.out.println("query created: " + query.toString());
		java.util.List<Employee> categories = query.getResultList();
		System.out.println("categories size: " + categories.size());
		System.out.println("employee 1 name: " + categories.get(1).getFirstName());
		Employee[] emparray = new Employee[categories.size()];
		for (int i = 0; i < emparray.length; i++) {
			emparray[i] = categories.get(i);
			// System.out.println("This is being added to array: " +
			// categories.get(i).getRoleID());
		}
		return emparray;
	}
}
