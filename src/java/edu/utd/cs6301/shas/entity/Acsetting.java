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
@Table(name = "ACSETTING")
@NamedQueries({
    @NamedQuery(name = "Acsetting.findAll", query = "SELECT a FROM Acsetting a"),
    @NamedQuery(name = "Acsetting.findBySettingid", query = "SELECT a FROM Acsetting a WHERE a.settingid = :settingid"),
    @NamedQuery(name = "Acsetting.findByTemperature", query = "SELECT a FROM Acsetting a WHERE a.temperature = :temperature"),
    @NamedQuery(name = "Acsetting.findByDayofweek", query = "SELECT a FROM Acsetting a WHERE a.dayofweek = :dayofweek"),
    @NamedQuery(name = "Acsetting.findByStarttime", query = "SELECT a FROM Acsetting a WHERE a.starttime = :starttime"),
    @NamedQuery(name = "Acsetting.findByEndtime", query = "SELECT a FROM Acsetting a WHERE a.endtime = :endtime")})
public class Acsetting implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETTINGID")
    private BigDecimal settingid;
    @Column(name = "TEMPERATURE")
    private BigInteger temperature;
    @Column(name = "DAYOFWEEK")
    private BigInteger dayofweek;
    @Size(max = 10)
    @Column(name = "STARTTIME")
    private String starttime;
    @Size(max = 10)
    @Column(name = "ENDTIME")
    private String endtime;
    @JoinColumn(name = "ACID", referencedColumnName = "ACID")
    @ManyToOne
    private Airconditioning acid;

    public Acsetting() {
    }

    public Acsetting(BigDecimal settingid) {
        this.settingid = settingid;
    }

    public BigDecimal getSettingid() {
        return settingid;
    }

    public void setSettingid(BigDecimal settingid) {
        this.settingid = settingid;
    }

    public BigInteger getTemperature() {
        return temperature;
    }

    public void setTemperature(BigInteger temperature) {
        this.temperature = temperature;
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

    public Airconditioning getAcid() {
        return acid;
    }

    public void setAcid(Airconditioning acid) {
        this.acid = acid;
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
        if (!(object instanceof Acsetting)) {
            return false;
        }
        Acsetting other = (Acsetting) object;
        if ((this.settingid == null && other.settingid != null) || (this.settingid != null && !this.settingid.equals(other.settingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.utd.cs6301.shas.entity.Acsetting[ settingid=" + settingid + " ]";
    }
    
}
