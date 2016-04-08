package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.HashMap;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.EmployeeManager;
import ca.bcit.infosys.managers.PayLevelCostManager;
import ca.bcit.infosys.managers.PayLevelDaysManager;
import ca.bcit.infosys.managers.PayLevelManager;
import ca.bcit.infosys.managers.TimesheetRowManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Employee;
import ca.bcit.infosys.models.MonthlyReport;
import ca.bcit.infosys.models.PayLevelCost;
import ca.bcit.infosys.models.PayLevelDays;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.TimesheetRow;
import ca.bcit.infosys.models.WorkPackage;

@Named("monthlyReportController")
@ConversationScoped
public class MonthlyReportController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private WorkPackageManager wpMgr;
	@Inject
	private PayLevelDaysManager pldMgr;
	@Inject
	private PayLevelCostManager plcMgr;
	@Inject
	private TimesheetRowManager tsrMgr;
	@Inject
	private EmployeeManager empMgr;
	@Inject
	private PayLevelManager plvlMgr;

	private MonthlyReport[] dataItems;
	private Project viewableProject;
	private WorkPackage[] projectWorkPackages;
	private HashMap<String, Double> costMap = new HashMap<String, Double>();
	private HashMap<String, Double> hoursMap = new HashMap<String, Double>();
	private HashMap<String, Double> actualCostMap = new HashMap<String, Double>();
	private HashMap<String, Double> actualHoursMap = new HashMap<String, Double>();

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
	public HashMap<String, Double> getCostMap() {
		return costMap;
	}
	public void setCostMap(HashMap<String, Double> costMap) {
		this.costMap = costMap;
	}
	public HashMap<String, Double> getActualCostMap() {
		return actualCostMap;
	}
	public void setActualCostMap(HashMap<String, Double> actualCostMap) {
		this.actualCostMap = actualCostMap;
	}
	public HashMap<String, Double> getActualHoursMap() {
		return actualHoursMap;
	}
	public void setActualHoursMap(HashMap<String, Double> actualHoursMap) {
		this.actualHoursMap = actualHoursMap;
	}
	public HashMap<String, Double> getHoursMap() {
		return hoursMap;
	}
	public void setHoursMap(HashMap<String, Double> hoursMap) {
		this.hoursMap = hoursMap;
	}

	// FUNCTIONALITY METHODS

	public MonthlyReport[] testFunction() {
		MonthlyReport[] test = new MonthlyReport[4];
		test[0] = new MonthlyReport("test", 1, 2, 3, 4, 5, 6);
		test[1] = new MonthlyReport("test", 6, 3, 7, 6, 2, 4);
		test[2] = new MonthlyReport("test", 7, 3, 4, 1, 1, 2);
		test[3] = new MonthlyReport("test", 0, 6, 5, 7, 3, 2);
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
		for (int i = 0; i < wps.length; i++) {
			MonthlyReport mr = new MonthlyReport();
			//boolean isChild = isChild(wps[i], wps);
			mr.setWpID(wps[i].getWpID() + ": " + wps[i].getWpName());
			mr.setBudgetCost(wps[i].getTotalBudgetCost());
			mr.setBudgetHours(wps[i].getTotalBudgetDays());
			TimesheetRow[] timesheetRows = tsrMgr.getSpecificTimesheetRows(viewableProject.getProjectID(),
					wps[i].getWpID());
			if (actualCostMap.containsKey(wps[i].getWpID())) {
				mr.setActualCost(actualCostMap.get(wps[i].getWpID()));
			} else {
				if (wps[i].getIsChild()) {
					mr.setActualCost(calculateTotalActualCost(wps[i], plc, timesheetRows));
					actualCostMap.put(wps[i].getWpID(), mr.getActualCost());
				} else {
					mr.setActualCost(parentTotalCostActual(wps[i], plc, wps));
				}
			}
			if (actualHoursMap.containsKey(wps[i].getWpID())) {
				mr.setActualHours(actualHoursMap.get(wps[i].getWpID()));
			} else {
				if (wps[i].getIsChild()) {
					mr.setActualHours(calculateTotalActualHours(wps[i], timesheetRows));
					actualHoursMap.put(wps[i].getWpID(), mr.getActualHours());
				} else {
					mr.setActualHours(parentTotalHoursActual(wps[i], wps));
				}
			}
			if (costMap.containsKey(wps[i].getWpID())) {
				mr.setRemainingCost(costMap.get(wps[i].getWpID()));
			} else {
				if (wps[i].getIsChild()) {
					mr.setRemainingCost(calculateTotalCostRemaining(wps[i], plc));
					costMap.put(wps[i].getWpID(), new Double(mr.getRemainingCost()));
				} else {
					mr.setRemainingCost(parentTotalCostRemaining(wps[i], wps, plc));
				}
			}
			if (hoursMap.containsKey(wps[i].getWpID())) {
				mr.setRemainingHours(hoursMap.get(wps[i].getWpID()));
			} else {
				if (wps[i].getIsChild()) {
					mr.setRemainingHours(calculateTotalHoursRemaining(wps[i]));
					hoursMap.put(wps[i].getWpID(), new Double(mr.getRemainingHours()));
				} else {
					mr.setRemainingHours(parentTotalHoursRemaining(wps[i], wps));
				}
			}
			mr.setEstimatedCost(mr.getActualCost() + mr.getRemainingCost());
			mr.setEstimatedHours(mr.getActualHours() + mr.getRemainingHours());
			mr.setVarianceCost(Math.round(((mr.getEstimatedCost() - mr.getBudgetCost()) / mr.getBudgetCost()) * 100));
			mr.setVarianceHours(
					Math.round(((mr.getEstimatedHours() - mr.getBudgetHours()) / mr.getBudgetHours()) * 100));
			mr.setPercentComplete(Math.round((mr.getActualCost() / mr.getEstimatedCost()) * 100));
			data[i] = mr;
		}
		dataItems = data;
	}

	public double parentTotalHoursRemaining(WorkPackage wp, WorkPackage[] wps) {
		WorkPackage[] children = wpMgr.getParentProjectWorkPackages(viewableProject.getProjectID(), wp.getWpID());
		double total = 0;
		for (int i = 0; i < children.length; i++) {
			if (hoursMap.containsKey(children[i].getWpID())) {
				total += hoursMap.get(children[i].getWpID());
			} else {
				double n = 0;
				if (isChild(children[i], wps)) {
					n = calculateTotalHoursRemaining(children[i]);

				} else {
					n = parentTotalHoursRemaining(children[i], wps);
				}
				total += n;
				hoursMap.put(children[i].getWpID(), new Double(n));
			}
		}
		return total;
	}

	public double parentTotalHoursActual(WorkPackage wp, WorkPackage[] wps) {
		WorkPackage[] children = wpMgr.getParentProjectWorkPackages(viewableProject.getProjectID(), wp.getWpID());
		double total = 0;
		for (int i = 0; i < children.length; i++) {
			if (actualHoursMap.containsKey(children[i].getWpID())) {
				total += actualHoursMap.get(children[i].getWpID());
			} else {
				double n = 0;
				if (isChild(children[i], wps)) {
					n = calculateTotalActualHours(children[i],
							tsrMgr.getSpecificTimesheetRows(viewableProject.getProjectID(), children[i].getWpID()));
				} else {
					n = parentTotalHoursActual(children[i], wps);
				}
				total += n;
				actualHoursMap.put(children[i].getWpID(), new Double(n));
			}
		}
		return total;
	}

	public double parentTotalCostRemaining(WorkPackage wp, WorkPackage[] wps, PayLevelCost plc) {
		WorkPackage[] children = wpMgr.getParentProjectWorkPackages(viewableProject.getProjectID(), wp.getWpID());
		double total = 0;
		for (int i = 0; i < children.length; i++) {
			if (costMap.containsKey(children[i].getWpID())) {
				total += costMap.get(children[i].getWpID());
			} else {
				double n = 0;
				if (isChild(children[i], wps)) {
					n = calculateTotalCostRemaining(children[i], plc);
				} else {
					n = parentTotalCostRemaining(children[i], wps, plc);
				}
				total += n;
				costMap.put(children[i].getWpID(), new Double(n));
			}
		}
		return total;
	}

	public double parentTotalCostActual(WorkPackage wp, PayLevelCost plc, WorkPackage[] wps) {
		WorkPackage[] children = wpMgr.getParentProjectWorkPackages(viewableProject.getProjectID(), wp.getWpID());
		double total = 0;
		for (int i = 0; i < children.length; i++) {
			if (actualCostMap.containsKey(children[i].getWpID())) {
				total += actualCostMap.get(children[i].getWpID());
			} else {
				double n = 0;
				if (isChild(children[i], wps)) {
					n = calculateTotalActualCost(children[i], plc,
							tsrMgr.getSpecificTimesheetRows(viewableProject.getProjectID(), children[i].getWpID()));
				} else {
					n = parentTotalCostActual(children[i], plc, wps);
				}
				total += n;
				actualCostMap.put(children[i].getWpID(), new Double(n));
			}
		}
		return total;
	}

	public double calculateTotalHoursRemaining(WorkPackage wp) {
		PayLevelDays pld = pldMgr.getSingleEntry(wp.getRemainingDaysID());
		double total = 0;
		total += pld.getP1Day();
		total += pld.getP2Day();
		total += pld.getP3Day();
		total += pld.getP4Day();
		total += pld.getP5Day();
		total += pld.getP6Day();
		return total;
	}

	public double calculateTotalCostRemaining(WorkPackage wp, PayLevelCost plc) {
		PayLevelDays pld = pldMgr.getSingleEntry(wp.getRemainingDaysID());
		double total = 0;
		total += pld.getP1Day() * plc.getP1Cost();
		total += pld.getP2Day() * plc.getP2Cost();
		total += pld.getP3Day() * plc.getP3Cost();
		total += pld.getP4Day() * plc.getP4Cost();
		total += pld.getP5Day() * plc.getP5Cost();
		total += pld.getP6Day() * plc.getP6Cost();
		return total;
	}

	public boolean isChild(WorkPackage wp, WorkPackage[] wps) {
		for (int i = 0; i < wps.length; i++) {
			if (wps[i].getParentWPID().equals(wp.getWpID())) {
				return false;
			}
		}
		return true;
	}

	public double calculateTotalActualHours(WorkPackage wp, TimesheetRow[] tsrs) {
		double totalHours = 0;
		double convertedHours = 0;
		for (int i = 0; i < tsrs.length; i++) {
			totalHours += tsrs[i].getTotalHours();
		}
		convertedHours = Math.round(totalHours / 8);
		return convertedHours;
	}

	public double calculateTotalActualCost(WorkPackage wp, PayLevelCost plc, TimesheetRow[] tsrs) {
		double totalCost = 0;
		for (int i = 0; i < tsrs.length; i++) {
			int empID = tsrs[i].getTimesheet().getEmployeeID();
			Employee e = empMgr.getTimesheetValidator(empID);
			totalCost += (tsrs[i].getTotalHours()) * (plvlMgr.find(e.getPayLevelID()).getAvgPayRate() / 8);
		}
		return totalCost;
	}

	public String leaveReportPage() {
		hoursMap = new HashMap<String, Double>();
		costMap = new HashMap<String, Double>();
		dataItems = null;
		projectWorkPackages = null;
		return "showAllProjects";
	}

}
