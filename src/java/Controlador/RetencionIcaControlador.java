/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Retencionica;
import facades.RetencionicaFacade;
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
@Named(value = "retencionIcaControlador")
@SessionScoped
public class RetencionIcaControlador implements Serializable {

    @EJB RetencionicaFacade retencionicafacade; 
    private Retencionica retencionica;
    private Retencionica retencionicaSelecionado;
     private boolean showpopup;
    
    public RetencionIcaControlador() {
        retencionica = new Retencionica();
        retencionicaSelecionado=new Retencionica();
    }

    public Retencionica getRetencionica() {
        return retencionica;
    }

    public void setRetencionica(Retencionica retencionica) {
        this.retencionica = retencionica;
    }
    
    
    public Retencionica getRetencionicaSelecionado() {
        return retencionicaSelecionado;
    }

    public void setRetencionicaSelecionado(Retencionica retencionicaSelecionado) {
        this.retencionicaSelecionado = retencionicaSelecionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }
    
     public void Consultauno(Retencionica in){
        showpopup=true;
        retencionica=in;
        int id=retencionica.getIdretencionica();
        retencionicaSelecionado=retencionicafacade.find(id);
    }
     
    public void cerrar(){
        showpopup=false;
    }
    
    //Inicio de los metodos crud
     public List<Retencionica> listaRetencionica(){
        return retencionicafacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (retencionica.getNombreretencionica().equals(null) || retencionica.getNombreretencionica().isEmpty() || retencionica.getNombreretencionica()==null) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"Por favor ingrese el nombre"));   
            }else if(retencionica.getTasaretencionica()<=0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo % Retención ICA no puede estar vacio"));
            }else if(retencionica.getUvtretencionica()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo Del UVT  no puede estar vacio"));
            }else{
            retencionicafacade.create(retencionica);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
            retencionica = new Retencionica();   
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }     
    }
    
    //eliminar de un dato
    public void eliminar(Retencionica c){
        try {
        retencionica = c;
        retencionicafacade.remove(retencionica);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        retencionica = new Retencionica();  
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }


    }
    
    
    //Carga del dato selecionado
    public String preEditar(Retencionica c){
        retencionica=c;
        return "ModificaRetencionIca?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (retencionica.getNombreretencionica().equals(null) || retencionica.getNombreretencionica().isEmpty() || retencionica.getNombreretencionica()==null) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"Por favor ingrese el nombre"));   
            }else if(retencionica.getTasaretencionica()<=0){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo % Retención ICA no puede estar vacio"));
            }else if(retencionica.getUvtretencionica()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo Del UVT  no puede estar vacio"));
            }else{
            retencionicafacade.edit(retencionica);
        retencionica = new Retencionica();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaRetencionIca?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
            return null;
        }
        return null;
    }
  
}
