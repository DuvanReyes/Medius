/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Retencionrenta;
import facades.RetencionrentaFacade;
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
@Named(value = "retencionRentaControlador")
@SessionScoped
public class RetencionRentaControlador implements Serializable {

    @EJB RetencionrentaFacade retencionrentafacade;
    private Retencionrenta retencionrenta;
    private Retencionrenta retencionrentaSeleccionado;
    private boolean showpopup;
    
    public RetencionRentaControlador() {
        retencionrenta= new Retencionrenta();
        retencionrentaSeleccionado= new Retencionrenta();
    }

    public Retencionrenta getRetencionrenta() {
        return retencionrenta;
    }

    public void setRetencionrenta(Retencionrenta retencionrenta) {
        this.retencionrenta = retencionrenta;
    }
    
        public Retencionrenta getRetencionrentaSeleccionado() {
        return retencionrentaSeleccionado;
    }

    public void setRetencionrentaSeleccionado(Retencionrenta retencionrentaSeleccionado) {
        this.retencionrentaSeleccionado = retencionrentaSeleccionado;
    }

    public boolean isShowpopup() {
        return showpopup;
    }

    public void setShowpopup(boolean showpopup) {
        this.showpopup = showpopup;
    }
    
     public void Consultauno(Retencionrenta in){
        showpopup=true;
        retencionrenta=in;
        int id=retencionrenta.getIdretencionrenta();
        retencionrentaSeleccionado=retencionrentafacade.find(id);
    }
    public void cerrar(){
        showpopup=false;
    }
    
    //Inicio de los metodos crud
     public List<Retencionrenta> listaRetencionrenta(){
        return retencionrentafacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (retencionrenta.getNombreretencionrenta().equals(null) || retencionrenta.getNombreretencionrenta()==null || retencionrenta.getNombreretencionrenta().isEmpty()) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"Por favor ingrese el nombre"));  
            }else if(retencionrenta.getTasaretencionrenta()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo % Retención Renta no puede estar vacio"));
            }else if(retencionrenta.getUvtretencionrenta()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo Del UVT  no puede estar vacio"));
            }else{
            retencionrentafacade.create(retencionrenta);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
           retencionrenta = new Retencionrenta(); 
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
              
       
      
    }
     
    //eliminar de un dato
    public void eliminar(Retencionrenta c){
        try {
           retencionrenta = c;
        retencionrentafacade.remove(retencionrenta);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        retencionrenta = new Retencionrenta(); 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada"));
        }
        

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Retencionrenta c){
        retencionrenta=c;
        return "ModificaRetencionRenta?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (retencionrenta.getNombreretencionrenta().equals(null) || retencionrenta.getNombreretencionrenta()==null || retencionrenta.getNombreretencionrenta().isEmpty()) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"Por favor ingrese el nombre"));  
            }else if(retencionrenta.getTasaretencionrenta()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo % Retención Renta no puede estar vacio"));
            }else if(retencionrenta.getUvtretencionrenta()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null,"El campo Del UVT  no puede estar vacio"));
            }else{
            retencionrentafacade.edit(retencionrenta);
        retencionrenta = new Retencionrenta();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaRetencionRenta?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
            return null;
        }

        return null;
    }

    
    
}
