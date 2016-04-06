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

import ca.bcit.infosys.models.Employee;

/**
 * @author Brendan Voon
 *
 */
@FacesValidator("passwordValidator")
@Stateless
public class PasswordValidator implements Validator {
	Employee e;

	public void getUser(Employee emp) {
		e = emp;
	}

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

		String password = value.toString();
		// System.out.println("password: " + password);
		if (!password.toString().equals(LoginValidator.pw)) {
			FacesMessage msg = new FacesMessage("Password validation failed", "Incorrect Password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
