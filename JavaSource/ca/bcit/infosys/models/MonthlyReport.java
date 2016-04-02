package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("monthlyReport")
@SessionScoped
public class MonthlyReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String wpID;
	
	private double budgetCost;
	private double budgetHours;

	private double actualCost;
	private double actualHours;
	
	private double remainingCost;
	private double remainingHours;
	
	private double estimatedCost;
	private double estimatedHours;
	
	private double varianceCost;
	private double varianceHours;
	
	private double percentComplete;
	
	// COTRS
	public MonthlyReport() {}
	public MonthlyReport(String wpID, double budgetCost, double budgetHours, double actualCost, double actualHours, double remainingCost, double remainingHours) {
		this.wpID = wpID;
		this.budgetCost = budgetCost;
		this.budgetHours = budgetHours;
		this.actualCost = actualCost;
		this.actualHours = actualHours;
		this.remainingCost = remainingCost;
		this.remainingHours = remainingHours;
	}
	
	
	// GETTERS AND SETTERS
	public String getWpID() {
		return wpID;
	}
	public void setWpID(String wpID) {
		this.wpID = wpID;
	}
	public double getBudgetCost() {
		return budgetCost;
	}
	public void setBudgetCost(double budgetCost) {
		this.budgetCost = budgetCost;
	}
	public double getBudgetHours() {
		return budgetHours;
	}
	public void setBudgetHours(double budgetHours) {
		this.budgetHours = budgetHours;
	}
	public double getActualCost() {
		return actualCost;
	}
	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}
	public double getActualHours() {
		return actualHours;
	}
	public void setActualHours(double actualHours) {
		this.actualHours = actualHours;
	}
	public double getRemainingCost() {
		return remainingCost;
	}
	public void setRemainingCost(double remainingCost) {
		this.remainingCost = remainingCost;
	}
	public double getRemainingHours() {
		return remainingHours;
	}
	public void setRemainingHours(double remainingHours) {
		this.remainingHours = remainingHours;
	}
	public double getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public double getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public double getVarianceCost() {
		return varianceCost;
	}
	public void setVarianceCost(double varianceCost) {
		this.varianceCost = varianceCost;
	}
	public double getVarianceHours() {
		return varianceHours;
	}
	public void setVarianceHours(double varianceHours) {
		this.varianceHours = varianceHours;
	}
	public double getPercentComplete() {
		return percentComplete;
	}
	public void setPercentComplete(double percentComplete) {
		this.percentComplete = percentComplete;
	}
}
