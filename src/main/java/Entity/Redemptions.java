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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "redemptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Redemptions.findAll", query = "SELECT r FROM Redemptions r"),
    @NamedQuery(name = "Redemptions.findByRid", query = "SELECT r FROM Redemptions r WHERE r.rid = :rid"),
    @NamedQuery(name = "Redemptions.findByUnits", query = "SELECT r FROM Redemptions r WHERE r.units = :units"),
    @NamedQuery(name = "Redemptions.findByStatus", query = "SELECT r FROM Redemptions r WHERE r.status = :status"),
    @NamedQuery(name = "Redemptions.findByExitFeeAmt", query = "SELECT r FROM Redemptions r WHERE r.exitFeeAmt = :exitFeeAmt"),
    @NamedQuery(name = "Redemptions.findByRequestedAt", query = "SELECT r FROM Redemptions r WHERE r.requestedAt = :requestedAt"),
    @NamedQuery(name = "Redemptions.findByApprovedAt", query = "SELECT r FROM Redemptions r WHERE r.approvedAt = :approvedAt"),
    @NamedQuery(name = "Redemptions.findByPaidAt", query = "SELECT r FROM Redemptions r WHERE r.paidAt = :paidAt")})
public class Redemptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rid")
    private Integer rid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "units")
    private BigDecimal units;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "exit_fee_amt")
    private BigDecimal exitFeeAmt;
    @Column(name = "requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedAt;
    @Column(name = "approved_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedAt;
    @Column(name = "paid_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;
    @OneToMany(mappedBy = "rid")
    private Collection<Payouts> payoutsCollection;
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    @ManyToOne
    private Assets aid;
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne
    private Users uid;

    public Redemptions() {
    }

    public Redemptions(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getExitFeeAmt() {
        return exitFeeAmt;
    }

    public void setExitFeeAmt(BigDecimal exitFeeAmt) {
        this.exitFeeAmt = exitFeeAmt;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    @XmlTransient
    public Collection<Payouts> getPayoutsCollection() {
        return payoutsCollection;
    }

    public void setPayoutsCollection(Collection<Payouts> payoutsCollection) {
        this.payoutsCollection = payoutsCollection;
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
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Redemptions)) {
            return false;
        }
        Redemptions other = (Redemptions) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Redemptions[ rid=" + rid + " ]";
    }
    
}
