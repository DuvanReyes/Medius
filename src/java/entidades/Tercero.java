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
@Table(name = "tercero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tercero.findAll", query = "SELECT t FROM Tercero t")
    , @NamedQuery(name = "Tercero.findByIdtercero", query = "SELECT t FROM Tercero t WHERE t.idtercero = :idtercero")
    , @NamedQuery(name = "Tercero.findByTipodocumento", query = "SELECT t FROM Tercero t WHERE t.tipodocumento = :tipodocumento")
    , @NamedQuery(name = "Tercero.findByNumerodocumento", query = "SELECT t FROM Tercero t WHERE t.numerodocumento = :numerodocumento")
    , @NamedQuery(name = "Tercero.findByPrimernombre", query = "SELECT t FROM Tercero t WHERE t.primernombre = :primernombre")
    , @NamedQuery(name = "Tercero.findBySegundonombre", query = "SELECT t FROM Tercero t WHERE t.segundonombre = :segundonombre")
    , @NamedQuery(name = "Tercero.findByPrimerapellido", query = "SELECT t FROM Tercero t WHERE t.primerapellido = :primerapellido")
    , @NamedQuery(name = "Tercero.findBySegundoapellido", query = "SELECT t FROM Tercero t WHERE t.segundoapellido = :segundoapellido")
    , @NamedQuery(name = "Tercero.findByTelefono", query = "SELECT t FROM Tercero t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Tercero.findByDireccion", query = "SELECT t FROM Tercero t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "Tercero.findByCorreo", query = "SELECT t FROM Tercero t WHERE t.correo = :correo")
    , @NamedQuery(name = "Tercero.findByNombrecomercial", query = "SELECT t FROM Tercero t WHERE t.nombrecomercial = :nombrecomercial")
    , @NamedQuery(name = "Tercero.findByRegimenrenta", query = "SELECT t FROM Tercero t WHERE t.regimenrenta = :regimenrenta")
    , @NamedQuery(name = "Tercero.findByRegimeniva", query = "SELECT t FROM Tercero t WHERE t.regimeniva = :regimeniva")
    , @NamedQuery(name = "Tercero.findByTarifareteiva", query = "SELECT t FROM Tercero t WHERE t.tarifareteiva = :tarifareteiva")
    , @NamedQuery(name = "Tercero.findByRegimenica", query = "SELECT t FROM Tercero t WHERE t.regimenica = :regimenica")
    , @NamedQuery(name = "Tercero.findByEstadotercero", query = "SELECT t FROM Tercero t WHERE t.estadotercero = :estadotercero")})
public class Tercero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTERCERO")
    private Integer idtercero;
    @Basic(optional = false)

    @Size(min = 1, max = 50)
    @Column(name = "TIPODOCUMENTO")
    private String tipodocumento;
    @Basic(optional = false)
   
    @Column(name = "NUMERODOCUMENTO")
    private int numerodocumento;
    @Basic(optional = false)

    @Column(name = "PRIMERNOMBRE")
    private String primernombre;
    @Basic(optional = false)
  

    @Column(name = "SEGUNDONOMBRE")
    private String segundonombre;
    @Basic(optional = false)


    @Column(name = "PRIMERAPELLIDO")
    private String primerapellido;
    @Basic(optional = false)


    @Column(name = "SEGUNDOAPELLIDO")
    private String segundoapellido;
    @Basic(optional = false)

    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)

    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "NOMBRECOMERCIAL")
    private String nombrecomercial;
    @Basic(optional = false)

    @Size(min = 1, max = 150)
    @Column(name = "REGIMENRENTA")
    private String regimenrenta;
    @Basic(optional = false)

    @Size(min = 1, max = 150)
    @Column(name = "REGIMENIVA")
    private String regimeniva;
    @Basic(optional = false)

    @Column(name = "TARIFARETEIVA")
    private double tarifareteiva;
    @Basic(optional = false)

    @Size(min = 1, max = 150)
    @Column(name = "REGIMENICA")
    private String regimenica;
    @Basic(optional = false)
 
    @Column(name = "ESTADOTERCERO")
    private int estadotercero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidempleado", fetch = FetchType.EAGER)
    private List<Empleado> empleadoList;
    @JoinColumn(name = "CIUDADTERCERO", referencedColumnName = "IDCIUDAD")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadtercero;
    @JoinColumn(name = "TIPORETENCIONRENTAID", referencedColumnName = "IDRETENCIONRENTA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Retencionrenta tiporetencionrentaid;
    @JoinColumn(name = "TIPORETENCIONICAID", referencedColumnName = "IDRETENCIONICA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Retencionica tiporetencionicaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroid", fetch = FetchType.EAGER)
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroid", fetch = FetchType.EAGER)
    private List<Proveedor> proveedorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tercerousuario", fetch = FetchType.EAGER)
    private List<Usuario> usuarioList;

    public Tercero() {
    }

    public Tercero(Integer idtercero) {
        this.idtercero = idtercero;
    }

    public Tercero(Integer idtercero, String tipodocumento, int numerodocumento, String primernombre, String segundonombre, String primerapellido, String segundoapellido, String telefono, String direccion, String correo, String regimenrenta, String regimeniva, double tarifareteiva, String regimenica, int estadotercero) {
        this.idtercero = idtercero;
        this.tipodocumento = tipodocumento;
        this.numerodocumento = numerodocumento;
        this.primernombre = primernombre;
        this.segundonombre = segundonombre;
        this.primerapellido = primerapellido;
        this.segundoapellido = segundoapellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.regimenrenta = regimenrenta;
        this.regimeniva = regimeniva;
        this.tarifareteiva = tarifareteiva;
        this.regimenica = regimenica;
        this.estadotercero = estadotercero;
    }

    public Integer getIdtercero() {
        return idtercero;
    }

    public void setIdtercero(Integer idtercero) {
        this.idtercero = idtercero;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public int getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(int numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getRegimenrenta() {
        return regimenrenta;
    }

    public void setRegimenrenta(String regimenrenta) {
        this.regimenrenta = regimenrenta;
    }

    public String getRegimeniva() {
        return regimeniva;
    }

    public void setRegimeniva(String regimeniva) {
        this.regimeniva = regimeniva;
    }

    public double getTarifareteiva() {
        return tarifareteiva;
    }

    public void setTarifareteiva(double tarifareteiva) {
        this.tarifareteiva = tarifareteiva;
    }

    public String getRegimenica() {
        return regimenica;
    }

    public void setRegimenica(String regimenica) {
        this.regimenica = regimenica;
    }

    public int getEstadotercero() {
        return estadotercero;
    }

    public void setEstadotercero(int estadotercero) {
        this.estadotercero = estadotercero;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Ciudad getCiudadtercero() {
        return ciudadtercero;
    }

    public void setCiudadtercero(Ciudad ciudadtercero) {
        this.ciudadtercero = ciudadtercero;
    }

    public Retencionrenta getTiporetencionrentaid() {
        return tiporetencionrentaid;
    }

    public void setTiporetencionrentaid(Retencionrenta tiporetencionrentaid) {
        this.tiporetencionrentaid = tiporetencionrentaid;
    }

    public Retencionica getTiporetencionicaid() {
        return tiporetencionicaid;
    }

    public void setTiporetencionicaid(Retencionica tiporetencionicaid) {
        this.tiporetencionicaid = tiporetencionicaid;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtercero != null ? idtercero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tercero)) {
            return false;
        }
        Tercero other = (Tercero) object;
        if ((this.idtercero == null && other.idtercero != null) || (this.idtercero != null && !this.idtercero.equals(other.idtercero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tercero[ idtercero=" + idtercero + " ]";
    }
    
}
