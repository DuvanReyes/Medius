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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "listaprecioproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listaprecioproducto.findAll", query = "SELECT l FROM Listaprecioproducto l")
    , @NamedQuery(name = "Listaprecioproducto.findByIdlistaprecioproducto", query = "SELECT l FROM Listaprecioproducto l WHERE l.idlistaprecioproducto = :idlistaprecioproducto")})
public class Listaprecioproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLISTAPRECIOPRODUCTO")
    private Integer idlistaprecioproducto;
    @JoinColumn(name = "LISTAID", referencedColumnName = "IDLISTA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Listaprecio listaid;
    @JoinColumn(name = "PRODUCTOID", referencedColumnName = "IDPRODUCTO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Producto productoid;

    public Listaprecioproducto() {
    }

    public Listaprecioproducto(Integer idlistaprecioproducto) {
        this.idlistaprecioproducto = idlistaprecioproducto;
    }

    public Integer getIdlistaprecioproducto() {
        return idlistaprecioproducto;
    }

    public void setIdlistaprecioproducto(Integer idlistaprecioproducto) {
        this.idlistaprecioproducto = idlistaprecioproducto;
    }

    public Listaprecio getListaid() {
        return listaid;
    }

    public void setListaid(Listaprecio listaid) {
        this.listaid = listaid;
    }

    public Producto getProductoid() {
        return productoid;
    }

    public void setProductoid(Producto productoid) {
        this.productoid = productoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistaprecioproducto != null ? idlistaprecioproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listaprecioproducto)) {
            return false;
        }
        Listaprecioproducto other = (Listaprecioproducto) object;
        if ((this.idlistaprecioproducto == null && other.idlistaprecioproducto != null) || (this.idlistaprecioproducto != null && !this.idlistaprecioproducto.equals(other.idlistaprecioproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Listaprecioproducto[ idlistaprecioproducto=" + idlistaprecioproducto + " ]";
    }
    
}
