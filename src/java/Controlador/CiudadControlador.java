/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Ciudad;
import facades.CiudadFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author Arco02
 */
@Named(value = "ciudadControlador")
@SessionScoped
public class CiudadControlador implements Serializable {

    @EJB CiudadFacade ciudadfacade;
    private Ciudad ciudad;


  
    
    public CiudadControlador() {
        ciudad=new Ciudad();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }


    //Inicio de los metodos crud
     public List<Ciudad> listaciudad(){
        return ciudadfacade.findAll();
    }
          
    //Registro de un dato
    public String registrar() throws Exception{
        try {
          Ciudad codigoCiudad;
        codigoCiudad= ciudadfacade.buscaCodigo(ciudad);
        if (codigoCiudad!=null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "El número de la ciudad ya se encuentra registrado"));
        }else if(ciudad.getNumerociudad()<=0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese el número de la ciudad")); 
        }else if(ciudad.getCiudadnombre().isEmpty() || ciudad.getCiudadnombre().equals(null) || ciudad.getCiudadnombre()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));
        }else{
         ciudadfacade.create(ciudad);
         FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        ciudad = new Ciudad(); 
        return "ListaCiudades?faces-redirect=true";
        }  
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
        return null;
        
    }
    
    
    //eliminar de un dato
    public void eliminar(Ciudad c){
        try {
        ciudad = c;
        ciudadfacade.remove(ciudad);
        ciudad = new Ciudad();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Ciudad c){
        ciudad=c;
        return "ModificaCiudad?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
          if(ciudad.getNumerociudad()<=0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese el número de la ciudad")); 
        }else if(ciudad.getCiudadnombre().isEmpty() || ciudad.getCiudadnombre().equals(null) || ciudad.getCiudadnombre()==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre"));
        }else{
        ciudadfacade.edit(ciudad);
        ciudad = new Ciudad();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaCiudades?faces-redirect=true";
        }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
             return null;
        }
 
        return null;
    }

   
    
}
