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

import ca.bcit.infosys.controllers.Login;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.ProjectEmployees;

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
	
	public Employee[] getAllMinions(int empID) {
		TypedQuery<Employee> query = em.createQuery("select c from Employee c WHERE SupervisorID = " + empID, Employee.class);
		java.util.List<Employee> categories = query.getResultList();	
		Employee[] emparray = new Employee[categories.size()];
		for (int i = 0; i < emparray.length; i++) {
			emparray[i] = categories.get(i);
			// System.out.println("This is being added to array: " +
			// categories.get(i).getRoleID());
		}
		return emparray;
	}
	
	public Employee[] getAllWithinProject(int projectID) {
		TypedQuery<ProjectEmployees> query = em.createQuery("SELECT c FROM ProjectEmployees c WHERE ProjectID = " + projectID + "", ProjectEmployees.class);
		List<ProjectEmployees> wps = query.getResultList();
		Employee[] empArray = new Employee[wps.size()];
		for (int i=0; i < empArray.length; i++) {
			TypedQuery<Employee> empQuery = em.createQuery("SELECT c FROM Employee c WHERE EmployeeID = " + wps.get(i).getEmp().getEmployeeID() + "", Employee.class);
			Employee empToAdd = empQuery.getSingleResult();
			empArray[i] = empToAdd;
		}
		return empArray;	
	}

	public Employee[] getValidating() {
		TypedQuery<Employee> query = em.createQuery("select c from Employee c where ValidatorID =" + Login.currentID, Employee.class);
		java.util.List<Employee> categories = query.getResultList();
		Employee[] emparray = new Employee[categories.size()];
		for (int i = 0; i < emparray.length; i++) {
			emparray[i] = categories.get(i);
			// System.out.println("This is being added to array: " +
			// categories.get(i).getRoleID());
		}
		return emparray;
	}
	
	public Employee[] getValidatees(int empID) {
		TypedQuery<Employee> query = em.createQuery("select c from Employee c where ValidatorID =" + empID, Employee.class);
		java.util.List<Employee> categories = query.getResultList();
		Employee[] emparray = new Employee[categories.size()];
		for (int i = 0; i < emparray.length; i++) {
			emparray[i] = categories.get(i);
			// System.out.println("This is being added to array: " +
			// categories.get(i).getRoleID());
		}
		return emparray;
	}
	
	public Employee getTimesheetValidator(int empID) {
		TypedQuery<Employee> queryOne = em.createQuery("SELECT c FROM Employee c WHERE EmployeeID = " + empID + "", Employee.class);
		Employee e = queryOne.getSingleResult();
		return e;
	}

	public List<SelectItem> getListOfEmployees(int empID) {
    	TypedQuery<Employee> proQuery = em.createQuery("select c from Employee c", Employee.class); 
        List<Employee> employees = proQuery.getResultList();
        List<SelectItem> selectableEmployees = new ArrayList<SelectItem>();
        for (int i=0; i < employees.size(); i++) {
        	if (employees.get(i).getEmployeeID() == empID) {
        		continue;
        	}
        	selectableEmployees.add(new SelectItem(employees.get(i), employees.get(i).getFirstName() + " " + employees.get(i).getLastName()));
        }
        return selectableEmployees;
    }
}
