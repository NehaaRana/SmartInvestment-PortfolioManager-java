/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Users;
import client.UserClient;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

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
    
//    UserClient ul;

    @Inject
    private UserClient ul;
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
    
    public String register() {
            try {
                ul.register(fullName, email, password, phone);

                msg = "Registration successful!";
                reset();
                return "Login?faces-redirect=true";

            } catch (Exception ex) {
                if (ex.getMessage().contains("Email already exists")) {
                    msg = "Email is already registered!";
                } else {
                    msg = "Error occurred: " + ex.getMessage();
                }
                return null;
            }
        }

    
//    public String register() {
//        UserClient ul= new UserClient();
//        try {
//            // Check if email already exists
//                    Users existing = ul.findByEmail(Users.class,email);
//                    if (existing != null) {
//                        msg = "Email already registered!";
//                        return null;
//                    }
//
//                    // Call EJB to create user (internally hashes password)
//                    ul.register(fullName, email, password, phone);
//
//                    msg = "Registration Successful! Now you can login.";
//
//                    // clear form
//                    reset();
//
//                    return "Login?faces-redirect=true";
//
//                } catch (Exception ex) {
//                    msg = "Error occurred during registration: " + ex.getMessage();
//                    ex.printStackTrace();
//                    return null;
//                }
//        }
    
    

    
//    public String logout() {
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//                .getExternalContext().getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "Login?faces-redirect=true";
//    }

    
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
