package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EmployeeWP")
@IdClass(EmployeeWPKey.class)
public class EmployeeWP implements Serializable {

	private static final long serialVersionUID = 1L;

	//bi-directional one-to-many association to WorkPackage
	// This is the one side
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name="ProjectID"),
    	@JoinColumn(name="WorkPackageID")	
    })
    private WorkPackage wp;
	
	//bi-directional one-to-many association to Employee
	// This is the one side
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EmployeeID")
    private Employee emp;
	
	@Column(name="TotalHours")
	private double totalHours;
		
	// Getters and Setters
	public WorkPackage getWp() {
		return wp;
	}
	public void setWp(WorkPackage wp) {
		this.wp = wp;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public double getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}
}
