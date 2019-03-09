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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuario.findByNombreusuario", query = "SELECT u FROM Usuario u WHERE u.nombreusuario = :nombreusuario")
    , @NamedQuery(name = "Usuario.findByContrasenausuario", query = "SELECT u FROM Usuario u WHERE u.contrasenausuario = :contrasenausuario")
    , @NamedQuery(name = "Usuario.findByEstadousuario", query = "SELECT u FROM Usuario u WHERE u.estadousuario = :estadousuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Basic(optional = false)

    @Column(name = "NOMBREUSUARIO")
    private String nombreusuario;
    @Basic(optional = false)

    @Column(name = "CONTRASENAUSUARIO")
    private String contrasenausuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOUSUARIO")
    private int estadousuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioid", fetch = FetchType.EAGER)
    private List<Rolusuario> rolusuarioList;
    @JoinColumn(name = "TERCEROUSUARIO", referencedColumnName = "IDTERCERO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tercero tercerousuario;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String nombreusuario, String contrasenausuario, int estadousuario) {
        this.idusuario = idusuario;
        this.nombreusuario = nombreusuario;
        this.contrasenausuario = contrasenausuario;
        this.estadousuario = estadousuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasenausuario() {
        return contrasenausuario;
    }

    public void setContrasenausuario(String contrasenausuario) {
        this.contrasenausuario = contrasenausuario;
    }

    public int getEstadousuario() {
        return estadousuario;
    }

    public void setEstadousuario(int estadousuario) {
        this.estadousuario = estadousuario;
    }

    @XmlTransient
    public List<Rolusuario> getRolusuarioList() {
        return rolusuarioList;
    }

    public void setRolusuarioList(List<Rolusuario> rolusuarioList) {
        this.rolusuarioList = rolusuarioList;
    }

    public Tercero getTercerousuario() {
        return tercerousuario;
    }

    public void setTercerousuario(Tercero tercerousuario) {
        this.tercerousuario = tercerousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
