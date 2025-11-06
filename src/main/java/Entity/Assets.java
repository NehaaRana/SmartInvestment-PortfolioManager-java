/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "assets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assets.findAll", query = "SELECT a FROM Assets a"),
    @NamedQuery(name = "Assets.findByAid", query = "SELECT a FROM Assets a WHERE a.aid = :aid"),
    @NamedQuery(name = "Assets.findBySymbol", query = "SELECT a FROM Assets a WHERE a.symbol = :symbol"),
    @NamedQuery(name = "Assets.findByName", query = "SELECT a FROM Assets a WHERE a.name = :name"),
    @NamedQuery(name = "Assets.findByType", query = "SELECT a FROM Assets a WHERE a.type = :type"),
    @NamedQuery(name = "Assets.findByCurrentPrice", query = "SELECT a FROM Assets a WHERE a.currentPrice = :currentPrice"),
    @NamedQuery(name = "Assets.findByStatus", query = "SELECT a FROM Assets a WHERE a.status = :status"),
    @NamedQuery(name = "Assets.findByRisk", query = "SELECT a FROM Assets a WHERE a.risk = :risk"),
    @NamedQuery(name = "Assets.findByCreatedAt", query = "SELECT a FROM Assets a WHERE a.createdAt = :createdAt")})
public class Assets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "aid")
    private Integer aid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "symbol")
    private String symbol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "risk")
    private String risk;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "aid")
    private Collection<Portfolio> portfolioCollection;
    @OneToMany(mappedBy = "aid")
    private Collection<Redemptions> redemptionsCollection;
    @JsonbTransient
    @OneToMany(mappedBy = "aid")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "aid")
    private Collection<Watchlist> watchlistCollection;

    public Assets() {
    }

    public Assets(Integer aid) {
        this.aid = aid;
    }

    public Assets(Integer aid, String symbol, String name) {
        this.aid = aid;
        this.symbol = symbol;
        this.name = name;
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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlTransient
    public Collection<Portfolio> getPortfolioCollection() {
        return portfolioCollection;
    }

    public void setPortfolioCollection(Collection<Portfolio> portfolioCollection) {
        this.portfolioCollection = portfolioCollection;
    }

    @XmlTransient
    public Collection<Redemptions> getRedemptionsCollection() {
        return redemptionsCollection;
    }

    public void setRedemptionsCollection(Collection<Redemptions> redemptionsCollection) {
        this.redemptionsCollection = redemptionsCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Watchlist> getWatchlistCollection() {
        return watchlistCollection;
    }

    public void setWatchlistCollection(Collection<Watchlist> watchlistCollection) {
        this.watchlistCollection = watchlistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assets)) {
            return false;
        }
        Assets other = (Assets) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Assets[ aid=" + aid + " ]";
    }
    
}
