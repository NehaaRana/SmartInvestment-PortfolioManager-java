/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Transactions;
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
@Named(value = "transactionBean")
@SessionScoped
public class TransactionBean implements Serializable {
    
    @Inject
    keepRecord keepRecord;

    private UserClient userclient;

    private List<Transactions> transactionList;

      @PostConstruct
    public void init() {
        userclient = new UserClient(keepRecord.getToken());
        transactionList = new ArrayList<>();
        loadUserTransactions();
    }

//    public void loadUserTransactions() {
//        try {
//            String uid = keepRecord.getUid();
//            Transactions[] arr = userclient.getUserTransactions(Transactions[].class, uid);
//            transactionList = new ArrayList<>();
//            if (arr != null) {
//                for (Transactions t : arr) {
//                    transactionList.add(t);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public void loadUserTransactions() {
        try {
            String uid = keepRecord.getUid();

            System.out.println("===== TRANSACTION DEBUG =====");
            System.out.println("UID FROM SESSION: " + uid);

            if (uid == null || uid.isEmpty()) {
                System.out.println("❌ UID NULL - USER NOT LOGGED IN");
                return;
            }

            Transactions[] arr = userclient.getUserTransactions(Transactions[].class, uid);

            System.out.println("RESULT COUNT: " + (arr == null ? "NULL" : arr.length));

            transactionList = new ArrayList<>();
            if (arr != null) {
                for (Transactions t : arr) {
                    System.out.println("TXN: " + t.getTid());
                    transactionList.add(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
   
    
    /**
     * Creates a new instance of TransactionBean
     */
    
    public List<Transactions> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transactions> transactionList) {
        this.transactionList = transactionList;
    }
    public TransactionBean() {
    }
    
}
