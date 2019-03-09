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
@Table(name = "listaprecio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listaprecio.findAll", query = "SELECT l FROM Listaprecio l")
    , @NamedQuery(name = "Listaprecio.findByIdlista", query = "SELECT l FROM Listaprecio l WHERE l.idlista = :idlista")
    , @NamedQuery(name = "Listaprecio.findByNombrelista", query = "SELECT l FROM Listaprecio l WHERE l.nombrelista = :nombrelista")
    , @NamedQuery(name = "Listaprecio.findByFijapreciolista", query = "SELECT l FROM Listaprecio l WHERE l.fijapreciolista = :fijapreciolista")})
public class Listaprecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLISTA")
    private Integer idlista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRELISTA")
    private String nombrelista;
    @Size(max = 50)
    @Column(name = "FIJAPRECIOLISTA")
    private String fijapreciolista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaid", fetch = FetchType.EAGER)
    private List<Listaprecioproducto> listaprecioproductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaprecioid", fetch = FetchType.EAGER)
    private List<Cliente> clienteList;

    public Listaprecio() {
    }

    public Listaprecio(Integer idlista) {
        this.idlista = idlista;
    }

    public Listaprecio(Integer idlista, String nombrelista) {
        this.idlista = idlista;
        this.nombrelista = nombrelista;
    }

    public Integer getIdlista() {
        return idlista;
    }

    public void setIdlista(Integer idlista) {
        this.idlista = idlista;
    }

    public String getNombrelista() {
        return nombrelista;
    }

    public void setNombrelista(String nombrelista) {
        this.nombrelista = nombrelista;
    }

    public String getFijapreciolista() {
        return fijapreciolista;
    }

    public void setFijapreciolista(String fijapreciolista) {
        this.fijapreciolista = fijapreciolista;
    }

    @XmlTransient
    public List<Listaprecioproducto> getListaprecioproductoList() {
        return listaprecioproductoList;
    }

    public void setListaprecioproductoList(List<Listaprecioproducto> listaprecioproductoList) {
        this.listaprecioproductoList = listaprecioproductoList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlista != null ? idlista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listaprecio)) {
            return false;
        }
        Listaprecio other = (Listaprecio) object;
        if ((this.idlista == null && other.idlista != null) || (this.idlista != null && !this.idlista.equals(other.idlista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Listaprecio[ idlista=" + idlista + " ]";
    }
    
}
