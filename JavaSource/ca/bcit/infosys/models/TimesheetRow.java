/**
 * 
 */
package ca.bcit.infosys.models;

/**
 * @author nguyen
 *
 */
public class TimesheetRow {
	
	private int TsrowID;
	
	private int TsID;
	
	private String workPackageID;
	
	private double HoursMon;
	
	private double HoursTues;
	
	private double HoursWed;
	
	private double HoursThurs;
	
	private double HoursFri;

	/**
	 * @return the tsrowID
	 */
	public int getTsrowID() {
		return TsrowID;
	}

	/**
	 * @param tsrowID the tsrowID to set
	 */
	public void setTsrowID(int tsrowID) {
		TsrowID = tsrowID;
	}

	/**
	 * @return the tsID
	 */
	public int getTsID() {
		return TsID;
	}

	/**
	 * @param tsID the tsID to set
	 */
	public void setTsID(int tsID) {
		TsID = tsID;
	}

	/**
	 * @return the workPackageID
	 */
	public String getWorkPackageID() {
		return workPackageID;
	}

	/**
	 * @param workPackageID the workPackageID to set
	 */
	public void setWorkPackageID(String workPackageID) {
		this.workPackageID = workPackageID;
	}

	/**
	 * @return the hoursMon
	 */
	public double getHoursMon() {
		return HoursMon;
	}

	/**
	 * @param hoursMon the hoursMon to set
	 */
	public void setHoursMon(double hoursMon) {
		HoursMon = hoursMon;
	}

	/**
	 * @return the hoursTues
	 */
	public double getHoursTues() {
		return HoursTues;
	}

	/**
	 * @param hoursTues the hoursTues to set
	 */
	public void setHoursTues(double hoursTues) {
		HoursTues = hoursTues;
	}

	/**
	 * @return the hoursWed
	 */
	public double getHoursWed() {
		return HoursWed;
	}

	/**
	 * @param hoursWed the hoursWed to set
	 */
	public void setHoursWed(double hoursWed) {
		HoursWed = hoursWed;
	}

	/**
	 * @return the hoursThurs
	 */
	public double getHoursThurs() {
		return HoursThurs;
	}

	/**
	 * @param hoursThurs the hoursThurs to set
	 */
	public void setHoursThurs(double hoursThurs) {
		HoursThurs = hoursThurs;
	}

	/**
	 * @return the hoursFri
	 */
	public double getHoursFri() {
		return HoursFri;
	}

	/**
	 * @param hoursFri the hoursFri to set
	 */
	public void setHoursFri(double hoursFri) {
		HoursFri = hoursFri;
	}
	
	
	
	

}
