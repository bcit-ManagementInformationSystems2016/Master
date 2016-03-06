package ca.bcit.infosys.tests;

import junit.framework.TestCase;
import org.junit.Test;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Credential;

/**
 * Testing the Credential Bean
 */
public class EmployeeBeanTest {

	 @Test
	 public void testEmployeeBeanGettersAndSetters() {
	
		 // variables
		 final int employeeID = 1;
		 final String firstName = "Bob";
		 final boolean isActive = true;
		 final String lastName = "Doe";
		 final double salary = 40.0;
		 final Credential credential = new Credential(1, 2, "password");
		 // TODO add list of projects
		 
		 // test setters for employee bean
		 final Employee employee = new Employee();
		 employee.setEmployeeID(employeeID);
		 employee.setFirstName(firstName);
		 employee.setIsActive(isActive);
		 employee.setLastName(lastName);
		 employee.setSalary(salary);
		 employee.setCredential(credential);
		 
		 // actual results
		 final int empIdActualResult = employee.getEmployeeID();
		 final String firstNameActualResult = employee.getFirstName();
		 final boolean isActiveActualResult = employee.getIsActive();
		 final String lastNameActualResult = employee.getLastName();
		 final double salaryActualResult = employee.getSalary();
		 final Credential credentialActualResult = employee.getCredential();
		
		 // expected conditions
		 final int empIdExpectedResult = employeeID;
		 final String firstNameExpectedResult = firstName;
		 final boolean isActiveExpectedResult = isActive;
		 final String lastNameExpectedResult = lastName;
		 final double salaryExpectedResult = salary;
		 final Credential credentialExpectedResult = credential;
		 
		 // actual tests
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters EmployeeID FAILED", empIdExpectedResult, empIdActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters FirstName FAILED", firstNameExpectedResult, firstNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters IsActive FAILED", isActiveExpectedResult, isActiveActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters LastName FAILED", lastNameExpectedResult, lastNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters Salary FAILED", salaryExpectedResult, salaryActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters Credential FAILED", credentialExpectedResult, credentialActualResult);
	 }
	 
}
