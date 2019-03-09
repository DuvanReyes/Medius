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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "Cliente.findByNombrealmacen", query = "SELECT c FROM Cliente c WHERE c.nombrealmacen = :nombrealmacen")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByEstadocliente", query = "SELECT c FROM Cliente c WHERE c.estadocliente = :estadocliente")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCLIENTE")
    private Integer idcliente;
    @Size(max = 150)
    @Column(name = "NOMBREALMACEN")
    private String nombrealmacen;
    @Size(max = 150)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOCLIENTE")
    private int estadocliente;
    @JoinColumn(name = "TERCEROID", referencedColumnName = "IDTERCERO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tercero terceroid;
    @JoinColumn(name = "CIUDADCLIENTE", referencedColumnName = "IDCIUDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadcliente;
    @JoinColumn(name = "LISTAPRECIOID", referencedColumnName = "IDLISTA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Listaprecio listaprecioid;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, int estadocliente) {
        this.idcliente = idcliente;
        this.estadocliente = estadocliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombrealmacen() {
        return nombrealmacen;
    }

    public void setNombrealmacen(String nombrealmacen) {
        this.nombrealmacen = nombrealmacen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstadocliente() {
        return estadocliente;
    }

    public void setEstadocliente(int estadocliente) {
        this.estadocliente = estadocliente;
    }

    public Tercero getTerceroid() {
        return terceroid;
    }

    public void setTerceroid(Tercero terceroid) {
        this.terceroid = terceroid;
    }

    public Ciudad getCiudadcliente() {
        return ciudadcliente;
    }

    public void setCiudadcliente(Ciudad ciudadcliente) {
        this.ciudadcliente = ciudadcliente;
    }

    public Listaprecio getListaprecioid() {
        return listaprecioid;
    }

    public void setListaprecioid(Listaprecio listaprecioid) {
        this.listaprecioid = listaprecioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
