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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author SQA
 */
@Entity
@Table(name = "insumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumo.findAll", query = "SELECT i FROM Insumo i")
    , @NamedQuery(name = "Insumo.findByIdinsumo", query = "SELECT i FROM Insumo i WHERE i.idinsumo = :idinsumo")
    , @NamedQuery(name = "Insumo.findByNombreinsumo", query = "SELECT i FROM Insumo i WHERE i.nombreinsumo = :nombreinsumo")
    , @NamedQuery(name = "Insumo.findByInsumocantidadminima", query = "SELECT i FROM Insumo i WHERE i.insumocantidadminima = :insumocantidadminima")
    , @NamedQuery(name = "Insumo.findByInsumocantidadmaxima", query = "SELECT i FROM Insumo i WHERE i.insumocantidadmaxima = :insumocantidadmaxima")
    , @NamedQuery(name = "Insumo.findByEstadoinsumo", query = "SELECT i FROM Insumo i WHERE i.estadoinsumo = :estadoinsumo")})
public class Insumo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINSUMO")
    private Integer idinsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBREINSUMO")
    private String nombreinsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSUMOCANTIDADMINIMA")
    private int insumocantidadminima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSUMOCANTIDADMAXIMA")
    private int insumocantidadmaxima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOINSUMO")
    private int estadoinsumo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumoid")
    private List<Detallecotizacion> detallecotizacionList;
    @JoinColumn(name = "INSUMORETEIVAID", referencedColumnName = "IDRETEIVA")
    @ManyToOne(optional = false)
    private Reteiva insumoreteivaid;
    @JoinColumn(name = "INSUMOMARCAID", referencedColumnName = "IDMARCA")
    @ManyToOne
    private Marca insumomarcaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumoid")
    private List<Detallecompra> detallecompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumoid")
    private List<Bodegainsumo> bodegainsumoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumoid")
    private List<Detallemovimiento> detallemovimientoList;

    public Insumo() {
    }

    public Insumo(Integer idinsumo) {
        this.idinsumo = idinsumo;
    }

    public Insumo(Integer idinsumo, String nombreinsumo, int insumocantidadminima, int insumocantidadmaxima, int estadoinsumo) {
        this.idinsumo = idinsumo;
        this.nombreinsumo = nombreinsumo;
        this.insumocantidadminima = insumocantidadminima;
        this.insumocantidadmaxima = insumocantidadmaxima;
        this.estadoinsumo = estadoinsumo;
    }

    public Integer getIdinsumo() {
        return idinsumo;
    }

    public void setIdinsumo(Integer idinsumo) {
        this.idinsumo = idinsumo;
    }

    public String getNombreinsumo() {
        return nombreinsumo;
    }

    public void setNombreinsumo(String nombreinsumo) {
        this.nombreinsumo = nombreinsumo;
    }

    public int getInsumocantidadminima() {
        return insumocantidadminima;
    }

    public void setInsumocantidadminima(int insumocantidadminima) {
        this.insumocantidadminima = insumocantidadminima;
    }

    public int getInsumocantidadmaxima() {
        return insumocantidadmaxima;
    }

    public void setInsumocantidadmaxima(int insumocantidadmaxima) {
        this.insumocantidadmaxima = insumocantidadmaxima;
    }

    public int getEstadoinsumo() {
        return estadoinsumo;
    }

    public void setEstadoinsumo(int estadoinsumo) {
        this.estadoinsumo = estadoinsumo;
    }

    @XmlTransient
    public List<Detallecotizacion> getDetallecotizacionList() {
        return detallecotizacionList;
    }

    public void setDetallecotizacionList(List<Detallecotizacion> detallecotizacionList) {
        this.detallecotizacionList = detallecotizacionList;
    }

    public Reteiva getInsumoreteivaid() {
        return insumoreteivaid;
    }

    public void setInsumoreteivaid(Reteiva insumoreteivaid) {
        this.insumoreteivaid = insumoreteivaid;
    }

    public Marca getInsumomarcaid() {
        return insumomarcaid;
    }

    public void setInsumomarcaid(Marca insumomarcaid) {
        this.insumomarcaid = insumomarcaid;
    }

    @XmlTransient
    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }

    @XmlTransient
    public List<Bodegainsumo> getBodegainsumoList() {
        return bodegainsumoList;
    }

    public void setBodegainsumoList(List<Bodegainsumo> bodegainsumoList) {
        this.bodegainsumoList = bodegainsumoList;
    }

    @XmlTransient
    public List<Detallemovimiento> getDetallemovimientoList() {
        return detallemovimientoList;
    }

    public void setDetallemovimientoList(List<Detallemovimiento> detallemovimientoList) {
        this.detallemovimientoList = detallemovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinsumo != null ? idinsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.idinsumo == null && other.idinsumo != null) || (this.idinsumo != null && !this.idinsumo.equals(other.idinsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Insumo[ idinsumo=" + idinsumo + " ]";
    }
    
}
