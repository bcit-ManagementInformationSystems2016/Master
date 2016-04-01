package ca.bcit.infosys.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Model Class for Work Packages
 * @author cbow
 *
 */

@Entity
@Table(name="WorkPackages")
@IdClass(WorkPackageKey.class)
public class WorkPackage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="WorkPackageID")
	private String wpID;
	
	@Column(name="WPParentID")
	private String parentWPID;

	@Column(name="WorkPackageName")
	private String wpName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="TotalBudgetDays")
	private double totalBudgetDays;
	
	@Column(name="TotalBudgetCost")
	private double totalBudgetCost;
	
	//bi-directional one-to-one association to Employee
//	@OneToOne
//	@JoinColumn(name="BudgetPDays")
	@Column(name="BudgetPDays")
	private int budgetedDaysID;
		
	//bi-directional one-to-one association to Employee
//	@OneToOne
//	@JoinColumn(name="RemainingDays")
	@Column(name="RemainingDays")
	private int remainingDaysID;

	//bi-directional one-to-many association to Employee
	// This is the one side
//	@Id
//	@ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="ResponsibleEngineer")
	@Column(name="ResponsibleEngineer")
    private int responsibleEngineerID;
	
	//bi-directional one-to-one association to Projects
	@Id
	@ManyToOne(targetEntity=Project.class,fetch=FetchType.LAZY)
	@JoinColumn(name="ProjectID")
	private Project workingProject;
	
	//bi-directional one-to-many association with EmployeeWP
	@OneToMany(targetEntity=EmployeeWP.class,mappedBy="wp",cascade={CascadeType.ALL},orphanRemoval=true)
	private List<EmployeeWP> assignedWorkPackages;
	
	// CTOR
	public WorkPackage() {}
	
	public WorkPackage(String wpID, String parentWPID, Project workingProject) {
		this.wpID = wpID;
		this.parentWPID = parentWPID;
		this.workingProject = workingProject;
		System.out.println("WP CREATED");
	}
	
	
	// Getters and Setters

	public String getWpID() {
		return wpID;
	}
	public void setWpID(String wpID) {
		this.wpID = wpID;
	}
	public String getParentWPID() {
		return parentWPID;
	}
	public void setParentWPID(String parentWPID) {
		this.parentWPID = parentWPID;
	}
	public String getWpName() {
		return wpName;
	}
	public void setWpName(String wpName) {
		this.wpName = wpName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Project getWorkingProject() {
		return workingProject;
	}
	public void setWorkingProject(Project workingProject) {
		this.workingProject = workingProject;
	}
	public List<EmployeeWP> getAssignedWorkPackages() {
		return assignedWorkPackages;
	}
	public void setAssignedWorkPackages(List<EmployeeWP> assignedWorkPackages) {
		this.assignedWorkPackages = assignedWorkPackages;
	}
	public double getTotalBudgetDays() {
		return totalBudgetDays;
	}
	public void setTotalBudgetDays(double totalBudgetDays) {
		this.totalBudgetDays = totalBudgetDays;
	}
	public double getTotalBudgetCost() {
		return totalBudgetCost;
	}
	public void setTotalBudgetCost(double totalBudgetCost) {
		this.totalBudgetCost = totalBudgetCost;
	}
	public int getResponsibleEngineerID() {
		return responsibleEngineerID;
	}
	public void setResponsibleEngineerID(int responsibleEngineerID) {
		this.responsibleEngineerID = responsibleEngineerID;
	}
	public int getBudgetedDaysID() {
		return budgetedDaysID;
	}
	public void setBudgetedDaysID(int budgetedDaysID) {
		this.budgetedDaysID = budgetedDaysID;
	}
	public int getRemainingDaysID() {
		return remainingDaysID;
	}
	public void setRemainingDaysID(int remainingDaysID) {
		this.remainingDaysID = remainingDaysID;
	}
} 
