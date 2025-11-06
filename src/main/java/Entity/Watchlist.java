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
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "watchlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Watchlist.findAll", query = "SELECT w FROM Watchlist w"),
    @NamedQuery(name = "Watchlist.findByWid", query = "SELECT w FROM Watchlist w WHERE w.wid = :wid"),
    @NamedQuery(name = "Watchlist.findByUser", query = "SELECT w FROM Watchlist w WHERE w.uid.uid = :uid"),
    @NamedQuery(name = "Watchlist.findByAsset", query = "SELECT w FROM Watchlist w WHERE w.aid.aid = :aid"),
    @NamedQuery(name = "Watchlist.findByAddedAt", query = "SELECT w FROM Watchlist w WHERE w.addedAt = :addedAt")})
public class Watchlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wid")
    private Integer wid;
    @Column(name = "added_at" ,insertable=false, updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    @ManyToOne
    private Assets aid;
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    @ManyToOne
    private Users uid;

    public Watchlist() {
    }

    public Watchlist(Integer wid) {
        this.wid = wid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
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
        hash += (wid != null ? wid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Watchlist)) {
            return false;
        }
        Watchlist other = (Watchlist) object;
        if ((this.wid == null && other.wid != null) || (this.wid != null && !this.wid.equals(other.wid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Watchlist[ wid=" + wid + " ]";
    }
    
}
