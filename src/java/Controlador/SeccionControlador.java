/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Seccion;
import facades.SeccionFacade;
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
@Named(value = "seccionControlador")
@SessionScoped
public class SeccionControlador implements Serializable {

   @EJB
   SeccionFacade seccionfacade;
   private Seccion seccion;
   
    public SeccionControlador() {
        seccion =new Seccion();
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    
     //Inicio de los metodos crud
     public List<Seccion> listaSeccion(){
        return seccionfacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (seccion.getNombreseccion().isEmpty() || seccion.getNombreseccion().equals(null) || seccion.getNombreseccion()==null) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre")); 
            }else{
            seccionfacade.create(seccion);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
           seccion= new Seccion();  
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }

    }
    
    
    
    //eliminar de un dato
    public void eliminar(Seccion c){
        try {
         seccion= c;
        seccionfacade.remove(seccion);
        seccion= new Seccion();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }
        

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Seccion c){
        seccion=c;
        return "ModificaSeccion?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (seccion.getNombreseccion().isEmpty() || seccion.getNombreseccion().equals(null) || seccion.getNombreseccion()==null) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre")); 
            }else{
            seccionfacade.edit(seccion);
        seccion= new Seccion();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaSeccion?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
            return "ListaSeccion?faces-redirect=true";
        }

        return "ListaSeccion?faces-redirect=true";
    }

}
