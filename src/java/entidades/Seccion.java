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
@Table(name = "seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s")
    , @NamedQuery(name = "Seccion.findByIdseccion", query = "SELECT s FROM Seccion s WHERE s.idseccion = :idseccion")
    , @NamedQuery(name = "Seccion.findByNombreseccion", query = "SELECT s FROM Seccion s WHERE s.nombreseccion = :nombreseccion")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSECCION")
    private Integer idseccion;
    @Basic(optional = false)

    @Column(name = "NOMBRESECCION")
    private String nombreseccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoseccion", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList;

    public Seccion() {
    }

    public Seccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public Seccion(Integer idseccion, String nombreseccion) {
        this.idseccion = idseccion;
        this.nombreseccion = nombreseccion;
    }

    public Integer getIdseccion() {
        return idseccion;
    }

    public void setIdseccion(Integer idseccion) {
        this.idseccion = idseccion;
    }

    public String getNombreseccion() {
        return nombreseccion;
    }

    public void setNombreseccion(String nombreseccion) {
        this.nombreseccion = nombreseccion;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList() {
        return contratoempleadoList;
    }

    public void setContratoempleadoList(List<Contratoempleado> contratoempleadoList) {
        this.contratoempleadoList = contratoempleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idseccion != null ? idseccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idseccion == null && other.idseccion != null) || (this.idseccion != null && !this.idseccion.equals(other.idseccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Seccion[ idseccion=" + idseccion + " ]";
    }
    
}
