/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "detallecotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecotizacion.findAll", query = "SELECT d FROM Detallecotizacion d")
    , @NamedQuery(name = "Detallecotizacion.findByIddetallecotizacion", query = "SELECT d FROM Detallecotizacion d WHERE d.iddetallecotizacion = :iddetallecotizacion")
    , @NamedQuery(name = "Detallecotizacion.findByPrecio", query = "SELECT d FROM Detallecotizacion d WHERE d.precio = :precio")
    , @NamedQuery(name = "Detallecotizacion.findByCantida", query = "SELECT d FROM Detallecotizacion d WHERE d.cantida = :cantida")})
public class Detallecotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDETALLECOTIZACION")
    private Integer iddetallecotizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDA")
    private int cantida;
    @JoinColumn(name = "COTIZACIONID", referencedColumnName = "IDCOTIZACION")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cotizacion cotizacionid;
    @JoinColumn(name = "INSUMOID", referencedColumnName = "IDINSUMO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Insumo insumoid;

    public Detallecotizacion() {
    }

    public Detallecotizacion(Integer iddetallecotizacion) {
        this.iddetallecotizacion = iddetallecotizacion;
    }

    public Detallecotizacion(Integer iddetallecotizacion, double precio, int cantida) {
        this.iddetallecotizacion = iddetallecotizacion;
        this.precio = precio;
        this.cantida = cantida;
    }

    public Integer getIddetallecotizacion() {
        return iddetallecotizacion;
    }

    public void setIddetallecotizacion(Integer iddetallecotizacion) {
        this.iddetallecotizacion = iddetallecotizacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public Cotizacion getCotizacionid() {
        return cotizacionid;
    }

    public void setCotizacionid(Cotizacion cotizacionid) {
        this.cotizacionid = cotizacionid;
    }

    public Insumo getInsumoid() {
        return insumoid;
    }

    public void setInsumoid(Insumo insumoid) {
        this.insumoid = insumoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallecotizacion != null ? iddetallecotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecotizacion)) {
            return false;
        }
        Detallecotizacion other = (Detallecotizacion) object;
        if ((this.iddetallecotizacion == null && other.iddetallecotizacion != null) || (this.iddetallecotizacion != null && !this.iddetallecotizacion.equals(other.iddetallecotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallecotizacion[ iddetallecotizacion=" + iddetallecotizacion + " ]";
    }
    
}
