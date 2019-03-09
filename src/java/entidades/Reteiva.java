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
@Table(name = "reteiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reteiva.findAll", query = "SELECT r FROM Reteiva r")
    , @NamedQuery(name = "Reteiva.findByIdreteiva", query = "SELECT r FROM Reteiva r WHERE r.idreteiva = :idreteiva")
    , @NamedQuery(name = "Reteiva.findByNombrereteiva", query = "SELECT r FROM Reteiva r WHERE r.nombrereteiva = :nombrereteiva")
    , @NamedQuery(name = "Reteiva.findByTasareteiva", query = "SELECT r FROM Reteiva r WHERE r.tasareteiva = :tasareteiva")})
public class Reteiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRETEIVA")
    private Integer idreteiva;
    @Basic(optional = false)

    @Column(name = "NOMBRERETEIVA")
    private String nombrereteiva;
    @Basic(optional = false)

    @Column(name = "TASARETEIVA")
    private double tasareteiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoreteivaid", fetch = FetchType.EAGER)
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumoreteivaid", fetch = FetchType.EAGER)
    private List<Insumo> insumoList;

    public Reteiva() {
    }

    public Reteiva(Integer idreteiva) {
        this.idreteiva = idreteiva;
    }

    public Reteiva(Integer idreteiva, String nombrereteiva, double tasareteiva) {
        this.idreteiva = idreteiva;
        this.nombrereteiva = nombrereteiva;
        this.tasareteiva = tasareteiva;
    }

    public Integer getIdreteiva() {
        return idreteiva;
    }

    public void setIdreteiva(Integer idreteiva) {
        this.idreteiva = idreteiva;
    }

    public String getNombrereteiva() {
        return nombrereteiva;
    }

    public void setNombrereteiva(String nombrereteiva) {
        this.nombrereteiva = nombrereteiva;
    }

    public double getTasareteiva() {
        return tasareteiva;
    }

    public void setTasareteiva(double tasareteiva) {
        this.tasareteiva = tasareteiva;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Insumo> getInsumoList() {
        return insumoList;
    }

    public void setInsumoList(List<Insumo> insumoList) {
        this.insumoList = insumoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreteiva != null ? idreteiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reteiva)) {
            return false;
        }
        Reteiva other = (Reteiva) object;
        if ((this.idreteiva == null && other.idreteiva != null) || (this.idreteiva != null && !this.idreteiva.equals(other.idreteiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Reteiva[ idreteiva=" + idreteiva + " ]";
    }
    
}
