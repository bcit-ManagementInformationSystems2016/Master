package ca.bcit.infosys.tests;

import junit.framework.TestCase;

import java.util.Date;

import org.junit.Test;

import ca.bcit.infosys.models.*;

public class ProjectBeanTest {

	@Test
	 public void testProjectBeanGettersAndSetters() {
	
		 // variables
		 final int projectID = 1;
		 final String projectName = "projectName";
		 final Date startDate = new Date();
		 final String description = "description";
		 final Customer cust = new Customer();
		 final Employee projectManager = new Employee();
		 // TODO add list of work packages
		 
		 // creating the credential object
		 final Project project = new Project();
		 
		 // testing setters for credential
		 project.setProjectID(projectID);
		 project.setProjectName(projectName);
		 project.setStartDate(startDate);
		 project.setDescription(description);
		 project.setCust(cust);
		 project.setProjectManager(projectManager);
		
		 // actual results
		 final int projectIDActualResult = project.getProjectID();
		 final String projectNameActualResult = project.getProjectName();
		 final Date startDateActualResult = project.getStartDate();
		 final String descriptionActualResult = project.getDescription();
		 final Customer customerActualResult = project.getCust();
		 final Employee projectManagerActualResult = project.getProjectManager();
		 
		 // expected results
		 final int projectIDExpectedResult = projectID;
		 final String projectNameExpectedResult = projectName;
		 final Date startDateExpectedResult = startDate;
		 final String descriptionExpectedResult = description;
		 final Customer custExpectedResult = cust;
		 final Employee projectManagerExpectedResult = projectManager;
		
		 // actual tests
		 TestCase.assertEquals("testProjectBeanGettersAndSetters projectID FAILED", projectIDExpectedResult, projectIDActualResult);
		 TestCase.assertEquals("testProjectBeanGettersAndSetters projectName FAILED", projectNameExpectedResult, projectNameActualResult);
		 TestCase.assertEquals("testProjectBeanGettersAndSetters startDate FAILED", startDateExpectedResult, startDateActualResult);
		 TestCase.assertEquals("testProjectBeanGettersAndSetters description FAILED", descriptionExpectedResult, descriptionActualResult);
		 TestCase.assertEquals("testProjectBeanGettersAndSetters cust FAILED", custExpectedResult, customerActualResult);
		 TestCase.assertEquals("testProjectBeanGettersAndSetters projectManager FAILED", projectManagerExpectedResult, projectManagerActualResult);
	 }
	
}
