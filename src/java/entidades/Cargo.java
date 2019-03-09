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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByIdcargo", query = "SELECT c FROM Cargo c WHERE c.idcargo = :idcargo")
    , @NamedQuery(name = "Cargo.findByNombrecargo", query = "SELECT c FROM Cargo c WHERE c.nombrecargo = :nombrecargo")
    , @NamedQuery(name = "Cargo.findBySalariominimo", query = "SELECT c FROM Cargo c WHERE c.salariominimo = :salariominimo")
    , @NamedQuery(name = "Cargo.findBySalariomaximo", query = "SELECT c FROM Cargo c WHERE c.salariomaximo = :salariomaximo")
    , @NamedQuery(name = "Cargo.findByEstadocargo", query = "SELECT c FROM Cargo c WHERE c.estadocargo = :estadocargo")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCARGO")
    private Integer idcargo;
    @Basic(optional = false)

    @Column(name = "NOMBRECARGO")
    private String nombrecargo;
    @Basic(optional = false)

    @Column(name = "SALARIOMINIMO")
    private double salariominimo;
    @Basic(optional = false)

    @Column(name = "SALARIOMAXIMO")
    private double salariomaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOCARGO")
    private int estadocargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigocargo", fetch = FetchType.EAGER)
    private List<Contratoempleado> contratoempleadoList;

    public Cargo() {
    }

    public Cargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public Cargo(Integer idcargo, String nombrecargo, double salariominimo, double salariomaximo, int estadocargo) {
        this.idcargo = idcargo;
        this.nombrecargo = nombrecargo;
        this.salariominimo = salariominimo;
        this.salariomaximo = salariomaximo;
        this.estadocargo = estadocargo;
    }

    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public double getSalariominimo() {
        return salariominimo;
    }

    public void setSalariominimo(double salariominimo) {
        this.salariominimo = salariominimo;
    }

    public double getSalariomaximo() {
        return salariomaximo;
    }

    public void setSalariomaximo(double salariomaximo) {
        this.salariomaximo = salariomaximo;
    }

    public int getEstadocargo() {
        return estadocargo;
    }

    public void setEstadocargo(int estadocargo) {
        this.estadocargo = estadocargo;
    }

    @XmlTransient
    public List<Contratoempleado> getContratoempleadoList() {
        return contratoempleadoList;
    }

    public void setContratoempleadoList(List<Contratoempleado> contratoempleadoList) {
        this.contratoempleadoList = contratoempleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcargo != null ? idcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.idcargo == null && other.idcargo != null) || (this.idcargo != null && !this.idcargo.equals(other.idcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cargo[ idcargo=" + idcargo + " ]";
    }
    
}
