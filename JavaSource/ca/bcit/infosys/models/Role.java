/**
 * 
 */
package ca.bcit.infosys.models;

/**
 * @author nguyen
 *
 */
public class Role {
	
	private int RoleID;
	
	private String roleName;

	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return RoleID;
	}

	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
