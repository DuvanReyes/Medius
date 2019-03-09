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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "rolusuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolusuario.findAll", query = "SELECT r FROM Rolusuario r")
    , @NamedQuery(name = "Rolusuario.findByIdrolusuario", query = "SELECT r FROM Rolusuario r WHERE r.idrolusuario = :idrolusuario")})
public class Rolusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDROLUSUARIO")
    private Integer idrolusuario;
    
    @JoinColumn(name = "USUARIOID", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuarioid;
    
    @JoinColumn(name = "ROLID", referencedColumnName = "IDROL")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rol rolid;

    public Rolusuario() {
    }

    public Rolusuario(Integer idrolusuario) {
        this.idrolusuario = idrolusuario;
    }

    public Integer getIdrolusuario() {
        return idrolusuario;
    }

    public void setIdrolusuario(Integer idrolusuario) {
        this.idrolusuario = idrolusuario;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Rol getRolid() {
        return rolid;
    }

    public void setRolid(Rol rolid) {
        this.rolid = rolid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrolusuario != null ? idrolusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolusuario)) {
            return false;
        }
        Rolusuario other = (Rolusuario) object;
        if ((this.idrolusuario == null && other.idrolusuario != null) || (this.idrolusuario != null && !this.idrolusuario.equals(other.idrolusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rolusuario[ idrolusuario=" + idrolusuario + " ]";
    }
    
}
