package ca.bcit.infosys.Tests;

import junit.framework.TestCase;

import org.junit.Test;

import ca.bcit.infosys.employee.EmployeeData;

public class TimesheetUnitTestCase {

	 /**
	
	 * Ensures that the CalculatorEJB adds as expected
	
	 */
	
	 @Test
	 public void testValidLogin() {
	
		 // Initialize
		
		 final EmployeeData data = new EmployeeData();
		
		 final String username = "employee";
		 final String password = "employee";
		
		 // Add
		
		 final boolean result = data.validateLogin(username, password);
		 final boolean expectedResult = true;
		
		 // Test
		
		 TestCase.assertEquals("testValidLogin FAILED", expectedResult, result);
	
	 }
	 
	 @Test
	 public void testInvalidLogin() {
	
		 // Initialize
		
		 final EmployeeData data = new EmployeeData();
		
		 final String username = "wrong";
		 final String password = "wrong";
		
		 // Add
		
		 final boolean result = data.validateLogin(username, password);
		 final boolean expectedResult = false;
		
		 // Test
		
		 TestCase.assertEquals("testInvalidLogin FAILED", expectedResult, result);
	
	 }

}
