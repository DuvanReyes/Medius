/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Ciudad;
import entidades.Proveedor;
import entidades.Tercero;
import facades.ProveedorFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arco02
 */
@Named(value = "proveedorControlador")
@SessionScoped
public class ProveedorControlador implements Serializable {

    @EJB 
    ProveedorFacade proveedorfaccade;
    private Proveedor proveedor;
    private Proveedor proveedorSeleccionado;
    private Ciudad ciudad;
    private Tercero tercero;
    private boolean showpopup;
    
    
    public ProveedorControlador() {
        proveedor= new Proveedor();
        ciudad = new Ciudad();
        tercero = new Tercero();
        proveedorSeleccionado= new Proveedor();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }
    
        public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    public void Consultauno(Proveedor in){
        showpopup=true;
        proveedor=in;
        int id=proveedor.getIdproveedor();
       proveedorSeleccionado=proveedorfaccade.find(id);
    }
    public void cerrar(){
        showpopup=false;
    }
    
    //Inicio de los metodos crud
     public List<Proveedor> listaProveedor(){
        return proveedorfaccade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
        proveedor.setCiudadid(ciudad);
        proveedor.setTerceroid(tercero);
        proveedorfaccade.create(proveedor);
         FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
         proveedor = new Proveedor();
  
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
            
    }
    
    
    
    //eliminar de un dato
    public void eliminar(Proveedor p){
        try {
         proveedor = p;
        proveedorfaccade.remove(proveedor);
        proveedor = new Proveedor();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }
        
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Proveedor p){
        proveedor=p;
        return "ModificaProveedor?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        
        try {
        proveedorfaccade.edit(proveedor);
        proveedor = new Proveedor();
         FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaProveedores?faces-redirect=true";
        } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
           return "ListaProveedores?faces-redirect=true";
        }
    }    
}
