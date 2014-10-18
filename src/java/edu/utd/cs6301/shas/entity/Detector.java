/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utd.cs6301.shas.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "DETECTOR")
@NamedQueries({
    @NamedQuery(name = "Detector.findAll", query = "SELECT d FROM Detector d"),
    @NamedQuery(name = "Detector.findByDetectorid", query = "SELECT d FROM Detector d WHERE d.detectorid = :detectorid"),
    @NamedQuery(name = "Detector.findByDescription", query = "SELECT d FROM Detector d WHERE d.description = :description"),
    @NamedQuery(name = "Detector.findByStatus", query = "SELECT d FROM Detector d WHERE d.status = :status")})
public class Detector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "DETECTORID")
    private String detectorid;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;

    public Detector() {
    }

    public Detector(String detectorid) {
        this.detectorid = detectorid;
    }

    public String getDetectorid() {
        return detectorid;
    }

    public void setDetectorid(String detectorid) {
        this.detectorid = detectorid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detectorid != null ? detectorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detector)) {
            return false;
        }
        Detector other = (Detector) object;
        if ((this.detectorid == null && other.detectorid != null) || (this.detectorid != null && !this.detectorid.equals(other.detectorid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.utd.cs6301.shas.entity.Detector[ detectorid=" + detectorid + " ]";
    }
    
}
