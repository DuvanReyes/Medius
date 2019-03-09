/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "contratoempleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratoempleado.findAll", query = "SELECT c FROM Contratoempleado c")
    , @NamedQuery(name = "Contratoempleado.findByIdcontrato", query = "SELECT c FROM Contratoempleado c WHERE c.idcontrato = :idcontrato")
    , @NamedQuery(name = "Contratoempleado.findByTipocontrato", query = "SELECT c FROM Contratoempleado c WHERE c.tipocontrato = :tipocontrato")
    , @NamedQuery(name = "Contratoempleado.findByPeriodopago", query = "SELECT c FROM Contratoempleado c WHERE c.periodopago = :periodopago")
    , @NamedQuery(name = "Contratoempleado.findByFechainicio", query = "SELECT c FROM Contratoempleado c WHERE c.fechainicio = :fechainicio")
    , @NamedQuery(name = "Contratoempleado.findByFechafin", query = "SELECT c FROM Contratoempleado c WHERE c.fechafin = :fechafin")
    , @NamedQuery(name = "Contratoempleado.findByPeriodoprueba", query = "SELECT c FROM Contratoempleado c WHERE c.periodoprueba = :periodoprueba")
    , @NamedQuery(name = "Contratoempleado.findBySalarioinicial", query = "SELECT c FROM Contratoempleado c WHERE c.salarioinicial = :salarioinicial")
    , @NamedQuery(name = "Contratoempleado.findBySalarioactual", query = "SELECT c FROM Contratoempleado c WHERE c.salarioactual = :salarioactual")
    , @NamedQuery(name = "Contratoempleado.findByEstadocontratoempleado", query = "SELECT c FROM Contratoempleado c WHERE c.estadocontratoempleado = :estadocontratoempleado")})
public class Contratoempleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONTRATO")
    private Integer idcontrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIPOCONTRATO")
    private String tipocontrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PERIODOPAGO")
    private String periodopago;
    @Basic(optional = false)

    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)

    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)

    @Column(name = "PERIODOPRUEBA")
    private int periodoprueba;
    @Basic(optional = false)

    @Column(name = "SALARIOINICIAL")
    private double salarioinicial;
    @Basic(optional = false)

    @Column(name = "SALARIOACTUAL")
    private double salarioactual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOCONTRATOEMPLEADO")
    private int estadocontratoempleado;
    @JoinColumn(name = "EMPLEADOID", referencedColumnName = "IDEMPLEADO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado empleadoid;
    @JoinColumn(name = "CIUDADTRABAJO", referencedColumnName = "IDCIUDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadtrabajo;
    @JoinColumn(name = "CODIGOCARGO", referencedColumnName = "IDCARGO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cargo codigocargo;
    @JoinColumn(name = "CODIGOSECCION", referencedColumnName = "IDSECCION")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Seccion codigoseccion;
    @JoinColumn(name = "CODIGOEPS", referencedColumnName = "IDENTIDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entidadseguridad codigoeps;
    @JoinColumn(name = "CODIGOARL", referencedColumnName = "IDENTIDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entidadseguridad codigoarl;
    @JoinColumn(name = "CODIGOCAJA", referencedColumnName = "IDENTIDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entidadseguridad codigocaja;
    @JoinColumn(name = "CODIGOPENSIONES", referencedColumnName = "IDENTIDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entidadseguridad codigopensiones;
    @JoinColumn(name = "CODIGOCESANTIAS", referencedColumnName = "IDENTIDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Entidadseguridad codigocesantias;

    public Contratoempleado() {
    }

    public Contratoempleado(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contratoempleado(Integer idcontrato, String tipocontrato, String periodopago, Date fechainicio, Date fechafin, int periodoprueba, double salarioinicial, double salarioactual, int estadocontratoempleado) {
        this.idcontrato = idcontrato;
        this.tipocontrato = tipocontrato;
        this.periodopago = periodopago;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.periodoprueba = periodoprueba;
        this.salarioinicial = salarioinicial;
        this.salarioactual = salarioactual;
        this.estadocontratoempleado = estadocontratoempleado;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public String getPeriodopago() {
        return periodopago;
    }

    public void setPeriodopago(String periodopago) {
        this.periodopago = periodopago;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getPeriodoprueba() {
        return periodoprueba;
    }

    public void setPeriodoprueba(int periodoprueba) {
        this.periodoprueba = periodoprueba;
    }

    public double getSalarioinicial() {
        return salarioinicial;
    }

    public void setSalarioinicial(double salarioinicial) {
        this.salarioinicial = salarioinicial;
    }

    public double getSalarioactual() {
        return salarioactual;
    }

    public void setSalarioactual(double salarioactual) {
        this.salarioactual = salarioactual;
    }

    public int getEstadocontratoempleado() {
        return estadocontratoempleado;
    }

    public void setEstadocontratoempleado(int estadocontratoempleado) {
        this.estadocontratoempleado = estadocontratoempleado;
    }

    public Empleado getEmpleadoid() {
        return empleadoid;
    }

    public void setEmpleadoid(Empleado empleadoid) {
        this.empleadoid = empleadoid;
    }

    public Ciudad getCiudadtrabajo() {
        return ciudadtrabajo;
    }

    public void setCiudadtrabajo(Ciudad ciudadtrabajo) {
        this.ciudadtrabajo = ciudadtrabajo;
    }

    public Cargo getCodigocargo() {
        return codigocargo;
    }

    public void setCodigocargo(Cargo codigocargo) {
        this.codigocargo = codigocargo;
    }

    public Seccion getCodigoseccion() {
        return codigoseccion;
    }

    public void setCodigoseccion(Seccion codigoseccion) {
        this.codigoseccion = codigoseccion;
    }

    public Entidadseguridad getCodigoeps() {
        return codigoeps;
    }

    public void setCodigoeps(Entidadseguridad codigoeps) {
        this.codigoeps = codigoeps;
    }

    public Entidadseguridad getCodigoarl() {
        return codigoarl;
    }

    public void setCodigoarl(Entidadseguridad codigoarl) {
        this.codigoarl = codigoarl;
    }

    public Entidadseguridad getCodigocaja() {
        return codigocaja;
    }

    public void setCodigocaja(Entidadseguridad codigocaja) {
        this.codigocaja = codigocaja;
    }

    public Entidadseguridad getCodigopensiones() {
        return codigopensiones;
    }

    public void setCodigopensiones(Entidadseguridad codigopensiones) {
        this.codigopensiones = codigopensiones;
    }

    public Entidadseguridad getCodigocesantias() {
        return codigocesantias;
    }

    public void setCodigocesantias(Entidadseguridad codigocesantias) {
        this.codigocesantias = codigocesantias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratoempleado)) {
            return false;
        }
        Contratoempleado other = (Contratoempleado) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Contratoempleado[ idcontrato=" + idcontrato + " ]";
    }
    
}
