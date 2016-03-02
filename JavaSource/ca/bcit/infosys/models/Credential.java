/**
 * 
 */
package ca.bcit.infosys.models;

/**
 * @author nguyen
 *
 */
public class Credential {
	
	private int CredentialID;
	
	private int EmpID;
	
	private String password;

	/**
	 * @return the credentialID
	 */
	public int getCredentialID() {
		return CredentialID;
	}

	/**
	 * @param credentialID the credentialID to set
	 */
	public void setCredentialID(int credentialID) {
		CredentialID = credentialID;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
