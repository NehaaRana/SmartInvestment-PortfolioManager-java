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
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "assetManagedBean")
//@RequestScoped
@ViewScoped
public class AssetManagedBean implements Serializable {
    
    @Inject
    private keepRecord keepRecord;
    
    private int aid;
    private String symbol;
    private String name;
    private String type;
    private BigDecimal  current_price;
    private String status;
    private String risk;
    
    private Collection<Assets> assetsList;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
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

    public BigDecimal  getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(BigDecimal  current_price) {
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

    
    @PostConstruct
    public void loadAssets() {
        AdminClient ac = new AdminClient(keepRecord.getToken());
        assetsList = ac.getAllAssets();
    }

    public Collection<Assets> getAssetsList() {
        return assetsList;
    }
    
    
    public String addAsset(){
    
        AdminClient ac= new AdminClient(keepRecord.getToken());
        ac.addAsset(symbol, name, type,  current_price.toString(), status, risk);
        loadAssets();
        return "AllAssets?faces-redirect=true";
    }
    
     public String edit(int aid) {
        AdminClient ac = new AdminClient(keepRecord.getToken());
        Assets asset = ac.findAssetById(Assets.class, String.valueOf(aid));

        this.aid = asset.getAid();
        this.symbol = asset.getSymbol();
        this.name = asset.getName();
        this.type = asset.getType();
        this.current_price = asset.getCurrentPrice();
        this.status = asset.getStatus();
        this.risk = asset.getRisk();

        return "EditAsset?faces-redirect=true";
    }

    public String update() {
        AdminClient ac = new AdminClient(keepRecord.getToken());
        ac.updateAssetPrice(String.valueOf(aid), String.valueOf(current_price));
        ac.updateAssetStatus(String.valueOf(aid), status);
        loadAssets();
        return "AllAssets?faces-redirect=true";
    }
    
    
    public String deleteAsset(int aid) {
        AdminClient ac = new AdminClient(keepRecord.getToken());
        ac.deleteAsset(aid);   // calling REST delete
        loadAssets();
        return "AllAssets?faces-redirect=true";
    }

    
    
    /**
     * Creates a new instance of AssetManagedBean
     */
    public AssetManagedBean() {
    }
    
}
