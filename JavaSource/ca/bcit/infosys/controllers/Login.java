package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("login")
@ConversationScoped
public class Login implements Serializable {
	@PersistenceContext(unitName = "BluehostTesty")
	EntityManager em;
	private String username;
	private String password;
	public static int currentID;

	public int getCurrentID() {
		return currentID;
	}

	public void setCurrentID(int currentID) {
		this.currentID = currentID;
	}

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
		String jpaQuery = "select c.username, c.password, c.employeeID from Credential c";
		List<Object[]> resultList = em.createQuery(jpaQuery).getResultList();
		// contains username and password
		Map<String, String> map = new HashMap<String, String>();
		for (Object[] object : resultList) {
			map.put((String) object[0], (String) object[1]);
		}
		// contains username and employeeID
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		for (Object[] object : resultList) {
			map2.put((String) object[0], (Integer) object[2]);
		}
		/*
		 * System.out.println("Print hashmap to test"); for (Map.Entry<String,
		 * String> entry : map.entrySet()) { System.out.println(entry.getKey() +
		 * " : " + entry.getValue()); }
		 */
		System.out.println("Entered Username: " + username);
		if (map.containsKey(username)) {
			System.out.println("found user");
			String value = map.get(username);
			if (!value.equals(password)) {
				System.out.println("Incorrect Password");
			} else {
				setCurrentID(map2.get(username));
				System.out.println("Current ID: " + currentID);
				System.out.println("Successful Login");
			}
			return "adminLanding";
		}
		return "invalid";
	}

}
