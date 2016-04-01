package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PayLevelCost")
public class PayLevelCost implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLvlCostID")
	public int payLevelCostID;
	
	@Column(name = "P1Cost")
	private double p1Cost;
	
	@Column(name = "P2Cost")
	private double p2Cost;
	
	@Column(name = "P3Cost")
	private double p3Cost;
	
	@Column(name = "P4Cost")
	private double p4Cost;
	
	@Column(name = "P5Cost")
	private double p5Cost;
	
	@Column(name = "P6Cost")
	private double p6Cost;
	
	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="ProjectID")
	private Project project;

	
	// Getters and Setters
	public int getPayLevelCostID() {
		return payLevelCostID;
	}
	public void setPayLevelCostID(int payLevelCostID) {
		this.payLevelCostID = payLevelCostID;
	}
	public double getP1Cost() {
		return p1Cost;
	}
	public void setP1Cost(double p1Cost) {
		this.p1Cost = p1Cost;
	}
	public double getP2Cost() {
		return p2Cost;
	}
	public void setP2Cost(double p2Cost) {
		this.p2Cost = p2Cost;
	}
	public double getP3Cost() {
		return p3Cost;
	}
	public void setP3Cost(double p3Cost) {
		this.p3Cost = p3Cost;
	}
	public double getP4Cost() {
		return p4Cost;
	}
	public void setP4Cost(double p4Cost) {
		this.p4Cost = p4Cost;
	}
	public double getP5Cost() {
		return p5Cost;
	}
	public void setP5Cost(double p5Cost) {
		this.p5Cost = p5Cost;
	}
	public double getP6Cost() {
		return p6Cost;
	}
	public void setP6Cost(double p6Cost) {
		this.p6Cost = p6Cost;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}	
}
