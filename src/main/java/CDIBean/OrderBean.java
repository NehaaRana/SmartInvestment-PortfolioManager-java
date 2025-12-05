/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Orders;
import client.UserClient;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
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
    
    UserClient userclient;
    /**
     * Creates a new instance of OrderBean
     */
    private int units;
    private double amount;
    private int assetId;
    private String orderType = "buy";

    public void openOrderDialog(int aid) {
        this.assetId = aid;
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
            );

            System.out.println("Order placed successfully!");
        } catch (Exception e) {
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
    
   
    public OrderBean() {
        userclient = new UserClient();
    }
    
}
