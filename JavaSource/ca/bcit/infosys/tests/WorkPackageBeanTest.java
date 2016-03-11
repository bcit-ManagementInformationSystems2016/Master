package ca.bcit.infosys.tests;

import ca.bcit.infosys.models.*;
import junit.framework.TestCase;

import org.junit.Test;

public class WorkPackageBeanTest {

	@Test
	public void testWorkPackageGettersAndSetters() {
	
		// variables
		final String wpID = "wpID";
		final String parentWPID = "parentWPID";
		final double estimatedHours = 1.0;
		final String wpName = "wpName";
		final String description = "description";
		final Project workingProject = new Project();
		
		// creating the workpackage object
		final WorkPackage workPackage = new WorkPackage();
		
		// test setters
		workPackage.setWpID(wpID);
		workPackage.setParentWPID(parentWPID);
		workPackage.setEstimatedHours(estimatedHours);
		workPackage.setWpName(wpName);
		workPackage.setDescription(description);
		workPackage.setWorkingProject(workingProject);
		
		// actual results from getters
		final String wpIDActualResult = workPackage.getWpID();
		final String parentWPIDActualResult = workPackage.getParentWPID();
		final double estimatedHoursActualResult = workPackage.getEstimatedHours();
		final String wpNameActualResult = workPackage.getWpName();
		final String descriptionActualResult = workPackage.getDescription();
		final Project workingProjectActualResult = workPackage.getWorkingProject();
		
		// expected results
		final String wpIDExpectedResult = wpID;
		final String parentWPIDExpectedResult = parentWPID;
		final double estimatedHoursExpectedResult = estimatedHours;
		final String wpNameExpectedResult = wpName;
		final String descriptionExpectedResult = description;
		final Project workingProjectExpectedResult = workingProject;
		
		TestCase.assertEquals("testWorkPackageGettersAndSetters wpID FAILED", wpIDExpectedResult, wpIDActualResult);
		TestCase.assertEquals("testWorkPackageGettersAndSetters parentWPID FAILED", parentWPIDExpectedResult, parentWPIDActualResult);
		TestCase.assertEquals("testWorkPackageGettersAndSetters estimatedHours FAILED", estimatedHoursExpectedResult, estimatedHoursActualResult);
		TestCase.assertEquals("testWorkPackageGettersAndSetters wpName FAILED", wpNameExpectedResult, wpNameActualResult);
		TestCase.assertEquals("testWorkPackageGettersAndSetters description FAILED", descriptionExpectedResult, descriptionActualResult);
		TestCase.assertEquals("testWorkPackageGettersAndSetters workingProject FAILED", workingProjectExpectedResult, workingProjectActualResult);
	}
}
