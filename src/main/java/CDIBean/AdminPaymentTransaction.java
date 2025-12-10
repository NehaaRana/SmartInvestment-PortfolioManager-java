/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Payments;
import Entity.Transactions;
import client.AdminClient;
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
 * @author Admin
 */
@Named(value = "adminPaymentTransaction")
@SessionScoped
public class AdminPaymentTransaction implements Serializable {

    /**
     * Creates a new instance of AdminPaymentTransaction
     */
    @Inject
    keepRecord keepRecord;

    private AdminClient adminClient;

    private List<Transactions> transactionList;
    private List<Payments> paymentsList;

    public List<Transactions> getTransactionList() {
        return transactionList;
    }
    
     @PostConstruct
    public void init() {
        adminClient = new AdminClient(keepRecord.getToken());
        transactionList = new ArrayList<>();
        paymentsList = new ArrayList<>();

        loadAllTransactions();
        loadAllPayments();
    }

    // ===================== LOAD ALL TRANSACTIONS =====================
    public void loadAllTransactions() {
        try {
            Transactions[] arr = adminClient.getAllTransactions(Transactions[].class);

            transactionList = new ArrayList<>();
            if (arr != null) {
                for (Transactions t : arr) {
                    transactionList.add(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===================== LOAD ALL PAYMENTS =====================
    public void loadAllPayments() {
        try {
            Payments[] arr = adminClient.getAllPayments(Payments[].class);

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
    
    
    
    public void setTransactionList(List<Transactions> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Payments> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payments> paymentsList) {
        this.paymentsList = paymentsList;
    }
    
    
    
    public AdminPaymentTransaction() {
    }
    
}
