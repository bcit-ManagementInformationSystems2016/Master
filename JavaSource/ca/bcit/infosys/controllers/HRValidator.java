/**
 * 
 */
package ca.bcit.infosys.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Credential;

/**
 * @author Brendan Voon
 *
 */
@FacesValidator("hrValidator")
@Stateless
public class HRValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String username = value.toString();

		if (Login.map2.containsKey(username)) {
			int ID = Login.map2.get(username);
			if (ID != HRController.emp.employeeID) {
				FacesMessage msg = new FacesMessage("Username validation failed", "Username already exists");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}
	}
}
