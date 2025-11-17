/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    
    @Inject
    keepRecord keepRecord;

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String login() {

        // store username + password in session bean
        keepRecord.setEmail(email);
        keepRecord.setPassword(password);
        keepRecord.setErrorStatus("");

        // return a dummy string
        // actual authentication happens in HttpAuthenticationMechanism
        return "AllAssets.xhtml?faces-redirect=true";
    }

    public String getErrorStatus() {
        return keepRecord.getErrorStatus();
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
}
