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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "payouts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payouts.findAll", query = "SELECT p FROM Payouts p"),
    @NamedQuery(name = "Payouts.findByPayId", query = "SELECT p FROM Payouts p WHERE p.payId = :payId"),
    @NamedQuery(name = "Payouts.findByAmount", query = "SELECT p FROM Payouts p WHERE p.amount = :amount"),
    @NamedQuery(name = "Payouts.findByReferenceNo", query = "SELECT p FROM Payouts p WHERE p.referenceNo = :referenceNo"),
    @NamedQuery(name = "Payouts.findByStatus", query = "SELECT p FROM Payouts p WHERE p.status = :status"),
    @NamedQuery(name = "Payouts.findByCreatedAt", query = "SELECT p FROM Payouts p WHERE p.createdAt = :createdAt")})
public class Payouts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pay_id")
    private Integer payId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @Size(max = 100)
    @Column(name = "reference_no")
    private String referenceNo;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    @ManyToOne
    private Redemptions rid;
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne
    private Users uid;

    public Payouts() {
    }

    public Payouts(Integer payId) {
        this.payId = payId;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Redemptions getRid() {
        return rid;
    }

    public void setRid(Redemptions rid) {
        this.rid = rid;
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
        hash += (payId != null ? payId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payouts)) {
            return false;
        }
        Payouts other = (Payouts) object;
        if ((this.payId == null && other.payId != null) || (this.payId != null && !this.payId.equals(other.payId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Payouts[ payId=" + payId + " ]";
    }
    
}
