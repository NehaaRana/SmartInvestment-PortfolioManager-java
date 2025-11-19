/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Users;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "userDashboardBean")
@SessionScoped
public class UserDashboardBean implements  Serializable{

    /**
     * Creates a new instance of UserDashboardBean
     */
    
     @Inject
    private keepRecord keepRecord;
    
    UserClient userClient;
    
    private Object profile;
    private Object portfolio;
    private Object goals;
    private Object orders;
    private Object payments;
    private Object transactions;
    public UserDashboardBean() {
    }
    
    @PostConstruct
    public void init(){
        try{
            System.out.println("INIT START");
            userClient = new UserClient(keepRecord.getToken());
            String token = keepRecord.getToken();
            String uid = keepRecord.getUid();
            
            System.out.println("Token= " + token);
            System.out.println("UID= " + uid);
            
//            UserClient uc = new UserClient(token);
            
            Users u = userClient.getProfile(Users.class, uid);
            
            this.profile = u; // set to dashboard
            System.out.println("User loaded: " + u.getFullName());
            
        }catch(Exception e){
            System.out.println("❌ ERROR IN init(): " + e.getMessage());
            e.printStackTrace();
        }
    }
//    @PostConstruct
//    public void init() {
//        
//            System.out.println("TOKEN from KeepRecord = " + keepRecord.getToken());
//            System.out.println("Dashboard Init Called, UID = " + keepRecord.getUid());
//
//        if (keepRecord.getUid() != null) {
//
//            userClient = new UserClient(keepRecord.getToken());
//            Users u = userClient.getProfile(Users.class, keepRecord.getUid() + "");
//             System.out.println("USER FROM API = " + u);
//            if (keepRecord.getUid() != null) {
//                loadDashboardData();
//            }
//        }
//    }
    
    public void loadDashboardData() {
        String uid = keepRecord.getUid();

        profile = userClient.getProfile(Object.class, uid);
        portfolio = userClient.getUserPortfolio(Object.class, uid);
        goals = userClient.getUserGoals(Object.class, uid);
        orders = userClient.getUserOrders(Object[].class, uid);
        payments = userClient.getUserPayments(Object.class, uid);
        transactions = userClient.getUserTransactions(Object.class, uid);
    }

     public Object getProfile() {
        return profile;
    }

    public Object getPortfolio() {
        return portfolio;
    }

    public Object getGoals() {
        return goals;
    }

    public Object getOrders() {
        return orders;
    }

    public Object getPayments() {
        return payments;
    }

    public Object getTransactions() {
        return transactions;
    }

    public String logout() {
        return "/login.xhtml?faces-redirect=true";
    }
}
