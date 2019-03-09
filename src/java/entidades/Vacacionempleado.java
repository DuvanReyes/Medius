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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author activ
 */
@Entity
@Table(name = "vacacionempleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacacionempleado.findAll", query = "SELECT v FROM Vacacionempleado v")
    , @NamedQuery(name = "Vacacionempleado.findByIdvacacion", query = "SELECT v FROM Vacacionempleado v WHERE v.idvacacion = :idvacacion")
    , @NamedQuery(name = "Vacacionempleado.findByFechainicio", query = "SELECT v FROM Vacacionempleado v WHERE v.fechainicio = :fechainicio")
    , @NamedQuery(name = "Vacacionempleado.findByFechafin", query = "SELECT v FROM Vacacionempleado v WHERE v.fechafin = :fechafin")
    , @NamedQuery(name = "Vacacionempleado.findByDiaspagos", query = "SELECT v FROM Vacacionempleado v WHERE v.diaspagos = :diaspagos")
    , @NamedQuery(name = "Vacacionempleado.findByDescuentosley", query = "SELECT v FROM Vacacionempleado v WHERE v.descuentosley = :descuentosley")
    , @NamedQuery(name = "Vacacionempleado.findByOtrosdesuentos", query = "SELECT v FROM Vacacionempleado v WHERE v.otrosdesuentos = :otrosdesuentos")
    , @NamedQuery(name = "Vacacionempleado.findByTotalpago", query = "SELECT v FROM Vacacionempleado v WHERE v.totalpago = :totalpago")})
public class Vacacionempleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDVACACION")
    private Integer idvacacion;
    @Basic(optional = false)

    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)

    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIASPAGOS")
    private int diaspagos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTOSLEY")
    private double descuentosley;
    @Basic(optional = false)

    @Column(name = "OTROSDESUENTOS")
    private double otrosdesuentos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALPAGO")
    private double totalpago;
    @JoinColumn(name = "EMPLEADOID", referencedColumnName = "IDEMPLEADO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado empleadoid;

    public Vacacionempleado() {
    }

    public Vacacionempleado(Integer idvacacion) {
        this.idvacacion = idvacacion;
    }

    public Vacacionempleado(Integer idvacacion, Date fechainicio, Date fechafin, int diaspagos, double descuentosley, double otrosdesuentos, double totalpago) {
        this.idvacacion = idvacacion;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.diaspagos = diaspagos;
        this.descuentosley = descuentosley;
        this.otrosdesuentos = otrosdesuentos;
        this.totalpago = totalpago;
    }

    public Integer getIdvacacion() {
        return idvacacion;
    }

    public void setIdvacacion(Integer idvacacion) {
        this.idvacacion = idvacacion;
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

    public int getDiaspagos() {
        return diaspagos;
    }

    public void setDiaspagos(int diaspagos) {
        this.diaspagos = diaspagos;
    }

    public double getDescuentosley() {
        return descuentosley;
    }

    public void setDescuentosley(double descuentosley) {
        this.descuentosley = descuentosley;
    }

    public double getOtrosdesuentos() {
        return otrosdesuentos;
    }

    public void setOtrosdesuentos(double otrosdesuentos) {
        this.otrosdesuentos = otrosdesuentos;
    }

    public double getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(double totalpago) {
        this.totalpago = totalpago;
    }

    public Empleado getEmpleadoid() {
        return empleadoid;
    }

    public void setEmpleadoid(Empleado empleadoid) {
        this.empleadoid = empleadoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvacacion != null ? idvacacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacacionempleado)) {
            return false;
        }
        Vacacionempleado other = (Vacacionempleado) object;
        if ((this.idvacacion == null && other.idvacacion != null) || (this.idvacacion != null && !this.idvacacion.equals(other.idvacacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Vacacionempleado[ idvacacion=" + idvacacion + " ]";
    }
    
}
