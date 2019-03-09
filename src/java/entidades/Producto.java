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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto")
    , @NamedQuery(name = "Producto.findByNombreproducto", query = "SELECT p FROM Producto p WHERE p.nombreproducto = :nombreproducto")
    , @NamedQuery(name = "Producto.findByEstadoproducto", query = "SELECT p FROM Producto p WHERE p.estadoproducto = :estadoproducto")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRODUCTO")
    private Integer idproducto;
    @Basic(optional = false)
    @NotNull
    

    @Column(name = "NOMBREPRODUCTO")
    private String nombreproducto;
    @Basic(optional = false)
    @NotNull
    
    @Column(name = "ESTADOPRODUCTO")
    private int estadoproducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoid", fetch = FetchType.EAGER)
    private List<Listaprecioproducto> listaprecioproductoList;
    @JoinColumn(name = "PRODUCTORETEIVAID", referencedColumnName = "IDRETEIVA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Reteiva productoreteivaid;
    @JoinColumn(name = "PRODUCTOMARCAID", referencedColumnName = "IDMARCA")
    @ManyToOne(fetch = FetchType.EAGER)
    private Marca productomarcaid;
    @JoinColumn(name = "PRODUCTOLINEAID", referencedColumnName = "IDLINEA")
    @ManyToOne(fetch = FetchType.EAGER)
    private Linea productolineaid;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.EAGER)
    private Bodegaproducto bodegaproducto;

    public Producto() {
    }

    public Producto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Producto(Integer idproducto, String nombreproducto, int estadoproducto) {
        this.idproducto = idproducto;
        this.nombreproducto = nombreproducto;
        this.estadoproducto = estadoproducto;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public int getEstadoproducto() {
        return estadoproducto;
    }

    public void setEstadoproducto(int estadoproducto) {
        this.estadoproducto = estadoproducto;
    }

    @XmlTransient
    public List<Listaprecioproducto> getListaprecioproductoList() {
        return listaprecioproductoList;
    }

    public void setListaprecioproductoList(List<Listaprecioproducto> listaprecioproductoList) {
        this.listaprecioproductoList = listaprecioproductoList;
    }

    public Reteiva getProductoreteivaid() {
        return productoreteivaid;
    }

    public void setProductoreteivaid(Reteiva productoreteivaid) {
        this.productoreteivaid = productoreteivaid;
    }

    public Marca getProductomarcaid() {
        return productomarcaid;
    }

    public void setProductomarcaid(Marca productomarcaid) {
        this.productomarcaid = productomarcaid;
    }

    public Linea getProductolineaid() {
        return productolineaid;
    }

    public void setProductolineaid(Linea productolineaid) {
        this.productolineaid = productolineaid;
    }

    public Bodegaproducto getBodegaproducto() {
        return bodegaproducto;
    }

    public void setBodegaproducto(Bodegaproducto bodegaproducto) {
        this.bodegaproducto = bodegaproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Producto[ idproducto=" + idproducto + " ]";
    }
    
}
