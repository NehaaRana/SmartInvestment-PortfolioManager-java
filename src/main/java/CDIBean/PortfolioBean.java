/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Orders;
import Entity.Portfolio;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import record.keepRecord;

/**
 *
 * @author User
 */
@Named(value = "portfolioBean")
//@RequestScoped
@ViewScoped
public class PortfolioBean implements Serializable{

    /**
     * Creates a new instance of PortfolioBean
     */
    @Inject
    keepRecord keepRecord;

    private Integer pfId;
    private BigDecimal units;
    private BigDecimal avgPrice;
    private BigDecimal currentValue;
    private Date updatedAt;
    private List<Portfolio> portfolioList;
    private List<Orders> portfolioOrders;
    private String assetName; // from Assets table
    private String assetType; // e.g., Stock / MF / Crypto
    
    private Orders selectOrders;
    private String redemUnits;
    private Portfolio selectedPortfolio;

    public Portfolio getSelectedPortfolio() {
        return selectedPortfolio;
    }

    public void setSelectedPortfolio(Portfolio selectedPortfolio) {
        this.selectedPortfolio = selectedPortfolio;
    }
    

    public PortfolioBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            UserClient client = new UserClient(keepRecord.getToken());
            String uid = keepRecord.getUid();

            Orders[] orderArray = client.getUserOrders(Orders[].class, uid);
            Portfolio[] pfArray = client.getUserPortfolio(Portfolio[].class, uid);

            portfolioOrders = new ArrayList<>();
            if (orderArray != null) {
                for (Orders o : orderArray) {
                    if ("PAID".equalsIgnoreCase(o.getStatus())) {   // show only purchased orders
                        portfolioOrders.add(o);
                    }
                }
            }
            
            
            portfolioList = new ArrayList<>();
            if (pfArray != null) {
                for (Portfolio p : pfArray) {
                    portfolioList.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    public Orders getSelectOrders() {
        return selectOrders;
    }

    public void setSelectOrders(Orders selectOrders) {
        this.selectOrders = selectOrders;
    }

    public String getRedemUnits() {
        return redemUnits;
    }

////            Portfolio p = uc.getUserPortfolio(Portfolio.class, uid);
//              portfolioList = uc.getUserPortfolio(List.class, uid);
////            if (p != null) {
    ////                this.pfId = p.getPfId();
    ////                this.units = p.getUnits();
    ////                this.avgPrice = p.getAvgPrice();
    ////                this.currentValue = p.getCurrentValue();
    ////                this.updatedAt = p.getUpdatedAt();
    ////
    ////                // From Assets table (ManyToOne)
    ////                if (p.getAid() != null) {
    ////                    this.assetName = p.getAid().getName();
    ////                    this.assetType = p.getAid().getType();
    ////                }
    ////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void openRedeemDialog(Orders order) {
//        this.selectOrders = order;
//    }
    
    public void openRedeemDialog(Portfolio p) {
    this.selectedPortfolio = p;  
}


    public void submitRedemption() {
        try {
            UserClient client = new UserClient(keepRecord.getToken());

            // calling your existing method
            client.requestRedemption(
                    String.valueOf(keepRecord.getUid()),
                    String.valueOf(selectedPortfolio.getAid().getAid()),
                    redemUnits,
                    "PENDING"
            );

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Redemption request submitted successfully!")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public BigDecimal profitLossAmount(Portfolio p) {
        BigDecimal invested = p.getUnits().multiply(p.getAvgPrice());
        BigDecimal currentVal = p.getUnits().multiply(p.getAid().getCurrentPrice());
        return currentVal.subtract(invested);   // Can be +ve or -ve
    }

    public void setRedemUnits(String redemUnits) {
        this.redemUnits = redemUnits;
    }

    public List<Orders> getPortfolioOrders() {
        return portfolioOrders;
    }

    public void setPortfolioOrders(List<Orders> portfolioOrders) {
        this.portfolioOrders = portfolioOrders;
    }

    
    public List<Portfolio> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }
    

    public Integer getPfId() {
        return pfId;
    }

    public void setPfId(Integer pfId) {
        this.pfId = pfId;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }
    
//    Portfolio bean current value
    public BigDecimal calculateCurrentValue(Portfolio p) {
        return p.getUnits().multiply(p.getAid().getCurrentPrice());
    }
    
    public boolean inProfit(Portfolio p) {
        BigDecimal invested = p.getUnits().multiply(p.getAvgPrice());
        BigDecimal currentVal = p.getUnits().multiply(p.getAid().getCurrentPrice());
        return currentVal.compareTo(invested) > 0;
    }
    
    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }  
}
