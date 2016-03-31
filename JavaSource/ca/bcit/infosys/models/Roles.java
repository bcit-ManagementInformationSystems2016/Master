/**
 * 
 */
package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Brendan Voon
 *
 */
@Entity
@Table(name = "Roles")
public class Roles implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleID")
	public int roleID;
	
	@Column(name = "RoleName")
	private String roleName;

	@OneToMany(mappedBy = "roleID")
	private List<Roles> rolesManagers;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Roles> getRolesManagers() {
		return rolesManagers;
	}

	public void setRolesManagers(List<Roles> rolesManagers) {
		this.rolesManagers = rolesManagers;
	}

}
