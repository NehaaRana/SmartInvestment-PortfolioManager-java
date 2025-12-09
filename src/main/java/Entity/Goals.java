/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.json.bind.annotation.JsonbDateFormat;
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
import jakarta.validation.constraints.NotNull;
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
@Table(name = "goals")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goals.findAll", query = "SELECT g FROM Goals g"),
    @NamedQuery(name = "Goals.findByGoalId", query = "SELECT g FROM Goals g WHERE g.goalId = :goalId"),
    @NamedQuery(name = "Goals.findByGoalName", query = "SELECT g FROM Goals g WHERE g.goalName = :goalName"),
    @NamedQuery(name = "Goals.findByTargetAmt", query = "SELECT g FROM Goals g WHERE g.targetAmt = :targetAmt"),
    @NamedQuery(name = "Goals.findByCurrentAmt", query = "SELECT g FROM Goals g WHERE g.currentAmt = :currentAmt"),
    @NamedQuery(name = "Goals.findByTargetDate", query = "SELECT g FROM Goals g WHERE g.targetDate = :targetDate"),
    @NamedQuery(name = "Goals.findByCreatedAt", query = "SELECT g FROM Goals g WHERE g.createdAt = :createdAt"),
    @NamedQuery(name = "Goals.findByUser", query = "SELECT g FROM Goals g WHERE g.uid.uid = :uid")
})
public class Goals implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "goal_id")
    private Integer goalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "goal_name")
    private String goalName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "target_amt")
    private BigDecimal targetAmt;
    @Column(name = "current_amt")
    private BigDecimal currentAmt;
//    @JsonbDateFormat("yyyy-MM-dd")
    @Column(name = "target_date")
//    @Temporal(TemporalType.DATE)
    private String targetDate;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_at",insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne
    private Users uid;

    public Goals() {
    }

    public Goals(Integer goalId) {
        this.goalId = goalId;
    }

    public Goals(Integer goalId, String goalName) {
        this.goalId = goalId;
        this.goalName = goalName;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public BigDecimal getTargetAmt() {
        return targetAmt;
    }

    public void setTargetAmt(BigDecimal targetAmt) {
        this.targetAmt = targetAmt;
    }

    public BigDecimal getCurrentAmt() {
        return currentAmt;
    }

    public void setCurrentAmt(BigDecimal currentAmt) {
        this.currentAmt = currentAmt;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (goalId != null ? goalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Goals)) {
            return false;
        }
        Goals other = (Goals) object;
        if ((this.goalId == null && other.goalId != null) || (this.goalId != null && !this.goalId.equals(other.goalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Goals[ goalId=" + goalId + " ]";
    }
    
}
