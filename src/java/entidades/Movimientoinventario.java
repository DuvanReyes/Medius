/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "movimientoinventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientoinventario.findAll", query = "SELECT m FROM Movimientoinventario m")
    , @NamedQuery(name = "Movimientoinventario.findByIdmovimiento", query = "SELECT m FROM Movimientoinventario m WHERE m.idmovimiento = :idmovimiento")
    , @NamedQuery(name = "Movimientoinventario.findByFuente", query = "SELECT m FROM Movimientoinventario m WHERE m.fuente = :fuente")
    , @NamedQuery(name = "Movimientoinventario.findByDescripcion", query = "SELECT m FROM Movimientoinventario m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Movimientoinventario.findByFechamovimiento", query = "SELECT m FROM Movimientoinventario m WHERE m.fechamovimiento = :fechamovimiento")
    , @NamedQuery(name = "Movimientoinventario.findByNotas", query = "SELECT m FROM Movimientoinventario m WHERE m.notas = :notas")
    , @NamedQuery(name = "Movimientoinventario.findByEstado", query = "SELECT m FROM Movimientoinventario m WHERE m.estado = :estado")})
public class Movimientoinventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMOVIMIENTO")
    private Integer idmovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "FUENTE")
    private String fuente;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAMOVIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechamovimiento;
    @Size(max = 100)
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private int estado;
    @JoinColumn(name = "BODEGAID", referencedColumnName = "IDBODEGA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Bodega bodegaid;
    @JoinColumn(name = "BODEGADESTINOID", referencedColumnName = "IDBODEGA")
    @ManyToOne(fetch = FetchType.EAGER)
    private Bodega bodegadestinoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimientoid", fetch = FetchType.EAGER)
    private List<Detallemovimiento> detallemovimientoList;

    public Movimientoinventario() {
    }

    public Movimientoinventario(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Movimientoinventario(Integer idmovimiento, String fuente, Date fechamovimiento, int estado) {
        this.idmovimiento = idmovimiento;
        this.fuente = fuente;
        this.fechamovimiento = fechamovimiento;
        this.estado = estado;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechamovimiento() {
        return fechamovimiento;
    }

    public void setFechamovimiento(Date fechamovimiento) {
        this.fechamovimiento = fechamovimiento;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Bodega getBodegaid() {
        return bodegaid;
    }

    public void setBodegaid(Bodega bodegaid) {
        this.bodegaid = bodegaid;
    }

    public Bodega getBodegadestinoid() {
        return bodegadestinoid;
    }

    public void setBodegadestinoid(Bodega bodegadestinoid) {
        this.bodegadestinoid = bodegadestinoid;
    }

    @XmlTransient
    public List<Detallemovimiento> getDetallemovimientoList() {
        return detallemovimientoList;
    }

    public void setDetallemovimientoList(List<Detallemovimiento> detallemovimientoList) {
        this.detallemovimientoList = detallemovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoinventario)) {
            return false;
        }
        Movimientoinventario other = (Movimientoinventario) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Movimientoinventario[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
