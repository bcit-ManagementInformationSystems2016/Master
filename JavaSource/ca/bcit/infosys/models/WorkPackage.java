/**
 * 
 */
package ca.bcit.infosys.models;

/**
 * @author nguyen
 *
 */
public class WorkPackage {
	
	private String WorkPackageID;
	
	private double EstimatedHours;
	
	private String WorkPackageName;
	
	private String Description;

	/**
	 * @return the workPackageID
	 */
	public String getWorkPackageID() {
		return WorkPackageID;
	}

	/**
	 * @param workPackageID the workPackageID to set
	 */
	public void setWorkPackageID(String workPackageID) {
		WorkPackageID = workPackageID;
	}

	/**
	 * @return the estimatedHours
	 */
	public double getEstimatedHours() {
		return EstimatedHours;
	}

	/**
	 * @param estimatedHours the estimatedHours to set
	 */
	public void setEstimatedHours(double estimatedHours) {
		EstimatedHours = estimatedHours;
	}

	/**
	 * @return the workPackageName
	 */
	public String getWorkPackageName() {
		return WorkPackageName;
	}

	/**
	 * @param workPackageName the workPackageName to set
	 */
	public void setWorkPackageName(String workPackageName) {
		WorkPackageName = workPackageName;
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
	
	

}
