package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.VacationManager;
import ca.bcit.infosys.models.Credential;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.PayLevel;
import ca.bcit.infosys.models.Vacation;


@Named("payLevel")
@SessionScoped
public class PayLevelController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject private PayLevelManager pmgr;
	
	private PayLevel plevel;
	public PayLevel getPlevel() {
		return plevel;
	}

	public void setPlevel(PayLevel plevel) {
		this.plevel = plevel;
	}

	public PayLevel[] getAllPayLevels() {
		
		return pmgr.getAll();
		
	}
	
	public String editP(PayLevel p) {
		setPlevel(p);
		return "editP";
	}
	
	public String updateP(PayLevel p){
		pmgr.merge(p);
		return "PLupdated";
	}
}
