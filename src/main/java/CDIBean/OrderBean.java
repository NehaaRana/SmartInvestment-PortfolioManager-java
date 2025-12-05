/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Orders;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
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
