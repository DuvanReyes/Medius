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
import javax.persistence.Lob;
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
@Table(name = "permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")
    , @NamedQuery(name = "Permiso.findByIdpermisos", query = "SELECT p FROM Permiso p WHERE p.idpermisos = :idpermisos")
    , @NamedQuery(name = "Permiso.findByNombrepermiso", query = "SELECT p FROM Permiso p WHERE p.nombrepermiso = :nombrepermiso")
    , @NamedQuery(name = "Permiso.findByIcono", query = "SELECT p FROM Permiso p WHERE p.icono = :icono")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPERMISOS")
    private Integer idpermisos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBREPERMISO")
    private String nombrepermiso;
    @Lob
    @Size(max = 65535)
    @Column(name = "URL")
    private String url;
    @Size(max = 50)
    @Column(name = "ICONO")
    private String icono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permisoid", fetch = FetchType.EAGER)
    private List<Rolpermiso> rolpermisoList;
    @OneToMany(mappedBy = "permisop", fetch = FetchType.EAGER)
    private List<Permiso> permisoList;
    @JoinColumn(name = "PERMISOP", referencedColumnName = "IDPERMISOS")
    @ManyToOne(fetch = FetchType.EAGER)
    private Permiso permisop;

    public Permiso() {
    }

    public Permiso(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public Permiso(Integer idpermisos, String nombrepermiso) {
        this.idpermisos = idpermisos;
        this.nombrepermiso = nombrepermiso;
    }

    public Integer getIdpermisos() {
        return idpermisos;
    }

    public void setIdpermisos(Integer idpermisos) {
        this.idpermisos = idpermisos;
    }

    public String getNombrepermiso() {
        return nombrepermiso;
    }

    public void setNombrepermiso(String nombrepermiso) {
        this.nombrepermiso = nombrepermiso;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @XmlTransient
    public List<Rolpermiso> getRolpermisoList() {
        return rolpermisoList;
    }

    public void setRolpermisoList(List<Rolpermiso> rolpermisoList) {
        this.rolpermisoList = rolpermisoList;
    }

    @XmlTransient
    public List<Permiso> getPermisoList() {
        return permisoList;
    }

    public void setPermisoList(List<Permiso> permisoList) {
        this.permisoList = permisoList;
    }

    public Permiso getPermisop() {
        return permisop;
    }

    public void setPermisop(Permiso permisop) {
        this.permisop = permisop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermisos != null ? idpermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idpermisos == null && other.idpermisos != null) || (this.idpermisos != null && !this.idpermisos.equals(other.idpermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Permiso[ idpermisos=" + idpermisos + " ]";
    }
    
}
