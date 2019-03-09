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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oscar
 */
@Entity
@Table(name = "bodegainsumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodegainsumo.findAll", query = "SELECT b FROM Bodegainsumo b")
    , @NamedQuery(name = "Bodegainsumo.findByIdbodegainsumo", query = "SELECT b FROM Bodegainsumo b WHERE b.idbodegainsumo = :idbodegainsumo")
    , @NamedQuery(name = "Bodegainsumo.findByBodegainsumoentrada", query = "SELECT b FROM Bodegainsumo b WHERE b.bodegainsumoentrada = :bodegainsumoentrada")
    , @NamedQuery(name = "Bodegainsumo.findByBodegainsumosalida", query = "SELECT b FROM Bodegainsumo b WHERE b.bodegainsumosalida = :bodegainsumosalida")
    , @NamedQuery(name = "Bodegainsumo.findByBodegainsumototal", query = "SELECT b FROM Bodegainsumo b WHERE b.bodegainsumototal = :bodegainsumototal")})
public class Bodegainsumo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBODEGAINSUMO")
    private Integer idbodegainsumo;
    @Column(name = "BODEGAINSUMOENTRADA")
    private Integer bodegainsumoentrada;
    @Column(name = "BODEGAINSUMOSALIDA")
    private Integer bodegainsumosalida;
    @Column(name = "BODEGAINSUMOTOTAL")
    private Integer bodegainsumototal;
    @JoinColumn(name = "INSUMOID", referencedColumnName = "IDINSUMO")
    @ManyToOne(optional = false)
    private Insumo insumoid;
    @JoinColumn(name = "BODEGAID", referencedColumnName = "IDBODEGA")
    @ManyToOne(optional = false)
    private Bodega bodegaid;

    public Bodegainsumo() {
    }

    public Bodegainsumo(Integer idbodegainsumo) {
        this.idbodegainsumo = idbodegainsumo;
    }

    public Integer getIdbodegainsumo() {
        return idbodegainsumo;
    }

    public void setIdbodegainsumo(Integer idbodegainsumo) {
        this.idbodegainsumo = idbodegainsumo;
    }

    public Integer getBodegainsumoentrada() {
        return bodegainsumoentrada;
    }

    public void setBodegainsumoentrada(Integer bodegainsumoentrada) {
        this.bodegainsumoentrada = bodegainsumoentrada;
    }

    public Integer getBodegainsumosalida() {
        return bodegainsumosalida;
    }

    public void setBodegainsumosalida(Integer bodegainsumosalida) {
        this.bodegainsumosalida = bodegainsumosalida;
    }

    public Integer getBodegainsumototal() {
        return bodegainsumototal;
    }

    public void setBodegainsumototal(Integer bodegainsumototal) {
        this.bodegainsumototal = bodegainsumototal;
    }

    public Insumo getInsumoid() {
        return insumoid;
    }

    public void setInsumoid(Insumo insumoid) {
        this.insumoid = insumoid;
    }

    public Bodega getBodegaid() {
        return bodegaid;
    }

    public void setBodegaid(Bodega bodegaid) {
        this.bodegaid = bodegaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbodegainsumo != null ? idbodegainsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodegainsumo)) {
            return false;
        }
        Bodegainsumo other = (Bodegainsumo) object;
        if ((this.idbodegainsumo == null && other.idbodegainsumo != null) || (this.idbodegainsumo != null && !this.idbodegainsumo.equals(other.idbodegainsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bodegainsumo[ idbodegainsumo=" + idbodegainsumo + " ]";
    }
    
}