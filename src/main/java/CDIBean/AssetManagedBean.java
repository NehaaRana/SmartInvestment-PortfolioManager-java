/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Assets;
import ejb.AssetsBeanLocal;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Named(value = "assetManagedBean")
@RequestScoped
public class AssetManagedBean {
    
    @EJB AssetsBeanLocal abl;
    
    String symbol;
    String name;
    String type;
    double current_price;
    String status;
    String risk;
    
    
    public String addAsset(){
        abl.addAsset(symbol, name, type, current_price, status, risk);
        return "AllAssets";
    }
    
    public Collection<Assets> getAssetsList(){
        return abl.getAllAssets();
    }
    
    public String deleteAsset(Integer aid){
        abl.removeAsset(aid);
        return "AllAssets";
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
    
    
    
    /**
     * Creates a new instance of AssetManagedBean
     */
    public AssetManagedBean() {
    }
    
}
