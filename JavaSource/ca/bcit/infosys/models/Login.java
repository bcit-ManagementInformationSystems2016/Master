package ca.bcit.infosys.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("login")
@SessionScoped
public class Login implements Serializable {
	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validate() {
		boolean matchPassword = false;
		String jpaQuery = "select c.username, c.password from Credential c";
		List<Object[]> resultList = em.createQuery(jpaQuery).getResultList();

		Map<String, String> map = new HashMap<String, String>();
		for (Object[] object : resultList) {
			map.put((String) object[0], (String) object[1]);
		}
		System.out.println("Print hashmap to test");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("Entered Username: " + username);
		if (map.containsKey(username)) {
			System.out.println("found user");
			String value = map.get(username);
			if (!value.equals(password)) {
				System.out.println("Incorrect Password");
			}
			else
				System.out.println("Successful Login");
				return "adminLanding";
		}
		return "invalid";
	}
	
}
