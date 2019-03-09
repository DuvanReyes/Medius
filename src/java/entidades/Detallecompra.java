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
@Table(name = "detallecompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecompra.findAll", query = "SELECT d FROM Detallecompra d")
    , @NamedQuery(name = "Detallecompra.findByIddetallecompra", query = "SELECT d FROM Detallecompra d WHERE d.iddetallecompra = :iddetallecompra")
    , @NamedQuery(name = "Detallecompra.findByPrecio", query = "SELECT d FROM Detallecompra d WHERE d.precio = :precio")
    , @NamedQuery(name = "Detallecompra.findByCantidad", query = "SELECT d FROM Detallecompra d WHERE d.cantidad = :cantidad")})
public class Detallecompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDETALLECOMPRA")
    private Integer iddetallecompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @JoinColumn(name = "COMPRAID", referencedColumnName = "IDCOMPRA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Compra compraid;
    @JoinColumn(name = "BODEGAID", referencedColumnName = "IDBODEGA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Bodega bodegaid;
    @JoinColumn(name = "INSUMOID", referencedColumnName = "IDINSUMO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Insumo insumoid;

    public Detallecompra() {
    }

    public Detallecompra(Integer iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }

    public Detallecompra(Integer iddetallecompra, double precio, int cantidad) {
        this.iddetallecompra = iddetallecompra;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Integer getIddetallecompra() {
        return iddetallecompra;
    }

    public void setIddetallecompra(Integer iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompraid() {
        return compraid;
    }

    public void setCompraid(Compra compraid) {
        this.compraid = compraid;
    }

    public Bodega getBodegaid() {
        return bodegaid;
    }

    public void setBodegaid(Bodega bodegaid) {
        this.bodegaid = bodegaid;
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
        hash += (iddetallecompra != null ? iddetallecompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecompra)) {
            return false;
        }
        Detallecompra other = (Detallecompra) object;
        if ((this.iddetallecompra == null && other.iddetallecompra != null) || (this.iddetallecompra != null && !this.iddetallecompra.equals(other.iddetallecompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallecompra[ iddetallecompra=" + iddetallecompra + " ]";
    }
    
}
