/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Cargo;
import facades.CargoFacade;
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
@Named(value = "cargoControlador")
@SessionScoped
public class CargoControlador implements Serializable {

    @EJB 
    CargoFacade cargofacade;
    private Cargo cargo;
    private String data ="";
    
    public CargoControlador() {
        cargo=new Cargo();
    }

    public Cargo getCargo() {
        return cargo;
    }
    
    

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
     public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    //Inicio de los metodos crud
     public List<Cargo> listaCargo(){
        return cargofacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
        try {
            if (cargo.getNombrecargo().isEmpty() || cargo.equals(null) || cargo.getNombrecargo()==null) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre")); 
            }else if(cargo.getSalariominimo()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario minimo")); 
            }else if(cargo.getSalariomaximo()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario maximo")); 
            }else{
            cargofacade.create(cargo);
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage("", mensajeOK);
            cargo= new Cargo(); 
            }  
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se pudo Registrar"));
        }
           
    }
    
    
    
    //eliminar de un dato
    public void eliminar(Cargo c){
        try {  
        cargo= c;
        cargofacade.remove(cargo);
        cargo= new Cargo(); 
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }
    }
    
    
    //Carga del dato selecionado
    public String preEditar(Cargo c){
        cargo=c;
        return "ModificarCargo?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
            if (cargo.getNombrecargo().isEmpty() || cargo.equals(null) || cargo.getNombrecargo()==null) {
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el nombre")); 
            }else if(cargo.getSalariominimo()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario minimo")); 
            }else if(cargo.getSalariomaximo()<=0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Por favor ingrese el salario maximo")); 
            }else{
        cargofacade.edit(cargo);
        cargo= new Cargo();
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Modifico el registro");
         FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        return "ListaCargo?faces-redirect=true";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
             return "ListaCargo?faces-redirect=true";
        }

        return "ListaCargo?faces-redirect=true";
    }
    
    
    public String datosGrafica() {
        data = "";
        
        for (Cargo car : cargofacade.findAll()) {
            data = data + "{y: " + car.getSalariominimo() + ", name:\"" + car.getNombrecargo() + "\", exploded: true},";
           
        }

        return data;
    }

   


   
    
}
