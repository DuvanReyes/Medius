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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "bodegaproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodegaproducto.findAll", query = "SELECT b FROM Bodegaproducto b")
    , @NamedQuery(name = "Bodegaproducto.findByProductoid", query = "SELECT b FROM Bodegaproducto b WHERE b.productoid = :productoid")
    , @NamedQuery(name = "Bodegaproducto.findByBodegaproductoentrada", query = "SELECT b FROM Bodegaproducto b WHERE b.bodegaproductoentrada = :bodegaproductoentrada")
    , @NamedQuery(name = "Bodegaproducto.findByBodegaproductosalida", query = "SELECT b FROM Bodegaproducto b WHERE b.bodegaproductosalida = :bodegaproductosalida")
    , @NamedQuery(name = "Bodegaproducto.findByBodegaproductototal", query = "SELECT b FROM Bodegaproducto b WHERE b.bodegaproductototal = :bodegaproductototal")})
public class Bodegaproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTOID")
    private Integer productoid;
    @Column(name = "BODEGAPRODUCTOENTRADA")
    private Integer bodegaproductoentrada;
    @Column(name = "BODEGAPRODUCTOSALIDA")
    private Integer bodegaproductosalida;
    @Column(name = "BODEGAPRODUCTOTOTAL")
    private Integer bodegaproductototal;
    @JoinColumn(name = "PRODUCTOID", referencedColumnName = "IDPRODUCTO", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Producto producto;
    @JoinColumn(name = "BODEGAID", referencedColumnName = "IDBODEGA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Bodega bodegaid;

    public Bodegaproducto() {
    }

    public Bodegaproducto(Integer productoid) {
        this.productoid = productoid;
    }

    public Integer getProductoid() {
        return productoid;
    }

    public void setProductoid(Integer productoid) {
        this.productoid = productoid;
    }

    public Integer getBodegaproductoentrada() {
        return bodegaproductoentrada;
    }

    public void setBodegaproductoentrada(Integer bodegaproductoentrada) {
        this.bodegaproductoentrada = bodegaproductoentrada;
    }

    public Integer getBodegaproductosalida() {
        return bodegaproductosalida;
    }

    public void setBodegaproductosalida(Integer bodegaproductosalida) {
        this.bodegaproductosalida = bodegaproductosalida;
    }

    public Integer getBodegaproductototal() {
        return bodegaproductototal;
    }

    public void setBodegaproductototal(Integer bodegaproductototal) {
        this.bodegaproductototal = bodegaproductototal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        hash += (productoid != null ? productoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodegaproducto)) {
            return false;
        }
        Bodegaproducto other = (Bodegaproducto) object;
        if ((this.productoid == null && other.productoid != null) || (this.productoid != null && !this.productoid.equals(other.productoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bodegaproducto[ productoid=" + productoid + " ]";
    }
    
}
