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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "rolpermiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolpermiso.findAll", query = "SELECT r FROM Rolpermiso r")
    , @NamedQuery(name = "Rolpermiso.findByRolpermisoid", query = "SELECT r FROM Rolpermiso r WHERE r.rolpermisoid = :rolpermisoid")})
public class Rolpermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLPERMISOID")
    private Integer rolpermisoid;
    @JoinColumn(name = "PERMISOID", referencedColumnName = "IDPERMISOS")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Permiso permisoid;
    @JoinColumn(name = "ROLPID", referencedColumnName = "IDROL")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rol rolpid;

    public Rolpermiso() {
    }

    public Rolpermiso(Integer rolpermisoid) {
        this.rolpermisoid = rolpermisoid;
    }

    public Integer getRolpermisoid() {
        return rolpermisoid;
    }

    public void setRolpermisoid(Integer rolpermisoid) {
        this.rolpermisoid = rolpermisoid;
    }

    public Permiso getPermisoid() {
        return permisoid;
    }

    public void setPermisoid(Permiso permisoid) {
        this.permisoid = permisoid;
    }

    public Rol getRolpid() {
        return rolpid;
    }

    public void setRolpid(Rol rolpid) {
        this.rolpid = rolpid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolpermisoid != null ? rolpermisoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolpermiso)) {
            return false;
        }
        Rolpermiso other = (Rolpermiso) object;
        if ((this.rolpermisoid == null && other.rolpermisoid != null) || (this.rolpermisoid != null && !this.rolpermisoid.equals(other.rolpermisoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rolpermiso[ rolpermisoid=" + rolpermisoid + " ]";
    }
    
}
