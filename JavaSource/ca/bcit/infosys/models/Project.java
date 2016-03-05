package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model Class for Project
 * @author cbow
 *
 */

@Entity
@Table(name="Projects")
//@NamedQuery(name="Project.findAll", query="SELECT e FROM Projects e")
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProjectID")
	private int projectID;

	@Column(name="ProjectName")
	private String projectName;

	@Column(name="StartDate")
	private Date startDate;

	@Column(name="Description")
	private String description;
	
	//bi0-directional one-to-many association to Customer
	// insert code here later

	//bi-directional one-to-one association to Credential
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProjectManager")
	private Employee projectManager;
	
	// Ctor
	public Project() {}

	// Getters and Setters
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}
	
	
}
