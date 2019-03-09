/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l")
    , @NamedQuery(name = "Linea.findByIdlinea", query = "SELECT l FROM Linea l WHERE l.idlinea = :idlinea")
    , @NamedQuery(name = "Linea.findByNombrelinea", query = "SELECT l FROM Linea l WHERE l.nombrelinea = :nombrelinea")
    , @NamedQuery(name = "Linea.findByEstadolinea", query = "SELECT l FROM Linea l WHERE l.estadolinea = :estadolinea")})
public class Linea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLINEA")
    private Integer idlinea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRELINEA")
    private String nombrelinea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOLINEA")
    private int estadolinea;
    @OneToMany(mappedBy = "productolineaid", fetch = FetchType.EAGER)
    private List<Producto> productoList;

    public Linea() {
    }

    public Linea(Integer idlinea) {
        this.idlinea = idlinea;
    }

    public Linea(Integer idlinea, String nombrelinea, int estadolinea) {
        this.idlinea = idlinea;
        this.nombrelinea = nombrelinea;
        this.estadolinea = estadolinea;
    }

    public Integer getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(Integer idlinea) {
        this.idlinea = idlinea;
    }

    public String getNombrelinea() {
        return nombrelinea;
    }

    public void setNombrelinea(String nombrelinea) {
        this.nombrelinea = nombrelinea;
    }

    public int getEstadolinea() {
        return estadolinea;
    }

    public void setEstadolinea(int estadolinea) {
        this.estadolinea = estadolinea;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlinea != null ? idlinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.idlinea == null && other.idlinea != null) || (this.idlinea != null && !this.idlinea.equals(other.idlinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Linea[ idlinea=" + idlinea + " ]";
    }
    
}
