package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.CredentialManager;
import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Employee;

@Named("account")
@SessionScoped
public class AccountController implements Serializable {
	@Inject
	private EmployeeManager empmgr;

	@Inject
	private CredentialManager crmgr;

	static Employee[] e;

	public Employee[] getAllEmp() {
		System.out.println("inside get all accountcontroller");
		if (e == null)
			e = empmgr.getAll();
		return e;

	}

	public Credential[] getAllCred() {
		return crmgr.getAll();
	}

	public void login(String userName, String password) {
		System.out.println("Login");
	}

	public Employee[] getValidating() {
		System.out.println("valid");
		return empmgr.getValidating();
	}
}
