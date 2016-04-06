package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.bcit.infosys.managers.ProjectManager;
import ca.bcit.infosys.managers.WorkPackageManager;
import ca.bcit.infosys.models.Project;
import ca.bcit.infosys.models.WorkPackage;

@Named("report")
@ConversationScoped
public class ReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProjectManager pjtmgr;
	@Inject
	private WorkPackageManager wpmgr;
	//@Inject
	//private ProjectController projctr;

	//private double budget;

	//private double cost;

	private String projName;

	private String customer;

	private String projManager;

	private int tasks;

	private Date startDate;

	private Date endDate;

	private WorkPackage[] wp;

	public WorkPackage[] getWp() {

		return wp;
	}

	public void setWp(WorkPackage[] wp) {
		this.wp = wp;
	}

	public ProjectManager getPjtmgr() {
		return pjtmgr;
	}

	public void setPjtmgr(ProjectManager pjtmgr) {
		this.pjtmgr = pjtmgr;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public int getTasks() {
		return tasks;
	}

	public void setTasks(int tasks) {
		this.tasks = tasks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String showReport(Project pro) {
		Project pr = pjtmgr.find(pro.getProjectID());
		projName = pr.getProjectName();
		customer = pr.getCust().getCompanyName();
		projManager = pr.getProjectManager().getFirstName() + " " + pr.getProjectManager().getLastName();
		startDate = pr.getStartDate();
		wp = wpmgr.getProjectWorkPackages(pro.getProjectID());

		return "displayReport";
	}

}
