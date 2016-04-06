/**
 * 
 */
package ca.bcit.infosys.controllers;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import ca.bcit.infosys.managers.CredentialManager;
import ca.bcit.infosys.models.Employee;

/**
 * @author Brendan Voon
 *
 */
@FacesValidator("loginValidator")
@Stateless
public class LoginValidator implements Validator {
	Employee e;

	public void getUser(Employee emp) {
		System.out.println("GET USER HR CONTROLLER");
		e = emp;
	}

	@Inject
	CredentialManager crmgr;
	public static String pw;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		Login.setMaps();
		String username = value.toString();
		boolean active = false;

		if (Login.map.containsKey(username)) {
			pw = Login.map.get(username);
			active = Login.activeMap.get(Login.map2.get(username));
			if (active == false) {
				FacesMessage msg = new FacesMessage("Username validation failed", "Account is not active");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} else {
			FacesMessage msg = new FacesMessage("Username validation failed", "Username does not exist");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
