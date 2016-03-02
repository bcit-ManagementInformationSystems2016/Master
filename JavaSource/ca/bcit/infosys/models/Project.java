/**
 * 
 */
package ca.bcit.infosys.models;

import java.util.Date;

/**
 * @author nguyen
 *
 */
public class Project {
	
	private int ProjectID;
	
	private String ProjectName;
	
	private int ProjectManager;
	
	private Date startDate;
	
	private String Description;
	
	private int CustomerID;

	/**
	 * @return the projectID
	 */
	public int getProjectID() {
		return ProjectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return ProjectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	/**
	 * @return the projectManager
	 */
	public int getProjectManager() {
		return ProjectManager;
	}

	/**
	 * @param projectManager the projectManager to set
	 */
	public void setProjectManager(int projectManager) {
		ProjectManager = projectManager;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return CustomerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	
	
	

}
