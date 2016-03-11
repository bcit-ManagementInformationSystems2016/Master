package ca.bcit.infosys.tests.beans;

import junit.framework.TestCase;
import org.junit.Test;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Employee;

/**
 * Testing the Credential Bean
 */
public class CredentialBeanTest {

	 @Test
	 public void testInitCredentialBean() {
	
		 // variables
		 final String username = "username";
		 final int empId = 2;
		 final String password = "password";
		 
		 // creating the credential object
		 final Credential credentials = new Credential(empId, password, username);
		
		 // test conditions
		 final String usernameActualResult = credentials.getUsername();
		 final int empIdActualResult = credentials.getEmployeeID();
		 final String passwordActualResult = credentials.getPassword();
		 
		 final String usernameExpectedResult = username;
		 final int credIdExpectedResult = empId;
		 final String passwordIdExpectedResult = password;
		
		 // actual tests
		 TestCase.assertEquals("testInitCredentialBean CredentialsID FAILED", usernameExpectedResult, usernameActualResult);
		 TestCase.assertEquals("testInitCredentialBean EmployeeId FAILED", credIdExpectedResult, empIdActualResult);
		 TestCase.assertEquals("testInitCredentialBean Password FAILED", passwordIdExpectedResult, passwordActualResult);
	 }
	 
	 @Test
	 public void testCredentialBeanGettersAndSetters() {
	
		 // variables
		 final String username = "username";
		 final int empId = 2;
		 final String password = "testing";
		 final Employee employee = new Employee();
		 
		 // creating the credential object
		 final Credential credentials = new Credential();
		 
		 // testing setters for credential
		 credentials.setUsername(username);
		 credentials.setEmployeeID(empId);
		 credentials.setPassword(password);
		 credentials.setEmployee(employee);
		
		 // test conditions
		 final String usernameActualResult = credentials.getUsername();
		 final int empIdActualResult = credentials.getEmployeeID();
		 final String passwordActualResult = credentials.getPassword();
		 final Employee employeeActualResult = credentials.getEmployee();
		 
		 final String usernameExpectedResult = username;
		 final int credIdExpectedResult = empId;
		 final String passwordIdExpectedResult = password;
		 final Employee employeeExpectedResult = employee;
		
		 // actual tests
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters CredentialsID FAILED", usernameExpectedResult, usernameActualResult);
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters EmployeeId FAILED", credIdExpectedResult, empIdActualResult);
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters Password FAILED", passwordIdExpectedResult, passwordActualResult);
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters Employee FAILED", employeeExpectedResult, employeeActualResult);
		 
	 }

}
