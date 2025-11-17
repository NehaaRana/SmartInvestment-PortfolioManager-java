/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package record;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Named(value = "keepRecord")
@SessionScoped
public class keepRecord implements Serializable {
    
    
    private  CredentialValidationResult result;
    private  CallerPrincipal principal;
    private  Set<String> roles;
    private  String token;
    private  String email;
    private  String password;
    private  String errorStatus;
    private  Credential credential;

    public CredentialValidationResult getResult() {
        return result;
    }

    public void setResult(CredentialValidationResult result) {
        this.result = result;
    }

    public CallerPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(CallerPrincipal principal) {
        this.principal = principal;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
    
    public  void reset()
    {
        
       principal=null;
       token=null;
       email=null;
       password=null;
       token=null;
       errorStatus="";
    }
    /**
     * Creates a new instance of keepRecord
     */
    public keepRecord() {
    }
    
}
