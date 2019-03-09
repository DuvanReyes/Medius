/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Reteiva;
import facades.ReteivaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author acer
 */
@Named(value = "grupoIvaControlador")
@SessionScoped
public class GrupoIvaControlador implements Serializable {

    @EJB ReteivaFacade reteivafacade;
    private Reteiva reteiva;
    
    public GrupoIvaControlador() {
        reteiva = new Reteiva();
    }

    public Reteiva getReteiva() {
        return reteiva;
    }

    public void setReteiva(Reteiva reteiva) {
        this.reteiva = reteiva;
    }
    
    //Inicio de los metodos crud
     public List<Reteiva> listaReteiva(){
        return reteivafacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (reteiva.getNombrereteiva().equals(null) || reteiva.getNombrereteiva().isEmpty() || reteiva.getNombrereteiva()==null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"Por favor ingrese el nombre"));    
            }else if(reteiva.getTasareteiva()<=0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo % Retención Iva no puede estar vacío"));
            }else{
            reteivafacade.create(reteiva);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
           reteiva = new Reteiva();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }


    }
    
    //eliminar de un dato
    public void eliminar(Reteiva i){
        try {
           reteiva = i;
        reteivafacade.remove(i);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        reteiva = new Reteiva(); 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }
        

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Reteiva i){
        reteiva=i;
        return "ModificaGrupoIva?faces-redirect=true";
    }
    
   
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (reteiva.getNombrereteiva().equals(null) || reteiva.getNombrereteiva().isEmpty() || reteiva.getNombrereteiva()==null) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"Por favor ingrese el nombre"));    
            }else if(reteiva.getTasareteiva()<=0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo % Retención Iva no puede estar vacío"));
            }else{
            reteivafacade.edit(reteiva);
        reteiva = new Reteiva();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);;
        return "ListaGruposIva?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
        }
        return null;
    }  
}
