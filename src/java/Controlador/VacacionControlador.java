/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Empleado;
import entidades.Vacacionempleado;
import facades.VacacionempleadoFacade;
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
@Named(value = "vacacionControlador")
@SessionScoped
public class VacacionControlador implements Serializable {

    @EJB 
    VacacionempleadoFacade vacacionempleadoFacade;
    private Vacacionempleado  vacacionempleado;
    private Empleado empleado;
    
    public VacacionControlador() {
        vacacionempleado= new Vacacionempleado ();
        empleado = new Empleado();
    }

    public Vacacionempleado getVacacionempleado() {
        return vacacionempleado;
    }

    public void setVacacionempleado(Vacacionempleado vacacionempleado) {
        this.vacacionempleado = vacacionempleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    //Inicio de los metodos crud
     public List<Vacacionempleado> listavacacionempleado(){
        return vacacionempleadoFacade.findAll();
    }
          
    //Registro de un dato
    public void registrar(){
  
        try {
            if (vacacionempleado.getFechainicio() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos tipo fechas son obligatorios")); 
            }else if (vacacionempleado.getFechafin() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos tipo fechas son obligatorios")); 
            }else if (vacacionempleado.getFechainicio().equals(vacacionempleado.getFechafin())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "No puede iniciar vacaciones y terminarlas el mismo dìa")); 
            }else if (vacacionempleado.getFechainicio().after(vacacionempleado.getFechafin())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La fecha fin no puede ser menor a la fecha de inicio")); 
            }else if(vacacionempleado.getDiaspagos()<=0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese el total de dìas de vacaciones")); 
            }else if(vacacionempleado.getDescuentosley()==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese los descuentos de ley")); 
            }else if(vacacionempleado.getTotalpago()==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese el total a pagar")); 
            }else{
        vacacionempleado.setEmpleadoid(empleado);
        vacacionempleadoFacade.create(vacacionempleado);
        FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, " Registro Exitoso");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        vacacionempleado = new Vacacionempleado(); 
            }
        } catch (Exception e) {
            
        }
    }
    
    
    
    //eliminar de un dato
    public void eliminar(Vacacionempleado c){
        try {
        vacacionempleado = c;
        vacacionempleadoFacade.remove(vacacionempleado);
        vacacionempleado = new Vacacionempleado();
            FacesMessage mensajeOK = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Se Elimino el registro");
        FacesContext.getCurrentInstance().addMessage("", mensajeOK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede eliminar, hay información relacionada")); 
        }
        vacacionempleado = c;
        vacacionempleadoFacade.remove(vacacionempleado);
        vacacionempleado = new Vacacionempleado();

    }
    
    
    //Carga del dato selecionado
    public String preEditar(Vacacionempleado c){
        vacacionempleado=c;
        return "ModificaVacacion?faces-redirect=true";
    }
    
    
    //metodo para registrar el nuevo dato
    public String editar(){
        try {
             if (vacacionempleado.getFechainicio() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos tipo fechas son obligatorios")); 
            }else if (vacacionempleado.getFechafin() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Los campos tipo fechas son obligatorios")); 
            }else if (vacacionempleado.getFechainicio().equals(vacacionempleado.getFechafin())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "No puede iniciar vacaciones y terminarlas el mismo dìa")); 
            }else if (vacacionempleado.getFechainicio().after(vacacionempleado.getFechafin())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La fecha fin no puede ser menor a la fecha de inicio")); 
            }else if(vacacionempleado.getDiaspagos()<=0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese el total de dìas de vacaciones")); 
            }else if(vacacionempleado.getDescuentosley()==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese los descuentos de ley")); 
            }else if(vacacionempleado.getTotalpago()==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Ingrese el total a pagar")); 
            }else{
            vacacionempleadoFacade.edit(vacacionempleado);
        vacacionempleado = new Vacacionempleado();
        return "ListaVacaciones?faces-redirect=true";}
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, " No se puede Modificar por el momento")); 
             return null;
        }
        return "ListaVacaciones?faces-redirect=true";
    }

    
}
