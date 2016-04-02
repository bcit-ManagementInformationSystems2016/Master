package ca.bcit.infosys.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("login")
@ConversationScoped
public class Login implements Serializable {
	@PersistenceContext(unitName = "BluehostTesty")
	static EntityManager em;
	public static String username;
	private String password;
	public static int currentID;
	private int id;
	public static String name;
	private String empName;
	
	

	public String getEmpName() {
		return name;
	}

	public void setEmpName(String empName) {
		name = empName;
	}


	@Inject
	private Conversation conversation;

	private boolean hr = false;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Login.name = name;
	}
	
	public int getId() {
		return currentID;
	}

	public void setId() {
		this.id = currentID;
	}
	
	public boolean isHr() {
		return hr;
	}

	public void setHr(boolean hr) {
		this.hr = hr;
	}

	public static int getCurrentID() {
		return currentID;
	}

	public static void setCurrentID(int currentID) {
		Login.currentID = currentID;
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

	
	static Map<String, Integer> map2 = new HashMap<String, Integer>();
	static Map<String, String> map = new HashMap<String, String>();
	static Map<Integer, Boolean> activeMap = new HashMap<Integer, Boolean>();
	static Map<Integer, String> nameMap = new HashMap<Integer, String>();
	static List<Object[]> roleList;

	public static void setMaps() {
		System.out.println("set maps");
		String jpaQuery = "select c.username, c.password, c.employeeID from Credential c";
		String eQuery = "select e.employeeID, e.isActive from Employee e";
		String nameQuery = "select e.employeeID, e.firstName, e.lastName from Employee e";
		List<Object[]> resultList = em.createQuery(jpaQuery).getResultList();
		List<Object[]> resultList2 = em.createQuery(eQuery).getResultList();
		List<Object[]> resultList3 = em.createQuery(nameQuery).getResultList();
		for (Object[] object : resultList) {
			map.put((String) object[0], (String) object[1]);
		}

		for (Object[] object : resultList) {
			map2.put((String) object[0], (Integer) object[2]);
		}

		for (Object[] object : resultList2) {
			activeMap.put((Integer) object[0], (Boolean) object[1]);
		}
		for (Object[] object: resultList3){
		nameMap.put((Integer) object[0], (String)(object[1] + " " + object[2]));
		}

		System.out.println("Print hashmap to test");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		String jpaQuery3 = "select e.employeeID, e.roleID from Employee e where e.employeeID =" + getCurrentID();
		roleList = em.createQuery(jpaQuery3).getResultList();
	}

	public String validate() {
		conversation.begin();
		String jpaQuery3 = "select e.employeeID, e.roleID from Employee e where e.employeeID =" + getCurrentID();
		List<Object[]> roleList = em.createQuery(jpaQuery3).getResultList();
		for (Object[] object : roleList) {
			if (((int) object[1] == 1) || (int) object[1] == 3 || (int) object[1] == 5) {
				setHr(true);
			}
		}
		return "adminLanding";
	}

	public String logout() {
		System.out.println("logout");
		conversation.end();

		return "logout";
	}

	public String goHome() {
		return "home";
	}
}
