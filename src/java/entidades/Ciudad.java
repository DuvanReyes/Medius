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
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.findByIdciudad", query = "SELECT c FROM Ciudad c WHERE c.idciudad = :idciudad")
    , @NamedQuery(name = "Ciudad.findByNumerociudad", query = "SELECT c FROM Ciudad c WHERE c.numerociudad = :numerociudad")
    , @NamedQuery(name = "Ciudad.findByCiudadnombre", query = "SELECT c FROM Ciudad c WHERE c.ciudadnombre = :ciudadnombre")})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCIUDAD")
    private Integer idciudad;
    @Basic(optional = false)

    @Column(name = "NUMEROCIUDAD")
    private int numerociudad;
    @Basic(optional = false)
   
    @Column(name = "CIUDADNOMBRE")
    private String ciudadnombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadnacimiento", fetch = FetchType.EAGER)
    private List<Empleado> empleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadexpedicion", fetch = FetchType.EAGER)
    private List<Empleado> empleadoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadtercero", fetch = FetchType.EAGER)
    private List<Tercero> terceroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadtrabajo", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadcliente", fetch = FetchType.EAGER)
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadid", fetch = FetchType.EAGER)
    private List<Proveedor> proveedorList;

    public Ciudad() {
    }

    public Ciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public Ciudad(Integer idciudad, int numerociudad, String ciudadnombre) {
        this.idciudad = idciudad;
        this.numerociudad = numerociudad;
        this.ciudadnombre = ciudadnombre;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public int getNumerociudad() {
        return numerociudad;
    }

    public void setNumerociudad(int numerociudad) {
        this.numerociudad = numerociudad;
    }

    public String getCiudadnombre() {
        return ciudadnombre;
    }

    public void setCiudadnombre(String ciudadnombre) {
        this.ciudadnombre = ciudadnombre;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList1() {
        return empleadoList1;
    }

    public void setEmpleadoList1(List<Empleado> empleadoList1) {
        this.empleadoList1 = empleadoList1;
    }

    @XmlTransient
    public List<Tercero> getTerceroList() {
        return terceroList;
    }

    public void setTerceroList(List<Tercero> terceroList) {
        this.terceroList = terceroList;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList() {
        return contratoempleadoList;
    }

    public void setContratoempleadoList(List<Contratoempleado> contratoempleadoList) {
        this.contratoempleadoList = contratoempleadoList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciudad != null ? idciudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.idciudad == null && other.idciudad != null) || (this.idciudad != null && !this.idciudad.equals(other.idciudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ciudad[ idciudad=" + idciudad + " ]";
    }
    
}
