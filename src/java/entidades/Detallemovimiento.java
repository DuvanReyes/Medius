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
@Table(name = "detallemovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallemovimiento.findAll", query = "SELECT d FROM Detallemovimiento d")
    , @NamedQuery(name = "Detallemovimiento.findByIdmovimientodetalle", query = "SELECT d FROM Detallemovimiento d WHERE d.idmovimientodetalle = :idmovimientodetalle")
    , @NamedQuery(name = "Detallemovimiento.findByCantidad", query = "SELECT d FROM Detallemovimiento d WHERE d.cantidad = :cantidad")})
public class Detallemovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMOVIMIENTODETALLE")
    private Integer idmovimientodetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @JoinColumn(name = "MOVIMIENTOID", referencedColumnName = "IDMOVIMIENTO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Movimientoinventario movimientoid;
    @JoinColumn(name = "INSUMOID", referencedColumnName = "IDINSUMO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Insumo insumoid;

    public Detallemovimiento() {
    }

    public Detallemovimiento(Integer idmovimientodetalle) {
        this.idmovimientodetalle = idmovimientodetalle;
    }

    public Detallemovimiento(Integer idmovimientodetalle, int cantidad) {
        this.idmovimientodetalle = idmovimientodetalle;
        this.cantidad = cantidad;
    }

    public Integer getIdmovimientodetalle() {
        return idmovimientodetalle;
    }

    public void setIdmovimientodetalle(Integer idmovimientodetalle) {
        this.idmovimientodetalle = idmovimientodetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Movimientoinventario getMovimientoid() {
        return movimientoid;
    }

    public void setMovimientoid(Movimientoinventario movimientoid) {
        this.movimientoid = movimientoid;
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
        hash += (idmovimientodetalle != null ? idmovimientodetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallemovimiento)) {
            return false;
        }
        Detallemovimiento other = (Detallemovimiento) object;
        if ((this.idmovimientodetalle == null && other.idmovimientodetalle != null) || (this.idmovimientodetalle != null && !this.idmovimientodetalle.equals(other.idmovimientodetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallemovimiento[ idmovimientodetalle=" + idmovimientodetalle + " ]";
    }
    
}
