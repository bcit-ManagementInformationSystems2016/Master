package ca.bcit.infosys.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
   CredentialBeanTest.class, 
   EmployeeBeanTest.class,
   CustomerBeanTest.class,
   ProjectBeanTest.class,
   TimesheetBeanTest.class
})
public class BeanTestSuite {
}
