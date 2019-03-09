/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Entidadseguridad;
import facades.EntidadseguridadFacade;
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
@Named(value = "entidadesControlador")
@SessionScoped
public class EntidadesControlador implements Serializable {

    @EJB 
    EntidadseguridadFacade entidadseguridadfacade;
    private Entidadseguridad entidadseguridad;
    
    public EntidadesControlador() {
        entidadseguridad = new Entidadseguridad();
    }

    public Entidadseguridad getEntidadseguridad() {
        return entidadseguridad;
    }

    public void setEntidadseguridad(Entidadseguridad entidadseguridad) {
        this.entidadseguridad = entidadseguridad;
    }
    
    //Inicio de los metodos crud
     public List<Entidadseguridad> listaentidadseguridad(){
        return entidadseguridadfacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (entidadseguridad.getNombreentidad().isEmpty() || entidadseguridad.getNombreentidad().equals(null) || entidadseguridad.getNombreentidad()==null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));   
            }else if(entidadseguridad.getCodigoentidad().equals(null) || entidadseguridad.getCodigoentidad().isEmpty() || entidadseguridad.getCodigoentidad()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el Código")); 
            }else{
            entidadseguridadfacade.create(entidadseguridad);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
            entidadseguridad = new Entidadseguridad(); 
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar")); 
        }     
    }
    
    
    
    //eliminar de un dato
    public void eliminar(Entidadseguridad e){
        try {
        entidadseguridad = e;
        entidadseguridadfacade.remove(entidadseguridad);
        entidadseguridad = new Entidadseguridad(); 
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception l) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }
 

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Entidadseguridad e){
        entidadseguridad=e;
        return "ModificaEntidades?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (entidadseguridad.getNombreentidad().isEmpty() || entidadseguridad.getNombreentidad().equals(null) || entidadseguridad.getNombreentidad()==null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));   
            }else if(entidadseguridad.getCodigoentidad().equals(null) || entidadseguridad.getCodigoentidad().isEmpty() || entidadseguridad.getCodigoentidad()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el Código")); 
            }else{
            entidadseguridadfacade.edit(entidadseguridad);
        entidadseguridad = new Entidadseguridad();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaEntidades?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
            return "ListaEntidades?faces-redirect=true";
        }
        return "ListaEntidades?faces-redirect=true";
    }

    
}
