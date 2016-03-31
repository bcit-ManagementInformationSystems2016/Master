package ca.bcit.infosys.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.VacationManager;
import ca.bcit.infosys.models.PayLevel;
import ca.bcit.infosys.models.Vacation;


@Named("payLevel")
@SessionScoped
public class PayLevelController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject private PayLevelManager pmgr;
	
	
	public PayLevel[] getAllPayLevels() {
		
		return pmgr.getAll();
		
	}
}
