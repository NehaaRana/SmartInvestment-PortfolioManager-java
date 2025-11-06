/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "portfolio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portfolio.findAll", query = "SELECT p FROM Portfolio p"),
    @NamedQuery(name = "Portfolio.findByPfId", query = "SELECT p FROM Portfolio p WHERE p.pfId = :pfId"),
    @NamedQuery(name = "Portfolio.findByUnits", query = "SELECT p FROM Portfolio p WHERE p.units = :units"),
    @NamedQuery(name = "Portfolio.findByAvgPrice", query = "SELECT p FROM Portfolio p WHERE p.avgPrice = :avgPrice"),
    @NamedQuery(name = "Portfolio.findByCurrentValue", query = "SELECT p FROM Portfolio p WHERE p.currentValue = :currentValue"),
    @NamedQuery(name = "Portfolio.findByUser",query = "SELECT p FROM Portfolio p WHERE p.uid.uid = :uid"),
    @NamedQuery(name = "Portfolio.findByAsset",query = "SELECT p FROM Portfolio p WHERE p.aid.aid = :aid"),
    @NamedQuery(name = "Portfolio.findByUserAsset",query = "SELECT p FROM Portfolio p WHERE p.uid.uid = :uid AND p.aid.aid = :aid"),
    @NamedQuery(name = "Portfolio.findByUpdatedAt", query = "SELECT p FROM Portfolio p WHERE p.updatedAt = :updatedAt")})
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pf_id")
    private Integer pfId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "units")
    private BigDecimal units;
    @Column(name = "avg_price")
    private BigDecimal avgPrice;
    @Column(name = "current_value")
    private BigDecimal currentValue;
    @Column(name = "updated_at",insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    @ManyToOne
    private Assets aid;
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne
    private Users uid;

    public Portfolio() {
    }

    public Portfolio(Integer pfId) {
        this.pfId = pfId;
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

    public Assets getAid() {
        return aid;
    }

    public void setAid(Assets aid) {
        this.aid = aid;
    }

    public Users getUid() {
        return uid;
    }

    public void setUid(Users uid) {
        this.uid = uid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pfId != null ? pfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.pfId == null && other.pfId != null) || (this.pfId != null && !this.pfId.equals(other.pfId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Portfolio[ pfId=" + pfId + " ]";
    }
    
}
