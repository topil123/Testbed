/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Zheng
 */
@Entity
@Table(name = "SPRINKLERSETTING")
@NamedQueries({
    @NamedQuery(name = "Sprinklersetting.findAll", query = "SELECT s FROM Sprinklersetting s"),
    @NamedQuery(name = "Sprinklersetting.findBySettingid", query = "SELECT s FROM Sprinklersetting s WHERE s.settingid = :settingid"),
    @NamedQuery(name = "Sprinklersetting.findByDayofweek", query = "SELECT s FROM Sprinklersetting s WHERE s.dayofweek = :dayofweek"),
    @NamedQuery(name = "Sprinklersetting.findByStarttime", query = "SELECT s FROM Sprinklersetting s WHERE s.starttime = :starttime"),
    @NamedQuery(name = "Sprinklersetting.findByEndtime", query = "SELECT s FROM Sprinklersetting s WHERE s.endtime = :endtime")})
public class Sprinklersetting implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "SETTINGID")
    private BigDecimal settingid;
    @Column(name = "DAYOFWEEK")
    private BigInteger dayofweek;
    @Size(max = 20)
    @Column(name = "STARTTIME")
    private String starttime;
    @Size(max = 20)
    @Column(name = "ENDTIME")
    private String endtime;
    @JoinColumn(name = "SPRINKLERID", referencedColumnName = "SPRINKLERID")
    @ManyToOne
    private Sprinkler sprinklerid;

    public Sprinklersetting() {
    }

    public Sprinklersetting(BigDecimal settingid) {
        this.settingid = settingid;
    }

    public BigDecimal getSettingid() {
        return settingid;
    }

    public void setSettingid(BigDecimal settingid) {
        this.settingid = settingid;
    }

    public BigInteger getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(BigInteger dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Sprinkler getSprinklerid() {
        return sprinklerid;
    }

    public void setSprinklerid(Sprinkler sprinklerid) {
        this.sprinklerid = sprinklerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (settingid != null ? settingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprinklersetting)) {
            return false;
        }
        Sprinklersetting other = (Sprinklersetting) object;
        if ((this.settingid == null && other.settingid != null) || (this.settingid != null && !this.settingid.equals(other.settingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ dayofweek =" + dayofweek + " start: " + starttime + " end: " + endtime + " ]";
    }
    
}
