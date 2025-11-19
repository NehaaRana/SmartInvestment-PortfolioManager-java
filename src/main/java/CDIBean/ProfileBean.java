/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Users;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "profileBean")
@Dependent
public class ProfileBean {

    /**
     * Creates a new instance of ProfileBean
     */
    @Inject
    keepRecord keepRecord;

    private String fullName;
    private String email;
    private String phone;
    private String password;

    @PostConstruct
    public void init() {
        try {
//            UserClient uc = new UserClient(keepRecord.getToken());
//            Users u = uc.getProfile(Users.class, keepRecord.getUid() + "");

            String token = keepRecord.getToken();

            // 2️⃣ Pass token into UserClient
            UserClient uc = new UserClient(token);

            // 3️⃣ Call API
            Users u = uc.getProfile(Users.class, keepRecord.getUid() + "");
            this.fullName = u.getFullName();
            this.email = u.getEmail();
            this.phone = u.getPhone();
            this.password = ""; // blank – only update if user enters new password

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String updateProfile() {
        try {
            UserClient uc = new UserClient(keepRecord.getToken());

            // if password is empty → keep old password
            String newPassword = password.isEmpty() ? keepRecord.getPassword() : password;

            uc.updateProfile(
                keepRecord.getUid() + "",
                fullName,
                phone,
                newPassword
            );

//            keepRecord.setFullName(fullName);
//            keepRecord.setPhone(phone);
            keepRecord.setPassword(newPassword);

            return "profile.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("PROFILE LOAD ERROR: " + e.getMessage());
            return null;
        }
    }

    public keepRecord getKeepRecord() {
        return keepRecord;
    }

    public void setKeepRecord(keepRecord keepRecord) {
        this.keepRecord = keepRecord;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public ProfileBean() {
    }
    
}
