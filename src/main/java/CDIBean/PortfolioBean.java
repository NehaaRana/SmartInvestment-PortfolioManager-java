/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Portfolio;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import record.keepRecord;

/**
 *
 * @author User
 */
@Named(value = "portfolioBean")
@RequestScoped
public class PortfolioBean {

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
    private String assetName; // from Assets table
    private String assetType; // e.g., Stock / MF / Crypto

    public PortfolioBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            String token = keepRecord.getToken();
            String uid = keepRecord.getUid();

            UserClient uc = new UserClient(token);

            // Call your API: returns *one Portfolio object*
//            Portfolio p = uc.getUserPortfolio(Portfolio.class, uid);
              portfolioList = uc.getUserPortfolio(List.class, uid);
//            if (p != null) {
//                this.pfId = p.getPfId();
//                this.units = p.getUnits();
//                this.avgPrice = p.getAvgPrice();
//                this.currentValue = p.getCurrentValue();
//                this.updatedAt = p.getUpdatedAt();
//
//                // From Assets table (ManyToOne)
//                if (p.getAid() != null) {
//                    this.assetName = p.getAid().getName();
//                    this.assetType = p.getAid().getType();
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
