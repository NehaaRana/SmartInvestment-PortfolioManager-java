/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Orders;
import Entity.Transactions;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import record.keepRecord;

/**
 *
 * @author User
 */
@Named(value = "orderBean")
@SessionScoped
public class OrderBean implements Serializable {

    @Inject
    private keepRecord keepRecord;
    
    private UserClient userclient;
    
    private List<Orders> ordersList;
    /**
     * Creates a new instance of OrderBean
     */
    private int units;
    private double amount;
    private int assetId;
    private String orderType = "buy";

    public Orders getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }
    
    private Orders selectedOrder;
    private String paymentMethod;
    private String referenceNo;


    @PostConstruct
    public void init() {
        userclient = new UserClient(keepRecord.getToken());
        ordersList = new ArrayList<>();
        loadUserOrders();
    }

    public void openOrderDialog(int aid) {
        this.assetId = aid;
        this.units=0;
        this.amount=0;
    }

    // Load user orders
    public void loadUserOrders() {
        try {
            String uid = keepRecord.getUid();
            System.out.println("Fetching orders for UID: " + uid);
                    
            if (uid != null && !uid.isEmpty()) {
                Orders[] orderArray = userclient.getUserOrders(Orders[].class, uid);
                ordersList = new ArrayList<>();
                if (orderArray != null) {
                    for (Orders o : orderArray) {
                        ordersList.add(o);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeOrder() {
        try {
            if(keepRecord.getUid() == null || keepRecord.getUid().isEmpty()){
                System.out.println("User not logged in . recirect to login.");
                return ;
            }

            // Call REST API
            userclient.placeOrder(
                    keepRecord.getUid(),
                    String.valueOf(assetId),
                    orderType,
                    String.valueOf(units),
                    String.valueOf(amount)
//                    keepRecord.getToken()
            );
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Order placed successfully ✔"));
            
            loadUserOrders();
            
            System.out.println("Order placed successfully!");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to place order ❌"));
            System.out.println("Error placing order: " + e.getMessage());
        }
    }
    
    public void calculateAmount() {
        try {
            // Fetch asset details by ID
            Entity.Assets asset = userclient.getAssetById(Entity.Assets.class, String.valueOf(assetId));

            if (asset != null && asset.getCurrentPrice() != null) {
                this.amount = units * asset.getCurrentPrice().doubleValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Payment and Transaction
    
        // Prepare payment: set selected order
    public void preparePayment(Orders order) {
        this.selectedOrder = order;
        this.paymentMethod = null;
        this.referenceNo = null;
    }
    
     // Make Payment
    // Make Payment
//    public void makePayment() {
//        try {
//            int uid = selectedOrder.getUid().getUid(); // user id
//            int oid = selectedOrder.getOid();
//            BigDecimal amount = selectedOrder.getAmount();
//
//            // Step 1: Record Transaction
//            userclient.recordTransaction(
//                    String.valueOf(oid),
//                    String.valueOf(uid),
//                    String.valueOf(amount),
//                    "ORDER_PAYMENT",
//                    "PENDING"
//            );
//
//            // Step 2: Make Payment
//            // For simplicity, using same tid as oid (if you have proper txn id, replace here)
//            userclient.makePayment(
//                    String.valueOf(uid),
//                    String.valueOf(oid), // Use real transaction id if available
//                    paymentMethod,
//                    referenceNo,
//                    "SUCCESS"
//            );
//
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Payment Successful", null));
//
//            // Reload orders to update status
//            loadUserOrders();
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Payment Failed: " + e.getMessage(), null));
//            e.printStackTrace();
//        }
//    }
    
    public void makePayment() {
        try {
            int uid = selectedOrder.getUid().getUid();
            int oid = selectedOrder.getOid();
            BigDecimal amount = selectedOrder.getAmount();

            // STEP 1 → Record Transaction and get back TID
            Transactions txn = userclient.recordTransaction(
                    Transactions.class,
                    String.valueOf(oid),
                    String.valueOf(uid),
                    String.valueOf(amount),
                    paymentMethod.toUpperCase(),   //  store user-selected method
                    "SUCCESS"
            );

            int tid = txn.getTid(); // ⭐ REAL TID HERE

            // STEP 2 → Make Payment using correct TID
            userclient.makePayment(
                    String.valueOf(uid),
                    String.valueOf(tid),
                    paymentMethod,
                    referenceNo,
                    "SUCCESS"
            );

             // STEP 3 → Update Order Status to PAID
            boolean updated = userclient.updateOrderStatus(
                    String.valueOf(oid),
                    "PAID"
            );

            if (updated) {
                selectedOrder.setStatus("PAID");  // Update UI object
            }


            // ⭐ STEP 4 → Refresh table
            loadUserOrders();
            
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Payment Successful", null)
            );

            loadUserOrders();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Payment Failed: " + e.getMessage(), null)
            );
            e.printStackTrace();
        }
    }



//    private void recordTransaction(int oid, int uid, BigDecimal amount, String txnType, String status) {
//        userclient.recordTransaction(
//            String.valueOf(oid),
//            String.valueOf(uid),
//            String.valueOf(amount),
//            txnType,
//            status
//        );
//    }

   
    
    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
    
    
   
    public OrderBean() {
        userclient = new UserClient();
    }
    
}
