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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdempleado", query = "SELECT e FROM Empleado e WHERE e.idempleado = :idempleado")
    , @NamedQuery(name = "Empleado.findByFechaexpedicioncedula", query = "SELECT e FROM Empleado e WHERE e.fechaexpedicioncedula = :fechaexpedicioncedula")
    , @NamedQuery(name = "Empleado.findByFechanacimientoempleado", query = "SELECT e FROM Empleado e WHERE e.fechanacimientoempleado = :fechanacimientoempleado")
    , @NamedQuery(name = "Empleado.findByNiveleducacionempleado", query = "SELECT e FROM Empleado e WHERE e.niveleducacionempleado = :niveleducacionempleado")
    , @NamedQuery(name = "Empleado.findByEstadocivilempleado", query = "SELECT e FROM Empleado e WHERE e.estadocivilempleado = :estadocivilempleado")
    , @NamedQuery(name = "Empleado.findByEstadoempleado", query = "SELECT e FROM Empleado e WHERE e.estadoempleado = :estadoempleado")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEMPLEADO")
    private Integer idempleado;
    @Basic(optional = false)

    @Column(name = "FECHAEXPEDICIONCEDULA")
    @Temporal(TemporalType.DATE)
    private Date fechaexpedicioncedula;
    @Basic(optional = false)

    @Column(name = "FECHANACIMIENTOEMPLEADO")
    @Temporal(TemporalType.DATE)
    private Date fechanacimientoempleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NIVELEDUCACIONEMPLEADO")
    private String niveleducacionempleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ESTADOCIVILEMPLEADO")
    private String estadocivilempleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOEMPLEADO")
    private int estadoempleado;
    @JoinColumn(name = "CIUDADNACIMIENTO", referencedColumnName = "IDCIUDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadnacimiento;
    @JoinColumn(name = "CIUDADEXPEDICION", referencedColumnName = "IDCIUDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadexpedicion;
    @JoinColumn(name = "TERCEROIDEMPLEADO", referencedColumnName = "IDTERCERO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tercero terceroidempleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoid", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoid", fetch = FetchType.EAGER)
    private List<Vacacionempleado> vacacionempleadoList;

    public Empleado() {
    }

    public Empleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Empleado(Integer idempleado, Date fechaexpedicioncedula, Date fechanacimientoempleado, String niveleducacionempleado, String estadocivilempleado, int estadoempleado) {
        this.idempleado = idempleado;
        this.fechaexpedicioncedula = fechaexpedicioncedula;
        this.fechanacimientoempleado = fechanacimientoempleado;
        this.niveleducacionempleado = niveleducacionempleado;
        this.estadocivilempleado = estadocivilempleado;
        this.estadoempleado = estadoempleado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Date getFechaexpedicioncedula() {
        return fechaexpedicioncedula;
    }

    public void setFechaexpedicioncedula(Date fechaexpedicioncedula) {
        this.fechaexpedicioncedula = fechaexpedicioncedula;
    }

    public Date getFechanacimientoempleado() {
        return fechanacimientoempleado;
    }

    public void setFechanacimientoempleado(Date fechanacimientoempleado) {
        this.fechanacimientoempleado = fechanacimientoempleado;
    }

    public String getNiveleducacionempleado() {
        return niveleducacionempleado;
    }

    public void setNiveleducacionempleado(String niveleducacionempleado) {
        this.niveleducacionempleado = niveleducacionempleado;
    }

    public String getEstadocivilempleado() {
        return estadocivilempleado;
    }

    public void setEstadocivilempleado(String estadocivilempleado) {
        this.estadocivilempleado = estadocivilempleado;
    }

    public int getEstadoempleado() {
        return estadoempleado;
    }

    public void setEstadoempleado(int estadoempleado) {
        this.estadoempleado = estadoempleado;
    }

    public Ciudad getCiudadnacimiento() {
        return ciudadnacimiento;
    }

    public void setCiudadnacimiento(Ciudad ciudadnacimiento) {
        this.ciudadnacimiento = ciudadnacimiento;
    }

    public Ciudad getCiudadexpedicion() {
        return ciudadexpedicion;
    }

    public void setCiudadexpedicion(Ciudad ciudadexpedicion) {
        this.ciudadexpedicion = ciudadexpedicion;
    }

    public Tercero getTerceroidempleado() {
        return terceroidempleado;
    }

    public void setTerceroidempleado(Tercero terceroidempleado) {
        this.terceroidempleado = terceroidempleado;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList() {
        return contratoempleadoList;
    }

    public void setContratoempleadoList(List<Contratoempleado> contratoempleadoList) {
        this.contratoempleadoList = contratoempleadoList;
    }

    @XmlTransient
    public List<Vacacionempleado> getVacacionempleadoList() {
        return vacacionempleadoList;
    }

    public void setVacacionempleadoList(List<Vacacionempleado> vacacionempleadoList) {
        this.vacacionempleadoList = vacacionempleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempleado != null ? idempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Empleado[ idempleado=" + idempleado + " ]";
    }
    
}
