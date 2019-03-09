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
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
    , @NamedQuery(name = "Compra.findByIdcompra", query = "SELECT c FROM Compra c WHERE c.idcompra = :idcompra")
    , @NamedQuery(name = "Compra.findByFechacreacioncompra", query = "SELECT c FROM Compra c WHERE c.fechacreacioncompra = :fechacreacioncompra")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOMPRA")
    private Integer idcompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACREACIONCOMPRA")
    @Temporal(TemporalType.DATE)
    private Date fechacreacioncompra;
    @JoinColumn(name = "COTIZACIONID", referencedColumnName = "IDCOTIZACION")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cotizacion cotizacionid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraid", fetch = FetchType.EAGER)
    private List<Detallecompra> detallecompraList;

    public Compra() {
    }

    public Compra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Compra(Integer idcompra, Date fechacreacioncompra) {
        this.idcompra = idcompra;
        this.fechacreacioncompra = fechacreacioncompra;
    }

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Date getFechacreacioncompra() {
        return fechacreacioncompra;
    }

    public void setFechacreacioncompra(Date fechacreacioncompra) {
        this.fechacreacioncompra = fechacreacioncompra;
    }

    public Cotizacion getCotizacionid() {
        return cotizacionid;
    }

    public void setCotizacionid(Cotizacion cotizacionid) {
        this.cotizacionid = cotizacionid;
    }

    @XmlTransient
    public List<Detallecompra> getDetallecompraList() {
        return detallecompraList;
    }

    public void setDetallecompraList(List<Detallecompra> detallecompraList) {
        this.detallecompraList = detallecompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompra != null ? idcompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idcompra == null && other.idcompra != null) || (this.idcompra != null && !this.idcompra.equals(other.idcompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Compra[ idcompra=" + idcompra + " ]";
    }
    
}
