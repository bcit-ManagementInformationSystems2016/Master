package ca.bcit.infosys.tests.controllers;

import javax.inject.Inject;

import org.junit.Test;

import ca.bcit.infosys.controllers.AccountController;
import ca.bcit.infosys.managers.CredentialManager;
import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Employee;
import junit.framework.TestCase;
import javax.inject.Inject;

public class AccountControllerTest {
	
	private AccountController accountController = new AccountController();
	
	@Inject
	private EmployeeManager empmgr;

	@Inject
	private CredentialManager crmgr;
	
	@Test
	public void testGetAllEmp() {
		
		Employee[] e = empmgr.getAll();
		for (int i = 0; i < e.length; i++) {
			System.out.println(e[i]);
		}
	}
	
}
