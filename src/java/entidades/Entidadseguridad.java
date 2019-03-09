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
@Table(name = "entidadseguridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidadseguridad.findAll", query = "SELECT e FROM Entidadseguridad e")
    , @NamedQuery(name = "Entidadseguridad.findByIdentidad", query = "SELECT e FROM Entidadseguridad e WHERE e.identidad = :identidad")
    , @NamedQuery(name = "Entidadseguridad.findByCodigoentidad", query = "SELECT e FROM Entidadseguridad e WHERE e.codigoentidad = :codigoentidad")
    , @NamedQuery(name = "Entidadseguridad.findByNombreentidad", query = "SELECT e FROM Entidadseguridad e WHERE e.nombreentidad = :nombreentidad")
    , @NamedQuery(name = "Entidadseguridad.findByClaseentidad", query = "SELECT e FROM Entidadseguridad e WHERE e.claseentidad = :claseentidad")
    , @NamedQuery(name = "Entidadseguridad.findByEstadoentidadseguridad", query = "SELECT e FROM Entidadseguridad e WHERE e.estadoentidadseguridad = :estadoentidadseguridad")})
public class Entidadseguridad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENTIDAD")
    private Integer identidad;
    @Basic(optional = false)

    @Column(name = "CODIGOENTIDAD")
    private String codigoentidad;
    @Basic(optional = false)

    @Column(name = "NOMBREENTIDAD")
    private String nombreentidad;
    @Basic(optional = false)

    @Column(name = "CLASEENTIDAD")
    private String claseentidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOENTIDADSEGURIDAD")
    private int estadoentidadseguridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoeps", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoarl", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigocaja", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigopensiones", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigocesantias", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList4;

    public Entidadseguridad() {
    }

    public Entidadseguridad(Integer identidad) {
        this.identidad = identidad;
    }

    public Entidadseguridad(Integer identidad, String codigoentidad, String nombreentidad, String claseentidad, int estadoentidadseguridad) {
        this.identidad = identidad;
        this.codigoentidad = codigoentidad;
        this.nombreentidad = nombreentidad;
        this.claseentidad = claseentidad;
        this.estadoentidadseguridad = estadoentidadseguridad;
    }

    public Integer getIdentidad() {
        return identidad;
    }

    public void setIdentidad(Integer identidad) {
        this.identidad = identidad;
    }

    public String getCodigoentidad() {
        return codigoentidad;
    }

    public void setCodigoentidad(String codigoentidad) {
        this.codigoentidad = codigoentidad;
    }

    public String getNombreentidad() {
        return nombreentidad;
    }

    public void setNombreentidad(String nombreentidad) {
        this.nombreentidad = nombreentidad;
    }

    public String getClaseentidad() {
        return claseentidad;
    }

    public void setClaseentidad(String claseentidad) {
        this.claseentidad = claseentidad;
    }

    public int getEstadoentidadseguridad() {
        return estadoentidadseguridad;
    }

    public void setEstadoentidadseguridad(int estadoentidadseguridad) {
        this.estadoentidadseguridad = estadoentidadseguridad;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList() {
        return contratoempleadoList;
    }

    public void setContratoempleadoList(List<Contratoempleado> contratoempleadoList) {
        this.contratoempleadoList = contratoempleadoList;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList1() {
        return contratoempleadoList1;
    }

    public void setContratoempleadoList1(List<Contratoempleado> contratoempleadoList1) {
        this.contratoempleadoList1 = contratoempleadoList1;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList2() {
        return contratoempleadoList2;
    }

    public void setContratoempleadoList2(List<Contratoempleado> contratoempleadoList2) {
        this.contratoempleadoList2 = contratoempleadoList2;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList3() {
        return contratoempleadoList3;
    }

    public void setContratoempleadoList3(List<Contratoempleado> contratoempleadoList3) {
        this.contratoempleadoList3 = contratoempleadoList3;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList4() {
        return contratoempleadoList4;
    }

    public void setContratoempleadoList4(List<Contratoempleado> contratoempleadoList4) {
        this.contratoempleadoList4 = contratoempleadoList4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identidad != null ? identidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidadseguridad)) {
            return false;
        }
        Entidadseguridad other = (Entidadseguridad) object;
        if ((this.identidad == null && other.identidad != null) || (this.identidad != null && !this.identidad.equals(other.identidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Entidadseguridad[ identidad=" + identidad + " ]";
    }
    
}
