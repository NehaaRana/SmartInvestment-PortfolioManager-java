/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Payments;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import record.keepRecord;

/**
 *
 * @author Tax
 */
@Named(value = "paymentBean")
@SessionScoped
public class PaymentBean implements Serializable {
    
    @Inject
    keepRecord keepRecord;

    private UserClient userclient;

    private List<Payments> paymentsList;

    public List<Payments> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payments> paymentsList) {
        this.paymentsList = paymentsList;
    }
    
    /**
     * Creates a new instance of PaymentBean
     */
    
     @PostConstruct
    public void init() {
        userclient = new UserClient(keepRecord.getToken());
        paymentsList = new ArrayList<>();
        loadUserPayments();
    }
    
    public void loadUserPayments() {
        try {
            String uid = keepRecord.getUid();
            Payments[] arr = userclient.getUserPayments(Payments[].class, uid);
            paymentsList = new ArrayList<>();

            if (arr != null) {
                for (Payments p : arr) {
                    paymentsList.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PaymentBean() {
    }
    
}
