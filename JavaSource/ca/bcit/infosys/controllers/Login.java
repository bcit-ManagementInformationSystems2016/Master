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
