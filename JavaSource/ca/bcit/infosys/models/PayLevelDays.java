package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PayLevelDays")
public class PayLevelDays implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLvlDaysID")
	public int payLevelCostID;
	
	@Column(name = "P1Day")
	private double p1Day;
	
	@Column(name = "P2Day")
	private double p2Day;
	
	@Column(name = "P3Day")
	private double p3Day;
	
	@Column(name = "P4Day")
	private double p4Day;
	
	@Column(name = "P5Day")
	private double p5Day;
	
	@Column(name = "P6Day")
	private double p6Day;
	
	// bi-directional one-to-one association to Credential
	@OneToOne(mappedBy = "budgetedDays")
	private WorkPackage budgetedWP;
		
	// bi-directional one-to-one association to Credential
	@OneToOne(mappedBy = "remainingDays")
	private WorkPackage remainingWP;
	
	// Getters and Setters
	public int getPayLevelCostID() {
		return payLevelCostID;
	}
	public void setPayLevelCostID(int payLevelCostID) {
		this.payLevelCostID = payLevelCostID;
	}
	public double getP1Day() {
		return p1Day;
	}
	public void setP1Day(double p1Day) {
		this.p1Day = p1Day;
	}
	public double getP2Day() {
		return p2Day;
	}
	public void setP2Day(double p2Day) {
		this.p2Day = p2Day;
	}
	public double getP3Day() {
		return p3Day;
	}
	public void setP3Day(double p3Day) {
		this.p3Day = p3Day;
	}
	public double getP4Day() {
		return p4Day;
	}
	public void setP4Day(double p4Day) {
		this.p4Day = p4Day;
	}
	public double getP5Day() {
		return p5Day;
	}
	public void setP5Day(double p5Day) {
		this.p5Day = p5Day;
	}
	public double getP6Day() {
		return p6Day;
	}
	public void setP6Day(double p6Day) {
		this.p6Day = p6Day;
	}
	public WorkPackage getBudgetedWP() {
		return budgetedWP;
	}
	public void setBudgetedWP(WorkPackage budgetedWP) {
		this.budgetedWP = budgetedWP;
	}
	public WorkPackage getRemainingWP() {
		return remainingWP;
	}
	public void setRemainingWP(WorkPackage remainingWP) {
		this.remainingWP = remainingWP;
	}	
}
