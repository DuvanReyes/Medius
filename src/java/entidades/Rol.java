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
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByIdrol", query = "SELECT r FROM Rol r WHERE r.idrol = :idrol")
    , @NamedQuery(name = "Rol.findByNombrerol", query = "SELECT r FROM Rol r WHERE r.nombrerol = :nombrerol")
    , @NamedQuery(name = "Rol.findByEstadorol", query = "SELECT r FROM Rol r WHERE r.estadorol = :estadorol")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDROL")
    private Integer idrol;
    @Basic(optional = false)

    @Column(name = "NOMBREROL")
    private String nombrerol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOROL")
    private int estadorol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolpid", fetch = FetchType.EAGER)
    private List<Rolpermiso> rolpermisoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolid", fetch = FetchType.EAGER)
    private List<Rolusuario> rolusuarioList;

    public Rol() {
    }

    public Rol(Integer idrol) {
        this.idrol = idrol;
    }

    public Rol(Integer idrol, String nombrerol, int estadorol) {
        this.idrol = idrol;
        this.nombrerol = nombrerol;
        this.estadorol = estadorol;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }

    public int getEstadorol() {
        return estadorol;
    }

    public void setEstadorol(int estadorol) {
        this.estadorol = estadorol;
    }

    @XmlTransient
    public List<Rolpermiso> getRolpermisoList() {
        return rolpermisoList;
    }

    public void setRolpermisoList(List<Rolpermiso> rolpermisoList) {
        this.rolpermisoList = rolpermisoList;
    }

    @XmlTransient
    public List<Rolusuario> getRolusuarioList() {
        return rolusuarioList;
    }

    public void setRolusuarioList(List<Rolusuario> rolusuarioList) {
        this.rolusuarioList = rolusuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rol[ idrol=" + idrol + " ]";
    }
    
}
