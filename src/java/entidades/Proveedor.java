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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor")
    , @NamedQuery(name = "Proveedor.findByNombrerepresentante", query = "SELECT p FROM Proveedor p WHERE p.nombrerepresentante = :nombrerepresentante")
    , @NamedQuery(name = "Proveedor.findByApellidorepresentante", query = "SELECT p FROM Proveedor p WHERE p.apellidorepresentante = :apellidorepresentante")
    , @NamedQuery(name = "Proveedor.findByTelefonoproveedor", query = "SELECT p FROM Proveedor p WHERE p.telefonoproveedor = :telefonoproveedor")
    , @NamedQuery(name = "Proveedor.findByPlazopago", query = "SELECT p FROM Proveedor p WHERE p.plazopago = :plazopago")
    , @NamedQuery(name = "Proveedor.findByEstadoproveedor", query = "SELECT p FROM Proveedor p WHERE p.estadoproveedor = :estadoproveedor")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPROVEEDOR")
    private Integer idproveedor;
    @Size(max = 50)
    @Column(name = "NOMBREREPRESENTANTE")
    private String nombrerepresentante;
    @Size(max = 50)
    @Column(name = "APELLIDOREPRESENTANTE")
    private String apellidorepresentante;
    @Size(max = 50)
    @Column(name = "TELEFONOPROVEEDOR")
    private String telefonoproveedor;
    @Column(name = "PLAZOPAGO")
    private Integer plazopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOPROVEEDOR")
    private int estadoproveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorid", fetch = FetchType.EAGER)
    private List<Cotizacion> cotizacionList;
    @JoinColumn(name = "TERCEROID", referencedColumnName = "IDTERCERO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tercero terceroid;
    @JoinColumn(name = "CIUDADID", referencedColumnName = "IDCIUDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadid;

    public Proveedor() {
    }

    public Proveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Proveedor(Integer idproveedor, int estadoproveedor) {
        this.idproveedor = idproveedor;
        this.estadoproveedor = estadoproveedor;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombrerepresentante() {
        return nombrerepresentante;
    }

    public void setNombrerepresentante(String nombrerepresentante) {
        this.nombrerepresentante = nombrerepresentante;
    }

    public String getApellidorepresentante() {
        return apellidorepresentante;
    }

    public void setApellidorepresentante(String apellidorepresentante) {
        this.apellidorepresentante = apellidorepresentante;
    }

    public String getTelefonoproveedor() {
        return telefonoproveedor;
    }

    public void setTelefonoproveedor(String telefonoproveedor) {
        this.telefonoproveedor = telefonoproveedor;
    }

    public Integer getPlazopago() {
        return plazopago;
    }

    public void setPlazopago(Integer plazopago) {
        this.plazopago = plazopago;
    }

    public int getEstadoproveedor() {
        return estadoproveedor;
    }

    public void setEstadoproveedor(int estadoproveedor) {
        this.estadoproveedor = estadoproveedor;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    public Tercero getTerceroid() {
        return terceroid;
    }

    public void setTerceroid(Tercero terceroid) {
        this.terceroid = terceroid;
    }

    public Ciudad getCiudadid() {
        return ciudadid;
    }

    public void setCiudadid(Ciudad ciudadid) {
        this.ciudadid = ciudadid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproveedor != null ? idproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idproveedor == null && other.idproveedor != null) || (this.idproveedor != null && !this.idproveedor.equals(other.idproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Proveedor[ idproveedor=" + idproveedor + " ]";
    }
    
}
