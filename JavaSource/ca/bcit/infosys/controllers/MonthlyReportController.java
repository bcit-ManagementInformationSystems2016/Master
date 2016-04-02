package ca.bcit.infosys.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.PayLevelCostManager;
import ca.bcit.infosys.managers.PayLevelDaysManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.MonthlyReport;
import ca.bcit.infosys.models.PayLevelCost;
import ca.bcit.infosys.models.PayLevelDays;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;

@Named("monthlyReportController")
@SessionScoped
public class MonthlyReportController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private WorkPackageManager wpMgr;
	@Inject
	private PayLevelDaysManager pldMgr;
	@Inject
	private PayLevelCostManager plcMgr;
	
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
		PayLevelCost plc = plcMgr.getProjectCosts(viewableProject.getProjectID());
		for (int i = 0; i < wps.length; i ++) {
			MonthlyReport mr = new MonthlyReport();
			PayLevelDays pld = pldMgr.getSingleEntry(wps[i].getRemainingDaysID());
			mr.setWpID(wps[i].getWpID() + ": " + wps[i].getWpName());
			mr.setBudgetCost(wps[i].getTotalBudgetCost());
			mr.setBudgetHours(wps[i].getTotalBudgetDays());
			mr.setActualCost(0);
			mr.setActualHours(0);
			mr.setRemainingCost(calculateTotalCostRemaining(pld, plc));
			mr.setRemainingHours(calculateTotalHoursRemaining(pld));
			mr.setEstimatedCost(mr.getActualCost() + mr.getRemainingCost());
			mr.setEstimatedHours(mr.getActualHours() + mr.getRemainingHours());
			mr.setVarianceCost(Math.round(((mr.getEstimatedCost()-mr.getBudgetCost())/mr.getBudgetCost())*100));
			mr.setVarianceHours(Math.round(((mr.getEstimatedHours()-mr.getBudgetHours())/mr.getBudgetHours())*100));
			mr.setPercentComplete(Math.round((mr.getActualCost()/mr.getEstimatedCost())*100));
			data[i] = mr;
		}
		dataItems = data;
	}
	
	public double calculateTotalHoursRemaining(PayLevelDays pld) {
		double total = 0;
		total += pld.getP1Day();
		total += pld.getP2Day();
		total += pld.getP3Day();
		total += pld.getP4Day();
		total += pld.getP5Day();
		total += pld.getP6Day();
		return total;
	}
	
	public double calculateTotalCostRemaining(PayLevelDays pld, PayLevelCost plc) {
		double total = 0;
		total += pld.getP1Day() * plc.getP1Cost();
		total += pld.getP2Day() * plc.getP2Cost();
		total += pld.getP3Day() * plc.getP3Cost();
		total += pld.getP4Day() * plc.getP4Cost();
		total += pld.getP5Day() * plc.getP5Cost();
		total += pld.getP6Day() * plc.getP6Cost();
		return total;
	}
	
	public double calculateTotalActualHours(WorkPackage wp) {
		return 0;
	}
	
}
