package ca.bcit.infosys.tests;

import junit.framework.TestCase;
import org.junit.Test;
import ca.bcit.infosys.models.Customer;

/**
 * Testing the Credential Bean
 */
public class CustomerBeanTest {

	 @Test
	 public void testCustomerBeanGettersAndSetters() {
	
		 // variables
		 final int customerID = 1;
		 final String contactFName = "Bob";
		 final String contactLName = "Doe";
		 final String companyName = "company";
		 final String description = "desc";
		 		 
		 // test setters for employee bean
		 final Customer customer = new Customer();
		 customer.setCustomerID(customerID);
		 customer.setContactFName(contactFName);
		 customer.setContactLName(contactLName);
		 customer.setCompanyName(companyName);
		 customer.setDescription(description);
		 
		 // actual results
		 final int customerIDActualResult = customer.getCustomerID();
		 final String contactFNameActualResult = customer.getContactFName();
		 final String contactLNameActualResult = customer.getContactLName();
		 final String companyNameActualResult = customer.getCompanyName();
		 final String descriptionActualResult = customer.getDescription();
		
		 // expected conditions
		 final int customerIDExpectedResult = customerID;
		 final String contactFNameExpectedResult = contactFName;
		 final String contactLNameExpectedResult = contactLName;
		 final String companyNameExpectedResult = companyName;
		 final String descriptionExpectedResult = description;
		 
		 // actual tests
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters CustomerID FAILED", customerIDExpectedResult, customerIDActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters FirstName FAILED", contactFNameExpectedResult, contactFNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters LastName FAILED", contactLNameExpectedResult, contactLNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters CompanyName FAILED", companyNameExpectedResult, companyNameActualResult);
		 TestCase.assertEquals("testEmployeeBeanGettersAndSetters Description FAILED", descriptionExpectedResult, descriptionActualResult);
	 }
	 
}

