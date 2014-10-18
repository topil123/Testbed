/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Zheng
 */
@Entity
@Table(name = "AIRCONDITIONING")
@NamedQueries({
    @NamedQuery(name = "Airconditioning.findAll", query = "SELECT a FROM Airconditioning a"),
    @NamedQuery(name = "Airconditioning.findByAcid", query = "SELECT a FROM Airconditioning a WHERE a.acid = :acid"),
    @NamedQuery(name = "Airconditioning.findByDescription", query = "SELECT a FROM Airconditioning a WHERE a.description = :description"),
    @NamedQuery(name = "Airconditioning.findByControlmode", query = "SELECT a FROM Airconditioning a WHERE a.controlmode = :controlmode"),
    @NamedQuery(name = "Airconditioning.findByStatus", query = "SELECT a FROM Airconditioning a WHERE a.status = :status"),
    @NamedQuery(name = "Airconditioning.findByDefaulttemperature", query = "SELECT a FROM Airconditioning a WHERE a.defaulttemperature = :defaulttemperature")})
public class Airconditioning implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "ACID")
    private String acid;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 10)
    @Column(name = "CONTROLMODE")
    private String controlmode;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DEFAULTTEMPERATURE")
    private Integer defaulttemperature;
    @Column(name = "CURRENTTEMPERATURE")
    private Integer currenttemperature;
    @OneToMany(mappedBy = "acid")
    private Collection<Acsetting> acsettingCollection;

    public Airconditioning() {
    }

    public Airconditioning(String acid) {
        this.acid = acid;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getControlmode() {
        return controlmode;
    }

    public void setControlmode(String controlmode) {
        this.controlmode = controlmode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDefaulttemperature() {
        return defaulttemperature;
    }

    public void setDefaulttemperature(Integer defaulttemperature) {
        this.defaulttemperature = defaulttemperature;
    }

    public Collection<Acsetting> getAcsettingCollection() {
        return acsettingCollection;
    }

    public void setAcsettingCollection(Collection<Acsetting> acsettingCollection) {
        this.acsettingCollection = acsettingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acid != null ? acid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airconditioning)) {
            return false;
        }
        Airconditioning other = (Airconditioning) object;
        if ((this.acid == null && other.acid != null) || (this.acid != null && !this.acid.equals(other.acid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.utd.cs6301.shas.entity.Airconditioning[ acid=" + acid + " ]";
    }

    public Integer getCurrenttemperature() {
        return currenttemperature;
    }

    public void setCurrenttemperature(Integer currenttemperature) {
        this.currenttemperature = currenttemperature;
    }
    
}
