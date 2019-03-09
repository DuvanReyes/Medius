/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Rol;
import facades.RolFacade;
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
@Named(value = "rolControlador")
@SessionScoped
public class RolControlador implements Serializable {

    @EJB
    RolFacade rolfacade;
    private Rol rol;
    
    public RolControlador() {
        rol = new Rol();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
     //Inicio de los metodos crud
     public List<Rol> listarol(){
        return rolfacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (rol.getNombrerol().isEmpty() || rol.getNombrerol().equals(null) || rol.getNombrerol()==null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,null, "Por favor ingrese el nombre"));   
            }else{
            rolfacade.create(rol);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
           rol = new Rol();        
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
        
      
    }
    
    
    
    //eliminar de un dato
    public void eliminar(Rol c){
        try {
         rol = c;
        rolfacade.remove(rol);
        rol = new Rol(); 
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }
       
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Rol c){
        rol=c;
        return "ModificaRol?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (rol.getNombrerol().isEmpty() || rol.getNombrerol().equals(null) || rol.getNombrerol()==null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,null, "Por favor ingrese el nombre"));   
            }else{
            rolfacade.edit(rol);
        rol = new Rol();
        return "ListaRoles?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
             return null;
        }

        return null;
    }

    
}
