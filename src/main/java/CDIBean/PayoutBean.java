/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Payouts;
import client.AdminClient;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "payoutBean")
@SessionScoped
public class PayoutBean implements Serializable {
    
    @Inject
    private keepRecord keepRecord;
    private AdminClient adminClient;
    private UserClient userClient; 
    private Collection<Payouts> payoutList;      // ADMIN — All payouts
    private Collection<Payouts> userPayoutList;  // USER — Logged-in user's payouts
    private BigDecimal totalReturns = BigDecimal.ZERO;

    public BigDecimal getTotalReturns() {
        return totalReturns;
    }

    public void setTotalReturns(BigDecimal totalReturns) {
        this.totalReturns = totalReturns;
    }

    public Collection<Payouts> getUserPayoutList() {
        return userPayoutList;
    }

    public void setUserPayoutList(Collection<Payouts> userPayoutList) {
        this.userPayoutList = userPayoutList;
    }
//    @PostConstruct
//    public void init() {
//        try {
//            String token = keepRecord.getToken();
//            adminClient = new AdminClient(token);
//            loadPayouts();
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage("Error initializing PayoutBean: " + e.getMessage()));
//        }
//    }
    
    @PostConstruct
    public void init() {
        try {
            String token = keepRecord.getToken();
            adminClient = new AdminClient(token);

            loadPayouts();  // Load all payouts for admin
            userClient = new UserClient(token);

            // Load user payouts ONLY when user is logged in
            if (keepRecord.getUid()!= null) {
                int uidInt = Integer.parseInt(keepRecord.getUid());
                loadUserPayouts(uidInt);
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Error initializing PayoutBean: " + e.getMessage()));
        }
    }
    
        // ADMIN — Load all payouts

     public void loadPayouts() {
        try {
            payoutList = adminClient.getAllPayouts();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Error loading payouts: " + e.getMessage()));
        }
    }
    
     // ------------------------------
    // USER — Load payouts for logged-in user
    // ------------------------------
    public void loadUserPayouts(int uid) {
        try {
            userPayoutList = userClient.getUserPayouts(
                new jakarta.ws.rs.core.GenericType<Collection<Payouts>>() {},
                String.valueOf(uid)
            );
            
            // Calculate total return
            totalReturns = BigDecimal.ZERO;
            if (userPayoutList != null) {
                for (Payouts p : userPayoutList) {
                    if (p.getAmount() != null) {  // assuming Payouts has getAmount()
                        totalReturns = totalReturns.add(p.getAmount());
                    }
                }
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Error loading user payouts: " + e.getMessage()));
        }
    }


    public Collection<Payouts> getPayoutList() {
        return payoutList;
    }

    public void setPayoutList(Collection<Payouts> payoutList) {
        this.payoutList = payoutList;
    }
    /**
     * Creates a new instance of PayoutBean
     */
    public PayoutBean() {
    }
    
}
