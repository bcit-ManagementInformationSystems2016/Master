package ca.bcit.infosys.tests.beans;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
   CredentialBeanTest.class, 
   EmployeeBeanTest.class,
   CustomerBeanTest.class,
   ProjectBeanTest.class,
   TimesheetBeanTest.class,
   TimesheetRowBeanTest.class,
   WorkPackageBeanTest.class
})
public class BeanTestSuite {
}
