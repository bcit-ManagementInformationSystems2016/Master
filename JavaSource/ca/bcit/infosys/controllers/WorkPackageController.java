package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.WorkPackage;

@Named("workPackageController")
@SessionScoped
public class WorkPackageController implements Serializable {
	
	@Inject
	private WorkPackageManager wpmgr;
	
	public WorkPackage[] getAllWorkPackages() {
		return wpmgr.getAll();
	}

}
