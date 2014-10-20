/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Zheng
 */
@Entity
@Table(name = "SPRINKLER")
@NamedQueries({
    @NamedQuery(name = "Sprinkler.findAll", query = "SELECT s FROM Sprinkler s"),
    @NamedQuery(name = "Sprinkler.findBySprinklerid", query = "SELECT s FROM Sprinkler s WHERE s.sprinklerid = :sprinklerid"),
    @NamedQuery(name = "Sprinkler.findByDescription", query = "SELECT s FROM Sprinkler s WHERE s.description = :description"),
    @NamedQuery(name = "Sprinkler.findByControlmode", query = "SELECT s FROM Sprinkler s WHERE s.controlmode = :controlmode"),
    @NamedQuery(name = "Sprinkler.findByStatus", query = "SELECT s FROM Sprinkler s WHERE s.status = :status")})
public class Sprinkler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "SPRINKLERID")
    private String sprinklerid;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 10)
    @Column(name = "CONTROLMODE")
    private String controlmode;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @OneToMany(mappedBy = "sprinklerid", fetch = FetchType.EAGER)
    @OrderBy("dayofweek ASC")
    private List<Sprinklersetting> sprinklersettingCollection = new ArrayList<>();
    public Sprinkler() {
    }

    public Sprinkler(String sprinklerid) {
        this.sprinklerid = sprinklerid;
    }

    public String getSprinklerid() {
        return sprinklerid;
    }

    public void setSprinklerid(String sprinklerid) {
        this.sprinklerid = sprinklerid;
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

    public List<Sprinklersetting> getSprinklersettingCollection() {
        return sprinklersettingCollection;
    }

    public void setSprinklersettingCollection(List<Sprinklersetting> sprinklersettingCollection) {
        this.sprinklersettingCollection = sprinklersettingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sprinklerid != null ? sprinklerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprinkler)) {
            return false;
        }
        Sprinkler other = (Sprinkler) object;
        if ((this.sprinklerid == null && other.sprinklerid != null) || (this.sprinklerid != null && !this.sprinklerid.equals(other.sprinklerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ sprinklerid=" + sprinklerid + ", description= " + description + " mode= " + controlmode + " status= " + status + " ]";
    }
    
}
