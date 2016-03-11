package ca.bcit.infosys.tests;

import junit.framework.TestCase;
import org.junit.Test;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Project;
import java.util.List;
import java.util.ArrayList;

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
		 final int validatorID = 1;
		 final String lastName = "Doe";
		 final double salary = 40.0;
		 final Credential credential = new Credential(1, "password", "username");
		 final List<Project> projectManagers = new ArrayList<Project>();
		 
		 // test setters for employee bean
		 final Employee employee = new Employee();
		 employee.setEmployeeID(employeeID);
		 employee.setFirstName(firstName);
		 employee.setIsActive(isActive);
		 employee.setValidatorID(validatorID);
		 employee.setLastName(lastName);
		 employee.setSalary(salary);
		 employee.setCredential(credential);
		 employee.setProjectManagers(projectManagers);
		 
		 // actual results
		 final int empIdActualResult = employee.getEmployeeID();
		 final String firstNameActualResult = employee.getFirstName();
		 final boolean isActiveActualResult = employee.getIsActive();
		 final int validatorIdActualResult = employee.getValidatorID();
		 final String lastNameActualResult = employee.getLastName();
		 final double salaryActualResult = employee.getSalary();
		 final Credential credentialActualResult = employee.getCredential();
		 final List<Project> projectManagersActualResult = employee.getProjectManagers();
		
		 // expected conditions
		 final int empIdExpectedResult = employeeID;
		 final String firstNameExpectedResult = firstName;
		 final boolean isActiveExpectedResult = isActive;
		 final int validatorIdExpectedResult = validatorID;
		 final String lastNameExpectedResult = lastName;
		 final double salaryExpectedResult = salary;
		 final Credential credentialExpectedResult = credential;
		 final List<Project> projectManagersExpectedResult = projectManagers;
		 
		 // actual tests
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters EmployeeID FAILED", empIdExpectedResult, empIdActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters FirstName FAILED", firstNameExpectedResult, firstNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters IsActive FAILED", isActiveExpectedResult, isActiveActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters IsActive FAILED", validatorIdExpectedResult, validatorIdActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters LastName FAILED", lastNameExpectedResult, lastNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters Salary FAILED", salaryExpectedResult, salaryActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters Credential FAILED", credentialExpectedResult, credentialActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters projectManagers FAILED", projectManagersExpectedResult, projectManagersActualResult);
		 
	 }
	 
}
