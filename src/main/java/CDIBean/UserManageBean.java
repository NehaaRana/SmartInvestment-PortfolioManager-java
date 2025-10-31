/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "userManageBean")
@RequestScoped
public class UserManageBean {

    /**
     * Creates a new instance of UserManageBean
     */
    
    @EJB 
    private UserBeanLocal ubl;
    
    String fullName;
    String email;
    String password;
    String phone;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String register(){
        try{
            ubl.registerUser(fullName, email, password, phone);
            msg="User Register Successfully";
             //  store username in session
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("username", fullName);
            return "index"; //navigate to index.xhtml
            
        }catch(Exception ex){
            msg="Error Occur while regsiter. " + ex.getMessage();
            ex.printStackTrace();
        }
        
        return null;
    }
    
    
    public void reset(){
        this.fullName="";
        this.email="";
        this.password="";
        this.phone="";
        this.msg="";
    }
    
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    public UserManageBean() {
    }
    
}
