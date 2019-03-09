/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "retencionrenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencionrenta.findAll", query = "SELECT r FROM Retencionrenta r")
    , @NamedQuery(name = "Retencionrenta.findByIdretencionrenta", query = "SELECT r FROM Retencionrenta r WHERE r.idretencionrenta = :idretencionrenta")
    , @NamedQuery(name = "Retencionrenta.findByNombreretencionrenta", query = "SELECT r FROM Retencionrenta r WHERE r.nombreretencionrenta = :nombreretencionrenta")
    , @NamedQuery(name = "Retencionrenta.findByTasaretencionrenta", query = "SELECT r FROM Retencionrenta r WHERE r.tasaretencionrenta = :tasaretencionrenta")
    , @NamedQuery(name = "Retencionrenta.findByUvtretencionrenta", query = "SELECT r FROM Retencionrenta r WHERE r.uvtretencionrenta = :uvtretencionrenta")
    , @NamedQuery(name = "Retencionrenta.findByEstadoretencionrenta", query = "SELECT r FROM Retencionrenta r WHERE r.estadoretencionrenta = :estadoretencionrenta")})
public class Retencionrenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRETENCIONRENTA")
    private Integer idretencionrenta;
    @Basic(optional = false)


    @Column(name = "NOMBRERETENCIONRENTA")
    private String nombreretencionrenta;
    @Basic(optional = false)

    @Column(name = "TASARETENCIONRENTA")
    private double tasaretencionrenta;
    @Basic(optional = false)

    @Column(name = "UVTRETENCIONRENTA")
    private double uvtretencionrenta;
    @Basic(optional = false)
  
    @Column(name = "ESTADORETENCIONRENTA")
    private int estadoretencionrenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiporetencionrentaid", fetch = FetchType.EAGER)
    private List<Tercero> terceroList;

    public Retencionrenta() {
    }

    public Retencionrenta(Integer idretencionrenta) {
        this.idretencionrenta = idretencionrenta;
    }

    public Retencionrenta(Integer idretencionrenta, String nombreretencionrenta, double tasaretencionrenta, double uvtretencionrenta, int estadoretencionrenta) {
        this.idretencionrenta = idretencionrenta;
        this.nombreretencionrenta = nombreretencionrenta;
        this.tasaretencionrenta = tasaretencionrenta;
        this.uvtretencionrenta = uvtretencionrenta;
        this.estadoretencionrenta = estadoretencionrenta;
    }

    public Integer getIdretencionrenta() {
        return idretencionrenta;
    }

    public void setIdretencionrenta(Integer idretencionrenta) {
        this.idretencionrenta = idretencionrenta;
    }

    public String getNombreretencionrenta() {
        return nombreretencionrenta;
    }

    public void setNombreretencionrenta(String nombreretencionrenta) {
        this.nombreretencionrenta = nombreretencionrenta;
    }

    public double getTasaretencionrenta() {
        return tasaretencionrenta;
    }

    public void setTasaretencionrenta(double tasaretencionrenta) {
        this.tasaretencionrenta = tasaretencionrenta;
    }

    public double getUvtretencionrenta() {
        return uvtretencionrenta;
    }

    public void setUvtretencionrenta(double uvtretencionrenta) {
        this.uvtretencionrenta = uvtretencionrenta;
    }

    public int getEstadoretencionrenta() {
        return estadoretencionrenta;
    }

    public void setEstadoretencionrenta(int estadoretencionrenta) {
        this.estadoretencionrenta = estadoretencionrenta;
    }

    @XmlTransient
    public List<Tercero> getTerceroList() {
        return terceroList;
    }

    public void setTerceroList(List<Tercero> terceroList) {
        this.terceroList = terceroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idretencionrenta != null ? idretencionrenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencionrenta)) {
            return false;
        }
        Retencionrenta other = (Retencionrenta) object;
        if ((this.idretencionrenta == null && other.idretencionrenta != null) || (this.idretencionrenta != null && !this.idretencionrenta.equals(other.idretencionrenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Retencionrenta[ idretencionrenta=" + idretencionrenta + " ]";
    }
    
}
