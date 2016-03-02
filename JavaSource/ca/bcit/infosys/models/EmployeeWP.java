/**
 * 
 */
package ca.bcit.infosys.models;

/**
 * @author nguyen
 *
 */
public class EmployeeWP {
	
	private String WorkPackageID;
	
	private int EmpID;
	
	private double TotalHours;

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
	 * @return the empID
	 */
	public int getEmpID() {
		return EmpID;
	}

	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(int empID) {
		EmpID = empID;
	}

	/**
	 * @return the totalHours
	 */
	public double getTotalHours() {
		return TotalHours;
	}

	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(double totalHours) {
		TotalHours = totalHours;
	}
	
	

}
