package ca.bcit.infosys.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model Class for Work Packages
 * @author cbow
 *
 */

@Entity
@Table(name="WorkPackages")
public class WorkPackage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WorkPackageID")
	private String wpID;
	
	@Column(name="WPParentID")
	private String parentWPID;
	
	@Column(name="EstimatedHours")
	private double estimatedHours;

	@Column(name="WorkPackageName")
	private String wpName;
	
	@Column(name="Description")
	private String description;
	
	//bi-directional one-to-one association to Projects
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProjectID")
	private Project workingProject;
	
	// CTOR
	public WorkPackage() {}
	
	
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

	public double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(double estimatedHours) {
		this.estimatedHours = estimatedHours;
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

} 
