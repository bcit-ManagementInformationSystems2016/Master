package ca.bcit.infosys.employee;

import java.io.Serializable;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.ArrayList;

/**
 * Fake database class that houses all the data members in the employee table.
 * @author Calvin Yee
 *
 */
@SessionScoped
@Named("employees")
public class EmployeeData implements Serializable {

    /** The list of employees in the database. */
    private ArrayList<Employee> list;

    /** The employee bean. */
    @Inject private Employee user;

    /**
     * The login credentials that were used to login.
     */
    @Inject private Credentials loginCredentials;

    /**
     * Employee's first name.
     */
    private String employeeFirstName;

    /**
     * Logs the user out of the system.
     *
     * @return a String representing the login page.
     */
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/login?faces-redirect=true";
    }

    /**
     * Initial constructor that initializes a list of valid employees.
     */
    public EmployeeData() {
        list = new ArrayList<Employee>();
        this.initList();
        }

    /**
     * Creates a new user in the employee database.
     * @return The user that is created
     */
    public String createUser() {
        Employee newUser = new Employee();

        newUser.setUserId(user.getUserId());
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setIsSupervisor(user.getIsSupervisor());
        list.add(newUser);
        this.clearUser();
        return "success";
        }

    /**
     * Checks if the logged in user is a supervisor.
     * @return if the user is a logged in supervisor
     */
    public boolean superviorLogin() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUsername());
            System.out.println(list.get(i).getPassword());
            System.out.println(loginCredentials.getUserName());
            System.out.println(loginCredentials.getPassword());
            if (list.get(i).getUsername().equals(loginCredentials.getUserName())
                    && list.get(i).getPassword().equals(loginCredentials.
                            getPassword())) {
                return list.get(i).getIsSupervisor();
            }
        }
        return false;
    }

    /**
     * Checks if the user has valid login credentials.
     * @return the navigation
     */
	public String validateLogin() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUsername());
			System.out.println(list.get(i).getPassword());
			System.out.println(loginCredentials.getUserName());
			System.out.println(loginCredentials.getPassword());
			if (list.get(i).getUsername().equals(loginCredentials.
			        getUserName()) && list.get(i).getPassword().
			        equals(loginCredentials.getPassword())) {
				return "success";
			}
		}
		return null;
	}
	
	public boolean validateLogin(String username, String pw) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsername().equals(username) && list.get(i).getPassword().
			        equals(pw)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the employee id by looping through list of employees.
	 * @return employee id
	 */
	public Integer getEmployeeId() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUsername());
            System.out.println(list.get(i).getPassword());
            System.out.println(loginCredentials.getUserName());
            System.out.println(loginCredentials.getPassword());
            if (list.get(i).getUsername().equals(loginCredentials.getUserName())
                    && list.get(i).getPassword().equals(loginCredentials.
                            getPassword())) {
                return list.get(i).getUserId();
            }
        }
        return null;
    }

	/**
	 * Sets the employee first name.
	 * @param employeeFirstName to be set
	 */
    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    /**
     * Gets the employee's first name by searching fake database.
     * @return employee's first name
     */
   public String getEmployeeFirstName() {
       System.out.println("getEmployeeFirstName");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUsername());
            System.out.println(list.get(i).getPassword());
            System.out.println(loginCredentials.getUserName());
            System.out.println(loginCredentials.getPassword());

            if (list.get(i).getUsername().equals(loginCredentials.getUserName())
                    && list.get(i).getPassword().equals(loginCredentials.
                            getPassword())) {
                employeeFirstName = list.get(i).getFname();
                return employeeFirstName;
            }
        }
        return null;
    }

   /**
    * Gets the employee's last name by searching fake db.
    * @return employee's last name
    */
   public String getEmployeeLastName() {

       for (int i = 0; i < list.size(); i++) {
           System.out.println(list.get(i).getUsername());
           System.out.println(list.get(i).getPassword());
           System.out.println(loginCredentials.getUserName());
           System.out.println(loginCredentials.getPassword());
           if (list.get(i).getUsername().equals(loginCredentials.getUserName())
                   && list.get(i).getPassword().equals(loginCredentials.
                           getPassword())) {
               return list.get(i).getLname();
           }
       }
       return null;
   }

   /**
    * Clears the employee form fields.
    */
	public void clearUser() {
	    user.setUserId(null);
	    user.setFname(null);
	    user.setLname(null);
	    user.setPassword(null);
	    user.setUsername(null);
	}

	/**
	 * Removes a user from the fake db.
	 * @param userToDelete is the user you want to delete
	 * @return null navigation to refresh page
	 */
	public String removeUser(Employee userToDelete) {
		list.remove(userToDelete);
		return null;
	}

	/**
	 * Go back to the previous page.
	 * @return success navigation
	 */
	public String goBack() {
		return "success";
	}

	/**
	 * Sets the editable of the table to false, making it viewOnly again.
	 * @return null navigation to refresh page
	 */
	public String save() {
		for (Employee user : list) {
		    user.setEditable(false);
		}
	    return null;
	}

	/**
	 * Gets the list of employees in the system.
	 * @return list of employees
	 */
	public ArrayList<Employee> getList() {
		return list;
	}

	/**
	 * Sets the list of employees in the system.
	 * @param list to set the list to
	 */
	public void setList(ArrayList<Employee> list) {
		this.list = list;
	}

	/**
	 * Initializes list of users in database.
	 */
	public void initList() {
		//Employee user1 = this.generateCustomer1();
		Employee user2 = this.generateCustomer2();
		Employee user3 = this.generateCustomer3();
		//Employee user4 = this.generateCustomer4();
		//list.add(user1);
		list.add(user2);
		list.add(user3);
		//list.add(user4);
	}

	/**
	 * Generates a hard-coded employee.
	 * @return employee
	 */
	public Employee generateCustomer1() {
		Employee user = new Employee();

		user.setUserId(123456789);
		user.setFname("David");
		user.setLname("Jordan");
		user.setPassword("testing");
		user.setUsername("testing");

		return user;
	}

	/**
	 * Generates a hard-coded employee.
	 * @return employee
	 */
	public Employee generateCustomer2() {
		Employee user = new Employee();

		user.setUserId(891234567);
		user.setFname("Soran");
		user.setLname("Shim");
		user.setPassword("employee");
		user.setUsername("employee");

		return user;
	}

	/**
	 * Generates a hard-coded employee.
	 * @return employee
	 */
	public Employee generateCustomer3() {
		Employee user = new Employee();
		user.setIsSupervisor(true);

		user.setUserId(123456789);
		user.setFname("Games");
		user.setLname("The James");
		user.setPassword("supervisor");
		user.setUsername("supervisor");

		return user;
	}

	/**
	 * Generates a hard-coded employee.
	 * @return employee
	 */
	public Employee generateCustomer4() {
		Employee user = new Employee();

		user.setUserId(123456789);
		user.setFname("Kobe");
		user.setLname("Bryant");
		user.setPassword("testing");
		user.setUsername("testtest");

		return user;
	}

}
