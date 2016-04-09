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
@FacesValidator("hrValidator")
@Stateless
public class HRValidator implements Validator {
	Employee e;

	public void getUser(Employee emp) {
		e = emp;
	}

	@Inject
	CredentialManager crmgr;

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String username = value.toString();

		if (Login.map2.containsKey(username)) {
			int ID = Login.map2.get(username);
			System.out.println(crmgr.getID(username));
			// if (ID != crmgr.getID(username)) {
			// FacesMessage msg = new FacesMessage("Username validation failed",
			// "Username already exists");
			// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			// throw new ValidatorException(msg);
			// }
		}
	}
}
