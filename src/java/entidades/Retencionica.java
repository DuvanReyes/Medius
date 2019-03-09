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
@Table(name = "retencionica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencionica.findAll", query = "SELECT r FROM Retencionica r")
    , @NamedQuery(name = "Retencionica.findByIdretencionica", query = "SELECT r FROM Retencionica r WHERE r.idretencionica = :idretencionica")
    , @NamedQuery(name = "Retencionica.findByNombreretencionica", query = "SELECT r FROM Retencionica r WHERE r.nombreretencionica = :nombreretencionica")
    , @NamedQuery(name = "Retencionica.findByTasaretencionica", query = "SELECT r FROM Retencionica r WHERE r.tasaretencionica = :tasaretencionica")
    , @NamedQuery(name = "Retencionica.findByUvtretencionica", query = "SELECT r FROM Retencionica r WHERE r.uvtretencionica = :uvtretencionica")
    , @NamedQuery(name = "Retencionica.findByEstadoretencionica", query = "SELECT r FROM Retencionica r WHERE r.estadoretencionica = :estadoretencionica")})
public class Retencionica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRETENCIONICA")
    private Integer idretencionica;
    @Basic(optional = false)

    @Column(name = "NOMBRERETENCIONICA")
    private String nombreretencionica;
    @Basic(optional = false)

    @Column(name = "TASARETENCIONICA")
    private double tasaretencionica;
    @Basic(optional = false)

    @Column(name = "UVTRETENCIONICA")
    private double uvtretencionica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADORETENCIONICA")
    private int estadoretencionica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiporetencionicaid", fetch = FetchType.EAGER)
    private List<Tercero> terceroList;

    public Retencionica() {
    }

    public Retencionica(Integer idretencionica) {
        this.idretencionica = idretencionica;
    }

    public Retencionica(Integer idretencionica, String nombreretencionica, double tasaretencionica, double uvtretencionica, int estadoretencionica) {
        this.idretencionica = idretencionica;
        this.nombreretencionica = nombreretencionica;
        this.tasaretencionica = tasaretencionica;
        this.uvtretencionica = uvtretencionica;
        this.estadoretencionica = estadoretencionica;
    }

    public Integer getIdretencionica() {
        return idretencionica;
    }

    public void setIdretencionica(Integer idretencionica) {
        this.idretencionica = idretencionica;
    }

    public String getNombreretencionica() {
        return nombreretencionica;
    }

    public void setNombreretencionica(String nombreretencionica) {
        this.nombreretencionica = nombreretencionica;
    }

    public double getTasaretencionica() {
        return tasaretencionica;
    }

    public void setTasaretencionica(double tasaretencionica) {
        this.tasaretencionica = tasaretencionica;
    }

    public double getUvtretencionica() {
        return uvtretencionica;
    }

    public void setUvtretencionica(double uvtretencionica) {
        this.uvtretencionica = uvtretencionica;
    }

    public int getEstadoretencionica() {
        return estadoretencionica;
    }

    public void setEstadoretencionica(int estadoretencionica) {
        this.estadoretencionica = estadoretencionica;
    }

    @XmlTransient
    public List<Tercero> getTerceroList() {
        return terceroList;
    }

    public void setTerceroList(List<Tercero> terceroList) {
        this.terceroList = terceroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idretencionica != null ? idretencionica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencionica)) {
            return false;
        }
        Retencionica other = (Retencionica) object;
        if ((this.idretencionica == null && other.idretencionica != null) || (this.idretencionica != null && !this.idretencionica.equals(other.idretencionica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Retencionica[ idretencionica=" + idretencionica + " ]";
    }
    
}
