package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.MonthlyReport;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;

@Named("monthlyReportController")
@SessionScoped
public class MonthlyReportController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private WorkPackageManager wpMgr;
	
	private MonthlyReport[] dataItems;
	private Project viewableProject;
	private WorkPackage[] projectWorkPackages;
	
	// GETTERS AND SETTERS
	
	public MonthlyReport[] getDataItems() {
		return dataItems;
	}
	public void setDataItems(MonthlyReport[] dataItems) {
		this.dataItems = dataItems;
	}
	public Project getViewableProject() {
		return viewableProject;
	}
	public void setViewableProject(Project viewableProject) {
		this.viewableProject = viewableProject;
	}
	public WorkPackage[] getProjectWorkPackages() {
		return projectWorkPackages;
	}
	public void setProjectWorkPackages(WorkPackage[] projectWorkPackages) {
		this.projectWorkPackages = projectWorkPackages;
	}
	
	
	// OTHER METHODS
	
	
	public MonthlyReport[] testFunction() {
		MonthlyReport[] test = new MonthlyReport[4];
		test[0] = new MonthlyReport("test",1,2,3,4,5,6);
		test[1] = new MonthlyReport("test",6,3,7,6,2,4);
		test[2] = new MonthlyReport("test",7,3,4,1,1,2);
		test[3] = new MonthlyReport("test",0,6,5,7,3,2);
		return test;
	}
	
	public String viewMonthlyReport(Project p) {
		viewableProject = p;
		setProjectWorkPackages(wpMgr.getProjectWorkPackages(viewableProject.getProjectID()));
		createMonthlyReportObjects(projectWorkPackages);
		return "viewMonthlyReport";
	}

	public void createMonthlyReportObjects(WorkPackage[] wps) {
		MonthlyReport[] data = new MonthlyReport[wps.length];
		for (int i = 0; i < wps.length; i ++) {
			MonthlyReport mr = new MonthlyReport();
			mr.setWpID(wps[i].getWpID() + ": " + wps[i].getWpName());
			mr.setBudgetCost(wps[i].getTotalBudgetCost());
			mr.setBudgetHours(wps[i].getTotalBudgetDays());
			mr.setActualCost(0);
			mr.setActualHours(0);
			mr.setRemainingCost(0);
			mr.setRemainingHours(0);
			data[i] = mr;
		}
		dataItems = data;
	}
	
}
