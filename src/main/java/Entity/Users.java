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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUid", query = "SELECT u FROM Users u WHERE u.uid = :uid"),
    @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt")})
public class Users implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "full_name")
    private String fullName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Payouts> payoutsCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Portfolio> portfolioCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Redemptions> redemptionsCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Payments> paymentsCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Transactions> transactionsCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Watchlist> watchlistCollection;
    @OneToMany(mappedBy = "uid")
    @JsonbTransient
    private Collection<Goals> goalsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Integer uid;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "gid", referencedColumnName = "id")
    @ManyToOne
    private GroupMaster gid;

    public Users() {
    }

    public Users(Integer uid) {
        this.uid = uid;
    }

    public Users(Integer uid, String fullName, String email, String password) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public GroupMaster getGid() {
        return gid;
    }

    public void setGid(GroupMaster gid) {
        this.gid = gid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ uid=" + uid + " ]";
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlTransient
    public Collection<Payouts> getPayoutsCollection() {
        return payoutsCollection;
    }

    public void setPayoutsCollection(Collection<Payouts> payoutsCollection) {
        this.payoutsCollection = payoutsCollection;
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
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    @XmlTransient
    public Collection<Watchlist> getWatchlistCollection() {
        return watchlistCollection;
    }

    public void setWatchlistCollection(Collection<Watchlist> watchlistCollection) {
        this.watchlistCollection = watchlistCollection;
    }

    @XmlTransient
    public Collection<Goals> getGoalsCollection() {
        return goalsCollection;
    }

    public void setGoalsCollection(Collection<Goals> goalsCollection) {
        this.goalsCollection = goalsCollection;
    }
    
}
