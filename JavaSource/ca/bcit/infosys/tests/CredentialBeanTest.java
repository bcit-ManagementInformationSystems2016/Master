package ca.bcit.infosys.tests;

import junit.framework.TestCase;
import org.junit.Test;
import ca.bcit.infosys.models.Credential;
//import ca.bcit.infosys.models.Employee;

/**
 * Testing the Credential Bean
 */
public class CredentialBeanTest {

	 @Test
	 public void testInitCredentialBean() {
	
		 // variables
		 final int credId = 1;
		 final int empId = 2;
		 final String password = "testing";
		 // TODO add employee
		 
		 // creating the credential object
		 final Credential credentials = new Credential(credId, empId, password);
		
		 // test conditions
		 final int credIdActualResult = credentials.getCredentialsID();
		 final int empIdActualResult = credentials.getEmployeeID();
		 final String passwordActualResult = credentials.getPassword();
		 final int empIdExpectedResult = credId;
		 final int credIdExpectedResult = empId;
		 final String passwordIdExpectedResult = password;
		
		 // actual tests
		 TestCase.assertEquals("testInitCredentialBean CredentialsID FAILED", empIdExpectedResult, credIdActualResult);
		 TestCase.assertEquals("testInitCredentialBean EmployeeId FAILED", credIdExpectedResult, empIdActualResult);
		 TestCase.assertEquals("testInitCredentialBean Password FAILED", passwordIdExpectedResult, passwordActualResult);
	 }
	 
	 @Test
	 public void testCredentialBeanGettersAndSetters() {
	
		 // variables
		 final int credId = 1;
		 final int empId = 2;
		 final String password = "testing";
		 // TODO add employee
		 
		 // creating the credential object
		 final Credential credentials = new Credential();
		 
		 // testing setters for credential
		 credentials.setCredentialsID(credId);
		 credentials.setEmployeeID(empId);
		 credentials.setPassword(password);
		
		 // test conditions
		 final int credIdActualResult = credentials.getCredentialsID();
		 final int empIdActualResult = credentials.getEmployeeID();
		 final String passwordActualResult = credentials.getPassword();
		 final int empIdExpectedResult = credId;
		 final int credIdExpectedResult = empId;
		 final String passwordIdExpectedResult = password;
		
		 // actual tests
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters CredentialsID FAILED", empIdExpectedResult, credIdActualResult);
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters EmployeeId FAILED", credIdExpectedResult, empIdActualResult);
		 TestCase.assertEquals("testCredentialBeanGettersAndSetters Password FAILED", passwordIdExpectedResult, passwordActualResult);
	 }

}
