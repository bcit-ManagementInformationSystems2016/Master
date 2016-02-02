package ca.bcit.infosys.employee;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Login Credentials.
 * @author blink
 */
@Named("login") // or @ManagedBean(name="user")
@SessionScoped
public class Credentials implements Serializable {


    private static final long serialVersionUID = 1L;
    /** The login ID. */
    private String userName;
    /** The password. */
    private String password;
    /**
     * @return the loginID
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param id the loginID to set
     */
    public void setUserName(final String id) {
        userName = id;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param pw the password to set
     */
    public void setPassword(final String pw) {
        password = pw;
    }


}
