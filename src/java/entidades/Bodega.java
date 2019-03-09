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
@Table(name = "bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodega.findAll", query = "SELECT b FROM Bodega b")
    , @NamedQuery(name = "Bodega.findByIdbodega", query = "SELECT b FROM Bodega b WHERE b.idbodega = :idbodega")
    , @NamedQuery(name = "Bodega.findByBodeganombre", query = "SELECT b FROM Bodega b WHERE b.bodeganombre = :bodeganombre")
    , @NamedQuery(name = "Bodega.findByEstadobodega", query = "SELECT b FROM Bodega b WHERE b.estadobodega = :estadobodega")})
public class Bodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBODEGA")
    private Integer idbodega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BODEGANOMBRE")
    private String bodeganombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOBODEGA")
    private int estadobodega;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaid", fetch = FetchType.EAGER)
    private List<Detallecompra> detallecompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaid", fetch = FetchType.EAGER)
    private List<Movimientoinventario> movimientoinventarioList;
    @OneToMany(mappedBy = "bodegadestinoid", fetch = FetchType.EAGER)
    private List<Movimientoinventario> movimientoinventarioList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaid", fetch = FetchType.EAGER)
    private List<Bodegainsumo> bodegainsumoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodegaid", fetch = FetchType.EAGER)
    private List<Bodegaproducto> bodegaproductoList;

    public Bodega() {
    }

    public Bodega(Integer idbodega) {
        this.idbodega = idbodega;
    }

    public Bodega(Integer idbodega, String bodeganombre, int estadobodega) {
        this.idbodega = idbodega;
        this.bodeganombre = bodeganombre;
        this.estadobodega = estadobodega;
    }

    public Integer getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Integer idbodega) {
        this.idbodega = idbodega;
    }

    public String getBodeganombre() {
        return bodeganombre;
    }

    public void setBodeganombre(String bodeganombre) {
        this.bodeganombre = bodeganombre;
    }

    public int getEstadobodega() {
        return estadobodega;
    }

    public void setEstadobodega(int estadobodega) {
        this.estadobodega = estadobodega;
    }

    @XmlTransient
    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }

    @XmlTransient
    public List<Movimientoinventario> getMovimientoinventarioList() {
        return movimientoinventarioList;
    }

    public void setMovimientoinventarioList(List<Movimientoinventario> movimientoinventarioList) {
        this.movimientoinventarioList = movimientoinventarioList;
    }

    @XmlTransient
    public List<Movimientoinventario> getMovimientoinventarioList1() {
        return movimientoinventarioList1;
    }

    public void setMovimientoinventarioList1(List<Movimientoinventario> movimientoinventarioList1) {
        this.movimientoinventarioList1 = movimientoinventarioList1;
    }

    @XmlTransient
    public List<Bodegainsumo> getBodegainsumoList() {
        return bodegainsumoList;
    }

    public void setBodegainsumoList(List<Bodegainsumo> bodegainsumoList) {
        this.bodegainsumoList = bodegainsumoList;
    }

    @XmlTransient
    public List<Bodegaproducto> getBodegaproductoList() {
        return bodegaproductoList;
    }

    public void setBodegaproductoList(List<Bodegaproducto> bodegaproductoList) {
        this.bodegaproductoList = bodegaproductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbodega != null ? idbodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodega)) {
            return false;
        }
        Bodega other = (Bodega) object;
        if ((this.idbodega == null && other.idbodega != null) || (this.idbodega != null && !this.idbodega.equals(other.idbodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bodega[ idbodega=" + idbodega + " ]";
    }
    
}
