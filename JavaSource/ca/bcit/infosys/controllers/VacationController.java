package ca.bcit.infosys.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.VacationManager;
import ca.bcit.infosys.models.Vacation;


@Named("vacation")
@SessionScoped
public class VacationController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject private VacationManager vacationManager;
	
	
	public Vacation[] getAllVacation() {
		
		return vacationManager.getAll();
		
	}
}
