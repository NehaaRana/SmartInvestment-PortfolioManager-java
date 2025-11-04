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
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Named(value = "assetManagedBean")
@SessionScoped
public class AssetManagedBean implements Serializable{
    
    @EJB AssetsBeanLocal abl;
    Integer aid;
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
    
//    Edit
    public String edit(Integer aid){
              // Load the entity and copy into bean fields
        this.aid = aid;
        Assets a = abl.findAssetsById(aid); // add this helper in the EJB (see section 2)
        if (a != null) {
            this.symbol = a.getSymbol();
            this.name = a.getName();
            this.type = a.getType();
            // currentPrice is BigDecimal in entity
            this.current_price = (a.getCurrentPrice() != null) ? a.getCurrentPrice().doubleValue() : 0.0;
            this.status = a.getStatus();
            this.risk = a.getRisk();
            // go to edit page
            return "EditAsset?faces-redirect=true";
        }
        // fallback: back to list
        return "AllAssets?faces-redirect=true";
    }
    
      // ------- Update (submit form) -------
    public String update() {
        if (this.aid != null) {
            abl.updateAsset(aid, symbol, name, type, current_price, status, risk);
        }
        return "AllAssets?faces-redirect=true";
    }
    
    public String deleteAsset(Integer aid){
        abl.removeAsset(aid);
        return "AllAssets";
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
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
