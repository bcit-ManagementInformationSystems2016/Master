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
@FacesValidator("loginValidator")
@Stateless
public class LoginValidator implements Validator {
	static String password = "";

	public static String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		LoginValidator.password = password;
	}

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		Login.setMaps();
		String username = value.toString();
		boolean active = false;

		if (Login.map.containsKey(username)) {
			Login.setCurrentID(Login.map2.get(username));
			Login.setName(Login.nameMap.get(Login.getCurrentID()));
			System.out.println("NAME: " + Login.name);
			active = Login.activeMap.get(Login.getCurrentID());
			if (active == false) {
				FacesMessage msg = new FacesMessage("Username validation failed", "Account is not active");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

			password = Login.map.get(username);

		} else {
			FacesMessage msg = new FacesMessage("Username validation failed", "Username does not exist");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
