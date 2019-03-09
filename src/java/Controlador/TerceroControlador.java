/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import entidades.Ciudad;
import entidades.Retencionica;
import entidades.Retencionrenta;
import entidades.Tercero;
import facades.TerceroFacade;
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
@Named(value = "terceroControlador")
@SessionScoped
public class TerceroControlador implements Serializable {

     @EJB TerceroFacade tercerofacade;
     private Tercero tercero;
      private Tercero terceroSeleccionado;
     private Ciudad ciudad;
     private Retencionrenta retencionrenta;
     private Retencionica  retencionica;
     private boolean showpopup;
     
    public TerceroControlador() {
        tercero = new Tercero();
        ciudad = new Ciudad();
        retencionrenta=new Retencionrenta();
        retencionica=new Retencionica();
        terceroSeleccionado= new Tercero();
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }
    
    
     public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Retencionrenta getRetencionrenta() {
        return retencionrenta;
    }

    public void setRetencionrenta(Retencionrenta retencionrenta) {
        this.retencionrenta = retencionrenta;
    }

    public Retencionica getRetencionica() {
        return retencionica;
    }

    public void setRetencionica(Retencionica retencionica) {
        this.retencionica = retencionica;
    }
    
    
    public Tercero getTerceroSeleccionado() {
        return terceroSeleccionado;
    }

    public void setTerceroSeleccionado(Tercero terceroSeleccionado) {
        this.terceroSeleccionado = terceroSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }

    public void Consultauno(Tercero in){
        showpopup=true;
        tercero=in;
        int id=tercero.getIdtercero();
       terceroSeleccionado=tercerofacade.find(id);
    }
    public void cerrar(){
        showpopup=false;
    }
    
    //Inicio de los metodos crud
     public List<Tercero> listaTercero(){
        return tercerofacade.findAll();
    }
     
      //Registro de un dato
    public void registrar(){
        try {
            if(tercero.getNumerodocumento()<=0 ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el numero del documento")); 
            }else if (tercero.getPrimernombre().isEmpty() || tercero.getPrimernombre().equals(null) || tercero.getPrimernombre()==null) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el primer nombre"));   
            }else if(tercero.getPrimerapellido().isEmpty() || tercero.getPrimerapellido().equals(null) || tercero.getPrimerapellido()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el primer apellido"));   
            }else if(tercero.getTelefono().equals(null) || tercero.getTelefono().isEmpty() || tercero.getTelefono()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el numero teléfonico"));   
            }else if(tercero.getDireccion().isEmpty() || tercero.getDireccion().equals(null) || tercero.getDireccion()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese la dirección"));   
            }else if(tercero.getCorreo().isEmpty() || tercero.getCorreo().equals(null) || tercero.getCorreo()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el correo"));   
            }else{
         tercero.setCiudadtercero(ciudad);
        tercero.setTiporetencionrentaid(retencionrenta);
        tercero.setTiporetencionicaid(retencionica);
        tercerofacade.create(tercero);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        tercero = new Tercero();     
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
          

      
    }
    
    //eliminar de un dato
    public void eliminar(Tercero t){
        try {
         tercero = t;
        tercerofacade.remove(tercero);
        tercero = new Tercero();
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }
        
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Tercero t){
        tercero=t;
        return "ModificaTercero?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if(tercero.getNumerodocumento()<=0 ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el numero del documento")); 
            }else if (tercero.getPrimernombre().isEmpty() || tercero.getPrimernombre().equals(null) || tercero.getPrimernombre()==null) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el primer nombre"));   
            }else if(tercero.getPrimerapellido().isEmpty() || tercero.getPrimerapellido().equals(null) || tercero.getPrimerapellido()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el primer apellido"));   
            }else if(tercero.getTelefono().equals(null) || tercero.getTelefono().isEmpty() || tercero.getTelefono()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el numero teléfonico"));   
            }else if(tercero.getDireccion().isEmpty() || tercero.getDireccion().equals(null) || tercero.getDireccion()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese la dirección"));   
            }else if(tercero.getCorreo().isEmpty() || tercero.getCorreo().equals(null) || tercero.getCorreo()==null){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el correo"));   
            }else{
            tercerofacade.edit(tercero);
        tercero = new Tercero();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaTerceros?faces-redirect=true";
            }
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
              return "ListaTerceros?faces-redirect=true";
        }
        return "ListaTerceros?faces-redirect=true";
    }
    
}
