/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
    , @NamedQuery(name = "Cotizacion.findByIdcotizacion", query = "SELECT c FROM Cotizacion c WHERE c.idcotizacion = :idcotizacion")
    , @NamedQuery(name = "Cotizacion.findByFechacreacioncotizacion", query = "SELECT c FROM Cotizacion c WHERE c.fechacreacioncotizacion = :fechacreacioncotizacion")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOTIZACION")
    private Integer idcotizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACREACIONCOTIZACION")
    @Temporal(TemporalType.DATE)
    private Date fechacreacioncotizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotizacionid", fetch = FetchType.EAGER)
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotizacionid", fetch = FetchType.EAGER)
    private List<Detallecotizacion> detallecotizacionList;
    @JoinColumn(name = "PROVEEDORID", referencedColumnName = "IDPROVEEDOR")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Proveedor proveedorid;

    public Cotizacion() {
    }

    public Cotizacion(Integer idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public Cotizacion(Integer idcotizacion, Date fechacreacioncotizacion) {
        this.idcotizacion = idcotizacion;
        this.fechacreacioncotizacion = fechacreacioncotizacion;
    }

    public Integer getIdcotizacion() {
        return idcotizacion;
    }

    public void setIdcotizacion(Integer idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public Date getFechacreacioncotizacion() {
        return fechacreacioncotizacion;
    }

    public void setFechacreacioncotizacion(Date fechacreacioncotizacion) {
        this.fechacreacioncotizacion = fechacreacioncotizacion;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<Detallecotizacion> getDetallecotizacionList() {
        return detallecotizacionList;
    }

    public void setDetallecotizacionList(List<Detallecotizacion> detallecotizacionList) {
        this.detallecotizacionList = detallecotizacionList;
    }

    public Proveedor getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(Proveedor proveedorid) {
        this.proveedorid = proveedorid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcotizacion != null ? idcotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.idcotizacion == null && other.idcotizacion != null) || (this.idcotizacion != null && !this.idcotizacion.equals(other.idcotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cotizacion[ idcotizacion=" + idcotizacion + " ]";
    }
    
}
