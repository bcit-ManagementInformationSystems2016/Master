/**
 * 
 */
package ca.bcit.infosys.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Brendan Voon
 *
 */
@FacesValidator("passwordValidator")
@Stateless
public class PasswordValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		Login.setMaps();
		String password = value.toString();
		System.out.println("password: " + password);
		if (!password.toString().equals(LoginValidator.getPassword())){
			FacesMessage msg = new FacesMessage("Password validation failed", "Incorrect Password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}