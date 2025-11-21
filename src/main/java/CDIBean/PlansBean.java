/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Assets;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Named(value = "plansBean")
@ViewScoped
public class PlansBean implements Serializable {
    
    private Collection<Assets> assetsList;
    
    @PostConstruct
    public void init() {
        loadPlans();
    }
    
    
     public void loadPlans() {
        try {
            AdminClient ac = new AdminClient();

            // CALL PUBLIC API (for guest user also)
            assetsList = ac.getAllAssetsPublic();

            System.out.println("----------- PUBLIC SIP PLANS LOADED -----------");
            System.out.println("Assets List Size: " + (assetsList != null ? assetsList.size() : "NULL"));
            System.out.println("Assets: " + assetsList);
            System.out.println("-----------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public Collection<Assets> getAssetsList() {
        return assetsList;
    }

    
    
    

    /**
     * Creates a new instance of PlansBean
     */
    public PlansBean() {
    }
    
}
